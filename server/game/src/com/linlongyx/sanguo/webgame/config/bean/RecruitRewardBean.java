/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RecruitRewardBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private String name; private int serverType; private String beginTime;
/*    */   private String endTime;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int day;
/*    */   private ArrayList<Integer> rankList;
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
/*    */   public int getDay() {
/* 40 */     return this.day;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getRankList() {
/* 46 */     return this.rankList;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "id= " + this.id + ", name= " + this.name + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", day= " + this.day + ", rankList= " + this.rankList;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RecruitRewardBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */