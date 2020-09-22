/*     */ package com.linlongyx.sanguo.webgame.processors.login;
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.Code;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.invitation.InvitationComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.sign.SignComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.common.reward.PlayerCurrency;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BagParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.net.handler.MD5;
/*     */ import com.linlongyx.sanguo.webgame.net.http.ask.PlayerEvent;
/*     */ import com.linlongyx.sanguo.webgame.net.http.ask.ServerStatusEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.sign.SignUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.OfflineNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.PlayerBaseService;
/*     */ import com.linlongyx.sanguo.webgame.util.HttpUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ 
/*     */ public class LoginUtil {
/*  58 */   private static AtomicInteger serverStatus = new AtomicInteger(0);
/*  59 */   private static AtomicInteger serverStatusTime = new AtomicInteger(0);
/*     */   
/*     */   private static final String WEIXIN_CHECKNAMEURL = "https://channel.rastargame.com/h5/v1/wx/common/MsgSecCheck/_do";
/*     */   
/*     */   private static final String APP_KEY = "lHUKpbnSwvkgrtf";
/*     */   
/*     */   public static final int OFFLINE_TYPE_REPLACE = 1;
/*     */ 
/*     */   
/*     */   public static int isServerStatus() {
/*  69 */     int now = TimeUtil.currentTime();
/*  70 */     if (!MContext.getDebug() && (
/*  71 */       serverStatusTime.get() == 0 || serverStatusTime.get() < now)) {
/*     */       
/*  73 */       ServerStatusEvent event = new ServerStatusEvent();
/*  74 */       Map<String, String> map = new HashMap<>();
/*  75 */       map.put("serverId", AppContext.getServerId());
/*  76 */       Map<String, Object> responseMap = event.request(map);
/*     */       
/*  78 */       serverStatusTime.set(now + 60);
/*  79 */       int retCode = Integer.parseInt(String.valueOf(responseMap.getOrDefault("code", Short.valueOf((short)10001))));
/*  80 */       if (retCode != 10001) {
/*  81 */         LogUtil.errorLog(new Object[] { "isServerStatus return from http: ", Integer.valueOf(retCode), map });
/*     */       } else {
/*  83 */         int status = Integer.parseInt(String.valueOf(responseMap.getOrDefault("serverStatus", Integer.valueOf(0))));
/*     */         
/*  85 */         serverStatus.set(status);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  90 */     return serverStatus.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short loginPreCheck(IPlayerSession playerSession, int white, int serverStatus) {
/*  99 */     if (!LookUpService.getRunning().get()) {
/* 100 */       return 10067;
/*     */     }
/*     */     
/* 103 */     int curServerStatus = isServerStatus();
/* 104 */     if ((curServerStatus == 4 || curServerStatus == 3) && white == 0) {
/* 105 */       return 10071;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short reconnectPlayer(IPlayerSession playerSession, long userId, String sign) {
/* 119 */     Player player = (Player)LookUpService.get(userId, MContext.getServerIdInt());
/* 120 */     if (null == player) {
/* 121 */       UserComponent userComponent = LookUpService.getUserComponent(userId);
/* 122 */       if (null == userComponent) {
/* 123 */         userComponent = new UserComponent(userId);
/* 124 */         userComponent.init();
/*     */       } 
/* 126 */       userComponent.getFromDB();
/* 127 */       if (!userComponent.isDBInit())
/* 128 */         return 10059; 
/* 129 */       if (!((UserEntity)userComponent.getEntity()).getToken().equals(sign)) {
/* 130 */         return 10060;
/*     */       }
/* 132 */       player = new Player(playerSession);
/* 133 */       player.setUserId(userId);
/* 134 */       player.addComponent((IComponent)userComponent);
/*     */       
/* 136 */       player.setSession(playerSession);
/* 137 */       playerSession.setPlayer((IPlayer)player);
/*     */     } else {
/* 139 */       UserComponent userComponent = (UserComponent)player.getComponent(UserComponent.class);
/* 140 */       if (null == userComponent) return 10059;
/*     */       
/* 142 */       if (!((UserEntity)userComponent.getEntity()).getToken().equals(sign)) {
/* 143 */         return 10060;
/*     */       }
/* 145 */       PlayerSession oldPlayerSession = (PlayerSession)player.getSession();
/* 146 */       player.setSession(playerSession);
/* 147 */       playerSession.setPlayer((IPlayer)player);
/* 148 */       if (null != oldPlayerSession) {
/* 149 */         loginReplace((IPlayerSession)oldPlayerSession);
/* 150 */         oldPlayerSession.setStatus(ISession.Status.RE_CONNECTING);
/* 151 */         oldPlayerSession.getTcpSender().close();
/*     */       } 
/*     */     } 
/* 154 */     playerSession.setStatus(ISession.Status.CONNECTED);
/* 155 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short checkNeedSign(Player player, String sign, long userId, long loginTime, int white) {
/* 162 */     boolean needSign = false;
/* 163 */     if (null == player) {
/* 164 */       needSign = true;
/*     */     } else {
/* 166 */       UserComponent userComponent = (UserComponent)player.getComponent(UserComponent.class);
/* 167 */       if (sign.isEmpty() || !userComponent.getToken().equals(sign)) {
/* 168 */         needSign = true;
/*     */       }
/*     */     } 
/* 171 */     if (needSign) {
/* 172 */       String _sign = Code.getInstance().getCode(userId + "" + loginTime + "" + white + "" + MContext.getSecretKey());
/* 173 */       if (!_sign.equals(sign)) {
/* 174 */         return 10030;
/*     */       }
/*     */     } 
/* 177 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initPlayer(IPlayerSession playerSession, Player player, String sign, long userId, ArrayList<PlayerInfo> players) {
/*     */     UserComponent userComponent;
/* 185 */     if (null == player) {
/* 186 */       userComponent = LookUpService.getUserComponent(userId);
/* 187 */       if (null == userComponent) {
/* 188 */         userComponent = new UserComponent(userId);
/* 189 */         userComponent.init();
/*     */       } 
/*     */       
/* 192 */       if (!userComponent.isDBInit()) {
/* 193 */         userComponent.initUser(userId, "", sign, 1);
/*     */       }
/* 195 */       userComponent.setToken(sign);
/* 196 */       userComponent.saveToDB();
/* 197 */       player = new Player(playerSession);
/* 198 */       player.setUserId(userId);
/* 199 */       player.addComponent((IComponent)userComponent);
/*     */       
/* 201 */       player.setSession(playerSession);
/* 202 */       playerSession.setPlayer((IPlayer)player);
/*     */     } else {
/* 204 */       userComponent = (UserComponent)player.getComponent(UserComponent.class);
/* 205 */       userComponent.setToken(sign);
/* 206 */       userComponent.saveToDB();
/* 207 */       PlayerSession oldPlayerSession = (PlayerSession)player.getSession();
/* 208 */       player.setSession(playerSession);
/* 209 */       playerSession.setPlayer((IPlayer)player);
/* 210 */       if (null != oldPlayerSession) {
/* 211 */         loginReplace((IPlayerSession)oldPlayerSession);
/* 212 */         oldPlayerSession.setStatus(ISession.Status.RE_CONNECTING);
/* 213 */         oldPlayerSession.getTcpSender().close();
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     userComponent.setToken(sign);
/* 218 */     userComponent.saveToDB();
/* 219 */     Set<PlayerInfo> playerList = ((UserEntity)userComponent.getEntity()).getPlayers();
/* 220 */     if (null != playerList) {
/* 221 */       players.addAll(playerList);
/*     */     }
/* 223 */     playerSession.setStatus(ISession.Status.CONNECTED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short checkPlayerNameAndSex(String playerName, byte sex) {
/* 230 */     short retcode = checkPlayerName(playerName);
/* 231 */     if (retcode != 0) {
/* 232 */       return retcode;
/*     */     }
/* 234 */     return checkSex(sex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short checkPlayerName(String playerName) {
/* 242 */     String reg = "[^(a-zA-Z0-9\\u4e00-\\u9fa5)]";
/* 243 */     String tempString = playerName.replaceAll(reg, "");
/* 244 */     if (!tempString.equals(playerName)) {
/* 245 */       return 10017;
/*     */     }
/*     */     
/* 248 */     if (playerName.isEmpty() || playerName.length() > 12) {
/* 249 */       return 10017;
/*     */     }
/*     */     
/* 252 */     if (!ChatUtil.isLegalName(playerName)) {
/* 253 */       return 10017;
/*     */     }
/*     */     
/* 256 */     if (PlayerUtil.getActPlatform() == 1 && MContext.getDebug() && 
/* 257 */       weixinCheckName(playerName) != 0) {
/* 258 */       return 10017;
/*     */     }
/*     */ 
/*     */     
/* 262 */     PlayerBaseService playerBaseService = (PlayerBaseService)AppContext.getBean("playerBaseService");
/* 263 */     if (playerBaseService.checkName(playerName)) {
/* 264 */       return 10016;
/*     */     }
/* 266 */     return 0;
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
/*     */   public static int weixinCheckName(String name) {
/* 283 */     int time = TimeUtil.currentTime();
/* 284 */     String signString = "app_id=" + MContext.getAppId() + "&cch_id=" + MContext.getCchId() + "&content=" + name + "&tm=" + time + "&" + "lHUKpbnSwvkgrtf";
/* 285 */     MD5 md = new MD5();
/* 286 */     String md5String = md.toDigest(signString);
/* 287 */     Map<String, Object> params_all = new TreeMap<>();
/* 288 */     params_all.put("app_id", MContext.getAppId());
/* 289 */     params_all.put("cch_id", MContext.getCchId());
/* 290 */     params_all.put("content", name);
/* 291 */     params_all.put("sign", md5String);
/* 292 */     params_all.put("tm", time + "");
/* 293 */     String urlEncode = urlEncode(params_all);
/*     */     
/* 295 */     String json = HttpUtil.http_post("https://channel.rastargame.com/h5/v1/wx/common/MsgSecCheck/_do?", urlEncode);
/* 296 */     Map maps = (Map)JSON.parse(json);
/* 297 */     if (maps.containsKey("code") && Integer.parseInt(maps.get("code").toString()) == 200)
/*     */     {
/* 299 */       return 0;
/*     */     }
/* 301 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String urlEncode(Map<String, Object> params) {
/* 312 */     StringBuilder sb = new StringBuilder();
/* 313 */     int size = params.size();
/* 314 */     int index = 0;
/* 315 */     for (Map.Entry<String, Object> i : params.entrySet()) {
/* 316 */       index++;
/* 317 */       sb.append(i.getKey()).append("=").append(i.getValue());
/* 318 */       if (index != size) {
/* 319 */         sb.append("&");
/*     */       }
/*     */     } 
/* 322 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static short checkSex(byte sex) {
/* 327 */     if (sex != 1 && sex != 2) {
/* 328 */       return 10023;
/*     */     }
/* 330 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short checkPlayerExist(PlayerComponent playerComponent, UserComponent userComponent) {
/* 338 */     if (null != playerComponent) {
/* 339 */       return 10013;
/*     */     }
/* 341 */     if (userComponent.isCreatingPlayer()) {
/* 342 */       return 10016;
/*     */     }
/* 344 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short createPlayer(IPlayerSession playerSession, long userId, String playerName, long playerId, byte sex, UserComponent userComponent, PlayerComponent playerComponent, IPlayer player, long aTime) {
/*     */     Map map1;
/* 352 */     userComponent.setCreatingPlayer(true);
/*     */     
/* 354 */     int ipInt = playerSession.getClientIp();
/* 355 */     long bTime = TimeUtil.currentTimeMillis();
/*     */     
/* 357 */     PlayerEvent event = new PlayerEvent();
/* 358 */     Map<String, String> map = new HashMap<>();
/* 359 */     map.put("userId", String.valueOf(userId));
/* 360 */     map.put("serverId", AppContext.getServerId());
/* 361 */     map.put("playerId", String.valueOf(playerId));
/* 362 */     map.put("playerName", playerName);
/* 363 */     map.put("sex", String.valueOf(sex));
/* 364 */     map.put("playerIp", String.valueOf(ipInt));
/* 365 */     int channel = 0;
/* 366 */     HashedMap hashedMap = new HashedMap();
/* 367 */     if (!MContext.getDebug()) {
/* 368 */       map1 = event.request(map);
/*     */       
/* 370 */       int retCode = Integer.parseInt(String.valueOf(map1.get("code")));
/* 371 */       if (retCode != 10001) {
/* 372 */         LogUtil.errorLog(new Object[] { "CreatePlayerProcess return from http: ", Integer.valueOf(retCode), Long.valueOf(playerId), map });
/* 373 */         userComponent.setCreatingPlayer(false);
/* 374 */         return 10031;
/*     */       } 
/* 376 */       int needSendCharge = 0;
/* 377 */       if (map1.containsKey("charge")) {
/* 378 */         needSendCharge = ((Integer)map1.get("charge")).intValue();
/*     */       }
/*     */       
/* 381 */       if (map1.containsKey("channel") && map1.get("channel") != null) {
/* 382 */         channel = ((Integer)map1.get("channel")).intValue();
/*     */       }
/* 384 */       userComponent.setChannel(channel);
/*     */     } 
/* 386 */     long cTime = TimeUtil.currentTimeMillis();
/*     */     
/* 388 */     userComponent.setCreatingPlayer(false);
/*     */     
/* 390 */     userComponent.addPlayer(playerId, playerName, sex);
/* 391 */     userComponent.saveAllToDB();
/*     */     
/* 393 */     player.setUserId(userId);
/* 394 */     player.setPlayerId(playerId);
/* 395 */     player.addComponent((IComponent)playerComponent);
/*     */     
/* 397 */     PlayerBaseService playerBaseService = (PlayerBaseService)AppContext.getBean("playerBaseService");
/* 398 */     playerBaseService.add(playerName, Long.valueOf(player.getPlayerId()));
/*     */     
/* 400 */     for (int i = playerComponent.getCurrencies().size(); i < (CurrencyType.values()).length; i++) {
/* 401 */       playerComponent.getCurrencies().add(Long.valueOf(0L));
/* 402 */       playerComponent.setCurrencies(playerComponent.getCurrencies());
/*     */     } 
/* 404 */     playerComponent.saveAllToDB();
/*     */     
/* 406 */     playerComponent.init();
/* 407 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*     */ 
/*     */     
/* 410 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/*     */ 
/*     */     
/* 413 */     SkillUtil.openSkill(player, playerComponent);
/* 414 */     String currentDate = TimeUtil.getFormatDate();
/* 415 */     playerComponent.setIp(ipInt);
/*     */     
/* 417 */     BagParameter bagParameter = (BagParameter)ParameterConstant.getParameter(7);
/* 418 */     extendComponent.setBagSize(bagParameter.getDefaultSize());
/* 419 */     extendComponent.setSkipTimes(loginParameter.getSkipTimes());
/* 420 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 421 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 422 */     bagComponent.addItem(loginParameter.getChangeNameItem(), 1, ResourceEvent.CreatePlayer);
/*     */     
/* 424 */     unparalleledComponent.getBattlePartner().addAll(playerComponent.getFighters());
/* 425 */     unparalleledComponent.setSweep(true);
/*     */     
/* 427 */     ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 428 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 429 */     arenaComponent.setRank(arenaParameter.getDefaultRank());
/*     */     
/* 431 */     SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)playerSession.getPlayer().createIfNotExist(SanGuoZhiComponent.class);
/* 432 */     Map<Integer, Object> starMap = JsonTableService.getJsonTable(RecordStarBean.class);
/* 433 */     for (Iterator<Integer> iterator = starMap.keySet().iterator(); iterator.hasNext(); ) { int recordStar = ((Integer)iterator.next()).intValue();
/* 434 */       RecordStarBean recordStarBean = (RecordStarBean)starMap.get(Integer.valueOf(recordStar));
/* 435 */       if (recordStarBean.getPreStar() == 0) {
/* 436 */         sanGuoZhiComponent.getEntity(recordStar);
/*     */       } }
/*     */ 
/*     */     
/* 440 */     SignComponent signComponent = (SignComponent)playerSession.getPlayer().createIfNotExist(SignComponent.class);
/* 441 */     if (signComponent.getShowLevel() == 0) {
/* 442 */       SignUtil.refreshShowLevel(playerSession.getPlayer());
/*     */     }
/*     */     
/* 445 */     long bgpTotal = 0L;
/*     */ 
/*     */ 
/*     */     
/* 449 */     if (map1.containsKey("bgpTotal")) {
/* 450 */       bgpTotal = ((Long)map1.get("bgpTotal")).longValue();
/*     */     }
/*     */     
/* 453 */     if (bgpTotal > 0L) {
/*     */       
/* 455 */       if (bgpTotal > (loginParameter.getRebateLimit() * 100)) {
/* 456 */         bgpTotal = (loginParameter.getRebateLimit() * 100);
/*     */       }
/* 458 */       long ccy = PlayerCurrency.get(playerComponent, CurrencyType.PlatformCharge);
/* 459 */       PlayerCurrency.set(playerComponent, CurrencyType.PlatformCharge, bgpTotal);
/* 460 */       LogUtils.errorLog(new Object[] { "PlayerBGP", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(ccy), Long.valueOf(bgpTotal), Long.valueOf(PlayerCurrency.get(playerComponent, CurrencyType.PlatformCharge)) });
/*     */     } 
/* 462 */     LogUtil.gameLog(LogType.PLAYER, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerId), Integer.valueOf(channel), 
/* 463 */           Integer.valueOf(AppContext.getDebug() ? 0 : 1), currentDate, currentDate, Integer.valueOf(playerComponent.getIp()), playerName, Byte.valueOf(sex) });
/* 464 */     LogUtil.gameLog(LogType.REMAIN, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(channel), Integer.valueOf(0), currentDate });
/* 465 */     long dTime = TimeUtil.currentTimeMillis();
/* 466 */     LogUtil.errorLog(new Object[] { "create player spend time:" + String.valueOf(dTime - aTime) + "," + String.valueOf(cTime - bTime), Long.valueOf(playerComponent.getPlayerId()), Long.valueOf(playerComponent.getTotalValue()), Long.valueOf(playerComponent.getFightValue()), Short.valueOf(playerComponent.getLevel()) });
/*     */     
/* 468 */     return 0;
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
/*     */   public static void loginReplace(IPlayerSession playerSession) {
/* 480 */     offlineNotice(playerSession, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void offlineNotice(IPlayerSession playerSession, int type) {
/* 490 */     OfflineNoticeResponse offlineNoticeResponse = new OfflineNoticeResponse();
/* 491 */     offlineNoticeResponse.type = type;
/* 492 */     if (null != playerSession) {
/* 493 */       playerSession.sendMessage((ResponseBase)offlineNoticeResponse);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateOrNotice(long playerId, WxPlayerInfo wxinfo1) {
/* 504 */     InvitationComponent invitationComponent = (InvitationComponent)LookUpService.getComponent(playerId, InvitationComponent.class);
/* 505 */     LogUtils.errorLog(new Object[] { "updateCurWxPlayerInfo", Long.valueOf(playerId), Long.valueOf(wxinfo1.playerId), Long.valueOf(wxinfo1.totalCharge), Integer.valueOf(wxinfo1.level) });
/* 506 */     if (null != invitationComponent) {
/* 507 */       updateInvitation(invitationComponent, wxinfo1, playerId);
/*     */     } else {
/*     */       
/* 510 */       int playerServerId = (int)(playerId / 1000000L);
/*     */       
/* 512 */       CrossUtil.updateCurWxPlayerInfo("updateCurWxPlayerInfo", playerServerId, wxinfo1, playerId);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void updateFromCross(WxPlayerInfo wxPlayerInfo, long playerId) {
/* 517 */     InvitationComponent invitationComponent = (InvitationComponent)LookUpService.getComponent(playerId, InvitationComponent.class);
/* 518 */     if (null == invitationComponent) {
/* 519 */       if (wxPlayerInfo != null) {
/* 520 */         LogUtils.errorLog(new Object[] { "updateFromCross", Long.valueOf(playerId), Long.valueOf(wxPlayerInfo.playerId), Long.valueOf(wxPlayerInfo.totalCharge), Integer.valueOf(wxPlayerInfo.level) });
/*     */       } else {
/* 522 */         LogUtils.errorLog(new Object[] { "updateFromCross", Long.valueOf(playerId) });
/*     */       } 
/*     */       return;
/*     */     } 
/* 526 */     updateInvitation(invitationComponent, wxPlayerInfo, playerId);
/*     */   }
/*     */   
/*     */   public static String updateHead(String str, int sex) {
/* 530 */     String[] strings = str.split(":");
/* 531 */     strings[1] = sex + "";
/* 532 */     StringBuilder stringBuilder = new StringBuilder();
/* 533 */     for (int i = 0; i < strings.length; i++) {
/* 534 */       if (i == strings.length - 1) {
/* 535 */         stringBuilder.append(strings[i]);
/*     */       } else {
/* 537 */         stringBuilder.append(strings[i]).append(":");
/*     */       } 
/*     */     } 
/* 540 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static void updateInvitation(InvitationComponent invitationComponent, WxPlayerInfo wxinfo1, long playerId) {
/* 544 */     boolean hash = false;
/* 545 */     for (WxPlayerInfo wxinfo : invitationComponent.getWxInfo()) {
/* 546 */       if (wxinfo.playerId == wxinfo1.playerId) {
/* 547 */         wxinfo.level = wxinfo1.level;
/* 548 */         wxinfo.totalCharge += wxinfo1.totalCharge;
/* 549 */         wxinfo.head = wxinfo1.head;
/* 550 */         wxinfo.name = wxinfo1.name;
/* 551 */         wxinfo.quality = wxinfo1.quality;
/* 552 */         wxinfo.playerId = wxinfo1.playerId;
/* 553 */         hash = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 557 */     if (!hash) {
/* 558 */       WxPlayerInfo info = new WxPlayerInfo();
/* 559 */       info.name = wxinfo1.name;
/* 560 */       info.head = wxinfo1.head;
/* 561 */       info.totalCharge = wxinfo1.totalCharge;
/* 562 */       info.level = wxinfo1.level;
/* 563 */       info.quality = wxinfo1.quality;
/* 564 */       info.playerId = wxinfo1.playerId;
/* 565 */       invitationComponent.getWxInfo().add(info);
/*     */     } 
/* 567 */     LogUtils.errorLog(new Object[] { "updateInvitation", Long.valueOf(invitationComponent.getPlayerId()), Long.valueOf(wxinfo1.playerId), Long.valueOf(wxinfo1.totalCharge), Integer.valueOf(wxinfo1.level) });
/* 568 */     invitationComponent.setWxInfo(invitationComponent.getWxInfo());
/* 569 */     invitationComponent.saveToDB();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\LoginUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */