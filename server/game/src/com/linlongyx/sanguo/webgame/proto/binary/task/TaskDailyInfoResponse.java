/*     */ package com.linlongyx.sanguo.webgame.proto.binary.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntLongValue;
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
/*     */ public class TaskDailyInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int point;
/*  21 */   public ArrayList<Integer> reward1 = new ArrayList<>();
/*  22 */   public ArrayList<Integer> reward2 = new ArrayList<>();
/*  23 */   public ArrayList<IntLongValue> process = new ArrayList<>();
/*     */   
/*     */   public TaskDailyInfoResponse() {
/*  26 */     this.eventResponseId = 22210;
/*  27 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public TaskDailyInfoResponse(short retCode) {
/*  31 */     this.eventResponseId = 22210;
/*  32 */     this.codeType = 2;
/*  33 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  38 */     ProtocolUtil.writeInt(out, this.point);
/*     */     
/*  40 */     int size_0 = this.reward1.size();
/*  41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  44 */       int tmp_0 = ((Integer)this.reward1.get(index_0)).intValue();
/*  45 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  48 */     int size_1 = this.reward2.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  50 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  52 */       int tmp_1 = ((Integer)this.reward2.get(index_1)).intValue();
/*  53 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */     
/*  56 */     int size_2 = this.process.size();
/*  57 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  58 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  60 */       IntLongValue tmp_2 = this.process.get(index_2);
/*  61 */       tmp_2.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  67 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  69 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  72 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  73 */       this.reward1.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  76 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  77 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  79 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  80 */       this.reward2.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */     
/*  83 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  84 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  86 */       IntLongValue tmp_2 = new IntLongValue();
/*  87 */       tmp_2.unserial(in);
/*  88 */       this.process.add(tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TaskDailyInfoResponse clone() throws CloneNotSupportedException {
/*  94 */     TaskDailyInfoResponse clone = (TaskDailyInfoResponse)super.clone();
/*  95 */     int size_0 = this.reward1.size();
/*  96 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  98 */       int tmp_0 = ((Integer)this.reward1.get(index_0)).intValue();
/*  99 */       clone.reward1.add(Integer.valueOf(tmp_0));
/*     */     } 
/* 101 */     int size_1 = this.reward2.size();
/* 102 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 104 */       int tmp_1 = ((Integer)this.reward2.get(index_1)).intValue();
/* 105 */       clone.reward2.add(Integer.valueOf(tmp_1));
/*     */     } 
/* 107 */     int size_2 = this.process.size();
/* 108 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 110 */       IntLongValue tmp_2 = this.process.get(index_2);
/* 111 */       clone.process.add(tmp_2.clone());
/*     */     } 
/* 113 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 118 */     this.point = 0;
/* 119 */     this.reward1.clear();
/* 120 */     this.reward2.clear();
/* 121 */     this.process.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     String retVal = "{\"point\":" + this.point + ",\"reward1\":" + this.reward1.toString() + ",\"reward2\":" + this.reward2.toString() + ",\"process\":" + this.process.toString() + "}";
/* 127 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskDailyInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */