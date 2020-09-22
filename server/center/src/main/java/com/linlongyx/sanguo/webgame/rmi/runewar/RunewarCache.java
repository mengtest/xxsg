/*     */ package com.linlongyx.sanguo.webgame.rmi.runewar;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarFightersData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RunewarPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiManager;
/*     */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.CoordinateData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonIgnoreProperties(ignoreUnknown = true)
/*     */ public class RunewarCache
/*     */ {
/*     */   public enum RunewarStatus
/*     */   {
/*  32 */     OPEN(1),
/*  33 */     CLOSE(2);
/*     */     
/*     */     int state;
/*     */     
/*     */     RunewarStatus(int state) {
/*  38 */       this.state = state;
/*     */     }
/*     */   }
/*     */   
/*  42 */   private Map<Integer, RunewarZone> runewarZoneMap = new HashMap<>();
/*  43 */   private Map<Integer, Integer> serverToZone = new HashMap<>();
/*  44 */   private volatile RunewarStatus status = RunewarStatus.OPEN;
/*     */   
/*     */   public void reZone() {
/*  47 */     this.runewarZoneMap = new HashMap<>();
/*  48 */     this.serverToZone = new HashMap<>();
/*  49 */     ArrayList<Pair<Integer, Integer>> serverWorldLevelList = LogicRmiManager.getServerWorldLevel();
/*  50 */     serverWorldLevelList.sort((o1, o2) -> Integer.compare(((Integer)o2.getValue()).intValue(), ((Integer)o1.getValue()).intValue()));
/*  51 */     int pageSize = RunewarUtil.SERVER_SIZE;
/*  52 */     for (int i = 0; i < serverWorldLevelList.size() / pageSize + 1; i++) {
/*  53 */       int start = i * pageSize;
/*  54 */       int max = (i + 1) * pageSize;
/*  55 */       int end = (max > serverWorldLevelList.size()) ? serverWorldLevelList.size() : max;
/*  56 */       List<Integer> subList = (List<Integer>)serverWorldLevelList.subList(start, end).stream().map(Pair::getValue).collect(Collectors.toList());
/*  57 */       RunewarZone runewarZone = new RunewarZone(i + 1, subList);
/*  58 */       this.runewarZoneMap.put(Integer.valueOf(runewarZone.getZoneId()), runewarZone);
/*     */       
/*  60 */       for (Iterator<Integer> iterator = subList.iterator(); iterator.hasNext(); ) { int serverId = ((Integer)iterator.next()).intValue();
/*  61 */         this.serverToZone.put(Integer.valueOf(serverId), Integer.valueOf(runewarZone.getZoneId())); }
/*     */     
/*     */     } 
/*  64 */     this.status = RunewarStatus.OPEN;
/*     */   }
/*     */   
/*     */   public void balanceRank() {
/*  68 */     this.status = RunewarStatus.CLOSE;
/*  69 */     for (RunewarZone zone : this.runewarZoneMap.values()) {
/*  70 */       zone.balanceRank();
/*     */     }
/*     */   }
/*     */   
/*     */   private RunewarZone chooseNotFullZone() {
/*  75 */     for (RunewarZone zone : this.runewarZoneMap.values()) {
/*  76 */       if (zone.getServerList().size() < RunewarUtil.SERVER_SIZE) {
/*  77 */         return zone;
/*     */       }
/*     */     } 
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public void playerJoin(PlayerData playerData) {
/*  84 */     if (this.status == RunewarStatus.CLOSE) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     int oldPoint = 0;
/*  89 */     if (CrossUtil.isHefuPlayer(playerData.getServerId(), playerData.getPlayerId())) {
/*  90 */       for (RunewarZone runewarZone : this.runewarZoneMap.values()) {
/*  91 */         CoordinateData data = runewarZone.getCoordinateMap().get(Long.valueOf(playerData.getPlayerId()));
/*  92 */         if (data != null && data.getPlayerData().getServerId() != playerData.getServerId()) {
/*  93 */           oldPoint = data.getPoint();
/*  94 */           runewarZone.removePlayer(playerData.getPlayerId());
/*     */         } 
/*     */       } 
/*     */     }
/*  98 */     if (this.serverToZone.containsKey(Integer.valueOf(playerData.getServerId()))) {
/*  99 */       RunewarZone zone = this.runewarZoneMap.get(this.serverToZone.get(Integer.valueOf(playerData.getServerId())));
/* 100 */       zone.playerJoin(playerData);
/*     */     } else {
/* 102 */       RunewarZone zone = chooseNotFullZone();
/* 103 */       if (zone != null && zone.addServer(playerData.getServerId())) {
/* 104 */         zone.playerJoin(playerData);
/* 105 */         this.serverToZone.putIfAbsent(Integer.valueOf(playerData.getServerId()), Integer.valueOf(zone.getZoneId()));
/*     */       } else {
/* 107 */         synchronized (RunewarCache.class) {
/* 108 */           zone = chooseNotFullZone();
/* 109 */           if (zone == null) {
/* 110 */             List<Integer> list = new ArrayList<>();
/* 111 */             list.add(Integer.valueOf(playerData.getServerId()));
/* 112 */             zone = new RunewarZone(this.runewarZoneMap.size() + 1, list);
/* 113 */             this.runewarZoneMap.put(Integer.valueOf(zone.getZoneId()), zone);
/*     */           } else {
/* 115 */             zone.playerJoin(playerData);
/*     */           } 
/*     */         } 
/* 118 */         this.serverToZone.putIfAbsent(Integer.valueOf(playerData.getServerId()), Integer.valueOf(zone.getZoneId()));
/*     */       } 
/*     */     } 
/* 121 */     if (oldPoint > 0) {
/* 122 */       RunewarZone zone = this.runewarZoneMap.get(this.serverToZone.get(Integer.valueOf(playerData.getServerId())));
/* 123 */       zone.updatePlayerPoint(playerData.getPlayerId(), oldPoint);
/*     */     } 
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public CoordinateData getPlayerCoordinateData(int serverId, long playerId) {
/* 129 */     if (this.status == RunewarStatus.CLOSE) {
/* 130 */       return null;
/*     */     }
/* 132 */     if (!this.serverToZone.containsKey(Integer.valueOf(serverId))) {
/* 133 */       return null;
/*     */     }
/* 135 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 136 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/* 137 */     return zone.getCoordinateDataByPlayerId(playerId);
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public List<RunewarPlayerData> getRunewardPlayerData(int serverId, long playerId) {
/* 142 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 143 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/* 144 */     return zone.getRunewardPlayerData(playerId);
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public RunewarFightersData getRunewarFighterData(int serverId, long playerId, long targetPlayerId) {
/* 149 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 150 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/* 151 */     return zone.getRunewarFighterData(playerId, targetPlayerId);
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public List<RankingData> getRankList(int serverId) {
/* 156 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 157 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/* 158 */     return zone.getRankList();
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public boolean isOpen() {
/* 163 */     return (this.status == RunewarStatus.OPEN);
/*     */   }
/*     */   
/*     */   @JsonIgnore
/*     */   public PlayerData getRunewarTargetPlayerdata(int serverId, long playerId, long targetPlayerId) {
/* 168 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 169 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/* 170 */     return zone.getRunewarTargetPlayerdata(playerId, targetPlayerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public int runewarFightResult(int serverId, long playerId, long targetPlayerId, int targetServerId, int point, boolean win, BattleRecordData record) {
/* 175 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 176 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/*     */     
/* 178 */     Fibers.createExecutorService().execute(() -> LogicRmiUtil.addRunewarDefRecord("runewarFightResult", targetServerId, targetPlayerId, record));
/*     */     
/* 180 */     return zone.runewarFightResult(playerId, targetPlayerId, point, win);
/*     */   }
/*     */   
/*     */   public List<RunewarPlayerData> refreshPlayerCoordinate(int serverId, long playerId) {
/* 184 */     int zoneId = ((Integer)this.serverToZone.get(Integer.valueOf(serverId))).intValue();
/* 185 */     RunewarZone zone = this.runewarZoneMap.get(Integer.valueOf(zoneId));
/* 186 */     return zone.refreshPlayerCoordinate(playerId, false);
/*     */   }
/*     */   
/*     */   public Map<Integer, RunewarZone> getRunewarZoneMap() {
/* 190 */     return this.runewarZoneMap;
/*     */   }
/*     */   
/*     */   public void setRunewarZoneMap(Map<Integer, RunewarZone> runewarZoneMap) {
/* 194 */     this.runewarZoneMap = runewarZoneMap;
/*     */   }
/*     */   
/*     */   public Map<Integer, Integer> getServerToZone() {
/* 198 */     return this.serverToZone;
/*     */   }
/*     */   
/*     */   public void setServerToZone(Map<Integer, Integer> serverToZone) {
/* 202 */     this.serverToZone = serverToZone;
/*     */   }
/*     */   
/*     */   public RunewarStatus getStatus() {
/* 206 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(RunewarStatus status) {
/* 210 */     this.status = status;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\runewar\RunewarCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */