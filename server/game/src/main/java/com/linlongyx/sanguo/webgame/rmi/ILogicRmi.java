package com.linlongyx.sanguo.webgame.rmi;

import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
import com.linlongyx.sanguo.webgame.rmi.data.BetData;
import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ILogicRmi extends Remote {
  void shutDown() throws RemoteException;
  
  void rewardGet(long paramLong, ArrayList<Reward> paramArrayList, ResourceEvent paramResourceEvent, boolean paramBoolean) throws RemoteException;
  
  DestinyPlayerData getLocalDestinyPlayerData(long paramLong) throws RemoteException;
  
  void addLocalDestinyDef(long paramLong1, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong2, String paramString, Reward paramReward) throws RemoteException;
  
  void refreshDestinyRanking(ArrayList<DestinyRankData> paramArrayList) throws RemoteException;
  
  void destinyDailyReward(long paramLong, int paramInt) throws RemoteException;
  
  void balanceDestinyRank(List<DestinyRankData> paramList) throws RemoteException;
  
  int getServerWorldLevel() throws RemoteException;
  
  void setZoneId(int paramInt) throws RemoteException;
  
  void updateGroupPkData(int paramInt, HashMap<Integer, PkDataItem[]> paramHashMap, MatchState paramMatchState) throws RemoteException;
  
  void sendDestinyBetReward(ArrayList<BetData> paramArrayList) throws RemoteException;
  
  void sendDestinyMatchReward(long paramLong, int paramInt) throws RemoteException;
  
  void sendDestinyMatchRewardMap(HashMap<Long, Integer> paramHashMap) throws RemoteException;
  
  void sendWxFriendInfo(WxPlayerInfo paramWxPlayerInfo, long paramLong) throws RemoteException;
  
  PlayerData getPlayerData(long paramLong) throws RemoteException;
  
  void sendCrossReward(long paramLong, int paramInt, ArrayList<Reward> paramArrayList) throws RemoteException;
  
  void sendCrossBattleNotice(int paramInt) throws RemoteException;
  
  void pushRankList(int paramInt, ArrayList<RankingData> paramArrayList) throws RemoteException;
  
  void sendCrossPointRankReward(long paramLong, int paramInt, ArrayList<Reward> paramArrayList) throws RemoteException;
  
  void sendCrossCampRankReward(int paramInt1, int paramInt2, ArrayList<Long> paramArrayList, ArrayList<Reward> paramArrayList1) throws RemoteException;
  
  boolean sendCrossRaceReward(long paramLong, int paramInt1, int paramInt2) throws RemoteException;
  
  void sendCrossRaceNotice(int paramInt) throws RemoteException;
  
  void addTowerDefRecord(int paramInt1, long paramLong1, int paramInt2, int paramInt3, boolean paramBoolean, long paramLong2, String paramString) throws RemoteException;
  
  void sendTowerRankReward(HashMap<Integer, Long> paramHashMap) throws RemoteException;
  
  void addRunewarDefRecord(long paramLong, BattleRecordData paramBattleRecordData) throws RemoteException;
  
  void runewarRankBalance(HashMap<Long, Integer> paramHashMap) throws RemoteException;
}


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\ILogicRmi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */