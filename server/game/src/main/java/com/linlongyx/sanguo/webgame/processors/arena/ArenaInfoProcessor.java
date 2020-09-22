/*     */ package com.linlongyx.sanguo.webgame.processors.arena;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRuleBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.arena.ArenaInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArenaInfoProcessor
/*     */   extends ProcessorBase<ArenaInfoRequest, ArenaInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new ArenaInfoResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, ArenaInfoRequest request) {
/*  33 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 17)) {
/*  34 */       return 10061;
/*     */     }
/*  36 */     IPlayer iPlayer = playerSession.getPlayer();
/*  37 */     ArenaComponent arenaComponent = (ArenaComponent)iPlayer.createIfNotExist(ArenaComponent.class);
/*  38 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/*     */     
/*  40 */     int rank = arenaComponent.getRank();
/*  41 */     if (0 == rank) {
/*  42 */       rank = arenaParameter.getDefaultRank();
/*  43 */       arenaComponent.setRank(rank);
/*     */     } 
/*  45 */     ((ArenaInfoResponse)this.response).rank = rank;
/*  46 */     ((ArenaInfoResponse)this.response).maxRank = arenaComponent.getMaxRank();
/*  47 */     ((ArenaInfoResponse)this.response).fightTimes = arenaParameter.getMaxChallengeTime() + arenaComponent.getBuyTimes() - arenaComponent.getFightTimes();
/*  48 */     ((ArenaInfoResponse)this.response).buyTimes = arenaComponent.getBuyTimes();
/*  49 */     ((ArenaInfoResponse)this.response).eliminateTimes = arenaComponent.getEliminateTimes();
/*  50 */     ((ArenaInfoResponse)this.response).remainTime = arenaComponent.getLastTime();
/*  51 */     ArenaRuleBean arenaRuleBean = ArenaUtil.getArenaRuleBean(rank);
/*  52 */     int challengeNum = arenaParameter.getChallengeNum();
/*  53 */     Map<Integer, Integer> showMap = arenaComponent.getShowMap();
/*  54 */     showMap.clear();
/*  55 */     Set<Integer> ranks = new HashSet<>();
/*  56 */     if (null != arenaRuleBean) {
/*  57 */       int matchHigh = rank + arenaRuleBean.getMatchHigh();
/*  58 */       int matchLow = rank + arenaRuleBean.getMatchLow();
/*     */       
/*  60 */       int count = 0;
/*  61 */       while (count < arenaRuleBean.getLower()) {
/*  62 */         int tempRank = RandUtil.randNum(rank + 1, matchLow);
/*  63 */         if (tempRank > 3 && !ranks.contains(Integer.valueOf(tempRank))) {
/*  64 */           ranks.add(Integer.valueOf(tempRank));
/*  65 */           count++;
/*     */         } 
/*     */       } 
/*     */       
/*  69 */       ranks.add(Integer.valueOf(1));
/*  70 */       ranks.add(Integer.valueOf(2));
/*  71 */       ranks.add(Integer.valueOf(3));
/*  72 */       ranks.add(Integer.valueOf(rank));
/*     */       
/*  74 */       count = ranks.size();
/*  75 */       matchHigh = (matchHigh < 4) ? 4 : matchHigh;
/*     */       
/*  77 */       while (count < challengeNum) {
/*  78 */         int tempRank = RandUtil.randNum(matchHigh, rank);
/*  79 */         if (!ranks.contains(Integer.valueOf(tempRank))) {
/*  80 */           ranks.add(Integer.valueOf(tempRank));
/*  81 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/*  85 */     for (Integer r : ranks) {
/*     */       ArenaData arenaData;
/*  87 */       if (r.intValue() == rank) {
/*  88 */         arenaData = ArenaUtil.getArenaData(playerSession.getPlayer().getPlayerId());
/*     */       } else {
/*  90 */         arenaData = ArenaUtil.getArenaData(r.intValue(), arenaRuleBean);
/*     */       } 
/*  92 */       if (null == arenaData) {
/*     */         continue;
/*     */       }
/*  95 */       if (arenaData.isRobot) {
/*  96 */         showMap.put(Integer.valueOf(arenaData.rank), Integer.valueOf(arenaData.objectId));
/*     */       }
/*  98 */       ((ArenaInfoResponse)this.response).arenaDatas.add(arenaData);
/*     */     } 
/* 100 */     ((ArenaInfoResponse)this.response).arenaDatas.sort(new ArenaComparator());
/* 101 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\arena\ArenaInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */