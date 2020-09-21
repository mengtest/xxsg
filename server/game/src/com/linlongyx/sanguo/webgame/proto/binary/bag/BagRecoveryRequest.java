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
/*    */ public class BagRecoveryRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/* 20 */   public ArrayList<Long> ids = new ArrayList<>();
/* 21 */   public ArrayList<Integer> itemNum = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public BagRecoveryRequest() {
/* 25 */     this.eventRequestId = 10708;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.type);
/*    */     
/* 33 */     int size_0 = this.ids.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       long tmp_0 = ((Long)this.ids.get(index_0)).longValue();
/* 38 */       ProtocolUtil.writeLong(out, tmp_0);
/*    */     } 
/*    */     
/* 41 */     int size_1 = this.itemNum.size();
/* 42 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 43 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 45 */       int tmp_1 = ((Integer)this.itemNum.get(index_1)).intValue();
/* 46 */       ProtocolUtil.writeInt(out, tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     this.type = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       long tmp_0 = ProtocolUtil.readUTFBinLong(in);
/* 58 */       this.ids.add(Long.valueOf(tmp_0));
/*    */     } 
/*    */     
/* 61 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 62 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 64 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 65 */       this.itemNum.add(Integer.valueOf(tmp_1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BagRecoveryRequest clone() throws CloneNotSupportedException {
/* 71 */     BagRecoveryRequest clone = (BagRecoveryRequest)super.clone();
/* 72 */     int size_0 = this.ids.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       long tmp_0 = ((Long)this.ids.get(index_0)).longValue();
/* 76 */       clone.ids.add(Long.valueOf(tmp_0));
/*    */     } 
/* 78 */     int size_1 = this.itemNum.size();
/* 79 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 81 */       int tmp_1 = ((Integer)this.itemNum.get(index_1)).intValue();
/* 82 */       clone.itemNum.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 84 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 89 */     this.type = 0;
/* 90 */     this.ids.clear();
/* 91 */     this.itemNum.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     String retVal = "{\"type\":" + this.type + ",\"ids\":" + this.ids.toString() + ",\"itemNum\":" + this.itemNum.toString() + "}";
/* 97 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagRecoveryRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */