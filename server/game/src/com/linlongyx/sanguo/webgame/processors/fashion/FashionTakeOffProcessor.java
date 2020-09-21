/*    */ package com.linlongyx.sanguo.webgame.processors.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.crossRace.CrossRaceUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionTakeOffRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionTakeOffResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionTakeOffProcessor
/*    */   extends ProcessorBase<FashionTakeOffRequest, FashionTakeOffResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new FashionTakeOffResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FashionTakeOffRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 39)) {
/* 27 */       return 10061;
/*    */     }
/* 29 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 30 */     if (playerComponent.getWearFashion() == 0 || request.fashionId != playerComponent.getWearFashion()) {
/* 31 */       return 13905;
/*    */     }
/* 33 */     playerComponent.setWearFashion(0);
/*    */ 
/*    */     
/* 36 */     DestinyUtil.uploadDestinyData(playerSession.getPlayer());
/* 37 */     CrossRaceUtil.uploadPlayerRaceData(playerSession.getPlayer());
/*    */     
/* 39 */     ((FashionTakeOffResponse)this.response).fashionId = request.fashionId;
/* 40 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fashion\FashionTakeOffProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */