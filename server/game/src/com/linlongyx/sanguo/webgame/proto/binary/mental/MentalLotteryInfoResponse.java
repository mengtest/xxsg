/*     */ package com.linlongyx.sanguo.webgame.proto.binary.mental;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
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
/*     */ public class MentalLotteryInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int point;
/*  21 */   public ArrayList<Integer> rewardList = new ArrayList<>();
/*     */   public int freeTime;
/*  23 */   public ArrayList<MentalShowStruct> items = new ArrayList<>();
/*     */   public int showLevel;
/*     */   
/*     */   public MentalLotteryInfoResponse() {
/*  27 */     this.eventResponseId = 27301;
/*  28 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public MentalLotteryInfoResponse(short retCode) {
/*  32 */     this.eventResponseId = 27301;
/*  33 */     this.codeType = 2;
/*  34 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  39 */     ProtocolUtil.writeInt(out, this.point);
/*     */     
/*  41 */     int size_0 = this.rewardList.size();
/*  42 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  43 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  45 */       int tmp_0 = ((Integer)this.rewardList.get(index_0)).intValue();
/*  46 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*  48 */     ProtocolUtil.writeInt(out, this.freeTime);
/*     */     
/*  50 */     int size_1 = this.items.size();
/*  51 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  52 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  54 */       MentalShowStruct tmp_1 = this.items.get(index_1);
/*  55 */       tmp_1.serial(out);
/*     */     } 
/*  57 */     ProtocolUtil.writeInt(out, this.showLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  62 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  64 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  67 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  68 */       this.rewardList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  70 */     this.freeTime = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  72 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  73 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  75 */       MentalShowStruct tmp_1 = new MentalShowStruct();
/*  76 */       tmp_1.unserial(in);
/*  77 */       this.items.add(tmp_1);
/*     */     } 
/*  79 */     this.showLevel = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public MentalLotteryInfoResponse clone() throws CloneNotSupportedException {
/*  84 */     MentalLotteryInfoResponse clone = (MentalLotteryInfoResponse)super.clone();
/*  85 */     int size_0 = this.rewardList.size();
/*  86 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  88 */       int tmp_0 = ((Integer)this.rewardList.get(index_0)).intValue();
/*  89 */       clone.rewardList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  91 */     int size_1 = this.items.size();
/*  92 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  94 */       MentalShowStruct tmp_1 = this.items.get(index_1);
/*  95 */       clone.items.add(tmp_1.clone());
/*     */     } 
/*  97 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 102 */     this.point = 0;
/* 103 */     this.rewardList.clear();
/* 104 */     this.freeTime = 0;
/* 105 */     this.items.clear();
/* 106 */     this.showLevel = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 111 */     String retVal = "{\"point\":" + this.point + ",\"rewardList\":" + this.rewardList.toString() + ",\"freeTime\":" + this.freeTime + ",\"items\":" + this.items.toString() + ",\"showLevel\":" + this.showLevel + "}";
/* 112 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mental\MentalLotteryInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */