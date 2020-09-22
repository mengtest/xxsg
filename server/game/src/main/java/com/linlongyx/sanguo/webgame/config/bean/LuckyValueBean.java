/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class LuckyValueBean
/*    */ {
/*    */   private int lucky;
/*    */   
/*    */   public int getLucky() {
/*  8 */     return this.lucky;
/*    */   }
/*    */   private int showReward;
/*    */   private int petReward;
/*    */   
/*    */   public int getShowReward() {
/* 14 */     return this.showReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPetReward() {
/* 20 */     return this.petReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     String retVal = "lucky= " + this.lucky + ", showReward= " + this.showReward + ", petReward= " + this.petReward;
/* 26 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LuckyValueBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */