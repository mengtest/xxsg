/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DestinyWarBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int anger; private ArrayList<RewardBean> maxReward;
/*    */   private int point;
/*    */   
/*    */   public int getAnger() {
/* 16 */     return this.anger;
/*    */   }
/*    */   private ArrayList<RewardBean> otherCost;
/*    */   private ArrayList<RewardBean> moneyCost;
/*    */   
/*    */   public ArrayList<RewardBean> getMaxReward() {
/* 22 */     return this.maxReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPoint() {
/* 28 */     return this.point;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getOtherCost() {
/* 34 */     return this.otherCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getMoneyCost() {
/* 40 */     return this.moneyCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", anger= " + this.anger + ", maxReward= " + this.maxReward + ", point= " + this.point + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DestinyWarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */