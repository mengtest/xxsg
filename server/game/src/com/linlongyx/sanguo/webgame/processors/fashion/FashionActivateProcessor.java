/*    */ package com.linlongyx.sanguo.webgame.processors.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.fashion.FashionEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FashionBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionActivateRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.fashion.FashionActivateResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionActivateProcessor
/*    */   extends ProcessorBase<FashionActivateRequest, FashionActivateResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 35 */     this.response = (ResponseBase)new FashionActivateResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, FashionActivateRequest request) {
/* 40 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 39)) {
/* 41 */       return 10061;
/*    */     }
/* 43 */     FashionBean fashionBean = (FashionBean)JsonTableService.getJsonData(request.fashionId, FashionBean.class);
/* 44 */     if (fashionBean == null) {
/* 45 */       return 13901;
/*    */     }
/*    */     
/* 48 */     FashionComponent fashionComponent = (FashionComponent)playerSession.getPlayer().createIfNotExist(FashionComponent.class);
/* 49 */     FashionEntity fashionEntity = fashionComponent.getFashionEntity(request.fashionId);
/* 50 */     if (fashionEntity != null) {
/* 51 */       return 13904;
/*    */     }
/*    */ 
/*    */     
/* 55 */     short errCode = CostUtil.handleCost(fashionBean.getItem(), playerSession, ResourceEvent.FashionActivate);
/* 56 */     if (errCode != 0) {
/* 57 */       return errCode;
/*    */     }
/* 59 */     fashionEntity = new FashionEntity(playerSession.getPlayer().getPlayerId(), request.fashionId);
/* 60 */     fashionComponent.addEntity((IEntity)fashionEntity);
/*    */     
/* 62 */     for (IMapEntity mapEntity : fashionComponent.getEntityMap().values()) {
/* 63 */       fashionEntity = (FashionEntity)mapEntity;
/* 64 */       IntIntValue intIntValue = new IntIntValue();
/* 65 */       intIntValue.key = fashionEntity.getFashionId();
/* 66 */       intIntValue.value = fashionEntity.getLevel();
/* 67 */       ((FashionActivateResponse)this.response).list.add(intIntValue);
/*    */     } 
/*    */     
/* 70 */     AttrUpdateUtil.refreshFashion(playerSession);
/*    */     
/* 72 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\fashion\FashionActivateProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */