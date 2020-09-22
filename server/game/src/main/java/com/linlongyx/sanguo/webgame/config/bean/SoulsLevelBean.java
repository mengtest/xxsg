/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SoulsLevelBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   private int levelClass; private int exp; private ArrayList<AttrBean> attr;
/*    */   private ArrayList<RewardBean> consumeClass;
/*    */   
/*    */   public int getLevelClass() {
/* 16 */     return this.levelClass;
/*    */   }
/*    */   private int totalExp;
/*    */   private ArrayList<RewardBean> totalConsume;
/*    */   
/*    */   public int getExp() {
/* 22 */     return this.exp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 28 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getConsumeClass() {
/* 34 */     return this.consumeClass;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTotalExp() {
/* 40 */     return this.totalExp;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getTotalConsume() {
/* 46 */     return this.totalConsume;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "level= " + this.level + ", levelClass= " + this.levelClass + ", exp= " + this.exp + ", attr= " + this.attr + ", consumeClass= " + this.consumeClass + ", totalExp= " + this.totalExp + ", totalConsume= " + this.totalConsume;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SoulsLevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */