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
/*    */ 
/*    */ public class StarBean
/*    */ {
/*    */   private ArrayList<RewardBean> card;
/*    */   private ArrayList<AttrBean> attr;
/*    */   private int levelPar;
/*    */   
/*    */   public ArrayList<RewardBean> getCard() {
/* 44 */     return this.card;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 50 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevelPar() {
/* 56 */     return this.levelPar;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "card= " + this.card + ", attr= " + this.attr + ", levelPar= " + this.levelPar;
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MountBean$StarBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */