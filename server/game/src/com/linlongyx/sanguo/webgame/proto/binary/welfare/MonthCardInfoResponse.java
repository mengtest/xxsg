/*    */ package com.linlongyx.sanguo.webgame.proto.binary.welfare;
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
/*    */ public class MonthCardInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   public int day;
/*    */   public int weekDay;
/*    */   
/*    */   public MonthCardInfoResponse() {
/* 25 */     this.eventResponseId = 21904;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public MonthCardInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 21904;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 38 */     int size_0 = this.list.size();
/* 39 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 43 */       tmp_0.serial(out);
/*    */     } 
/* 45 */     ProtocolUtil.writeInt(out, this.day);
/* 46 */     ProtocolUtil.writeInt(out, this.weekDay);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 53 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 55 */       IntIntValue tmp_0 = new IntIntValue();
/* 56 */       tmp_0.unserial(in);
/* 57 */       this.list.add(tmp_0);
/*    */     } 
/* 59 */     this.day = ProtocolUtil.readUTFBinInt(in);
/* 60 */     this.weekDay = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public MonthCardInfoResponse clone() throws CloneNotSupportedException {
/* 65 */     MonthCardInfoResponse clone = (MonthCardInfoResponse)super.clone();
/* 66 */     int size_0 = this.list.size();
/* 67 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 69 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 70 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.list.clear();
/* 78 */     this.day = 0;
/* 79 */     this.weekDay = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "{\"list\":" + this.list.toString() + ",\"day\":" + this.day + ",\"weekDay\":" + this.weekDay + "}";
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\welfare\MonthCardInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */