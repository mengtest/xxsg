/*      */ package com.linlongyx.sanguo.webgame.processors.player;
/*      */ import com.linlongyx.core.framework.logic.IPlayer;
/*      */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*      */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*      */ import com.linlongyx.core.utils.LogUtils;
/*      */ import com.linlongyx.core.utils.TimeUtil;
/*      */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.mental.MentalComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.rune.RuneComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.welfare.WelfareComponent;
/*      */ import com.linlongyx.sanguo.webgame.common.reward.PlayerCurrency;
/*      */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.DailyShopBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ExpLeaderBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.LevelPackageBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MapTableBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MountBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.TitleBean;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.GoldBuyParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.InvitationParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.RuneParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*      */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*      */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*      */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*      */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.RankActType;
/*      */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*      */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*      */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*      */ import com.linlongyx.sanguo.webgame.log.LogType;
/*      */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.cat.FortuneCatUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.login.LoginUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.runewar.RunewarUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.shop.ShopUtil;
/*      */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.aoi.EnterSceneRequest;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.common.KeyValueUpdateResponse;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.common.RedNoticeResponse;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*      */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*      */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ public class PlayerUtil {
/*      */   public static final long PLAYER_PID = -1L;
/*   78 */   private static Logger LOG = LoggerFactory.getLogger(PlayerUtil.class); public static final long WARPET_PID = -2L; public static final int PLATFROM_WEIXIN = 1; public static final int PLATFROM_XINGHUI = 2; public static final int PLATFROM_AIFENG = 3; public static final int PLATFROM_WANBA = 4;
/*      */   public static final int PLATFROM_BAIDU = 5;
/*      */   
/*      */   public int hashCode() {
/*   82 */     return super.hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short silentForbid(String playerIds, int endtime, byte type) {
/*   93 */     String[] playerIdList = playerIds.split("_");
/*      */     try {
/*   95 */       if (type == 1 || type == 4 || type == 6) {
/*      */         
/*   97 */         for (String playerIdStr : playerIdList) {
/*   98 */           long playerId = Long.parseLong(playerIdStr);
/*   99 */           boolean playerExist = LookUpService.setPlayerStatus(playerId, type, TimeUtil.currentTime(), (endtime == -1) ? Integer.MAX_VALUE : endtime);
/*  100 */           if (!playerExist) {
/*  101 */             LOG.warn("不存在id为 {} 的player", Long.valueOf(playerId));
/*      */           }
/*      */         } 
/*  104 */       } else if (type == 0) {
/*  105 */         for (String playerIdStr : playerIdList) {
/*  106 */           long playerId = Long.parseLong(playerIdStr);
/*  107 */           if (LookUpService.isOnline(playerId)) {
/*  108 */             LookUpService.logout(LookUpService.getByPlayerId(playerId));
/*      */           }
/*  110 */           boolean playerExist = LookUpService.setPlayerStatus(playerId, type, TimeUtil.currentTime(), (endtime == -1) ? Integer.MAX_VALUE : endtime);
/*  111 */           if (!playerExist) {
/*  112 */             LOG.warn("不存在id为 {} 的player", Long.valueOf(playerId));
/*      */           }
/*      */         } 
/*  115 */       } else if (type == 3 || type == 2 || type == 5 || type == 7) {
/*      */         
/*  117 */         for (String playerIdStr : playerIdList) {
/*  118 */           long playerId = Long.parseLong(playerIdStr);
/*  119 */           byte clearType = 0;
/*  120 */           switch (type) {
/*      */             case 3:
/*  122 */               clearType = 0;
/*      */               break;
/*      */             case 2:
/*  125 */               clearType = 1;
/*      */               break;
/*      */             case 5:
/*  128 */               clearType = 4;
/*      */               break;
/*      */             case 7:
/*  131 */               clearType = 6;
/*      */               break;
/*      */           } 
/*  134 */           boolean playerExist = LookUpService.clearPlayerStatus(playerId, clearType);
/*  135 */           if (!playerExist) {
/*  136 */             LOG.warn("不存在id为 {} 的player", Long.valueOf(playerId));
/*      */           }
/*      */         } 
/*      */       } else {
/*  140 */         LOG.error("illegal type");
/*  141 */         return 10002;
/*      */       } 
/*  143 */     } catch (NumberFormatException e) {
/*  144 */       LOG.error("非法的玩家id");
/*  145 */       return 10002;
/*      */     } 
/*  147 */     return 10001;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void checkSameActivateCard(IPlayerSession playerSession) {
/*  156 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*      */ 
/*      */     
/*  159 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*      */     
/*  161 */     Map<Integer, Integer> activeTitles = playerComponent.getActiveTitles();
/*  162 */     for (Map.Entry<Integer, Integer> entry : activeTitles.entrySet()) {
/*  163 */       if (((Integer)entry.getValue()).intValue() == 0) {
/*  164 */         TitleBean titleBean = (TitleBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), TitleBean.class);
/*  165 */         if (null != titleBean) {
/*  166 */           for (RewardBean rewardBean : titleBean.getItem()) {
/*  167 */             handleSameCard(bagComponent, rewardBean.getId());
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void handleSameCard(BagComponent bagComponent, int itemId) {
/*  175 */     int num = (int)bagComponent.getItemNum(itemId);
/*  176 */     if (num > 0) {
/*  177 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  178 */       if (null != itemBean) {
/*  179 */         bagComponent.deleteItem(itemId, num, ResourceEvent.RedundantCard);
/*  180 */         String title = LanguageConstant.getLanguage(5509);
/*  181 */         String content = LanguageConstant.getAndReplaceLanguage(5510, new String[] { itemBean.getName() });
/*  182 */         Reward reward = new Reward();
/*  183 */         reward.type = 1;
/*  184 */         reward.id = CurrencyType.CCY.getType();
/*  185 */         reward.num = (itemBean.getPrice() * num);
/*  186 */         ArrayList<Reward> attachments = new ArrayList<>();
/*  187 */         attachments.add(reward);
/*  188 */         MailUtil.sendSysMail(bagComponent.getPlayerId(), attachments, title, content);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static short charge(IPlayer iPlayer, ChargeBean chargeBean) {
/*  201 */     LogUtils.errorLog(new Object[] { "PlayerUtil charge start", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(chargeBean.getId()) });
/*  202 */     IPlayerSession playerSession = iPlayer.getSession();
/*  203 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*  204 */     int originVip = playerComponent.getVip();
/*      */     
/*  206 */     FinanceUtil.addCurrency(iPlayer, CurrencyType.TotalCharge, (chargeBean.getRmb() / 100), ResourceEvent.charge);
/*      */     
/*  208 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/*  209 */     Set<Integer> firstReChargeSet = extendComponent.getFirstReChargeSet();
/*  210 */     Set<Integer> newFirstCharge = extendComponent.getNewFirstCharge();
/*      */     
/*  212 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  213 */     if (chargeBean.getMoney() > 0) {
/*  214 */       Reward reward = new Reward();
/*  215 */       reward.type = 1;
/*  216 */       reward.id = CurrencyType.CCY.getType();
/*  217 */       reward.num = chargeBean.getMoney();
/*      */       
/*  219 */       if (firstReChargeSet.contains(Integer.valueOf(chargeBean.getId()))) {
/*  220 */         if (newFirstCharge.isEmpty() && chargeBean.getIsFirst() != 0 && !MContext.getPlatform().equals("xinghui")) {
/*  221 */           newFirstCharges(playerSession, reward, chargeBean, extendComponent, newFirstCharge);
/*  222 */         } else if (newFirstCharge.isEmpty() && chargeBean.getIsFirstweiduan() != 0 && MContext.getPlatform().equals("xinghui")) {
/*  223 */           newWeiduFirstCharge(playerSession, reward, chargeBean, extendComponent, newFirstCharge);
/*      */         }
/*  225 */         else if (chargeBean.getLimitless() == 1) {
/*  226 */           reward.num += chargeBean.getFirstReward();
/*      */         } else {
/*  228 */           reward.num += chargeBean.getGeneralReward();
/*      */         }
/*      */       
/*      */       }
/*  232 */       else if (newFirstCharge.isEmpty() && chargeBean.getIsFirst() != 0 && !MContext.getPlatform().equals("xinghui")) {
/*  233 */         newFirstCharges(playerSession, reward, chargeBean, extendComponent, newFirstCharge);
/*  234 */       } else if (newFirstCharge.isEmpty() && chargeBean.getIsFirstweiduan() != 0 && MContext.getPlatform().equals("xinghui")) {
/*  235 */         newWeiduFirstCharge(playerSession, reward, chargeBean, extendComponent, newFirstCharge);
/*      */       } else {
/*  237 */         reward.num += chargeBean.getFirstReward();
/*  238 */         firstReChargeSet.add(Integer.valueOf(chargeBean.getId()));
/*  239 */         extendComponent.setFirstReChargeSet(firstReChargeSet);
/*      */       } 
/*      */ 
/*      */       
/*  243 */       rewards.add(reward);
/*      */       
/*  245 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  246 */       Set<Integer> normalChargeId = new HashSet<>(loginParameter.getNormalChargeId());
/*      */       
/*  248 */       for (Iterator<Integer> iterator = firstReChargeSet.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  249 */         if (normalChargeId.contains(Integer.valueOf(id))) {
/*  250 */           normalChargeId.remove(Integer.valueOf(id));
/*      */         } }
/*      */       
/*  253 */       if (normalChargeId.isEmpty()) {
/*  254 */         LogUtils.errorLog(new Object[] { "firstReChargeSetReset", Long.valueOf(iPlayer.getPlayerId()), firstReChargeSet.toString() });
/*  255 */         firstReChargeSet.removeAll(loginParameter.getNormalChargeId());
/*  256 */         extendComponent.setFirstReChargeSet(firstReChargeSet);
/*      */       } 
/*      */     } 
/*      */     
/*  260 */     if (chargeBean.getType() == 3) {
/*  261 */       WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/*      */       
/*  263 */       int dailylevel = WelfareUtil.getfundLevel(chargeBean.getId(), WelfareUtil.getDailyWorldLevel());
/*  264 */       Map<Integer, Object> map = JsonTableService.getJsonTable(DailyShopBean.class);
/*  265 */       for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  266 */         DailyShopBean dailyShopBean = (DailyShopBean)map.get(Integer.valueOf(id));
/*  267 */         if (dailyShopBean.getChargeID().get(Integer.valueOf(chargeBean.getId())) == null) {
/*      */           continue;
/*      */         }
/*  270 */         if (welfareComponent.getDailyBuyReward().containsKey(Integer.valueOf(chargeBean.getId()))) {
/*      */           continue;
/*      */         }
/*  273 */         int wordLevel = ((DailyShopBean.ChargeIDBean)dailyShopBean.getChargeID().get(Integer.valueOf(chargeBean.getId()))).getWorldLevel();
/*  274 */         if (wordLevel == dailylevel) {
/*  275 */           welfareComponent.getDailyBuyReward().put(Integer.valueOf(chargeBean.getId()), Integer.valueOf(TimeUtil.currentTime()));
/*  276 */           welfareComponent.setDailyBuyReward(welfareComponent.getDailyBuyReward());
/*  277 */           FinanceUtil.reward(FinanceUtil.transform(((DailyShopBean.ChargeIDBean)dailyShopBean.getChargeID().get(Integer.valueOf(chargeBean.getId()))).getGoods()), playerSession.getPlayer(), ResourceEvent.DailyShop, true);
/*  278 */           WelfareUtil.noticeDailyShopInfo(playerSession, welfareComponent);
/*      */           
/*      */           break;
/*      */         }  }
/*      */     
/*      */     } 
/*  284 */     TimeLimitUtil.timeLimitBuy(iPlayer, chargeBean);
/*      */     
/*  286 */     FinanceUtil.reward(rewards, iPlayer, ResourceEvent.charge, true);
/*      */     
/*  288 */     ChargeUtil.handleRecharge(playerSession.getPlayer(), chargeBean);
/*      */ 
/*      */     
/*  291 */     MonthCardUtil.handleBuy(iPlayer.getPlayerId(), chargeBean);
/*      */     
/*  293 */     GoldBuyParameter goldBuyParameter = (GoldBuyParameter)ParameterConstant.getParameter(70);
/*  294 */     if (chargeBean.getId() == goldBuyParameter.getChargeId()) {
/*  295 */       RuneComponent runeComponent = (RuneComponent)iPlayer.createIfNotExist(RuneComponent.class);
/*  296 */       LogUtils.errorLog(new Object[] { "goldBuy", playerSession.getPlayer(), Integer.valueOf(runeComponent.getGoldEndTime()) });
/*  297 */       if (runeComponent.getGoldEndTime() + 60 > TimeUtil.currentTime()) {
/*  298 */         FinanceUtil.reward(goldBuyParameter.getGoldRewards(), iPlayer, ResourceEvent.GoldBuy, true);
/*  299 */         runeComponent.setGoldEndTime(TimeUtil.currentTime() - 60);
/*  300 */         updateKeyValueInfo(iPlayer.getSession(), KeyValueConstant.GOLDENDTIME.getKey(), runeComponent.getGoldEndTime());
/*      */       } 
/*      */     } 
/*      */     
/*  304 */     updateKeyValueInfo(playerSession, KeyValueConstant.CHARGE.getKey(), chargeBean.getId());
/*      */     
/*  306 */     updateAllAct(playerSession, iPlayer, chargeBean.getRmb(), extendComponent, playerComponent, originVip, chargeBean.getId());
/*      */     
/*  308 */     LogUtils.errorLog(new Object[] { "PlayerUtil charge end", Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(chargeBean.getId()) });
/*  309 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateAllAct(IPlayerSession playerSession, IPlayer iPlayer, int num, ExtendComponent extendComponent, PlayerComponent playerComponent, int originVip, int chargeId) {
/*  323 */     extendComponent.setTodayRecharge(extendComponent.getTodayRecharge() + num);
/*  324 */     extendComponent.setTotalChargeCCB(extendComponent.getTotalChargeCCB() + num);
/*      */     
/*  326 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/*  327 */     welfareComponent.setOneBuyCharge(welfareComponent.getOneBuyCharge() + num);
/*      */     
/*  329 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/*  330 */     signComponent.setWeekCharge(signComponent.getWeekCharge() + num);
/*      */     
/*  332 */     signComponent.setDayCharge(signComponent.getDayCharge() + num);
/*      */     
/*  334 */     LuckyMoneyUtil.addLuckyItem(num, playerSession);
/*      */     
/*  336 */     FortuneCatUtil.updateActCharge(playerSession.getPlayer(), num);
/*      */     
/*  338 */     if (extendComponent.getTodayFirstCharge() == 0L) {
/*  339 */       extendComponent.setTodayFirstCharge(num);
/*      */     }
/*      */     
/*  342 */     if (originVip != playerComponent.getVip()) {
/*  343 */       int newSize = extendComponent.getBagSize() + VipUtil.getNum(playerComponent.getVip(), 12);
/*      */       
/*  345 */       updateKeyValueInfo(playerSession, KeyValueConstant.BAG_SIZE_CHANGE.getKey(), newSize);
/*      */       
/*  347 */       LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  348 */       int maxCount = VipUtil.getNum(playerComponent.getVip(), 1) + loginParameter.getInitCombatTimes();
/*  349 */       updateKeyValueInfo(playerSession, KeyValueConstant.RABIT_COMBAT.getKey(), maxCount);
/*      */       
/*  351 */       ShopParameter parameter = (ShopParameter)ParameterConstant.getParameter(31);
/*  352 */       int freeCount = parameter.getFreeCount() + VipUtil.getNum(playerComponent.getVip(), 7);
/*  353 */       updateKeyValueInfo(playerSession, KeyValueConstant.MYSTERY_SHOP.getKey(), freeCount);
/*  354 */       int crFreeCount = parameter.getCrystalRreeCount() + VipUtil.getNum(playerComponent.getVip(), 21);
/*  355 */       updateKeyValueInfo(playerSession, KeyValueConstant.CRYSTAL_SHOP.getKey(), crFreeCount);
/*      */       
/*  357 */       sendNotice(1, playerSession.getPlayer(), 0, null);
/*      */     } 
/*  359 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  360 */     if (taskComponent != null) {
/*  361 */       taskComponent.refreshSchedule(TaskType.VipLevel, 0, 0L);
/*  362 */       taskComponent.refreshSchedule(TaskType.Charge, 0, num);
/*  363 */       taskComponent.refreshSchedule(TaskType.ActivityTotalCharge, 0, num);
/*  364 */       taskComponent.refreshSchedule(TaskType.TotalCharge, 0, num);
/*  365 */       taskComponent.refreshSchedule(TaskType.OneCharge, 0, num);
/*  366 */       taskComponent.refreshSchedule(TaskType.OneMoreCharge, 0, chargeId);
/*      */     } 
/*      */ 
/*      */     
/*  370 */     ContinueUtil.refresh(iPlayer.getPlayerId(), num);
/*      */ 
/*      */     
/*  373 */     RechargeActivityUtil.refreshOpeningActivity(iPlayer, num);
/*      */     
/*  375 */     iPlayer.saveAll();
/*      */     
/*  377 */     UserComponent userComponent = (UserComponent)playerSession.getPlayer().createIfNotExist(UserComponent.class);
/*  378 */     if (userComponent.getOrderByPlayerId() != 0L) {
/*  379 */       WxPlayerInfo info = new WxPlayerInfo();
/*  380 */       info.level = playerComponent.getLevel();
/*  381 */       info.totalCharge = num;
/*  382 */       info.head = playerComponent.getHead();
/*  383 */       info.name = playerComponent.getPlayerName();
/*  384 */       info.quality = playerComponent.getQuality();
/*  385 */       info.playerId = playerComponent.getPlayerId();
/*  386 */       LoginUtil.updateOrNotice(userComponent.getOrderByPlayerId(), info);
/*      */     } 
/*  388 */     RankActUtil.refreshRankValue(RankActType.Charge.getType(), num, iPlayer.getPlayerId());
/*  389 */     CrossRankActUtil.refreshRankValue(CrossRankActType.Charge.getType(), num, iPlayer.getPlayerId());
/*      */   }
/*      */   
/*      */   public static void addExp(IPlayer iPlayer, long exp) {
/*  393 */     if (exp <= 0L) {
/*      */       return;
/*      */     }
/*  396 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*  397 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
/*  398 */     PlayerCurrency.set(playerComponent, CurrencyType.EXP, playerComponent.getCurrency(CurrencyType.EXP) + exp);
/*  399 */     ExpLeaderBean expLeaderBean = (ExpLeaderBean)JsonTableService.getJsonData(playerComponent.getLevel(), ExpLeaderBean.class);
/*  400 */     int oldLevel = playerComponent.getLevel();
/*      */ 
/*      */     
/*  403 */     int limitLevel = 200;
/*  404 */     for (Iterator<Integer> iterator = playerComponent.getReincarnationIds().iterator(); iterator.hasNext(); ) { int reinId = ((Integer)iterator.next()).intValue();
/*  405 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reinId, FighterReincarnBean.class);
/*  406 */       if (null != fighterReincarnBean && fighterReincarnBean.getLevelLimit() > limitLevel) {
/*  407 */         limitLevel = fighterReincarnBean.getLevelLimit();
/*      */       } }
/*      */     
/*  410 */     while (expLeaderBean != null && expLeaderBean.getExp() != 0L && limitLevel > playerComponent.getLevel() && PlayerCurrency.get(playerComponent, CurrencyType.EXP) >= expLeaderBean.getExp()) {
/*  411 */       PlayerCurrency.set(playerComponent, CurrencyType.EXP, playerComponent.getCurrency(CurrencyType.EXP) - expLeaderBean.getExp());
/*  412 */       playerComponent.setLevel((short)(playerComponent.getLevel() + 1));
/*  413 */       expLeaderBean = (ExpLeaderBean)JsonTableService.getJsonData(playerComponent.getLevel(), ExpLeaderBean.class);
/*  414 */       FunctionOpenConstant.checkFunctionOpen(iPlayer.getSession());
/*      */       
/*  416 */       SignComponent signComponent = (SignComponent)iPlayer.createIfNotExist(SignComponent.class);
/*  417 */       LevelPackageBean levelPackageBean = (LevelPackageBean)JsonTableService.getJsonData(playerComponent.getLevel(), LevelPackageBean.class);
/*  418 */       if (null != levelPackageBean) {
/*  419 */         signComponent.getLevelMap().put(Integer.valueOf(playerComponent.getLevel()), Integer.valueOf(TimeUtil.currentTime() + levelPackageBean.getEffectivetime()));
/*  420 */         signComponent.setLevelMap(signComponent.getLevelMap());
/*      */       } 
/*      */ 
/*      */       
/*  424 */       LogUtil.gameLog(LogType.LEVEL, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Short.valueOf(playerComponent.getLevel()), TimeUtil.getFormatDate() });
/*      */ 
/*      */ 
/*      */       
/*  428 */       if (playerComponent.getLevel() == 200) {
/*  429 */         sendNotice(5, iPlayer, 0, null);
/*      */       }
/*      */       
/*  432 */       InvitationParameter invitationParameter = (InvitationParameter)ParameterConstant.getParameter(36);
/*  433 */       UserComponent userComponent = (UserComponent)iPlayer.createIfNotExist(UserComponent.class);
/*  434 */       if (userComponent.getOrderByPlayerId() != 0L && (playerComponent.getLevel() == invitationParameter.getLevelCount() || playerComponent.getLevel() == invitationParameter.getInviteLevelNum())) {
/*  435 */         WxPlayerInfo info = new WxPlayerInfo();
/*  436 */         info.level = playerComponent.getLevel();
/*  437 */         info.totalCharge = 0L;
/*  438 */         info.head = playerComponent.getHead();
/*  439 */         info.name = playerComponent.getPlayerName();
/*  440 */         info.quality = playerComponent.getQuality();
/*  441 */         info.playerId = playerComponent.getPlayerId();
/*  442 */         LoginUtil.updateOrNotice(userComponent.getOrderByPlayerId(), info);
/*      */       } 
/*      */     } 
/*  445 */     IPlayerSession playerSession = iPlayer.getSession();
/*  446 */     if (oldLevel != playerComponent.getLevel()) {
/*  447 */       playerComponent.getPlayerAttrUp().levelUp(iPlayer, playerComponent.getLevel());
/*  448 */       updateKeyValueInfo(playerSession, KeyValueConstant.PLAYER_LEVEL.getKey(), playerComponent.getLevel(), playerComponent.getTotalValue() + "");
/*      */       
/*  450 */       if (taskComponent != null) {
/*  451 */         taskComponent.refreshSchedule(TaskType.ReachLevel, 0, 0L);
/*      */       }
/*  453 */       RuneParameter runeParameter = (RuneParameter)ParameterConstant.getParameter(69);
/*  454 */       if (runeParameter.getLevelList().indexOf(Short.valueOf(playerComponent.getLevel())) >= 0) {
/*  455 */         RuneUtil.sysList(playerSession);
/*      */       }
/*  457 */       RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*  458 */       if (oldLevel < runewarParameter.getLevelLimit() && runewarParameter.getLevelLimit() <= playerComponent.getLevel()) {
/*  459 */         Fibers.createScheduler().execute(() -> RunewarUtil.uploadRunewarData(playerSession.getPlayer()));
/*      */       }
/*      */     } 
/*  462 */     updateKeyValueInfo(playerSession, KeyValueConstant.EXP.getKey(), playerComponent.getCurrency(CurrencyType.EXP));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setLevel(IPlayer iPlayer, int level) {
/*  467 */     if (level <= 0)
/*      */       return; 
/*  469 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*  470 */     if (level < playerComponent.getLevel())
/*      */       return; 
/*  472 */     int oldLevel = playerComponent.getLevel();
/*  473 */     int limitLevel = 200;
/*  474 */     for (Iterator<Integer> iterator = playerComponent.getReincarnationIds().iterator(); iterator.hasNext(); ) { int reinId = ((Integer)iterator.next()).intValue();
/*  475 */       FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reinId, FighterReincarnBean.class);
/*  476 */       if (null != fighterReincarnBean && fighterReincarnBean.getLevelLimit() > limitLevel) {
/*  477 */         limitLevel = fighterReincarnBean.getLevelLimit();
/*      */       } }
/*      */     
/*  480 */     if (level > limitLevel) {
/*      */       return;
/*      */     }
/*  483 */     while (oldLevel < level) {
/*  484 */       ExpLeaderBean expLeaderBean = (ExpLeaderBean)JsonTableService.getJsonData(playerComponent.getLevel(), ExpLeaderBean.class);
/*  485 */       addExp(iPlayer, expLeaderBean.getExp());
/*  486 */       oldLevel = playerComponent.getLevel();
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void addLevel(IPlayer iPlayer) {
/*  491 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*  492 */     playerComponent.setLevel((short)(playerComponent.getLevel() + 1));
/*      */     
/*  494 */     FunctionOpenConstant.checkFunctionOpen(iPlayer.getSession());
/*      */     
/*  496 */     TaskComponent taskComponent = (TaskComponent)iPlayer.createIfNotExist(TaskComponent.class);
/*  497 */     if (taskComponent != null) {
/*  498 */       taskComponent.refreshSchedule(TaskType.ReachLevel, 0, 0L);
/*      */     }
/*  500 */     updateKeyValueInfo(iPlayer.getSession(), KeyValueConstant.PLAYER_LEVEL.getKey(), playerComponent.getLevel());
/*  501 */     LogUtil.gameLog(LogType.LEVEL, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(iPlayer.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), Short.valueOf(playerComponent.getLevel()), TimeUtil.getFormatDate() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateKeyValueInfo(IPlayerSession playerSession, int key, long value) {
/*  512 */     updateKeyValueInfo(playerSession, key, value, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateKeyValueInfo(IPlayerSession playerSession, int key, long value, String valueStr) {
/*  524 */     KeyValue keyValue = new KeyValue();
/*  525 */     keyValue.key = key;
/*  526 */     keyValue.value = value;
/*  527 */     keyValue.valueStr = valueStr;
/*      */     
/*  529 */     ArrayList<KeyValue> keyValues = new ArrayList<>();
/*  530 */     keyValues.add(keyValue);
/*  531 */     updateKeyValueInfo(playerSession, keyValues);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateKeyValueInfo(IPlayerSession playerSession, ArrayList<KeyValue> keyValues) {
/*  541 */     if (null == playerSession) {
/*      */       return;
/*      */     }
/*  544 */     KeyValueUpdateResponse keyValueInfoUpdateResponse = new KeyValueUpdateResponse();
/*  545 */     keyValueInfoUpdateResponse.keyValueList = keyValues;
/*  546 */     playerSession.sendMessage((ResponseBase)keyValueInfoUpdateResponse);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateKeyValueInfo(Long playerId, int key, long value, String valueStr) {
/*  556 */     KeyValue keyValue = new KeyValue();
/*  557 */     keyValue.key = key;
/*  558 */     keyValue.value = value;
/*  559 */     keyValue.valueStr = valueStr;
/*      */     
/*  561 */     ArrayList<KeyValue> keyValues = new ArrayList<>();
/*  562 */     keyValues.add(keyValue);
/*  563 */     KeyValueUpdateResponse keyValueInfoUpdateResponse = new KeyValueUpdateResponse();
/*  564 */     keyValueInfoUpdateResponse.keyValueList = keyValues;
/*  565 */     LookUpService.sendMessage(playerId.longValue(), (ResponseBase)keyValueInfoUpdateResponse);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendRedNotice(Long playerId, RedNoticeConstant redNoticeConstant) {
/*  574 */     RedNoticeResponse response = new RedNoticeResponse();
/*  575 */     response.sys = redNoticeConstant.getSys();
/*  576 */     response.index = redNoticeConstant.getIndex();
/*  577 */     LookUpService.sendMessage(playerId.longValue(), (ResponseBase)response);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendRedNotice(Long playerId, int sys, int index) {
/*  588 */     RedNoticeResponse response = new RedNoticeResponse();
/*  589 */     response.sys = sys;
/*  590 */     response.index = index;
/*  591 */     LookUpService.sendMessage(playerId.longValue(), (ResponseBase)response);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendRedNotice(List<Long> playerIds, RedNoticeConstant redNoticeConstant) {
/*  601 */     RedNoticeResponse response = new RedNoticeResponse();
/*  602 */     response.sys = redNoticeConstant.getSys();
/*  603 */     response.index = redNoticeConstant.getIndex();
/*  604 */     for (Long playerId : playerIds) {
/*  605 */       LookUpService.sendMessage(playerId.longValue(), (ResponseBase)response);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendRedNotice(RedNoticeConstant redNoticeConstant) {
/*  615 */     RedNoticeResponse response = new RedNoticeResponse();
/*  616 */     response.sys = redNoticeConstant.getSys();
/*  617 */     response.index = redNoticeConstant.getIndex();
/*  618 */     LookUpService.broadcast((ResponseBase)response);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendKeyValue(int key, long value) {
/*  627 */     KeyValueUpdateResponse response = new KeyValueUpdateResponse();
/*  628 */     KeyValue keyValue = new KeyValue();
/*  629 */     keyValue.key = key;
/*  630 */     keyValue.value = value;
/*  631 */     keyValue.valueStr = "";
/*  632 */     response.keyValueList.add(keyValue);
/*  633 */     LookUpService.broadcast((ResponseBase)response);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getHeadUrl(long playerId) {
/*  655 */     String head = "";
/*      */     
/*  657 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.createIfNotExist(playerId, PlayerComponent.class);
/*  658 */     if (null != playerComponent) {
/*  659 */       if ("".equals(playerComponent.getHead())) {
/*  660 */         head = head + "sex:" + playerComponent.getSex();
/*      */       } else {
/*  662 */         head = head + playerComponent.getHead();
/*      */       } 
/*      */     } else {
/*  665 */       head = head + "sex:1";
/*      */     } 
/*  667 */     return head;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getRebotHeadUrl(String headurl, int sex) {
/*  678 */     String head = "";
/*  679 */     head = head + "frame=0&bubble=0&";
/*  680 */     if ("".equals(headurl)) {
/*  681 */       head = head + "head=sex:" + sex;
/*      */     } else {
/*  683 */       head = head + "head=" + headurl;
/*      */     } 
/*  685 */     return head;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getExtend(String... param) {
/*  695 */     String extend = "";
/*  696 */     for (int i = 1; i <= param.length; i++) {
/*  697 */       if (i % 2 == 0) {
/*  698 */         if (i == param.length) {
/*  699 */           extend = extend + param[i - 1];
/*      */         } else {
/*  701 */           extend = extend + param[i - 1] + ";";
/*      */         } 
/*      */       } else {
/*  704 */         extend = extend + param[i - 1] + ":";
/*      */       } 
/*      */     } 
/*  707 */     return extend;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void enterMainScene(IPlayerSession playerSession) {
/*  714 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*  715 */     if (null == playerComponent)
/*  716 */       return;  enterScene(playerSession, playerComponent.getMainSceneId());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void enterMainScene(Long playerId) {
/*  723 */     IPlayer iPlayer = LookUpService.getByPlayerId(playerId.longValue());
/*  724 */     if (null == iPlayer)
/*  725 */       return;  IPlayerSession playerSession = iPlayer.getSession();
/*  726 */     if (null == playerSession)
/*  727 */       return;  PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*  728 */     if (null == playerComponent)
/*  729 */       return;  enterScene(playerSession, playerComponent.getMainSceneId());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void enterScene(long playerId, int sceneId) {
/*  736 */     IPlayerSession playerSession = LookUpService.getByPlayerId(playerId).getSession();
/*  737 */     if (null == playerSession)
/*  738 */       return;  enterScene(playerSession, sceneId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void enterScene(IPlayerSession playerSession, int sceneId) {
/*  745 */     EnterSceneRequest request = new EnterSceneRequest();
/*  746 */     request.sceneId = sceneId;
/*  747 */     request.type = 1;
/*  748 */     if (null == playerSession) {
/*      */       return;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleOfflineIncome(IPlayerSession playerSession, int loginOutTime, int sceneId, ArrayList<Reward> rewards) {
/*  763 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*  764 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  765 */     int offlineSec = extendComponent.getOfflineSec();
/*  766 */     int tempSec = 0;
/*  767 */     if (0 != loginOutTime) {
/*  768 */       tempSec = TimeUtil.currentTime() - loginOutTime;
/*      */     }
/*  770 */     if (tempSec > 0) {
/*  771 */       offlineSec += tempSec;
/*      */     }
/*  773 */     int maxSec = VipUtil.getNum(playerComponent.getVip(), 14);
/*  774 */     if (maxSec == 0) {
/*  775 */       maxSec = 28800;
/*      */     }
/*  777 */     if (offlineSec > maxSec) {
/*  778 */       offlineSec = maxSec;
/*      */     }
/*  780 */     extendComponent.setOfflineSec(offlineSec);
/*  781 */     if (offlineSec >= 14400) {
/*  782 */       LogUtil.errorLog(new Object[] { "handleOfflineIncome", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(tempSec), Integer.valueOf(maxSec), Integer.valueOf(offlineSec), Integer.valueOf(TimeUtil.currentTime()), Integer.valueOf(loginOutTime) });
/*      */     }
/*  784 */     WelfareComponent welfareComponent = (WelfareComponent)playerSession.getPlayer().createIfNotExist(WelfareComponent.class);
/*  785 */     double monthCardRate = welfareComponent.getMonthCardRate();
/*      */     
/*  787 */     getOfflineRewards(sceneId, offlineSec, rewards, monthCardRate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void getOfflineRewards(int sceneId, int offlineSec, ArrayList<Reward> rewards, double monthCardRate) {
/*  799 */     if (offlineSec <= 0) {
/*      */       return;
/*      */     }
/*  802 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  803 */     int num = offlineSec / loginParameter.getOfflinePerSec();
/*  804 */     MapTableBean mapTableBean = (MapTableBean)JsonTableService.getJsonData(sceneId, MapTableBean.class);
/*  805 */     if (null != mapTableBean && mapTableBean.getMapIncome().size() > 0)
/*      */     {
/*  807 */       for (RewardBean rewardBean : mapTableBean.getMapIncome()) {
/*  808 */         Reward reward = new Reward();
/*  809 */         reward.type = (byte)rewardBean.getType();
/*  810 */         reward.id = rewardBean.getId();
/*  811 */         reward.num = rewardBean.getNum() * 1L * num;
/*  812 */         if (monthCardRate > 0.0D) {
/*  813 */           reward.num = (long)(reward.num + reward.num * monthCardRate);
/*      */         }
/*  815 */         rewards.add(reward);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendWorldLevel(IPlayerSession playerSession) {
/*  826 */     int worldLevel = RankingLevel.getWorldLevel();
/*  827 */     updateKeyValueInfo(playerSession, KeyValueConstant.WORLD_LEVEL.getKey(), worldLevel);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendWorldLevel() {
/*  835 */     List<Long> onlinePlayer = LookUpService.getOnlinePlayer();
/*  836 */     for (Long playerId : onlinePlayer) {
/*  837 */       IPlayer player = LookUpService.getByPlayerId(playerId.longValue());
/*  838 */       if (null != player && null != player.getSession()) {
/*  839 */         sendWorldLevel(player.getSession());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PlayerData tramformPlayerData(long playerId) {
/*  851 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/*  852 */     MountsComponent mountsComponent = (MountsComponent)LookUpService.getComponent(playerId, MountsComponent.class);
/*  853 */     PlayerData playerInfo = new PlayerData();
/*  854 */     playerInfo.blocName = GroupUtil.getGroupName(playerComponent.getPlayerId());
/*  855 */     playerInfo.fightValue = playerComponent.getTotalValue();
/*  856 */     playerInfo.level = playerComponent.getLevel();
/*  857 */     playerInfo.playerName = playerComponent.getPlayerName();
/*  858 */     playerInfo.head = playerComponent.getHead();
/*  859 */     playerInfo.vip = playerComponent.getVip();
/*  860 */     playerInfo.playerId = playerComponent.getPlayerId();
/*  861 */     playerInfo.quality = playerComponent.getQuality();
/*  862 */     playerInfo.title = playerComponent.getWearTitle();
/*  863 */     playerInfo.fashionId = playerComponent.getWearFashion();
/*  864 */     playerInfo.mounts = mountsComponent.getTurnMounts();
/*  865 */     playerInfo.sex = playerComponent.getSex();
/*  866 */     return playerInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updatePlayerFightValue(PlayerComponent playerComponent, boolean update) {
/*  875 */     long fightValue = playerComponent.getPlayerAttrUp().getFightValue(playerComponent.getLevel(), -1L);
/*  876 */     playerComponent.setFightValue(fightValue);
/*  877 */     updateTotalFightValue(playerComponent.getPlayerId(), update);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateTotalFightValue(long playerId, boolean update) {
/*  886 */     IPlayer player = LookUpService.getByPlayerId(playerId);
/*  887 */     if (null != player) {
/*  888 */       PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  889 */       PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  890 */       long value = 0L;
/*  891 */       for (Long id : playerComponent.getFighters()) {
/*  892 */         if (id.longValue() == -1L) {
/*  893 */           value += playerComponent.getFightValue(); continue;
/*      */         } 
/*  895 */         PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/*  896 */         if (null != partnerEntity) {
/*  897 */           value += partnerEntity.getFightValue();
/*      */         }
/*      */       } 
/*      */       
/*  901 */       if (value != playerComponent.getTotalValue()) {
/*  902 */         playerComponent.setTotalValue(value);
/*  903 */         RankActUtil.refreshRankValue(RankActType.PlayerFight2.getType(), playerComponent.getTotalValue(), playerComponent.getPlayerId());
/*  904 */         if (update) {
/*  905 */           updateKeyValueInfo(player.getSession(), KeyValueConstant.TOTAL_FIGHT_VALUE.getKey(), value);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendNotice(int type, IPlayer player, int targetId, String value) {
/*  917 */     ArrayList<String> list = new ArrayList<>();
/*  918 */     PlayerComponent playerComponent = null;
/*  919 */     if (null != player) {
/*  920 */       playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*      */     } else {
/*      */       return;
/*      */     } 
/*  924 */     int langId = -1;
/*  925 */     if (type == 1)
/*  926 */     { langId = 701;
/*  927 */       list.add(playerComponent.getPlayerName());
/*  928 */       list.add(playerComponent.getVip() + ""); }
/*  929 */     else if (type == 2)
/*  930 */     { langId = 702;
/*  931 */       LookUpService.radiate(langId, list); }
/*  932 */     else if (type == 3)
/*  933 */     { PetBean petBean = (PetBean)JsonTableService.getJsonData(targetId, PetBean.class);
/*  934 */       if (null != petBean) {
/*  935 */         if (petBean.getQuality() == 50) {
/*  936 */           langId = 703;
/*  937 */         } else if (petBean.getQuality() == 60) {
/*  938 */           langId = 704;
/*  939 */         } else if (petBean.getQuality() == 70) {
/*  940 */           langId = 705;
/*      */         } 
/*  942 */         list.add(playerComponent.getPlayerName());
/*  943 */         list.add(petBean.getName());
/*      */       }  }
/*  945 */     else if (type == 4)
/*  946 */     { MountBean mountBean = (MountBean)JsonTableService.getJsonData(targetId, MountBean.class);
/*  947 */       if (null != mountBean) {
/*  948 */         if (mountBean.getQuality() == 50) {
/*  949 */           langId = 706;
/*  950 */         } else if (mountBean.getQuality() == 60) {
/*  951 */           langId = 707;
/*  952 */         } else if (mountBean.getQuality() == 70) {
/*  953 */           langId = 708;
/*      */         } 
/*  955 */         list.add(playerComponent.getPlayerName());
/*  956 */         list.add(mountBean.getName());
/*      */       }  }
/*  958 */     else if (type == 5)
/*  959 */     { langId = 709;
/*  960 */       list.add(playerComponent.getPlayerName()); }
/*  961 */     else if (type == 6)
/*  962 */     { FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(targetId, FighterBean.class);
/*  963 */       if (null != fighterBean) {
/*  964 */         if (fighterBean.getQuality() == 40) {
/*  965 */           langId = 710;
/*  966 */         } else if (fighterBean.getQuality() == 50) {
/*  967 */           langId = 711;
/*  968 */         } else if (fighterBean.getQuality() == 60) {
/*  969 */           langId = 712;
/*  970 */         } else if (fighterBean.getQuality() == 70) {
/*  971 */           langId = 713;
/*  972 */         } else if (fighterBean.getQuality() == 80) {
/*  973 */           langId = 723;
/*      */         } 
/*  975 */         list.add(playerComponent.getPlayerName());
/*  976 */         list.add(fighterBean.getName());
/*      */       }  }
/*  978 */     else if (type == 7)
/*  979 */     { FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(targetId, FighterBean.class);
/*  980 */       if (null != fighterBean) {
/*  981 */         if (fighterBean.getQuality() == 40) {
/*  982 */           langId = 714;
/*  983 */         } else if (fighterBean.getQuality() == 50) {
/*  984 */           langId = 715;
/*  985 */         } else if (fighterBean.getQuality() == 60) {
/*  986 */           langId = 716;
/*  987 */         } else if (fighterBean.getQuality() == 70) {
/*  988 */           langId = 717;
/*  989 */         } else if (fighterBean.getQuality() == 80) {
/*  990 */           langId = 722;
/*      */         } 
/*  992 */         list.add(playerComponent.getPlayerName());
/*  993 */         list.add(fighterBean.getName());
/*      */       }  }
/*  995 */     else if (type == 8)
/*  996 */     { langId = 718;
/*  997 */       list.add(playerComponent.getPlayerName());
/*  998 */       list.add(targetId + ""); }
/*  999 */     else if (type == 9)
/* 1000 */     { langId = 109;
/* 1001 */       list.add(playerComponent.getPlayerName()); }
/* 1002 */     else if (type == 24)
/* 1003 */     { langId = 2401;
/* 1004 */       list.add(playerComponent.getPlayerName());
/* 1005 */       list.add(targetId + ""); }
/* 1006 */     else if (type == 10)
/* 1007 */     { langId = 801;
/* 1008 */       list.add(player.getPlayerName());
/* 1009 */       list.add(targetId + ""); }
/* 1010 */     else if (type == 11)
/* 1011 */     { FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(targetId, FighterBean.class);
/* 1012 */       if (null != fighterBean) {
/* 1013 */         if (fighterBean.getQuality() == 60) {
/* 1014 */           langId = 719;
/* 1015 */         } else if (fighterBean.getQuality() == 70) {
/* 1016 */           langId = 720;
/* 1017 */         } else if (fighterBean.getQuality() == 80) {
/* 1018 */           langId = 721;
/*      */         } 
/* 1020 */         list.add(playerComponent.getPlayerName());
/* 1021 */         list.add(fighterBean.getName());
/* 1022 */         list.add(value);
/*      */       }  }
/* 1024 */     else if (type == 12)
/* 1025 */     { langId = 5801;
/* 1026 */       list.add(player.getPlayerName());
/* 1027 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(targetId, ItemBean.class);
/* 1028 */       if (null != itemBean) {
/* 1029 */         list.add(itemBean.getName());
/* 1030 */         list.add(value + "");
/*      */       }  }
/* 1032 */     else { if (type == 13) {
/* 1033 */         langId = 309;
/* 1034 */         list.add(targetId + "");
/* 1035 */         LookUpService.alienRadiate(langId, list); return;
/*      */       } 
/* 1037 */       if (type == 2901 || type == 5703) {
/* 1038 */         langId = 2901;
/* 1039 */         list.add(playerComponent.getPlayerName());
/* 1040 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(targetId, ItemBean.class);
/* 1041 */         if (null != itemBean) {
/* 1042 */           list.add(itemBean.getName());
/*      */         }
/*      */       }  }
/*      */     
/* 1046 */     if (-1 != langId) {
/* 1047 */       LookUpService.radiate(langId, list);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRemotePlayer(long playerId) {
/* 1059 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getBattleNum(PlayerComponent playerComponent) {
/* 1069 */     int oldBattleNum = 0;
/* 1070 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 1071 */       if (pid != 0L) {
/* 1072 */         oldBattleNum++;
/*      */       } }
/*      */     
/* 1075 */     return oldBattleNum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void newWeiduFirstCharge(IPlayerSession playerSession, Reward reward, ChargeBean chargeBean, ExtendComponent extendComponent, Set<Integer> newFirstCharge) {
/* 1086 */     reward.num *= chargeBean.getIsFirstweiduan();
/* 1087 */     newFirstCharge.add(Integer.valueOf(chargeBean.getId()));
/* 1088 */     extendComponent.setNewFirstCharge(newFirstCharge);
/* 1089 */     int num = WelfareUtil.groupCharegeNum.incrementAndGet();
/* 1090 */     sendKeyValue(KeyValueConstant.GROUPCHARGE.getKey(), num);
/* 1091 */     sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.FirstCharge.getSys(), chargeBean.getId());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void newFirstCharges(IPlayerSession playerSession, Reward reward, ChargeBean chargeBean, ExtendComponent extendComponent, Set<Integer> newFirstCharge) {
/* 1104 */     reward.num *= chargeBean.getIsFirst();
/* 1105 */     newFirstCharge.add(Integer.valueOf(chargeBean.getId()));
/* 1106 */     extendComponent.setNewFirstCharge(newFirstCharge);
/* 1107 */     int num = WelfareUtil.groupCharegeNum.incrementAndGet();
/* 1108 */     sendKeyValue(KeyValueConstant.GROUPCHARGE.getKey(), num);
/* 1109 */     sendRedNotice(Long.valueOf(playerSession.getPlayer().getPlayerId()), RedNoticeConstant.FirstCharge.getSys(), chargeBean.getId());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getPlatformType() {
/* 1118 */     int type = 0;
/* 1119 */     if (MContext.getPlatform().equals("weixin")) {
/* 1120 */       type = 1;
/* 1121 */     } else if (MContext.getPlatform().equals("xinghui") || MContext.getPlatform().equals("micro")) {
/* 1122 */       type = 2;
/*      */     } 
/* 1124 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String changeHead(String head, int sex) {
/* 1134 */     String heads = "";
/* 1135 */     heads = heads + "sex:" + sex + ":" + head;
/* 1136 */     return heads;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resetEveryWeek(long playerId) {
/* 1146 */     SignComponent signComponent = (SignComponent)LookUpService.getComponent(playerId, SignComponent.class);
/* 1147 */     if (null != signComponent) {
/*      */       
/* 1149 */       signComponent.setWeekCharge(0L);
/* 1150 */       signComponent.setWeekReward(new ArrayList());
/* 1151 */       if (LookUpService.getByPlayerId(playerId) != null) {
/* 1152 */         IPlayerSession playerSession = LookUpService.getByPlayerId(playerId).getSession();
/* 1153 */         SignUtil.refreshShowLevel(playerSession.getPlayer());
/*      */       } 
/*      */     } 
/* 1156 */     MentalComponent mentalComponent = (MentalComponent)LookUpService.getComponent(playerId, MentalComponent.class);
/* 1157 */     if (null != mentalComponent) {
/* 1158 */       mentalComponent.setRewardIds(new HashSet());
/* 1159 */       mentalComponent.setPoint(0);
/* 1160 */       mentalComponent.setTimes(0);
/* 1161 */       mentalComponent.setRecords(new ArrayList());
/*      */     } 
/* 1163 */     ShopComponent shopComponent = (ShopComponent)LookUpService.getComponent(playerId, ShopComponent.class);
/* 1164 */     if (null != shopComponent) {
/* 1165 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/* 1166 */       if (null != shopEntity) {
/* 1167 */         shopComponent.resetToWeek(shopEntity);
/*      */       }
/* 1169 */       for (ShopUtil.ShopType shopType : ShopUtil.ShopType.values()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1175 */         shopEntity = shopComponent.getShopEntity(shopType.getType());
/* 1176 */         if (null != shopEntity) {
/* 1177 */           shopComponent.resetToWeek(shopEntity);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getActPlatform() {
/* 1189 */     int type = 0;
/* 1190 */     if (MContext.getPlatform().equals("weixin")) {
/* 1191 */       type = 1;
/* 1192 */     } else if (MContext.getPlatform().equals("xinghui") || MContext.getPlatform().equals("micro")) {
/* 1193 */       type = 2;
/* 1194 */     } else if (MContext.getPlatform().equals("awy")) {
/* 1195 */       type = 3;
/* 1196 */     } else if (MContext.getPlatform().equals("wanba")) {
/* 1197 */       type = 4;
/* 1198 */     } else if (MContext.getPlatform().equals("baidu")) {
/* 1199 */       type = 5;
/*      */     } 
/* 1201 */     return type;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean platformActIsOpen(int channelId) {
/* 1210 */     if (channelId == 0) {
/* 1211 */       return true;
/*      */     }
/* 1213 */     if (channelId == getActPlatform()) {
/* 1214 */       return true;
/*      */     }
/* 1216 */     return false;
/*      */   }
/*      */   
/*      */   public static void platformRebate(long bgpTotal, IPlayerSession playerSession) {
/* 1220 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 1221 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/* 1222 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*      */ 
/*      */     
/* 1225 */     int addActCharge = (int)(bgpTotal * loginParameter.getActRebate() / 10000.0D);
/* 1226 */     updateAllAct(playerSession, playerSession.getPlayer(), addActCharge, extendComponent, playerComponent, playerComponent.getVip(), 0);
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\player\PlayerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */