/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.AdditionValue;
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
/*    */ public class AdditionUpdateResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public ArrayList<AdditionValue> addValues = new ArrayList<>();
/*    */   
/*    */   public AdditionUpdateResponse() {
/* 23 */     this.eventResponseId = 21004;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public AdditionUpdateResponse(short retCode) {
/* 28 */     this.eventResponseId = 21004;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     int size_0 = this.addValues.size();
/* 37 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 38 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 40 */       AdditionValue tmp_0 = this.addValues.get(index_0);
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
/* 51 */       AdditionValue tmp_0 = new AdditionValue();
/* 52 */       tmp_0.unserial(in);
/* 53 */       this.addValues.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AdditionUpdateResponse clone() throws CloneNotSupportedException {
/* 59 */     AdditionUpdateResponse clone = (AdditionUpdateResponse)super.clone();
/* 60 */     int size_0 = this.addValues.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       AdditionValue tmp_0 = this.addValues.get(index_0);
/* 64 */       clone.addValues.add(tmp_0.clone());
/*    */     } 
/* 66 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 71 */     this.addValues.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"addValues\":" + this.addValues.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\AdditionUpdateResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */