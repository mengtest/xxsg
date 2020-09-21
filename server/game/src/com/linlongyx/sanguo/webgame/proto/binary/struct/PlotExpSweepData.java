/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlotExpSweepData
/*    */ {
/*    */   public int insId;
/* 14 */   public ArrayList<Reward> list = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeInt(out, this.insId);
/*    */     
/* 20 */     int size_0 = this.list.size();
/* 21 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 22 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 24 */       Reward tmp_0 = this.list.get(index_0);
/* 25 */       tmp_0.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 31 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/*    */     
/* 33 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 34 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 36 */       Reward tmp_0 = new Reward();
/* 37 */       tmp_0.unserial(in);
/* 38 */       this.list.add(tmp_0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public PlotExpSweepData clone() throws CloneNotSupportedException {
/* 44 */     PlotExpSweepData clone = (PlotExpSweepData)super.clone();
/* 45 */     int size_0 = this.list.size();
/* 46 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 48 */       Reward tmp_0 = this.list.get(index_0);
/* 49 */       clone.list.add(tmp_0.clone());
/*    */     } 
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.insId = 0;
/* 57 */     this.list.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"insId\":" + this.insId + ",\"list\":" + this.list.toString() + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\PlotExpSweepData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */