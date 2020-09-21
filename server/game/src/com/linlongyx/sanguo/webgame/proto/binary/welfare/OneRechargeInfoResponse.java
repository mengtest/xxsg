/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ public class OneRechargeInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public int time;
/*    */   public int state;
/*    */   public int rewardId;
/*    */   public int chargeId;
/*    */   
/*    */   public OneRechargeInfoResponse() {
/* 25 */     this.eventResponseId = 21917;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public OneRechargeInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 21917;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.id);
/* 38 */     ProtocolUtil.writeInt(out, this.time);
/* 39 */     ProtocolUtil.writeInt(out, this.state);
/* 40 */     ProtocolUtil.writeInt(out, this.rewardId);
/* 41 */     ProtocolUtil.writeInt(out, this.chargeId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.state = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.rewardId = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.chargeId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public OneRechargeInfoResponse clone() throws CloneNotSupportedException {
/* 55 */     OneRechargeInfoResponse clone = (OneRechargeInfoResponse)super.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.id = 0;
/* 62 */     this.time = 0;
/* 63 */     this.state = 0;
/* 64 */     this.rewardId = 0;
/* 65 */     this.chargeId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"id\":" + this.id + ",\"time\":" + this.time + ",\"state\":" + this.state + ",\"rewardId\":" + this.rewardId + ",\"chargeId\":" + this.chargeId + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\OneRechargeInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */