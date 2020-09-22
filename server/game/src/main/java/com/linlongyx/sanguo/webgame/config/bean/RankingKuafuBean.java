/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ 
/*     */ public class RankingKuafuBean {
/*     */   private int id;
/*     */   private int seriesId;
/*     */   private int showType;
/*     */   private int openType;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int type; private String tagName; private int countType; private int playerNum; private int day; private ArrayList<Integer> serverList; private String beginTime; private String endTime;
/*     */   private String closeTime;
/*     */   
/*     */   public int getSeriesId() {
/*  16 */     return this.seriesId;
/*     */   }
/*     */   private int entryConditions; private ArrayList<Integer> rankReward; private ArrayList<Integer> personalReward; private int detailsActivities;
/*     */   private String conditionsParticipation;
/*     */   
/*     */   public int getShowType() {
/*  22 */     return this.showType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpenType() {
/*  28 */     return this.openType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/*  34 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTagName() {
/*  40 */     return this.tagName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCountType() {
/*  46 */     return this.countType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPlayerNum() {
/*  52 */     return this.playerNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDay() {
/*  58 */     return this.day;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getServerList() {
/*  64 */     return this.serverList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBeginTime() {
/*  70 */     return this.beginTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndTime() {
/*  76 */     return this.endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCloseTime() {
/*  82 */     return this.closeTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEntryConditions() {
/*  88 */     return this.entryConditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRankReward() {
/*  94 */     return this.rankReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getPersonalReward() {
/* 100 */     return this.personalReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDetailsActivities() {
/* 106 */     return this.detailsActivities;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConditionsParticipation() {
/* 112 */     return this.conditionsParticipation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 117 */     String retVal = "id= " + this.id + ", seriesId= " + this.seriesId + ", showType= " + this.showType + ", openType= " + this.openType + ", type= " + this.type + ", tagName= " + this.tagName + ", countType= " + this.countType + ", playerNum= " + this.playerNum + ", day= " + this.day + ", serverList= " + this.serverList + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", closeTime= " + this.closeTime + ", entryConditions= " + this.entryConditions + ", rankReward= " + this.rankReward + ", personalReward= " + this.personalReward + ", detailsActivities= " + this.detailsActivities + ", conditionsParticipation= " + this.conditionsParticipation;
/* 118 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RankingKuafuBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */