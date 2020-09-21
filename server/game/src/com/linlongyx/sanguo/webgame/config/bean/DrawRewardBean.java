/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DrawRewardBean {
/*    */   private int num;
/*    */   private ArrayList<RewardBean> drawAward;
/*    */   
/*    */   public int getNum() {
/* 10 */     return this.num;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getDrawAward() {
/* 16 */     return this.drawAward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     String retVal = "num= " + this.num + ", drawAward= " + this.drawAward;
/* 22 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DrawRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */