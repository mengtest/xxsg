/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class TreasureStrengthBean {
/*    */   private int level;
/*    */   private int itemId;
/*    */   
/*    */   public int getLevel() {
/*  8 */     return this.level;
/*    */   }
/*    */   
/*    */   private int costStone;
/*    */   
/*    */   public int getItemId() {
/* 14 */     return this.itemId;
/*    */   }
/*    */   
/*    */   private int totalCost;
/*    */   
/*    */   public int getCostStone() {
/* 20 */     return this.costStone;
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
/* 31 */     String retVal = "level= " + this.level + ", itemId= " + this.itemId + ", costStone= " + this.costStone + ", totalCost= " + this.totalCost;
/* 32 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TreasureStrengthBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */