/*     */ package com.linlongyx.sanguo.webgame.rmi;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.ODTime;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.ServerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.DestinyCache;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.PkData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.Zone;
/*     */ import com.linlongyx.sanguo.webgame.rmi.race.RaceUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.rank.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.runewar.RunewarUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.tower.TowerUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ public class CrossRmiServer extends UnicastRemoteObject implements ICrossRmi {
/*     */   public void registerLogicRmi(String logicRmi, ServerData serverData) throws RemoteException {
/*  37 */     LogicRmiManager.lookUpLogicRmi(serverData.getServerId(), logicRmi, serverData.getOpentime(), serverData.getWorldLevel());
/*  38 */     DestinyCache.addServer(serverData.getOpentime(), serverData.getServerId(), serverData.getWorldLevel());
/*  39 */     LogicRmiManager.serverDataMap.put(Integer.valueOf(serverData.getServerId()), serverData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeLogicRmi(int serverId) throws RemoteException {
/*  44 */     LogicRmiManager.removeLogicRmi(serverId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean needInit(int serverId) throws RemoteException {
/*  49 */     return !LogicRmiManager.isRegisted(serverId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean heartBeat(int serverId, int worldLevel) throws RemoteException {
/*  54 */     if (LogicRmiManager.serverDataMap.containsKey(Integer.valueOf(serverId))) {
/*  55 */       ((ServerData)LogicRmiManager.serverDataMap.get(Integer.valueOf(serverId))).setWorldLevel(worldLevel);
/*     */     }
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDestinyDef(int serverId, long playerId, int type, int time, int robNum, boolean win, long pkPlayerId, String pkPlayerName, Reward reward) throws RemoteException {
/*  62 */     DestinyCache.addLocalDestinyDef(serverId, playerId, type, time, robNum, win, pkPlayerId, pkPlayerName, reward);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<DestinyPlayerData> getDestinyPlayerData(int serverId, HashSet<Long> playerId) throws RemoteException {
/*  67 */     return DestinyCache.getDestinyPlayerData(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<DestinyPlayerData> destinyRecomment(DestinyPlayerData myplayerData, HashSet<Long> battles, int recommentSize) throws RemoteException {
/*  72 */     return DestinyCache.destinyRecomment(myplayerData, battles, recommentSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDestinyPlayerData(DestinyPlayerData destinyPlayerData) throws RemoteException {
/*  77 */     DestinyCache.updateDestinyPlayer(destinyPlayerData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTalismanMap(int serverId, long playerId, HashMap<Long, HashMap<Integer, Integer>> talismanMap) throws RemoteException {
/*  82 */     Map<Long, Map<Integer, Integer>> finalMap = Collections.unmodifiableMap((Map)talismanMap);
/*  83 */     DestinyCache.updateTalismanMap(serverId, playerId, finalMap);
/*  84 */     RaceUtil.updateTalismanMap(serverId, playerId, finalMap);
/*     */   }
/*     */ 
/*     */   
/*     */   public void destinyRemovePlayer(int serverId, long playerId) throws RemoteException {
/*  89 */     DestinyCache.destinyRemovePlayer(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public DestinyRankData getDestinyRankData(int serverId, long playerId) throws RemoteException {
/*  94 */     DestinyRankData destinyRankData = new DestinyRankData();
/*  95 */     DestinyPlayerData destinyPlayerData = DestinyCache.getLogicDestinyPlayerData(serverId, playerId);
/*  96 */     if (destinyPlayerData != null) {
/*  97 */       DestinyPlayerData localDestinyPlayerData = DestinyCache.getLocalDestinyPlayerData(destinyPlayerData.getServerId(), playerId);
/*     */       
/*  99 */       destinyRankData.destinyPoint = localDestinyPlayerData.getDestinyPoint();
/* 100 */       destinyRankData.fightValue = destinyPlayerData.getFightValue();
/* 101 */       destinyRankData.head = destinyPlayerData.getHead();
/* 102 */       destinyRankData.playerId = destinyPlayerData.getPlayerId();
/* 103 */       destinyRankData.playerName = destinyPlayerData.getPlayerName();
/* 104 */       destinyRankData.quality = destinyPlayerData.getQuality();
/* 105 */       destinyRankData.rank = DestinyCache.getPlayerRank(destinyPlayerData.getServerId(), playerId);
/*     */     } 
/* 107 */     return destinyRankData;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addDestinyPoint(int serverId, long playerId, int destinyPoint) throws RemoteException {
/* 112 */     return DestinyCache.addDestinyPoint(serverId, playerId, destinyPoint);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearCrossPlayerData(int serverId) throws RemoteException {
/* 117 */     DestinyCache.clearServerPlayerData(serverId);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZoneId(int serverId) throws RemoteException {
/* 122 */     return MatchUtil.getZoneByServerId(serverId).getZoneId();
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Integer, PkDataItem[]> getPkDataItems(int serverId, int type) throws RemoteException {
/* 127 */     return MatchUtil.getGroupPkDataList(serverId, type);
/*     */   }
/*     */ 
/*     */   
/*     */   public MatchState getCurrentMatchState(int serverId) throws RemoteException {
/* 132 */     return MatchUtil.getCurMatch().getMatchState();
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Long, Integer> getBetNum(int serverId, String pkId) throws RemoteException {
/* 137 */     HashMap<Long, Integer> resultMap = new HashMap<>();
/* 138 */     Zone zone = MatchUtil.getZoneByServerId(serverId);
/* 139 */     if (zone != null && 
/* 140 */       zone.getBetNumMap().containsKey(pkId)) {
/* 141 */       PkData pkData = (PkData)zone.pkRecords().get(pkId);
/* 142 */       if (pkData.getLeftPlayer() != null && ((ConcurrentHashMap)zone.getBetNumMap().get(pkId)).containsKey(Long.valueOf(pkData.getLeftPlayer().getPlayerId()))) {
/* 143 */         long playerId = pkData.getLeftPlayer().getPlayerId();
/* 144 */         resultMap.put(Long.valueOf(playerId), Integer.valueOf(((AtomicInteger)((ConcurrentHashMap)zone.getBetNumMap().get(pkId)).get(Long.valueOf(playerId))).get()));
/*     */       } 
/* 146 */       if (pkData.getRightPlayer() != null && ((ConcurrentHashMap)zone.getBetNumMap().get(pkId)).containsKey(Long.valueOf(pkData.getRightPlayer().getPlayerId()))) {
/* 147 */         long playerId = pkData.getRightPlayer().getPlayerId();
/* 148 */         resultMap.put(Long.valueOf(playerId), Integer.valueOf(((AtomicInteger)((ConcurrentHashMap)zone.getBetNumMap().get(pkId)).get(Long.valueOf(playerId))).get()));
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     return resultMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addPlayerBetNum(int serverId, String pkId, long playerId, long targetPlayerId, int betNum, int type) throws RemoteException {
/* 157 */     Zone zone = MatchUtil.getZoneByServerId(serverId);
/* 158 */     if (zone != null) {
/* 159 */       return zone.addPlayerBetNum(serverId, pkId, playerId, targetPlayerId, betNum, type);
/*     */     }
/* 161 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<DestinyBetRecord> getPlayerBetRecord(int serverId, long playerId) throws RemoteException {
/* 166 */     return MatchUtil.getPlayerBetRecord(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public PkRecord getPkRecord(int serverId, String pkId) throws RemoteException {
/* 171 */     Zone zone = MatchUtil.getZoneByServerId(serverId);
/* 172 */     if (zone != null) {
/* 173 */       if (zone.pkRecords().containsKey(pkId)) {
/* 174 */         return ((PkData)zone.pkRecords().get(pkId)).getPkRecord();
/*     */       }
/* 176 */       return null;
/*     */     } 
/*     */     
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMatch(int serverId, long playerId) throws RemoteException {
/* 184 */     return MatchUtil.hasMatch(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void gmMatch(int serverId, int state) throws RemoteException {
/* 189 */     LogUtil.errorLog(new Object[] { "gmMatch from server:", Integer.valueOf(serverId), ", state:", Integer.valueOf(state) });
/* 190 */     MatchUtil.changeState(state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void gmBattle(int serverId, int state) throws RemoteException {
/* 195 */     LogUtil.errorLog(new Object[] { "gmBattle from server:", Integer.valueOf(serverId), ", state:", Integer.valueOf(state) });
/* 196 */     BattleUtil.gmBattle(state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBattleOpen() throws RemoteException {
/* 201 */     return (BattleUtil.getBattleMap() != null && BattleUtil.getBattleMap().isOpen());
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<Long> getCurBetPkPlayers(int serverId) throws RemoteException {
/* 206 */     ArrayList<Long> players = new ArrayList<>();
/* 207 */     Zone zone = MatchUtil.getZoneByServerId(serverId);
/* 208 */     if (zone != null) {
/* 209 */       PkData pkData = (PkData)zone.pkRecords().get(zone.getCurrentBetPkId());
/* 210 */       if (pkData != null) {
/* 211 */         if (pkData.getLeftPlayer() != null) {
/* 212 */           players.add(Long.valueOf(pkData.getLeftPlayer().getPlayerId()));
/*     */         }
/* 214 */         if (pkData.getRightPlayer() != null) {
/* 215 */           players.add(Long.valueOf(pkData.getRightPlayer().getPlayerId()));
/*     */         }
/*     */       } 
/*     */     } 
/* 219 */     return players;
/*     */   }
/*     */ 
/*     */   
/*     */   public PkDataItem getCurPkDataItem(int serverId) throws RemoteException {
/* 224 */     Zone zone = MatchUtil.getZoneByServerId(serverId);
/* 225 */     if (zone != null) {
/* 226 */       PkData pkData = (PkData)zone.pkRecords().get(zone.getCurrentBetPkId());
/* 227 */       if (pkData != null) {
/* 228 */         if (pkData.getLeftPlayer() == null && pkData.getRightPlayer() == null) {
/* 229 */           return null;
/*     */         }
/* 231 */         return MatchUtil.transformPkData(pkData);
/*     */       } 
/*     */     } 
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWxFriendInfo(int serverId, WxPlayerInfo wxPlayerInfo, long playerId) throws RemoteException {
/* 239 */     LogicRmiUtil.sendWxFriendInfo("updateWxFriendInfo", wxPlayerInfo, serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void uploadPlayerRankData(int actId, int rankType, int limit, boolean isDesc, int condition, int serverId, RankingData data) throws RemoteException {
/* 244 */     RankActUtil.updatePlayerRankData(actId, rankType, limit, isDesc, condition, serverId, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRankServer(int actId, int rankType, int limit, boolean isDesc, int condition, int serverId) throws RemoteException {
/* 249 */     RankActUtil.addRankServer(actId, rankType, limit, isDesc, condition, serverId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<RankingData> getRankList(int actId, boolean isClose) throws RemoteException {
/* 255 */     return RankActUtil.getActRankList(actId, isClose);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePlayerStatus(long playerId, HashMap<Integer, ODTime> status) throws RemoteException {
/* 260 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 261 */     if (playerData != null) {
/* 262 */       playerData.setStatus(status);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int updatePlayerRacePoint(int serverId, long playerId, int point) throws RemoteException {
/* 268 */     return RaceUtil.updatePlayerRacePoint(serverId, playerId, point);
/*     */   }
/*     */ 
/*     */   
/*     */   public RacePlayerData recommentOne(int serverId, long playerId, long fightValue, int point) throws RemoteException {
/* 273 */     return RaceUtil.recommentOne(serverId, playerId, fightValue, point);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<RankingData> getRaceRankList(int serverId, int pageSize) throws RemoteException {
/* 278 */     return RaceUtil.getRaceRankList(serverId, pageSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public RankingData getPlayerRaceRank(int serverId, long playerId, int curPoint) throws RemoteException {
/* 283 */     return RaceUtil.getPlayerRaceRank(serverId, playerId, curPoint);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurRaceId() throws RemoteException {
/* 288 */     return RaceUtil.getCurRaceId();
/*     */   }
/*     */ 
/*     */   
/*     */   public int newRace() throws RemoteException {
/* 293 */     return RaceUtil.newRace();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRaceOpen() throws RemoteException {
/* 298 */     return RaceUtil.isRaceOpen();
/*     */   }
/*     */ 
/*     */   
/*     */   public void playerJoinRace(int worldLevel, PlayerData playerData) throws RemoteException {
/* 303 */     RaceUtil.addPlayerData(worldLevel, playerData);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRaceState() throws RemoteException {
/* 308 */     return RaceUtil.getRaceState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateCandidateFighters(int serverId, long playerId, ArrayList<Pair<Integer, Integer>> candidateFighters) throws RemoteException {
/* 313 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 314 */     if (playerData != null) {
/* 315 */       playerData.setCandidateFighters(candidateFighters);
/*     */     }
/* 317 */     DestinyPlayerData destinyPlayerData = DestinyCache.getDestinyMap().getDestinyPlayerData(serverId, playerId);
/* 318 */     if (destinyPlayerData != null) {
/* 319 */       destinyPlayerData.setCandidateFighters(candidateFighters);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateZhenfa(int serverId, long playerId, Pair<Integer, Integer> zhenfa) throws RemoteException {
/* 325 */     PlayerData playerData = LookUpService.getPlayerData(playerId);
/* 326 */     if (playerData != null) {
/* 327 */       playerData.setZhenfa(zhenfa);
/*     */     }
/* 329 */     DestinyPlayerData destinyPlayerData = DestinyCache.getDestinyMap().getDestinyPlayerData(serverId, playerId);
/* 330 */     if (destinyPlayerData != null) {
/* 331 */       destinyPlayerData.setZhenfa(zhenfa);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RankingData> getTowerData(PlayerData playerData, int curTowerId) throws RemoteException {
/* 337 */     return TowerUtil.getTowerData(playerData, curTowerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pair<Integer, ArrayList<RankingData>> getTowerRankList(int serverId, long playerId) throws RemoteException {
/* 342 */     return TowerUtil.getRankList(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pair<RankingData, Pair<Integer, PlayerData>> fightTowerLayer(int serverId, long playerId, int layerId) throws RemoteException {
/* 347 */     return TowerUtil.fightTowerLayer(serverId, playerId, layerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Pair<Integer, RankingData> towerLayerState(int serverId, long playerId, int layerId) throws RemoteException {
/* 352 */     return TowerUtil.towerLayerState(serverId, playerId, layerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fightTowerResult(int serverId, long playerId, long targetPlayerId, int layerId, boolean win) throws RemoteException {
/* 357 */     return TowerUtil.fightTowerResult(serverId, playerId, targetPlayerId, layerId, win);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTowerDefRecord(int serverId, int layerId, long playerId, int type, int time, boolean win, long pkPlayerId, String pkPlayerName) throws RemoteException {
/* 362 */     TowerUtil.addTowerDefRecord(serverId, layerId, playerId, type, time, win, pkPlayerId, pkPlayerName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean giveupTowerLayer(int serverId, long playerId, int layerId) throws RemoteException {
/* 367 */     return TowerUtil.giveupTowerLayer(serverId, playerId, layerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runewarPlayerJoin(PlayerData playerData) throws RemoteException {
/* 372 */     RunewarUtil.playerJoin(playerData);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RunewarPlayerData> getRunewardPlayerData(int serverId, long playerId) throws RemoteException {
/* 377 */     return RunewarUtil.getRunewardPlayerData(serverId, playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public RunewarFightersData getRunewarFighterData(int serverId, long playerId, long targetPlayerId) throws RemoteException {
/* 382 */     return RunewarUtil.getRunewarFighterData(serverId, playerId, targetPlayerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RankingData> getRunewarRankList(int serverId) throws RemoteException {
/* 387 */     return RunewarUtil.getRankList(serverId);
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerData getRunewarTargetPlayerdata(int serverId, long playerId, long targetPlayerId) throws RemoteException {
/* 392 */     return RunewarUtil.getRunewarTargetPlayerdata(serverId, playerId, targetPlayerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public int runewarFightResult(int serverId, long playerId, long targetPlayerId, int targetServerId, int point, boolean win, BattleRecordData record) throws RemoteException {
/* 397 */     return RunewarUtil.runewarFightResult(serverId, playerId, targetPlayerId, targetServerId, point, win, record);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRunewarOpen() throws RemoteException {
/* 402 */     return RunewarUtil.isOpen();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<RunewarPlayerData> refreshPlayerCoordinate(int serverId, long playerId) throws RemoteException {
/* 407 */     return RunewarUtil.refreshPlayerCoordinate(serverId, playerId);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\CrossRmiServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */