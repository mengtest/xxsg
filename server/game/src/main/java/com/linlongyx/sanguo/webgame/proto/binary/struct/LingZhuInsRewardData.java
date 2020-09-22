/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LingZhuInsRewardData
/*    */ {
/*    */   public int chapter;
/* 13 */   public ArrayList<Integer> list = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.chapter);
/*    */     
/* 19 */     int size_0 = this.list.size();
/* 20 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 21 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 23 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 24 */       ProtocolUtil.writeInt(out, tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 30 */     this.chapter = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 32 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 33 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 35 */       int tmp_0 = ProtocolUtil.readUTFBinInt(in);
/* 36 */       this.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public LingZhuInsRewardData clone() throws CloneNotSupportedException {
/* 42 */     LingZhuInsRewardData clone = (LingZhuInsRewardData)super.clone();
/* 43 */     int size_0 = this.list.size();
/* 44 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 46 */       int tmp_0 = ((Integer)this.list.get(index_0)).intValue();
/* 47 */       clone.list.add(Integer.valueOf(tmp_0));
/*    */     } 
/* 49 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.chapter = 0;
/* 55 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"chapter\":" + this.chapter + ",\"list\":" + this.list.toString() + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\LingZhuInsRewardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */