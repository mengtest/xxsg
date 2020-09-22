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
/*    */ public class DifficultyBean
/*    */ {
/*    */   private ArrayList<RewardBean> fReward;
/*    */   private ArrayList<RewardBean> eReward;
/*    */   private ArrayList<RewardBean> sReward;
/*    */   
/*    */   public ArrayList<RewardBean> getFReward() {
/* 26 */     return this.fReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getEReward() {
/* 32 */     return this.eReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getSReward() {
/* 38 */     return this.sReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "fReward= " + this.fReward + ", eReward= " + this.eReward + ", sReward= " + this.sReward;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\GeneralChapterBean$DifficultyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */