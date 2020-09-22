/*     */ package com.linlongyx.sanguo.webgame.app.rune;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuneComponent
/*     */   extends AbstractComponent<RuneEntity>
/*     */ {
/*     */   private int lastRefreshTime;
/*     */   private int lasfFightTime;
/*     */   
/*     */   public RuneComponent(long playerId) {
/*  24 */     super(RuneEntity.class, playerId);
/*  25 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  30 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  35 */     setPlayerId(playerId);
/*  36 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*  37 */     setHp(runewarParameter.getHp());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  42 */     if (time == 0) {
/*     */       
/*  44 */       setDailyReward(new HashMap<>());
/*  45 */       setTimes(0);
/*     */     }
/*  47 */     else if (time != 18000) {
/*     */       
/*  49 */       if (time == 50) {
/*  50 */         RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*  51 */         addPoint(-((RuneEntity)getEntity()).getPoint());
/*  52 */         ((RuneEntity)getEntity()).setHp(runewarParameter.getHp());
/*  53 */         ((RuneEntity)getEntity()).setRecords(new LinkedList<>());
/*  54 */         ((RuneEntity)getEntity()).setStageReward(new HashMap<>());
/*     */       } 
/*  56 */     }  return true;
/*     */   }
/*     */   
/*     */   public long genRuneMidId() {
/*  60 */     long val = getRuneMidInit() + 1L;
/*  61 */     setRuneMidInit(val);
/*  62 */     return val;
/*     */   }
/*     */   
/*     */   public synchronized void addRecord(BattleRecordData battleRecordData) {
/*  66 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*  67 */     LinkedList<BattleRecordData> recordList = getRecords();
/*  68 */     if (recordList.size() >= runewarParameter.getRecordLimit()) {
/*  69 */       recordList.removeFirst();
/*     */     }
/*  71 */     recordList.addLast(battleRecordData);
/*  72 */     setRecords(recordList);
/*     */   }
/*     */   
/*     */   public synchronized void addPoint(int point) {
/*  76 */     ((RuneEntity)getEntity()).setPoint(((RuneEntity)getEntity()).getPoint() + point);
/*  77 */     if (getPoint() < 0) {
/*  78 */       ((RuneEntity)getEntity()).setPoint(0);
/*     */     }
/*     */   }
/*     */   
/*     */   public long getRuneMidInit() {
/*  83 */     return ((RuneEntity)getEntity()).getRuneMidInit();
/*     */   }
/*     */   
/*     */   public void setRuneMidInit(long runeMidInit) {
/*  87 */     ((RuneEntity)getEntity()).setRuneMidInit(runeMidInit);
/*     */   }
/*     */   
/*     */   public int getGoldEndTime() {
/*  91 */     return ((RuneEntity)getEntity()).getGoldEndTime();
/*     */   }
/*     */   
/*     */   public void setGoldEndTime(int goldEndTime) {
/*  95 */     ((RuneEntity)getEntity()).setGoldEndTime(goldEndTime);
/*     */   }
/*     */   
/*     */   public Map<Integer, Long> getRuneMap() {
/*  99 */     return ((RuneEntity)getEntity()).getRuneMap();
/*     */   }
/*     */   
/*     */   public void setRuneMap(Map<Integer, Long> runeMap) {
/* 103 */     ((RuneEntity)getEntity()).setRuneMap(runeMap);
/*     */   }
/*     */   
/*     */   public int getTotalZhuHun() {
/* 107 */     return ((RuneEntity)getEntity()).getTotalZhuHun();
/*     */   }
/*     */   
/*     */   public void setTotalZhuHun(int totalZhuHun) {
/* 111 */     ((RuneEntity)getEntity()).setTotalZhuHun(totalZhuHun);
/*     */   }
/*     */   
/*     */   public int getTalismanTurn() {
/* 115 */     return ((RuneEntity)getEntity()).getTalismanTurn();
/*     */   }
/*     */   
/*     */   public void setTalismanTurn(int talismanTurn) {
/* 119 */     ((RuneEntity)getEntity()).setTalismanTurn(talismanTurn);
/*     */   }
/*     */   
/*     */   public long getTatalArtificeTimes() {
/* 123 */     return ((RuneEntity)getEntity()).getTatalArtificeTimes();
/*     */   }
/*     */   
/*     */   public void setTatalArtificeTimes(long tatalArtificeTimes) {
/* 127 */     ((RuneEntity)getEntity()).setTatalArtificeTimes(tatalArtificeTimes);
/*     */   }
/*     */   
/*     */   public int getPoint() {
/* 131 */     return ((RuneEntity)getEntity()).getPoint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHp() {
/* 139 */     return ((RuneEntity)getEntity()).getHp();
/*     */   }
/*     */   
/*     */   public void setHp(int hp) {
/* 143 */     ((RuneEntity)getEntity()).setHp(hp);
/*     */   }
/*     */   
/*     */   public LinkedList<BattleRecordData> getRecords() {
/* 147 */     return ((RuneEntity)getEntity()).getRecords();
/*     */   }
/*     */   
/*     */   public void setRecords(LinkedList<BattleRecordData> records) {
/* 151 */     ((RuneEntity)getEntity()).setRecords(records);
/*     */   }
/*     */   
/*     */   public int getLastRecoverTime() {
/* 155 */     return ((RuneEntity)getEntity()).getLastRecoverTime();
/*     */   }
/*     */   
/*     */   public void setLastRecoverTime(int lastRecoverTime) {
/* 159 */     ((RuneEntity)getEntity()).setLastRecoverTime(lastRecoverTime);
/*     */   }
/*     */   
/*     */   public int getTimes() {
/* 163 */     return ((RuneEntity)getEntity()).getTimes();
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 167 */     ((RuneEntity)getEntity()).setTimes(times);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDailyReward() {
/* 171 */     return ((RuneEntity)getEntity()).getDailyReward();
/*     */   }
/*     */   
/*     */   public void setDailyReward(Map<Integer, Integer> dailyReward) {
/* 175 */     ((RuneEntity)getEntity()).setDailyReward(dailyReward);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getStageReward() {
/* 179 */     return ((RuneEntity)getEntity()).getStageReward();
/*     */   }
/*     */   
/*     */   public void setStageReward(Map<Integer, Integer> stageReward) {
/* 183 */     ((RuneEntity)getEntity()).setStageReward(stageReward);
/*     */   }
/*     */   
/*     */   public int getLastRefreshTime() {
/* 187 */     return this.lastRefreshTime;
/*     */   }
/*     */   
/*     */   public void setLastRefreshTime(int lastRefreshTime) {
/* 191 */     this.lastRefreshTime = lastRefreshTime;
/*     */   }
/*     */   
/*     */   public int getLasfFightTime() {
/* 195 */     return this.lasfFightTime;
/*     */   }
/*     */   
/*     */   public void setLasfFightTime(int lasfFightTime) {
/* 199 */     this.lasfFightTime = lasfFightTime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rune\RuneComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */