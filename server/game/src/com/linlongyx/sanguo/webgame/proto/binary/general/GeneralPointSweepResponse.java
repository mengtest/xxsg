/*     */ package com.linlongyx.sanguo.webgame.proto.binary.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlotExpSweepData;
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
/*     */ public class GeneralPointSweepResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int chapter;
/*     */   public int point;
/*     */   public int num;
/*     */   public int time;
/*     */   public int orderNum;
/*     */   public int orderTime;
/*  26 */   public ArrayList<PlotExpSweepData> rewards = new ArrayList<>();
/*     */   
/*     */   public GeneralPointSweepResponse() {
/*  29 */     this.eventResponseId = 21203;
/*  30 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public GeneralPointSweepResponse(short retCode) {
/*  34 */     this.eventResponseId = 21203;
/*  35 */     this.codeType = 2;
/*  36 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  41 */     ProtocolUtil.writeInt(out, this.chapter);
/*  42 */     ProtocolUtil.writeInt(out, this.point);
/*  43 */     ProtocolUtil.writeInt(out, this.num);
/*  44 */     ProtocolUtil.writeInt(out, this.time);
/*  45 */     ProtocolUtil.writeInt(out, this.orderNum);
/*  46 */     ProtocolUtil.writeInt(out, this.orderTime);
/*     */     
/*  48 */     int size_0 = this.rewards.size();
/*  49 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  50 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  52 */       PlotExpSweepData tmp_0 = this.rewards.get(index_0);
/*  53 */       tmp_0.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  59 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/*  60 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*  61 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*  62 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*  63 */     this.orderNum = ProtocolUtil.readUTFBinInt(in);
/*  64 */     this.orderTime = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  66 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  69 */       PlotExpSweepData tmp_0 = new PlotExpSweepData();
/*  70 */       tmp_0.unserial(in);
/*  71 */       this.rewards.add(tmp_0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GeneralPointSweepResponse clone() throws CloneNotSupportedException {
/*  77 */     GeneralPointSweepResponse clone = (GeneralPointSweepResponse)super.clone();
/*  78 */     int size_0 = this.rewards.size();
/*  79 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  81 */       PlotExpSweepData tmp_0 = this.rewards.get(index_0);
/*  82 */       clone.rewards.add(tmp_0.clone());
/*     */     } 
/*  84 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  89 */     this.chapter = 0;
/*  90 */     this.point = 0;
/*  91 */     this.num = 0;
/*  92 */     this.time = 0;
/*  93 */     this.orderNum = 0;
/*  94 */     this.orderTime = 0;
/*  95 */     this.rewards.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     String retVal = "{\"chapter\":" + this.chapter + ",\"point\":" + this.point + ",\"num\":" + this.num + ",\"time\":" + this.time + ",\"orderNum\":" + this.orderNum + ",\"orderTime\":" + this.orderTime + ",\"rewards\":" + this.rewards.toString() + "}";
/* 101 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralPointSweepResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */