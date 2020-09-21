/*    */ package com.linlongyx.core.framework.communication;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NettyTCPMessageSender
/*    */   implements MessageSender
/*    */ {
/*    */   private final Channel channel;
/* 19 */   private static final Logger LOG = LoggerFactory.getLogger(NettyTCPMessageSender.class);
/*    */ 
/*    */   
/*    */   public NettyTCPMessageSender(Channel channel) {
/* 23 */     this.channel = channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object sendMessage(Object message) {
/* 28 */     return this.channel.writeAndFlush(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(ByteBuf out) {
/* 33 */     this.channel.writeAndFlush(new BinaryWebSocketFrame(out));
/*    */   }
/*    */   
/*    */   public Channel getChannel() {
/* 37 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 42 */     LOG.debug("Going to close tcp connection in class: {}", this.channel);
/*    */     
/* 44 */     if (this.channel.isOpen()) {
/* 45 */       this.channel.close();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String channelId = "TCP channel: ";
/* 61 */     if (null != this.channel) {
/* 62 */       channelId = channelId + this.channel.toString();
/*    */     } else {
/* 64 */       channelId = channelId + "0";
/*    */     } 
/* 66 */     String sender = "Netty " + channelId;
/* 67 */     return sender;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\communication\NettyTCPMessageSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */