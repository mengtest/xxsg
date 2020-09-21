/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ShopNormalBean {
/*     */   private int goodsId;
/*     */   private int goodsType;
/*     */   private int order;
/*     */   
/*     */   public int getGoodsId() {
/*  10 */     return this.goodsId;
/*     */   }
/*     */   private ArrayList<RewardBean> reward; private int costType; private int costNum; private int maxNum; private int costItem; private int itemNum; private ArrayList<BuyConditionBean> buyCondition;
/*     */   private int limitType;
/*     */   
/*     */   public int getGoodsType() {
/*  16 */     return this.goodsType;
/*     */   }
/*     */   private int sellTimes; private int stock; private int addStock;
/*     */   private int midStock;
/*     */   
/*     */   public int getOrder() {
/*  22 */     return this.order;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getReward() {
/*  28 */     return this.reward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostType() {
/*  34 */     return this.costType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostNum() {
/*  40 */     return this.costNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxNum() {
/*  46 */     return this.maxNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCostItem() {
/*  52 */     return this.costItem;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemNum() {
/*  58 */     return this.itemNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<BuyConditionBean> getBuyCondition() {
/*  64 */     return this.buyCondition;
/*     */   }
/*     */   
/*     */   public class BuyConditionBean { private int type;
/*     */     private int value;
/*     */     
/*     */     public int getType() {
/*  71 */       return this.type;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getValue() {
/*  76 */       return this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  81 */       String retVal = "type= " + this.type + ", value= " + this.value;
/*  82 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLimitType() {
/*  89 */     return this.limitType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSellTimes() {
/*  95 */     return this.sellTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStock() {
/* 101 */     return this.stock;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAddStock() {
/* 107 */     return this.addStock;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMidStock() {
/* 113 */     return this.midStock;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     String retVal = "goodsId= " + this.goodsId + ", goodsType= " + this.goodsType + ", order= " + this.order + ", reward= " + this.reward + ", costType= " + this.costType + ", costNum= " + this.costNum + ", maxNum= " + this.maxNum + ", costItem= " + this.costItem + ", itemNum= " + this.itemNum + ", buyCondition= " + this.buyCondition + ", limitType= " + this.limitType + ", sellTimes= " + this.sellTimes + ", stock= " + this.stock + ", addStock= " + this.addStock + ", midStock= " + this.midStock;
/* 119 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ShopNormalBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */