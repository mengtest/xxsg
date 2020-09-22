/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BaguaInsBean {
/*     */   private int insId;
/*     */   private int last;
/*     */   private int checkPoint;
/*     */   
/*     */   public int getInsId() {
/*  11 */     return this.insId;
/*     */   }
/*     */   private String name; private ArrayList<RewardBean> proReward; private ArrayList<RewardBean> sureReward; private ArrayList<RewardBean> chest; private int money;
/*     */   private ArrayList<RewardBean> sweep;
/*     */   
/*     */   public int getLast() {
/*  17 */     return this.last;
/*     */   }
/*     */   private ArrayList<RewardBean> assist; private ArrayList<RewardBean> otherCost; private ArrayList<RewardBean> moneyCost;
/*     */   private Map<Integer, WaveBean> wave;
/*     */   
/*     */   public int getCheckPoint() {
/*  23 */     return this.checkPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  29 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getProReward() {
/*  35 */     return this.proReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getSureReward() {
/*  41 */     return this.sureReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getChest() {
/*  47 */     return this.chest;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMoney() {
/*  53 */     return this.money;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getSweep() {
/*  59 */     return this.sweep;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getAssist() {
/*  65 */     return this.assist;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getOtherCost() {
/*  71 */     return this.otherCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMoneyCost() {
/*  77 */     return this.moneyCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, WaveBean> getWave() {
/*  83 */     return this.wave;
/*     */   }
/*     */   
/*     */   public class WaveBean
/*     */   {
/*     */     private int round;
/*     */     private ArrayList<MonsterBean> monster;
/*     */     
/*     */     public int getRound() {
/*  92 */       return this.round;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ArrayList<MonsterBean> getMonster() {
/*  98 */       return this.monster;
/*     */     }
/*     */     
/*     */     public class MonsterBean { private int posi;
/*     */       private int id;
/*     */       
/*     */       public int getPosi() {
/* 105 */         return this.posi;
/*     */       }
/*     */ 
/*     */       
/*     */       public int getId() {
/* 110 */         return this.id;
/*     */       }
/*     */ 
/*     */       
/*     */       public String toString() {
/* 115 */         String retVal = "posi= " + this.posi + ", id= " + this.id;
/* 116 */         return retVal;
/*     */       } }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 122 */       String retVal = "round= " + this.round + ", monster= " + this.monster;
/* 123 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     String retVal = "insId= " + this.insId + ", last= " + this.last + ", checkPoint= " + this.checkPoint + ", name= " + this.name + ", proReward= " + this.proReward + ", sureReward= " + this.sureReward + ", chest= " + this.chest + ", money= " + this.money + ", sweep= " + this.sweep + ", assist= " + this.assist + ", otherCost= " + this.otherCost + ", moneyCost= " + this.moneyCost + ", wave= " + this.wave;
/* 130 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BaguaInsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */