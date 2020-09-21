/*     */ package com.linlongyx.sanguo.webgame.processors.destiny;
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.sanguo.webgame.app.activitybag.ActivityBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.RedNoticeConstant;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattlePlayerData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyGroupFightData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkRecord;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ 
/*     */ public class DestinyUtil {
/*  37 */   private static CopyOnWriteArrayList<DestinyRankData> destinyRankList = new CopyOnWriteArrayList<>();
/*  38 */   private static Map<Long, Integer> rankMap = new HashMap<>();
/*  39 */   private static Lock lock = new ReentrantLock();
/*     */   
/*  41 */   public static ConcurrentHashMap<Integer, PkDataItem[]> groupPkDataMap = (ConcurrentHashMap)new ConcurrentHashMap<>();
/*     */   
/*  43 */   private static ConcurrentHashMap<String, PkRecord> pkRecordMap = new ConcurrentHashMap<>();
/*     */   
/*  45 */   public static volatile MatchState curMatchState = MatchState.begin;
/*     */   
/*  47 */   public static int zoneId = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshRankList(List<DestinyRankData> destinyRankDataList) {
/*  53 */     destinyRankList.clear();
/*  54 */     destinyRankList.addAll(destinyRankDataList);
/*     */     try {
/*  56 */       lock.lock();
/*  57 */       rankMap.clear();
/*  58 */       for (int i = 0; i < destinyRankDataList.size(); i++) {
/*  59 */         ((DestinyRankData)destinyRankDataList.get(i)).rank = i + 1;
/*  60 */         rankMap.put(Long.valueOf(((DestinyRankData)destinyRankDataList.get(i)).playerId), Integer.valueOf(i + 1));
/*     */       } 
/*     */     } finally {
/*  63 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ArrayList<DestinyRankData> getDestinyRankList(int size) {
/*  68 */     if (size >= destinyRankList.size()) {
/*  69 */       return new ArrayList<>(destinyRankList);
/*     */     }
/*  71 */     ArrayList<DestinyRankData> resultList = new ArrayList<>();
/*  72 */     for (int i = 0; i < size; i++) {
/*  73 */       resultList.add(destinyRankList.get(i));
/*     */     }
/*  75 */     return resultList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getPlayerRank(long playerId) {
/*     */     try {
/*  85 */       lock.lock();
/*  86 */       return ((Integer)rankMap.getOrDefault(Long.valueOf(playerId), Integer.valueOf(0))).intValue();
/*     */     } finally {
/*  88 */       lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DestinyRankData getTargetPlayerRankData(long playerId) {
/*  99 */     return CrossUtil.getDestinyRankData("DestinyUtil::getTargetPlayerRankData", playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void uploadDestinyData(IPlayer player) {
/* 108 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 109 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 110 */     if (playerComponent.getLevel() >= destinyParameter.getLevelLimit()) {
/* 111 */       DestinyComponent destinyComponent = (DestinyComponent)player.createIfNotExist(DestinyComponent.class);
/* 112 */       if (destinyComponent.getRobTimes() > 0) {
/* 113 */         PlayerUtil.sendRedNotice(Long.valueOf(player.getPlayerId()), RedNoticeConstant.DestinyTimes.getSys(), RedNoticeConstant.DestinyTimes.getIndex());
/*     */       }
/* 115 */       DestinyPlayerData destinyPlayerData = CrossUtil.buildLocalDestinyPlayerData(playerComponent.getPlayerId());
/* 116 */       Fibers.createExecutorService().execute(() -> CrossUtil.updateDestinyPlayerData("DestinyUtil::uploadDestinyData ", destinyPlayerData));
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
/*     */   public static boolean isRefreshTimeOutofLimit(int refreshTimes) {
/* 140 */     if (refreshTimes <= 0)
/* 141 */       return false; 
/* 142 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 143 */     return !destinyParameter.getRefreshPriceMap().containsKey(Integer.valueOf(refreshTimes));
/*     */   }
/*     */   
/*     */   public static BattleRecordData tranform(int type, int time, int robNum, boolean win, long playerId, String playerName) {
/* 147 */     BattleRecordData battlePlayerData = new BattleRecordData();
/* 148 */     battlePlayerData.playerId = playerId;
/* 149 */     battlePlayerData.playerName = playerName;
/* 150 */     battlePlayerData.type = type;
/* 151 */     battlePlayerData.time = time;
/* 152 */     battlePlayerData.robNum = robNum;
/* 153 */     battlePlayerData.win = win;
/* 154 */     return battlePlayerData;
/*     */   }
/*     */   
/*     */   public static BattlePlayerData tranform(DestinyPlayerData destinyPlayerData) {
/* 158 */     BattlePlayerData battlePlayerData = new BattlePlayerData();
/* 159 */     battlePlayerData.playerId = destinyPlayerData.getPlayerId();
/* 160 */     battlePlayerData.playerName = destinyPlayerData.getPlayerName();
/* 161 */     battlePlayerData.model = destinyPlayerData.getModelData();
/* 162 */     battlePlayerData.fightValue = destinyPlayerData.getFightValue();
/* 163 */     battlePlayerData.destinyPoint = destinyPlayerData.getDestinyPoint();
/* 164 */     battlePlayerData.destinyStone = destinyPlayerData.getDestinyStone();
/* 165 */     battlePlayerData.quality = destinyPlayerData.getQuality();
/* 166 */     battlePlayerData.head = destinyPlayerData.getHead();
/* 167 */     return battlePlayerData;
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
/*     */ 
/*     */   
/*     */   public static DestinyPlayerData getLocalDestinyPlayerData(long playerId) {
/* 198 */     return CrossUtil.buildLocalDestinyPlayerData(playerId);
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
/*     */   public static void addLocalDestinyDef(long playerId, int type, int time, int robNum, boolean win, long pkPlayerId, String pkPlayerName, Reward reward) {
/* 214 */     DestinyComponent toDestinyComponent = (DestinyComponent)LookUpService.getComponent(playerId, DestinyComponent.class);
/* 215 */     if (robNum > 0 && reward != null) {
/*     */       
/* 217 */       BagComponent bagComponent = (BagComponent)LookUpService.createIfNotExist(playerId, BagComponent.class);
/* 218 */       ActivityBagComponent activityBagComponent = (ActivityBagComponent)LookUpService.getComponent(playerId, ActivityBagComponent.class);
/* 219 */       PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 220 */       if (null == playerComponent)
/*     */         return; 
/* 222 */       IPlayer iPlayer = LookUpService.getByPlayerId(playerId);
/* 223 */       int now = TimeUtil.currentTime();
/* 224 */       if (reward.type == 1) {
/* 225 */         CurrencyType currencyType = CurrencyType.values()[reward.id];
/* 226 */         long val = FinanceUtil.getPlayerCurrency(playerComponent, currencyType);
/* 227 */         if (reward.num > val)
/* 228 */           reward.num = (int)val; 
/* 229 */       } else if (reward.type == 2) {
/* 230 */         int val = (int)bagComponent.getItemNum(reward.id);
/* 231 */         if (reward.num > val)
/* 232 */           reward.num = val; 
/* 233 */       } else if (reward.type == 9) {
/* 234 */         int val = (int)activityBagComponent.getItemNum(now, reward.id);
/* 235 */         if (reward.num > val)
/* 236 */           reward.num = val; 
/*     */       } 
/* 238 */       robNum = (int)reward.num;
/* 239 */       CostUtil.costOffline(reward, iPlayer, playerComponent, bagComponent, activityBagComponent, ResourceEvent.DestinyBattleFight, playerId);
/*     */     } 
/*     */     
/* 242 */     if (null != toDestinyComponent) {
/* 243 */       BattleRecordData atkRecord = tranform(type, time, robNum, win, pkPlayerId, pkPlayerName);
/*     */       
/* 245 */       DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 246 */       toDestinyComponent.addDefBattleRecordData(atkRecord, destinyParameter.getRecordSize());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 251 */       toDestinyComponent.saveAllToDB();
/* 252 */       CrossUtil.updateDestinyPlayerData("DestinyUtil::addLocalDestinyDef", CrossUtil.buildLocalDestinyPlayerData(playerId));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static DestinyGroupFightData transformPkDataItem(PkDataItem pkDataItem) {
/* 257 */     DestinyGroupFightData data = new DestinyGroupFightData();
/* 258 */     if (pkDataItem != null) {
/* 259 */       data.leftPlayerId = (pkDataItem.getLeftPlayer() == null) ? 0L : pkDataItem.getLeftPlayer().getPlayerId();
/* 260 */       data.rightPlayerId = (pkDataItem.getRightPlayer() == null) ? 0L : pkDataItem.getRightPlayer().getPlayerId();
/* 261 */       data.winner = pkDataItem.getWinner();
/* 262 */       data.recordId = pkDataItem.getId();
/*     */     } 
/* 264 */     return data;
/*     */   }
/*     */   
/*     */   public static ArrayList<DestinyPlayerData> transformPkDataItem2(PkDataItem pkDataItem, boolean current) {
/* 268 */     ArrayList<DestinyPlayerData> resultList = new ArrayList<>();
/* 269 */     if (pkDataItem != null) {
/* 270 */       if (pkDataItem.getLeftPlayer() != null) {
/* 271 */         resultList.add(transformPkPlayerData(pkDataItem.getLeftPlayer(), pkDataItem.getWinner(), current));
/*     */       }
/* 273 */       if (pkDataItem.getRightPlayer() != null) {
/* 274 */         resultList.add(transformPkPlayerData(pkDataItem.getRightPlayer(), pkDataItem.getWinner(), current));
/*     */       }
/*     */     } 
/* 277 */     return resultList;
/*     */   }
/*     */   
/*     */   public static DestinyPlayerData transformPkPlayerData(PkPlayerData pkPlayerData, long winner, boolean current) {
/* 281 */     DestinyPlayerData data = new DestinyPlayerData();
/* 282 */     data.playerId = pkPlayerData.getPlayerId();
/* 283 */     data.playerName = pkPlayerData.getPlayerName();
/* 284 */     data.destinyPoint = pkPlayerData.getDestinyPoint();
/* 285 */     data.fightValue = pkPlayerData.getFightValue();
/* 286 */     data.quality = pkPlayerData.getQuality();
/* 287 */     data.modelData = pkPlayerData.getModelData();
/* 288 */     Map<Integer, Integer> fighterIdMap = new HashMap<>();
/* 289 */     if (!current) {
/* 290 */       for (Integer id : pkPlayerData.getDefaultFighterData().keySet()) {
/* 291 */         if (id.intValue() != 0) {
/* 292 */           fighterIdMap.put(id, Integer.valueOf((int)((CommonFighterData)pkPlayerData.getDefaultFighterData().get(id)).getId()));
/*     */         }
/*     */       } 
/*     */     } else {
/* 296 */       for (Integer id : pkPlayerData.getFighterData().keySet()) {
/* 297 */         if (id.intValue() != 0) {
/* 298 */           fighterIdMap.put(id, Integer.valueOf((int)((CommonFighterData)pkPlayerData.getFighterData().get(id)).getId()));
/*     */         }
/*     */       } 
/*     */     } 
/* 302 */     for (int i = 1; i < 7; i++) {
/* 303 */       data.fighterList.add(fighterIdMap.getOrDefault(Integer.valueOf(i), Integer.valueOf(0)));
/*     */     }
/* 305 */     data.win = (byte)((winner == 0L) ? -1 : ((winner == pkPlayerData.getPlayerId()) ? 1 : 0));
/* 306 */     return data;
/*     */   }
/*     */   
/*     */   public static boolean isGroupMatch(MatchState matchState) {
/* 310 */     return (MatchState.stepOnePrepare.getState() <= matchState.getState() && matchState.getState() <= MatchState.stepThreeBalance.getState());
/*     */   }
/*     */   
/*     */   public static boolean isFinalMatch(MatchState matchState) {
/* 314 */     return (MatchState.stepFourPrepare.getState() <= matchState.getState() && matchState.getState() <= MatchState.stepFiveBalance.getState());
/*     */   }
/*     */   
/*     */   public static boolean canBet(MatchState matchState) {
/* 318 */     return (matchState == MatchState.stepOnePrepare || matchState == MatchState.stepTwoPrepare || matchState == MatchState.stepThreePrepare || matchState == MatchState.stepFourPrepare || matchState == MatchState.stepFivePrepare);
/*     */   }
/*     */ 
/*     */   
/*     */   public static PkRecord getPkRecord(String pkId) {
/* 323 */     if (!pkRecordMap.containsKey(pkId)) {
/* 324 */       PkRecord pkRecord = CrossUtil.getPkRecord("DestinyUtil::getPkRecord", pkId);
/* 325 */       if (pkRecord != null) {
/* 326 */         pkRecordMap.putIfAbsent(pkId, pkRecord);
/*     */       }
/*     */     } 
/* 329 */     return pkRecordMap.get(pkId);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\destiny\DestinyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */