/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class VipAccessBean
/*    */ {
/*    */   private int vipLevel;
/*    */   
/*    */   public int getVipLevel() {
/* 10 */     return this.vipLevel;
/*    */   }
/*    */   private int recharge; private int total; private ArrayList<AccessTimesBean> accessTimes;
/*    */   private ArrayList<RewardBean> vipReward;
/*    */   
/*    */   public int getRecharge() {
/* 16 */     return this.recharge;
/*    */   }
/*    */   private int price;
/*    */   private ArrayList<RewardBean> dailyPackage;
/*    */   
/*    */   public int getTotal() {
/* 22 */     return this.total;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AccessTimesBean> getAccessTimes() {
/* 28 */     return this.accessTimes;
/*    */   }
/*    */   
/*    */   public class AccessTimesBean { private int type;
/*    */     private int value;
/*    */     
/*    */     public int getType() {
/* 35 */       return this.type;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getValue() {
/* 40 */       return this.value;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 45 */       String retVal = "type= " + this.type + ", value= " + this.value;
/* 46 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getVipReward() {
/* 53 */     return this.vipReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPrice() {
/* 59 */     return this.price;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getDailyPackage() {
/* 65 */     return this.dailyPackage;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "vipLevel= " + this.vipLevel + ", recharge= " + this.recharge + ", total= " + this.total + ", accessTimes= " + this.accessTimes + ", vipReward= " + this.vipReward + ", price= " + this.price + ", dailyPackage= " + this.dailyPackage;
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\VipAccessBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */