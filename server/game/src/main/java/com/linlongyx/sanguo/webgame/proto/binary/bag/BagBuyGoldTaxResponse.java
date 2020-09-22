/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*    */ public class BagBuyGoldTaxResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int buyGoldTimes;
/*    */   public int buyGoldCost;
/*    */   
/*    */   public BagBuyGoldTaxResponse() {
/* 22 */     this.eventResponseId = 20715;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BagBuyGoldTaxResponse(short retCode) {
/* 27 */     this.eventResponseId = 20715;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.buyGoldTimes);
/* 35 */     ProtocolUtil.writeInt(out, this.buyGoldCost);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.buyGoldTimes = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.buyGoldCost = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagBuyGoldTaxResponse clone() throws CloneNotSupportedException {
/* 46 */     BagBuyGoldTaxResponse clone = (BagBuyGoldTaxResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.buyGoldTimes = 0;
/* 53 */     this.buyGoldCost = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"buyGoldTimes\":" + this.buyGoldTimes + ",\"buyGoldCost\":" + this.buyGoldCost + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagBuyGoldTaxResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */