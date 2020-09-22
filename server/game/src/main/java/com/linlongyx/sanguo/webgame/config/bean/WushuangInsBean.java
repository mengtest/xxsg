/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WushuangInsBean {
/*     */   private int insId;
/*     */   private int checkPoint;
/*     */   
/*     */   public int getInsId() {
/*  11 */     return this.insId;
/*     */   }
/*     */   private int level; private int star; private ArrayList<RewardBean> passReward;
/*     */   private ArrayList<RewardBean> otherCost;
/*     */   
/*     */   public int getCheckPoint() {
/*  17 */     return this.checkPoint;
/*     */   }
/*     */   private ArrayList<RewardBean> moneyCost;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getLevel() {
/*  23 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStar() {
/*  29 */     return this.star;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getPassReward() {
/*  35 */     return this.passReward;
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
/*     */   public Map<Integer, WaveBean> getWave() {
/*  53 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  62 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/*  68 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/*  75 */         return this.posi;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getId() {
/*  80 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/*  85 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/*  86 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  92 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/*  93 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     String retVal = "insId= " + this.insId + ", checkPoint= " + this.checkPoint + ", level= " + this.level + ", star= " + this.star + ", passReward= " + this.passReward + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost + ", wave= " + this.wave;
/* 100 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\WushuangInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */