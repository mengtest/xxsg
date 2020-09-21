/*    */ package com.linlongyx.sanguo.webgame.proto.binary.rebate;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
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
/*    */ public class GetPersonalRecordResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<KeyValue> personalRecords = new ArrayList<>();
/*    */   
/*    */   public GetPersonalRecordResponse() {
/* 23 */     this.eventResponseId = 24013;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetPersonalRecordResponse(short retCode) {
/* 28 */     this.eventResponseId = 24013;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.personalRecords.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       KeyValue tmp_0 = this.personalRecords.get(index_0);
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
/* 51 */       KeyValue tmp_0 = new KeyValue();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.personalRecords.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public GetPersonalRecordResponse clone() throws CloneNotSupportedException {
/* 59 */     GetPersonalRecordResponse clone = (GetPersonalRecordResponse)super.clone();
/* 60 */     int size_0 = this.personalRecords.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       KeyValue tmp_0 = this.personalRecords.get(index_0);
/* 64 */       clone.personalRecords.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.personalRecords.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"personalRecords\":" + this.personalRecords.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\rebate\GetPersonalRecordResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */