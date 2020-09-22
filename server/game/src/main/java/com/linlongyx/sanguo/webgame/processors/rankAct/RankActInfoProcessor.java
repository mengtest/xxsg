/*    */ package com.linlongyx.sanguo.webgame.processors.rankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rankAct.RankActEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankActInfoProcessor
/*    */   extends ProcessorBase<RankActInfoRequest, RankActInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new RankActInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankActInfoRequest request) {
/* 32 */     int rankId = request.rankId;
/* 33 */     RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(rankId, RankingActivityBean.class);
/* 34 */     if (null == rankingActivityBean) {
/* 35 */       return 12001;
/*    */     }
/* 37 */     if (null == RankActUtil.rankActMap.get(Integer.valueOf(rankId))) {
/* 38 */       return 10061;
/*    */     }
/*    */     
/* 41 */     RankActComponent rankActComponent = (RankActComponent)playerSession.getPlayer().createIfNotExist(RankActComponent.class);
/* 42 */     RankActEntity rankActEntity = rankActComponent.getEntity(rankId);
/*    */     
/* 44 */     ((RankActInfoResponse)this.response).value = rankActEntity.getValue();
/* 45 */     ((RankActInfoResponse)this.response).rankId = rankId;
/*    */     
/* 47 */     int day = TimeUtil.getDayDisTime(MContext.getOpenTimeInt());
/* 48 */     int time = TimeUtil.timingTime(0) + 86400;
/* 49 */     ((RankActInfoResponse)this.response).time = time + (rankingActivityBean.getEndTime() - day - 1) * 86400;
/*    */     
/* 51 */     Map<Integer, Integer> states = rankActEntity.getStates();
/* 52 */     for (Integer itemId : rankingActivityBean.getPersonalReward()) {
/* 53 */       IntIntValue intIntValue = new IntIntValue();
/* 54 */       intIntValue.key = itemId.intValue();
/* 55 */       intIntValue.value = ((Integer)states.getOrDefault(itemId, Integer.valueOf(0))).intValue();
/* 56 */       ((RankActInfoResponse)this.response).list.add(intIntValue);
/*    */     } 
/* 58 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\RankActInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */