/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RankingKuafulistBean {
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
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public String getTarget() {
/* 22 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 28 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "id= " + this.id + ", reachConditions= " + this.reachConditions + ", target= " + this.target + ", reward= " + this.reward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RankingKuafulistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */