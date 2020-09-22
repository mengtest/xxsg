/*     */ package com.linlongyx.sanguo.logclient;
/*     */ import com.linlongyx.sanguo.logclient.coder.EventDecoder;
/*     */ import com.linlongyx.sanguo.logclient.coder.EventEncoder;
/*     */ import com.linlongyx.sanguo.logclient.coder.ShareCoders;
/*     */ import com.linlongyx.sanguo.logclient.event.Event;
/*     */ import com.linlongyx.sanguo.logclient.event.Events;
/*     */ import com.linlongyx.sanguo.logclient.handler.ClientHandler;
/*     */ import com.linlongyx.sanguo.logclient.handler.MsgPackDecoder;
/*     */ import com.linlongyx.sanguo.logclient.handler.MsgPackEncoder;
/*     */ import io.netty.bootstrap.Bootstrap;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.channel.socket.SocketChannel;
/*     */ import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
/*     */ import io.netty.handler.codec.LengthFieldPrepender;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ChatLogClient implements Runnable {
/*  36 */   private static final Logger LOG = LoggerFactory.getLogger(ChatLogClient.class);
/*     */   
/*     */   private static final int MAX_RECONNECT_COUNT = 50;
/*     */   
/*     */   private final String host;
/*     */   private final int port;
/*     */   private boolean isOpen;
/*     */   private int reConnectCount;
/*  44 */   private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
/*     */   private ClientHandler clientHandler;
/*  46 */   private EventLoopGroup eventLoopGroup = (EventLoopGroup)new NioEventLoopGroup(1);
/*  47 */   private Bootstrap bootstrap = createBootstrap();
/*     */   
/*     */   private DiskQueue diskQueue;
/*     */   private volatile boolean isRunning;
/*     */   
/*     */   public ChatLogClient(String host, int port) {
/*  53 */     this(true, host, port, 0, System.getProperty("user.dir") + File.separator + "chatlog");
/*     */   }
/*     */   
/*     */   public ChatLogClient(boolean isOpen, String host, int port) {
/*  57 */     this(isOpen, host, port, 0, System.getProperty("user.dir") + File.separator + "chatlog");
/*     */   }
/*     */   
/*     */   public ChatLogClient(boolean isOpen, String host, int port, String dataPath) {
/*  61 */     this(isOpen, host, port, 0, dataPath);
/*     */   }
/*     */   
/*     */   public ChatLogClient(boolean isOpen, String host, int port, int reConnectCount, String dataPath) {
/*  65 */     this.isOpen = isOpen;
/*  66 */     this.host = host;
/*  67 */     this.port = port;
/*  68 */     if (isOpen) {
/*  69 */       this.reConnectCount = reConnectCount;
/*  70 */       this.diskQueue = new DiskQueue("chatLogClient", dataPath, 524288);
/*  71 */       Path path = Paths.get(dataPath, new String[0]);
/*  72 */       if (!Files.exists(path, new java.nio.file.LinkOption[0])) {
/*     */         try {
/*  74 */           Files.createDirectory(path, (FileAttribute<?>[])new FileAttribute[0]);
/*  75 */         } catch (IOException e) {
/*  76 */           LOG.error("Failed to create dataPath {}, {}", dataPath, e.getMessage());
/*     */         } 
/*     */       }
/*  79 */       this.executorService.submit(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/*  84 */     return this.isOpen;
/*     */   }
/*     */   
/*     */   public void sendMessage(Event event) {
/*  88 */     if (!this.isOpen) {
/*     */       return;
/*     */     }
/*  91 */     if (this.isRunning) {
/*  92 */       this.clientHandler.sendMessage(event);
/*     */     } else {
/*     */       try {
/*  95 */         LOG.info("已与服务器{}:{}断开连接，数据将写入本地文件", this.host, Integer.valueOf(this.port));
/*  96 */         this.diskQueue.put(Events.getBytes((EventEncoder)ShareCoders.MSGPACK_EVENT_ENCODER, event));
/*  97 */       } catch (IOException e) {
/*  98 */         LOG.error("{}写入本地文件失败{}", event.toString(), e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 105 */     connect(this.host, this.port);
/*     */   }
/*     */   
/*     */   public void close() {
/* 109 */     if (this.isOpen) {
/* 110 */       this.diskQueue.close();
/* 111 */       this.eventLoopGroup.shutdownGracefully();
/* 112 */       if (!this.executorService.isShutdown()) {
/* 113 */         this.executorService.shutdown();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private Bootstrap createBootstrap() {
/* 119 */     this.clientHandler = new ClientHandler();
/* 120 */     Bootstrap bootstrap = new Bootstrap();
/* 121 */     ((Bootstrap)((Bootstrap)bootstrap.group(this.eventLoopGroup))
/* 122 */       .channel(NioSocketChannel.class))
/* 123 */       .handler((ChannelHandler)new ChannelInitializer<SocketChannel>()
/*     */         {
/*     */           protected void initChannel(SocketChannel socketChannel) throws Exception {
/* 126 */             ChannelPipeline pipeline = socketChannel.pipeline();
/* 127 */             pipeline.addLast("lengthDecoder", (ChannelHandler)new LengthFieldBasedFrameDecoder(2147483647, 0, 2, 0, 2));
/* 128 */             pipeline.addLast("msgDecoder", (ChannelHandler)new MsgPackDecoder());
/* 129 */             pipeline.addLast(new ChannelHandler[] { (ChannelHandler)ChatLogClient.access$000(this.this$0) });
/* 130 */             pipeline.addLast("lengthFieldPrepender", (ChannelHandler)new LengthFieldPrepender(2, false));
/* 131 */             pipeline.addLast("msgEncoder", (ChannelHandler)new MsgPackEncoder());
/*     */           }
/*     */         });
/* 134 */     return bootstrap;
/*     */   }
/*     */   
/*     */   private void connect(String host, int port) {
/*     */     try {
/* 139 */       ChannelFuture f = this.bootstrap.remoteAddress(host, port).connect().sync();
/* 140 */       f.addListener(future -> {
/*     */             if (future.isSuccess()) {
/*     */               this.reConnectCount = 0;
/*     */               
/*     */               this.isRunning = true;
/*     */               LOG.info("与服务器{}:{}建立连接成功", host, Integer.valueOf(port));
/*     */               List<byte[]> bytesList = this.diskQueue.readAllBytesFromFile();
/*     */               int curtime = (int)(System.currentTimeMillis() / 1000L);
/*     */               for (byte[] bytes : bytesList) {
/*     */                 Event event = Events.getObject((EventDecoder)ShareCoders.MSGPACK_EVENT_DECODER, bytes, true, 600, curtime);
/*     */                 if (event != null) {
/*     */                   sendMessage(event);
/*     */                 }
/*     */               } 
/*     */             } else {
/*     */               this.isRunning = false;
/*     */               LOG.info("与服务器{}:{}建立连接失败", host, Integer.valueOf(port));
/*     */             } 
/*     */           });
/* 159 */       f.channel().closeFuture().sync();
/* 160 */     } catch (InterruptedException e) {
/* 161 */       this.isRunning = false;
/* 162 */       LOG.error("与服务器{}:{}建立连接失败{}", new Object[] { host, Integer.valueOf(port), e.getMessage() });
/*     */     } finally {
/* 164 */       this.isRunning = false;
/*     */       
/* 166 */       int delay = ++this.reConnectCount * 5;
/* 167 */       this.reConnectCount = (this.reConnectCount > 50) ? 50 : this.reConnectCount;
/* 168 */       LOG.error("与服务器{}:{}连接断开,{}秒后重连.", new Object[] { host, Integer.valueOf(port), Integer.valueOf(delay) });
/* 169 */       this.executorService.schedule(() -> connect(host, port), delay, TimeUnit.SECONDS);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 191 */     ChatLogClient client = new ChatLogClient(true, "127.0.0.1", 6000);
/* 192 */     Random random = new Random();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\ChatLogClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */