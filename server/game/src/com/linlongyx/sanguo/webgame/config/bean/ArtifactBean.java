/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArtifactBean {
/*     */   private int id;
/*     */   private String name;
/*     */   private int quality;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private ArrayList<RewardBean> sensitize; private ArrayList<AttrBean> sensitizeAttr; private ArrayList<AttrBean> initialAttr; private ArrayList<AttrBean> maxAttr; private ArrayList<RewardBean> primaryCost; private ArrayList<RewardBean> middleCost;
/*     */   private ArrayList<RewardBean> advancedCost;
/*     */   
/*     */   public String getName() {
/*  16 */     return this.name;
/*     */   }
/*     */   private ArrayList<PrimaryRollBean> primaryRoll; private ArrayList<MiddleRollBean> middleRoll; private ArrayList<AdvancedRollBean> advancedRoll;
/*     */   private int rollNum;
/*     */   
/*     */   public int getQuality() {
/*  22 */     return this.quality;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getSensitize() {
/*  28 */     return this.sensitize;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getSensitizeAttr() {
/*  34 */     return this.sensitizeAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getInitialAttr() {
/*  40 */     return this.initialAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getMaxAttr() {
/*  46 */     return this.maxAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getPrimaryCost() {
/*  52 */     return this.primaryCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMiddleCost() {
/*  58 */     return this.middleCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getAdvancedCost() {
/*  64 */     return this.advancedCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<PrimaryRollBean> getPrimaryRoll() {
/*  70 */     return this.primaryRoll;
/*     */   }
/*     */   
/*     */   public class PrimaryRollBean { private int id;
/*     */     private int low;
/*     */     private int high;
/*     */     
/*     */     public int getId() {
/*  78 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLow() {
/*  83 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/*  88 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  93 */       String retVal = "id= " + this.id + ", low= " + this.low + ", high= " + this.high;
/*  94 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<MiddleRollBean> getMiddleRoll() {
/* 101 */     return this.middleRoll;
/*     */   }
/*     */   
/*     */   public class MiddleRollBean { private int id;
/*     */     private int low;
/*     */     private int high;
/*     */     
/*     */     public int getId() {
/* 109 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLow() {
/* 114 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/* 119 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 124 */       String retVal = "id= " + this.id + ", low= " + this.low + ", high= " + this.high;
/* 125 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AdvancedRollBean> getAdvancedRoll() {
/* 132 */     return this.advancedRoll;
/*     */   }
/*     */   
/*     */   public class AdvancedRollBean { private int id;
/*     */     private int low;
/*     */     private int high;
/*     */     
/*     */     public int getId() {
/* 140 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLow() {
/* 145 */       return this.low;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getHigh() {
/* 150 */       return this.high;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 155 */       String retVal = "id= " + this.id + ", low= " + this.low + ", high= " + this.high;
/* 156 */       return retVal;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRollNum() {
/* 163 */     return this.rollNum;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 168 */     String retVal = "id= " + this.id + ", name= " + this.name + ", quality= " + this.quality + ", sensitize= " + this.sensitize + ", sensitizeAttr= " + this.sensitizeAttr + ", initialAttr= " + this.initialAttr + ", maxAttr= " + this.maxAttr + ", primaryCost= " + this.primaryCost + ", middleCost= " + this.middleCost + ", advancedCost= " + this.advancedCost + ", primaryRoll= " + this.primaryRoll + ", middleRoll= " + this.middleRoll + ", advancedRoll= " + this.advancedRoll + ", rollNum= " + this.rollNum;
/* 169 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ArtifactBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */