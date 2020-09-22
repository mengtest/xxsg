/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class WushuangBoxBean {
/*    */   private int costStar;
/*    */   private ArrayList<RewardBean> starReward;
/*    */   
/*    */   public int getCostStar() {
/* 10 */     return this.costStar;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getStarReward() {
/* 16 */     return this.starReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     String retVal = "costStar= " + this.costStar + ", starReward= " + this.starReward;
/* 22 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\WushuangBoxBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */