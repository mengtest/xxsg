/*    */ package com.linlongyx.sanguo.webgame.processors.towerOwner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.cross.CrossUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerGiveUpRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.towerOwner.TowerOwnerGiveUpResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TowerOwnerGiveUpProcessor
/*    */   extends ProcessorBase<TowerOwnerGiveUpRequest, TowerOwnerGiveUpResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 17 */     this.response = (ResponseBase)new TowerOwnerGiveUpResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, TowerOwnerGiveUpRequest request) {
/* 22 */     boolean success = CrossUtil.giveupTowerLayer("TowerOwnerGiveUpProcessor::handleRequest", playerSession.getPlayer().getPlayerId(), request.layerId);
/*    */     
/* 24 */     ((TowerOwnerGiveUpResponse)this.response).state = success ? 0 : 1;
/* 25 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\towerOwner\TowerOwnerGiveUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */