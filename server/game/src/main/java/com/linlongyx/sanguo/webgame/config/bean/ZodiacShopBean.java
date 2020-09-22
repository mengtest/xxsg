/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ZodiacShopBean {
/*    */   private int id;
/*    */   private int order;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<RewardBean> reward; private int costType; private int costNum; private int maxNum;
/*    */   private int costItem;
/*    */   
/*    */   public int getOrder() {
/* 16 */     return this.order;
/*    */   }
/*    */   private int itemNum; private int limitType;
/*    */   private int sellTimes;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 22 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostType() {
/* 28 */     return this.costType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostNum() {
/* 34 */     return this.costNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxNum() {
/* 40 */     return this.maxNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostItem() {
/* 46 */     return this.costItem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getItemNum() {
/* 52 */     return this.itemNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLimitType() {
/* 58 */     return this.limitType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSellTimes() {
/* 64 */     return this.sellTimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "id= " + this.id + ", order= " + this.order + ", reward= " + this.reward + ", costType= " + this.costType + ", costNum= " + this.costNum + ", maxNum= " + this.maxNum + ", costItem= " + this.costItem + ", itemNum= " + this.itemNum + ", limitType= " + this.limitType + ", sellTimes= " + this.sellTimes;
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ZodiacShopBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */