/*     */ package com.linlongyx.sanguo.webgame.net.ws;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.net.handler.HttpHandler;
/*     */ import com.linlongyx.sanguo.webgame.net.handler.WSServerHandler;
/*     */ import com.linlongyx.sanguo.webgame.processors.MsgDispatcher;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.socket.SocketChannel;
/*     */ import io.netty.handler.codec.http.HttpObjectAggregator;
/*     */ import io.netty.handler.codec.http.HttpServerCodec;
/*     */ import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
/*     */ import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
/*     */ import io.netty.handler.ssl.SslHandler;
/*     */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*     */ import io.netty.util.concurrent.DefaultEventExecutorGroup;
/*     */ import java.io.InputStream;
/*     */ import java.security.KeyStore;
/*     */ import javax.net.ssl.KeyManager;
/*     */ import javax.net.ssl.KeyManagerFactory;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLEngine;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WebSocketServerInitializer
/*     */   extends ChannelInitializer<SocketChannel>
/*     */ {
/*     */   private static final String WEBSOCKET_PATH = "/ws";
/*     */   private static final int READ_TIMEOUT_SECONDS = 1200;
/*     */   private static final int MAX_BUFFER_SIZE = 65536;
/*  38 */   private SSLContext sslContext = null;
/*     */   
/*     */   private void initSSLContext() {
/*  41 */     if (MContext.isWss()) {
/*     */       try {
/*  43 */         String domain = MContext.getJks();
/*  44 */         String jksFile = "/webgame/jks/" + domain + ".jks";
/*  45 */         String keystorePass = MContext.getKeystorePass();
/*  46 */         KeyStore ks = KeyStore.getInstance("JKS");
/*  47 */         InputStream in = getClass().getResourceAsStream(jksFile);
/*  48 */         ks.load(in, keystorePass.toCharArray());
/*     */         
/*  50 */         KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
/*  51 */         kmf.init(ks, keystorePass.toCharArray());
/*     */         
/*  53 */         KeyManager[] km = kmf.getKeyManagers();
/*     */         
/*  55 */         this.sslContext = SSLContext.getInstance("SSL");
/*  56 */         this.sslContext.init(km, null, null);
/*     */       }
/*  58 */       catch (Exception e) {
/*  59 */         e.printStackTrace();
/*     */       } 
/*     */     } else {
/*     */       
/*  63 */       this.sslContext = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private DefaultEventExecutorGroup businessGroup;
/*     */ 
/*     */   
/*     */   public WebSocketServerInitializer(boolean ssl) {
/*  73 */     if (ssl) {
/*  74 */       initSSLContext();
/*     */     }
/*     */   }
/*     */   
/*     */   public void initChannel(SocketChannel ch) throws Exception {
/*  79 */     ChannelPipeline pipeline = ch.pipeline();
/*  80 */     MsgDispatcher msgDispatcher = new MsgDispatcher();
/*  81 */     if (this.sslContext != null) {
/*  82 */       SSLEngine sslEngine = this.sslContext.createSSLEngine();
/*  83 */       sslEngine.setUseClientMode(false);
/*  84 */       sslEngine.setNeedClientAuth(false);
/*  85 */       pipeline.addFirst("ssl", (ChannelHandler)new SslHandler(sslEngine));
/*     */     } 
/*  87 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpServerCodec() });
/*  88 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpHandler(msgDispatcher) });
/*  89 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpObjectAggregator(65536) });
/*     */     
/*  91 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketServerCompressionHandler() });
/*  92 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketServerProtocolHandler("/ws", null, true) });
/*     */     
/*  94 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ReadTimeoutHandler(1200) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WSServerHandler(msgDispatcher) });
/*     */   }
/*     */   
/*     */   public void setBusinessGroup(DefaultEventExecutorGroup businessGroup) {
/* 104 */     this.businessGroup = businessGroup;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\ws\WebSocketServerInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */