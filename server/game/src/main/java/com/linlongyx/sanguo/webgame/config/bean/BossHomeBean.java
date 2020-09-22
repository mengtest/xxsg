/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BossHomeBean {
/*     */   private int insId;
/*     */   private int type;
/*     */   private String name;
/*     */   
/*     */   public int getInsId() {
/*  11 */     return this.insId;
/*     */   }
/*     */   private int worldLevel; private int level; private int freshTime; private int sustainedTime; private ArrayList<RewardBean> lastHitReward;
/*     */   private Map<Integer, RankRewardBean> rankReward;
/*     */   
/*     */   public int getType() {
/*  17 */     return this.type;
/*     */   }
/*     */   private ArrayList<RewardBean> fresh; private ArrayList<RewardBean> otherCost; private ArrayList<RewardBean> moneyCost;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public String getName() {
/*  23 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldLevel() {
/*  29 */     return this.worldLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  35 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFreshTime() {
/*  41 */     return this.freshTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSustainedTime() {
/*  47 */     return this.sustainedTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getLastHitReward() {
/*  53 */     return this.lastHitReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, RankRewardBean> getRankReward() {
/*  59 */     return this.rankReward;
/*     */   }
/*     */   
/*     */   public class RankRewardBean {
/*     */     private ArrayList<Integer> dropId;
/*     */     
/*     */     public ArrayList<Integer> getDropId() {
/*  66 */       return this.dropId;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  71 */       String retVal = "dropId= " + this.dropId;
/*  72 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getFresh() {
/*  79 */     return this.fresh;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getOtherCost() {
/*  85 */     return this.otherCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMoneyCost() {
/*  91 */     return this.moneyCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  97 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/* 106 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/* 112 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/* 119 */         return this.posi;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getId() {
/* 124 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 129 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 130 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 136 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 137 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     String retVal = "insId= " + this.insId + ", type= " + this.type + ", name= " + this.name + ", worldLevel= " + this.worldLevel + ", level= " + this.level + ", freshTime= " + this.freshTime + ", sustainedTime= " + this.sustainedTime + ", lastHitReward= " + this.lastHitReward + ", rankReward= " + this.rankReward + ", fresh= " + this.fresh + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost + ", wave= " + this.wave;
/* 144 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BossHomeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */