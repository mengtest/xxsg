/*    */ package com.linlongyx.sanguo.webgame.processors.stage;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.stage.StageInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.StageData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StageInfoProcessor
/*    */   extends ProcessorBase<StageInfoRequest, StageInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new StageInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, StageInfoRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 54))
/* 27 */       return 10061; 
/* 28 */     StageComponent stageComponent = (StageComponent)playerSession.getPlayer().createIfNotExist(StageComponent.class);
/* 29 */     stageComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           StageEntity stageEntity = (StageEntity)iMapEntity;
/*    */           if (stageEntity != null) {
/*    */             StageData stageData = new StageData();
/*    */             stageData.battle = stageEntity.getBattle();
/*    */             stageData.level = stageEntity.getLevel();
/*    */             stageData.star = stageEntity.getStar();
/*    */             stageData.id = stageEntity.getId();
/*    */             stageData.exp = stageEntity.getExp();
/*    */             if (stageEntity.getFightValue() <= 0L) {
/*    */               StageUtil.updateStageFightValue(stageEntity, playerSession, stageComponent, false);
/*    */             }
/*    */             stageData.fightValue = stageEntity.getFightValue();
/*    */             ((StageInfoResponse)this.response).list.add(stageData);
/*    */           } 
/*    */         });
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\stage\StageInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */