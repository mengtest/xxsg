/*     */ package com.linlongyx.sanguo.webgame.proto.binary.unparalleled;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlotExpSweepData;
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
/*     */ public class SweepUnparalleledResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int curPoint;
/*     */   public int currMaxStar;
/*     */   public int surpStar;
/*  23 */   public ArrayList<Integer> playedPoints = new ArrayList<>();
/*  24 */   public ArrayList<PlotExpSweepData> reward = new ArrayList<>();
/*     */   public int isSweep;
/*  26 */   public ArrayList<Integer> layerBox = new ArrayList<>();
/*  27 */   public ArrayList<Integer> attrbuteAddition = new ArrayList<>();
/*  28 */   public ArrayList<Integer> layerAddition = new ArrayList<>();
/*     */   
/*     */   public SweepUnparalleledResponse() {
/*  31 */     this.eventResponseId = 24410;
/*  32 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public SweepUnparalleledResponse(short retCode) {
/*  36 */     this.eventResponseId = 24410;
/*  37 */     this.codeType = 2;
/*  38 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  43 */     ProtocolUtil.writeInt(out, this.curPoint);
/*  44 */     ProtocolUtil.writeInt(out, this.currMaxStar);
/*  45 */     ProtocolUtil.writeInt(out, this.surpStar);
/*     */     
/*  47 */     int size_0 = this.playedPoints.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  51 */       int tmp_0 = ((Integer)this.playedPoints.get(index_0)).intValue();
/*  52 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  55 */     int size_1 = this.reward.size();
/*  56 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  57 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  59 */       PlotExpSweepData tmp_1 = this.reward.get(index_1);
/*  60 */       tmp_1.serial(out);
/*     */     } 
/*  62 */     ProtocolUtil.writeInt(out, this.isSweep);
/*     */     
/*  64 */     int size_2 = this.layerBox.size();
/*  65 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  66 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  68 */       int tmp_2 = ((Integer)this.layerBox.get(index_2)).intValue();
/*  69 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*     */     
/*  72 */     int size_3 = this.attrbuteAddition.size();
/*  73 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  74 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  76 */       int tmp_3 = ((Integer)this.attrbuteAddition.get(index_3)).intValue();
/*  77 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*     */     
/*  80 */     int size_4 = this.layerAddition.size();
/*  81 */     ProtocolUtil.writeStrArraySize(out, size_4);
/*  82 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/*  84 */       int tmp_4 = ((Integer)this.layerAddition.get(index_4)).intValue();
/*  85 */       ProtocolUtil.writeInt(out, tmp_4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  91 */     this.curPoint = ProtocolUtil.readUTFBinInt(in);
/*  92 */     this.currMaxStar = ProtocolUtil.readUTFBinInt(in);
/*  93 */     this.surpStar = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  95 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  96 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  98 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  99 */       this.playedPoints.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/* 102 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 103 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 105 */       PlotExpSweepData tmp_1 = new PlotExpSweepData();
/* 106 */       tmp_1.unserial(in);
/* 107 */       this.reward.add(tmp_1);
/*     */     } 
/* 109 */     this.isSweep = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 111 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 112 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 114 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/* 115 */       this.layerBox.add(Integer.valueOf(tmp_2));
/*     */     } 
/*     */     
/* 118 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 119 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 121 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 122 */       this.attrbuteAddition.add(Integer.valueOf(tmp_3));
/*     */     } 
/*     */     
/* 125 */     int size_4 = ProtocolUtil.readStrArraySize(in);
/* 126 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 128 */       int tmp_4 = ProtocolUtil.readUTFBinInt(in);
/* 129 */       this.layerAddition.add(Integer.valueOf(tmp_4));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SweepUnparalleledResponse clone() throws CloneNotSupportedException {
/* 135 */     SweepUnparalleledResponse clone = (SweepUnparalleledResponse)super.clone();
/* 136 */     int size_0 = this.playedPoints.size();
/* 137 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 139 */       int tmp_0 = ((Integer)this.playedPoints.get(index_0)).intValue();
/* 140 */       clone.playedPoints.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 142 */     int size_1 = this.reward.size();
/* 143 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 145 */       PlotExpSweepData tmp_1 = this.reward.get(index_1);
/* 146 */       clone.reward.add(tmp_1.clone());
/*     */     } 
/* 148 */     int size_2 = this.layerBox.size();
/* 149 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 151 */       int tmp_2 = ((Integer)this.layerBox.get(index_2)).intValue();
/* 152 */       clone.layerBox.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 154 */     int size_3 = this.attrbuteAddition.size();
/* 155 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 157 */       int tmp_3 = ((Integer)this.attrbuteAddition.get(index_3)).intValue();
/* 158 */       clone.attrbuteAddition.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 160 */     int size_4 = this.layerAddition.size();
/* 161 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 163 */       int tmp_4 = ((Integer)this.layerAddition.get(index_4)).intValue();
/* 164 */       clone.layerAddition.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 166 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 171 */     this.curPoint = 0;
/* 172 */     this.currMaxStar = 0;
/* 173 */     this.surpStar = 0;
/* 174 */     this.playedPoints.clear();
/* 175 */     this.reward.clear();
/* 176 */     this.isSweep = 0;
/* 177 */     this.layerBox.clear();
/* 178 */     this.attrbuteAddition.clear();
/* 179 */     this.layerAddition.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 184 */     String retVal = "{\"curPoint\":" + this.curPoint + ",\"currMaxStar\":" + this.currMaxStar + ",\"surpStar\":" + this.surpStar + ",\"playedPoints\":" + this.playedPoints.toString() + ",\"reward\":" + this.reward.toString() + ",\"isSweep\":" + this.isSweep + ",\"layerBox\":" + this.layerBox.toString() + ",\"attrbuteAddition\":" + this.attrbuteAddition.toString() + ",\"layerAddition\":" + this.layerAddition.toString() + "}";
/* 185 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binar\\unparalleled\SweepUnparalleledResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */