/*    */ package com.linlongyx.sanguo.webgame.processors.warpet;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WarPetData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetInfoRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.warpet.WarPetInfoResponse;
/*    */ 
/*    */ 
/*    */ public class WarPetInfoProcessor
/*    */   extends ProcessorBase<WarPetInfoRequest, WarPetInfoResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 19 */     this.response = (ResponseBase)new WarPetInfoResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, WarPetInfoRequest request) {
/* 24 */     WarPetComponent warPetComponent = (WarPetComponent)playerSession.getPlayer().createIfNotExist(WarPetComponent.class);
/* 25 */     warPetComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           WarPetEntity warPetEntity = (WarPetEntity)iMapEntity;
/*    */           if (warPetEntity != null) {
/*    */             WarPetData warPetData = new WarPetData();
/*    */             warPetData.battle = warPetEntity.getBattle();
/*    */             warPetData.level = warPetEntity.getLevel();
/*    */             warPetData.star = warPetEntity.getStar();
/*    */             warPetData.warPet = warPetEntity.getWarPetId();
/*    */             warPetData.exp = warPetEntity.getExp();
/*    */             if (warPetEntity.getFightValue() <= 0L) {
/*    */               WarPetUtil.updateWarPetFightValue(warPetEntity, playerSession, warPetComponent, false);
/*    */             }
/*    */             warPetData.fightValue = warPetEntity.getFightValue();
/*    */             ((WarPetInfoResponse)this.response).warPetList.add(warPetData);
/*    */           } 
/*    */         });
/* 41 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\warpet\WarPetInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */