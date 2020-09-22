/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyRankComparator;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Match;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Zone;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public class DestinyMap
/*     */ {
/*     */   private static final int ZONE_SIZE = 10;
/*     */   private static final int CYCLE_COUNT = 50;
/*  30 */   private List<Pair<Integer, Integer>> serverWorldLevelList = new ArrayList<>();
/*  31 */   private Map<Integer, Integer> serverIdToZoneId = new HashMap<>();
/*  32 */   private Map<Integer, Map<Long, DestinyPlayerData>> zoneToPlayerMap = new HashMap<>();
/*  33 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void reZone() {
/*     */     try {
/*  41 */       this.lock.lock();
/*  42 */       this.serverWorldLevelList.sort((o1, o2) -> Integer.compare(((Integer)o2.getValue()).intValue(), ((Integer)o1.getValue()).intValue()));
/*  43 */       for (int i = 0; i < this.serverWorldLevelList.size(); i++) {
/*  44 */         int zoneId = i / 10 + 1;
/*  45 */         this.serverIdToZoneId.put(((Pair)this.serverWorldLevelList.get(i)).getKey(), Integer.valueOf(zoneId));
/*  46 */         this.zoneToPlayerMap.put(Integer.valueOf(zoneId), new HashMap<>());
/*     */       } 
/*     */     } finally {
/*  49 */       this.lock.unlock();
/*     */     } 
/*     */     
/*  52 */     for (Map.Entry<Integer, Integer> kv : this.serverIdToZoneId.entrySet()) {
/*  53 */       LogicRmiUtil.setZoneId("DestinyMap::reZone", ((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(ArrayList<Pair<Integer, Integer>> pairs) {
/*     */     try {
/*  63 */       this.lock.lock();
/*  64 */       this.serverWorldLevelList.clear();
/*  65 */       this.serverWorldLevelList.addAll(pairs);
/*  66 */       reZone();
/*     */     } finally {
/*  68 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*     */     try {
/*  78 */       this.lock.lock();
/*  79 */       this.serverWorldLevelList.clear();
/*  80 */       this.serverIdToZoneId.clear();
/*  81 */       this.zoneToPlayerMap.clear();
/*     */     } finally {
/*  83 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addServer(int serverId, int worldLevel) {
/*     */     try {
/*  94 */       this.lock.lock();
/*  95 */       if (!this.serverIdToZoneId.containsKey(Integer.valueOf(serverId))) {
/*  96 */         Pair<Integer, Integer> kv = new Pair(Integer.valueOf(serverId), Integer.valueOf(worldLevel));
/*  97 */         this.serverWorldLevelList.add(kv);
/*  98 */         int i = this.serverWorldLevelList.size() / 10 + 1;
/*  99 */         this.serverIdToZoneId.put(Integer.valueOf(serverId), Integer.valueOf(i));
/* 100 */         this.zoneToPlayerMap.put(Integer.valueOf(i), new HashMap<>());
/*     */         
/* 102 */         Match match1 = MatchUtil.getCurMatch();
/* 103 */         match1.getServerIdToZoneId().putIfAbsent(Integer.valueOf(serverId), Integer.valueOf(i));
/* 104 */         match1.getZoneDataMap().putIfAbsent(Integer.valueOf(i), new Zone(match1.getMatchId(), i));
/*     */         
/* 106 */         LogicRmiUtil.setZoneId("DestinyMap::addServer", serverId, i);
/* 107 */         return i;
/*     */       } 
/* 109 */       Match match = MatchUtil.getCurMatch();
/* 110 */       int zoneId = ((Integer)this.serverIdToZoneId.get(Integer.valueOf(serverId))).intValue();
/* 111 */       if (!match.getServerIdToZoneId().containsKey(Integer.valueOf(serverId))) {
/* 112 */         match.getServerIdToZoneId().putIfAbsent(Integer.valueOf(serverId), Integer.valueOf(zoneId));
/* 113 */         match.getZoneDataMap().putIfAbsent(Integer.valueOf(zoneId), new Zone(match.getMatchId(), zoneId));
/*     */         
/* 115 */         LogicRmiUtil.setZoneId("DestinyMap::addServer", serverId, zoneId);
/*     */       } 
/* 117 */       return zoneId;
/*     */     } finally {
/*     */       
/* 120 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public ArrayList<DestinyPlayerData> getDestinyPlayerDataList(int serverId) {
/*     */     try {
/* 127 */       this.lock.lock();
/* 128 */       return new ArrayList(((Map)this.zoneToPlayerMap.get(this.serverIdToZoneId.get(Integer.valueOf(serverId)))).values());
/*     */     } finally {
/* 130 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addDestinyPlayerData(DestinyPlayerData destinyPlayerData) {
/*     */     try {
/* 136 */       this.lock.lock();
/* 137 */       int zoneId = ((Integer)this.serverIdToZoneId.get(Integer.valueOf(destinyPlayerData.getServerId()))).intValue();
/* 138 */       this.zoneToPlayerMap.putIfAbsent(Integer.valueOf(zoneId), new HashMap<>());
/* 139 */       ((Map<Long, DestinyPlayerData>)this.zoneToPlayerMap.get(Integer.valueOf(zoneId))).put(Long.valueOf(destinyPlayerData.getPlayerId()), destinyPlayerData);
/*     */     } finally {
/* 141 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void updateTalismanMap(int serverId, long playerId, Map<Long, Map<Integer, Integer>> talismanMap) {
/*     */     try {
/* 148 */       this.lock.lock();
/* 149 */       int zoneId = ((Integer)getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 150 */       if (((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).containsKey(Long.valueOf(playerId))) {
/* 151 */         ((DestinyPlayerData)((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId))).getTalismanMap().clear();
/* 152 */         ((DestinyPlayerData)((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId))).getTalismanMap().putAll(talismanMap);
/*     */       } 
/*     */     } finally {
/* 155 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateDestinyPlayerData(DestinyPlayerData destinyPlayerData) {
/*     */     try {
/* 161 */       this.lock.lock();
/* 162 */       int zoneId = ((Integer)getServerIdToZoneId().get(Integer.valueOf(destinyPlayerData.getServerId()))).intValue();
/*     */ 
/*     */       
/* 165 */       DestinyPlayerData oldDestinyPlayerData = null;
/* 166 */       if (CrossUtil.isHefuPlayer(destinyPlayerData.getServerId(), destinyPlayerData.getPlayerId())) {
/* 167 */         for (Map.Entry<Integer, Map<Long, DestinyPlayerData>> kv : getZoneToPlayerMap().entrySet()) {
/* 168 */           if (((Integer)kv.getKey()).intValue() != zoneId && ((Map)kv.getValue()).containsKey(Long.valueOf(destinyPlayerData.getPlayerId()))) {
/* 169 */             oldDestinyPlayerData = (DestinyPlayerData)((Map)kv.getValue()).remove(Long.valueOf(destinyPlayerData.getPlayerId()));
/*     */           }
/*     */         } 
/*     */       }
/* 173 */       if (oldDestinyPlayerData != null) {
/* 174 */         destinyPlayerData.setDestinyStone(oldDestinyPlayerData.getDestinyStone());
/* 175 */         destinyPlayerData.setDestinyPoint(oldDestinyPlayerData.getDestinyPoint());
/*     */       } 
/* 177 */       if (((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).containsKey(Long.valueOf(destinyPlayerData.getPlayerId()))) {
/* 178 */         DestinyPlayerData old = (DestinyPlayerData)((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(destinyPlayerData.getPlayerId()));
/*     */         
/* 180 */         if (old.getDestinyPoint() > 0) {
/* 181 */           destinyPlayerData.setDestinyPoint(old.getDestinyPoint());
/*     */         }
/* 183 */         if (old.getDestinyStone() > 0) {
/* 184 */           destinyPlayerData.setDestinyStone(old.getDestinyStone());
/*     */         }
/*     */         
/* 187 */         ((Map<Long, DestinyPlayerData>)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).put(Long.valueOf(destinyPlayerData.getPlayerId()), destinyPlayerData);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 200 */         ((Map<Long, DestinyPlayerData>)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).put(Long.valueOf(destinyPlayerData.getPlayerId()), destinyPlayerData);
/*     */       } 
/*     */     } finally {
/*     */       
/* 204 */       this.lock.unlock();
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
/*     */   @JsonIgnore
/*     */   public Map<Integer, List<DestinyRankData>> getTopPlayerMap() {
/*     */     try {
/* 219 */       this.lock.lock();
/* 220 */       DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 221 */       Map<Integer, List<DestinyRankData>> resultMap = new HashMap<>();
/* 222 */       for (Map.Entry<Integer, Map<Long, DestinyPlayerData>> kv : getZoneToPlayerMap().entrySet()) {
/* 223 */         ArrayList<DestinyPlayerData> list = new ArrayList<>(((Map)kv.getValue()).values());
/* 224 */         list.sort((Comparator<? super DestinyPlayerData>)new DestinyRankComparator());
/* 225 */         int total = (list.size() <= destinyParameter.getRankSize()) ? list.size() : destinyParameter.getRankSize();
/* 226 */         for (int i = 0; i < total; i++) {
/* 227 */           DestinyPlayerData destinyPlayerData = list.get(i);
/* 228 */           resultMap.putIfAbsent(Integer.valueOf(destinyPlayerData.getServerId()), new ArrayList<>());
/*     */           
/* 230 */           DestinyRankData destinyRankData = new DestinyRankData();
/* 231 */           if (destinyPlayerData.getDestinyPoint() != 0) {
/*     */             
/* 233 */             destinyRankData.rank = i + 1;
/* 234 */             destinyRankData.playerId = destinyPlayerData.getPlayerId();
/* 235 */             destinyRankData.playerName = destinyPlayerData.getPlayerName();
/* 236 */             destinyRankData.fightValue = destinyPlayerData.getFightValue();
/* 237 */             destinyRankData.head = destinyPlayerData.getHead();
/* 238 */             destinyRankData.destinyPoint = destinyPlayerData.getDestinyPoint();
/* 239 */             destinyRankData.quality = destinyPlayerData.getQuality();
/* 240 */             ((List<DestinyRankData>)resultMap.get(Integer.valueOf(destinyPlayerData.getServerId()))).add(destinyRankData);
/*     */           } 
/*     */         } 
/* 243 */       }  return resultMap;
/*     */     } finally {
/* 245 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<DestinyPlayerData> recomment(int serverId, long playerId, long fightValue, HashSet<Long> battles, int recommentSize) {
/*     */     try {
/* 254 */       this.lock.lock();
/* 255 */       int zoneId = ((Integer)getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 256 */       ArrayList<DestinyPlayerData> resultList = new ArrayList<>();
/* 257 */       ArrayList<DestinyPlayerData> destinyPlayerDatas = new ArrayList<>(((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).values());
/*     */       
/* 259 */       if (destinyPlayerDatas.size() <= recommentSize) {
/* 260 */         for (DestinyPlayerData data : destinyPlayerDatas) {
/* 261 */           if (data.getPlayerId() != playerId) {
/* 262 */             resultList.add(data);
/*     */           }
/*     */         } 
/* 265 */         return resultList;
/*     */       } 
/* 267 */       for (Long id : battles) {
/* 268 */         DestinyPlayerData tmp = (DestinyPlayerData)((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(id);
/* 269 */         if (tmp != null) {
/* 270 */           resultList.add(tmp);
/*     */         }
/*     */       } 
/* 273 */       if (recommentSize == 0) {
/* 274 */         return resultList;
/*     */       }
/* 276 */       Set<Long> recommentSet = new HashSet<>();
/*     */       
/* 278 */       int total = destinyPlayerDatas.size(), tick = 0;
/* 279 */       long start = fightValue * 120L / 100L, end = fightValue * 150L / 100L;
/* 280 */       if (total <= 200) {
/* 281 */         int i = 0, num = 0;
/* 282 */         for (DestinyPlayerData data : destinyPlayerDatas) {
/* 283 */           if (i >= 4 || i >= recommentSize) {
/*     */             break;
/*     */           }
/* 286 */           if (playerId != data.getPlayerId() && start <= data.getFightValue() && data.getFightValue() <= end && 
/* 287 */             !recommentSet.contains(Long.valueOf(data.getPlayerId())) && !battles.contains(Long.valueOf(data.getPlayerId()))) {
/* 288 */             i++;
/* 289 */             recommentSet.add(Long.valueOf(data.getPlayerId()));
/* 290 */             resultList.add(data);
/*     */           } 
/*     */         } 
/* 293 */         int expect = recommentSize - i;
/* 294 */         while (num < expect && tick < 50) {
/* 295 */           tick++;
/* 296 */           int index = RandUtil.randNum(total - 1);
/* 297 */           DestinyPlayerData tmp = destinyPlayerDatas.get(index);
/* 298 */           if (playerId != tmp.getPlayerId() && !recommentSet.contains(Long.valueOf(tmp.getPlayerId())) && 
/* 299 */             !battles.contains(Long.valueOf(tmp.getPlayerId()))) {
/* 300 */             num++;
/* 301 */             recommentSet.add(Long.valueOf(tmp.getPlayerId()));
/* 302 */             resultList.add(tmp);
/*     */           } 
/*     */         } 
/* 305 */         return resultList;
/*     */       } 
/* 307 */       int count = 0;
/* 308 */       destinyPlayerDatas.sort((Comparator<? super DestinyPlayerData>)new DestinyRankComparator());
/* 309 */       tick = 0;
/* 310 */       while (count < 2 && count < recommentSize && tick < 50) {
/* 311 */         tick++;
/* 312 */         int index = RandUtil.randNum(200);
/* 313 */         DestinyPlayerData tmp = destinyPlayerDatas.get(index);
/* 314 */         if (playerId != tmp.getPlayerId() && !recommentSet.contains(Long.valueOf(tmp.getPlayerId())) && !battles.contains(Long.valueOf(tmp.getPlayerId()))) {
/* 315 */           count++;
/* 316 */           recommentSet.add(Long.valueOf(tmp.getPlayerId()));
/* 317 */           resultList.add(tmp);
/*     */         } 
/*     */       } 
/*     */       
/* 321 */       for (DestinyPlayerData data : destinyPlayerDatas) {
/* 322 */         if (count >= 4 || count >= recommentSize)
/* 323 */           break;  if (playerId != data.getPlayerId() && start <= data.getFightValue() && data.getFightValue() <= end && 
/* 324 */           !recommentSet.contains(Long.valueOf(data.getPlayerId())) && !battles.contains(Long.valueOf(data.getPlayerId()))) {
/* 325 */           count++;
/* 326 */           recommentSet.add(Long.valueOf(data.getPlayerId()));
/* 327 */           resultList.add(data);
/*     */         } 
/*     */       } 
/*     */       
/* 331 */       tick = 0;
/* 332 */       while (count < recommentSize && tick < 50) {
/* 333 */         tick++;
/* 334 */         int index = RandUtil.randNum(total - 1);
/* 335 */         DestinyPlayerData tmp = destinyPlayerDatas.get(index);
/* 336 */         if (playerId != tmp.getPlayerId() && !recommentSet.contains(Long.valueOf(tmp.getPlayerId())) && !battles.contains(Long.valueOf(tmp.getPlayerId()))) {
/* 337 */           count++;
/* 338 */           recommentSet.add(Long.valueOf(tmp.getPlayerId()));
/* 339 */           resultList.add(tmp);
/*     */         } 
/*     */       } 
/* 342 */       return resultList;
/*     */     } finally {
/*     */       
/* 345 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public List<DestinyRankData> getDestinyRankDataList(int zoneId) {
/*     */     try {
/* 352 */       this.lock.lock();
/* 353 */       List<DestinyRankData> resultList = new ArrayList<>();
/* 354 */       DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 355 */       ArrayList<DestinyPlayerData> list = new ArrayList<>(((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).values());
/* 356 */       list.sort((Comparator<? super DestinyPlayerData>)new DestinyRankComparator());
/* 357 */       int total = (list.size() <= destinyParameter.getRankSize()) ? list.size() : destinyParameter.getRankSize();
/* 358 */       for (int i = 0; i < total; i++) {
/* 359 */         DestinyRankData destinyRankData = new DestinyRankData();
/* 360 */         DestinyPlayerData destinyPlayerData = list.get(i);
/* 361 */         if (destinyPlayerData.getDestinyPoint() != 0) {
/*     */           
/* 363 */           destinyRankData.rank = i + 1;
/* 364 */           destinyRankData.playerId = destinyPlayerData.getPlayerId();
/* 365 */           destinyRankData.playerName = destinyPlayerData.getPlayerName();
/* 366 */           destinyRankData.fightValue = destinyPlayerData.getFightValue();
/* 367 */           destinyRankData.head = destinyPlayerData.getHead();
/* 368 */           destinyRankData.destinyPoint = destinyPlayerData.getDestinyPoint();
/* 369 */           destinyRankData.quality = destinyPlayerData.getQuality();
/* 370 */           resultList.add(destinyRankData);
/*     */         } 
/* 372 */       }  return resultList;
/*     */     } finally {
/* 374 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearServerPlayerData(int serverId) {
/* 379 */     int zoneId = ((Integer)getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 380 */     for (DestinyPlayerData data : ((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).values()) {
/* 381 */       if (data.getServerId() == serverId) {
/* 382 */         ((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).remove(Long.valueOf(data.getPlayerId()));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public DestinyPlayerData getDestinyPlayerData(int serverId, long playerId) {
/* 389 */     int zoneId = ((Integer)this.serverIdToZoneId.get(Integer.valueOf(serverId))).intValue();
/* 390 */     return (DestinyPlayerData)((Map)this.zoneToPlayerMap.get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public int addDestinyPoint(int serverId, long playerId, int destinyPoint) {
/*     */     try {
/* 395 */       this.lock.lock();
/* 396 */       int zoneId = ((Integer)getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 397 */       DestinyPlayerData destinyPlayerData = (DestinyPlayerData)((Map)getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId));
/* 398 */       if (destinyPlayerData != null && 
/* 399 */         destinyPoint > 0) {
/* 400 */         destinyPlayerData.setDestinyPoint(destinyPlayerData.getDestinyPoint() + destinyPoint);
/* 401 */         return destinyPlayerData.getDestinyPoint();
/*     */       } 
/*     */       
/* 404 */       return -1;
/*     */     } finally {
/* 406 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<Pair<Integer, Integer>> getServerWorldLevelList() {
/* 411 */     return this.serverWorldLevelList;
/*     */   }
/*     */   
/*     */   public void setServerWorldLevelList(List<Pair<Integer, Integer>> serverWorldLevelList) {
/* 415 */     this.serverWorldLevelList = serverWorldLevelList;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getServerIdToZoneId() {
/* 419 */     return this.serverIdToZoneId;
/*     */   }
/*     */   
/*     */   public void setServerIdToZoneId(Map<Integer, Integer> serverIdToZoneId) {
/* 423 */     this.serverIdToZoneId = serverIdToZoneId;
/*     */   }
/*     */   
/*     */   public Map<Integer, Map<Long, DestinyPlayerData>> getZoneToPlayerMap() {
/* 427 */     return this.zoneToPlayerMap;
/*     */   }
/*     */   
/*     */   public void setZoneToPlayerMap(Map<Integer, Map<Long, DestinyPlayerData>> zoneToPlayerMap) {
/* 431 */     this.zoneToPlayerMap = zoneToPlayerMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\DestinyMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */