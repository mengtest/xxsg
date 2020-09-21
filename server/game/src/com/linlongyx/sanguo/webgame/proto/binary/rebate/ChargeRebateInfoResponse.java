/*     */ package com.linlongyx.sanguo.webgame.proto.binary.rebate;
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
/*     */ public class ChargeRebateInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int actId;
/*     */   public int times;
/*     */   public int sorce;
/*  23 */   public ArrayList<Integer> list = new ArrayList<>();
/*  24 */   public ArrayList<KeyValue> serviceRecords = new ArrayList<>();
/*     */   
/*     */   public ChargeRebateInfoResponse() {
/*  27 */     this.eventResponseId = 24009;
/*  28 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public ChargeRebateInfoResponse(short retCode) {
/*  32 */     this.eventResponseId = 24009;
/*  33 */     this.codeType = 2;
/*  34 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  39 */     ProtocolUtil.writeInt(out, this.actId);
/*  40 */     ProtocolUtil.writeInt(out, this.times);
/*  41 */     ProtocolUtil.writeInt(out, this.sorce);
/*     */     
/*  43 */     int size_0 = this.list.size();
/*  44 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  47 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  48 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  51 */     int size_1 = this.serviceRecords.size();
/*  52 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  53 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  55 */       KeyValue tmp_1 = this.serviceRecords.get(index_1);
/*  56 */       tmp_1.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  62 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/*  63 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*  64 */     this.sorce = ProtocolUtil.readUTFBinInt(in);
/*     */     
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
/*  76 */       KeyValue tmp_1 = new KeyValue();
/*  77 */       tmp_1.unserial(in);
/*  78 */       this.serviceRecords.add(tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ChargeRebateInfoResponse clone() throws CloneNotSupportedException {
/*  84 */     ChargeRebateInfoResponse clone = (ChargeRebateInfoResponse)super.clone();
/*  85 */     int size_0 = this.list.size();
/*  86 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  88 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  89 */       clone.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  91 */     int size_1 = this.serviceRecords.size();
/*  92 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  94 */       KeyValue tmp_1 = this.serviceRecords.get(index_1);
/*  95 */       clone.serviceRecords.add(tmp_1.clone());
/*     */     } 
/*  97 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 102 */     this.actId = 0;
/* 103 */     this.times = 0;
/* 104 */     this.sorce = 0;
/* 105 */     this.list.clear();
/* 106 */     this.serviceRecords.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 111 */     String retVal = "{\"actId\":" + this.actId + ",\"times\":" + this.times + ",\"sorce\":" + this.sorce + ",\"list\":" + this.list.toString() + ",\"serviceRecords\":" + this.serviceRecords.toString() + "}";
/* 112 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rebate\ChargeRebateInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */