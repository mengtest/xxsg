/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RechargeTurntableBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RechargeTurntablerewardBean;
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
/*     */ public class ChargeRebateParameter
/*     */   extends AbstractParameter {
/*     */   public ChargeRebateParameter() {
/*  20 */     super(49);
/*     */ 
/*     */     
/*  23 */     this.actTimes = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  28 */     this.raffleWeightMap = new HashMap<>();
/*     */   }
/*     */   private Map<Integer, FestivalTime> actTimes; private int oneCost;
/*     */   private int serviceRecord;
/*     */   private int personalRecord;
/*     */   private HashMap<Integer, Integer> raffleWeightMap;
/*     */   private int addScore;
/*     */   private int oneTimes;
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
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  47 */     return this.actTimes;
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
/*  58 */     RechargeTurntableBean rechargeTurntableBean = (RechargeTurntableBean)JsonTableService.getJsonData(actId, RechargeTurntableBean.class);
/*  59 */     if (null == rechargeTurntableBean) {
/*  60 */       return false;
/*     */     }
/*  62 */     boolean flag1 = LimitUtil.isValid(rechargeTurntableBean.getServerType(), rechargeTurntableBean.getDay());
/*  63 */     if (!flag1) {
/*  64 */       return false;
/*     */     }
/*  66 */     FestivalTime festivalTime = getActTime(rechargeTurntableBean.getActId());
/*  67 */     int curTime = TimeUtil.currentTime();
/*  68 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  69 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/*  79 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/*  80 */     List<Integer> list = new ArrayList<>();
/*  81 */     actTimes.keySet().forEach(actId -> {
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
/*  92 */     Collections.sort(list);
/*  93 */     return list;
/*     */   }
/*     */   
/*     */   private void initWeight() {
/*  97 */     this.raffleWeightMap.clear();
/*  98 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RechargeTurntablerewardBean.class);
/*  99 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 100 */       int key = ((Integer)entry.getKey()).intValue();
/* 101 */       int weight = ((Integer)this.raffleWeightMap.getOrDefault(Integer.valueOf(key), Integer.valueOf(0))).intValue();
/* 102 */       RechargeTurntablerewardBean rechargeTurntablerewardBean = (RechargeTurntablerewardBean)entry.getValue();
/* 103 */       for (RechargeTurntablerewardBean.RewardIdBean rewardIdBean : rechargeTurntablerewardBean.getRewardId().values()) {
/* 104 */         weight += rewardIdBean.getProbability();
/*     */       }
/* 106 */       this.raffleWeightMap.put(Integer.valueOf(key), Integer.valueOf(weight));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 112 */     this.actTimes.clear();
/* 113 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RechargeTurntableBean.class);
/*     */     
/* 115 */     int openTime = MContext.getOpenTimeInt();
/* 116 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 117 */       RechargeTurntableBean rechargeTurntableBean = (RechargeTurntableBean)entry.getValue();
/* 118 */       FestivalTime festivalTime = new FestivalTime();
/* 119 */       festivalTime.actId = rechargeTurntableBean.getActId();
/* 120 */       if (rechargeTurntableBean.getServerType() == 0) {
/* 121 */         festivalTime.startTime = TimeUtil.getTimeFromDate(rechargeTurntableBean.getBeginTime());
/* 122 */         festivalTime.endTime = TimeUtil.getTimeFromDate(rechargeTurntableBean.getEndTime());
/*     */       } else {
/* 124 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(rechargeTurntableBean.getBeginTime()).intValue() - 1) * 86400;
/* 125 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(rechargeTurntableBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 127 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */     
/* 130 */     this.oneCost = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 131 */     this.serviceRecord = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 132 */     this.personalRecord = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 133 */     this.addScore = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/*     */     
/* 135 */     this.oneTimes = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue()).intValue();
/* 136 */     initWeight();
/*     */   }
/*     */   
/*     */   public int getOneCost() {
/* 140 */     return this.oneCost;
/*     */   }
/*     */   
/*     */   public int getServiceRecord() {
/* 144 */     return this.serviceRecord;
/*     */   }
/*     */   
/*     */   public int getPersonalRecord() {
/* 148 */     return this.personalRecord;
/*     */   }
/*     */   
/*     */   public HashMap<Integer, Integer> getRaffleWeightMap() {
/* 152 */     return this.raffleWeightMap;
/*     */   }
/*     */   
/*     */   public int getAddScore() {
/* 156 */     return this.addScore;
/*     */   }
/*     */   
/*     */   public int getOneTimes() {
/* 160 */     return this.oneTimes;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\ChargeRebateParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */