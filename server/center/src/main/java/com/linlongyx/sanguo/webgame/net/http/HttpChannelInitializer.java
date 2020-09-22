/*    */ package com.linlongyx.sanguo.webgame.net.http;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.handler.HttpServerHandler;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelInitializer;
/*    */ import io.netty.channel.ChannelPipeline;
/*    */ import io.netty.channel.socket.SocketChannel;
/*    */ import io.netty.handler.codec.http.HttpObjectAggregator;
/*    */ import io.netty.handler.codec.http.HttpRequestDecoder;
/*    */ import io.netty.handler.codec.http.HttpResponseEncoder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpChannelInitializer
/*    */   extends ChannelInitializer<SocketChannel>
/*    */ {
/*    */   protected void initChannel(SocketChannel ch) throws Exception {
/* 20 */     ChannelPipeline pipeline = ch.pipeline();
/* 21 */     pipeline.addLast("encoder", (ChannelHandler)new HttpResponseEncoder());
/*    */     
/* 23 */     pipeline.addLast("decoder", (ChannelHandler)new HttpRequestDecoder());
/* 24 */     pipeline.addLast("aggregator", (ChannelHandler)new HttpObjectAggregator(65536));
/* 25 */     pipeline.addLast("handler", (ChannelHandler)new HttpServerHandler());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\http\HttpChannelInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */