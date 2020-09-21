/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DrawWarehouseBean {
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
/*    */   public class IdBean
/*    */   {
/*    */     private ArrayList<RewardBean> raffleReward;
/*    */     private String desc;
/*    */     
/*    */     public ArrayList<RewardBean> getRaffleReward() {
/* 26 */       return this.raffleReward;
/*    */     }
/*    */     private int rafflePer;
/*    */     private int isShow;
/*    */     
/*    */     public String getDesc() {
/* 32 */       return this.desc;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getRafflePer() {
/* 38 */       return this.rafflePer;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getIsShow() {
/* 44 */       return this.isShow;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 49 */       String retVal = "raffleReward= " + this.raffleReward + ", desc= " + this.desc + ", rafflePer= " + this.rafflePer + ", isShow= " + this.isShow;
/* 50 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String retVal = "warehouseId= " + this.warehouseId + ", id= " + this.id;
/* 57 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DrawWarehouseBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */