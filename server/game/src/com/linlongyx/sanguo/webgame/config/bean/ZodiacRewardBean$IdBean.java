/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IdBean
/*    */ {
/*    */   private ArrayList<Integer> condition;
/*    */   private int number;
/*    */   private ArrayList<RewardBean> reward1;
/*    */   private ArrayList<RewardBean> reward2;
/*    */   
/*    */   public ArrayList<Integer> getCondition() {
/* 26 */     return this.condition;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumber() {
/* 32 */     return this.number;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward1() {
/* 38 */     return this.reward1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward2() {
/* 44 */     return this.reward2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "condition= " + this.condition + ", number= " + this.number + ", reward1= " + this.reward1 + ", reward2= " + this.reward2;
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZodiacRewardBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */