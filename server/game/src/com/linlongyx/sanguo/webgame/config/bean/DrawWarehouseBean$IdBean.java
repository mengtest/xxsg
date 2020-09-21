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
/*    */ public class IdBean
/*    */ {
/*    */   private ArrayList<RewardBean> raffleReward;
/*    */   private String desc;
/*    */   private int rafflePer;
/*    */   private int isShow;
/*    */   
/*    */   public ArrayList<RewardBean> getRaffleReward() {
/* 26 */     return this.raffleReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDesc() {
/* 32 */     return this.desc;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRafflePer() {
/* 38 */     return this.rafflePer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIsShow() {
/* 44 */     return this.isShow;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "raffleReward= " + this.raffleReward + ", desc= " + this.desc + ", rafflePer= " + this.rafflePer + ", isShow= " + this.isShow;
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DrawWarehouseBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */