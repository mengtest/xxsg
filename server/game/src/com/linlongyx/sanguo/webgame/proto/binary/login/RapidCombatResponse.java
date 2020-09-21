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
/*    */ public class RapidCombatResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int times;
/*    */   public int combatCostTimes;
/*    */   
/*    */   public RapidCombatResponse() {
/* 22 */     this.eventResponseId = 20008;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RapidCombatResponse(short retCode) {
/* 27 */     this.eventResponseId = 20008;
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
/*    */   public RapidCombatResponse clone() throws CloneNotSupportedException {
/* 46 */     RapidCombatResponse clone = (RapidCombatResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\RapidCombatResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */