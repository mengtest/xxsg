/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class TiringrebateBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String name; private int serverType;
/*    */   private String beginTime;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private String endTime;
/*    */   private ArrayList<Integer> activityList;
/*    */   
/*    */   public int getServerType() {
/* 22 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 28 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 34 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getActivityList() {
/* 40 */     return this.activityList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "id= " + this.id + ", name= " + this.name + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", activityList= " + this.activityList;
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\TiringrebateBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */