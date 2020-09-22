/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LimitActivitylistBean {
/*    */   private int id;
/*    */   private int rewardType;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int displayType; private int num; private int targetType; private int target; private int targetS;
/*    */   private String fameName;
/*    */   
/*    */   public int getRewardType() {
/* 16 */     return this.rewardType;
/*    */   }
/*    */   private ArrayList<RewardBean> award; private ArrayList<RewardBean> cost;
/*    */   private int vipLimit;
/*    */   
/*    */   public int getDisplayType() {
/* 22 */     return this.displayType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNum() {
/* 28 */     return this.num;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTargetType() {
/* 34 */     return this.targetType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTarget() {
/* 40 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTargetS() {
/* 46 */     return this.targetS;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFameName() {
/* 52 */     return this.fameName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getAward() {
/* 58 */     return this.award;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getCost() {
/* 64 */     return this.cost;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVipLimit() {
/* 70 */     return this.vipLimit;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "id= " + this.id + ", rewardType= " + this.rewardType + ", displayType= " + this.displayType + ", num= " + this.num + ", targetType= " + this.targetType + ", target= " + this.target + ", targetS= " + this.targetS + ", fameName= " + this.fameName + ", award= " + this.award + ", cost= " + this.cost + ", vipLimit= " + this.vipLimit;
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LimitActivitylistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */