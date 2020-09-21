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
/*    */ 
/*    */ 
/*    */ public class StarBean
/*    */ {
/*    */   private int kungfuSkill;
/*    */   private ArrayList<RewardBean> card;
/*    */   private ArrayList<AttrBean> attr;
/*    */   private int levelPar;
/*    */   
/*    */   public int getKungfuSkill() {
/* 44 */     return this.kungfuSkill;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCard() {
/* 50 */     return this.card;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 56 */     return this.attr;
/*    */   }
/*    */   
/*    */   public class AttrBean {
/*    */     private int id;
/*    */     private int num;
/*    */     
/*    */     public int getId() {
/* 64 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 69 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 74 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 75 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevelPar() {
/* 82 */     return this.levelPar;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 87 */     String retVal = "kungfuSkill= " + this.kungfuSkill + ", card= " + this.card + ", attr= " + this.attr + ", levelPar= " + this.levelPar;
/* 88 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\KungfuBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */