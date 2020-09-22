/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
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
/*    */ public class BagSellItemRequest
/*    */   extends RequestBase
/*    */ {
/* 19 */   public ArrayList<Integer> ids = new ArrayList<>();
/* 20 */   public ArrayList<Integer> nums = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public BagSellItemRequest() {
/* 24 */     this.eventRequestId = 10703;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     int size_0 = this.ids.size();
/* 32 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       int tmp_0 = ((Integer)this.ids.get(index_0)).intValue();
/* 36 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */     
/* 39 */     int size_1 = this.nums.size();
/* 40 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 41 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 43 */       int tmp_1 = ((Integer)this.nums.get(index_1)).intValue();
/* 44 */       ProtocolUtil.writeInt(out, tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 51 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 52 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 54 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 55 */       this.ids.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */     
/* 58 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 59 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 61 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 62 */       this.nums.add(Integer.valueOf(tmp_1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BagSellItemRequest clone() throws CloneNotSupportedException {
/* 68 */     BagSellItemRequest clone = (BagSellItemRequest)super.clone();
/* 69 */     int size_0 = this.ids.size();
/* 70 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 72 */       int tmp_0 = ((Integer)this.ids.get(index_0)).intValue();
/* 73 */       clone.ids.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 75 */     int size_1 = this.nums.size();
/* 76 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 78 */       int tmp_1 = ((Integer)this.nums.get(index_1)).intValue();
/* 79 */       clone.nums.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 81 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 86 */     this.ids.clear();
/* 87 */     this.nums.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 92 */     String retVal = "{\"ids\":" + this.ids.toString() + ",\"nums\":" + this.nums.toString() + "}";
/* 93 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagSellItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */