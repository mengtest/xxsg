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
/*    */ public class IdBean
/*    */ {
/*    */   private ArrayList<RewardBean> raffleReward;
/*    */   private int rafflePer;
/*    */   private int pro;
/*    */   private int isShow;
/*    */   private int isFresh;
/*    */   
/*    */   public ArrayList<RewardBean> getRaffleReward() {
/* 26 */     return this.raffleReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRafflePer() {
/* 32 */     return this.rafflePer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPro() {
/* 38 */     return this.pro;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIsShow() {
/* 44 */     return this.isShow;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIsFresh() {
/* 50 */     return this.isFresh;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "raffleReward= " + this.raffleReward + ", rafflePer= " + this.rafflePer + ", pro= " + this.pro + ", isShow= " + this.isShow + ", isFresh= " + this.isFresh;
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TalismanBoxlibBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */