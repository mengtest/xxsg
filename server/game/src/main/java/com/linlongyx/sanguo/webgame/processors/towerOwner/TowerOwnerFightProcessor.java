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
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerFightRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerFightResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerOwnerFightProcessor
/*    */   extends ProcessorBase<TowerOwnerFightRequest, TowerOwnerFightResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new TowerOwnerFightResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerOwnerFightRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 67))
/* 29 */       return 10061; 
/* 30 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 31 */     if (towerComponent.getCurLayers() < request.towerLevel) {
/* 32 */       return 11808;
/*    */     }
/* 34 */     Pair<RankingData, Pair<Integer, PlayerData>> data = CrossUtil.fightTowerLayer("TowerOwnerFightProcessor::handleRequest", playerSession.getPlayer().getPlayerId(), request.towerLevel);
/*    */     
/* 36 */     int state = ((Integer)((Pair)data.getValue()).getKey()).intValue();
/* 37 */     ((TowerOwnerFightResponse)this.response).state = state;
/* 38 */     if (state != TowerLayerFightState.CAN_OWN.getState())
/*    */     {
/* 40 */       if (state != TowerLayerFightState.FIGHTING_CAN_NOT_OWN.getState())
/*    */       {
/* 42 */         if (state != TowerLayerFightState.OWN_PROTECT.getState())
/*    */         {
/* 44 */           if (state == TowerLayerFightState.FIGHTING_CAN_OWN.getState() || state == TowerLayerFightState.OWN_CAN_FIGHT.getState()) {
/* 45 */             towerComponent.setTargetPlayerData((PlayerData)((Pair)data.getValue()).getValue());
/* 46 */             towerComponent.setCacheLayerId(request.towerLevel);
/*    */           } else {
/* 48 */             return 16702;
/*    */           }  }  } 
/*    */     }
/* 51 */     if (data.getKey() != null) {
/* 52 */       ((TowerOwnerFightResponse)this.response).layerData = (RankingData)data.getKey();
/*    */     } else {
/* 54 */       ((TowerOwnerFightResponse)this.response).layerData.rank = request.towerLevel;
/*    */     } 
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerOwnerFightProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */