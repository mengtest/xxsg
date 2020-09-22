/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rechargeAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*    */ public class RechargeActRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/* 20 */   public IntIntValue data = new IntIntValue();
/*    */   
/*    */   public RechargeActRewardResponse() {
/* 23 */     this.eventResponseId = 22302;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RechargeActRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 22302;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.id);
/* 36 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.data = new IntIntValue();
/* 43 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RechargeActRewardResponse clone() throws CloneNotSupportedException {
/* 48 */     RechargeActRewardResponse clone = (RechargeActRewardResponse)super.clone();
/* 49 */     clone.data = this.data.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.id = 0;
/* 56 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"id\":" + this.id + ",\"data\":" + this.data.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rechargeAct\RechargeActRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */