/*     */ package com.linlongyx.sanguo.webgame.rmi;
/*     */ 
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.BetData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ public class LogicRmiUtil
/*     */ {
/*     */   public static void rewardGet(String logstr, int serverId, long playerId, ArrayList<Reward> rewards, ResourceEvent resourceEvent, boolean sendFlag) {
/*     */     try {
/*  68 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/*  69 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/*  70 */       if (null != logicRmi) {
/*  71 */         logicRmi.rewardGet(playerId, rewards, resourceEvent, sendFlag);
/*  72 */         LogUtil.errorLog(new Object[] { "LogicRmiUtil::rewardGet", Integer.valueOf(serverId), Long.valueOf(playerId), GsonUtil.toJson(rewards), resourceEvent, Boolean.valueOf(sendFlag) });
/*     */       } 
/*  74 */     } catch (RemoteException e) {
/*  75 */       e.printStackTrace();
/*  76 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
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
/*     */   public static DestinyPlayerData getLocalDestinyPlayerData(String logstr, int serverId, long playerId) {
/*     */     try {
/* 107 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 108 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 109 */       if (null != logicRmi) {
/* 110 */         return logicRmi.getLocalDestinyPlayerData(playerId);
/*     */       }
/* 112 */     } catch (RemoteException e) {
/* 113 */       e.printStackTrace();
/* 114 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 116 */     return null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addLocalDestinyDef(String logstr, int serverId, long playerId, int type, int time, int robNum, boolean win, long pkPlayerId, String pkPlayerName, Reward reward) {
/*     */     try {
/* 145 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 146 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 147 */       if (null != logicRmi) {
/* 148 */         logicRmi.addLocalDestinyDef(playerId, type, time, robNum, win, pkPlayerId, pkPlayerName, reward);
/*     */       }
/* 150 */     } catch (RemoteException e) {
/* 151 */       e.printStackTrace();
/* 152 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
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
/*     */   public static void setZoneId(String logstr, int serverId, int zoneId) {
/*     */     try {
/* 165 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 166 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 167 */       if (null != logicRmi) {
/* 168 */         logicRmi.setZoneId(zoneId);
/*     */       }
/* 170 */     } catch (RemoteException e) {
/* 171 */       e.printStackTrace();
/* 172 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
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
/*     */   public static void updateGroupPkData(String logstr, int serverId, int type, HashMap<Integer, PkDataItem[]> pkDataItemMap, MatchState matchState) {
/*     */     try {
/* 186 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 187 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 188 */       if (null != logicRmi) {
/* 189 */         logicRmi.updateGroupPkData(type, pkDataItemMap, matchState);
/*     */       }
/* 191 */     } catch (RemoteException e) {
/* 192 */       e.printStackTrace();
/* 193 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
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
/*     */   public static boolean sendDestinyTopReward(String logstr, int serverId, List<DestinyRankData> list) {
/*     */     try {
/* 207 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 208 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 209 */       if (null != logicRmi) {
/* 210 */         logicRmi.balanceDestinyRank(list);
/* 211 */         return true;
/*     */       } 
/* 213 */     } catch (RemoteException e) {
/* 214 */       e.printStackTrace();
/* 215 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 217 */     return false;
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
/*     */   public static boolean sendDestinyBetReward(String logstr, int serverId, ArrayList<BetData> betDataList) {
/*     */     try {
/* 230 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 231 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 232 */       if (null != logicRmi) {
/* 233 */         logicRmi.sendDestinyBetReward(betDataList);
/* 234 */         return true;
/*     */       } 
/* 236 */     } catch (RemoteException e) {
/* 237 */       e.printStackTrace();
/* 238 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 240 */     return false;
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
/*     */   public static boolean sendDestinyMatchReward(String logstr, int serverId, HashMap<Long, Integer> matchReward) {
/*     */     try {
/* 253 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 254 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 255 */       if (null != logicRmi) {
/* 256 */         logicRmi.sendDestinyMatchRewardMap(matchReward);
/* 257 */         return true;
/*     */       } 
/* 259 */     } catch (RemoteException e) {
/* 260 */       e.printStackTrace();
/* 261 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendWxFriendInfo(String logstr, WxPlayerInfo wxPlayerInfo, int serverId, long playerId) {
/*     */     try {
/* 269 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 270 */       if (wxPlayerInfo != null && wxPlayerInfo.totalCharge > 0L) {
/* 271 */         LogUtils.errorLog(new Object[] { "updateWxFriendInfo", Long.valueOf(playerId), Integer.valueOf(serverId), Integer.valueOf(wxPlayerInfo.level), Long.valueOf(wxPlayerInfo.totalCharge), Integer.valueOf(realServerId) });
/*     */       }
/* 273 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 274 */       if (null != logicRmi) {
/* 275 */         logicRmi.sendWxFriendInfo(wxPlayerInfo, playerId);
/*     */       }
/* 277 */     } catch (RemoteException e) {
/* 278 */       e.printStackTrace();
/* 279 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static PlayerData getPlayerData(String logstr, int serverId, long playerId) {
/*     */     try {
/* 286 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 287 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 288 */       if (null != logicRmi) {
/* 289 */         return logicRmi.getPlayerData(playerId);
/*     */       }
/* 291 */     } catch (RemoteException e) {
/* 292 */       e.printStackTrace();
/* 293 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 295 */     return null;
/*     */   }
/*     */   
/*     */   public static void sendCrossReward(String logstr, int serverId, long playerId, int type, ArrayList<Reward> rewardList) {
/*     */     try {
/* 300 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 301 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 302 */       if (null != logicRmi) {
/* 303 */         logicRmi.sendCrossReward(playerId, type, rewardList);
/*     */       }
/* 305 */     } catch (RemoteException e) {
/* 306 */       e.printStackTrace();
/* 307 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendCrossBattleNotice(String logstr, int serverId, int type) {
/*     */     try {
/* 313 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 314 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 315 */       if (null != logicRmi) {
/* 316 */         logicRmi.sendCrossBattleNotice(type);
/*     */       }
/* 318 */     } catch (RemoteException e) {
/* 319 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void pushRankList(String logstr, int serverId, int actId, ArrayList<RankingData> rankList) {
/*     */     try {
/* 325 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 326 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 327 */       if (null != logicRmi) {
/* 328 */         logicRmi.pushRankList(actId, rankList);
/*     */       }
/* 330 */     } catch (RemoteException e) {
/* 331 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean sendCrossPointRankReward(String logstr, int serverId, long playerId, int rank, ArrayList<Reward> rewardList) {
/*     */     try {
/* 337 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 338 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 339 */       if (null != logicRmi) {
/* 340 */         logicRmi.sendCrossPointRankReward(playerId, rank, rewardList);
/* 341 */         return true;
/*     */       } 
/* 343 */     } catch (RemoteException e) {
/* 344 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 346 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean sendCrossCampRankReward(String logstr, int serverId, int camp, int rank, ArrayList<Long> playerIdList, ArrayList<Reward> rewardList) {
/*     */     try {
/* 351 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 352 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 353 */       if (null != logicRmi) {
/* 354 */         logicRmi.sendCrossCampRankReward(camp, rank, playerIdList, rewardList);
/* 355 */         return true;
/*     */       } 
/* 357 */     } catch (RemoteException e) {
/* 358 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 360 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean sendCrossRaceReward(String logstr, int serverId, long playerId, int racePoint, int rank) {
/*     */     try {
/* 365 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 366 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 367 */       if (null != logicRmi) {
/* 368 */         return logicRmi.sendCrossRaceReward(playerId, racePoint, rank);
/*     */       }
/* 370 */     } catch (RemoteException e) {
/* 371 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 373 */     return false;
/*     */   }
/*     */   
/*     */   public static void sendCrossRaceNotice(String logstr, int serverId, int type) {
/*     */     try {
/* 378 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 379 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 380 */       if (null != logicRmi) {
/* 381 */         logicRmi.sendCrossRaceNotice(type);
/*     */       }
/* 383 */     } catch (RemoteException e) {
/* 384 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addTowerDefRecord(String logstr, int serverId, int layerId, long playerId, int type, int time, boolean win, long pkPlayerId, String pkPlayerName) {
/*     */     try {
/* 394 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 395 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 396 */       if (null != logicRmi) {
/* 397 */         logicRmi.addTowerDefRecord(layerId, playerId, type, time, win, pkPlayerId, pkPlayerName);
/*     */       }
/* 399 */     } catch (RemoteException e) {
/* 400 */       e.printStackTrace();
/* 401 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendTowerRankReward(String logstr, int serverId, HashMap<Integer, Long> rankMap) {
/*     */     try {
/* 407 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 408 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 409 */       if (null != logicRmi) {
/* 410 */         logicRmi.sendTowerRankReward(rankMap);
/*     */       }
/* 412 */     } catch (RemoteException e) {
/* 413 */       e.printStackTrace();
/* 414 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addRunewarDefRecord(String logstr, int serverId, long playerId, BattleRecordData record) {
/*     */     try {
/* 420 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 421 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 422 */       if (null != logicRmi) {
/* 423 */         logicRmi.addRunewarDefRecord(playerId, record);
/*     */       }
/* 425 */     } catch (RemoteException e) {
/* 426 */       e.printStackTrace();
/* 427 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void runewarRankBalance(String logstr, int serverId, HashMap<Long, Integer> playerRankMap) {
/*     */     try {
/* 433 */       int realServerId = LogicRmiManager.getRealServer(serverId);
/* 434 */       ILogicRmi logicRmi = LogicRmiManager.getILogicRmi(realServerId);
/* 435 */       if (null != logicRmi) {
/* 436 */         logicRmi.runewarRankBalance(playerRankMap);
/*     */       }
/* 438 */     } catch (RemoteException e) {
/* 439 */       e.printStackTrace();
/* 440 */       LogUtil.errorLog(new Object[] { logstr, Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\LogicRmiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */