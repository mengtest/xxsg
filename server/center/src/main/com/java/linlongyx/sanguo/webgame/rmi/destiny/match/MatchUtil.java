/*     */ package linlongyx.sanguo.webgame.rmi.destiny.match;
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.core.type.TypeReference;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import linlongyx.sanguo.webgame.proto.binary.struct.DestinyBetRecord;
/*     */ import linlongyx.sanguo.webgame.quartz.job.match.MatchStepOneBalanceJob;
/*     */ import linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.BetData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyRankComparator;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import linlongyx.sanguo.webgame.rmi.destiny.DestinyCache;
/*     */ import linlongyx.sanguo.webgame.rmi.destiny.MatchCache;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.group.HalfMatchGroup;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.quartz.JobExecutionException;
/*     */ 
/*     */ public class MatchUtil {
/*  30 */   private static Map<Integer, Match> matchMap = new HashMap<>();
/*     */   
/*  32 */   private static Map<Integer, MatchCache> matchCacheMap = new HashMap<>();
/*     */   
/*  34 */   private static String MATCH_CACHE_KEY = "match_cache";
/*     */ 
/*     */ 
/*     */   
/*     */   public enum MatchRewardType
/*     */   {
/*  40 */     reward32(6),
/*  41 */     reward16(5),
/*  42 */     reward8(4),
/*  43 */     reward4(3),
/*  44 */     reward2(2),
/*  45 */     reward1(1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int type;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MatchRewardType(int type) {
/*  56 */       this.type = type;
/*     */     }
/*     */     
/*     */     public int getType() {
/*  60 */       return this.type;
/*     */     }
/*     */   }
/*     */   
/*     */   public static int bytesToInt(byte[] b) {
/*  65 */     return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] intToBytes(int a) {
/*  72 */     return new byte[] { (byte)(a >> 24 & 0xFF), (byte)(a >> 16 & 0xFF), (byte)(a >> 8 & 0xFF), (byte)(a & 0xFF) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saveToDb() {
/*  81 */     saveMatchToDb();
/*  82 */     saveMatchCacheToDb();
/*     */   }
/*     */   
/*     */   public static void loadFromDb() {
/*  86 */     loadRecentMatchFromDb();
/*  87 */     loadMatchCacheFromDb();
/*     */   }
/*     */   
/*     */   private static void saveMatchCacheToDb() {
/*     */     try {
/*  92 */       RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/*  93 */       String jsonStr = CrossUtil.objectMapper.writeValueAsString(matchCacheMap);
/*  94 */       redisClientTemplate.set(MATCH_CACHE_KEY, jsonStr);
/*  95 */     } catch (JsonProcessingException e) {
/*  96 */       e.printStackTrace();
/*  97 */       LogUtil.errorLog(new Object[] { "save match cache to redis error, ", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void loadMatchCacheFromDb() {
/* 102 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 103 */     if (!redisClientTemplate.exists(MATCH_CACHE_KEY).booleanValue())
/*     */       return;  try {
/* 105 */       String jsonStr = redisClientTemplate.get(MATCH_CACHE_KEY);
/* 106 */       matchCacheMap = (Map<Integer, MatchCache>)CrossUtil.objectMapper.readValue(jsonStr, new TypeReference<HashMap<Integer, MatchCache>>() {  });
/* 107 */     } catch (IOException e) {
/* 108 */       e.printStackTrace();
/* 109 */       LogUtil.errorLog(new Object[] { "load match cache from redis error", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Match loadMatchFromDb(int matchId) {
/* 114 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 115 */     byte[] keyBytes = intToBytes(matchId);
/* 116 */     if (!redisClientTemplate.exists(keyBytes).booleanValue()) {
/* 117 */       LogUtil.errorLog(new Object[] { "no match with id ", Integer.valueOf(matchId), "in reids create new one" });
/* 118 */       return new Match(matchId);
/*     */     } 
/* 120 */     byte[] bytes = redisClientTemplate.get(keyBytes);
/* 121 */     if (bytes == null) {
/* 122 */       LogUtil.errorLog(new Object[] { "load match with id ", Integer.valueOf(matchId), "from reids error, create new one" });
/* 123 */       return new Match(matchId);
/*     */     } 
/*     */     try {
/* 126 */       Match match = (Match)CrossUtil.objectMapper.readValue(bytes, Match.class);
/* 127 */       match.init();
/* 128 */       matchMap.put(Integer.valueOf(matchId), match);
/* 129 */       return match;
/* 130 */     } catch (IOException e) {
/* 131 */       e.printStackTrace();
/* 132 */       LogUtil.errorLog(new Object[] { "load match ", Integer.valueOf(matchId), "from redis error", e.getMessage() });
/*     */       
/* 134 */       return new Match(matchId);
/*     */     } 
/*     */   }
/*     */   private static void saveMatchToDb() {
/* 138 */     for (Match match : matchMap.values()) {
/* 139 */       match.saveToDb();
/*     */     }
/*     */   }
/*     */   
/*     */   private static Match loadRecentMatchFromDb() {
/* 144 */     int matchId = TimeUtil.getPrevTargetWeekDay(2);
/* 145 */     return loadMatchFromDb(matchId);
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
/*     */   public static boolean quarterMatchQuarterInit() {
/* 174 */     Match match = getCurMatch();
/* 175 */     match.getServerIdToZoneId().clear();
/* 176 */     match.getZoneDataMap().clear();
/*     */ 
/*     */     
/* 179 */     for (Map.Entry<Integer, Integer> kv : (Iterable<Map.Entry<Integer, Integer>>)DestinyCache.getDestinyMap().getServerIdToZoneId().entrySet()) {
/* 180 */       match.getServerIdToZoneId().putIfAbsent(kv.getKey(), kv.getValue());
/*     */     }
/*     */     
/* 183 */     for (Map.Entry<Integer, Map<Long, DestinyPlayerData>> kv : (Iterable<Map.Entry<Integer, Map<Long, DestinyPlayerData>>>)DestinyCache.getDestinyMap().getZoneToPlayerMap().entrySet()) {
/* 184 */       List<DestinyPlayerData> list = (List<DestinyPlayerData>)((Map)kv.getValue()).values().stream().filter(data -> (data.getDestinyPoint() > 0)).sorted((Comparator)new DestinyRankComparator()).limit(32L).collect(Collectors.toList());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 189 */       Zone zone = new Zone(match.getMatchId(), ((Integer)kv.getKey()).intValue());
/* 190 */       zone.zoneQuarterMatchQuarterInit(list);
/* 191 */       match.getZoneDataMap().put(kv.getKey(), zone);
/*     */     } 
/* 193 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void quarterMatchQuarterFight() {
/* 200 */     Match match = getCurMatch();
/*     */     
/* 202 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 203 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 204 */         zone.zoneQuarterMatchQuarterFight();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void quarterMatchHalfInit() {
/* 213 */     Match match = getCurMatch();
/*     */     
/* 215 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 216 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 217 */         zone.zoneQuarterMatchHalfInit();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void quarterMatchHalfFight() {
/* 226 */     Match match = getCurMatch();
/*     */     
/* 228 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 229 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 230 */         zone.zoneQuarterMatchHalfFight();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void quarterMatchFinalInit() {
/* 239 */     Match match = getCurMatch();
/*     */     
/* 241 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 242 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 243 */         zone.zoneQuarterMatchFinalInit();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void quarterMatchFinalFight() {
/* 252 */     Match match = getCurMatch();
/*     */     
/* 254 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 255 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 256 */         zone.zoneQuarterMatchFinalFight();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void halfMatchInit() {
/* 265 */     Match match = getCurMatch();
/*     */     
/* 267 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 268 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 269 */         zone.zoneHalfMatchHalfInit();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void halfMatchFight() {
/* 278 */     Match match = getCurMatch();
/*     */     
/* 280 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 281 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 282 */         zone.zoneHalfMatchHalfFight();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void finalMatchInit() {
/* 291 */     Match match = getCurMatch();
/*     */     
/* 293 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 294 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 295 */         zone.zoneHalfMatchFinalInit();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void finalMatchFight() {
/* 304 */     Match match = getCurMatch();
/*     */     
/* 306 */     for (Zone zone : match.getZoneDataMap().values()) {
/* 307 */       if (zone.getZoneState() != Zone.ZoneState.empty) {
/* 308 */         zone.zoneHalfMatchFinalFight();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCurMatchId() {
/* 320 */     return TimeUtil.getPrevTargetWeekDay(2);
/*     */   }
/*     */   
/*     */   public static MatchCache getCurMatchCache() {
/* 324 */     int curMatchId = getCurMatchId();
/* 325 */     matchCacheMap.putIfAbsent(Integer.valueOf(curMatchId), new MatchCache());
/* 326 */     return matchCacheMap.get(Integer.valueOf(curMatchId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Match getByMatchId(int matchId) {
/* 336 */     RedisClientTemplate redisClientTemplate = (RedisClientTemplate)MContext.getBean("redisClientTemplate", RedisClientTemplate.class);
/* 337 */     if (!matchMap.containsKey(Integer.valueOf(matchId))) {
/*     */       Match match;
/* 339 */       if (redisClientTemplate.exists(intToBytes(matchId)).booleanValue()) {
/* 340 */         match = loadMatchFromDb(matchId);
/* 341 */         matchMap.put(Integer.valueOf(matchId), match);
/*     */       } else {
/* 343 */         match = new Match(matchId);
/* 344 */         match.saveToDb();
/* 345 */         matchMap.put(Integer.valueOf(matchId), match);
/*     */       } 
/* 347 */       return match;
/*     */     } 
/* 349 */     return matchMap.get(Integer.valueOf(matchId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Match getCurMatch() {
/* 358 */     int matchId = getCurMatchId();
/* 359 */     return getByMatchId(matchId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Zone getZoneById(int zoneId) {
/* 369 */     return getCurMatch().getZoneDataMap().get(Integer.valueOf(zoneId));
/*     */   }
/*     */   
/*     */   public static boolean hasMatch(int serverId, long playerId) {
/* 373 */     Match curMatch = getCurMatch();
/* 374 */     if (!curMatch.getServerIdToZoneId().containsKey(Integer.valueOf(serverId))) {
/* 375 */       return false;
/*     */     }
/* 377 */     return curMatch.hasMatch(((Integer)curMatch.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue(), playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Zone getZoneByServerId(int serverId) {
/* 388 */     Match curMatch = getCurMatch();
/* 389 */     return curMatch.getZoneDataMap().getOrDefault(curMatch.getServerIdToZoneId().getOrDefault(Integer.valueOf(serverId), Integer.valueOf(-1)), null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PkRecord getPkRecord(int zoneId, String pkId) {
/* 396 */     return getZoneById(zoneId).getPkRecord(pkId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Match newMatch() {
/* 403 */     int nextMatchId = TimeUtil.getNextTargetWeekDay(2);
/* 404 */     Match match = new Match(nextMatchId);
/* 405 */     matchMap.put(Integer.valueOf(nextMatchId), match);
/* 406 */     return match;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PkDataItem transformPkData(PkData pkData) {
/* 411 */     PkDataItem pkDataItem = new PkDataItem();
/* 412 */     if (pkData != null) {
/* 413 */       pkDataItem.setId(pkData.getId());
/* 414 */       pkDataItem.setLeftPlayer(pkData.getLeftPlayer());
/* 415 */       pkDataItem.setRightPlayer(pkData.getRightPlayer());
/* 416 */       pkDataItem.setWinner(pkData.getWinner());
/*     */     } 
/* 418 */     return pkDataItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HashMap<Integer, PkDataItem[]> getGroupPkDataList(int serverId, int type) {
/* 429 */     HashMap<Integer, PkDataItem[]> pkDataItemMap = (HashMap)new HashMap<>();
/* 430 */     Zone zone = getZoneByServerId(serverId);
/* 431 */     if (zone == null) {
/* 432 */       LogUtil.errorLog(new Object[] { "MatchUtil::getGroupPkDataList -> server zone not exist", Integer.valueOf(serverId) });
/* 433 */       return pkDataItemMap;
/*     */     } 
/* 435 */     if (type < 0 || type > 1) {
/* 436 */       LogUtil.errorLog(new Object[] { "MatchUtil::getGroupPkDataList -> server", Integer.valueOf(serverId), "invalid type", Integer.valueOf(type) });
/* 437 */       return pkDataItemMap;
/*     */     } 
/* 439 */     if (type == 0) {
/* 440 */       for (int i = 0; i < (zone.getQuarterMatchGroups()).length; i++) {
/* 441 */         if (zone.getQuarterMatchGroups()[i] != null) {
/* 442 */           PkData[] pkDataArr = zone.getQuarterMatchGroups()[i].getAllPkData();
/* 443 */           PkDataItem[] pkDataItems = new PkDataItem[pkDataArr.length];
/* 444 */           for (int j = 0; j < pkDataArr.length; j++) {
/* 445 */             pkDataItems[j] = (pkDataArr[j] == null) ? null : transformPkData(pkDataArr[j]);
/*     */           }
/* 447 */           pkDataItemMap.put(Integer.valueOf(i + 1), pkDataItems);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 451 */       HalfMatchGroup halfMatchGroup = zone.getHalfMatchGroup();
/* 452 */       if (halfMatchGroup != null) {
/* 453 */         PkData[] pkDataArr = halfMatchGroup.getAllPkData();
/* 454 */         PkDataItem[] pkDataItems = new PkDataItem[pkDataArr.length];
/* 455 */         for (int j = 0; j < pkDataArr.length; j++) {
/* 456 */           pkDataItems[j] = (pkDataArr[j] == null) ? null : transformPkData(pkDataArr[j]);
/*     */         }
/* 458 */         pkDataItemMap.put(Integer.valueOf((zone.getQuarterMatchGroups()).length + 1), pkDataItems);
/*     */       } 
/*     */     } 
/* 461 */     return pkDataItemMap;
/*     */   }
/*     */   
/*     */   public static ArrayList<DestinyBetRecord> getPlayerBetRecord(int serverId, long playerId) {
/* 465 */     Zone zone = getZoneByServerId(serverId);
/* 466 */     if (zone != null) {
/* 467 */       return zone.getPlayerBetRecord(playerId);
/*     */     }
/* 469 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reZone(Map<Integer, Integer> serverIdToZoneIdMap) {
/* 478 */     Match match = getCurMatch();
/* 479 */     match.getServerIdToZoneId().putAll(serverIdToZoneIdMap);
/* 480 */     for (Integer zoneId : serverIdToZoneIdMap.values()) {
/* 481 */       match.getZoneDataMap().putIfAbsent(zoneId, new Zone(match.getMatchId(), zoneId.intValue()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canBet() {
/* 490 */     Match match = getCurMatch();
/* 491 */     MatchState matchState = match.getMatchState();
/* 492 */     return (matchState == MatchState.stepOnePrepare || matchState == MatchState.stepTwoPrepare || matchState == MatchState.stepThreePrepare || matchState == MatchState.stepFourPrepare || matchState == MatchState.stepFivePrepare);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void retrySendMatchReward() {
/* 500 */     if (matchCacheMap.isEmpty())
/* 501 */       return;  boolean success = false;
/* 502 */     for (MatchCache matchCache : matchCacheMap.values()) {
/* 503 */       for (Map.Entry<Integer, MatchCache.RewardCache> kv : (Iterable<Map.Entry<Integer, MatchCache.RewardCache>>)matchCache.getServerCache().entrySet()) {
/* 504 */         if (!((MatchCache.RewardCache)kv.getValue()).getDestinyTopRewardCache().isEmpty()) {
/* 505 */           success = LogicRmiUtil.sendDestinyTopReward("MatchUtil::retrySendMatchReward", ((Integer)kv.getKey()).intValue(), ((MatchCache.RewardCache)kv.getValue()).getDestinyTopRewardCache());
/* 506 */           if (success) {
/* 507 */             ((MatchCache.RewardCache)kv.getValue()).getDestinyTopRewardCache().clear();
/*     */           }
/*     */         } 
/* 510 */         if (!((MatchCache.RewardCache)kv.getValue()).getBetDataCache().isEmpty()) {
/* 511 */           ArrayList<BetData> betDataList = new ArrayList<>();
/* 512 */           for (List<BetData> data : (Iterable<List<BetData>>)((MatchCache.RewardCache)kv.getValue()).getBetDataCache().values()) {
/* 513 */             betDataList.addAll(data);
/*     */           }
/* 515 */           success = LogicRmiUtil.sendDestinyBetReward("MatchUtil::retrySendMatchReward", ((Integer)kv.getKey()).intValue(), betDataList);
/* 516 */           if (success) {
/* 517 */             ((MatchCache.RewardCache)kv.getValue()).getBetDataCache().clear();
/*     */           }
/*     */         } 
/* 520 */         if (!((MatchCache.RewardCache)kv.getValue()).getMatchRewardCache().isEmpty()) {
/* 521 */           success = LogicRmiUtil.sendDestinyMatchReward("MatchUtil::retrySendMatchReward", ((Integer)kv.getKey()).intValue(), ((MatchCache.RewardCache)kv.getValue()).getMatchRewardCache());
/* 522 */           if (success) {
/* 523 */             ((MatchCache.RewardCache)kv.getValue()).getMatchRewardCache().clear();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 528 */     if (success) {
/* 529 */       saveMatchCacheToDb();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Map<Integer, MatchCache> getMatchCacheMap() {
/* 534 */     return matchCacheMap;
/*     */   }
/*     */   
/*     */   public static void changeState(int state) {
/*     */     try {
/* 539 */       switch (state) {
/*     */         case 0:
/* 541 */           (new MatchStepBeginJob()).execute(null);
/*     */           return;
/*     */         case 1:
/* 544 */           (new MatchStepOnePrepareJob()).execute(null);
/*     */           return;
/*     */         case 2:
/* 547 */           (new MatchStepOneBalanceJob()).execute(null);
/*     */           return;
/*     */         case 3:
/* 550 */           (new MatchStepTwoPrepareJob()).execute(null);
/*     */           return;
/*     */         case 4:
/* 553 */           (new MatchStepTwoBalanceJob()).execute(null);
/*     */           return;
/*     */         case 5:
/* 556 */           (new MatchStepThreePrepareJob()).execute(null);
/*     */           return;
/*     */         case 6:
/* 559 */           (new MatchStepThreeBalanceJob()).execute(null);
/*     */           return;
/*     */         case 7:
/* 562 */           (new MatchStepFourPrepareJob()).execute(null);
/*     */           return;
/*     */         case 8:
/* 565 */           (new MatchStepFourBalanceJob()).execute(null);
/*     */           return;
/*     */         case 9:
/* 568 */           (new MatchStepFivePrepareJob()).execute(null);
/*     */           return;
/*     */         case 10:
/* 571 */           (new MatchStepFiveBalanceJob()).execute(null);
/*     */           return;
/*     */         case 11:
/* 574 */           (new MatchStepEndJob()).execute(null);
/*     */           return;
/*     */       } 
/* 577 */       Match match = getCurMatch();
/* 578 */       LogUtil.errorLog(new Object[] { Integer.valueOf(match.getMatchId()), match.getMatchState() });
/*     */     }
/* 580 */     catch (JobExecutionException e) {
/* 581 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void matchTest() {
/* 586 */     String destinyData = "{\"userId\":50608,\"serverId\":1001,\"playerId\":1001000002,\"playerName\":\"S1.YUI\",\"level\":66,\"sex\":2,\"head\":\"sex:2:\",\"nickname\":\"YUI\",\"vip\":3,\"fightValue\":1645393,\"modelData\":{\"playerId\":1001000002,\"sex\":2,\"title\":0,\"fashion\":0,\"nickname\":\"YUI\",\"mounts\":0},\"destinyStone\":0,\"destinyPoint\":0,\"quality\":40,\"timestamp\":0,\"fighters\":{\"0\":{\"pid\":-1,\"id\":1001,\"type\":3,\"hurtType\":2,\"skillMap\":{\"30100104\":1},\"level\":66,\"head\":\"\",\"name\":\"\",\"fightValue\":1645393,\"vip\":0,\"sex\":0,\"quality\":40,\"baseAttrs\":[0,286820,37996,37996,1324450],\"attrs\":[0,286820,37996,37996,1324450,466,66,66,66,0,0,2000,2000,0,0,0,0,0,15650,4450,4450,15650,0,0,0,1]},\"1\":{\"pid\":-1,\"id\":102,\"type\":0,\"hurtType\":2,\"skillMap\":{\"100201\":1},\"level\":66,\"head\":\"sex:2:\",\"name\":\"YUI\",\"fightValue\":1645393,\"vip\":3,\"sex\":2,\"quality\":40,\"baseAttrs\":[0,286820,37996,37996,1324450],\"attrs\":[0,286820,37996,37996,1324450,466,66,66,66,0,0,2000,2000,0,0,0,0,0,15650,4450,4450,15650,0,0,0,1]},\"2\":{\"pid\":2,\"id\":1701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,339497,46124,46124,1351831],\"attrs\":[0,339497,46124,46124,1351831,441,41,41,41,0,0,2000,2000,0,0,0,0,0,14650,3450,3450,12650,0,0,0,1]},\"3\":{\"pid\":3,\"id\":2701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,120594,35548,35548,408198],\"attrs\":[0,120594,35548,35548,408198,441,41,41,41,0,0,0,0,0,0,0,0,0,3450,3450,3450,1450,0,300,300,1]},\"4\":{\"pid\":4,\"id\":3701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,367042,46124,46124,1351831],\"attrs\":[0,367042,46124,46124,1351831,441,41,41,41,0,0,2000,2000,0,0,0,0,0,16650,3450,3450,12650,0,0,0,1]},\"5\":{\"pid\":5,\"id\":4701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,121804,36153,36153,413350],\"attrs\":[0,121804,36153,36153,413350,441,41,41,41,0,0,0,0,0,0,0,0,0,3450,3450,3450,1450,0,300,300,1]}}}";
/* 587 */     String targetDestinyData = "{\"userId\":50610,\"serverId\":1001,\"playerId\":1001000003,\"playerName\":\"S1.亓官荷\",\"level\":66,\"sex\":2,\"head\":\"sex:2:\",\"nickname\":\"亓官荷\",\"vip\":3,\"fightValue\":1645393,\"modelData\":{\"playerId\":1001000003,\"sex\":2,\"title\":0,\"fashion\":0,\"nickname\":\"亓官荷\",\"mounts\":0},\"destinyStone\":0,\"destinyPoint\":0,\"quality\":40,\"timestamp\":0,\"fighters\":{\"0\":{\"pid\":-1,\"id\":1001,\"type\":3,\"hurtType\":2,\"skillMap\":{\"30100104\":1},\"level\":66,\"head\":\"\",\"name\":\"\",\"fightValue\":1645393,\"vip\":0,\"sex\":0,\"quality\":40,\"baseAttrs\":[0,286820,37996,37996,1324450],\"attrs\":[0,286820,37996,37996,1324450,466,66,66,66,0,0,2000,2000,0,0,0,0,0,15650,4450,4450,15650,0,0,0,1]},\"1\":{\"pid\":-1,\"id\":102,\"type\":0,\"hurtType\":2,\"skillMap\":{\"100201\":1},\"level\":66,\"head\":\"sex:2:\",\"name\":\"亓官荷\",\"fightValue\":1645393,\"vip\":3,\"sex\":2,\"quality\":40,\"baseAttrs\":[0,286820,37996,37996,1324450],\"attrs\":[0,286820,37996,37996,1324450,466,66,66,66,0,0,2000,2000,0,0,0,0,0,15650,4450,4450,15650,0,0,0,1]},\"2\":{\"pid\":2,\"id\":1701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,339497,46124,46124,1351831],\"attrs\":[0,339497,46124,46124,1351831,441,41,41,41,0,0,2000,2000,0,0,0,0,0,14650,3450,3450,12650,0,0,0,1]},\"3\":{\"pid\":3,\"id\":2701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,120594,35548,35548,408198],\"attrs\":[0,120594,35548,35548,408198,441,41,41,41,0,0,0,0,0,0,0,0,0,3450,3450,3450,1450,0,300,300,1]},\"4\":{\"pid\":4,\"id\":3701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,367042,46124,46124,1351831],\"attrs\":[0,367042,46124,46124,1351831,441,41,41,41,0,0,2000,2000,0,0,0,0,0,16650,3450,3450,12650,0,0,0,1]},\"5\":{\"pid\":5,\"id\":4701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,121804,36153,36153,413350],\"attrs\":[0,121804,36153,36153,413350,441,41,41,41,0,0,0,0,0,0,0,0,0,3450,3450,3450,1450,0,300,300,1]}}} ";
/* 588 */     String thirdDestinyData = "{\"userId\":50609,\"serverId\":1001,\"playerId\":1001000378,\"playerName\":\"S1.LSH\",\"level\":66,\"sex\":2,\"head\":\"sex:2:\",\"nickname\":\"亓官荷\",\"vip\":3,\"fightValue\":1645393,\"modelData\":{\"playerId\":1001000378,\"sex\":2,\"title\":0,\"fashion\":0,\"nickname\":\"亓官荷\",\"mounts\":0},\"destinyStone\":0,\"destinyPoint\":0,\"quality\":40,\"timestamp\":0,\"fighters\":{\"0\":{\"pid\":-1,\"id\":1001,\"type\":3,\"hurtType\":2,\"skillMap\":{\"30100104\":1},\"level\":66,\"head\":\"\",\"name\":\"\",\"fightValue\":1645393,\"vip\":0,\"sex\":0,\"quality\":40,\"baseAttrs\":[0,286820,37996,37996,1324450],\"attrs\":[0,286820,37996,37996,1324450,466,66,66,66,0,0,2000,2000,0,0,0,0,0,15650,4450,4450,15650,0,0,0,1]},\"1\":{\"pid\":-1,\"id\":102,\"type\":0,\"hurtType\":2,\"skillMap\":{\"100201\":1},\"level\":66,\"head\":\"sex:2:\",\"name\":\"亓官荷\",\"fightValue\":1645393,\"vip\":3,\"sex\":2,\"quality\":40,\"baseAttrs\":[0,286820,37996,37996,1324450],\"attrs\":[0,286820,37996,37996,1324450,466,66,66,66,0,0,2000,2000,0,0,0,0,0,15650,4450,4450,15650,0,0,0,1]},\"2\":{\"pid\":2,\"id\":1701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,339497,46124,46124,1351831],\"attrs\":[0,339497,46124,46124,1351831,441,41,41,41,0,0,2000,2000,0,0,0,0,0,14650,3450,3450,12650,0,0,0,1]},\"3\":{\"pid\":3,\"id\":2701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,120594,35548,35548,408198],\"attrs\":[0,120594,35548,35548,408198,441,41,41,41,0,0,0,0,0,0,0,0,0,3450,3450,3450,1450,0,300,300,1]},\"4\":{\"pid\":4,\"id\":3701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,367042,46124,46124,1351831],\"attrs\":[0,367042,46124,46124,1351831,441,41,41,41,0,0,2000,2000,0,0,0,0,0,16650,3450,3450,12650,0,0,0,1]},\"5\":{\"pid\":5,\"id\":4701,\"type\":1,\"hurtType\":0,\"skillMap\":{},\"level\":0,\"fightValue\":0,\"vip\":0,\"sex\":0,\"quality\":0,\"baseAttrs\":[0,121804,36153,36153,413350],\"attrs\":[0,121804,36153,36153,413350,441,41,41,41,0,0,0,0,0,0,0,0,0,3450,3450,3450,1450,0,300,300,1]}}} ";
/*     */     try {
/* 590 */       ObjectMapper objectMapper = (ObjectMapper)MContext.getBean("objectMapper", ObjectMapper.class);
/* 591 */       int zoneId = 1;
/* 592 */       Map<Long, DestinyPlayerData> map = new HashMap<>();
/* 593 */       DestinyPlayerData destinyPlayerData = (DestinyPlayerData)objectMapper.readValue(destinyData, DestinyPlayerData.class);
/* 594 */       DestinyPlayerData targetDestinyPlayerData = (DestinyPlayerData)objectMapper.readValue(targetDestinyData, DestinyPlayerData.class);
/* 595 */       DestinyPlayerData thirdDestinyPlayerData = (DestinyPlayerData)objectMapper.readValue(thirdDestinyData, DestinyPlayerData.class);
/*     */       
/* 597 */       map.put(Long.valueOf(destinyPlayerData.getPlayerId()), destinyPlayerData);
/* 598 */       map.put(Long.valueOf(targetDestinyPlayerData.getPlayerId()), targetDestinyPlayerData);
/* 599 */       map.put(Long.valueOf(thirdDestinyPlayerData.getPlayerId()), thirdDestinyPlayerData);
/*     */       
/* 601 */       DestinyCache.getDestinyMap().getServerIdToZoneId().put(Integer.valueOf(destinyPlayerData.getServerId()), Integer.valueOf(zoneId));
/* 602 */       DestinyCache.getDestinyMap().getServerIdToZoneId().putIfAbsent(Integer.valueOf(targetDestinyPlayerData.getServerId()), Integer.valueOf(zoneId));
/* 603 */       DestinyCache.getDestinyMap().getServerIdToZoneId().putIfAbsent(Integer.valueOf(thirdDestinyPlayerData.getServerId()), Integer.valueOf(zoneId));
/* 604 */       DestinyCache.getDestinyMap().getZoneToPlayerMap().put(Integer.valueOf(zoneId), map);
/*     */       
/* 606 */       getCurMatch().changeState(MatchState.begin);
/* 607 */       quarterMatchQuarterInit();
/*     */       
/* 609 */       getCurMatch().changeState(MatchState.stepOnePrepare);
/*     */       
/* 611 */       getCurMatch().changeState(MatchState.stepOneBalance);
/* 612 */       quarterMatchQuarterFight();
/*     */       
/* 614 */       getCurMatch().changeState(MatchState.stepTwoPrepare);
/* 615 */       quarterMatchHalfInit();
/*     */       
/* 617 */       getCurMatch().changeState(MatchState.stepTwoBalance);
/* 618 */       quarterMatchHalfFight();
/*     */       
/* 620 */       getCurMatch().changeState(MatchState.stepThreePrepare);
/* 621 */       quarterMatchFinalInit();
/*     */       
/* 623 */       getCurMatch().changeState(MatchState.stepThreeBalance);
/* 624 */       quarterMatchFinalFight();
/*     */       
/* 626 */       getCurMatch().changeState(MatchState.stepFourPrepare);
/* 627 */       halfMatchInit();
/*     */       
/* 629 */       getCurMatch().changeState(MatchState.stepFourBalance);
/* 630 */       halfMatchFight();
/*     */       
/* 632 */       getCurMatch().changeState(MatchState.stepFivePrepare);
/* 633 */       finalMatchInit();
/*     */       
/* 635 */       getCurMatch().changeState(MatchState.stepFiveBalance);
/* 636 */       finalMatchFight();
/*     */       
/* 638 */       getCurMatch().changeState(MatchState.end);
/* 639 */       getCurMatch().saveToDb();
/*     */     }
/* 641 */     catch (Exception e) {
/* 642 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\MatchUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */