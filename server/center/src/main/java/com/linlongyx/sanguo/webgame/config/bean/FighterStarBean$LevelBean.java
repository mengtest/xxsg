/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LevelBean
/*     */ {
/*     */   private int intelligence;
/*     */   private ArrayList<AttrParbonusBean> attrParbonus;
/*     */   private ArrayList<AttrBean> attr;
/*     */   private ArrayList<RewardBean> cost;
/*     */   private ArrayList<RewardBean> money;
/*     */   private ArrayList<RewardBean> fCost;
/*     */   private int hotSkill;
/*     */   private int fitSkill;
/*     */   
/*     */   public int getIntelligence() {
/*  26 */     return this.intelligence;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrParbonusBean> getAttrParbonus() {
/*  32 */     return this.attrParbonus;
/*     */   }
/*     */   
/*     */   public class AttrParbonusBean {
/*     */     private int id;
/*     */     private int par;
/*     */     
/*     */     public int getId() {
/*  40 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPar() {
/*  45 */       return this.par;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  50 */       String retVal = "id= " + this.id + ", par= " + this.par;
/*  51 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getAttr() {
/*  58 */     return this.attr;
/*     */   }
/*     */   
/*     */   public class AttrBean {
/*     */     private int id;
/*     */     private int num;
/*     */     
/*     */     public int getId() {
/*  66 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNum() {
/*  71 */       return this.num;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  76 */       String retVal = "id= " + this.id + ", num= " + this.num;
/*  77 */       return retVal;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getCost() {
/*  84 */     return this.cost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getMoney() {
/*  90 */     return this.money;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RewardBean> getFCost() {
/*  96 */     return this.fCost;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHotSkill() {
/* 102 */     return this.hotSkill;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFitSkill() {
/* 108 */     return this.fitSkill;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     String retVal = "intelligence= " + this.intelligence + ", attrParbonus= " + this.attrParbonus + ", attr= " + this.attr + ", cost= " + this.cost + ", money= " + this.money + ", fCost= " + this.fCost + ", hotSkill= " + this.hotSkill + ", fitSkill= " + this.fitSkill;
/* 114 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterStarBean$LevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */