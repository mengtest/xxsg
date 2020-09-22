/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FighterReincarnBean {
/*    */   private int id;
/*    */   private int openLevel;
/*    */   private ArrayList<TaskBean> task;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int levelLimit;
/*    */   private ArrayList<RewardBean> cost;
/*    */   
/*    */   public int getOpenLevel() {
/* 16 */     return this.openLevel;
/*    */   }
/*    */   private ArrayList<AttrBean> attr;
/*    */   private ArrayList<RewardBean> recover;
/*    */   
/*    */   public ArrayList<TaskBean> getTask() {
/* 22 */     return this.task;
/*    */   }
/*    */   
/*    */   public class TaskBean {
/*    */     private int type;
/*    */     
/*    */     public int getType() {
/* 29 */       return this.type;
/*    */     }
/*    */     private int targetId; private int num;
/*    */     
/*    */     public int getTargetId() {
/* 34 */       return this.targetId;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getNum() {
/* 39 */       return this.num;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 44 */       String retVal = "type= " + this.type + ", targetId= " + this.targetId + ", num= " + this.num;
/* 45 */       return retVal;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevelLimit() {
/* 52 */     return this.levelLimit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 58 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<AttrBean> getAttr() {
/* 64 */     return this.attr;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getRecover() {
/* 70 */     return this.recover;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "id= " + this.id + ", openLevel= " + this.openLevel + ", task= " + this.task + ", levelLimit= " + this.levelLimit + ", cost= " + this.cost + ", attr= " + this.attr + ", recover= " + this.recover;
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FighterReincarnBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */