/*    */ package com.linlongyx.sanguo.webgame.processors.rankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingActivityBean;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.ArenaParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.rankAct.RankActListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankActListProcessor
/*    */   extends ProcessorBase<RankActListRequest, RankActListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new RankActListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, RankActListRequest request) {
/* 26 */     int rankId = request.rankId;
/* 27 */     ArenaParameter arenaParameter = (ArenaParameter)ParameterConstant.getParameter(17);
/* 28 */     RankingActivityBean rankingActivityBean = (RankingActivityBean)JsonTableService.getJsonData(rankId, RankingActivityBean.class);
/* 29 */     if (null == rankingActivityBean) {
/* 30 */       return 12001;
/*    */     }
/* 32 */     if (null == RankActUtil.rankActMap.get(Integer.valueOf(rankId))) {
/* 33 */       return 10061;
/*    */     }
/* 35 */     ((RankActListResponse)this.response).rankId = rankId;
/* 36 */     ((RankActListResponse)this.response).list = RankActUtil.getRankDataList(rankId);
/*    */     
/* 38 */     for (RankingData rankingData : ((RankActListResponse)this.response).list) {
/* 39 */       if (rankingData.playerId == playerSession.getPlayer().getPlayerId()) {
/* 40 */         ((RankActListResponse)this.response).myRank = rankingData.rank;
/*    */         break;
/*    */       } 
/*    */     } 
/* 44 */     if (RankActUtil.isOldServer()) {
/* 45 */       ((RankActListResponse)this.response).myRank = (((RankActListResponse)this.response).myRank == 0) ? arenaParameter.getDefaultRank() : ((RankActListResponse)this.response).myRank;
/*    */     }
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\RankActListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */