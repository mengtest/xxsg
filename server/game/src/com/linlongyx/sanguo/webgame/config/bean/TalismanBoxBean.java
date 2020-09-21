/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TalismanBoxBean {
/*    */   private int id;
/*    */   private int basicLib;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int trumpLib; private ArrayList<TrumpNumBean> trumpNum; private int cost; private int costItem; private ArrayList<Integer> fresh;
/*    */   private int maxNum;
/*    */   
/*    */   public int getBasicLib() {
/* 16 */     return this.basicLib;
/*    */   }
/*    */   private int hitNum; private ArrayList<RewardBean> currency;
/*    */   private ArrayList<RewardBean> tenCurrency;
/*    */   
/*    */   public int getTrumpLib() {
/* 22 */     return this.trumpLib;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<TrumpNumBean> getTrumpNum() {
/* 28 */     return this.trumpNum;
/*    */   }
/*    */   
/*    */   public class TrumpNumBean { private int num;
/*    */     private int pro;
/*    */     
/*    */     public int getNum() {
/* 35 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getPro() {
/* 40 */       return this.pro;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 45 */       String retVal = "num= " + this.num + ", pro= " + this.pro;
/* 46 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost() {
/* 53 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCostItem() {
/* 59 */     return this.costItem;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getFresh() {
/* 65 */     return this.fresh;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxNum() {
/* 71 */     return this.maxNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHitNum() {
/* 77 */     return this.hitNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCurrency() {
/* 83 */     return this.currency;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getTenCurrency() {
/* 89 */     return this.tenCurrency;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     String retVal = "id= " + this.id + ", basicLib= " + this.basicLib + ", trumpLib= " + this.trumpLib + ", trumpNum= " + this.trumpNum + ", cost= " + this.cost + ", costItem= " + this.costItem + ", fresh= " + this.fresh + ", maxNum= " + this.maxNum + ", hitNum= " + this.hitNum + ", currency= " + this.currency + ", tenCurrency= " + this.tenCurrency;
/* 95 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TalismanBoxBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */