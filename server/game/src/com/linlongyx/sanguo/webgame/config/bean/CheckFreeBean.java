/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CheckFreeBean {
/*    */   private int day;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getDay() {
/* 10 */     return this.day;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     String retVal = "day= " + this.day + ", reward= " + this.reward;
/* 22 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\CheckFreeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */