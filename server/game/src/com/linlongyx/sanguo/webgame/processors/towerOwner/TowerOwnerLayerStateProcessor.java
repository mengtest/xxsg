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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerLayerStateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerLayerStateResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerOwnerLayerStateProcessor
/*    */   extends ProcessorBase<TowerOwnerLayerStateRequest, TowerOwnerLayerStateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new TowerOwnerLayerStateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerOwnerLayerStateRequest request) {
/* 27 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 67))
/* 28 */       return 10061; 
/* 29 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 30 */     if (towerComponent.getCurLayers() < request.layerId) {
/* 31 */       return 11808;
/*    */     }
/* 33 */     Pair<Integer, RankingData> kv = CrossUtil.towerLayerState("TowerOwnerLayerStateProcessor::handleRequest", playerSession.getPlayer().getPlayerId(), request.layerId);
/* 34 */     if (kv.getValue() != null) {
/* 35 */       ((RankingData)kv.getValue()).rank = request.layerId;
/* 36 */       ((TowerOwnerLayerStateResponse)this.response).layerData = (RankingData)kv.getValue();
/*    */     } else {
/* 38 */       ((TowerOwnerLayerStateResponse)this.response).layerData.rank = request.layerId;
/*    */     } 
/* 40 */     ((TowerOwnerLayerStateResponse)this.response).state = ((Integer)kv.getKey()).intValue();
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerOwnerLayerStateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */