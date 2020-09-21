/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EquipStoneBean {
/*    */   private int costItem;
/*    */   private int itemId;
/*    */   
/*    */   public int getCostItem() {
/* 10 */     return this.costItem;
/*    */   }
/*    */   
/*    */   private int costNum;
/*    */   
/*    */   public int getItemId() {
/* 16 */     return this.itemId;
/*    */   }
/*    */   
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public int getCostNum() {
/* 22 */     return this.costNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 28 */     return this.attr;
/*    */   }
/*    */   
/*    */   public class AttrBean {
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 35 */       return this.id;
/*    */     }
/*    */     private int num;
/*    */     
/*    */     public int getNum() {
/* 40 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 45 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 46 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "costItem= " + this.costItem + ", itemId= " + this.itemId + ", costNum= " + this.costNum + ", attr= " + this.attr;
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EquipStoneBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */