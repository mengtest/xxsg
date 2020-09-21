/*    */ package com.linlongyx.sanguo.webgame.processors.bag;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*    */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*    */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.GetRebornListRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.bag.GetRebornListResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GetRebornListProcessor
/*    */   extends ProcessorBase<GetRebornListRequest, GetRebornListResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 30 */     this.response = (ResponseBase)new GetRebornListResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, GetRebornListRequest request) {
/* 35 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 8))
/* 36 */       return 10061; 
/* 37 */     if (request.type == 0) {
/* 38 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 39 */       equipComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */             EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*    */             
/*    */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*    */             
/*    */             if (itemBean != null && !EquipUtil.isTreasure(equipEntity.getItemId()) && (equipEntity.getRefineLv() > 0 || equipEntity.getStrengthLv() > 0 || equipEntity.getZhuLv() > 0 || EquipUtil.getEquipStonesNum(equipEntity) > 0 || equipEntity.getStar() > 0) && equipEntity.getIsWear() == 0 && itemBean.getEquipPart() != 7) {
/*    */               ((GetRebornListResponse)this.response).ids.add(Long.valueOf(equipEntity.getMid()));
/*    */             }
/*    */           });
/* 48 */     } else if (request.type == 1) {
/* 49 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 50 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 51 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/* 52 */       partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */             PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*    */             
/*    */             FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*    */             
/*    */             if (fighterBean != null && (partnerEntity.getBreakthroughs() > 0 || partnerEntity.getLevel() > 1) && playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) < 0 && generalComponent.getAssistInBattle().indexOf(Long.valueOf(partnerEntity.getPid())) < 0) {
/*    */               ((GetRebornListResponse)this.response).ids.add(Long.valueOf(partnerEntity.getPid()));
/*    */             }
/*    */           });
/* 61 */     } else if (request.type == 2) {
/* 62 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 63 */       equipComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */             EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*    */             if ((equipEntity.getRefineLv() > 0 || equipEntity.getStrengthLv() > 0) && equipEntity.getIsWear() == 0 && EquipUtil.isTreasure(equipEntity.getItemId())) {
/*    */               ((GetRebornListResponse)this.response).ids.add(Long.valueOf(equipEntity.getMid()));
/*    */             }
/*    */           });
/* 69 */     } else if (request.type == 3) {
/* 70 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 71 */       equipComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */             EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*    */             
/*    */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*    */             
/*    */             if (itemBean != null && equipEntity.getTalismanRank() > 1 && equipEntity.getIsWear() == 0 && EquipUtil.isTalisman(equipEntity.getItemId())) {
/*    */               ((GetRebornListResponse)this.response).ids.add(Long.valueOf(equipEntity.getMid()));
/*    */             }
/*    */           });
/* 80 */     } else if (request.type == 4) {
/* 81 */       RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/* 82 */       runeBagComponent.getEntityMap().values().forEach(iMapEntity -> {
/*    */             RuneBagEntity runeBagEntity = (RuneBagEntity)iMapEntity;
/*    */             
/*    */             if (null != runeBagEntity && runeBagEntity.getHole() <= 0 && runeBagEntity.getLevel() > 1) {
/*    */               ((GetRebornListResponse)this.response).ids.add(Long.valueOf(runeBagEntity.getMid()));
/*    */             }
/*    */           });
/*    */     } 
/*    */     
/* 91 */     ((GetRebornListResponse)this.response).type = request.type;
/* 92 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\GetRebornListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */