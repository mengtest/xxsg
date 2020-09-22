/*     */ package com.linlongyx.sanguo.webgame.proto.binary.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
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
/*     */ public class TaskGetAskRewardResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int id;
/*  21 */   public ArrayList<Integer> list = new ArrayList<>();
/*  22 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*     */   
/*     */   public TaskGetAskRewardResponse() {
/*  25 */     this.eventResponseId = 22206;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public TaskGetAskRewardResponse(short retCode) {
/*  30 */     this.eventResponseId = 22206;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     ProtocolUtil.writeInt(out, this.id);
/*     */     
/*  39 */     int size_0 = this.list.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  44 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.rewards.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       Reward tmp_1 = this.rewards.get(index_1);
/*  52 */       tmp_1.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     this.id = ProtocolUtil.readUTFBinInt(in);
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
/*  70 */       Reward tmp_1 = new Reward();
/*  71 */       tmp_1.unserial(in);
/*  72 */       this.rewards.add(tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TaskGetAskRewardResponse clone() throws CloneNotSupportedException {
/*  78 */     TaskGetAskRewardResponse clone = (TaskGetAskRewardResponse)super.clone();
/*  79 */     int size_0 = this.list.size();
/*  80 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  82 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/*  83 */       clone.list.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  85 */     int size_1 = this.rewards.size();
/*  86 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  88 */       Reward tmp_1 = this.rewards.get(index_1);
/*  89 */       clone.rewards.add(tmp_1.clone());
/*     */     } 
/*  91 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  96 */     this.id = 0;
/*  97 */     this.list.clear();
/*  98 */     this.rewards.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     String retVal = "{\"id\":" + this.id + ",\"list\":" + this.list.toString() + ",\"rewards\":" + this.rewards.toString() + "}";
/* 104 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskGetAskRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */