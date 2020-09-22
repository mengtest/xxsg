/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ZodiacRewardBean {
/*    */   private int type;
/*    */   private Map<Integer, IdBean> id;
/*    */   
/*    */   public int getType() {
/* 11 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, IdBean> getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   
/*    */   public class IdBean
/*    */   {
/*    */     private ArrayList<Integer> condition;
/*    */     private int number;
/*    */     
/*    */     public ArrayList<Integer> getCondition() {
/* 26 */       return this.condition;
/*    */     }
/*    */     private ArrayList<RewardBean> reward1;
/*    */     private ArrayList<RewardBean> reward2;
/*    */     
/*    */     public int getNumber() {
/* 32 */       return this.number;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<RewardBean> getReward1() {
/* 38 */       return this.reward1;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<RewardBean> getReward2() {
/* 44 */       return this.reward2;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 49 */       String retVal = "condition= " + this.condition + ", number= " + this.number + ", reward1= " + this.reward1 + ", reward2= " + this.reward2;
/* 50 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "type= " + this.type + ", id= " + this.id;
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZodiacRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */