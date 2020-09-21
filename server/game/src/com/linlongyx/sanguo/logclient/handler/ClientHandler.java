/*    */ package com.linlongyx.sanguo.logclient.handler;
/*    */ 
/*    */ import com.linlongyx.sanguo.logclient.event.Event;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.SimpleChannelInboundHandler;
/*    */ 
/*    */ @Sharable
/*    */ public class ClientHandler
/*    */   extends SimpleChannelInboundHandler
/*    */ {
/*    */   private Channel channel;
/*    */   
/*    */   public void channelActive(ChannelHandlerContext ctx) throws Exception {
/* 16 */     this.channel = ctx.channel();
/* 17 */     super.channelActive(ctx);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 27 */     super.channelInactive(ctx);
/*    */   }
/*    */   
/*    */   public void sendMessage(Event event) {
/* 31 */     this.channel.writeAndFlush(event);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\handler\ClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */