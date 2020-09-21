/*    */ package com.linlongyx.sanguo.webgame.processors.mounts;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.mounts.MountsInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.MountsData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MountsInfoProcessor
/*    */   extends ProcessorBase<MountsInfoRequest, MountsInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 23 */     this.response = (ResponseBase)new MountsInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, MountsInfoRequest request) {
/* 28 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 42)) {
/* 29 */       return 10061;
/*    */     }
/* 31 */     MountsComponent mountsComponent = (MountsComponent)playerSession.getPlayer().createIfNotExist(MountsComponent.class);
/* 32 */     mountsComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           MountsEntity mountsEntity = (MountsEntity)iMapEntity;
/*    */           if (mountsEntity != null) {
/*    */             MountsData mountsData = new MountsData();
/*    */             mountsData.battle = mountsEntity.getBattle();
/*    */             mountsData.level = mountsEntity.getLevel();
/*    */             mountsData.star = mountsEntity.getStar();
/*    */             mountsData.mounts = mountsEntity.getMountsId();
/*    */             mountsData.exp = mountsEntity.getExp();
/*    */             if (mountsEntity.getFightValue() <= 0L) {
/*    */               MountsUtil.updateMountsFightValue(mountsEntity, playerSession, mountsComponent, false);
/*    */             }
/*    */             mountsData.fightValue = mountsEntity.getFightValue();
/*    */             mountsData.breaksLevel.addAll(mountsEntity.getBreaksLevel().keySet());
/*    */             ((MountsInfoResponse)this.response).warPetList.add(mountsData);
/*    */           } 
/*    */         });
/* 49 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 50 */     if (null != taskComponent) {
/* 51 */       taskComponent.refreshSchedule(TaskType.MountsOpen, 0, 1L);
/*    */     }
/* 53 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\mounts\MountsInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */