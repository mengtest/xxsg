/*     */ package com.linlongyx.sanguo.webgame.rmi;
/*     */ 
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.BetData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.ServerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.DestinyCache;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.springframework.remoting.rmi.RmiProxyFactoryBean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogicRmiManager
/*     */ {
/*  28 */   private static ConcurrentHashMap<Integer, ILogicRmi> logicRmiMap = new ConcurrentHashMap<>(4);
/*     */   
/*  30 */   public static Map<Integer, ServerData> serverDataMap = new HashMap<>();
/*     */   
/*     */   public static boolean isRegisted(int serverId) {
/*  33 */     return logicRmiMap.containsKey(Integer.valueOf(serverId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addLogicRmi(int serverId, ILogicRmi rmi) {
/*  42 */     logicRmiMap.put(Integer.valueOf(serverId), rmi);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeLogicRmi(int serverId) {
/*  50 */     logicRmiMap.remove(Integer.valueOf(serverId));
/*  51 */     LogUtil.errorLog(new Object[] { "LogicRmiManager::removeLogicRmi:key serverId=" + serverId + ",shutdown remove battle rmi and battle data" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void lookUpLogicRmi(int serverId, String rmiUrl, int opentime, int worldLevel) {
/*     */     try {
/*  61 */       RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
/*  62 */       rmiProxyFactoryBean.setServiceUrl(rmiUrl);
/*  63 */       rmiProxyFactoryBean.setServiceInterface(ILogicRmi.class);
/*  64 */       rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
/*  65 */       rmiProxyFactoryBean.setLookupStubOnStartup(false);
/*  66 */       rmiProxyFactoryBean.afterPropertiesSet();
/*  67 */       ILogicRmi iLogicRmi = (ILogicRmi)rmiProxyFactoryBean.getObject();
/*  68 */       if (iLogicRmi != null) {
/*  69 */         addLogicRmi(serverId, iLogicRmi);
/*     */         
/*  71 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::lookUpLogicRmi:success lookup logic battle rmi serverId=" + serverId + ",rmiUrl=" + rmiUrl });
/*     */       } 
/*  73 */     } catch (Exception e) {
/*  74 */       LogUtil.errorLog(new Object[] { "LogicRmiManager::lookUpLogicRmi:zoneId=" + serverId + ",rmiUrl=" + rmiUrl, e });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ILogicRmi getILogicRmi(int serverId) {
/*  80 */     return logicRmiMap.get(Integer.valueOf(serverId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void shutDown() {
/*  87 */     if (logicRmiMap.isEmpty())
/*     */       return; 
/*  89 */     Iterator<Map.Entry<Integer, ILogicRmi>> iterator = logicRmiMap.entrySet().iterator();
/*  90 */     while (iterator.hasNext()) {
/*     */       try {
/*  92 */         ((ILogicRmi)((Map.Entry)iterator.next()).getValue()).shutDown();
/*  93 */       } catch (Exception e) {
/*  94 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::shutDown", e });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void balanceDestinyTops() {
/* 115 */     if (logicRmiMap.isEmpty()) {
/*     */       return;
/*     */     }
/* 118 */     Map<Integer, List<DestinyRankData>> map = DestinyCache.getTopPlayerMap();
/* 119 */     LogUtil.errorLog(new Object[] { "destiny rank balance -> ", GsonUtil.toJson(map) });
/* 120 */     for (Map.Entry<Integer, List<DestinyRankData>> kv : map.entrySet()) {
/*     */       try {
/* 122 */         if (logicRmiMap.containsKey(kv.getKey())) {
/* 123 */           ((ILogicRmi)logicRmiMap.get(kv.getKey())).balanceDestinyRank(kv.getValue()); continue;
/*     */         } 
/* 125 */         MatchUtil.getCurMatchCache().getRewardCache(((Integer)kv.getKey()).intValue()).setDestinyTopRewardCache(kv.getValue());
/*     */       }
/* 127 */       catch (Exception e) {
/* 128 */         MatchUtil.getCurMatchCache().getRewardCache(((Integer)kv.getKey()).intValue()).setDestinyTopRewardCache(kv.getValue());
/* 129 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::balanceDestinyTops", kv.getKey(), "error:", Arrays.toString((Object[])e.getStackTrace()) });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendDestinyBetReward(String pkId, Map<Integer, ArrayList<BetData>> betDataMap) {
/* 135 */     if (logicRmiMap.isEmpty()) {
/*     */       return;
/*     */     }
/* 138 */     LogUtil.errorLog(new Object[] { "destiny bet balance -> ", GsonUtil.toJson(betDataMap) });
/* 139 */     for (Map.Entry<Integer, ArrayList<BetData>> kv : betDataMap.entrySet()) {
/*     */       try {
/* 141 */         if (logicRmiMap.containsKey(kv.getKey())) {
/* 142 */           ((ILogicRmi)logicRmiMap.get(kv.getKey())).sendDestinyBetReward(kv.getValue()); continue;
/*     */         } 
/* 144 */         MatchUtil.getCurMatchCache().getRewardCache(((Integer)kv.getKey()).intValue()).getBetDataCache().put(pkId, kv.getValue());
/*     */       }
/* 146 */       catch (Exception e) {
/* 147 */         MatchUtil.getCurMatchCache().getRewardCache(((Integer)kv.getKey()).intValue()).getBetDataCache().put(pkId, kv.getValue());
/* 148 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::sendDestinyBetReward", kv.getKey(), "error:", Arrays.toString((Object[])e.getStackTrace()) });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendDestinyMatchReward(int serverId, long playerId, int type) {
/* 154 */     if (logicRmiMap.isEmpty()) {
/*     */       return;
/*     */     }
/* 157 */     LogUtil.errorLog(new Object[] { "destiny match reward -> ", Long.valueOf(playerId), Integer.valueOf(type) });
/*     */     try {
/* 159 */       if (logicRmiMap.containsKey(Integer.valueOf(serverId))) {
/* 160 */         ((ILogicRmi)logicRmiMap.get(Integer.valueOf(serverId))).sendDestinyMatchReward(playerId, type);
/*     */       } else {
/* 162 */         MatchUtil.getCurMatchCache().getRewardCache(serverId).getMatchRewardCache().put(Long.valueOf(playerId), Integer.valueOf(type));
/*     */       } 
/* 164 */     } catch (Exception e) {
/* 165 */       MatchUtil.getCurMatchCache().getRewardCache(serverId).getMatchRewardCache().put(Long.valueOf(playerId), Integer.valueOf(type));
/* 166 */       LogUtil.errorLog(new Object[] { "LogicRmiManager::sendDestinyMatchReward", Integer.valueOf(serverId), Long.valueOf(playerId), Integer.valueOf(type), "error:", Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateGroupPkData(int type, MatchState matchState) {
/* 175 */     if (logicRmiMap.isEmpty())
/*     */       return; 
/* 177 */     Map<Integer, HashMap<Integer, PkDataItem[]>> zonePkDataMap = new HashMap<>();
/* 178 */     Iterator<Map.Entry<Integer, ILogicRmi>> iterator = logicRmiMap.entrySet().iterator();
/* 179 */     while (iterator.hasNext()) {
/*     */       try {
/* 181 */         Map.Entry<Integer, ILogicRmi> next = iterator.next();
/* 182 */         int zoneId = MatchUtil.getZoneByServerId(((Integer)next.getKey()).intValue()).getZoneId();
/* 183 */         if (!zonePkDataMap.containsKey(Integer.valueOf(zoneId))) {
/* 184 */           zonePkDataMap.put(Integer.valueOf(zoneId), MatchUtil.getGroupPkDataList(((Integer)next.getKey()).intValue(), type));
/*     */         }
/* 186 */         ((ILogicRmi)next.getValue()).updateGroupPkData(type, zonePkDataMap.get(Integer.valueOf(zoneId)), matchState);
/* 187 */       } catch (Exception e) {
/* 188 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::updateGroupPkData", e });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void getDestinyTops() {
/* 194 */     if (logicRmiMap.isEmpty()) {
/*     */       return;
/*     */     }
/* 197 */     DestinyCache.resetZoneSortMap();
/* 198 */     Iterator<Map.Entry<Integer, ILogicRmi>> iterator = logicRmiMap.entrySet().iterator();
/* 199 */     while (iterator.hasNext()) {
/*     */       try {
/* 201 */         Map.Entry<Integer, ILogicRmi> next = iterator.next();
/* 202 */         ArrayList<DestinyRankData> list = DestinyCache.getTopRankList(((Integer)next.getKey()).intValue());
/* 203 */         ((ILogicRmi)next.getValue()).refreshDestinyRanking(list);
/* 204 */       } catch (Exception e) {
/* 205 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::getDestinyTops", e });
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<Pair<Integer, Integer>> getServerWorldLevel() {
/* 211 */     ArrayList<Pair<Integer, Integer>> pairList = new ArrayList<>();
/* 212 */     if (logicRmiMap.isEmpty()) {
/* 213 */       return pairList;
/*     */     }
/* 215 */     Iterator<Map.Entry<Integer, ILogicRmi>> iterator = logicRmiMap.entrySet().iterator();
/* 216 */     while (iterator.hasNext()) {
/*     */       try {
/* 218 */         Map.Entry<Integer, ILogicRmi> next = iterator.next();
/* 219 */         int worldLevel = ((ILogicRmi)next.getValue()).getServerWorldLevel();
/* 220 */         pairList.add(new Pair(next.getKey(), Integer.valueOf(worldLevel)));
/* 221 */       } catch (Exception e) {
/* 222 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::getServerWorldLevel", e });
/*     */       } 
/*     */     } 
/* 225 */     return pairList;
/*     */   }
/*     */   
/*     */   public static void getDestinyTops(int serverId) {
/* 229 */     if (logicRmiMap.isEmpty()) {
/*     */       return;
/*     */     }
/* 232 */     ArrayList<DestinyRankData> list = DestinyCache.getTopRankList(serverId);
/* 233 */     ILogicRmi logicRmi = logicRmiMap.get(Integer.valueOf(serverId));
/* 234 */     if (null != logicRmi) {
/*     */       try {
/* 236 */         logicRmi.refreshDestinyRanking(list);
/* 237 */       } catch (Exception e) {
/* 238 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::getDestinyTops:serverId", Integer.valueOf(serverId), e });
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRealServer(int serverId) {
/* 249 */     int server = 0;
/* 250 */     if (!serverDataMap.containsKey(Integer.valueOf(serverId))) {
/* 251 */       for (Iterator<Integer> iterator = serverDataMap.keySet().iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 252 */         if (((ServerData)serverDataMap.get(Integer.valueOf(key))).getHefu().contains(Integer.valueOf(serverId))) {
/* 253 */           server = key;
/*     */           break;
/*     */         }  }
/*     */     
/*     */     } else {
/* 258 */       server = serverId;
/*     */     } 
/* 260 */     return server;
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
/*     */   public static ConcurrentHashMap<Integer, ILogicRmi> getLogicRmiMap() {
/* 277 */     return logicRmiMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\LogicRmiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */