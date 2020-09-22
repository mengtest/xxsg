/*     */ package com.linlongyx.sanguo.webgame.proto.binary.singleIns;
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
/*     */ public class SingleBossInfoResponse
/*     */   extends ResponseBase
/*     */ {
/*  20 */   public ArrayList<Integer> passBoss = new ArrayList<>();
/*  21 */   public ArrayList<KeyValue> bossTimes = new ArrayList<>();
/*     */   
/*     */   public SingleBossInfoResponse() {
/*  24 */     this.eventResponseId = 23003;
/*  25 */     this.codeType = 2;
/*     */   }
/*     */   
/*     */   public SingleBossInfoResponse(short retCode) {
/*  29 */     this.eventResponseId = 23003;
/*  30 */     this.codeType = 2;
/*  31 */     this.retCode = retCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void serial(ByteBuf out) {
/*  37 */     int size_0 = this.passBoss.size();
/*  38 */     ProtocolUtil.writeStrArraySize(out, size_0);
/*  39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  41 */       int tmp_0 = ((Integer)this.passBoss.get(index_0)).intValue();
/*  42 */       ProtocolUtil.writeInt(out, tmp_0);
/*     */     } 
/*     */     
/*  45 */     int size_1 = this.bossTimes.size();
/*  46 */     ProtocolUtil.writeStrArraySize(out, size_1);
/*  47 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  49 */       KeyValue tmp_1 = this.bossTimes.get(index_1);
/*  50 */       tmp_1.serial(out);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unserial(ByteBuf in) {
/*  57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/*  58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  60 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/*  61 */       this.passBoss.add(Integer.valueOf(tmp_0));
/*     */     } 
/*     */     
/*  64 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/*  65 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  67 */       KeyValue tmp_1 = new KeyValue();
/*  68 */       tmp_1.unserial(in);
/*  69 */       this.bossTimes.add(tmp_1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SingleBossInfoResponse clone() throws CloneNotSupportedException {
/*  75 */     SingleBossInfoResponse clone = (SingleBossInfoResponse)super.clone();
/*  76 */     int size_0 = this.passBoss.size();
/*  77 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*     */       
/*  79 */       int tmp_0 = ((Integer)this.passBoss.get(index_0)).intValue();
/*  80 */       clone.passBoss.add(Integer.valueOf(tmp_0));
/*     */     } 
/*  82 */     int size_1 = this.bossTimes.size();
/*  83 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*     */       
/*  85 */       KeyValue tmp_1 = this.bossTimes.get(index_1);
/*  86 */       clone.bossTimes.add(tmp_1.clone());
/*     */     } 
/*  88 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  93 */     this.passBoss.clear();
/*  94 */     this.bossTimes.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     String retVal = "{\"passBoss\":" + this.passBoss.toString() + ",\"bossTimes\":" + this.bossTimes.toString() + "}";
/* 100 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\singleIns\SingleBossInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */