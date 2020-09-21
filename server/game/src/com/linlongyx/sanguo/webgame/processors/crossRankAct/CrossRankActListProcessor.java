/*    */ package com.linlongyx.sanguo.webgame.processors.crossRankAct;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.RankingKuafuBean;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.crossRankAct.CrossRankActListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossRankActListProcessor
/*    */   extends ProcessorBase<CrossRankActListRequest, CrossRankActListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new CrossRankActListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossRankActListRequest request) {
/* 24 */     int id = request.id;
/* 25 */     RankingKuafuBean bean = (RankingKuafuBean)JsonTableService.getJsonData(id, RankingKuafuBean.class);
/* 26 */     if (null == bean) {
/* 27 */       return 12001;
/*    */     }
/* 29 */     ((CrossRankActListResponse)this.response).id = id;
/* 30 */     ((CrossRankActListResponse)this.response).list = CrossRankActUtil.getRankDataList(id);
/* 31 */     for (RankingData data : ((CrossRankActListResponse)this.response).list) {
/* 32 */       if (data.playerId == playerSession.getPlayer().getPlayerId()) {
/* 33 */         ((CrossRankActListResponse)this.response).myRank = data.rank; break;
/*    */       } 
/*    */     } 
/* 36 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\crossRankAct\CrossRankActListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */