/*     */ package com.linlongyx.sanguo.webgame.processors.common;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.GetPlayerBattleInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.GetPlayerBattleInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PartnerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GetPlayerBattleInfoProcessor
/*     */   extends ProcessorBase<GetPlayerBattleInfoRequest, GetPlayerBattleInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  28 */     this.response = (ResponseBase)new GetPlayerBattleInfoResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, GetPlayerBattleInfoRequest request) {
/*  33 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(request.playerId, PlayerComponent.class);
/*  34 */     PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(request.playerId, PartnerComponent.class);
/*  35 */     EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(request.playerId, EquipComponent.class);
/*     */     
/*  37 */     if (null == partnerComponent || null == partnerComponent || null == equipComponent) {
/*  38 */       return 10019;
/*     */     }
/*     */     
/*  41 */     PartnerInfo partner = new PartnerInfo();
/*  42 */     partner.pid = -1L;
/*  43 */     partner.level = playerComponent.getLevel();
/*  44 */     partner.tid = playerComponent.getLeaderId();
/*  45 */     partner.exp = 0L;
/*  46 */     partner.wearSkin = 0;
/*  47 */     partner.activeSkins = new ArrayList();
/*  48 */     partner.desLv = playerComponent.getDesLv();
/*  49 */     partner.progress = playerComponent.getDesProgress();
/*  50 */     partner.primLevel = 0;
/*  51 */     partner.stars = playerComponent.getStars();
/*  52 */     partner.status = 1;
/*  53 */     partner.breachLevel = playerComponent.getBreakthroughs();
/*  54 */     partner.fightValue = playerComponent.getFightValue();
/*  55 */     playerComponent.getEquips().keySet().forEach(part -> {
/*     */           KeyValue keyValue = new KeyValue();
/*     */           keyValue.key = part.intValue();
/*     */           keyValue.value = ((Long)playerComponent.getEquips().get(part)).longValue();
/*     */           partner.equips.add(keyValue);
/*     */         });
/*  61 */     KeyValue keyValue = new KeyValue();
/*  62 */     keyValue.key = 7L;
/*  63 */     keyValue.value = playerComponent.getTalisman();
/*  64 */     partner.equips.add(keyValue);
/*  65 */     partner.reinIds = new ArrayList(playerComponent.getReincarnationIds());
/*  66 */     ((GetPlayerBattleInfoResponse)this.response).partnerList.add(partner);
/*     */     
/*  68 */     partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*     */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*     */           
/*     */           if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) >= 0) {
/*     */             PartnerInfo partnerInfo = new PartnerInfo();
/*     */             
/*     */             partnerInfo.pid = partnerEntity.getPid();
/*     */             
/*     */             partnerInfo.tid = partnerEntity.getTableId();
/*     */             
/*     */             partnerInfo.level = partnerEntity.getLevel();
/*     */             
/*     */             partnerInfo.exp = partnerEntity.getExp();
/*     */             
/*     */             partnerInfo.wearSkin = partnerEntity.getWearSkin();
/*     */             
/*     */             partnerInfo.activeSkins = new ArrayList(partnerEntity.getActiveSkins());
/*     */             partnerInfo.desLv = partnerEntity.getDesLv();
/*     */             partnerInfo.progress = partnerEntity.getProgress();
/*     */             partnerInfo.primLevel = partnerEntity.getPrimLv();
/*     */             partnerInfo.stars = partnerEntity.getStars();
/*     */             partnerInfo.status = partnerEntity.getStatus();
/*     */             partnerInfo.breachLevel = partnerEntity.getBreakthroughs();
/*     */             partnerInfo.fightValue = partnerEntity.getFightValue();
/*     */             partnerEntity.getEquips().keySet().forEach(());
/*     */             KeyValue keyValue1 = new KeyValue();
/*     */             keyValue1.key = 7L;
/*     */             keyValue1.value = partnerEntity.getTalisman();
/*     */             partnerInfo.equips.add(keyValue1);
/*     */             partnerInfo.reinIds = new ArrayList(partnerEntity.getReincarnationIds());
/*     */             ((GetPlayerBattleInfoResponse)this.response).partnerList.add(partnerInfo);
/*     */           } 
/*     */         });
/* 101 */     equipComponent.getEntityMap().values().forEach(iMapEntity -> {
/*     */           EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*     */           if (equipEntity.getIsWear() == 1) {
/*     */             EquipData equipData = EquipUtil.getEquipData(equipEntity);
/*     */             ((GetPlayerBattleInfoResponse)this.response).equips.add(equipData);
/*     */           } 
/*     */         });
/* 108 */     equipComponent.maybeSaveToDB();
/* 109 */     playerComponent.maybeSaveToDB();
/* 110 */     partnerComponent.maybeSaveToDB();
/* 111 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\common\GetPlayerBattleInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */