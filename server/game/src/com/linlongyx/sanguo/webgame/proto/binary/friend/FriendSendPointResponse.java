/*     */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
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
/*     */ public class FriendSendPointResponse
/*     */   extends ResponseBase
/*     */ {
/*  19 */   public ArrayList<Long> playerIds = new ArrayList<>();
/*     */   public int sendNum;
/*  21 */   public ArrayList<Long> favorValues = new ArrayList<>();
/*     */   
/*     */   public FriendSendPointResponse() {
/*  24 */     this.eventResponseId = 23416;
/*  25 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public FriendSendPointResponse(short retCode) {
/*  29 */     this.eventResponseId = 23416;
/*  30 */     this.codeType = 2;
/*  31 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     int size_0 = this.playerIds.size();
/*  38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  41 */       long tmp_0 = ((Long)this.playerIds.get(index_0)).longValue();
/*  42 */       ProtocolUtil.writeLong(out, tmp_0);
/*     */     } 
/*  44 */     ProtocolUtil.writeInt(out, this.sendNum);
/*     */     
/*  46 */     int size_1 = this.favorValues.size();
/*  47 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  48 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  50 */       long tmp_1 = ((Long)this.favorValues.get(index_1)).longValue();
/*  51 */       ProtocolUtil.writeLong(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  61 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/*  62 */       this.playerIds.add(Long.valueOf(tmp_0));
/*     */     } 
/*  64 */     this.sendNum = ProtocolUtil.readUTFBinInt(in);
/*     */     
/*  66 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  67 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  69 */       long tmp_1 = ProtocolUtil.readUTFBinLong(in);
/*  70 */       this.favorValues.add(Long.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public FriendSendPointResponse clone() throws CloneNotSupportedException {
/*  76 */     FriendSendPointResponse clone = (FriendSendPointResponse)super.clone();
/*  77 */     int size_0 = this.playerIds.size();
/*  78 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  80 */       long tmp_0 = ((Long)this.playerIds.get(index_0)).longValue();
/*  81 */       clone.playerIds.add(Long.valueOf(tmp_0));
/*     */     } 
/*  83 */     int size_1 = this.favorValues.size();
/*  84 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  86 */       long tmp_1 = ((Long)this.favorValues.get(index_1)).longValue();
/*  87 */       clone.favorValues.add(Long.valueOf(tmp_1));
/*     */     } 
/*  89 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  94 */     this.playerIds.clear();
/*  95 */     this.sendNum = 0;
/*  96 */     this.favorValues.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     String retVal = "{\"playerIds\":" + this.playerIds.toString() + ",\"sendNum\":" + this.sendNum + ",\"favorValues\":" + this.favorValues.toString() + "}";
/* 102 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendSendPointResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */