/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class MonsterAIBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private int bornInterval; private int lieInterval; private int atkType;
/*    */   private int walkArea;
/*    */   
/*    */   public int getBornInterval() {
/* 14 */     return this.bornInterval;
/*    */   }
/*    */   private int visualRadius;
/*    */   private int atkArea;
/*    */   
/*    */   public int getLieInterval() {
/* 20 */     return this.lieInterval;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAtkType() {
/* 26 */     return this.atkType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWalkArea() {
/* 32 */     return this.walkArea;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVisualRadius() {
/* 38 */     return this.visualRadius;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAtkArea() {
/* 44 */     return this.atkArea;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "id= " + this.id + ", bornInterval= " + this.bornInterval + ", lieInterval= " + this.lieInterval + ", atkType= " + this.atkType + ", walkArea= " + this.walkArea + ", visualRadius= " + this.visualRadius + ", atkArea= " + this.atkArea;
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MonsterAIBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */