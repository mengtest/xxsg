/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TurnplateShopBean {
/*    */   private int goodsId;
/*    */   private int lib;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getGoodsId() {
/* 10 */     return this.goodsId;
/*    */   }
/*    */   
/*    */   private int cost;
/*    */   
/*    */   public int getLib() {
/* 16 */     return this.lib;
/*    */   }
/*    */   private int activityType;
/*    */   private int exchangeTimes;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 22 */     return this.reward;
/*    */   }
/*    */   
/*    */   public class RewardBean {
/*    */     private int type;
/*    */     
/*    */     public int getType() {
/* 29 */       return this.type;
/*    */     }
/*    */     private int id; private int num;
/*    */     
/*    */     public int getId() {
/* 34 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 39 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 44 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/* 45 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost() {
/* 52 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getActivityType() {
/* 58 */     return this.activityType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExchangeTimes() {
/* 64 */     return this.exchangeTimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "goodsId= " + this.goodsId + ", lib= " + this.lib + ", reward= " + this.reward + ", cost= " + this.cost + ", activityType= " + this.activityType + ", exchangeTimes= " + this.exchangeTimes;
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TurnplateShopBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */