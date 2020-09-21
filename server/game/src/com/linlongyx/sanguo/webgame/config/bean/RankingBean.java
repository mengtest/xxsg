/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RankingBean {
/*    */   private int rankId;
/*    */   private String rankName;
/*    */   
/*    */   public int getRankId() {
/* 10 */     return this.rankId;
/*    */   }
/*    */   
/*    */   private int maxRank;
/*    */   
/*    */   public String getRankName() {
/* 16 */     return this.rankName;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> worshipReward;
/*    */   
/*    */   public int getMaxRank() {
/* 22 */     return this.maxRank;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> levelReward;
/*    */   
/*    */   public ArrayList<RewardBean> getWorshipReward() {
/* 28 */     return this.worshipReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getLevelReward() {
/* 34 */     return this.levelReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "rankId= " + this.rankId + ", rankName= " + this.rankName + ", maxRank= " + this.maxRank + ", worshipReward= " + this.worshipReward + ", levelReward= " + this.levelReward;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RankingBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */