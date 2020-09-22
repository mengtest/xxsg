/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ArenaRobotBean {
/*     */   private int robotId;
/*     */   private String robotName;
/*     */   
/*     */   public int getRobotId() {
/*  11 */     return this.robotId;
/*     */   }
/*     */   private String robotShow; private int roboteff; private String sceneRid;
/*     */   private int sex;
/*     */   
/*     */   public String getRobotName() {
/*  17 */     return this.robotName;
/*     */   }
/*     */   private int level; private int quality;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public String getRobotShow() {
/*  23 */     return this.robotShow;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRoboteff() {
/*  29 */     return this.roboteff;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSceneRid() {
/*  35 */     return this.sceneRid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSex() {
/*  41 */     return this.sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/*  47 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getQuality() {
/*  53 */     return this.quality;
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
/* 105 */     String retVal = "robotId= " + this.robotId + ", robotName= " + this.robotName + ", robotShow= " + this.robotShow + ", roboteff= " + this.roboteff + ", sceneRid= " + this.sceneRid + ", sex= " + this.sex + ", level= " + this.level + ", quality= " + this.quality + ", wave= " + this.wave;
/* 106 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ArenaRobotBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */