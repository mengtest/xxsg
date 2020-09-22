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
/*    */ public class CardBookAskGiftResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int itemId;
/*    */   public int chatType;
/*    */   public int nexAskTime;
/*    */   public int bottomCard;
/*    */   public int colorDan;
/*    */   
/*    */   public CardBookAskGiftResponse() {
/* 25 */     this.eventResponseId = 22603;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CardBookAskGiftResponse(short retCode) {
/* 30 */     this.eventResponseId = 22603;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.itemId);
/* 38 */     ProtocolUtil.writeInt(out, this.chatType);
/* 39 */     ProtocolUtil.writeInt(out, this.nexAskTime);
/* 40 */     ProtocolUtil.writeInt(out, this.bottomCard);
/* 41 */     ProtocolUtil.writeInt(out, this.colorDan);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.chatType = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.nexAskTime = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.bottomCard = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.colorDan = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CardBookAskGiftResponse clone() throws CloneNotSupportedException {
/* 55 */     CardBookAskGiftResponse clone = (CardBookAskGiftResponse)super.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.itemId = 0;
/* 62 */     this.chatType = 0;
/* 63 */     this.nexAskTime = 0;
/* 64 */     this.bottomCard = 0;
/* 65 */     this.colorDan = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"itemId\":" + this.itemId + ",\"chatType\":" + this.chatType + ",\"nexAskTime\":" + this.nexAskTime + ",\"bottomCard\":" + this.bottomCard + ",\"colorDan\":" + this.colorDan + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cardbook\CardBookAskGiftResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */