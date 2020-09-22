/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class TreasurePurifyBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/*  8 */     return this.level;
/*    */   }
/*    */   private int costItem; private int costNum; private int totalCost;
/*    */   private int extraCost;
/*    */   
/*    */   public int getCostItem() {
/* 14 */     return this.costItem;
/*    */   }
/*    */   private int extraNum;
/*    */   private int totalTreasure;
/*    */   
/*    */   public int getCostNum() {
/* 20 */     return this.costNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTotalCost() {
/* 26 */     return this.totalCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExtraCost() {
/* 32 */     return this.extraCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExtraNum() {
/* 38 */     return this.extraNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTotalTreasure() {
/* 44 */     return this.totalTreasure;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "level= " + this.level + ", costItem= " + this.costItem + ", costNum= " + this.costNum + ", totalCost= " + this.totalCost + ", extraCost= " + this.extraCost + ", extraNum= " + this.extraNum + ", totalTreasure= " + this.totalTreasure;
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TreasurePurifyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */