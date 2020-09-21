/*    */ package com.linlongyx.sanguo.webgame.processors.unparalleled;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetUnparalleledRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.unparalleled.GetUnparalleledRankResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingUnparaStar;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetUnparalleledRankProcessor
/*    */   extends ProcessorBase<GetUnparalleledRankRequest, GetUnparalleledRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new GetUnparalleledRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetUnparalleledRankRequest request) {
/* 28 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 29 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 30 */     RankingUnparaStar rankingUnparaStar = (RankingUnparaStar)rankBaseService.getAbstractRank(RankingType.UNPARALELLED_STAR.getType());
/* 31 */     ((GetUnparalleledRankResponse)this.response).rankList = new ArrayList(RankingUnparaStar.getRankInfos());
/* 32 */     ((GetUnparalleledRankResponse)this.response).myRank = rankingUnparaStar.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 33 */     unparalleledComponent.setLastMaxRank(((GetUnparalleledRankResponse)this.response).myRank);
/* 34 */     ((GetUnparalleledRankResponse)this.response).stars = unparalleledComponent.getLastMaxStar();
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processor\\unparalleled\GetUnparalleledRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */