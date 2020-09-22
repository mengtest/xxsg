/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RuneLevelBean {
/*    */   private int level;
/*    */   private int attr;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> upCost;
/*    */   
/*    */   public int getAttr() {
/* 16 */     return this.attr;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> sellReward;
/*    */   
/*    */   public ArrayList<RewardBean> getUpCost() {
/* 22 */     return this.upCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getSellReward() {
/* 28 */     return this.sellReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "level= " + this.level + ", attr= " + this.attr + ", upCost= " + this.upCost + ", sellReward= " + this.sellReward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RuneLevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */