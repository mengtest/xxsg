/*     */ package com.linlongyx.sanguo.webgame.proto.binary.consume;
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
/*     */ public class ConsumeRebateInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int actId;
/*     */   public long takeConsume;
/*     */   public long postureConsume;
/*     */   public long zhenFaConsume;
/*  23 */   public ArrayList<Integer> takeReward = new ArrayList<>();
/*  24 */   public ArrayList<Integer> postureReward = new ArrayList<>();
/*  25 */   public ArrayList<Integer> zhenFaReward = new ArrayList<>();
/*     */   
/*     */   public ConsumeRebateInfoResponse() {
/*  28 */     this.eventResponseId = 25501;
/*  29 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public ConsumeRebateInfoResponse(short retCode) {
/*  33 */     this.eventResponseId = 25501;
/*  34 */     this.codeType = 2;
/*  35 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  40 */     ProtocolUtil.writeInt(out, this.actId);
/*  41 */     ProtocolUtil.writeLong(out, this.takeConsume);
/*  42 */     ProtocolUtil.writeLong(out, this.postureConsume);
/*  43 */     ProtocolUtil.writeLong(out, this.zhenFaConsume);
/*     */     
/*  45 */     int size_0 = this.takeReward.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  49 */       int tmp_0 = ((Integer)this.takeReward.get(index_0)).intValue();
/*  50 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  53 */     int size_1 = this.postureReward.size();
/*  54 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  55 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  57 */       int tmp_1 = ((Integer)this.postureReward.get(index_1)).intValue();
/*  58 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  61 */     int size_2 = this.zhenFaReward.size();
/*  62 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  63 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  65 */       int tmp_2 = ((Integer)this.zhenFaReward.get(index_2)).intValue();
/*  66 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  72 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/*  73 */     this.takeConsume = ProtocolUtil.readUTFBinLong(in);
/*  74 */     this.postureConsume = ProtocolUtil.readUTFBinLong(in);
/*  75 */     this.zhenFaConsume = ProtocolUtil.readUTFBinLong(in);
/*     */     
/*  77 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  78 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  80 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  81 */       this.takeReward.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  84 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  85 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  87 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  88 */       this.postureReward.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/*  91 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  92 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  94 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/*  95 */       this.zhenFaReward.add(Integer.valueOf(tmp_2));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ConsumeRebateInfoResponse clone() throws CloneNotSupportedException {
/* 101 */     ConsumeRebateInfoResponse clone = (ConsumeRebateInfoResponse)super.clone();
/* 102 */     int size_0 = this.takeReward.size();
/* 103 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 105 */       int tmp_0 = ((Integer)this.takeReward.get(index_0)).intValue();
/* 106 */       clone.takeReward.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 108 */     int size_1 = this.postureReward.size();
/* 109 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 111 */       int tmp_1 = ((Integer)this.postureReward.get(index_1)).intValue();
/* 112 */       clone.postureReward.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 114 */     int size_2 = this.zhenFaReward.size();
/* 115 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 117 */       int tmp_2 = ((Integer)this.zhenFaReward.get(index_2)).intValue();
/* 118 */       clone.zhenFaReward.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 120 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 125 */     this.actId = 0;
/* 126 */     this.takeConsume = 0L;
/* 127 */     this.postureConsume = 0L;
/* 128 */     this.zhenFaConsume = 0L;
/* 129 */     this.takeReward.clear();
/* 130 */     this.postureReward.clear();
/* 131 */     this.zhenFaReward.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 136 */     String retVal = "{\"actId\":" + this.actId + ",\"takeConsume\":" + this.takeConsume + ",\"postureConsume\":" + this.postureConsume + ",\"zhenFaConsume\":" + this.zhenFaConsume + ",\"takeReward\":" + this.takeReward.toString() + ",\"postureReward\":" + this.postureReward.toString() + ",\"zhenFaReward\":" + this.zhenFaReward.toString() + "}";
/* 137 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\consume\ConsumeRebateInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */