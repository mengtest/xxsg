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
/* 68 */     return this.round;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<MonsterBean> getMonster() {
/* 74 */     return this.monster;
/*    */   }
/*    */   
/*    */   public class MonsterBean {
/*    */     private int posi;
/*    */     
/*    */     public int getPosi() {
/* 81 */       return this.posi;
/*    */     }
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 86 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 91 */       String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 92 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 98 */     String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 99 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ArenaRobotBean$WaveBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */