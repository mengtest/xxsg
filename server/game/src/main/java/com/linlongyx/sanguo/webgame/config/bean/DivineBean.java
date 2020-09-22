/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DivineBean
/*    */ {
/*    */   private int id;
/*    */   
/*    */   public int getId() {
/* 10 */     return this.id;
/*    */   }
/*    */   private int serverType; private int day; private String beginTime;
/*    */   private String endTime;
/*    */   
/*    */   public int getServerType() {
/* 16 */     return this.serverType;
/*    */   }
/*    */   private ArrayList<Integer> luckyReward;
/*    */   private ArrayList<Integer> rankReward;
/*    */   
/*    */   public int getDay() {
/* 22 */     return this.day;
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
/*    */   public ArrayList<Integer> getLuckyReward() {
/* 40 */     return this.luckyReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<Integer> getRankReward() {
/* 46 */     return this.rankReward;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "id= " + this.id + ", serverType= " + this.serverType + ", day= " + this.day + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", luckyReward= " + this.luckyReward + ", rankReward= " + this.rankReward;
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\DivineBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */