/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LuckyTurntableBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<RewardBean> reward; private int probability_ten; private int doubleProbability_ten;
/*    */   private int probability;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */   private int doubleProbability;
/*    */   private int notice;
/*    */   
/*    */   public int getProbability_ten() {
/* 22 */     return this.probability_ten;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDoubleProbability_ten() {
/* 28 */     return this.doubleProbability_ten;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getProbability() {
/* 34 */     return this.probability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDoubleProbability() {
/* 40 */     return this.doubleProbability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNotice() {
/* 46 */     return this.notice;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "id= " + this.id + ", reward= " + this.reward + ", probability_ten= " + this.probability_ten + ", doubleProbability_ten= " + this.doubleProbability_ten + ", probability= " + this.probability + ", doubleProbability= " + this.doubleProbability + ", notice= " + this.notice;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LuckyTurntableBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */