/*     */ package com.linlongyx.sanguo.webgame.rmi.destiny;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*     */ import java.io.IOException;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DestinyCache {
/*  24 */   private static DestinyMap destinyMap = new DestinyMap();
/*     */   
/*  26 */   private static Map<Integer, Map<Long, DestinyRankData>> cacheZoneSortList = Collections.synchronizedMap(new HashMap<>());
/*  27 */   private static Map<Integer, Boolean> zoneHasSort = new HashMap<>();
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
/*     */   public static void clearServerPlayerData(int serverId) {
/*  40 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/*  41 */     destinyMap.clearServerPlayerData(serverId);
/*  42 */     reRank(zoneId);
/*     */   }
/*     */   
/*     */   private static void reRank(int zoneId) {
/*  46 */     cacheZoneSortList.putIfAbsent(Integer.valueOf(zoneId), new HashMap<>());
/*  47 */     ((Map)cacheZoneSortList.get(Integer.valueOf(zoneId))).clear();
/*  48 */     List<DestinyRankData> rankDataList = destinyMap.getDestinyRankDataList(zoneId);
/*  49 */     rankDataList.forEach(data -> ((Map<Long, DestinyRankData>)cacheZoneSortList.get(Integer.valueOf(zoneId))).put(Long.valueOf(data.playerId), data));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void destinyRemovePlayer(int serverId, long playerId) {
/*  55 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/*  56 */     ((Map)destinyMap.getZoneToPlayerMap().get(Integer.valueOf(zoneId))).remove(Long.valueOf(playerId));
/*  57 */     reRank(zoneId);
/*     */   }
/*     */   
/*     */   public static void addServer(int opentime, int serverId, int worldLevel) {
/*  61 */     destinyMap.addServer(serverId, worldLevel);
/*     */   }
/*     */   
/*     */   public static void resetZoneSortMap() {
/*  65 */     zoneHasSort.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<DestinyRankData> getTopRankList(int serverId) {
/*  73 */     if (!destinyMap.getServerIdToZoneId().containsKey(Integer.valueOf(serverId))) {
/*  74 */       return new ArrayList<>();
/*     */     }
/*  76 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/*  77 */     zoneHasSort.putIfAbsent(Integer.valueOf(zoneId), Boolean.valueOf(false));
/*  78 */     cacheZoneSortList.putIfAbsent(Integer.valueOf(zoneId), new HashMap<>());
/*  79 */     ArrayList<DestinyRankData> resultList = new ArrayList<>();
/*  80 */     if (!((Boolean)zoneHasSort.get(Integer.valueOf(zoneId))).booleanValue()) {
/*  81 */       zoneHasSort.put(Integer.valueOf(zoneId), Boolean.valueOf(true));
/*  82 */       ((Map)cacheZoneSortList.get(Integer.valueOf(zoneId))).clear();
/*     */       
/*  84 */       List<DestinyRankData> rankDataList = destinyMap.getDestinyRankDataList(zoneId);
/*  85 */       rankDataList.forEach(data -> {
/*     */             ((Map<Long, DestinyRankData>)cacheZoneSortList.get(Integer.valueOf(zoneId))).put(Long.valueOf(data.playerId), data);
/*     */             resultList.add(data);
/*     */           });
/*  89 */       return resultList;
/*     */     } 
/*  91 */     resultList.addAll(((Map)cacheZoneSortList.get(Integer.valueOf(zoneId))).values());
/*  92 */     resultList.sort(Comparator.comparingInt(o -> o.rank));
/*  93 */     return resultList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void serverReZone(ArrayList<Pair<Integer, Integer>> pairs) {
/* 102 */     destinyMap.init(pairs);
/* 103 */     MatchUtil.reZone(destinyMap.getServerIdToZoneId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, List<DestinyRankData>> getTopPlayerMap() {
/* 112 */     return destinyMap.getTopPlayerMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearAll() {
/* 119 */     destinyMap.reset();
/*     */   }
/*     */   
/*     */   public static void updateDestinyPlayer(DestinyPlayerData destinyPlayerData) {
/* 123 */     destinyMap.updateDestinyPlayerData(destinyPlayerData);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void updateTalismanMap(int serverId, long playerId, Map<Long, Map<Integer, Integer>> talismanMap) {
/* 128 */     destinyMap.updateTalismanMap(serverId, playerId, talismanMap);
/*     */   }
/*     */   
/*     */   public static int addDestinyPoint(int serverId, long playerId, int destinyPoint) {
/* 132 */     return destinyMap.addDestinyPoint(serverId, playerId, destinyPoint);
/*     */   }
/*     */   
/*     */   public static void addLocalDestinyDef(int serverId, long playerId, int type, int time, int robNum, boolean win, long pkPlayerId, String pkPlayerName, Reward reward) {
/* 136 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 137 */     DestinyPlayerData destinyPlayerData = (DestinyPlayerData)((Map)destinyMap.getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId));
/* 138 */     if (null != destinyPlayerData) {
/* 139 */       LogicRmiUtil.addLocalDestinyDef("DestinyCache::addLocalDestinyDef", destinyPlayerData.getServerId(), playerId, type, time, robNum, win, pkPlayerId, pkPlayerName, reward);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<DestinyPlayerData> getDestinyPlayerData(int serverId, HashSet<Long> playerId) throws RemoteException {
/* 145 */     ArrayList<DestinyPlayerData> result = new ArrayList<>();
/* 146 */     for (Iterator<Long> iterator = playerId.iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/* 147 */       DestinyPlayerData destinyPlayerData = getLogicDestinyPlayerData(serverId, id);
/* 148 */       if (destinyPlayerData != null) {
/* 149 */         result.add(destinyPlayerData);
/*     */       } }
/*     */     
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   public static DestinyPlayerData getLogicDestinyPlayerData(int serverId, long playerId) throws RemoteException {
/* 156 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 157 */     DestinyPlayerData destinyPlayerData = (DestinyPlayerData)((Map)destinyMap.getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId));
/* 158 */     if (null != destinyPlayerData) {
/* 159 */       return LogicRmiUtil.getLocalDestinyPlayerData("DestinyCache::getDestinyPlayerData", destinyPlayerData.getServerId(), playerId);
/*     */     }
/* 161 */     return null;
/*     */   }
/*     */   
/*     */   public static DestinyPlayerData getLocalDestinyPlayerData(int serverId, long playerId) {
/* 165 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 166 */     return (DestinyPlayerData)((Map)destinyMap.getZoneToPlayerMap().get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId));
/*     */   }
/*     */   
/*     */   public static int getPlayerRank(int serverId, long playerId) {
/* 170 */     int zoneId = ((Integer)destinyMap.getServerIdToZoneId().get(Integer.valueOf(serverId))).intValue();
/* 171 */     cacheZoneSortList.putIfAbsent(Integer.valueOf(zoneId), new HashMap<>());
/* 172 */     if (((Map)cacheZoneSortList.get(Integer.valueOf(zoneId))).containsKey(Long.valueOf(playerId))) {
/* 173 */       return ((DestinyRankData)((Map)cacheZoneSortList.get(Integer.valueOf(zoneId))).get(Long.valueOf(playerId))).rank;
/*     */     }
/* 175 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArrayList<DestinyPlayerData> destinyRecomment(DestinyPlayerData myplayerData, HashSet<Long> battles, int recommentSize) throws RemoteException {
/* 180 */     updateDestinyPlayer(myplayerData);
/*     */     
/* 182 */     return recomment(myplayerData.getServerId(), myplayerData.getPlayerId(), myplayerData.getFightValue(), battles, recommentSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<DestinyPlayerData> recomment(int serverId, long playerId, long fightValue, HashSet<Long> battles, int recommentSize) {
/* 190 */     return destinyMap.recomment(serverId, playerId, fightValue, battles, recommentSize);
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] toBytes() {
/*     */     try {
/* 196 */       return CrossUtil.objectMapper.writeValueAsBytes(destinyMap);
/* 197 */     } catch (JsonProcessingException e) {
/* 198 */       e.printStackTrace();
/* 199 */       LogUtil.errorLog(new Object[] { "write playerDatas to bytes error, ", e.getMessage() });
/*     */       
/* 201 */       return null;
/*     */     } 
/*     */   }
/*     */   public static DestinyMap getDestinyMap() {
/* 205 */     return destinyMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void fromBytes(byte[] bytes) {
/*     */     try {
/* 211 */       destinyMap = (DestinyMap)CrossUtil.objectMapper.readValue(bytes, DestinyMap.class);
/* 212 */     } catch (IOException e) {
/* 213 */       e.printStackTrace();
/* 214 */       LogUtil.errorLog(new Object[] { "read playerDatas from bytes error, ", e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static DestinyMap loadRecord(byte[] bytes) {
/*     */     try {
/* 221 */       return (DestinyMap)CrossUtil.objectMapper.readValue(bytes, DestinyMap.class);
/* 222 */     } catch (IOException e) {
/* 223 */       e.printStackTrace();
/*     */       
/* 225 */       return null;
/*     */     } 
/*     */   }
/*     */   public static DestinyRankData transform(DestinyPlayerData destinyPlayerData, int rank) {
/* 229 */     DestinyRankData destinyRankData = new DestinyRankData();
/* 230 */     if (destinyPlayerData != null) {
/*     */       
/* 232 */       destinyRankData.destinyPoint = destinyPlayerData.getDestinyPoint();
/* 233 */       destinyRankData.fightValue = destinyPlayerData.getFightValue();
/* 234 */       destinyRankData.head = destinyPlayerData.getHead();
/* 235 */       destinyRankData.playerId = destinyPlayerData.getPlayerId();
/* 236 */       destinyRankData.playerName = destinyPlayerData.getPlayerName();
/* 237 */       destinyRankData.quality = destinyPlayerData.getQuality();
/* 238 */       destinyRankData.rank = rank;
/*     */     } 
/* 240 */     return destinyRankData;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\destiny\DestinyCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */