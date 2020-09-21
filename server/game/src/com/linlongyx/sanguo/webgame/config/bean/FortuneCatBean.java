/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FortuneCatBean {
/*    */   private int type;
/*    */   private Map<Integer, TimesBean> times;
/*    */   
/*    */   public int getType() {
/*  9 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, TimesBean> getTimes() {
/* 15 */     return this.times;
/*    */   }
/*    */   
/*    */   public class TimesBean
/*    */   {
/*    */     private int cost;
/*    */     private int firstR;
/*    */     
/*    */     public int getCost() {
/* 24 */       return this.cost;
/*    */     }
/*    */     private int firstL; private int proF; private int secondR; private int secondL;
/*    */     private int proS;
/*    */     
/*    */     public int getFirstR() {
/* 30 */       return this.firstR;
/*    */     }
/*    */     private int thirdR; private int thirdL;
/*    */     private int proT;
/*    */     
/*    */     public int getFirstL() {
/* 36 */       return this.firstL;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getProF() {
/* 42 */       return this.proF;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getSecondR() {
/* 48 */       return this.secondR;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getSecondL() {
/* 54 */       return this.secondL;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getProS() {
/* 60 */       return this.proS;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getThirdR() {
/* 66 */       return this.thirdR;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getThirdL() {
/* 72 */       return this.thirdL;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getProT() {
/* 78 */       return this.proT;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 83 */       String retVal = "cost= " + this.cost + ", firstR= " + this.firstR + ", firstL= " + this.firstL + ", proF= " + this.proF + ", secondR= " + this.secondR + ", secondL= " + this.secondL + ", proS= " + this.proS + ", thirdR= " + this.thirdR + ", thirdL= " + this.thirdL + ", proT= " + this.proT;
/* 84 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     String retVal = "type= " + this.type + ", times= " + this.times;
/* 91 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FortuneCatBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */