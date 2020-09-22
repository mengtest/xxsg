/*    */ package com.linlongyx.sanguo.webgame.proto.binary.consume;
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
/*    */ public class ConsumeRebateRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public int rewardId;
/*    */   
/*    */   public ConsumeRebateRewardResponse() {
/* 22 */     this.eventResponseId = 25503;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ConsumeRebateRewardResponse(short retCode) {
/* 27 */     this.eventResponseId = 25503;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.actId);
/* 35 */     ProtocolUtil.writeInt(out, this.rewardId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.rewardId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConsumeRebateRewardResponse clone() throws CloneNotSupportedException {
/* 46 */     ConsumeRebateRewardResponse clone = (ConsumeRebateRewardResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.actId = 0;
/* 53 */     this.rewardId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"actId\":" + this.actId + ",\"rewardId\":" + this.rewardId + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\consume\ConsumeRebateRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */