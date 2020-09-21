/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class YearBeastListBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   private int front; private int worldLevel;
/*    */   private int openDay;
/*    */   
/*    */   public int getFront() {
/* 17 */     return this.front;
/*    */   }
/*    */   private ArrayList<RewardBean> challReward;
/*    */   private Map<Integer, WaveBean> wave;
/*    */   
/*    */   public int getWorldLevel() {
/* 23 */     return this.worldLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpenDay() {
/* 29 */     return this.openDay;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getChallReward() {
/* 35 */     return this.challReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, WaveBean> getWave() {
/* 41 */     return this.wave;
/*    */   }
/*    */   
/*    */   public class WaveBean
/*    */   {
/*    */     private int round;
/*    */     private ArrayList<MonsterBean> monster;
/*    */     
/*    */     public int getRound() {
/* 50 */       return this.round;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<MonsterBean> getMonster() {
/* 56 */       return this.monster;
/*    */     }
/*    */     
/*    */     public class MonsterBean { private int posi;
/*    */       private int id;
/*    */       
/*    */       public int getPosi() {
/* 63 */         return this.posi;
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public int getId() {
/* 69 */         return this.id;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 74 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 75 */         return retVal;
/*    */       } }
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 81 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 82 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 88 */     String retVal = "id= " + this.id + ", front= " + this.front + ", worldLevel= " + this.worldLevel + ", openDay= " + this.openDay + ", challReward= " + this.challReward + ", wave= " + this.wave;
/* 89 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\YearBeastListBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */