/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class RapidCombatInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int times;
/*    */   public int combatCostTimes;
/*    */   
/*    */   public RapidCombatInfoResponse() {
/* 22 */     this.eventResponseId = 20009;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RapidCombatInfoResponse(short retCode) {
/* 27 */     this.eventResponseId = 20009;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.times);
/* 35 */     ProtocolUtil.writeInt(out, this.combatCostTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.times = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.combatCostTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RapidCombatInfoResponse clone() throws CloneNotSupportedException {
/* 46 */     RapidCombatInfoResponse clone = (RapidCombatInfoResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.times = 0;
/* 53 */     this.combatCostTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"times\":" + this.times + ",\"combatCostTimes\":" + this.combatCostTimes + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\RapidCombatInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */