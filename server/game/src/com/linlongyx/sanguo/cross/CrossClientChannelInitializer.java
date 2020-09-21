/*    */ package com.linlongyx.sanguo.cross;
/*    */ 
/*    */ import com.linlongyx.sanguo.cross.coder.CrossDecoder;
/*    */ import com.linlongyx.sanguo.cross.coder.CrossEncoder;
/*    */ import com.linlongyx.sanguo.cross.handler.CrossClientHandler;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelInitializer;
/*    */ import io.netty.channel.ChannelPipeline;
/*    */ import io.netty.channel.socket.SocketChannel;
/*    */ import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
/*    */ import io.netty.handler.codec.LengthFieldPrepender;
/*    */ 
/*    */ public class CrossClientChannelInitializer
/*    */   extends ChannelInitializer<SocketChannel> {
/*    */   public CrossClientChannelInitializer(CrossClientHandler crossClientHandler) {
/* 17 */     this.crossClientHandler = crossClientHandler;
/*    */   }
/*    */   private CrossClientHandler crossClientHandler;
/*    */   
/*    */   protected void initChannel(SocketChannel socketChannel) throws Exception {
/* 22 */     ChannelPipeline pipeline = socketChannel.pipeline();
/*    */ 
/*    */     
/* 25 */     pipeline.addLast("lengthDecoder", (ChannelHandler)new LengthFieldBasedFrameDecoder(2147483647, 0, 2, 0, 2));
/* 26 */     pipeline.addLast("crossDecoder", (ChannelHandler)new CrossDecoder());
/* 27 */     pipeline.addLast("crossClientHandler", (ChannelHandler)this.crossClientHandler);
/* 28 */     pipeline.addLast("lengthFieldPrepender", (ChannelHandler)new LengthFieldPrepender(2, false));
/* 29 */     pipeline.addLast("crossEncoder", (ChannelHandler)new CrossEncoder());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\CrossClientChannelInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */