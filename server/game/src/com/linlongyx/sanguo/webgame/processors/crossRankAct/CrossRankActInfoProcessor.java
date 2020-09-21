/*    */ package com.linlongyx.sanguo.webgame.processors.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRankAct.CrossRankActEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRankActInfoProcessor
/*    */   extends ProcessorBase<CrossRankActInfoRequest, CrossRankActInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new CrossRankActInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRankActInfoRequest request) {
/* 32 */     int rankId = request.rankId;
/* 33 */     RankingKuafuBean rankingKuafuBean = (RankingKuafuBean)JsonTableService.getJsonData(rankId, RankingKuafuBean.class);
/* 34 */     if (null == rankingKuafuBean) {
/* 35 */       return 12001;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 40 */     CrossRankActComponent crossRankActComponent = (CrossRankActComponent)playerSession.getPlayer().createIfNotExist(CrossRankActComponent.class);
/* 41 */     CrossRankActEntity crossRankActEntity = crossRankActComponent.getEntity(rankId);
/* 42 */     if (crossRankActEntity == null) {
/* 43 */       ((CrossRankActInfoResponse)this.response).rankId = rankId;
/* 44 */       return 0;
/*    */     } 
/* 46 */     ((CrossRankActInfoResponse)this.response).value = crossRankActEntity.getValue();
/* 47 */     ((CrossRankActInfoResponse)this.response).rankId = rankId;
/* 48 */     ((CrossRankActInfoResponse)this.response).time = TimeUtil.getTimeFromDate(rankingKuafuBean.getEndTime());
/*    */ 
/*    */     
/* 51 */     Map<Integer, Integer> states = crossRankActEntity.getStates();
/* 52 */     for (Integer itemId : rankingKuafuBean.getPersonalReward()) {
/* 53 */       IntIntValue intIntValue = new IntIntValue();
/* 54 */       intIntValue.key = itemId.intValue();
/* 55 */       intIntValue.value = ((Integer)states.getOrDefault(itemId, Integer.valueOf(0))).intValue();
/* 56 */       ((CrossRankActInfoResponse)this.response).list.add(intIntValue);
/*    */     } 
/* 58 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRankAct\CrossRankActInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */