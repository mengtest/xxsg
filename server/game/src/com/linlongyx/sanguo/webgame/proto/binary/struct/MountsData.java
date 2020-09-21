/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MountsData
/*    */ {
/*    */   public int mounts;
/*    */   public int star;
/*    */   public int level;
/*    */   public int battle;
/*    */   public int exp;
/*    */   public long fightValue;
/* 18 */   public ArrayList<Integer> breaksLevel = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 22 */     ProtocolUtil.writeInt(out, this.mounts);
/* 23 */     ProtocolUtil.writeInt(out, this.star);
/* 24 */     ProtocolUtil.writeInt(out, this.level);
/* 25 */     ProtocolUtil.writeInt(out, this.battle);
/* 26 */     ProtocolUtil.writeInt(out, this.exp);
/* 27 */     ProtocolUtil.writeLong(out, this.fightValue);
/*    */     
/* 29 */     int size_0 = this.breaksLevel.size();
/* 30 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 31 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 33 */       int tmp_0 = ((Integer)this.breaksLevel.get(index_0)).intValue();
/* 34 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.mounts = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.battle = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.exp = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 47 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 51 */       this.breaksLevel.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public MountsData clone() throws CloneNotSupportedException {
/* 57 */     MountsData clone = (MountsData)super.clone();
/* 58 */     int size_0 = this.breaksLevel.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       int tmp_0 = ((Integer)this.breaksLevel.get(index_0)).intValue();
/* 62 */       clone.breaksLevel.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.mounts = 0;
/* 70 */     this.star = 0;
/* 71 */     this.level = 0;
/* 72 */     this.battle = 0;
/* 73 */     this.exp = 0;
/* 74 */     this.fightValue = 0L;
/* 75 */     this.breaksLevel.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "{\"mounts\":" + this.mounts + ",\"star\":" + this.star + ",\"level\":" + this.level + ",\"battle\":" + this.battle + ",\"exp\":" + this.exp + ",\"fightValue\":" + this.fightValue + ",\"breaksLevel\":" + this.breaksLevel.toString() + "}";
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\MountsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */