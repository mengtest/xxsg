/*     */ package com.linlongyx.sanguo.webgame.proto.binary.unparalleled;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class ResetUnparalleledResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int resetTimes;
/*     */   public int curPoint;
/*     */   public int surpStar;
/*  23 */   public ArrayList<Integer> playedPoints = new ArrayList<>();
/*     */   public int isSweep;
/*  25 */   public ArrayList<Integer> layerAddition = new ArrayList<>();
/*  26 */   public ArrayList<KeyValue> partnerHP = new ArrayList<>();
/*  27 */   public ArrayList<Integer> buffs = new ArrayList<>();
/*  28 */   public ArrayList<Integer> layerBox = new ArrayList<>();
/*     */   public int currMaxStar;
/*     */   
/*     */   public ResetUnparalleledResponse() {
/*  32 */     this.eventResponseId = 24409;
/*  33 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public ResetUnparalleledResponse(short retCode) {
/*  37 */     this.eventResponseId = 24409;
/*  38 */     this.codeType = 2;
/*  39 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  44 */     ProtocolUtil.writeInt(out, this.resetTimes);
/*  45 */     ProtocolUtil.writeInt(out, this.curPoint);
/*  46 */     ProtocolUtil.writeInt(out, this.surpStar);
/*     */     
/*  48 */     int size_0 = this.playedPoints.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  52 */       int tmp_0 = ((Integer)this.playedPoints.get(index_0)).intValue();
/*  53 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  55 */     ProtocolUtil.writeInt(out, this.isSweep);
/*     */     
/*  57 */     int size_1 = this.layerAddition.size();
/*  58 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  59 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  61 */       int tmp_1 = ((Integer)this.layerAddition.get(index_1)).intValue();
/*  62 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  65 */     int size_2 = this.partnerHP.size();
/*  66 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  67 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  69 */       KeyValue tmp_2 = this.partnerHP.get(index_2);
/*  70 */       tmp_2.serial(out);
/*     */     } 
/*     */     
/*  73 */     int size_3 = this.buffs.size();
/*  74 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  75 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  77 */       int tmp_3 = ((Integer)this.buffs.get(index_3)).intValue();
/*  78 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*     */     
/*  81 */     int size_4 = this.layerBox.size();
/*  82 */     ProtocolUtil.writeStrArraySize(out, size_4);
/*  83 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/*  85 */       int tmp_4 = ((Integer)this.layerBox.get(index_4)).intValue();
/*  86 */       ProtocolUtil.writeInt(out, tmp_4);
/*     */     } 
/*  88 */     ProtocolUtil.writeInt(out, this.currMaxStar);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  93 */     this.resetTimes = ProtocolUtil.readUTFBinInt(in);
/*  94 */     this.curPoint = ProtocolUtil.readUTFBinInt(in);
/*  95 */     this.surpStar = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  97 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  98 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 100 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 101 */       this.playedPoints.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 103 */     this.isSweep = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 105 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 106 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 108 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 109 */       this.layerAddition.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/* 112 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 113 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 115 */       KeyValue tmp_2 = new KeyValue();
/* 116 */       tmp_2.unserial(in);
/* 117 */       this.partnerHP.add(tmp_2);
/*     */     } 
/*     */     
/* 120 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 121 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 123 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 124 */       this.buffs.add(Integer.valueOf(tmp_3));
/*     */     } 
/*     */     
/* 127 */     int size_4 = ProtocolUtil.readStrArraySize(in);
/* 128 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 130 */       int tmp_4 = ProtocolUtil.readUTFBinInt(in);
/* 131 */       this.layerBox.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 133 */     this.currMaxStar = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResetUnparalleledResponse clone() throws CloneNotSupportedException {
/* 138 */     ResetUnparalleledResponse clone = (ResetUnparalleledResponse)super.clone();
/* 139 */     int size_0 = this.playedPoints.size();
/* 140 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 142 */       int tmp_0 = ((Integer)this.playedPoints.get(index_0)).intValue();
/* 143 */       clone.playedPoints.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 145 */     int size_1 = this.layerAddition.size();
/* 146 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 148 */       int tmp_1 = ((Integer)this.layerAddition.get(index_1)).intValue();
/* 149 */       clone.layerAddition.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 151 */     int size_2 = this.partnerHP.size();
/* 152 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 154 */       KeyValue tmp_2 = this.partnerHP.get(index_2);
/* 155 */       clone.partnerHP.add(tmp_2.clone());
/*     */     } 
/* 157 */     int size_3 = this.buffs.size();
/* 158 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 160 */       int tmp_3 = ((Integer)this.buffs.get(index_3)).intValue();
/* 161 */       clone.buffs.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 163 */     int size_4 = this.layerBox.size();
/* 164 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 166 */       int tmp_4 = ((Integer)this.layerBox.get(index_4)).intValue();
/* 167 */       clone.layerBox.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 169 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 174 */     this.resetTimes = 0;
/* 175 */     this.curPoint = 0;
/* 176 */     this.surpStar = 0;
/* 177 */     this.playedPoints.clear();
/* 178 */     this.isSweep = 0;
/* 179 */     this.layerAddition.clear();
/* 180 */     this.partnerHP.clear();
/* 181 */     this.buffs.clear();
/* 182 */     this.layerBox.clear();
/* 183 */     this.currMaxStar = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 188 */     String retVal = "{\"resetTimes\":" + this.resetTimes + ",\"curPoint\":" + this.curPoint + ",\"surpStar\":" + this.surpStar + ",\"playedPoints\":" + this.playedPoints.toString() + ",\"isSweep\":" + this.isSweep + ",\"layerAddition\":" + this.layerAddition.toString() + ",\"partnerHP\":" + this.partnerHP.toString() + ",\"buffs\":" + this.buffs.toString() + ",\"layerBox\":" + this.layerBox.toString() + ",\"currMaxStar\":" + this.currMaxStar + "}";
/* 189 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binar\\unparalleled\ResetUnparalleledResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */