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
/*    */ public class IdBean
/*    */ {
/*    */   private ArrayList<RewardBean> raffleReward;
/*    */   private String desc;
/*    */   private int rafflePer;
/*    */   private int rewardPro;
/*    */   private int sort;
/*    */   private int isShow;
/*    */   private int isRare;
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
/*    */   public int getRewardPro() {
/* 44 */     return this.rewardPro;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSort() {
/* 50 */     return this.sort;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIsShow() {
/* 56 */     return this.isShow;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getIsRare() {
/* 62 */     return this.isRare;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     String retVal = "raffleReward= " + this.raffleReward + ", desc= " + this.desc + ", rafflePer= " + this.rafflePer + ", rewardPro= " + this.rewardPro + ", sort= " + this.sort + ", isShow= " + this.isShow + ", isRare= " + this.isRare;
/* 68 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TurnplateWarehouseBean$IdBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */