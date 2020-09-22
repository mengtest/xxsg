/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class NeutralBossBean {
/*     */   private int id;
/*     */   private int Level;
/*     */   
/*     */   public int getId() {
/*  11 */     return this.id;
/*     */   }
/*     */   private ArrayList<RewardBean> belongReward; private ArrayList<RewardBean> proReward; private ArrayList<ProReward_cliBean> proReward_cli;
/*     */   private int reviveTime;
/*     */   
/*     */   public int getLevel() {
/*  17 */     return this.Level;
/*     */   }
/*     */   private int runTime;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public ArrayList<RewardBean> getBelongReward() {
/*  23 */     return this.belongReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getProReward() {
/*  29 */     return this.proReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ProReward_cliBean> getProReward_cli() {
/*  35 */     return this.proReward_cli;
/*     */   }
/*     */   public class ProReward_cliBean { private int type;
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getType() {
/*  42 */       return this.type;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getId() {
/*  48 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  53 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  58 */       String retVal = "type= " + this.type + ", id= " + this.id + ", num= " + this.num;
/*  59 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReviveTime() {
/*  66 */     return this.reviveTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRunTime() {
/*  72 */     return this.runTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  78 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  87 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/*  93 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/* 100 */         return this.posi;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public int getId() {
/* 106 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 111 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 112 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 118 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 119 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     String retVal = "id= " + this.id + ", Level= " + this.Level + ", belongReward= " + this.belongReward + ", proReward= " + this.proReward + ", proReward_cli= " + this.proReward_cli + ", reviveTime= " + this.reviveTime + ", runTime= " + this.runTime + ", wave= " + this.wave;
/* 126 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\NeutralBossBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */