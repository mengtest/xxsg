/*     */ package com.linlongyx.sanguo.webgame.processors.shop;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.MysteryShopListRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.MysteryShopListResponse;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MysteryShopListProcessor
/*     */   extends ProcessorBase<MysteryShopListRequest, MysteryShopListResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  27 */     this.response = (ResponseBase)new MysteryShopListResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, MysteryShopListRequest request) {
/*  33 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().getComponent(PlayerComponent.class);
/*  34 */     ShopParameter parameter = (ShopParameter)ParameterConstant.getParameter(31);
/*     */     
/*  36 */     ShopComponent shopComponent = (ShopComponent)playerSession.getPlayer().createIfNotExist(ShopComponent.class);
/*  37 */     if (request.type == ShopUtil.ShopType.MysteryShop.getType()) {
/*  38 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 31)) {
/*  39 */         return 10061;
/*     */       }
/*  41 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/*  42 */       int curTime = TimeUtil.currentTime();
/*  43 */       if (null == shopEntity) {
/*  44 */         shopComponent.addShop(playerComponent.getPlayerId(), ShopUtil.ShopType.MysteryShop.getType());
/*  45 */         shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/*  46 */         int zeroTime = TimeUtil.timingTime(0);
/*  47 */         int delta = curTime - zeroTime;
/*  48 */         shopEntity.setRefreshTime(curTime - delta % parameter.getRefreshTime() + parameter.getRefreshTime());
/*  49 */         shopEntity.setIds(parameter.getMysteryShopIds(playerComponent.getLevel()));
/*  50 */         shopEntity.setRefreshNum(0);
/*  51 */         shopComponent.saveToDB();
/*     */       } 
/*  53 */       if (curTime > shopEntity.getRefreshTime()) {
/*  54 */         int zeroTime = TimeUtil.timingTime(0);
/*  55 */         int delta = curTime - zeroTime;
/*  56 */         shopEntity.setRefreshTime(curTime - delta % parameter.getRefreshTime() + parameter.getRefreshTime());
/*  57 */         shopEntity.setIds(parameter.getMysteryShopIds(playerComponent.getLevel()));
/*  58 */         shopEntity.setSells(new HashMap<>());
/*     */       } 
/*     */       
/*  61 */       List<Integer> list = shopEntity.getIds();
/*  62 */       for (Integer id : list) {
/*  63 */         ((MysteryShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.MysteryShop.getType(), shopEntity
/*  64 */               .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), 0));
/*     */       }
/*  66 */       ((MysteryShopListResponse)this.response).refreshNum = shopEntity.getRefreshNum();
/*  67 */       ((MysteryShopListResponse)this.response).refreshTime = shopEntity.getRefreshTime();
/*  68 */     } else if (request.type == ShopUtil.ShopType.CrystalSoulShop.getType()) {
/*  69 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3101)) {
/*  70 */         return 10061;
/*     */       }
/*  72 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.CrystalSoulShop.getType());
/*  73 */       int curTime = TimeUtil.currentTime();
/*  74 */       if (null == shopEntity) {
/*  75 */         shopComponent.addShop(playerComponent.getPlayerId(), ShopUtil.ShopType.CrystalSoulShop.getType());
/*  76 */         shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.CrystalSoulShop.getType());
/*  77 */         int zeroTime = TimeUtil.timingTime(0);
/*  78 */         int delta = curTime - zeroTime;
/*  79 */         shopEntity.setRefreshTime(curTime - delta % parameter.getCrystalRefreshTime() + parameter.getCrystalRefreshTime());
/*  80 */         shopEntity.setIds(parameter.getCrystalShopIds(playerComponent.getLevel()));
/*  81 */         shopEntity.setRefreshNum(0);
/*  82 */         shopComponent.saveToDB();
/*     */       } 
/*  84 */       if (curTime > shopEntity.getRefreshTime()) {
/*  85 */         int zeroTime = TimeUtil.timingTime(0);
/*  86 */         int delta = curTime - zeroTime;
/*  87 */         shopEntity.setRefreshTime(curTime - delta % parameter.getCrystalRefreshTime() + parameter.getCrystalRefreshTime());
/*  88 */         shopEntity.setIds(parameter.getCrystalShopIds(playerComponent.getLevel()));
/*  89 */         shopEntity.setSells(new HashMap<>());
/*     */       } 
/*     */       
/*  92 */       List<Integer> list = shopEntity.getIds();
/*  93 */       for (Integer id : list) {
/*  94 */         ((MysteryShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.CrystalSoulShop.getType(), shopEntity
/*  95 */               .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), 0));
/*     */       }
/*  97 */       ((MysteryShopListResponse)this.response).refreshNum = shopEntity.getRefreshNum();
/*  98 */       ((MysteryShopListResponse)this.response).refreshTime = shopEntity.getRefreshTime();
/*  99 */     } else if (request.type == ShopUtil.ShopType.BloodCrystalsShop.getType()) {
/* 100 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3102)) {
/* 101 */         return 10061;
/*     */       }
/* 103 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 104 */       int curTime = TimeUtil.currentTime();
/* 105 */       if (null == shopEntity) {
/* 106 */         shopComponent.addShop(playerComponent.getPlayerId(), ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 107 */         shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 108 */         int zeroTime = TimeUtil.timingTime(0);
/* 109 */         int delta = curTime - zeroTime;
/* 110 */         shopEntity.setRefreshTime(curTime - delta % parameter.getBloodRefreshTime() + parameter.getBloodRefreshTime());
/* 111 */         shopEntity.setIds(parameter.getBloodShopIds(playerComponent.getLevel()));
/* 112 */         shopEntity.setRefreshNum(0);
/* 113 */         shopComponent.saveToDB();
/*     */       } 
/* 115 */       if (curTime > shopEntity.getRefreshTime()) {
/* 116 */         int zeroTime = TimeUtil.timingTime(0);
/* 117 */         int delta = curTime - zeroTime;
/* 118 */         shopEntity.setRefreshTime(curTime - delta % parameter.getBloodRefreshTime() + parameter.getBloodRefreshTime());
/* 119 */         shopEntity.setIds(parameter.getBloodShopIds(playerComponent.getLevel()));
/* 120 */         shopEntity.setSells(new HashMap<>());
/*     */       } 
/*     */       
/* 123 */       List<Integer> list = shopEntity.getIds();
/* 124 */       for (Integer id : list) {
/* 125 */         ((MysteryShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.BloodCrystalsShop.getType(), shopEntity
/* 126 */               .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), 0));
/*     */       }
/* 128 */       ((MysteryShopListResponse)this.response).refreshNum = shopEntity.getRefreshNum();
/* 129 */       ((MysteryShopListResponse)this.response).refreshTime = shopEntity.getRefreshTime();
/*     */     } 
/* 131 */     ((MysteryShopListResponse)this.response).type = request.type;
/* 132 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\shop\MysteryShopListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */