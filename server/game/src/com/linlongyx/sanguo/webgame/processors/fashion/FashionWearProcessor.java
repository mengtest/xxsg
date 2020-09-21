/*    */ package com.linlongyx.sanguo.webgame.processors.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.crossRace.CrossRaceUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.destiny.DestinyUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionWearRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionWearResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionWearProcessor
/*    */   extends ProcessorBase<FashionWearRequest, FashionWearResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new FashionWearResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FashionWearRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 39)) {
/* 29 */       return 10061;
/*    */     }
/* 31 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 32 */     FashionComponent fashionComponent = (FashionComponent)playerSession.getPlayer().createIfNotExist(FashionComponent.class);
/* 33 */     FashionEntity fashionEntity = fashionComponent.getFashionEntity(request.fashionId);
/* 34 */     if (fashionEntity == null) {
/* 35 */       return 13902;
/*    */     }
/* 37 */     if (playerComponent.getWearFashion() == request.fashionId) {
/* 38 */       return 13903;
/*    */     }
/*    */     
/* 41 */     playerComponent.setWearFashion(fashionEntity.getFashionId());
/*    */ 
/*    */     
/* 44 */     DestinyUtil.uploadDestinyData(playerSession.getPlayer());
/* 45 */     CrossRaceUtil.uploadPlayerRaceData(playerSession.getPlayer());
/*    */     
/* 47 */     ((FashionWearResponse)this.response).fashionId = fashionEntity.getFashionId();
/*    */ 
/*    */     
/* 50 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fashion\FashionWearProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */