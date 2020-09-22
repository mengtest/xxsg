/*     */ package com.linlongyx.sanguo.webgame.proto.binary.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GeneralPointData;
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
/*     */ public class GeneralChapterInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int chapter;
/*     */   public int level;
/*     */   public int num;
/*     */   public int time;
/*  24 */   public ArrayList<GeneralPointData> list = new ArrayList<>();
/*  25 */   public ArrayList<Integer> box = new ArrayList<>();
/*     */   
/*     */   public GeneralChapterInfoResponse() {
/*  28 */     this.eventResponseId = 21202;
/*  29 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public GeneralChapterInfoResponse(short retCode) {
/*  33 */     this.eventResponseId = 21202;
/*  34 */     this.codeType = 2;
/*  35 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  40 */     ProtocolUtil.writeInt(out, this.chapter);
/*  41 */     ProtocolUtil.writeInt(out, this.level);
/*  42 */     ProtocolUtil.writeInt(out, this.num);
/*  43 */     ProtocolUtil.writeInt(out, this.time);
/*     */     
/*  45 */     int size_0 = this.list.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  49 */       GeneralPointData tmp_0 = this.list.get(index_0);
/*  50 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  53 */     int size_1 = this.box.size();
/*  54 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  55 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  57 */       int tmp_1 = ((Integer)this.box.get(index_1)).intValue();
/*  58 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  64 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/*  65 */     this.level = ProtocolUtil.readUTFBinInt(in);
/*  66 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*  67 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  69 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  72 */       GeneralPointData tmp_0 = new GeneralPointData();
/*  73 */       tmp_0.unserial(in);
/*  74 */       this.list.add(tmp_0);
/*     */     } 
/*     */     
/*  77 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  78 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  80 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  81 */       this.box.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GeneralChapterInfoResponse clone() throws CloneNotSupportedException {
/*  87 */     GeneralChapterInfoResponse clone = (GeneralChapterInfoResponse)super.clone();
/*  88 */     int size_0 = this.list.size();
/*  89 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  91 */       GeneralPointData tmp_0 = this.list.get(index_0);
/*  92 */       clone.list.add(tmp_0);
/*     */     } 
/*  94 */     int size_1 = this.box.size();
/*  95 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  97 */       int tmp_1 = ((Integer)this.box.get(index_1)).intValue();
/*  98 */       clone.box.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 100 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 105 */     this.chapter = 0;
/* 106 */     this.level = 0;
/* 107 */     this.num = 0;
/* 108 */     this.time = 0;
/* 109 */     this.list.clear();
/* 110 */     this.box.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     String retVal = "{\"chapter\":" + this.chapter + ",\"level\":" + this.level + ",\"num\":" + this.num + ",\"time\":" + this.time + ",\"list\":" + this.list + ",\"box\":" + this.box.toString() + "}";
/* 116 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralChapterInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */