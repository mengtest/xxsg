/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LimitActivityBean {
/*    */   private int id;
/*    */   private int actType;
/*    */   private int openDay;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int type; private String beginTime; private String endTime; private int delyDay; private int activityType;
/*    */   private int serverType;
/*    */   
/*    */   public int getActType() {
/* 16 */     return this.actType;
/*    */   }
/*    */   private int day; private int mutex;
/*    */   private ArrayList<Integer> activityList;
/*    */   
/*    */   public int getOpenDay() {
/* 22 */     return this.openDay;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 28 */     return this.type;
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
/*    */   public int getDelyDay() {
/* 46 */     return this.delyDay;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getActivityType() {
/* 52 */     return this.activityType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getServerType() {
/* 58 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 64 */     return this.day;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMutex() {
/* 70 */     return this.mutex;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getActivityList() {
/* 76 */     return this.activityList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String retVal = "id= " + this.id + ", actType= " + this.actType + ", openDay= " + this.openDay + ", type= " + this.type + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", delyDay= " + this.delyDay + ", activityType= " + this.activityType + ", serverType= " + this.serverType + ", day= " + this.day + ", mutex= " + this.mutex + ", activityList= " + this.activityList;
/* 82 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LimitActivityBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */