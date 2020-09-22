/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawMinBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DrawWarehouseBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DrawCardParameter extends AbstractParameter {
/*     */   public DrawCardParameter() {
/*  18 */     super(48);
/*     */ 
/*     */     
/*  21 */     this.actTimes = new HashMap<>();
/*  22 */     this.idHouseMap = new HashMap<>();
/*  23 */     this.houseProps = new HashMap<>();
/*  24 */     this.maxRounds = new HashMap<>();
/*  25 */     this.allNumSet = new HashMap<>();
/*     */   }
/*     */   private Map<Integer, FestivalTime> actTimes; private Map<Integer, Integer> idHouseMap;
/*     */   private Map<Integer, Integer> houseProps;
/*     */   private Map<Integer, Integer> maxRounds;
/*     */   private Map<Integer, ArrayList<Integer>> allNumSet;
/*     */   private int oneCost;
/*     */   private int freeTime;
/*     */   
/*     */   public FestivalTime getActTime(int id) {
/*  35 */     return this.actTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/*  43 */     return this.actTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOpenCost(int num) {
/*  52 */     return this.oneCost * num;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActOpen(int actId) {
/*  60 */     DrawBean drawBean = (DrawBean)JsonTableService.getJsonData(actId, DrawBean.class);
/*  61 */     if (null == drawBean) {
/*  62 */       return false;
/*     */     }
/*  64 */     boolean flag1 = LimitUtil.isValid(drawBean.getServerType(), drawBean.getDay());
/*  65 */     if (!flag1) {
/*  66 */       return false;
/*     */     }
/*  68 */     FestivalTime festivalTime = getActTime(drawBean.getId());
/*  69 */     int curTime = TimeUtil.currentTime();
/*  70 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*  71 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActEndTime(int actId) {
/*  80 */     int currentTime = TimeUtil.currentTime();
/*  81 */     DrawBean drawBean = (DrawBean)JsonTableService.getJsonData(actId, DrawBean.class);
/*  82 */     if (null == drawBean) {
/*  83 */       return currentTime;
/*     */     }
/*  85 */     DrawCardParameter drawCardParameter = (DrawCardParameter)ParameterConstant.getParameter(48);
/*  86 */     FestivalTime festivalTime = drawCardParameter.getActTime(actId);
/*  87 */     if (null == festivalTime) {
/*  88 */       return currentTime;
/*     */     }
/*  90 */     return festivalTime.endTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/*  99 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/* 100 */     List<Integer> list = new ArrayList<>();
/* 101 */     actTimes.keySet().forEach(actId -> {
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
/* 112 */     Collections.sort(list);
/* 113 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWarehouseId(int id) {
/* 122 */     return ((Integer)this.idHouseMap.get(Integer.valueOf(id))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHouseProp(int warehouseId) {
/* 131 */     return ((Integer)this.houseProps.get(Integer.valueOf(warehouseId))).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Integer> getAllNumSet(int allPlay, int id) {
/* 139 */     Set<Integer> set = new HashSet<>();
/* 140 */     for (Integer num : this.allNumSet.get(Integer.valueOf(id))) {
/* 141 */       if (allPlay >= num.intValue()) {
/* 142 */         set.add(num);
/*     */       }
/*     */     } 
/* 145 */     return set;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 151 */     this.actTimes.clear();
/* 152 */     Map<Integer, Object> map = JsonTableService.getJsonTable(DrawBean.class);
/*     */     
/* 154 */     int openTime = MContext.getOpenTimeInt();
/* 155 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 156 */       DrawBean drawBean = (DrawBean)entry.getValue();
/* 157 */       FestivalTime festivalTime = new FestivalTime();
/* 158 */       festivalTime.actId = drawBean.getId();
/* 159 */       if (drawBean.getServerType() == 0) {
/* 160 */         festivalTime.startTime = TimeUtil.getTimeFromDate(drawBean.getBeginTime());
/* 161 */         festivalTime.endTime = TimeUtil.getTimeFromDate(drawBean.getEndTime());
/*     */       } else {
/* 163 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(drawBean.getBeginTime()).intValue() - 1) * 86400;
/* 164 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(drawBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 166 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */     
/* 169 */     this.idHouseMap.clear();
/* 170 */     this.houseProps.clear();
/* 171 */     map = JsonTableService.getJsonTable(DrawWarehouseBean.class);
/* 172 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 173 */       DrawWarehouseBean drawWarehouseBean = (DrawWarehouseBean)entry.getValue();
/* 174 */       Map<Integer, DrawWarehouseBean.IdBean> id = drawWarehouseBean.getId();
/* 175 */       for (Map.Entry<Integer, DrawWarehouseBean.IdBean> entry1 : id.entrySet()) {
/* 176 */         DrawWarehouseBean.IdBean idBean = entry1.getValue();
/* 177 */         this.idHouseMap.put(entry1.getKey(), Integer.valueOf(drawWarehouseBean.getWarehouseId()));
/* 178 */         this.houseProps.put(Integer.valueOf(drawWarehouseBean.getWarehouseId()), Integer.valueOf(((Integer)this.houseProps.getOrDefault(Integer.valueOf(drawWarehouseBean.getWarehouseId()), Integer.valueOf(0))).intValue() + idBean.getRafflePer()));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     this.allNumSet.clear();
/* 196 */     map = JsonTableService.getJsonTable(DrawMinBean.class);
/* 197 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 198 */       DrawMinBean drawMinBean = (DrawMinBean)entry.getValue();
/* 199 */       if (!this.allNumSet.containsKey(Integer.valueOf(drawMinBean.getId()))) {
/* 200 */         this.allNumSet.put(Integer.valueOf(drawMinBean.getId()), new ArrayList<>(drawMinBean.getNum().keySet()));
/*     */       }
/*     */     } 
/*     */     
/* 204 */     this.oneCost = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 205 */     this.freeTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/*     */   }
/*     */   
/*     */   public int getOneCost() {
/* 209 */     return this.oneCost;
/*     */   }
/*     */   
/*     */   public int getFreeTime() {
/* 213 */     return this.freeTime;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\DrawCardParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */