/*     */ package com.linlongyx.sanguo.webgame.proto.binary.zodiac;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import org.msgpack.annotation.Message;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Message
/*     */ public class ZodiacInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public FestivalTime festivalTim = new FestivalTime();
/*  21 */   public ArrayList<Integer> zodiacRewar = new ArrayList<>();
/*  22 */   public ArrayList<IntIntValue> zodiacSh = new ArrayList<>();
/*  23 */   public ArrayList<KeyValue> palaceLis = new ArrayList<>();
/*     */   
/*     */   public ZodiacInfoResponse() {
/*  26 */     this.eventResponseId = 27505;
/*  27 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  32 */     this.festivalTim.serial(out);
/*     */     
/*  34 */     int size_0 = this.zodiacRewar.size();
/*  35 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  36 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  38 */       int tmp_0 = ((Integer)this.zodiacRewar.get(index_0)).intValue();
/*  39 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  42 */     int size_1 = this.zodiacSh.size();
/*  43 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  44 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  46 */       IntIntValue tmp_1 = this.zodiacSh.get(index_1);
/*  47 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  50 */     int size_2 = this.palaceLis.size();
/*  51 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  52 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  54 */       KeyValue tmp_2 = this.palaceLis.get(index_2);
/*  55 */       tmp_2.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  61 */     this.festivalTim = new FestivalTime();
/*  62 */     this.festivalTim.unserial(in);
/*     */     
/*  64 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  65 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  67 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  68 */       this.zodiacRewar.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  71 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  72 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  74 */       IntIntValue tmp_1 = new IntIntValue();
/*  75 */       tmp_1.unserial(in);
/*  76 */       this.zodiacSh.add(tmp_1);
/*     */     } 
/*     */     
/*  79 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  80 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  82 */       KeyValue tmp_2 = new KeyValue();
/*  83 */       tmp_2.unserial(in);
/*  84 */       this.palaceLis.add(tmp_2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ZodiacInfoResponse clone() throws CloneNotSupportedException {
/*  90 */     ZodiacInfoResponse clone = (ZodiacInfoResponse)super.clone();
/*  91 */     clone.festivalTim = this.festivalTim.clone();
/*  92 */     int size_0 = this.zodiacRewar.size();
/*  93 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  95 */       int tmp_0 = ((Integer)this.zodiacRewar.get(index_0)).intValue();
/*  96 */       clone.zodiacRewar.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  98 */     int size_1 = this.zodiacSh.size();
/*  99 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 101 */       IntIntValue tmp_1 = this.zodiacSh.get(index_1);
/* 102 */       clone.zodiacSh.add(tmp_1.clone());
/*     */     } 
/* 104 */     int size_2 = this.palaceLis.size();
/* 105 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 107 */       KeyValue tmp_2 = this.palaceLis.get(index_2);
/* 108 */       clone.palaceLis.add(tmp_2.clone());
/*     */     } 
/* 110 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 115 */     this.festivalTim.reset();
/* 116 */     this.zodiacRewar.clear();
/* 117 */     this.zodiacSh.clear();
/* 118 */     this.palaceLis.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 123 */     String retVal = "{\"festivalTim\":" + this.festivalTim.toString() + ",\"zodiacRewar\":" + this.zodiacRewar.toString() + ",\"zodiacSh\":" + this.zodiacSh.toString() + ",\"palaceLis\":" + this.palaceLis.toString() + "}";
/* 124 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\zodiac\ZodiacInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */