/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpVipData
/*    */ {
/*    */   public int intType;
/*    */   public int challenge;
/*    */   public int maxChallenge;
/*    */   public int sweep;
/*    */   public int maxSweep;
/*    */   public int freeSweep;
/*    */   public int maxPoint;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.intType);
/* 21 */     ProtocolUtil.writeInt(out, this.challenge);
/* 22 */     ProtocolUtil.writeInt(out, this.maxChallenge);
/* 23 */     ProtocolUtil.writeInt(out, this.sweep);
/* 24 */     ProtocolUtil.writeInt(out, this.maxSweep);
/* 25 */     ProtocolUtil.writeInt(out, this.freeSweep);
/* 26 */     ProtocolUtil.writeInt(out, this.maxPoint);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.intType = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.challenge = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.maxChallenge = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.sweep = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.maxSweep = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.freeSweep = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.maxPoint = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public UpVipData clone() throws CloneNotSupportedException {
/* 42 */     UpVipData clone = (UpVipData)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.intType = 0;
/* 49 */     this.challenge = 0;
/* 50 */     this.maxChallenge = 0;
/* 51 */     this.sweep = 0;
/* 52 */     this.maxSweep = 0;
/* 53 */     this.freeSweep = 0;
/* 54 */     this.maxPoint = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"intType\":" + this.intType + ",\"challenge\":" + this.challenge + ",\"maxChallenge\":" + this.maxChallenge + ",\"sweep\":" + this.sweep + ",\"maxSweep\":" + this.maxSweep + ",\"freeSweep\":" + this.freeSweep + ",\"maxPoint\":" + this.maxPoint + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\UpVipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */