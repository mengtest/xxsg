/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
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
/*    */ public class FriendSendChatRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long playerId;
/*    */   public String context;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.playerId);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 37 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendSendChatRequest clone() throws CloneNotSupportedException {
/* 42 */     FriendSendChatRequest clone = (FriendSendChatRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.playerId = 0L;
/* 49 */     this.context = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"playerId\":" + this.playerId + ",\"context\":" + StringUtil.str2Str(this.context) + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendSendChatRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */