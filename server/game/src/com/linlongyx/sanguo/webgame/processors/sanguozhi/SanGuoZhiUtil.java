/*     */ package com.linlongyx.sanguo.webgame.processors.sanguozhi;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SanGuoZhiUtil
/*     */ {
/*     */   public static void updateRecord(IPlayerSession playerSession, int id, int targetId, long value2, TaskType taskType) {
/*  33 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/*  34 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  35 */     SanGuoZhiEntity findHostEntity = sanGuoZhiComponent.getEntity2(id);
/*  36 */     if (null == findHostEntity) {
/*     */       return;
/*     */     }
/*  39 */     if (findHostEntity.isActivity()) {
/*     */       return;
/*     */     }
/*  42 */     Set<Integer> finishes = findHostEntity.getFinishes();
/*  43 */     Set<Integer> rewards = findHostEntity.getRewarded();
/*  44 */     Map<Integer, Long> values = findHostEntity.getValues();
/*  45 */     RecordStarBean recordStarBean = (RecordStarBean)JsonTableService.getJsonData(id, RecordStarBean.class);
/*     */     
/*  47 */     boolean flag1 = false;
/*  48 */     boolean flag2 = false;
/*  49 */     for (RecordStarBean.TaskTpyeBean taskTpyeBean : recordStarBean.getTaskTpye()) {
/*  50 */       int type = taskTpyeBean.getType();
/*  51 */       if (finishes.contains(Integer.valueOf(type))) {
/*     */         continue;
/*     */       }
/*  54 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(type, TaskTypeBean.class);
/*  55 */       if (null == taskTypeBean) {
/*     */         continue;
/*     */       }
/*  58 */       if (taskTypeBean.getCountType() == 0) {
/*  59 */         long value = TaskUtil.getSchedule(playerSession.getPlayer().getPlayerId(), type, taskTpyeBean.getTargetId());
/*  60 */         if (rewards.contains(Integer.valueOf(type)) || TaskUtil.isTaskDone(value, taskTpyeBean.getNum(), taskTypeBean.getCount())) {
/*  61 */           finishes.add(Integer.valueOf(type));
/*  62 */           flag1 = true;
/*     */         } 
/*  64 */         values.put(Integer.valueOf(type), Long.valueOf(value));
/*  65 */         flag2 = true;
/*     */       } 
/*  67 */       if (null == taskType) {
/*     */         continue;
/*     */       }
/*  70 */       if (taskTypeBean.getTaskType() == taskType.getType()) {
/*  71 */         boolean in = false;
/*  72 */         if (taskTypeBean.getCountType() == 1 && targetId != 0) {
/*  73 */           in = true;
/*  74 */           long couont = ((Long)values.getOrDefault(Integer.valueOf(type), Long.valueOf(0L))).longValue();
/*  75 */           boolean isDone = TaskUtil.isTaskDone2(targetId, taskTpyeBean.getTargetId());
/*  76 */           if (isDone) {
/*  77 */             couont += value2;
/*  78 */             values.put(Integer.valueOf(type), Long.valueOf(couont));
/*  79 */             flag2 = true;
/*     */           } 
/*  81 */           if ((rewards.contains(Integer.valueOf(type)) || isDone) && couont >= taskTpyeBean.getNum()) {
/*  82 */             finishes.add(Integer.valueOf(type));
/*  83 */             flag1 = true;
/*     */           } 
/*     */         } 
/*  86 */         if (taskTypeBean.getCountType() == 1 && value2 != 0L && !in) {
/*  87 */           long coun = ((Long)values.getOrDefault(Integer.valueOf(type), Long.valueOf(0L))).longValue();
/*  88 */           coun += value2;
/*  89 */           if (rewards.contains(Integer.valueOf(type)) || TaskUtil.isTaskDone(coun, taskTpyeBean.getNum(), taskTypeBean.getCount())) {
/*  90 */             finishes.add(Integer.valueOf(type));
/*  91 */             flag1 = true;
/*     */           } 
/*  93 */           values.put(Integer.valueOf(type), Long.valueOf(coun));
/*  94 */           flag2 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*  98 */     if (flag1) {
/*  99 */       findHostEntity.setFinishes(finishes);
/* 100 */       sanGuoZhiComponent.updateFinishesDB(id);
/*     */     } 
/* 102 */     if (flag2) {
/* 103 */       findHostEntity.setValues(values);
/* 104 */       sanGuoZhiComponent.updateValuesDB(id);
/*     */     } 
/* 106 */     short code = CostUtil.checkRewards(FinanceUtil.transform(recordStarBean.getCost()), playerSession, bagComponent);
/* 107 */     if ((recordStarBean.getTaskTpye().size() == findHostEntity.getFinishes().size() && code == 0) || findHostEntity.getFinishes().size() != findHostEntity.getRewarded().size()) {
/* 108 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.RecordStar.getSys(), findHostEntity.getRecordStarId());
/*     */     }
/*     */     
/* 111 */     if (recordStarBean.getTaskTpye().size() == findHostEntity.getRewarded().size() && findHostEntity.getFinishes().size() == findHostEntity.getRewarded().size() && code == 0) {
/* 112 */       PlayerUtil.sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.RecordStarSpec.getSys(), findHostEntity.getRecordStarId());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void gm(IPlayerSession playerSession, int recordStart) {
/* 118 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/* 119 */     SanGuoZhiEntity findHostEntity = sanGuoZhiComponent.getEntity2(recordStart);
/* 120 */     if (null == findHostEntity) {
/*     */       return;
/*     */     }
/* 123 */     if (findHostEntity.isActivity()) {
/*     */       return;
/*     */     }
/* 126 */     Set<Integer> finishes = findHostEntity.getFinishes();
/* 127 */     Set<Integer> rewards = findHostEntity.getRewarded();
/* 128 */     Map<Integer, Long> values = findHostEntity.getValues();
/* 129 */     RecordStarBean recordStarBean = (RecordStarBean)JsonTableService.getJsonData(recordStart, RecordStarBean.class);
/* 130 */     for (RecordStarBean.TaskTpyeBean taskTpyeBean : recordStarBean.getTaskTpye()) {
/* 131 */       values.put(Integer.valueOf(taskTpyeBean.getType()), Long.valueOf(taskTpyeBean.getNum()));
/* 132 */       finishes.add(Integer.valueOf(taskTpyeBean.getType()));
/* 133 */       rewards.contains(Integer.valueOf(taskTpyeBean.getType()));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\sanguozhi\SanGuoZhiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */