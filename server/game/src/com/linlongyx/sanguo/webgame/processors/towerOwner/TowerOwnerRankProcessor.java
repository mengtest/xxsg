/*    */ package com.linlongyx.sanguo.webgame.processors.towerOwner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerRankRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerRankResponse;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerOwnerRankProcessor
/*    */   extends ProcessorBase<TowerOwnerRankRequest, TowerOwnerRankResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 24 */     this.response = (ResponseBase)new TowerOwnerRankResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerOwnerRankRequest request) {
/* 29 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 67))
/* 30 */       return 10061; 
/* 31 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 32 */     Pair<Integer, ArrayList<RankingData>> kv = CrossUtil.getTowerRankList("TowerOwnerRankProcessor::handleRequest", playerSession.getPlayer().getPlayerId());
/* 33 */     if (kv.getValue() != null) {
/* 34 */       ((TowerOwnerRankResponse)this.response).towerOwnerList = (ArrayList)kv.getValue();
/*    */     }
/* 36 */     ((TowerOwnerRankResponse)this.response).myRank = ((Integer)kv.getKey()).intValue();
/* 37 */     ((TowerOwnerRankResponse)this.response).mobai = towerComponent.getMobai();
/* 38 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerOwnerRankProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */