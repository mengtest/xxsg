/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RankingActivitylistBean {
/*    */   private int id;
/*    */   private String reachConditions;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private String target;
/*    */   
/*    */   public String getReachConditions() {
/* 16 */     return this.reachConditions;
/*    */   }
/*    */   
/*    */   private int entryConditions;
/*    */   
/*    */   public String getTarget() {
/* 22 */     return this.target;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getEntryConditions() {
/* 28 */     return this.entryConditions;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 34 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 39 */     String retVal = "id= " + this.id + ", reachConditions= " + this.reachConditions + ", target= " + this.target + ", entryConditions= " + this.entryConditions + ", reward= " + this.reward;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RankingActivitylistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */