/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DrawMinBean {
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
/*    */   
/*    */   public class NumBean
/*    */   {
/*    */     private int minId;
/*    */     
/*    */     public int getMinId() {
/* 24 */       return this.minId;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 29 */       String retVal = "minId= " + this.minId;
/* 30 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     String retVal = "id= " + this.id + ", num= " + this.num;
/* 37 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DrawMinBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */