/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class VipWeekBean
/*    */ {
/*    */   private int packageType;
/*    */   
/*    */   public int getPackageType() {
/* 10 */     return this.packageType;
/*    */   }
/*    */   private int level; private ArrayList<RewardBean> reward; private int price;
/*    */   private int discount;
/*    */   
/*    */   public int getLevel() {
/* 16 */     return this.level;
/*    */   }
/*    */   private int limitType;
/*    */   private int selltimes;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 22 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPrice() {
/* 28 */     return this.price;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDiscount() {
/* 34 */     return this.discount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLimitType() {
/* 40 */     return this.limitType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSelltimes() {
/* 46 */     return this.selltimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "packageType= " + this.packageType + ", level= " + this.level + ", reward= " + this.reward + ", price= " + this.price + ", discount= " + this.discount + ", limitType= " + this.limitType + ", selltimes= " + this.selltimes;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\VipWeekBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */