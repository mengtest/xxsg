/*    */ package com.linlongyx.sanguo.webgame.proto.binary.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossRankActInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rankId;
/*    */   public long value;
/*    */   public int time;
/* 23 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   
/*    */   public CrossRankActInfoResponse() {
/* 26 */     this.eventResponseId = 24102;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     ProtocolUtil.writeInt(out, this.rankId);
/* 33 */     ProtocolUtil.writeLong(out, this.value);
/* 34 */     ProtocolUtil.writeInt(out, this.time);
/*    */     
/* 36 */     int size_0 = this.list.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 47 */     this.rankId = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.value = ProtocolUtil.readUTFBinLong(in);
/* 49 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       IntIntValue tmp_0 = new IntIntValue();
/* 55 */       tmp_0.unserial(in);
/* 56 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossRankActInfoResponse clone() throws CloneNotSupportedException {
/* 62 */     CrossRankActInfoResponse clone = (CrossRankActInfoResponse)super.clone();
/* 63 */     int size_0 = this.list.size();
/* 64 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 66 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 67 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 69 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 74 */     this.rankId = 0;
/* 75 */     this.value = 0L;
/* 76 */     this.time = 0;
/* 77 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"rankId\":" + this.rankId + ",\"value\":" + this.value + ",\"time\":" + this.time + ",\"list\":" + this.list.toString() + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\crossRankAct\CrossRankActInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */