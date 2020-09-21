/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class BlocLevelBean {
/*    */   private int id;
/*    */   private int exp;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   
/*    */   private int dailyLimited;
/*    */   
/*    */   public int getExp() {
/* 14 */     return this.exp;
/*    */   }
/*    */   
/*    */   private int personNum;
/*    */   
/*    */   public int getDailyLimited() {
/* 20 */     return this.dailyLimited;
/*    */   }
/*    */   
/*    */   private int applyNum;
/*    */   
/*    */   public int getPersonNum() {
/* 26 */     return this.personNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getApplyNum() {
/* 32 */     return this.applyNum;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     String retVal = "id= " + this.id + ", exp= " + this.exp + ", dailyLimited= " + this.dailyLimited + ", personNum= " + this.personNum + ", applyNum= " + this.applyNum;
/* 38 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BlocLevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */