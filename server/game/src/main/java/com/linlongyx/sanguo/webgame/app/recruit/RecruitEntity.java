/*     */ package com.linlongyx.sanguo.webgame.app.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_recruit", prefix = "recruit_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class RecruitEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private int nextFreeTime;
/*     */   private int currFreeCount;
/*     */   private int recruitTimes;
/*     */   private int time;
/*     */   private int ccyRecruitTimes;
/*     */   private int totalRecruitTimes;
/*     */   private int score;
/*  27 */   private ArrayList<Integer> boxList = new ArrayList<>();
/*     */   private int free;
/*  29 */   private ArrayList<Integer> today = new ArrayList<>();
/*  30 */   private ArrayList<Integer> tommorow = new ArrayList<>();
/*     */   private int refreshNum;
/*     */   private int tenCCYRecruit;
/*  33 */   private ArrayList<KeyValue> firstGetList = new ArrayList<>();
/*     */   private long rebateScore;
/*  35 */   private Map<Integer, Integer> rebateReward = new HashMap<>();
/*     */   
/*     */   private int endTime;
/*     */   private int goldScore;
/*  39 */   private ArrayList<Integer> goldBoxList = new ArrayList<>();
/*     */   private int goldFree;
/*  41 */   private ArrayList<Integer> goldToday = new ArrayList<>();
/*  42 */   private ArrayList<Integer> goldTommorow = new ArrayList<>();
/*     */   private int goldRefreshNum;
/*     */   private int goldRecruitTimes;
/*     */   
/*     */   public RecruitEntity(long playerId) {
/*  47 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  51 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getNextFreeTime() {
/*  55 */     return this.nextFreeTime;
/*     */   }
/*     */   
/*     */   public void setNextFreeTime(int nextFreeTime) {
/*  59 */     this.nextFreeTime = nextFreeTime;
/*     */   }
/*     */   
/*     */   public int getCurrFreeCount() {
/*  63 */     return this.currFreeCount;
/*     */   }
/*     */   
/*     */   public void setCurrFreeCount(int currFreeCount) {
/*  67 */     this.currFreeCount = currFreeCount;
/*     */   }
/*     */   
/*     */   public int getRecruitTimes() {
/*  71 */     return this.recruitTimes;
/*     */   }
/*     */   
/*     */   public void setRecruitTimes(int recruitTimes) {
/*  75 */     this.recruitTimes = recruitTimes;
/*     */   }
/*     */   
/*     */   public int getTime() {
/*  79 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(int time) {
/*  83 */     this.time = time;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCcyRecruitTimes() {
/*  88 */     return this.ccyRecruitTimes;
/*     */   }
/*     */   
/*     */   public void setCcyRecruitTimes(int ccyRecruitTimes) {
/*  92 */     this.ccyRecruitTimes = ccyRecruitTimes;
/*     */   }
/*     */   
/*     */   public int getTotalRecruitTimes() {
/*  96 */     return this.totalRecruitTimes;
/*     */   }
/*     */   
/*     */   public void setTotalRecruitTimes(int totalRecruitTimes) {
/* 100 */     this.totalRecruitTimes = totalRecruitTimes;
/*     */   }
/*     */   
/*     */   public int getScore() {
/* 104 */     return this.score;
/*     */   }
/*     */   
/*     */   public void setScore(int score) {
/* 108 */     this.score = score;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getBoxList() {
/* 112 */     return this.boxList;
/*     */   }
/*     */   
/*     */   public void setBoxList(ArrayList<Integer> boxList) {
/* 116 */     this.boxList = boxList;
/*     */   }
/*     */   
/*     */   public int getFree() {
/* 120 */     return this.free;
/*     */   }
/*     */   
/*     */   public void setFree(int free) {
/* 124 */     this.free = free;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getToday() {
/* 128 */     return this.today;
/*     */   }
/*     */   
/*     */   public void setToday(ArrayList<Integer> today) {
/* 132 */     this.today = today;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getTommorow() {
/* 136 */     return this.tommorow;
/*     */   }
/*     */   
/*     */   public void setTommorow(ArrayList<Integer> tommorow) {
/* 140 */     this.tommorow = tommorow;
/*     */   }
/*     */   
/*     */   public int getRefreshNum() {
/* 144 */     return this.refreshNum;
/*     */   }
/*     */   
/*     */   public void setRefreshNum(int refreshNum) {
/* 148 */     this.refreshNum = refreshNum;
/*     */   }
/*     */   
/*     */   public int getTenCCYRecruit() {
/* 152 */     return this.tenCCYRecruit;
/*     */   }
/*     */   
/*     */   public void setTenCCYRecruit(int tenCCYRecruit) {
/* 156 */     this.tenCCYRecruit = tenCCYRecruit;
/*     */   }
/*     */   
/*     */   public ArrayList<KeyValue> getFirstGetList() {
/* 160 */     return this.firstGetList;
/*     */   }
/*     */   
/*     */   public void setFirstGetList(ArrayList<KeyValue> firstGetList) {
/* 164 */     this.firstGetList = firstGetList;
/*     */   }
/*     */   
/*     */   public long getRebateScore() {
/* 168 */     return this.rebateScore;
/*     */   }
/*     */   
/*     */   public void setRebateScore(long rebateScore) {
/* 172 */     this.rebateScore = rebateScore;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getRebateReward() {
/* 176 */     return this.rebateReward;
/*     */   }
/*     */   
/*     */   public void setRebateReward(Map<Integer, Integer> rebateReward) {
/* 180 */     this.rebateReward = rebateReward;
/*     */   }
/*     */   
/*     */   public int getEndTime() {
/* 184 */     return this.endTime;
/*     */   }
/*     */   
/*     */   public void setEndTime(int endTime) {
/* 188 */     this.endTime = endTime;
/*     */   }
/*     */   
/*     */   public int getGoldScore() {
/* 192 */     return this.goldScore;
/*     */   }
/*     */   
/*     */   public void setGoldScore(int goldScore) {
/* 196 */     this.goldScore = goldScore;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getGoldBoxList() {
/* 200 */     return this.goldBoxList;
/*     */   }
/*     */   
/*     */   public void setGoldBoxList(ArrayList<Integer> goldBoxList) {
/* 204 */     this.goldBoxList = goldBoxList;
/*     */   }
/*     */   
/*     */   public int getGoldFree() {
/* 208 */     return this.goldFree;
/*     */   }
/*     */   
/*     */   public void setGoldFree(int goldFree) {
/* 212 */     this.goldFree = goldFree;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getGoldToday() {
/* 216 */     return this.goldToday;
/*     */   }
/*     */   
/*     */   public void setGoldToday(ArrayList<Integer> goldToday) {
/* 220 */     this.goldToday = goldToday;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getGoldTommorow() {
/* 224 */     return this.goldTommorow;
/*     */   }
/*     */   
/*     */   public void setGoldTommorow(ArrayList<Integer> goldTommorow) {
/* 228 */     this.goldTommorow = goldTommorow;
/*     */   }
/*     */   
/*     */   public int getGoldRefreshNum() {
/* 232 */     return this.goldRefreshNum;
/*     */   }
/*     */   
/*     */   public void setGoldRefreshNum(int goldRefreshNum) {
/* 236 */     this.goldRefreshNum = goldRefreshNum;
/*     */   }
/*     */   
/*     */   public int getGoldRecruitTimes() {
/* 240 */     return this.goldRecruitTimes;
/*     */   }
/*     */   
/*     */   public void setGoldRecruitTimes(int goldRecruitTimes) {
/* 244 */     this.goldRecruitTimes = goldRecruitTimes;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 249 */     return "RecruitEntity{playerId=" + this.playerId + ", nextFreeTime=" + this.nextFreeTime + ", currFreeCount=" + this.currFreeCount + ", recruitTimes=" + this.recruitTimes + ", time=" + this.time + ", ccyRecruitTimes=" + this.ccyRecruitTimes + ", totalRecruitTimes=" + this.totalRecruitTimes + ", score=" + this.score + ", boxList=" + this.boxList + ", free=" + this.free + ", today=" + this.today + ", tommorow=" + this.tommorow + ", refreshNum=" + this.refreshNum + ", tenCCYRecruit=" + this.tenCCYRecruit + ", firstGetList=" + this.firstGetList + ", rebateScore=" + this.rebateScore + ", rebateReward=" + this.rebateReward + ", endTime=" + this.endTime + ", goldScore=" + this.goldScore + ", goldBoxList=" + this.goldBoxList + ", goldFree=" + this.goldFree + ", goldToday=" + this.goldToday + ", goldTommorow=" + this.goldTommorow + ", goldRefreshNum=" + this.goldRefreshNum + ", goldRecruitTimes=" + this.goldRecruitTimes + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\recruit\RecruitEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */