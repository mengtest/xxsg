/*    */ package com.linlongyx.sanguo.webgame.processors.stage;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageBattleRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageBattleResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageBattleProcessor
/*    */   extends ProcessorBase<StageBattleRequest, StageBattleResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 20 */     this.response = (ResponseBase)new StageBattleResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, StageBattleRequest request) {
/* 25 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 54))
/* 26 */       return 10061; 
/* 27 */     StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/* 28 */     StageEntity stageEntity = stageComponent.getEntity(request.stage);
/* 29 */     if (null == stageEntity) {
/* 30 */       return 15005;
/*    */     }
/* 32 */     StageEntity stageEntity1 = stageComponent.getBattleStage();
/*    */     
/* 34 */     if (stageEntity.getBattle() == 0) {
/* 35 */       stageEntity.setBattle(1);
/* 36 */       stageComponent.updateBattleDB(request.stage);
/* 37 */       if (stageEntity1 != null) {
/* 38 */         stageEntity1.setBattle(0);
/* 39 */         stageComponent.updateBattleDB(stageEntity1.getId());
/*    */       } 
/*    */     } else {
/* 42 */       stageEntity.setBattle(0);
/* 43 */       stageComponent.updateBattleDB(request.stage);
/*    */     } 
/* 45 */     ((StageBattleResponse)this.response).stage = request.stage;
/* 46 */     ((StageBattleResponse)this.response).state = stageEntity.getBattle();
/* 47 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\stage\StageBattleProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */