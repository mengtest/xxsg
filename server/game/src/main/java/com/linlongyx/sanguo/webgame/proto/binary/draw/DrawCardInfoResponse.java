/*     */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DrawCardData;
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
/*     */ @Message
/*     */ public class DrawCardInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int drawId;
/*     */   public int playTimes;
/*     */   public int freeTime;
/*     */   public int endTime;
/*  24 */   public ArrayList<DrawCardData> openList = new ArrayList<>();
/*  25 */   public ArrayList<Integer> counts = new ArrayList<>();
/*  26 */   public ArrayList<KeyValue> records = new ArrayList<>();
/*     */   public int cleanCCY;
/*  28 */   public ArrayList<Integer> rares = new ArrayList<>();
/*     */   
/*     */   public DrawCardInfoResponse() {
/*  31 */     this.eventResponseId = 21802;
/*  32 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public DrawCardInfoResponse(short retCode) {
/*  36 */     this.eventResponseId = 21802;
/*  37 */     this.codeType = 2;
/*  38 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  43 */     ProtocolUtil.writeInt(out, this.drawId);
/*  44 */     ProtocolUtil.writeInt(out, this.playTimes);
/*  45 */     ProtocolUtil.writeInt(out, this.freeTime);
/*  46 */     ProtocolUtil.writeInt(out, this.endTime);
/*     */     
/*  48 */     int size_0 = this.openList.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  52 */       DrawCardData tmp_0 = this.openList.get(index_0);
/*  53 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  56 */     int size_1 = this.counts.size();
/*  57 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  58 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  60 */       int tmp_1 = ((Integer)this.counts.get(index_1)).intValue();
/*  61 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  64 */     int size_2 = this.records.size();
/*  65 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  66 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  68 */       KeyValue tmp_2 = this.records.get(index_2);
/*  69 */       tmp_2.serial(out);
/*     */     } 
/*  71 */     ProtocolUtil.writeInt(out, this.cleanCCY);
/*     */     
/*  73 */     int size_3 = this.rares.size();
/*  74 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  75 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  77 */       int tmp_3 = ((Integer)this.rares.get(index_3)).intValue();
/*  78 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  84 */     this.drawId = ProtocolUtil.readUTFBinInt(in);
/*  85 */     this.playTimes = ProtocolUtil.readUTFBinInt(in);
/*  86 */     this.freeTime = ProtocolUtil.readUTFBinInt(in);
/*  87 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  89 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  90 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  92 */       DrawCardData tmp_0 = new DrawCardData();
/*  93 */       tmp_0.unserial(in);
/*  94 */       this.openList.add(tmp_0);
/*     */     } 
/*     */     
/*  97 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  98 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 100 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 101 */       this.counts.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/* 104 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 105 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 107 */       KeyValue tmp_2 = new KeyValue();
/* 108 */       tmp_2.unserial(in);
/* 109 */       this.records.add(tmp_2);
/*     */     } 
/* 111 */     this.cleanCCY = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 113 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 114 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 116 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 117 */       this.rares.add(Integer.valueOf(tmp_3));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DrawCardInfoResponse clone() throws CloneNotSupportedException {
/* 123 */     DrawCardInfoResponse clone = (DrawCardInfoResponse)super.clone();
/* 124 */     int size_0 = this.openList.size();
/* 125 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 127 */       DrawCardData tmp_0 = this.openList.get(index_0);
/* 128 */       clone.openList.add(tmp_0.clone());
/*     */     } 
/* 130 */     int size_1 = this.counts.size();
/* 131 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 133 */       int tmp_1 = ((Integer)this.counts.get(index_1)).intValue();
/* 134 */       clone.counts.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 136 */     int size_2 = this.records.size();
/* 137 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 139 */       KeyValue tmp_2 = this.records.get(index_2);
/* 140 */       clone.records.add(tmp_2.clone());
/*     */     } 
/* 142 */     int size_3 = this.rares.size();
/* 143 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 145 */       int tmp_3 = ((Integer)this.rares.get(index_3)).intValue();
/* 146 */       clone.rares.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 148 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 153 */     this.drawId = 0;
/* 154 */     this.playTimes = 0;
/* 155 */     this.freeTime = 0;
/* 156 */     this.endTime = 0;
/* 157 */     this.openList.clear();
/* 158 */     this.counts.clear();
/* 159 */     this.records.clear();
/* 160 */     this.cleanCCY = 0;
/* 161 */     this.rares.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     String retVal = "{\"drawId\":" + this.drawId + ",\"playTimes\":" + this.playTimes + ",\"freeTime\":" + this.freeTime + ",\"endTime\":" + this.endTime + ",\"openList\":" + this.openList.toString() + ",\"counts\":" + this.counts.toString() + ",\"records\":" + this.records.toString() + ",\"cleanCCY\":" + this.cleanCCY + ",\"rares\":" + this.rares.toString() + "}";
/* 167 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */