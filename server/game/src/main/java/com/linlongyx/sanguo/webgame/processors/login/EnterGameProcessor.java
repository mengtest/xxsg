/*     */ package com.linlongyx.sanguo.webgame.processors.login;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.MD5;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.recruit.RecruitComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecruitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.GameConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TabNotice;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bag.BagUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cardbook.CardBookUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.chat.ChatUtils;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRace.CrossRaceUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.friend.FriendLoginNoticeProcessor;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.recruit.RecruitUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rune.RuneUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.runewar.RunewarUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.timelimit.TimeLimitUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.tower.TowerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.welfare.WelfareUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.friend.FriendLoginNoticeRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.EnterGameResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JmxAgent;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class EnterGameProcessor extends ProcessorBase<EnterGameRequest, EnterGameResponse> {
/*     */   PlayerComponent playerComponent;
/*     */   
/*     */   protected void initResponse() {
/*  63 */     this.response = (ResponseBase)new EnterGameResponse();
/*     */   }
/*     */   boolean beforeLogin = false;
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EnterGameRequest request) {
/*  68 */     IPlayer player = playerSession.getPlayer();
/*  69 */     if (null == player) {
/*  70 */       return 10061;
/*     */     }
/*  72 */     LogUtil.errorLog(new Object[] { "EnterGameProcessor::handleRequest", "userId: " + player.getUserId() + " playerId: " + player
/*  73 */           .getPlayerId() + " request.playerId: " + request.playerId + " nanoTime: " + System.nanoTime() });
/*     */     
/*  75 */     UserComponent userComponent = (UserComponent)player.createIfNotExist(UserComponent.class);
/*  76 */     if (null == userComponent) {
/*  77 */       return 10019;
/*     */     }
/*     */     
/*  80 */     if (!userComponent.hasPlayer(request.playerId)) {
/*  81 */       return 10019;
/*     */     }
/*     */     
/*  84 */     this.playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/*  85 */     if (null == this.playerComponent) {
/*  86 */       this.playerComponent = new PlayerComponent(player.getUserId(), request.playerId);
/*  87 */       this.playerComponent.init();
/*  88 */       if (!this.playerComponent.isDBInit()) {
/*  89 */         return 10019;
/*     */       }
/*  91 */       player.addComponent((IComponent)this.playerComponent);
/*     */     } 
/*     */     
/*  94 */     if (this.playerComponent.isPrivilege(0)) {
/*  95 */       return 10020;
/*     */     }
/*     */     
/*  98 */     for (int i = this.playerComponent.getCurrencies().size(); i < (CurrencyType.values()).length; i++) {
/*  99 */       this.playerComponent.getCurrencies().add(Long.valueOf(0L));
/*     */     }
/*     */     
/* 102 */     player.setSession(playerSession);
/* 103 */     player.setPlayerId(request.playerId);
/* 104 */     player.setPlayerName(this.playerComponent.getPlayerName());
/*     */     
/* 106 */     IPlayer iPlayer = LookUpService.getByPlayerId(request.playerId);
/* 107 */     if (null != iPlayer) {
/* 108 */       iPlayer.enterLogout();
/*     */     }
/* 110 */     LookUpService.add(player);
/* 111 */     player.login();
/*     */     
/* 113 */     this.beforeLogin = playerSession.isLogin();
/*     */     
/* 115 */     playerSession.setLogin(true);
/* 116 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 117 */     if (taskComponent.getFixedStatus() == 0) {
/* 118 */       VersionUtil.CheckBattlePartner(playerSession.getPlayer());
/*     */     }
/* 120 */     this.playerComponent.getPlayerAttrUp().initAll(player, -1L);
/*     */     
/* 122 */     PartnerUtil.initPartner(player);
/*     */     
/* 124 */     this.playerComponent.setIp(playerSession.getClientIp());
/* 125 */     int loginOutTime = this.playerComponent.getLoginOutTime();
/* 126 */     int days = -TimeUtil.getDayDiff(loginOutTime);
/* 127 */     int creatDays = TimeUtil.getDayDiff(((PlayerEntity)this.playerComponent.getEntity()).getCreateTime());
/* 128 */     while (days > 1) {
/* 129 */       creatDays--;
/* 130 */       days--;
/* 131 */       if (GameConstant.GAME_REMAIN_DAYS.contains(Integer.valueOf(creatDays)))
/* 132 */         LogUtil.gameLog(LogType.REMAIN, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(this.playerComponent.getUserId()), Long.valueOf(this.playerComponent.getPlayerId()), Integer.valueOf(this.playerComponent.getChannel()), Integer.valueOf(creatDays), TimeUtil.getFormatDate() }); 
/*     */     } 
/* 134 */     this.playerComponent.setLoginTime(new Date());
/* 135 */     this.playerComponent.setLastOnlineTime(TimeUtil.currentTime());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     FunctionOpenConstant.checkFunctionOpen(playerSession);
/*     */ 
/*     */     
/* 144 */     DestinyUtil.uploadDestinyData(playerSession.getPlayer());
/*     */ 
/*     */     
/* 147 */     RunewarUtil.uploadRunewarData(playerSession.getPlayer());
/*     */ 
/*     */     
/* 150 */     TimeLimitUtil.resertLimitExchangeGoods(playerSession.getPlayer().getPlayerId());
/*     */     
/* 152 */     ((EnterGameResponse)this.response).sceneId = this.playerComponent.getMainSceneId();
/* 153 */     ((EnterGameResponse)this.response).playerId = player.getPlayerId();
/* 154 */     ((EnterGameResponse)this.response).playerName = player.getPlayerName();
/* 155 */     ((EnterGameResponse)this.response).timestamp = TimeUtil.currentTime();
/* 156 */     if (this.playerComponent.getQuality() == 999) {
/* 157 */       this.playerComponent.setQuality(60);
/*     */     }
/* 159 */     ((EnterGameResponse)this.response).quality = this.playerComponent.getQuality();
/* 160 */     ((EnterGameResponse)this.response).level = this.playerComponent.getLevel();
/* 161 */     ((EnterGameResponse)this.response).currencies = new ArrayList(this.playerComponent.getCurrencies());
/* 162 */     ((EnterGameResponse)this.response).head = PlayerUtil.getHeadUrl(player.getPlayerId());
/* 163 */     ((EnterGameResponse)this.response).sex = this.playerComponent.getSex();
/* 164 */     long total = this.playerComponent.getPlayerAttrUp().getAttrList(((EnterGameResponse)this.response).attributes, -1L);
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
/* 175 */     ((EnterGameResponse)this.response).checksum = (new MD5()).toDigest(playerSession.getKey() + total);
/* 176 */     ((EnterGameResponse)this.response).fightValue = this.playerComponent.getFightValue();
/* 177 */     ((EnterGameResponse)this.response).totalValue = this.playerComponent.getTotalValue();
/*     */     
/* 179 */     ((EnterGameResponse)this.response).openTime = MContext.getOpenTimeInt();
/* 180 */     ((EnterGameResponse)this.response).createTime = this.playerComponent.getCreateTime();
/* 181 */     ((EnterGameResponse)this.response).nickname = this.playerComponent.getPlayerName();
/* 182 */     ((EnterGameResponse)this.response).exp = this.playerComponent.getCurrency(CurrencyType.EXP);
/*     */     
/* 184 */     ExtendComponent extendComponent = (ExtendComponent)player.getComponent(ExtendComponent.class);
/* 185 */     MountsComponent mountsComponent = (MountsComponent)player.getComponent(MountsComponent.class);
/* 186 */     ((EnterGameResponse)this.response).functionIds = new ArrayList(extendComponent.getFunctionIds());
/* 187 */     ((EnterGameResponse)this.response).titleId = this.playerComponent.getWearTitle();
/* 188 */     ((EnterGameResponse)this.response).fashionId = this.playerComponent.getWearFashion();
/* 189 */     ((EnterGameResponse)this.response).mounts = mountsComponent.getTurnMounts();
/*     */     
/* 191 */     ((EnterGameResponse)this.response).extend = PlayerUtil.getExtend(new String[] { "taskId", taskComponent.getId() + "", "firstFail", taskComponent.getFirstFail() + "", "readName", (taskComponent.isRealName() == true) ? "1" : "0", "follow", taskComponent.getFollow() + "", "wxguide", (new StringBuilder())
/* 192 */           .append(taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(3), Integer.valueOf(0))).append("").toString(), "hefu", (MContext.isHeFu() == true) ? "1" : "0", "wxfollow", (new StringBuilder()).append(taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(4), Integer.valueOf(0))).append("").toString(), "awybind", (new StringBuilder())
/* 193 */           .append(taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(5), Integer.valueOf(0))).append("").toString(), "xinyue", (new StringBuilder()).append(taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(6), Integer.valueOf(0))).append("").toString(), "pcwb", (new StringBuilder()).append(taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(7), Integer.valueOf(0))).append("").toString(), "qqVIP", (new StringBuilder())
/* 194 */           .append(taskComponent.getPlatformReward().getOrDefault(Integer.valueOf(8), Integer.valueOf(0))).append("").toString() });
/* 195 */     RecruitComponent recruitComponent = (RecruitComponent)playerSession.getPlayer().createIfNotExist(RecruitComponent.class);
/* 196 */     if (recruitComponent.getTenCCYRecruit() == 0 && taskComponent.getId() <= 1 && !recruitComponent.getFirstGetList().isEmpty()) {
/* 197 */       for (KeyValue keyValue : recruitComponent.getFirstGetList()) {
/* 198 */         if (Integer.parseInt(keyValue.valueStr) == 4) {
/* 199 */           ((EnterGameResponse)this.response).partnerTableId = (int)keyValue.key;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 204 */     if (recruitComponent.getTenCCYRecruit() == 0 && taskComponent.getId() <= 1 && recruitComponent.getFirstGetList().isEmpty()) {
/* 205 */       RecruitBean.TimesBean libTimesBean = RecruitUtil.getLibBean(this.playerComponent.getLevel(), 0);
/* 206 */       ArrayList<Reward> rewardList = RecruitUtil.firstRecruitList(playerSession.getPlayer(), 0, libTimesBean, 1);
/* 207 */       for (Reward reward : rewardList) {
/* 208 */         if (reward.type == 4) {
/* 209 */           ((EnterGameResponse)this.response).partnerTableId = reward.id;
/*     */         }
/* 211 */         KeyValue keyValue = new KeyValue();
/* 212 */         keyValue.key = reward.id;
/* 213 */         keyValue.value = reward.num;
/* 214 */         keyValue.valueStr = reward.type + "";
/* 215 */         recruitComponent.getFirstGetList().add(keyValue);
/* 216 */         recruitComponent.setFirstGetList(recruitComponent.getFirstGetList());
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void process(IPlayerSession playerSession, EnterGameRequest request) throws Exception {
/* 225 */     short retCode = handleRequest(playerSession, request);
/* 226 */     ((EnterGameResponse)this.response).setRetCode(retCode);
/* 227 */     playerSession.sendMessage(this.response);
/*     */     
/* 229 */     if (0 != retCode)
/*     */       return; 
/* 231 */     LookUpService.reset(playerSession);
/*     */ 
/*     */     
/* 234 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 235 */     if (taskComponent != null) {
/* 236 */       taskComponent.refreshSchedule(TaskType.EnterGame, 0, 1L);
/* 237 */       taskComponent.refreshSchedule(TaskType.Login, 0, 1L);
/* 238 */       ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 239 */       taskComponent.refreshSchedule(TaskType.ArenaRank, 0, arenaParameter.getDefaultRank());
/*     */     } 
/* 241 */     if (this.playerComponent.getEquips().isEmpty()) {
/* 242 */       for (EquipPart equipPart : EquipPart.values()) {
/* 243 */         if (null == ((PlayerEntity)this.playerComponent.getEntity()).getEquips().get(Integer.valueOf(equipPart.getPart()))) {
/* 244 */           ((PlayerEntity)this.playerComponent.getEntity()).getEquips().put(Integer.valueOf(equipPart.getPart()), Long.valueOf(0L));
/*     */         }
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
/* 260 */     PlayerUtil.checkSameActivateCard(playerSession);
/*     */ 
/*     */     
/* 263 */     RechargeActivityUtil.balanceExpiredActivity(playerSession.getPlayer());
/*     */ 
/*     */     
/* 266 */     CrossRaceUtil.balancePrevRace(playerSession.getPlayer());
/*     */ 
/*     */     
/* 269 */     CrossRaceUtil.uploadPlayerRaceData(playerSession.getPlayer());
/*     */ 
/*     */     
/* 272 */     (new FriendLoginNoticeProcessor()).handle(playerSession, (RequestBase)new FriendLoginNoticeRequest());
/*     */     
/* 274 */     PlayerUtil.sendWorldLevel(playerSession);
/*     */     
/* 276 */     TaskUtil.updateSchedule(playerSession);
/*     */ 
/*     */     
/* 279 */     LimitUtil.updateLogin(playerSession.getPlayer());
/*     */     
/* 281 */     RankActUtil.balanceExpired(playerSession);
/* 282 */     int giftId = WelfareUtil.checkBackGift(playerSession);
/* 283 */     if (giftId != 0) {
/* 284 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.BACKGIFT.getKey(), giftId);
/*     */     }
/*     */     
/* 287 */     CrossRankActUtil.balanceExpireCrossRankAct(playerSession.getPlayer().getPlayerId());
/*     */     
/* 289 */     JmxAgent.addJmx(playerSession.getPlayer());
/*     */     
/* 291 */     ChatUtils.sendChatList(playerSession);
/* 292 */     VersionUtil.checkAndRepair(playerSession.getPlayer());
/* 293 */     VersionUtil.downNotBattleEquip(playerSession.getPlayer());
/*     */     
/* 295 */     BagUtil.petMountsRedNotice(playerSession, this.playerComponent);
/* 296 */     RuneUtil.sysList(playerSession);
/*     */     
/* 298 */     CardBookUtil.robtorResponse(playerSession.getPlayer().getPlayerId());
/*     */     
/* 300 */     UserComponent userComponent = (UserComponent)playerSession.getPlayer().createIfNotExist(UserComponent.class);
/* 301 */     if (userComponent.getOrderByPlayerId() != 0L) {
/* 302 */       WxPlayerInfo info = new WxPlayerInfo();
/* 303 */       info.level = this.playerComponent.getLevel();
/* 304 */       info.totalCharge = 0L;
/* 305 */       info.head = this.playerComponent.getHead();
/* 306 */       info.name = this.playerComponent.getPlayerName();
/* 307 */       info.quality = this.playerComponent.getQuality();
/* 308 */       info.playerId = this.playerComponent.getPlayerId();
/* 309 */       LoginUtil.updateOrNotice(userComponent.getOrderByPlayerId(), info);
/* 310 */       LogUtils.errorLog(new Object[] { "updateOrNotice", Long.valueOf(playerSession.getPlayer().getPlayerId()) });
/*     */     } 
/*     */ 
/*     */     
/* 314 */     TowerUtil.sendTowerOwnerTabNotice(this.playerComponent.getPlayerId(), true);
/* 315 */     TabNotice.sendSysOpenTabNotice(this.playerComponent.getPlayerId());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\EnterGameProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */