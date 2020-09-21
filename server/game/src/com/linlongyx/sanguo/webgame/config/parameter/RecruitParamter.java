/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedlistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RecruitParamter extends AbstractParameter {
/*     */   protected static final int TYPE_1 = 1;
/*     */   protected static final int TYPE_2 = 2;
/*     */   protected static final int TYPE_3 = 3;
/*     */   protected static final int TYPE_4 = 4;
/*     */   protected static final int TYPE_5 = 5;
/*     */   protected static final int TYPE_6 = 6;
/*     */   protected static final int TYPE_7 = 7;
/*     */   protected static final int TYPE_8 = 8;
/*     */   protected static final int TYPE_9 = 9;
/*     */   protected static final int TYPE_10 = 10;
/*     */   protected static final int TYPE_11 = 11;
/*     */   protected static final int TYPE_12 = 12;
/*     */   protected static final int TYPE_13 = 13;
/*     */   private Reward RecruitTenPrice;
/*     */   private Reward RecruitOnePrice;
/*     */   private int FreeRecruitTime;
/*     */   private int RecruitItemId;
/*     */   private int freeRecruitCount;
/*     */   private Reward yingOnceRecruit;
/*     */   
/*     */   public RecruitParamter() {
/*  38 */     super(15);
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
/*  50 */     this.refreshCost = new HashMap<>();
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
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.festivalTimes = new HashMap<>();
/*  69 */     this.rebateTimes = new HashMap<>();
/*     */   }
/*     */   private Reward ccyOnceRecruit;
/*     */   private int redOneCost;
/*     */   private int redTenCost;
/*     */   private Map<Integer, Integer> refreshCost;
/*     */   private int addScore;
/*     */   
/*     */   public int getCost(int num) {
/*  78 */     int count = 1;
/*  79 */     if (null != this.refreshCost.get(Integer.valueOf(num))) {
/*  80 */       return ((Integer)this.refreshCost.get(Integer.valueOf(num))).intValue();
/*     */     }
/*  82 */     int key2 = 0;
/*  83 */     for (Iterator<Integer> iterator = this.refreshCost.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/*  84 */       if (num >= key && key > key2) {
/*  85 */         count = key;
/*  86 */         key2 = key;
/*     */       }  }
/*     */ 
/*     */     
/*  90 */     return ((Integer)this.refreshCost.get(Integer.valueOf(count))).intValue();
/*     */   }
/*     */   private int tenRecruitItem; private int oneRecruitItem; private int recruitPoint; private int resetNum; private int recruitRedPoint; private int normalTenPoint; private int redTenPoint; private int normalRebateOne; private int normalRebateTen;
/*     */   private int redRebateOne;
/*     */   private int redRebateten;
/*     */   private int goldOneCost;
/*     */   private int goldTenCost;
/*     */   private Map<Integer, FestivalTime> festivalTimes;
/*     */   private Map<Integer, FestivalTime> rebateTimes;
/*     */   
/*     */   public FestivalTime getFestivalTime(int id) {
/* 101 */     return this.festivalTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActOpen(int actId, boolean isToday, int type) {
/* 111 */     RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(actId, RecruitRedlistBean.class);
/* 112 */     if (null == recruitRedlistBean)
/* 113 */       return false; 
/* 114 */     int reType = recruitRedlistBean.getType();
/* 115 */     if (reType != type) {
/* 116 */       return false;
/*     */     }
/* 118 */     return isActOpen(recruitRedlistBean, isToday);
/*     */   }
/*     */   
/*     */   public boolean isActOpen(RecruitRedlistBean recruitRedlistBean, boolean isToday) {
/* 122 */     boolean flag2 = false;
/* 123 */     if (recruitRedlistBean.getServerType() == 0) {
/* 124 */       FestivalTime festivalTime = getFestivalTime(recruitRedlistBean.getId());
/* 125 */       int curTime = TimeUtil.currentTime();
/* 126 */       if (isToday) {
/* 127 */         flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/*     */       } else {
/* 129 */         flag2 = (null != festivalTime && festivalTime.startTime <= curTime + 86400 && curTime + 86400 <= festivalTime.endTime);
/*     */       } 
/*     */     } else {
/* 132 */       int curDay = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/* 133 */       boolean flag1 = RecruitUtil.isValid(recruitRedlistBean.getServerType());
/* 134 */       if (!flag1) {
/* 135 */         return false;
/*     */       }
/* 137 */       if (isToday) {
/* 138 */         if (Integer.parseInt(recruitRedlistBean.getBeginTime()) <= curDay && curDay <= Integer.parseInt(recruitRedlistBean.getEndTime())) {
/* 139 */           return true;
/*     */         }
/*     */       }
/* 142 */       else if (Integer.parseInt(recruitRedlistBean.getBeginTime()) <= curDay + 1 && curDay + 1 <= Integer.parseInt(recruitRedlistBean.getEndTime())) {
/* 143 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 148 */     return flag2;
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
/*     */   
/*     */   public ArrayList<Integer> getActId(boolean isOpen, boolean isToday, int type) {
/* 161 */     Map<Integer, FestivalTime> festivalTimeMap = getFestivalTimes();
/* 162 */     ArrayList<Integer> list = new ArrayList<>();
/* 163 */     festivalTimeMap.keySet().forEach(actId -> {
/*     */           if (isOpen) {
/*     */             if (isActOpen(actId.intValue(), isToday, type)) {
/*     */               list.add(actId);
/*     */             }
/*     */           } else if (!isActOpen(actId.intValue(), isToday, type)) {
/*     */             list.add(actId);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 174 */     Collections.sort(list);
/* 175 */     return list;
/*     */   }
/*     */   
/*     */   public synchronized void initType() {
/* 179 */     this.festivalTimes.clear();
/* 180 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RecruitRedlistBean.class);
/*     */     
/* 182 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 183 */       RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)entry.getValue();
/* 184 */       FestivalTime festivalTime = new FestivalTime();
/* 185 */       festivalTime.actId = recruitRedlistBean.getId();
/* 186 */       if (recruitRedlistBean.getServerType() == 0) {
/* 187 */         festivalTime.startTime = TimeUtil.getTimeFromDate(recruitRedlistBean.getBeginTime());
/* 188 */         festivalTime.endTime = TimeUtil.getTimeFromDate(recruitRedlistBean.getEndTime());
/*     */       } else {
/* 190 */         festivalTime.startTime = MContext.getOpenTimeInt() + (Integer.parseInt(recruitRedlistBean.getBeginTime()) - 1) * 86400;
/* 191 */         festivalTime.endTime = MContext.getOpenTimeInt() + Integer.parseInt(recruitRedlistBean.getEndTime()) * 86400;
/*     */       } 
/* 193 */       this.festivalTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */     
/* 196 */     Map<Integer, Object> rebateMap = JsonTableService.getJsonTable(RecruitRewardBean.class);
/* 197 */     for (Map.Entry<Integer, Object> entry : rebateMap.entrySet()) {
/* 198 */       RecruitRewardBean recruitRewardBean = (RecruitRewardBean)entry.getValue();
/* 199 */       FestivalTime festivalTime = new FestivalTime();
/* 200 */       festivalTime.actId = recruitRewardBean.getId();
/* 201 */       if (recruitRewardBean.getServerType() == 0) {
/* 202 */         festivalTime.startTime = TimeUtil.getTimeFromDate(recruitRewardBean.getBeginTime());
/* 203 */         festivalTime.endTime = TimeUtil.getTimeFromDate(recruitRewardBean.getEndTime());
/*     */       } else {
/* 205 */         festivalTime.startTime = MContext.getOpenTimeInt() + (Integer.parseInt(recruitRewardBean.getBeginTime()) - 1) * 86400;
/* 206 */         festivalTime.endTime = MContext.getOpenTimeInt() + Integer.parseInt(recruitRewardBean.getEndTime()) * 86400;
/*     */       } 
/* 208 */       this.rebateTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRebateActOpen(int actId) {
/* 219 */     RecruitRewardBean recruitRewardBean = (RecruitRewardBean)JsonTableService.getJsonData(actId, RecruitRewardBean.class);
/* 220 */     FestivalTime festivalTime = this.rebateTimes.get(Integer.valueOf(actId));
/* 221 */     boolean flag = LimitUtil.isValid(recruitRewardBean.getServerType(), recruitRewardBean.getDay());
/* 222 */     if (!flag) {
/* 223 */       return false;
/*     */     }
/* 225 */     boolean flag2 = (festivalTime != null && festivalTime.startTime < TimeUtil.currentTime() && festivalTime.endTime > TimeUtil.currentTime());
/* 226 */     return (flag && flag2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/* 236 */     Map<Integer, FestivalTime> festivalTimeMap = getRebateTimes();
/* 237 */     List<Integer> list = new ArrayList<>();
/* 238 */     Set<Integer> ids = festivalTimeMap.keySet();
/* 239 */     for (Integer actId : ids) {
/* 240 */       boolean open = isRebateActOpen(actId.intValue());
/* 241 */       if (isOpen) {
/* 242 */         if (open)
/* 243 */           list.add(actId); 
/*     */         continue;
/*     */       } 
/* 246 */       if (!open) {
/* 247 */         list.add(actId);
/*     */       }
/*     */     } 
/*     */     
/* 251 */     Collections.sort(list);
/* 252 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecruitPoint() {
/* 261 */     return this.recruitPoint;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 267 */     String[] string = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue().split(",");
/* 268 */     this.RecruitTenPrice = new Reward();
/* 269 */     tranform(this.RecruitTenPrice, string);
/* 270 */     String[] strings = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue().split(",");
/* 271 */     this.RecruitOnePrice = new Reward();
/* 272 */     tranform(this.RecruitOnePrice, strings);
/*     */     
/* 274 */     this.FreeRecruitTime = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue());
/* 275 */     this.RecruitItemId = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue());
/* 276 */     this.freeRecruitCount = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(5))).getValue());
/*     */     
/* 278 */     String[] string2 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue().split(",");
/* 279 */     this.yingOnceRecruit = new Reward();
/* 280 */     tranform(this.yingOnceRecruit, string2);
/*     */     
/* 282 */     String[] string3 = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue().split(",");
/* 283 */     this.ccyOnceRecruit = new Reward();
/* 284 */     tranform(this.ccyOnceRecruit, string3);
/*     */     
/* 286 */     this.redOneCost = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue());
/* 287 */     this.redTenCost = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(9))).getValue());
/*     */     
/* 289 */     this.refreshCost.clear();
/* 290 */     String[] stringcost = ((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(10))).getValue().split(";");
/* 291 */     for (String stringc : stringcost) {
/* 292 */       String[] strings2 = stringc.split(":");
/* 293 */       this.refreshCost.put(Integer.valueOf(Integer.parseInt(strings2[0])), Integer.valueOf(Integer.parseInt(strings2[1])));
/*     */     } 
/*     */     
/* 296 */     this.addScore = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue());
/*     */     
/* 298 */     this.tenRecruitItem = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(12))).getValue());
/* 299 */     this.oneRecruitItem = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(13))).getValue());
/* 300 */     this.recruitPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(14))).getValue());
/* 301 */     this.resetNum = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(15))).getValue());
/*     */     
/* 303 */     this.recruitRedPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(16))).getValue());
/* 304 */     this.normalTenPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(17))).getValue());
/* 305 */     this.redTenPoint = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(18))).getValue());
/*     */     
/* 307 */     this.normalRebateOne = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(20))).getValue());
/* 308 */     this.normalRebateTen = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(21))).getValue());
/* 309 */     this.redRebateOne = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(22))).getValue());
/* 310 */     this.redRebateten = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(23))).getValue());
/* 311 */     this.goldOneCost = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(24))).getValue());
/* 312 */     this.goldTenCost = Integer.parseInt(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(25))).getValue());
/*     */     
/* 314 */     initType();
/*     */   }
/*     */   
/*     */   public Reward getRecruitTenPrice() {
/* 318 */     return this.RecruitTenPrice;
/*     */   }
/*     */   
/*     */   public Reward getRecruitOnePrice() {
/* 322 */     return this.RecruitOnePrice;
/*     */   }
/*     */   
/*     */   public int getFreeRecruitTime() {
/* 326 */     return this.FreeRecruitTime;
/*     */   }
/*     */   
/*     */   public int getRecruitItemId() {
/* 330 */     return this.RecruitItemId;
/*     */   }
/*     */   
/*     */   public int getFreeRecruitCount() {
/* 334 */     return this.freeRecruitCount;
/*     */   }
/*     */   
/*     */   public Reward getYingOnceRecruit() {
/* 338 */     return this.yingOnceRecruit;
/*     */   }
/*     */   
/*     */   public Reward getCcyOnceRecruit() {
/* 342 */     return this.ccyOnceRecruit;
/*     */   }
/*     */   
/*     */   public int getRedOneCost() {
/* 346 */     return this.redOneCost;
/*     */   }
/*     */   
/*     */   public int getRedTenCost() {
/* 350 */     return this.redTenCost;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getRefreshCost() {
/* 354 */     return this.refreshCost;
/*     */   }
/*     */   
/*     */   public int getAddScore() {
/* 358 */     return this.addScore;
/*     */   }
/*     */   
/*     */   public Map<Integer, FestivalTime> getFestivalTimes() {
/* 362 */     return this.festivalTimes;
/*     */   }
/*     */   
/*     */   public int getTenRecruitItem() {
/* 366 */     return this.tenRecruitItem;
/*     */   }
/*     */   
/*     */   public int getOneRecruitItem() {
/* 370 */     return this.oneRecruitItem;
/*     */   }
/*     */   
/*     */   public int getResetNum() {
/* 374 */     return this.resetNum;
/*     */   }
/*     */   
/*     */   public int getRecruitRedPoint() {
/* 378 */     return this.recruitRedPoint;
/*     */   }
/*     */   
/*     */   public int getNormalTenPoint() {
/* 382 */     return this.normalTenPoint;
/*     */   }
/*     */   
/*     */   public int getRedTenPoint() {
/* 386 */     return this.redTenPoint;
/*     */   }
/*     */   
/*     */   public static int getType1() {
/* 390 */     return 1;
/*     */   }
/*     */   
/*     */   public int getNormalRebateOne() {
/* 394 */     return this.normalRebateOne;
/*     */   }
/*     */   
/*     */   public int getNormalRebateTen() {
/* 398 */     return this.normalRebateTen;
/*     */   }
/*     */   
/*     */   public int getRedRebateOne() {
/* 402 */     return this.redRebateOne;
/*     */   }
/*     */   
/*     */   public int getRedRebateten() {
/* 406 */     return this.redRebateten;
/*     */   }
/*     */   
/*     */   public Map<Integer, FestivalTime> getRebateTimes() {
/* 410 */     return this.rebateTimes;
/*     */   }
/*     */   
/*     */   public int getGoldOneCost() {
/* 414 */     return this.goldOneCost;
/*     */   }
/*     */   
/*     */   public int getGoldTenCost() {
/* 418 */     return this.goldTenCost;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\RecruitParamter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */