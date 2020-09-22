/*     */ package com.linlongyx.sanguo.webgame.app.tower;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.framework.logic.AbstractComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TowerBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TowerOwnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TowerComponent
/*     */   extends AbstractComponent<TowerEntity>
/*     */ {
/*     */   private PlayerData targetPlayerData;
/*     */   private int cacheLayerId;
/*     */   
/*     */   public TowerComponent(long playerId) {
/*  30 */     super(TowerEntity.class, playerId);
/*  31 */     this.playerId = playerId;
/*  32 */     makeKey();
/*  33 */     getProxy().createProxy(new Object[] { Long.valueOf(playerId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  38 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  43 */     this.playerId = playerId;
/*  44 */     this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*  45 */     setMobai(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  50 */     if (time == 0) {
/*  51 */       if (getPreLayers() != 0) {
/*  52 */         dailyReward();
/*     */       }
/*  54 */       setPreLayers(getCurLayers());
/*  55 */       setTodayCCYReward(0);
/*  56 */       setTodayNormalReward(0);
/*  57 */       setMobai(1);
/*  58 */       setNeedSend((byte)0);
/*     */     } 
/*  60 */     return true;
/*     */   }
/*     */   
/*     */   public int getCurLayers() {
/*  64 */     return ((TowerEntity)getEntity()).getCurLayers();
/*     */   }
/*     */   
/*     */   public void setCurLayers(int curLayers) {
/*  68 */     ((TowerEntity)getEntity()).setCurLayers(curLayers);
/*     */   }
/*     */   
/*     */   public int getPreLayers() {
/*  72 */     return ((TowerEntity)getEntity()).getPreLayers();
/*     */   }
/*     */   
/*     */   public void setPreLayers(int preLayers) {
/*  76 */     ((TowerEntity)getEntity()).setPreLayers(preLayers);
/*     */   }
/*     */   
/*     */   public int getTodayNormalReward() {
/*  80 */     return ((TowerEntity)getEntity()).getTodayNormalReward();
/*     */   }
/*     */   
/*     */   public void setTodayNormalReward(int todayNormalReward) {
/*  84 */     ((TowerEntity)getEntity()).setTodayNormalReward(todayNormalReward);
/*     */   }
/*     */   
/*     */   public int getTodayCCYReward() {
/*  88 */     return ((TowerEntity)getEntity()).getTodayCCYReward();
/*     */   }
/*     */   
/*     */   public void setTodayCCYReward(int todayCCYReward) {
/*  92 */     ((TowerEntity)getEntity()).setTodayCCYReward(todayCCYReward);
/*     */   }
/*     */   
/*     */   public int getPassTime() {
/*  96 */     return ((TowerEntity)getEntity()).getPassTime();
/*     */   }
/*     */   
/*     */   public void setPassTime(int passTime) {
/* 100 */     ((TowerEntity)getEntity()).setPassTime(passTime);
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getActPlayTime() {
/* 104 */     return ((TowerEntity)getEntity()).getActPlayTime();
/*     */   }
/*     */   
/*     */   public void setActPlayTime(Map<Integer, Integer> actPlayTime) {
/* 108 */     ((TowerEntity)getEntity()).setActPlayTime(actPlayTime);
/*     */   }
/*     */   
/*     */   public int getMobai() {
/* 112 */     return ((TowerEntity)getEntity()).getMobai();
/*     */   }
/*     */   
/*     */   public void setMobai(int mobai) {
/* 116 */     ((TowerEntity)getEntity()).setMobai(mobai);
/*     */   }
/*     */   
/*     */   public byte getNeedSend() {
/* 120 */     return ((TowerEntity)getEntity()).getNeedSend();
/*     */   }
/*     */   
/*     */   public void setNeedSend(byte needSend) {
/* 124 */     ((TowerEntity)getEntity()).setNeedSend(needSend);
/*     */   }
/*     */   
/*     */   public LinkedList<BattleRecordData> getRecords() {
/* 128 */     return ((TowerEntity)getEntity()).getRecords();
/*     */   }
/*     */   
/*     */   public void setRecords(LinkedList<BattleRecordData> records) {
/* 132 */     ((TowerEntity)getEntity()).setRecords(records);
/*     */   }
/*     */   
/*     */   public PlayerData getTargetPlayerData() {
/* 136 */     return this.targetPlayerData;
/*     */   }
/*     */   
/*     */   public void setTargetPlayerData(PlayerData targetPlayerData) {
/* 140 */     this.targetPlayerData = targetPlayerData;
/*     */   }
/*     */   
/*     */   public int getCacheLayerId() {
/* 144 */     return this.cacheLayerId;
/*     */   }
/*     */   
/*     */   public void setCacheLayerId(int cacheLayerId) {
/* 148 */     this.cacheLayerId = cacheLayerId;
/*     */   }
/*     */   
/*     */   public void addRecord(BattleRecordData battleRecordData) {
/* 152 */     TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/* 153 */     LinkedList<BattleRecordData> recordList = getRecords();
/* 154 */     if (recordList.size() >= towerOwnerParameter.getRecordLimit()) {
/* 155 */       recordList.removeFirst();
/*     */     }
/* 157 */     recordList.addLast(battleRecordData);
/* 158 */     setRecords(recordList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dailyReward() {
/* 165 */     if (getTodayNormalReward() == 1) {
/*     */       return;
/*     */     }
/* 168 */     TowerBean towerBean = (TowerBean)JsonTableService.getJsonData(getCurLayers(), TowerBean.class);
/* 169 */     String title = LanguageConstant.getAndReplaceLanguage(4001, new String[] { towerBean.getName() });
/* 170 */     String content = LanguageConstant.getAndReplaceLanguage(4002, new String[] { towerBean.getName() });
/* 171 */     MailUtil.sendSysMail(this.playerId, FinanceUtil.rewardGet(FinanceUtil.transform(towerBean.getOrdinaryReward())), title, content);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\tower\TowerComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */