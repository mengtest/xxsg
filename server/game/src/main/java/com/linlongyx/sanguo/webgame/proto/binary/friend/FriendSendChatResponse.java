/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class FriendSendChatResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public String context;
/*    */   
/*    */   public FriendSendChatResponse() {
/* 23 */     this.eventResponseId = 23404;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendSendChatResponse(short retCode) {
/* 28 */     this.eventResponseId = 23404;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendSendChatResponse clone() throws CloneNotSupportedException {
/* 47 */     FriendSendChatResponse clone = (FriendSendChatResponse)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.playerId = 0L;
/* 54 */     this.context = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"playerId\":" + this.playerId + ",\"context\":" + StringUtil.str2Str(this.context) + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendSendChatResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */