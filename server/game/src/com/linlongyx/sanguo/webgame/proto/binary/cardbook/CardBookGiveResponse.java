/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cardbook;
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
/*    */ public class CardBookGiveResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long giftId;
/*    */   public long playerId;
/*    */   
/*    */   public CardBookGiveResponse() {
/* 22 */     this.eventResponseId = 22604;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CardBookGiveResponse(short retCode) {
/* 27 */     this.eventResponseId = 22604;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeLong(out, this.giftId);
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.giftId = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CardBookGiveResponse clone() throws CloneNotSupportedException {
/* 46 */     CardBookGiveResponse clone = (CardBookGiveResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.giftId = 0L;
/* 53 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"giftId\":" + this.giftId + ",\"playerId\":" + this.playerId + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cardbook\CardBookGiveResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */