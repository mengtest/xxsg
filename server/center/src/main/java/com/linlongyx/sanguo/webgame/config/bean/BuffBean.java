/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ public class BuffBean {
/*    */   private int id;
/*    */   private int buffed;
/*    */   private int type;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private int effect; private int effectType; private int effecPer; private int effectValue; private int lastAndTime; private int last; private int time;
/*    */   private int limit;
/*    */   
/*    */   public int getBuffed() {
/* 14 */     return this.buffed;
/*    */   }
/*    */   private int sourceType; private int inherit; private int calculationType;
/*    */   private int dispel;
/*    */   
/*    */   public int getType() {
/* 20 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEffect() {
/* 26 */     return this.effect;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEffectType() {
/* 32 */     return this.effectType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEffecPer() {
/* 38 */     return this.effecPer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEffectValue() {
/* 44 */     return this.effectValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLastAndTime() {
/* 50 */     return this.lastAndTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLast() {
/* 56 */     return this.last;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTime() {
/* 62 */     return this.time;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLimit() {
/* 68 */     return this.limit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSourceType() {
/* 74 */     return this.sourceType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getInherit() {
/* 80 */     return this.inherit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCalculationType() {
/* 86 */     return this.calculationType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDispel() {
/* 92 */     return this.dispel;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     String retVal = "id= " + this.id + ", buffed= " + this.buffed + ", type= " + this.type + ", effect= " + this.effect + ", effectType= " + this.effectType + ", effecPer= " + this.effecPer + ", effectValue= " + this.effectValue + ", lastAndTime= " + this.lastAndTime + ", last= " + this.last + ", time= " + this.time + ", limit= " + this.limit + ", sourceType= " + this.sourceType + ", inherit= " + this.inherit + ", calculationType= " + this.calculationType + ", dispel= " + this.dispel;
/* 98 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\BuffBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */