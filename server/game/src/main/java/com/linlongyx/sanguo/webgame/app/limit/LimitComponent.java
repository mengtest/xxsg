/*     */ package com.linlongyx.sanguo.webgame.app.limit;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitActNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActNoticeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LimitComponent
/*     */   extends AbstractMapComponent<LimitEntity>
/*     */ {
/*     */   public LimitComponent(long playerId) {
/*  32 */     super(LimitEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  37 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  42 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  47 */     if (time == 0) {
/*  48 */       LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/*  49 */       List<Integer> list = limitActParameter.getActId(true);
/*  50 */       for (Integer actId : list) {
/*  51 */         LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(actId.intValue(), LimitActivityBean.class);
/*  52 */         if (null != limitActivityBean && limitActivityBean.getActivityType() == 1) {
/*  53 */           LimitEntity limitEntity = getEntity(limitActivityBean.getId());
/*  54 */           resetAll(limitEntity);
/*     */         } 
/*     */       } 
/*  57 */       LimitUtil.updateChargeCCY(this, 0L, false);
/*     */       
/*  59 */       IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/*  60 */       if (null != iPlayer) {
/*  61 */         LimitUtil.updateLogin(iPlayer);
/*     */       }
/*     */       
/*  64 */       resetExpired();
/*     */     } 
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetAll(LimitEntity limitEntity) {
/*  75 */     if (null == limitEntity) {
/*     */       return;
/*     */     }
/*  78 */     if (limitEntity.getValues().size() > 0 || limitEntity.getStates().size() > 0 || limitEntity.getDateCharges().size() > 0) {
/*  79 */       LogUtil.errorLog(new Object[] { "LimitComponent::resetAll:before:", Long.valueOf(limitEntity.getPlayerId()), limitEntity.toString() });
/*     */     }
/*  81 */     int id = limitEntity.getId();
/*  82 */     Map<Integer, Integer> rewardCounts = limitEntity.getRewardCounts();
/*  83 */     Map<Integer, Long> values = limitEntity.getValues();
/*  84 */     limitEntity.setConsumeValue(0L);
/*  85 */     updateConsumeValueDB(id);
/*  86 */     limitEntity.setChargeValue(0L);
/*  87 */     updateChargeValueDB(id);
/*  88 */     limitEntity.setLoginDay(0);
/*  89 */     updateLoginDayDB(id);
/*  90 */     limitEntity.setLoginTime(0);
/*  91 */     updateLoginTimeDB(id);
/*  92 */     if (rewardCounts.size() > 0) {
/*  93 */       limitEntity.getRewardCounts().clear();
/*  94 */       updateRewardCountsDB(id);
/*     */     } 
/*  96 */     if (values.size() > 0) {
/*  97 */       limitEntity.getValues().clear();
/*  98 */       updateValuesDB(id);
/*     */     } 
/* 100 */     if (limitEntity.getStates().size() > 0) {
/* 101 */       limitEntity.getStates().clear();
/* 102 */       updateStatesDB(id);
/*     */     } 
/* 104 */     if (limitEntity.getDateCharges().size() > 0) {
/* 105 */       limitEntity.getDateCharges().clear();
/* 106 */       updateDateChargesDB(id);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetExpired() {
/* 114 */     LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/* 115 */     List<Integer> list = limitActParameter.getActId(false);
/* 116 */     reset(list, limitActParameter, false);
/* 117 */     List<Integer> listc = limitActParameter.getActId(false, true);
/* 118 */     reset(listc, limitActParameter, true);
/*     */   }
/*     */   
/*     */   public void reset(List<Integer> list, LimitActParameter limitActParameter, boolean resetAll) {
/* 122 */     for (Integer actId : list) {
/* 123 */       LimitEntity limitEntity = getEntity(actId.intValue());
/* 124 */       FestivalTime festivalTime = limitActParameter.getFestivalTime(actId.intValue());
/* 125 */       if (null != festivalTime && null != limitEntity) {
/* 126 */         LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(actId.intValue(), LimitActivityBean.class);
/* 127 */         if (null == limitActivityBean) {
/*     */           continue;
/*     */         }
/* 130 */         if (limitEntity.isOpen()) {
/* 131 */           limitEntity.setOpen(false);
/*     */         }
/* 133 */         if (resetAll) {
/* 134 */           resetAll(limitEntity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean judgeDone(LimitEntity limitEntity, LimitActivitylistBean limitActivitylistBean) {
/* 148 */     Map<Integer, Integer> states = limitEntity.getStates();
/* 149 */     int state = ((Integer)states.getOrDefault(Integer.valueOf(limitActivitylistBean.getId()), Integer.valueOf(0))).intValue();
/* 150 */     return (0 != state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void done(LimitEntity limitEntity, LimitActivitylistBean limitActivitylistBean) {
/* 160 */     int id = limitEntity.getId();
/* 161 */     int itemId = limitActivitylistBean.getId();
/* 162 */     Map<Integer, Long> values = limitEntity.getValues();
/* 163 */     Map<Integer, Integer> states = limitEntity.getStates();
/* 164 */     Map<Integer, Integer> rewardCounts = limitEntity.getRewardCounts();
/* 165 */     int count = ((Integer)rewardCounts.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/* 166 */     int num = limitActivitylistBean.getNum();
/* 167 */     if (limitActivitylistBean.getRewardType() == 1) {
/* 168 */       count++;
/* 169 */       rewardCounts.put(Integer.valueOf(itemId), Integer.valueOf(count));
/* 170 */       limitEntity.setRewardCounts(rewardCounts);
/* 171 */       updateRewardCountsDB(id);
/* 172 */       values.put(Integer.valueOf(itemId), Long.valueOf(0L));
/* 173 */       limitEntity.setValues(values);
/* 174 */       updateValuesDB(limitEntity.getId());
/* 175 */       if (count >= limitActivitylistBean.getNum()) {
/* 176 */         states.put(Integer.valueOf(itemId), Integer.valueOf(2));
/*     */       }
/* 178 */       IPlayer player = LookUpService.getByPlayerId(this.playerId);
/* 179 */       if (null != player) {
/* 180 */         FinanceUtil.reward(FinanceUtil.transform(limitActivitylistBean.getAward()), player, ResourceEvent.LimitAct, true);
/* 181 */         (new LimitActNoticeProcessor()).handle(player.getSession(), (RequestBase)new LimitActNoticeRequest());
/*     */       } 
/* 183 */       LogUtil.errorLog(new Object[] { "LimitAuto", Long.valueOf(this.playerId), Integer.valueOf(id), Integer.valueOf(itemId), Integer.valueOf(count), Boolean.valueOf((null == player)) });
/*     */     } else {
/* 185 */       int state = ((Integer)states.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/* 186 */       if (2 != state) {
/* 187 */         states.put(Integer.valueOf(itemId), Integer.valueOf(1));
/*     */       }
/*     */       
/* 190 */       IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/* 191 */       if (null != iPlayer && 1 != limitActivitylistBean.getDisplayType() && count < num) {
/* 192 */         LimitUtil.sendRed(iPlayer.getSession(), limitEntity.getId());
/*     */       }
/*     */     } 
/* 195 */     limitEntity.setStates(states);
/* 196 */     updateStatesDB(id);
/*     */   }
/*     */   
/*     */   public void updateRewardCountsDB(int id) {
/* 200 */     getProxy().setUpdate(String.valueOf(id), "rewardCounts");
/*     */   }
/*     */   
/*     */   public void updateValuesDB(int id) {
/* 204 */     getProxy().setUpdate(String.valueOf(id), "values");
/*     */   }
/*     */   
/*     */   public void updateStatesDB(int id) {
/* 208 */     getProxy().setUpdate(String.valueOf(id), "states");
/*     */   }
/*     */   
/*     */   public void updateDateChargesDB(int id) {
/* 212 */     getProxy().setUpdate(String.valueOf(id), "dateCharges");
/*     */   }
/*     */   
/*     */   public void updateConsumeValueDB(int id) {
/* 216 */     getProxy().setUpdate(String.valueOf(id), "consumeValue");
/*     */   }
/*     */   
/*     */   public void updateChargeValueDB(int id) {
/* 220 */     getProxy().setUpdate(String.valueOf(id), "chargeValue");
/*     */   }
/*     */   
/*     */   public void updateLoginDayDB(int id) {
/* 224 */     getProxy().setUpdate(String.valueOf(id), "loginDay");
/*     */   }
/*     */   
/*     */   public void updateLoginTimeDB(int id) {
/* 228 */     getProxy().setUpdate(String.valueOf(id), "loginTime");
/*     */   }
/*     */   
/*     */   public LimitEntity getEntity(int id) {
/* 232 */     LimitEntity limitEntity = (LimitEntity)getEntity(String.valueOf(id));
/* 233 */     if (null == limitEntity) {
/* 234 */       limitEntity = new LimitEntity(this.playerId, id);
/* 235 */       addEntity((IEntity)limitEntity);
/* 236 */       saveToDB();
/*     */     } 
/* 238 */     LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/* 239 */     FestivalTime festivalTime = limitActParameter.getFestivalTime(id);
/* 240 */     if (null != festivalTime) {
/* 241 */       boolean nowOpen = limitActParameter.isActOpen(id);
/* 242 */       boolean nowClose = limitActParameter.isActClose(id);
/* 243 */       if (nowOpen && !limitEntity.isOpen()) {
/* 244 */         resetAll(limitEntity);
/* 245 */         limitEntity.setOpen(true);
/* 246 */         addTodayRecharge(limitEntity);
/* 247 */         addTodayConsume(limitEntity);
/* 248 */       } else if (!nowClose && limitEntity.isOpen()) {
/* 249 */         LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(id, LimitActivityBean.class);
/* 250 */         if (null != limitActivityBean) {
/* 251 */           resetAll(limitEntity);
/* 252 */           limitEntity.setOpen(false);
/*     */         } 
/* 254 */       } else if (!nowOpen && limitEntity.isOpen()) {
/* 255 */         LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(id, LimitActivityBean.class);
/* 256 */         if (null != limitActivityBean) {
/* 257 */           limitEntity.setOpen(false);
/*     */         }
/*     */       } 
/*     */     } 
/* 261 */     return limitEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LimitEntity getEntity2(int id) {
/* 272 */     LimitEntity limitEntity = (LimitEntity)getEntity(String.valueOf(id));
/* 273 */     return limitEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTodayRecharge(LimitEntity limitEntity) {
/* 282 */     IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/* 283 */     if (null == iPlayer) {
/*     */       return;
/*     */     }
/* 286 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 287 */     if (extendComponent.getZeroResetDate() != TimeUtil.getCurrentYearMonthDay()) {
/*     */       return;
/*     */     }
/* 290 */     long todayRecharge = extendComponent.getTodayRecharge();
/* 291 */     LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(limitEntity.getId(), LimitActivityBean.class);
/* 292 */     for (Integer id : limitActivityBean.getActivityList()) {
/* 293 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(id.intValue(), LimitActivitylistBean.class);
/* 294 */       if (null == limitActivitylistBean || limitActivitylistBean.getTargetType() != TaskType.Charge.getType()) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 300 */       LimitUtil.countValue(this, limitEntity, todayRecharge, limitActivitylistBean, 0);
/*     */     } 
/* 302 */     LimitUtil.updateChargeCCY(this, todayRecharge, true);
/* 303 */     LimitUtil.addSeriesRecharge(todayRecharge, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTodayConsume(LimitEntity limitEntity) {
/* 312 */     IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/* 313 */     if (null == iPlayer) {
/*     */       return;
/*     */     }
/* 316 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 317 */     if (extendComponent.getZeroResetDate() != TimeUtil.getCurrentYearMonthDay()) {
/*     */       return;
/*     */     }
/* 320 */     long dailyConsume = extendComponent.getDailyConsume();
/* 321 */     LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(limitEntity.getId(), LimitActivityBean.class);
/* 322 */     for (Integer id : limitActivityBean.getActivityList()) {
/* 323 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(id.intValue(), LimitActivitylistBean.class);
/* 324 */       if (null == limitActivitylistBean || limitActivitylistBean.getTargetType() != TaskType.ConsumeCCY.getType()) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 330 */       LimitUtil.countValue(this, limitEntity, dailyConsume, limitActivitylistBean, 0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\limit\LimitComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */