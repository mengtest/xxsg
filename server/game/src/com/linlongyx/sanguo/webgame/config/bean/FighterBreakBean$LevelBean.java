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
/*    */ public class LevelBean
/*    */ {
/*    */   private int fLevel;
/*    */   private ArrayList<AttrBean> attr;
/*    */   private ArrayList<RewardBean> cost;
/*    */   private ArrayList<RewardBean> money;
/*    */   private ArrayList<TalentBean> talent;
/*    */   private ArrayList<RewardBean> fCost;
/*    */   
/*    */   public int getFLevel() {
/* 26 */     return this.fLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 32 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 38 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getMoney() {
/* 44 */     return this.money;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<TalentBean> getTalent() {
/* 50 */     return this.talent;
/*    */   }
/*    */   
/*    */   public class TalentBean {
/*    */     private int id;
/*    */     
/*    */     public int getId() {
/* 57 */       return this.id;
/*    */     }
/*    */     private int num;
/*    */     
/*    */     public int getNum() {
/* 62 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 67 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 68 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getFCost() {
/* 75 */     return this.fCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     String retVal = "fLevel= " + this.fLevel + ", attr= " + this.attr + ", cost= " + this.cost + ", money= " + this.money + ", talent= " + this.talent + ", fCost= " + this.fCost;
/* 81 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterBreakBean$LevelBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */