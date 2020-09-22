/*    */ package com.linlongyx.sanguo.webgame.processors.mounts;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsRideRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsRideResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MountsRideProcessor
/*    */   extends ProcessorBase<MountsRideRequest, MountsRideResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 18 */     this.response = (ResponseBase)new MountsRideResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MountsRideRequest request) {
/* 23 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/* 24 */     MountsEntity mountsEntity = mountsComponent.getEntity(request.mounts);
/* 25 */     if (null == mountsEntity) {
/* 26 */       return 15005;
/*    */     }
/* 28 */     MountsEntity mountsEntity1 = mountsComponent.getTurnMountEntity();
/* 29 */     if (mountsEntity.getBattle() == 0) {
/* 30 */       mountsEntity.setBattle(1);
/* 31 */       mountsComponent.updateBattleDB(request.mounts);
/* 32 */       if (mountsEntity1 != null) {
/* 33 */         mountsEntity1.setBattle(0);
/* 34 */         mountsComponent.updateBattleDB(mountsEntity1.getMountsId());
/*    */       } 
/*    */     } else {
/* 37 */       mountsEntity.setBattle(0);
/* 38 */       mountsComponent.updateBattleDB(request.mounts);
/*    */     } 
/* 40 */     ((MountsRideResponse)this.response).mounts = request.mounts;
/* 41 */     ((MountsRideResponse)this.response).state = mountsEntity.getBattle();
/* 42 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MountsRideProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */