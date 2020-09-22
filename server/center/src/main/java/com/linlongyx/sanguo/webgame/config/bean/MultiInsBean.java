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
/*     */   private int showLog; private int level; private ArrayList<RewardBean> reward;
/*     */   private ArrayList<RewardBean> doubleReward;
/*     */   
/*     */   public String getName() {
/*  17 */     return this.name;
/*     */   }
/*     */   private ArrayList<Integer> robotId;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getShowLog() {
/*  23 */     return this.showLog;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  29 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getReward() {
/*  35 */     return this.reward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getDoubleReward() {
/*  41 */     return this.doubleReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRobotId() {
/*  47 */     return this.robotId;
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
/*  99 */     String retVal = "insId= " + this.insId + ", name= " + this.name + ", showLog= " + this.showLog + ", level= " + this.level + ", reward= " + this.reward + ", doubleReward= " + this.doubleReward + ", robotId= " + this.robotId + ", wave= " + this.wave;
/* 100 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MultiInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */