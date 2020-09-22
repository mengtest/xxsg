/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class OneGiftboxBean {
/*    */   private int rmb;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getRmb() {
/* 10 */     return this.rmb;
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
/* 21 */     String retVal = "rmb= " + this.rmb + ", reward= " + this.reward;
/* 22 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\OneGiftboxBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */