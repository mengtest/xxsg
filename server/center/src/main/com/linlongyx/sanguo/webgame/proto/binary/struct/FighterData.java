/*     */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FighterData
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public byte guid;
/*     */   public byte type;
/*     */   public byte pos;
/*     */   public byte fury;
/*     */   public short level;
/*     */   public long id;
/*     */   public long hp;
/*     */   public long maxHp;
/*     */   public long attack;
/*     */   public long phyDef;
/*     */   public long magDef;
/*     */   public int quality;
/*     */   public int fashionId;
/*  28 */   public ArrayList<SkillData> skills = new ArrayList<>();
/*  29 */   public ArrayList<Integer> buffs = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  33 */     ProtocolUtil.writeByte(out, this.guid);
/*  34 */     ProtocolUtil.writeByte(out, this.type);
/*  35 */     ProtocolUtil.writeByte(out, this.pos);
/*  36 */     ProtocolUtil.writeByte(out, this.fury);
/*  37 */     ProtocolUtil.writeShort(out, this.level);
/*  38 */     ProtocolUtil.writeLong(out, this.id);
/*  39 */     ProtocolUtil.writeLong(out, this.hp);
/*  40 */     ProtocolUtil.writeLong(out, this.maxHp);
/*  41 */     ProtocolUtil.writeLong(out, this.attack);
/*  42 */     ProtocolUtil.writeLong(out, this.phyDef);
/*  43 */     ProtocolUtil.writeLong(out, this.magDef);
/*  44 */     ProtocolUtil.writeInt(out, this.quality);
/*  45 */     ProtocolUtil.writeInt(out, this.fashionId);
/*     */     
/*  47 */     int size_0 = this.skills.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  51 */       SkillData tmp_0 = this.skills.get(index_0);
/*  52 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  55 */     int size_1 = this.buffs.size();
/*  56 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  57 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  59 */       int tmp_1 = ((Integer)this.buffs.get(index_1)).intValue();
/*  60 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  66 */     this.guid = ProtocolUtil.readUTFBinByte(in);
/*  67 */     this.type = ProtocolUtil.readUTFBinByte(in);
/*  68 */     this.pos = ProtocolUtil.readUTFBinByte(in);
/*  69 */     this.fury = ProtocolUtil.readUTFBinByte(in);
/*  70 */     this.level = ProtocolUtil.readUTFBinShort(in);
/*  71 */     this.id = ProtocolUtil.readUTFBinLong(in);
/*  72 */     this.hp = ProtocolUtil.readUTFBinLong(in);
/*  73 */     this.maxHp = ProtocolUtil.readUTFBinLong(in);
/*  74 */     this.attack = ProtocolUtil.readUTFBinLong(in);
/*  75 */     this.phyDef = ProtocolUtil.readUTFBinLong(in);
/*  76 */     this.magDef = ProtocolUtil.readUTFBinLong(in);
/*  77 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*  78 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  80 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  81 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  83 */       SkillData tmp_0 = new SkillData();
/*  84 */       tmp_0.unserial(in);
/*  85 */       this.skills.add(tmp_0);
/*     */     } 
/*     */     
/*  88 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  89 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  91 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  92 */       this.buffs.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public FighterData clone() throws CloneNotSupportedException {
/*  98 */     FighterData clone = (FighterData)super.clone();
/*  99 */     int size_0 = this.skills.size();
/* 100 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 102 */       SkillData tmp_0 = this.skills.get(index_0);
/* 103 */       clone.skills.add(tmp_0.clone());
/*     */     } 
/* 105 */     int size_1 = this.buffs.size();
/* 106 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 108 */       int tmp_1 = ((Integer)this.buffs.get(index_1)).intValue();
/* 109 */       clone.buffs.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 111 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 116 */     this.guid = 0;
/* 117 */     this.type = 0;
/* 118 */     this.pos = 0;
/* 119 */     this.fury = 0;
/* 120 */     this.level = 0;
/* 121 */     this.id = 0L;
/* 122 */     this.hp = 0L;
/* 123 */     this.maxHp = 0L;
/* 124 */     this.attack = 0L;
/* 125 */     this.phyDef = 0L;
/* 126 */     this.magDef = 0L;
/* 127 */     this.quality = 0;
/* 128 */     this.fashionId = 0;
/* 129 */     this.skills.clear();
/* 130 */     this.buffs.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 135 */     String retVal = "{\"guid\":" + this.guid + ",\"type\":" + this.type + ",\"pos\":" + this.pos + ",\"fury\":" + this.fury + ",\"level\":" + this.level + ",\"id\":" + this.id + ",\"hp\":" + this.hp + ",\"maxHp\":" + this.maxHp + ",\"attack\":" + this.attack + ",\"phyDef\":" + this.phyDef + ",\"magDef\":" + this.magDef + ",\"quality\":" + this.quality + ",\"fashionId\":" + this.fashionId + ",\"skills\":" + this.skills.toString() + ",\"buffs\":" + this.buffs.toString() + "}";
/* 136 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FighterData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */