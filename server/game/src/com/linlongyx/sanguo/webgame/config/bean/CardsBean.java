/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class CardsBean {
/*    */   private int id;
/*    */   private int tag;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int quality; private int card; private int cardNumber;
/*    */   private ArrayList<RewardBean> color;
/*    */   
/*    */   public int getTag() {
/* 16 */     return this.tag;
/*    */   }
/*    */   private ArrayList<RewardBean> firstReward; private ArrayList<RewardBean> repeatReward;
/*    */   private int max;
/*    */   
/*    */   public int getQuality() {
/* 22 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCard() {
/* 28 */     return this.card;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCardNumber() {
/* 34 */     return this.cardNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getColor() {
/* 40 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getFirstReward() {
/* 46 */     return this.firstReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getRepeatReward() {
/* 52 */     return this.repeatReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMax() {
/* 58 */     return this.max;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "id= " + this.id + ", tag= " + this.tag + ", quality= " + this.quality + ", card= " + this.card + ", cardNumber= " + this.cardNumber + ", color= " + this.color + ", firstReward= " + this.firstReward + ", repeatReward= " + this.repeatReward + ", max= " + this.max;
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\CardsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */