/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class StageLevelBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   private int exp;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public int getExp() {
/* 16 */     return this.exp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 22 */     return this.attr;
/*    */   }
/*    */   
/*    */   public class AttrBean { private int id;
/*    */     private int num;
/*    */     
/*    */     public int getId() {
/* 29 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 34 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 39 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 40 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     String retVal = "level= " + this.level + ", exp= " + this.exp + ", attr= " + this.attr;
/* 47 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\StageLevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */