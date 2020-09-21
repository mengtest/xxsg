/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TowerBean {
/*     */   private int number;
/*     */   private String name;
/*     */   
/*     */   public int getNumber() {
/*  11 */     return this.number;
/*     */   }
/*     */   private int challengeLevel; private int condition; private int victory; private ArrayList<RewardBean> basicReward; private ArrayList<RewardBean> bossReward;
/*     */   private ArrayList<RewardBean> ordinaryReward;
/*     */   
/*     */   public String getName() {
/*  17 */     return this.name;
/*     */   }
/*     */   private ArrayList<RewardBean> additionalReward; private int money;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getChallengeLevel() {
/*  23 */     return this.challengeLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCondition() {
/*  29 */     return this.condition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVictory() {
/*  35 */     return this.victory;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getBasicReward() {
/*  41 */     return this.basicReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getBossReward() {
/*  47 */     return this.bossReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getOrdinaryReward() {
/*  53 */     return this.ordinaryReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getAdditionalReward() {
/*  59 */     return this.additionalReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMoney() {
/*  65 */     return this.money;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  71 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterIDBean> monsterID;
/*     */     
/*     */     public int getRound() {
/*  80 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterIDBean> getMonsterID() {
/*  86 */       return this.monsterID;
/*     */     }
/*     */     
/*     */     public class MonsterIDBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/*  93 */         return this.posi;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getId() {
/*  98 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 103 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 104 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 110 */       String retVal = "round= " + this.round + ", monsterID= " + this.monsterID;
/* 111 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 117 */     String retVal = "number= " + this.number + ", name= " + this.name + ", challengeLevel= " + this.challengeLevel + ", condition= " + this.condition + ", victory= " + this.victory + ", basicReward= " + this.basicReward + ", bossReward= " + this.bossReward + ", ordinaryReward= " + this.ordinaryReward + ", additionalReward= " + this.additionalReward + ", money= " + this.money + ", wave= " + this.wave;
/* 118 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TowerBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */