/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ShopCrystalBean {
/*    */   private int goodsId;
/*    */   private int lv;
/*    */   
/*    */   public int getGoodsId() {
/* 10 */     return this.goodsId;
/*    */   }
/*    */   private ArrayList<RewardBean> reward; private int costType; private int costNum;
/*    */   private int vip;
/*    */   
/*    */   public int getLv() {
/* 16 */     return this.lv;
/*    */   }
/*    */   private int sellTimes; private int prob;
/*    */   private int recommend;
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
/*    */   public int getVip() {
/* 40 */     return this.vip;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSellTimes() {
/* 46 */     return this.sellTimes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getProb() {
/* 52 */     return this.prob;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRecommend() {
/* 58 */     return this.recommend;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "goodsId= " + this.goodsId + ", lv= " + this.lv + ", reward= " + this.reward + ", costType= " + this.costType + ", costNum= " + this.costNum + ", vip= " + this.vip + ", sellTimes= " + this.sellTimes + ", prob= " + this.prob + ", recommend= " + this.recommend;
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ShopCrystalBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */