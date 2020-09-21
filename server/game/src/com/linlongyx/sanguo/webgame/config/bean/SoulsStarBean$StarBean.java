/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StarBean
/*    */ {
/*    */   private int hotSkill;
/*    */   private int debutSkill;
/*    */   private ArrayList<RewardBean> card;
/*    */   private ArrayList<AttrBean> attr;
/*    */   private int levelPar;
/*    */   private int breakPar;
/*    */   
/*    */   public int getHotSkill() {
/* 44 */     return this.hotSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDebutSkill() {
/* 50 */     return this.debutSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCard() {
/* 56 */     return this.card;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 62 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevelPar() {
/* 68 */     return this.levelPar;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBreakPar() {
/* 74 */     return this.breakPar;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     String retVal = "hotSkill= " + this.hotSkill + ", debutSkill= " + this.debutSkill + ", card= " + this.card + ", attr= " + this.attr + ", levelPar= " + this.levelPar + ", breakPar= " + this.breakPar;
/* 80 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SoulsStarBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */