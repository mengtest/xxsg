/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SpecialExchargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SpecialOfferListBean;
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
/*     */ public class LimitBuyActParameter
/*     */   extends AbstractParameter {
/*     */   private int exchangeItem;
/*     */   private Map<Integer, FestivalTime> actTimes;
/*     */   
/*     */   public LimitBuyActParameter() {
/*  24 */     super(68);
/*     */ 
/*     */     
/*  27 */     this.actTimes = new HashMap<>();
/*  28 */     this.actTimesExchange = new HashMap<>();
/*  29 */     this.sameActMap = new HashMap<>();
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<Integer, FestivalTime> actTimesExchange;
/*     */   
/*     */   private Map<Integer, Map<Integer, Integer>> sameActMap;
/*     */   
/*     */   public FestivalTime getActTime(int id) {
/*  38 */     return this.actTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FestivalTime getActTimeExchange(int id) {
/*  48 */     return this.actTimesExchange.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  57 */     return this.actTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimesExchange() {
/*  66 */     return this.actTimesExchange;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLimitExchangeAct(int actId) {
/*  77 */     SpecialExchargeBean specialExchargeBean = (SpecialExchargeBean)JsonTableService.getJsonData(actId, SpecialExchargeBean.class);
/*  78 */     if (null == specialExchargeBean) {
/*  79 */       return false;
/*     */     }
/*  81 */     boolean flag1 = LimitUtil.isValid(specialExchargeBean.getServerType(), specialExchargeBean.getDay());
/*  82 */     if (!flag1) {
/*  83 */       return false;
/*     */     }
/*     */     
/*  86 */     FestivalTime festivalTime = getActTimeExchange(specialExchargeBean.getId());
/*  87 */     int curTime = TimeUtil.currentTime();
/*  88 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  89 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLimitBuyAct(int actId) {
/*  99 */     SpecialOfferBean specialOfferBean = (SpecialOfferBean)JsonTableService.getJsonData(actId, SpecialOfferBean.class);
/* 100 */     if (null == specialOfferBean) {
/* 101 */       return false;
/*     */     }
/* 103 */     boolean flag1 = LimitUtil.isValid(specialOfferBean.getServerType(), specialOfferBean.getDay());
/* 104 */     if (!flag1) {
/* 105 */       return false;
/*     */     }
/*     */     
/* 108 */     FestivalTime festivalTime = getActTime(specialOfferBean.getId());
/* 109 */     int curTime = TimeUtil.currentTime();
/* 110 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/* 111 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getLimitBuyAct(boolean isOpen) {
/* 121 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/* 122 */     List<Integer> list = new ArrayList<>();
/* 123 */     actTimes.keySet().forEach(actId -> {
/*     */           if (isOpen) {
/*     */             if (isLimitBuyAct(actId.intValue())) {
/*     */               list.add(actId);
/*     */             }
/*     */           } else if (!isLimitBuyAct(actId.intValue())) {
/*     */             list.add(actId);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 134 */     Collections.sort(list);
/* 135 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getLimitExchangeAct(boolean isOpen) {
/* 145 */     Map<Integer, FestivalTime> actTimes = getActTimesExchange();
/* 146 */     List<Integer> list = new ArrayList<>();
/* 147 */     actTimes.keySet().forEach(actId -> {
/*     */           if (isOpen) {
/*     */             if (isLimitExchangeAct(actId.intValue())) {
/*     */               list.add(actId);
/*     */             }
/*     */           } else if (!isLimitExchangeAct(actId.intValue())) {
/*     */             list.add(actId);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 158 */     Collections.sort(list);
/* 159 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 164 */     this.actTimes.clear();
/* 165 */     this.sameActMap.clear();
/*     */     
/* 167 */     Map<Integer, Object> mapsameActMap = JsonTableService.getJsonTable(SpecialOfferBean.class);
/* 168 */     for (Map.Entry<Integer, Object> entry : mapsameActMap.entrySet()) {
/* 169 */       SpecialOfferBean specialOfferBean = (SpecialOfferBean)entry.getValue();
/* 170 */       this.sameActMap.putIfAbsent(Integer.valueOf(specialOfferBean.getId()), new HashMap<>());
/* 171 */       for (Integer specialOfferListId : specialOfferBean.getActivityList()) {
/* 172 */         SpecialOfferListBean specialOfferListBean = (SpecialOfferListBean)JsonTableService.getJsonData(specialOfferListId.intValue(), SpecialOfferListBean.class);
/* 173 */         ((Map<Integer, Integer>)this.sameActMap.get(Integer.valueOf(specialOfferBean.getId()))).putIfAbsent(Integer.valueOf(specialOfferListBean.getChargeId()), Integer.valueOf(specialOfferListBean.getId()));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 178 */     Map<Integer, Object> map = JsonTableService.getJsonTable(SpecialOfferBean.class);
/*     */     
/* 180 */     int openTime = MContext.getOpenTimeInt();
/* 181 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 182 */       SpecialOfferBean specialOfferBean = (SpecialOfferBean)entry.getValue();
/* 183 */       FestivalTime festivalTime = new FestivalTime();
/* 184 */       festivalTime.actId = specialOfferBean.getId();
/* 185 */       if (specialOfferBean.getServerType() == 0) {
/* 186 */         festivalTime.startTime = TimeUtil.getTimeFromDate(specialOfferBean.getBeginTime());
/* 187 */         festivalTime.endTime = TimeUtil.getTimeFromDate(specialOfferBean.getEndTime());
/*     */       } else {
/* 189 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(specialOfferBean.getBeginTime()).intValue() - 1) * 86400;
/* 190 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(specialOfferBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 192 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */     
/* 195 */     this.actTimesExchange.clear();
/* 196 */     Map<Integer, Object> map1 = JsonTableService.getJsonTable(SpecialExchargeBean.class);
/*     */     
/* 198 */     for (Map.Entry<Integer, Object> entry : map1.entrySet()) {
/* 199 */       SpecialExchargeBean specialExchargeBean = (SpecialExchargeBean)entry.getValue();
/* 200 */       FestivalTime festivalTime1 = new FestivalTime();
/* 201 */       festivalTime1.actId = specialExchargeBean.getId();
/* 202 */       if (specialExchargeBean.getServerType() == 0) {
/* 203 */         festivalTime1.startTime = TimeUtil.getTimeFromDate(specialExchargeBean.getBeginTime());
/* 204 */         festivalTime1.endTime = TimeUtil.getTimeFromDate(specialExchargeBean.getEndTime());
/*     */       } else {
/* 206 */         festivalTime1.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(specialExchargeBean.getBeginTime()).intValue() - 1) * 86400;
/* 207 */         festivalTime1.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(specialExchargeBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 209 */       this.actTimesExchange.put(Integer.valueOf(festivalTime1.actId), festivalTime1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     String str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue();
/* 216 */     this.exchangeItem = Integer.parseInt(str);
/*     */   }
/*     */   
/*     */   public int getExchangeItem() {
/* 220 */     return this.exchangeItem;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Integer, Integer>> getSameActMap() {
/* 224 */     return this.sameActMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\LimitBuyActParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */