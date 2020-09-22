/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class GoodsBean {
/*    */   private int goodsId;
/*    */   private int goodsType;
/*    */   
/*    */   public int getGoodsId() {
/* 10 */     return this.goodsId;
/*    */   }
/*    */   private int order; private ArrayList<RewardBean> reward; private int costType;
/*    */   private int costNum;
/*    */   
/*    */   public int getGoodsType() {
/* 16 */     return this.goodsType;
/*    */   }
/*    */   private ArrayList<BuyConditionBean> buyCondition;
/*    */   private int sellTimes;
/*    */   
/*    */   public int getOrder() {
/* 22 */     return this.order;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 28 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostType() {
/* 34 */     return this.costType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostNum() {
/* 40 */     return this.costNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<BuyConditionBean> getBuyCondition() {
/* 46 */     return this.buyCondition;
/*    */   }
/*    */   
/*    */   public class BuyConditionBean { private int type;
/*    */     private int value;
/*    */     
/*    */     public int getType() {
/* 53 */       return this.type;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getValue() {
/* 58 */       return this.value;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 63 */       String retVal = "type= " + this.type + ", value= " + this.value;
/* 64 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSellTimes() {
/* 71 */     return this.sellTimes;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     String retVal = "goodsId= " + this.goodsId + ", goodsType= " + this.goodsType + ", order= " + this.order + ", reward= " + this.reward + ", costType= " + this.costType + ", costNum= " + this.costNum + ", buyCondition= " + this.buyCondition + ", sellTimes= " + this.sellTimes;
/* 77 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\GoodsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */