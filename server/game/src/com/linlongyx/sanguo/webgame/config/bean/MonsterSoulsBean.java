/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MonsterSoulsBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String rid; private int fightType; private int skill; private int hotSkill; private int debutSkill;
/*    */   private int quality;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int fury; private int hurtType;
/*    */   private ArrayList<AttrBean> attr;
/*    */   
/*    */   public String getRid() {
/* 22 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFightType() {
/* 28 */     return this.fightType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSkill() {
/* 34 */     return this.skill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHotSkill() {
/* 40 */     return this.hotSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDebutSkill() {
/* 46 */     return this.debutSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getQuality() {
/* 52 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFury() {
/* 58 */     return this.fury;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHurtType() {
/* 64 */     return this.hurtType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 70 */     return this.attr;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", fightType= " + this.fightType + ", skill= " + this.skill + ", hotSkill= " + this.hotSkill + ", debutSkill= " + this.debutSkill + ", quality= " + this.quality + ", fury= " + this.fury + ", hurtType= " + this.hurtType + ", attr= " + this.attr;
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MonsterSoulsBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */