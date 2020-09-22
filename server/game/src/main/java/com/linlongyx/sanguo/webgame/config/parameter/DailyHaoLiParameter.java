/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EverydayRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DailyHaoLiParameter extends AbstractParameter {
/*     */   private Map<Integer, FestivalTime> actTimes;
/*     */   
/*     */   public DailyHaoLiParameter() {
/*  20 */     super(56);
/*     */ 
/*     */ 
/*     */     
/*  24 */     this.actTimes = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FestivalTime getActTime(int id) {
/*  33 */     return this.actTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  42 */     return this.actTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActOpen(int actId) {
/*  53 */     EverydayRewardBean everydayRewardBean = (EverydayRewardBean)JsonTableService.getJsonData(actId, EverydayRewardBean.class);
/*  54 */     if (null == everydayRewardBean) {
/*  55 */       return false;
/*     */     }
/*  57 */     boolean flag1 = LimitUtil.isValid(everydayRewardBean.getServerType(), everydayRewardBean.getDay());
/*  58 */     if (!flag1) {
/*  59 */       return false;
/*     */     }
/*  61 */     FestivalTime festivalTime = getActTime(everydayRewardBean.getId());
/*  62 */     int curTime = TimeUtil.currentTime();
/*  63 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  64 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/*  74 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/*  75 */     List<Integer> list = new ArrayList<>();
/*  76 */     actTimes.keySet().forEach(actId -> {
/*     */           if (isOpen) {
/*     */             if (isActOpen(actId.intValue())) {
/*     */               list.add(actId);
/*     */             }
/*     */           } else if (!isActOpen(actId.intValue())) {
/*     */             list.add(actId);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/*  87 */     Collections.sort(list);
/*  88 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/*  93 */     this.actTimes.clear();
/*  94 */     Map<Integer, Object> map = JsonTableService.getJsonTable(EverydayRewardBean.class);
/*     */     
/*  96 */     int openTime = MContext.getOpenTimeInt();
/*  97 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  98 */       EverydayRewardBean everydayRewardBean = (EverydayRewardBean)entry.getValue();
/*  99 */       FestivalTime festivalTime = new FestivalTime();
/* 100 */       festivalTime.actId = everydayRewardBean.getId();
/* 101 */       if (everydayRewardBean.getServerType() == 0) {
/* 102 */         festivalTime.startTime = TimeUtil.getTimeFromDate(everydayRewardBean.getBeginTime());
/* 103 */         festivalTime.endTime = TimeUtil.getTimeFromDate(everydayRewardBean.getEndTime());
/*     */       } else {
/* 105 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(everydayRewardBean.getBeginTime()).intValue() - 1) * 86400;
/* 106 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(everydayRewardBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 108 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\DailyHaoLiParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */