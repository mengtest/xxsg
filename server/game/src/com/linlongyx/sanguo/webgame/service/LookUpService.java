/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.mail.MailComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.mail.PubMailEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.BossHomeParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bagua.BaguaUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.friend.FriendUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rebate.ChargeRebateUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.chat.ChatRadiateResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.mental.PushLotteryRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rebate.PushServicRecordResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FriendInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MentalShowStruct;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.stream.Collectors;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class LookUpService {
/*  48 */   private static Logger LOG = LoggerFactory.getLogger(LookUpService.class);
/*  49 */   private static AtomicBoolean running = new AtomicBoolean(true);
/*     */   
/*  51 */   private static final Map<Long, HashSet<Long>> players = new ConcurrentHashMap<>();
/*     */   
/*  53 */   private static final Map<Long, IPlayer> playerIdToPlayer = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IPlayer get(long userId, int serverId) {
/*  62 */     if (!players.containsKey(Long.valueOf(userId))) {
/*  63 */       return null;
/*     */     }
/*  65 */     for (Long playerId : players.get(Long.valueOf(userId))) {
/*  66 */       if (serverId == 0 || serverId == VersionUtil.getServerIdByPlayerId(playerId.longValue())) {
/*  67 */         return playerIdToPlayer.get(playerId);
/*     */       }
/*     */     } 
/*  70 */     return null;
/*     */   }
/*     */   
/*     */   public static UserComponent getUserComponent(long userId) {
/*  74 */     if (!players.containsKey(Long.valueOf(userId))) {
/*  75 */       return null;
/*     */     }
/*  77 */     Iterator<Long> iterator = ((HashSet)players.get(Long.valueOf(userId))).iterator(); if (iterator.hasNext()) { Long playerId = iterator.next();
/*  78 */       return (UserComponent)((IPlayer)playerIdToPlayer.get(playerId)).getComponent(UserComponent.class); }
/*     */     
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IPlayer getByPlayerId(long playerId) {
/*  90 */     return playerIdToPlayer.get(Long.valueOf(playerId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void add(IPlayer player) {
/* 100 */     if (!players.containsKey(Long.valueOf(player.getUserId())))
/* 101 */       players.put(Long.valueOf(player.getUserId()), new HashSet<>()); 
/* 102 */     ((HashSet<Long>)players.get(Long.valueOf(player.getUserId()))).add(Long.valueOf(player.getPlayerId()));
/*     */     
/* 104 */     playerIdToPlayer.put(Long.valueOf(player.getPlayerId()), player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void remove(long userId, long playerId) {
/* 114 */     if (players.containsKey(Long.valueOf(userId))) {
/* 115 */       ((HashSet)players.get(Long.valueOf(userId))).remove(Long.valueOf(playerId));
/*     */     }
/* 117 */     playerIdToPlayer.remove(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public static void sendMessage(long playerId, ResponseBase response) {
/* 121 */     if (playerIdToPlayer.containsKey(Long.valueOf(playerId))) {
/* 122 */       IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/* 123 */       if (null != player && null != player.getSession())
/* 124 */         player.getSession().sendMessage(response); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void broadcast(ResponseBase response) {
/* 129 */     playerIdToPlayer.values().stream().filter(player -> (player.getSession() != null)).forEach(player -> player.getSession().sendMessage(response));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void campBoradcast(ResponseBase response, GroupEntity groupEntity) {
/* 138 */     groupEntity.getMemberList().forEach(playerId -> {
/*     */           IPlayer player = getByPlayerId(playerId.longValue());
/*     */           if (player != null && null != player.getSession()) {
/*     */             player.getSession().sendMessage(response);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void radiate(int languageId, ArrayList<String> list) {
/* 150 */     ChatRadiateResponse response = new ChatRadiateResponse();
/* 151 */     response.languageId = languageId;
/* 152 */     response.list = list;
/* 153 */     playerIdToPlayer.entrySet().stream().filter(entry -> (((IPlayer)entry.getValue()).getSession() != null)).forEach(entry -> ((IPlayer)entry.getValue()).getSession().sendMessage((ResponseBase)response));
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
/*     */   public static void alienRadiate(int languageId, ArrayList<String> list) {
/* 168 */     BossHomeParameter bossHomeParameter = (BossHomeParameter)ParameterConstant.getParameter(3);
/* 169 */     ChatRadiateResponse response = new ChatRadiateResponse();
/* 170 */     response.languageId = languageId;
/* 171 */     response.list = list;
/* 172 */     playerIdToPlayer.values().forEach(iPlayer -> {
/*     */           PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*     */           if (playerComponent.getLevel() >= bossHomeParameter.getAlienBossLimit()) {
/*     */             iPlayer.getSession().sendMessage((ResponseBase)response);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void pushServiceNotice() {
/* 185 */     PushServicRecordResponse response = new PushServicRecordResponse();
/* 186 */     response.serviceRecords = ChargeRebateUtil.getRecordList();
/* 187 */     playerIdToPlayer.entrySet().stream().filter(entry -> (((IPlayer)entry.getValue()).getSession() != null)).forEach(entry -> ((IPlayer)entry.getValue()).getSession().sendMessage((ResponseBase)response));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void pushMentalNotice(ArrayList<MentalShowStruct> items) {
/* 194 */     PushLotteryRecordResponse response = new PushLotteryRecordResponse();
/* 195 */     response.items = items;
/* 196 */     playerIdToPlayer.entrySet().stream().filter(entry -> (((IPlayer)entry.getValue()).getSession() != null)).forEach(entry -> ((IPlayer)entry.getValue()).getSession().sendMessage((ResponseBase)response));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void shutdown() {
/* 205 */     LogUtil.errorLog(new Object[] { "LookUpService::shutdown", TimeUtil.getFormatDate() });
/* 206 */     playerIdToPlayer.values().forEach(IPlayer::saveAll);
/* 207 */     playerIdToPlayer.clear();
/* 208 */     players.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reset() {
/* 219 */     playerIdToPlayer.values().forEach(player -> reset(player.getSession()));
/*     */     
/* 221 */     LogUtil.errorLog(new Object[] { "reset", TimeUtil.getFormatDate() });
/*     */   }
/*     */   
/*     */   public static void reset(IPlayerSession playerSession) {
/* 225 */     IPlayer iPlayer = playerSession.getPlayer();
/* 226 */     ExtendComponent extendComponent = (ExtendComponent)iPlayer.createIfNotExist(ExtendComponent.class);
/* 227 */     if (iPlayer.getSession() == null || !iPlayer.getSession().isLogin()) {
/*     */       return;
/*     */     }
/* 230 */     int date = TimeUtil.getCurrentYearMonthDay();
/* 231 */     if (date != extendComponent.getZeroResetDate()) {
/*     */       
/* 233 */       extendComponent.setZeroResetDate(date);
/* 234 */       playerSession.getPlayer().reset(0);
/*     */ 
/*     */ 
/*     */       
/* 238 */       PlayerUtil.updateKeyValueInfo(playerSession, KeyValueConstant.RESET_DATE.getKey(), 0L);
/*     */     } 
/*     */ 
/*     */     
/* 242 */     int curtime = TimeUtil.currentTime();
/* 243 */     if (extendComponent.getWeekResetTime() == 0) {
/* 244 */       extendComponent.setWeekResetTime(TimeUtil.getTimeStampZero(TimeUtil.getPrevTargetWeekDay(2)));
/*     */     }
/* 246 */     else if (curtime - extendComponent.getWeekResetTime() + 60 >= 604800) {
/* 247 */       extendComponent.setWeekResetTime(TimeUtil.getTimeStampZero(TimeUtil.getPrevTargetWeekDay(2)));
/* 248 */       playerSession.getPlayer().reset(50);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reset(int time) {
/* 254 */     playerIdToPlayer.values().forEach(player -> player.reset(time));
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
/*     */   public static boolean setPlayerStatus(long playerId, int type, int originTime, int destinationTime) {
/* 266 */     PlayerComponent playerComponent = getPlayerComponent(playerId);
/* 267 */     if (null == playerComponent)
/* 268 */       return false; 
/* 269 */     playerComponent.setPlayerStatus(type, originTime, destinationTime);
/* 270 */     playerComponent.maybeSaveToDB();
/* 271 */     CrossUtil.updatePlayerStatus("LookupService::setPlayerStatus", playerId, (HashMap)playerComponent.getStatus());
/* 272 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean clearPlayerStatus(long playerId, int type) {
/* 276 */     PlayerComponent playerComponent = getPlayerComponent(playerId);
/* 277 */     if (null == playerComponent)
/* 278 */       return false; 
/* 279 */     playerComponent.clearPlayerStatus(type);
/* 280 */     playerComponent.maybeSaveToDB();
/* 281 */     return true;
/*     */   }
/*     */   
/*     */   public static PlayerComponent getPlayerComponent(long playerId) {
/* 285 */     return (PlayerComponent)getComponent(playerId, (Class)PlayerComponent.class);
/*     */   }
/*     */   
/*     */   public static IComponent getComponent(long playerId, Class<? extends IComponent> clazz) {
/* 289 */     if (0L == playerId) {
/* 290 */       LogUtil.errorLog(new Object[] { "getComponent playerId == 0", clazz.getSimpleName() });
/* 291 */       return null;
/*     */     } 
/* 293 */     IComponent iComponent = null;
/* 294 */     IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/* 295 */     boolean isOnline = false;
/* 296 */     if (null != player) {
/* 297 */       isOnline = true;
/* 298 */       iComponent = player.createIfNotExist(clazz);
/*     */     } else {
/*     */       try {
/* 301 */         Constructor<? extends IComponent> c = clazz.getDeclaredConstructor(new Class[] { long.class });
/* 302 */         IComponent component = c.newInstance(new Object[] { Long.valueOf(playerId) });
/* 303 */         component.getFromDB();
/* 304 */         component.setNeedSaveToDB(true);
/* 305 */         if (!component.isDBInit()) {
/* 306 */           iComponent = null;
/*     */         } else {
/* 308 */           iComponent = component;
/*     */         } 
/* 310 */       } catch (NoSuchMethodException|IllegalAccessException|InstantiationException|java.lang.reflect.InvocationTargetException e) {
/* 311 */         LogUtil.errorLog(new Object[] { "getComponent", Arrays.toString((Object[])e.getStackTrace()) });
/* 312 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 315 */     if (null == iComponent) {
/* 316 */       LogUtil.errorLog(new Object[] { "getComponentNull", Long.valueOf(playerId), clazz.getSimpleName(), Boolean.valueOf(isOnline) });
/*     */     }
/* 318 */     return iComponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IComponent getOnlineComponent(long playerId, Class<? extends IComponent> clazz) {
/* 328 */     IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/* 329 */     if (null != player) {
/* 330 */       return player.createIfNotExist(clazz);
/*     */     }
/* 332 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IComponent createIfNotExist(long playerId, Class<? extends IComponent> clazz) {
/* 340 */     IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/* 341 */     if (null != player) {
/* 342 */       return player.createIfNotExist(clazz);
/*     */     }
/*     */     try {
/* 345 */       Constructor<? extends IComponent> c = clazz.getDeclaredConstructor(new Class[] { long.class });
/* 346 */       IComponent component = c.newInstance(new Object[] { Long.valueOf(playerId) });
/* 347 */       component.init();
/* 348 */       component.setNeedSaveToDB(true);
/* 349 */       return component;
/* 350 */     } catch (NoSuchMethodException|IllegalAccessException|InstantiationException|java.lang.reflect.InvocationTargetException e) {
/* 351 */       e.printStackTrace();
/* 352 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void OnlinePubMail(PubMailEntity pubMailEntity) {
/* 362 */     playerIdToPlayer.values().stream().filter(player -> MailUtil.checkPubMail(player, pubMailEntity.getRules()))
/* 363 */       .forEach(player -> sendMailToOnlinePlayer(player, pubMailEntity));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendMailToOnlinePlayer(IPlayer iPlayer, PubMailEntity pubMailEntity) {
/* 372 */     if (null == iPlayer || null == iPlayer.getSession()) {
/*     */       return;
/*     */     }
/* 375 */     PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/* 376 */     playerComponent.setPubIdCount(pubMailEntity.getId());
/* 377 */     MailComponent mailComponent = (MailComponent)iPlayer.createIfNotExist(MailComponent.class);
/* 378 */     MailUtil.sendPubMailToPlayer(playerComponent, mailComponent, pubMailEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getOnlineNum() {
/* 387 */     return (int)playerIdToPlayer.values().stream().filter(player -> player.getSession().isLogin()).count();
/*     */   }
/*     */   
/*     */   public static List<FriendInfo> recommendFriend(int needSize, int biasLevel) {
/* 391 */     List<FriendInfo> friendInfoList = new ArrayList<>();
/* 392 */     for (Map.Entry<Long, IPlayer> entry : playerIdToPlayer.entrySet()) {
/* 393 */       if (friendInfoList.size() >= needSize) {
/*     */         break;
/*     */       }
/* 396 */       PlayerComponent targetComponent = getPlayerComponent(((Long)entry.getKey()).longValue());
/* 397 */       if (null != targetComponent && targetComponent.getLevel() >= biasLevel) {
/* 398 */         friendInfoList.add(FriendUtil.buildFriendInfo(targetComponent));
/*     */       }
/*     */     } 
/* 401 */     return friendInfoList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Long> getOnlinePlayer() {
/* 410 */     return (List<Long>)playerIdToPlayer.values().stream().filter(player -> player.getSession().isLogin()).map(IPlayer::getPlayerId).collect(Collectors.toList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isOnline(long playerId) {
/* 419 */     if (!playerIdToPlayer.containsKey(Long.valueOf(playerId)))
/* 420 */       return false; 
/* 421 */     IPlayer player = playerIdToPlayer.get(Long.valueOf(playerId));
/* 422 */     return player.getSession().isLogin();
/*     */   }
/*     */   
/*     */   public static void logout(IPlayer player) {
/* 426 */     if (null == player)
/*     */       return; 
/* 428 */     player.logout(false);
/* 429 */     player.getSession().setLogin(false);
/* 430 */     player.getSession().getTcpSender().close();
/*     */   }
/*     */   
/*     */   public static void beUserLogout(long userId) {
/* 434 */     System.out.println("beUserLogout");
/* 435 */     if (players.containsKey(Long.valueOf(userId))) {
/* 436 */       for (Long playerId : players.get(Long.valueOf(userId))) {
/* 437 */         logout(playerIdToPlayer.get(playerId));
/* 438 */         remove(userId, playerId.longValue());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IPlayer forceGetPlayer(long playerId) {
/*     */     Player player;
/* 449 */     IPlayer iPlayer = getByPlayerId(playerId);
/*     */     
/* 451 */     if (iPlayer == null) {
/* 452 */       PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/* 453 */       builder.validateAndSetValues();
/* 454 */       IPlayerSession playerSession = (IPlayerSession)builder.status(ISession.Status.CLOSED).isLogin(false).writeable(false).build();
/* 455 */       player = new Player(playerSession);
/* 456 */       player.setPlayerId(playerId);
/* 457 */       playerSession.setPlayer((IPlayer)player);
/*     */     } 
/* 459 */     return (IPlayer)player;
/*     */   }
/*     */   
/*     */   public static void bePlayerLogout(long playerId) {
/* 463 */     System.out.println("bePlayerLogout");
/* 464 */     if (playerIdToPlayer.containsKey(Long.valueOf(playerId))) {
/* 465 */       IPlayer iPlayer = playerIdToPlayer.get(Long.valueOf(playerId));
/* 466 */       long userId = iPlayer.getUserId();
/* 467 */       logout(iPlayer);
/* 468 */       remove(userId, playerId);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void bePlayerLogoutGM(long playerId) {
/* 473 */     System.out.println("bePlayerLogout");
/* 474 */     if (playerIdToPlayer.containsKey(Long.valueOf(playerId))) {
/* 475 */       IPlayer iPlayer = playerIdToPlayer.get(Long.valueOf(playerId));
/* 476 */       long userId = iPlayer.getUserId();
/* 477 */       logout(iPlayer);
/* 478 */       remove(userId, playerId);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, Integer> getOnlineNumByChannel() {
/* 488 */     Map<Integer, Integer> map = new HashMap<>();
/* 489 */     playerIdToPlayer.values().forEach(iPlayer -> {
/*     */           PlayerComponent playerComponent = (PlayerComponent)iPlayer.createIfNotExist(PlayerComponent.class);
/*     */           int channel = playerComponent.getChannel();
/*     */           map.putIfAbsent(Integer.valueOf(channel), Integer.valueOf(0));
/*     */           map.put(Integer.valueOf(channel), Integer.valueOf(((Integer)map.get(Integer.valueOf(channel))).intValue() + 1));
/*     */         });
/* 495 */     return map;
/*     */   }
/*     */   
/*     */   public static void allLogout() {
/* 499 */     System.out.println("allLogout");
/* 500 */     BaguaUtil.saveRecords();
/* 501 */     WorldBossUtil.executeOnShutdown();
/*     */     
/* 503 */     playerIdToPlayer.values().forEach(LookUpService::logout);
/*     */     
/* 505 */     ((DBIncrementService)MContext.getBean("dbIncrementService")).saveToDB();
/* 506 */     ConstantService.saveToDB();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void allLogoutForJmx() {
/* 511 */     allLogout();
/* 512 */     setRunning(new AtomicBoolean(false));
/*     */   }
/*     */   
/*     */   public static void runningTrue() {
/* 516 */     setRunning(new AtomicBoolean(true));
/*     */   }
/*     */   
/*     */   public static AtomicBoolean getRunning() {
/* 520 */     return running;
/*     */   }
/*     */   
/*     */   public static void setRunning(AtomicBoolean running) {
/* 524 */     LookUpService.running = running;
/*     */   }
/*     */   
/*     */   public static int onlineNum() {
/* 528 */     return playerIdToPlayer.size();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\LookUpService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */