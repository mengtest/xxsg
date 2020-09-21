/*     */ package com.linlongyx.sanguo.webgame.app.singleIns;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingleInsComponent
/*     */   extends AbstractComponent<SingleInsEntity>
/*     */ {
/*     */   public SingleInsComponent(long playerId) {
/*  21 */     super(SingleInsEntity.class);
/*  22 */     this.playerId = playerId;
/*  23 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  28 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  33 */     setPlayerId(playerId);
/*  34 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  39 */     if (time == 0) {
/*  40 */       setTimes(new HashMap<>());
/*  41 */       setBuyTimeMap((Map<Integer, Integer>)new HashedMap());
/*  42 */       setTimesMap((Map<Integer, Integer>)new HashedMap());
/*  43 */       setVipSweepTimes((Map<Integer, Integer>)new HashedMap());
/*     */     } 
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveFindTimes(boolean isToday, int type, int times) {
/*  56 */     if (times <= 0) {
/*     */       return;
/*     */     }
/*  59 */     TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  60 */     Map<Integer, Map<Integer, Integer>> actPlayTime = getActPlayTime();
/*  61 */     Map<Integer, Integer> dayMap = actPlayTime.getOrDefault(Integer.valueOf(type), new HashMap<>());
/*  62 */     int maxTimes = TaskUtil.getMaxCheckPoint(type, this.playerId);
/*     */     
/*  64 */     if (isToday) {
/*  65 */       int day = TimeUtil.getCurrentYearMonthDay();
/*  66 */       int value = ((Integer)dayMap.getOrDefault(Integer.valueOf(day), Integer.valueOf(0))).intValue();
/*  67 */       if (value >= TaskUtil.getMaxCheckPoint(type, this.playerId)) {
/*     */         return;
/*     */       }
/*  70 */       value += times;
/*  71 */       if (value > maxTimes) {
/*  72 */         value = maxTimes;
/*     */       }
/*  74 */       dayMap.put(Integer.valueOf(day), Integer.valueOf(value));
/*  75 */       actPlayTime.put(Integer.valueOf(type), dayMap);
/*  76 */       setActPlayTime(actPlayTime);
/*     */     } else {
/*  78 */       for (int i = taskParameter.getDays(); i >= 1; i--) {
/*  79 */         int time = TimeUtil.currentTime() - i * 86400;
/*  80 */         int day = TimeUtil.getYearMonthDay(time);
/*  81 */         int value = ((Integer)dayMap.getOrDefault(Integer.valueOf(day), Integer.valueOf(0))).intValue();
/*  82 */         int lastValue = value;
/*     */         
/*  84 */         if (taskParameter.getEffectDate() <= day)
/*     */         {
/*     */           
/*  87 */           if (value < TaskUtil.getMaxCheckPoint(type, this.playerId)) {
/*     */ 
/*     */             
/*  90 */             value += times;
/*  91 */             if (value > maxTimes) {
/*  92 */               int mush = maxTimes - lastValue;
/*  93 */               times -= mush;
/*  94 */               value = maxTimes;
/*     */             } else {
/*  96 */               times = 0;
/*     */             } 
/*  98 */             dayMap.put(Integer.valueOf(day), Integer.valueOf(value));
/*  99 */             actPlayTime.put(Integer.valueOf(type), dayMap);
/* 100 */             setActPlayTime(actPlayTime);
/*     */           }  } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public Map<Integer, Integer> getTimes() {
/* 106 */     return ((SingleInsEntity)getEntity()).getTimes();
/*     */   }
/*     */   
/*     */   public void setTimes(Map<Integer, Integer> times) {
/* 110 */     ((SingleInsEntity)getEntity()).setTimes(times);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getMaxPoints() {
/* 114 */     return ((SingleInsEntity)getEntity()).getMaxPoints();
/*     */   }
/*     */   
/*     */   public void setMaxPoints(Map<Integer, Integer> maxPoints) {
/* 118 */     ((SingleInsEntity)getEntity()).setMaxPoints(maxPoints);
/*     */   }
/*     */   
/*     */   public Set<Integer> getClears() {
/* 122 */     return ((SingleInsEntity)getEntity()).getClears();
/*     */   }
/*     */   
/*     */   public void setClears(Set<Integer> clears) {
/* 126 */     ((SingleInsEntity)getEntity()).setClears(clears);
/*     */   }
/*     */   
/*     */   public int getTotalTime() {
/* 130 */     return ((SingleInsEntity)getEntity()).getTotalTime();
/*     */   }
/*     */   
/*     */   public void setTotalTime(int totalTime) {
/* 134 */     ((SingleInsEntity)getEntity()).setTotalTime(totalTime);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBossMap() {
/* 138 */     return ((SingleInsEntity)getEntity()).getBossMap();
/*     */   }
/*     */   
/*     */   public void setBossMap(Map<Integer, Integer> bossMap) {
/* 142 */     ((SingleInsEntity)getEntity()).setBossMap(bossMap);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTimesMap() {
/* 146 */     return ((SingleInsEntity)getEntity()).getTimesMap();
/*     */   }
/*     */   
/*     */   public void setTimesMap(Map<Integer, Integer> timesMap) {
/* 150 */     ((SingleInsEntity)getEntity()).setTimesMap(timesMap);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getBuyTimeMap() {
/* 154 */     return ((SingleInsEntity)getEntity()).getBuyTimeMap();
/*     */   }
/*     */   
/*     */   public void setBuyTimeMap(Map<Integer, Integer> buyTimeMap) {
/* 158 */     ((SingleInsEntity)getEntity()).setBuyTimeMap(buyTimeMap);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getTotalChallengeMap() {
/* 162 */     return ((SingleInsEntity)getEntity()).getTotalChallengeMap();
/*     */   }
/*     */   
/*     */   public void setTotalChallengeMap(Map<Integer, Integer> totalChallengeMap) {
/* 166 */     ((SingleInsEntity)getEntity()).setTotalChallengeMap(totalChallengeMap);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getVipSweepTimes() {
/* 170 */     return ((SingleInsEntity)getEntity()).getVipSweepTimes();
/*     */   }
/*     */   
/*     */   public void setVipSweepTimes(Map<Integer, Integer> vipSweepTimes) {
/* 174 */     ((SingleInsEntity)getEntity()).setVipSweepTimes(vipSweepTimes);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getKfHandbook() {
/* 178 */     return ((SingleInsEntity)getEntity()).getKfHandbook();
/*     */   }
/*     */   
/*     */   public void setKfHandbook(Map<Integer, Integer> kfHandbook) {
/* 182 */     ((SingleInsEntity)getEntity()).setKfHandbook(kfHandbook);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStageHandbook() {
/* 186 */     return ((SingleInsEntity)getEntity()).getStageHandbook();
/*     */   }
/*     */   
/*     */   public void setStageHandbook(Map<Integer, Integer> stageHandbook) {
/* 190 */     ((SingleInsEntity)getEntity()).setStageHandbook(stageHandbook);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getZfHandbook() {
/* 194 */     return ((SingleInsEntity)getEntity()).getZfHandbook();
/*     */   }
/*     */   
/*     */   public void setZfHandbook(Map<Integer, Integer> zfHandbook) {
/* 198 */     ((SingleInsEntity)getEntity()).setZfHandbook(zfHandbook);
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Integer>> getActPlayTime() {
/* 202 */     return ((SingleInsEntity)getEntity()).getActPlayTime();
/*     */   }
/*     */   
/*     */   public void setActPlayTime(Map<Integer, Map<Integer, Integer>> actPlayTime) {
/* 206 */     ((SingleInsEntity)getEntity()).setActPlayTime(actPlayTime);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\singleIns\SingleInsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */