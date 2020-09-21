/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GeneralChapterBean {
/*    */   private int chapter;
/*    */   private Map<Integer, DifficultyBean> difficulty;
/*    */   
/*    */   public int getChapter() {
/* 11 */     return this.chapter;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, DifficultyBean> getDifficulty() {
/* 17 */     return this.difficulty;
/*    */   }
/*    */   
/*    */   public class DifficultyBean {
/*    */     private ArrayList<RewardBean> fReward;
/*    */     private ArrayList<RewardBean> eReward;
/*    */     private ArrayList<RewardBean> sReward;
/*    */     
/*    */     public ArrayList<RewardBean> getFReward() {
/* 26 */       return this.fReward;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<RewardBean> getEReward() {
/* 32 */       return this.eReward;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<RewardBean> getSReward() {
/* 38 */       return this.sReward;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 43 */       String retVal = "fReward= " + this.fReward + ", eReward= " + this.eReward + ", sReward= " + this.sReward;
/* 44 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "chapter= " + this.chapter + ", difficulty= " + this.difficulty;
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\GeneralChapterBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */