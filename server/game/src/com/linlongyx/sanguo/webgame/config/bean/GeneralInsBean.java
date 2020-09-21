/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GeneralInsBean {
/*     */   private int insId;
/*     */   private int frontIns;
/*     */   
/*     */   public int getInsId() {
/*  11 */     return this.insId;
/*     */   }
/*     */   private int chapter; private int checkPoint; private int difficult; private int limit; private ArrayList<RewardBean> proReward;
/*     */   private ArrayList<RewardBean> sureReward;
/*     */   
/*     */   public int getFrontIns() {
/*  17 */     return this.frontIns;
/*     */   }
/*     */   private int dailyTimes; private int cost;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getChapter() {
/*  23 */     return this.chapter;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCheckPoint() {
/*  29 */     return this.checkPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDifficult() {
/*  35 */     return this.difficult;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLimit() {
/*  41 */     return this.limit;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getProReward() {
/*  47 */     return this.proReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getSureReward() {
/*  53 */     return this.sureReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDailyTimes() {
/*  59 */     return this.dailyTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCost() {
/*  65 */     return this.cost;
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
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  80 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/*  86 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
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
/* 110 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 111 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 117 */     String retVal = "insId= " + this.insId + ", frontIns= " + this.frontIns + ", chapter= " + this.chapter + ", checkPoint= " + this.checkPoint + ", difficult= " + this.difficult + ", limit= " + this.limit + ", proReward= " + this.proReward + ", sureReward= " + this.sureReward + ", dailyTimes= " + this.dailyTimes + ", cost= " + this.cost + ", wave= " + this.wave;
/* 118 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\GeneralInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */