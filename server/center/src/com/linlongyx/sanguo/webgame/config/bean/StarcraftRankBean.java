/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class StarcraftRankBean {
/*    */   private int id;
/*    */   private String campRanking;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int target;
/*    */   
/*    */   public String getCampRanking() {
/* 16 */     return this.campRanking;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> rankReward;
/*    */   
/*    */   public int getTarget() {
/* 22 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getRankReward() {
/* 28 */     return this.rankReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", campRanking= " + this.campRanking + ", target= " + this.target + ", rankReward= " + this.rankReward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\StarcraftRankBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */