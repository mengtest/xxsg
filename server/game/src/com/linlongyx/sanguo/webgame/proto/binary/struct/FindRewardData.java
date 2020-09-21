/*     */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FindRewardData
/*     */ {
/*     */   public int type;
/*     */   public int mold;
/*     */   public int curValue;
/*     */   public int maxValue;
/*  17 */   public ArrayList<Reward> list = new ArrayList<>();
/*  18 */   public ArrayList<Reward> goldCost = new ArrayList<>();
/*  19 */   public ArrayList<Reward> moneyCost = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  23 */     ProtocolUtil.writeInt(out, this.type);
/*  24 */     ProtocolUtil.writeInt(out, this.mold);
/*  25 */     ProtocolUtil.writeInt(out, this.curValue);
/*  26 */     ProtocolUtil.writeInt(out, this.maxValue);
/*     */     
/*  28 */     int size_0 = this.list.size();
/*  29 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  30 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  32 */       Reward tmp_0 = this.list.get(index_0);
/*  33 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  36 */     int size_1 = this.goldCost.size();
/*  37 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  38 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  40 */       Reward tmp_1 = this.goldCost.get(index_1);
/*  41 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  44 */     int size_2 = this.moneyCost.size();
/*  45 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  46 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  48 */       Reward tmp_2 = this.moneyCost.get(index_2);
/*  49 */       tmp_2.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  55 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*  56 */     this.mold = ProtocolUtil.readUTFBinInt(in);
/*  57 */     this.curValue = ProtocolUtil.readUTFBinInt(in);
/*  58 */     this.maxValue = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  60 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  63 */       Reward tmp_0 = new Reward();
/*  64 */       tmp_0.unserial(in);
/*  65 */       this.list.add(tmp_0);
/*     */     } 
/*     */     
/*  68 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  69 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  71 */       Reward tmp_1 = new Reward();
/*  72 */       tmp_1.unserial(in);
/*  73 */       this.goldCost.add(tmp_1);
/*     */     } 
/*     */     
/*  76 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  77 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  79 */       Reward tmp_2 = new Reward();
/*  80 */       tmp_2.unserial(in);
/*  81 */       this.moneyCost.add(tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public FindRewardData clone() throws CloneNotSupportedException {
/*  87 */     FindRewardData clone = (FindRewardData)super.clone();
/*  88 */     int size_0 = this.list.size();
/*  89 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  91 */       Reward tmp_0 = this.list.get(index_0);
/*  92 */       clone.list.add(tmp_0.clone());
/*     */     } 
/*  94 */     int size_1 = this.goldCost.size();
/*  95 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  97 */       Reward tmp_1 = this.goldCost.get(index_1);
/*  98 */       clone.goldCost.add(tmp_1.clone());
/*     */     } 
/* 100 */     int size_2 = this.moneyCost.size();
/* 101 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 103 */       Reward tmp_2 = this.moneyCost.get(index_2);
/* 104 */       clone.moneyCost.add(tmp_2.clone());
/*     */     } 
/* 106 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 111 */     this.type = 0;
/* 112 */     this.mold = 0;
/* 113 */     this.curValue = 0;
/* 114 */     this.maxValue = 0;
/* 115 */     this.list.clear();
/* 116 */     this.goldCost.clear();
/* 117 */     this.moneyCost.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     String retVal = "{\"type\":" + this.type + ",\"mold\":" + this.mold + ",\"curValue\":" + this.curValue + ",\"maxValue\":" + this.maxValue + ",\"list\":" + this.list.toString() + ",\"goldCost\":" + this.goldCost.toString() + ",\"moneyCost\":" + this.moneyCost.toString() + "}";
/* 123 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FindRewardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */