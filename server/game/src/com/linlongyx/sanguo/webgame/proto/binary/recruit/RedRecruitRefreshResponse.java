/*     */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*     */ public class RedRecruitRefreshResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int type;
/*  20 */   public ArrayList<Integer> today = new ArrayList<>();
/*  21 */   public ArrayList<Integer> tommorow = new ArrayList<>();
/*     */   public int refreshNum;
/*     */   
/*     */   public RedRecruitRefreshResponse() {
/*  25 */     this.eventResponseId = 24004;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public RedRecruitRefreshResponse(short retCode) {
/*  30 */     this.eventResponseId = 24004;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     ProtocolUtil.writeInt(out, this.type);
/*     */     
/*  39 */     int size_0 = this.today.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       int tmp_0 = ((Integer)this.today.get(index_0)).intValue();
/*  44 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.tommorow.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       int tmp_1 = ((Integer)this.tommorow.get(index_1)).intValue();
/*  52 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*  54 */     ProtocolUtil.writeInt(out, this.refreshNum);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  59 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  61 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  62 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  64 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  65 */       this.today.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  68 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  69 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  71 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  72 */       this.tommorow.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  74 */     this.refreshNum = ProtocolUtil.readUTFBinInt(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public RedRecruitRefreshResponse clone() throws CloneNotSupportedException {
/*  79 */     RedRecruitRefreshResponse clone = (RedRecruitRefreshResponse)super.clone();
/*  80 */     int size_0 = this.today.size();
/*  81 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  83 */       int tmp_0 = ((Integer)this.today.get(index_0)).intValue();
/*  84 */       clone.today.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  86 */     int size_1 = this.tommorow.size();
/*  87 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  89 */       int tmp_1 = ((Integer)this.tommorow.get(index_1)).intValue();
/*  90 */       clone.tommorow.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  92 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  97 */     this.type = 0;
/*  98 */     this.today.clear();
/*  99 */     this.tommorow.clear();
/* 100 */     this.refreshNum = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 105 */     String retVal = "{\"type\":" + this.type + ",\"today\":" + this.today.toString() + ",\"tommorow\":" + this.tommorow.toString() + ",\"refreshNum\":" + this.refreshNum + "}";
/* 106 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\RedRecruitRefreshResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */