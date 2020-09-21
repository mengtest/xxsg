package com.linlongyx.sanguo.webgame.rmi;

import com.linlongyx.sanguo.webgame.common.structure.ODTime;
import com.linlongyx.sanguo.webgame.common.structure.Pair;
import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyBetRecord;
import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarFightersData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
import com.linlongyx.sanguo.webgame.rmi.data.PkRecord;
import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
import com.linlongyx.sanguo.webgame.rmi.data.RacePlayerData;
import com.linlongyx.sanguo.webgame.rmi.data.ServerData;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface ICrossRmi extends Remote {
  void registerLogicRmi(String paramString, ServerData paramServerData) throws RemoteException;
  
  void removeLogicRmi(int paramInt) throws RemoteException;
  
  boolean needInit(int paramInt) throws RemoteException;
  
  boolean heartBeat(int paramInt1, int paramInt2) throws RemoteException;
  
  void addDestinyDef(int paramInt1, long paramLong1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, long paramLong2, String paramString, Reward paramReward) throws RemoteException;
  
  ArrayList<DestinyPlayerData> getDestinyPlayerData(int paramInt, HashSet<Long> paramHashSet) throws RemoteException;
  
  ArrayList<DestinyPlayerData> destinyRecomment(DestinyPlayerData paramDestinyPlayerData, HashSet<Long> paramHashSet, int paramInt) throws RemoteException;
  
  void updateDestinyPlayerData(DestinyPlayerData paramDestinyPlayerData) throws RemoteException;
  
  void updateTalismanMap(int paramInt, long paramLong, HashMap<Long, HashMap<Integer, Integer>> paramHashMap) throws RemoteException;
  
  void destinyRemovePlayer(int paramInt, long paramLong) throws RemoteException;
  
  DestinyRankData getDestinyRankData(int paramInt, long paramLong) throws RemoteException;
  
  int addDestinyPoint(int paramInt1, long paramLong, int paramInt2) throws RemoteException;
  
  void clearCrossPlayerData(int paramInt) throws RemoteException;
  
  int getZoneId(int paramInt) throws RemoteException;
  
  HashMap<Integer, PkDataItem[]> getPkDataItems(int paramInt1, int paramInt2) throws RemoteException;
  
  MatchState getCurrentMatchState(int paramInt) throws RemoteException;
  
  HashMap<Long, Integer> getBetNum(int paramInt, String paramString) throws RemoteException;
  
  int addPlayerBetNum(int paramInt1, String paramString, long paramLong1, long paramLong2, int paramInt2, int paramInt3) throws RemoteException;
  
  ArrayList<DestinyBetRecord> getPlayerBetRecord(int paramInt, long paramLong) throws RemoteException;
  
  PkRecord getPkRecord(int paramInt, String paramString) throws RemoteException;
  
  boolean hasMatch(int paramInt, long paramLong) throws RemoteException;
  
  void gmMatch(int paramInt1, int paramInt2) throws RemoteException;
  
  void gmBattle(int paramInt1, int paramInt2) throws RemoteException;
  
  boolean isBattleOpen() throws RemoteException;
  
  ArrayList<Long> getCurBetPkPlayers(int paramInt) throws RemoteException;
  
  PkDataItem getCurPkDataItem(int paramInt) throws RemoteException;
  
  void updateWxFriendInfo(int paramInt, WxPlayerInfo paramWxPlayerInfo, long paramLong) throws RemoteException;
  
  void uploadPlayerRankData(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, RankingData paramRankingData) throws RemoteException;
  
  void addRankServer(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5) throws RemoteException;
  
  ArrayList<RankingData> getRankList(int paramInt, boolean paramBoolean) throws RemoteException;
  
  void updatePlayerStatus(long paramLong, HashMap<Integer, ODTime> paramHashMap) throws RemoteException;
  
  int updatePlayerRacePoint(int paramInt1, long paramLong, int paramInt2) throws RemoteException;
  
  RacePlayerData recommentOne(int paramInt1, long paramLong1, long paramLong2, int paramInt2) throws RemoteException;
  
  ArrayList<RankingData> getRaceRankList(int paramInt1, int paramInt2) throws RemoteException;
  
  RankingData getPlayerRaceRank(int paramInt1, long paramLong, int paramInt2) throws RemoteException;
  
  int getCurRaceId() throws RemoteException;
  
  int newRace() throws RemoteException;
  
  boolean isRaceOpen() throws RemoteException;
  
  void playerJoinRace(int paramInt, PlayerData paramPlayerData) throws RemoteException;
  
  int getRaceState() throws RemoteException;
  
  void updateCandidateFighters(int paramInt, long paramLong, ArrayList<Pair<Integer, Integer>> paramArrayList) throws RemoteException;
  
  void updateZhenfa(int paramInt, long paramLong, Pair<Integer, Integer> paramPair) throws RemoteException;
  
  List<RankingData> getTowerData(PlayerData paramPlayerData, int paramInt) throws RemoteException;
  
  Pair<Integer, ArrayList<RankingData>> getTowerRankList(int paramInt, long paramLong) throws RemoteException;
  
  Pair<RankingData, Pair<Integer, PlayerData>> fightTowerLayer(int paramInt1, long paramLong, int paramInt2) throws RemoteException;
  
  Pair<Integer, RankingData> towerLayerState(int paramInt1, long paramLong, int paramInt2) throws RemoteException;
  
  boolean fightTowerResult(int paramInt1, long paramLong1, long paramLong2, int paramInt2, boolean paramBoolean) throws RemoteException;
  
  void addTowerDefRecord(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, boolean paramBoolean, long paramLong2, String paramString) throws RemoteException;
  
  boolean giveupTowerLayer(int paramInt1, long paramLong, int paramInt2) throws RemoteException;
  
  void runewarPlayerJoin(PlayerData paramPlayerData) throws RemoteException;
  
  List<RunewarPlayerData> getRunewardPlayerData(int paramInt, long paramLong) throws RemoteException;
  
  RunewarFightersData getRunewarFighterData(int paramInt, long paramLong1, long paramLong2) throws RemoteException;
  
  List<RankingData> getRunewarRankList(int paramInt) throws RemoteException;
  
  PlayerData getRunewarTargetPlayerdata(int paramInt, long paramLong1, long paramLong2) throws RemoteException;
  
  int runewarFightResult(int paramInt1, long paramLong1, long paramLong2, int paramInt2, int paramInt3, boolean paramBoolean, BattleRecordData paramBattleRecordData) throws RemoteException;
  
  boolean isRunewarOpen() throws RemoteException;
  
  List<RunewarPlayerData> refreshPlayerCoordinate(int paramInt, long paramLong) throws RemoteException;
}


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\ICrossRmi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */