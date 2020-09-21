/*     */ package com.linlongyx.sanguo.webgame.processors.bag;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.reward.DropBag;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ChargeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagUseItemRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.bag.BagUseItemResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BagUseItemProcessor
/*     */   extends ProcessorBase<BagUseItemRequest, BagUseItemResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  37 */     this.response = (ResponseBase)new BagUseItemResponse();
/*     */   }
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, BagUseItemRequest request) {
/*     */     int count;
/*  42 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 1004)) {
/*  43 */       return 10061;
/*     */     }
/*  45 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(request.itemId, ItemBean.class);
/*  46 */     if (null == itemBean) {
/*  47 */       return 10701;
/*     */     }
/*  49 */     if (request.num <= 0) {
/*  50 */       return 10720;
/*     */     }
/*     */     
/*  53 */     if (itemBean.getUseStatus() == 0 || (itemBean.getUseStatus() == 1 && request.num > 1)) {
/*  54 */       return 10705;
/*     */     }
/*  56 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*     */     
/*  58 */     if (itemBean.getUseLevel() > playerComponent.getLevel() || itemBean.getMaxUseLevel() < playerComponent.getLevel()) {
/*  59 */       return 10706;
/*     */     }
/*     */     
/*  62 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  63 */     BagEntity bagEntity = bagComponent.getEntity(request.itemId);
/*  64 */     if (null == bagEntity) {
/*  65 */       return 10703;
/*     */     }
/*     */     
/*  68 */     if (bagEntity.getNum() < request.num) {
/*  69 */       return 10704;
/*     */     }
/*     */     
/*  72 */     if (itemBean.getUseType() == 7) {
/*  73 */       ChargeBean chargeBean = (ChargeBean)JsonTableService.getJsonData(itemBean.getExtend(), ChargeBean.class);
/*  74 */       if (null == chargeBean) {
/*  75 */         return 11603;
/*     */       }
/*  77 */       bagComponent.deleteItem(request.itemId, request.num, ResourceEvent.BagUseItem, true);
/*  78 */       PlayerUtil.charge(playerSession.getPlayer(), chargeBean);
/*  79 */       return 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     if (itemBean.getUseType() == 5) {
/*  87 */       if (request.num % itemBean.getComposeNum() != 0) {
/*  88 */         return 10094;
/*     */       }
/*  90 */       count = request.num / itemBean.getComposeNum();
/*     */ 
/*     */       
/*  93 */       for (RewardBean reward : itemBean.getReward()) {
/*  94 */         if (reward.getType() == 4) {
/*  95 */           if (count > 1) {
/*  96 */             return 10094;
/*     */           }
/*  98 */           PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*  99 */           boolean isHash = partnerComponent.checkPartner(reward.getId());
/* 100 */           if (isHash) {
/* 101 */             return 13331;
/*     */           }
/* 103 */           TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 104 */           if (null != taskComponent) {
/* 105 */             taskComponent.refreshSchedule(TaskType.ComposePartner, 0, count);
/* 106 */             ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 107 */             extendComponent.setTotalComPartner(extendComponent.getTotalComPartner() + count);
/* 108 */             taskComponent.refreshSchedule(TaskType.TotalComposePartner, 0, count);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 113 */       count = request.num;
/*     */     } 
/*     */ 
/*     */     
/* 117 */     List<RewardBean> rewards = itemBean.getReward();
/* 118 */     ArrayList<Reward> reward2 = new ArrayList<>();
/* 119 */     for (RewardBean reward : rewards) {
/* 120 */       ArrayList<Reward> dropRewards; int i; switch (reward.getType()) {
/*     */         case 2:
/* 122 */           reward2.add(FinanceUtil.transform4(reward, count));
/*     */         
/*     */         case 1:
/* 125 */           reward2.add(FinanceUtil.transform4(reward, count));
/*     */         
/*     */         case 3:
/* 128 */           dropRewards = new ArrayList<>();
/* 129 */           for (i = 0; i < count; i++) {
/* 130 */             dropRewards.clear();
/* 131 */             DropBag.getDropReward(reward.getId(), dropRewards);
/* 132 */             if (!dropRewards.isEmpty()) {
/* 133 */               reward2.addAll(dropRewards);
/*     */             }
/*     */           } 
/*     */         
/*     */         case 4:
/* 138 */           reward2.add(FinanceUtil.transform4(reward, count));
/*     */         
/*     */         case 5:
/* 141 */           reward2.add(FinanceUtil.transform4(reward, count));
/*     */         
/*     */         case 7:
/* 144 */           reward2.add(FinanceUtil.transform4(reward, count));
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 150 */     bagComponent.deleteItem(request.itemId, request.num, ResourceEvent.BagUseItem, true);
/* 151 */     Map<Long, Long> mapRewards = new HashMap<>();
/* 152 */     FinanceUtil.overlyingMap(mapRewards, reward2);
/* 153 */     ArrayList<Reward> reward3 = FinanceUtil.overlyingReward(mapRewards);
/* 154 */     if (!reward3.isEmpty()) {
/* 155 */       ((BagUseItemResponse)this.response).list = FinanceUtil.rewardGet(reward3, playerSession.getPlayer(), ResourceEvent.BagUseItem, true);
/*     */     }
/* 157 */     ((BagUseItemResponse)this.response).itemId = request.itemId;
/* 158 */     ((BagUseItemResponse)this.response).num = request.num;
/* 159 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\bag\BagUseItemProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */