/*    */ package com.linlongyx.sanguo.webgame.processors.crossRace;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.CrossRankRewardBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceRewardGetRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceRewardGetResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRaceRewardGetProcessor
/*    */   extends ProcessorBase<CrossRaceRewardGetRequest, CrossRaceRewardGetResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new CrossRaceRewardGetResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRaceRewardGetRequest request) {
/* 29 */     CrossRaceComponent crossRaceComponent = (CrossRaceComponent)playerSession.getPlayer().createIfNotExist(CrossRaceComponent.class);
/* 30 */     int curRaceId = CrossUtil.getCurRaceId("CrossRaceRewardGetProcessor");
/* 31 */     if (crossRaceComponent.getRaceId() != curRaceId) {
/* 32 */       LogUtil.errorLog(new Object[] { "error race id", Integer.valueOf(crossRaceComponent.getRaceId()), Integer.valueOf(curRaceId) });
/* 33 */       return 10091;
/*    */     } 
/* 35 */     if (!crossRaceComponent.getJoinReward().containsKey(Integer.valueOf(request.rewardId))) {
/* 36 */       return 10095;
/*    */     }
/* 38 */     if (((Integer)crossRaceComponent.getJoinReward().get(Integer.valueOf(request.rewardId))).intValue() == 2) {
/* 39 */       return 10091;
/*    */     }
/* 41 */     CrossRankRewardBean crossRankRewardBean = (CrossRankRewardBean)JsonTableService.getJsonData(request.rewardId, CrossRankRewardBean.class);
/* 42 */     FinanceUtil.reward(FinanceUtil.transform(crossRankRewardBean.getReward()), playerSession.getPlayer(), ResourceEvent.CrossRace, true);
/* 43 */     crossRaceComponent.getJoinReward().put(Integer.valueOf(request.rewardId), Integer.valueOf(2));
/* 44 */     ((CrossRaceRewardGetResponse)this.response).rewardId = request.rewardId;
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRace\CrossRaceRewardGetProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */