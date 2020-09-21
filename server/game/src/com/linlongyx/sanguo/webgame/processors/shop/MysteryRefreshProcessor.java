/*     */ package com.linlongyx.sanguo.webgame.processors.shop;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.login.VipUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.MysteryRefreshRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.MysteryRefreshResponse;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ public class MysteryRefreshProcessor extends ProcessorBase<MysteryRefreshRequest, MysteryRefreshResponse> {
/*     */   protected void initResponse() {
/*  26 */     this.response = (ResponseBase)new MysteryRefreshResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, MysteryRefreshRequest request) {
/*  35 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*  36 */     ShopParameter parameter = (ShopParameter)ParameterConstant.getParameter(31);
/*  37 */     ShopComponent shopComponent = (ShopComponent)playerSession.getPlayer().createIfNotExist(ShopComponent.class);
/*  38 */     if (request.type == ShopUtil.ShopType.MysteryShop.getType()) {
/*  39 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 31)) {
/*  40 */         return 10061;
/*     */       }
/*     */       
/*  43 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/*  44 */       if (null == shopEntity) {
/*  45 */         return 13101;
/*     */       }
/*  47 */       int refreshNum = shopEntity.getRefreshNum();
/*  48 */       refreshNum++;
/*     */       
/*  50 */       int freeCount = parameter.getFreeCount() + VipUtil.getNum(playerComponent.getVip(), 7);
/*  51 */       if (refreshNum > freeCount) {
/*  52 */         int cost = parameter.getCost(refreshNum - freeCount, ShopUtil.ShopType.MysteryShop.getType());
/*  53 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/*  54 */           return 10052;
/*     */         }
/*  56 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.ShopRefresh);
/*     */       } 
/*     */       
/*  59 */       shopEntity.setRefreshNum(refreshNum);
/*  60 */       shopEntity.setSells(new HashMap<>());
/*  61 */       shopEntity.setIds(parameter.getMysteryShopIds(playerComponent.getLevel()));
/*  62 */       List<Integer> list = shopEntity.getIds();
/*  63 */       for (Integer id : list) {
/*  64 */         ((MysteryRefreshResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.MysteryShop.getType(), shopEntity.getTotalSellTimes(id.intValue()), shopEntity
/*  65 */               .getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), 0));
/*     */       }
/*  67 */       ((MysteryRefreshResponse)this.response).refreshNum = shopEntity.getRefreshNum();
/*  68 */       ((MysteryRefreshResponse)this.response).refreshTime = shopEntity.getRefreshTime();
/*  69 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  70 */       if (null != taskComponent) {
/*  71 */         taskComponent.refreshSchedule(TaskType.RefreshMySteryShop, 0, 1L);
/*     */       }
/*  73 */     } else if (request.type == ShopUtil.ShopType.CrystalSoulShop.getType()) {
/*  74 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3101)) {
/*  75 */         return 10061;
/*     */       }
/*  77 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.CrystalSoulShop.getType());
/*  78 */       if (null == shopEntity) {
/*  79 */         return 13101;
/*     */       }
/*  81 */       int refreshNum = shopEntity.getRefreshNum();
/*  82 */       refreshNum++;
/*     */       
/*  84 */       int freeCount = parameter.getCrystalRreeCount() + VipUtil.getNum(playerComponent.getVip(), 21);
/*  85 */       if (refreshNum > freeCount) {
/*  86 */         int cost = parameter.getCost(refreshNum - freeCount, ShopUtil.ShopType.CrystalSoulShop.getType());
/*  87 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/*  88 */           return 10052;
/*     */         }
/*  90 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.ShopRefresh);
/*     */       } 
/*     */       
/*  93 */       shopEntity.setRefreshNum(refreshNum);
/*  94 */       shopEntity.setSells(new HashMap<>());
/*  95 */       shopEntity.setIds(parameter.getCrystalShopIds(playerComponent.getLevel()));
/*  96 */       List<Integer> list = shopEntity.getIds();
/*  97 */       for (Integer id : list) {
/*  98 */         ((MysteryRefreshResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.CrystalSoulShop.getType(), shopEntity.getTotalSellTimes(id.intValue()), shopEntity
/*  99 */               .getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), 0));
/*     */       }
/* 101 */       ((MysteryRefreshResponse)this.response).refreshNum = shopEntity.getRefreshNum();
/* 102 */       ((MysteryRefreshResponse)this.response).refreshTime = shopEntity.getRefreshTime();
/* 103 */     } else if (request.type == ShopUtil.ShopType.BloodCrystalsShop.getType()) {
/* 104 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3102)) {
/* 105 */         return 10061;
/*     */       }
/*     */       
/* 108 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 109 */       if (null == shopEntity) {
/* 110 */         return 13101;
/*     */       }
/* 112 */       int refreshNum = shopEntity.getRefreshNum();
/* 113 */       refreshNum++;
/*     */       
/* 115 */       int freeCount = VipUtil.getNum(playerComponent.getVip(), 22);
/* 116 */       if (refreshNum > freeCount) {
/* 117 */         int cost = parameter.getCost(refreshNum - freeCount, ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 118 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost)) {
/* 119 */           return 10052;
/*     */         }
/* 121 */         FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.CCY, cost, ResourceEvent.BloodShopRefresh);
/*     */       } 
/*     */       
/* 124 */       shopEntity.setRefreshNum(refreshNum);
/* 125 */       shopEntity.setSells(new HashMap<>());
/* 126 */       shopEntity.setIds(parameter.getBloodShopIds(playerComponent.getLevel()));
/* 127 */       List<Integer> list = shopEntity.getIds();
/* 128 */       for (Integer id : list) {
/* 129 */         ((MysteryRefreshResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.BloodCrystalsShop.getType(), shopEntity.getTotalSellTimes(id.intValue()), shopEntity
/* 130 */               .getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), 0));
/*     */       }
/* 132 */       ((MysteryRefreshResponse)this.response).refreshNum = shopEntity.getRefreshNum();
/* 133 */       ((MysteryRefreshResponse)this.response).refreshTime = shopEntity.getRefreshTime();
/*     */     } 
/* 135 */     ((MysteryRefreshResponse)this.response).type = request.type;
/* 136 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\shop\MysteryRefreshProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */