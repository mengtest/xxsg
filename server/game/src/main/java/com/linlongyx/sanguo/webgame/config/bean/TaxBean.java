/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TaxBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int gold;
/*    */   private ArrayList<Integer> cost;
/*    */   
/*    */   public int getGold() {
/* 16 */     return this.gold;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getCost() {
/* 22 */     return this.cost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", gold= " + this.gold + ", cost= " + this.cost;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TaxBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */