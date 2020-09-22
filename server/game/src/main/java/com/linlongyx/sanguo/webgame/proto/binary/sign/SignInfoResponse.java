/*     */ package com.linlongyx.sanguo.webgame.proto.binary.sign;
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
/*     */ public class SignInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int signToday;
/*     */   public int signCount;
/*     */   public long weekCharge;
/*     */   public long todayCharge;
/*  23 */   public ArrayList<Integer> todayReward = new ArrayList<>();
/*  24 */   public ArrayList<Integer> weekReward = new ArrayList<>();
/*  25 */   public ArrayList<Integer> signReward = new ArrayList<>();
/*     */   public int level;
/*     */   
/*     */   public SignInfoResponse() {
/*  29 */     this.eventResponseId = 25201;
/*  30 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public SignInfoResponse(short retCode) {
/*  34 */     this.eventResponseId = 25201;
/*  35 */     this.codeType = 2;
/*  36 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  41 */     ProtocolUtil.writeInt(out, this.signToday);
/*  42 */     ProtocolUtil.writeInt(out, this.signCount);
/*  43 */     ProtocolUtil.writeLong(out, this.weekCharge);
/*  44 */     ProtocolUtil.writeLong(out, this.todayCharge);
/*     */     
/*  46 */     int size_0 = this.todayReward.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  50 */       int tmp_0 = ((Integer)this.todayReward.get(index_0)).intValue();
/*  51 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  54 */     int size_1 = this.weekReward.size();
/*  55 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  56 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  58 */       int tmp_1 = ((Integer)this.weekReward.get(index_1)).intValue();
/*  59 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  62 */     int size_2 = this.signReward.size();
/*  63 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  64 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  66 */       int tmp_2 = ((Integer)this.signReward.get(index_2)).intValue();
/*  67 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*  69 */     ProtocolUtil.writeInt(out, this.level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  74 */     this.signToday = ProtocolUtil.readUTFBinInt(in);
/*  75 */     this.signCount = ProtocolUtil.readUTFBinInt(in);
/*  76 */     this.weekCharge = ProtocolUtil.readUTFBinLong(in);
/*  77 */     this.todayCharge = ProtocolUtil.readUTFBinLong(in);
/*     */     
/*  79 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  80 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  82 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  83 */       this.todayReward.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  86 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  87 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  89 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  90 */       this.weekReward.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/*  93 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  94 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  96 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/*  97 */       this.signReward.add(Integer.valueOf(tmp_2));
/*     */     } 
/*  99 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public SignInfoResponse clone() throws CloneNotSupportedException {
/* 104 */     SignInfoResponse clone = (SignInfoResponse)super.clone();
/* 105 */     int size_0 = this.todayReward.size();
/* 106 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 108 */       int tmp_0 = ((Integer)this.todayReward.get(index_0)).intValue();
/* 109 */       clone.todayReward.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 111 */     int size_1 = this.weekReward.size();
/* 112 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 114 */       int tmp_1 = ((Integer)this.weekReward.get(index_1)).intValue();
/* 115 */       clone.weekReward.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 117 */     int size_2 = this.signReward.size();
/* 118 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 120 */       int tmp_2 = ((Integer)this.signReward.get(index_2)).intValue();
/* 121 */       clone.signReward.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 123 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 128 */     this.signToday = 0;
/* 129 */     this.signCount = 0;
/* 130 */     this.weekCharge = 0L;
/* 131 */     this.todayCharge = 0L;
/* 132 */     this.todayReward.clear();
/* 133 */     this.weekReward.clear();
/* 134 */     this.signReward.clear();
/* 135 */     this.level = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 140 */     String retVal = "{\"signToday\":" + this.signToday + ",\"signCount\":" + this.signCount + ",\"weekCharge\":" + this.weekCharge + ",\"todayCharge\":" + this.todayCharge + ",\"todayReward\":" + this.todayReward.toString() + ",\"weekReward\":" + this.weekReward.toString() + ",\"signReward\":" + this.signReward.toString() + ",\"level\":" + this.level + "}";
/* 141 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sign\SignInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */