/*     */ package com.linlongyx.sanguo.webgame.rmi;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.destiny.DestinyComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DestinyRankBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.DestinyTopBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RuneWarRankBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TowerOwnerBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.DestinyParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.LanguageConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TabNotice;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRace.CrossRaceUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.crossRankAct.CrossRankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.login.LoginUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.mail.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.runewar.RunewarUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.tower.TowerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.TabNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BattleRecordData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.DestinyRankData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MailInfo;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WxPlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.BetData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.DestinyPlayerData;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.MatchState;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PkDataItem;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*     */ import java.rmi.RemoteException;
/*     */ import java.rmi.server.UnicastRemoteObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class LogicRmiServer
/*     */   extends UnicastRemoteObject
/*     */   implements ILogicRmi
/*     */ {
/*     */   public void shutDown() throws RemoteException {}
/*     */   
/*     */   public void rewardGet(long playerId, ArrayList<Reward> rewards, ResourceEvent resourceEvent, boolean sendFlag) throws RemoteException {
/*  90 */     IPlayer iPlayer = LookUpService.getByPlayerId(playerId);
/*  91 */     FinanceUtil.rewardGet2(rewards, iPlayer, playerId, resourceEvent, sendFlag);
/*  92 */     LogUtil.errorLog(new Object[] { "LogicRmiServer::rewardGet", Long.valueOf(playerId), GsonUtil.toJson(rewards), Integer.valueOf(resourceEvent.getType()), resourceEvent.getDetail() });
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
/*     */   public DestinyPlayerData getLocalDestinyPlayerData(long playerId) throws RemoteException {
/* 113 */     return DestinyUtil.getLocalDestinyPlayerData(playerId);
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
/*     */   public void addLocalDestinyDef(long playerId, int type, int time, int robNum, boolean win, long pkPlayerId, String pkPlayerName, Reward reward) throws RemoteException {
/* 131 */     DestinyUtil.addLocalDestinyDef(playerId, type, time, robNum, win, pkPlayerId, pkPlayerName, reward);
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshDestinyRanking(ArrayList<DestinyRankData> list) throws RemoteException {
/* 136 */     LogUtil.errorLog(new Object[] { "destiny rank refresh" });
/* 137 */     DestinyUtil.refreshRankList(list);
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
/*     */   public void destinyDailyReward(long playerId, int rank) throws RemoteException {}
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
/*     */   public void balanceDestinyRank(List<DestinyRankData> list) throws RemoteException {
/* 177 */     if (!list.isEmpty()) {
/* 178 */       String title = LanguageConstant.getLanguage(7401);
/* 179 */       Set<Integer> keySet = JsonTableService.getJsonTableKey(DestinyRankBean.class);
/* 180 */       for (DestinyRankData destinyRankData : list) {
/* 181 */         LogUtil.errorLog(new Object[] { "logic destiny rank balance -> ", destinyRankData.toString() });
/*     */         
/* 183 */         String content = LanguageConstant.getAndReplaceLanguage(7402, new String[] { String.valueOf(destinyRankData.rank) });
/* 184 */         DestinyRankBean bean = null;
/* 185 */         for (Iterator<Integer> iterator = keySet.iterator(); iterator.hasNext(); ) { int key = ((Integer)iterator.next()).intValue();
/* 186 */           bean = (DestinyRankBean)JsonTableService.getJsonData(key, DestinyRankBean.class);
/* 187 */           if (destinyRankData.rank <= bean.getRank()) {
/*     */             break;
/*     */           } }
/*     */         
/* 191 */         if (bean != null) {
/* 192 */           MailUtil.sendSysMail(destinyRankData.playerId, FinanceUtil.transform(bean.getReward()), title, content);
/*     */         }
/*     */       } 
/*     */     } 
/* 196 */     DestinyUtil.refreshRankList(new ArrayList());
/*     */ 
/*     */     
/* 199 */     LookUpService.getOnlinePlayer().forEach(playerId -> {
/*     */           DestinyComponent destinyComponent = (DestinyComponent)LookUpService.getComponent(playerId.longValue(), DestinyComponent.class);
/*     */           
/*     */           if (destinyComponent != null && !destinyComponent.getRecommentsCacheData().isEmpty()) {
/*     */             destinyComponent.getRecommentsCacheData().clear();
/*     */           }
/*     */         });
/* 206 */     DestinyParameter destinyParameter = (DestinyParameter)ParameterConstant.getParameter(74);
/* 207 */     Map<Integer, String> rules = new HashMap<>();
/* 208 */     rules.put(Integer.valueOf(1), String.valueOf(destinyParameter.getLevelLimit()));
/* 209 */     rules.put(Integer.valueOf(2), String.valueOf(500));
/* 210 */     MailInfo mailInfo = new MailInfo();
/* 211 */     mailInfo.type = 1;
/* 212 */     mailInfo.sendId = 6L;
/* 213 */     mailInfo.sendName = "系统";
/* 214 */     mailInfo.title = LanguageConstant.getLanguage(7407);
/* 215 */     mailInfo.context = LanguageConstant.getLanguage(7408);
/* 216 */     mailInfo.rewards = destinyParameter.getDestinyPrepareRewards();
/* 217 */     MailUtil.sendPubMail(mailInfo, rules);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getServerWorldLevel() throws RemoteException {
/* 222 */     return RankingLevel.getWorldLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setZoneId(int zoneId) throws RemoteException {
/* 227 */     DestinyUtil.zoneId = zoneId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateGroupPkData(int type, HashMap<Integer, PkDataItem[]> pkDataItemMap, MatchState matchState) throws RemoteException {
/* 238 */     DestinyUtil.groupPkDataMap.putAll(pkDataItemMap);
/* 239 */     DestinyUtil.curMatchState = matchState;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendDestinyBetReward(ArrayList<BetData> betDataList) throws RemoteException {
/* 244 */     if (!betDataList.isEmpty()) {
/* 245 */       for (BetData betData : betDataList) {
/* 246 */         LogUtil.errorLog(new Object[] { "logic destiny match bet reward ->", LookUpService.isOnline(betData.getPlayerId()) ? "online" : "offline", betData.toString() });
/* 247 */         PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(betData.getPlayerId(), PlayerComponent.class);
/* 248 */         if (playerComponent != null) {
/* 249 */           if (LookUpService.isOnline(playerComponent.getPlayerId())) {
/* 250 */             FinanceUtil.addCurrency(LookUpService.getByPlayerId(playerComponent.getPlayerId()), CurrencyType.GuessCoin, betData.getBetNum(), ResourceEvent.DestinyMatchGuess); continue;
/*     */           } 
/* 252 */           FinanceUtil.addCurrencyOffline(playerComponent, CurrencyType.GuessCoin, betData.getBetNum(), ResourceEvent.DestinyMatchGuess, true, 0);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendDestinyMatchReward(long playerId, int type) throws RemoteException {
/* 261 */     String rankStr = CrossUtil.getRankStr(type);
/* 262 */     String title = LanguageConstant.getLanguage(7405);
/* 263 */     String content = LanguageConstant.getAndReplaceLanguage(7406, new String[] { rankStr });
/* 264 */     DestinyTopBean destinyTopBean = (DestinyTopBean)JsonTableService.getJsonData(type, DestinyTopBean.class);
/* 265 */     MailUtil.sendSysMail(playerId, FinanceUtil.transform(destinyTopBean.getReward()), title, content);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendDestinyMatchRewardMap(HashMap<Long, Integer> matchReward) throws RemoteException {
/* 270 */     String title = LanguageConstant.getLanguage(7405);
/* 271 */     for (Map.Entry<Long, Integer> kv : matchReward.entrySet()) {
/* 272 */       String rankStr = CrossUtil.getRankStr(1 << ((Integer)kv.getValue()).intValue() - 1);
/* 273 */       String content = LanguageConstant.getAndReplaceLanguage(7406, new String[] { rankStr });
/* 274 */       DestinyTopBean destinyTopBean = (DestinyTopBean)JsonTableService.getJsonData(((Integer)kv.getValue()).intValue(), DestinyTopBean.class);
/* 275 */       MailUtil.sendSysMail(((Long)kv.getKey()).longValue(), FinanceUtil.transform(destinyTopBean.getReward()), title, content);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendWxFriendInfo(WxPlayerInfo wxPlayerInfo, long playerId) throws RemoteException {
/* 285 */     LoginUtil.updateFromCross(wxPlayerInfo, playerId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlayerData getPlayerData(long playerId) throws RemoteException {
/* 292 */     return CrossUtil.buildLocalPlayerData(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendCrossReward(long playerId, int type, ArrayList<Reward> rewardList) throws RemoteException {
/* 297 */     IPlayer player = LookUpService.getByPlayerId(playerId);
/* 298 */     if (player != null) {
/* 299 */       FinanceUtil.reward(rewardList, player, ResourceEvent.CrossBattleCollect, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendCrossBattleNotice(int type) throws RemoteException {
/* 305 */     if (type == 1) {
/* 306 */       LookUpService.radiate(8102, new ArrayList());
/* 307 */     } else if (type == 2) {
/* 308 */       LookUpService.radiate(8103, new ArrayList());
/* 309 */       TabNoticeResponse resp = new TabNoticeResponse();
/* 310 */       resp.sys = TabNotice.crossBattle.getSys();
/* 311 */       resp.index = TabNotice.crossBattle.getIndex();
/* 312 */       LookUpService.broadcast((ResponseBase)resp);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void pushRankList(int actId, ArrayList<RankingData> rankList) throws RemoteException {
/* 318 */     CrossRankActUtil.refreshRankMap(actId, rankList);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendCrossPointRankReward(long playerId, int rank, ArrayList<Reward> rewardList) throws RemoteException {
/* 323 */     String title = LanguageConstant.getLanguage(8106);
/* 324 */     String content = LanguageConstant.getAndReplaceLanguage(8107, new String[] { rank + "" });
/* 325 */     MailUtil.sendSysMail(playerId, rewardList, title, content);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendCrossCampRankReward(int camp, int rank, ArrayList<Long> playerIdList, ArrayList<Reward> rewardList) throws RemoteException {
/*     */     String campName;
/* 331 */     switch (camp + 1) {
/*     */       case 1:
/* 333 */         campName = "魏国";
/*     */         break;
/*     */       case 2:
/* 336 */         campName = "蜀国";
/*     */         break;
/*     */       case 3:
/* 339 */         campName = "吴国";
/*     */         break;
/*     */       default:
/* 342 */         campName = "无所属"; break;
/*     */     } 
/* 344 */     String title = LanguageConstant.getLanguage(8108);
/* 345 */     String content = LanguageConstant.getAndReplaceLanguage(8109, new String[] { campName, rank + "" });
/* 346 */     for (Iterator<Long> iterator = playerIdList.iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 347 */       MailUtil.sendSysMail(playerId, rewardList, title, content); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sendCrossRaceReward(long playerId, int racePoint, int rank) throws RemoteException {
/* 353 */     return CrossRaceUtil.sendCrossRaceReward(playerId, racePoint, rank);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendCrossRaceNotice(int type) throws RemoteException {
/* 358 */     CrossRaceUtil.sendCrossRaceNotice(type);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTowerDefRecord(int layerId, long playerId, int type, int time, boolean win, long pkPlayerId, String pkPlayerName) throws RemoteException {
/* 363 */     TowerUtil.addTowerDefRecord(layerId, playerId, type, time, win, pkPlayerId, pkPlayerName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendTowerRankReward(HashMap<Integer, Long> rankMap) throws RemoteException {
/* 368 */     String title = LanguageConstant.getLanguage(6701);
/* 369 */     for (Map.Entry<Integer, Long> kv : rankMap.entrySet()) {
/* 370 */       TowerOwnerBean towerOwnerBean = (TowerOwnerBean)JsonTableService.getJsonData(((Integer)kv.getKey()).intValue(), TowerOwnerBean.class);
/* 371 */       String content = LanguageConstant.getAndReplaceLanguage(6702, new String[] { towerOwnerBean.getName() });
/* 372 */       MailUtil.sendSysMail(((Long)kv.getValue()).longValue(), FinanceUtil.transform(towerOwnerBean.getDefenseReward()), title, content);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRunewarDefRecord(long playerId, BattleRecordData record) throws RemoteException {
/* 378 */     RunewarUtil.addRunewarDefRecord(playerId, record);
/*     */   }
/*     */ 
/*     */   
/*     */   public void runewarRankBalance(HashMap<Long, Integer> playerRankMap) throws RemoteException {
/* 383 */     String title = LanguageConstant.getLanguage(4501);
/* 384 */     for (Map.Entry<Long, Integer> kv : playerRankMap.entrySet()) {
/* 385 */       LogUtil.errorLog(new Object[] { "runewar rank balance, playerId:", kv.getKey(), "rank:", kv.getValue() });
/* 386 */       RuneWarRankBean targetBean = null;
/* 387 */       for (Iterator<Integer> iterator = JsonTableService.getJsonTableKey(RuneWarRankBean.class).iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 388 */         targetBean = (RuneWarRankBean)JsonTableService.getJsonData(id, RuneWarRankBean.class);
/* 389 */         if (((Integer)kv.getValue()).intValue() <= targetBean.getRank())
/*     */           break;  }
/* 391 */        if (targetBean != null) {
/* 392 */         String content = LanguageConstant.getAndReplaceLanguage(4502, new String[] { (new StringBuilder()).append(kv.getValue()).append("").toString() });
/* 393 */         MailUtil.sendSysMail(((Long)kv.getKey()).longValue(), FinanceUtil.transform(targetBean.getReward()), title, content);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\LogicRmiServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */