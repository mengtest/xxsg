/*    */ package com.linlongyx.sanguo.webgame.processors.partner;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PartnerListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PartnerInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PartnerListProcessor
/*    */   extends ProcessorBase<PartnerListRequest, PartnerListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 22 */     this.response = (ResponseBase)new PartnerListResponse();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, PartnerListRequest request) {
/* 30 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 31 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*    */     
/* 33 */     PartnerInfo partner = PartnerUtil.tranformPartner2(playerComponent);
/* 34 */     ((PartnerListResponse)this.response).partnerList.add(partner);
/*    */     
/* 36 */     long[] playerFirstHand = { 0L };
/*    */     
/* 38 */     partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*    */ 
/*    */           
/*    */           PartnerInfo partnerInfo = PartnerUtil.tranformPartner(partnerEntity.getPid(), playerComponent, partnerEntity);
/*    */ 
/*    */           
/*    */           partnerComponent.getPartnerAttrUp().getAttrList(partnerInfo.attributes, partnerEntity.getPid());
/*    */ 
/*    */           
/*    */           playerFirstHand[0] = playerFirstHand[0] + partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.FIRST_HAND.getType(), partnerEntity.getPid());
/*    */ 
/*    */           
/*    */           ((PartnerListResponse)this.response).partnerList.add(partnerInfo);
/*    */         });
/*    */     
/* 54 */     playerFirstHand[0] = playerFirstHand[0] + playerComponent.getPlayerAttrUp().getAttrByType(AttributeType.FIRST_HAND.getType(), -1L);
/* 55 */     playerComponent.setFirstHand(playerFirstHand[0]);
/* 56 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */