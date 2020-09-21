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
/* 56 */     return this.round;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<MonsterBean> getMonster() {
/* 62 */     return this.monster;
/*    */   }
/*    */   
/*    */   public class MonsterBean {
/*    */     private int posi;
/*    */     
/*    */     public int getPosi() {
/* 69 */       return this.posi;
/*    */     }
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 74 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 79 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 80 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MainInsBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */