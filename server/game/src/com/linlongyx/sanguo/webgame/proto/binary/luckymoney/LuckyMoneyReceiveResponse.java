/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckymoney;
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
/*    */ public class LuckyMoneyReceiveResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int battle;
/*    */   public long goldMoneypot;
/*    */   public long silverMoneypot;
/*    */   
/*    */   public LuckyMoneyReceiveResponse() {
/* 23 */     this.eventResponseId = 24308;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LuckyMoneyReceiveResponse(short retCode) {
/* 28 */     this.eventResponseId = 24308;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.battle);
/* 36 */     ProtocolUtil.writeLong(out, this.goldMoneypot);
/* 37 */     ProtocolUtil.writeLong(out, this.silverMoneypot);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.battle = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.goldMoneypot = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.silverMoneypot = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyMoneyReceiveResponse clone() throws CloneNotSupportedException {
/* 49 */     LuckyMoneyReceiveResponse clone = (LuckyMoneyReceiveResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.battle = 0;
/* 56 */     this.goldMoneypot = 0L;
/* 57 */     this.silverMoneypot = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"battle\":" + this.battle + ",\"goldMoneypot\":" + this.goldMoneypot + ",\"silverMoneypot\":" + this.silverMoneypot + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckymoney\LuckyMoneyReceiveResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */