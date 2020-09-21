/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FightInitData
/*    */ {
/* 12 */   public ArrayList<FightGroupData> lGroups = new ArrayList<>();
/* 13 */   public ArrayList<FightGroupData> rGroups = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     int size_0 = this.lGroups.size();
/* 19 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 20 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 22 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/* 23 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 26 */     int size_1 = this.rGroups.size();
/* 27 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 28 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 30 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/* 31 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 39 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 41 */       FightGroupData tmp_0 = new FightGroupData();
/* 42 */       tmp_0.unserial(in);
/* 43 */       this.lGroups.add(tmp_0);
/*    */     } 
/*    */     
/* 46 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 47 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 49 */       FightGroupData tmp_1 = new FightGroupData();
/* 50 */       tmp_1.unserial(in);
/* 51 */       this.rGroups.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FightInitData clone() throws CloneNotSupportedException {
/* 57 */     FightInitData clone = (FightInitData)super.clone();
/* 58 */     int size_0 = this.lGroups.size();
/* 59 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 61 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/* 62 */       clone.lGroups.add(tmp_0.clone());
/*    */     } 
/* 64 */     int size_1 = this.rGroups.size();
/* 65 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 67 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/* 68 */       clone.rGroups.add(tmp_1.clone());
/*    */     } 
/* 70 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 75 */     this.lGroups.clear();
/* 76 */     this.rGroups.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "{\"lGroups\":" + this.lGroups.toString() + ",\"rGroups\":" + this.rGroups.toString() + "}";
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FightInitData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */