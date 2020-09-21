/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rebate;
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
/*    */ public class ChargeRebateRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public int boxId;
/*    */   
/*    */   public ChargeRebateRewardResponse() {
/* 22 */     this.eventResponseId = 24011;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChargeRebateRewardResponse(short retCode) {
/* 27 */     this.eventResponseId = 24011;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.actId);
/* 35 */     ProtocolUtil.writeInt(out, this.boxId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.boxId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChargeRebateRewardResponse clone() throws CloneNotSupportedException {
/* 46 */     ChargeRebateRewardResponse clone = (ChargeRebateRewardResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.actId = 0;
/* 53 */     this.boxId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"actId\":" + this.actId + ",\"boxId\":" + this.boxId + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rebate\ChargeRebateRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */