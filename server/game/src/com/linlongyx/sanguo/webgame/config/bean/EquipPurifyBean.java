/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EquipPurifyBean {
/*    */   private int level;
/*    */   private int costItem;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   
/*    */   private int costNum;
/*    */   
/*    */   public int getCostItem() {
/* 16 */     return this.costItem;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> totalCost;
/*    */   
/*    */   public int getCostNum() {
/* 22 */     return this.costNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getTotalCost() {
/* 28 */     return this.totalCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "level= " + this.level + ", costItem= " + this.costItem + ", costNum= " + this.costNum + ", totalCost= " + this.totalCost;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipPurifyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */