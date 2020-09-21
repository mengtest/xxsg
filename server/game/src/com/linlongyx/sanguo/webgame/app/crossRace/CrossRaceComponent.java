/*     */ package com.linlongyx.sanguo.webgame.app.crossRace;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRaceParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrossRaceComponent
/*     */   extends AbstractComponent<CrossRaceEntity>
/*     */ {
/*     */   private RacePlayerData cacheRacePlayerData;
/*     */   private int lastFightTime;
/*     */   
/*     */   public CrossRaceComponent(long playerId) {
/*  27 */     super(CrossRaceEntity.class, playerId);
/*  28 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  33 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  38 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  43 */     if (time == 0) {
/*     */       
/*  45 */       CrossRaceParameter crossRaceParameter = (CrossRaceParameter)ParameterConstant.getParameter(28);
/*  46 */       setFightTimes(crossRaceParameter.getFightTimes());
/*  47 */       SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(this.playerId, SingleInsComponent.class);
/*  48 */       if (TimeUtil.getWeek() == 6 || TimeUtil.getWeek() == 7) {
/*  49 */         if (null != singleInsComponent) {
/*  50 */           singleInsComponent.saveFindTimes(true, FindRewardType.CROSS.getType(), crossRaceParameter.getFightTimes());
/*     */         }
/*     */       } else {
/*  53 */         TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  54 */         for (int i = 1; i <= taskParameter.getDays(); i++) {
/*  55 */           int lastTime = TimeUtil.currentTime() - i * 86400;
/*  56 */           int yearMonth = TimeUtil.getYearMonthDay(lastTime);
/*  57 */           int days = TimeUtil.time2Week(lastTime);
/*  58 */           if (days == 0 || days == 6) {
/*  59 */             Map<Integer, Integer> map = (Map<Integer, Integer>)singleInsComponent.getActPlayTime().getOrDefault(Integer.valueOf(FindRewardType.CROSS.getType()), new HashMap<>());
/*  60 */             if (null != singleInsComponent && ((Integer)map.getOrDefault(Integer.valueOf(yearMonth), Integer.valueOf(0))).intValue() <= 0) {
/*  61 */               singleInsComponent.saveFindTimes(false, FindRewardType.CROSS.getType(), crossRaceParameter.getFightTimes());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  67 */     return true;
/*     */   }
/*     */   
/*     */   public void resetRaceData(int raceId) {
/*  71 */     CrossRaceParameter crossRaceParameter = (CrossRaceParameter)ParameterConstant.getParameter(28);
/*  72 */     setFightTimes(crossRaceParameter.getFightTimes());
/*  73 */     ((CrossRaceEntity)getEntity()).setPoint(0);
/*  74 */     ((CrossRaceEntity)getEntity()).setTimes(0);
/*  75 */     ((CrossRaceEntity)getEntity()).setWinTimes(0);
/*  76 */     ((CrossRaceEntity)getEntity()).setRaceId(raceId);
/*  77 */     ((CrossRaceEntity)getEntity()).setBalance((byte)0);
/*  78 */     setJoinReward(new HashMap<>());
/*     */   }
/*     */   
/*     */   public int getRaceId() {
/*  82 */     return ((CrossRaceEntity)getEntity()).getRaceId();
/*     */   }
/*     */   
/*     */   public void setRaceId(int raceId) {
/*  86 */     ((CrossRaceEntity)getEntity()).setRaceId(raceId);
/*     */   }
/*     */   
/*     */   public int getPoint() {
/*  90 */     return ((CrossRaceEntity)getEntity()).getPoint();
/*     */   }
/*     */   
/*     */   public void setPoint(int point) {
/*  94 */     ((CrossRaceEntity)getEntity()).setPoint(point);
/*     */   }
/*     */   
/*     */   public int getTimes() {
/*  98 */     return ((CrossRaceEntity)getEntity()).getTimes();
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 102 */     ((CrossRaceEntity)getEntity()).setTimes(times);
/*     */   }
/*     */   
/*     */   public int getWinTimes() {
/* 106 */     return ((CrossRaceEntity)getEntity()).getWinTimes();
/*     */   }
/*     */   
/*     */   public void setWinTimes(int winTimes) {
/* 110 */     ((CrossRaceEntity)getEntity()).setWinTimes(winTimes);
/*     */   }
/*     */   
/*     */   public int getFightTimes() {
/* 114 */     return ((CrossRaceEntity)getEntity()).getFightTimes();
/*     */   }
/*     */   
/*     */   public void setFightTimes(int fightTimes) {
/* 118 */     ((CrossRaceEntity)getEntity()).setFightTimes(fightTimes);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getJoinReward() {
/* 122 */     return ((CrossRaceEntity)getEntity()).getJoinReward();
/*     */   }
/*     */   
/*     */   public void setJoinReward(Map<Integer, Integer> joinReward) {
/* 126 */     ((CrossRaceEntity)getEntity()).setJoinReward(joinReward);
/*     */   }
/*     */   
/*     */   public byte getBalance() {
/* 130 */     return ((CrossRaceEntity)getEntity()).getBalance();
/*     */   }
/*     */   
/*     */   public void setBalance(byte balance) {
/* 134 */     ((CrossRaceEntity)getEntity()).setBalance(balance);
/*     */   }
/*     */   
/*     */   public RacePlayerData getCacheRacePlayerData() {
/* 138 */     return this.cacheRacePlayerData;
/*     */   }
/*     */   
/*     */   public void setCacheRacePlayerData(RacePlayerData cacheRacePlayerData) {
/* 142 */     this.cacheRacePlayerData = cacheRacePlayerData;
/*     */   }
/*     */   
/*     */   public int getLastFightTime() {
/* 146 */     return this.lastFightTime;
/*     */   }
/*     */   
/*     */   public void setLastFightTime(int lastFightTime) {
/* 150 */     this.lastFightTime = lastFightTime;
/*     */   }
/*     */   
/*     */   public int getTotalTimes() {
/* 154 */     return ((CrossRaceEntity)getEntity()).getTotalTimes();
/*     */   }
/*     */   
/*     */   public void setTotalTimes(int totalTimes) {
/* 158 */     ((CrossRaceEntity)getEntity()).setTotalTimes(totalTimes);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\crossRace\CrossRaceComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */