/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class EquipZhuhunBean {
/*    */   private int level;
/*    */   private int costItem;
/*    */   
/*    */   public int getLevel() {
/*  8 */     return this.level;
/*    */   }
/*    */   
/*    */   private int costNum;
/*    */   
/*    */   public int getCostItem() {
/* 14 */     return this.costItem;
/*    */   }
/*    */   
/*    */   private int totalCost;
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
/*    */   public String toString() {
/* 31 */     String retVal = "level= " + this.level + ", costItem= " + this.costItem + ", costNum= " + this.costNum + ", totalCost= " + this.totalCost;
/* 32 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipZhuhunBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */