/*     */ package com.linlongyx.sanguo.webgame.proto.binary.bagua;
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
/*     */ public class BaguaChapterInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  19 */   public ArrayList<Integer> chapterList = new ArrayList<>();
/*     */   public int curChapterId;
/*     */   public int curInsId;
/*     */   public byte sweep;
/*  23 */   public ArrayList<Integer> insId = new ArrayList<>();
/*  24 */   public ArrayList<Integer> canSweepInsId = new ArrayList<>();
/*     */   public byte canSweep;
/*     */   
/*     */   public BaguaChapterInfoResponse() {
/*  28 */     this.eventResponseId = 23601;
/*  29 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  35 */     int size_0 = this.chapterList.size();
/*  36 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  37 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  39 */       int tmp_0 = ((Integer)this.chapterList.get(index_0)).intValue();
/*  40 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  42 */     ProtocolUtil.writeInt(out, this.curChapterId);
/*  43 */     ProtocolUtil.writeInt(out, this.curInsId);
/*  44 */     ProtocolUtil.writeByte(out, this.sweep);
/*     */     
/*  46 */     int size_1 = this.insId.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  48 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  50 */       int tmp_1 = ((Integer)this.insId.get(index_1)).intValue();
/*  51 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  54 */     int size_2 = this.canSweepInsId.size();
/*  55 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  56 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  58 */       int tmp_2 = ((Integer)this.canSweepInsId.get(index_2)).intValue();
/*  59 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*  61 */     ProtocolUtil.writeByte(out, this.canSweep);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  67 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  68 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  70 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  71 */       this.chapterList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  73 */     this.curChapterId = ProtocolUtil.readUTFBinInt(in);
/*  74 */     this.curInsId = ProtocolUtil.readUTFBinInt(in);
/*  75 */     this.sweep = ProtocolUtil.readUTFBinByte(in);
/*     */     
/*  77 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  78 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  80 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  81 */       this.insId.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/*  84 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  85 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  87 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/*  88 */       this.canSweepInsId.add(Integer.valueOf(tmp_2));
/*     */     } 
/*  90 */     this.canSweep = ProtocolUtil.readUTFBinByte(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public BaguaChapterInfoResponse clone() throws CloneNotSupportedException {
/*  95 */     BaguaChapterInfoResponse clone = (BaguaChapterInfoResponse)super.clone();
/*  96 */     int size_0 = this.chapterList.size();
/*  97 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  99 */       int tmp_0 = ((Integer)this.chapterList.get(index_0)).intValue();
/* 100 */       clone.chapterList.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 102 */     int size_1 = this.insId.size();
/* 103 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 105 */       int tmp_1 = ((Integer)this.insId.get(index_1)).intValue();
/* 106 */       clone.insId.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 108 */     int size_2 = this.canSweepInsId.size();
/* 109 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 111 */       int tmp_2 = ((Integer)this.canSweepInsId.get(index_2)).intValue();
/* 112 */       clone.canSweepInsId.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 114 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 119 */     this.chapterList.clear();
/* 120 */     this.curChapterId = 0;
/* 121 */     this.curInsId = 0;
/* 122 */     this.sweep = 0;
/* 123 */     this.insId.clear();
/* 124 */     this.canSweepInsId.clear();
/* 125 */     this.canSweep = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     String retVal = "{\"chapterList\":" + this.chapterList.toString() + ",\"curChapterId\":" + this.curChapterId + ",\"curInsId\":" + this.curInsId + ",\"sweep\":" + this.sweep + ",\"insId\":" + this.insId.toString() + ",\"canSweepInsId\":" + this.canSweepInsId.toString() + ",\"canSweep\":" + this.canSweep + "}";
/* 131 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bagua\BaguaChapterInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */