/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class PartnerDestinyUpRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long pid;
/* 20 */   public ArrayList<Integer> itemIdList = new ArrayList<>();
/* 21 */   public ArrayList<Integer> itemNumList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public PartnerDestinyUpRequest() {
/* 25 */     this.eventRequestId = 13312;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeLong(out, this.pid);
/*    */     
/* 33 */     int size_0 = this.itemIdList.size();
/* 34 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 35 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 37 */       int tmp_0 = ((Integer)this.itemIdList.get(index_0)).intValue();
/* 38 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */     
/* 41 */     int size_1 = this.itemNumList.size();
/* 42 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 43 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 45 */       int tmp_1 = ((Integer)this.itemNumList.get(index_1)).intValue();
/* 46 */       ProtocolUtil.writeInt(out, tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 52 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/*    */     
/* 54 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 55 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 57 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 58 */       this.itemIdList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */     
/* 61 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 62 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 64 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 65 */       this.itemNumList.add(Integer.valueOf(tmp_1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerDestinyUpRequest clone() throws CloneNotSupportedException {
/* 71 */     PartnerDestinyUpRequest clone = (PartnerDestinyUpRequest)super.clone();
/* 72 */     int size_0 = this.itemIdList.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       int tmp_0 = ((Integer)this.itemIdList.get(index_0)).intValue();
/* 76 */       clone.itemIdList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 78 */     int size_1 = this.itemNumList.size();
/* 79 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 81 */       int tmp_1 = ((Integer)this.itemNumList.get(index_1)).intValue();
/* 82 */       clone.itemNumList.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 84 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 89 */     this.pid = 0L;
/* 90 */     this.itemIdList.clear();
/* 91 */     this.itemNumList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     String retVal = "{\"pid\":" + this.pid + ",\"itemIdList\":" + this.itemIdList.toString() + ",\"itemNumList\":" + this.itemNumList.toString() + "}";
/* 97 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerDestinyUpRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */