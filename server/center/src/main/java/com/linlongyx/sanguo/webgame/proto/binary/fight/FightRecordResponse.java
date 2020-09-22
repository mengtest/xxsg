/*     */ package com.linlongyx.sanguo.webgame.proto.binary.fight;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.StringUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BoutPlayData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FightGroupData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.HpData;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class FightRecordResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public byte type;
/*     */   public String id;
/*     */   public byte result;
/*  24 */   public ArrayList<FightGroupData> lGroups = new ArrayList<>();
/*  25 */   public ArrayList<FightGroupData> rGroups = new ArrayList<>();
/*  26 */   public ArrayList<BoutPlayData> boutPlayData = new ArrayList<>();
/*  27 */   public ArrayList<HpData> fightersHp = new ArrayList<>();
/*     */   public String debugStr;
/*     */   public long totalHurt;
/*     */   
/*     */   public FightRecordResponse() {
/*  32 */     this.eventResponseId = 20204;
/*  33 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  38 */     ProtocolUtil.writeByte(out, this.type);
/*  39 */     ProtocolUtil.writeUTFBinary(out, this.id);
/*  40 */     ProtocolUtil.writeByte(out, this.result);
/*     */     
/*  42 */     int size_0 = this.lGroups.size();
/*  43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  46 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/*  47 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  50 */     int size_1 = this.rGroups.size();
/*  51 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  52 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  54 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/*  55 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  58 */     int size_2 = this.boutPlayData.size();
/*  59 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  60 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  62 */       BoutPlayData tmp_2 = this.boutPlayData.get(index_2);
/*  63 */       tmp_2.serial(out);
/*     */     } 
/*     */     
/*  66 */     int size_3 = this.fightersHp.size();
/*  67 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  68 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  70 */       HpData tmp_3 = this.fightersHp.get(index_3);
/*  71 */       tmp_3.serial(out);
/*     */     } 
/*  73 */     ProtocolUtil.writeUTFBinary(out, this.debugStr);
/*  74 */     ProtocolUtil.writeLong(out, this.totalHurt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  79 */     this.type = ProtocolUtil.readUTFBinByte(in);
/*  80 */     this.id = ProtocolUtil.readUTFStr(in);
/*  81 */     this.result = ProtocolUtil.readUTFBinByte(in);
/*     */     
/*  83 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  84 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  86 */       FightGroupData tmp_0 = new FightGroupData();
/*  87 */       tmp_0.unserial(in);
/*  88 */       this.lGroups.add(tmp_0);
/*     */     } 
/*     */     
/*  91 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  92 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  94 */       FightGroupData tmp_1 = new FightGroupData();
/*  95 */       tmp_1.unserial(in);
/*  96 */       this.rGroups.add(tmp_1);
/*     */     } 
/*     */     
/*  99 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 100 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 102 */       BoutPlayData tmp_2 = new BoutPlayData();
/* 103 */       tmp_2.unserial(in);
/* 104 */       this.boutPlayData.add(tmp_2);
/*     */     } 
/*     */     
/* 107 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 108 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 110 */       HpData tmp_3 = new HpData();
/* 111 */       tmp_3.unserial(in);
/* 112 */       this.fightersHp.add(tmp_3);
/*     */     } 
/* 114 */     this.debugStr = ProtocolUtil.readUTFStr(in);
/* 115 */     this.totalHurt = ProtocolUtil.readUTFBinLong(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public FightRecordResponse clone() throws CloneNotSupportedException {
/* 120 */     FightRecordResponse clone = (FightRecordResponse)super.clone();
/* 121 */     int size_0 = this.lGroups.size();
/* 122 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 124 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/* 125 */       clone.lGroups.add(tmp_0.clone());
/*     */     } 
/* 127 */     int size_1 = this.rGroups.size();
/* 128 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 130 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/* 131 */       clone.rGroups.add(tmp_1.clone());
/*     */     } 
/* 133 */     int size_2 = this.boutPlayData.size();
/* 134 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 136 */       BoutPlayData tmp_2 = this.boutPlayData.get(index_2);
/* 137 */       clone.boutPlayData.add(tmp_2.clone());
/*     */     } 
/* 139 */     int size_3 = this.fightersHp.size();
/* 140 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 142 */       HpData tmp_3 = this.fightersHp.get(index_3);
/* 143 */       clone.fightersHp.add(tmp_3.clone());
/*     */     } 
/* 145 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 150 */     this.type = 0;
/* 151 */     this.id = null;
/* 152 */     this.result = 0;
/* 153 */     this.lGroups.clear();
/* 154 */     this.rGroups.clear();
/* 155 */     this.boutPlayData.clear();
/* 156 */     this.fightersHp.clear();
/* 157 */     this.debugStr = null;
/* 158 */     this.totalHurt = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 163 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + StringUtil.str2Str(this.id) + ",\"result\":" + this.result + ",\"lGroups\":" + this.lGroups.toString() + ",\"rGroups\":" + this.rGroups.toString() + ",\"boutPlayData\":" + this.boutPlayData.toString() + ",\"fightersHp\":" + this.fightersHp.toString() + ",\"debugStr\":" + StringUtil.str2Str(this.debugStr) + ",\"totalHurt\":" + this.totalHurt + "}";
/* 164 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\FightRecordResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */