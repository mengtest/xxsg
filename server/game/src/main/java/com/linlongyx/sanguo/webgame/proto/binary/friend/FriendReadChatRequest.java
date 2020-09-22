/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class FriendReadChatRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long playerId;
/*    */   public byte needChat;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.playerId);
/* 30 */     ProtocolUtil.writeByte(out, this.needChat);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.needChat = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendReadChatRequest clone() throws CloneNotSupportedException {
/* 41 */     FriendReadChatRequest clone = (FriendReadChatRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.playerId = 0L;
/* 48 */     this.needChat = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"playerId\":" + this.playerId + ",\"needChat\":" + this.needChat + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendReadChatRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */