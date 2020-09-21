/*     */ package com.linlongyx.sanguo.client.startup;
/*     */ 
/*     */ import com.linlongyx.sanguo.client.net.WebSocketClientHandler;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoDebugRequest;
/*     */ import io.netty.bootstrap.Bootstrap;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.channel.socket.SocketChannel;
/*     */ import io.netty.channel.socket.nio.NioSocketChannel;
/*     */ import io.netty.handler.codec.http.DefaultHttpHeaders;
/*     */ import io.netty.handler.codec.http.HttpClientCodec;
/*     */ import io.netty.handler.codec.http.HttpHeaders;
/*     */ import io.netty.handler.codec.http.HttpObjectAggregator;
/*     */ import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
/*     */ import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
/*     */ import io.netty.handler.codec.http.websocketx.WebSocketVersion;
/*     */ import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
/*     */ import io.netty.handler.ssl.SslContext;
/*     */ import io.netty.handler.ssl.SslContextBuilder;
/*     */ import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import javax.net.ssl.SSLException;
/*     */ 
/*     */ public class WebSocketClient
/*     */   implements Runnable
/*     */ {
/*     */   private long userId;
/*     */   private String url;
/*     */   SslContext sslCtx;
/*     */   
/*     */   public WebSocketClient(long userId, String ip, int port) {
/*  39 */     this.userId = userId;
/*  40 */     this.url = "ws://" + ip.trim() + ":" + port + "/ws";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     final int port;
/*  47 */     URI uri = null;
/*     */     try {
/*  49 */       uri = new URI(this.url);
/*  50 */     } catch (URISyntaxException e) {
/*  51 */       e.printStackTrace();
/*     */     } 
/*  53 */     String scheme = (uri.getScheme() == null) ? "ws" : uri.getScheme();
/*  54 */     final String host = (uri.getHost() == null) ? "127.0.0.1" : uri.getHost();
/*     */     
/*  56 */     if (uri.getPort() == -1) {
/*  57 */       if ("ws".equalsIgnoreCase(scheme)) {
/*  58 */         port = 80;
/*  59 */       } else if ("wss".equalsIgnoreCase(scheme)) {
/*  60 */         port = 443;
/*     */       } else {
/*  62 */         port = -1;
/*     */       } 
/*     */     } else {
/*  65 */       port = uri.getPort();
/*     */     } 
/*     */     
/*  68 */     if (!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
/*  69 */       System.err.println("Only WS(S) is supported.");
/*     */       
/*     */       return;
/*     */     } 
/*  73 */     boolean ssl = "wss".equalsIgnoreCase(scheme);
/*     */     
/*  75 */     if (ssl) {
/*     */       try {
/*  77 */         this
/*  78 */           .sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
/*  79 */       } catch (SSLException e) {
/*  80 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*  83 */       this.sslCtx = null;
/*     */     } 
/*     */     
/*  86 */     NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
/*     */     
/*     */     try {
/*  89 */       final WebSocketClientHandler handler = new WebSocketClientHandler(WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null, true, (HttpHeaders)new DefaultHttpHeaders()));
/*     */ 
/*     */       
/*  92 */       Bootstrap b = new Bootstrap();
/*  93 */       ((Bootstrap)((Bootstrap)b.group((EventLoopGroup)nioEventLoopGroup))
/*  94 */         .channel(NioSocketChannel.class))
/*  95 */         .handler((ChannelHandler)new ChannelInitializer<SocketChannel>()
/*     */           {
/*     */             protected void initChannel(SocketChannel ch) {
/*  98 */               ChannelPipeline p = ch.pipeline();
/*  99 */               if (WebSocketClient.this.sslCtx != null) {
/* 100 */                 p.addLast(new ChannelHandler[] { (ChannelHandler)this.this$0.sslCtx.newHandler(ch.alloc(), this.val$host, this.val$port) });
/*     */               }
/* 102 */               p.addLast(new ChannelHandler[] { (ChannelHandler)new HttpClientCodec(), (ChannelHandler)new HttpObjectAggregator(8192), (ChannelHandler)WebSocketClientCompressionHandler.INSTANCE, (ChannelHandler)this.val$handler });
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 111 */       Channel ch = b.connect(uri.getHost(), port).sync().channel();
/* 112 */       handler.handshakeFuture().sync();
/*     */       
/* 114 */       LoginInfoDebugRequest request = new LoginInfoDebugRequest();
/* 115 */       request.token = "sanguo" + this.userId;
/*     */ 
/*     */       
/* 118 */       ByteBuf byteBuf = Unpooled.buffer();
/* 119 */       ByteBuf out = Unpooled.buffer();
/* 120 */       request.serial(out);
/* 121 */       byteBuf.writeShort(request.eventRequestId);
/* 122 */       byteBuf.writeByte(request.codeType);
/* 123 */       byteBuf.writeShort(out.readableBytes());
/* 124 */       byteBuf.writeBytes(out);
/*     */       
/* 126 */       ch.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
/* 127 */       ch.closeFuture().sync();
/* 128 */     } catch (InterruptedException e) {
/* 129 */       e.printStackTrace();
/*     */     } finally {
/* 131 */       nioEventLoopGroup.shutdownGracefully();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\startup\WebSocketClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */