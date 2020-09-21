/*    */ package com.linlongyx.sanguo.webgame.processors.tower;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.tower.TowerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.tower.TowerInfoResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerInfoProcessor
/*    */   extends ProcessorBase<TowerInfoRequest, TowerInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new TowerInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerInfoRequest request) {
/* 24 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 40))
/* 25 */       return 10061; 
/* 26 */     TowerComponent towerComponent = (TowerComponent)playerSession.getPlayer().createIfNotExist(TowerComponent.class);
/* 27 */     ((TowerInfoResponse)this.response).curLayers = towerComponent.getCurLayers();
/* 28 */     ((TowerInfoResponse)this.response).preLayers = towerComponent.getPreLayers();
/* 29 */     ((TowerInfoResponse)this.response).todayCCYReward = towerComponent.getTodayCCYReward();
/* 30 */     ((TowerInfoResponse)this.response).todayNormalReward = towerComponent.getTodayNormalReward();
/* 31 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\tower\TowerInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */