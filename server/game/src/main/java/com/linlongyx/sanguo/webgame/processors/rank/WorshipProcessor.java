/*     */ package com.linlongyx.sanguo.webgame.processors.rank;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ArenaRankingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RankingBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rank.WorshipRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.rank.WorshipResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.DBIncrementService;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.AbstractRank;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WorshipProcessor extends ProcessorBase<WorshipRequest, WorshipResponse> {
/*     */   protected void initResponse() {
/*  34 */     this.response = (ResponseBase)new WorshipResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, WorshipRequest request) {
/*  39 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 35)) {
/*  40 */       return 10061;
/*     */     }
/*  42 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/*  43 */     AbstractRank abstractRank = rankBaseService.getAbstractRank(request.type);
/*  44 */     long first = abstractRank.getFirst();
/*     */     
/*  46 */     if (0L == first && request.type != RankingType.ARENA.getType()) {
/*  47 */       return 17001;
/*     */     }
/*  49 */     PlayerComponent firstComponent = LookUpService.getPlayerComponent(first);
/*  50 */     if (null == firstComponent && request.type != RankingType.ARENA.getType()) {
/*  51 */       return 17002;
/*     */     }
/*     */     
/*  54 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*     */ 
/*     */ 
/*     */     
/*  58 */     if (playerComponent.checkIsWorshop(request.type)) {
/*  59 */       return 17003;
/*     */     }
/*  61 */     if (null != firstComponent) {
/*  62 */       firstComponent.addWorship(request.type);
/*  63 */       firstComponent.saveToDB();
/*     */     } 
/*  65 */     playerComponent.isWorshop(request.type);
/*     */     
/*  67 */     int myRank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/*  68 */     Map<Long, Long> mapRewards = new HashMap<>();
/*  69 */     RankingBean rankingBean = (RankingBean)JsonTableService.getJsonData(request.type, RankingBean.class);
/*     */     
/*  71 */     if (rankingBean.getWorshipReward() != null && rankingBean.getWorshipReward().size() > 0)
/*     */     {
/*  73 */       FinanceUtil.overlyingMap(mapRewards, FinanceUtil.transform(rankingBean.getWorshipReward()));
/*     */     }
/*  75 */     if (rankingBean.getLevelReward() != null && rankingBean.getLevelReward().size() > 0) {
/*     */       
/*  77 */       int worldLevel = RankingLevel.getWorldLevel();
/*  78 */       FinanceUtil.overlyingMap(mapRewards, FinanceUtil.transform2(rankingBean.getLevelReward(), worldLevel));
/*     */     } 
/*  80 */     ArrayList<Reward> rewards = FinanceUtil.overlyingReward(mapRewards);
/*  81 */     if (!rewards.isEmpty()) {
/*  82 */       FinanceUtil.reward(rewards, playerSession.getPlayer(), ResourceEvent.WorshipReward, true);
/*     */     }
/*  84 */     if (0L == first) {
/*  85 */       ArenaRankingBean arenaRankingBean = (ArenaRankingBean)JsonTableService.getJsonData(1, ArenaRankingBean.class);
/*  86 */       if (null != arenaRankingBean) {
/*  87 */         first = arenaRankingBean.getId();
/*     */       }
/*     */     } 
/*  90 */     if (0L == first) {
/*  91 */       Map<Integer, Object> values = JsonTableService.getJsonTable(ArenaRankingBean.class);
/*  92 */       Iterator iterator = values.values().iterator(); if (iterator.hasNext()) { Object obj = iterator.next();
/*  93 */         ArenaRankingBean arenaRankingBean = (ArenaRankingBean)obj;
/*  94 */         first = arenaRankingBean.getId(); }
/*     */     
/*     */     } 
/*     */ 
/*     */     
/*  99 */     ((WorshipResponse)this.response).playerId = first;
/* 100 */     ((WorshipResponse)this.response).type = request.type;
/* 101 */     if (null != firstComponent) {
/* 102 */       ((WorshipResponse)this.response).worships = firstComponent.getWorship(request.type);
/*     */     }
/* 104 */     else if (request.type == RankingType.ARENA.getType()) {
/* 105 */       DBIncrementService service = (DBIncrementService)AppContext.getBean("dbIncrementService");
/* 106 */       long worships = service.generate("arena_worships", 0L).longValue();
/* 107 */       if (worships >= 2147483646L) {
/* 108 */         service.reset("arena_worships", 0L);
/* 109 */         worships = 0L;
/*     */       } 
/* 111 */       ((WorshipResponse)this.response).worships = (int)worships;
/*     */     } 
/*     */     
/* 114 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 115 */     if (null != taskComponent) {
/* 116 */       taskComponent.refreshSchedule(TaskType.RankWorship, 0, 1L);
/*     */     }
/* 118 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rank\WorshipProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */