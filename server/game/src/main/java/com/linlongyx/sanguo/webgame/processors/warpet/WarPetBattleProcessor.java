/*    */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetBattleResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarPetBattleProcessor
/*    */   extends ProcessorBase<WarPetBattleRequest, WarPetBattleResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new WarPetBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarPetBattleRequest request) {
/* 25 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 30))
/* 26 */       return 10061; 
/* 27 */     WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/* 28 */     WarPetEntity warPetEntity = warPetComponent.getEntity(request.warpet);
/* 29 */     if (null == warPetEntity) {
/* 30 */       return 15005;
/*    */     }
/* 32 */     WarPetEntity warPetEntity1 = warPetComponent.getBattleWarPet();
/*    */ 
/*    */ 
/*    */     
/* 36 */     if (warPetEntity.getBattle() == 0) {
/* 37 */       warPetEntity.setBattle(1);
/* 38 */       warPetComponent.updateBattleDB(request.warpet);
/* 39 */       if (warPetEntity1 != null) {
/* 40 */         warPetEntity1.setBattle(0);
/* 41 */         warPetComponent.updateBattleDB(warPetEntity1.getWarPetId());
/*    */       } 
/*    */     } else {
/* 44 */       warPetEntity.setBattle(0);
/* 45 */       warPetComponent.updateBattleDB(request.warpet);
/*    */     } 
/* 47 */     ((WarPetBattleResponse)this.response).warpet = request.warpet;
/* 48 */     ((WarPetBattleResponse)this.response).battleState = warPetEntity.getBattle();
/* 49 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\WarPetBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */