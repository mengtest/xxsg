/*    */ package com.linlongyx.sanguo.webgame.proto.binary.divine;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongStringValue;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class DivineRecordInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 22 */   public List<LongStringValue> recordList = new ArrayList<>();
/*    */   
/*    */   public DivineRecordInfoResponse() {
/* 25 */     this.eventResponseId = 24305;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     int size_0 = this.recordList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       LongStringValue tmp_0 = this.recordList.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 45 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 47 */       LongStringValue tmp_0 = new LongStringValue();
/* 48 */       tmp_0.unserial(in);
/* 49 */       this.recordList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DivineRecordInfoResponse clone() throws CloneNotSupportedException {
/* 55 */     DivineRecordInfoResponse clone = (DivineRecordInfoResponse)super.clone();
/* 56 */     int size_0 = this.recordList.size();
/* 57 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 59 */       LongStringValue tmp_0 = this.recordList.get(index_0);
/* 60 */       clone.recordList.add(tmp_0);
/*    */     } 
/* 62 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 67 */     this.recordList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     String retVal = "{\"recordList\":" + this.recordList.toString() + "}";
/* 73 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\divine\DivineRecordInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */