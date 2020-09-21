/*     */ package com.linlongyx.sanguo.webgame.app.continuity;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillinglistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.continuity.ContinueUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContinuityComponent
/*     */   extends AbstractMapComponent<ContinuityEntity>
/*     */ {
/*     */   public ContinuityComponent(long playerId) {
/*  31 */     super(ContinuityEntity.class, playerId);
/*     */   }
/*     */   
/*     */   public String getType() {
/*  35 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  40 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  45 */     if (time == 0) {
/*  46 */       resetExpired();
/*     */     }
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetExpired() {
/*  55 */     ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/*  56 */     List<Integer> list = continuityParameter.getActId(false);
/*  57 */     for (Integer id : list) {
/*  58 */       ContinuityEntity continuityEntity = getContinuityEntity(id.intValue());
/*  59 */       if (continuityEntity.isOpen()) {
/*  60 */         continuityEntity.setOpen(false);
/*  61 */         resetAll(continuityEntity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetAll(ContinuityEntity continuityEntity) {
/*  67 */     if (null == continuityEntity) {
/*     */       return;
/*     */     }
/*  70 */     LogUtils.errorLog(new Object[] { "ContinuityReset:before", Long.valueOf(continuityEntity.getPlayerId()), continuityEntity.toString() });
/*  71 */     int id = continuityEntity.getId();
/*  72 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  73 */     Map<Integer, Integer> states = continuityEntity.getStates();
/*  74 */     for (Map.Entry<Integer, Integer> entry : states.entrySet()) {
/*  75 */       if (((Integer)entry.getValue()).intValue() != 1) {
/*     */         continue;
/*     */       }
/*  78 */       if (((Integer)entry.getKey()).intValue() == -1) {
/*  79 */         ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(id, ContinFillingBean.class);
/*  80 */         if (null != continFillingBean)
/*  81 */           rewards.addAll(FinanceUtil.transform(continFillingBean.getTargetList())); 
/*     */         continue;
/*     */       } 
/*  84 */       ContinFillinglistBean continFillinglistBean = (ContinFillinglistBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), ContinFillinglistBean.class);
/*  85 */       if (null != continFillinglistBean) {
/*  86 */         rewards.addAll(FinanceUtil.transform(continFillinglistBean.getReward()));
/*     */       }
/*     */     } 
/*     */     
/*  90 */     if (!rewards.isEmpty()) {
/*  91 */       String title = LanguageConstant.getAndReplaceLanguage(2101, new String[] { "连充" });
/*  92 */       String content = LanguageConstant.getAndReplaceLanguage(2102, new String[] { "连充" });
/*  93 */       MailUtil.sendSysMail(this.playerId, rewards, title, content);
/*     */     } 
/*  95 */     if (continuityEntity.getStates().size() > 0) {
/*  96 */       continuityEntity.getStates().clear();
/*  97 */       updateStatesDB(id);
/*     */     } 
/*  99 */     if (continuityEntity.getDateCharges().size() > 0) {
/* 100 */       continuityEntity.getDateCharges().clear();
/* 101 */       updateDateChargesDB(id);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateStatesDB(int id) {
/* 106 */     getProxy().setUpdate(String.valueOf(id), "states");
/*     */   }
/*     */   
/*     */   public void updateDateChargesDB(int id) {
/* 110 */     getProxy().setUpdate(String.valueOf(id), "dateCharges");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 114 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public ContinuityEntity getContinuityEntity(int id) {
/* 118 */     ContinuityEntity continuityEntity = (ContinuityEntity)getEntity(String.valueOf(id));
/* 119 */     if (null == continuityEntity) {
/* 120 */       continuityEntity = new ContinuityEntity(this.playerId, id);
/* 121 */       addEntity((IEntity)continuityEntity);
/* 122 */       saveToDB();
/*     */     } 
/* 124 */     ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/* 125 */     boolean nowOpen = continuityParameter.checkActOpen(id);
/* 126 */     if (nowOpen) {
/* 127 */       if (!continuityEntity.isOpen()) {
/* 128 */         resetAll(continuityEntity);
/* 129 */         continuityEntity.setOpen(true);
/* 130 */         addTodayRecharge();
/* 131 */         updateOpenDB(id);
/*     */       }
/*     */     
/* 134 */     } else if (continuityEntity.isOpen()) {
/* 135 */       resetAll(continuityEntity);
/* 136 */       continuityEntity.setOpen(false);
/* 137 */       updateOpenDB(id);
/*     */     } 
/*     */     
/* 140 */     return continuityEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTodayRecharge() {
/* 148 */     IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/* 149 */     if (null == iPlayer) {
/*     */       return;
/*     */     }
/* 152 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 153 */     if (extendComponent.getZeroResetDate() != TimeUtil.getCurrentYearMonthDay()) {
/*     */       return;
/*     */     }
/* 156 */     long todayRecharge = extendComponent.getTodayRecharge();
/* 157 */     ContinueUtil.refresh(this.playerId, todayRecharge);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\continuity\ContinuityComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */