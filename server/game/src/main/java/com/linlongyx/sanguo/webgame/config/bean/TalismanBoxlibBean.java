/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TalismanBoxlibBean {
/*    */   private int libID;
/*    */   private Map<Integer, IdBean> id;
/*    */   
/*    */   public int getLibID() {
/* 11 */     return this.libID;
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
/*    */     private ArrayList<RewardBean> raffleReward;
/*    */     private int rafflePer;
/*    */     
/*    */     public ArrayList<RewardBean> getRaffleReward() {
/* 26 */       return this.raffleReward;
/*    */     }
/*    */     private int pro; private int isShow;
/*    */     private int isFresh;
/*    */     
/*    */     public int getRafflePer() {
/* 32 */       return this.rafflePer;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getPro() {
/* 38 */       return this.pro;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getIsShow() {
/* 44 */       return this.isShow;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getIsFresh() {
/* 50 */       return this.isFresh;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 55 */       String retVal = "raffleReward= " + this.raffleReward + ", rafflePer= " + this.rafflePer + ", pro= " + this.pro + ", isShow= " + this.isShow + ", isFresh= " + this.isFresh;
/* 56 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "libID= " + this.libID + ", id= " + this.id;
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TalismanBoxlibBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */