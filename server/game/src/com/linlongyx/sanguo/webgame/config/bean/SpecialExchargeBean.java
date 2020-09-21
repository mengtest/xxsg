/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SpecialExchargeBean {
/*    */   private int id;
/*    */   private String name;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int activityType; private int serverType; private String beginTime;
/*    */   private String endTime;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int day;
/*    */   private ArrayList<Integer> activityList;
/*    */   
/*    */   public int getActivityType() {
/* 22 */     return this.activityType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getServerType() {
/* 28 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 34 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 40 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 46 */     return this.day;
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
/* 57 */     String retVal = "id= " + this.id + ", name= " + this.name + ", activityType= " + this.activityType + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day + ", activityList= " + this.activityList;
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\SpecialExchargeBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */