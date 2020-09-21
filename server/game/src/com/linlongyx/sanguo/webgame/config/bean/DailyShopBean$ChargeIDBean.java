/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChargeIDBean
/*    */ {
/*    */   private int worldLevel;
/*    */   private ArrayList<RewardBean> goods;
/*    */   
/*    */   public int getWorldLevel() {
/* 26 */     return this.worldLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getGoods() {
/* 32 */     return this.goods;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     String retVal = "worldLevel= " + this.worldLevel + ", goods= " + this.goods;
/* 38 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DailyShopBean$ChargeIDBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */