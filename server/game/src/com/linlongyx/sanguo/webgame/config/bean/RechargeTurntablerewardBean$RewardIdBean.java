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
/*    */ 
/*    */ public class RewardIdBean
/*    */ {
/*    */   private ArrayList<RewardBean> reward;
/*    */   private int probability;
/*    */   private int notice;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 26 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getProbability() {
/* 32 */     return this.probability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNotice() {
/* 38 */     return this.notice;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "reward= " + this.reward + ", probability= " + this.probability + ", notice= " + this.notice;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RechargeTurntablerewardBean$RewardIdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */