/*     */ package com.linlongyx.sanguo.webgame.rmi.battle;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftRankBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftRewardBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.StarcraftWorldBean;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattlePlayerData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleResourceData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.CrossBattleTeamData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class Battle implements Serializable {
/*  39 */   private BattleCamp[] battleCamps = new BattleCamp[3]; private String battleKey;
/*  40 */   private Map<Integer, BattleResource> resourceMap = new HashMap<>();
/*     */   
/*  42 */   private Map<Integer, Integer> serverWorldLevelMap = new HashMap<>();
/*  43 */   private ConcurrentHashMap<Long, BattlePlayer> battlePlayers = new ConcurrentHashMap<>();
/*     */   
/*  45 */   private Map<Long, BattlePlayer> leavePlayers = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Battle(String battleKey, List<Pair<Integer, Integer>> serverWorldLevelList) {
/*  51 */     this.battleKey = battleKey;
/*  52 */     Collections.shuffle(serverWorldLevelList);
/*     */     
/*  54 */     Map<Integer, Integer> campBornpointMap = new HashMap<>();
/*     */     
/*  56 */     for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(StarcraftBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/*  57 */       StarcraftBean bean = (StarcraftBean)JsonTableService.getJsonData(id, StarcraftBean.class);
/*  58 */       boolean campOwn = (bean.getCamp() != CampTag.NONE.getTag());
/*  59 */       int[] pos = { ((StarcraftBean.BuildingLocationBean)bean.getBuildingLocation().get(0)).getX(), ((StarcraftBean.BuildingLocationBean)bean.getBuildingLocation().get(0)).getY() };
/*  60 */       BattleResource.ResourceType type = (bean.getBuildingType() == 0) ? BattleResource.ResourceType.BORNPOINT : BattleResource.ResourceType.RESOURCE;
/*  61 */       BattleResource.ResourceState state = (bean.getCamp() == 0) ? BattleResource.ResourceState.FREE : BattleResource.ResourceState.SEIZED;
/*  62 */       CampTag campTag = CampTag.getCampTag(bean.getCamp());
/*  63 */       int limit = bean.getAccommodateCap();
/*  64 */       int collectTime = bean.getAcquisitionTime();
/*  65 */       this.resourceMap.put(Integer.valueOf(id), new BattleResource(id, campOwn, pos, type, state, campTag, limit, collectTime));
/*     */       
/*  67 */       if (bean.getBuildingType() == 0) {
/*  68 */         campBornpointMap.put(Integer.valueOf(bean.getCamp()), Integer.valueOf(bean.getId()));
/*     */       } }
/*     */ 
/*     */     
/*  72 */     for (int campIndex = 0; campIndex < 3; campIndex++) {
/*  73 */       this.battleCamps[campIndex] = new BattleCamp(this.battleKey, campIndex, CampTag.getCampTag(campIndex + 1));
/*  74 */       int[] pos = ((BattleResource)this.resourceMap.get(campBornpointMap.get(Integer.valueOf(campIndex + 1)))).getPos();
/*  75 */       int limit = ((BattleResource)this.resourceMap.get(campBornpointMap.get(Integer.valueOf(campIndex + 1)))).getLimit();
/*  76 */       int resourceId = ((Integer)campBornpointMap.get(Integer.valueOf(campIndex + 1))).intValue();
/*  77 */       this.battleCamps[campIndex].setBornPoint(Arrays.copyOf(pos, pos.length));
/*  78 */       this.battleCamps[campIndex].setResourceId(resourceId);
/*  79 */       this.battleCamps[campIndex].setLimit(limit);
/*     */     } 
/*  81 */     for (int i = 0; i < serverWorldLevelList.size(); i++) {
/*  82 */       int serverId = ((Integer)((Pair)serverWorldLevelList.get(i)).getKey()).intValue();
/*  83 */       int worldLevel = ((Integer)((Pair)serverWorldLevelList.get(i)).getValue()).intValue();
/*  84 */       this.serverWorldLevelMap.put(Integer.valueOf(serverId), Integer.valueOf(worldLevel));
/*  85 */       int j = i / 3;
/*  86 */       if (this.battleCamps[j].getServerPlayers().size() < 3) {
/*  87 */         this.battleCamps[j].getServerPlayers().putIfAbsent(Integer.valueOf(serverId), new ConcurrentSkipListSet<>());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() {
/*  94 */     for (BattlePlayer p : this.battlePlayers.values()) {
/*     */       
/*  96 */       TeamUtil.removePlayerFromTeam(p.getPlayerId());
/*  97 */       p.setPlayerTeamStatus(BattlePlayer.PlayerTeamStatus.NOTEAM);
/*  98 */       p.reset(this);
/*  99 */       LookUpService.logoutWithoutNotice(LookUpService.getByPlayerId(p.getPlayerId()));
/*     */     } 
/* 101 */     this.leavePlayers.putAll(this.battlePlayers);
/* 102 */     this.battlePlayers.clear();
/* 103 */     for (BattleResource battleResource : this.resourceMap.values()) {
/* 104 */       battleResource.reset();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void balance() {
/*     */     try {
/* 113 */       int maxWorldLevel = 100;
/* 114 */       Map<Integer, ArrayList<Reward>> personalRewardMap = new TreeMap<>();
/* 115 */       Map<Integer, ArrayList<Reward>> rankRewardMap = new HashMap<>();
/* 116 */       int targetLevel = 0;
/* 117 */       for (Iterator<Integer> iterator1 = JsonTableService.getJsonTableKey(StarcraftWorldBean.class).iterator(); iterator1.hasNext(); ) { int level = ((Integer)iterator1.next()).intValue();
/* 118 */         targetLevel = level;
/* 119 */         if (maxWorldLevel <= level) {
/*     */           break;
/*     */         } }
/*     */       
/* 123 */       StarcraftWorldBean bean = (StarcraftWorldBean)JsonTableService.getJsonData(targetLevel, StarcraftWorldBean.class);
/* 124 */       if (bean == null)
/* 125 */         return;  int personalLimit = 0; Iterator<Integer> iterator2;
/* 126 */       for (iterator2 = bean.getReward().iterator(); iterator2.hasNext(); ) { int id = ((Integer)iterator2.next()).intValue();
/* 127 */         StarcraftRewardBean starcraftRewardBean = (StarcraftRewardBean)JsonTableService.getJsonData(id, StarcraftRewardBean.class);
/* 128 */         if (personalLimit < starcraftRewardBean.getTarget()) {
/* 129 */           personalLimit = starcraftRewardBean.getTarget();
/*     */         }
/* 131 */         personalRewardMap.put(Integer.valueOf(starcraftRewardBean.getTarget()), FinanceUtil.transform(starcraftRewardBean.getReward())); }
/*     */       
/* 133 */       for (iterator2 = bean.getRankReward().iterator(); iterator2.hasNext(); ) { int id = ((Integer)iterator2.next()).intValue();
/* 134 */         StarcraftRankBean starcraftRankBean = (StarcraftRankBean)JsonTableService.getJsonData(id, StarcraftRankBean.class);
/* 135 */         rankRewardMap.put(Integer.valueOf(starcraftRankBean.getTarget()), FinanceUtil.transform(starcraftRankBean.getRankReward())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       List<BattlePlayer> playerList = (List<BattlePlayer>)Stream.<Collection>of(new Collection[] { getBattlePlayers().values(), getLeavePlayers().values() }).flatMap(Collection::stream).parallel().filter(p -> (p.getPoint() > 0)).sorted((o1, o2) -> (o1.getPoint() == o2.getPoint()) ? Integer.compare(o1.getLastCollectTime(), o2.getLastCollectTime()) : Integer.compare(o2.getPoint(), o1.getPoint())).collect(Collectors.toList());
/*     */       
/* 147 */       List<BattlePlayer> personalRankList = (List<BattlePlayer>)playerList.stream().limit(personalLimit).collect(Collectors.toList());
/* 148 */       for (int i = 0; i < personalRankList.size(); i++) {
/* 149 */         int targetId = -1;
/* 150 */         for (Iterator<Integer> iterator = personalRewardMap.keySet().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 151 */           if (i + 1 <= id) {
/* 152 */             targetId = id;
/*     */             break;
/*     */           }  }
/*     */         
/* 156 */         if (targetId != -1) {
/* 157 */           ArrayList<Reward> rewardList = personalRewardMap.get(Integer.valueOf(targetId));
/* 158 */           BattlePlayer battlePlayer = personalRankList.get(i);
/* 159 */           LogUtil.errorLog(new Object[] { "battle personal rank, serverId:", Integer.valueOf(battlePlayer.getServerId()), "playerId:", Long.valueOf(battlePlayer.getPlayerId()), "rank:", Integer.valueOf(i + 1) });
/*     */           
/* 161 */           boolean success = LogicRmiUtil.sendCrossPointRankReward("Battle::balance:personal", battlePlayer.getServerId(), battlePlayer.getPlayerId(), i + 1, rewardList);
/* 162 */           if (!success) {
/* 163 */             BattleUtil.addBattleRewardCache(new BattleRewardCache(battlePlayer.getServerId(), false, battlePlayer.getPlayerId(), i + 1, rewardList));
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 170 */       int[] campRank = new int[3];
/*     */       
/* 172 */       Map<Integer, List<BattlePlayer>> campPlayerMap = new HashMap<>();
/* 173 */       playerList.forEach(p -> {
/*     */             campRank[p.getCampIndex()] = campRank[p.getCampIndex()] + p.getPoint();
/*     */             campPlayerMap.putIfAbsent(Integer.valueOf(p.getCampIndex()), new ArrayList());
/*     */             ((List<BattlePlayer>)campPlayerMap.get(Integer.valueOf(p.getCampIndex()))).add(p);
/*     */           });
/* 178 */       List<Pair<Integer, Integer>> campRankList = new ArrayList<>();
/* 179 */       for (int j = 0; j < campRank.length; j++) {
/* 180 */         campRankList.add(new Pair(Integer.valueOf(j), Integer.valueOf(campRank[j])));
/*     */       }
/* 182 */       campRankList.sort((o1, o2) -> ((Integer)o2.getValue()).intValue() - ((Integer)o1.getValue()).intValue());
/*     */ 
/*     */       
/* 185 */       Map<Integer, ArrayList<Reward>> campRankRewardMap = new HashMap<>();
/* 186 */       for (int k = 0; k < campRank.length; k++) {
/* 187 */         if (((Integer)((Pair)campRankList.get(k)).getValue()).intValue() > 0) {
/* 188 */           campRankRewardMap.put(((Pair)campRankList.get(k)).getKey(), rankRewardMap.get(Integer.valueOf(k + 1)));
/*     */         }
/* 190 */         campRank[((Integer)((Pair)campRankList.get(k)).getKey()).intValue()] = k + 1;
/*     */       } 
/*     */ 
/*     */       
/* 194 */       for (Map.Entry<Integer, List<BattlePlayer>> kv : campPlayerMap.entrySet()) {
/* 195 */         if (campRankRewardMap.containsKey(kv.getKey())) {
/* 196 */           ArrayList<Reward> rewardList = campRankRewardMap.get(kv.getKey());
/*     */ 
/*     */           
/* 199 */           Map<Integer, List<Long>> resultMap = (Map<Integer, List<Long>>)((List)kv.getValue()).stream().collect(Collectors.groupingBy(BattlePlayer::getServerId, 
/* 200 */                 Collectors.mapping(BattlePlayer::getPlayerId, Collectors.toList())));
/* 201 */           for (Map.Entry<Integer, List<Long>> spKV : resultMap.entrySet())
/*     */           {
/* 203 */             ArrayList<Long> targetPlayerList = new ArrayList<>(spKV.getValue());
/* 204 */             LogUtil.errorLog(new Object[] { "battle camp rank, serverId: ", spKV.getKey(), "camp:", kv.getKey(), "rank:", Integer.valueOf(campRank[((Integer)kv.getKey()).intValue()]) });
/* 205 */             boolean success = LogicRmiUtil.sendCrossCampRankReward("Battle::balance:camp", ((Integer)spKV.getKey()).intValue(), ((Integer)kv.getKey()).intValue(), campRank[((Integer)kv.getKey()).intValue()], targetPlayerList, rewardList);
/* 206 */             if (!success) {
/* 207 */               BattleUtil.addBattleRewardCache(new BattleRewardCache(((Integer)spKV.getKey()).intValue(), true, targetPlayerList, campRank[((Integer)kv.getKey()).intValue()], ((Integer)kv.getKey()).intValue(), rewardList));
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 213 */     } catch (Exception e) {
/* 214 */       LogUtil.errorLog(new Object[] { "battle", this.battleKey, "balance error,", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public short createTeam(long playerId) {
/* 219 */     if (!this.battlePlayers.containsKey(Long.valueOf(playerId))) {
/* 220 */       return 10019;
/*     */     }
/* 222 */     BattlePlayer battlePlayer = this.battlePlayers.get(Long.valueOf(playerId));
/* 223 */     if (TeamUtil.isPlayerInTeam(playerId) || battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM)
/*     */     {
/* 225 */       return 13705;
/*     */     }
/* 227 */     BattleCamp battleCamp = getBattleCamps()[battlePlayer.getCampIndex()];
/* 228 */     if (!BattleUtil.checkPos(battleCamp.getBornPoint(), battlePlayer.getPos())) {
/* 229 */       return 11307;
/*     */     }
/*     */     
/* 232 */     short code = battlePlayer.createTeam(this);
/* 233 */     return code;
/*     */   }
/*     */ 
/*     */   
/*     */   public short joinTeam(long playerId, long teamId) {
/* 238 */     if (!this.battlePlayers.containsKey(Long.valueOf(playerId))) {
/* 239 */       return 10019;
/*     */     }
/* 241 */     BattlePlayer battlePlayer = this.battlePlayers.get(Long.valueOf(playerId));
/* 242 */     if (TeamUtil.isPlayerInTeam(playerId) || battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.NOTEAM)
/*     */     {
/* 244 */       return 13705;
/*     */     }
/* 246 */     Team team = TeamUtil.getTeamById(teamId);
/* 247 */     if (team == null) {
/* 248 */       return 13701;
/*     */     }
/* 250 */     BattlePlayer leader = getBattlePlayers().get(Long.valueOf(team.getLeaderId()));
/* 251 */     if (battlePlayer.getCampIndex() != leader.getCampIndex()) {
/* 252 */       return 11316;
/*     */     }
/* 254 */     BattleCamp battleCamp = getBattleCamps()[leader.getCampIndex()];
/* 255 */     if (!BattleUtil.checkPos(battleCamp.getBornPoint(), leader.getPos())) {
/* 256 */       return 11315;
/*     */     }
/* 258 */     short code = battlePlayer.joinTeam(this, team);
/* 259 */     return code;
/*     */   }
/*     */   
/*     */   public short leaveTeam(long playerId, long teamId) {
/* 263 */     if (!this.battlePlayers.containsKey(Long.valueOf(playerId))) {
/* 264 */       return 10019;
/*     */     }
/* 266 */     BattlePlayer battlePlayer = this.battlePlayers.get(Long.valueOf(playerId));
/* 267 */     Team team = TeamUtil.getTeamById(teamId);
/* 268 */     if (team == null) {
/* 269 */       return 13701;
/*     */     }
/* 271 */     if (team.isLeader(playerId)) {
/* 272 */       BattleCamp battleCamp = getBattleCamps()[battlePlayer.getCampIndex()];
/* 273 */       if (!BattleUtil.checkPos(battleCamp.getBornPoint(), battlePlayer.getPos())) {
/* 274 */         return 11307;
/*     */       }
/*     */     } else {
/* 277 */       BattlePlayer leader = this.battlePlayers.get(Long.valueOf(team.getLeaderId()));
/* 278 */       BattleCamp battleCamp = getBattleCamps()[leader.getCampIndex()];
/* 279 */       if (!BattleUtil.checkPos(battleCamp.getBornPoint(), leader.getPos())) {
/* 280 */         return 11315;
/*     */       }
/*     */     } 
/* 283 */     short code = battlePlayer.leaveTeam(this, team);
/* 284 */     return code;
/*     */   }
/*     */   
/*     */   public boolean addBattlePlayer(int serverId, int worldLevel, long playerId) {
/* 288 */     BattleCamp targetCamp = null;
/* 289 */     for (BattleCamp battleCamp : this.battleCamps) {
/* 290 */       if (battleCamp != null && battleCamp.containsServer(serverId)) {
/* 291 */         targetCamp = battleCamp;
/*     */         break;
/*     */       } 
/*     */     } 
/* 295 */     if (targetCamp == null) {
/* 296 */       for (BattleCamp battleCamp : this.battleCamps) {
/* 297 */         if (battleCamp != null && battleCamp.getServerPlayers().size() < 3) {
/* 298 */           targetCamp = battleCamp;
/*     */           break;
/*     */         } 
/*     */       } 
/* 302 */       if (targetCamp == null)
/* 303 */         return false; 
/*     */     } 
/* 305 */     this.serverWorldLevelMap.putIfAbsent(Integer.valueOf(serverId), Integer.valueOf(worldLevel));
/* 306 */     if (targetCamp.addPlayer(serverId, playerId)) {
/* 307 */       BattlePlayer battlePlayer = this.leavePlayers.remove(Long.valueOf(playerId));
/* 308 */       if (battlePlayer == null) {
/* 309 */         PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 310 */         if (playerData == null) {
/* 311 */           return false;
/*     */         }
/* 313 */         battlePlayer = new BattlePlayer(playerData, targetCamp.getCampKey(), targetCamp.getTag(), targetCamp.getCampIndex(), playerId, targetCamp.getResourceId(), targetCamp.getBornPoint());
/*     */       } else {
/* 315 */         battlePlayer.reset(this);
/*     */       } 
/* 317 */       this.battlePlayers.putIfAbsent(Long.valueOf(playerId), battlePlayer);
/*     */     } 
/* 319 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeBattlePlayer(long playerId) {
/* 323 */     BattlePlayer battlePlayer = this.battlePlayers.remove(Long.valueOf(playerId));
/* 324 */     if (battlePlayer != null) {
/* 325 */       this.leavePlayers.put(Long.valueOf(playerId), battlePlayer);
/* 326 */       return this.battleCamps[battlePlayer.getCampIndex()].removePlayer(playerId);
/*     */     } 
/* 328 */     return true;
/*     */   }
/*     */   
/*     */   public BattleCamp chooseNotFullCamp() {
/* 332 */     for (BattleCamp battleCamp : this.battleCamps) {
/* 333 */       if (battleCamp != null && battleCamp.getServerPlayers().size() < 3) {
/* 334 */         return battleCamp;
/*     */       }
/*     */     } 
/* 337 */     return null;
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public ArrayList<CrossBattleResourceData> getCurBattleResourceList() {
/* 342 */     ArrayList<CrossBattleResourceData> resourceList = new ArrayList<>();
/* 343 */     for (BattleResource res : this.resourceMap.values()) {
/* 344 */       if (res.getType() == BattleResource.ResourceType.RESOURCE) {
/* 345 */         CrossBattleResourceData resData = new CrossBattleResourceData();
/* 346 */         resData.playerId = res.getPlayerId();
/* 347 */         resData.resourceId = res.getResourceId();
/* 348 */         resData.num = res.getCount();
/* 349 */         resData.state = res.getCurState().getState();
/* 350 */         resData.own = (byte)(res.isCampOwn() ? 1 : 0);
/* 351 */         resData.camp = res.getOwnCamp().getTag();
/* 352 */         resourceList.add(resData);
/*     */       } 
/*     */     } 
/* 355 */     return resourceList;
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public Set<Long> getSingleOrTeamPlayerSet() {
/* 360 */     Set<Long> playerSet = new HashSet<>();
/* 361 */     for (BattlePlayer battlePlayer : this.battlePlayers.values()) {
/*     */       
/* 363 */       if (battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.HASTEAM) {
/* 364 */         playerSet.add(Long.valueOf(battlePlayer.getPlayerId()));
/*     */       }
/*     */     } 
/* 367 */     return playerSet;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValidPos(int x, int y) {
/* 372 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean playerMove(long playerId, int x, int y) {
/* 377 */     BattlePlayer battlePlayer = this.battlePlayers.get(Long.valueOf(playerId));
/* 378 */     if (battlePlayer == null) {
/* 379 */       return false;
/*     */     }
/* 381 */     if (!isValidPos(x, y)) {
/* 382 */       return false;
/*     */     }
/*     */     
/* 385 */     BattleResource battleResource = getResourceMap().get(Integer.valueOf(battlePlayer.getResourceId()));
/* 386 */     BattleCamp battleCamp = getBattleCamps()[battlePlayer.getCampIndex()];
/*     */     
/* 388 */     boolean ownResource = (battleResource != null);
/* 389 */     long teamId = TeamUtil.getTeamIdByPlayerId(playerId);
/* 390 */     if (teamId == -1L) {
/* 391 */       if (x == battleCamp.getBornPoint()[0] && y == battleCamp.getBornPoint()[1]) {
/* 392 */         battlePlayer.reset(this);
/*     */       } else {
/* 394 */         battlePlayer.updatePos(x, y);
/* 395 */         battlePlayer.setState(BattlePlayer.PlayerState.MOVING);
/*     */       } 
/* 397 */       if (ownResource) {
/* 398 */         battleResource.removePlayer(playerId);
/*     */       }
/*     */     } else {
/* 401 */       Team team = TeamUtil.getTeamByInsIdAndTeamId(1, teamId);
/* 402 */       if (team != null) {
/* 403 */         for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 404 */           if (teamPlayer != null && !teamPlayer.isRobot()) {
/* 405 */             BattlePlayer p = this.battlePlayers.get(Long.valueOf(teamPlayer.getPlayerId()));
/*     */             
/* 407 */             if (x == battleCamp.getBornPoint()[0] && y == battleCamp.getBornPoint()[1]) {
/* 408 */               p.reset(this);
/*     */             } else {
/* 410 */               p.updatePos(x, y);
/* 411 */               p.setState(BattlePlayer.PlayerState.MOVING);
/*     */             } 
/* 413 */             if (ownResource) {
/* 414 */               battleResource.removePlayer(p.getPlayerId());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 420 */     return true;
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public CrossBattleTeamData getPlayerBattleTeamData(long playerId) {
/* 425 */     BattlePlayer battlePlayer = this.battlePlayers.get(Long.valueOf(playerId));
/* 426 */     if (battlePlayer == null) {
/* 427 */       return null;
/*     */     }
/* 429 */     Team team = TeamUtil.getTeamByPlayerId(battlePlayer.getPlayerId());
/* 430 */     CrossBattleTeamData teamData = new CrossBattleTeamData();
/* 431 */     if (team != null) {
/* 432 */       battlePlayer = this.battlePlayers.get(Long.valueOf(team.getLeaderId()));
/* 433 */       teamData.teamId = team.getTeamId();
/* 434 */       teamData.playerId = team.getLeaderId();
/*     */     } else {
/* 436 */       teamData.teamId = -1L;
/* 437 */       teamData.playerId = playerId;
/*     */     } 
/* 439 */     teamData.camp = this.battleCamps[battlePlayer.getCampIndex()].getTag().getTag();
/* 440 */     teamData.state = battlePlayer.getState().getState();
/* 441 */     teamData.x = battlePlayer.getPos()[0];
/* 442 */     teamData.y = battlePlayer.getPos()[1];
/* 443 */     if (battlePlayer.getResourceId() != 0) {
/* 444 */       BattleResource battleResource = getResourceMap().get(Integer.valueOf(battlePlayer.getResourceId()));
/* 445 */       if (battleResource.getType() == BattleResource.ResourceType.RESOURCE) {
/* 446 */         teamData.time = battlePlayer.getLastCollectTime() + battleResource.getCollectTime();
/*     */       }
/*     */     } 
/* 449 */     if (team != null) {
/* 450 */       for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 451 */         if (teamPlayer != null && !teamPlayer.isRobot()) {
/* 452 */           PlayerData playerData = getPlayerData(teamPlayer.getPlayerId());
/* 453 */           if (playerData != null) {
/* 454 */             CrossBattlePlayerData crossBattlePlayerData = BattleUtil.transformToCrossBattlePlayerData(getBattlePlayers().get(Long.valueOf(teamPlayer.getPlayerId())), playerData, team);
/*     */             
/* 456 */             teamData.players.add(crossBattlePlayerData);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 461 */       PlayerData playerData = getPlayerData(battlePlayer.getPlayerId());
/* 462 */       if (playerData != null) {
/* 463 */         CrossBattlePlayerData crossBattlePlayerData = BattleUtil.transformToCrossBattlePlayerData(battlePlayer, playerData, null);
/* 464 */         teamData.players.add(crossBattlePlayerData);
/*     */       } else {
/* 466 */         return null;
/*     */       } 
/*     */     } 
/* 469 */     return teamData;
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public ArrayList<CrossBattleTeamData> getCurBattleTeamDataList() {
/* 474 */     ArrayList<CrossBattleTeamData> resultList = new ArrayList<>();
/* 475 */     for (BattlePlayer battlePlayer : this.battlePlayers.values()) {
/*     */       
/* 477 */       if (battlePlayer.getPlayerTeamStatus() != BattlePlayer.PlayerTeamStatus.HASTEAM) {
/* 478 */         CrossBattleTeamData teamData = getPlayerBattleTeamData(battlePlayer.getPlayerId());
/* 479 */         if (teamData != null) {
/* 480 */           resultList.add(teamData);
/*     */         }
/*     */       } 
/*     */     } 
/* 484 */     return resultList;
/*     */   }
/*     */   
/*     */   public int maxWorldLevel() {
/* 488 */     return ((Integer)this.serverWorldLevelMap.values().stream().max((f1, f2) -> f1.intValue() - f2.intValue()).orElse(Integer.valueOf(0))).intValue();
/*     */   }
/*     */   
/*     */   public enum CampTag {
/* 492 */     NONE(0),
/* 493 */     WEI(1),
/* 494 */     SHU(2),
/* 495 */     WU(3);
/*     */     
/*     */     int tag;
/*     */     
/*     */     CampTag(int tag) {
/* 500 */       this.tag = tag;
/*     */     }
/*     */     
/*     */     public int getTag() {
/* 504 */       return this.tag;
/*     */     }
/*     */     
/*     */     public static CampTag getCampTag(int tag) {
/* 508 */       switch (tag) {
/*     */         case 1:
/* 510 */           return WEI;
/*     */         case 2:
/* 512 */           return SHU;
/*     */         case 3:
/* 514 */           return WU;
/*     */       } 
/* 516 */       return NONE;
/*     */     }
/*     */ 
/*     */     
/*     */     public static String getCampName(int tag) {
/* 521 */       switch (tag) {
/*     */         case 1:
/* 523 */           return "魏国";
/*     */         case 2:
/* 525 */           return "蜀国";
/*     */         case 3:
/* 527 */           return "吴国";
/*     */       } 
/* 529 */       return "无所属";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public PlayerData getPlayerData(long playerId) {
/* 536 */     PlayerData playerData = null;
/* 537 */     if (getBattlePlayers().containsKey(Long.valueOf(playerId))) {
/* 538 */       playerData = ((BattlePlayer)getBattlePlayers().get(Long.valueOf(playerId))).getPlayerData();
/*     */     } else {
/* 540 */       playerData = ((BattlePlayer)getLeavePlayers().get(Long.valueOf(playerId))).getPlayerData();
/*     */     } 
/* 542 */     return playerData;
/*     */   }
/*     */   
/*     */   public String getBattleKey() {
/* 546 */     return this.battleKey;
/*     */   }
/*     */   
/*     */   public void setBattleKey(String battleKey) {
/* 550 */     this.battleKey = battleKey;
/*     */   }
/*     */   
/*     */   public BattleCamp[] getBattleCamps() {
/* 554 */     return this.battleCamps;
/*     */   }
/*     */   
/*     */   public void setBattleCamps(BattleCamp[] battleCamps) {
/* 558 */     this.battleCamps = battleCamps;
/*     */   }
/*     */   
/*     */   public Map<Integer, BattleResource> getResourceMap() {
/* 562 */     return this.resourceMap;
/*     */   }
/*     */   
/*     */   public void setResourceMap(Map<Integer, BattleResource> resourceMap) {
/* 566 */     this.resourceMap = resourceMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getServerWorldLevelMap() {
/* 570 */     return this.serverWorldLevelMap;
/*     */   }
/*     */   
/*     */   public void setServerWorldLevelMap(Map<Integer, Integer> serverWorldLevelMap) {
/* 574 */     this.serverWorldLevelMap = serverWorldLevelMap;
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<Long, BattlePlayer> getBattlePlayers() {
/* 578 */     return this.battlePlayers;
/*     */   }
/*     */   
/*     */   public void setBattlePlayers(ConcurrentHashMap<Long, BattlePlayer> battlePlayers) {
/* 582 */     this.battlePlayers = battlePlayers;
/*     */   }
/*     */   
/*     */   public Map<Long, BattlePlayer> getLeavePlayers() {
/* 586 */     return this.leavePlayers;
/*     */   }
/*     */   
/*     */   public void setLeavePlayers(Map<Long, BattlePlayer> leavePlayers) {
/* 590 */     this.leavePlayers = leavePlayers;
/*     */   }
/*     */   
/*     */   public Battle() {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\battle\Battle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */