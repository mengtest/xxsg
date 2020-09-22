/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SoulBean
/*    */ {
/*    */   private int level;
/*    */   
/*    */   public int getLevel() {
/* 10 */     return this.level;
/*    */   }
/*    */   private int fighteLevel; private ArrayList<AttrBean> attr;
/*    */   private ArrayList<RewardBean> cost;
/*    */   
/*    */   public int getFighteLevel() {
/* 16 */     return this.fighteLevel;
/*    */   }
/*    */   private ArrayList<RewardBean> currency;
/*    */   private ArrayList<RewardBean> recover;
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 22 */     return this.attr;
/*    */   }
/*    */   
/*    */   public class AttrBean { private int id;
/*    */     private int num;
/*    */     
/*    */     public int getId() {
/* 29 */       return this.id;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 34 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 39 */       String retVal = "id= " + this.id + ", num= " + this.num;
/* 40 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 47 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCurrency() {
/* 53 */     return this.currency;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getRecover() {
/* 59 */     return this.recover;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     String retVal = "level= " + this.level + ", fighteLevel= " + this.fighteLevel + ", attr= " + this.attr + ", cost= " + this.cost + ", currency= " + this.currency + ", recover= " + this.recover;
/* 65 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SoulBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */