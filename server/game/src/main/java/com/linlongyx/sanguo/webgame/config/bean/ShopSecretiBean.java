/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ShopSecretiBean {
/*     */   private int level;
/*     */   private int openLevel;
/*     */   private Map<Integer, GoodsIdBean> goodsId;
/*     */   
/*     */   public int getLevel() {
/*  11 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpenLevel() {
/*  17 */     return this.openLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, GoodsIdBean> getGoodsId() {
/*  23 */     return this.goodsId;
/*     */   }
/*     */   
/*     */   public class GoodsIdBean {
/*     */     private ArrayList<RewardBean> reward;
/*     */     private int costType;
/*     */     private int costNum;
/*     */     
/*     */     public ArrayList<RewardBean> getReward() {
/*  32 */       return this.reward;
/*     */     }
/*     */     private int maxNum; private int costItem; private int itemNum; private ArrayList<BuyConditionBean> buyCondition; private int limitType;
/*     */     private int sellTimes;
/*     */     
/*     */     public int getCostType() {
/*  38 */       return this.costType;
/*     */     }
/*     */     private int stock; private int addStock;
/*     */     private int midStock;
/*     */     
/*     */     public int getCostNum() {
/*  44 */       return this.costNum;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getMaxNum() {
/*  50 */       return this.maxNum;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCostItem() {
/*  56 */       return this.costItem;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getItemNum() {
/*  62 */       return this.itemNum;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<BuyConditionBean> getBuyCondition() {
/*  68 */       return this.buyCondition;
/*     */     }
/*     */     
/*     */     public class BuyConditionBean { private int type;
/*     */       private int value;
/*     */       
/*     */       public int getType() {
/*  75 */         return this.type;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getValue() {
/*  80 */         return this.value;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/*  85 */         String retVal = "type= " + this.type + ", value= " + this.value;
/*  86 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLimitType() {
/*  93 */       return this.limitType;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getSellTimes() {
/*  99 */       return this.sellTimes;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getStock() {
/* 105 */       return this.stock;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getAddStock() {
/* 111 */       return this.addStock;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getMidStock() {
/* 117 */       return this.midStock;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 122 */       String retVal = "reward= " + this.reward + ", costType= " + this.costType + ", costNum= " + this.costNum + ", maxNum= " + this.maxNum + ", costItem= " + this.costItem + ", itemNum= " + this.itemNum + ", buyCondition= " + this.buyCondition + ", limitType= " + this.limitType + ", sellTimes= " + this.sellTimes + ", stock= " + this.stock + ", addStock= " + this.addStock + ", midStock= " + this.midStock;
/* 123 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     String retVal = "level= " + this.level + ", openLevel= " + this.openLevel + ", goodsId= " + this.goodsId;
/* 130 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ShopSecretiBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */