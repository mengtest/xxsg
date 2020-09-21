/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageData
/*    */ {
/*    */   public int id;
/*    */   public int star;
/*    */   public int level;
/*    */   public int battle;
/*    */   public int exp;
/*    */   public long fightValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.id);
/* 21 */     ProtocolUtil.writeInt(out, this.star);
/* 22 */     ProtocolUtil.writeInt(out, this.level);
/* 23 */     ProtocolUtil.writeInt(out, this.battle);
/* 24 */     ProtocolUtil.writeInt(out, this.exp);
/* 25 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 30 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 31 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 32 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.battle = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public StageData clone() throws CloneNotSupportedException {
/* 40 */     StageData clone = (StageData)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 46 */     this.id = 0;
/* 47 */     this.star = 0;
/* 48 */     this.level = 0;
/* 49 */     this.battle = 0;
/* 50 */     this.exp = 0;
/* 51 */     this.fightValue = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "{\"id\":" + this.id + ",\"star\":" + this.star + ",\"level\":" + this.level + ",\"battle\":" + this.battle + ",\"exp\":" + this.exp + ",\"fightValue\":" + this.fightValue + "}";
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\StageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */