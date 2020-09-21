/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ public class FriendFavorValueNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public long favorValue;
/*    */   
/*    */   public FriendFavorValueNoticeResponse() {
/* 22 */     this.eventResponseId = 23419;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendFavorValueNoticeResponse(short retCode) {
/* 27 */     this.eventResponseId = 23419;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeLong(out, this.playerId);
/* 35 */     ProtocolUtil.writeLong(out, this.favorValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.favorValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendFavorValueNoticeResponse clone() throws CloneNotSupportedException {
/* 46 */     FriendFavorValueNoticeResponse clone = (FriendFavorValueNoticeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.playerId = 0L;
/* 53 */     this.favorValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"playerId\":" + this.playerId + ",\"favorValue\":" + this.favorValue + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendFavorValueNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */