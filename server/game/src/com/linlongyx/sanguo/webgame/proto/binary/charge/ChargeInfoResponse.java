/*     */ package com.linlongyx.sanguo.webgame.proto.binary.charge;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*     */ public class ChargeInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  19 */   public ArrayList<Integer> firstList = new ArrayList<>();
/*  20 */   public ArrayList<Integer> firstReward = new ArrayList<>();
/*  21 */   public ArrayList<Integer> firstCharge = new ArrayList<>();
/*     */   public int firstChargeCount;
/*     */   public int chargeEndTime;
/*  24 */   public ArrayList<Integer> grooupReward = new ArrayList<>();
/*     */   public long totalCharge;
/*     */   public long todayFirstCharge;
/*  27 */   public ArrayList<Integer> todayFirstReward = new ArrayList<>();
/*     */   public int showLevel;
/*     */   
/*     */   public ChargeInfoResponse() {
/*  31 */     this.eventResponseId = 21601;
/*  32 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public ChargeInfoResponse(short retCode) {
/*  36 */     this.eventResponseId = 21601;
/*  37 */     this.codeType = 2;
/*  38 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  44 */     int size_0 = this.firstList.size();
/*  45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  48 */       int tmp_0 = ((Integer)this.firstList.get(index_0)).intValue();
/*  49 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  52 */     int size_1 = this.firstReward.size();
/*  53 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  54 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  56 */       int tmp_1 = ((Integer)this.firstReward.get(index_1)).intValue();
/*  57 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  60 */     int size_2 = this.firstCharge.size();
/*  61 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  62 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  64 */       int tmp_2 = ((Integer)this.firstCharge.get(index_2)).intValue();
/*  65 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*  67 */     ProtocolUtil.writeInt(out, this.firstChargeCount);
/*  68 */     ProtocolUtil.writeInt(out, this.chargeEndTime);
/*     */     
/*  70 */     int size_3 = this.grooupReward.size();
/*  71 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  72 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  74 */       int tmp_3 = ((Integer)this.grooupReward.get(index_3)).intValue();
/*  75 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*  77 */     ProtocolUtil.writeLong(out, this.totalCharge);
/*  78 */     ProtocolUtil.writeLong(out, this.todayFirstCharge);
/*     */     
/*  80 */     int size_4 = this.todayFirstReward.size();
/*  81 */     ProtocolUtil.writeStrArraySize(out, size_4);
/*  82 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/*  84 */       int tmp_4 = ((Integer)this.todayFirstReward.get(index_4)).intValue();
/*  85 */       ProtocolUtil.writeInt(out, tmp_4);
/*     */     } 
/*  87 */     ProtocolUtil.writeInt(out, this.showLevel);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  93 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  94 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  96 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  97 */       this.firstList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/* 100 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 101 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 103 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 104 */       this.firstReward.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/* 107 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 108 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 110 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/* 111 */       this.firstCharge.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 113 */     this.firstChargeCount = ProtocolUtil.readUTFBinInt(in);
/* 114 */     this.chargeEndTime = ProtocolUtil.readUTFBinInt(in);
/*     */     
/* 116 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 117 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 119 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 120 */       this.grooupReward.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 122 */     this.totalCharge = ProtocolUtil.readUTFBinLong(in);
/* 123 */     this.todayFirstCharge = ProtocolUtil.readUTFBinLong(in);
/*     */     
/* 125 */     int size_4 = ProtocolUtil.readStrArraySize(in);
/* 126 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 128 */       int tmp_4 = ProtocolUtil.readUTFBinInt(in);
/* 129 */       this.todayFirstReward.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 131 */     this.showLevel = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChargeInfoResponse clone() throws CloneNotSupportedException {
/* 136 */     ChargeInfoResponse clone = (ChargeInfoResponse)super.clone();
/* 137 */     int size_0 = this.firstList.size();
/* 138 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 140 */       int tmp_0 = ((Integer)this.firstList.get(index_0)).intValue();
/* 141 */       clone.firstList.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 143 */     int size_1 = this.firstReward.size();
/* 144 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 146 */       int tmp_1 = ((Integer)this.firstReward.get(index_1)).intValue();
/* 147 */       clone.firstReward.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 149 */     int size_2 = this.firstCharge.size();
/* 150 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 152 */       int tmp_2 = ((Integer)this.firstCharge.get(index_2)).intValue();
/* 153 */       clone.firstCharge.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 155 */     int size_3 = this.grooupReward.size();
/* 156 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 158 */       int tmp_3 = ((Integer)this.grooupReward.get(index_3)).intValue();
/* 159 */       clone.grooupReward.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 161 */     int size_4 = this.todayFirstReward.size();
/* 162 */     for (int index_4 = 0; index_4 < size_4; index_4++) {
/*     */       
/* 164 */       int tmp_4 = ((Integer)this.todayFirstReward.get(index_4)).intValue();
/* 165 */       clone.todayFirstReward.add(Integer.valueOf(tmp_4));
/*     */     } 
/* 167 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 172 */     this.firstList.clear();
/* 173 */     this.firstReward.clear();
/* 174 */     this.firstCharge.clear();
/* 175 */     this.firstChargeCount = 0;
/* 176 */     this.chargeEndTime = 0;
/* 177 */     this.grooupReward.clear();
/* 178 */     this.totalCharge = 0L;
/* 179 */     this.todayFirstCharge = 0L;
/* 180 */     this.todayFirstReward.clear();
/* 181 */     this.showLevel = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 186 */     String retVal = "{\"firstList\":" + this.firstList.toString() + ",\"firstReward\":" + this.firstReward.toString() + ",\"firstCharge\":" + this.firstCharge.toString() + ",\"firstChargeCount\":" + this.firstChargeCount + ",\"chargeEndTime\":" + this.chargeEndTime + ",\"grooupReward\":" + this.grooupReward.toString() + ",\"totalCharge\":" + this.totalCharge + ",\"todayFirstCharge\":" + this.todayFirstCharge + ",\"todayFirstReward\":" + this.todayFirstReward.toString() + ",\"showLevel\":" + this.showLevel + "}";
/* 187 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\charge\ChargeInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */