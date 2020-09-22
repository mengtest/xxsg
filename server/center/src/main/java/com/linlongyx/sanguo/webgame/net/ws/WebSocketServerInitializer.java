/*    */ package com.linlongyx.sanguo.webgame.net.ws;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.net.handler.HttpHandler;
/*    */ import com.linlongyx.sanguo.webgame.net.handler.WSServerHandler;
/*    */ import com.linlongyx.sanguo.webgame.processors.MsgDispatcher;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelInitializer;
/*    */ import io.netty.channel.ChannelPipeline;
/*    */ import io.netty.channel.socket.SocketChannel;
/*    */ import io.netty.handler.codec.http.HttpObjectAggregator;
/*    */ import io.netty.handler.codec.http.HttpServerCodec;
/*    */ import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
/*    */ import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
/*    */ import io.netty.handler.ssl.SslHandler;
/*    */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*    */ import java.io.InputStream;
/*    */ import java.security.KeyStore;
/*    */ import javax.net.ssl.KeyManager;
/*    */ import javax.net.ssl.KeyManagerFactory;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.SSLEngine;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WebSocketServerInitializer
/*    */   extends ChannelInitializer<SocketChannel>
/*    */ {
/*    */   private static final String WEBSOCKET_PATH = "/ws";
/*    */   private static final int READ_TIMEOUT_SECONDS = 1200;
/*    */   private static final int MAX_BUFFER_SIZE = 65536;
/* 37 */   private SSLContext sslContext = null;
/*    */   
/*    */   private void initSSLContext() {
/* 40 */     if (this.sslContext == null) {
/*    */       try {
/* 42 */         String domain = MContext.getJks();
/* 43 */         String jksFile = "/webgame/jks/" + domain + ".jks";
/* 44 */         String keystorePass = MContext.getKeystorePass();
/* 45 */         KeyStore ks = KeyStore.getInstance("JKS");
/* 46 */         InputStream in = getClass().getResourceAsStream(jksFile);
/* 47 */         ks.load(in, keystorePass.toCharArray());
/*    */         
/* 49 */         KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
/* 50 */         kmf.init(ks, keystorePass.toCharArray());
/*    */         
/* 52 */         KeyManager[] km = kmf.getKeyManagers();
/*    */         
/* 54 */         this.sslContext = SSLContext.getInstance("SSL");
/* 55 */         this.sslContext.init(km, null, null);
/*    */       }
/* 57 */       catch (Exception e) {
/* 58 */         e.printStackTrace();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WebSocketServerInitializer(boolean ssl) {
/* 69 */     if (ssl) {
/* 70 */       initSSLContext();
/*    */     }
/*    */   }
/*    */   
/*    */   public void initChannel(SocketChannel ch) throws Exception {
/* 75 */     ChannelPipeline pipeline = ch.pipeline();
/* 76 */     MsgDispatcher msgDispatcher = new MsgDispatcher();
/* 77 */     if (this.sslContext != null) {
/* 78 */       SSLEngine sslEngine = this.sslContext.createSSLEngine();
/* 79 */       sslEngine.setUseClientMode(false);
/* 80 */       sslEngine.setNeedClientAuth(false);
/* 81 */       pipeline.addFirst("ssl", (ChannelHandler)new SslHandler(sslEngine));
/*    */     } 
/* 83 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpServerCodec() });
/* 84 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpHandler(msgDispatcher) });
/* 85 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpObjectAggregator(65536) });
/*    */     
/* 87 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketServerCompressionHandler() });
/* 88 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketServerProtocolHandler("/ws", null, true) });
/*    */     
/* 90 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ReadTimeoutHandler(1200) });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 96 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WSServerHandler(msgDispatcher) });
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\ws\WebSocketServerInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */