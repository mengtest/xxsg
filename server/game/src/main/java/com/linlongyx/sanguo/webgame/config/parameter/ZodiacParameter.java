/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ZodiacBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ZodiacParameter extends AbstractParameter {
/*     */   private Map<Integer, FestivalTime> actTimes;
/*     */   
/*     */   public ZodiacParameter() {
/*  17 */     super(73);
/*     */     
/*  19 */     this.actTimes = new HashMap<>();
/*     */   }
/*     */   private int itemId;
/*     */   public int getItemId() {
/*  23 */     return this.itemId;
/*     */   }
/*     */ 
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
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  41 */     return this.actTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isZodiacAct(int actId) {
/*  50 */     ZodiacBean zodiacBean = (ZodiacBean)JsonTableService.getJsonData(actId, ZodiacBean.class);
/*  51 */     if (null == zodiacBean) {
/*  52 */       return false;
/*     */     }
/*  54 */     boolean flag1 = LimitUtil.isValid(zodiacBean.getServerType(), zodiacBean.getDay());
/*  55 */     if (!flag1) {
/*  56 */       return false;
/*     */     }
/*     */     
/*  59 */     FestivalTime festivalTime = getActTime(zodiacBean.getId());
/*  60 */     int curTime = TimeUtil.currentTime();
/*  61 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  62 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getZodiacAct(boolean isOpen) {
/*  71 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/*  72 */     List<Integer> list = new ArrayList<>();
/*  73 */     actTimes.keySet().forEach(actId -> {
/*     */           if (isOpen) {
/*     */             if (isZodiacAct(actId.intValue())) {
/*     */               list.add(actId);
/*     */             }
/*     */           } else if (!isZodiacAct(actId.intValue())) {
/*     */             list.add(actId);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/*  84 */     Collections.sort(list);
/*  85 */     return list;
/*     */   }
/*     */   
/*     */   void init(ParameterBean bean) {
/*  89 */     this.actTimes.clear();
/*  90 */     Map<Integer, Object> map = JsonTableService.getJsonTable(ZodiacBean.class);
/*     */     
/*  92 */     int openTime = MContext.getOpenTimeInt();
/*  93 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  94 */       ZodiacBean zodiacBean = (ZodiacBean)entry.getValue();
/*  95 */       FestivalTime festivalTime = new FestivalTime();
/*  96 */       festivalTime.actId = zodiacBean.getId();
/*  97 */       if (zodiacBean.getServerType() == 0) {
/*  98 */         festivalTime.startTime = TimeUtil.getTimeFromDate(zodiacBean.getBeginTime());
/*  99 */         festivalTime.endTime = TimeUtil.getTimeFromDate(zodiacBean.getEndTime());
/*     */       } else {
/* 101 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(zodiacBean.getBeginTime()).intValue() - 1) * 86400;
/* 102 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(zodiacBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 104 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     String str = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue();
/* 111 */     this.itemId = Integer.parseInt(str);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\ZodiacParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */