/*     */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PartnerInfo
/*     */ {
/*     */   public long pid;
/*     */   public int tid;
/*     */   public int level;
/*     */   public long exp;
/*     */   public int wearSkin;
/*  17 */   public ArrayList<Integer> activeSkins = new ArrayList<>();
/*     */   public int desLv;
/*     */   public int progress;
/*     */   public int primLevel;
/*     */   public int stars;
/*  22 */   public ArrayList<KeyValue> equips = new ArrayList<>();
/*     */   public int status;
/*     */   public int breachLevel;
/*     */   public long fightValue;
/*  26 */   public ArrayList<Long> attributes = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  30 */     ProtocolUtil.writeLong(out, this.pid);
/*  31 */     ProtocolUtil.writeInt(out, this.tid);
/*  32 */     ProtocolUtil.writeInt(out, this.level);
/*  33 */     ProtocolUtil.writeLong(out, this.exp);
/*  34 */     ProtocolUtil.writeInt(out, this.wearSkin);
/*     */     
/*  36 */     int size_0 = this.activeSkins.size();
/*  37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  40 */       int tmp_0 = ((Integer)this.activeSkins.get(index_0)).intValue();
/*  41 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  43 */     ProtocolUtil.writeInt(out, this.desLv);
/*  44 */     ProtocolUtil.writeInt(out, this.progress);
/*  45 */     ProtocolUtil.writeInt(out, this.primLevel);
/*  46 */     ProtocolUtil.writeInt(out, this.stars);
/*     */     
/*  48 */     int size_1 = this.equips.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  50 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  52 */       KeyValue tmp_1 = this.equips.get(index_1);
/*  53 */       tmp_1.serial(out);
/*     */     } 
/*  55 */     ProtocolUtil.writeInt(out, this.status);
/*  56 */     ProtocolUtil.writeInt(out, this.breachLevel);
/*  57 */     ProtocolUtil.writeLong(out, this.fightValue);
/*     */     
/*  59 */     int size_2 = this.attributes.size();
/*  60 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  61 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  63 */       long tmp_2 = ((Long)this.attributes.get(index_2)).longValue();
/*  64 */       ProtocolUtil.writeLong(out, tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  70 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/*  71 */     this.tid = ProtocolUtil.readUTFBinInt(in);
/*  72 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*  73 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/*  74 */     this.wearSkin = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  76 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  77 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  79 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  80 */       this.activeSkins.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  82 */     this.desLv = ProtocolUtil.readUTFBinInt(in);
/*  83 */     this.progress = ProtocolUtil.readUTFBinInt(in);
/*  84 */     this.primLevel = ProtocolUtil.readUTFBinInt(in);
/*  85 */     this.stars = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  87 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  88 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  90 */       KeyValue tmp_1 = new KeyValue();
/*  91 */       tmp_1.unserial(in);
/*  92 */       this.equips.add(tmp_1);
/*     */     } 
/*  94 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*  95 */     this.breachLevel = ProtocolUtil.readUTFBinInt(in);
/*  96 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*     */     
/*  98 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  99 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 101 */       long tmp_2 = ProtocolUtil.readUTFBinLong(in);
/* 102 */       this.attributes.add(Long.valueOf(tmp_2));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PartnerInfo clone() throws CloneNotSupportedException {
/* 108 */     PartnerInfo clone = (PartnerInfo)super.clone();
/* 109 */     int size_0 = this.activeSkins.size();
/* 110 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 112 */       int tmp_0 = ((Integer)this.activeSkins.get(index_0)).intValue();
/* 113 */       clone.activeSkins.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 115 */     int size_1 = this.equips.size();
/* 116 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 118 */       KeyValue tmp_1 = this.equips.get(index_1);
/* 119 */       clone.equips.add(tmp_1.clone());
/*     */     } 
/* 121 */     int size_2 = this.attributes.size();
/* 122 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 124 */       long tmp_2 = ((Long)this.attributes.get(index_2)).longValue();
/* 125 */       clone.attributes.add(Long.valueOf(tmp_2));
/*     */     } 
/* 127 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 132 */     this.pid = 0L;
/* 133 */     this.tid = 0;
/* 134 */     this.level = 0;
/* 135 */     this.exp = 0L;
/* 136 */     this.wearSkin = 0;
/* 137 */     this.activeSkins.clear();
/* 138 */     this.desLv = 0;
/* 139 */     this.progress = 0;
/* 140 */     this.primLevel = 0;
/* 141 */     this.stars = 0;
/* 142 */     this.equips.clear();
/* 143 */     this.status = 0;
/* 144 */     this.breachLevel = 0;
/* 145 */     this.fightValue = 0L;
/* 146 */     this.attributes.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 151 */     String retVal = "{\"pid\":" + this.pid + ",\"tid\":" + this.tid + ",\"level\":" + this.level + ",\"exp\":" + this.exp + ",\"wearSkin\":" + this.wearSkin + ",\"activeSkins\":" + this.activeSkins.toString() + ",\"desLv\":" + this.desLv + ",\"progress\":" + this.progress + ",\"primLevel\":" + this.primLevel + ",\"stars\":" + this.stars + ",\"equips\":" + this.equips.toString() + ",\"status\":" + this.status + ",\"breachLevel\":" + this.breachLevel + ",\"fightValue\":" + this.fightValue + ",\"attributes\":" + this.attributes.toString() + "}";
/* 152 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PartnerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */