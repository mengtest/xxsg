/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class ContinuityParameter
/*     */   extends AbstractParameter
/*     */ {
/*     */   private Map<Integer, FestivalTime> festivalTimes;
/*     */   
/*     */   public ContinuityParameter() {
/*  23 */     super(21);
/*     */ 
/*     */     
/*  26 */     this.festivalTimes = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FestivalTime getFestivalTime(int id) {
/*  35 */     return this.festivalTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<Integer> getActId(boolean isOpen) {
/*  45 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ContinFillingBean.class);
/*  46 */     ArrayList<Integer> list = new ArrayList<>();
/*  47 */     for (Integer actId : map.keySet()) {
/*  48 */       if (isOpen) {
/*  49 */         if (checkActOpen(actId.intValue()))
/*  50 */           list.add(actId); 
/*     */         continue;
/*     */       } 
/*  53 */       if (!checkActOpen(actId.intValue())) {
/*  54 */         list.add(actId);
/*     */       }
/*     */     } 
/*     */     
/*  58 */     Collections.sort(list);
/*  59 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkActOpen(int actId) {
/*  69 */     ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(actId, ContinFillingBean.class);
/*  70 */     if (null == continFillingBean) {
/*  71 */       return false;
/*     */     }
/*  73 */     int curDay = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/*  74 */     boolean flag1 = isValid(continFillingBean.getServerType(), curDay, continFillingBean.getDay());
/*  75 */     if (!flag1) {
/*  76 */       return false;
/*     */     }
/*     */     
/*  79 */     FestivalTime festivalTime = getFestivalTime(continFillingBean.getId());
/*  80 */     int curTime = TimeUtil.currentTime();
/*  81 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  82 */     if (flag2 && !continFillingBean.getLimit().isEmpty()) {
/*  83 */       if (continFillingBean.getServerType() == 2 && ((Integer)continFillingBean.getLimit().get(0)).intValue() != 0) {
/*  84 */         LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  85 */         int startOpen = TimeUtil.getDayDiffToOpen(MContext.getOpenTimeInt(), loginParameter.getContinuityTimes());
/*  86 */         if (startOpen > ((Integer)continFillingBean.getLimit().get(0)).intValue()) {
/*  87 */           return false;
/*     */         }
/*  89 */       } else if (continFillingBean.getServerType() == 0) {
/*  90 */         int startOpen = TimeUtil.getDayDiffToOpen(MContext.getOpenTimeInt(), festivalTime.startTime);
/*  91 */         if (startOpen >= ((Integer)continFillingBean.getLimit().get(0)).intValue() && startOpen <= ((Integer)continFillingBean.getLimit().get(1)).intValue()) {
/*  92 */           return true;
/*     */         }
/*  94 */         return false;
/*     */       } 
/*     */     }
/*     */     
/*  98 */     return flag2;
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
/*     */   private boolean isValid(int serverType, int curDay, int configDay) {
/* 110 */     if (serverType == 0) {
/* 111 */       if (MContext.isHeFu()) {
/* 112 */         return true;
/*     */       }
/* 114 */       return (curDay >= configDay);
/* 115 */     }  if (serverType == 1 && !MContext.isHeFu())
/* 116 */       return false; 
/* 117 */     if (serverType == 2 && MContext.isHeFu()) {
/* 118 */       return false;
/*     */     }
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   private void initConfig() {
/* 124 */     this.festivalTimes.clear();
/* 125 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ContinFillingBean.class);
/*     */     
/* 127 */     int openTime = MContext.getOpenTimeInt();
/* 128 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 129 */       ContinFillingBean continFillingBean = (ContinFillingBean)entry.getValue();
/* 130 */       FestivalTime festivalTime = new FestivalTime();
/* 131 */       festivalTime.actId = continFillingBean.getId();
/* 132 */       if (continFillingBean.getServerType() == 0) {
/* 133 */         festivalTime.startTime = TimeUtil.getTimeFromDate(continFillingBean.getBeginTime());
/* 134 */         festivalTime.endTime = TimeUtil.getTimeFromDate(continFillingBean.getEndTime());
/*     */       } else {
/* 136 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.parseInt(continFillingBean.getBeginTime()) - 1) * 86400;
/* 137 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.parseInt(continFillingBean.getEndTime()) * 86400;
/*     */       } 
/* 139 */       this.festivalTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/* 141 */     LogUtils.errorLog(new Object[] { "ContinuityParameter", this.festivalTimes.toString() });
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 146 */     initConfig();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\ContinuityParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */