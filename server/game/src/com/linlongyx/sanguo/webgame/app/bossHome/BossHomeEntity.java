/*     */ package com.linlongyx.sanguo.webgame.app.bossHome;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Table(tableName = "tb_bossHome", prefix = "bossHome_%serverId_%playerId", keyField = "playerId", isPlayerIdKey = true)
/*     */ public class BossHomeEntity
/*     */   implements IEntity
/*     */ {
/*     */   @TableField(isKey = true)
/*     */   private final transient long playerId;
/*  21 */   private Map<Integer, Integer> counts = new HashMap<>();
/*  22 */   private Map<Integer, Integer> times = new HashMap<>();
/*  23 */   private Map<Integer, Integer> restores = new HashMap<>();
/*  24 */   private Map<Integer, Integer> buys = new HashMap<>();
/*  25 */   private List<Integer> worldRewards = new ArrayList<>();
/*  26 */   private List<Integer> bossNeedNotice = new ArrayList<>();
/*     */   
/*     */   private int alienCount;
/*     */   
/*     */   private int buyAlienCount;
/*     */   
/*     */   private int teamFightTimes;
/*     */   private int totalFightTimes;
/*     */   private int totalNeutralBoss;
/*     */   
/*     */   public BossHomeEntity(long playerId) {
/*  37 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  41 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCounts() {
/*  45 */     return this.counts;
/*     */   }
/*     */   
/*     */   public void setCounts(Map<Integer, Integer> counts) {
/*  49 */     this.counts = counts;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimes() {
/*  53 */     return this.times;
/*     */   }
/*     */   
/*     */   public void setTimes(Map<Integer, Integer> times) {
/*  57 */     this.times = times;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getRestores() {
/*  61 */     return this.restores;
/*     */   }
/*     */   
/*     */   public void setRestores(Map<Integer, Integer> restores) {
/*  65 */     this.restores = restores;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBuys() {
/*  69 */     return this.buys;
/*     */   }
/*     */   
/*     */   public void setBuys(Map<Integer, Integer> buys) {
/*  73 */     this.buys = buys;
/*     */   }
/*     */   
/*     */   public List<Integer> getWorldRewards() {
/*  77 */     return this.worldRewards;
/*     */   }
/*     */   
/*     */   public void setWorldRewards(List<Integer> worldRewards) {
/*  81 */     this.worldRewards = worldRewards;
/*     */   }
/*     */   
/*     */   public List<Integer> getBossNeedNotice() {
/*  85 */     return this.bossNeedNotice;
/*     */   }
/*     */   
/*     */   public void setBossNeedNotice(List<Integer> bossNotice) {
/*  89 */     this.bossNeedNotice = bossNotice;
/*     */   }
/*     */   
/*     */   public int getTeamFightTimes() {
/*  93 */     return this.teamFightTimes;
/*     */   }
/*     */   
/*     */   public void setTeamFightTimes(int teamFightTimes) {
/*  97 */     this.teamFightTimes = teamFightTimes;
/*     */   }
/*     */   
/*     */   public int getTotalFightTimes() {
/* 101 */     return this.totalFightTimes;
/*     */   }
/*     */   
/*     */   public void setTotalFightTimes(int totalFightTimes) {
/* 105 */     this.totalFightTimes = totalFightTimes;
/*     */   }
/*     */   
/*     */   public int getAlienCount() {
/* 109 */     return this.alienCount;
/*     */   }
/*     */   
/*     */   public void setAlienCount(int alienCount) {
/* 113 */     this.alienCount = alienCount;
/*     */   }
/*     */   
/*     */   public int getBuyAlienCount() {
/* 117 */     return this.buyAlienCount;
/*     */   }
/*     */   
/*     */   public void setBuyAlienCount(int buyAlienCount) {
/* 121 */     this.buyAlienCount = buyAlienCount;
/*     */   }
/*     */   
/*     */   public int getTotalNeutralBoss() {
/* 125 */     return this.totalNeutralBoss;
/*     */   }
/*     */   
/*     */   public void setTotalNeutralBoss(int totalNeutralBoss) {
/* 129 */     this.totalNeutralBoss = totalNeutralBoss;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return "BossHomeEntity{playerId=" + this.playerId + ", counts=" + this.counts + ", times=" + this.times + ", restores=" + this.restores + ", buys=" + this.buys + ", worldRewards=" + this.worldRewards + ", bossNeedNotice=" + this.bossNeedNotice + ", teamFightTimes=" + this.teamFightTimes + ", totalFightTimes=" + this.totalFightTimes + ", alienCount=" + this.alienCount + ", buyAlienCount=" + this.buyAlienCount + ", totalNeutralBoss=" + this.totalNeutralBoss + '}';
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\bossHome\BossHomeEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */