/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ShareRewardBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private ArrayList<RewardBean> reward;
/*    */   private int mainTaskID;
/*    */   
/*    */   public ArrayList<RewardBean> getReward() {
/* 16 */     return this.reward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMainTaskID() {
/* 22 */     return this.mainTaskID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     String retVal = "id= " + this.id + ", reward= " + this.reward + ", mainTaskID= " + this.mainTaskID;
/* 28 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ShareRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */