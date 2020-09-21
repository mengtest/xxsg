/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WaveBean
/*    */ {
/*    */   private int round;
/*    */   private ArrayList<MonsterBean> monster;
/*    */   
/*    */   public int getRound() {
/* 50 */     return this.round;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<MonsterBean> getMonster() {
/* 56 */     return this.monster;
/*    */   }
/*    */   
/*    */   public class MonsterBean {
/*    */     private int posi;
/*    */     
/*    */     public int getPosi() {
/* 63 */       return this.posi;
/*    */     }
/*    */     
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 69 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 74 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 75 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\YearBeastListBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */