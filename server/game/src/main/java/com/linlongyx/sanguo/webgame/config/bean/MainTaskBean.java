/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MainTaskBean {
/*    */   private int id;
/*    */   private int nextId;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int para; private int targeId; private int targeScene;
/*    */   private int num;
/*    */   
/*    */   public int getNextId() {
/* 16 */     return this.nextId;
/*    */   }
/*    */   private int mainScene; private ArrayList<RewardBean> reward;
/*    */   private int levelLimit;
/*    */   
/*    */   public int getPara() {
/* 22 */     return this.para;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTargeId() {
/* 28 */     return this.targeId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTargeScene() {
/* 34 */     return this.targeScene;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNum() {
/* 40 */     return this.num;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMainScene() {
/* 46 */     return this.mainScene;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 52 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getLevelLimit() {
/* 58 */     return this.levelLimit;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "id= " + this.id + ", nextId= " + this.nextId + ", para= " + this.para + ", targeId= " + this.targeId + ", targeScene= " + this.targeScene + ", num= " + this.num + ", mainScene= " + this.mainScene + ", reward= " + this.reward + ", levelLimit= " + this.levelLimit;
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\MainTaskBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */