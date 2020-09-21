/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DivineNumBean {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  9 */     return this.id;
/*    */   }
/*    */   
/*    */   private Map<Integer, NumBean> num;
/*    */   
/*    */   public Map<Integer, NumBean> getNum() {
/* 15 */     return this.num;
/*    */   }
/*    */   
/*    */   public class NumBean
/*    */   {
/*    */     private int pro;
/*    */     private int luckPro;
/*    */     
/*    */     public int getPro() {
/* 24 */       return this.pro;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getLuckPro() {
/* 30 */       return this.luckPro;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 35 */       String retVal = "pro= " + this.pro + ", luckPro= " + this.luckPro;
/* 36 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     String retVal = "id= " + this.id + ", num= " + this.num;
/* 43 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DivineNumBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */