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
/*     */ public class GetUnparalleledInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int resetTimes;
/*  21 */   public ArrayList<Integer> layerAddition = new ArrayList<>();
/*     */   public int curPoint;
/*     */   public int currMaxStar;
/*     */   public int surpStar;
/*  25 */   public ArrayList<KeyValue> layerStar = new ArrayList<>();
/*  26 */   public ArrayList<Integer> playedPoints = new ArrayList<>();
/*  27 */   public ArrayList<Integer> layerBox = new ArrayList<>();
/*  28 */   public ArrayList<Integer> attrbuteAddition = new ArrayList<>();
/*     */   public int lastMaxStar;
/*  30 */   public ArrayList<KeyValue> partnerHP = new ArrayList<>();
/*     */   public int isSweep;
/*     */   
/*     */   public GetUnparalleledInfoResponse() {
/*  34 */     this.eventResponseId = 24408;
/*  35 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public GetUnparalleledInfoResponse(short retCode) {
/*  39 */     this.eventResponseId = 24408;
/*  40 */     this.codeType = 2;
/*  41 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  46 */     ProtocolUtil.writeInt(out, this.resetTimes);
/*     */     
/*  48 */     int size_0 = this.layerAddition.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  52 */       int tmp_0 = ((Integer)this.layerAddition.get(index_0)).intValue();
/*  53 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  55 */     ProtocolUtil.writeInt(out, this.curPoint);
/*  56 */     ProtocolUtil.writeInt(out, this.currMaxStar);
/*  57 */     ProtocolUtil.writeInt(out, this.surpStar);
/*     */     
/*  59 */     int size_1 = this.layerStar.size();
/*  60 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  61 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  63 */       KeyValue tmp_1 = this.layerStar.get(index_1);
/*  64 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  67 */     int size_2 = this.playedPoints.size();
/*  68 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  69 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  71 */       int tmp_2 = ((Integer)this.playedPoints.get(index_2)).intValue();
/*  72 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*     */     
/*  75 */     int size_3 = this.layerBox.size();
/*  76 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  77 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  79 */       int tmp_3 = ((Integer)this.layerBox.get(index_3)).intValue();
/*  80 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*     */     
/*  83 */     int size_4 = this.attrbuteAddition.size();
/*  84 */     ProtocolUtil.writeStrArraySize(out, size_4);
/*  85 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/*  87 */       int tmp_4 = ((Integer)this.attrbuteAddition.get(index_4)).intValue();
/*  88 */       ProtocolUtil.writeInt(out, tmp_4);
/*     */     } 
/*  90 */     ProtocolUtil.writeInt(out, this.lastMaxStar);
/*     */     
/*  92 */     int size_5 = this.partnerHP.size();
/*  93 */     ProtocolUtil.writeStrArraySize(out, size_5);
/*  94 */     for (int index_5 = 0; index_5 < size_5; index_5++) {
/*     */       
/*  96 */       KeyValue tmp_5 = this.partnerHP.get(index_5);
/*  97 */       tmp_5.serial(out);
/*     */     } 
/*  99 */     ProtocolUtil.writeInt(out, this.isSweep);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/* 104 */     this.resetTimes = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 106 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 107 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 109 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 110 */       this.layerAddition.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 112 */     this.curPoint = ProtocolUtil.readUTFBinInt(in);
/* 113 */     this.currMaxStar = ProtocolUtil.readUTFBinInt(in);
/* 114 */     this.surpStar = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 116 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 117 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 119 */       KeyValue tmp_1 = new KeyValue();
/* 120 */       tmp_1.unserial(in);
/* 121 */       this.layerStar.add(tmp_1);
/*     */     } 
/*     */     
/* 124 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 125 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 127 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/* 128 */       this.playedPoints.add(Integer.valueOf(tmp_2));
/*     */     } 
/*     */     
/* 131 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 132 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 134 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 135 */       this.layerBox.add(Integer.valueOf(tmp_3));
/*     */     } 
/*     */     
/* 138 */     int size_4 = ProtocolUtil.readStrArraySize(in);
/* 139 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 141 */       int tmp_4 = ProtocolUtil.readUTFBinInt(in);
/* 142 */       this.attrbuteAddition.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 144 */     this.lastMaxStar = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 146 */     int size_5 = ProtocolUtil.readStrArraySize(in);
/* 147 */     for (int index_5 = 0; index_5 < size_5; index_5++) {
/*     */       
/* 149 */       KeyValue tmp_5 = new KeyValue();
/* 150 */       tmp_5.unserial(in);
/* 151 */       this.partnerHP.add(tmp_5);
/*     */     } 
/* 153 */     this.isSweep = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public GetUnparalleledInfoResponse clone() throws CloneNotSupportedException {
/* 158 */     GetUnparalleledInfoResponse clone = (GetUnparalleledInfoResponse)super.clone();
/* 159 */     int size_0 = this.layerAddition.size();
/* 160 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 162 */       int tmp_0 = ((Integer)this.layerAddition.get(index_0)).intValue();
/* 163 */       clone.layerAddition.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 165 */     int size_1 = this.layerStar.size();
/* 166 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 168 */       KeyValue tmp_1 = this.layerStar.get(index_1);
/* 169 */       clone.layerStar.add(tmp_1.clone());
/*     */     } 
/* 171 */     int size_2 = this.playedPoints.size();
/* 172 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 174 */       int tmp_2 = ((Integer)this.playedPoints.get(index_2)).intValue();
/* 175 */       clone.playedPoints.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 177 */     int size_3 = this.layerBox.size();
/* 178 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 180 */       int tmp_3 = ((Integer)this.layerBox.get(index_3)).intValue();
/* 181 */       clone.layerBox.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 183 */     int size_4 = this.attrbuteAddition.size();
/* 184 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 186 */       int tmp_4 = ((Integer)this.attrbuteAddition.get(index_4)).intValue();
/* 187 */       clone.attrbuteAddition.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 189 */     int size_5 = this.partnerHP.size();
/* 190 */     for (int index_5 = 0; index_5 < size_5; index_5++) {
/*     */       
/* 192 */       KeyValue tmp_5 = this.partnerHP.get(index_5);
/* 193 */       clone.partnerHP.add(tmp_5.clone());
/*     */     } 
/* 195 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 200 */     this.resetTimes = 0;
/* 201 */     this.layerAddition.clear();
/* 202 */     this.curPoint = 0;
/* 203 */     this.currMaxStar = 0;
/* 204 */     this.surpStar = 0;
/* 205 */     this.layerStar.clear();
/* 206 */     this.playedPoints.clear();
/* 207 */     this.layerBox.clear();
/* 208 */     this.attrbuteAddition.clear();
/* 209 */     this.lastMaxStar = 0;
/* 210 */     this.partnerHP.clear();
/* 211 */     this.isSweep = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     String retVal = "{\"resetTimes\":" + this.resetTimes + ",\"layerAddition\":" + this.layerAddition.toString() + ",\"curPoint\":" + this.curPoint + ",\"currMaxStar\":" + this.currMaxStar + ",\"surpStar\":" + this.surpStar + ",\"layerStar\":" + this.layerStar.toString() + ",\"playedPoints\":" + this.playedPoints.toString() + ",\"layerBox\":" + this.layerBox.toString() + ",\"attrbuteAddition\":" + this.attrbuteAddition.toString() + ",\"lastMaxStar\":" + this.lastMaxStar + ",\"partnerHP\":" + this.partnerHP.toString() + ",\"isSweep\":" + this.isSweep + "}";
/* 217 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binar\\unparalleled\GetUnparalleledInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */