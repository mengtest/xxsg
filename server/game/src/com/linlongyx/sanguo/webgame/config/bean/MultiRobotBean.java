/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MultiRobotBean {
/*    */   private int robotId;
/*    */   private ArrayList<FightingBean> fighting;
/*    */   private ArrayList<LevelBean> level;
/*    */   
/*    */   public int getRobotId() {
/* 10 */     return this.robotId;
/*    */   }
/*    */   
/*    */   private int quality;
/*    */   
/*    */   public ArrayList<FightingBean> getFighting() {
/* 16 */     return this.fighting;
/*    */   }
/*    */   private String head; private ArrayList<Integer> monster;
/*    */   public class FightingBean { private int low;
/*    */     private int hight;
/*    */     
/*    */     public int getLow() {
/* 23 */       return this.low;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getHight() {
/* 28 */       return this.hight;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 33 */       String retVal = "low= " + this.low + ", hight= " + this.hight;
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
/*    */   public class LevelBean { private int low;
/*    */     private int hight;
/*    */     
/*    */     public int getLow() {
/* 48 */       return this.low;
/*    */     }
/*    */ 
/*    */     
/*    */     public int getHight() {
/* 53 */       return this.hight;
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 58 */       String retVal = "low= " + this.low + ", hight= " + this.hight;
/* 59 */       return retVal;
/*    */     } }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getQuality() {
/* 66 */     return this.quality;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getHead() {
/* 72 */     return this.head;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getMonster() {
/* 78 */     return this.monster;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     String retVal = "robotId= " + this.robotId + ", fighting= " + this.fighting + ", level= " + this.level + ", quality= " + this.quality + ", head= " + this.head + ", monster= " + this.monster;
/* 84 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MultiRobotBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */