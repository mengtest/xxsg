/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LuckyRankBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String name; private int actId;
/*    */   private String reachConditions;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int target;
/*    */   private ArrayList<RewardBean> reward;
/*    */   
/*    */   public int getActId() {
/* 22 */     return this.actId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getReachConditions() {
/* 28 */     return this.reachConditions;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTarget() {
/* 34 */     return this.target;
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
/* 45 */     String retVal = "id= " + this.id + ", name= " + this.name + ", actId= " + this.actId + ", reachConditions= " + this.reachConditions + ", target= " + this.target + ", reward= " + this.reward;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LuckyRankBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */