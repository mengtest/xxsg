/*    */ package com.linlongyx.sanguo.webgame.processors.crossRace;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.crossRace.CrossRaceComponent;
/*    */ import com.linlongyx.sanguo.webgame.config.parameter.CrossRaceParameter;
/*    */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRace.CrossRaceRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRaceRankProcessor
/*    */   extends ProcessorBase<CrossRaceRankRequest, CrossRaceRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 28 */     this.response = (ResponseBase)new CrossRaceRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRaceRankRequest request) {
/* 33 */     CrossRaceComponent crossRaceComponent = (CrossRaceComponent)playerSession.getPlayer().createIfNotExist(CrossRaceComponent.class);
/* 34 */     int curRaceId = CrossUtil.getCurRaceId("CrossRaceInfoProcessor");
/* 35 */     if (curRaceId <= 0) {
/* 36 */       return 12801;
/*    */     }
/* 38 */     if (crossRaceComponent.getRaceId() != curRaceId) {
/* 39 */       crossRaceComponent.resetRaceData(curRaceId);
/*    */     }
/* 41 */     CrossRaceParameter crossRaceParameter = (CrossRaceParameter)ParameterConstant.getParameter(28);
/* 42 */     ((CrossRaceRankResponse)this.response).ranklist = CrossUtil.getRaceRankList("CrossRaceRankProcessor", crossRaceParameter.getLimit());
/* 43 */     if (crossRaceComponent.getPoint() != 0) {
/* 44 */       RankingData data = null;
/* 45 */       for (RankingData rankingData : ((CrossRaceRankResponse)this.response).ranklist) {
/* 46 */         if (rankingData.playerId == playerSession.getPlayer().getPlayerId()) {
/* 47 */           data = rankingData; break;
/*    */         } 
/*    */       } 
/* 50 */       LogUtil.errorLog(new Object[] { "local rank data", data });
/* 51 */       if (data == null) {
/* 52 */         data = CrossUtil.getPlayerRaceRank("", playerSession.getPlayer().getPlayerId(), crossRaceComponent.getPoint());
/* 53 */         LogUtil.errorLog(new Object[] { "remote rank data", data });
/*    */       } 
/* 55 */       if (data != null) {
/* 56 */         ((CrossRaceRankResponse)this.response).myRank = data;
/*    */       }
/*    */     } 
/*    */     
/* 60 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRace\CrossRaceRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */