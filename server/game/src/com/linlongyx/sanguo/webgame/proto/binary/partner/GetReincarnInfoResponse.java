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
/*     */ public class GetReincarnInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*     */   public long pid;
/*  21 */   public ArrayList<KeyValue> list = new ArrayList<>();
/*  22 */   public ArrayList<Integer> reincarnList = new ArrayList<>();
/*     */   
/*     */   public GetReincarnInfoResponse() {
/*  25 */     this.eventResponseId = 23316;
/*  26 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public GetReincarnInfoResponse(short retCode) {
/*  30 */     this.eventResponseId = 23316;
/*  31 */     this.codeType = 2;
/*  32 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     ProtocolUtil.writeLong(out, this.pid);
/*     */     
/*  39 */     int size_0 = this.list.size();
/*  40 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  41 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  43 */       KeyValue tmp_0 = this.list.get(index_0);
/*  44 */       tmp_0.serial(out);
/*     */     } 
/*     */     
/*  47 */     int size_1 = this.reincarnList.size();
/*  48 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  49 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  51 */       int tmp_1 = ((Integer)this.reincarnList.get(index_1)).intValue();
/*  52 */       ProtocolUtil.writeInt(out, tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  58 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/*     */     
/*  60 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  63 */       KeyValue tmp_0 = new KeyValue();
/*  64 */       tmp_0.unserial(in);
/*  65 */       this.list.add(tmp_0);
/*     */     } 
/*     */     
/*  68 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  69 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  71 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/*  72 */       this.reincarnList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GetReincarnInfoResponse clone() throws CloneNotSupportedException {
/*  78 */     GetReincarnInfoResponse clone = (GetReincarnInfoResponse)super.clone();
/*  79 */     int size_0 = this.list.size();
/*  80 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  82 */       KeyValue tmp_0 = this.list.get(index_0);
/*  83 */       clone.list.add(tmp_0.clone());
/*     */     } 
/*  85 */     int size_1 = this.reincarnList.size();
/*  86 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  88 */       int tmp_1 = ((Integer)this.reincarnList.get(index_1)).intValue();
/*  89 */       clone.reincarnList.add(Integer.valueOf(tmp_1));
/*     */     } 
/*  91 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  96 */     this.pid = 0L;
/*  97 */     this.list.clear();
/*  98 */     this.reincarnList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 103 */     String retVal = "{\"pid\":" + this.pid + ",\"list\":" + this.list.toString() + ",\"reincarnList\":" + this.reincarnList.toString() + "}";
/* 104 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\GetReincarnInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */