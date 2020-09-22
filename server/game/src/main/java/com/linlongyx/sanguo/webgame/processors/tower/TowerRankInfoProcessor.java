/*    */ package com.linlongyx.sanguo.webgame.processors.tower;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerRankInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerRankInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.AbstractRank;
/*    */ import com.linlongyx.sanguo.webgame.service.rank.RankingType;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerRankInfoProcessor
/*    */   extends ProcessorBase<TowerRankInfoRequest, TowerRankInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new TowerRankInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerRankInfoRequest request) {
/* 28 */     RankBaseService rankBaseService = (RankBaseService)MContext.getBean("rankBaseService");
/* 29 */     AbstractRank abstractRank = rankBaseService.getAbstractRank(RankingType.TOWER.getType());
/* 30 */     ((TowerRankInfoResponse)this.response).list = new ArrayList(abstractRank.getRanks());
/* 31 */     ((TowerRankInfoResponse)this.response).rank = abstractRank.getPlayerRank(playerSession.getPlayer().getPlayerId());
/* 32 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 33 */     ((TowerRankInfoResponse)this.response).star = towerComponent.getCurLayers();
/* 34 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\tower\TowerRankInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */