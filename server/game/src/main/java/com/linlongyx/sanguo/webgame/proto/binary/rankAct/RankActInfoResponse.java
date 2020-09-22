/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rankAct;
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
/*    */ public class RankActInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int rankId;
/*    */   public long value;
/*    */   public int time;
/* 23 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   
/*    */   public RankActInfoResponse() {
/* 26 */     this.eventResponseId = 22002;
/* 27 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RankActInfoResponse(short retCode) {
/* 31 */     this.eventResponseId = 22002;
/* 32 */     this.codeType = 2;
/* 33 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     ProtocolUtil.writeInt(out, this.rankId);
/* 39 */     ProtocolUtil.writeLong(out, this.value);
/* 40 */     ProtocolUtil.writeInt(out, this.time);
/*    */     
/* 42 */     int size_0 = this.list.size();
/* 43 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 47 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     this.rankId = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.value = ProtocolUtil.readUTFBinLong(in);
/* 55 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 57 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 58 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 60 */       IntIntValue tmp_0 = new IntIntValue();
/* 61 */       tmp_0.unserial(in);
/* 62 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RankActInfoResponse clone() throws CloneNotSupportedException {
/* 68 */     RankActInfoResponse clone = (RankActInfoResponse)super.clone();
/* 69 */     int size_0 = this.list.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 73 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 75 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 80 */     this.rankId = 0;
/* 81 */     this.value = 0L;
/* 82 */     this.time = 0;
/* 83 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "{\"rankId\":" + this.rankId + ",\"value\":" + this.value + ",\"time\":" + this.time + ",\"list\":" + this.list.toString() + "}";
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rankAct\RankActInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */