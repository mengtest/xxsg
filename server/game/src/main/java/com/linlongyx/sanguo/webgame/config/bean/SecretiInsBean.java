/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SecretiInsBean {
/*     */   private int id;
/*     */   private ArrayList<RewardBean> vitoryReward;
/*     */   
/*     */   public int getId() {
/*  11 */     return this.id;
/*     */   }
/*     */   private ArrayList<RewardBean> loseReward; private int pet; private int stage;
/*     */   private ArrayList<ZhenfaBean> zhenfa;
/*     */   
/*     */   public ArrayList<RewardBean> getVitoryReward() {
/*  17 */     return this.vitoryReward;
/*     */   }
/*     */   private ArrayList<Integer> souls;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public ArrayList<RewardBean> getLoseReward() {
/*  23 */     return this.loseReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPet() {
/*  29 */     return this.pet;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStage() {
/*  35 */     return this.stage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ZhenfaBean> getZhenfa() {
/*  41 */     return this.zhenfa;
/*     */   }
/*     */   
/*     */   public class ZhenfaBean {
/*     */     private int id;
/*     */     private int star;
/*     */     
/*     */     public int getId() {
/*  49 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getStar() {
/*  54 */       return this.star;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  59 */       String retVal = "id= " + this.id + ", star= " + this.star;
/*  60 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getSouls() {
/*  67 */     return this.souls;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  73 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  82 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/*  88 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/*  95 */         return this.posi;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int getId() {
/* 101 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 106 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 107 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 113 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 114 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     String retVal = "id= " + this.id + ", vitoryReward= " + this.vitoryReward + ", loseReward= " + this.loseReward + ", pet= " + this.pet + ", stage= " + this.stage + ", zhenfa= " + this.zhenfa + ", souls= " + this.souls + ", wave= " + this.wave;
/* 121 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SecretiInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */