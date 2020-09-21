/*     */ package com.linlongyx.sanguo.cross;
/*     */ 
/*     */ import com.linlongyx.sanguo.cross.event.IRequestEvent;
/*     */ import com.linlongyx.sanguo.cross.handler.CrossClientHandler;
/*     */ import com.linlongyx.sanguo.cross.proto.add.AddRequest;
/*     */ import com.linlongyx.sanguo.cross.proto.add.AddResponse;
/*     */ import com.linlongyx.sanguo.cross.proto.login.ClientConnectRequest;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import io.netty.bootstrap.Bootstrap;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.channel.socket.nio.NioSocketChannel;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class CrossClient implements Runnable {
/*  24 */   private static final Logger LOG = LoggerFactory.getLogger(CrossClient.class);
/*     */   
/*     */   private static final int MAX_RECONNECT_COUNT = 50;
/*     */   
/*     */   private final String host;
/*     */   private final int port;
/*     */   private int reConnectCount;
/*     */   private volatile boolean isRunning;
/*  32 */   private ExecutorService executorService = Executors.newSingleThreadExecutor();
/*     */   private CrossClientHandler crossClientHandler;
/*  34 */   private EventLoopGroup eventLoopGroup = (EventLoopGroup)new NioEventLoopGroup(1);
/*  35 */   private Bootstrap bootstrap = createBootstrap();
/*     */   
/*     */   public CrossClient(String host, int port) {
/*  38 */     this(host, port, 10);
/*     */   }
/*     */   
/*     */   public CrossClient(String host, int port, int reConnectCount) {
/*  42 */     this.host = host;
/*  43 */     this.port = port;
/*  44 */     this.reConnectCount = reConnectCount;
/*     */   }
/*     */   
/*     */   public MessageCallback sendMessage(IRequestEvent request) {
/*  48 */     if (this.isRunning) {
/*  49 */       LOG.debug("send request...");
/*  50 */       return this.crossClientHandler.sendRequest(request);
/*     */     } 
/*  52 */     LOG.error("已与服务器{}:{}断开连接", this.host, Integer.valueOf(this.port));
/*     */     
/*  54 */     return null;
/*     */   }
/*     */   
/*     */   private Bootstrap createBootstrap() {
/*  58 */     this.crossClientHandler = new CrossClientHandler();
/*  59 */     Bootstrap bootstrap = new Bootstrap();
/*  60 */     ((Bootstrap)((Bootstrap)bootstrap.group(this.eventLoopGroup))
/*  61 */       .channel(NioSocketChannel.class))
/*  62 */       .handler((ChannelHandler)new CrossClientChannelInitializer(this.crossClientHandler));
/*  63 */     return bootstrap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  68 */     connect(this.host, this.port);
/*     */   }
/*     */   
/*     */   public void connect() {
/*  72 */     this.executorService.submit(this);
/*     */   }
/*     */   
/*     */   public void sendConnectRequest() {
/*  76 */     if (this.isRunning) {
/*  77 */       ClientConnectRequest request = new ClientConnectRequest();
/*  78 */       request.setId(1000);
/*  79 */       request.setMark((byte)0);
/*  80 */       request.setServerId(1001L);
/*  81 */       sendMessage((IRequestEvent)request);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void connect(String host, int port) {
/*     */     try {
/*  87 */       ChannelFuture f = this.bootstrap.remoteAddress(host, port).connect().sync();
/*  88 */       f.addListener(future -> {
/*     */             if (future.isSuccess()) {
/*     */               this.reConnectCount = 0;
/*     */               
/*     */               this.isRunning = true;
/*     */               LOG.info("与服务器{}:{}建立连接成功", host, Integer.valueOf(port));
/*     */               sendConnectRequest();
/*     */             } else {
/*     */               this.isRunning = false;
/*     */               LOG.info("与服务器{}:{}建立连接失败", host, Integer.valueOf(port));
/*     */             } 
/*     */           });
/* 100 */       f.channel().closeFuture().sync();
/* 101 */     } catch (InterruptedException e) {
/* 102 */       this.isRunning = false;
/* 103 */       LOG.error("与服务器{}:{}建立连接失败{}", new Object[] { host, Integer.valueOf(port), e.getMessage() });
/*     */     } finally {
/* 105 */       this.isRunning = false;
/* 106 */       int delay = ++this.reConnectCount * 5;
/* 107 */       this.reConnectCount = (this.reConnectCount > 50) ? 50 : this.reConnectCount;
/* 108 */       LOG.error("与服务器{}:{}连接断开,{}秒后重连.", new Object[] { host, Integer.valueOf(port), Integer.valueOf(delay) });
/* 109 */       this.eventLoopGroup.schedule(() -> connect(host, port), delay, TimeUnit.SECONDS);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 117 */     this.eventLoopGroup.shutdownGracefully();
/* 118 */     this.executorService.shutdown();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws InterruptedException {
/* 122 */     ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();
/* 123 */     CrossClient client = new CrossClient("127.0.0.1", 19650);
/* 124 */     client.connect();
/*     */ 
/*     */     
/* 127 */     EXECUTOR.scheduleAtFixedRate(() -> {
/*     */           int num = RandUtil.randNum(100);
/*     */           AddRequest event = new AddRequest();
/*     */           event.setId(1001);
/*     */           event.setPlayerId(1000000001L);
/*     */           event.setServerId(10001L);
/*     */           event.setMark((byte)1);
/*     */           event.setA(10);
/*     */           event.setB(num);
/*     */           MessageCallback result = client.sendMessage((IRequestEvent)event);
/*     */           try {
/*     */             AddResponse response = (AddResponse)result.start();
/*     */             System.out.println("10 add " + num + ", is " + response.getResult());
/* 140 */           } catch (InterruptedException e) {
/*     */             System.out.println(e.getMessage());
/*     */           } 
/*     */         }1L, 1L, TimeUnit.SECONDS);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\CrossClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */