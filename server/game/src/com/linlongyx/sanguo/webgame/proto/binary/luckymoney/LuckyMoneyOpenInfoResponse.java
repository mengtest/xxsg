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
/*    */ public class LuckyMoneyOpenInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long goldMoneypot;
/*    */   public long silverMoneypot;
/*    */   public long goldMoneypotSum;
/*    */   public long silverMoneypotSum;
/*    */   public long point;
/*    */   public int battle;
/*    */   public int day;
/*    */   
/*    */   public LuckyMoneyOpenInfoResponse() {
/* 27 */     this.eventResponseId = 24309;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LuckyMoneyOpenInfoResponse(short retCode) {
/* 32 */     this.eventResponseId = 24309;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeLong(out, this.goldMoneypot);
/* 40 */     ProtocolUtil.writeLong(out, this.silverMoneypot);
/* 41 */     ProtocolUtil.writeLong(out, this.goldMoneypotSum);
/* 42 */     ProtocolUtil.writeLong(out, this.silverMoneypotSum);
/* 43 */     ProtocolUtil.writeLong(out, this.point);
/* 44 */     ProtocolUtil.writeInt(out, this.battle);
/* 45 */     ProtocolUtil.writeInt(out, this.day);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     this.goldMoneypot = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.silverMoneypot = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.goldMoneypotSum = ProtocolUtil.readUTFBinLong(in);
/* 53 */     this.silverMoneypotSum = ProtocolUtil.readUTFBinLong(in);
/* 54 */     this.point = ProtocolUtil.readUTFBinLong(in);
/* 55 */     this.battle = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.day = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyMoneyOpenInfoResponse clone() throws CloneNotSupportedException {
/* 61 */     LuckyMoneyOpenInfoResponse clone = (LuckyMoneyOpenInfoResponse)super.clone();
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.goldMoneypot = 0L;
/* 68 */     this.silverMoneypot = 0L;
/* 69 */     this.goldMoneypotSum = 0L;
/* 70 */     this.silverMoneypotSum = 0L;
/* 71 */     this.point = 0L;
/* 72 */     this.battle = 0;
/* 73 */     this.day = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     String retVal = "{\"goldMoneypot\":" + this.goldMoneypot + ",\"silverMoneypot\":" + this.silverMoneypot + ",\"goldMoneypotSum\":" + this.goldMoneypotSum + ",\"silverMoneypotSum\":" + this.silverMoneypotSum + ",\"point\":" + this.point + ",\"battle\":" + this.battle + ",\"day\":" + this.day + "}";
/* 79 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckymoney\LuckyMoneyOpenInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */