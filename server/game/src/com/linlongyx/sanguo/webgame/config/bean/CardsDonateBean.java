/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CardsDonateBean {
/*    */   private int target;
/*    */   private ArrayList<RewardBean> donateReward;
/*    */   
/*    */   public int getTarget() {
/* 10 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getDonateReward() {
/* 16 */     return this.donateReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     String retVal = "target= " + this.target + ", donateReward= " + this.donateReward;
/* 22 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\CardsDonateBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */