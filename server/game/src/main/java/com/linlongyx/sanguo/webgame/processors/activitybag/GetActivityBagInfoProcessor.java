/*    */ package com.linlongyx.sanguo.webgame.processors.activitybag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.activitybag.ActivityBagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.activitybag.ActivityBagEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.activitybag.GetActivityBagInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.activitybag.GetActivityBagInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ActivityItemInfo;
/*    */ 
/*    */ 
/*    */ public class GetActivityBagInfoProcessor
/*    */   extends ProcessorBase<GetActivityBagInfoRequest, GetActivityBagInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new GetActivityBagInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetActivityBagInfoRequest request) {
/* 24 */     ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/*    */     
/* 26 */     activityBagComponent.getEntityMap().values().forEach(activityBagEntity -> {
/*    */           ActivityBagEntity bagEntity = (ActivityBagEntity)activityBagEntity;
/*    */           
/*    */           ActivityItemInfo activityItemInfo = new ActivityItemInfo();
/*    */           activityItemInfo.itemId = bagEntity.getItemId();
/*    */           activityItemInfo.num = bagEntity.getItemId();
/*    */           activityItemInfo.end = bagEntity.getEnd();
/*    */           ((GetActivityBagInfoResponse)this.response).bagItemInfos.add(activityItemInfo);
/*    */         });
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\activitybag\GetActivityBagInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */