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
/*    */ public class CardBookPairUpgradeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int pairId;
/*    */   public int level;
/*    */   
/*    */   public CardBookPairUpgradeResponse() {
/* 23 */     this.eventResponseId = 22606;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CardBookPairUpgradeResponse(short retCode) {
/* 28 */     this.eventResponseId = 22606;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     ProtocolUtil.writeInt(out, this.pairId);
/* 37 */     ProtocolUtil.writeInt(out, this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.pairId = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CardBookPairUpgradeResponse clone() throws CloneNotSupportedException {
/* 49 */     CardBookPairUpgradeResponse clone = (CardBookPairUpgradeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.pairId = 0;
/* 57 */     this.level = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"type\":" + this.type + ",\"pairId\":" + this.pairId + ",\"level\":" + this.level + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cardbook\CardBookPairUpgradeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */