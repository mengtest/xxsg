/*    */ package com.linlongyx.sanguo.webgame.processors.kungfu;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.kungfu.KungFuInfoResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KungFuData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KungFuInfoProcessor
/*    */   extends ProcessorBase<KungFuInfoRequest, KungFuInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 21 */     this.response = (ResponseBase)new KungFuInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, KungFuInfoRequest request) {
/* 26 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 53))
/* 27 */       return 10061; 
/* 28 */     KungFuComponent kungFuComponent = (KungFuComponent)playerSession.getPlayer().createIfNotExist(KungFuComponent.class);
/* 29 */     kungFuComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           KungFuEntity kungFuEntity = (KungFuEntity)iMapEntity;
/*    */           if (kungFuEntity != null) {
/*    */             KungFuData kungFuData = new KungFuData();
/*    */             kungFuData.battle = kungFuEntity.getBattle();
/*    */             kungFuData.level = kungFuEntity.getLevel();
/*    */             kungFuData.star = kungFuEntity.getStar();
/*    */             kungFuData.kungFu = kungFuEntity.getKungFuId();
/*    */             kungFuData.exp = kungFuEntity.getExp();
/*    */             if (kungFuEntity.getFightValue() <= 0L) {
/*    */               KungFuUtil.updateKungfuFightValue(kungFuEntity, playerSession, kungFuComponent, false);
/*    */             }
/*    */             kungFuData.fightValue = kungFuEntity.getFightValue();
/*    */             ((KungFuInfoResponse)this.response).list.add(kungFuData);
/*    */           } 
/*    */         });
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\kungfu\KungFuInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */