/*     */ package com.linlongyx.sanguo.webgame.processors.recruit;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitLibBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedTenthBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitRedlistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecruitParamter;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecruitUtil
/*     */ {
/*     */   private static final int RECRUIT_TEN_TIMES = 10;
/*     */   
/*     */   public static RecruitBean.TimesBean getLibBean(int level, int totalTimes) {
/*  46 */     Map<Integer, Object> recruitBean = JsonTableService.getJsonTable(RecruitBean.class);
/*  47 */     RecruitBean libBean = null;
/*     */     
/*  49 */     ArrayList<Integer> levelList = new ArrayList<>();
/*  50 */     boolean isHit = false;
/*  51 */     int index = -1;
/*  52 */     int timesKey = totalTimes + 1;
/*  53 */     boolean isTimesHit = false;
/*  54 */     for (Iterator<Integer> iterator = recruitBean.keySet().iterator(); iterator.hasNext(); ) { int lvlKey = ((Integer)iterator.next()).intValue();
/*  55 */       libBean = (RecruitBean)recruitBean.get(Integer.valueOf(lvlKey));
/*  56 */       levelList.add(Integer.valueOf(lvlKey));
/*  57 */       index++;
/*  58 */       if (level >= lvlKey) {
/*  59 */         for (Iterator<Integer> iterator1 = libBean.getTimes().keySet().iterator(); iterator1.hasNext(); ) { int i = ((Integer)iterator1.next()).intValue();
/*  60 */           if (timesKey <= i) {
/*  61 */             isTimesHit = true;
/*  62 */             timesKey = i;
/*     */             break;
/*     */           }  }
/*     */         
/*  66 */         if (!isTimesHit) {
/*     */           continue;
/*     */         }
/*  69 */         isHit = true;
/*     */         break;
/*     */       }  }
/*     */     
/*  73 */     if (!isHit) {
/*  74 */       LogUtils.errorLog(new Object[] { "RecruitUtil::rewards", "not hit level", Integer.valueOf(level), Integer.valueOf(index), Integer.valueOf(totalTimes) });
/*  75 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     if (!isTimesHit) {
/*  86 */       if (index == 0) {
/*  87 */         LogUtils.errorLog(new Object[] { "RecruitUtil::rewards", "not hit level", Integer.valueOf(level), Integer.valueOf(index), Integer.valueOf(totalTimes) });
/*  88 */         return null;
/*     */       } 
/*  90 */       int level2 = ((Integer)levelList.get(index - 1)).intValue();
/*  91 */       getLibBean(level2, totalTimes);
/*     */     } 
/*     */     
/*  94 */     return (RecruitBean.TimesBean)libBean.getTimes().get(Integer.valueOf(timesKey));
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
/*     */   public static RecruitLibBean.IdBean recruitReward(RecruitBean.TimesBean timesBean, int times, int type, boolean suly) {
/* 106 */     if (type == 0) {
/* 107 */       int i = timesBean.getLibId();
/* 108 */       if (suly)
/* 109 */         i = timesBean.getSulyLib(); 
/* 110 */       if (times == 10) {
/* 111 */         i = timesBean.getLibId();
/* 112 */         if (suly) {
/* 113 */           i = timesBean.getSulyLib();
/*     */         }
/*     */       } 
/* 116 */       RecruitLibBean recruitLibBean1 = (RecruitLibBean)JsonTableService.getJsonData(i, RecruitLibBean.class);
/* 117 */       int j = 0;
/* 118 */       ArrayList<RecruitLibBean.IdBean> arrayList = new ArrayList<>();
/* 119 */       for (RecruitLibBean.IdBean tmp : recruitLibBean1.getId().values()) {
/* 120 */         arrayList.add(tmp);
/* 121 */         j += tmp.getProb();
/*     */       } 
/* 123 */       int k = RandUtil.randNum(0, j);
/* 124 */       j = 0;
/* 125 */       Collections.shuffle(arrayList);
/* 126 */       RecruitLibBean.IdBean idBean = null;
/* 127 */       for (RecruitLibBean.IdBean tmp : arrayList) {
/* 128 */         j += tmp.getProb();
/* 129 */         if (j >= k) {
/* 130 */           idBean = tmp;
/*     */           break;
/*     */         } 
/*     */       } 
/* 134 */       return idBean;
/*     */     } 
/*     */     
/* 137 */     int libId = timesBean.getYuanbaoLib();
/* 138 */     if (suly)
/* 139 */       libId = timesBean.getTenSulyLib(); 
/* 140 */     if (times == 10) {
/* 141 */       libId = timesBean.getYuanbaoLib();
/* 142 */       if (suly) {
/* 143 */         libId = timesBean.getTenSulyLib();
/*     */       }
/*     */     } 
/* 146 */     RecruitLibBean recruitLibBean = (RecruitLibBean)JsonTableService.getJsonData(libId, RecruitLibBean.class);
/* 147 */     int total = 0;
/* 148 */     ArrayList<RecruitLibBean.IdBean> tmpArray = new ArrayList<>();
/* 149 */     for (RecruitLibBean.IdBean tmp : recruitLibBean.getId().values()) {
/* 150 */       tmpArray.add(tmp);
/* 151 */       total += tmp.getProb();
/*     */     } 
/* 153 */     int rand = RandUtil.randNum(0, total);
/* 154 */     total = 0;
/* 155 */     Collections.shuffle(tmpArray);
/* 156 */     RecruitLibBean.IdBean bean = null;
/* 157 */     for (RecruitLibBean.IdBean tmp : tmpArray) {
/* 158 */       total += tmp.getProb();
/* 159 */       if (total >= rand) {
/* 160 */         bean = tmp;
/*     */         break;
/*     */       } 
/*     */     } 
/* 164 */     return bean;
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
/*     */   
/*     */   public static short checkOneRaffle(IPlayerSession playerSession, RecruitComponent recruitComponent, RecruitParamter paramter, BagComponent bagComponent, boolean isCCyTen) {
/* 179 */     if (isCCyTen) {
/*     */       
/* 181 */       if (recruitComponent.getTenCCYRecruit() == 0) {
/* 182 */         return 1;
/*     */       }
/* 184 */       if (bagComponent.check(paramter.getTenRecruitItem(), 1L)) {
/* 185 */         return 4;
/*     */       }
/*     */       
/* 188 */       short result = CostUtil.check(paramter.getRecruitTenPrice(), playerSession, bagComponent);
/* 189 */       if (result != 0) {
/* 190 */         return 2;
/*     */       }
/*     */     } else {
/*     */       
/* 194 */       int freeTime = recruitComponent.getNextFreeTime();
/*     */       
/* 196 */       if (freeTime == 0 || freeTime < TimeUtil.currentTime()) {
/* 197 */         return 1;
/*     */       }
/* 199 */       if (bagComponent.check(paramter.getOneRecruitItem(), 1L)) {
/* 200 */         return 4;
/*     */       }
/*     */       
/* 203 */       short result = CostUtil.check(paramter.getRecruitOnePrice(), playerSession, bagComponent);
/* 204 */       if (result != 0) {
/* 205 */         return 2;
/*     */       }
/*     */     } 
/* 208 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> recruitReardList(IPlayer player, int ccyTimes, RecruitBean.TimesBean timesBean, int type) {
/* 218 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 219 */     ArrayList<Reward> rewardList = new ArrayList<>();
/* 220 */     RecruitComponent recruitComponent = (RecruitComponent)player.createIfNotExist(RecruitComponent.class);
/* 221 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 222 */     for (int i = 0; i < 10; i++) {
/* 223 */       boolean suly = false;
/* 224 */       if (++ccyTimes == 10) {
/* 225 */         suly = true;
/* 226 */         ccyTimes = 0;
/*     */       } 
/* 228 */       int level = playerComponent.getLevel();
/* 229 */       int totalTimes = recruitComponent.getTotalRecruitTimes();
/* 230 */       timesBean = getLibBean(level, totalTimes);
/* 231 */       RecruitLibBean.IdBean idBean = recruitReward(timesBean, ccyTimes, type, suly);
/* 232 */       List<RewardBean> oneReward = idBean.getGoods();
/* 233 */       if (null == oneReward || oneReward.isEmpty()) {
/* 234 */         LogUtil.errorLog(new Object[] { "RecruitUtil::raffleLottery reward error", Long.valueOf(player.getPlayerId()), Boolean.valueOf(suly), Integer.valueOf(type) });
/*     */       } else {
/*     */         
/* 237 */         rewardList = FinanceUtil.rewardGet(FinanceUtil.transform(oneReward), player, ResourceEvent.Recruit, true);
/* 238 */         rewards.addAll(rewardList);
/* 239 */         if (idBean.getIsShow() != 0);
/*     */ 
/*     */         
/* 242 */         recruitComponent.setTotalRecruitTimes(recruitComponent.getTotalRecruitTimes() + 1);
/*     */       } 
/* 244 */     }  return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Reward> firstRecruitList(IPlayer player, int ccyTimes, RecruitBean.TimesBean timesBean, int type) {
/* 254 */     ArrayList<Reward> rewards = new ArrayList<>();
/* 255 */     RecruitComponent recruitComponent = (RecruitComponent)player.createIfNotExist(RecruitComponent.class);
/* 256 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 257 */     for (int i = 0; i < 10; i++) {
/* 258 */       boolean suly = false;
/* 259 */       if (++ccyTimes == 10) {
/* 260 */         suly = true;
/* 261 */         ccyTimes = 0;
/*     */       } 
/* 263 */       int level = playerComponent.getLevel();
/* 264 */       int totalTimes = recruitComponent.getTotalRecruitTimes();
/* 265 */       timesBean = getLibBean(level, totalTimes);
/* 266 */       RecruitLibBean.IdBean idBean = recruitReward(timesBean, ccyTimes, type, suly);
/* 267 */       List<RewardBean> oneReward = idBean.getGoods();
/* 268 */       if (null == oneReward || oneReward.isEmpty()) {
/* 269 */         LogUtil.errorLog(new Object[] { "RecruitUtil::raffleLottery reward error", Long.valueOf(player.getPlayerId()), Boolean.valueOf(suly), Integer.valueOf(type) });
/*     */       } else {
/*     */         
/* 272 */         ArrayList<Reward> rewardList = new ArrayList<>();
/* 273 */         rewardList = FinanceUtil.transform(oneReward);
/* 274 */         rewards.addAll(rewardList);
/*     */       } 
/* 276 */     }  return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendRecruitNotice(FighterBean fighterBean, IPlayer player) {
/* 286 */     if (null == fighterBean) {
/*     */       return;
/*     */     }
/* 289 */     int langId = -1;
/* 290 */     if (fighterBean.getQuality() == 50) {
/* 291 */       langId = 201;
/* 292 */     } else if (fighterBean.getQuality() == 60) {
/* 293 */       langId = 202;
/* 294 */     } else if (fighterBean.getQuality() == 70) {
/* 295 */       langId = 203;
/* 296 */     } else if (fighterBean.getQuality() == 80) {
/* 297 */       langId = 204;
/*     */     } 
/* 299 */     if (-1 != langId) {
/* 300 */       ArrayList<String> list = new ArrayList<>();
/* 301 */       list.add(player.getPlayerName());
/* 302 */       list.add(fighterBean.getName());
/* 303 */       LookUpService.radiate(langId, list);
/*     */     } 
/* 305 */     TaskComponent taskComponent = (TaskComponent)LookUpService.getComponent(player.getPlayerId(), TaskComponent.class);
/* 306 */     if (null != taskComponent) {
/* 307 */       taskComponent.refreshSchedule(TaskType.QualityFighter, fighterBean.getQuality(), 0L);
/* 308 */       taskComponent.refreshSchedule(TaskType.StarFighter, 0, 0L);
/* 309 */       taskComponent.refreshSchedule(TaskType.BreakFighter, 0, 0L);
/*     */     } 
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
/*     */   public static ArrayList<RecruitRedBean.IdBean> recruitRedReward(IPlayer player, RecruitComponent recruitComponent, int times, int type) {
/* 322 */     ArrayList<RecruitRedBean.IdBean> rewards = new ArrayList<>();
/* 323 */     int num = 0;
/* 324 */     if (times == 0) {
/* 325 */       num = 1;
/*     */     } else {
/* 327 */       num = 10;
/*     */     } 
/*     */     
/* 330 */     for (int i = 0; i < num; i++) {
/*     */       
/* 332 */       int total = 0;
/* 333 */       int fighter = 1;
/* 334 */       if (type == 1) {
/* 335 */         fighter = RandUtil.randomList(recruitComponent.getToday());
/*     */       } else {
/* 337 */         fighter = RandUtil.randomList(recruitComponent.getGoldToday());
/*     */       } 
/* 339 */       RecruitRedBean recruitRedBean = (RecruitRedBean)JsonTableService.getJsonData(fighter, RecruitRedBean.class);
/* 340 */       if (recruitRedBean == null) {
/* 341 */         LogUtil.errorLog(new Object[] { "RecruitUtil::raffleLottery recruitRed reward error", Long.valueOf(player.getPlayerId()), Integer.valueOf(times) });
/* 342 */         return rewards;
/*     */       } 
/* 344 */       for (Iterator<Integer> iterator1 = recruitRedBean.getId().keySet().iterator(); iterator1.hasNext(); ) { int id = ((Integer)iterator1.next()).intValue();
/* 345 */         total += ((RecruitRedBean.IdBean)recruitRedBean.getId().get(Integer.valueOf(id))).getProb(); }
/*     */ 
/*     */       
/* 348 */       int rand = RandUtil.randNum(0, total);
/* 349 */       total = 0;
/* 350 */       for (Iterator<Integer> iterator2 = recruitRedBean.getId().keySet().iterator(); iterator2.hasNext(); ) { int id = ((Integer)iterator2.next()).intValue();
/* 351 */         RecruitRedBean.IdBean idBean = (RecruitRedBean.IdBean)recruitRedBean.getId().get(Integer.valueOf(id));
/* 352 */         total += idBean.getProb();
/* 353 */         if (total >= rand) {
/* 354 */           rewards.add(idBean);
/*     */           break;
/*     */         }  }
/*     */     
/*     */     } 
/* 359 */     return rewards;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isValid(int serverType) {
/* 369 */     if (serverType == 1) {
/* 370 */       if (!MContext.isHeFu()) {
/* 371 */         return false;
/*     */       }
/* 373 */     } else if (serverType == 2 && 
/* 374 */       MContext.isHeFu()) {
/* 375 */       return false;
/*     */     } 
/*     */     
/* 378 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<RewardBean> recruitRedTen(int fighter) {
/* 388 */     RecruitRedTenthBean recruitRedTenthBean = (RecruitRedTenthBean)JsonTableService.getJsonData(fighter, RecruitRedTenthBean.class);
/* 389 */     if (null == recruitRedTenthBean) {
/* 390 */       LogUtil.errorLog(new Object[] { "RecruitUtil::raffleLottery recruitRedTen reward error", Integer.valueOf(fighter) });
/* 391 */       return new ArrayList<>();
/*     */     } 
/* 393 */     int total = 0;
/* 394 */     ArrayList<RecruitRedTenthBean.IdBean> tmpArray = new ArrayList<>();
/* 395 */     for (RecruitRedTenthBean.IdBean tmp : recruitRedTenthBean.getId().values()) {
/* 396 */       tmpArray.add(tmp);
/* 397 */       total += tmp.getProb();
/*     */     } 
/* 399 */     int rand = RandUtil.randNum(0, total);
/* 400 */     total = 0;
/* 401 */     Collections.shuffle(tmpArray);
/* 402 */     RecruitRedTenthBean.IdBean bean = null;
/* 403 */     for (RecruitRedTenthBean.IdBean tmp : tmpArray) {
/* 404 */       total += tmp.getProb();
/* 405 */       if (total >= rand) {
/* 406 */         bean = tmp;
/*     */         break;
/*     */       } 
/*     */     } 
/* 410 */     return (bean == null) ? new ArrayList<>() : bean.getTenthGoods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<Integer> getRecruitLib(RecruitParamter recruitParamter, boolean isToday, int type) {
/* 421 */     ArrayList<Integer> arrayList = new ArrayList<>();
/* 422 */     ArrayList<Integer> arrayList2 = recruitParamter.getActId(true, isToday, type);
/* 423 */     int actId = 0;
/* 424 */     if (arrayList2.isEmpty()) {
/* 425 */       LogUtil.errorLog(new Object[] { "RecruitUtil::raffleLottery refresh redRecruit error", Integer.valueOf(type) });
/* 426 */       if (type == 1) {
/* 427 */         actId = 2;
/* 428 */       } else if (type == 2) {
/* 429 */         actId = 5;
/*     */       } 
/*     */     } else {
/* 432 */       actId = ((Integer)arrayList2.get(0)).intValue();
/*     */     } 
/* 434 */     RecruitRedlistBean recruitRedlistBean = (RecruitRedlistBean)JsonTableService.getJsonData(actId, RecruitRedlistBean.class);
/* 435 */     if (recruitRedlistBean == null) {
/* 436 */       LogUtil.errorLog(new Object[] { "RecruitUtil::raffleLottery refresh2 redRecruit2 error", Integer.valueOf(actId) });
/* 437 */       return arrayList;
/*     */     } 
/*     */     
/* 440 */     return recruitRedlistBean.getActivityList();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\recruit\RecruitUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */