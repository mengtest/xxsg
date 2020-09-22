/*    */ package com.linlongyx.sanguo.webgame.proto.binary.charge;
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
/*    */ public class GetDaliyChargeRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   
/*    */   public GetDaliyChargeRewardResponse() {
/* 21 */     this.eventResponseId = 21604;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetDaliyChargeRewardResponse(short retCode) {
/* 26 */     this.eventResponseId = 21604;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GetDaliyChargeRewardResponse clone() throws CloneNotSupportedException {
/* 43 */     GetDaliyChargeRewardResponse clone = (GetDaliyChargeRewardResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"id\":" + this.id + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\charge\GetDaliyChargeRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */