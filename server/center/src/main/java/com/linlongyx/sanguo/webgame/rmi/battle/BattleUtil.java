/*     */ package com.linlongyx.sanguo.webgame.rmi.battle;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.core.type.TypeReference;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.CrossParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleCloseNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossBattleResourceNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossChatByChannelResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossRewardNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ChatInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattlePlayerData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.ServerData;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ 
/*     */ public class BattleUtil {
/*  45 */   public static String LAST_BATTLE_MAP_KEY = "last_battle_key";
/*  46 */   private static String BATTLE_REWARD_CACHE_KEY = "battle_reward_cache";
/*     */   
/*     */   public static final int BATTLE_CAMP_SIZE = 3;
/*     */   
/*     */   public static final int BATTLE_CAMP_SERVER_SIZE = 3;
/*     */   
/*     */   public static final int TYPE_REBORN_NOTICE = 0;
/*     */   public static final int TYPE_EXIT_NOTICE = 1;
/*     */   public static final int TYPE_JOIN_TEAM_NOTICE = 2;
/*     */   public static final int TYPE_LEAVE_TEAM_NOTICE = 3;
/*     */   public static final int TYPE_DISMISS_TEAM_NOTICE = 4;
/*     */   private static BattleMap battleMap;
/*     */   @JsonIgnore
/*  59 */   private static List<BattleRewardCache> battleRewardCacheList = new CopyOnWriteArrayList<>();
/*     */   
/*     */   public enum BattleState {
/*  62 */     PREPARE(0),
/*  63 */     OPEN(1),
/*  64 */     CLOSE(2);
/*     */     private int state;
/*     */     
/*     */     BattleState(int state) {
/*  68 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  72 */       return this.state;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void gmBattle(int state) {
/*  80 */     switch (state) {
/*     */       case 0:
/*  82 */         prepareBattle();
/*     */         break;
/*     */       case 1:
/*  85 */         startBattle();
/*     */         break;
/*     */       case 2:
/*  88 */         stopBattle();
/*     */         break;
/*     */       case 3:
/*  91 */         loadFromDb();
/*     */         break;
/*     */       case 4:
/*  94 */         saveToDb();
/*     */         break;
/*     */       case 5:
/*  97 */         shutdownBattle();
/*     */         break;
/*     */       case 6:
/* 100 */         balanceBattle();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadBattleTask() {}
/*     */ 
/*     */   
/*     */   public static void saveToDb() {
/* 111 */     saveBattleRewardCacheToDb();
/* 112 */     if (battleMap != null && 
/* 113 */       !battleMap.getBattleMapKey().isEmpty()) {
/*     */       try {
/* 115 */         RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/*     */         
/* 117 */         redisClientTemplate.set(LAST_BATTLE_MAP_KEY, battleMap.getBattleMapKey());
/* 118 */         redisClientTemplate.set(battleMap.getBattleMapKey(), CrossUtil.objectMapper.writeValueAsString(battleMap));
/* 119 */         redisClientTemplate.expire(battleMap.getBattleMapKey(), 2592000);
/* 120 */       } catch (JsonProcessingException e) {
/* 121 */         e.printStackTrace();
/* 122 */         LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*     */       } 
/* 124 */       LogUtil.errorLog(new Object[] { "save battleMap", battleMap.getBattleMapKey() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadFromDb() {
/* 130 */     loadBattleRewardCacheFromDb();
/* 131 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 132 */     if (!redisClientTemplate.exists(LAST_BATTLE_MAP_KEY).booleanValue()) {
/* 133 */       LogUtil.errorLog(new Object[] { "last battle not exist" });
/*     */       return;
/*     */     } 
/* 136 */     String key = redisClientTemplate.get(LAST_BATTLE_MAP_KEY);
/* 137 */     if (!redisClientTemplate.exists(key).booleanValue()) {
/* 138 */       LogUtil.errorLog(new Object[] { "no battleMap with key ", key, "in reids create new one" });
/*     */       return;
/*     */     } 
/* 141 */     String battleMapJson = redisClientTemplate.get(key);
/* 142 */     if (battleMapJson == null) {
/* 143 */       LogUtil.errorLog(new Object[] { "load battleMap with key ", key, "from reids error" });
/*     */       return;
/*     */     } 
/*     */     try {
/* 147 */       battleMap = (BattleMap)CrossUtil.objectMapper.readValue(battleMapJson, BattleMap.class);
/* 148 */     } catch (IOException e) {
/* 149 */       e.printStackTrace();
/* 150 */       LogUtil.errorLog(new Object[] { "load battleMap ", key, "from redis error", e.getMessage() });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void saveBattleRewardCacheToDb() {
/* 155 */     if (battleRewardCacheList.isEmpty())
/*     */       return;  try {
/* 157 */       RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 158 */       String jsonStr = CrossUtil.objectMapper.writeValueAsString(battleRewardCacheList);
/* 159 */       redisClientTemplate.set(BATTLE_REWARD_CACHE_KEY, jsonStr);
/* 160 */     } catch (JsonProcessingException e) {
/* 161 */       e.printStackTrace();
/* 162 */       LogUtil.errorLog(new Object[] { "save battle reward cache to redis error, ", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void loadBattleRewardCacheFromDb() {
/* 167 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 168 */     if (!redisClientTemplate.exists(BATTLE_REWARD_CACHE_KEY).booleanValue())
/*     */       return;  try {
/* 170 */       String jsonStr = redisClientTemplate.get(BATTLE_REWARD_CACHE_KEY);
/* 171 */       battleRewardCacheList = (List<BattleRewardCache>)CrossUtil.objectMapper.readValue(jsonStr, new TypeReference<CopyOnWriteArrayList<BattleRewardCache>>() {  });
/* 172 */     } catch (IOException e) {
/* 173 */       e.printStackTrace();
/* 174 */       LogUtil.errorLog(new Object[] { "load battle reward cache from redis error", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void retrySendBattleReward() {
/* 179 */     Iterator<BattleRewardCache> it = battleRewardCacheList.iterator();
/* 180 */     while (it.hasNext()) {
/* 181 */       BattleRewardCache cache = it.next();
/* 182 */       if (cache.isCampRank()) {
/* 183 */         if (LogicRmiUtil.sendCrossCampRankReward("BattleUtil::retrySendBattleReward", cache.getServerId(), cache.getCamp(), cache.getRank(), cache.getPlayerList(), cache.getRewardList()))
/* 184 */           it.remove(); 
/*     */         continue;
/*     */       } 
/* 187 */       if (LogicRmiUtil.sendCrossPointRankReward("BattleUtil::retrySendBattleReward", cache.getServerId(), cache.getPlayerId(), cache.getRank(), cache.getRewardList())) {
/* 188 */         it.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addBattleRewardCache(BattleRewardCache cache) {
/* 195 */     battleRewardCacheList.add(cache);
/*     */   }
/*     */   
/*     */   public static void prepareBattle() {
/* 199 */     CrossParameter crossParameter = (CrossParameter)ParameterConstant.getParameter(81);
/* 200 */     if (battleMap != null) {
/* 201 */       saveToDb();
/*     */     }
/* 203 */     int curtime = TimeUtil.currentTime();
/* 204 */     int curYearMonthDay = TimeUtil.getCurrentYearMonthDay();
/* 205 */     List<Pair<Integer, Integer>> serverWorldLevelList = LogicRmiManager.getServerWorldLevel();
/* 206 */     Iterator<Pair<Integer, Integer>> iterator = serverWorldLevelList.listIterator();
/* 207 */     while (iterator.hasNext()) {
/* 208 */       Pair<Integer, Integer> next = iterator.next();
/* 209 */       if (LogicRmiManager.serverDataMap.containsKey(next.getKey())) {
/* 210 */         ServerData serverData = (ServerData)LogicRmiManager.serverDataMap.get(next.getKey());
/* 211 */         if (TimeUtil.getDayDiffToOpen(serverData.getOpentime(), curtime) < crossParameter.getDays()) {
/* 212 */           iterator.remove();
/*     */         }
/*     */       } 
/*     */     } 
/* 216 */     String battleMapKey = "cross_battle_" + curYearMonthDay;
/* 217 */     battleMap = new BattleMap(battleMapKey, serverWorldLevelList);
/* 218 */     battleMap.prepare();
/* 219 */     saveToDb();
/*     */   }
/*     */   
/*     */   public static void startBattle() {
/* 223 */     if (battleMap == null) {
/* 224 */       prepareBattle();
/*     */     }
/* 226 */     battleMap.open();
/* 227 */     saveToDb();
/*     */   }
/*     */   
/*     */   public static void stopBattle() {
/* 231 */     if (battleMap != null && (battleMap.getBattleState() == BattleState.PREPARE || battleMap
/* 232 */       .getBattleState() == BattleState.OPEN)) {
/* 233 */       battleMap.close();
/* 234 */       saveToDb();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void shutdownBattle() {
/* 239 */     if (battleMap != null && (battleMap.getBattleState() == BattleState.PREPARE || battleMap
/* 240 */       .getBattleState() == BattleState.OPEN)) {
/* 241 */       battleMap.shutdown();
/* 242 */       saveToDb();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void balanceBattle() {
/* 247 */     if (battleMap != null && battleMap.getBattleState() == BattleState.CLOSE) {
/* 248 */       for (Battle battle : battleMap.getBattlesMap().values()) {
/* 249 */         battle.balance();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static synchronized boolean joinBattle(int serverId, long playerId) {
/* 255 */     CrossParameter crossParameter = (CrossParameter)ParameterConstant.getParameter(81);
/* 256 */     ServerData serverData = (ServerData)LogicRmiManager.serverDataMap.get(Integer.valueOf(serverId));
/* 257 */     if (TimeUtil.getDayDiffToOpen(serverData.getOpentime(), TimeUtil.currentTime()) < crossParameter.getDays()) {
/* 258 */       return false;
/*     */     }
/* 260 */     Battle battle = null;
/* 261 */     BattleCamp chooseCamp = null;
/* 262 */     Collection<Battle> battleCollection = battleMap.getBattlesMap().values();
/* 263 */     for (Battle b : battleCollection) {
/* 264 */       if (b.getServerWorldLevelMap().containsKey(Integer.valueOf(serverId))) {
/* 265 */         battle = b;
/*     */         break;
/*     */       } 
/*     */     } 
/* 269 */     if (battle == null) {
/* 270 */       for (Battle b : battleCollection) {
/* 271 */         chooseCamp = b.chooseNotFullCamp();
/* 272 */         if (chooseCamp != null) {
/* 273 */           battle = b;
/*     */           break;
/*     */         } 
/*     */       } 
/* 277 */       int worldLevel = ((ServerData)LogicRmiManager.serverDataMap.get(Integer.valueOf(serverId))).getWorldLevel();
/* 278 */       if (chooseCamp != null) {
/* 279 */         if (chooseCamp.addPlayer(serverId, playerId)) {
/* 280 */           return battle.addBattlePlayer(serverId, worldLevel, playerId);
/*     */         }
/*     */       } else {
/* 283 */         List<Pair<Integer, Integer>> list = new ArrayList<>();
/* 284 */         Pair<Integer, Integer> pair = new Pair(Integer.valueOf(serverId), Integer.valueOf(worldLevel));
/* 285 */         list.add(pair);
/* 286 */         battle = new Battle(battleMap.getBattleMapKey() + "_" + battleMap.generateBattleId(), list);
/* 287 */         chooseCamp = battle.chooseNotFullCamp();
/* 288 */         chooseCamp.addPlayer(serverId, playerId);
/* 289 */         battle.addBattlePlayer(serverId, worldLevel, playerId);
/* 290 */         battleMap.addBattle(battle);
/* 291 */         return true;
/*     */       } 
/*     */     } else {
/* 294 */       return battle.addBattlePlayer(serverId, 0, playerId);
/*     */     } 
/* 296 */     return false;
/*     */   }
/*     */   
/*     */   public static Battle getCurBattle(long playerId) {
/* 300 */     if (battleMap != null) {
/* 301 */       Collection<Battle> battleCollection = battleMap.battleCollections();
/* 302 */       for (Battle battle : battleCollection) {
/* 303 */         if (battle.getBattlePlayers().containsKey(Long.valueOf(playerId))) {
/* 304 */           return battle;
/*     */         }
/*     */       } 
/*     */     } 
/* 308 */     return null;
/*     */   }
/*     */   
/*     */   public static CrossBattlePlayerData transformToCrossBattlePlayerData(BattlePlayer battlePlayer, PlayerData playerData, Team team) {
/* 312 */     CrossBattlePlayerData crossBattlePlayerData = new CrossBattlePlayerData();
/* 313 */     crossBattlePlayerData.playerId = playerData.getPlayerId();
/* 314 */     crossBattlePlayerData.name = playerData.getPlayerName();
/* 315 */     crossBattlePlayerData.head = playerData.getHead();
/* 316 */     crossBattlePlayerData.quality = playerData.getQuality();
/* 317 */     crossBattlePlayerData.level = playerData.getLevel();
/* 318 */     crossBattlePlayerData.fightValue = playerData.getTotalValue();
/* 319 */     long hp = 0L, maxHp = 0L;
/* 320 */     if (team != null) {
/* 321 */       TeamPlayer teamPlayer = team.getTeamPlayerById(battlePlayer.getPlayerId());
/* 322 */       for (TeamPlayerFighter fighter : teamPlayer.getFighters()) {
/* 323 */         if (fighter != null) {
/* 324 */           hp += fighter.getFighter().getHP();
/* 325 */           maxHp += fighter.getFighter().getMaxHp();
/*     */         } 
/*     */       } 
/*     */     } else {
/* 329 */       for (Map.Entry<Integer, CommonFighterData> kv : battlePlayer.getFighters().entrySet()) {
/* 330 */         if (((Integer)kv.getKey()).intValue() == 0)
/*     */           continue; 
/* 332 */         if (((Integer)kv.getKey()).intValue() == 10) {
/*     */           continue;
/*     */         }
/* 335 */         hp += ((CommonFighterData)kv.getValue()).getAttrs()[AttributeType.HP.getType()];
/* 336 */         maxHp += ((CommonFighterData)kv.getValue()).getBaseAttrs()[AttributeType.HP.getType()];
/*     */       } 
/*     */     } 
/*     */     
/* 340 */     crossBattlePlayerData.hp = hp;
/* 341 */     crossBattlePlayerData.maxHp = maxHp;
/* 342 */     return crossBattlePlayerData;
/*     */   }
/*     */   
/*     */   public static boolean checkPos(int[] src, int[] target) {
/* 346 */     if (src == null || target == null || src.length != target.length) {
/* 347 */       return false;
/*     */     }
/* 349 */     for (int i = 0; i < src.length; i++) {
/* 350 */       if (src[i] != target[i]) {
/* 351 */         return false;
/*     */       }
/*     */     } 
/* 354 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean checkPos(int[] src, int x, int y) {
/* 358 */     int[] target = { x, y };
/* 359 */     return checkPos(src, target);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void broadcastRebornNotice(Battle battle, CrossBattleRebornNoticeResponse resp) {
/* 364 */     for (Iterator<Long> iterator = battle.getBattlePlayers().keySet().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 365 */       if (LookUpService.isOnline(playerId)) {
/* 366 */         LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)resp);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static void broadcastBattleCloseNotice(Battle battle) {
/* 372 */     CrossBattleCloseNoticeResponse resp = new CrossBattleCloseNoticeResponse();
/* 373 */     for (Iterator<Long> iterator = battle.getBattlePlayers().keySet().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 374 */       if (LookUpService.isOnline(playerId)) {
/* 375 */         LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)resp);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static void broadcastResourceNotice(CrossBattleResourceNoticeResponse resp, Set<Long> targets) {
/* 381 */     for (Iterator<Long> iterator = targets.iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 382 */       if (LookUpService.isOnline(playerId)) {
/* 383 */         LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)resp);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static void broadcastResourceNotice(Battle battle, CrossBattleResourceNoticeResponse resp) {
/* 389 */     for (Iterator<Long> iterator = battle.getBattlePlayers().keySet().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 390 */       if (LookUpService.isOnline(playerId)) {
/* 391 */         LookUpService.getByPlayerId(playerId).getSession().sendMessage((ResponseBase)resp);
/*     */       } }
/*     */   
/*     */   }
/*     */   
/*     */   public static void sendBattleReward(long playerId, int type, ArrayList<Reward> rewardList) {
/* 397 */     Player player = (Player)LookUpService.getByPlayerId(playerId);
/* 398 */     if (player != null) {
/* 399 */       CrossRewardNoticeResponse resp = new CrossRewardNoticeResponse();
/* 400 */       resp.type = type;
/* 401 */       resp.rewards = rewardList;
/* 402 */       if (LookUpService.isOnline(playerId)) {
/* 403 */         int serverId = player.getPlayerData().getServerId();
/* 404 */         player.getSession().sendMessage((ResponseBase)resp);
/* 405 */         LogicRmiUtil.sendCrossReward("BattleUtil::sendBattleReward", serverId, playerId, type, rewardList);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void broadcastCampNotice(long playerId, ResponseBase resp) {
/* 411 */     Battle battle = getCurBattle(playerId);
/* 412 */     if (battle != null) {
/* 413 */       BattlePlayer battlePlayer = battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 414 */       battle.getBattlePlayers().values().stream().filter(p -> (p.getCampIndex() == battlePlayer.getCampIndex() && p.getPlayerId() != playerId)).forEach(p -> {
/*     */             IPlayer player = LookUpService.getByPlayerId(p.getPlayerId());
/*     */             if (player != null && player.getSession().isLogin()) {
/*     */               player.getSession().sendMessage(resp);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void broadcastResourceOwnNotice(Battle battle, int resourceId, int tag, String playerName) {
/* 425 */     StarcraftBean starcraftBean = (StarcraftBean)JsonTableService.getJsonData(resourceId, StarcraftBean.class);
/* 426 */     if (starcraftBean != null && starcraftBean.getBuildingLevel() >= 3) {
/* 427 */       String context = LanguageConstant.getAndReplaceLanguage(8104, new String[] { Battle.CampTag.getCampName(tag), playerName, starcraftBean
/* 428 */             .getName() });
/* 429 */       CrossChatByChannelResponse resp = new CrossChatByChannelResponse();
/* 430 */       ChatInfo chatInfo = new ChatInfo();
/* 431 */       chatInfo.type = 1;
/* 432 */       chatInfo.context = context;
/* 433 */       chatInfo.time = TimeUtil.currentTime();
/* 434 */       resp.list.add(chatInfo);
/* 435 */       battle.getBattlePlayers().values().forEach(p -> {
/*     */             IPlayer player = LookUpService.getByPlayerId(p.getPlayerId());
/*     */             if (player != null && player.getSession().isLogin()) {
/*     */               player.getSession().sendMessage((ResponseBase)resp);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendBeHitNotice(int tag, String playerName, long targetPlayerId) {
/* 446 */     String context = LanguageConstant.getAndReplaceLanguage(8105, new String[] { Battle.CampTag.getCampName(tag), playerName });
/*     */     
/* 448 */     CrossChatByChannelResponse resp = new CrossChatByChannelResponse();
/* 449 */     ChatInfo chatInfo = new ChatInfo();
/* 450 */     chatInfo.type = 1;
/* 451 */     chatInfo.context = context;
/* 452 */     chatInfo.time = TimeUtil.currentTime();
/* 453 */     resp.list.add(chatInfo);
/* 454 */     Team team = TeamUtil.getTeamByPlayerId(targetPlayerId);
/* 455 */     if (team != null) {
/* 456 */       for (TeamPlayer tp : team.getTeamPlayers()) {
/* 457 */         if (tp != null && !tp.isRobot() && LookUpService.isOnline(tp.getPlayerId())) {
/* 458 */           LookUpService.getByPlayerId(tp.getPlayerId()).getSession().sendMessage((ResponseBase)resp);
/*     */         }
/*     */       }
/*     */     
/* 462 */     } else if (LookUpService.isOnline(targetPlayerId)) {
/* 463 */       LookUpService.getByPlayerId(targetPlayerId).getSession().sendMessage((ResponseBase)resp);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void battlePlayerOffline(long playerId) {
/* 469 */     Battle battle = getCurBattle(playerId);
/* 470 */     if (battle == null) {
/*     */       return;
/*     */     }
/* 473 */     BattlePlayer battlePlayer = battle.getBattlePlayers().get(Long.valueOf(playerId));
/* 474 */     if (battlePlayer == null) {
/*     */       return;
/*     */     }
/* 477 */     if (battlePlayer.getPlayerTeamStatus() == BattlePlayer.PlayerTeamStatus.TEAMLEADER) {
/* 478 */       battlePlayer.exitBattle(battle);
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
/*     */   public static BattleMap getBattleMap() {
/* 505 */     return battleMap;
/*     */   }
/*     */   
/*     */   public static void setBattleMap(BattleMap battleMap) {
/* 509 */     BattleUtil.battleMap = battleMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\BattleUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */