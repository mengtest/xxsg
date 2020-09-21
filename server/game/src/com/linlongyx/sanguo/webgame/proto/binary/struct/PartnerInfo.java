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
/*     */ public class PartnerInfo
/*     */ {
/*     */   public long pid;
/*     */   public int tid;
/*     */   public int level;
/*     */   public long exp;
/*     */   public int wearSkin;
/*  18 */   public ArrayList<Integer> activeSkins = new ArrayList<>();
/*     */   public int desLv;
/*     */   public int progress;
/*     */   public int primLevel;
/*     */   public int stars;
/*  23 */   public ArrayList<KeyValue> equips = new ArrayList<>();
/*     */   public int status;
/*     */   public int breachLevel;
/*     */   public long fightValue;
/*  27 */   public ArrayList<Long> attributes = new ArrayList<>();
/*     */   public int soulLevel;
/*  29 */   public ArrayList<Integer> reinIds = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  33 */     ProtocolUtil.writeLong(out, this.pid);
/*  34 */     ProtocolUtil.writeInt(out, this.tid);
/*  35 */     ProtocolUtil.writeInt(out, this.level);
/*  36 */     ProtocolUtil.writeLong(out, this.exp);
/*  37 */     ProtocolUtil.writeInt(out, this.wearSkin);
/*     */     
/*  39 */     int size_0 = this.activeSkins.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       int tmp_0 = ((Integer)this.activeSkins.get(index_0)).intValue();
/*  44 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  46 */     ProtocolUtil.writeInt(out, this.desLv);
/*  47 */     ProtocolUtil.writeInt(out, this.progress);
/*  48 */     ProtocolUtil.writeInt(out, this.primLevel);
/*  49 */     ProtocolUtil.writeInt(out, this.stars);
/*     */     
/*  51 */     int size_1 = this.equips.size();
/*  52 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  53 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  55 */       KeyValue tmp_1 = this.equips.get(index_1);
/*  56 */       tmp_1.serial(out);
/*     */     } 
/*  58 */     ProtocolUtil.writeInt(out, this.status);
/*  59 */     ProtocolUtil.writeInt(out, this.breachLevel);
/*  60 */     ProtocolUtil.writeLong(out, this.fightValue);
/*     */     
/*  62 */     int size_2 = this.attributes.size();
/*  63 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  64 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  66 */       long tmp_2 = ((Long)this.attributes.get(index_2)).longValue();
/*  67 */       ProtocolUtil.writeLong(out, tmp_2);
/*     */     } 
/*  69 */     ProtocolUtil.writeInt(out, this.soulLevel);
/*     */     
/*  71 */     int size_3 = this.reinIds.size();
/*  72 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  73 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  75 */       int tmp_3 = ((Integer)this.reinIds.get(index_3)).intValue();
/*  76 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  82 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/*  83 */     this.tid = ProtocolUtil.readUTFBinInt(in);
/*  84 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*  85 */     this.exp = ProtocolUtil.readUTFBinLong(in);
/*  86 */     this.wearSkin = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  88 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  89 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  91 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  92 */       this.activeSkins.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  94 */     this.desLv = ProtocolUtil.readUTFBinInt(in);
/*  95 */     this.progress = ProtocolUtil.readUTFBinInt(in);
/*  96 */     this.primLevel = ProtocolUtil.readUTFBinInt(in);
/*  97 */     this.stars = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  99 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 100 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 102 */       KeyValue tmp_1 = new KeyValue();
/* 103 */       tmp_1.unserial(in);
/* 104 */       this.equips.add(tmp_1);
/*     */     } 
/* 106 */     this.status = ProtocolUtil.readUTFBinInt(in);
/* 107 */     this.breachLevel = ProtocolUtil.readUTFBinInt(in);
/* 108 */     this.fightValue = ProtocolUtil.readUTFBinLong(in);
/*     */     
/* 110 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 111 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 113 */       long tmp_2 = ProtocolUtil.readUTFBinLong(in);
/* 114 */       this.attributes.add(Long.valueOf(tmp_2));
/*     */     } 
/* 116 */     this.soulLevel = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 118 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 119 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 121 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 122 */       this.reinIds.add(Integer.valueOf(tmp_3));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PartnerInfo clone() throws CloneNotSupportedException {
/* 128 */     PartnerInfo clone = (PartnerInfo)super.clone();
/* 129 */     int size_0 = this.activeSkins.size();
/* 130 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 132 */       int tmp_0 = ((Integer)this.activeSkins.get(index_0)).intValue();
/* 133 */       clone.activeSkins.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 135 */     int size_1 = this.equips.size();
/* 136 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 138 */       KeyValue tmp_1 = this.equips.get(index_1);
/* 139 */       clone.equips.add(tmp_1.clone());
/*     */     } 
/* 141 */     int size_2 = this.attributes.size();
/* 142 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 144 */       long tmp_2 = ((Long)this.attributes.get(index_2)).longValue();
/* 145 */       clone.attributes.add(Long.valueOf(tmp_2));
/*     */     } 
/* 147 */     int size_3 = this.reinIds.size();
/* 148 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 150 */       int tmp_3 = ((Integer)this.reinIds.get(index_3)).intValue();
/* 151 */       clone.reinIds.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 153 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 158 */     this.pid = 0L;
/* 159 */     this.tid = 0;
/* 160 */     this.level = 0;
/* 161 */     this.exp = 0L;
/* 162 */     this.wearSkin = 0;
/* 163 */     this.activeSkins.clear();
/* 164 */     this.desLv = 0;
/* 165 */     this.progress = 0;
/* 166 */     this.primLevel = 0;
/* 167 */     this.stars = 0;
/* 168 */     this.equips.clear();
/* 169 */     this.status = 0;
/* 170 */     this.breachLevel = 0;
/* 171 */     this.fightValue = 0L;
/* 172 */     this.attributes.clear();
/* 173 */     this.soulLevel = 0;
/* 174 */     this.reinIds.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 179 */     String retVal = "{\"pid\":" + this.pid + ",\"tid\":" + this.tid + ",\"level\":" + this.level + ",\"exp\":" + this.exp + ",\"wearSkin\":" + this.wearSkin + ",\"activeSkins\":" + this.activeSkins.toString() + ",\"desLv\":" + this.desLv + ",\"progress\":" + this.progress + ",\"primLevel\":" + this.primLevel + ",\"stars\":" + this.stars + ",\"equips\":" + this.equips.toString() + ",\"status\":" + this.status + ",\"breachLevel\":" + this.breachLevel + ",\"fightValue\":" + this.fightValue + ",\"attributes\":" + this.attributes.toString() + ",\"soulLevel\":" + this.soulLevel + ",\"reinIds\":" + this.reinIds.toString() + "}";
/* 180 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PartnerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */