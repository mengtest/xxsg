/*    */ package com.linlongyx.sanguo.webgame.processors.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafulistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRankActRewardProcessor
/*    */   extends ProcessorBase<CrossRankActRewardRequest, CrossRankActRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new CrossRankActRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRankActRewardRequest request) {
/* 32 */     int rankId = request.rankId;
/* 33 */     int itemId = request.itemId;
/* 34 */     RankingKuafuBean rankingKuafuBean = (RankingKuafuBean)JsonTableService.getJsonData(rankId, RankingKuafuBean.class);
/* 35 */     if (null == rankingKuafuBean) {
/* 36 */       return 12001;
/*    */     }
/* 38 */     if (!CrossRankActUtil.isActOpen(rankingKuafuBean)) {
/* 39 */       return 12002;
/*    */     }
/* 41 */     if (!rankingKuafuBean.getPersonalReward().contains(Integer.valueOf(itemId))) {
/* 42 */       return 12003;
/*    */     }
/* 44 */     RankingKuafulistBean rankingKuafulistBean = (RankingKuafulistBean)JsonTableService.getJsonData(itemId, RankingKuafulistBean.class);
/* 45 */     if (null == rankingKuafulistBean) {
/* 46 */       return 12001;
/*    */     }
/* 48 */     CrossRankActComponent crossRankActComponent = (CrossRankActComponent)playerSession.getPlayer().createIfNotExist(CrossRankActComponent.class);
/* 49 */     CrossRankActEntity crossRankActEntity = crossRankActComponent.getEntity(rankId);
/* 50 */     if (crossRankActEntity == null) {
/* 51 */       return 12002;
/*    */     }
/* 53 */     Map<Integer, Integer> states = crossRankActEntity.getStates();
/*    */     
/* 55 */     int state = ((Integer)states.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/* 56 */     if (2 == state) {
/* 57 */       return 12004;
/*    */     }
/* 59 */     if (0 == state) {
/* 60 */       return 12005;
/*    */     }
/* 62 */     states.put(Integer.valueOf(itemId), Integer.valueOf(2));
/* 63 */     crossRankActEntity.setStates(states);
/* 64 */     crossRankActComponent.updateStatesDB(rankId);
/*    */     
/* 66 */     FinanceUtil.reward(FinanceUtil.transform(rankingKuafulistBean.getReward()), playerSession.getPlayer(), ResourceEvent.CrossRankAct, true);
/*    */     
/* 68 */     ((CrossRankActRewardResponse)this.response).data.key = itemId;
/* 69 */     ((CrossRankActRewardResponse)this.response).data.value = 2;
/*    */     
/* 71 */     ((CrossRankActRewardResponse)this.response).rankId = rankId;
/* 72 */     LogUtils.errorLog(new Object[] { "CrossRankActReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((CrossRankActRewardResponse)this.response).toString() });
/* 73 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRankAct\CrossRankActRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */