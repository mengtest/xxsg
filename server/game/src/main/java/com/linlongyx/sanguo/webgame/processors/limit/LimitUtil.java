/*     */ package com.linlongyx.sanguo.webgame.processors.limit;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitEntity;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LimitCountType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.limit.LimitActRedResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LimitUtil {
/*  31 */   private static LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/*  32 */   private static volatile int expExpMulti = 1;
/*  33 */   private static volatile int vipExpMulti = 1;
/*  34 */   private static volatile int vipBossMulti = 1;
/*  35 */   private static volatile int neutralBossMulti = 1;
/*     */ 
/*     */   
/*  38 */   private static Set<Integer> actSet = new HashSet<>();
/*  39 */   private static Set<Integer> notHandleType = new HashSet<>();
/*     */   
/*  41 */   private static volatile Map<Integer, ArrayList<RankingData>> ranks = new HashMap<>();
/*     */   
/*     */   private static int preResetTime;
/*     */   
/*     */   public static final int TYPE_1 = 1;
/*     */   
/*     */   public static final int TYPE_2 = 2;
/*     */   public static final int TYPE_3 = 3;
/*     */   public static final int TYPE_4 = 4;
/*     */   public static final int TASK_TYPE_EXCHANGE = 0;
/*     */   
/*     */   static {
/*  53 */     notHandleType.add(Integer.valueOf(TaskType.Login.getType()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addFestValue(int targetType, long value, long playerId, int targetId) {
/*  64 */     TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/*  65 */     if (null == taskTypeBean) {
/*     */       return;
/*     */     }
/*  68 */     if (taskTypeBean.getCountType() == 1 && 0L == value) {
/*     */       return;
/*     */     }
/*     */     try {
/*  72 */       limitHandle(targetType, value, playerId, targetId);
/*  73 */     } catch (Exception e) {
/*  74 */       LogUtils.errorLog(new Object[] { "LimitUtilAddFestValue", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void limitHandle(int targetType, long value, long playerId, int targetId) {
/*  79 */     if (notHandleType.contains(Integer.valueOf(targetType))) {
/*     */       return;
/*     */     }
/*  82 */     LimitComponent limitComponent = (LimitComponent)LookUpService.getComponent(playerId, LimitComponent.class);
/*  83 */     if (null == limitComponent) {
/*     */       return;
/*     */     }
/*  86 */     if (TaskType.Charge.getType() == targetType) {
/*  87 */       addSeriesRecharge(value, limitComponent);
/*  88 */     } else if (TaskType.ConsumeCCY.getType() == targetType) {
/*  89 */       addConsumeCCY(value, limitComponent);
/*     */     } 
/*     */     
/*  92 */     Set<Integer> sets = limitActParameter.getLimitActivitylist(targetType);
/*     */     
/*  94 */     if (null == sets || sets.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     for (Integer itemId : sets) {
/*  99 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 100 */       if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */         continue;
/*     */       }
/*     */       
/* 104 */       Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 105 */       if (null == ids || ids.isEmpty()) {
/*     */         continue;
/*     */       }
/* 108 */       for (Integer id : ids) {
/* 109 */         LimitEntity limitEntity = limitComponent.getEntity(id.intValue());
/* 110 */         if (!limitEntity.isOpen()) {
/*     */           continue;
/*     */         }
/* 113 */         if (limitActivitylistBean.getTargetType() == TaskType.EnterGame.getType() && limitComponent.judgeDone(limitEntity, limitActivitylistBean)) {
/*     */           continue;
/*     */         }
/* 116 */         countValue(limitComponent, limitEntity, value, limitActivitylistBean, targetId);
/*     */       } 
/*     */     } 
/* 119 */     if (targetType == TaskType.ActivityTotalCharge.getType()) {
/* 120 */       updateChargeCCY(limitComponent, value, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addSeriesRecharge(long value, LimitComponent limitComponent) {
/* 131 */     int date = TimeUtil.getCurrentYearMonthDay();
/* 132 */     Set<Integer> seriesIds = limitActParameter.getSeriesIds();
/* 133 */     List<Integer> openList = limitActParameter.getActId(true);
/* 134 */     for (Integer id : seriesIds) {
/* 135 */       if (!openList.contains(id)) {
/*     */         continue;
/*     */       }
/* 138 */       LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(id.intValue(), LimitActivityBean.class);
/* 139 */       if (null == limitActivityBean) {
/*     */         continue;
/*     */       }
/* 142 */       LimitEntity limitEntity = limitComponent.getEntity(id.intValue());
/*     */       
/* 144 */       Map<Integer, Long> dateCharges = limitEntity.getDateCharges();
/* 145 */       Long charge = dateCharges.getOrDefault(Integer.valueOf(date), Long.valueOf(0L));
/* 146 */       charge = Long.valueOf(charge.longValue() + value);
/* 147 */       dateCharges.put(Integer.valueOf(date), charge);
/* 148 */       limitEntity.setDateCharges(dateCharges);
/* 149 */       limitComponent.updateDateChargesDB(id.intValue());
/*     */       
/* 151 */       Map<Integer, Long> values = limitEntity.getValues();
/* 152 */       for (Integer itemId : limitActivityBean.getActivityList()) {
/* 153 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 154 */         if (null == limitActivitylistBean || limitActivitylistBean.getTargetType() != TaskType.SeriesRecharge.getType()) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 161 */         long day = 0L;
/* 162 */         for (Map.Entry<Integer, Long> entry : dateCharges.entrySet()) {
/* 163 */           if (((Long)entry.getValue()).longValue() >= limitActivitylistBean.getTargetS()) {
/* 164 */             day++;
/*     */           }
/*     */         } 
/* 167 */         if (0L == day) {
/*     */           continue;
/*     */         }
/* 170 */         values.put(itemId, Long.valueOf(day));
/* 171 */         limitEntity.setValues(values);
/* 172 */         limitComponent.updateValuesDB(id.intValue());
/* 173 */         boolean isDone = (day >= limitActivitylistBean.getTarget());
/* 174 */         if (isDone) {
/* 175 */           limitComponent.done(limitEntity, limitActivitylistBean);
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
/*     */   private static void addConsumeCCY(long value, LimitComponent limitComponent) {
/* 189 */     Set<Integer> consumeIds = limitActParameter.getConsumeIds();
/* 190 */     List<Integer> openList = limitActParameter.getActId(true);
/* 191 */     for (Integer id : consumeIds) {
/* 192 */       if (!openList.contains(id)) {
/*     */         continue;
/*     */       }
/* 195 */       LimitEntity limitEntity = limitComponent.getEntity(id.intValue());
/* 196 */       long consumeValue = limitEntity.getConsumeValue();
/* 197 */       consumeValue += value;
/* 198 */       limitEntity.setConsumeValue(consumeValue);
/* 199 */       limitComponent.updateConsumeValueDB(id.intValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateChargeCCY(LimitComponent limitComponent, long value, boolean add) {
/* 210 */     Set<Integer> sets = limitActParameter.getLimitActivitylist(TaskType.ActivityTotalCharge.getType());
/* 211 */     if (null == sets || sets.isEmpty()) {
/*     */       return;
/*     */     }
/* 214 */     long lastChargeValue = 0L;
/* 215 */     Collections.sort(new ArrayList<>((Collection)sets));
/* 216 */     for (Integer itemId : sets) {
/* 217 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 218 */       if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */         continue;
/*     */       }
/* 221 */       Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 222 */       if (null == ids || ids.isEmpty()) {
/*     */         continue;
/*     */       }
/* 225 */       for (Integer id : ids) {
/* 226 */         LimitEntity limitEntity = limitComponent.getEntity(id.intValue());
/* 227 */         if (!limitEntity.isOpen()) {
/*     */           continue;
/*     */         }
/* 230 */         long chargeValue = limitEntity.getChargeValue();
/* 231 */         if (add) {
/* 232 */           if (chargeValue != 0L) {
/* 233 */             value = 0L;
/*     */           }
/* 235 */           chargeValue += value;
/*     */         } else {
/* 237 */           chargeValue += value;
/*     */         } 
/* 239 */         limitEntity.setChargeValue(chargeValue);
/* 240 */         if (chargeValue > lastChargeValue) {
/* 241 */           lastChargeValue = chargeValue;
/*     */         }
/*     */       } 
/*     */     } 
/* 245 */     for (Integer itemId : sets) {
/* 246 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 247 */       if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */         continue;
/*     */       }
/* 250 */       Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 251 */       if (null == ids || ids.isEmpty()) {
/*     */         continue;
/*     */       }
/* 254 */       for (Integer id : ids) {
/* 255 */         LimitEntity limitEntity = limitComponent.getEntity(id.intValue());
/* 256 */         if (!limitEntity.isOpen()) {
/*     */           continue;
/*     */         }
/* 259 */         int chargeId = limitActivitylistBean.getId();
/* 260 */         Map<Integer, Long> values = limitEntity.getValues();
/* 261 */         values.put(Integer.valueOf(chargeId), Long.valueOf(lastChargeValue));
/* 262 */         boolean isDone = (((Long)values.getOrDefault(id, Long.valueOf(0L))).longValue() == limitActivitylistBean.getTarget());
/* 263 */         if (isDone) {
/* 264 */           limitComponent.done(limitEntity, limitActivitylistBean);
/*     */         }
/* 266 */         limitEntity.setValues(values);
/* 267 */         limitComponent.updateValuesDB(limitEntity.getId());
/* 268 */         if (value != 0L) {
/* 269 */           LogUtil.errorLog(new Object[] { "ActivityTotalCharge", Long.valueOf(limitEntity.getPlayerId()), Integer.valueOf(limitEntity.getId()), id, Boolean.valueOf(isDone), Long.valueOf(lastChargeValue) });
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
/*     */   public static void countValue(LimitComponent limitComponent, LimitEntity limitEntity, long value, LimitActivitylistBean limitActivitylistBean, int targetId) {
/* 284 */     long defaultValue = 0L;
/* 285 */     int id = limitActivitylistBean.getId();
/* 286 */     int targetType = limitActivitylistBean.getTargetType();
/* 287 */     TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 288 */     if (null == taskTypeBean) {
/*     */       return;
/*     */     }
/* 291 */     Map<Integer, Long> values = limitEntity.getValues();
/* 292 */     long count = 0L;
/* 293 */     boolean isDone = false;
/* 294 */     if (taskTypeBean.getCountType() == 0) {
/* 295 */       long value2 = TaskUtil.getSchedule(limitComponent.getPlayerId(), targetType, limitActivitylistBean.getTargetS());
/* 296 */       values.put(Integer.valueOf(id), Long.valueOf(value2));
/* 297 */       if (taskTypeBean.getCount() == 1) {
/* 298 */         isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() <= limitActivitylistBean.getTarget());
/* 299 */       } else if (taskTypeBean.getCount() == 2) {
/* 300 */         isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() == limitActivitylistBean.getTarget());
/*     */       } else {
/* 302 */         isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/*     */       } 
/* 304 */       if (isDone) {
/* 305 */         limitComponent.done(limitEntity, limitActivitylistBean);
/*     */       }
/*     */     }
/* 308 */     else if (taskTypeBean.getCount() == LimitCountType.singleDone.getType()) {
/* 309 */       count += ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() + value;
/* 310 */       values.put(Integer.valueOf(id), Long.valueOf(count));
/* 311 */       isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 312 */       if (isDone) {
/* 313 */         limitComponent.done(limitEntity, limitActivitylistBean);
/*     */       }
/* 315 */     } else if (taskTypeBean.getCount() == LimitCountType.singleEqual.getType()) {
/* 316 */       count = value;
/* 317 */       values.put(Integer.valueOf(id), Long.valueOf(count));
/* 318 */       isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() <= limitActivitylistBean.getTarget());
/* 319 */       if (isDone) {
/* 320 */         limitComponent.done(limitEntity, limitActivitylistBean);
/*     */       }
/* 322 */     } else if (taskTypeBean.getCount() == LimitCountType.cumDone.getType()) {
/* 323 */       count = value;
/* 324 */       values.put(Integer.valueOf(id), Long.valueOf(count));
/* 325 */       isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() == limitActivitylistBean.getTarget());
/* 326 */       if (isDone) {
/* 327 */         limitComponent.done(limitEntity, limitActivitylistBean);
/*     */       }
/* 329 */     } else if (taskTypeBean.getCount() == LimitCountType.cumMoreDone.getType() && 
/* 330 */       limitActivitylistBean.getTarget() == value) {
/* 331 */       count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() + 1L;
/* 332 */       values.put(Integer.valueOf(id), Long.valueOf(count));
/* 333 */       IPlayer iPlayer = LookUpService.getByPlayerId(limitComponent.getPlayerId());
/* 334 */       if (null != iPlayer) {
/* 335 */         sendRed(iPlayer.getSession(), limitEntity.getId());
/*     */       }
/* 337 */       isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getNum());
/* 338 */       if (isDone) {
/* 339 */         limitComponent.done(limitEntity, limitActivitylistBean);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 344 */     limitEntity.setValues(values);
/* 345 */     limitComponent.updateValuesDB(limitEntity.getId());
/* 346 */     LogUtil.errorLog(new Object[] { "LimitCountValue", Long.valueOf(limitEntity.getPlayerId()), Integer.valueOf(limitEntity.getId()), Integer.valueOf(id), Long.valueOf(value), Long.valueOf(count), Boolean.valueOf(isDone) });
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
/*     */   public static void countValue2(LimitComponent limitComponent, LimitEntity limitEntity, ArrayList<Integer> actIdList) {
/* 358 */     for (Integer itemId : actIdList) {
/* 359 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 360 */       if (null == limitActivitylistBean) {
/*     */         continue;
/*     */       }
/*     */       
/* 364 */       long defaultValue = 0L;
/* 365 */       int id = limitActivitylistBean.getId();
/* 366 */       int targetType = limitActivitylistBean.getTargetType();
/* 367 */       if (targetType == 0) {
/*     */         continue;
/*     */       }
/* 370 */       TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 371 */       Map<Integer, Long> values = limitEntity.getValues();
/* 372 */       if (taskTypeBean.getCountType() == 1) {
/*     */         continue;
/*     */       }
/* 375 */       if (!limitEntity.isOpen() && taskTypeBean.getCount() != 1) {
/*     */         break;
/*     */       }
/* 378 */       boolean isDone = false;
/* 379 */       long value2 = TaskUtil.getSchedule(limitComponent.getPlayerId(), targetType, limitActivitylistBean.getTargetS());
/* 380 */       if (!limitEntity.isOpen() && taskTypeBean.getCount() == 1 && ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(0L))).longValue() != 0L) {
/*     */         break;
/*     */       }
/* 383 */       values.put(Integer.valueOf(id), Long.valueOf(value2));
/* 384 */       if (taskTypeBean.getCount() == 1) {
/* 385 */         isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() <= limitActivitylistBean.getTarget());
/*     */       } else {
/* 387 */         isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/*     */       } 
/*     */       
/* 390 */       if (isDone) {
/* 391 */         limitComponent.done(limitEntity, limitActivitylistBean);
/*     */       }
/*     */       
/* 394 */       limitEntity.setValues(values);
/* 395 */       limitComponent.updateValuesDB(limitEntity.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateLogin(IPlayer iPlayer) {
/* 406 */     Set<Integer> loginIns = limitActParameter.getLoginIns();
/* 407 */     List<Integer> openList = limitActParameter.getActId(true);
/* 408 */     LimitComponent limitComponent = (LimitComponent)iPlayer.getComponent(LimitComponent.class);
/* 409 */     for (Integer id : loginIns) {
/* 410 */       if (!openList.contains(id)) {
/*     */         continue;
/*     */       }
/*     */       
/* 414 */       LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(id.intValue(), LimitActivityBean.class);
/* 415 */       if (null == limitActivityBean) {
/*     */         continue;
/*     */       }
/*     */       
/* 419 */       LimitEntity limitEntity = limitComponent.getEntity(id.intValue());
/* 420 */       long day = limitEntity.getLoginDay();
/* 421 */       int loginTiem = limitEntity.getLoginTime();
/* 422 */       if (loginTiem == 0) {
/* 423 */         loginTiem = TimeUtil.currentTime();
/* 424 */         limitEntity.setLoginTime(loginTiem);
/* 425 */         limitComponent.updateLoginTimeDB(id.intValue());
/* 426 */         day++;
/* 427 */         limitEntity.setLoginDay((int)day);
/* 428 */         limitComponent.updateLoginDayDB(id.intValue());
/*     */       } else {
/* 430 */         int login = TimeUtil.getDayDiffToOpen(loginTiem, TimeUtil.currentTime());
/* 431 */         if (login > 1) {
/* 432 */           limitEntity.setLoginTime(TimeUtil.currentTime());
/* 433 */           limitComponent.updateLoginTimeDB(id.intValue());
/* 434 */           day++;
/* 435 */           limitEntity.setLoginDay((int)day);
/* 436 */           limitComponent.updateLoginDayDB(id.intValue());
/*     */         } 
/*     */       } 
/*     */       
/* 440 */       Map<Integer, Long> values = limitEntity.getValues();
/* 441 */       for (Integer itemId : limitActivityBean.getActivityList()) {
/* 442 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 443 */         if (null == limitActivitylistBean || limitActivitylistBean.getTargetType() != TaskType.Login.getType()) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 449 */         values.put(itemId, Long.valueOf(day));
/* 450 */         limitEntity.setValues(values);
/* 451 */         limitComponent.updateValuesDB(id.intValue());
/* 452 */         boolean isDone = (day >= Integer.valueOf(limitActivitylistBean.getTarget()).intValue());
/* 453 */         if (isDone) {
/* 454 */           limitComponent.done(limitEntity, limitActivitylistBean);
/*     */         }
/* 456 */         LogUtil.errorLog(new Object[] { "LimitUpdateLogin", Long.valueOf(limitEntity.getPlayerId()), Integer.valueOf(limitEntity.getId()), id, Long.valueOf(day), Boolean.valueOf(isDone) });
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
/*     */   public static void sendRed(IPlayerSession playerSession, int id) {
/* 469 */     LimitActRedResponse limitActRedResponse = new LimitActRedResponse();
/* 470 */     limitActRedResponse.actId = id;
/* 471 */     playerSession.sendMessage((ResponseBase)limitActRedResponse);
/*     */   }
/*     */   
/*     */   public static int getKey(List<Integer> list) {
/* 475 */     int worldLevel = RankingLevel.getWorldLevel();
/* 476 */     Collections.sort(list);
/* 477 */     int selectKey = ((Integer)list.get(0)).intValue();
/* 478 */     for (Integer i : list) {
/* 479 */       if (worldLevel >= i.intValue()) {
/* 480 */         selectKey = i.intValue();
/*     */       }
/*     */     } 
/* 483 */     return selectKey;
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
/*     */   public static boolean isValid(int serverType, int configDay) {
/* 495 */     int curDay = TimeUtil.getDayDiffToOpen(MContext.getOpenTime());
/* 496 */     if (serverType == 0) {
/* 497 */       if (MContext.isHeFu()) {
/* 498 */         return true;
/*     */       }
/* 500 */       return (curDay >= configDay);
/* 501 */     }  if (serverType == 1) {
/* 502 */       if (!MContext.isHeFu()) {
/* 503 */         return false;
/*     */       }
/* 505 */     } else if (serverType == 2 && 
/* 506 */       MContext.isHeFu()) {
/* 507 */       return false;
/*     */     } 
/*     */     
/* 510 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean isActOpen(int serverType, String starttime, String endtime) {
/* 514 */     if (serverType == 0) {
/* 515 */       int curTime = TimeUtil.currentTime();
/* 516 */       int startTime = TimeUtil.getTimeFromDate(starttime);
/* 517 */       int endTime = TimeUtil.getTimeFromDate(endtime);
/* 518 */       return (startTime <= curTime && curTime <= endTime);
/*     */     } 
/* 520 */     int curDays = TimeUtil.getDayDiffToOpen(MContext.getOpenTime());
/* 521 */     int startDays = Integer.parseInt(starttime);
/* 522 */     int endDays = Integer.parseInt(endtime);
/* 523 */     return (startDays <= curDays && curDays <= endDays);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\limit\LimitUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */