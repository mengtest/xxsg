/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ 
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.processors.limit.LimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LuckyMoneyActParameter
/*     */   extends AbstractParameter
/*     */ {
/*  22 */   private Map<Integer, Integer> costDay = new HashMap<>();
/*     */   
/*     */   private int costGoldItem;
/*     */   
/*     */   private int costSilverItem;
/*     */   
/*     */   public LuckyMoneyActParameter() {
/*  29 */     super(61);
/*     */ 
/*     */     
/*  32 */     this.actTimes = new HashMap<>();
/*     */   }
/*     */ 
/*     */   
/*     */   private double perCentAge;
/*     */   private int updateTime;
/*     */   private Map<Integer, FestivalTime> actTimes;
/*     */   
/*     */   public FestivalTime getActTime(int id) {
/*  41 */     return this.actTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  50 */     return this.actTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActLuckyOpen(int actId) {
/*  61 */     MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId, MoneyPotBean.class);
/*  62 */     if (null == moneyPotBean) {
/*  63 */       return false;
/*     */     }
/*  65 */     boolean flag1 = LimitUtil.isValid(moneyPotBean.getServerType(), moneyPotBean.getDay());
/*  66 */     if (!flag1) {
/*  67 */       return false;
/*     */     }
/*  69 */     if (!PlayerUtil.platformActIsOpen(moneyPotBean.getChannelType())) {
/*  70 */       return false;
/*     */     }
/*  72 */     FestivalTime festivalTime = getActTime(moneyPotBean.getId());
/*  73 */     int curTime = TimeUtil.currentTime();
/*  74 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  75 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActSaveLuckyOpen(int actId) {
/*  85 */     MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId, MoneyPotBean.class);
/*  86 */     if (null == moneyPotBean) {
/*  87 */       return false;
/*     */     }
/*  89 */     boolean flag1 = LimitUtil.isValid(moneyPotBean.getServerType(), moneyPotBean.getDay());
/*  90 */     if (!flag1) {
/*  91 */       return false;
/*     */     }
/*  93 */     if (!PlayerUtil.platformActIsOpen(moneyPotBean.getChannelType())) {
/*  94 */       return false;
/*     */     }
/*  96 */     FestivalTime festivalTime = getActTime(moneyPotBean.getId());
/*  97 */     int curTime = TimeUtil.currentTime();
/*  98 */     boolean flag2 = (null != festivalTime && festivalTime.endTime <= curTime && curTime <= festivalTime.closeTime);
/*  99 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLuckySaveAct(int actId) {
/* 109 */     MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId, MoneyPotBean.class);
/* 110 */     if (null == moneyPotBean) {
/* 111 */       return false;
/*     */     }
/* 113 */     boolean flag1 = LimitUtil.isValid(moneyPotBean.getServerType(), moneyPotBean.getDay());
/* 114 */     if (!flag1) {
/* 115 */       return false;
/*     */     }
/* 117 */     if (!PlayerUtil.platformActIsOpen(moneyPotBean.getChannelType())) {
/* 118 */       return false;
/*     */     }
/*     */     
/* 121 */     int oprm = getUpdateTime();
/* 122 */     FestivalTime festivalTime = getActTime(moneyPotBean.getId());
/* 123 */     if (festivalTime.endTime <= oprm) {
/* 124 */       return false;
/*     */     }
/* 126 */     int curTime = TimeUtil.currentTime();
/* 127 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.closeTime);
/* 128 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getLuckySaveAct(boolean isOpen) {
/* 138 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/* 139 */     List<Integer> list = new ArrayList<>();
/* 140 */     actTimes.keySet().forEach(actId -> {
/*     */           if (isOpen) {
/*     */             if (isLuckySaveAct(actId.intValue())) {
/*     */               list.add(actId);
/*     */             }
/*     */           } else if (!isLuckySaveAct(actId.intValue())) {
/*     */             list.add(actId);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 151 */     Collections.sort(list);
/* 152 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 158 */     this.actTimes.clear();
/* 159 */     Map<Integer, Object> map = JsonTableService.getJsonTable(MoneyPotBean.class);
/*     */     
/* 161 */     int openTime = MContext.getOpenTimeInt();
/* 162 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 163 */       MoneyPotBean moneyPotBean = (MoneyPotBean)entry.getValue();
/* 164 */       FestivalTime festivalTime = new FestivalTime();
/* 165 */       festivalTime.actId = moneyPotBean.getId();
/* 166 */       if (moneyPotBean.getServerType() == 0) {
/* 167 */         festivalTime.startTime = TimeUtil.getTimeFromDate(moneyPotBean.getBeginTime());
/* 168 */         festivalTime.endTime = TimeUtil.getTimeFromDate(moneyPotBean.getEndTime());
/* 169 */         festivalTime.closeTime = TimeUtil.getTimeFromDate(moneyPotBean.getFinalTime());
/*     */       } else {
/* 171 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(moneyPotBean.getBeginTime()).intValue() - 1) * 86400;
/* 172 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(moneyPotBean.getEndTime()).intValue() * 86400;
/* 173 */         festivalTime.closeTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(moneyPotBean.getFinalTime()).intValue() * 86400;
/*     */       } 
/* 175 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     this.costDay.clear();
/* 182 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(";");
/* 183 */     for (String string : strings) {
/* 184 */       String[] strings1 = string.split(":");
/* 185 */       this.costDay.put(Integer.valueOf(Integer.parseInt(strings1[0])), Integer.valueOf(Integer.parseInt(strings1[1])));
/*     */     } 
/* 187 */     String str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue();
/* 188 */     this.costGoldItem = Integer.parseInt(str);
/* 189 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue();
/* 190 */     this.costSilverItem = Integer.parseInt(str);
/* 191 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue();
/* 192 */     this.perCentAge = Double.parseDouble(str);
/* 193 */     str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue();
/* 194 */     this.updateTime = TimeUtil.getTimeFromDate(str);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getPerCentAge() {
/* 199 */     return this.perCentAge;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Integer, Integer> getCostDay() {
/* 204 */     return this.costDay;
/*     */   }
/*     */   
/*     */   public int getCostGoldItem() {
/* 208 */     return this.costGoldItem;
/*     */   }
/*     */   
/*     */   public int getCostSilverItem() {
/* 212 */     return this.costSilverItem;
/*     */   }
/*     */   
/*     */   public int getUpdateTime() {
/* 216 */     return this.updateTime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\LuckyMoneyActParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */