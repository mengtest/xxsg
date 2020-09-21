/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ArenaRuleBean {
/*    */   private int id;
/*    */   private int lowId;
/*    */   private int highId;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int matchHigh; private int matchLow; private int lower; private ArrayList<Integer> rebotFighter; private ArrayList<RewardBean> dailyReward;
/*    */   private ArrayList<RewardBean> breakReward;
/*    */   
/*    */   public int getLowId() {
/* 16 */     return this.lowId;
/*    */   }
/*    */   private ArrayList<RewardBean> winReward; private ArrayList<RewardBean> otherCost;
/*    */   private ArrayList<RewardBean> moneyCost;
/*    */   
/*    */   public int getHighId() {
/* 22 */     return this.highId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMatchHigh() {
/* 28 */     return this.matchHigh;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMatchLow() {
/* 34 */     return this.matchLow;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLower() {
/* 40 */     return this.lower;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getRebotFighter() {
/* 46 */     return this.rebotFighter;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getDailyReward() {
/* 52 */     return this.dailyReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getBreakReward() {
/* 58 */     return this.breakReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getWinReward() {
/* 64 */     return this.winReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getOtherCost() {
/* 70 */     return this.otherCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getMoneyCost() {
/* 76 */     return this.moneyCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "id= " + this.id + ", lowId= " + this.lowId + ", highId= " + this.highId + ", matchHigh= " + this.matchHigh + ", matchLow= " + this.matchLow + ", lower= " + this.lower + ", rebotFighter= " + this.rebotFighter + ", dailyReward= " + this.dailyReward + ", breakReward= " + this.breakReward + ", winReward= " + this.winReward + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost;
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ArenaRuleBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */