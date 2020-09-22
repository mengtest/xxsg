/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class EquipStrengthBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/*  8 */     return this.level;
/*    */   }
/*    */   private int coin;
/*    */   private int totalCost;
/*    */   
/*    */   public int getCoin() {
/* 14 */     return this.coin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTotalCost() {
/* 20 */     return this.totalCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     String retVal = "level= " + this.level + ", coin= " + this.coin + ", totalCost= " + this.totalCost;
/* 26 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipStrengthBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */