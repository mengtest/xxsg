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
/*    */ public class FightInitData
/*    */ {
/* 13 */   public ArrayList<FightGroupData> lGroups = new ArrayList<>();
/* 14 */   public ArrayList<FightGroupData> rGroups = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 19 */     int size_0 = this.lGroups.size();
/* 20 */     ProtocolUtil.writeStrArraySize(out, size_0);
/* 21 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 23 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/* 24 */       tmp_0.serial(out);
/*    */     } 
/*    */     
/* 27 */     int size_1 = this.rGroups.size();
/* 28 */     ProtocolUtil.writeStrArraySize(out, size_1);
/* 29 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 31 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/* 32 */       tmp_1.serial(out);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     int size_0 = ProtocolUtil.readStrArraySize(in);
/* 40 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 42 */       FightGroupData tmp_0 = new FightGroupData();
/* 43 */       tmp_0.unserial(in);
/* 44 */       this.lGroups.add(tmp_0);
/*    */     } 
/*    */     
/* 47 */     int size_1 = ProtocolUtil.readStrArraySize(in);
/* 48 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 50 */       FightGroupData tmp_1 = new FightGroupData();
/* 51 */       tmp_1.unserial(in);
/* 52 */       this.rGroups.add(tmp_1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public FightInitData clone() throws CloneNotSupportedException {
/* 58 */     FightInitData clone = (FightInitData)super.clone();
/* 59 */     int size_0 = this.lGroups.size();
/* 60 */     for (int index_0 = 0; index_0 < size_0; index_0++) {
/*    */       
/* 62 */       FightGroupData tmp_0 = this.lGroups.get(index_0);
/* 63 */       clone.lGroups.add(tmp_0.clone());
/*    */     } 
/* 65 */     int size_1 = this.rGroups.size();
/* 66 */     for (int index_1 = 0; index_1 < size_1; index_1++) {
/*    */       
/* 68 */       FightGroupData tmp_1 = this.rGroups.get(index_1);
/* 69 */       clone.rGroups.add(tmp_1.clone());
/*    */     } 
/* 71 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 76 */     this.lGroups.clear();
/* 77 */     this.rGroups.clear();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"lGroups\":" + this.lGroups.toString() + ",\"rGroups\":" + this.rGroups.toString() + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\FightInitData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */