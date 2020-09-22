/*     */ package com.linlongyx.sanguo.webgame.app.arena;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TaskParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.FindRewardType;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaReportData;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class ArenaComponent
/*     */   extends AbstractComponent<ArenaEntity>
/*     */ {
/*     */   private Map<Integer, Integer> showMap;
/*     */   
/*     */   public ArenaComponent(long playerId) {
/*  25 */     super(ArenaEntity.class, playerId);
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.showMap = new HashMap<>();
/*     */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */   public String getType() {
/*  33 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  38 */     if (getRank() == 0) {
/*  39 */       ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*  40 */       setRank(arenaParameter.getDefaultRank());
/*     */     } 
/*  42 */     setPlayerId(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  47 */     super.init();
/*  48 */     if (getRank() == 0) {
/*  49 */       ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*  50 */       setRank(arenaParameter.getDefaultRank());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  56 */     if (time == 0) {
/*     */       
/*  58 */       setBuyTimes(0);
/*  59 */       setEliminateTimes(0);
/*  60 */       setFightTimes(0);
/*  61 */       PlayerComponent playerComponent = LookUpService.getPlayerComponent(this.playerId);
/*  62 */       int create = TimeUtil.getYearMonthDay(playerComponent.getCreateTime() + 86400);
/*  63 */       if (create >= TimeUtil.getCurrentYearMonthDay()) {
/*  64 */         SingleInsComponent singleInsComponent = (SingleInsComponent)LookUpService.getComponent(this.playerId, SingleInsComponent.class);
/*  65 */         if (null != singleInsComponent) {
/*  66 */           TaskParameter taskParameter = (TaskParameter)ParameterConstant.getParameter(22);
/*  67 */           int max = TaskUtil.getMaxCheckPoint(FindRewardType.ARENA.getType(), this.playerId);
/*  68 */           singleInsComponent.saveFindTimes(false, FindRewardType.ARENA.getType(), max * (taskParameter.getDays() - 1));
/*     */         } 
/*     */       } 
/*  71 */     } else if (time == 18000) {
/*     */     
/*     */     } 
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getShowMap() {
/*  78 */     return this.showMap;
/*     */   }
/*     */   
/*     */   public int getRank() {
/*  82 */     return ((ArenaEntity)getEntity()).getRank();
/*     */   }
/*     */   
/*     */   public void setRank(int rank) {
/*  86 */     ((ArenaEntity)getEntity()).setRank(rank);
/*     */   }
/*     */   
/*     */   public int getMaxRank() {
/*  90 */     return ((ArenaEntity)getEntity()).getMaxRank();
/*     */   }
/*     */   
/*     */   public void setMaxRank(int maxRank) {
/*  94 */     ((ArenaEntity)getEntity()).setMaxRank(maxRank);
/*     */   }
/*     */   
/*     */   public ArrayList<ArenaReportData> getReports() {
/*  98 */     return ((ArenaEntity)getEntity()).getReports();
/*     */   }
/*     */   
/*     */   public void setReports(ArrayList<ArenaReportData> reports) {
/* 102 */     ((ArenaEntity)getEntity()).setReports(reports);
/*     */   }
/*     */   
/*     */   public int getFightTimes() {
/* 106 */     return ((ArenaEntity)getEntity()).getFightTimes();
/*     */   }
/*     */   
/*     */   public void setFightTimes(int fightTimes) {
/* 110 */     ((ArenaEntity)getEntity()).setFightTimes(fightTimes);
/*     */   }
/*     */   
/*     */   public int getLastTime() {
/* 114 */     return ((ArenaEntity)getEntity()).getLastTime();
/*     */   }
/*     */   
/*     */   public void setLastTime(int lastTime) {
/* 118 */     ((ArenaEntity)getEntity()).setLastTime(lastTime);
/*     */   }
/*     */   
/*     */   public int getBuyTimes() {
/* 122 */     return ((ArenaEntity)getEntity()).getBuyTimes();
/*     */   }
/*     */   
/*     */   public void setBuyTimes(int buyTimes) {
/* 126 */     ((ArenaEntity)getEntity()).setBuyTimes(buyTimes);
/*     */   }
/*     */   
/*     */   public int getEliminateTimes() {
/* 130 */     return ((ArenaEntity)getEntity()).getEliminateTimes();
/*     */   }
/*     */   
/*     */   public void setEliminateTimes(int eliminateTimes) {
/* 134 */     ((ArenaEntity)getEntity()).setEliminateTimes(eliminateTimes);
/*     */   }
/*     */   
/*     */   public int getTotalFightTimes() {
/* 138 */     return ((ArenaEntity)getEntity()).getTotalFightTimes();
/*     */   }
/*     */   
/*     */   public void setTotalFightTimes(int totalFightTimes) {
/* 142 */     ((ArenaEntity)getEntity()).setTotalFightTimes(totalFightTimes);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\arena\ArenaComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */