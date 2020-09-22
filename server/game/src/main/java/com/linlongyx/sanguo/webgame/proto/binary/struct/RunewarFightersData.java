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
/*    */ public class RunewarFightersData
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   public ArrayList<IntIntValue> fighters = new ArrayList<>();
/* 16 */   public ArrayList<Integer> subFihgters = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     int size_0 = this.fighters.size();
/* 22 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 23 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 25 */       IntIntValue tmp_0 = this.fighters.get(index_0);
/* 26 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 29 */     int size_1 = this.subFihgters.size();
/* 30 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 31 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 33 */       int tmp_1 = ((Integer)this.subFihgters.get(index_1)).intValue();
/* 34 */       ProtocolUtil.writeInt(out, tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 42 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 44 */       IntIntValue tmp_0 = new IntIntValue();
/* 45 */       tmp_0.unserial(in);
/* 46 */       this.fighters.add(tmp_0);
/*    */     } 
/*    */     
/* 49 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 50 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 52 */       int tmp_1 = ProtocolUtil.readUTFBinInt(in);
/* 53 */       this.subFihgters.add(Integer.valueOf(tmp_1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RunewarFightersData clone() throws CloneNotSupportedException {
/* 59 */     RunewarFightersData clone = (RunewarFightersData)super.clone();
/* 60 */     int size_0 = this.fighters.size();
/* 61 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 63 */       IntIntValue tmp_0 = this.fighters.get(index_0);
/* 64 */       clone.fighters.add(tmp_0.clone());
/*    */     } 
/* 66 */     int size_1 = this.subFihgters.size();
/* 67 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 69 */       int tmp_1 = ((Integer)this.subFihgters.get(index_1)).intValue();
/* 70 */       clone.subFihgters.add(Integer.valueOf(tmp_1));
/*    */     } 
/* 72 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 77 */     this.fighters.clear();
/* 78 */     this.subFihgters.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     String retVal = "{\"fighters\":" + this.fighters.toString() + ",\"subFihgters\":" + this.subFihgters.toString() + "}";
/* 84 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\RunewarFightersData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */