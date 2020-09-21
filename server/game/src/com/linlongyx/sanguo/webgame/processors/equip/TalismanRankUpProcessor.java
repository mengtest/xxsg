/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TalismanLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.TalismanRankUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.TalismanRankUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TalismanRankUpProcessor
/*     */   extends ProcessorBase<TalismanRankUpRequest, TalismanRankUpResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new TalismanRankUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, TalismanRankUpRequest request) {
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 2901))
/*  43 */       return 10061; 
/*  44 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  45 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  46 */     long pid = request.pid;
/*     */     
/*  48 */     if (pid == -1L) {
/*  49 */       PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  50 */       if (playerComponent.getTalisman() == 0L) {
/*  51 */         return 10823;
/*     */       }
/*  53 */       EquipEntity equipEntity = equipComponent.getEquipEntity(playerComponent.getTalisman());
/*  54 */       if (null == equipEntity) {
/*  55 */         return 10804;
/*     */       }
/*  57 */       int itemId = equipEntity.getItemId();
/*  58 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  59 */       if (null == itemBean) {
/*  60 */         return 10050;
/*     */       }
/*  62 */       TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(itemId, TalismanLevelBean.class);
/*  63 */       if (null == talismanLevelBean) {
/*  64 */         return 10006;
/*     */       }
/*  66 */       TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(equipEntity.getTalismanRank()));
/*  67 */       if (lvBean.getItem() == null || lvBean.getItem().isEmpty()) {
/*  68 */         return 10011;
/*     */       }
/*  70 */       ArrayList<Reward> rewards = FinanceUtil.transform(lvBean.getItem());
/*  71 */       short code = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/*  72 */       if (code != 0) {
/*  73 */         return code;
/*     */       }
/*  75 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.TalismanCost);
/*  76 */       equipEntity.setTalismanRank(equipEntity.getTalismanRank() + 1);
/*  77 */       equipComponent.updateTalismanRankToDB(equipEntity);
/*     */       
/*  79 */       AttrUpdateUtil.refreshLeader(playerSession);
/*  80 */       ((TalismanRankUpResponse)this.response).pid = pid;
/*  81 */       ((TalismanRankUpResponse)this.response).talismanRank = equipEntity.getTalismanRank();
/*  82 */       ((TalismanRankUpResponse)this.response).equipId = equipEntity.getMid();
/*     */     } else {
/*     */       
/*  85 */       PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  86 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  87 */       if (null == partnerEntity) {
/*  88 */         return 13303;
/*     */       }
/*  90 */       if (partnerEntity.getTalisman() == 0L) {
/*  91 */         return 10823;
/*     */       }
/*  93 */       EquipEntity equipEntity = equipComponent.getEquipEntity(partnerEntity.getTalisman());
/*  94 */       if (null == equipEntity) {
/*  95 */         return 10804;
/*     */       }
/*  97 */       int itemId = equipEntity.getItemId();
/*  98 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  99 */       if (null == itemBean) {
/* 100 */         return 10050;
/*     */       }
/* 102 */       TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(itemId, TalismanLevelBean.class);
/* 103 */       if (null == talismanLevelBean) {
/* 104 */         return 10006;
/*     */       }
/* 106 */       TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(equipEntity.getTalismanRank()));
/* 107 */       if (lvBean.getItem() == null || lvBean.getItem().isEmpty()) {
/* 108 */         return 10011;
/*     */       }
/* 110 */       ArrayList<Reward> rewards = FinanceUtil.transform(lvBean.getItem());
/* 111 */       short code = CostUtil.checkRewards(rewards, playerSession, bagComponent);
/* 112 */       if (code != 0) {
/* 113 */         return code;
/*     */       }
/*     */       
/* 116 */       CostUtil.costs(rewards, playerSession, bagComponent, ResourceEvent.TalismanCost);
/* 117 */       equipEntity.setTalismanRank(equipEntity.getTalismanRank() + 1);
/* 118 */       equipComponent.updateTalismanRankToDB(equipEntity);
/*     */       
/* 120 */       PartnerAttrUpdate.refreshPartner(playerSession, pid);
/* 121 */       ((TalismanRankUpResponse)this.response).pid = pid;
/* 122 */       ((TalismanRankUpResponse)this.response).talismanRank = equipEntity.getTalismanRank();
/* 123 */       ((TalismanRankUpResponse)this.response).equipId = equipEntity.getMid();
/*     */     } 
/* 125 */     AttrUpdateUtil.refreshAllEquip(playerSession);
/*     */     
/* 127 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\TalismanRankUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */