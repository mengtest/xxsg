/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CrossRankBean {
/*    */   private int id;
/*    */   private String desc;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<RewardBean> rankReward; private int score; private ArrayList<RewardBean> reward;
/*    */   private int win;
/*    */   
/*    */   public String getDesc() {
/* 16 */     return this.desc;
/*    */   }
/*    */   private int lose; private ArrayList<RewardBean> otherCost;
/*    */   private ArrayList<RewardBean> moneyCost;
/*    */   
/*    */   public ArrayList<RewardBean> getRankReward() {
/* 22 */     return this.rankReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getScore() {
/* 28 */     return this.score;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 34 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWin() {
/* 40 */     return this.win;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLose() {
/* 46 */     return this.lose;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getOtherCost() {
/* 52 */     return this.otherCost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getMoneyCost() {
/* 58 */     return this.moneyCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "id= " + this.id + ", desc= " + this.desc + ", rankReward= " + this.rankReward + ", score= " + this.score + ", reward= " + this.reward + ", win= " + this.win + ", lose= " + this.lose + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost;
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\CrossRankBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */