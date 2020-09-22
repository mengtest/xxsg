/*     */ package com.linlongyx.sanguo.webgame.app.task;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_task", prefix = "task_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class TaskEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private int id;
/*     */   private long schedule;
/*  21 */   private Map<Integer, Integer> chapterReward = new HashMap<>();
/*  22 */   private Map<Integer, Integer> insMap = new HashMap<>();
/*     */   private int chapter;
/*     */   private int point;
/*  25 */   private Map<Integer, Long> dailyProcess = new HashMap<>();
/*  26 */   private Set<Integer> dailyDone = new HashSet<>();
/*  27 */   private Set<Integer> dailyReward = new HashSet<>();
/*  28 */   private Set<Integer> pointReward = new HashSet<>();
/*     */   private int guideId;
/*     */   private int fixedStatus;
/*     */   private int firstFail;
/*  32 */   private Map<Integer, Integer> askReward = new HashMap<>();
/*     */   private int follow;
/*     */   private boolean realName;
/*  35 */   private Map<Integer, Integer> channelGift = new HashMap<>();
/*  36 */   private Map<Integer, Integer> channelTime = new HashMap<>();
/*  37 */   private Map<Integer, Integer> platformReward = new HashMap<>();
/*  38 */   private Map<Integer, Long> previewValue = new HashMap<>();
/*  39 */   private Set<Integer> previewReward = new HashSet<>();
/*  40 */   private Map<Integer, Integer> findRewardMax = new HashMap<>();
/*     */   
/*     */   public TaskEntity(long playerId) {
/*  43 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  47 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getId() {
/*  51 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/*  55 */     this.id = id;
/*     */   }
/*     */   
/*     */   public long getSchedule() {
/*  59 */     return this.schedule;
/*     */   }
/*     */   
/*     */   public void setSchedule(long schedule) {
/*  63 */     this.schedule = schedule;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChapterReward() {
/*  67 */     return this.chapterReward;
/*     */   }
/*     */   
/*     */   public void setChapterReward(Map<Integer, Integer> chapterReward) {
/*  71 */     this.chapterReward = chapterReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getInsMap() {
/*  75 */     return this.insMap;
/*     */   }
/*     */   
/*     */   public void setInsMap(Map<Integer, Integer> insMap) {
/*  79 */     this.insMap = insMap;
/*     */   }
/*     */   
/*     */   public int getPoint() {
/*  83 */     return this.point;
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/*  87 */     this.point = point;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getDailyProcess() {
/*  91 */     return this.dailyProcess;
/*     */   }
/*     */   
/*     */   public void setDailyProcess(Map<Integer, Long> dailyProcess) {
/*  95 */     this.dailyProcess = dailyProcess;
/*     */   }
/*     */   
/*     */   public Set<Integer> getDailyDone() {
/*  99 */     return this.dailyDone;
/*     */   }
/*     */   
/*     */   public void setDailyDone(Set<Integer> dailyDone) {
/* 103 */     this.dailyDone = dailyDone;
/*     */   }
/*     */   
/*     */   public Set<Integer> getDailyReward() {
/* 107 */     return this.dailyReward;
/*     */   }
/*     */   
/*     */   public void setDailyReward(Set<Integer> dailyReward) {
/* 111 */     this.dailyReward = dailyReward;
/*     */   }
/*     */   
/*     */   public Set<Integer> getPointReward() {
/* 115 */     return this.pointReward;
/*     */   }
/*     */   
/*     */   public void setPointReward(Set<Integer> pointReward) {
/* 119 */     this.pointReward = pointReward;
/*     */   }
/*     */   
/*     */   public int getChapter() {
/* 123 */     return this.chapter;
/*     */   }
/*     */   
/*     */   public void setChapter(int chapter) {
/* 127 */     this.chapter = chapter;
/*     */   }
/*     */   
/*     */   public int getGuideId() {
/* 131 */     return this.guideId;
/*     */   }
/*     */   
/*     */   public void setGuideId(int guideId) {
/* 135 */     this.guideId = guideId;
/*     */   }
/*     */   
/*     */   public int getFixedStatus() {
/* 139 */     return this.fixedStatus;
/*     */   }
/*     */   
/*     */   public void setFixedStatus(int fixedStatus) {
/* 143 */     this.fixedStatus = fixedStatus;
/*     */   }
/*     */   
/*     */   public int getFirstFail() {
/* 147 */     return this.firstFail;
/*     */   }
/*     */   
/*     */   public void setFirstFail(int firstFail) {
/* 151 */     this.firstFail = firstFail;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getAskReward() {
/* 155 */     return this.askReward;
/*     */   }
/*     */   
/*     */   public void setAskReward(Map<Integer, Integer> askReward) {
/* 159 */     this.askReward = askReward;
/*     */   }
/*     */   
/*     */   public int getFollow() {
/* 163 */     return this.follow;
/*     */   }
/*     */   
/*     */   public void setFollow(int follow) {
/* 167 */     this.follow = follow;
/*     */   }
/*     */   
/*     */   public boolean isRealName() {
/* 171 */     return this.realName;
/*     */   }
/*     */   
/*     */   public void setRealName(boolean realName) {
/* 175 */     this.realName = realName;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChannelGift() {
/* 179 */     return this.channelGift;
/*     */   }
/*     */   
/*     */   public void setChannelGift(Map<Integer, Integer> channelGift) {
/* 183 */     this.channelGift = channelGift;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChannelTime() {
/* 187 */     return this.channelTime;
/*     */   }
/*     */   
/*     */   public void setChannelTime(Map<Integer, Integer> channelTime) {
/* 191 */     this.channelTime = channelTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getPlatformReward() {
/* 195 */     return this.platformReward;
/*     */   }
/*     */   
/*     */   public void setPlatformReward(Map<Integer, Integer> platformReward) {
/* 199 */     this.platformReward = platformReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getPreviewValue() {
/* 203 */     return this.previewValue;
/*     */   }
/*     */   
/*     */   public void setPreviewValue(Map<Integer, Long> previewValue) {
/* 207 */     this.previewValue = previewValue;
/*     */   }
/*     */   
/*     */   public Set<Integer> getPreviewReward() {
/* 211 */     return this.previewReward;
/*     */   }
/*     */   
/*     */   public void setPreviewReward(Set<Integer> previewReward) {
/* 215 */     this.previewReward = previewReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getFindRewardMax() {
/* 219 */     return this.findRewardMax;
/*     */   }
/*     */   
/*     */   public void setFindRewardMax(Map<Integer, Integer> findRewardMax) {
/* 223 */     this.findRewardMax = findRewardMax;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 228 */     return "TaskEntity{playerId=" + this.playerId + ", id=" + this.id + ", schedule=" + this.schedule + ", chapterReward=" + this.chapterReward + ", insMap=" + this.insMap + ", point=" + this.point + ", dailyProcess=" + this.dailyProcess + ", dailyDone=" + this.dailyDone + ", dailyReward=" + this.dailyReward + ", pointReward=" + this.pointReward + ", chapter=" + this.chapter + ", guideId=" + this.guideId + ", fixedStatus=" + this.fixedStatus + ", firstFail=" + this.firstFail + ", askReward=" + this.askReward + ", follow=" + this.follow + ", realName=" + this.realName + ", channelGift=" + this.channelGift + ", channelTime=" + this.channelTime + ", platformReward=" + this.platformReward + ", previewValue=" + this.previewValue + ", previewReward=" + this.previewReward + ", findRewardMax=" + this.findRewardMax + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\task\TaskEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */