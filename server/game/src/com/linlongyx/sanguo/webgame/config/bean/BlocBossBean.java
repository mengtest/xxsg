/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BlocBossBean {
/*    */   private int id;
/*    */   private Map<Integer, ChallBean> chall;
/*    */   
/*    */   public int getId() {
/* 11 */     return this.id;
/*    */   }
/*    */   
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public Map<Integer, ChallBean> getChall() {
/* 17 */     return this.chall;
/*    */   }
/*    */   private Map<Integer, WaveBean> wave;
/*    */   
/*    */   public class ChallBean { private ArrayList<RewardBean> reward;
/*    */     
/*    */     public ArrayList<RewardBean> getReward() {
/* 24 */       return this.reward;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 31 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Integer, WaveBean> getWave() {
/* 37 */     return this.wave;
/*    */   }
/*    */   
/*    */   public class WaveBean
/*    */   {
/*    */     private int round;
/*    */     private ArrayList<MonsterBean> monster;
/*    */     
/*    */     public int getRound() {
/* 46 */       return this.round;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public ArrayList<MonsterBean> getMonster() {
/* 52 */       return this.monster;
/*    */     }
/*    */     
/*    */     public class MonsterBean { private int posi;
/*    */       private int id;
/*    */       
/*    */       public int getPosi() {
/* 59 */         return this.posi;
/*    */       }
/*    */ 
/*    */ 
/*    */       
/*    */       public int getId() {
/* 65 */         return this.id;
/*    */       }
/*    */ 
/*    */       
/*    */       public String toString() {
/* 70 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 71 */         return retVal;
/*    */       } }
/*    */ 
/*    */ 
/*    */     
/*    */     public String toString() {
/* 77 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 78 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     String retVal = "id= " + this.id + ", chall= " + this.chall + ", reward= " + this.reward + ", wave= " + this.wave;
/* 85 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BlocBossBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */