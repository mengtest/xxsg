/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class MultiRobotBean {
/*    */   private int robotId;
/*    */   private ArrayList<FightingBean> fighting;
/*    */   private ArrayList<LevelBean> level;
/*    */   private String head;
/*    */   
/*    */   public int getRobotId() {
/* 10 */     return this.robotId;
/*    */   }
/*    */   
/*    */   private ArrayList<Integer> monster;
/*    */   
/*    */   public ArrayList<FightingBean> getFighting() {
/* 16 */     return this.fighting;
/*    */   }
/*    */   
/*    */   public class FightingBean { private int hight;
/*    */     private int low;
/*    */     
/*    */     public int getHight() {
/* 23 */       return this.hight;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLow() {
/* 28 */       return this.low;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 33 */       String retVal = "hight= " + this.hight + ", low= " + this.low;
/* 34 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<LevelBean> getLevel() {
/* 41 */     return this.level;
/*    */   }
/*    */   
/*    */   public class LevelBean { private int hight;
/*    */     private int low;
/*    */     
/*    */     public int getHight() {
/* 48 */       return this.hight;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLow() {
/* 53 */       return this.low;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 58 */       String retVal = "hight= " + this.hight + ", low= " + this.low;
/* 59 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getHead() {
/* 66 */     return this.head;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getMonster() {
/* 72 */     return this.monster;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     String retVal = "robotId= " + this.robotId + ", fighting= " + this.fighting + ", level= " + this.level + ", head= " + this.head + ", monster= " + this.monster;
/* 78 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MultiRobotBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */