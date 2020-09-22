/*     */ package com.linlongyx.sanguo.webgame.app.player;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ @Table(tableName = "tb_player", prefix = "player_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class PlayerEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*     */   private long userId;
/*     */   private String playerName;
/*     */   private short level;
/*     */   private byte sex;
/*     */   private int sceneId;
/*     */   private String head;
/*  25 */   private ArrayList<Long> currencies = new ArrayList<>();
/*  26 */   private ArrayList<Long> fighters = new ArrayList<>();
/*     */   private int createTime;
/*     */   private int mailIdCount;
/*     */   private int pubIdCount;
/*  30 */   private Map<Integer, ODTime> status = new HashMap<>();
/*     */   private int breakthroughs;
/*  32 */   private ArrayList<Integer> talents = new ArrayList<>();
/*     */   private int desLv;
/*     */   private int desProgress;
/*     */   private int stars;
/*  36 */   private Map<Integer, Long> equips = new HashMap<>();
/*     */   private byte vip;
/*  38 */   private List<Integer> vipReward = new ArrayList<>();
/*     */   private int loginOutTime;
/*     */   private int leaderId;
/*     */   private long fightValue;
/*     */   private long totalValue;
/*     */   private int quality;
/*  44 */   private ArrayList<Integer> worships = new ArrayList<>();
/*  45 */   private ArrayList<Integer> isWorship = new ArrayList<>();
/*  46 */   private Map<Integer, Integer> activeTitles = new HashMap<>();
/*     */   
/*     */   private int wearTitle;
/*     */   private int wearFashion;
/*     */   private int onlineTime;
/*  51 */   private ArrayList<Integer> onlineReward = new ArrayList<>();
/*     */   private int todayLevel;
/*     */   private long firstHand;
/*     */   private int soulLevel;
/*  55 */   private ArrayList<Integer> soulsFighter = new ArrayList<>();
/*     */   private long talisman;
/*  57 */   private Map<Integer, Map<Integer, Long>> reincarnationMap = new HashMap<>();
/*  58 */   private HashSet<Integer> reincarnationIds = new HashSet<>();
/*     */   
/*     */   public PlayerEntity(long playerId) {
/*  61 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  65 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  69 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  73 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public String getPlayerName() {
/*  77 */     return this.playerName;
/*     */   }
/*     */   
/*     */   public void setPlayerName(String playerName) {
/*  81 */     this.playerName = playerName;
/*     */   }
/*     */   
/*     */   public short getLevel() {
/*  85 */     return this.level;
/*     */   }
/*     */   
/*     */   public void setLevel(short level) {
/*  89 */     this.level = level;
/*     */   }
/*     */   
/*     */   public byte getSex() {
/*  93 */     return this.sex;
/*     */   }
/*     */   
/*     */   public void setSex(byte sex) {
/*  97 */     this.sex = sex;
/*     */   }
/*     */   public String getHead() {
/* 100 */     return this.head;
/*     */   } public void setHead(String head) {
/* 102 */     this.head = head;
/*     */   }
/*     */   public ArrayList<Long> getCurrencies() {
/* 105 */     return this.currencies;
/*     */   }
/*     */   
/*     */   public void setCurrencies(ArrayList<Long> currencies) {
/* 109 */     this.currencies = currencies;
/*     */   }
/*     */   public int getMainSceneId() {
/* 112 */     return this.sceneId;
/*     */   } public void setMainSceneId(int mainSceneId) {
/* 114 */     this.sceneId = mainSceneId;
/*     */   }
/*     */   public int getSceneId() {
/* 117 */     return this.sceneId;
/*     */   }
/*     */   
/*     */   public void setSceneId(int sceneId) {
/* 121 */     this.sceneId = sceneId;
/*     */   }
/*     */   
/*     */   public int getCreateTime() {
/* 125 */     return this.createTime;
/*     */   }
/*     */   
/*     */   public void setCreateTime(int createTime) {
/* 129 */     this.createTime = createTime;
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getFighters() {
/* 133 */     return this.fighters;
/*     */   }
/*     */   
/*     */   public void setFighters(ArrayList<Long> fighters) {
/* 137 */     this.fighters = fighters;
/*     */   }
/*     */   
/*     */   public int getMailIdCount() {
/* 141 */     return this.mailIdCount;
/*     */   }
/*     */   
/*     */   public void setMailIdCount(int mailIdCount) {
/* 145 */     this.mailIdCount = mailIdCount;
/*     */   }
/*     */   
/*     */   public int getPubIdCount() {
/* 149 */     return this.pubIdCount;
/*     */   }
/*     */   
/*     */   public void setPubIdCount(int pubIdCount) {
/* 153 */     this.pubIdCount = pubIdCount;
/*     */   }
/*     */   
/*     */   public Map<Integer, ODTime> getStatus() {
/* 157 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Map<Integer, ODTime> status) {
/* 161 */     this.status = status;
/*     */   }
/*     */   
/*     */   public int getBreakthroughs() {
/* 165 */     return this.breakthroughs;
/*     */   }
/*     */   
/*     */   public void setBreakthroughs(int breakthroughs) {
/* 169 */     this.breakthroughs = breakthroughs;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getTalents() {
/* 173 */     return this.talents;
/*     */   }
/*     */   
/*     */   public void setTalents(ArrayList<Integer> talents) {
/* 177 */     this.talents = talents;
/*     */   }
/*     */   
/*     */   public int getDesLv() {
/* 181 */     return this.desLv;
/*     */   }
/*     */   
/*     */   public void setDesLv(int desLv) {
/* 185 */     this.desLv = desLv;
/*     */   }
/*     */   
/*     */   public int getDesProgress() {
/* 189 */     return this.desProgress;
/*     */   }
/*     */   
/*     */   public void setDesProgress(int desProgress) {
/* 193 */     this.desProgress = desProgress;
/*     */   }
/*     */   
/*     */   public int getStars() {
/* 197 */     return this.stars;
/*     */   }
/*     */   
/*     */   public void setStars(int stars) {
/* 201 */     this.stars = stars;
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getEquips() {
/* 205 */     return this.equips;
/*     */   }
/*     */   
/*     */   public void setEquips(Map<Integer, Long> equips) {
/* 209 */     this.equips = equips;
/*     */   }
/*     */   
/*     */   public byte getVip() {
/* 213 */     return this.vip;
/*     */   }
/*     */   
/*     */   public void setVip(byte vip) {
/* 217 */     this.vip = vip;
/*     */   }
/*     */   
/*     */   public int getLoginOutTime() {
/* 221 */     return this.loginOutTime;
/*     */   }
/*     */   
/*     */   public void setLoginOutTime(int loginOutTime) {
/* 225 */     this.loginOutTime = loginOutTime;
/*     */   }
/*     */   
/*     */   public int getLeaderId() {
/* 229 */     return this.leaderId;
/*     */   }
/*     */   
/*     */   public void setLeaderId(int leaderId) {
/* 233 */     this.leaderId = leaderId;
/*     */   }
/*     */   
/*     */   public long getFightValue() {
/* 237 */     return this.fightValue;
/*     */   }
/*     */   
/*     */   public void setFightValue(long fightValue) {
/* 241 */     this.fightValue = fightValue;
/*     */   }
/*     */   
/*     */   public int getQuality() {
/* 245 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(int quality) {
/* 249 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getWorships() {
/* 253 */     return this.worships;
/*     */   }
/*     */   
/*     */   public void setWorships(ArrayList<Integer> worships) {
/* 257 */     this.worships = worships;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getIsWorship() {
/* 261 */     return this.isWorship;
/*     */   }
/*     */   
/*     */   public void setIsWorship(ArrayList<Integer> isWorship) {
/* 265 */     this.isWorship = isWorship;
/*     */   }
/*     */   
/*     */   public List<Integer> getVipReward() {
/* 269 */     return this.vipReward;
/*     */   }
/*     */   
/*     */   public void setVipReward(List<Integer> vipReward) {
/* 273 */     this.vipReward = vipReward;
/*     */   }
/*     */   
/*     */   public long getTotalValue() {
/* 277 */     return this.totalValue;
/*     */   }
/*     */   
/*     */   public void setTotalValue(long totalValue) {
/* 281 */     this.totalValue = totalValue;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getActiveTitles() {
/* 285 */     return this.activeTitles;
/*     */   }
/*     */   
/*     */   public void setActiveTitles(Map<Integer, Integer> activeTitles) {
/* 289 */     this.activeTitles = activeTitles;
/*     */   }
/*     */   
/*     */   public int getWearTitle() {
/* 293 */     return this.wearTitle;
/*     */   }
/*     */   
/*     */   public void setWearTitle(int wearTitle) {
/* 297 */     this.wearTitle = wearTitle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWearFashion() {
/* 309 */     return this.wearFashion;
/*     */   }
/*     */   
/*     */   public void setWearFashion(int wearFashion) {
/* 313 */     this.wearFashion = wearFashion;
/*     */   }
/*     */   
/*     */   public int getOnlineTime() {
/* 317 */     return this.onlineTime;
/*     */   }
/*     */   
/*     */   public void setOnlineTime(int onlineTime) {
/* 321 */     this.onlineTime = onlineTime;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getOnlineReward() {
/* 325 */     return this.onlineReward;
/*     */   }
/*     */   
/*     */   public void setOnlineReward(ArrayList<Integer> onlineReward) {
/* 329 */     this.onlineReward = onlineReward;
/*     */   }
/*     */   
/*     */   public int getTodayLevel() {
/* 333 */     return this.todayLevel;
/*     */   }
/*     */   
/*     */   public void setTodayLevel(int todayLevel) {
/* 337 */     this.todayLevel = todayLevel;
/*     */   }
/*     */   
/*     */   public long getFirstHand() {
/* 341 */     return this.firstHand;
/*     */   }
/*     */   
/*     */   public void setFirstHand(long firstHand) {
/* 345 */     this.firstHand = firstHand;
/*     */   }
/*     */   
/*     */   public int getSoulLevel() {
/* 349 */     return this.soulLevel;
/*     */   }
/*     */   
/*     */   public void setSoulLevel(int soulLevel) {
/* 353 */     this.soulLevel = soulLevel;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getSoulsFighter() {
/* 357 */     return this.soulsFighter;
/*     */   }
/*     */   
/*     */   public void setSoulsFighter(ArrayList<Integer> soulsFighter) {
/* 361 */     this.soulsFighter = soulsFighter;
/*     */   }
/*     */   
/*     */   public long getTalisman() {
/* 365 */     return this.talisman;
/*     */   }
/*     */   
/*     */   public void setTalisman(long talisman) {
/* 369 */     this.talisman = talisman;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Long>> getReincarnationMap() {
/* 373 */     return this.reincarnationMap;
/*     */   }
/*     */   
/*     */   public void setReincarnationMap(Map<Integer, Map<Integer, Long>> reincarnationMap) {
/* 377 */     this.reincarnationMap = reincarnationMap;
/*     */   }
/*     */   
/*     */   public HashSet<Integer> getReincarnationIds() {
/* 381 */     return this.reincarnationIds;
/*     */   }
/*     */   
/*     */   public void setReincarnationIds(HashSet<Integer> reincarnationIds) {
/* 385 */     this.reincarnationIds = reincarnationIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 390 */     return "PlayerEntity{playerId=" + this.playerId + ", userId=" + this.userId + ", playerName='" + this.playerName + '\'' + ", level=" + this.level + ", sex=" + this.sex + ", sceneId=" + this.sceneId + ", head='" + this.head + '\'' + ", currencies=" + this.currencies + ", fighters=" + this.fighters + ", createTime=" + this.createTime + ", mailIdCount=" + this.mailIdCount + ", pubIdCount=" + this.pubIdCount + ", status=" + this.status + ", breakthroughs=" + this.breakthroughs + ", talents=" + this.talents + ", desLv=" + this.desLv + ", desProgress=" + this.desProgress + ", stars=" + this.stars + ", equips=" + this.equips + ", vip=" + this.vip + ", vipReward=" + this.vipReward + ", loginOutTime=" + this.loginOutTime + ", leaderId=" + this.leaderId + ", fightValue=" + this.fightValue + ", totalValue=" + this.totalValue + ", quality=" + this.quality + ", worships=" + this.worships + ", isWorship=" + this.isWorship + ", activeTitles=" + this.activeTitles + ", wearTitle=" + this.wearTitle + ", wearFashion=" + this.wearFashion + ", onlineTime=" + this.onlineTime + ", onlineReward=" + this.onlineReward + ", todayLevel=" + this.todayLevel + ", firstHand=" + this.firstHand + ", soulLevel=" + this.soulLevel + ", soulsFighter=" + this.soulsFighter + ", talisman=" + this.talisman + ", reincarnationMap=" + this.reincarnationMap + ", reincarnationIds=" + this.reincarnationIds + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\player\PlayerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */