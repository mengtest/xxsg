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
/*    */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetBattleListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.partner.GetBattleListResponse;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetBattleListProcessor
/*    */   extends ProcessorBase<GetBattleListRequest, GetBattleListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 25 */     this.response = (ResponseBase)new GetBattleListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetBattleListRequest request) {
/* 30 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 31 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 32 */     if (request.type == 5) {
/* 33 */       ((GetBattleListResponse)this.response).partnerList.addAll(unparalleledComponent.getBattlePartner());
/* 34 */       unparalleledComponent.getPartneredHpMap().keySet().forEach(partner -> {
/*    */             KeyValue keyValue = new KeyValue();
/*    */             keyValue.key = partner.longValue();
/*    */             keyValue.value = ((Long)unparalleledComponent.getPartneredHpMap().get(partner)).longValue();
/*    */             ((GetBattleListResponse)this.response).partnerHP.add(keyValue);
/*    */           });
/* 40 */       if (unparalleledComponent.getAttrsList().isEmpty()) {
/*    */         
/* 42 */         PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 43 */         KeyValue keyValue = new KeyValue();
/* 44 */         keyValue.key = -1L;
/* 45 */         keyValue.value = playerComponent.getPlayerAttrUp().getAttrByType(AttributeType.HP.getType(), -1L);
/* 46 */         keyValue.valueStr = playerComponent.getLevel() + "";
/* 47 */         ((GetBattleListResponse)this.response).MaxHPLevel.add(keyValue);
/* 48 */         for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 49 */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 50 */           if (null != partnerEntity) {
/* 51 */             KeyValue kv = new KeyValue();
/* 52 */             kv.key = partnerEntity.getPid();
/* 53 */             kv.value = partnerComponent.getPartnerAttrUp().getAttrByType(AttributeType.HP.getType(), partnerEntity.getPid());
/* 54 */             kv.valueStr = partnerEntity.getLevel() + "";
/* 55 */             ((GetBattleListResponse)this.response).MaxHPLevel.add(kv);
/*    */           } 
/*    */         } 
/*    */       } else {
/* 59 */         for (Iterator<Long> iterator = unparalleledComponent.getAttrsList().keySet().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 60 */           KeyValue keyValue = new KeyValue();
/* 61 */           keyValue.key = pid;
/* 62 */           keyValue.value = ((Long)((ArrayList<Long>)unparalleledComponent.getAttrsList().get(Long.valueOf(pid))).get(AttributeType.HP.getType())).longValue();
/* 63 */           keyValue.valueStr = ((Integer)unparalleledComponent.getLevelMap().getOrDefault(Long.valueOf(pid), Integer.valueOf(0))).toString();
/* 64 */           ((GetBattleListResponse)this.response).MaxHPLevel.add(keyValue); }
/*    */       
/*    */       } 
/* 67 */     } else if (request.type == 1) {
/* 68 */       ((GetBattleListResponse)this.response).partnerList.addAll(playerComponent.getFighters());
/* 69 */     } else if (request.type == 6) {
/* 70 */       if (playerComponent.getSoulsFighter().isEmpty()) {
/* 71 */         ArrayList<Integer> arrayList = new ArrayList<>(6);
/* 72 */         for (int i = 0; i < 6; i++) {
/* 73 */           arrayList.add(Integer.valueOf(0));
/*    */         }
/* 75 */         playerComponent.setSoulsFighter(arrayList);
/*    */       } 
/* 77 */       for (Iterator<Integer> iterator = playerComponent.getSoulsFighter().iterator(); iterator.hasNext(); ) { int sId = ((Integer)iterator.next()).intValue();
/* 78 */         ((GetBattleListResponse)this.response).partnerList.add(Long.valueOf(sId)); }
/*    */     
/*    */     } 
/* 81 */     ((GetBattleListResponse)this.response).quality = playerComponent.getQuality();
/*    */     
/* 83 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\GetBattleListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */