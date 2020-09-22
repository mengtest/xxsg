/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class SkillCostBean {
/*    */   private int id;
/*    */   private int skillId;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private int condition; private int level; private int cost;
/*    */   private int damagePer;
/*    */   
/*    */   public int getSkillId() {
/* 14 */     return this.skillId;
/*    */   }
/*    */   private int damageVal;
/*    */   private int fightValue;
/*    */   
/*    */   public int getCondition() {
/* 20 */     return this.condition;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 26 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCost() {
/* 32 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDamagePer() {
/* 38 */     return this.damagePer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDamageVal() {
/* 44 */     return this.damageVal;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFightValue() {
/* 50 */     return this.fightValue;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "id= " + this.id + ", skillId= " + this.skillId + ", condition= " + this.condition + ", level= " + this.level + ", cost= " + this.cost + ", damagePer= " + this.damagePer + ", damageVal= " + this.damageVal + ", fightValue= " + this.fightValue;
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SkillCostBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */