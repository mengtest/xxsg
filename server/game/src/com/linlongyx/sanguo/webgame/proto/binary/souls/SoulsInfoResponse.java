/*     */ package com.linlongyx.sanguo.webgame.proto.binary.souls;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SoulsData;
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
/*     */ public class SoulsInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<Integer> combinationList = new ArrayList<>();
/*  21 */   public ArrayList<SoulsData> soulList = new ArrayList<>();
/*  22 */   public ArrayList<Integer> soulsFighter = new ArrayList<>();
/*     */   
/*     */   public SoulsInfoResponse() {
/*  25 */     this.eventResponseId = 21208;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public SoulsInfoResponse(short retCode) {
/*  30 */     this.eventResponseId = 21208;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  38 */     int size_0 = this.combinationList.size();
/*  39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  42 */       int tmp_0 = ((Integer)this.combinationList.get(index_0)).intValue();
/*  43 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  46 */     int size_1 = this.soulList.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  48 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  50 */       SoulsData tmp_1 = this.soulList.get(index_1);
/*  51 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  54 */     int size_2 = this.soulsFighter.size();
/*  55 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  56 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  58 */       int tmp_2 = ((Integer)this.soulsFighter.get(index_2)).intValue();
/*  59 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  66 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  69 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  70 */       this.combinationList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  73 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  74 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  76 */       SoulsData tmp_1 = new SoulsData();
/*  77 */       tmp_1.unserial(in);
/*  78 */       this.soulList.add(tmp_1);
/*     */     } 
/*     */     
/*  81 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  82 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  84 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/*  85 */       this.soulsFighter.add(Integer.valueOf(tmp_2));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SoulsInfoResponse clone() throws CloneNotSupportedException {
/*  91 */     SoulsInfoResponse clone = (SoulsInfoResponse)super.clone();
/*  92 */     int size_0 = this.combinationList.size();
/*  93 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  95 */       int tmp_0 = ((Integer)this.combinationList.get(index_0)).intValue();
/*  96 */       clone.combinationList.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  98 */     int size_1 = this.soulList.size();
/*  99 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 101 */       SoulsData tmp_1 = this.soulList.get(index_1);
/* 102 */       clone.soulList.add(tmp_1.clone());
/*     */     } 
/* 104 */     int size_2 = this.soulsFighter.size();
/* 105 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 107 */       int tmp_2 = ((Integer)this.soulsFighter.get(index_2)).intValue();
/* 108 */       clone.soulsFighter.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 110 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 115 */     this.combinationList.clear();
/* 116 */     this.soulList.clear();
/* 117 */     this.soulsFighter.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     String retVal = "{\"combinationList\":" + this.combinationList.toString() + ",\"soulList\":" + this.soulList.toString() + ",\"soulsFighter\":" + this.soulsFighter.toString() + "}";
/* 123 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\souls\SoulsInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */