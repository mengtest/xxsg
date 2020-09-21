/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TurnplateWarehouseBean {
/*    */   private int warehouseId;
/*    */   private Map<Integer, IdBean> id;
/*    */   
/*    */   public int getWarehouseId() {
/* 11 */     return this.warehouseId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, IdBean> getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   
/*    */   public class IdBean { private ArrayList<RewardBean> raffleReward;
/*    */     private String desc;
/*    */     private int rafflePer;
/*    */     private int rewardPro;
/*    */     
/*    */     public ArrayList<RewardBean> getRaffleReward() {
/* 26 */       return this.raffleReward;
/*    */     }
/*    */     
/*    */     private int sort;
/*    */     
/*    */     public String getDesc() {
/* 32 */       return this.desc;
/*    */     }
/*    */     private int isShow;
/*    */     private int isRare;
/*    */     
/*    */     public int getRafflePer() {
/* 38 */       return this.rafflePer;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getRewardPro() {
/* 44 */       return this.rewardPro;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getSort() {
/* 50 */       return this.sort;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getIsShow() {
/* 56 */       return this.isShow;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getIsRare() {
/* 62 */       return this.isRare;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 67 */       String retVal = "raffleReward= " + this.raffleReward + ", desc= " + this.desc + ", rafflePer= " + this.rafflePer + ", rewardPro= " + this.rewardPro + ", sort= " + this.sort + ", isShow= " + this.isShow + ", isRare= " + this.isRare;
/* 68 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 74 */     String retVal = "warehouseId= " + this.warehouseId + ", id= " + this.id;
/* 75 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TurnplateWarehouseBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */