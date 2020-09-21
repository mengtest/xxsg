/*     */ package com.linlongyx.sanguo.webgame.proto.binary.cardbook;
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
/*     */ public class CardBookInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<KeyValue> coloringInfo = new ArrayList<>();
/*     */   public int bottomCard;
/*     */   public int colorDan;
/*     */   public int lastToColor;
/*     */   public int refToColor;
/*  25 */   public ArrayList<KeyValue> cardPair = new ArrayList<>();
/*  26 */   public ArrayList<Integer> process = new ArrayList<>();
/*     */   public int giveTimes;
/*     */   public int nextAskTime;
/*     */   
/*     */   public CardBookInfoResponse() {
/*  31 */     this.eventResponseId = 22601;
/*  32 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public CardBookInfoResponse(short retCode) {
/*  36 */     this.eventResponseId = 22601;
/*  37 */     this.codeType = 2;
/*  38 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  44 */     int size_0 = this.coloringInfo.size();
/*  45 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  48 */       KeyValue tmp_0 = this.coloringInfo.get(index_0);
/*  49 */       tmp_0.serial(out);
/*     */     } 
/*  51 */     ProtocolUtil.writeInt(out, this.bottomCard);
/*  52 */     ProtocolUtil.writeInt(out, this.colorDan);
/*  53 */     ProtocolUtil.writeInt(out, this.lastToColor);
/*  54 */     ProtocolUtil.writeInt(out, this.refToColor);
/*     */     
/*  56 */     int size_1 = this.cardPair.size();
/*  57 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  58 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  60 */       KeyValue tmp_1 = this.cardPair.get(index_1);
/*  61 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  64 */     int size_2 = this.process.size();
/*  65 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  66 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  68 */       int tmp_2 = ((Integer)this.process.get(index_2)).intValue();
/*  69 */       ProtocolUtil.writeInt(out, tmp_2);
/*     */     } 
/*  71 */     ProtocolUtil.writeInt(out, this.giveTimes);
/*  72 */     ProtocolUtil.writeInt(out, this.nextAskTime);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  78 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  79 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  81 */       KeyValue tmp_0 = new KeyValue();
/*  82 */       tmp_0.unserial(in);
/*  83 */       this.coloringInfo.add(tmp_0);
/*     */     } 
/*  85 */     this.bottomCard = ProtocolUtil.readUTFBinInt(in);
/*  86 */     this.colorDan = ProtocolUtil.readUTFBinInt(in);
/*  87 */     this.lastToColor = ProtocolUtil.readUTFBinInt(in);
/*  88 */     this.refToColor = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  90 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  91 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  93 */       KeyValue tmp_1 = new KeyValue();
/*  94 */       tmp_1.unserial(in);
/*  95 */       this.cardPair.add(tmp_1);
/*     */     } 
/*     */     
/*  98 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  99 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 101 */       int tmp_2 = ProtocolUtil.readUTFBinInt(in);
/* 102 */       this.process.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 104 */     this.giveTimes = ProtocolUtil.readUTFBinInt(in);
/* 105 */     this.nextAskTime = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public CardBookInfoResponse clone() throws CloneNotSupportedException {
/* 110 */     CardBookInfoResponse clone = (CardBookInfoResponse)super.clone();
/* 111 */     int size_0 = this.coloringInfo.size();
/* 112 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/* 114 */       KeyValue tmp_0 = this.coloringInfo.get(index_0);
/* 115 */       clone.coloringInfo.add(tmp_0.clone());
/*     */     } 
/* 117 */     int size_1 = this.cardPair.size();
/* 118 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 120 */       KeyValue tmp_1 = this.cardPair.get(index_1);
/* 121 */       clone.cardPair.add(tmp_1.clone());
/*     */     } 
/* 123 */     int size_2 = this.process.size();
/* 124 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 126 */       int tmp_2 = ((Integer)this.process.get(index_2)).intValue();
/* 127 */       clone.process.add(Integer.valueOf(tmp_2));
/*     */     } 
/* 129 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 134 */     this.coloringInfo.clear();
/* 135 */     this.bottomCard = 0;
/* 136 */     this.colorDan = 0;
/* 137 */     this.lastToColor = 0;
/* 138 */     this.refToColor = 0;
/* 139 */     this.cardPair.clear();
/* 140 */     this.process.clear();
/* 141 */     this.giveTimes = 0;
/* 142 */     this.nextAskTime = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 147 */     String retVal = "{\"coloringInfo\":" + this.coloringInfo.toString() + ",\"bottomCard\":" + this.bottomCard + ",\"colorDan\":" + this.colorDan + ",\"lastToColor\":" + this.lastToColor + ",\"refToColor\":" + this.refToColor + ",\"cardPair\":" + this.cardPair.toString() + ",\"process\":" + this.process.toString() + ",\"giveTimes\":" + this.giveTimes + ",\"nextAskTime\":" + this.nextAskTime + "}";
/* 148 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cardbook\CardBookInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */