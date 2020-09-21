/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FunctionPredictBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int targetType; private int num;
/*    */   private int target;
/*    */   
/*    */   public int getTargetType() {
/* 16 */     return this.targetType;
/*    */   }
/*    */   private String fameName;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getNum() {
/* 22 */     return this.num;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTarget() {
/* 28 */     return this.target;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFameName() {
/* 34 */     return this.fameName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 40 */     return this.reward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", targetType= " + this.targetType + ", num= " + this.num + ", target= " + this.target + ", fameName= " + this.fameName + ", reward= " + this.reward;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\FunctionPredictBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */