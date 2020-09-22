/*     */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EquipData
/*     */ {
/*     */   public long mid;
/*     */   public int itemId;
/*     */   public int strengthLv;
/*     */   public int quaity;
/*     */   public int refineLv;
/*     */   public int zhuLv;
/*  19 */   public ArrayList<IntIntValue> stones = new ArrayList<>();
/*     */   
/*     */   public byte isWear;
/*     */   public long fightValue;
/*     */   public int artificeProcess;
/*     */   public int artificeLevel;
/*     */   public int artificeLucky;
/*     */   public int talismanRank;
/*     */   public long belondTo;
/*     */   public int star;
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  31 */     ProtocolUtil.writeLong(out, this.mid);
/*  32 */     ProtocolUtil.writeInt(out, this.itemId);
/*  33 */     ProtocolUtil.writeInt(out, this.strengthLv);
/*  34 */     ProtocolUtil.writeInt(out, this.quaity);
/*  35 */     ProtocolUtil.writeInt(out, this.refineLv);
/*  36 */     ProtocolUtil.writeInt(out, this.zhuLv);
/*     */     
/*  38 */     int size_0 = this.stones.size();
/*  39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  42 */       IntIntValue tmp_0 = this.stones.get(index_0);
/*  43 */       tmp_0.serial(out);
/*     */     } 
/*  45 */     ProtocolUtil.writeByte(out, this.isWear);
/*  46 */     ProtocolUtil.writeLong(out, this.fightValue);
/*  47 */     ProtocolUtil.writeInt(out, this.artificeProcess);
/*  48 */     ProtocolUtil.writeInt(out, this.artificeLevel);
/*  49 */     ProtocolUtil.writeInt(out, this.artificeLucky);
/*  50 */     ProtocolUtil.writeInt(out, this.talismanRank);
/*  51 */     ProtocolUtil.writeLong(out, this.belondTo);
/*  52 */     ProtocolUtil.writeInt(out, this.star);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  57 */     this.mid = ProtocolUtil.readUTFBinLong(in);
/*  58 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/*  59 */     this.strengthLv = ProtocolUtil.readUTFBinInt(in);
/*  60 */     this.quaity = ProtocolUtil.readUTFBinInt(in);
/*  61 */     this.refineLv = ProtocolUtil.readUTFBinInt(in);
/*  62 */     this.zhuLv = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  64 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  67 */       IntIntValue tmp_0 = new IntIntValue();
/*  68 */       tmp_0.unserial(in);
/*  69 */       this.stones.add(tmp_0);
/*     */     } 
/*  71 */     this.isWear = ProtocolUtil.readUTFBinByte(in);
/*  72 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*  73 */     this.artificeProcess = ProtocolUtil.readUTFBinInt(in);
/*  74 */     this.artificeLevel = ProtocolUtil.readUTFBinInt(in);
/*  75 */     this.artificeLucky = ProtocolUtil.readUTFBinInt(in);
/*  76 */     this.talismanRank = ProtocolUtil.readUTFBinInt(in);
/*  77 */     this.belondTo = ProtocolUtil.readUTFBinLong(in);
/*  78 */     this.star = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public EquipData clone() throws CloneNotSupportedException {
/*  83 */     EquipData clone = (EquipData)super.clone();
/*  84 */     int size_0 = this.stones.size();
/*  85 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  87 */       IntIntValue tmp_0 = this.stones.get(index_0);
/*  88 */       clone.stones.add(tmp_0.clone());
/*     */     } 
/*  90 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  95 */     this.mid = 0L;
/*  96 */     this.itemId = 0;
/*  97 */     this.strengthLv = 0;
/*  98 */     this.quaity = 0;
/*  99 */     this.refineLv = 0;
/* 100 */     this.zhuLv = 0;
/* 101 */     this.stones.clear();
/* 102 */     this.isWear = 0;
/* 103 */     this.fightValue = 0L;
/* 104 */     this.artificeProcess = 0;
/* 105 */     this.artificeLevel = 0;
/* 106 */     this.artificeLucky = 0;
/* 107 */     this.talismanRank = 0;
/* 108 */     this.belondTo = 0L;
/* 109 */     this.star = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 114 */     String retVal = "{\"mid\":" + this.mid + ",\"itemId\":" + this.itemId + ",\"strengthLv\":" + this.strengthLv + ",\"quaity\":" + this.quaity + ",\"refineLv\":" + this.refineLv + ",\"zhuLv\":" + this.zhuLv + ",\"stones\":" + this.stones.toString() + ",\"isWear\":" + this.isWear + ",\"fightValue\":" + this.fightValue + ",\"artificeProcess\":" + this.artificeProcess + ",\"artificeLevel\":" + this.artificeLevel + ",\"artificeLucky\":" + this.artificeLucky + ",\"talismanRank\":" + this.talismanRank + ",\"belondTo\":" + this.belondTo + ",\"star\":" + this.star + "}";
/* 115 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\EquipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */