/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RecruitBean {
/*    */   private int lv;
/*    */   
/*    */   public int getLv() {
/*  9 */     return this.lv;
/*    */   }
/*    */   
/*    */   private Map<Integer, TimesBean> times;
/*    */   
/*    */   public Map<Integer, TimesBean> getTimes() {
/* 15 */     return this.times;
/*    */   }
/*    */   
/*    */   public class TimesBean
/*    */   {
/*    */     private int libId;
/*    */     private int yuanbaoLib;
/*    */     
/*    */     public int getLibId() {
/* 24 */       return this.libId;
/*    */     }
/*    */     private int sulyLib;
/*    */     private int tenSulyLib;
/*    */     
/*    */     public int getYuanbaoLib() {
/* 30 */       return this.yuanbaoLib;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getSulyLib() {
/* 36 */       return this.sulyLib;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public int getTenSulyLib() {
/* 42 */       return this.tenSulyLib;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 47 */       String retVal = "libId= " + this.libId + ", yuanbaoLib= " + this.yuanbaoLib + ", sulyLib= " + this.sulyLib + ", tenSulyLib= " + this.tenSulyLib;
/* 48 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "lv= " + this.lv + ", times= " + this.times;
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */