/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class SkillBean {
/*     */   private int id;
/*     */   private String name;
/*     */   private int type;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int belong; private ArrayList<AttrBean> addAttr; private int distance; private int targetType; private int target; private ArrayList<Integer> range; private int enemyNum;
/*     */   private int action;
/*     */   
/*     */   public String getName() {
/*  16 */     return this.name;
/*     */   }
/*     */   private int cd; private int damageType; private int damagePer;
/*     */   private int damageVal;
/*     */   
/*     */   public int getType() {
/*  22 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBelong() {
/*  28 */     return this.belong;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AttrBean> getAddAttr() {
/*  34 */     return this.addAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDistance() {
/*  40 */     return this.distance;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTargetType() {
/*  46 */     return this.targetType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTarget() {
/*  52 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRange() {
/*  58 */     return this.range;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEnemyNum() {
/*  64 */     return this.enemyNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAction() {
/*  70 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCd() {
/*  76 */     return this.cd;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamageType() {
/*  82 */     return this.damageType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamagePer() {
/*  88 */     return this.damagePer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamageVal() {
/*  94 */     return this.damageVal;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     String retVal = "id= " + this.id + ", name= " + this.name + ", type= " + this.type + ", belong= " + this.belong + ", addAttr= " + this.addAttr + ", distance= " + this.distance + ", targetType= " + this.targetType + ", target= " + this.target + ", range= " + this.range + ", enemyNum= " + this.enemyNum + ", action= " + this.action + ", cd= " + this.cd + ", damageType= " + this.damageType + ", damagePer= " + this.damagePer + ", damageVal= " + this.damageVal;
/* 100 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SkillBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */