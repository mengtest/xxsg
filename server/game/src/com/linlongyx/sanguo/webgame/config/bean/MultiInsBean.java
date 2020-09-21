/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MultiInsBean {
/*     */   private int insId;
/*     */   private String name;
/*     */   
/*     */   public int getInsId() {
/*  11 */     return this.insId;
/*     */   }
/*     */   private int level; private ArrayList<RewardBean> reward; private ArrayList<RewardBean> doubleReward;
/*     */   private ArrayList<RewardBean> otherCost;
/*     */   
/*     */   public String getName() {
/*  17 */     return this.name;
/*     */   }
/*     */   private ArrayList<RewardBean> moneyCost; private ArrayList<Integer> robotId;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getLevel() {
/*  23 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getReward() {
/*  29 */     return this.reward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getDoubleReward() {
/*  35 */     return this.doubleReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getOtherCost() {
/*  41 */     return this.otherCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMoneyCost() {
/*  47 */     return this.moneyCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRobotId() {
/*  53 */     return this.robotId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  59 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  68 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/*  74 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/*  81 */         return this.posi;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getId() {
/*  86 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/*  91 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/*  92 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  98 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/*  99 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 105 */     String retVal = "insId= " + this.insId + ", name= " + this.name + ", level= " + this.level + ", reward= " + this.reward + ", doubleReward= " + this.doubleReward + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost + ", robotId= " + this.robotId + ", wave= " + this.wave;
/* 106 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MultiInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */