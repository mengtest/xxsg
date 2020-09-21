/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TurnplateRaffleBean {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  9 */     return this.id;
/*    */   }
/*    */   
/*    */   private Map<Integer, TimesBean> times;
/*    */   
/*    */   public Map<Integer, TimesBean> getTimes() {
/* 15 */     return this.times;
/*    */   }
/*    */   
/*    */   public class TimesBean {
/*    */     private int warehouseId;
/*    */     private int rareId;
/*    */     private int rarePer;
/*    */     
/*    */     public int getWarehouseId() {
/* 24 */       return this.warehouseId;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getRareId() {
/* 30 */       return this.rareId;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getRarePer() {
/* 36 */       return this.rarePer;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 41 */       String retVal = "warehouseId= " + this.warehouseId + ", rareId= " + this.rareId + ", rarePer= " + this.rarePer;
/* 42 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "id= " + this.id + ", times= " + this.times;
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TurnplateRaffleBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */