/*     */ package com.linlongyx.sanguo.webgame.processors.shop;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.shop.ShopEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.turnplate.TurnplateEntity;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopNormalBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ShopSecretiBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TurnplateBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.ShopParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.FunctionOpenConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.RoutineShopListRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.shop.RoutineShopListResponse;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoutineShopListProcessor
/*     */   extends ProcessorBase<RoutineShopListRequest, RoutineShopListResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  33 */     this.response = (ResponseBase)new RoutineShopListResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, RoutineShopListRequest request) {
/*  38 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 31)) {
/*  39 */       return 10061;
/*     */     }
/*  41 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  42 */     ShopComponent shopComponent = (ShopComponent)playerSession.getPlayer().createIfNotExist(ShopComponent.class);
/*  43 */     ShopParameter parameter = (ShopParameter)ParameterConstant.getParameter(31);
/*  44 */     int type = request.type;
/*  45 */     ShopEntity shopEntity = null;
/*     */     
/*  47 */     if (type == ShopUtil.ShopType.ItemShop.getType()) {
/*  48 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ItemShop.getType());
/*  49 */       if (null == shopEntity) {
/*  50 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.ItemShop.getType());
/*     */       }
/*  52 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ItemShop.getType());
/*  53 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.ItemShop.getType()));
/*  54 */       if (null != shopList) {
/*     */         
/*  56 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/*  57 */         shopEntity.setIds(new ArrayList());
/*  58 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/*  61 */       List<Integer> list = shopEntity.getIds();
/*  62 */       for (Integer id : list) {
/*  63 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/*  64 */         if (null != shopNormalBean) {
/*  65 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/*  66 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/*  68 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.ItemShop.getType(), shopEntity
/*  69 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/*  72 */     } else if (type == ShopUtil.ShopType.PartnerShop.getType()) {
/*  73 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.PartnerShop.getType());
/*  74 */       if (null == shopEntity) {
/*  75 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.PartnerShop.getType());
/*     */       }
/*  77 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.PartnerShop.getType());
/*  78 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.PartnerShop.getType()));
/*  79 */       if (null != shopList) {
/*     */         
/*  81 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/*  82 */         shopEntity.setIds(new ArrayList());
/*  83 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/*  86 */       List<Integer> list = shopEntity.getIds();
/*  87 */       for (Integer id : list) {
/*  88 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/*  89 */         if (null != shopNormalBean) {
/*  90 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/*  91 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/*  93 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.PartnerShop.getType(), shopEntity
/*  94 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/*  97 */     } else if (type == ShopUtil.ShopType.EquipShop.getType()) {
/*  98 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.EquipShop.getType());
/*  99 */       if (null == shopEntity) {
/* 100 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.EquipShop.getType());
/*     */       }
/* 102 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.EquipShop.getType());
/* 103 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.EquipShop.getType()));
/*     */       
/* 105 */       if (null != shopList) {
/*     */         
/* 107 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 108 */         shopEntity.setIds(new ArrayList());
/* 109 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 112 */       List<Integer> list = shopEntity.getIds();
/* 113 */       for (Integer id : list) {
/* 114 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 115 */         if (null != shopNormalBean) {
/* 116 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 117 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 119 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.EquipShop.getType(), shopEntity
/* 120 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 123 */     } else if (type == ShopUtil.ShopType.ArenaRewardShop.getType()) {
/* 124 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ArenaRewardShop.getType());
/* 125 */       if (null == shopEntity) {
/* 126 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.ArenaRewardShop.getType());
/*     */       }
/* 128 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ArenaRewardShop.getType());
/* 129 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.ArenaRewardShop.getType()));
/*     */       
/* 131 */       if (null != shopList) {
/*     */         
/* 133 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 134 */         shopEntity.setIds(new ArrayList());
/* 135 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 138 */       List<Integer> list = shopEntity.getIds();
/* 139 */       for (Integer id : list) {
/* 140 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 141 */         if (null != shopNormalBean) {
/* 142 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 143 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 145 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.ArenaRewardShop.getType(), shopEntity
/* 146 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 149 */     } else if (type == ShopUtil.ShopType.ArenaShop.getType()) {
/* 150 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ArenaShop.getType());
/* 151 */       if (null == shopEntity) {
/* 152 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.ArenaShop.getType());
/*     */       }
/* 154 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ArenaShop.getType());
/* 155 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.ArenaShop.getType()));
/* 156 */       if (null != shopList) {
/*     */         
/* 158 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 159 */         shopEntity.setIds(new ArrayList());
/* 160 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 163 */       List<Integer> list = shopEntity.getIds();
/* 164 */       for (Integer id : list) {
/* 165 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 166 */         if (null != shopNormalBean) {
/* 167 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 168 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 170 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.ArenaShop.getType(), shopEntity
/* 171 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 174 */     } else if (type == ShopUtil.ShopType.FriendShop.getType()) {
/* 175 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.FriendShop.getType());
/* 176 */       if (null == shopEntity) {
/* 177 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.FriendShop.getType());
/*     */       }
/* 179 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.FriendShop.getType());
/* 180 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.FriendShop.getType()));
/*     */       
/* 182 */       if (null != shopList) {
/*     */         
/* 184 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 185 */         shopEntity.setIds(new ArrayList());
/* 186 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 189 */       List<Integer> list = shopEntity.getIds();
/* 190 */       for (Integer id : list) {
/* 191 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 192 */         if (null != shopNormalBean) {
/* 193 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 194 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 196 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.FriendShop.getType(), shopEntity
/* 197 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 200 */     } else if (type == ShopUtil.ShopType.UnparalleledShop.getType()) {
/* 201 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.UnparalleledShop.getType());
/* 202 */       if (null == shopEntity) {
/* 203 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.UnparalleledShop.getType());
/*     */       }
/* 205 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.UnparalleledShop.getType());
/* 206 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.UnparalleledShop.getType()));
/*     */       
/* 208 */       if (null != shopList) {
/*     */         
/* 210 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 211 */         shopEntity.setIds(new ArrayList());
/* 212 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 215 */       List<Integer> list = shopEntity.getIds();
/* 216 */       for (Integer id : list) {
/* 217 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 218 */         if (null != shopNormalBean) {
/* 219 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 220 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 222 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.UnparalleledShop.getType(), shopEntity
/* 223 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 226 */     } else if (type == ShopUtil.ShopType.UnparalleledRewardShop.getType()) {
/* 227 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.UnparalleledRewardShop.getType());
/* 228 */       if (null == shopEntity) {
/* 229 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.UnparalleledRewardShop.getType());
/*     */       }
/* 231 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.UnparalleledRewardShop.getType());
/* 232 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.UnparalleledRewardShop.getType()));
/*     */       
/* 234 */       if (null != shopList) {
/*     */         
/* 236 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 237 */         shopEntity.setIds(new ArrayList());
/* 238 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 241 */       List<Integer> list = shopEntity.getIds();
/* 242 */       for (Integer id : list) {
/* 243 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 244 */         if (null != shopNormalBean) {
/* 245 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 246 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 248 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.UnparalleledRewardShop.getType(), shopEntity
/* 249 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 252 */     } else if (type == ShopUtil.ShopType.GroupShop.getType()) {
/* 253 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.GroupShop.getType());
/* 254 */       if (null == shopEntity) {
/* 255 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.GroupShop.getType());
/*     */       }
/* 257 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.GroupShop.getType());
/* 258 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.GroupShop.getType()));
/*     */       
/* 260 */       if (null != shopList) {
/*     */         
/* 262 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 263 */         shopEntity.setIds(new ArrayList());
/* 264 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 267 */       List<Integer> list = shopEntity.getIds();
/* 268 */       for (Integer id : list) {
/* 269 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 270 */         if (null != shopNormalBean) {
/* 271 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 272 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 274 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.GroupShop.getType(), shopEntity
/* 275 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 278 */     } else if (type == ShopUtil.ShopType.GroupRewardShop.getType()) {
/* 279 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.GroupRewardShop.getType());
/* 280 */       if (null == shopEntity) {
/* 281 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.GroupRewardShop.getType());
/*     */       }
/* 283 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.GroupRewardShop.getType());
/* 284 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.GroupRewardShop.getType()));
/*     */       
/* 286 */       if (null != shopList) {
/*     */         
/* 288 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 289 */         shopEntity.setIds(new ArrayList());
/* 290 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 293 */       List<Integer> list = shopEntity.getIds();
/* 294 */       for (Integer id : list) {
/* 295 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 296 */         if (null != shopNormalBean) {
/* 297 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 298 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 300 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.GroupRewardShop.getType(), shopEntity
/* 301 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 304 */     } else if (type == ShopUtil.ShopType.SoulShop.getType()) {
/* 305 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.SoulShop.getType());
/* 306 */       if (null == shopEntity) {
/* 307 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.SoulShop.getType());
/*     */       }
/* 309 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.SoulShop.getType());
/* 310 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.SoulShop.getType()));
/*     */       
/* 312 */       if (null != shopList) {
/*     */         
/* 314 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 315 */         shopEntity.setIds(new ArrayList());
/* 316 */         shopEntity.setIds(shopList);
/*     */       } 
/*     */       
/* 319 */       List<Integer> list = shopEntity.getIds();
/* 320 */       for (Integer id : list) {
/* 321 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 322 */         if (null != shopNormalBean) {
/* 323 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 324 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 326 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.SoulShop.getType(), shopEntity
/* 327 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 330 */     } else if (type == ShopUtil.ShopType.BossShop.getType()) {
/* 331 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.BossShop.getType());
/* 332 */       if (null == shopEntity) {
/* 333 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.BossShop.getType());
/*     */       }
/* 335 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.BossShop.getType());
/* 336 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.BossShop.getType()));
/*     */       
/* 338 */       if (null != shopList) {
/*     */         
/* 340 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 341 */         shopEntity.setIds(new ArrayList());
/* 342 */         shopEntity.setIds(shopList);
/*     */       } 
/* 344 */       List<Integer> list = shopEntity.getIds();
/* 345 */       for (Integer id : list) {
/* 346 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 347 */         if (null != shopNormalBean) {
/* 348 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 349 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 351 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.BossShop.getType(), shopEntity
/* 352 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 355 */     } else if (type == ShopUtil.ShopType.TurnplateShop.getType()) {
/* 356 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.TurnplateShop.getType());
/* 357 */       if (null == shopEntity) {
/* 358 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.TurnplateShop.getType());
/*     */       }
/* 360 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.TurnplateShop.getType());
/*     */       
/* 362 */       TurnplateComponent turnplateComponent = (TurnplateComponent)playerSession.getPlayer().createIfNotExist(TurnplateComponent.class);
/* 363 */       if (turnplateComponent.getCurActId() != 0) {
/* 364 */         TurnplateEntity turnplateEntity = turnplateComponent.getEntity(turnplateComponent.getCurActId());
/* 365 */         if (turnplateEntity == null) {
/* 366 */           return 13101;
/*     */         }
/*     */       } 
/* 369 */       if (shopEntity.getActId() == 0) {
/* 370 */         shopEntity.setActId(turnplateComponent.getCurActId());
/*     */       }
/* 372 */       if (shopEntity.getActId() != turnplateComponent.getCurActId()) {
/* 373 */         shopEntity.setActId(turnplateComponent.getCurActId());
/* 374 */         shopComponent.resetToAct(shopEntity);
/*     */       } 
/*     */ 
/*     */       
/* 378 */       TurnplateBean turnplateBean = (TurnplateBean)JsonTableService.getJsonData(turnplateComponent.getCurActId(), TurnplateBean.class);
/*     */ 
/*     */       
/* 381 */       if (turnplateBean != null) {
/* 382 */         ArrayList<Integer> shopList = new ArrayList<>(turnplateBean.getGoodsId());
/*     */         
/* 384 */         shopEntity.setIds(shopList);
/*     */       } 
/* 386 */       List<Integer> list = shopEntity.getIds();
/* 387 */       for (Integer id : list) {
/* 388 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 389 */         if (null != shopNormalBean) {
/* 390 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 391 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 393 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.TurnplateShop.getType(), shopEntity
/* 394 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 397 */     } else if (type == ShopUtil.ShopType.RedEquipShop.getType()) {
/* 398 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.RedEquipShop.getType());
/* 399 */       if (null == shopEntity) {
/* 400 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.RedEquipShop.getType());
/*     */       }
/* 402 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.RedEquipShop.getType());
/* 403 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.RedEquipShop.getType()));
/*     */       
/* 405 */       if (null != shopList) {
/*     */         
/* 407 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 408 */         shopEntity.setIds(new ArrayList());
/* 409 */         shopEntity.setIds(shopList);
/*     */       } 
/* 411 */       List<Integer> list = shopEntity.getIds();
/* 412 */       for (Integer id : list) {
/* 413 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 414 */         if (null != shopNormalBean) {
/* 415 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 416 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 418 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.RedEquipShop.getType(), shopEntity
/* 419 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 422 */     } else if (type == ShopUtil.ShopType.GuessShop.getType()) {
/* 423 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.GuessShop.getType());
/* 424 */       if (null == shopEntity) {
/* 425 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.GuessShop.getType());
/*     */       }
/* 427 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.GuessShop.getType());
/* 428 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.GuessShop.getType()));
/*     */       
/* 430 */       if (null != shopList) {
/*     */         
/* 432 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 433 */         shopEntity.setIds(new ArrayList());
/* 434 */         shopEntity.setIds(shopList);
/*     */       } 
/* 436 */       List<Integer> list = shopEntity.getIds();
/* 437 */       for (Integer id : list) {
/* 438 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 439 */         if (null != shopNormalBean) {
/* 440 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 441 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 443 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.GuessShop.getType(), shopEntity
/* 444 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 447 */     } else if (type == ShopUtil.ShopType.InviteShop.getType()) {
/* 448 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.InviteShop.getType());
/* 449 */       if (null == shopEntity) {
/* 450 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.InviteShop.getType());
/*     */       }
/* 452 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.InviteShop.getType());
/* 453 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.InviteShop.getType()));
/*     */       
/* 455 */       if (null != shopList) {
/*     */         
/* 457 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 458 */         shopEntity.setIds(new ArrayList());
/* 459 */         shopEntity.setIds(shopList);
/*     */       } 
/* 461 */       List<Integer> list = shopEntity.getIds();
/* 462 */       for (Integer id : list) {
/* 463 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 464 */         if (null != shopNormalBean) {
/* 465 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 466 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 468 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.InviteShop.getType(), shopEntity
/* 469 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 472 */     } else if (type == ShopUtil.ShopType.SoulsShop.getType()) {
/* 473 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.SoulsShop.getType());
/* 474 */       if (null == shopEntity) {
/* 475 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.SoulsShop.getType());
/*     */       }
/* 477 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.SoulsShop.getType());
/* 478 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.SoulsShop.getType()));
/*     */       
/* 480 */       if (null != shopList) {
/*     */         
/* 482 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 483 */         shopEntity.setIds(new ArrayList());
/* 484 */         shopEntity.setIds(shopList);
/*     */       } 
/* 486 */       List<Integer> list = shopEntity.getIds();
/* 487 */       for (Integer id : list) {
/* 488 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 489 */         if (null != shopNormalBean) {
/* 490 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 491 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 493 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.SoulsShop.getType(), shopEntity
/* 494 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 497 */     } else if (type == ShopUtil.ShopType.SecretShop.getType()) {
/* 498 */       if (!FunctionOpenConstant.isFunctionOpen(playerSession, 3103)) {
/* 499 */         return 10061;
/*     */       }
/* 501 */       int shopLevel = ShopUtil.updateShopLevel(playerSession.getPlayer().getPlayerId());
/* 502 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.SecretShop.getType());
/* 503 */       if (null == shopEntity) {
/* 504 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.SecretShop.getType());
/*     */       }
/* 506 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.SecretShop.getType());
/* 507 */       ArrayList<Integer> shopList = new ArrayList<>((Collection<? extends Integer>)parameter.getSecretShopMap().get(Integer.valueOf(shopLevel)));
/*     */       
/* 509 */       if (null != shopList) {
/*     */         
/* 511 */         int level = ShopUtil.updateShopLevel(playerSession.getPlayer().getPlayerId());
/* 512 */         ShopSecretiBean shopSecretiBean = (ShopSecretiBean)JsonTableService.getJsonData(level, ShopSecretiBean.class);
/* 513 */         if (null != shopSecretiBean) {
/* 514 */           shopList = ShopUtil.getSecritShopList(shopList, playerComponent, shopSecretiBean);
/*     */         }
/* 516 */         shopEntity.setIds(new ArrayList());
/* 517 */         shopEntity.setIds(shopList);
/*     */       } 
/* 519 */       List<Integer> list = shopEntity.getIds();
/* 520 */       for (Integer id : list) {
/* 521 */         ShopSecretiBean shopSecretiBean = (ShopSecretiBean)JsonTableService.getJsonData(shopLevel, ShopSecretiBean.class);
/* 522 */         if (null != shopSecretiBean) {
/* 523 */           ShopSecretiBean.GoodsIdBean goodsIdBean = (ShopSecretiBean.GoodsIdBean)shopSecretiBean.getGoodsId().get(id);
/* 524 */           if (null == shopEntity.getCurrStock().get(id) && goodsIdBean.getLimitType() == 0 && goodsIdBean.getStock() != 0) {
/* 525 */             shopEntity.updateStock(id.intValue(), 0, goodsIdBean.getStock());
/*     */           }
/* 527 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.SecretShop.getType(), shopEntity
/* 528 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/* 531 */       ((RoutineShopListResponse)this.response).shopLevel = shopLevel;
/* 532 */     } else if (type == ShopUtil.ShopType.ExaminationShop.getType()) {
/* 533 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ExaminationShop.getType());
/* 534 */       if (null == shopEntity) {
/* 535 */         shopComponent.addShop(playerSession.getPlayer().getPlayerId(), ShopUtil.ShopType.ExaminationShop.getType());
/*     */       }
/* 537 */       shopEntity = shopComponent.getShopEntity(ShopUtil.ShopType.ExaminationShop.getType());
/* 538 */       ArrayList<Integer> shopList = (ArrayList<Integer>)parameter.getNormalConfig().get(Integer.valueOf(ShopUtil.ShopType.ExaminationShop.getType()));
/*     */       
/* 540 */       if (null != shopList) {
/*     */         
/* 542 */         shopList = ShopUtil.getShopList(shopList, playerComponent);
/* 543 */         shopEntity.setIds(new ArrayList());
/* 544 */         shopEntity.setIds(shopList);
/*     */       } 
/* 546 */       List<Integer> list = shopEntity.getIds();
/* 547 */       for (Integer id : list) {
/* 548 */         ShopNormalBean shopNormalBean = (ShopNormalBean)JsonTableService.getJsonData(id.intValue(), ShopNormalBean.class);
/* 549 */         if (null != shopNormalBean) {
/* 550 */           if (null == shopEntity.getCurrStock().get(id) && shopNormalBean.getLimitType() == 0 && shopNormalBean.getStock() != 0) {
/* 551 */             shopEntity.updateStock(id.intValue(), 0, shopNormalBean.getStock());
/*     */           }
/* 553 */           ((RoutineShopListResponse)this.response).goods.add(ShopUtil.transform(id.intValue(), shopEntity.getSellTimes(id.intValue()), ShopUtil.ShopType.ExaminationShop.getType(), shopEntity
/* 554 */                 .getTotalSellTimes(id.intValue()), shopEntity.getWeekSellTimes(id.intValue()), shopEntity.getActivitySellTimes(id.intValue()), ((Integer)shopEntity.getCurrStock().getOrDefault(id, Integer.valueOf(0))).intValue()));
/*     */         } 
/*     */       } 
/*     */     } 
/* 558 */     ExtendComponent extendComponent = (ExtendComponent)playerSession.getPlayer().createIfNotExist(ExtendComponent.class);
/* 559 */     ((RoutineShopListResponse)this.response).type = type;
/* 560 */     ((RoutineShopListResponse)this.response).totalChange = extendComponent.getTotalChangeBoss();
/*     */     
/* 562 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\shop\RoutineShopListProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */