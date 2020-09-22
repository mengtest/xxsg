/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RecruitRedlistBean {
/*    */   private int id;
/*    */   private int type;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String beginTime; private String endTime; private int activityType;
/*    */   private int serverType;
/*    */   
/*    */   public int getType() {
/* 16 */     return this.type;
/*    */   }
/*    */   private int fighterNum;
/*    */   private ArrayList<Integer> activityList;
/*    */   
/*    */   public String getBeginTime() {
/* 22 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 28 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getActivityType() {
/* 34 */     return this.activityType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getServerType() {
/* 40 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFighterNum() {
/* 46 */     return this.fighterNum;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getActivityList() {
/* 52 */     return this.activityList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "id= " + this.id + ", type= " + this.type + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", activityType= " + this.activityType + ", serverType= " + this.serverType + ", fighterNum= " + this.fighterNum + ", activityList= " + this.activityList;
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitRedlistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */