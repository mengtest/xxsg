/*     */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DrawCardData;
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
/*     */ public class DrawCardCleanResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public int drawId;
/*  21 */   public ArrayList<DrawCardData> openList = new ArrayList<>();
/*     */   public int cleanCCY;
/*  23 */   public ArrayList<Integer> rares = new ArrayList<>();
/*     */   
/*     */   public DrawCardCleanResponse() {
/*  26 */     this.eventResponseId = 21803;
/*  27 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public DrawCardCleanResponse(short retCode) {
/*  31 */     this.eventResponseId = 21803;
/*  32 */     this.codeType = 2;
/*  33 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  38 */     ProtocolUtil.writeInt(out, this.drawId);
/*     */     
/*  40 */     int size_0 = this.openList.size();
/*  41 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  44 */       DrawCardData tmp_0 = this.openList.get(index_0);
/*  45 */       tmp_0.serial(out);
/*     */     } 
/*  47 */     ProtocolUtil.writeInt(out, this.cleanCCY);
/*     */     
/*  49 */     int size_1 = this.rares.size();
/*  50 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  51 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  53 */       int tmp_1 = ((Integer)this.rares.get(index_1)).intValue();
/*  54 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  60 */     this.drawId = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  62 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  63 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  65 */       DrawCardData tmp_0 = new DrawCardData();
/*  66 */       tmp_0.unserial(in);
/*  67 */       this.openList.add(tmp_0);
/*     */     } 
/*  69 */     this.cleanCCY = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  71 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  72 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  74 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  75 */       this.rares.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DrawCardCleanResponse clone() throws CloneNotSupportedException {
/*  81 */     DrawCardCleanResponse clone = (DrawCardCleanResponse)super.clone();
/*  82 */     int size_0 = this.openList.size();
/*  83 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  85 */       DrawCardData tmp_0 = this.openList.get(index_0);
/*  86 */       clone.openList.add(tmp_0.clone());
/*     */     } 
/*  88 */     int size_1 = this.rares.size();
/*  89 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  91 */       int tmp_1 = ((Integer)this.rares.get(index_1)).intValue();
/*  92 */       clone.rares.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  94 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  99 */     this.drawId = 0;
/* 100 */     this.openList.clear();
/* 101 */     this.cleanCCY = 0;
/* 102 */     this.rares.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 107 */     String retVal = "{\"drawId\":" + this.drawId + ",\"openList\":" + this.openList.toString() + ",\"cleanCCY\":" + this.cleanCCY + ",\"rares\":" + this.rares.toString() + "}";
/* 108 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardCleanResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */