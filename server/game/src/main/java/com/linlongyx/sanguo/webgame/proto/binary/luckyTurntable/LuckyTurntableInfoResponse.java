/*    */ package com.linlongyx.sanguo.webgame.proto.binary.luckyTurntable;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LuckyTurntableRecord;
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
/*    */ public class LuckyTurntableInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int luckyPoint;
/*    */   public int freeTimes;
/*    */   public byte isFirst;
/*    */   public int time;
/* 24 */   public ArrayList<LuckyTurntableRecord> recordList = new ArrayList<>();
/*    */   
/*    */   public LuckyTurntableInfoResponse() {
/* 27 */     this.eventResponseId = 23501;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.luckyPoint);
/* 34 */     ProtocolUtil.writeInt(out, this.freeTimes);
/* 35 */     ProtocolUtil.writeByte(out, this.isFirst);
/* 36 */     ProtocolUtil.writeInt(out, this.time);
/*    */     
/* 38 */     int size_0 = this.recordList.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       LuckyTurntableRecord tmp_0 = this.recordList.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.luckyPoint = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.freeTimes = ProtocolUtil.readUTFBinInt(in);
/* 51 */     this.isFirst = ProtocolUtil.readUTFBinByte(in);
/* 52 */     this.time = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       LuckyTurntableRecord tmp_0 = new LuckyTurntableRecord();
/* 58 */       tmp_0.unserial(in);
/* 59 */       this.recordList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public LuckyTurntableInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     LuckyTurntableInfoResponse clone = (LuckyTurntableInfoResponse)super.clone();
/* 66 */     int size_0 = this.recordList.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       LuckyTurntableRecord tmp_0 = this.recordList.get(index_0);
/* 70 */       clone.recordList.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.luckyPoint = 0;
/* 78 */     this.freeTimes = 0;
/* 79 */     this.isFirst = 0;
/* 80 */     this.time = 0;
/* 81 */     this.recordList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "{\"luckyPoint\":" + this.luckyPoint + ",\"freeTimes\":" + this.freeTimes + ",\"isFirst\":" + this.isFirst + ",\"time\":" + this.time + ",\"recordList\":" + this.recordList.toString() + "}";
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\luckyTurntable\LuckyTurntableInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */