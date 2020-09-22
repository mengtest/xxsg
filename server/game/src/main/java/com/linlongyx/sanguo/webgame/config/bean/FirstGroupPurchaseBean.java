/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FirstGroupPurchaseBean {
/*    */   private int id;
/*    */   private int number;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   
/*    */   private int condition;
/*    */   
/*    */   public int getNumber() {
/* 16 */     return this.number;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getCondition() {
/* 22 */     return this.condition;
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
/* 33 */     String retVal = "id= " + this.id + ", number= " + this.number + ", condition= " + this.condition + ", reward= " + this.reward;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FirstGroupPurchaseBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */