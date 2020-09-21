/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FortuneTimeBean;
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
/*     */ import java.util.Set;
/*     */ 
/*     */ public class FortuneCatParameter extends AbstractParameter {
/*     */   public FortuneCatParameter() {
/*  19 */     super(80);
/*     */ 
/*     */ 
/*     */     
/*  23 */     this.festivalTimes = new HashMap<>();
/*  24 */     this.vipCounts = new HashMap<>();
/*     */   }
/*     */   
/*     */   private static final int TYPE_1 = 1;
/*     */   private Map<Integer, FestivalTime> festivalTimes;
/*     */   private Map<Integer, Integer> vipCounts;
/*     */   private int maxLength;
/*     */   private int defaultNum;
/*     */   
/*     */   public FestivalTime getFestivalTime(int id) {
/*  34 */     return this.festivalTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getFestivalTimes() {
/*  42 */     return new HashMap<>(this.festivalTimes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/*  51 */     Map<Integer, FestivalTime> festivalTimeMap = getFestivalTimes();
/*  52 */     List<Integer> list = new ArrayList<>();
/*  53 */     Set<Integer> ids = festivalTimeMap.keySet();
/*  54 */     for (Integer actId : ids) {
/*  55 */       if (isOpen) {
/*  56 */         if (checkActOpen(actId.intValue()))
/*  57 */           list.add(actId); 
/*     */         continue;
/*     */       } 
/*  60 */       if (!checkActOpen(actId.intValue())) {
/*  61 */         list.add(actId);
/*     */       }
/*     */     } 
/*     */     
/*  65 */     Collections.sort(list);
/*  66 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkActOpen(int actId) {
/*  75 */     FortuneTimeBean fortuneTimeBean = (FortuneTimeBean)JsonTableService.getJsonData(actId, FortuneTimeBean.class);
/*  76 */     if (null == fortuneTimeBean) {
/*  77 */       return false;
/*     */     }
/*  79 */     boolean flag1 = LimitUtil.isValid(fortuneTimeBean.getServerType(), fortuneTimeBean.getDay());
/*  80 */     if (!flag1) {
/*  81 */       return false;
/*     */     }
/*  83 */     FestivalTime festivalTime = getFestivalTime(fortuneTimeBean.getId());
/*  84 */     int curTime = TimeUtil.currentTime();
/*  85 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  86 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActOpen(int actId) {
/*  95 */     List<Integer> list = getActId(true);
/*  96 */     return list.contains(Integer.valueOf(actId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount(int vip) {
/* 105 */     return ((Integer)this.vipCounts.getOrDefault(Integer.valueOf(vip), Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxLength() {
/* 113 */     return this.maxLength;
/*     */   }
/*     */   
/*     */   public synchronized void initType() {
/* 117 */     this.festivalTimes.clear();
/* 118 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FortuneTimeBean.class);
/*     */     
/* 120 */     int openTime = MContext.getOpenTimeInt();
/* 121 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 122 */       FortuneTimeBean fortuneTimeBean = (FortuneTimeBean)entry.getValue();
/* 123 */       FestivalTime festivalTime = new FestivalTime();
/* 124 */       festivalTime.actId = fortuneTimeBean.getId();
/* 125 */       if (fortuneTimeBean.getServerType() == 0) {
/* 126 */         festivalTime.startTime = TimeUtil.getTimeFromDate(fortuneTimeBean.getBeginTime());
/* 127 */         festivalTime.endTime = TimeUtil.getTimeFromDate(fortuneTimeBean.getEndTime());
/*     */       } else {
/* 129 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.parseInt(fortuneTimeBean.getBeginTime()) - 1) * 86400;
/* 130 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.parseInt(fortuneTimeBean.getEndTime()) * 86400;
/*     */       } 
/* 132 */       this.festivalTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 139 */     this.vipCounts.clear();
/* 140 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 141 */     for (String string : strings) {
/* 142 */       String[] strings1 = string.split(":");
/* 143 */       this.vipCounts.put(Integer.valueOf(strings1[0]), Integer.valueOf(strings1[1]));
/*     */     } 
/*     */     
/* 146 */     this.maxLength = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 147 */     this.defaultNum = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 148 */     initType();
/*     */   }
/*     */   
/*     */   public int getDefaultNum() {
/* 152 */     return this.defaultNum;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\FortuneCatParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */