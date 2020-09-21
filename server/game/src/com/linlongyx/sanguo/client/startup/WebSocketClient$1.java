/*     */ package com.linlongyx.sanguo.client.startup;
/*     */ 
/*     */ import com.linlongyx.sanguo.client.net.WebSocketClientHandler;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.socket.SocketChannel;
/*     */ import io.netty.handler.codec.http.HttpClientCodec;
/*     */ import io.netty.handler.codec.http.HttpObjectAggregator;
/*     */ import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
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
/*     */ class null
/*     */   extends ChannelInitializer<SocketChannel>
/*     */ {
/*     */   protected void initChannel(SocketChannel ch) {
/*  98 */     ChannelPipeline p = ch.pipeline();
/*  99 */     if (WebSocketClient.this.sslCtx != null) {
/* 100 */       p.addLast(new ChannelHandler[] { (ChannelHandler)this.this$0.sslCtx.newHandler(ch.alloc(), this.val$host, this.val$port) });
/*     */     }
/* 102 */     p.addLast(new ChannelHandler[] { (ChannelHandler)new HttpClientCodec(), (ChannelHandler)new HttpObjectAggregator(8192), (ChannelHandler)WebSocketClientCompressionHandler.INSTANCE, (ChannelHandler)this.val$handler });
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\startup\WebSocketClient$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */