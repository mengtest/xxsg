/*    */ package com.linlongyx.sanguo.webgame.processors.rankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivitylistBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActRewardRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActRewardResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankActRewardProcessor
/*    */   extends ProcessorBase<RankActRewardRequest, RankActRewardResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new RankActRewardResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankActRewardRequest request) {
/* 32 */     int rankId = request.rankId;
/* 33 */     int itemId = request.itemId;
/* 34 */     RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(rankId, RankingActivityBean.class);
/* 35 */     if (null == rankingActivityBean) {
/* 36 */       return 12001;
/*    */     }
/* 38 */     if (null == RankActUtil.rankActMap.get(Integer.valueOf(rankId))) {
/* 39 */       return 10061;
/*    */     }
/* 41 */     if (!RankActUtil.isActOpen(rankId)) {
/* 42 */       return 12002;
/*    */     }
/* 44 */     if (!rankingActivityBean.getPersonalReward().contains(Integer.valueOf(itemId))) {
/* 45 */       return 12003;
/*    */     }
/* 47 */     RankingActivitylistBean rankingActivitylistBean = (RankingActivitylistBean)JsonTableService.getJsonData(itemId, RankingActivitylistBean.class);
/* 48 */     if (null == rankingActivitylistBean) {
/* 49 */       return 12001;
/*    */     }
/* 51 */     RankActComponent rankActComponent = (RankActComponent)playerSession.getPlayer().createIfNotExist(RankActComponent.class);
/* 52 */     RankActEntity rankActEntity = rankActComponent.getEntity(rankId);
/* 53 */     Map<Integer, Integer> states = rankActEntity.getStates();
/* 54 */     int state = ((Integer)states.getOrDefault(Integer.valueOf(itemId), Integer.valueOf(0))).intValue();
/* 55 */     if (2 == state) {
/* 56 */       return 12004;
/*    */     }
/* 58 */     if (0 == state) {
/* 59 */       return 12005;
/*    */     }
/* 61 */     states.put(Integer.valueOf(itemId), Integer.valueOf(2));
/* 62 */     rankActEntity.setStates(states);
/* 63 */     rankActComponent.updateStatesDB(rankId);
/*    */     
/* 65 */     FinanceUtil.reward(FinanceUtil.transform(rankingActivitylistBean.getReward()), playerSession.getPlayer(), ResourceEvent.RankActReward, true);
/*    */     
/* 67 */     ((RankActRewardResponse)this.response).data.key = itemId;
/* 68 */     ((RankActRewardResponse)this.response).data.value = 2;
/*    */     
/* 70 */     ((RankActRewardResponse)this.response).rankId = rankId;
/* 71 */     LogUtils.errorLog(new Object[] { "RankActReward", Long.valueOf(playerSession.getPlayer().getPlayerId()), ((RankActRewardResponse)this.response).toString() });
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\RankActRewardProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */