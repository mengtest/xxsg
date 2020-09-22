/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ChannelShopBean
/*    */ {
/*    */   private int goodsId;
/*    */   
/*    */   public int getGoodsId() {
/* 10 */     return this.goodsId;
/*    */   }
/*    */   private ArrayList<RewardBean> reward; private int costType; private int costNum;
/*    */   private int vip;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */   private int goodTpye;
/*    */   private int sellTimes;
/*    */   
/*    */   public int getCostType() {
/* 22 */     return this.costType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostNum() {
/* 28 */     return this.costNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVip() {
/* 34 */     return this.vip;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getGoodTpye() {
/* 40 */     return this.goodTpye;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSellTimes() {
/* 46 */     return this.sellTimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "goodsId= " + this.goodsId + ", reward= " + this.reward + ", costType= " + this.costType + ", costNum= " + this.costNum + ", vip= " + this.vip + ", goodTpye= " + this.goodTpye + ", sellTimes= " + this.sellTimes;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ChannelShopBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */