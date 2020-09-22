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
/*    */ public class BagBuyGoldResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int buyGoldTimes;
/*    */   public int buyGoldCost;
/*    */   
/*    */   public BagBuyGoldResponse() {
/* 22 */     this.eventResponseId = 20712;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BagBuyGoldResponse(short retCode) {
/* 27 */     this.eventResponseId = 20712;
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
/*    */   public BagBuyGoldResponse clone() throws CloneNotSupportedException {
/* 46 */     BagBuyGoldResponse clone = (BagBuyGoldResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagBuyGoldResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */