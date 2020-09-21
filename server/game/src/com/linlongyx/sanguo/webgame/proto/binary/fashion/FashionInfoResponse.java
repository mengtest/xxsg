/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fashion;
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
/*    */ public class FashionInfoResponse
/*    */   extends ResponseBase
/*    */ {
/* 20 */   public IntIntValue fashion = new IntIntValue();
/* 21 */   public ArrayList<IntIntValue> list = new ArrayList<>();
/*    */   
/*    */   public FashionInfoResponse() {
/* 24 */     this.eventResponseId = 23901;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     this.fashion.serial(out);
/*    */     
/* 32 */     int size_0 = this.list.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 37 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.fashion = new IntIntValue();
/* 44 */     this.fashion.unserial(in);
/*    */     
/* 46 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 49 */       IntIntValue tmp_0 = new IntIntValue();
/* 50 */       tmp_0.unserial(in);
/* 51 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FashionInfoResponse clone() throws CloneNotSupportedException {
/* 57 */     FashionInfoResponse clone = (FashionInfoResponse)super.clone();
/* 58 */     clone.fashion = this.fashion.clone();
/* 59 */     int size_0 = this.list.size();
/* 60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 62 */       IntIntValue tmp_0 = this.list.get(index_0);
/* 63 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 65 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 70 */     this.fashion.reset();
/* 71 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "{\"fashion\":" + this.fashion.toString() + ",\"list\":" + this.list.toString() + "}";
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fashion\FashionInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */