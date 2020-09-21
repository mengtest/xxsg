/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ZhenfaTempleWorldBean {
/*    */   private int worldLevel;
/*    */   private ArrayList<Integer> integralReward;
/*    */   
/*    */   public int getWorldLevel() {
/* 10 */     return this.worldLevel;
/*    */   }
/*    */   
/*    */   private ArrayList<Integer> rankReward;
/*    */   
/*    */   public ArrayList<Integer> getIntegralReward() {
/* 16 */     return this.integralReward;
/*    */   }
/*    */   
/*    */   private int entryScore;
/*    */   
/*    */   public ArrayList<Integer> getRankReward() {
/* 22 */     return this.rankReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEntryScore() {
/* 28 */     return this.entryScore;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     String retVal = "worldLevel= " + this.worldLevel + ", integralReward= " + this.integralReward + ", rankReward= " + this.rankReward + ", entryScore= " + this.entryScore;
/* 34 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZhenfaTempleWorldBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */