/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FirstRechargeBean {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  9 */     return this.id;
/*    */   }
/*    */   private int hefu;
/*    */   private int money;
/*    */   
/*    */   public int getHefu() {
/* 15 */     return this.hefu;
/*    */   }
/*    */   private ArrayList<RewardBean> rechargeReward;
/*    */   private int connectortype;
/*    */   
/*    */   public int getMoney() {
/* 21 */     return this.money;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getRechargeReward() {
/* 27 */     return this.rechargeReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getConnectortype() {
/* 33 */     return this.connectortype;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     String retVal = "id= " + this.id + ", hefu= " + this.hefu + ", money= " + this.money + ", rechargeReward= " + this.rechargeReward + ", connectortype= " + this.connectortype;
/* 39 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FirstRechargeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */