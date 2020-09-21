/*     */ package com.linlongyx.sanguo.webgame.rmi.runewar;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RunewarParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarFightersData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.CoordinateData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class RunewarZone
/*     */ {
/*     */   private int zoneId;
/*  35 */   private Map<Long, CoordinateData> coordinateMap = new HashMap<>();
/*     */   
/*     */   private Set<Integer> serverList;
/*     */   @JsonIgnore
/*  39 */   private Lock lock = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RunewarZone(int zoneId, List<Integer> serverList) {
/*  46 */     this.zoneId = zoneId;
/*  47 */     this.serverList = new HashSet<>(serverList);
/*     */   }
/*     */   
/*     */   public boolean addServer(int serverId) {
/*     */     try {
/*  52 */       this.lock.lock();
/*  53 */       if (this.serverList == null) {
/*  54 */         this.serverList = new HashSet<>();
/*     */       }
/*  56 */       if (this.serverList.size() >= RunewarUtil.SERVER_SIZE) {
/*  57 */         return false;
/*     */       }
/*  59 */       return this.serverList.add(Integer.valueOf(serverId));
/*     */     } finally {
/*  61 */       this.lock.unlock();
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
/*     */   public void balanceRank() {
/*     */     try {
/*  77 */       this.lock.lock();
/*  78 */       RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*  79 */       Map<Integer, HashMap<Long, Integer>> rankMap = new HashMap<>();
/*  80 */       List<CoordinateData> dataList = (List<CoordinateData>)this.coordinateMap.values().stream().filter(d -> (d.getPoint() > 0)).sorted(Comparator.comparing(CoordinateData::getPoint).reversed().thenComparing(CoordinateData::getUpdatetime)).limit(runewarParameter.getRankSize()).collect(Collectors.toList());
/*  81 */       int rank = 0;
/*  82 */       for (CoordinateData data : dataList) {
/*  83 */         rank++;
/*  84 */         rankMap.putIfAbsent(Integer.valueOf(data.getPlayerData().getServerId()), new HashMap<>());
/*  85 */         ((HashMap<Long, Integer>)rankMap.get(Integer.valueOf(data.getPlayerData().getServerId()))).put(Long.valueOf(data.getPlayerData().getPlayerId()), Integer.valueOf(rank));
/*     */       } 
/*  87 */       for (Iterator<Integer> iterator = rankMap.keySet().iterator(); iterator.hasNext(); ) { int serverId = ((Integer)iterator.next()).intValue();
/*  88 */         LogicRmiUtil.runewarRankBalance("RunewarZone::balanceRank", serverId, rankMap.get(Integer.valueOf(serverId))); }
/*     */     
/*     */     } finally {
/*  91 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void playerJoin(PlayerData playerData) {
/*     */     try {
/*  97 */       this.lock.lock();
/*  98 */       if (!this.coordinateMap.containsKey(Long.valueOf(playerData.getPlayerId()))) {
/*  99 */         CoordinateData data = new CoordinateData(playerData);
/* 100 */         this.coordinateMap.put(Long.valueOf(playerData.getPlayerId()), data);
/* 101 */         refreshPlayerCoordinate(playerData.getPlayerId(), true);
/*     */       } else {
/* 103 */         ((CoordinateData)this.coordinateMap.get(Long.valueOf(playerData.getPlayerId()))).setPlayerData(playerData);
/*     */       } 
/*     */     } finally {
/* 106 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removePlayer(long playerId) {
/*     */     try {
/* 112 */       this.lock.lock();
/* 113 */       this.coordinateMap.remove(Long.valueOf(playerId));
/*     */     } finally {
/* 115 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updatePlayerPoint(long playerId, int point) {
/*     */     try {
/* 121 */       this.lock.lock();
/* 122 */       CoordinateData data = this.coordinateMap.get(Long.valueOf(playerId));
/* 123 */       if (data != null) {
/* 124 */         data.setPoint(point);
/*     */       }
/*     */     } finally {
/* 127 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   private List<CoordinateData> getRefreshPlayerList(long playerId, int limit) {
/* 133 */     CoordinateData data = this.coordinateMap.get(Long.valueOf(playerId));
/* 134 */     if (data == null) {
/* 135 */       return new ArrayList<>();
/*     */     }
/* 137 */     Set<Long> players = getCurAxisPlayers(data);
/*     */     
/* 139 */     RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/* 140 */     if (limit <= 0) {
/* 141 */       limit = runewarParameter.getEnemySize();
/*     */     }
/*     */     
/* 144 */     int maxPoint = data.getPoint() * (10000 + runewarParameter.getPointRange()) / 10000, minPoint = data.getPoint() * (10000 - runewarParameter.getPointRange()) / 10000;
/*     */ 
/*     */     
/* 147 */     List<CoordinateData> dataList = (List<CoordinateData>)this.coordinateMap.values().parallelStream().filter(p -> (p.getPlayerData().getPlayerId() != data.getPlayerData().getPlayerId() && !players.contains(Long.valueOf(p.getPlayerData().getPlayerId())) && minPoint <= p.getPoint() && p.getPoint() <= maxPoint)).limit(limit).collect(Collectors.toList());
/*     */     
/* 149 */     if (dataList.size() < runewarParameter.getEnemySize()) {
/* 150 */       for (CoordinateData coordinateData : dataList) {
/* 151 */         players.add(Long.valueOf(coordinateData.getPlayerData().getPlayerId()));
/*     */       }
/* 153 */       long minFightVal = data.getPlayerData().getTotalValue() * (10000L - runewarParameter.getFightValRangeLow()) / 10000L;
/* 154 */       long maxFightVal = data.getPlayerData().getTotalValue() * (10000L + runewarParameter.getFightValRangeHigh()) / 10000L;
/*     */ 
/*     */       
/* 157 */       List<CoordinateData> list = (List<CoordinateData>)this.coordinateMap.values().stream().filter(p -> (p.getPlayerData().getPlayerId() != data.getPlayerData().getPlayerId() && !players.contains(Long.valueOf(p.getPlayerData().getPlayerId())) && p.getPlayerData().getTotalValue() >= minFightVal && p.getPlayerData().getTotalValue() <= maxFightVal)).sorted((p1, p2) -> Long.compare(p2.getPlayerData().getTotalValue(), p1.getPlayerData().getTotalValue())).limit(limit).collect(Collectors.toList());
/*     */       
/* 159 */       dataList.addAll(list);
/*     */     } 
/* 161 */     if (dataList.size() > limit) {
/* 162 */       Collections.shuffle(dataList);
/* 163 */       return dataList.subList(0, limit);
/*     */     } 
/* 165 */     return dataList;
/*     */   }
/*     */   
/*     */   public List<RunewarPlayerData> refreshPlayerCoordinate(long playerId, boolean auto) {
/*     */     try {
/* 170 */       this.lock.lock();
/* 171 */       CoordinateData data = this.coordinateMap.get(Long.valueOf(playerId));
/* 172 */       if (data == null) {
/* 173 */         return null;
/*     */       }
/* 175 */       List<CoordinateData> dataList = getRefreshPlayerList(playerId, -1);
/* 176 */       int length = (data.getAxisDataList()).length;
/* 177 */       if (data.isEmpty()) {
/* 178 */         int i; for (i = 0; i < length; i++) {
/* 179 */           if (i < dataList.size()) {
/* 180 */             data.getAxisDataList()[i] = new CoordinateData.AxisData();
/* 181 */             data.getAxisDataList()[i].getLinkedList().add(new CoordinateData.NodeData(3, ((CoordinateData)dataList.get(i)).getPlayerData().getPlayerId()));
/*     */           } 
/*     */         } 
/* 184 */         if (dataList.size() > length) {
/* 185 */           for (i = 0; i < dataList.size() - length; i++) {
/* 186 */             int rand = RandUtil.randNum(0, length - 1);
/* 187 */             while (data.getAxisDataList()[rand].getLinkedList().size() > 1) {
/* 188 */               rand = RandUtil.randNum(0, length - 1);
/*     */             }
/* 190 */             data.getAxisDataList()[rand].getLinkedList().addFirst(new CoordinateData.NodeData(2, ((CoordinateData)dataList.get(i + length)).getPlayerData().getPlayerId()));
/*     */           } 
/*     */         }
/*     */       } else {
/* 194 */         int tmp = -1;
/* 195 */         for (int i = 0; i < dataList.size(); i++) {
/* 196 */           int index = i % length;
/* 197 */           if (i < length) {
/* 198 */             if (data.getAxisDataList()[index] == null || data.getAxisDataList()[index].getLinkedList().isEmpty()) {
/* 199 */               data.getAxisDataList()[index] = new CoordinateData.AxisData();
/* 200 */               data.getAxisDataList()[index].getLinkedList().add(new CoordinateData.NodeData(3, ((CoordinateData)dataList.get(i)).getPlayerData().getPlayerId()));
/*     */             } else {
/* 202 */               ((CoordinateData.NodeData)data.getAxisDataList()[index].getLinkedList().getFirst()).setPlayerId(((CoordinateData)dataList.get(i)).getPlayerData().getPlayerId());
/*     */             } 
/*     */           } else {
/* 205 */             CoordinateData.AxisData target = null;
/* 206 */             for (int j = 0; j < length; j++) {
/* 207 */               if (data.getAxisDataList()[j].getLinkedList().size() > 1 && tmp != j) {
/* 208 */                 tmp = j;
/* 209 */                 target = data.getAxisDataList()[j];
/*     */               } 
/*     */             } 
/* 212 */             if (target != null) {
/* 213 */               ((CoordinateData.NodeData)target.getLinkedList().getLast()).setPlayerId(((CoordinateData)dataList.get(i)).getPlayerData().getPlayerId());
/*     */             } else {
/* 215 */               int rand = RandUtil.randNum(0, length - 1);
/* 216 */               while (data.getAxisDataList()[rand].getLinkedList().size() > 1) {
/* 217 */                 rand = RandUtil.randNum(0, length - 1);
/*     */               }
/* 219 */               int distance = ((CoordinateData.NodeData)data.getAxisDataList()[rand].getLinkedList().getFirst()).getDistance();
/* 220 */               if (distance == 3) {
/* 221 */                 ((CoordinateData.NodeData)data.getAxisDataList()[rand].getLinkedList().getFirst()).setDistance(2);
/*     */               }
/* 223 */               data.getAxisDataList()[rand].getLinkedList().addLast(new CoordinateData.NodeData(3, ((CoordinateData)dataList.get(i)).getPlayerData().getPlayerId()));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 228 */       return auto ? null : getRunewardPlayerData(playerId);
/*     */     } finally {
/* 230 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public CoordinateData getCoordinateDataByPlayerId(long playerId) {
/*     */     try {
/* 237 */       this.lock.lock();
/* 238 */       return this.coordinateMap.get(Long.valueOf(playerId));
/*     */     } finally {
/* 240 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public PlayerData getPlayerData(long targetPlayerId) {
/*     */     try {
/* 247 */       this.lock.lock();
/* 248 */       return ((CoordinateData)this.coordinateMap.get(Long.valueOf(targetPlayerId))).getPlayerData();
/*     */     } finally {
/* 250 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public List<RankingData> getRankList() {
/*     */     try {
/* 257 */       this.lock.lock();
/* 258 */       RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/* 259 */       List<RankingData> rankList = new ArrayList<>();
/* 260 */       List<CoordinateData> dataList = (List<CoordinateData>)this.coordinateMap.values().stream().filter(d -> (d.getPoint() > 0)).sorted(Comparator.comparing(CoordinateData::getPoint).reversed()).limit(runewarParameter.getRankLimit()).collect(Collectors.toList());
/* 261 */       int i = 0;
/* 262 */       for (CoordinateData data : dataList) {
/* 263 */         i++;
/* 264 */         RankingData rankingData = CrossUtil.transform(data.getPlayerData());
/* 265 */         rankingData.rank = i;
/* 266 */         rankingData.value = data.getPoint();
/* 267 */         rankList.add(rankingData);
/*     */       } 
/* 269 */       return rankList;
/*     */     } finally {
/* 271 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public List<RunewarPlayerData> getRunewardPlayerData(long playerId) {
/*     */     try {
/* 278 */       this.lock.lock();
/* 279 */       List<RunewarPlayerData> dataList = new ArrayList<>();
/* 280 */       CoordinateData data = this.coordinateMap.get(Long.valueOf(playerId));
/* 281 */       dataList.add(RunewarUtil.transform(data, 0, 0));
/*     */       
/* 283 */       for (int i = 0; i < (data.getAxisDataList()).length; i++) {
/* 284 */         CoordinateData.AxisData axisData = data.getAxisDataList()[i];
/* 285 */         if (axisData != null) {
/* 286 */           for (CoordinateData.NodeData nodeData : axisData.getLinkedList()) {
/* 287 */             CoordinateData tmp = this.coordinateMap.get(Long.valueOf(nodeData.getPlayerId()));
/* 288 */             if (tmp != null) {
/* 289 */               dataList.add(RunewarUtil.transform(tmp, i, nodeData.getDistance()));
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/* 294 */       return dataList;
/*     */     } finally {
/* 296 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public RunewarFightersData getRunewarFighterData(long playerId, long targetPlayerId) {
/*     */     try {
/* 303 */       this.lock.lock();
/* 304 */       if (!isExist(playerId, targetPlayerId)) {
/* 305 */         return null;
/*     */       }
/* 307 */       RunewarFightersData resultData = new RunewarFightersData();
/* 308 */       CoordinateData targetData = this.coordinateMap.get(Long.valueOf(targetPlayerId));
/*     */ 
/*     */       
/* 311 */       for (int i = 1; i <= 6; i++) {
/* 312 */         IntIntValue intIntValue = new IntIntValue();
/* 313 */         if (targetData.getPlayerData().getFighters() != null && targetData.getPlayerData().getFighters().containsKey(Integer.valueOf(i))) {
/* 314 */           CommonFighterData commonFighterData = (CommonFighterData)targetData.getPlayerData().getFighters().get(Integer.valueOf(i));
/* 315 */           intIntValue.key = (int)commonFighterData.getId();
/* 316 */           if (commonFighterData.getType() == 0) {
/* 317 */             intIntValue.value = ((CommonFighterData)targetData.getPlayerData().getFighters().get(Integer.valueOf(i))).getQuality();
/*     */           }
/*     */         } 
/* 320 */         resultData.fighters.add(intIntValue);
/*     */       } 
/*     */ 
/*     */       
/* 324 */       if (targetData.getPlayerData().getFighters() != null && targetData.getPlayerData().getFighters().containsKey(Integer.valueOf(0))) {
/* 325 */         resultData.subFihgters.add(Integer.valueOf((int)((CommonFighterData)targetData.getPlayerData().getFighters().get(Integer.valueOf(0))).getId()));
/*     */       } else {
/* 327 */         resultData.subFihgters.add(Integer.valueOf(0));
/*     */       } 
/*     */ 
/*     */       
/* 331 */       if (targetData.getPlayerData().getFighters() != null && targetData.getPlayerData().getFighters().containsKey(Integer.valueOf(10))) {
/* 332 */         resultData.subFihgters.add(Integer.valueOf((int)((CommonFighterData)targetData.getPlayerData().getFighters().get(Integer.valueOf(10))).getId()));
/*     */       } else {
/* 334 */         resultData.subFihgters.add(Integer.valueOf(0));
/*     */       } 
/*     */ 
/*     */       
/* 338 */       if (targetData.getPlayerData().getZhenfa() != null) {
/* 339 */         resultData.subFihgters.add(targetData.getPlayerData().getZhenfa().getKey());
/*     */       } else {
/* 341 */         resultData.subFihgters.add(Integer.valueOf(0));
/*     */       } 
/* 343 */       return resultData;
/*     */     } finally {
/* 345 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public PlayerData getRunewarTargetPlayerdata(long playerId, long targetPlayerId) {
/*     */     try {
/* 352 */       this.lock.lock();
/*     */       
/* 354 */       if (!isExist(playerId, targetPlayerId)) {
/* 355 */         return null;
/*     */       }
/* 357 */       CoordinateData targetData = this.coordinateMap.get(Long.valueOf(targetPlayerId));
/* 358 */       if (targetData == null) {
/* 359 */         return null;
/*     */       }
/* 361 */       return targetData.getPlayerData();
/*     */     } finally {
/* 363 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   private boolean isExist(long playerId, long targetPlayerId) {
/*     */     try {
/* 370 */       this.lock.lock();
/* 371 */       boolean exist = false;
/* 372 */       CoordinateData data = this.coordinateMap.get(Long.valueOf(playerId));
/* 373 */       for (CoordinateData.AxisData axisData : data.getAxisDataList()) {
/* 374 */         if (axisData != null) {
/* 375 */           for (CoordinateData.NodeData nodeData : axisData.getLinkedList()) {
/* 376 */             if (nodeData.getPlayerId() == targetPlayerId) {
/* 377 */               exist = true;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/* 383 */       return exist;
/*     */     } finally {
/* 385 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int runewarFightResult(long playerId, long targetPlaeyrId, int point, boolean win) {
/*     */     try {
/* 391 */       this.lock.lock();
/* 392 */       int decHp = 0;
/* 393 */       CoordinateData data = this.coordinateMap.get(Long.valueOf(playerId));
/* 394 */       data.setPoint(point);
/* 395 */       data.setUpdatetime(System.currentTimeMillis());
/*     */       
/* 397 */       RunewarParameter runewarParameter = (RunewarParameter)ParameterConstant.getParameter(45);
/*     */       
/* 399 */       if (win) {
/* 400 */         int targetIndex = -1, toAddIndex = -1, tmp = -1;
/* 401 */         for (int i = 0; i < (data.getAxisDataList()).length; i++) {
/* 402 */           CoordinateData.AxisData axisData = data.getAxisDataList()[i];
/* 403 */           if (axisData != null) {
/* 404 */             boolean remove = axisData.getLinkedList().removeIf(nodeData -> (nodeData.getPlayerId() == targetPlaeyrId));
/* 405 */             if (remove) {
/* 406 */               toAddIndex = i;
/*     */             }
/*     */             
/* 409 */             if (!axisData.getLinkedList().isEmpty() && 
/* 410 */               tmp < ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).getDistance()) {
/* 411 */               tmp = ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).getDistance();
/* 412 */               targetIndex = i;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 419 */         boolean moveTail = (toAddIndex != -1 && data.getAxisDataList()[toAddIndex].getLinkedList().size() > 0 && ((CoordinateData.NodeData)data.getAxisDataList()[toAddIndex].getLinkedList().getLast()).getDistance() == 3);
/* 420 */         if (!moveTail && targetIndex != -1) {
/* 421 */           CoordinateData.AxisData axisData = data.getAxisDataList()[targetIndex];
/*     */           
/* 423 */           if (!axisData.getLinkedList().isEmpty()) {
/* 424 */             int maxDistance = ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).getDistance();
/*     */ 
/*     */             
/* 427 */             boolean[] checkPoint = new boolean[maxDistance];
/* 428 */             for (CoordinateData.NodeData nodeData : axisData.getLinkedList()) {
/* 429 */               checkPoint[nodeData.getDistance() - 1] = true;
/*     */             }
/* 431 */             if (maxDistance - 1 > 0) {
/* 432 */               if (!checkPoint[maxDistance - 2]) {
/* 433 */                 ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).setDistance(maxDistance - 1);
/*     */               } else {
/* 435 */                 int count = 0;
/* 436 */                 for (int j = maxDistance - 1; j >= 0 && 
/* 437 */                   checkPoint[j]; j--) {
/* 438 */                   count++;
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/* 443 */                 CoordinateData.NodeData nodeData = axisData.getLinkedList().get(axisData.getLinkedList().size() - count);
/* 444 */                 nodeData.setDistance(nodeData.getDistance() - 1);
/*     */               } 
/*     */             } else {
/* 447 */               ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).setDistance(maxDistance - 1);
/*     */             } 
/*     */             
/* 450 */             int distance = ((CoordinateData.NodeData)axisData.getLinkedList().getFirst()).getDistance();
/* 451 */             if (distance <= 0) {
/* 452 */               decHp++;
/* 453 */               axisData.getLinkedList().removeFirst();
/* 454 */               addRandomNodeToAxisTail(targetIndex, data);
/*     */             } else {
/* 456 */               ((CoordinateData.NodeData)axisData.getLinkedList().getFirst()).setDistance(distance);
/*     */             } 
/*     */           } 
/*     */         } 
/* 460 */         if (toAddIndex != -1) {
/* 461 */           addRandomNodeToAxisTail(toAddIndex, data);
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 467 */         boolean[] affectAxis = new boolean[(data.getAxisDataList()).length];
/* 468 */         int affectAxisCount = 0;
/*     */         
/* 470 */         for (int i = 0; i < (data.getAxisDataList()).length; i++) {
/* 471 */           CoordinateData.AxisData axisData = data.getAxisDataList()[i];
/* 472 */           if (axisData != null && !axisData.getLinkedList().isEmpty()) {
/* 473 */             boolean remove = false;
/* 474 */             for (Iterator<CoordinateData.NodeData> it = axisData.getLinkedList().iterator(); it.hasNext(); ) {
/* 475 */               CoordinateData.NodeData nodeData = it.next();
/* 476 */               int distance = nodeData.getDistance() - 1;
/* 477 */               nodeData.setDistance(distance);
/* 478 */               if (distance == 0) {
/* 479 */                 it.remove();
/* 480 */                 decHp++;
/* 481 */                 remove = true;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 486 */             if (remove) {
/* 487 */               affectAxis[i] = true;
/* 488 */               affectAxisCount++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 493 */         if (decHp > 0) {
/*     */ 
/*     */           
/* 496 */           List<CoordinateData> dataList = getRefreshPlayerList(playerId, affectAxisCount);
/* 497 */           for (int j = 0; j < affectAxis.length; j++) {
/* 498 */             if (affectAxis[j] && j < dataList.size()) {
/* 499 */               data.getAxisDataList()[j].getLinkedList().addLast(new CoordinateData.NodeData(3, ((CoordinateData)dataList.get(j)).getPlayerData().getPlayerId()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 504 */       return decHp;
/*     */     } finally {
/* 506 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   private Set<Long> getCurAxisPlayers(CoordinateData data) {
/*     */     try {
/* 513 */       this.lock.lock();
/* 514 */       Set<Long> players = new HashSet<>();
/* 515 */       for (CoordinateData.AxisData axisDataArr : data.getAxisDataList()) {
/* 516 */         if (axisDataArr != null && !axisDataArr.getLinkedList().isEmpty()) {
/* 517 */           for (CoordinateData.NodeData nodeData : axisDataArr.getLinkedList()) {
/* 518 */             players.add(Long.valueOf(nodeData.getPlayerId()));
/*     */           }
/*     */         }
/*     */       } 
/* 522 */       return players;
/*     */     } finally {
/* 524 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean addRandomNodeToAxisTail(int axis, CoordinateData data) {
/*     */     try {
/* 530 */       this.lock.lock();
/* 531 */       boolean move = false;
/* 532 */       CoordinateData.AxisData axisData = data.getAxisDataList()[axis];
/* 533 */       if (axisData.getLinkedList().size() > 0 && ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).getDistance() == 3) {
/* 534 */         ((CoordinateData.NodeData)axisData.getLinkedList().getLast()).setDistance(2);
/* 535 */         move = true;
/*     */       } 
/* 537 */       List<CoordinateData> newNodes = getRefreshPlayerList(data.getPlayerData().getPlayerId(), 20);
/* 538 */       Collections.shuffle(newNodes);
/* 539 */       if (!newNodes.isEmpty()) {
/* 540 */         axisData.getLinkedList().addLast(new CoordinateData.NodeData(3, ((CoordinateData)newNodes.get(0)).getPlayerData().getPlayerId()));
/*     */       }
/* 542 */       return move;
/*     */     } finally {
/* 544 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getZoneId() {
/* 549 */     return this.zoneId;
/*     */   }
/*     */   
/*     */   public void setZoneId(int zoneId) {
/* 553 */     this.zoneId = zoneId;
/*     */   }
/*     */   
/*     */   public Map<Long, CoordinateData> getCoordinateMap() {
/* 557 */     return this.coordinateMap;
/*     */   }
/*     */   
/*     */   public void setCoordinateMap(Map<Long, CoordinateData> coordinateMap) {
/* 561 */     this.coordinateMap = coordinateMap;
/*     */   }
/*     */   
/*     */   public Set<Integer> getServerList() {
/* 565 */     return this.serverList;
/*     */   }
/*     */   
/*     */   public void setServerList(Set<Integer> serverList) {
/* 569 */     this.serverList = serverList;
/*     */   }
/*     */   
/*     */   public RunewarZone() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\runewar\RunewarZone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */