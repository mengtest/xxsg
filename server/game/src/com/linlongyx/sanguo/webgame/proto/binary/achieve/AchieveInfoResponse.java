/*     */ package com.linlongyx.sanguo.webgame.proto.binary.achieve;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.AchieveData;
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
/*     */ public class AchieveInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int point;
/*  21 */   public ArrayList<AchieveData> datas = new ArrayList<>();
/*  22 */   public ArrayList<Integer> process = new ArrayList<>();
/*     */   
/*     */   public AchieveInfoResponse() {
/*  25 */     this.eventResponseId = 20401;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public AchieveInfoResponse(short retCode) {
/*  30 */     this.eventResponseId = 20401;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     ProtocolUtil.writeInt(out, this.point);
/*     */     
/*  39 */     int size_0 = this.datas.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       AchieveData tmp_0 = this.datas.get(index_0);
/*  44 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.process.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       int tmp_1 = ((Integer)this.process.get(index_1)).intValue();
/*  52 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  60 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  63 */       AchieveData tmp_0 = new AchieveData();
/*  64 */       tmp_0.unserial(in);
/*  65 */       this.datas.add(tmp_0);
/*     */     } 
/*     */     
/*  68 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  69 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  71 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  72 */       this.process.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AchieveInfoResponse clone() throws CloneNotSupportedException {
/*  78 */     AchieveInfoResponse clone = (AchieveInfoResponse)super.clone();
/*  79 */     int size_0 = this.datas.size();
/*  80 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  82 */       AchieveData tmp_0 = this.datas.get(index_0);
/*  83 */       clone.datas.add(tmp_0.clone());
/*     */     } 
/*  85 */     int size_1 = this.process.size();
/*  86 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  88 */       int tmp_1 = ((Integer)this.process.get(index_1)).intValue();
/*  89 */       clone.process.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  91 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  96 */     this.point = 0;
/*  97 */     this.datas.clear();
/*  98 */     this.process.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     String retVal = "{\"point\":" + this.point + ",\"datas\":" + this.datas.toString() + ",\"process\":" + this.process.toString() + "}";
/* 104 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\achieve\AchieveInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */