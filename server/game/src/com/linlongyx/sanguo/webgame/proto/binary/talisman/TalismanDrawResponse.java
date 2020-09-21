/*     */ package com.linlongyx.sanguo.webgame.proto.binary.talisman;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
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
/*     */ public class TalismanDrawResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<IntIntValue> itemList = new ArrayList<>();
/*  21 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*     */   public int times;
/*     */   public byte freeTimes;
/*     */   
/*     */   public TalismanDrawResponse() {
/*  26 */     this.eventResponseId = 22902;
/*  27 */     this.codeType = 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  33 */     int size_0 = this.itemList.size();
/*  34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  37 */       IntIntValue tmp_0 = this.itemList.get(index_0);
/*  38 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  41 */     int size_1 = this.list.size();
/*  42 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  43 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  45 */       IntIntValue tmp_1 = this.list.get(index_1);
/*  46 */       tmp_1.serial(out);
/*     */     } 
/*  48 */     ProtocolUtil.writeInt(out, this.times);
/*  49 */     ProtocolUtil.writeByte(out, this.freeTimes);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  55 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  56 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  58 */       IntIntValue tmp_0 = new IntIntValue();
/*  59 */       tmp_0.unserial(in);
/*  60 */       this.itemList.add(tmp_0);
/*     */     } 
/*     */     
/*  63 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  64 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  66 */       IntIntValue tmp_1 = new IntIntValue();
/*  67 */       tmp_1.unserial(in);
/*  68 */       this.list.add(tmp_1);
/*     */     } 
/*  70 */     this.times = ProtocolUtil.readUTFBinInt(in);
/*  71 */     this.freeTimes = ProtocolUtil.readUTFBinByte(in);
/*     */   }
/*     */ 
/*     */   
/*     */   public TalismanDrawResponse clone() throws CloneNotSupportedException {
/*  76 */     TalismanDrawResponse clone = (TalismanDrawResponse)super.clone();
/*  77 */     int size_0 = this.itemList.size();
/*  78 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  80 */       IntIntValue tmp_0 = this.itemList.get(index_0);
/*  81 */       clone.itemList.add(tmp_0.clone());
/*     */     } 
/*  83 */     int size_1 = this.list.size();
/*  84 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  86 */       IntIntValue tmp_1 = this.list.get(index_1);
/*  87 */       clone.list.add(tmp_1.clone());
/*     */     } 
/*  89 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  94 */     this.itemList.clear();
/*  95 */     this.list.clear();
/*  96 */     this.times = 0;
/*  97 */     this.freeTimes = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 102 */     String retVal = "{\"itemList\":" + this.itemList.toString() + ",\"list\":" + this.list.toString() + ",\"times\":" + this.times + ",\"freeTimes\":" + this.freeTimes + "}";
/* 103 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\talisman\TalismanDrawResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */