/*     */ package com.linlongyx.sanguo.webgame.app.unparalleled;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_unparalleled", prefix = "unparalleled_%serverId_%playerId", isPlayerIdKey = true)
/*     */ public class UnparalleledEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  23 */   private Map<Long, Long> partneredHpMap = new HashMap<>();
/*     */   private int resetTimes;
/*  25 */   private ArrayList<Integer> buffs = new ArrayList<>();
/*  26 */   private ArrayList<Integer> layerAddition = new ArrayList<>();
/*     */   private int curPoint;
/*     */   private int maxPoint;
/*     */   private int challengeNum;
/*     */   private int currMaxStar;
/*     */   private int surpStar;
/*  32 */   private Map<Integer, Integer> playedPoints = (Map<Integer, Integer>)new HashedMap();
/*  33 */   private ArrayList<Integer> layerBox = new ArrayList<>();
/*     */   private int lastMaxStar;
/*  35 */   private ArrayList<Long> battlePartner = new ArrayList<>();
/*     */   private boolean sweep;
/*  37 */   private Map<Integer, Integer> insMap = new LinkedHashMap<>();
/*     */   private long lastPassTime;
/*     */   private int lastMaxRank;
/*     */   private int totalResetTimes;
/*  41 */   private Map<Long, ArrayList<Long>> attrsList = new HashMap<>();
/*  42 */   private Map<Long, Integer> levelMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   public UnparalleledEntity(long playerId) {
/*  46 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  50 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public Map<Long, Long> getPartneredHpMap() {
/*  54 */     return this.partneredHpMap;
/*     */   }
/*     */   
/*     */   public void setPartneredHpMap(Map<Long, Long> partneredHpMap) {
/*  58 */     this.partneredHpMap = partneredHpMap;
/*     */   }
/*     */   
/*     */   public int getResetTimes() {
/*  62 */     return this.resetTimes;
/*     */   }
/*     */   
/*     */   public void setResetTimes(int resetTimes) {
/*  66 */     this.resetTimes = resetTimes;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getBuffs() {
/*  70 */     return this.buffs;
/*     */   }
/*     */   
/*     */   public void setBuffs(ArrayList<Integer> buffs) {
/*  74 */     this.buffs = buffs;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getLayerAddition() {
/*  78 */     return this.layerAddition;
/*     */   }
/*     */   
/*     */   public void setLayerAddition(ArrayList<Integer> layerAddition) {
/*  82 */     this.layerAddition = layerAddition;
/*     */   }
/*     */   
/*     */   public int getCurPoint() {
/*  86 */     return this.curPoint;
/*     */   }
/*     */   
/*     */   public void setCurPoint(int curPoint) {
/*  90 */     this.curPoint = curPoint;
/*     */   }
/*     */   
/*     */   public int getMaxPoint() {
/*  94 */     return this.maxPoint;
/*     */   }
/*     */   
/*     */   public void setMaxPoint(int maxPoint) {
/*  98 */     this.maxPoint = maxPoint;
/*     */   }
/*     */   
/*     */   public int getChallengeNum() {
/* 102 */     return this.challengeNum;
/*     */   }
/*     */   
/*     */   public void setChallengeNum(int challengeNum) {
/* 106 */     this.challengeNum = challengeNum;
/*     */   }
/*     */   
/*     */   public int getCurrMaxStar() {
/* 110 */     return this.currMaxStar;
/*     */   }
/*     */   
/*     */   public void setCurrMaxStar(int currMaxStar) {
/* 114 */     this.currMaxStar = currMaxStar;
/*     */   }
/*     */   
/*     */   public int getSurpStar() {
/* 118 */     return this.surpStar;
/*     */   }
/*     */   
/*     */   public void setSurpStar(int surpStar) {
/* 122 */     this.surpStar = surpStar;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getPlayedPoints() {
/* 126 */     return this.playedPoints;
/*     */   }
/*     */   
/*     */   public void setPlayedPoints(Map<Integer, Integer> playedPoints) {
/* 130 */     this.playedPoints = playedPoints;
/*     */   }
/*     */   
/*     */   public ArrayList<Integer> getLayerBox() {
/* 134 */     return this.layerBox;
/*     */   }
/*     */   
/*     */   public void setLayerBox(ArrayList<Integer> layerBox) {
/* 138 */     this.layerBox = layerBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLastMaxStar() {
/* 143 */     return this.lastMaxStar;
/*     */   }
/*     */   
/*     */   public void setLastMaxStar(int lastMaxStar) {
/* 147 */     this.lastMaxStar = lastMaxStar;
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getBattlePartner() {
/* 151 */     return this.battlePartner;
/*     */   }
/*     */   
/*     */   public void setBattlePartner(ArrayList<Long> battlePartner) {
/* 155 */     this.battlePartner = battlePartner;
/*     */   }
/*     */   
/*     */   public boolean isSweep() {
/* 159 */     return this.sweep;
/*     */   }
/*     */   
/*     */   public void setSweep(boolean sweep) {
/* 163 */     this.sweep = sweep;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getInsMap() {
/* 167 */     return this.insMap;
/*     */   }
/*     */   
/*     */   public void setInsMap(Map<Integer, Integer> insMap) {
/* 171 */     this.insMap = insMap;
/*     */   }
/*     */   
/*     */   public long getLastPassTime() {
/* 175 */     return this.lastPassTime;
/*     */   }
/*     */   
/*     */   public void setLastPassTime(long lastPassTime) {
/* 179 */     this.lastPassTime = lastPassTime;
/*     */   }
/*     */   
/*     */   public int getLastMaxRank() {
/* 183 */     return this.lastMaxRank;
/*     */   }
/*     */   
/*     */   public void setLastMaxRank(int lastMaxRank) {
/* 187 */     this.lastMaxRank = lastMaxRank;
/*     */   }
/*     */   
/*     */   public int getTotalResetTimes() {
/* 191 */     return this.totalResetTimes;
/*     */   }
/*     */   
/*     */   public void setTotalResetTimes(int totalResetTimes) {
/* 195 */     this.totalResetTimes = totalResetTimes;
/*     */   }
/*     */   
/*     */   public Map<Long, ArrayList<Long>> getAttrsList() {
/* 199 */     return this.attrsList;
/*     */   }
/*     */   
/*     */   public void setAttrsList(Map<Long, ArrayList<Long>> attrsList) {
/* 203 */     this.attrsList = attrsList;
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getLevelMap() {
/* 207 */     return this.levelMap;
/*     */   }
/*     */   
/*     */   public void setLevelMap(Map<Long, Integer> levelMap) {
/* 211 */     this.levelMap = levelMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     return "UnparalleledEntity{playerId=" + this.playerId + ", partneredHpMap=" + this.partneredHpMap + ", resetTimes=" + this.resetTimes + ", buffs=" + this.buffs + ", layerAddition=" + this.layerAddition + ", curPoint=" + this.curPoint + ", maxPoint=" + this.maxPoint + ", challengeNum=" + this.challengeNum + ", currMaxStar=" + this.currMaxStar + ", surpStar=" + this.surpStar + ", playedPoints=" + this.playedPoints + ", layerBox=" + this.layerBox + ", lastMaxStar=" + this.lastMaxStar + ", battlePartner=" + this.battlePartner + ", sweep=" + this.sweep + ", insMap=" + this.insMap + ", lastPassTime=" + this.lastPassTime + ", lastMaxRank=" + this.lastMaxRank + ", totalResetTimes=" + this.totalResetTimes + ", attrsList=" + this.attrsList + ", levelMap=" + this.levelMap + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\ap\\unparalleled\UnparalleledEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */