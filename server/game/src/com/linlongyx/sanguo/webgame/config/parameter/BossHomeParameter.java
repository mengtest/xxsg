/*     */ package com.linlongyx.sanguo.webgame.config.parameter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ParameterBean;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BossHomeParameter extends AbstractParameter {
/*     */   private Map<Integer, List<Integer>> bossMap;
/*     */   private int rankMaxCount;
/*     */   private int rankRestoreCD;
/*     */   private int worldNormalCount;
/*     */   private int worldRestoreCD;
/*     */   private Map<Integer, Reward> worldBuyCost;
/*     */   private Reward worldMaxBuyCost;
/*     */   private int worldMaxBuyTime;
/*     */   private Map<Integer, Reward> rankBuyCost;
/*     */   private Reward rankMaxBuyCost;
/*     */   private int worldRevive;
/*     */   private int worldBattleSec;
/*     */   private Map<Integer, List<Integer>> worldBossRewards;
/*     */   private Map<Integer, Integer> levelInsId;
/*     */   
/*     */   public BossHomeParameter() {
/*  22 */     super(3);
/*     */ 
/*     */     
/*  25 */     this.bossMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.worldBuyCost = new TreeMap<>();
/*  32 */     this.worldMaxBuyCost = new Reward();
/*     */     
/*  34 */     this.rankBuyCost = new TreeMap<>();
/*  35 */     this.rankMaxBuyCost = new Reward();
/*     */ 
/*     */     
/*  38 */     this.worldBossRewards = new HashMap<>();
/*  39 */     this.levelInsId = new TreeMap<>();
/*  40 */     this.SinglebuyPrice = new HashMap<>();
/*  41 */     this.MaxbuyPrice = new Reward();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     this.levelBoss = new HashMap<>();
/*  52 */     this.actTimes = new HashMap<>();
/*  53 */     this.dayBoss = new HashMap<>();
/*     */   } private Map<Integer, Reward> SinglebuyPrice; private Reward MaxbuyPrice; private int maxBuy; private int dailyChallenge; private int alienRewardCount; private int startFresh; private int endFresh; private int belongToTime; private int timesLimit; private int sinleLimit; private int alienBossLimit; private Map<Integer, Set<Integer>> levelBoss; private Map<Integer, FestivalTime> actTimes; private Map<Integer, Integer> dayBoss;
/*     */   private void initBoss() {
/*  56 */     this.bossMap.clear();
/*  57 */     this.levelInsId.clear();
/*  58 */     Map<Integer, Object> map = JsonTableService.getJsonTable(BossHomeBean.class);
/*  59 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  60 */       BossHomeBean bossHomeBean = (BossHomeBean)entry.getValue();
/*  61 */       List<Integer> list = this.bossMap.getOrDefault(Integer.valueOf(bossHomeBean.getType()), new ArrayList<>());
/*  62 */       list.add(Integer.valueOf(bossHomeBean.getInsId()));
/*  63 */       this.bossMap.put(Integer.valueOf(bossHomeBean.getType()), list);
/*  64 */       if (bossHomeBean.getType() == 2) {
/*  65 */         this.levelInsId.put(Integer.valueOf(bossHomeBean.getWorldLevel()), Integer.valueOf(bossHomeBean.getInsId()));
/*     */       }
/*     */     } 
/*  68 */     this.worldBossRewards.clear();
/*  69 */     map = JsonTableService.getJsonTable(BossWorldRewardBean.class);
/*     */ 
/*     */     
/*  72 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  73 */       BossWorldRewardBean bossWorldRewardBean = (BossWorldRewardBean)entry.getValue();
/*  74 */       List<Integer> list = this.worldBossRewards.getOrDefault(Integer.valueOf(bossWorldRewardBean.getType()), new ArrayList<>());
/*  75 */       list.add(Integer.valueOf(bossWorldRewardBean.getId()));
/*  76 */       this.worldBossRewards.put(Integer.valueOf(bossWorldRewardBean.getType()), list);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initYearBeast() {
/*  87 */     Map<Integer, Object> map = JsonTableService.getJsonTable(YearBeastListBean.class);
/*  88 */     this.levelBoss.clear();
/*  89 */     this.dayBoss.clear();
/*  90 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  91 */       YearBeastListBean yearBeastListBean = (YearBeastListBean)entry.getValue();
/*  92 */       int level = yearBeastListBean.getWorldLevel();
/*  93 */       if (!this.levelBoss.containsKey(Integer.valueOf(level))) {
/*  94 */         this.levelBoss.put(Integer.valueOf(level), new HashSet<>());
/*     */       }
/*  96 */       Set<Integer> set = this.levelBoss.get(Integer.valueOf(level));
/*  97 */       set.add(Integer.valueOf(yearBeastListBean.getId()));
/*  98 */       this.levelBoss.put(Integer.valueOf(level), set);
/*  99 */       this.dayBoss.put(Integer.valueOf(yearBeastListBean.getId()), Integer.valueOf(yearBeastListBean.getOpenDay()));
/*     */     } 
/*     */     
/* 102 */     this.actTimes.clear();
/* 103 */     map = JsonTableService.getJsonTable(YearBeastBean.class);
/*     */     
/* 105 */     int openTime = MContext.getOpenTimeInt();
/* 106 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/* 107 */       YearBeastBean yearBeastBean = (YearBeastBean)entry.getValue();
/* 108 */       FestivalTime festivalTime = new FestivalTime();
/* 109 */       festivalTime.actId = yearBeastBean.getId();
/* 110 */       if (yearBeastBean.getServerType() == 0) {
/* 111 */         festivalTime.startTime = TimeUtil.getTimeFromDate(yearBeastBean.getBeginTime());
/* 112 */         festivalTime.endTime = TimeUtil.getTimeFromDate(yearBeastBean.getEndTime());
/*     */       } else {
/* 114 */         festivalTime.startTime = TimeUtil.getZeroTimeStamp(openTime) + (Integer.valueOf(yearBeastBean.getBeginTime()).intValue() - 1) * 86400;
/* 115 */         festivalTime.endTime = TimeUtil.getZeroTimeStamp(openTime) + Integer.valueOf(yearBeastBean.getEndTime()).intValue() * 86400;
/*     */       } 
/* 117 */       this.actTimes.put(Integer.valueOf(festivalTime.actId), festivalTime);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getBossList(int type) {
/* 127 */     return this.bossMap.get(Integer.valueOf(type));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRankMaxCount() {
/* 135 */     return this.rankMaxCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRankRestoreCD() {
/* 143 */     return this.rankRestoreCD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldNormalCount() {
/* 151 */     return this.worldNormalCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldRestoreCD() {
/* 159 */     return this.worldRestoreCD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldMaxBuyTime() {
/* 167 */     return this.worldMaxBuyTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAlienRewardCount() {
/* 172 */     return this.alienRewardCount;
/*     */   }
/*     */   
/*     */   public int getStartFresh() {
/* 176 */     return this.startFresh;
/*     */   }
/*     */   
/*     */   public int getEndFresh() {
/* 180 */     return this.endFresh;
/*     */   }
/*     */   
/*     */   public int getBelongToTime() {
/* 184 */     return this.belongToTime;
/*     */   }
/*     */   
/*     */   public int getTimesLimit() {
/* 188 */     return this.timesLimit;
/*     */   }
/*     */   
/*     */   public int getSinleLimit() {
/* 192 */     return this.sinleLimit;
/*     */   }
/*     */   
/*     */   public int getAlienBossLimit() {
/* 196 */     return this.alienBossLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getWorldBuyCost(int times) {
/* 205 */     int target = -1;
/* 206 */     for (Iterator<Integer> iterator = this.worldBuyCost.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 207 */       if (key >= times) {
/* 208 */         target = key; break;
/*     */       }  }
/*     */     
/* 211 */     return (target == -1) ? this.worldMaxBuyCost : this.worldBuyCost.get(Integer.valueOf(target));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getRankBuyCost(int times) {
/* 220 */     int target = -1;
/* 221 */     for (Iterator<Integer> iterator = this.rankBuyCost.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 222 */       if (key >= times) {
/* 223 */         target = key; break;
/*     */       }  }
/*     */     
/* 226 */     return (target == -1) ? this.rankMaxBuyCost : this.rankBuyCost.get(Integer.valueOf(target));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Reward getSingleBossBuyCost(int times) {
/* 235 */     if (this.SinglebuyPrice.containsKey(Integer.valueOf(times))) {
/* 236 */       return this.SinglebuyPrice.get(Integer.valueOf(times));
/*     */     }
/* 238 */     return this.MaxbuyPrice;
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
/*     */   
/*     */   public FestivalTime getActTime(int id) {
/* 252 */     return this.actTimes.get(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, FestivalTime> getActTimes() {
/* 261 */     return this.actTimes;
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
/* 272 */     YearBeastBean yearBeastBean = (YearBeastBean)JsonTableService.getJsonData(actId, YearBeastBean.class);
/* 273 */     if (null == yearBeastBean) {
/* 274 */       return false;
/*     */     }
/* 276 */     if (!PlayerUtil.platformActIsOpen(yearBeastBean.getChannelType())) {
/* 277 */       return false;
/*     */     }
/* 279 */     boolean flag1 = LimitUtil.isValid(yearBeastBean.getServerType(), yearBeastBean.getDay());
/* 280 */     if (!flag1) {
/* 281 */       return false;
/*     */     }
/* 283 */     FestivalTime festivalTime = getActTime(yearBeastBean.getId());
/* 284 */     int curTime = TimeUtil.currentTime();
/* 285 */     boolean flag2 = (null != festivalTime && festivalTime.startTime <= curTime && curTime <= festivalTime.endTime);
/* 286 */     return flag2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getActId(boolean isOpen) {
/* 296 */     Map<Integer, FestivalTime> actTimes = getActTimes();
/* 297 */     List<Integer> list = new ArrayList<>();
/* 298 */     actTimes.keySet().forEach(actId -> {
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
/* 309 */     Collections.sort(list);
/* 310 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldRevive() {
/* 318 */     return this.worldRevive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldBattleSec() {
/* 326 */     return this.worldBattleSec;
/*     */   }
/*     */   
/*     */   public Map<Integer, Reward> getSinglebuyPrice() {
/* 330 */     return this.SinglebuyPrice;
/*     */   }
/*     */   
/*     */   public Reward getMaxbuyPrice() {
/* 334 */     return this.MaxbuyPrice;
/*     */   }
/*     */   
/*     */   public int getMaxBuy() {
/* 338 */     return this.maxBuy;
/*     */   }
/*     */   
/*     */   public int getDailyChallenge() {
/* 342 */     return this.dailyChallenge;
/*     */   }
/*     */   
/*     */   public Map<Integer, Set<Integer>> getLevelBoss() {
/* 346 */     return this.levelBoss;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getDayBoss() {
/* 350 */     return this.dayBoss;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Integer> getWorldBossRewardByType(int type) {
/* 359 */     return this.worldBossRewards.get(Integer.valueOf(type));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWorldBossInsId() {
/* 367 */     int worldLevel = RankingLevel.getWorldLevel();
/* 368 */     int insId = 0;
/* 369 */     if (worldLevel < 10) {
/* 370 */       worldLevel = 10;
/*     */     }
/* 372 */     for (Map.Entry<Integer, Integer> entry : this.levelInsId.entrySet()) {
/* 373 */       if (worldLevel >= ((Integer)entry.getKey()).intValue()) {
/* 374 */         insId = ((Integer)entry.getValue()).intValue();
/*     */       }
/*     */     } 
/* 377 */     return insId;
/*     */   }
/*     */ 
/*     */   
/*     */   void init(ParameterBean bean) {
/* 382 */     this.rankMaxCount = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(1))).getValue()).intValue();
/* 383 */     this.rankRestoreCD = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(2))).getValue()).intValue();
/* 384 */     this.worldNormalCount = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(3))).getValue()).intValue();
/* 385 */     this.worldRestoreCD = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(4))).getValue()).intValue();
/* 386 */     this.worldBuyCost.clear();
/* 387 */     this.worldMaxBuyCost = handleReward(bean, this.worldBuyCost, 5);
/* 388 */     this.worldMaxBuyTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(6))).getValue()).intValue();
/* 389 */     this.worldRevive = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(7))).getValue()).intValue();
/* 390 */     this.worldBattleSec = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(8))).getValue()).intValue();
/* 391 */     this.rankBuyCost.clear();
/* 392 */     this.rankMaxBuyCost = handleReward(bean, this.rankBuyCost, 9);
/*     */     
/* 394 */     this.SinglebuyPrice.clear();
/* 395 */     this.MaxbuyPrice = handleReward(bean, this.SinglebuyPrice, 10);
/* 396 */     this.maxBuy = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(11))).getValue()).intValue();
/* 397 */     this.dailyChallenge = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(12))).getValue()).intValue();
/*     */     
/* 399 */     this.alienRewardCount = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(13))).getValue()).intValue();
/* 400 */     this.startFresh = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(14))).getValue()).intValue();
/* 401 */     this.endFresh = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(15))).getValue()).intValue();
/* 402 */     this.belongToTime = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(16))).getValue()).intValue();
/* 403 */     this.timesLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(17))).getValue()).intValue();
/* 404 */     this.sinleLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(18))).getValue()).intValue();
/* 405 */     this.alienBossLimit = Integer.valueOf(((ParameterBean.SecBean)bean.getSec().get(Integer.valueOf(19))).getValue()).intValue();
/*     */     
/* 407 */     initBoss();
/* 408 */     initYearBeast();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\config\parameter\BossHomeParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */