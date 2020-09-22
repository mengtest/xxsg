/*     */ package com.linlongyx.sanguo.webgame.config.bean;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class RankingActivityBean {
/*     */   private int id;
/*     */   private int serverType;
/*     */   private int distinguish;
/*     */   
/*     */   public int getId() {
/*  10 */     return this.id;
/*     */   }
/*     */   private int openType; private int type; private String tagName; private int countType; private int playerNum; private int beginTime; private int endTime;
/*     */   private int entryConditions;
/*     */   
/*     */   public int getServerType() {
/*  16 */     return this.serverType;
/*     */   }
/*     */   private ArrayList<Integer> rankReward; private ArrayList<Integer> personalReward; private int detailsActivities;
/*     */   private String conditionsParticipation;
/*     */   
/*     */   public int getDistinguish() {
/*  22 */     return this.distinguish;
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
/*     */   public int getBeginTime() {
/*  58 */     return this.beginTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndTime() {
/*  64 */     return this.endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEntryConditions() {
/*  70 */     return this.entryConditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getRankReward() {
/*  76 */     return this.rankReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getPersonalReward() {
/*  82 */     return this.personalReward;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDetailsActivities() {
/*  88 */     return this.detailsActivities;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getConditionsParticipation() {
/*  94 */     return this.conditionsParticipation;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     String retVal = "id= " + this.id + ", serverType= " + this.serverType + ", distinguish= " + this.distinguish + ", openType= " + this.openType + ", type= " + this.type + ", tagName= " + this.tagName + ", countType= " + this.countType + ", playerNum= " + this.playerNum + ", beginTime= " + this.beginTime + ", endTime= " + this.endTime + ", entryConditions= " + this.entryConditions + ", rankReward= " + this.rankReward + ", personalReward= " + this.personalReward + ", detailsActivities= " + this.detailsActivities + ", conditionsParticipation= " + this.conditionsParticipation;
/* 100 */     return retVal;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\bean\RankingActivityBean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */