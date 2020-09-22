/*    */ package com.linlongyx.sanguo.webgame.proto.binary.souls;
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
/*    */ public class SoulsUpgradeRequest
/*    */   extends RequestBase
/*    */ {
/* 19 */   public ArrayList<Integer> itemList = new ArrayList<>();
/* 20 */   public ArrayList<Integer> numList = new ArrayList<>();
/*    */   
/*    */   public int soulsId;
/*    */   
/*    */   public SoulsUpgradeRequest() {
/* 25 */     this.eventRequestId = 11210;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     int size_0 = this.itemList.size();
/* 33 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       int tmp_0 = ((Integer)this.itemList.get(index_0)).intValue();
/* 37 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */     
/* 40 */     int size_1 = this.numList.size();
/* 41 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 42 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 44 */       int tmp_1 = ((Integer)this.numList.get(index_1)).intValue();
/* 45 */       ProtocolUtil.writeInt(out, tmp_1);
/*    */     } 
/* 47 */     ProtocolUtil.writeInt(out, this.soulsId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 53 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 54 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 56 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 57 */       this.itemList.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */     
/* 60 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 61 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 63 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 64 */       this.numList.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 66 */     this.soulsId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SoulsUpgradeRequest clone() throws CloneNotSupportedException {
/* 71 */     SoulsUpgradeRequest clone = (SoulsUpgradeRequest)super.clone();
/* 72 */     int size_0 = this.itemList.size();
/* 73 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 75 */       int tmp_0 = ((Integer)this.itemList.get(index_0)).intValue();
/* 76 */       clone.itemList.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 78 */     int size_1 = this.numList.size();
/* 79 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 81 */       int tmp_1 = ((Integer)this.numList.get(index_1)).intValue();
/* 82 */       clone.numList.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 84 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 89 */     this.itemList.clear();
/* 90 */     this.numList.clear();
/* 91 */     this.soulsId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 96 */     String retVal = "{\"itemList\":" + this.itemList.toString() + ",\"numList\":" + this.numList.toString() + ",\"soulsId\":" + this.soulsId + "}";
/* 97 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\souls\SoulsUpgradeRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */