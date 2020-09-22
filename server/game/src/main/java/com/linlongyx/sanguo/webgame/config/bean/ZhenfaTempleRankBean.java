/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ZhenfaTempleRankBean {
/*    */   private int id;
/*    */   private String Rank;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int target;
/*    */   
/*    */   public String getRank() {
/* 16 */     return this.Rank;
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
/* 33 */     String retVal = "id= " + this.id + ", Rank= " + this.Rank + ", target= " + this.target + ", rankReward= " + this.rankReward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZhenfaTempleRankBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */