/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PersonalInsBean {
/*     */   private int insId;
/*     */   private int intType;
/*     */   private int checkPoint;
/*     */   
/*     */   public int getInsId() {
/*  11 */     return this.insId;
/*     */   }
/*     */   private ArrayList<Integer> day; private int vip; private int level; private int levelMax; private ArrayList<Integer> price; private ArrayList<RewardBean> passReward;
/*     */   private ArrayList<RewardBean> firstReward;
/*     */   
/*     */   public int getIntType() {
/*  17 */     return this.intType;
/*     */   }
/*     */   private int dailyTimes; private ArrayList<RewardBean> otherCost; private ArrayList<RewardBean> moneyCost;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getCheckPoint() {
/*  23 */     return this.checkPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getDay() {
/*  29 */     return this.day;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVip() {
/*  35 */     return this.vip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  41 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevelMax() {
/*  47 */     return this.levelMax;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getPrice() {
/*  53 */     return this.price;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getPassReward() {
/*  59 */     return this.passReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getFirstReward() {
/*  65 */     return this.firstReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDailyTimes() {
/*  71 */     return this.dailyTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getOtherCost() {
/*  77 */     return this.otherCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMoneyCost() {
/*  83 */     return this.moneyCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  89 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  98 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/* 104 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/* 111 */         return this.posi;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getId() {
/* 116 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 121 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 122 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 128 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 129 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 135 */     String retVal = "insId= " + this.insId + ", intType= " + this.intType + ", checkPoint= " + this.checkPoint + ", day= " + this.day + ", vip= " + this.vip + ", level= " + this.level + ", levelMax= " + this.levelMax + ", price= " + this.price + ", passReward= " + this.passReward + ", firstReward= " + this.firstReward + ", dailyTimes= " + this.dailyTimes + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost + ", wave= " + this.wave;
/* 136 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\PersonalInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */