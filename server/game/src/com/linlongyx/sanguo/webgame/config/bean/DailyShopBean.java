/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DailyShopBean {
/*    */   private int id;
/*    */   private Map<Integer, ChargeIDBean> chargeID;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, ChargeIDBean> getChargeID() {
/* 17 */     return this.chargeID;
/*    */   }
/*    */   
/*    */   public class ChargeIDBean
/*    */   {
/*    */     private int worldLevel;
/*    */     private ArrayList<RewardBean> goods;
/*    */     
/*    */     public int getWorldLevel() {
/* 26 */       return this.worldLevel;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<RewardBean> getGoods() {
/* 32 */       return this.goods;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 37 */       String retVal = "worldLevel= " + this.worldLevel + ", goods= " + this.goods;
/* 38 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "id= " + this.id + ", chargeID= " + this.chargeID;
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DailyShopBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */