/*     */ package com.linlongyx.sanguo.webgame.proto.binary.task;
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
/*     */ public class TaskInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int id;
/*     */   public long schedule;
/*  22 */   public ArrayList<Integer> chapterReward = new ArrayList<>();
/*     */   public int chapter;
/*     */   public int guideId;
/*  25 */   public ArrayList<Integer> askList = new ArrayList<>();
/*  26 */   public ArrayList<KeyValue> previewValue = new ArrayList<>();
/*  27 */   public ArrayList<Integer> previewReward = new ArrayList<>();
/*     */   
/*     */   public TaskInfoResponse() {
/*  30 */     this.eventResponseId = 22201;
/*  31 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public TaskInfoResponse(short retCode) {
/*  35 */     this.eventResponseId = 22201;
/*  36 */     this.codeType = 2;
/*  37 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  42 */     ProtocolUtil.writeInt(out, this.id);
/*  43 */     ProtocolUtil.writeLong(out, this.schedule);
/*     */     
/*  45 */     int size_0 = this.chapterReward.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  49 */       int tmp_0 = ((Integer)this.chapterReward.get(index_0)).intValue();
/*  50 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  52 */     ProtocolUtil.writeInt(out, this.chapter);
/*  53 */     ProtocolUtil.writeInt(out, this.guideId);
/*     */     
/*  55 */     int size_1 = this.askList.size();
/*  56 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  57 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  59 */       int tmp_1 = ((Integer)this.askList.get(index_1)).intValue();
/*  60 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  63 */     int size_2 = this.previewValue.size();
/*  64 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  65 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  67 */       KeyValue tmp_2 = this.previewValue.get(index_2);
/*  68 */       tmp_2.serial(out);
/*     */     } 
/*     */     
/*  71 */     int size_3 = this.previewReward.size();
/*  72 */     ProtocolUtil.writeStrArraySize(out, size_3);
/*  73 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/*  75 */       int tmp_3 = ((Integer)this.previewReward.get(index_3)).intValue();
/*  76 */       ProtocolUtil.writeInt(out, tmp_3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  82 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*  83 */     this.schedule = ProtocolUtil.readUTFBinLong(in);
/*     */     
/*  85 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  86 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  88 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  89 */       this.chapterReward.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  91 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/*  92 */     this.guideId = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  94 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  95 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  97 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  98 */       this.askList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/* 101 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/* 102 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 104 */       KeyValue tmp_2 = new KeyValue();
/* 105 */       tmp_2.unserial(in);
/* 106 */       this.previewValue.add(tmp_2);
/*     */     } 
/*     */     
/* 109 */     int size_3 = ProtocolUtil.readStrArraySize(in);
/* 110 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 112 */       int tmp_3 = ProtocolUtil.readUTFBinInt(in);
/* 113 */       this.previewReward.add(Integer.valueOf(tmp_3));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TaskInfoResponse clone() throws CloneNotSupportedException {
/* 119 */     TaskInfoResponse clone = (TaskInfoResponse)super.clone();
/* 120 */     int size_0 = this.chapterReward.size();
/* 121 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 123 */       int tmp_0 = ((Integer)this.chapterReward.get(index_0)).intValue();
/* 124 */       clone.chapterReward.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 126 */     int size_1 = this.askList.size();
/* 127 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 129 */       int tmp_1 = ((Integer)this.askList.get(index_1)).intValue();
/* 130 */       clone.askList.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 132 */     int size_2 = this.previewValue.size();
/* 133 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 135 */       KeyValue tmp_2 = this.previewValue.get(index_2);
/* 136 */       clone.previewValue.add(tmp_2.clone());
/*     */     } 
/* 138 */     int size_3 = this.previewReward.size();
/* 139 */     for (int index_3 = 0; index_3 < size_3; index_3++) {
/*     */       
/* 141 */       int tmp_3 = ((Integer)this.previewReward.get(index_3)).intValue();
/* 142 */       clone.previewReward.add(Integer.valueOf(tmp_3));
/*     */     } 
/* 144 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 149 */     this.id = 0;
/* 150 */     this.schedule = 0L;
/* 151 */     this.chapterReward.clear();
/* 152 */     this.chapter = 0;
/* 153 */     this.guideId = 0;
/* 154 */     this.askList.clear();
/* 155 */     this.previewValue.clear();
/* 156 */     this.previewReward.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     String retVal = "{\"id\":" + this.id + ",\"schedule\":" + this.schedule + ",\"chapterReward\":" + this.chapterReward.toString() + ",\"chapter\":" + this.chapter + ",\"guideId\":" + this.guideId + ",\"askList\":" + this.askList.toString() + ",\"previewValue\":" + this.previewValue.toString() + ",\"previewReward\":" + this.previewReward.toString() + "}";
/* 162 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */