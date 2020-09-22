/*    */ package com.linlongyx.sanguo.client.actor;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelFutureListener;
/*    */ import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
/*    */ import io.netty.util.concurrent.GenericFutureListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NettyTCPMessageSender
/*    */ {
/*    */   private final Channel channel;
/*    */   
/*    */   public NettyTCPMessageSender(Channel channel) {
/* 20 */     this.channel = channel;
/*    */   }
/*    */   
/*    */   public Object sendMessage(Object message) {
/* 24 */     return this.channel.writeAndFlush(message);
/*    */   }
/*    */   
/*    */   public void sendMessage(ByteBuf out) {
/* 28 */     this.channel.writeAndFlush(new BinaryWebSocketFrame(out));
/*    */   }
/*    */ 
/*    */   
/*    */   public Channel getChannel() {
/* 33 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 38 */     if (this.channel.isActive()) {
/* 39 */       this.channel.write(null).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String channelId = "TCP channel: ";
/* 46 */     if (null != this.channel) {
/* 47 */       channelId = channelId + this.channel.toString();
/*    */     } else {
/* 49 */       channelId = channelId + "0";
/*    */     } 
/* 51 */     String sender = "Netty " + channelId;
/* 52 */     return sender;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\actor\NettyTCPMessageSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */