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
/*    */ public class FriendStateNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte type;
/*    */   public long playerId;
/*    */   public String name;
/*    */   
/*    */   public FriendStateNoticeResponse() {
/* 24 */     this.eventResponseId = 23413;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendStateNoticeResponse(short retCode) {
/* 29 */     this.eventResponseId = 23413;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeByte(out, this.type);
/* 37 */     ProtocolUtil.writeLong(out, this.playerId);
/* 38 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 44 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 45 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendStateNoticeResponse clone() throws CloneNotSupportedException {
/* 50 */     FriendStateNoticeResponse clone = (FriendStateNoticeResponse)super.clone();
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.type = 0;
/* 57 */     this.playerId = 0L;
/* 58 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "{\"type\":" + this.type + ",\"playerId\":" + this.playerId + ",\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendStateNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */