/*     */ package com.linlongyx.sanguo.webgame.processors.shop;
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.arena.ArenaComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopCrystalBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopHaeminBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopMysteryBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopNormalBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopSecretiBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.KeyValueConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.group.GroupUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.BuyGoodsRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.BuyGoodsResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.util.CostUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class BuyGoodsProcessor extends ProcessorBase<BuyGoodsRequest, BuyGoodsResponse> {
/*     */   protected void initResponse() {
/*  39 */     this.response = (ResponseBase)new BuyGoodsResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, BuyGoodsRequest request) {
/*  45 */     LogUtil.errorLog(new Object[] { "buy goods", Integer.valueOf(request.goodsId), Integer.valueOf(request.type), Integer.valueOf(request.num) });
/*     */     
/*  47 */     if (0 == request.goodsId) {
/*  48 */       return 13103;
/*     */     }
/*  50 */     if (request.num <= 0) {
/*  51 */       return 13110;
/*     */     }
/*     */     
/*  54 */     ((BuyGoodsResponse)this.response).type = request.type;
/*  55 */     ((BuyGoodsResponse)this.response).goodsId = request.goodsId;
/*  56 */     ((BuyGoodsResponse)this.response).num = request.num;
/*  57 */     if (request.type == ShopUtil.ShopType.MysteryShop.getType())
/*  58 */       return mysteryBuy(playerSession, request, ShopUtil.ShopType.MysteryShop.getType()); 
/*  59 */     if (request.type == ShopUtil.ShopType.ItemShop.getType())
/*  60 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.ItemShop.getType()); 
/*  61 */     if (request.type == ShopUtil.ShopType.PartnerShop.getType())
/*  62 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.PartnerShop.getType()); 
/*  63 */     if (request.type == ShopUtil.ShopType.EquipShop.getType())
/*  64 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.EquipShop.getType()); 
/*  65 */     if (request.type == ShopUtil.ShopType.ArenaShop.getType())
/*  66 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.ArenaShop.getType()); 
/*  67 */     if (request.type == ShopUtil.ShopType.ArenaRewardShop.getType())
/*  68 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.ArenaRewardShop.getType()); 
/*  69 */     if (request.type == ShopUtil.ShopType.FriendShop.getType())
/*  70 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.FriendShop.getType()); 
/*  71 */     if (request.type == ShopUtil.ShopType.UnparalleledShop.getType())
/*  72 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.UnparalleledShop.getType()); 
/*  73 */     if (request.type == ShopUtil.ShopType.UnparalleledRewardShop.getType())
/*  74 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.UnparalleledRewardShop.getType()); 
/*  75 */     if (request.type == ShopUtil.ShopType.GroupShop.getType())
/*  76 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.GroupShop.getType()); 
/*  77 */     if (request.type == ShopUtil.ShopType.GroupRewardShop.getType())
/*  78 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.GroupRewardShop.getType()); 
/*  79 */     if (request.type == ShopUtil.ShopType.SoulShop.getType())
/*  80 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.SoulShop.getType()); 
/*  81 */     if (request.type == ShopUtil.ShopType.BossShop.getType())
/*  82 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.BossShop.getType()); 
/*  83 */     if (request.type == ShopUtil.ShopType.TurnplateShop.getType())
/*  84 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.TurnplateShop.getType()); 
/*  85 */     if (request.type == ShopUtil.ShopType.RedEquipShop.getType())
/*  86 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.RedEquipShop.getType()); 
/*  87 */     if (request.type == ShopUtil.ShopType.GuessShop.getType())
/*  88 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.GuessShop.getType()); 
/*  89 */     if (request.type == ShopUtil.ShopType.InviteShop.getType())
/*  90 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.InviteShop.getType()); 
/*  91 */     if (request.type == ShopUtil.ShopType.SoulsShop.getType())
/*  92 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.SoulsShop.getType()); 
/*  93 */     if (request.type == ShopUtil.ShopType.CrystalSoulShop.getType())
/*  94 */       return mysteryBuy(playerSession, request, ShopUtil.ShopType.CrystalSoulShop.getType()); 
/*  95 */     if (request.type == ShopUtil.ShopType.BloodCrystalsShop.getType())
/*  96 */       return mysteryBuy(playerSession, request, ShopUtil.ShopType.BloodCrystalsShop.getType()); 
/*  97 */     if (request.type == ShopUtil.ShopType.SecretShop.getType())
/*  98 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.SecretShop.getType()); 
/*  99 */     if (request.type == ShopUtil.ShopType.ExaminationShop.getType()) {
/* 100 */       return dailyBuy(playerSession, request, ShopUtil.ShopType.ExaminationShop.getType());
/*     */     }
/*     */     
/* 103 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short mysteryBuy(IPlayerSession playerSession, BuyGoodsRequest request, int type) {
/* 115 */     IPlayer player = playerSession.getPlayer();
/* 116 */     PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/* 117 */     ShopComponent shopComponent = (ShopComponent)player.getComponent(ShopComponent.class);
/*     */ 
/*     */ 
/*     */     
/* 121 */     ShopParameter parameter = (ShopParameter)ParameterConstant.getParameter(31);
/* 122 */     if (type == ShopUtil.ShopType.MysteryShop.getType()) {
/* 123 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 31)) {
/* 124 */         return 10061;
/*     */       }
/* 126 */       ShopMysteryBean mysteryShopBean = (ShopMysteryBean)JsonTableService.getJsonData(request.goodsId, ShopMysteryBean.class);
/* 127 */       if (null == mysteryShopBean) {
/* 128 */         return 13104;
/*     */       }
/*     */       
/* 131 */       if (!FinanceUtil.checkCurrency(player, CurrencyType.getCurrencyType(mysteryShopBean.getCostType()), (mysteryShopBean.getCostNum() * request.num))) {
/* 132 */         return CostUtil.getErrorCode(CurrencyType.getCurrencyType(mysteryShopBean.getCostType()));
/*     */       }
/*     */       
/* 135 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.MysteryShop.getType());
/* 136 */       if (null == shopEntity) {
/* 137 */         return 13101;
/*     */       }
/*     */       
/* 140 */       int curTime = TimeUtil.currentTime();
/* 141 */       if (curTime > shopEntity.getRefreshTime()) {
/* 142 */         int zeroTime = TimeUtil.timingTime(0);
/* 143 */         int delta = curTime - zeroTime;
/* 144 */         shopEntity.setRefreshTime(curTime - delta % parameter.getRefreshTime() + parameter.getRefreshTime());
/* 145 */         shopEntity.setSells(new HashMap<>());
/* 146 */         shopEntity.setIds(parameter.getMysteryShopIds(playerComponent.getLevel()));
/*     */       } 
/*     */       
/* 149 */       if (!shopEntity.getIds().contains(Integer.valueOf(request.goodsId))) {
/* 150 */         return 13111;
/*     */       }
/*     */       
/* 153 */       if (mysteryShopBean.getSellTimes() > 0 && shopEntity.getSellTimes(request.goodsId) + request.num > mysteryShopBean.getSellTimes()) {
/* 154 */         return 13108;
/*     */       }
/*     */       
/* 157 */       long checkNum = mysteryShopBean.getCostNum() * request.num;
/* 158 */       if (checkNum > 2147483647L) {
/* 159 */         return 11808;
/*     */       }
/* 161 */       int num = (int)checkNum;
/* 162 */       FinanceUtil.decCurrency(player, CurrencyType.getCurrencyType(mysteryShopBean.getCostType()), num, ResourceEvent.MysteryBuy);
/*     */       
/* 164 */       shopEntity.addSellTimes(request.goodsId, request.num);
/* 165 */       shopEntity.addTotalSellTimes(request.goodsId, request.num);
/* 166 */       shopEntity.addWeekSellTimes(request.goodsId, request.num);
/* 167 */       shopComponent.updateSellsToDB(shopEntity);
/*     */       
/* 169 */       ShopUtil.grantReward(mysteryShopBean.getReward(), playerSession, request.num, ResourceEvent.MysteryBuy, ShopUtil.ShopType.MysteryShop.getType(), mysteryShopBean.getCostType(), num);
/*     */       
/* 171 */       ((BuyGoodsResponse)this.response).type = request.type;
/* 172 */       ((BuyGoodsResponse)this.response).goodsId = request.goodsId;
/* 173 */       ((BuyGoodsResponse)this.response).num = request.num;
/* 174 */       ((BuyGoodsResponse)this.response).sells = shopEntity.getSellTimes(request.goodsId);
/* 175 */       ((BuyGoodsResponse)this.response).totalSells = shopEntity.getTotalSellTimes(request.goodsId);
/* 176 */     } else if (type == ShopUtil.ShopType.CrystalSoulShop.getType()) {
/*     */       
/* 178 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3101)) {
/* 179 */         return 10061;
/*     */       }
/* 181 */       ShopCrystalBean shopCrystalBean = (ShopCrystalBean)JsonTableService.getJsonData(request.goodsId, ShopCrystalBean.class);
/* 182 */       if (null == shopCrystalBean) {
/* 183 */         return 13104;
/*     */       }
/*     */ 
/*     */       
/* 187 */       if (!FinanceUtil.checkCurrency(player, CurrencyType.getCurrencyType(shopCrystalBean.getCostType()), (shopCrystalBean.getCostNum() * request.num))) {
/* 188 */         return CostUtil.getErrorCode(CurrencyType.getCurrencyType(shopCrystalBean.getCostType()));
/*     */       }
/*     */       
/* 191 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.CrystalSoulShop.getType());
/* 192 */       if (null == shopEntity) {
/* 193 */         return 13101;
/*     */       }
/*     */       
/* 196 */       int curTime = TimeUtil.currentTime();
/* 197 */       if (curTime > shopEntity.getRefreshTime()) {
/* 198 */         int zeroTime = TimeUtil.timingTime(0);
/* 199 */         int delta = curTime - zeroTime;
/* 200 */         shopEntity.setRefreshTime(curTime - delta % parameter.getCrystalRefreshTime() + parameter.getCrystalRefreshTime());
/* 201 */         shopEntity.setSells(new HashMap<>());
/* 202 */         shopEntity.setIds(parameter.getCrystalShopIds(playerComponent.getLevel()));
/*     */       } 
/*     */       
/* 205 */       if (!shopEntity.getIds().contains(Integer.valueOf(request.goodsId))) {
/* 206 */         return 13111;
/*     */       }
/*     */       
/* 209 */       if (shopCrystalBean.getSellTimes() > 0 && shopEntity.getSellTimes(request.goodsId) + request.num > shopCrystalBean.getSellTimes()) {
/* 210 */         return 13108;
/*     */       }
/*     */       
/* 213 */       long checkNum = shopCrystalBean.getCostNum() * request.num;
/* 214 */       if (checkNum > 2147483647L) {
/* 215 */         return 11808;
/*     */       }
/* 217 */       int num = (int)checkNum;
/* 218 */       FinanceUtil.decCurrency(player, CurrencyType.getCurrencyType(shopCrystalBean.getCostType()), num, ResourceEvent.CrystalBuy);
/*     */       
/* 220 */       shopEntity.addSellTimes(request.goodsId, request.num);
/* 221 */       shopEntity.addTotalSellTimes(request.goodsId, request.num);
/* 222 */       shopEntity.addWeekSellTimes(request.goodsId, request.num);
/* 223 */       shopComponent.updateSellsToDB(shopEntity);
/*     */       
/* 225 */       ShopUtil.grantReward(shopCrystalBean.getReward(), playerSession, request.num, ResourceEvent.CrystalBuy, ShopUtil.ShopType.CrystalSoulShop.getType(), shopCrystalBean.getCostType(), num);
/*     */       
/* 227 */       ((BuyGoodsResponse)this.response).type = request.type;
/* 228 */       ((BuyGoodsResponse)this.response).goodsId = request.goodsId;
/* 229 */       ((BuyGoodsResponse)this.response).num = request.num;
/* 230 */       ((BuyGoodsResponse)this.response).sells = shopEntity.getSellTimes(request.goodsId);
/* 231 */       ((BuyGoodsResponse)this.response).totalSells = shopEntity.getTotalSellTimes(request.goodsId);
/* 232 */     } else if (type == ShopUtil.ShopType.BloodCrystalsShop.getType()) {
/*     */       
/* 234 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3102)) {
/* 235 */         return 10061;
/*     */       }
/* 237 */       ShopHaeminBean shopHaeminBean = (ShopHaeminBean)JsonTableService.getJsonData(request.goodsId, ShopHaeminBean.class);
/* 238 */       if (null == shopHaeminBean) {
/* 239 */         return 13104;
/*     */       }
/*     */ 
/*     */       
/* 243 */       if (!FinanceUtil.checkCurrency(player, CurrencyType.getCurrencyType(shopHaeminBean.getCostType()), (shopHaeminBean.getCostNum() * request.num))) {
/* 244 */         return CostUtil.getErrorCode(CurrencyType.getCurrencyType(shopHaeminBean.getCostType()));
/*     */       }
/*     */       
/* 247 */       ShopEntity shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.BloodCrystalsShop.getType());
/* 248 */       if (null == shopEntity) {
/* 249 */         return 13101;
/*     */       }
/*     */       
/* 252 */       int curTime = TimeUtil.currentTime();
/* 253 */       if (curTime > shopEntity.getRefreshTime()) {
/* 254 */         int zeroTime = TimeUtil.timingTime(0);
/* 255 */         int delta = curTime - zeroTime;
/* 256 */         shopEntity.setRefreshTime(curTime - delta % parameter.getBloodRefreshTime() + parameter.getBloodRefreshTime());
/* 257 */         shopEntity.setSells(new HashMap<>());
/* 258 */         shopEntity.setIds(parameter.getBloodShopIds(playerComponent.getLevel()));
/*     */       } 
/*     */       
/* 261 */       if (!shopEntity.getIds().contains(Integer.valueOf(request.goodsId))) {
/* 262 */         return 13111;
/*     */       }
/*     */       
/* 265 */       if (shopHaeminBean.getSellTimes() > 0 && shopEntity.getSellTimes(request.goodsId) + request.num > shopHaeminBean.getSellTimes()) {
/* 266 */         return 13108;
/*     */       }
/*     */       
/* 269 */       long checkNum = shopHaeminBean.getCostNum() * request.num;
/* 270 */       if (checkNum > 2147483647L) {
/* 271 */         return 11808;
/*     */       }
/* 273 */       int num = (int)checkNum;
/* 274 */       FinanceUtil.decCurrency(player, CurrencyType.getCurrencyType(shopHaeminBean.getCostType()), num, ResourceEvent.BloodCrystalBuy);
/*     */       
/* 276 */       shopEntity.addSellTimes(request.goodsId, request.num);
/* 277 */       shopEntity.addTotalSellTimes(request.goodsId, request.num);
/* 278 */       shopEntity.addWeekSellTimes(request.goodsId, request.num);
/* 279 */       shopComponent.updateSellsToDB(shopEntity);
/*     */       
/* 281 */       ShopUtil.grantReward(shopHaeminBean.getReward(), playerSession, request.num, ResourceEvent.CrystalBuy, ShopUtil.ShopType.BloodCrystalsShop.getType(), shopHaeminBean.getCostType(), num);
/*     */       
/* 283 */       ((BuyGoodsResponse)this.response).type = request.type;
/* 284 */       ((BuyGoodsResponse)this.response).goodsId = request.goodsId;
/* 285 */       ((BuyGoodsResponse)this.response).num = request.num;
/* 286 */       ((BuyGoodsResponse)this.response).sells = shopEntity.getSellTimes(request.goodsId);
/* 287 */       ((BuyGoodsResponse)this.response).totalSells = shopEntity.getTotalSellTimes(request.goodsId);
/*     */     } 
/* 289 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short dailyBuy(IPlayerSession playerSession, BuyGoodsRequest request, int type) {
/* 305 */     if (type == ShopUtil.ShopType.SecretShop.getType()) {
/* 306 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3103)) {
/* 307 */         return 10061;
/*     */       }
/* 309 */       int shopLevel = ShopUtil.updateShopLevel(playerSession.getPlayer().getPlayerId());
/* 310 */       ShopSecretiBean shopSecretiBean = (ShopSecretiBean)JsonTableService.getJsonData(shopLevel, ShopSecretiBean.class);
/* 311 */       if (null == shopSecretiBean) {
/* 312 */         return 13104;
/*     */       }
/* 314 */       ShopSecretiBean.GoodsIdBean goodsIdBean = (ShopSecretiBean.GoodsIdBean)shopSecretiBean.getGoodsId().get(Integer.valueOf(request.goodsId));
/* 315 */       if (goodsIdBean == null) {
/* 316 */         return 10006;
/*     */       }
/* 318 */       if (goodsIdBean.getMaxNum() < request.num) {
/* 319 */         return 13316;
/*     */       }
/* 321 */       IPlayer player = playerSession.getPlayer();
/* 322 */       long checkNum = goodsIdBean.getCostNum() * request.num;
/* 323 */       if (checkNum > 2147483647L) {
/* 324 */         return 11808;
/*     */       }
/* 326 */       int num = (int)checkNum;
/* 327 */       PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/* 328 */       ArrayList<ShopSecretiBean.GoodsIdBean.BuyConditionBean> condition = goodsIdBean.getBuyCondition();
/* 329 */       ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 330 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 331 */       TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/* 332 */       BagComponent bagComponent = (BagComponent)player.createIfNotExist(BagComponent.class);
/*     */       
/* 334 */       ShopComponent shopComponent = (ShopComponent)player.getComponent(ShopComponent.class);
/* 335 */       ShopEntity shopEntity = shopComponent.getShopEntity(type);
/* 336 */       if (null == shopEntity) {
/* 337 */         return 13101;
/*     */       }
/*     */       
/* 340 */       if (!FinanceUtil.checkCurrency(player, CurrencyType.getCurrencyType(goodsIdBean.getCostType()), num)) {
/* 341 */         return CostUtil.getErrorCode(CurrencyType.getCurrencyType(goodsIdBean.getCostType()));
/*     */       }
/* 343 */       Reward reward = null;
/* 344 */       if (goodsIdBean.getCostItem() != 0) {
/* 345 */         reward = new Reward();
/* 346 */         int itemType = FinanceUtil.correctType(2, goodsIdBean.getCostItem());
/* 347 */         reward.type = (byte)itemType;
/* 348 */         reward.num = goodsIdBean.getItemNum() * 1L * request.num;
/* 349 */         reward.id = goodsIdBean.getCostItem();
/* 350 */         short res = CostUtil.check(reward, playerSession, bagComponent);
/* 351 */         if (res != 0) {
/* 352 */           return res;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 357 */       for (ShopSecretiBean.GoodsIdBean.BuyConditionBean buyConditionBean : condition) {
/* 358 */         if (buyConditionBean.getType() == 1 && playerComponent.getLevel() < buyConditionBean.getValue())
/* 359 */           return 10084; 
/* 360 */         if (buyConditionBean.getType() == 2 && playerComponent.getVip() < buyConditionBean.getValue())
/* 361 */           return 13107; 
/* 362 */         if (buyConditionBean.getType() == 3 && arenaComponent.getRank() > buyConditionBean.getValue())
/* 363 */           return 13112; 
/* 364 */         if (buyConditionBean.getType() == 4 && unparalleledComponent.getLastMaxStar() < buyConditionBean.getValue())
/* 365 */           return 13113; 
/* 366 */         if (buyConditionBean.getType() == 5) {
/* 367 */           int level = GroupUtil.getGroupLevel(player.getPlayerId());
/* 368 */           if (level == 0) {
/* 369 */             return 11101;
/*     */           }
/* 371 */           if (level < buyConditionBean.getValue())
/* 372 */             return 13113;  continue;
/* 373 */         }  if (buyConditionBean.getType() == 6) {
/* 374 */           ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 375 */           long changeNum = extendComponent.getTotalChangeBoss();
/* 376 */           if (changeNum < buyConditionBean.getValue())
/* 377 */             return 13113;  continue;
/* 378 */         }  if (buyConditionBean.getType() == 7) {
/* 379 */           ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 380 */           long changeNum = extendComponent.getTotalChangeBoss();
/*     */           
/* 382 */           return 13113;
/*     */         } 
/*     */       } 
/*     */       
/* 386 */       if (goodsIdBean.getLimitType() == 0 && goodsIdBean.getStock() != 0) {
/* 387 */         int currStock = ((Integer)shopEntity.getCurrStock().getOrDefault(Integer.valueOf(request.goodsId), Integer.valueOf(goodsIdBean.getStock()))).intValue();
/* 388 */         if (currStock < request.num) {
/* 389 */           return 13115;
/*     */         }
/*     */       } 
/*     */       
/* 393 */       if (goodsIdBean.getSellTimes() > 0 && shopEntity.getSellTimes(request.goodsId) + request.num > goodsIdBean.getSellTimes()) {
/* 394 */         return 13108;
/*     */       }
/*     */       
/* 397 */       if (goodsIdBean.getLimitType() == 2 && shopEntity.getTotalSellTimes(request.goodsId) + request.num > goodsIdBean.getSellTimes()) {
/* 398 */         return 13108;
/*     */       }
/*     */       
/* 401 */       if (goodsIdBean.getLimitType() == 3 && shopEntity.getWeekSellTimes(request.goodsId) + request.num > goodsIdBean.getSellTimes()) {
/* 402 */         return 13108;
/*     */       }
/*     */       
/* 405 */       if (goodsIdBean.getLimitType() == 4 && shopEntity.getActivitySellTimes(request.goodsId) + request.num > goodsIdBean.getSellTimes()) {
/* 406 */         return 13108;
/*     */       }
/*     */       
/* 409 */       if (type == ShopUtil.ShopType.TurnplateShop.getType()) {
/* 410 */         TurnplateEntity turnplateEntity = turnplateComponent.getEntity(turnplateComponent.getCurActId());
/* 411 */         turnplateEntity.setPoint(turnplateEntity.getPoint() - num);
/* 412 */         PlayerUtil.updateKeyValueInfo(player.getSession(), KeyValueConstant.TurnplatePoint.getKey(), turnplateEntity.getPoint());
/*     */       } else {
/* 414 */         FinanceUtil.decCurrency(player, CurrencyType.getCurrencyType(goodsIdBean.getCostType()), num, ResourceEvent.RoutineBuy);
/*     */       } 
/* 416 */       if (null != reward) {
/* 417 */         CostUtil.cost(reward, playerSession, bagComponent, ResourceEvent.RoutineBuy);
/*     */       }
/* 419 */       if (goodsIdBean.getLimitType() == 4) {
/* 420 */         shopEntity.addActSellTimes(request.goodsId, request.num);
/*     */       }
/* 422 */       if (goodsIdBean.getLimitType() == 3) {
/* 423 */         shopEntity.addWeekSellTimes(request.goodsId, request.num);
/*     */       }
/* 425 */       if (goodsIdBean.getLimitType() == 1) {
/* 426 */         shopEntity.addSellTimes(request.goodsId, request.num);
/*     */       }
/* 428 */       if (goodsIdBean.getLimitType() == 2) {
/* 429 */         shopEntity.addTotalSellTimes(request.goodsId, request.num);
/*     */       }
/* 431 */       if (goodsIdBean.getLimitType() == 0 && goodsIdBean.getStock() != 0) {
/* 432 */         shopEntity.updateStock(request.goodsId, request.num, goodsIdBean.getStock());
/*     */       }
/* 434 */       shopComponent.updateSellsToDB(shopEntity);
/*     */       
/* 436 */       ShopUtil.grantReward(goodsIdBean.getReward(), playerSession, request.num, ResourceEvent.SecretShopBuy, type, goodsIdBean.getCostType(), num);
/*     */       
/* 438 */       ((BuyGoodsResponse)this.response).type = request.type;
/* 439 */       ((BuyGoodsResponse)this.response).goodsId = request.goodsId;
/* 440 */       ((BuyGoodsResponse)this.response).num = request.num;
/* 441 */       ((BuyGoodsResponse)this.response).sells = shopEntity.getSellTimes(request.goodsId);
/* 442 */       ((BuyGoodsResponse)this.response).totalSells = shopEntity.getTotalSellTimes(request.goodsId);
/* 443 */       ((BuyGoodsResponse)this.response).weekSells = shopEntity.getWeekSellTimes(request.goodsId);
/* 444 */       ((BuyGoodsResponse)this.response).actSells = shopEntity.getActivitySellTimes(request.goodsId);
/* 445 */       ((BuyGoodsResponse)this.response).stock = ((Integer)shopEntity.getCurrStock().getOrDefault(Integer.valueOf(request.goodsId), Integer.valueOf(0))).intValue();
/* 446 */       ((BuyGoodsResponse)this.response).shopLevel = shopLevel;
/*     */     } else {
/* 448 */       ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(request.goodsId, ShopNormalBean.class);
/* 449 */       if (null == shopNormalBean) {
/* 450 */         return 13104;
/*     */       }
/* 452 */       if (shopNormalBean.getMaxNum() < request.num) {
/* 453 */         return 13316;
/*     */       }
/* 455 */       IPlayer player = playerSession.getPlayer();
/* 456 */       long checkNum = shopNormalBean.getCostNum() * request.num;
/* 457 */       if (checkNum > 2147483647L) {
/* 458 */         return 11808;
/*     */       }
/* 460 */       int num = (int)checkNum;
/* 461 */       PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/* 462 */       ArrayList<ShopNormalBean.BuyConditionBean> condition = shopNormalBean.getBuyCondition();
/* 463 */       ArenaComponent arenaComponent = (ArenaComponent)playerSession.getPlayer().createIfNotExist(ArenaComponent.class);
/* 464 */       UnparalleledComponent unparalleledComponent = (UnparalleledComponent)playerSession.getPlayer().createIfNotExist(UnparalleledComponent.class);
/* 465 */       TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/* 466 */       BagComponent bagComponent = (BagComponent)player.createIfNotExist(BagComponent.class);
/*     */       
/* 468 */       ShopComponent shopComponent = (ShopComponent)player.getComponent(ShopComponent.class);
/* 469 */       ShopEntity shopEntity = shopComponent.getShopEntity(type);
/* 470 */       if (null == shopEntity) {
/* 471 */         return 13101;
/*     */       }
/* 473 */       if (type == ShopUtil.ShopType.TurnplateShop.getType()) {
/* 474 */         TurnplateEntity turnplateEntity = turnplateComponent.getEntity(turnplateComponent.getCurActId());
/* 475 */         if (turnplateEntity == null) {
/* 476 */           return 13101;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 485 */         if (num > turnplateEntity.getPoint()) {
/* 486 */           return 12403;
/*     */         }
/*     */       }
/* 489 */       else if (!FinanceUtil.checkCurrency(player, CurrencyType.getCurrencyType(shopNormalBean.getCostType()), num)) {
/* 490 */         return CostUtil.getErrorCode(CurrencyType.getCurrencyType(shopNormalBean.getCostType()));
/*     */       } 
/*     */       
/* 493 */       Reward reward = null;
/* 494 */       if (shopNormalBean.getCostItem() != 0) {
/* 495 */         reward = new Reward();
/* 496 */         int itemType = FinanceUtil.correctType(2, shopNormalBean.getCostItem());
/* 497 */         reward.type = (byte)itemType;
/* 498 */         reward.num = shopNormalBean.getItemNum() * 1L * request.num;
/* 499 */         reward.id = shopNormalBean.getCostItem();
/* 500 */         short res = CostUtil.check(reward, playerSession, bagComponent);
/* 501 */         if (res != 0) {
/* 502 */           return res;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 507 */       for (ShopNormalBean.BuyConditionBean buyConditionBean : condition) {
/* 508 */         if (buyConditionBean.getType() == 1 && playerComponent.getLevel() < buyConditionBean.getValue())
/* 509 */           return 10084; 
/* 510 */         if (buyConditionBean.getType() == 2 && playerComponent.getVip() < buyConditionBean.getValue())
/* 511 */           return 13107; 
/* 512 */         if (buyConditionBean.getType() == 3 && arenaComponent.getRank() > buyConditionBean.getValue())
/* 513 */           return 13112; 
/* 514 */         if (buyConditionBean.getType() == 4 && unparalleledComponent.getLastMaxStar() < buyConditionBean.getValue())
/* 515 */           return 13113; 
/* 516 */         if (buyConditionBean.getType() == 5) {
/* 517 */           int level = GroupUtil.getGroupLevel(player.getPlayerId());
/* 518 */           if (level == 0) {
/* 519 */             return 11101;
/*     */           }
/* 521 */           if (level < buyConditionBean.getValue())
/* 522 */             return 13113;  continue;
/* 523 */         }  if (buyConditionBean.getType() == 6) {
/* 524 */           ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 525 */           long changeNum = extendComponent.getTotalChangeBoss();
/* 526 */           if (changeNum < buyConditionBean.getValue()) {
/* 527 */             return 13113;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 532 */       if (shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 533 */         int currStock = ((Integer)shopEntity.getCurrStock().getOrDefault(Integer.valueOf(request.goodsId), Integer.valueOf(shopNormalBean.getStock()))).intValue();
/* 534 */         if (currStock < request.num) {
/* 535 */           return 13115;
/*     */         }
/*     */       } 
/*     */       
/* 539 */       if (shopNormalBean.getSellTimes() > 0 && shopEntity.getSellTimes(request.goodsId) + request.num > shopNormalBean.getSellTimes()) {
/* 540 */         return 13108;
/*     */       }
/*     */       
/* 543 */       if (shopNormalBean.getLimitType() == 2 && shopEntity.getTotalSellTimes(request.goodsId) + request.num > shopNormalBean.getSellTimes()) {
/* 544 */         return 13108;
/*     */       }
/*     */       
/* 547 */       if (shopNormalBean.getLimitType() == 3 && shopEntity.getWeekSellTimes(request.goodsId) + request.num > shopNormalBean.getSellTimes()) {
/* 548 */         return 13108;
/*     */       }
/*     */       
/* 551 */       if (shopNormalBean.getLimitType() == 4 && shopEntity.getActivitySellTimes(request.goodsId) + request.num > shopNormalBean.getSellTimes()) {
/* 552 */         return 13108;
/*     */       }
/*     */       
/* 555 */       if (type == ShopUtil.ShopType.TurnplateShop.getType()) {
/* 556 */         TurnplateEntity turnplateEntity = turnplateComponent.getEntity(turnplateComponent.getCurActId());
/* 557 */         turnplateEntity.setPoint(turnplateEntity.getPoint() - num);
/* 558 */         PlayerUtil.updateKeyValueInfo(player.getSession(), KeyValueConstant.TurnplatePoint.getKey(), turnplateEntity.getPoint());
/*     */       } else {
/* 560 */         FinanceUtil.decCurrency(player, CurrencyType.getCurrencyType(shopNormalBean.getCostType()), num, ResourceEvent.RoutineBuy);
/*     */       } 
/* 562 */       if (null != reward) {
/* 563 */         CostUtil.cost(reward, playerSession, bagComponent, ResourceEvent.RoutineBuy);
/*     */       }
/* 565 */       if (shopNormalBean.getLimitType() == 4) {
/* 566 */         shopEntity.addActSellTimes(request.goodsId, request.num);
/*     */       }
/* 568 */       if (shopNormalBean.getLimitType() == 3) {
/* 569 */         shopEntity.addWeekSellTimes(request.goodsId, request.num);
/*     */       }
/* 571 */       if (shopNormalBean.getLimitType() == 1) {
/* 572 */         shopEntity.addSellTimes(request.goodsId, request.num);
/*     */       }
/* 574 */       if (shopNormalBean.getLimitType() == 2) {
/* 575 */         shopEntity.addTotalSellTimes(request.goodsId, request.num);
/*     */       }
/* 577 */       if (shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 578 */         shopEntity.updateStock(request.goodsId, request.num, shopNormalBean.getStock());
/*     */       }
/* 580 */       shopComponent.updateSellsToDB(shopEntity);
/*     */       
/* 582 */       ShopUtil.grantReward(shopNormalBean.getReward(), playerSession, request.num, ResourceEvent.RoutineBuy, type, shopNormalBean.getCostType(), num);
/*     */       
/* 584 */       ((BuyGoodsResponse)this.response).type = request.type;
/* 585 */       ((BuyGoodsResponse)this.response).goodsId = request.goodsId;
/* 586 */       ((BuyGoodsResponse)this.response).num = request.num;
/* 587 */       ((BuyGoodsResponse)this.response).sells = shopEntity.getSellTimes(request.goodsId);
/* 588 */       ((BuyGoodsResponse)this.response).totalSells = shopEntity.getTotalSellTimes(request.goodsId);
/* 589 */       ((BuyGoodsResponse)this.response).weekSells = shopEntity.getWeekSellTimes(request.goodsId);
/* 590 */       ((BuyGoodsResponse)this.response).actSells = shopEntity.getActivitySellTimes(request.goodsId);
/* 591 */       ((BuyGoodsResponse)this.response).stock = ((Integer)shopEntity.getCurrStock().getOrDefault(Integer.valueOf(request.goodsId), Integer.valueOf(0))).intValue();
/*     */     } 
/* 593 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\shop\BuyGoodsProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */