/*     */ package com.linlongyx.sanguo.webgame.app.general;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ @Table(tableName = "tb_general", prefix = "general_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class GeneralEntity
/*     */   implements IEntity {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  16 */   private Map<Integer, Integer> challenges = new HashMap<>();
/*  17 */   private Map<Integer, Integer> resetTimes = new HashMap<>();
/*  18 */   private Map<Integer, Integer> stars = new HashMap<>();
/*  19 */   private Map<Integer, List<Integer>> boxReward = new HashMap<>();
/*     */   
/*     */   private int allStar;
/*     */   
/*     */   private int costOrder;
/*     */   private int restore;
/*     */   private int nextTime;
/*     */   private int buyTime;
/*     */   private int totalNum;
/*  28 */   private ArrayList<Long> assistInBattle = new ArrayList<>();
/*     */   private int difficultyStar;
/*     */   private int nightmareStar;
/*  31 */   private Map<Integer, List<Integer>> diffboxReward = new HashMap<>();
/*  32 */   private Map<Integer, List<Integer>> nightboxReward = new HashMap<>();
/*     */   
/*     */   public GeneralEntity(long playerId) {
/*  35 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  39 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getChallenges() {
/*  43 */     return this.challenges;
/*     */   }
/*     */   
/*     */   public void setChallenges(Map<Integer, Integer> challenges) {
/*  47 */     this.challenges = challenges;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getResetTimes() {
/*  51 */     return this.resetTimes;
/*     */   }
/*     */   
/*     */   public void setResetTimes(Map<Integer, Integer> resetTimes) {
/*  55 */     this.resetTimes = resetTimes;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStars() {
/*  59 */     return this.stars;
/*     */   }
/*     */   
/*     */   public void setStars(Map<Integer, Integer> stars) {
/*  63 */     this.stars = stars;
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getBoxReward() {
/*  67 */     return this.boxReward;
/*     */   }
/*     */   
/*     */   public void setBoxReward(Map<Integer, List<Integer>> boxReward) {
/*  71 */     this.boxReward = boxReward;
/*     */   }
/*     */   
/*     */   public int getCostOrder() {
/*  75 */     return this.costOrder;
/*     */   }
/*     */   
/*     */   public void setCostOrder(int costOrder) {
/*  79 */     this.costOrder = costOrder;
/*     */   }
/*     */   
/*     */   public int getRestore() {
/*  83 */     return this.restore;
/*     */   }
/*     */   
/*     */   public void setRestore(int restore) {
/*  87 */     this.restore = restore;
/*     */   }
/*     */   
/*     */   public int getNextTime() {
/*  91 */     return this.nextTime;
/*     */   }
/*     */   
/*     */   public void setNextTime(int nextTime) {
/*  95 */     this.nextTime = nextTime;
/*     */   }
/*     */   
/*     */   public int getBuyTime() {
/*  99 */     return this.buyTime;
/*     */   }
/*     */   
/*     */   public void setBuyTime(int buyTime) {
/* 103 */     this.buyTime = buyTime;
/*     */   }
/*     */   
/*     */   public int getAllStar() {
/* 107 */     return this.allStar;
/*     */   }
/*     */   
/*     */   public void setAllStar(int allStar) {
/* 111 */     this.allStar = allStar;
/*     */   }
/*     */   
/*     */   public int getTotalNum() {
/* 115 */     return this.totalNum;
/*     */   }
/*     */   
/*     */   public void setTotalNum(int totalNum) {
/* 119 */     this.totalNum = totalNum;
/*     */   }
/*     */   
/*     */   public ArrayList<Long> getAssistInBattle() {
/* 123 */     return this.assistInBattle;
/*     */   }
/*     */   
/*     */   public void setAssistInBattle(ArrayList<Long> assistInBattle) {
/* 127 */     this.assistInBattle = assistInBattle;
/*     */   }
/*     */   
/*     */   public int getDifficultyStar() {
/* 131 */     return this.difficultyStar;
/*     */   }
/*     */   
/*     */   public void setDifficultyStar(int difficultyStar) {
/* 135 */     this.difficultyStar = difficultyStar;
/*     */   }
/*     */   
/*     */   public int getNightmareStar() {
/* 139 */     return this.nightmareStar;
/*     */   }
/*     */   
/*     */   public void setNightmareStar(int nightmareStar) {
/* 143 */     this.nightmareStar = nightmareStar;
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getDiffboxReward() {
/* 147 */     return this.diffboxReward;
/*     */   }
/*     */   
/*     */   public void setDiffboxReward(Map<Integer, List<Integer>> diffboxReward) {
/* 151 */     this.diffboxReward = diffboxReward;
/*     */   }
/*     */   
/*     */   public Map<Integer, List<Integer>> getNightboxReward() {
/* 155 */     return this.nightboxReward;
/*     */   }
/*     */   
/*     */   public void setNightboxReward(Map<Integer, List<Integer>> nightboxReward) {
/* 159 */     this.nightboxReward = nightboxReward;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 164 */     return "GeneralEntity{playerId=" + this.playerId + ", challenges=" + this.challenges + ", resetTimes=" + this.resetTimes + ", stars=" + this.stars + ", boxReward=" + this.boxReward + ", allStar=" + this.allStar + ", costOrder=" + this.costOrder + ", restore=" + this.restore + ", nextTime=" + this.nextTime + ", buyTime=" + this.buyTime + ", totalNum=" + this.totalNum + ", assistInBattle=" + this.assistInBattle + ", difficultyStar=" + this.difficultyStar + ", nightmareStar=" + this.nightmareStar + ", diffboxReward=" + this.diffboxReward + ", nightboxReward=" + this.nightboxReward + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\general\GeneralEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */