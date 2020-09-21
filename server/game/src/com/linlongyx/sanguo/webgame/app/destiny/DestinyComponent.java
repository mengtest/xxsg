/*     */ package com.linlongyx.sanguo.webgame.app.destiny;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DestinyComponent
/*     */   extends AbstractComponent<DestinyEntity>
/*     */ {
/*  26 */   private Map<Long, DestinyPlayerData> recommentsCacheData = Collections.synchronizedMap(new HashMap<>());
/*     */   
/*     */   public DestinyComponent(long playerId) {
/*  29 */     super(DestinyEntity.class, playerId);
/*  30 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  35 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  40 */     setPlayerId(playerId);
/*  41 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/*  42 */     addRobTimes(destinyParameter.getInitRobTimes() - getRobTimes());
/*  43 */     setLastResetTime(TimeUtil.currentTime());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  48 */     if (time == 0) {
/*     */       
/*  50 */       setTasks(new HashSet<>());
/*     */       
/*  52 */       setTotalRobTimes(0);
/*  53 */       setRefreshTimes(0);
/*     */       
/*  55 */       DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/*  56 */       addRobTimes(destinyParameter.getInitRobTimes() - getRobTimes());
/*  57 */       setLastResetTime(TimeUtil.currentTime() + 60);
/*  58 */       refreshRecomment(destinyParameter.getPlayersSize(), true);
/*  59 */       PlayerUtil.sendRedNotice(Long.valueOf(this.playerId), RedNoticeConstant.DestinyTimes.getSys(), RedNoticeConstant.DestinyTimes.getIndex());
/*  60 */       SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(this.playerId, SingleInsComponent.class);
/*  61 */       if (TimeUtil.getWeek() == 6 || TimeUtil.getWeek() == 7) {
/*  62 */         if (null != singleInsComponent) {
/*  63 */           singleInsComponent.saveFindTimes(true, FindRewardType.DENSTINY.getType(), destinyParameter.getInitRobTimes() * 2);
/*     */         }
/*     */       } else {
/*  66 */         TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  67 */         for (int i = 1; i <= taskParameter.getDays(); i++) {
/*  68 */           int lastTime = TimeUtil.currentTime() - i * 86400;
/*  69 */           int yearMonth = TimeUtil.getYearMonthDay(lastTime);
/*  70 */           int days = TimeUtil.time2Week(lastTime);
/*  71 */           if (days == 0 || days == 6) {
/*  72 */             Map<Integer, Integer> map = (Map<Integer, Integer>)singleInsComponent.getActPlayTime().getOrDefault(Integer.valueOf(FindRewardType.DENSTINY.getType()), new HashMap<>());
/*  73 */             if (null != singleInsComponent && ((Integer)map.getOrDefault(Integer.valueOf(yearMonth), Integer.valueOf(0))).intValue() <= 0) {
/*  74 */               singleInsComponent.saveFindTimes(false, FindRewardType.DENSTINY.getType(), destinyParameter.getInitRobTimes() * 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     return true;
/*     */   }
/*     */   
/*     */   public void removeBattle(long playerId) {
/*  90 */     ((DestinyEntity)getEntity()).getBattles().remove(Long.valueOf(playerId));
/*  91 */     setUpdate("battles");
/*     */   }
/*     */   
/*     */   public void addTask(int id) {
/*  95 */     ((DestinyEntity)getEntity()).getTasks().add(Integer.valueOf(id));
/*  96 */     setUpdate("tasks");
/*     */   }
/*     */   
/*     */   public void addAtkBattleRecordData(BattleRecordData recordData, int max) {
/* 100 */     ((DestinyEntity)getEntity()).getAttacks().add(recordData);
/* 101 */     if (((DestinyEntity)getEntity()).getAttacks().size() > max) {
/* 102 */       ((DestinyEntity)getEntity()).getAttacks().remove(0);
/*     */     }
/* 104 */     setUpdate("attacks");
/*     */   }
/*     */   
/*     */   public synchronized void addDefBattleRecordData(BattleRecordData recordData, int max) {
/* 108 */     ((DestinyEntity)getEntity()).getDefenses().add(recordData);
/* 109 */     if (((DestinyEntity)getEntity()).getDefenses().size() > max) {
/* 110 */       ((DestinyEntity)getEntity()).getDefenses().remove(0);
/*     */     }
/* 112 */     setUpdate("defenses");
/*     */   }
/*     */   
/*     */   public ArrayList<BattleRecordData> getAttacks() {
/* 116 */     return ((DestinyEntity)getEntity()).getAttacks();
/*     */   }
/*     */   
/*     */   public void setAttacks(ArrayList<BattleRecordData> attacks) {
/* 120 */     ((DestinyEntity)getEntity()).setAttacks(attacks);
/*     */   }
/*     */   
/*     */   public ArrayList<BattleRecordData> getDefenses() {
/* 124 */     return ((DestinyEntity)getEntity()).getDefenses();
/*     */   }
/*     */   
/*     */   public void setDefenses(ArrayList<BattleRecordData> defenses) {
/* 128 */     ((DestinyEntity)getEntity()).setDefenses(defenses);
/*     */   }
/*     */   
/*     */   public Map<Long, Byte> getBattles() {
/* 132 */     return ((DestinyEntity)getEntity()).getBattles();
/*     */   }
/*     */   
/*     */   public void setBattles(Map<Long, Byte> battles) {
/* 136 */     ((DestinyEntity)getEntity()).setBattles(battles);
/*     */   }
/*     */   
/*     */   public int getRobTimes() {
/* 140 */     return ((DestinyEntity)getEntity()).getRobTimes();
/*     */   }
/*     */   
/*     */   public synchronized void addRobTimes(int addTimes) {
/* 144 */     ((DestinyEntity)getEntity()).setRobTimes(((DestinyEntity)getEntity()).getRobTimes() + addTimes);
/*     */   }
/*     */   
/*     */   public Set<Integer> getTasks() {
/* 148 */     return ((DestinyEntity)getEntity()).getTasks();
/*     */   }
/*     */   
/*     */   public void setTasks(Set<Integer> tasks) {
/* 152 */     ((DestinyEntity)getEntity()).setTasks(tasks);
/*     */   }
/*     */   
/*     */   public int getDestinyStone() {
/* 156 */     return ((DestinyEntity)getEntity()).getDestinyStone();
/*     */   }
/*     */   
/*     */   public void setDestinyStone(int destinyStone) {
/* 160 */     ((DestinyEntity)getEntity()).setDestinyStone(destinyStone);
/*     */   }
/*     */   
/*     */   public int getTotalRobTimes() {
/* 164 */     return ((DestinyEntity)getEntity()).getTotalRobTimes();
/*     */   }
/*     */   
/*     */   public void setTotalRobTimes(int totalRobTimes) {
/* 168 */     ((DestinyEntity)getEntity()).setTotalRobTimes(totalRobTimes);
/*     */   }
/*     */   
/*     */   public int getRefreshTimes() {
/* 172 */     return ((DestinyEntity)getEntity()).getRefreshTimes();
/*     */   }
/*     */   
/*     */   public void setRefreshTimes(int refreshTimes) {
/* 176 */     ((DestinyEntity)getEntity()).setRefreshTimes(refreshTimes);
/*     */   }
/*     */   
/*     */   public Map<Long, DestinyPlayerData> getRecommentsCacheData() {
/* 180 */     return this.recommentsCacheData;
/*     */   }
/*     */   
/*     */   public void setRecommentsCacheData(ArrayList<DestinyPlayerData> recommentsCacheData) {
/* 184 */     this.recommentsCacheData.clear();
/* 185 */     for (DestinyPlayerData data : recommentsCacheData) {
/* 186 */       this.recommentsCacheData.put(Long.valueOf(data.getPlayerId()), data);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getLastResetTime() {
/* 191 */     return ((DestinyEntity)getEntity()).getLastResetTime();
/*     */   }
/*     */   
/*     */   public void setLastResetTime(int lastResetTime) {
/* 195 */     ((DestinyEntity)getEntity()).setLastResetTime(lastResetTime);
/*     */   }
/*     */   
/*     */   public void refreshRecomment(int recommentSize, boolean force) {
/* 199 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 200 */     DestinyPlayerData myplayerData = CrossUtil.buildLocalDestinyPlayerData(this.playerId);
/* 201 */     HashSet<Long> playerSet = new HashSet<>();
/* 202 */     if (recommentSize != destinyParameter.getPlayersSize()) {
/* 203 */       playerSet.addAll(getBattles().keySet());
/*     */     }
/* 205 */     ArrayList<DestinyPlayerData> recomments = CrossUtil.destinyRecomment("DestinyComponent::refreshRecomment", myplayerData, playerSet, recommentSize);
/* 206 */     if (recomments != null) {
/* 207 */       setRecommentsCacheData(recomments);
/* 208 */       HashMap<Long, Byte> battleMap = new HashMap<>();
/* 209 */       if (force) {
/* 210 */         for (DestinyPlayerData data : recomments) {
/* 211 */           battleMap.put(Long.valueOf(data.getPlayerId()), Byte.valueOf((byte)0));
/*     */         }
/* 213 */         setBattles(battleMap);
/*     */       } else {
/* 215 */         for (DestinyPlayerData data : recomments) {
/* 216 */           if (getBattles().containsKey(Long.valueOf(data.getPlayerId()))) {
/* 217 */             battleMap.put(Long.valueOf(data.getPlayerId()), getBattles().get(Long.valueOf(data.getPlayerId()))); continue;
/*     */           } 
/* 219 */           if (battleMap.size() < destinyParameter.getPlayersSize()) {
/* 220 */             battleMap.put(Long.valueOf(data.getPlayerId()), Byte.valueOf((byte)0));
/*     */           }
/*     */         } 
/*     */         
/* 224 */         setBattles(battleMap);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\destiny\DestinyComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */