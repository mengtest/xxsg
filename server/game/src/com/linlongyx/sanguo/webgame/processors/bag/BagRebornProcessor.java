/*     */ package com.linlongyx.sanguo.webgame.processors.bag;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.rune.RuneBagEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.RecoveryParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRebornRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagRebornResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BagRebornProcessor
/*     */   extends ProcessorBase<BagRebornRequest, BagRebornResponse> {
/*     */   protected void initResponse() {
/*  38 */     this.response = (ResponseBase)new BagRebornResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, BagRebornRequest request) {
/*  43 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 8)) {
/*  44 */       return 10061;
/*     */     }
/*     */     
/*  47 */     ArrayList<Reward> rewards = new ArrayList<>();
/*  48 */     RecoveryParameter recoveryParameter = (RecoveryParameter)ParameterConstant.getParameter(8);
/*  49 */     if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.getCurrencyType((recoveryParameter.getRebornCost()).id), (recoveryParameter.getRebornCost()).num)) {
/*  50 */       return 10052;
/*     */     }
/*  52 */     if (request.type == 0) {
/*     */       
/*  54 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*     */       
/*  56 */       EquipEntity equipEntity = equipComponent.checkEquip(request.pid);
/*  57 */       if (null == equipEntity) {
/*  58 */         return 10804;
/*     */       }
/*  60 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  61 */       if (null == itemBean) {
/*  62 */         return 10804;
/*     */       }
/*  64 */       if (equipEntity.getIsWear() == 1) {
/*  65 */         return 10812;
/*     */       }
/*  67 */       if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  68 */         return 10814;
/*     */       }
/*  70 */       if (EquipUtil.isTalisman(equipEntity.getItemId())) {
/*  71 */         return 10804;
/*     */       }
/*     */       
/*  74 */       rewards.addAll(BagUtil.equipBack(equipComponent, equipEntity));
/*  75 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/*  76 */       EquipUtil.EquipDataSys(playerSession, equipData);
/*  77 */     } else if (request.type == 1) {
/*  78 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  79 */       PartnerEntity partnerEntity = partnerComponent.getEntity(request.pid);
/*  80 */       if (null == partnerEntity) {
/*  81 */         return 13303;
/*     */       }
/*  83 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  84 */       if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) >= 0) {
/*  85 */         return 13327;
/*     */       }
/*  87 */       GeneralComponent generalComponent = (GeneralComponent)playerSession.getPlayer().createIfNotExist(GeneralComponent.class);
/*  88 */       if (generalComponent.getAssistInBattle().indexOf(Long.valueOf(partnerEntity.getPid())) >= 0) {
/*  89 */         return 13332;
/*     */       }
/*     */ 
/*     */       
/*  93 */       rewards = BagUtil.rebornBack(partnerComponent, partnerEntity, true);
/*  94 */       partnerComponent.getPartnerAttrUp().initBase(null, partnerEntity.getPid());
/*  95 */       partnerComponent.getPartnerAttrUp().initAll(playerSession.getPlayer(), partnerEntity.getPid());
/*  96 */       PartnerUtil.pushPartnerInfo(playerSession, PartnerUtil.tranformPartner(request.pid, playerComponent, partnerEntity));
/*  97 */       AttrUpdateUtil.refreshPartner(playerSession);
/*  98 */     } else if (request.type == 2) {
/*     */       
/* 100 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*     */       
/* 102 */       EquipEntity equipEntity = equipComponent.checkEquip(request.pid);
/* 103 */       if (null == equipEntity) {
/* 104 */         return 10804;
/*     */       }
/* 106 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 107 */       if (null == itemBean) {
/* 108 */         return 10804;
/*     */       }
/* 110 */       if (equipEntity.getIsWear() == 1) {
/* 111 */         return 10812;
/*     */       }
/* 113 */       if (!EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 114 */         return 10814;
/*     */       }
/*     */       
/* 117 */       rewards.addAll(BagUtil.magicWeapon(equipComponent, equipEntity));
/* 118 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 119 */       EquipUtil.EquipDataSys(playerSession, equipData);
/* 120 */     } else if (request.type == 3) {
/*     */       
/* 122 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*     */       
/* 124 */       EquipEntity equipEntity = equipComponent.checkEquip(request.pid);
/* 125 */       if (null == equipEntity) {
/* 126 */         return 10804;
/*     */       }
/* 128 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 129 */       if (null == itemBean) {
/* 130 */         return 10804;
/*     */       }
/* 132 */       if (equipEntity.getIsWear() == 1) {
/* 133 */         return 10812;
/*     */       }
/* 135 */       if (!EquipUtil.isTalisman(equipEntity.getItemId())) {
/* 136 */         return 10814;
/*     */       }
/*     */ 
/*     */       
/* 140 */       rewards.addAll(BagUtil.talismanRecovery(equipComponent, equipEntity));
/* 141 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 142 */       EquipUtil.EquipDataSys(playerSession, equipData);
/* 143 */     } else if (request.type == 4) {
/*     */       
/* 145 */       RuneBagComponent runeBagComponent = (RuneBagComponent)playerSession.getPlayer().createIfNotExist(RuneBagComponent.class);
/*     */       
/* 147 */       RuneBagEntity runeBagEntity = runeBagComponent.getEntity(request.pid);
/* 148 */       if (null == runeBagEntity) {
/* 149 */         return 17806;
/*     */       }
/* 151 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(runeBagEntity.getItemId(), ItemBean.class);
/* 152 */       if (null == itemBean) {
/* 153 */         return 10804;
/*     */       }
/* 155 */       if (runeBagEntity.getHole() > 0) {
/* 156 */         return 17809;
/*     */       }
/* 158 */       if (runeBagEntity.getLevel() <= 1) {
/* 159 */         return 17813;
/*     */       }
/*     */ 
/*     */       
/* 163 */       rewards.addAll(BagUtil.runeRecovery(runeBagComponent, runeBagEntity));
/*     */     } 
/*     */     
/* 166 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 167 */     FinanceUtil.overlyingMap(mapRewards, rewards);
/* 168 */     ArrayList<Reward> reward2 = FinanceUtil.overlyingReward(mapRewards);
/* 169 */     for (Reward reward : reward2) {
/* 170 */       if (reward.id == CurrencyType.COIN.getType()) {
/* 171 */         reward.num = reward.num * recoveryParameter.getRatio() / 10000L;
/*     */         break;
/*     */       } 
/*     */     } 
/* 175 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.getCurrencyType((recoveryParameter.getRebornCost()).id), (recoveryParameter.getRebornCost()).num, ResourceEvent.PartnerReborn);
/* 176 */     if (!reward2.isEmpty()) {
/* 177 */       FinanceUtil.reward(reward2, playerSession.getPlayer(), ResourceEvent.PartnerReborn, true);
/*     */     }
/* 179 */     ((BagRebornResponse)this.response).type = request.type;
/* 180 */     ((BagRebornResponse)this.response).pid = request.pid;
/* 181 */     LogUtils.errorLog(new Object[] { "BagRebornProcessor", Long.valueOf(playerSession.getPlayer().getPlayerId()), Integer.valueOf(request.type), Long.valueOf(request.pid) });
/* 182 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagRebornProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */