/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny.match;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyBetRecord;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.BetData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.group.HalfMatchGroup;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.group.QuarterMatchGroup;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class Zone implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int zoneId;
/*     */   private int matchId;
/*     */   private String zoneKey;
/*     */   private ZoneState zoneState;
/*     */   
/*     */   public enum ZoneState {
/*  25 */     empty(0),
/*  26 */     notFull(1),
/*  27 */     full(2);
/*     */     
/*     */     private int state;
/*     */     
/*     */     ZoneState(int state) {
/*  32 */       this.state = state;
/*     */     }
/*     */     
/*     */     public int getState() {
/*  36 */       return this.state;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private Set<Long> zonePlayers = new HashSet<>();
/*     */   
/*  46 */   private Map<Long, Integer> playerIdToServerIdMap = new HashMap<>();
/*     */   
/*  48 */   private QuarterMatchGroup[] quarterMatchGroups = new QuarterMatchGroup[4];
/*     */   
/*     */   private HalfMatchGroup halfMatchGroup;
/*     */   @JsonIgnore
/*  52 */   private Map<String, PkData> pkRecordMap = new HashMap<>();
/*     */ 
/*     */   
/*  55 */   private Map<String, ConcurrentHashMap<Long, AtomicInteger>> betNumMap = new HashMap<>();
/*     */   
/*  57 */   private Map<String, Map<Long, List<BetData>>> playerBetNumMap = new HashMap<>();
/*     */   
/*  59 */   private Map<Long, Integer> betPlayerIdToServerIdMap = new HashMap<>();
/*     */   
/*     */   private volatile String currentBetPkId;
/*     */   
/*  63 */   private Set<Long> curMatchPlayers = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Zone() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Zone(int matchId, int zoneId) {
/*  73 */     this.matchId = matchId;
/*  74 */     this.zoneId = zoneId;
/*  75 */     this.zoneKey = matchId + "_" + zoneId;
/*     */   }
/*     */   
/*     */   public void init() {
/*  79 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/*  80 */       if (quarterMatchGroup != null) {
/*  81 */         for (PkData pkData : quarterMatchGroup.getAllPkData()) {
/*  82 */           if (pkData != null) {
/*  83 */             this.pkRecordMap.put(pkData.getId(), pkData);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*  88 */     if (this.halfMatchGroup != null) {
/*  89 */       for (PkData pkData : this.halfMatchGroup.getAllPkData()) {
/*  90 */         if (pkData != null) {
/*  91 */           this.pkRecordMap.put(pkData.getId(), pkData);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<String, PkData> pkRecords() {
/*  98 */     return this.pkRecordMap;
/*     */   }
/*     */   public int addPlayerBetNum(int serverId, String pkId, long playerId, long targetPlayerId, int betNum, int type) {
/*     */     int current, update;
/* 102 */     if (!MatchUtil.canBet() || !this.currentBetPkId.equals(pkId)) {
/* 103 */       return -1;
/*     */     }
/* 105 */     this.betPlayerIdToServerIdMap.putIfAbsent(Long.valueOf(playerId), Integer.valueOf(serverId));
/* 106 */     this.playerBetNumMap.putIfAbsent(pkId, new HashMap<>());
/* 107 */     ((Map)this.playerBetNumMap.get(pkId)).putIfAbsent(Long.valueOf(playerId), new ArrayList());
/* 108 */     this.betNumMap.putIfAbsent(pkId, new ConcurrentHashMap<>());
/* 109 */     BetData targetBetData = null;
/* 110 */     for (BetData betData : ((Map)this.playerBetNumMap.get(pkId)).get(Long.valueOf(playerId))) {
/* 111 */       if (betData.getPlayerId() == targetPlayerId) {
/* 112 */         targetBetData = betData;
/*     */         break;
/*     */       } 
/*     */     } 
/* 116 */     if (targetBetData == null) {
/* 117 */       targetBetData = new BetData(targetPlayerId, 0);
/* 118 */       ((List<BetData>)((Map)this.playerBetNumMap.get(pkId)).get(Long.valueOf(playerId))).add(targetBetData);
/*     */     } 
/* 120 */     int curBetNum = targetBetData.getBetNum();
/* 121 */     int afterBetNum = curBetNum + betNum;
/* 122 */     AtomicInteger preValue = ((ConcurrentHashMap<Long, AtomicInteger>)this.betNumMap.get(pkId)).putIfAbsent(Long.valueOf(targetPlayerId), new AtomicInteger(betNum));
/* 123 */     if (preValue == null) {
/*     */       
/* 125 */       targetBetData.setBetNum(afterBetNum);
/* 126 */       return (type == 0) ? afterBetNum : betNum;
/*     */     } 
/* 128 */     AtomicInteger curValue = (AtomicInteger)((ConcurrentHashMap)this.betNumMap.get(pkId)).get(Long.valueOf(targetPlayerId));
/*     */     
/*     */     do {
/* 131 */       current = curValue.get();
/* 132 */       update = current + betNum;
/* 133 */     } while (!curValue.compareAndSet(current, update));
/*     */     
/* 135 */     targetBetData.setBetNum(afterBetNum);
/* 136 */     return (type == 0) ? afterBetNum : update;
/*     */   }
/*     */   
/*     */   private void updateCurrentBetPkId(List<PkData> pkDataList) {
/* 140 */     if (pkDataList.isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 145 */     List<PkData> fullPkDataList = new ArrayList<>();
/* 146 */     List<PkData> halfPkDataList = new ArrayList<>();
/* 147 */     for (PkData pkData : pkDataList) {
/* 148 */       if (pkData != null) {
/* 149 */         if (pkData.getRightPlayer() != null && pkData.getLeftPlayer() != null) {
/* 150 */           fullPkDataList.add(pkData);
/*     */         } else {
/* 152 */           halfPkDataList.add(pkData);
/*     */         } 
/*     */         
/* 155 */         if (pkData.getLeftPlayer() != null) {
/* 156 */           this.curMatchPlayers.add(Long.valueOf(pkData.getLeftPlayer().getPlayerId()));
/*     */         }
/* 158 */         if (pkData.getRightPlayer() != null) {
/* 159 */           this.curMatchPlayers.add(Long.valueOf(pkData.getRightPlayer().getPlayerId()));
/*     */         }
/*     */       } 
/*     */     } 
/* 163 */     if (fullPkDataList.isEmpty() && halfPkDataList.isEmpty())
/*     */       return; 
/* 165 */     long balance = Long.MAX_VALUE;
/* 166 */     String tmpPkId = null;
/* 167 */     if (!fullPkDataList.isEmpty()) {
/* 168 */       for (PkData pkData : fullPkDataList) {
/* 169 */         long tmp = Math.abs(pkData.getLeftPlayer().getFightValue() - pkData.getRightPlayer().getFightValue());
/* 170 */         if (tmp < balance) {
/* 171 */           balance = tmp;
/* 172 */           tmpPkId = pkData.getId();
/*     */         } 
/*     */       } 
/* 175 */       this.currentBetPkId = tmpPkId;
/* 176 */       this.betNumMap.putIfAbsent(tmpPkId, new ConcurrentHashMap<>());
/* 177 */       this.playerBetNumMap.putIfAbsent(tmpPkId, new HashMap<>());
/*     */     } else {
/* 179 */       balance = 0L;
/* 180 */       for (PkData pkData : halfPkDataList) {
/* 181 */         PkPlayerData pkPlayerData = null;
/* 182 */         if (pkData.getLeftPlayer() != null) {
/* 183 */           pkPlayerData = pkData.getLeftPlayer();
/*     */         }
/* 185 */         if (pkData.getRightPlayer() != null) {
/* 186 */           pkPlayerData = pkData.getRightPlayer();
/*     */         }
/* 188 */         if (pkPlayerData != null) {
/* 189 */           long tmp = pkPlayerData.getFightValue();
/* 190 */           if (balance < tmp) {
/* 191 */             balance = tmp;
/* 192 */             tmpPkId = pkData.getId();
/*     */           } 
/*     */         } 
/*     */       } 
/* 196 */       this.currentBetPkId = tmpPkId;
/* 197 */       this.betNumMap.putIfAbsent(tmpPkId, new ConcurrentHashMap<>());
/* 198 */       this.playerBetNumMap.putIfAbsent(tmpPkId, new HashMap<>());
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public boolean isEmpty() {
/* 204 */     return (getZoneState() == ZoneState.empty);
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
/*     */   public void balanceRank() {
/* 229 */     if (isEmpty())
/*     */       return; 
/* 231 */     Map<Long, Integer> rewardPlayerMap = new HashMap<>();
/* 232 */     if (this.halfMatchGroup != null) {
/* 233 */       if (this.halfMatchGroup.getFinalPkData() != null) {
/* 234 */         PkData pkData = this.halfMatchGroup.getFinalPkData();
/* 235 */         rewardPlayerMap.put(Long.valueOf(pkData.getWinner()), Integer.valueOf(MatchUtil.MatchRewardType.reward1.getType()));
/* 236 */         if (pkData.getRightPlayer() != null) {
/* 237 */           if (pkData.getLeftPlayer().getPlayerId() == pkData.getWinner()) {
/* 238 */             rewardPlayerMap.put(Long.valueOf(pkData.getRightPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward2.getType()));
/*     */           } else {
/* 240 */             rewardPlayerMap.put(Long.valueOf(pkData.getLeftPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward2.getType()));
/*     */           } 
/*     */         }
/*     */       } 
/* 244 */       if (this.halfMatchGroup.getHalfPkData() != null) {
/* 245 */         for (PkData pkData : this.halfMatchGroup.getHalfPkData()) {
/* 246 */           if (pkData != null) {
/* 247 */             if (pkData.getLeftPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getLeftPlayer().getPlayerId()))) {
/* 248 */               rewardPlayerMap.put(Long.valueOf(pkData.getLeftPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward4.getType()));
/*     */             }
/* 250 */             if (pkData.getRightPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getRightPlayer().getPlayerId()))) {
/* 251 */               rewardPlayerMap.put(Long.valueOf(pkData.getRightPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward4.getType()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 257 */     if (this.quarterMatchGroups != null) {
/* 258 */       for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 259 */         if (quarterMatchGroup != null) {
/* 260 */           if (quarterMatchGroup.getFinalPkData() != null) {
/* 261 */             PkData pkData = quarterMatchGroup.getFinalPkData();
/* 262 */             if (pkData != null) {
/* 263 */               if (pkData.getLeftPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getLeftPlayer().getPlayerId()))) {
/* 264 */                 rewardPlayerMap.put(Long.valueOf(pkData.getLeftPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward8.getType()));
/*     */               }
/* 266 */               if (pkData.getRightPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getRightPlayer().getPlayerId()))) {
/* 267 */                 rewardPlayerMap.put(Long.valueOf(pkData.getRightPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward8.getType()));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 272 */           for (PkData pkData : quarterMatchGroup.getHalfPkData()) {
/* 273 */             if (pkData != null) {
/* 274 */               if (pkData.getLeftPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getLeftPlayer().getPlayerId()))) {
/* 275 */                 rewardPlayerMap.put(Long.valueOf(pkData.getLeftPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward16.getType()));
/*     */               }
/* 277 */               if (pkData.getRightPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getRightPlayer().getPlayerId()))) {
/* 278 */                 rewardPlayerMap.put(Long.valueOf(pkData.getRightPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward16.getType()));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 283 */           for (PkData pkData : quarterMatchGroup.getQuarterPkData()) {
/* 284 */             if (pkData != null) {
/* 285 */               if (pkData.getLeftPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getLeftPlayer().getPlayerId()))) {
/* 286 */                 rewardPlayerMap.put(Long.valueOf(pkData.getLeftPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward32.getType()));
/*     */               }
/* 288 */               if (pkData.getRightPlayer() != null && !rewardPlayerMap.containsKey(Long.valueOf(pkData.getRightPlayer().getPlayerId()))) {
/* 289 */                 rewardPlayerMap.put(Long.valueOf(pkData.getRightPlayer().getPlayerId()), Integer.valueOf(MatchUtil.MatchRewardType.reward32.getType()));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 297 */     for (Map.Entry<Long, Integer> kv : rewardPlayerMap.entrySet()) {
/* 298 */       LogicRmiManager.sendDestinyMatchReward(((Integer)this.playerIdToServerIdMap.get(kv.getKey())).intValue(), ((Long)kv.getKey()).longValue(), ((Integer)kv.getValue()).intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void balanceBet() {
/* 307 */     this.curMatchPlayers.clear();
/*     */     
/* 309 */     if (isEmpty())
/*     */       return; 
/* 311 */     if (this.currentBetPkId == null || this.currentBetPkId.isEmpty())
/* 312 */       return;  long winner = ((PkData)this.pkRecordMap.get(this.currentBetPkId)).getWinner();
/* 313 */     if (winner == 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 317 */     if (!this.playerBetNumMap.containsKey(this.currentBetPkId))
/*     */       return; 
/* 319 */     Map<Long, List<BetData>> betDataMap = this.playerBetNumMap.get(this.currentBetPkId);
/*     */     
/* 321 */     Map<Integer, ArrayList<BetData>> winBetPlayerMap = new HashMap<>();
/* 322 */     for (Map.Entry<Long, List<BetData>> kv : betDataMap.entrySet()) {
/* 323 */       if (this.betPlayerIdToServerIdMap.containsKey(kv.getKey())) {
/* 324 */         winBetPlayerMap.putIfAbsent(this.betPlayerIdToServerIdMap.get(kv.getKey()), new ArrayList<>());
/* 325 */         for (BetData betData : kv.getValue()) {
/* 326 */           if (betData.getPlayerId() == winner)
/* 327 */             ((ArrayList<BetData>)winBetPlayerMap.get(this.betPlayerIdToServerIdMap.get(kv.getKey()))).add(new BetData(((Long)kv.getKey()).longValue(), betData.getBetNum() * 2)); 
/*     */         } 
/*     */         continue;
/*     */       } 
/* 331 */       LogUtil.errorLog(new Object[] { "player balance error, has no server", kv.getKey() });
/*     */     } 
/*     */     
/* 334 */     LogicRmiManager.sendDestinyBetReward(this.currentBetPkId, winBetPlayerMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean zoneQuarterMatchQuarterInit(List<DestinyPlayerData> destinyPlayerDataList) {
/* 344 */     if (destinyPlayerDataList == null || destinyPlayerDataList.size() == 0) {
/* 345 */       this.zoneState = ZoneState.empty;
/*     */       
/* 347 */       return false;
/*     */     } 
/* 349 */     destinyPlayerDataList.sort((Comparator<? super DestinyPlayerData>)new DestinyRankComparator());
/* 350 */     this.zoneState = (destinyPlayerDataList.size() < 32) ? ZoneState.notFull : ZoneState.full;
/* 351 */     Map<Long, Integer> rankMap = new HashMap<>();
/* 352 */     int rank = 1;
/* 353 */     for (DestinyPlayerData destinyPlayerData : destinyPlayerDataList) {
/* 354 */       rankMap.put(Long.valueOf(destinyPlayerData.getPlayerId()), Integer.valueOf(rank));
/* 355 */       rank++;
/*     */     } 
/*     */     
/* 358 */     Collections.shuffle(destinyPlayerDataList);
/* 359 */     for (int i = 0; i < destinyPlayerDataList.size(); i++) {
/* 360 */       this.zonePlayers.add(Long.valueOf(((DestinyPlayerData)destinyPlayerDataList.get(i)).getPlayerId()));
/* 361 */       this.playerIdToServerIdMap.putIfAbsent(Long.valueOf(((DestinyPlayerData)destinyPlayerDataList.get(i)).getPlayerId()), Integer.valueOf(((DestinyPlayerData)destinyPlayerDataList.get(i)).getServerId()));
/* 362 */       int groupIndex = i / 8;
/* 363 */       int pkIndex = i % 8;
/* 364 */       if (this.quarterMatchGroups[groupIndex] == null) {
/* 365 */         this.quarterMatchGroups[groupIndex] = new QuarterMatchGroup(groupIndex + 1, this.zoneKey + "_" + (groupIndex + 1));
/*     */       }
/*     */       
/* 368 */       this.quarterMatchGroups[groupIndex].getGroupPlayerMap().put(Long.valueOf(((DestinyPlayerData)destinyPlayerDataList.get(i)).getPlayerId()), 
/* 369 */           DestinyCache.transform(destinyPlayerDataList.get(i), ((Integer)rankMap.get(Long.valueOf(((DestinyPlayerData)destinyPlayerDataList.get(i)).getPlayerId()))).intValue()));
/*     */       
/* 371 */       this.quarterMatchGroups[groupIndex].initQuarterPkData(this, destinyPlayerDataList.get(i), pkIndex);
/*     */     } 
/*     */     
/* 374 */     List<PkData> pkDataList = new ArrayList<>();
/* 375 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 376 */       if (quarterMatchGroup != null) {
/* 377 */         for (PkData pkData : quarterMatchGroup.getQuarterPkData()) {
/* 378 */           if (pkData != null) {
/* 379 */             pkDataList.add(pkData);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 384 */     updateCurrentBetPkId(pkDataList);
/* 385 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneQuarterMatchQuarterFight() {
/* 392 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 393 */       if (quarterMatchGroup != null) {
/* 394 */         quarterMatchGroup.quarterPk(this);
/*     */       }
/*     */     } 
/* 397 */     balanceBet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneQuarterMatchHalfInit() {
/* 404 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 405 */       if (quarterMatchGroup != null) {
/* 406 */         quarterMatchGroup.initHalfPkData(this);
/*     */       }
/*     */     } 
/*     */     
/* 410 */     List<PkData> pkDataList = new ArrayList<>();
/* 411 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 412 */       if (quarterMatchGroup != null) {
/* 413 */         for (PkData pkData : quarterMatchGroup.getHalfPkData()) {
/* 414 */           if (pkData != null) {
/* 415 */             pkDataList.add(pkData);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 420 */     updateCurrentBetPkId(pkDataList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneQuarterMatchHalfFight() {
/* 427 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 428 */       if (quarterMatchGroup != null) {
/* 429 */         quarterMatchGroup.halfPk(this);
/*     */       }
/*     */     } 
/* 432 */     balanceBet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneQuarterMatchFinalInit() {
/* 439 */     List<PkData> pkDataList = new ArrayList<>();
/* 440 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 441 */       if (quarterMatchGroup != null) {
/* 442 */         quarterMatchGroup.initFinalPkData(this);
/* 443 */         PkData finalPkData = quarterMatchGroup.getFinalPkData();
/* 444 */         if (finalPkData != null) {
/* 445 */           pkDataList.add(finalPkData);
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
/* 456 */     updateCurrentBetPkId(pkDataList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneQuarterMatchFinalFight() {
/* 463 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 464 */       if (quarterMatchGroup != null) {
/* 465 */         quarterMatchGroup.finalPk(this);
/*     */       }
/*     */     } 
/* 468 */     balanceBet();
/*     */   }
/*     */   
/*     */   public boolean zoneHalfMatchHalfInit() {
/* 472 */     if (this.zoneState == ZoneState.empty) {
/* 473 */       return false;
/*     */     }
/* 475 */     List<PkPlayerData> pkPlayerDataList = new ArrayList<>();
/* 476 */     if (this.halfMatchGroup == null) {
/* 477 */       int groupId = 5;
/* 478 */       this.halfMatchGroup = new HalfMatchGroup(groupId, this.zoneKey + "_" + groupId);
/*     */     } 
/* 480 */     for (QuarterMatchGroup quarterMatchGroup : this.quarterMatchGroups) {
/* 481 */       if (quarterMatchGroup != null) {
/* 482 */         PkPlayerData pkPlayerData = quarterMatchGroup.getFinalWinner();
/* 483 */         if (pkPlayerData != null) {
/* 484 */           pkPlayerDataList.add(pkPlayerData);
/* 485 */           this.halfMatchGroup.getGroupPlayerMap().put(Long.valueOf(pkPlayerData.getPlayerId()), quarterMatchGroup.getGroupPlayerMap().get(Long.valueOf(pkPlayerData.getPlayerId())));
/*     */         } 
/*     */       } 
/*     */     } 
/* 489 */     this.halfMatchGroup.initHalfPkData(this, pkPlayerDataList);
/*     */     
/* 491 */     List<PkData> pkDataList = new ArrayList<>();
/* 492 */     for (PkData pkData : this.halfMatchGroup.getHalfPkData()) {
/* 493 */       if (pkData != null) {
/* 494 */         pkDataList.add(pkData);
/*     */       }
/*     */     } 
/* 497 */     updateCurrentBetPkId(pkDataList);
/* 498 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneHalfMatchHalfFight() {
/* 505 */     this.halfMatchGroup.halfPk(this);
/* 506 */     balanceBet();
/*     */   }
/*     */   
/*     */   public boolean zoneHalfMatchFinalInit() {
/* 510 */     this.halfMatchGroup.initFinalPkData(this);
/*     */     
/* 512 */     List<PkData> pkDataList = new ArrayList<>();
/* 513 */     if (this.halfMatchGroup.getFinalPkData() != null) {
/* 514 */       pkDataList.add(this.halfMatchGroup.getFinalPkData());
/*     */     }
/* 516 */     updateCurrentBetPkId(pkDataList);
/* 517 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void zoneHalfMatchFinalFight() {
/* 524 */     this.halfMatchGroup.finalPk(this);
/* 525 */     balanceBet();
/*     */   }
/*     */ 
/*     */   
/*     */   public PkRecord getPkRecord(String pkId) {
/* 530 */     return ((PkData)this.pkRecordMap.get(pkId)).getPkRecord();
/*     */   }
/*     */   
/*     */   public ArrayList<DestinyBetRecord> getPlayerBetRecord(long playerId) {
/* 534 */     ArrayList<DestinyBetRecord> resultList = new ArrayList<>();
/* 535 */     for (Map.Entry<String, Map<Long, List<BetData>>> kv : this.playerBetNumMap.entrySet()) {
/* 536 */       for (Map.Entry<Long, List<BetData>> betKv : (Iterable<Map.Entry<Long, List<BetData>>>)((Map)kv.getValue()).entrySet()) {
/* 537 */         if (((Long)betKv.getKey()).longValue() == playerId) {
/* 538 */           for (BetData betData : betKv.getValue()) {
/* 539 */             DestinyBetRecord record = new DestinyBetRecord();
/* 540 */             PkData pkData = this.pkRecordMap.get(kv.getKey());
/* 541 */             if (pkData == null)
/* 542 */               continue;  record.leftPlayerId = pkData.getLeftPlayer().getPlayerId();
/* 543 */             record.leftPlayerName = pkData.getLeftPlayer().getPlayerName();
/* 544 */             record.leftHead = pkData.getLeftPlayer().getHead();
/* 545 */             record.leftFightValue = pkData.getLeftPlayer().getFightValue();
/*     */             
/* 547 */             record.rightPlayerId = pkData.getRightPlayer().getPlayerId();
/* 548 */             record.rightPlayerName = pkData.getRightPlayer().getPlayerName();
/* 549 */             record.rightHead = pkData.getRightPlayer().getHead();
/* 550 */             record.rightFightValue = pkData.getRightPlayer().getFightValue();
/* 551 */             record.winner = pkData.getWinner();
/* 552 */             record.recordId = pkData.getId();
/* 553 */             record.createtime = betData.getCreatetime();
/* 554 */             if (pkData.getWinner() != 0L) {
/* 555 */               if (betData.getPlayerId() == pkData.getWinner()) {
/* 556 */                 record.num = (betData.getBetNum() * 2);
/*     */               } else {
/* 558 */                 record.num = -betData.getBetNum();
/*     */               } 
/*     */             }
/* 561 */             resultList.add(record);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 566 */     return resultList;
/*     */   }
/*     */   
/*     */   public int getZoneId() {
/* 570 */     return this.zoneId;
/*     */   }
/*     */   
/*     */   public static long getSerialVersionUID() {
/* 574 */     return 1L;
/*     */   }
/*     */   
/*     */   public String getZoneKey() {
/* 578 */     return this.zoneKey;
/*     */   }
/*     */   
/*     */   public void setZoneKey(String zoneKey) {
/* 582 */     this.zoneKey = zoneKey;
/*     */   }
/*     */   
/*     */   public void setZoneId(int zoneId) {
/* 586 */     this.zoneId = zoneId;
/*     */   }
/*     */   
/*     */   public int getMatchId() {
/* 590 */     return this.matchId;
/*     */   }
/*     */   
/*     */   public void setMatchId(int matchId) {
/* 594 */     this.matchId = matchId;
/*     */   }
/*     */   
/*     */   public Set<Long> getZonePlayers() {
/* 598 */     return this.zonePlayers;
/*     */   }
/*     */   
/*     */   public void setZonePlayers(Set<Long> zonePlayers) {
/* 602 */     this.zonePlayers = zonePlayers;
/*     */   }
/*     */   
/*     */   public QuarterMatchGroup[] getQuarterMatchGroups() {
/* 606 */     return this.quarterMatchGroups;
/*     */   }
/*     */   
/*     */   public void setQuarterMatchGroups(QuarterMatchGroup[] quarterMatchGroups) {
/* 610 */     this.quarterMatchGroups = quarterMatchGroups;
/*     */   }
/*     */   
/*     */   public HalfMatchGroup getHalfMatchGroup() {
/* 614 */     return this.halfMatchGroup;
/*     */   }
/*     */   
/*     */   public void setHalfMatchGroup(HalfMatchGroup halfMatchGroup) {
/* 618 */     this.halfMatchGroup = halfMatchGroup;
/*     */   }
/*     */   
/*     */   public ZoneState getZoneState() {
/* 622 */     return this.zoneState;
/*     */   }
/*     */   
/*     */   public void setZoneState(ZoneState zoneState) {
/* 626 */     this.zoneState = zoneState;
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getPlayerIdToServerIdMap() {
/* 630 */     return this.playerIdToServerIdMap;
/*     */   }
/*     */   
/*     */   public void setPlayerIdToServerIdMap(Map<Long, Integer> playerIdToServerIdMap) {
/* 634 */     this.playerIdToServerIdMap = playerIdToServerIdMap;
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
/*     */   public Map<String, ConcurrentHashMap<Long, AtomicInteger>> getBetNumMap() {
/* 646 */     return this.betNumMap;
/*     */   }
/*     */   
/*     */   public void setBetNumMap(Map<String, ConcurrentHashMap<Long, AtomicInteger>> betNumMap) {
/* 650 */     this.betNumMap = betNumMap;
/*     */   }
/*     */   
/*     */   public Map<String, Map<Long, List<BetData>>> getPlayerBetNumMap() {
/* 654 */     return this.playerBetNumMap;
/*     */   }
/*     */   
/*     */   public void setPlayerBetNumMap(Map<String, Map<Long, List<BetData>>> playerBetNumMap) {
/* 658 */     this.playerBetNumMap = playerBetNumMap;
/*     */   }
/*     */   
/*     */   public String getCurrentBetPkId() {
/* 662 */     return this.currentBetPkId;
/*     */   }
/*     */   
/*     */   public void setCurrentBetPkId(String currentBetPkId) {
/* 666 */     this.currentBetPkId = currentBetPkId;
/*     */   }
/*     */   
/*     */   public Set<Long> getCurMatchPlayers() {
/* 670 */     return this.curMatchPlayers;
/*     */   }
/*     */   
/*     */   public void setCurMatchPlayers(Set<Long> curMatchPlayers) {
/* 674 */     this.curMatchPlayers = curMatchPlayers;
/*     */   }
/*     */   
/*     */   public Map<Long, Integer> getBetPlayerIdToServerIdMap() {
/* 678 */     return this.betPlayerIdToServerIdMap;
/*     */   }
/*     */   
/*     */   public void setBetPlayerIdToServerIdMap(Map<Long, Integer> betPlayerIdToServerIdMap) {
/* 682 */     this.betPlayerIdToServerIdMap = betPlayerIdToServerIdMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\match\Zone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */