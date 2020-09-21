/*     */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
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
/*     */ public class StartRecruitResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int type;
/*  21 */   public ArrayList<Reward> rewards = new ArrayList<>();
/*     */   public int freeCount;
/*     */   public int nextFreeTime;
/*     */   public int times;
/*     */   public int score;
/*     */   public int redFree;
/*     */   public int tenCCYRecruit;
/*     */   
/*     */   public StartRecruitResponse() {
/*  30 */     this.eventResponseId = 24001;
/*  31 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public StartRecruitResponse(short retCode) {
/*  35 */     this.eventResponseId = 24001;
/*  36 */     this.codeType = 2;
/*  37 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  42 */     ProtocolUtil.writeInt(out, this.type);
/*     */     
/*  44 */     int size_0 = this.rewards.size();
/*  45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  48 */       Reward tmp_0 = this.rewards.get(index_0);
/*  49 */       tmp_0.serial(out);
/*     */     } 
/*  51 */     ProtocolUtil.writeInt(out, this.freeCount);
/*  52 */     ProtocolUtil.writeInt(out, this.nextFreeTime);
/*  53 */     ProtocolUtil.writeInt(out, this.times);
/*  54 */     ProtocolUtil.writeInt(out, this.score);
/*  55 */     ProtocolUtil.writeInt(out, this.redFree);
/*  56 */     ProtocolUtil.writeInt(out, this.tenCCYRecruit);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  61 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  63 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  66 */       Reward tmp_0 = new Reward();
/*  67 */       tmp_0.unserial(in);
/*  68 */       this.rewards.add(tmp_0);
/*     */     } 
/*  70 */     this.freeCount = ProtocolUtil.readUTFBinInt(in);
/*  71 */     this.nextFreeTime = ProtocolUtil.readUTFBinInt(in);
/*  72 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*  73 */     this.score = ProtocolUtil.readUTFBinInt(in);
/*  74 */     this.redFree = ProtocolUtil.readUTFBinInt(in);
/*  75 */     this.tenCCYRecruit = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public StartRecruitResponse clone() throws CloneNotSupportedException {
/*  80 */     StartRecruitResponse clone = (StartRecruitResponse)super.clone();
/*  81 */     int size_0 = this.rewards.size();
/*  82 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  84 */       Reward tmp_0 = this.rewards.get(index_0);
/*  85 */       clone.rewards.add(tmp_0.clone());
/*     */     } 
/*  87 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  92 */     this.type = 0;
/*  93 */     this.rewards.clear();
/*  94 */     this.freeCount = 0;
/*  95 */     this.nextFreeTime = 0;
/*  96 */     this.times = 0;
/*  97 */     this.score = 0;
/*  98 */     this.redFree = 0;
/*  99 */     this.tenCCYRecruit = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     String retVal = "{\"type\":" + this.type + ",\"rewards\":" + this.rewards.toString() + ",\"freeCount\":" + this.freeCount + ",\"nextFreeTime\":" + this.nextFreeTime + ",\"times\":" + this.times + ",\"score\":" + this.score + ",\"redFree\":" + this.redFree + ",\"tenCCYRecruit\":" + this.tenCCYRecruit + "}";
/* 105 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\StartRecruitResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */