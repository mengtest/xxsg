/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DailyBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int taskType; private int level; private int targeId;
/*    */   private int num;
/*    */   
/*    */   public int getTaskType() {
/* 16 */     return this.taskType;
/*    */   }
/*    */   private int activity;
/*    */   private ArrayList<RewardBean> Reward;
/*    */   
/*    */   public int getLevel() {
/* 22 */     return this.level;
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
/*    */   public int getNum() {
/* 34 */     return this.num;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getActivity() {
/* 40 */     return this.activity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 46 */     return this.Reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "id= " + this.id + ", taskType= " + this.taskType + ", level= " + this.level + ", targeId= " + this.targeId + ", num= " + this.num + ", activity= " + this.activity + ", Reward= " + this.Reward;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DailyBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */