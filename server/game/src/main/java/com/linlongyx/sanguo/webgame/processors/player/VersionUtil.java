/*     */ package com.linlongyx.sanguo.webgame.processors.player;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.continuity.ContinuityEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.divine.DivineComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.divine.DivineEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.limit.LimitEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.luckymoney.LuckyMoneyComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ContinFillinglistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivityBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.LimitActivitylistBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MoneyPotBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ContinuityParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DivineParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LimitActParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LuckyMoneyActParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerBattleParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.continuity.ContinueUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankAct;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FestivalTime;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class VersionUtil {
/*     */   public static int getServerIdByPlayerId(long playerId) {
/*  66 */     return (int)(playerId / 1000000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getCrossPlayerName(long playerId, String playerName) {
/*     */     int zone;
/*  76 */     long serverId = playerId / 1000000L;
/*  77 */     int channelNum = (int)(serverId / 10000L);
/*  78 */     String namePrefix = "";
/*  79 */     switch (channelNum) {
/*     */       case 1:
/*  81 */         zone = (int)(serverId % 10000L);
/*  82 */         if (zone < 70) {
/*  83 */           namePrefix = "微信" + zone + "区"; break;
/*     */         } 
/*  85 */         namePrefix = "微信" + (zone - 1) + "区";
/*     */         break;
/*     */       
/*     */       case 3:
/*  89 */         namePrefix = "三国" + (serverId % 10000L) + "区";
/*     */         break;
/*     */       case 4:
/*  92 */         namePrefix = "玩吧" + (serverId % 10000L) + "区";
/*     */         break;
/*     */       case 5:
/*  95 */         namePrefix = "双线" + (serverId % 10000L) + "区";
/*     */         break;
/*     */       case 6:
/*  98 */         namePrefix = "百度" + (serverId % 10000L) + "区";
/*     */         break;
/*     */       case 7:
/* 101 */         namePrefix = "霸业" + (serverId % 10000L) + "区";
/*     */         break;
/*     */       case 8:
/* 104 */         namePrefix = "群雄" + (serverId % 10000L) + "区";
/*     */         break;
/*     */     } 
/*     */     
/* 108 */     return namePrefix.isEmpty() ? playerName : (namePrefix + "." + playerName);
/*     */   }
/*     */   
/*     */   public static String getCrossBlocName(long playerId, String blocName) {
/* 112 */     return getCrossPlayerName(playerId, blocName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int version2Int(String str) {
/* 122 */     return (int)(Double.parseDouble(str) * 100000.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurVersion() {
/* 131 */     return version2Int(MContext.getVersion());
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
/*     */   public static void checkAndRepair(IPlayer iPlayer) {
/* 143 */     repair11180(iPlayer);
/*     */     
/* 145 */     repair11190(iPlayer);
/*     */     
/* 147 */     repair11200(iPlayer);
/*     */     
/* 149 */     repair11220(iPlayer);
/*     */     
/* 151 */     repair11230(iPlayer);
/*     */     
/* 153 */     repair11240(iPlayer);
/*     */     
/* 155 */     repair11330(iPlayer);
/*     */     
/* 157 */     repair11350(iPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repair11180(IPlayer iPlayer) {
/* 163 */     RecruitComponent recruitComponent = (RecruitComponent)iPlayer.createIfNotExist(RecruitComponent.class);
/* 164 */     if (recruitComponent.getTenCCYRecruit() == 0 && recruitComponent.getTotalRecruitTimes() > 0) {
/* 165 */       recruitComponent.setTenCCYRecruit(1);
/*     */     }
/* 167 */     TaskComponent taskComponent = (TaskComponent)iPlayer.getComponent(TaskComponent.class);
/* 168 */     if (null == taskComponent || taskComponent.getId() <= 1)
/*     */       return; 
/* 170 */     if (taskComponent.getFixedStatus() <= 0) {
/* 171 */       taskComponent.setFixedStatus(1);
/*     */       
/* 173 */       int id = taskComponent.getId();
/* 174 */       if (id < 6910) {
/*     */         return;
/*     */       }
/* 177 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 178 */       String title = LanguageConstant.getLanguage(105);
/* 179 */       String content = LanguageConstant.getLanguage(106);
/* 180 */       MailUtil.sendSysMail(iPlayer.getPlayerId(), loginParameter.getMountsFixedReward(), title, content);
/* 181 */       LogUtil.errorLog(new Object[] { "repair11180", Long.valueOf(iPlayer.getPlayerId()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repair11190(IPlayer iPlayer) {
/* 188 */     TaskComponent taskComponent = (TaskComponent)iPlayer.getComponent(TaskComponent.class);
/* 189 */     if (null == taskComponent) {
/*     */       return;
/*     */     }
/* 192 */     if (taskComponent.getFixedStatus() < 2) {
/* 193 */       taskComponent.setFixedStatus(2);
/* 194 */       LimitComponent limitComponent = (LimitComponent)iPlayer.createIfNotExist(LimitComponent.class);
/* 195 */       ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 196 */       LimitUtil.updateChargeCCY(limitComponent, extendComponent.getTotalChargeCCB(), true);
/* 197 */       LogUtil.errorLog(new Object[] { "repair11190", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(extendComponent.getTotalChargeCCB()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repair11200(IPlayer iPlayer) {
/* 204 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 205 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
/* 206 */     if (taskComponent.getFixedStatus() < 3) {
/* 207 */       taskComponent.setFixedStatus(3);
/* 208 */       if (playerComponent.getVip() >= 7) {
/* 209 */         playerComponent.setVipReward(new ArrayList());
/*     */       }
/* 211 */       LogUtil.errorLog(new Object[] { "repair11200", Long.valueOf(iPlayer.getPlayerId()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void repair11220(IPlayer iPlayer) {
/* 217 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repair11230(IPlayer iPlayer) {
/* 258 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
/* 259 */     if (taskComponent.getFixedStatus() < 5) {
/* 260 */       taskComponent.setFixedStatus(5);
/*     */       
/* 262 */       PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 263 */       int quality = playerComponent.getQuality();
/* 264 */       int starId = 0;
/* 265 */       int newQuality = 0;
/* 266 */       SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)LookUpService.getComponent(iPlayer.getPlayerId(), SanGuoZhiComponent.class);
/*     */       
/* 268 */       for (IMapEntity iMapEntity : sanGuoZhiComponent.getEntityMap().values()) {
/* 269 */         SanGuoZhiEntity sanGuoZhiEntity = (SanGuoZhiEntity)iMapEntity;
/* 270 */         if (!sanGuoZhiEntity.isActivity()) {
/* 271 */           starId = sanGuoZhiEntity.getRecordStarId();
/*     */         }
/*     */       } 
/* 274 */       if (starId > 5) {
/* 275 */         newQuality = 50;
/* 276 */       } else if (starId > 33) {
/* 277 */         newQuality = 60;
/* 278 */       } else if (starId > 101) {
/* 279 */         newQuality = 70;
/*     */       } 
/* 281 */       if (quality < newQuality) {
/* 282 */         playerComponent.setQuality(newQuality);
/*     */       }
/* 284 */       PlayerUtil.updateKeyValueInfo(iPlayer.getSession(), KeyValueConstant.QUILITY.getKey(), playerComponent.getQuality());
/* 285 */       LogUtil.errorLog(new Object[] { "repair11230", Long.valueOf(iPlayer.getPlayerId()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void repair11240(IPlayer iPlayer) {
/* 291 */     if (TimeUtil.getCurrentYearMonthDay() != 20190311) {
/*     */       return;
/*     */     }
/*     */     
/* 295 */     LuckyMoneyActParameter luckyMoneyActParameter = (LuckyMoneyActParameter)ParameterConstant.getParameter(61);
/* 296 */     List<Integer> list = luckyMoneyActParameter.getLuckySaveAct(true);
/* 297 */     Map<Integer, FestivalTime> festivalTimes = luckyMoneyActParameter.getActTimes();
/* 298 */     BagComponent bagComponent = (BagComponent)iPlayer.createIfNotExist(BagComponent.class);
/* 299 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 300 */     LuckyMoneyComponent luckyMoneyComponent = (LuckyMoneyComponent)iPlayer.createIfNotExist(LuckyMoneyComponent.class);
/*     */     
/* 302 */     double rechardTotal = extendComponent.getTodayRecharge();
/* 303 */     double costTotal = extendComponent.getDailyConsume();
/* 304 */     for (Integer actId : list) {
/* 305 */       if (festivalTimes.containsKey(actId) && 
/* 306 */         luckyMoneyActParameter.isActLuckyOpen(actId.intValue())) {
/*     */         
/* 308 */         MoneyPotBean moneyPotBean = (MoneyPotBean)JsonTableService.getJsonData(actId.intValue(), MoneyPotBean.class);
/* 309 */         double d = ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(0)).getNum();
/* 310 */         double temCostGold = bagComponent.getItemNum(luckyMoneyActParameter.getCostGoldItem()) + luckyMoneyComponent.getEntity(actId.intValue()).getGoldMoney() * d;
/* 311 */         if (rechardTotal > temCostGold) {
/* 312 */           double cost = rechardTotal - temCostGold;
/* 313 */           long todayRecharge = (long)cost;
/* 314 */           double num = todayRecharge / d;
/* 315 */           bagComponent.addItem(luckyMoneyActParameter.getCostGoldItem(), (int)num, ResourceEvent.LuckyMoney, true);
/*     */         } 
/*     */ 
/*     */         
/* 319 */         double numSilver = ((MoneyPotBean.CostRewardBean)moneyPotBean.getCostReward().get(1)).getNum();
/* 320 */         double temCostSilver = bagComponent.getItemNum(luckyMoneyActParameter.getCostSilverItem()) + luckyMoneyComponent.getEntity(actId.intValue()).getSilverMoney() * numSilver;
/* 321 */         if (costTotal > temCostSilver) {
/* 322 */           double value = costTotal - temCostSilver;
/* 323 */           double num2 = value / numSilver;
/* 324 */           bagComponent.addItem(luckyMoneyActParameter.getCostSilverItem(), (int)num2, ResourceEvent.LuckyMoney, true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void repair11330(IPlayer iPlayer) {
/* 335 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
/* 336 */     if (taskComponent.getFixedStatus() < 15) {
/* 337 */       taskComponent.setFixedStatus(15);
/*     */       
/* 339 */       ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 340 */       long todayC = extendComponent.getTodayRecharge();
/*     */       
/* 342 */       if (todayC >= 600L) {
/* 343 */         ArrayList<Reward> rewards = new ArrayList<>();
/* 344 */         Reward reward = new Reward();
/* 345 */         reward.type = 1;
/* 346 */         reward.id = CurrencyType.BloodCrystal.getType();
/*     */         
/* 348 */         if (todayC >= 600L && todayC < 3000L) {
/* 349 */           reward.num = 10L;
/*     */         }
/* 351 */         if (todayC >= 3000L && todayC < 12800L) {
/* 352 */           reward.num = 60L;
/*     */         }
/* 354 */         if (todayC >= 12800L && todayC < 28800L) {
/* 355 */           reward.num = 160L;
/*     */         }
/* 357 */         if (todayC >= 28800L && todayC < 64800L) {
/* 358 */           reward.num = 360L;
/*     */         }
/* 360 */         if (todayC >= 64800L && todayC < 288800L) {
/* 361 */           reward.num = 860L;
/*     */         }
/* 363 */         if (todayC >= 288800L) {
/* 364 */           reward.num = 1860L;
/*     */         }
/* 366 */         rewards.add(reward);
/* 367 */         String title = "豪华签到奖励";
/* 368 */         String content = "版本更新完毕，新增血晶商店，为您补上今日的豪华签到额外新增的血晶如下";
/* 369 */         MailUtil.sendSysMail(iPlayer.getPlayerId(), rewards, title, content);
/*     */       } 
/* 371 */       LogUtil.errorLog(new Object[] { "repair11330", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(todayC) });
/*     */     } 
/*     */     
/* 374 */     if (taskComponent.getFixedStatus() < 22) {
/* 375 */       taskComponent.setFixedStatus(22);
/*     */       
/* 377 */       ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 378 */       long todayC = extendComponent.getTodayRecharge();
/* 379 */       long todayConsume = extendComponent.getDailyConsume();
/*     */       
/* 381 */       ContinuityParameter continuityParameter = (ContinuityParameter)ParameterConstant.getParameter(21);
/* 382 */       ArrayList<Integer> list = continuityParameter.getActId(true);
/* 383 */       LogUtil.errorLog(new Object[] { "repair11335", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(todayC) });
/* 384 */       if (null == list || list.isEmpty()) {
/*     */         return;
/*     */       }
/* 387 */       ContinuityComponent continuityComponent = (ContinuityComponent)LookUpService.getComponent(iPlayer.getPlayerId(), ContinuityComponent.class);
/* 388 */       if (null == continuityComponent) {
/*     */         return;
/*     */       }
/* 391 */       LogUtil.errorLog(new Object[] { "repair113307", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(todayC) });
/* 392 */       for (Integer id : list) {
/* 393 */         ContinFillingBean continFillingBean = (ContinFillingBean)JsonTableService.getJsonData(id.intValue(), ContinFillingBean.class);
/* 394 */         if (null == continFillingBean) {
/*     */           continue;
/*     */         }
/* 397 */         FestivalTime festivalTime = continuityParameter.getFestivalTime(id.intValue());
/* 398 */         if (null == festivalTime) {
/*     */           continue;
/*     */         }
/* 401 */         int curDay = TimeUtil.getDayDisTime(festivalTime.startTime);
/* 402 */         ContinuityEntity continuityEntity = continuityComponent.getContinuityEntity(id.intValue());
/* 403 */         Map<Integer, Long> dateCharges = continuityEntity.getDateCharges();
/* 404 */         Map<Integer, Integer> states = continuityEntity.getStates();
/* 405 */         ArrayList<Integer> rankList = continFillingBean.getRankList();
/* 406 */         int type = PlayerUtil.getPlatformType();
/*     */         
/* 408 */         for (Integer tid : rankList) {
/* 409 */           ContinFillinglistBean continFillinglistBean = (ContinFillinglistBean)JsonTableService.getJsonData(tid.intValue(), ContinFillinglistBean.class);
/* 410 */           if (null == continFillinglistBean || ContinueUtil.judgeEntryDone(states, continFillinglistBean) || curDay != continFillinglistBean.getDay()) {
/*     */             continue;
/*     */           }
/* 413 */           LogUtil.errorLog(new Object[] { "repair113309", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(curDay), Integer.valueOf(type), Long.valueOf(todayC), tid });
/* 414 */           if (type != continFillinglistBean.getConnectortype()) {
/*     */             continue;
/*     */           }
/* 417 */           long oldValue = ((Long)dateCharges.getOrDefault(Integer.valueOf(curDay), Long.valueOf(0L))).longValue();
/* 418 */           LogUtil.errorLog(new Object[] { "repair11330", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(curDay), Long.valueOf(oldValue), Long.valueOf(todayC), tid });
/* 419 */           if (todayC > oldValue) {
/* 420 */             oldValue = todayC;
/*     */           }
/* 422 */           LogUtil.errorLog(new Object[] { "repair11332", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(curDay), Long.valueOf(oldValue), Long.valueOf(todayC) });
/* 423 */           if (oldValue >= continFillinglistBean.getRmb()) {
/* 424 */             oldValue = continFillinglistBean.getRmb();
/* 425 */             states.put(Integer.valueOf(continFillinglistBean.getId()), Integer.valueOf(1));
/*     */             
/* 427 */             if (!ContinueUtil.judgeTotalDone(states) && 
/* 428 */               ContinueUtil.getTotalNum(states) >= continFillingBean.getCommit()) {
/* 429 */               states.put(Integer.valueOf(-1), Integer.valueOf(1));
/*     */             }
/*     */ 
/*     */             
/* 433 */             continuityEntity.setStates(states);
/* 434 */             continuityComponent.updateStatesDB(id.intValue());
/*     */           } 
/* 436 */           dateCharges.put(Integer.valueOf(curDay), Long.valueOf(oldValue));
/* 437 */           continuityEntity.setDateCharges(dateCharges);
/* 438 */           continuityComponent.updateDateChargesDB(id.intValue());
/*     */         } 
/*     */       } 
/* 441 */       continuityComponent.maybeSaveToDB();
/*     */ 
/*     */       
/* 444 */       int curActId = DivineUtil.getCurDivineActId();
/* 445 */       if (curActId == -1) {
/*     */         return;
/*     */       }
/* 448 */       if (todayConsume == 0L) {
/*     */         return;
/*     */       }
/* 451 */       DivineComponent divineComponent = (DivineComponent)LookUpService.getComponent(iPlayer.getPlayerId(), DivineComponent.class);
/* 452 */       if (divineComponent == null) {
/*     */         return;
/*     */       }
/* 455 */       DivineParameter divineParameter = (DivineParameter)ParameterConstant.getParameter(57);
/*     */       
/* 457 */       DivineEntity divineEntity = divineComponent.getEntity(curActId);
/* 458 */       LogUtil.errorLog(new Object[] { "repair11330", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(divineEntity.getTimes()) });
/* 459 */       int times = (int)((todayConsume - (divineEntity.getTimes() * divineParameter.getPerTimes()) + divineEntity.getYuanbao()) / divineParameter.getPerTimes());
/* 460 */       divineEntity.setTimes(divineEntity.getTimes() + times);
/*     */       
/* 462 */       LogUtil.errorLog(new Object[] { "repair11330", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(todayC), Long.valueOf(todayConsume) });
/*     */     } 
/* 464 */     if (taskComponent.getFixedStatus() < 26) {
/* 465 */       taskComponent.setFixedStatus(26);
/*     */       
/* 467 */       ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 468 */       long todayC = extendComponent.getTodayRecharge();
/* 469 */       long todayConsume = extendComponent.getDailyConsume();
/* 470 */       LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/* 471 */       LimitComponent limitComponent = (LimitComponent)iPlayer.createIfNotExist(LimitComponent.class);
/*     */       
/* 473 */       Set<Integer> sets = limitActParameter.getLimitActivitylist(TaskType.ConsumeCCY.getType());
/*     */       
/* 475 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/*     */       
/* 479 */       for (Integer itemId : sets) {
/* 480 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 481 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 485 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 486 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 489 */         for (Integer id1 : ids) {
/* 490 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 491 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
/* 494 */           long defaultValue = 0L;
/* 495 */           int id = limitActivitylistBean.getId();
/* 496 */           int targetType = limitActivitylistBean.getTargetType();
/* 497 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 498 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 501 */           Map<Integer, Long> values = limitEntity.getValues();
/* 502 */           long count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue();
/* 503 */           boolean isDone = false;
/* 504 */           if (todayConsume > count) {
/* 505 */             values.put(Integer.valueOf(id), Long.valueOf(todayConsume));
/*     */           }
/* 507 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 508 */           if (isDone) {
/* 509 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 511 */           limitEntity.setValues(values);
/* 512 */           limitComponent.updateValuesDB(limitEntity.getId());
/*     */         } 
/*     */       } 
/*     */       
/* 516 */       sets = limitActParameter.getLimitActivitylist(TaskType.Charge.getType());
/*     */       
/* 518 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/*     */       
/* 522 */       for (Integer itemId : sets) {
/* 523 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 524 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 528 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 529 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 532 */         for (Integer id1 : ids) {
/* 533 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 534 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
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
/* 547 */           long defaultValue = 0L;
/* 548 */           int id = limitActivitylistBean.getId();
/* 549 */           int targetType = limitActivitylistBean.getTargetType();
/* 550 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 551 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 554 */           Map<Integer, Long> values = limitEntity.getValues();
/* 555 */           long count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue();
/* 556 */           boolean isDone = false;
/* 557 */           if (todayC > count) {
/* 558 */             values.put(Integer.valueOf(id), Long.valueOf(todayC));
/*     */           }
/* 560 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 561 */           if (isDone) {
/* 562 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 564 */           limitEntity.setValues(values);
/* 565 */           limitComponent.updateValuesDB(limitEntity.getId());
/* 566 */           LogUtils.errorLog(new Object[] { "charge repair" });
/*     */         } 
/*     */       } 
/*     */       
/* 570 */       sets = limitActParameter.getLimitActivitylist(TaskType.ClearNormal.getType());
/* 571 */       SingleInsComponent singleInsComponent = (SingleInsComponent)iPlayer.createIfNotExist(SingleInsComponent.class);
/*     */       
/* 573 */       long pass = 0L;
/* 574 */       for (null = singleInsComponent.getTimes().values().iterator(); null.hasNext(); ) { int value = ((Integer)null.next()).intValue();
/* 575 */         pass += value; }
/*     */       
/* 577 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/*     */       
/* 581 */       for (Integer itemId : sets) {
/* 582 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 583 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 587 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 588 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 591 */         for (Integer id1 : ids) {
/* 592 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 593 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
/* 596 */           long defaultValue = 0L;
/* 597 */           int id = limitActivitylistBean.getId();
/* 598 */           int targetType = limitActivitylistBean.getTargetType();
/* 599 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 600 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 603 */           Map<Integer, Long> values = limitEntity.getValues();
/* 604 */           long count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue();
/* 605 */           boolean isDone = false;
/* 606 */           if (pass > count) {
/* 607 */             values.put(Integer.valueOf(id), Long.valueOf(pass));
/*     */           }
/* 609 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 610 */           if (isDone) {
/* 611 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 613 */           limitEntity.setValues(values);
/* 614 */           limitComponent.updateValuesDB(limitEntity.getId());
/* 615 */           LogUtil.errorLog(new Object[] { "single", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(pass) });
/*     */         } 
/*     */       } 
/*     */       
/* 619 */       sets = limitActParameter.getLimitActivitylist(TaskType.OnGeneralChallege.getType());
/* 620 */       GeneralComponent generalComponent = (GeneralComponent)iPlayer.createIfNotExist(GeneralComponent.class);
/*     */       
/* 622 */       long general = 0L;
/* 623 */       for (null = generalComponent.getChallenges().values().iterator(); null.hasNext(); ) { int value = ((Integer)null.next()).intValue();
/* 624 */         general += value; }
/*     */       
/* 626 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/*     */       
/* 630 */       for (Integer itemId : sets) {
/* 631 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 632 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 636 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 637 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 640 */         for (Integer id1 : ids) {
/* 641 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 642 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
/* 645 */           long defaultValue = 0L;
/* 646 */           int id = limitActivitylistBean.getId();
/* 647 */           int targetType = limitActivitylistBean.getTargetType();
/* 648 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 649 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 652 */           Map<Integer, Long> values = limitEntity.getValues();
/* 653 */           boolean isDone = false;
/* 654 */           long count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue();
/* 655 */           if (general > count) {
/* 656 */             values.put(Integer.valueOf(id), Long.valueOf(general));
/*     */           }
/* 658 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 659 */           if (isDone) {
/* 660 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 662 */           limitEntity.setValues(values);
/* 663 */           limitComponent.updateValuesDB(limitEntity.getId());
/* 664 */           LogUtil.errorLog(new Object[] { "general", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(general) });
/*     */         } 
/*     */       } 
/* 667 */       LogUtil.errorLog(new Object[] { "repair1133026", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(todayC), Long.valueOf(todayConsume) });
/*     */     } 
/* 669 */     if (taskComponent.getFixedStatus() < 28) {
/* 670 */       taskComponent.setFixedStatus(28);
/*     */       
/* 672 */       PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 673 */       if (playerComponent.getLevel() <= 1) {
/*     */         return;
/*     */       }
/* 676 */       ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 677 */       long todayC = extendComponent.getTodayRecharge();
/* 678 */       long todayConsume = extendComponent.getDailyConsume();
/* 679 */       LimitActParameter limitActParameter = (LimitActParameter)ParameterConstant.getParameter(23);
/* 680 */       LimitComponent limitComponent = (LimitComponent)iPlayer.createIfNotExist(LimitComponent.class);
/*     */       
/* 682 */       Set<Integer> sets = limitActParameter.getLimitActivitylist(TaskType.Charge.getType());
/*     */       
/* 684 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/* 687 */       if (MContext.getDebug()) {
/*     */         return;
/*     */       }
/* 690 */       for (Integer itemId : sets) {
/* 691 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 692 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 696 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 697 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 700 */         for (Integer id1 : ids) {
/* 701 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 702 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
/*     */           
/* 706 */           FestivalTime festivalTime = limitActParameter.getFestivalTime(id1.intValue());
/* 707 */           if (null != festivalTime) {
/* 708 */             LimitActivityBean limitActivityBean = (LimitActivityBean)JsonTableService.getJsonData(id1.intValue(), LimitActivityBean.class);
/* 709 */             if (limitActivityBean.getActType() == 99 && TimeUtil.getDayDiffToOpen(festivalTime.startTime, 1552147190) == 2) {
/* 710 */               String url = "http://129.204.166.80:803/fairy/testCharge.html?playerId=";
/* 711 */               if (MContext.getPlatform().equals("weixin")) {
/* 712 */                 url = "http://129.204.166.80/fairy/testCharge.html?playerId=";
/* 713 */               } else if (MContext.getPlatform().equals("xinghui") || MContext.getPlatform().equals("micro")) {
/* 714 */                 url = "http://129.204.166.80:804/fairy/testCharge.html?playerId=";
/* 715 */               } else if (MContext.getPlatform().equals("awy")) {
/* 716 */                 url = "http://129.204.166.80:801/fairy/testCharge.html?playerId=";
/* 717 */               } else if (MContext.getPlatform().equals("wanba")) {
/* 718 */                 url = "http://129.204.166.80:802/fairy/testCharge.html?playerId=";
/* 719 */               } else if (MContext.getPlatform().equals("baidu")) {
/* 720 */                 url = "http://129.204.166.80:803/fairy/testCharge.html?playerId=";
/*     */               } 
/*     */               
/*     */               try {
/* 724 */                 String result = HttpClientSingleton.INSTANCE.get(url + iPlayer.getPlayerId() + "&starttime=2019-03-08%2000:00:00&endtime=2019-03-09%2023:59:59", null, null);
/* 725 */                 if (null != result) {
/* 726 */                   ObjectMapper objectMapper = new ObjectMapper();
/* 727 */                   Map<Long, Integer> resMap = (Map<Long, Integer>)objectMapper.readValue(result, Map.class);
/* 728 */                   if (!resMap.isEmpty()) {
/* 729 */                     todayC = 0L;
/* 730 */                     for (Iterator<Integer> iterator = resMap.values().iterator(); iterator.hasNext(); ) { int charge = ((Integer)iterator.next()).intValue();
/* 731 */                       todayC += charge; }
/*     */                   
/*     */                   } 
/*     */                 } 
/* 735 */               } catch (Exception e) {
/* 736 */                 e.printStackTrace();
/*     */               } 
/*     */             } else {
/*     */               continue;
/*     */             } 
/*     */           } 
/*     */           
/* 743 */           long defaultValue = 0L;
/* 744 */           int id = limitActivitylistBean.getId();
/* 745 */           int targetType = limitActivitylistBean.getTargetType();
/* 746 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 747 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 750 */           Map<Integer, Long> values = limitEntity.getValues();
/* 751 */           long count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue();
/* 752 */           boolean isDone = false;
/* 753 */           if (todayC > count) {
/* 754 */             values.put(Integer.valueOf(id), Long.valueOf(todayC));
/*     */           }
/* 756 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 757 */           if (isDone) {
/* 758 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 760 */           limitEntity.setValues(values);
/* 761 */           limitComponent.updateValuesDB(limitEntity.getId());
/* 762 */           LogUtils.errorLog(new Object[] { "Totalcharge repair" });
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 767 */       sets = limitActParameter.getLimitActivitylist(TaskType.ClearNormal.getType());
/* 768 */       SingleInsComponent singleInsComponent = (SingleInsComponent)iPlayer.createIfNotExist(SingleInsComponent.class);
/*     */       
/* 770 */       long pass = 0L;
/* 771 */       for (null = singleInsComponent.getTimes().values().iterator(); null.hasNext(); ) { int value = ((Integer)null.next()).intValue();
/* 772 */         pass += value; }
/*     */       
/* 774 */       for (null = singleInsComponent.getVipSweepTimes().values().iterator(); null.hasNext(); ) { int sweep = ((Integer)null.next()).intValue();
/* 775 */         pass += sweep; }
/*     */       
/* 777 */       if (null == sets || sets.isEmpty()) {
/*     */         return;
/*     */       }
/*     */       
/* 781 */       for (Integer itemId : sets) {
/* 782 */         LimitActivitylistBean limitActivitylistBean = (LimitActivitylistBean)JsonTableService.getJsonData(itemId.intValue(), LimitActivitylistBean.class);
/* 783 */         if (null == limitActivitylistBean || 1 == limitActivitylistBean.getDisplayType()) {
/*     */           continue;
/*     */         }
/*     */         
/* 787 */         Set<Integer> ids = limitActParameter.getIds(itemId.intValue());
/* 788 */         if (null == ids || ids.isEmpty()) {
/*     */           continue;
/*     */         }
/* 791 */         for (Integer id1 : ids) {
/* 792 */           LimitEntity limitEntity = limitComponent.getEntity(id1.intValue());
/* 793 */           if (!limitEntity.isOpen()) {
/*     */             continue;
/*     */           }
/* 796 */           long defaultValue = 0L;
/* 797 */           int id = limitActivitylistBean.getId();
/* 798 */           int targetType = limitActivitylistBean.getTargetType();
/* 799 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(targetType, TaskTypeBean.class);
/* 800 */           if (null == taskTypeBean) {
/*     */             return;
/*     */           }
/* 803 */           Map<Integer, Long> values = limitEntity.getValues();
/* 804 */           boolean isDone = false;
/* 805 */           long count = ((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue();
/* 806 */           if (pass > count) {
/* 807 */             values.put(Integer.valueOf(id), Long.valueOf(pass));
/*     */           }
/* 809 */           isDone = (((Long)values.getOrDefault(Integer.valueOf(id), Long.valueOf(defaultValue))).longValue() >= limitActivitylistBean.getTarget());
/* 810 */           if (isDone) {
/* 811 */             limitComponent.done(limitEntity, limitActivitylistBean);
/*     */           }
/* 813 */           limitEntity.setValues(values);
/* 814 */           limitComponent.updateValuesDB(limitEntity.getId());
/* 815 */           LogUtil.errorLog(new Object[] { "single2", Long.valueOf(iPlayer.getPlayerId()), Long.valueOf(pass) });
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void downNotBattleEquip(IPlayer iPlayer) {
/* 824 */     PartnerComponent partnerComponent = (PartnerComponent)iPlayer.createIfNotExist(PartnerComponent.class);
/* 825 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 826 */     EquipComponent equipComponent = (EquipComponent)iPlayer.createIfNotExist(EquipComponent.class);
/* 827 */     for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 828 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 829 */       if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) < 0) {
/* 830 */         for (Iterator<Long> iterator = partnerEntity.getEquips().values().iterator(); iterator.hasNext(); ) { long mid = ((Long)iterator.next()).longValue();
/* 831 */           if (mid == 0L) {
/*     */             continue;
/*     */           }
/* 834 */           EquipEntity equipEntity = equipComponent.getEquipEntity(mid);
/* 835 */           if (equipEntity != null) {
/* 836 */             equipEntity.setIsWear((byte)0);
/*     */           } }
/*     */         
/* 839 */         for (EquipPart equipPart : EquipPart.values()) {
/* 840 */           partnerEntity.getEquips().put(Integer.valueOf(equipPart.getPart()), Long.valueOf(0L));
/*     */         }
/* 842 */         partnerEntity.setEquips(partnerEntity.getEquips());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void CheckBattlePartner(IPlayer iPlayer) {
/* 848 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 849 */     PartnerBattleParameter partnerBattleParameter = (PartnerBattleParameter)ParameterConstant.getParameter(34);
/* 850 */     PartnerComponent partnerComponent = (PartnerComponent)iPlayer.createIfNotExist(PartnerComponent.class);
/*     */     
/* 852 */     int num2 = 0;
/* 853 */     for (Iterator<Integer> iterator = partnerBattleParameter.getMap().keySet().iterator(); iterator.hasNext(); ) { int pos = ((Integer)iterator.next()).intValue();
/* 854 */       int level = ((Integer)partnerBattleParameter.getMap().get(Integer.valueOf(pos))).intValue();
/* 855 */       int vipLevel = ((Integer)partnerBattleParameter.getVipMap().get(Integer.valueOf(pos))).intValue();
/* 856 */       if (playerComponent.getLevel() >= level || playerComponent.getVip() >= vipLevel) {
/* 857 */         num2++;
/*     */       } }
/*     */     
/* 860 */     for (int i = num2; i < 6; i++) {
/* 861 */       PartnerEntity partnerEntity = partnerComponent.getEntity(((Long)playerComponent.getFighters().get(i)).longValue());
/* 862 */       if (null != partnerEntity) {
/* 863 */         partnerEntity.setStatus(0);
/*     */       }
/* 865 */       playerComponent.getFighters().set(i, Long.valueOf(0L));
/*     */     } 
/* 867 */     playerComponent.setFighters(playerComponent.getFighters());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void repair11350(IPlayer iPlayer) {
/* 872 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
/* 873 */     if (taskComponent.getFixedStatus() < 30) {
/* 874 */       taskComponent.setFixedStatus(30);
/*     */       
/* 876 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 877 */       int starId = 0;
/* 878 */       SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)LookUpService.getComponent(iPlayer.getPlayerId(), SanGuoZhiComponent.class);
/*     */       
/* 880 */       for (IMapEntity iMapEntity : sanGuoZhiComponent.getEntityMap().values()) {
/* 881 */         SanGuoZhiEntity sanGuoZhiEntity = (SanGuoZhiEntity)iMapEntity;
/* 882 */         if (!sanGuoZhiEntity.isActivity()) {
/* 883 */           starId = sanGuoZhiEntity.getRecordStarId();
/*     */         }
/*     */       } 
/* 886 */       if (starId > loginParameter.getStarId()) {
/* 887 */         String title = LanguageConstant.getLanguage(114);
/* 888 */         String content = LanguageConstant.getAndReplaceLanguage(115, new String[0]);
/* 889 */         MailUtil.sendSysMail(iPlayer.getPlayerId(), loginParameter.getArtiReward(), title, content);
/*     */       } 
/* 891 */       LogUtil.errorLog(new Object[] { "repair11350", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(starId) });
/*     */     } 
/* 893 */     if (taskComponent.getFixedStatus() < 32) {
/* 894 */       taskComponent.setFixedStatus(32);
/*     */       
/* 896 */       RankAct rankact = (RankAct)RankActUtil.rankActMap.get(Integer.valueOf(RankActType.Recruit2.getType()));
/* 897 */       if (null != rankact && rankact.getState() == RankAct.RankActState.Opening) {
/* 898 */         RecruitComponent recruitComponent = (RecruitComponent)iPlayer.createIfNotExist(RecruitComponent.class);
/* 899 */         RankActComponent rankActComponent = (RankActComponent)iPlayer.createIfNotExist(RankActComponent.class);
/* 900 */         RankActEntity rankActEntity = rankActComponent.getEntity(RankActType.Recruit2.getType());
/* 901 */         if (null != rankActEntity && 
/* 902 */           recruitComponent.getRebateScore() > rankActEntity.getValue()) {
/* 903 */           RankActUtil.refreshRankValue(RankActType.Recruit2.getType(), recruitComponent.getRebateScore() - rankActEntity.getValue(), iPlayer.getPlayerId());
/*     */         }
/*     */       } 
/*     */       
/* 907 */       PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 908 */       int quality = playerComponent.getQuality();
/* 909 */       int starId = 0;
/* 910 */       int newQuality = 0;
/* 911 */       SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)LookUpService.getComponent(iPlayer.getPlayerId(), SanGuoZhiComponent.class);
/*     */       
/* 913 */       for (IMapEntity iMapEntity : sanGuoZhiComponent.getEntityMap().values()) {
/* 914 */         SanGuoZhiEntity sanGuoZhiEntity = (SanGuoZhiEntity)iMapEntity;
/* 915 */         if (!sanGuoZhiEntity.isActivity()) {
/* 916 */           starId = sanGuoZhiEntity.getRecordStarId();
/*     */         }
/*     */       } 
/* 919 */       if (starId > 5) {
/* 920 */         newQuality = 50;
/*     */       }
/* 922 */       if (starId > 33) {
/* 923 */         newQuality = 60;
/*     */       }
/* 925 */       if (starId > 101) {
/* 926 */         newQuality = 70;
/*     */       }
/* 928 */       if (quality < newQuality || quality == 999) {
/* 929 */         playerComponent.setQuality(newQuality);
/*     */       }
/* 931 */       PlayerUtil.updateKeyValueInfo(iPlayer.getSession(), KeyValueConstant.QUILITY.getKey(), playerComponent.getQuality());
/* 932 */       LogUtil.errorLog(new Object[] { "repair11360", Long.valueOf(iPlayer.getPlayerId()) });
/*     */     } 
/* 934 */     if (taskComponent.getFixedStatus() < 34) {
/* 935 */       taskComponent.setFixedStatus(34);
/*     */       
/* 937 */       for (Iterator<Long> iterator = LookUpService.getOnlinePlayer().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 938 */         IPlayer player = LookUpService.getByPlayerId(playerId);
/* 939 */         if (null != player) {
/* 940 */           TaskComponent taskComponent1 = (TaskComponent)player.createIfNotExist(TaskComponent.class);
/* 941 */           if (taskComponent1.getFixedStatus() < 34) {
/* 942 */             taskComponent1.setFixedStatus(34);
/*     */             
/* 944 */             ExtendComponent extendComponent1 = (ExtendComponent)player.createIfNotExist(ExtendComponent.class);
/* 945 */             extendComponent1.setWeekResetTime(TimeUtil.getTimeStampZero(TimeUtil.getPrevTargetWeekDay(2)));
/*     */             
/* 947 */             LogUtil.errorLog(new Object[] { "repair11360", Long.valueOf(iPlayer.getPlayerId()) });
/*     */           } 
/*     */         }  }
/*     */ 
/*     */ 
/*     */       
/* 953 */       ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 954 */       extendComponent.setWeekResetTime(TimeUtil.getTimeStampZero(TimeUtil.getPrevTargetWeekDay(2)));
/*     */       
/* 956 */       LogUtil.errorLog(new Object[] { "repair11360", Long.valueOf(iPlayer.getPlayerId()) });
/*     */     } 
/* 958 */     if (taskComponent.getFixedStatus() < 36) {
/* 959 */       taskComponent.setFixedStatus(36);
/*     */ 
/*     */       
/* 962 */       FinanceUtil.addCurrency(iPlayer, CurrencyType.TotalCharge, 0L, ResourceEvent.gmAdd);
/* 963 */       taskComponent.refreshSchedule(TaskType.VipLevel, 0, 0L);
/* 964 */       LogUtil.errorLog(new Object[] { "repair11380", Long.valueOf(iPlayer.getPlayerId()) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\player\VersionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */