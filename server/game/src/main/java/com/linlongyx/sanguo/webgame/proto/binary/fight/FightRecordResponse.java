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
/*     */   public FightRecordResponse(short retCode) {
/*  37 */     this.eventResponseId = 20204;
/*  38 */     this.codeType = 2;
/*  39 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  44 */     ProtocolUtil.writeByte(out, this.type);
/*  45 */     ProtocolUtil.writeUTFBinary(out, this.id);
/*  46 */     ProtocolUtil.writeByte(out, this.result);
/*     */     
/*  48 */     int size_0 = this.lGroups.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  52 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/*  53 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  56 */     int size_1 = this.rGroups.size();
/*  57 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  58 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  60 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/*  61 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  64 */     int size_2 = this.boutPlayData.size();
/*  65 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  66 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  68 */       BoutPlayData tmp_2 = this.boutPlayData.get(index_2);
/*  69 */       tmp_2.serial(out);
/*     */     } 
/*     */     
/*  72 */     int size_3 = this.fightersHp.size();
/*  73 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  74 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  76 */       HpData tmp_3 = this.fightersHp.get(index_3);
/*  77 */       tmp_3.serial(out);
/*     */     } 
/*  79 */     ProtocolUtil.writeUTFBinary(out, this.debugStr);
/*  80 */     ProtocolUtil.writeLong(out, this.totalHurt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  85 */     this.type = ProtocolUtil.readUTFBinByte(in);
/*  86 */     this.id = ProtocolUtil.readUTFStr(in);
/*  87 */     this.result = ProtocolUtil.readUTFBinByte(in);
/*     */     
/*  89 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  90 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  92 */       FightGroupData tmp_0 = new FightGroupData();
/*  93 */       tmp_0.unserial(in);
/*  94 */       this.lGroups.add(tmp_0);
/*     */     } 
/*     */     
/*  97 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  98 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 100 */       FightGroupData tmp_1 = new FightGroupData();
/* 101 */       tmp_1.unserial(in);
/* 102 */       this.rGroups.add(tmp_1);
/*     */     } 
/*     */     
/* 105 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 106 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 108 */       BoutPlayData tmp_2 = new BoutPlayData();
/* 109 */       tmp_2.unserial(in);
/* 110 */       this.boutPlayData.add(tmp_2);
/*     */     } 
/*     */     
/* 113 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 114 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 116 */       HpData tmp_3 = new HpData();
/* 117 */       tmp_3.unserial(in);
/* 118 */       this.fightersHp.add(tmp_3);
/*     */     } 
/* 120 */     this.debugStr = ProtocolUtil.readUTFStr(in);
/* 121 */     this.totalHurt = ProtocolUtil.readUTFBinLong(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public FightRecordResponse clone() throws CloneNotSupportedException {
/* 126 */     FightRecordResponse clone = (FightRecordResponse)super.clone();
/* 127 */     int size_0 = this.lGroups.size();
/* 128 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 130 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/* 131 */       clone.lGroups.add(tmp_0.clone());
/*     */     } 
/* 133 */     int size_1 = this.rGroups.size();
/* 134 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 136 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/* 137 */       clone.rGroups.add(tmp_1.clone());
/*     */     } 
/* 139 */     int size_2 = this.boutPlayData.size();
/* 140 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 142 */       BoutPlayData tmp_2 = this.boutPlayData.get(index_2);
/* 143 */       clone.boutPlayData.add(tmp_2.clone());
/*     */     } 
/* 145 */     int size_3 = this.fightersHp.size();
/* 146 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 148 */       HpData tmp_3 = this.fightersHp.get(index_3);
/* 149 */       clone.fightersHp.add(tmp_3.clone());
/*     */     } 
/* 151 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 156 */     this.type = 0;
/* 157 */     this.id = null;
/* 158 */     this.result = 0;
/* 159 */     this.lGroups.clear();
/* 160 */     this.rGroups.clear();
/* 161 */     this.boutPlayData.clear();
/* 162 */     this.fightersHp.clear();
/* 163 */     this.debugStr = null;
/* 164 */     this.totalHurt = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 169 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + StringUtil.str2Str(this.id) + ",\"result\":" + this.result + ",\"lGroups\":" + this.lGroups.toString() + ",\"rGroups\":" + this.rGroups.toString() + ",\"boutPlayData\":" + this.boutPlayData.toString() + ",\"fightersHp\":" + this.fightersHp.toString() + ",\"debugStr\":" + StringUtil.str2Str(this.debugStr) + ",\"totalHurt\":" + this.totalHurt + "}";
/* 170 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\FightRecordResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */