/*     */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*     */ public class WelfareVipInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<Integer> list = new ArrayList<>();
/*  21 */   public ArrayList<IntIntValue> goods = new ArrayList<>();
/*  22 */   public ArrayList<IntIntValue> vipGoods = new ArrayList<>();
/*     */   
/*     */   public WelfareVipInfoResponse() {
/*  25 */     this.eventResponseId = 21901;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public WelfareVipInfoResponse(short retCode) {
/*  30 */     this.eventResponseId = 21901;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  38 */     int size_0 = this.list.size();
/*  39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  42 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  43 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  46 */     int size_1 = this.goods.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  48 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  50 */       IntIntValue tmp_1 = this.goods.get(index_1);
/*  51 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  54 */     int size_2 = this.vipGoods.size();
/*  55 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  56 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  58 */       IntIntValue tmp_2 = this.vipGoods.get(index_2);
/*  59 */       tmp_2.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  66 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  69 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  70 */       this.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  73 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  74 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  76 */       IntIntValue tmp_1 = new IntIntValue();
/*  77 */       tmp_1.unserial(in);
/*  78 */       this.goods.add(tmp_1);
/*     */     } 
/*     */     
/*  81 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  82 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  84 */       IntIntValue tmp_2 = new IntIntValue();
/*  85 */       tmp_2.unserial(in);
/*  86 */       this.vipGoods.add(tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WelfareVipInfoResponse clone() throws CloneNotSupportedException {
/*  92 */     WelfareVipInfoResponse clone = (WelfareVipInfoResponse)super.clone();
/*  93 */     int size_0 = this.list.size();
/*  94 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  96 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  97 */       clone.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  99 */     int size_1 = this.goods.size();
/* 100 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 102 */       IntIntValue tmp_1 = this.goods.get(index_1);
/* 103 */       clone.goods.add(tmp_1.clone());
/*     */     } 
/* 105 */     int size_2 = this.vipGoods.size();
/* 106 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 108 */       IntIntValue tmp_2 = this.vipGoods.get(index_2);
/* 109 */       clone.vipGoods.add(tmp_2.clone());
/*     */     } 
/* 111 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 116 */     this.list.clear();
/* 117 */     this.goods.clear();
/* 118 */     this.vipGoods.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 123 */     String retVal = "{\"list\":" + this.list.toString() + ",\"goods\":" + this.goods.toString() + ",\"vipGoods\":" + this.vipGoods.toString() + "}";
/* 124 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\WelfareVipInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */