/*    */ package com.linlongyx.sanguo.webgame.config.bean;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class LuckylistBean {
/*    */   private int actId;
/*    */   private String name;
/*    */   private ArrayList<Integer> actReward;
/*    */   
/*    */   public int getActId() {
/* 10 */     return this.actId;
/*    */   }
/*    */   private int entryConditions; private int money; private ArrayList<RewardBean> itemReward; private int freeTimes; private int serverType; private String beginTime;
/*    */   private String endTime;
/*    */   
/*    */   public String getName() {
/* 16 */     return this.name;
/*    */   }
/*    */   private int serverTypeb; private String beginTimeb; private String endTimeb;
/*    */   private int day;
/*    */   
/*    */   public ArrayList<Integer> getActReward() {
/* 22 */     return this.actReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEntryConditions() {
/* 28 */     return this.entryConditions;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMoney() {
/* 34 */     return this.money;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList<RewardBean> getItemReward() {
/* 40 */     return this.itemReward;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFreeTimes() {
/* 46 */     return this.freeTimes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getServerType() {
/* 52 */     return this.serverType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTime() {
/* 58 */     return this.beginTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTime() {
/* 64 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getServerTypeb() {
/* 70 */     return this.serverTypeb;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getBeginTimeb() {
/* 76 */     return this.beginTimeb;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEndTimeb() {
/* 82 */     return this.endTimeb;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getDay() {
/* 88 */     return this.day;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     String retVal = "actId= " + this.actId + ", name= " + this.name + ", actReward= " + this.actReward + ", entryConditions= " + this.entryConditions + ", money= " + this.money + ", itemReward= " + this.itemReward + ", freeTimes= " + this.freeTimes + ", serverType= " + this.serverType + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", serverTypeb= " + this.serverTypeb + ", beginTimeb= " + this.beginTimeb + ", endTimeb= " + this.endTimeb + ", day= " + this.day;
/* 94 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\LuckylistBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */