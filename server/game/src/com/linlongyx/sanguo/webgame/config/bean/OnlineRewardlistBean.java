/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class OnlineRewardlistBean
/*    */ {
/*    */   private int time;
/*    */   
/*    */   public int getTime() {
/* 10 */     return this.time;
/*    */   }
/*    */   private ArrayList<RewardBean> onlineReward;
/*    */   private ArrayList<RewardBean> extraReward;
/*    */   
/*    */   public ArrayList<RewardBean> getOnlineReward() {
/* 16 */     return this.onlineReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getExtraReward() {
/* 22 */     return this.extraReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "time= " + this.time + ", onlineReward= " + this.onlineReward + ", extraReward= " + this.extraReward;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\OnlineRewardlistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */