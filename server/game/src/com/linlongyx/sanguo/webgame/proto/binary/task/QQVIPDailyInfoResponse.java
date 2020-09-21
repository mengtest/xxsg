/*     */ package com.linlongyx.sanguo.webgame.proto.binary.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GoodsData;
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
/*     */ public class QQVIPDailyInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int vip;
/*  21 */   public ArrayList<Integer> list = new ArrayList<>();
/*  22 */   public ArrayList<GoodsData> goods = new ArrayList<>();
/*     */   
/*     */   public QQVIPDailyInfoResponse() {
/*  25 */     this.eventResponseId = 22213;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public QQVIPDailyInfoResponse(short retCode) {
/*  30 */     this.eventResponseId = 22213;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     ProtocolUtil.writeInt(out, this.vip);
/*     */     
/*  39 */     int size_0 = this.list.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  44 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.goods.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       GoodsData tmp_1 = this.goods.get(index_1);
/*  52 */       tmp_1.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     this.vip = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  60 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  63 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  64 */       this.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  67 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  68 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  70 */       GoodsData tmp_1 = new GoodsData();
/*  71 */       tmp_1.unserial(in);
/*  72 */       this.goods.add(tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public QQVIPDailyInfoResponse clone() throws CloneNotSupportedException {
/*  78 */     QQVIPDailyInfoResponse clone = (QQVIPDailyInfoResponse)super.clone();
/*  79 */     int size_0 = this.list.size();
/*  80 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  82 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  83 */       clone.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  85 */     int size_1 = this.goods.size();
/*  86 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  88 */       GoodsData tmp_1 = this.goods.get(index_1);
/*  89 */       clone.goods.add(tmp_1.clone());
/*     */     } 
/*  91 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  96 */     this.vip = 0;
/*  97 */     this.list.clear();
/*  98 */     this.goods.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     String retVal = "{\"vip\":" + this.vip + ",\"list\":" + this.list.toString() + ",\"goods\":" + this.goods.toString() + "}";
/* 104 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\QQVIPDailyInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */