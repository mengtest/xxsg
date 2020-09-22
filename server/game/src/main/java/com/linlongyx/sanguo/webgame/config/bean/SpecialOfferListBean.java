/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SpecialOfferListBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int type; private int chargeId;
/*    */   private int number;
/*    */   
/*    */   public int getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   private ArrayList<RewardBean> reward;
/*    */   private ArrayList<RewardBean> cost;
/*    */   
/*    */   public int getChargeId() {
/* 22 */     return this.chargeId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNumber() {
/* 28 */     return this.number;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 34 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 40 */     return this.cost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", type= " + this.type + ", chargeId= " + this.chargeId + ", number= " + this.number + ", reward= " + this.reward + ", cost= " + this.cost;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SpecialOfferListBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */