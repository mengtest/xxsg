/*    */ package com.linlongyx.core.framework.communication.mobile;
/*    */ 
/*    */ import com.linlongyx.core.framework.communication.MessageSender;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.Channel;
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
/* 33 */     this.channel.writeAndFlush(out);
/*    */   }
/*    */   
/*    */   public Channel getChannel() {
/* 37 */     return this.channel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 42 */     LOG.debug("Going to close tcp connection in class: {}", this.channel);
/* 43 */     this.channel.close();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String channelId = "TCP channel: ";
/* 49 */     if (null != this.channel) {
/* 50 */       channelId = channelId + this.channel.toString();
/*    */     } else {
/* 52 */       channelId = channelId + "0";
/*    */     } 
/* 54 */     String sender = "Netty " + channelId;
/* 55 */     return sender;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\communication\mobile\NettyTCPMessageSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */