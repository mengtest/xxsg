/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ContinFillingBean {
/*    */   private int id;
/*    */   private ArrayList<Integer> limit;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int type; private String name; private int serverType; private String beginTime; private String endTime;
/*    */   private int day;
/*    */   
/*    */   public ArrayList<Integer> getLimit() {
/* 16 */     return this.limit;
/*    */   }
/*    */   private ArrayList<Integer> rankList; private int commit;
/*    */   private ArrayList<RewardBean> targetList;
/*    */   
/*    */   public int getType() {
/* 22 */     return this.type;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 28 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getServerType() {
/* 34 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 40 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 46 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 52 */     return this.day;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getRankList() {
/* 58 */     return this.rankList;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCommit() {
/* 64 */     return this.commit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getTargetList() {
/* 70 */     return this.targetList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "id= " + this.id + ", limit= " + this.limit + ", type= " + this.type + ", name= " + this.name + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day + ", rankList= " + this.rankList + ", commit= " + this.commit + ", targetList= " + this.targetList;
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\ContinFillingBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */