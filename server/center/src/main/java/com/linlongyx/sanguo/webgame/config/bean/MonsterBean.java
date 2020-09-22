/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MonsterBean {
/*    */   private int id;
/*    */   private String name;
/*    */   private String rid;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int monsterModel; private int quality; private int skill; private int hotSkill; private int fitSkill;
/*    */   private int lv;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int fury; private int hurtType; private ArrayList<AttrBean> attr;
/*    */   private int monsterAI;
/*    */   
/*    */   public String getRid() {
/* 22 */     return this.rid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMonsterModel() {
/* 28 */     return this.monsterModel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getQuality() {
/* 34 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSkill() {
/* 40 */     return this.skill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHotSkill() {
/* 46 */     return this.hotSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFitSkill() {
/* 52 */     return this.fitSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLv() {
/* 58 */     return this.lv;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFury() {
/* 64 */     return this.fury;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHurtType() {
/* 70 */     return this.hurtType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 76 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMonsterAI() {
/* 82 */     return this.monsterAI;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     String retVal = "id= " + this.id + ", name= " + this.name + ", rid= " + this.rid + ", monsterModel= " + this.monsterModel + ", quality= " + this.quality + ", skill= " + this.skill + ", hotSkill= " + this.hotSkill + ", fitSkill= " + this.fitSkill + ", lv= " + this.lv + ", fury= " + this.fury + ", hurtType= " + this.hurtType + ", attr= " + this.attr + ", monsterAI= " + this.monsterAI;
/* 88 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MonsterBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */