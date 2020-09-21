/*     */ package com.linlongyx.sanguo.webgame.app.extend;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ @Table(tableName = "tb_extend", prefix = "extend_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class ExtendEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private int battleMaxCount;
/*  19 */   private Map<Integer, Long> totalCurrency = new HashMap<>();
/*  20 */   private Map<Integer, Long> consumeCurrency = new HashMap<>();
/*     */   private int bagSize;
/*     */   private int offlineSec;
/*  23 */   private Map<Integer, ArrayList<Integer>> handbookMap = new HashMap<>();
/*     */   private int zeroResetDate;
/*     */   private int weekResetTime;
/*     */   private int combatTimes;
/*     */   private int combatCostTimes;
/*  28 */   private Map<Integer, Integer> wpHandbook = new HashMap<>();
/*     */   private long equipId;
/*  30 */   private Set<Integer> functionIds = new HashSet<>();
/*     */   private long partnerId;
/*  32 */   private Set<Integer> firstReChargeSet = new HashSet<>();
/*  33 */   private Set<Integer> firstReward = new HashSet<>();
/*     */   private long todayRecharge;
/*     */   private int speedRadio;
/*     */   private long totalChangeBoss;
/*     */   private long totalChargeCCB;
/*     */   private long totalComPartner;
/*  39 */   private Set<Integer> newFirstReward = new HashSet<>();
/*  40 */   private Set<Integer> newFirstCharge = new HashSet<>();
/*  41 */   private Map<Integer, Integer> groupReward = new HashMap<>();
/*  42 */   private Map<Integer, Integer> wanderReward = new HashMap<>();
/*  43 */   private Set<Integer> lastWanderReward = new HashSet<>();
/*     */   private long wanderEndtime;
/*     */   private boolean choose;
/*     */   private int skipTimes;
/*     */   private long todayFirstCharge;
/*  48 */   private Map<Integer, Integer> todayFirstReward = new HashMap<>();
/*     */   private long dailyConsume;
/*     */   private int buyGoldTimes;
/*     */   private int buyGoldCost;
/*  52 */   private Set<Integer> questionnaire = new HashSet<>();
/*  53 */   private String clientSets = "";
/*     */   
/*  55 */   private int shortCut = 0;
/*     */   
/*     */   public ExtendEntity(long playerId) {
/*  58 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  62 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public int getBattleMaxCount() {
/*  66 */     return this.battleMaxCount;
/*     */   }
/*     */   
/*     */   public void setBattleMaxCount(int battleMaxCount) {
/*  70 */     this.battleMaxCount = battleMaxCount;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getTotalCurrency() {
/*  74 */     return this.totalCurrency;
/*     */   }
/*     */   
/*     */   public void setTotalCurrency(Map<Integer, Long> totalCurrency) {
/*  78 */     this.totalCurrency = totalCurrency;
/*     */   }
/*     */   
/*     */   public int getBagSize() {
/*  82 */     return this.bagSize;
/*     */   }
/*     */   
/*     */   public void setBagSize(int bagSize) {
/*  86 */     this.bagSize = bagSize;
/*     */   }
/*     */   
/*     */   public int getOfflineSec() {
/*  90 */     return this.offlineSec;
/*     */   }
/*     */   
/*     */   public void setOfflineSec(int offlineSec) {
/*  94 */     this.offlineSec = offlineSec;
/*     */   }
/*     */   
/*     */   public Map<Integer, ArrayList<Integer>> getHandbookMap() {
/*  98 */     return this.handbookMap;
/*     */   }
/*     */   
/*     */   public void setHandbookMap(Map<Integer, ArrayList<Integer>> handbookMap) {
/* 102 */     this.handbookMap = handbookMap;
/*     */   }
/*     */   
/*     */   public int getZeroResetDate() {
/* 106 */     return this.zeroResetDate;
/*     */   }
/*     */   
/*     */   public void setZeroResetDate(int zeroResetDate) {
/* 110 */     this.zeroResetDate = zeroResetDate;
/*     */   }
/*     */   
/*     */   public int getCombatTimes() {
/* 114 */     return this.combatTimes;
/*     */   }
/*     */   
/*     */   public void setCombatTimes(int combatTimes) {
/* 118 */     this.combatTimes = combatTimes;
/*     */   }
/*     */   
/*     */   public int getCombatCostTimes() {
/* 122 */     return this.combatCostTimes;
/*     */   }
/*     */   
/*     */   public void setCombatCostTimes(int combatCostTimes) {
/* 126 */     this.combatCostTimes = combatCostTimes;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getConsumeCurrency() {
/* 130 */     return this.consumeCurrency;
/*     */   }
/*     */   
/*     */   public void setConsumeCurrency(Map<Integer, Long> consumeCurrency) {
/* 134 */     this.consumeCurrency = consumeCurrency;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getWpHandbook() {
/* 138 */     return this.wpHandbook;
/*     */   }
/*     */   
/*     */   public void setWpHandbook(Map<Integer, Integer> wpHandbook) {
/* 142 */     this.wpHandbook = wpHandbook;
/*     */   }
/*     */   
/*     */   public long getEquipId() {
/* 146 */     return this.equipId;
/*     */   }
/*     */   
/*     */   public void setEquipId(long equipId) {
/* 150 */     this.equipId = equipId;
/*     */   }
/*     */   
/*     */   public Set<Integer> getFunctionIds() {
/* 154 */     return this.functionIds;
/*     */   }
/*     */   
/*     */   public void setFunctionIds(Set<Integer> functionIds) {
/* 158 */     this.functionIds = functionIds;
/*     */   }
/*     */   
/*     */   public long getPartnerId() {
/* 162 */     return this.partnerId;
/*     */   }
/*     */   
/*     */   public void setPartnerId(long partnerId) {
/* 166 */     this.partnerId = partnerId;
/*     */   }
/*     */   
/*     */   public Set<Integer> getFirstReChargeSet() {
/* 170 */     return this.firstReChargeSet;
/*     */   }
/*     */   
/*     */   public void setFirstReChargeSet(Set<Integer> firstReChargeSet) {
/* 174 */     this.firstReChargeSet = firstReChargeSet;
/*     */   }
/*     */   
/*     */   public Set<Integer> getFirstReward() {
/* 178 */     return this.firstReward;
/*     */   }
/*     */   
/*     */   public void setFirstReward(Set<Integer> firstReward) {
/* 182 */     this.firstReward = firstReward;
/*     */   }
/*     */   
/*     */   public long getTodayRecharge() {
/* 186 */     return this.todayRecharge;
/*     */   }
/*     */   
/*     */   public void setTodayRecharge(long todayRecharge) {
/* 190 */     this.todayRecharge = todayRecharge;
/*     */   }
/*     */   
/*     */   public int getSpeedRadio() {
/* 194 */     return this.speedRadio;
/*     */   }
/*     */   
/*     */   public void setSpeedRadio(int speedRadio) {
/* 198 */     this.speedRadio = speedRadio;
/*     */   }
/*     */   
/*     */   public long getTotalChangeBoss() {
/* 202 */     return this.totalChangeBoss;
/*     */   }
/*     */   
/*     */   public void setTotalChangeBoss(long totalChangeBoss) {
/* 206 */     this.totalChangeBoss = totalChangeBoss;
/*     */   }
/*     */   
/*     */   public long getTotalChargeCCB() {
/* 210 */     return this.totalChargeCCB;
/*     */   }
/*     */   
/*     */   public void setTotalChargeCCB(long totalChargeCCB) {
/* 214 */     this.totalChargeCCB = totalChargeCCB;
/*     */   }
/*     */   
/*     */   public long getTotalComPartner() {
/* 218 */     return this.totalComPartner;
/*     */   }
/*     */   
/*     */   public void setTotalComPartner(long totalComPartner) {
/* 222 */     this.totalComPartner = totalComPartner;
/*     */   }
/*     */   
/*     */   public Set<Integer> getNewFirstReward() {
/* 226 */     return this.newFirstReward;
/*     */   }
/*     */   
/*     */   public void setNewFirstReward(Set<Integer> newFirstReward) {
/* 230 */     this.newFirstReward = newFirstReward;
/*     */   }
/*     */   
/*     */   public Set<Integer> getNewFirstCharge() {
/* 234 */     return this.newFirstCharge;
/*     */   }
/*     */   
/*     */   public void setNewFirstCharge(Set<Integer> newFirstCharge) {
/* 238 */     this.newFirstCharge = newFirstCharge;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getGroupReward() {
/* 242 */     return this.groupReward;
/*     */   }
/*     */   
/*     */   public void setGroupReward(Map<Integer, Integer> groupReward) {
/* 246 */     this.groupReward = groupReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getWanderReward() {
/* 250 */     return this.wanderReward;
/*     */   }
/*     */   
/*     */   public void setWanderReward(Map<Integer, Integer> wanderReward) {
/* 254 */     this.wanderReward = wanderReward;
/*     */   }
/*     */   
/*     */   public boolean isChoose() {
/* 258 */     return this.choose;
/*     */   }
/*     */   
/*     */   public void setChoose(boolean choose) {
/* 262 */     this.choose = choose;
/*     */   }
/*     */   
/*     */   public int getSkipTimes() {
/* 266 */     return this.skipTimes;
/*     */   }
/*     */   
/*     */   public void setSkipTimes(int skipTimes) {
/* 270 */     this.skipTimes = skipTimes;
/*     */   }
/*     */   
/*     */   public long getTodayFirstCharge() {
/* 274 */     return this.todayFirstCharge;
/*     */   }
/*     */   
/*     */   public void setTodayFirstCharge(long todayFirstCharge) {
/* 278 */     this.todayFirstCharge = todayFirstCharge;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTodayFirstReward() {
/* 282 */     return this.todayFirstReward;
/*     */   }
/*     */   
/*     */   public void setTodayFirstReward(Map<Integer, Integer> todayFirstReward) {
/* 286 */     this.todayFirstReward = todayFirstReward;
/*     */   }
/*     */   
/*     */   public long getDailyConsume() {
/* 290 */     return this.dailyConsume;
/*     */   }
/*     */   
/*     */   public void setDailyConsume(long dailyConsume) {
/* 294 */     this.dailyConsume = dailyConsume;
/*     */   }
/*     */   
/*     */   public int getBuyGoldTimes() {
/* 298 */     return this.buyGoldTimes;
/*     */   }
/*     */   
/*     */   public void setBuyGoldTimes(int buyGoldTimes) {
/* 302 */     this.buyGoldTimes = buyGoldTimes;
/*     */   }
/*     */   
/*     */   public int getBuyGoldCost() {
/* 306 */     return this.buyGoldCost;
/*     */   }
/*     */   
/*     */   public void setBuyGoldCost(int buyGoldCost) {
/* 310 */     this.buyGoldCost = buyGoldCost;
/*     */   }
/*     */   
/*     */   public String getClientSets() {
/* 314 */     return this.clientSets;
/*     */   }
/*     */   
/*     */   public void setClientSets(String value) {
/* 318 */     this.clientSets = value;
/*     */   }
/*     */   
/*     */   public Set<Integer> getQuestionnaire() {
/* 322 */     return this.questionnaire;
/*     */   }
/*     */   
/*     */   public void setQuestionnaire(Set<Integer> questionnaire) {
/* 326 */     this.questionnaire = questionnaire;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getShortCut() {
/* 331 */     return this.shortCut;
/*     */   }
/*     */   
/*     */   public void setShortCut(int shortCut) {
/* 335 */     this.shortCut = shortCut;
/*     */   }
/*     */   
/*     */   public long getWanderEndtime() {
/* 339 */     return this.wanderEndtime;
/*     */   }
/*     */   
/*     */   public void setWanderEndtime(long wanderEndtime) {
/* 343 */     this.wanderEndtime = wanderEndtime;
/*     */   }
/*     */   
/*     */   public Set<Integer> getLastWanderReward() {
/* 347 */     return this.lastWanderReward;
/*     */   }
/*     */   
/*     */   public void setLastWanderReward(Set<Integer> lastWanderReward) {
/* 351 */     this.lastWanderReward = lastWanderReward;
/*     */   }
/*     */   
/*     */   public int getWeekResetTime() {
/* 355 */     return this.weekResetTime;
/*     */   }
/*     */   
/*     */   public void setWeekResetTime(int weekResetTime) {
/* 359 */     this.weekResetTime = weekResetTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 364 */     return "ExtendEntity{playerId=" + this.playerId + ", battleMaxCount=" + this.battleMaxCount + ", totalCurrency=" + this.totalCurrency + ", consumeCurrency=" + this.consumeCurrency + ", bagSize=" + this.bagSize + ", offlineSec=" + this.offlineSec + ", handbookMap=" + this.handbookMap + ", zeroResetDate=" + this.zeroResetDate + ", weekResetTime=" + this.weekResetTime + ", combatTimes=" + this.combatTimes + ", combatCostTimes=" + this.combatCostTimes + ", wpHandbook=" + this.wpHandbook + ", equipId=" + this.equipId + ", functionIds=" + this.functionIds + ", partnerId=" + this.partnerId + ", firstReChargeSet=" + this.firstReChargeSet + ", firstReward=" + this.firstReward + ", todayRecharge=" + this.todayRecharge + ", speedRadio=" + this.speedRadio + ", totalChangeBoss=" + this.totalChangeBoss + ", totalChargeCCB=" + this.totalChargeCCB + ", totalComPartner=" + this.totalComPartner + ", newFirstReward=" + this.newFirstReward + ", newFirstCharge=" + this.newFirstCharge + ", groupReward=" + this.groupReward + ", wanderReward=" + this.wanderReward + ", lastWanderReward=" + this.lastWanderReward + ", wanderEndtime=" + this.wanderEndtime + ", choose=" + this.choose + ", skipTimes=" + this.skipTimes + ", todayFirstCharge=" + this.todayFirstCharge + ", todayFirstReward=" + this.todayFirstReward + ", dailyConsume=" + this.dailyConsume + ", buyGoldTimes=" + this.buyGoldTimes + ", buyGoldCost=" + this.buyGoldCost + ", questionnaire=" + this.questionnaire + ", clientSets='" + this.clientSets + '\'' + ", shortCut=" + this.shortCut + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\extend\ExtendEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */