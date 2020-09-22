/*     */ package com.linlongyx.sanguo.webgame.rmi.tower;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TowerOwnerBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.TowerOwnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class TowerZone {
/*  24 */   private Set<Integer> serverSet = new HashSet<>(); private int zoneId;
/*  25 */   private Map<Integer, TowerLayerData> layerMap = new TreeMap<>();
/*  26 */   private Map<Long, PlayerData> playerMap = new HashMap<>();
/*     */   @JsonIgnore
/*  28 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TowerZone(int zoneId) {
/*  35 */     this(zoneId, null);
/*     */   }
/*     */   
/*     */   public TowerZone(int zoneId, List<Integer> serverList) {
/*  39 */     this.zoneId = zoneId;
/*  40 */     if (serverList != null) {
/*  41 */       this.serverSet.addAll(serverList);
/*     */     }
/*  43 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(TowerOwnerBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  44 */       this.layerMap.put(Integer.valueOf(id), new TowerLayerData(id)); }
/*     */   
/*     */   }
/*     */   
/*     */   public void reloadLayerData() {
/*     */     try {
/*  50 */       this.lock.lock();
/*  51 */       for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(TowerOwnerBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  52 */         this.layerMap.putIfAbsent(Integer.valueOf(id), new TowerLayerData(id)); }
/*     */     
/*     */     } finally {
/*  55 */       this.lock.unlock();
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
/*     */   @JsonIgnore
/*     */   public Pair<Integer, ArrayList<RankingData>> getRankList(long playerId, int limit) {
/*     */     try {
/*  69 */       this.lock.lock();
/*  70 */       ArrayList<RankingData> rankList = new ArrayList<>();
/*     */       
/*  72 */       List<TowerLayerData> list = (List<TowerLayerData>)this.layerMap.values().stream().filter(data -> (data.getOwnerId() > 0L && data.getState() != TowerLayerData.TowerLayerState.FREE)).sorted(Comparator.comparingInt(TowerLayerData::getLayerId).reversed()).collect(Collectors.toList());
/*  73 */       int rank = 0, myRank = 0;
/*  74 */       for (TowerLayerData towerLayerData : list) {
/*  75 */         PlayerData playerData = this.playerMap.get(Long.valueOf(towerLayerData.getOwnerId()));
/*  76 */         if (playerData != null) {
/*  77 */           rank++;
/*  78 */           if (playerId == playerData.getPlayerId()) {
/*  79 */             myRank = rank;
/*     */           }
/*  81 */           if (rank <= limit) {
/*  82 */             RankingData rankingData = CrossUtil.transform(playerData);
/*  83 */             rankingData.rank = rank;
/*  84 */             rankingData.value = towerLayerData.getLayerId();
/*  85 */             rankList.add(rankingData);
/*     */           } 
/*     */         } 
/*     */       } 
/*  89 */       return new Pair(Integer.valueOf(myRank), rankList);
/*     */     } finally {
/*  91 */       this.lock.unlock();
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
/*     */   @JsonIgnore
/*     */   public List<RankingData> getTowerData(long playerId, int curTowerId, int range) {
/*     */     try {
/* 105 */       this.lock.lock();
/* 106 */       List<RankingData> rankingList = new ArrayList<>();
/* 107 */       for (TowerLayerData data : this.layerMap.values()) {
/* 108 */         if (data.getOwnerId() == playerId) {
/* 109 */           curTowerId = data.getLayerId();
/*     */           break;
/*     */         } 
/*     */       } 
/* 113 */       int start = (curTowerId < range) ? 1 : (curTowerId + 1 - range), end = (curTowerId < range) ? range : (curTowerId + 1);
/* 114 */       if (end > this.layerMap.size() + 1) {
/* 115 */         start = this.layerMap.size() + 1 - range;
/* 116 */         end = this.layerMap.size() + 1;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       int finalStart = start;
/* 126 */       int finalEnd = end;
/* 127 */       int curtime = TimeUtil.currentTime();
/*     */       
/* 129 */       List<TowerLayerData> list = (List<TowerLayerData>)this.layerMap.values().stream().filter(data -> (data.getLayerId() >= finalStart && data.getLayerId() <= finalEnd)).sorted(Comparator.comparingInt(TowerLayerData::getLayerId).reversed()).collect(Collectors.toList());
/* 130 */       for (TowerLayerData towerLayerData : list) {
/* 131 */         RankingData rankingData; if (towerLayerData.getState() == TowerLayerData.TowerLayerState.FIGHTING && towerLayerData.getExpireTime() < curtime) {
/* 132 */           if (towerLayerData.getOwnerId() > 0L) {
/* 133 */             towerLayerData.setState(TowerLayerData.TowerLayerState.OWN);
/* 134 */             towerLayerData.setFighterId(0L);
/*     */           } else {
/* 136 */             towerLayerData.setState(TowerLayerData.TowerLayerState.FREE);
/* 137 */             towerLayerData.setFighterId(0L);
/*     */           } 
/*     */         }
/* 140 */         PlayerData playerData = this.playerMap.get(Long.valueOf(towerLayerData.getOwnerId()));
/*     */         
/* 142 */         if (playerData != null) {
/* 143 */           rankingData = CrossUtil.transform(playerData);
/*     */         } else {
/* 145 */           rankingData = new RankingData();
/*     */         } 
/* 147 */         rankingData.time = towerLayerData.getOwnTime();
/* 148 */         rankingData.rank = towerLayerData.getLayerId();
/* 149 */         rankingData.value = towerLayerData.getState().getState();
/* 150 */         rankingList.add(rankingData);
/*     */       } 
/* 152 */       return rankingList;
/*     */     } finally {
/* 154 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean removePlayer(long playerId) {
/*     */     try {
/* 160 */       this.lock.lock();
/* 161 */       for (TowerLayerData towerLayerData : this.layerMap.values()) {
/* 162 */         if (towerLayerData.getOwnerId() == playerId) {
/* 163 */           towerLayerData.setState(TowerLayerData.TowerLayerState.FREE);
/* 164 */           towerLayerData.setExpireTime(0);
/* 165 */           towerLayerData.setOwnTime(0);
/* 166 */           towerLayerData.setOwnerId(0L);
/*     */         } 
/*     */       } 
/* 169 */       return (this.playerMap.remove(Long.valueOf(playerId)) != null);
/*     */     } finally {
/* 171 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updatePlayer(PlayerData playerData) {
/*     */     try {
/* 177 */       this.lock.lock();
/* 178 */       this.playerMap.put(Long.valueOf(playerData.getPlayerId()), playerData);
/*     */     } finally {
/* 180 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetLayerData(TowerLayerData data) {
/* 185 */     data.setFighterId(0L);
/* 186 */     data.setOwnerId(0L);
/* 187 */     data.setExpireTime(0);
/* 188 */     data.setOwnTime(0);
/* 189 */     data.setState(TowerLayerData.TowerLayerState.FREE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Pair<RankingData, Pair<Integer, PlayerData>> fightTowerLayer(long playerId, int layerId) {
/*     */     try {
/* 201 */       this.lock.lock();
/* 202 */       RankingData rankingData = new RankingData();
/* 203 */       int curtime = TimeUtil.currentTime();
/* 204 */       TowerLayerData towerLayerData = this.layerMap.get(Integer.valueOf(layerId));
/* 205 */       if (towerLayerData.getState() == TowerLayerData.TowerLayerState.FREE || towerLayerData.getOwnerId() == 0L) {
/*     */         
/* 207 */         for (TowerLayerData data : this.layerMap.values()) {
/* 208 */           if (data.getOwnerId() == playerId) {
/* 209 */             resetLayerData(data);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 214 */         towerLayerData.setOwnerId(playerId);
/* 215 */         towerLayerData.setFighterId(0L);
/* 216 */         towerLayerData.setState(TowerLayerData.TowerLayerState.OWN);
/* 217 */         towerLayerData.setOwnTime(0);
/* 218 */         getLayerData(rankingData, towerLayerData);
/* 219 */         return new Pair(rankingData, new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.CAN_OWN.getState()), null));
/*     */       } 
/*     */       
/* 222 */       if (towerLayerData.getOwnerId() == playerId) {
/* 223 */         getLayerData(rankingData, towerLayerData);
/* 224 */         return new Pair(rankingData, new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.UNKNOWN.getState()), null));
/*     */       } 
/*     */       
/* 227 */       if (towerLayerData.getState() == TowerLayerData.TowerLayerState.FIGHTING) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 232 */         getLayerData(rankingData, towerLayerData);
/* 233 */         return new Pair(rankingData, new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.FIGHTING_CAN_NOT_OWN.getState()), null));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       if (towerLayerData.getState() == TowerLayerData.TowerLayerState.OWN) {
/* 244 */         if (towerLayerData.getOwnTime() > curtime) {
/* 245 */           getLayerData(rankingData, towerLayerData);
/* 246 */           return new Pair(rankingData, new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.OWN_PROTECT.getState()), null));
/*     */         } 
/* 248 */         towerLayerData.setExpireTime(curtime + 120);
/* 249 */         towerLayerData.setState(TowerLayerData.TowerLayerState.FIGHTING);
/* 250 */         towerLayerData.setFighterId(playerId);
/* 251 */         getLayerData(rankingData, towerLayerData);
/* 252 */         return new Pair(rankingData, new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.OWN_CAN_FIGHT.getState()), this.playerMap.get(Long.valueOf(towerLayerData.getOwnerId()))));
/*     */       } 
/* 254 */       getLayerData(rankingData, towerLayerData);
/* 255 */       return new Pair(rankingData, new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.UNKNOWN.getState()), null));
/*     */     } finally {
/* 257 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Pair<Integer, RankingData> towerLayerState(long playerId, int layerId) {
/*     */     try {
/* 264 */       this.lock.lock();
/* 265 */       RankingData rankingData = new RankingData();
/* 266 */       rankingData.rank = layerId;
/* 267 */       int curtime = TimeUtil.currentTime();
/* 268 */       TowerLayerData towerLayerData = this.layerMap.get(Integer.valueOf(layerId));
/* 269 */       if (towerLayerData.getState() == TowerLayerData.TowerLayerState.FREE || towerLayerData.getOwnerId() == 0L) {
/* 270 */         rankingData.value = TowerLayerData.TowerLayerState.FREE.getState();
/* 271 */         return new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.CAN_OWN.getState()), rankingData);
/*     */       } 
/* 273 */       if (towerLayerData.getOwnerId() == playerId) {
/* 274 */         return new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.UNKNOWN.getState()), rankingData);
/*     */       }
/*     */       
/* 277 */       getLayerData(rankingData, towerLayerData);
/* 278 */       if (towerLayerData.getState() == TowerLayerData.TowerLayerState.FIGHTING) {
/* 279 */         return new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.FIGHTING_CAN_NOT_OWN.getState()), rankingData);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 285 */       if (towerLayerData.getState() == TowerLayerData.TowerLayerState.OWN) {
/* 286 */         if (curtime > towerLayerData.getOwnTime()) {
/* 287 */           return new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.OWN_CAN_FIGHT.getState()), rankingData);
/*     */         }
/* 289 */         return new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.OWN_PROTECT.getState()), rankingData);
/*     */       } 
/*     */       
/* 292 */       return new Pair(Integer.valueOf(TowerLayerData.TowerLayerFightState.UNKNOWN.getState()), rankingData);
/*     */     } finally {
/* 294 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   private void getLayerData(RankingData rankingData, TowerLayerData towerLayerData) {
/* 300 */     if (this.playerMap.containsKey(Long.valueOf(towerLayerData.getOwnerId()))) {
/* 301 */       CrossUtil.transform(rankingData, this.playerMap.get(Long.valueOf(towerLayerData.getOwnerId())));
/*     */     }
/* 303 */     rankingData.time = towerLayerData.getOwnTime();
/* 304 */     rankingData.rank = towerLayerData.getLayerId();
/* 305 */     rankingData.value = towerLayerData.getState().getState();
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
/*     */   public boolean fightResult(long playerId, long targetPlayerId, int layerId, boolean win) {
/*     */     try {
/* 318 */       this.lock.lock();
/* 319 */       TowerOwnerParameter towerOwnerParameter = (TowerOwnerParameter)ParameterConstant.getParameter(67);
/* 320 */       TowerLayerData towerLayerData = this.layerMap.get(Integer.valueOf(layerId));
/* 321 */       if (towerLayerData.getOwnerId() != targetPlayerId || towerLayerData.getFighterId() != playerId) {
/* 322 */         return false;
/*     */       }
/*     */       
/* 325 */       for (TowerLayerData data : this.layerMap.values()) {
/* 326 */         if (data.getOwnerId() == playerId) {
/* 327 */           resetLayerData(data);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 332 */       towerLayerData.setState(TowerLayerData.TowerLayerState.OWN);
/* 333 */       towerLayerData.setFighterId(0L);
/* 334 */       towerLayerData.setExpireTime(0);
/* 335 */       if (win) {
/* 336 */         towerLayerData.setOwnTime(TimeUtil.currentTime() + towerOwnerParameter.getProtectTime());
/* 337 */         towerLayerData.setOwnerId(playerId);
/*     */       } 
/* 339 */       return true;
/*     */     } finally {
/* 341 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean giveupTowerLayer(long playerId, int layerId) {
/*     */     try {
/* 347 */       this.lock.lock();
/* 348 */       TowerLayerData towerLayerData = this.layerMap.get(Integer.valueOf(layerId));
/* 349 */       if (towerLayerData.getOwnerId() != playerId) {
/* 350 */         return true;
/*     */       }
/* 352 */       if (towerLayerData.getState() != TowerLayerData.TowerLayerState.OWN) {
/* 353 */         return false;
/*     */       }
/* 355 */       resetLayerData(towerLayerData);
/* 356 */       return true;
/*     */     } finally {
/* 358 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getZoneId() {
/* 363 */     return this.zoneId;
/*     */   }
/*     */   
/*     */   public void setZoneId(int zoneId) {
/* 367 */     this.zoneId = zoneId;
/*     */   }
/*     */   
/*     */   public Set<Integer> getServerSet() {
/* 371 */     return this.serverSet;
/*     */   }
/*     */   
/*     */   public void setServerSet(Set<Integer> serverSet) {
/* 375 */     this.serverSet = serverSet;
/*     */   }
/*     */   
/*     */   public Map<Integer, TowerLayerData> getLayerMap() {
/* 379 */     return this.layerMap;
/*     */   }
/*     */   
/*     */   public void setLayerMap(Map<Integer, TowerLayerData> layerMap) {
/* 383 */     this.layerMap = layerMap;
/*     */   }
/*     */   
/*     */   public Map<Long, PlayerData> getPlayerMap() {
/* 387 */     return this.playerMap;
/*     */   }
/*     */   
/*     */   public void setPlayerMap(Map<Long, PlayerData> playerMap) {
/* 391 */     this.playerMap = playerMap;
/*     */   }
/*     */   
/*     */   public TowerZone() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\tower\TowerZone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */