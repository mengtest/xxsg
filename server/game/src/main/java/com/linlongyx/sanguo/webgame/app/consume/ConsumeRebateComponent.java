/*     */ package com.linlongyx.sanguo.webgame.app.consume;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ConsumeRebateParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConsumeRebateComponent
/*     */   extends AbstractMapComponent<ConsumeRebateEntity>
/*     */ {
/*     */   public ConsumeRebateComponent(long playerId) {
/*  26 */     super(ConsumeRebateEntity.class, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getType() {
/*  31 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void build(long playerId) {
/*  36 */     this.playerId = playerId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/*  42 */     if (time == 0) {
/*  43 */       resetActivity();
/*     */     }
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ConsumeRebateEntity getEntity(int id) {
/*  50 */     ConsumeRebateEntity consumeRebateEntity = (ConsumeRebateEntity)getEntity(String.valueOf(id));
/*  51 */     if (null == consumeRebateEntity) {
/*  52 */       consumeRebateEntity = new ConsumeRebateEntity(this.playerId, id);
/*  53 */       addEntity((IEntity)consumeRebateEntity);
/*  54 */       saveToDB();
/*     */     } 
/*  56 */     ConsumeRebateParameter consumeRebateParameter = (ConsumeRebateParameter)ParameterConstant.getParameter(52);
/*  57 */     FestivalTime festivalTime = consumeRebateParameter.getActTime(id);
/*  58 */     if (null != festivalTime) {
/*  59 */       boolean nowOpen = consumeRebateParameter.isActOpen(id);
/*  60 */       if (nowOpen && !consumeRebateEntity.isOpen()) {
/*  61 */         consumeRebateEntity.setOpen(true);
/*  62 */         addTodayConSume(consumeRebateEntity);
/*     */       } 
/*     */     } 
/*  65 */     return consumeRebateEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addTodayConSume(ConsumeRebateEntity consumeRebateEntity) {
/*  75 */     IPlayer iPlayer = LookUpService.getByPlayerId(this.playerId);
/*  76 */     if (null == iPlayer) {
/*     */       return;
/*     */     }
/*  79 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/*  80 */     if (extendComponent.getZeroResetDate() != TimeUtil.getCurrentYearMonthDay()) {
/*     */       return;
/*     */     }
/*  83 */     long dailyConsume = extendComponent.getDailyConsume();
/*  84 */     int actId = consumeRebateEntity.getId();
/*  85 */     ChargeRewardBean chargeRewardBean = (ChargeRewardBean)JsonTableService.getJsonData(actId, ChargeRewardBean.class);
/*  86 */     if (null != chargeRewardBean && null != consumeRebateEntity) {
/*  87 */       if (chargeRewardBean.getActType() == 1) {
/*  88 */         if (consumeRebateEntity.getTakeConsume() == 0L) {
/*  89 */           consumeRebateEntity.setTakeConsume(dailyConsume);
/*  90 */           updateTakeConsumeDB(actId);
/*     */         } 
/*  92 */       } else if (chargeRewardBean.getActType() == 2) {
/*  93 */         if (consumeRebateEntity.getPostureConsume() == 0L) {
/*  94 */           consumeRebateEntity.setPostureConsume(dailyConsume);
/*  95 */           updatePostureConsumeDB(actId);
/*     */         } 
/*  97 */       } else if (chargeRewardBean.getActType() == 3 && 
/*  98 */         consumeRebateEntity.getZhenFaConsume() == 0L) {
/*  99 */         consumeRebateEntity.setZhenFaConsume(dailyConsume);
/* 100 */         updatezhenFaConsumeDB(actId);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetActivity() {
/* 111 */     ConsumeRebateParameter consumeRebateParameter = (ConsumeRebateParameter)ParameterConstant.getParameter(52);
/* 112 */     List<Integer> list = consumeRebateParameter.getActId(false);
/* 113 */     for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int actId = ((Integer)iterator.next()).intValue();
/* 114 */       ConsumeRebateEntity consumeRebateEntity = (ConsumeRebateEntity)getEntity(String.valueOf(actId));
/* 115 */       ChargeRewardBean chargeRewardBean = (ChargeRewardBean)JsonTableService.getJsonData(actId, ChargeRewardBean.class);
/* 116 */       if (null != chargeRewardBean && null != consumeRebateEntity) {
/* 117 */         if (consumeRebateEntity.getTakeConsume() > 0L || consumeRebateEntity.getZhenFaConsume() > 0L || consumeRebateEntity.getPostureConsume() > 0L) {
/* 118 */           LogUtil.errorLog(new Object[] { "consumeRebateEntity", Long.valueOf(this.playerId), Long.valueOf(consumeRebateEntity.getPlayerId()), Long.valueOf(consumeRebateEntity.getTakeConsume()), Long.valueOf(consumeRebateEntity.getZhenFaConsume()), Long.valueOf(consumeRebateEntity.getPostureConsume()), consumeRebateEntity.getPostureReward().toString(), consumeRebateEntity.getTakeReward().toString(), consumeRebateEntity.getZhenFaReward().toString() });
/*     */         }
/* 120 */         if (chargeRewardBean.getActType() == 1) {
/* 121 */           consumeRebateEntity.setTakeConsume(0L);
/* 122 */           consumeRebateEntity.setTakeReward(new HashMap<>());
/* 123 */           consumeRebateEntity.setOpen(false);
/* 124 */           updateTakeConsumeDB(actId);
/* 125 */           updateTakeRewardDB(actId);
/* 126 */           updateOpenDB(actId); continue;
/* 127 */         }  if (chargeRewardBean.getActType() == 2) {
/* 128 */           consumeRebateEntity.setPostureConsume(0L);
/* 129 */           consumeRebateEntity.setPostureReward(new HashMap<>());
/* 130 */           consumeRebateEntity.setOpen(false);
/* 131 */           updatePostureConsumeDB(actId);
/* 132 */           updatePostureRewardDB(actId);
/* 133 */           updateOpenDB(actId); continue;
/* 134 */         }  if (chargeRewardBean.getActType() == 3) {
/* 135 */           consumeRebateEntity.setZhenFaConsume(0L);
/* 136 */           consumeRebateEntity.setZhenFaReward(new HashMap<>());
/* 137 */           consumeRebateEntity.setOpen(false);
/* 138 */           updatezhenFaConsumeDB(actId);
/* 139 */           updatezhenFaRewardDB(actId);
/* 140 */           updateOpenDB(actId);
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public void updateTakeConsumeDB(int id) {
/* 147 */     getProxy().setUpdate(String.valueOf(id), "takeConsume");
/*     */   }
/*     */   
/*     */   public void updateTakeRewardDB(int id) {
/* 151 */     getProxy().setUpdate(String.valueOf(id), "takeReward");
/*     */   }
/*     */   
/*     */   public void updatePostureConsumeDB(int id) {
/* 155 */     getProxy().setUpdate(String.valueOf(id), "postureConsume");
/*     */   }
/*     */   
/*     */   public void updatePostureRewardDB(int id) {
/* 159 */     getProxy().setUpdate(String.valueOf(id), "postureReward");
/*     */   }
/*     */   
/*     */   public void updateOpenDB(int id) {
/* 163 */     getProxy().setUpdate(String.valueOf(id), "open");
/*     */   }
/*     */   
/*     */   public void updatezhenFaConsumeDB(int id) {
/* 167 */     getProxy().setUpdate(String.valueOf(id), "zhenFaConsume");
/*     */   }
/*     */   
/*     */   public void updatezhenFaRewardDB(int id) {
/* 171 */     getProxy().setUpdate(String.valueOf(id), "zhenFaReward");
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\consume\ConsumeRebateComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */