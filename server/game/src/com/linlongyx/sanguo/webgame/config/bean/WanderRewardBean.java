/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class WanderRewardBean {
/*    */   private int id;
/*    */   private int type;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int rmb;
/*    */   
/*    */   public int getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> cost;
/*    */   
/*    */   public int getRmb() {
/* 22 */     return this.rmb;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 28 */     return this.cost;
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
/* 39 */     String retVal = "id= " + this.id + ", type= " + this.type + ", rmb= " + this.rmb + ", cost= " + this.cost + ", reward= " + this.reward;
/* 40 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\WanderRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */