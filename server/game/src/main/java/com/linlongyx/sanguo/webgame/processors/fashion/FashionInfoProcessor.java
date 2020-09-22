/*    */ package com.linlongyx.sanguo.webgame.processors.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionInfoProcessor
/*    */   extends ProcessorBase<FashionInfoRequest, FashionInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new FashionInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FashionInfoRequest request) {
/* 30 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 39)) {
/* 31 */       return 10061;
/*    */     }
/* 33 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/* 34 */     FashionComponent fashionComponent = (FashionComponent)playerSession.getPlayer().createIfNotExist(FashionComponent.class);
/*    */ 
/*    */     
/* 37 */     for (IMapEntity mapEntity : fashionComponent.getEntityMap().values()) {
/* 38 */       FashionEntity fashionEntity = (FashionEntity)mapEntity;
/* 39 */       IntIntValue intIntValue = new IntIntValue();
/* 40 */       intIntValue.key = fashionEntity.getFashionId();
/* 41 */       intIntValue.value = fashionEntity.getLevel();
/* 42 */       ((FashionInfoResponse)this.response).list.add(intIntValue);
/* 43 */       if (fashionEntity.getFashionId() == playerComponent.getWearFashion()) {
/* 44 */         ((FashionInfoResponse)this.response).fashion.key = fashionEntity.getFashionId();
/* 45 */         ((FashionInfoResponse)this.response).fashion.value = fashionEntity.getLevel();
/*    */       } 
/*    */     } 
/* 48 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fashion\FashionInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */