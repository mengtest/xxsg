/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LimitActParameter extends AbstractParameter {
/*     */   private static final int TYPE_1 = 1;
/*     */   private static final int TYPE_2 = 2;
/*     */   private static final int TYPE_3 = 3;
/*     */   
/*     */   public LimitActParameter() {
/*  22 */     super(23);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.festivalTimes = new HashMap<>();
/*  32 */     this.targetTypeSet = new HashMap<>();
/*  33 */     this.festivalIds = new HashMap<>();
/*  34 */     this.loginDates = new HashMap<>();
/*  35 */     this.reSendTypes = new HashSet<>();
/*  36 */     this.seriesIds = new HashSet<>();
/*  37 */     this.loginIns = new HashSet<>();
/*  38 */     this.consumeIds = new HashSet<>();
/*     */   }
/*     */   private static final int TYPE_4 = 4; private static final int TYPE_5 = 5; private Map<Integer, FestivalTime> festivalTimes; private Map<Integer, Set<Integer>> targetTypeSet; private Map<Integer, Set<Integer>> festivalIds;
/*     */   private Map<Integer, String> loginDates;
/*     */   private Set<Integer> reSendTypes;
/*     */   private Set<Integer> seriesIds;
/*     */   private Set<Integer> loginIns;
/*     */   private Set<Integer> consumeIds;
/*     */   
/*     */   public FestivalTime getFestivalTime(int id) {
/*  48 */     return this.festivalTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getFestivalTimes() {
/*  57 */     return new HashMap<>(this.festivalTimes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen, boolean close) {
/*  67 */     Map<Integer, FestivalTime> festivalTimeMap = getFestivalTimes();
/*  68 */     List<Integer> list = new ArrayList<>();
/*  69 */     Set<Integer> ids = festivalTimeMap.keySet();
/*  70 */     for (Integer actId : ids) {
/*  71 */       if (isOpen) {
/*  72 */         if (checkActOpen(isOpen, actId.intValue(), close))
/*  73 */           list.add(actId); 
/*     */         continue;
/*     */       } 
/*  76 */       if (!checkActOpen(isOpen, actId.intValue(), close)) {
/*  77 */         list.add(actId);
/*     */       }
/*     */     } 
/*     */     
/*  81 */     Collections.sort(list);
/*  82 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/*  87 */     return getActId(isOpen, false);
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
/*     */   private boolean checkActOpen(boolean open, int actId, boolean close) {
/*  99 */     LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(actId, LimitActivityBean.class);
/* 100 */     if (null == limitActivityBean) {
/* 101 */       return false;
/*     */     }
/* 103 */     int curDay = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/* 104 */     boolean flag1 = isValid(limitActivityBean.getServerType(), curDay, limitActivityBean.getDay());
/* 105 */     if (!flag1) {
/* 106 */       return false;
/*     */     }
/* 108 */     FestivalTime festivalTime = getFestivalTime(limitActivityBean.getId());
/* 109 */     int curTime = TimeUtil.currentTime();
/* 110 */     boolean flag2 = false;
/* 111 */     if (close) {
/* 112 */       flag2 = (null != festivalTime && festivalTime.startTime <= curTime && (curTime <= festivalTime.endTime || curTime < festivalTime.closeTime));
/*     */     } else {
/* 114 */       flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*     */     } 
/* 116 */     if (open && flag2) {
/*     */       
/* 118 */       if (limitActivityBean.getServerType() == 0 && festivalTime.endTime <= MContext.getOpenTimeInt() && MContext.getOpenTimeInt() < festivalTime.closeTime) {
/* 119 */         return false;
/*     */       }
/* 121 */       if (limitActivityBean.getMutex() != 0) {
/* 122 */         int startOpen = TimeUtil.getDayDiffToOpen(MContext.getOpenTimeInt(), festivalTime.startTime);
/* 123 */         if (startOpen <= 0 || (limitActivityBean.getDay() < startOpen && startOpen <= limitActivityBean.getMutex())) {
/* 124 */           if (MContext.isHeFu() && limitActivityBean.getMutex() != 9999 && limitActivityBean.getDay() != 0) {
/* 125 */             return false;
/*     */           }
/* 127 */           return true;
/*     */         } 
/* 129 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 133 */       boolean isCompensate = false;
/* 134 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 135 */       boolean isCompensate2 = false;
/* 136 */       for (IntIntValue act : loginParameter.getCompensateAct()) {
/* 137 */         if (act.key <= actId && actId <= act.value) {
/* 138 */           isCompensate2 = true;
/* 139 */           for (IntIntValue server : loginParameter.getServerList()) {
/* 140 */             if (server.key <= MContext.getServerIdInt() && MContext.getServerIdInt() <= server.value) {
/* 141 */               isCompensate = true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 147 */       if (!isCompensate && isCompensate2) {
/* 148 */         return false;
/*     */       }
/*     */     } 
/* 151 */     return flag2;
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
/* 162 */     List<Integer> list = getActId(true);
/* 163 */     return list.contains(Integer.valueOf(actId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActClose(int actId) {
/* 173 */     List<Integer> list = getActId(true, true);
/* 174 */     return list.contains(Integer.valueOf(actId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Integer> getLimitActivitylist(int targetType) {
/* 184 */     if (this.targetTypeSet.containsKey(Integer.valueOf(targetType))) {
/* 185 */       return new HashSet<>(this.targetTypeSet.get(Integer.valueOf(targetType)));
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Integer> getIds(int itemId) {
/* 197 */     if (this.festivalIds.containsKey(Integer.valueOf(itemId))) {
/* 198 */       return new HashSet<>(this.festivalIds.get(Integer.valueOf(itemId)));
/*     */     }
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public String getLoginDates(int id) {
/* 204 */     return this.loginDates.getOrDefault(Integer.valueOf(id), "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNeedSend(int type) {
/* 214 */     return this.reSendTypes.contains(Integer.valueOf(type));
/*     */   }
/*     */   
/*     */   public synchronized void initType() {
/* 218 */     this.festivalTimes.clear();
/* 219 */     this.seriesIds.clear();
/* 220 */     this.consumeIds.clear();
/* 221 */     this.loginIns.clear();
/* 222 */     Map<Integer, Object> map = JsonTableService.getJsonTable(LimitActivityBean.class);
/*     */     
/* 224 */     int openTime = MContext.getOpenTimeInt();
/* 225 */     int currTime = TimeUtil.currentTime();
/* 226 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 227 */       LimitActivityBean limitActivityBean = (LimitActivityBean)entry.getValue();
/* 228 */       FestivalTime festivalTime = new FestivalTime();
/* 229 */       festivalTime.actId = limitActivityBean.getId();
/* 230 */       if (limitActivityBean.getServerType() == 0) {
/* 231 */         festivalTime.startTime = TimeUtil.getTimeFromDate(limitActivityBean.getBeginTime());
/* 232 */         festivalTime.endTime = TimeUtil.getTimeFromDate(limitActivityBean.getEndTime());
/* 233 */         festivalTime.closeTime = TimeUtil.getTimeFromDate(limitActivityBean.getEndTime()) + limitActivityBean.getDelyDay() * 86400;
/*     */       } else {
/* 235 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.parseInt(limitActivityBean.getBeginTime()) - 1) * 86400;
/* 236 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.parseInt(limitActivityBean.getEndTime()) * 86400;
/* 237 */         festivalTime.closeTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.parseInt(limitActivityBean.getEndTime()) * 86400 + limitActivityBean.getDelyDay() * 86400;
/*     */       } 
/* 239 */       this.festivalTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */     
/* 242 */     this.festivalIds.clear();
/* 243 */     this.loginDates.clear();
/* 244 */     map = JsonTableService.getJsonTable(LimitActivityBean.class);
/*     */     
/* 246 */     for (Object obj : map.values()) {
/* 247 */       LimitActivityBean limitActivityBean = (LimitActivityBean)obj;
/*     */ 
/*     */ 
/*     */       
/* 251 */       if (limitActivityBean.getType() == 3) {
/* 252 */         if (limitActivityBean.getServerType() == 0) {
/* 253 */           if (StatisticsService.getDays() > limitActivityBean.getDay()) {
/* 254 */             String dateStr = limitActivityBean.getBeginTime();
/* 255 */             this.loginDates.put(Integer.valueOf(limitActivityBean.getId()), dateStr);
/*     */           } 
/*     */         } else {
/* 258 */           Long timestamp = Long.valueOf((MContext.getOpenTimeInt() + (Integer.parseInt(limitActivityBean.getBeginTime()) - 1) * 86400) * 1000L);
/* 259 */           String dateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(timestamp.longValue()));
/* 260 */           this.loginDates.put(Integer.valueOf(limitActivityBean.getId()), dateStr);
/*     */         } 
/*     */       }
/*     */       
/* 264 */       if (limitActivityBean.getActivityList() != null && !limitActivityBean.getActivityList().isEmpty()) {
/* 265 */         for (Integer itemId : limitActivityBean.getActivityList()) {
/* 266 */           if (!this.festivalIds.containsKey(itemId)) {
/* 267 */             this.festivalIds.put(itemId, new HashSet<>());
/*     */           }
/* 269 */           ((Set<Integer>)this.festivalIds.get(itemId)).add(Integer.valueOf(limitActivityBean.getId()));
/*     */         } 
/*     */       }
/*     */     } 
/* 273 */     this.targetTypeSet.clear();
/* 274 */     map = JsonTableService.getJsonTable(LimitActivitylistBean.class);
/* 275 */     for (Object obj : map.values()) {
/* 276 */       LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)obj;
/* 277 */       if (limitActivitylistBean.getDisplayType() == 1) {
/*     */         continue;
/*     */       }
/* 280 */       if (!this.targetTypeSet.containsKey(Integer.valueOf(limitActivitylistBean.getTargetType()))) {
/* 281 */         this.targetTypeSet.put(Integer.valueOf(limitActivitylistBean.getTargetType()), new HashSet<>());
/*     */       }
/* 283 */       ((Set<Integer>)this.targetTypeSet.get(Integer.valueOf(limitActivitylistBean.getTargetType()))).add(Integer.valueOf(limitActivitylistBean.getId()));
/* 284 */       if (limitActivitylistBean.getTargetType() == TaskType.Login.getType()) {
/* 285 */         this.loginIns.addAll(this.festivalIds.get(Integer.valueOf(limitActivitylistBean.getId())));
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
/*     */   public Set<Integer> getSeriesIds() {
/* 297 */     return this.seriesIds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Integer> getConsumeIds() {
/* 306 */     return this.consumeIds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Integer> getLoginIns() {
/* 315 */     return this.loginIns;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 321 */     initType();
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
/*     */   public static boolean isValid(int serverType, int curDay, int configDay) {
/* 333 */     if (serverType == 0) {
/* 334 */       if (MContext.isHeFu()) {
/* 335 */         return true;
/*     */       }
/* 337 */       return (curDay >= configDay);
/* 338 */     }  if (serverType == 1) {
/* 339 */       if (!MContext.isHeFu()) {
/* 340 */         return false;
/*     */       }
/* 342 */     } else if (serverType == 2 && 
/* 343 */       MContext.isHeFu()) {
/* 344 */       return false;
/*     */     } 
/*     */     
/* 347 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\LimitActParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */