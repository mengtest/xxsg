/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mental;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
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
/*    */ public class PushLotteryRecordResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<MentalShowStruct> items = new ArrayList<>();
/*    */   
/*    */   public PushLotteryRecordResponse() {
/* 23 */     this.eventResponseId = 27306;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PushLotteryRecordResponse(short retCode) {
/* 28 */     this.eventResponseId = 27306;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.items.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       MentalShowStruct tmp_0 = this.items.get(index_0);
/* 41 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 48 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 49 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 51 */       MentalShowStruct tmp_0 = new MentalShowStruct();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.items.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public PushLotteryRecordResponse clone() throws CloneNotSupportedException {
/* 59 */     PushLotteryRecordResponse clone = (PushLotteryRecordResponse)super.clone();
/* 60 */     int size_0 = this.items.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       MentalShowStruct tmp_0 = this.items.get(index_0);
/* 64 */       clone.items.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.items.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"items\":" + this.items.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mental\PushLotteryRecordResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */