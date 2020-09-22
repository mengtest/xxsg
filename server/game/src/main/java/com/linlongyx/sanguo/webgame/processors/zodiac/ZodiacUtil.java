/*     */ package com.linlongyx.sanguo.webgame.processors.zodiac;
/*     */ 
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.zodiac.ZodiacEntity;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacTaskBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ZodiacParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class ZodiacUtil
/*     */ {
/*  23 */   private static ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Integer> getZodiacTasks(int level, int actid) {
/*  31 */     List<Integer> list = new ArrayList<>();
/*  32 */     ZodiacBean zodiacBean = (ZodiacBean)JsonTableService.getJsonData(actid, ZodiacBean.class);
/*  33 */     int targetLevel = Integer.MAX_VALUE;
/*  34 */     for (Integer id : zodiacBean.getLevelList()) {
/*  35 */       if (id.intValue() >= level && 
/*  36 */         targetLevel > id.intValue()) {
/*  37 */         targetLevel = id.intValue();
/*     */       }
/*     */     } 
/*     */     
/*  41 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ZodiacTaskBean.class);
/*  42 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  43 */       ZodiacTaskBean zodiacTaskBean = (ZodiacTaskBean)entry.getValue();
/*  44 */       if (targetLevel == zodiacTaskBean.getLevel()) {
/*  45 */         list.add(Integer.valueOf(zodiacTaskBean.getId()));
/*     */       }
/*     */     } 
/*  48 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addZodiacValue(long playerId, int targetType, int targetId, long value) {
/*  60 */     TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/*  61 */     if (null == taskTypeBean) {
/*     */       return;
/*     */     }
/*  64 */     if (taskTypeBean.getCountType() != 1 || 0L == value) {
/*     */       return;
/*     */     }
/*     */     
/*     */     try {
/*  69 */       sendProp(playerId, targetType, targetId, value);
/*  70 */     } catch (Exception e) {
/*  71 */       LogUtils.errorLog(new Object[] { "ZodiacUtil", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendProp(long playerId, int targetType, int targetId, long value) {
/*  79 */     ZodiacComponent zodiacComponent = (ZodiacComponent)LookUpService.getComponent(playerId, ZodiacComponent.class);
/*  80 */     if (zodiacComponent == null) {
/*     */       return;
/*     */     }
/*  83 */     ZodiacParameter zodiacParameter = (ZodiacParameter)ParameterConstant.getParameter(73);
/*  84 */     List<Integer> list = zodiacParameter.getZodiacAct(true);
/*  85 */     if (!list.isEmpty()) {
/*  86 */       for (Integer actId : list) {
/*  87 */         List<Integer> tasks = getZodiacTasks(zodiacComponent.getFirstLever(actId.intValue()), actId.intValue());
/*  88 */         for (Integer taskId : tasks) {
/*  89 */           ZodiacTaskBean zodiacTaskBean = (ZodiacTaskBean)JsonTableService.getJsonData(taskId.intValue(), ZodiacTaskBean.class);
/*  90 */           if (zodiacTaskBean == null) {
/*     */             continue;
/*     */           }
/*     */           
/*  94 */           int taskType = ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getType();
/*  95 */           int taskTargetId = ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getTargetId();
/*  96 */           int targetNum = ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getNum();
/*  97 */           if (taskType == targetType && taskTargetId == targetId) {
/*  98 */             Map<Integer, Integer> zodiaTasks = zodiacComponent.getZodiaTasks(actId.intValue());
/*     */             
/* 100 */             if (!zodiaTasks.containsKey(taskId)) {
/* 101 */               zodiaTasks.put(taskId, Integer.valueOf((int)value));
/*     */             }
/* 103 */             else if (((Integer)zodiaTasks.get(taskId)).intValue() <= targetNum) {
/* 104 */               zodiaTasks.put(taskId, Integer.valueOf((int)(((Integer)zodiaTasks.get(taskId)).intValue() + value)));
/*     */             } 
/*     */             
/* 107 */             if (((Integer)zodiaTasks.get(taskId)).intValue() >= targetNum && !zodiacComponent.getZodiaState(actId.intValue()).containsKey(taskId)) {
/* 108 */               zodiacComponent.getZodiaState(actId.intValue()).put(taskId, Integer.valueOf(1));
/* 109 */               zodiacComponent.setZodiaState(actId.intValue(), zodiacComponent.getZodiaState(actId.intValue()));
/*     */               
/* 111 */               PlayerUtil.sendRedNotice(Long.valueOf(playerId), RedNoticeConstant.Zodiac);
/*     */             } 
/* 113 */             zodiacComponent.setZodiaTasks(actId.intValue(), zodiaTasks);
/*     */           } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addSeriesRechargeConsumeCCY(ZodiacEntity zodiacEntity, int targetType, long value, int actId) {
/* 132 */     List<Integer> tasks = getZodiacTasks(zodiacEntity.getFirstLever(), actId);
/* 133 */     for (Integer taskId : tasks) {
/* 134 */       ZodiacTaskBean zodiacTaskBean = (ZodiacTaskBean)JsonTableService.getJsonData(taskId.intValue(), ZodiacTaskBean.class);
/* 135 */       if (zodiacTaskBean == null) {
/*     */         continue;
/*     */       }
/* 138 */       int taskType = ((ZodiacTaskBean.TypeBean)zodiacTaskBean.getType().get(0)).getType();
/*     */ 
/*     */       
/* 141 */       if (taskType == targetType) {
/* 142 */         Map<Integer, Integer> zodiaTasks = zodiacEntity.getZodiaTasks();
/* 143 */         int num = ((Integer)zodiaTasks.getOrDefault(taskId, Integer.valueOf(0))).intValue();
/* 144 */         zodiaTasks.put(taskId, Integer.valueOf(num + (int)value));
/* 145 */         zodiacEntity.setZodiaTasks(zodiaTasks);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\zodiac\ZodiacUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */