/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoulsData
/*    */ {
/*    */   public int soulsId;
/*    */   public int star;
/*    */   public int level;
/*    */   public int exp;
/*    */   public long fightValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     ProtocolUtil.writeInt(out, this.soulsId);
/* 20 */     ProtocolUtil.writeInt(out, this.star);
/* 21 */     ProtocolUtil.writeInt(out, this.level);
/* 22 */     ProtocolUtil.writeInt(out, this.exp);
/* 23 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 28 */     this.soulsId = ProtocolUtil.readUTFBinInt(in);
/* 29 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SoulsData clone() throws CloneNotSupportedException {
/* 37 */     SoulsData clone = (SoulsData)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.soulsId = 0;
/* 44 */     this.star = 0;
/* 45 */     this.level = 0;
/* 46 */     this.exp = 0;
/* 47 */     this.fightValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"soulsId\":" + this.soulsId + ",\"star\":" + this.star + ",\"level\":" + this.level + ",\"exp\":" + this.exp + ",\"fightValue\":" + this.fightValue + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SoulsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */