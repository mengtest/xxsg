/*     */ package com.linlongyx.sanguo.webgame.rmi.race;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class CrossRaceCache {
/*  23 */   private Map<Integer, RaceServerData> raceServerMap = new HashMap<>();
/*     */   private static final int ZONE_SIZE = 20;
/*  25 */   private Map<Integer, RaceZone> raceZoneMap = new HashMap<>();
/*     */   
/*  27 */   private volatile RaceState raceState = RaceState.CLOSE;
/*     */   @JsonIgnore
/*  29 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */   
/*     */   @JsonIgnoreProperties(ignoreUnknown = true)
/*     */   public static class RaceServerData
/*     */   {
/*     */     private int serverId;
/*     */     private int zoneId;
/*     */     private int worldLevel;
/*     */     
/*     */     public RaceServerData() {}
/*     */     
/*     */     public RaceServerData(int serverId, int worldLevel) {
/*  42 */       this(serverId, 0, worldLevel);
/*     */     }
/*     */     
/*     */     public RaceServerData(int serverId, int zoneId, int worldLevel) {
/*  46 */       this.serverId = serverId;
/*  47 */       this.zoneId = zoneId;
/*  48 */       this.worldLevel = worldLevel;
/*     */     }
/*     */     
/*     */     public int getServerId() {
/*  52 */       return this.serverId;
/*     */     }
/*     */     
/*     */     public void setServerId(int serverId) {
/*  56 */       this.serverId = serverId;
/*     */     }
/*     */     
/*     */     public int getZoneId() {
/*  60 */       return this.zoneId;
/*     */     }
/*     */     
/*     */     public void setZoneId(int zoneId) {
/*  64 */       this.zoneId = zoneId;
/*     */     }
/*     */     
/*     */     public int getWorldLevel() {
/*  68 */       return this.worldLevel;
/*     */     }
/*     */     
/*     */     public void setWorldLevel(int worldLevel) {
/*  72 */       this.worldLevel = worldLevel;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum RaceState {
/*  77 */     CLOSE(0),
/*  78 */     OPEN(1),
/*  79 */     BALANCE(2);
/*     */     
/*     */     int state;
/*     */     
/*     */     RaceState(int state) {
/*  84 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  88 */       return this.state;
/*     */     }
/*     */   }
/*     */   
/*     */   public void balance() {
/*     */     try {
/*  94 */       this.lock.lock();
/*  95 */       this.raceState = RaceState.BALANCE;
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 101 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void open() {
/* 106 */     this.raceState = RaceState.OPEN;
/*     */   }
/*     */   
/*     */   public void close() {
/* 110 */     this.raceState = RaceState.CLOSE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reZone() {
/*     */     try {
/* 118 */       this.lock.lock();
/* 119 */       this.raceZoneMap.clear();
/* 120 */       List<RaceServerData> serverList = (List<RaceServerData>)this.raceServerMap.values().stream().sorted((o1, o2) -> Integer.compare(o2.getWorldLevel(), o1.getWorldLevel())).collect(Collectors.toList());
/* 121 */       for (int i = 0; i < serverList.size(); i++) {
/* 122 */         int zoneId = i / 20 + 1;
/* 123 */         RaceServerData data = serverList.get(i);
/* 124 */         data.setZoneId(zoneId);
/* 125 */         this.raceServerMap.put(Integer.valueOf(data.getServerId()), data);
/* 126 */         this.raceZoneMap.putIfAbsent(Integer.valueOf(zoneId), new RaceZone(zoneId));
/* 127 */         ((RaceZone)this.raceZoneMap.get(Integer.valueOf(zoneId))).addServer(data.getServerId());
/*     */       } 
/*     */     } finally {
/* 130 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(ArrayList<Pair<Integer, Integer>> pairs) {
/*     */     try {
/* 141 */       this.lock.lock();
/* 142 */       this.raceServerMap.clear();
/* 143 */       for (Pair<Integer, Integer> kv : pairs) {
/* 144 */         this.raceServerMap.put(kv.getKey(), new RaceServerData(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue()));
/*     */       }
/* 146 */       reZone();
/*     */     } finally {
/* 148 */       this.lock.unlock();
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
/*     */   public int addServer(int serverId, int worldLevel) {
/*     */     try {
/* 161 */       this.lock.lock();
/* 162 */       if (!this.raceServerMap.containsKey(Integer.valueOf(serverId))) {
/* 163 */         RaceServerData data = new RaceServerData(serverId, worldLevel);
/* 164 */         RaceZone raceZone = chooseNotFullZone();
/* 165 */         if (raceZone == null) {
/* 166 */           int zoneId = this.raceZoneMap.isEmpty() ? 1 : (((RaceZone)this.raceZoneMap.values().stream().max(Comparator.comparingInt(RaceZone::getZoneId)).get()).getZoneId() + 1);
/* 167 */           data.setZoneId(zoneId);
/* 168 */           raceZone = new RaceZone(zoneId);
/* 169 */           raceZone.addServer(serverId);
/* 170 */           this.raceZoneMap.put(Integer.valueOf(zoneId), raceZone);
/*     */         } else {
/* 172 */           data.setZoneId(raceZone.getZoneId());
/* 173 */           raceZone.addServer(serverId);
/*     */         } 
/* 175 */         this.raceServerMap.put(Integer.valueOf(serverId), data);
/* 176 */         LogUtil.errorLog(new Object[] { "cross race add server", Integer.valueOf(serverId), "to zone", Integer.valueOf(raceZone.getZoneId()) });
/* 177 */         return raceZone.getZoneId();
/*     */       } 
/* 179 */       RaceZone targetZone = null;
/* 180 */       for (RaceZone raceZone : this.raceZoneMap.values()) {
/* 181 */         if (raceZone.getServerToPlayerMap().containsKey(Integer.valueOf(serverId))) {
/* 182 */           targetZone = raceZone;
/*     */           break;
/*     */         } 
/*     */       } 
/* 186 */       if (targetZone == null) {
/* 187 */         targetZone = chooseNotFullZone();
/* 188 */         if (targetZone == null) {
/* 189 */           LogUtil.errorLog(new Object[] { "cross race add server error server:", Integer.valueOf(serverId), "previously join error" });
/*     */         } else {
/* 191 */           ((RaceServerData)this.raceServerMap.get(Integer.valueOf(serverId))).setWorldLevel(worldLevel);
/* 192 */           ((RaceServerData)this.raceServerMap.get(Integer.valueOf(serverId))).setZoneId(targetZone.getZoneId());
/* 193 */           targetZone.addServer(serverId);
/*     */         } 
/*     */       } 
/* 196 */       return (targetZone == null) ? 0 : targetZone.getZoneId();
/*     */     } finally {
/*     */       
/* 199 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addServerIfAbsent(int serverId, int worldLevel) {
/*     */     try {
/* 205 */       this.lock.lock();
/* 206 */       if (!this.raceServerMap.containsKey(Integer.valueOf(serverId))) {
/* 207 */         addServer(serverId, worldLevel);
/*     */       }
/*     */     } finally {
/* 210 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private RaceZone chooseNotFullZone() {
/*     */     try {
/* 216 */       this.lock.lock();
/* 217 */       for (RaceZone raceZone : this.raceZoneMap.values()) {
/* 218 */         if (raceZone.getServerToPlayerMap().size() < 20) {
/* 219 */           return raceZone;
/*     */         }
/*     */       } 
/* 222 */       return null;
/*     */     } finally {
/* 224 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean playerJoin(PlayerData playerData) {
/*     */     try {
/* 230 */       this.lock.lock();
/* 231 */       if (!this.raceServerMap.containsKey(Integer.valueOf(playerData.getServerId()))) {
/* 232 */         return false;
/*     */       }
/* 234 */       int zoneId = ((RaceServerData)this.raceServerMap.get(Integer.valueOf(playerData.getServerId()))).getZoneId();
/* 235 */       if (!this.raceZoneMap.containsKey(Integer.valueOf(zoneId))) {
/* 236 */         return false;
/*     */       }
/*     */       
/* 239 */       RacePlayerData oldRacePlayerData = null;
/* 240 */       if (CrossUtil.isHefuPlayer(playerData.getServerId(), playerData.getPlayerId())) {
/* 241 */         int srcServerId = CrossUtil.getServerIdByPlayerId(playerData.getPlayerId());
/* 242 */         for (RaceZone zone : this.raceZoneMap.values()) {
/* 243 */           if (zone.getServerToPlayerMap().containsKey(Integer.valueOf(srcServerId))) {
/* 244 */             oldRacePlayerData = (RacePlayerData)((Map)zone.getServerToPlayerMap().get(Integer.valueOf(srcServerId))).remove(Long.valueOf(playerData.getPlayerId()));
/*     */           }
/*     */         } 
/*     */       } 
/* 248 */       RacePlayerData newRacePlayerData = new RacePlayerData(playerData);
/* 249 */       if (oldRacePlayerData != null) {
/* 250 */         newRacePlayerData.setRacePoint(oldRacePlayerData.getRacePoint());
/*     */       }
/* 252 */       oldRacePlayerData = (RacePlayerData)((Map)((RaceZone)this.raceZoneMap.get(Integer.valueOf(zoneId))).getServerToPlayerMap().get(Integer.valueOf(playerData.getServerId()))).get(Long.valueOf(playerData.getPlayerId()));
/* 253 */       if (oldRacePlayerData != null) {
/* 254 */         newRacePlayerData.setRacePoint(oldRacePlayerData.getRacePoint());
/* 255 */         newRacePlayerData.setUpdateTime(TimeUtil.currentTime());
/*     */       } 
/* 257 */       ((Map<Long, RacePlayerData>)((RaceZone)this.raceZoneMap.get(Integer.valueOf(zoneId))).getServerToPlayerMap().get(Integer.valueOf(playerData.getServerId())))
/* 258 */         .put(Long.valueOf(playerData.getPlayerId()), newRacePlayerData);
/* 259 */       return true;
/*     */     } finally {
/* 261 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int updatePlayerRacePoint(int serverId, long playerId, int point) {
/*     */     try {
/* 267 */       this.lock.lock();
/* 268 */       if (this.raceState == RaceState.CLOSE)
/* 269 */         return -1; 
/* 270 */       RaceServerData data = this.raceServerMap.get(Integer.valueOf(serverId));
/* 271 */       if (data == null)
/* 272 */         return -1; 
/* 273 */       RaceZone raceZone = this.raceZoneMap.get(Integer.valueOf(data.getZoneId()));
/* 274 */       RacePlayerData racePlayerData = (RacePlayerData)((Map)raceZone.getServerToPlayerMap().get(Integer.valueOf(serverId))).get(Long.valueOf(playerId));
/* 275 */       if (point != racePlayerData.getRacePoint()) {
/* 276 */         racePlayerData.setUpdateTime(TimeUtil.currentTime());
/*     */       }
/* 278 */       racePlayerData.setRacePoint(point);
/* 279 */       return racePlayerData.getRacePoint();
/*     */     } finally {
/* 281 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void updateTalismanMap(int serverId, long playerId, Map<Long, Map<Integer, Integer>> talismanMap) {
/*     */     try {
/* 288 */       this.lock.lock();
/* 289 */       RaceServerData data = this.raceServerMap.get(Integer.valueOf(serverId));
/* 290 */       if (data == null)
/*     */         return; 
/* 292 */       RaceZone raceZone = this.raceZoneMap.get(Integer.valueOf(data.getZoneId()));
/* 293 */       RacePlayerData racePlayerData = (RacePlayerData)((Map)raceZone.getServerToPlayerMap().get(Integer.valueOf(serverId))).get(Long.valueOf(playerId));
/* 294 */       if (racePlayerData != null) {
/* 295 */         racePlayerData.getPlayerData().getTalismanMap().clear();
/* 296 */         racePlayerData.getPlayerData().getTalismanMap().putAll(talismanMap);
/*     */       } 
/*     */     } finally {
/* 299 */       this.lock.unlock();
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
/*     */   public RacePlayerData recommentOne(int serverId, long playerId, long fightValue, int point) {
/*     */     try {
/* 312 */       this.lock.lock();
/* 313 */       if (this.raceState == RaceState.CLOSE)
/* 314 */         return null; 
/* 315 */       RaceServerData data = this.raceServerMap.get(Integer.valueOf(serverId));
/* 316 */       if (data == null)
/* 317 */         return null; 
/* 318 */       RaceZone raceZone = this.raceZoneMap.get(Integer.valueOf(data.getZoneId()));
/* 319 */       long deltaPoint = (point / 5);
/* 320 */       long minPoint = point - deltaPoint;
/* 321 */       long maxPoint = point + deltaPoint;
/* 322 */       List<RacePlayerData> playerList = new ArrayList<>();
/*     */ 
/*     */       
/* 325 */       raceZone.getServerToPlayerMap().values().forEach(sp -> sp.values().stream().forEach(playerList::add));
/*     */ 
/*     */       
/* 328 */       if (!playerList.isEmpty()) {
/* 329 */         return playerList.get(RandUtil.randNum(0, playerList.size() - 1));
/*     */       }
/*     */       
/* 332 */       long deltaFightValue = fightValue / 5L;
/* 333 */       long minFightValue = fightValue - deltaFightValue;
/* 334 */       long maxFightValue = fightValue + deltaFightValue;
/*     */ 
/*     */       
/* 337 */       raceZone.getServerToPlayerMap().values().forEach(sp -> sp.values().stream().forEach(playerList::add));
/*     */ 
/*     */ 
/*     */       
/* 341 */       if (!playerList.isEmpty()) {
/* 342 */         return playerList.get(RandUtil.randNum(0, playerList.size() - 1));
/*     */       }
/*     */ 
/*     */       
/* 346 */       deltaFightValue = fightValue * 2L / 5L;
/* 347 */       long finalMinFightValue = fightValue - deltaFightValue;
/* 348 */       long finalMaxFightValue = fightValue + deltaFightValue;
/* 349 */       raceZone.getServerToPlayerMap().values().forEach(sp -> sp.values().stream().forEach(playerList::add));
/*     */ 
/*     */ 
/*     */       
/* 353 */       if (!playerList.isEmpty()) {
/* 354 */         return playerList.get(RandUtil.randNum(0, playerList.size() - 1));
/*     */       }
/*     */ 
/*     */       
/* 358 */       raceZone.getServerToPlayerMap().values().forEach(sp -> sp.values().stream().forEach(playerList::add));
/*     */ 
/*     */       
/* 361 */       if (!playerList.isEmpty()) {
/* 362 */         return playerList.get(RandUtil.randNum(0, playerList.size() - 1));
/*     */       }
/* 364 */       return null;
/*     */     } finally {
/* 366 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public ArrayList<RankingData> getRankList(int serverId, int pageSize) {
/*     */     try {
/* 373 */       this.lock.lock();
/* 374 */       RaceServerData data = this.raceServerMap.get(Integer.valueOf(serverId));
/* 375 */       if (data == null)
/* 376 */         return null; 
/* 377 */       RaceZone raceZone = this.raceZoneMap.get(Integer.valueOf(data.getZoneId()));
/* 378 */       raceZone.setLimit(pageSize);
/* 379 */       return raceZone.rank();
/*     */     } finally {
/* 381 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public RankingData getPlayerRank(int serverId, long playerId, int curPoint) {
/*     */     try {
/* 388 */       this.lock.lock();
/* 389 */       RaceServerData data = this.raceServerMap.get(Integer.valueOf(serverId));
/* 390 */       if (data == null)
/* 391 */         return null; 
/* 392 */       RaceZone raceZone = this.raceZoneMap.get(Integer.valueOf(data.getZoneId()));
/* 393 */       List<RacePlayerData> playerList = new ArrayList<>();
/* 394 */       raceZone.getServerToPlayerMap().values().forEach(sp -> sp.values().stream().forEach(playerList::add));
/*     */ 
/*     */       
/* 397 */       playerList.sort(Comparator.comparingInt(RacePlayerData::getRacePoint).reversed().thenComparing(RacePlayerData::getUpdateTime));
/* 398 */       int rank = 0;
/* 399 */       for (RacePlayerData racePlayerData : playerList) {
/* 400 */         rank++;
/* 401 */         if (racePlayerData.getPlayerData().getPlayerId() == playerId) {
/* 402 */           RankingData rankingData = RaceUtil.transform(racePlayerData);
/* 403 */           rankingData.rank = rank;
/* 404 */           return rankingData;
/*     */         } 
/*     */       } 
/* 407 */       return null;
/*     */     } finally {
/* 409 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<Integer, RaceServerData> getRaceServerMap() {
/* 414 */     return this.raceServerMap;
/*     */   }
/*     */   
/*     */   public void setRaceServerMap(Map<Integer, RaceServerData> raceServerMap) {
/* 418 */     this.raceServerMap = raceServerMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, RaceZone> getRaceZoneMap() {
/* 422 */     return this.raceZoneMap;
/*     */   }
/*     */   
/*     */   public void setRaceZoneMap(Map<Integer, RaceZone> raceZoneMap) {
/* 426 */     this.raceZoneMap = raceZoneMap;
/*     */   }
/*     */   
/*     */   public RaceState getRaceState() {
/* 430 */     return this.raceState;
/*     */   }
/*     */   
/*     */   public void setRaceState(RaceState raceState) {
/* 434 */     this.raceState = raceState;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\race\CrossRaceCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */