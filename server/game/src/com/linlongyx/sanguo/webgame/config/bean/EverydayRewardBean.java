/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ public class EverydayRewardBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/*  8 */     return this.id;
/*    */   }
/*    */   private int rewardId; private int serverType;
/*    */   private String beginTime;
/*    */   
/*    */   public int getRewardId() {
/* 14 */     return this.rewardId;
/*    */   }
/*    */   private String endTime;
/*    */   private int day;
/*    */   
/*    */   public int getServerType() {
/* 20 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 26 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 32 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 38 */     return this.day;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     String retVal = "id= " + this.id + ", rewardId= " + this.rewardId + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day;
/* 44 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\EverydayRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */