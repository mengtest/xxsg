/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PvpInfoBean {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  9 */     return this.id;
/*    */   }
/*    */   
/*    */   private Map<Integer, WaveBean> wave;
/*    */   
/*    */   public Map<Integer, WaveBean> getWave() {
/* 15 */     return this.wave;
/*    */   }
/*    */ 
/*    */   
/*    */   public class WaveBean
/*    */   {
/*    */     private int round;
/*    */     
/*    */     public int getRound() {
/* 24 */       return this.round;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 29 */       String retVal = "round= " + this.round;
/* 30 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     String retVal = "id= " + this.id + ", wave= " + this.wave;
/* 37 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PvpInfoBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */