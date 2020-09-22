/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaguaRecord
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public int insId;
/* 16 */   public ArrayList<LongStringValue> playerList = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 20 */     ProtocolUtil.writeInt(out, this.insId);
/*    */     
/* 22 */     int size_0 = this.playerList.size();
/* 23 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 24 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 26 */       LongStringValue tmp_0 = this.playerList.get(index_0);
/* 27 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 35 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 36 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 38 */       LongStringValue tmp_0 = new LongStringValue();
/* 39 */       tmp_0.unserial(in);
/* 40 */       this.playerList.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BaguaRecord clone() throws CloneNotSupportedException {
/* 46 */     BaguaRecord clone = (BaguaRecord)super.clone();
/* 47 */     int size_0 = this.playerList.size();
/* 48 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 50 */       LongStringValue tmp_0 = this.playerList.get(index_0);
/* 51 */       clone.playerList.add(tmp_0);
/*    */     } 
/* 53 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 58 */     this.insId = 0;
/* 59 */     this.playerList.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     String retVal = "{\"insId\":" + this.insId + ",\"playerList\":" + this.playerList.toString() + "}";
/* 65 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\BaguaRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */