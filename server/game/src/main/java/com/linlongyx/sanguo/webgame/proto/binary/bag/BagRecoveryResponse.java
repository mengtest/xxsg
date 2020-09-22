/*     */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*     */ public class BagRecoveryResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int type;
/*  20 */   public ArrayList<Long> ids = new ArrayList<>();
/*  21 */   public ArrayList<Integer> itemNum = new ArrayList<>();
/*     */   
/*     */   public BagRecoveryResponse() {
/*  24 */     this.eventResponseId = 20708;
/*  25 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public BagRecoveryResponse(short retCode) {
/*  29 */     this.eventResponseId = 20708;
/*  30 */     this.codeType = 2;
/*  31 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  36 */     ProtocolUtil.writeInt(out, this.type);
/*     */     
/*  38 */     int size_0 = this.ids.size();
/*  39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  42 */       long tmp_0 = ((Long)this.ids.get(index_0)).longValue();
/*  43 */       ProtocolUtil.writeLong(out, tmp_0);
/*     */     } 
/*     */     
/*  46 */     int size_1 = this.itemNum.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  48 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  50 */       int tmp_1 = ((Integer)this.itemNum.get(index_1)).intValue();
/*  51 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  57 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  59 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  62 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/*  63 */       this.ids.add(Long.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  66 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  67 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  69 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  70 */       this.itemNum.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BagRecoveryResponse clone() throws CloneNotSupportedException {
/*  76 */     BagRecoveryResponse clone = (BagRecoveryResponse)super.clone();
/*  77 */     int size_0 = this.ids.size();
/*  78 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  80 */       long tmp_0 = ((Long)this.ids.get(index_0)).longValue();
/*  81 */       clone.ids.add(Long.valueOf(tmp_0));
/*     */     } 
/*  83 */     int size_1 = this.itemNum.size();
/*  84 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  86 */       int tmp_1 = ((Integer)this.itemNum.get(index_1)).intValue();
/*  87 */       clone.itemNum.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  89 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  94 */     this.type = 0;
/*  95 */     this.ids.clear();
/*  96 */     this.itemNum.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     String retVal = "{\"type\":" + this.type + ",\"ids\":" + this.ids.toString() + ",\"itemNum\":" + this.itemNum.toString() + "}";
/* 102 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagRecoveryResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */