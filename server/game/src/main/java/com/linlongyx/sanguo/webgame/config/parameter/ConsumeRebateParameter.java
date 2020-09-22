/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeRewardBean;
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
/*     */ public class ConsumeRebateParameter extends AbstractParameter {
/*     */   private Map<Integer, FestivalTime> actTimes;
/*     */   
/*     */   public ConsumeRebateParameter() {
/*  20 */     super(52);
/*     */ 
/*     */     
/*  23 */     this.actTimes = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FestivalTime getActTime(int id) {
/*  32 */     return this.actTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  41 */     return this.actTimes;
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
/*  52 */     ChargeRewardBean chargeRewardBean = (ChargeRewardBean)JsonTableService.getJsonData(actId, ChargeRewardBean.class);
/*  53 */     if (null == chargeRewardBean) {
/*  54 */       return false;
/*     */     }
/*  56 */     boolean flag1 = LimitUtil.isValid(chargeRewardBean.getServerType(), chargeRewardBean.getDay());
/*  57 */     if (!flag1) {
/*  58 */       return false;
/*     */     }
/*  60 */     FestivalTime festivalTime = getActTime(chargeRewardBean.getId());
/*  61 */     int curTime = TimeUtil.currentTime();
/*  62 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  63 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/*  73 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/*  74 */     List<Integer> list = new ArrayList<>();
/*  75 */     actTimes.keySet().forEach(actId -> {
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
/*  86 */     Collections.sort(list);
/*  87 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/*  94 */     this.actTimes.clear();
/*  95 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ChargeRewardBean.class);
/*     */     
/*  97 */     int openTime = MContext.getOpenTimeInt();
/*  98 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  99 */       ChargeRewardBean chargeRewardBean = (ChargeRewardBean)entry.getValue();
/* 100 */       FestivalTime festivalTime = new FestivalTime();
/* 101 */       festivalTime.actId = chargeRewardBean.getId();
/* 102 */       if (chargeRewardBean.getServerType() == 0) {
/* 103 */         festivalTime.startTime = TimeUtil.getTimeFromDate(chargeRewardBean.getBeginTime());
/* 104 */         festivalTime.endTime = TimeUtil.getTimeFromDate(chargeRewardBean.getEndTime());
/*     */       } else {
/* 106 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(chargeRewardBean.getBeginTime()).intValue() - 1) * 86400;
/* 107 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(chargeRewardBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 109 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\ConsumeRebateParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */