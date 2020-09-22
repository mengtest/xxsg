/*     */ package com.linlongyx.sanguo.webgame.app.bossHome;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BossHomeComponent
/*     */   extends AbstractComponent<BossHomeEntity>
/*     */ {
/*     */   private int worldBossTime;
/*     */   private int lastCreateTeamTime;
/*     */   
/*     */   public BossHomeComponent(long playerId) {
/*  20 */     super(BossHomeEntity.class, playerId);
/*  21 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  26 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  31 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  36 */     if (time == 0) {
/*  37 */       int buyCount = ((Integer)getBuys().getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/*  38 */       int rankBossCount = ((Integer)getCounts().getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue() - buyCount;
/*  39 */       int rankBossTimes = ((Integer)getTimes().getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/*  40 */       int restoreCount = ((Integer)getRestores().getOrDefault(Integer.valueOf(1), Integer.valueOf(0))).intValue();
/*     */       
/*  42 */       getCounts().clear();
/*  43 */       getCounts().put(Integer.valueOf(1), Integer.valueOf(rankBossCount));
/*  44 */       getTimes().clear();
/*  45 */       getTimes().put(Integer.valueOf(1), Integer.valueOf(rankBossTimes));
/*  46 */       getRestores().clear();
/*  47 */       getRestores().put(Integer.valueOf(1), Integer.valueOf(restoreCount));
/*     */ 
/*     */       
/*  50 */       setBuys(new HashMap<>());
/*     */       
/*  52 */       setTeamFightTimes(0);
/*  53 */       setWorldRewards(new ArrayList<>());
/*  54 */       setAlienCount(0);
/*  55 */       setBuyAlienCount(0);
/*     */     } 
/*  57 */     return true;
/*     */   }
/*     */   
/*     */   public int getWorldBossTime() {
/*  61 */     return this.worldBossTime;
/*     */   }
/*     */   
/*     */   public void setWorldBossTime(int worldBossTime) {
/*  65 */     this.worldBossTime = worldBossTime;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getCounts() {
/*  69 */     return ((BossHomeEntity)getEntity()).getCounts();
/*     */   }
/*     */   
/*     */   public void setCounts(Map<Integer, Integer> counts) {
/*  73 */     ((BossHomeEntity)getEntity()).setCounts(counts);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimes() {
/*  77 */     return ((BossHomeEntity)getEntity()).getTimes();
/*     */   }
/*     */   
/*     */   public void setTimes(Map<Integer, Integer> times) {
/*  81 */     ((BossHomeEntity)getEntity()).setTimes(times);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getRestores() {
/*  85 */     return ((BossHomeEntity)getEntity()).getRestores();
/*     */   }
/*     */   
/*     */   public void setRestores(Map<Integer, Integer> restores) {
/*  89 */     ((BossHomeEntity)getEntity()).setRestores(restores);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBuys() {
/*  93 */     return ((BossHomeEntity)getEntity()).getBuys();
/*     */   }
/*     */   
/*     */   public void setBuys(Map<Integer, Integer> buys) {
/*  97 */     ((BossHomeEntity)getEntity()).setBuys(buys);
/*     */   }
/*     */   
/*     */   public List<Integer> getWorldRewards() {
/* 101 */     return ((BossHomeEntity)getEntity()).getWorldRewards();
/*     */   }
/*     */   
/*     */   public void setWorldRewards(List<Integer> worldRewards) {
/* 105 */     ((BossHomeEntity)getEntity()).setWorldRewards(worldRewards);
/*     */   }
/*     */   
/*     */   public List<Integer> getBossNeedNotice() {
/* 109 */     return ((BossHomeEntity)getEntity()).getBossNeedNotice();
/*     */   }
/*     */   
/*     */   public void setBossNeedNotice(List<Integer> bossNotice) {
/* 113 */     ((BossHomeEntity)getEntity()).setBossNeedNotice(bossNotice);
/*     */   }
/*     */   
/*     */   public int getTeamFightTimes() {
/* 117 */     return ((BossHomeEntity)getEntity()).getTeamFightTimes();
/*     */   }
/*     */   
/*     */   public void setTeamFightTimes(int teamFightTimes) {
/* 121 */     ((BossHomeEntity)getEntity()).setTeamFightTimes(teamFightTimes);
/*     */   }
/*     */   
/*     */   public int getLastCreateTeamTime() {
/* 125 */     return this.lastCreateTeamTime;
/*     */   }
/*     */   
/*     */   public void setLastCreateTeamTime(int lastCreateTeamTime) {
/* 129 */     this.lastCreateTeamTime = lastCreateTeamTime;
/*     */   }
/*     */   
/*     */   public int getTotalFightTimes() {
/* 133 */     return ((BossHomeEntity)getEntity()).getTotalFightTimes();
/*     */   }
/*     */   
/*     */   public void setTotalFightTimes(int totalFightTimes) {
/* 137 */     ((BossHomeEntity)getEntity()).setTotalFightTimes(totalFightTimes);
/*     */   }
/*     */   
/*     */   public int getAlienCount() {
/* 141 */     return ((BossHomeEntity)getEntity()).getAlienCount();
/*     */   }
/*     */   
/*     */   public void setAlienCount(int alienCount) {
/* 145 */     ((BossHomeEntity)getEntity()).setAlienCount(alienCount);
/*     */   }
/*     */   
/*     */   public int getBuyAlienCount() {
/* 149 */     return ((BossHomeEntity)getEntity()).getBuyAlienCount();
/*     */   }
/*     */   
/*     */   public void setBuyAlienCount(int buyAlienCount) {
/* 153 */     ((BossHomeEntity)getEntity()).setBuyAlienCount(buyAlienCount);
/*     */   }
/*     */   
/*     */   public int getTotalNeutralBoss() {
/* 157 */     return ((BossHomeEntity)getEntity()).getTotalNeutralBoss();
/*     */   }
/*     */   
/*     */   public void setTotalNeutralBoss(int totalNeutralBoss) {
/* 161 */     ((BossHomeEntity)getEntity()).setTotalNeutralBoss(totalNeutralBoss);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\bossHome\BossHomeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */