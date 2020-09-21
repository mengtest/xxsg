/*     */ package com.linlongyx.sanguo.webgame.proto.binary.rankAct;
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
/*     */ public class RankActNoticeResponse
/*     */   extends ResponseBase
/*     */ {
/*  19 */   public ArrayList<Integer> list = new ArrayList<>();
/*  20 */   public ArrayList<Integer> allList = new ArrayList<>();
/*     */   public int closeTime;
/*     */   
/*     */   public RankActNoticeResponse() {
/*  24 */     this.eventResponseId = 22001;
/*  25 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public RankActNoticeResponse(short retCode) {
/*  29 */     this.eventResponseId = 22001;
/*  30 */     this.codeType = 2;
/*  31 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     int size_0 = this.list.size();
/*  38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  41 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  42 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  45 */     int size_1 = this.allList.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  47 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  49 */       int tmp_1 = ((Integer)this.allList.get(index_1)).intValue();
/*  50 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*  52 */     ProtocolUtil.writeInt(out, this.closeTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  61 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  62 */       this.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  65 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  66 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  68 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  69 */       this.allList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  71 */     this.closeTime = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public RankActNoticeResponse clone() throws CloneNotSupportedException {
/*  76 */     RankActNoticeResponse clone = (RankActNoticeResponse)super.clone();
/*  77 */     int size_0 = this.list.size();
/*  78 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  80 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  81 */       clone.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  83 */     int size_1 = this.allList.size();
/*  84 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  86 */       int tmp_1 = ((Integer)this.allList.get(index_1)).intValue();
/*  87 */       clone.allList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  89 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  94 */     this.list.clear();
/*  95 */     this.allList.clear();
/*  96 */     this.closeTime = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     String retVal = "{\"list\":" + this.list.toString() + ",\"allList\":" + this.allList.toString() + ",\"closeTime\":" + this.closeTime + "}";
/* 102 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rankAct\RankActNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */