/*     */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*     */ public class GetBattleListResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<Long> partnerList = new ArrayList<>();
/*  21 */   public ArrayList<KeyValue> partnerHP = new ArrayList<>();
/*  22 */   public ArrayList<KeyValue> MaxHPLevel = new ArrayList<>();
/*     */   public int quality;
/*     */   
/*     */   public GetBattleListResponse() {
/*  26 */     this.eventResponseId = 23311;
/*  27 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public GetBattleListResponse(short retCode) {
/*  31 */     this.eventResponseId = 23311;
/*  32 */     this.codeType = 2;
/*  33 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  39 */     int size_0 = this.partnerList.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       long tmp_0 = ((Long)this.partnerList.get(index_0)).longValue();
/*  44 */       ProtocolUtil.writeLong(out, tmp_0);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.partnerHP.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       KeyValue tmp_1 = this.partnerHP.get(index_1);
/*  52 */       tmp_1.serial(out);
/*     */     } 
/*     */     
/*  55 */     int size_2 = this.MaxHPLevel.size();
/*  56 */     ProtocolUtil.writeStrArraySize(out, size_2);
/*  57 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  59 */       KeyValue tmp_2 = this.MaxHPLevel.get(index_2);
/*  60 */       tmp_2.serial(out);
/*     */     } 
/*  62 */     ProtocolUtil.writeInt(out, this.quality);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  68 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  69 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  71 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/*  72 */       this.partnerList.add(Long.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  75 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  76 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  78 */       KeyValue tmp_1 = new KeyValue();
/*  79 */       tmp_1.unserial(in);
/*  80 */       this.partnerHP.add(tmp_1);
/*     */     } 
/*     */     
/*  83 */     int size_2 = ProtocolUtil.readStrArraySize(in);
/*  84 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/*  86 */       KeyValue tmp_2 = new KeyValue();
/*  87 */       tmp_2.unserial(in);
/*  88 */       this.MaxHPLevel.add(tmp_2);
/*     */     } 
/*  90 */     this.quality = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public GetBattleListResponse clone() throws CloneNotSupportedException {
/*  95 */     GetBattleListResponse clone = (GetBattleListResponse)super.clone();
/*  96 */     int size_0 = this.partnerList.size();
/*  97 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  99 */       long tmp_0 = ((Long)this.partnerList.get(index_0)).longValue();
/* 100 */       clone.partnerList.add(Long.valueOf(tmp_0));
/*     */     } 
/* 102 */     int size_1 = this.partnerHP.size();
/* 103 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/* 105 */       KeyValue tmp_1 = this.partnerHP.get(index_1);
/* 106 */       clone.partnerHP.add(tmp_1.clone());
/*     */     } 
/* 108 */     int size_2 = this.MaxHPLevel.size();
/* 109 */     for (int index_2 = 0; index_2 < size_2; index_2++) {
/*     */       
/* 111 */       KeyValue tmp_2 = this.MaxHPLevel.get(index_2);
/* 112 */       clone.MaxHPLevel.add(tmp_2.clone());
/*     */     } 
/* 114 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 119 */     this.partnerList.clear();
/* 120 */     this.partnerHP.clear();
/* 121 */     this.MaxHPLevel.clear();
/* 122 */     this.quality = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 127 */     String retVal = "{\"partnerList\":" + this.partnerList.toString() + ",\"partnerHP\":" + this.partnerHP.toString() + ",\"MaxHPLevel\":" + this.MaxHPLevel.toString() + ",\"quality\":" + this.quality + "}";
/* 128 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\GetBattleListResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */