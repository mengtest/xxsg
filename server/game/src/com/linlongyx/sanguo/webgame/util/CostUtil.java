/*     */ package com.linlongyx.sanguo.webgame.util;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.activitybag.ActivityBagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RewardBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CostUtil
/*     */ {
/*     */   public static short handleCost(ArrayList<RewardBean> cost, IPlayerSession playerSession, ResourceEvent resourceEvent) {
/*  28 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*  29 */     ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/*  30 */     int time = TimeUtil.currentTime();
/*     */     
/*  32 */     for (RewardBean rewardBean : cost) {
/*  33 */       if (rewardBean.getType() == 1) {
/*  34 */         CurrencyType currencyType = CurrencyType.values()[rewardBean.getId()];
/*  35 */         if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), currencyType, rewardBean.getNum()))
/*  36 */           return getErrorCode(currencyType);  continue;
/*     */       } 
/*  38 */       if (rewardBean.getType() == 2) {
/*  39 */         if (!bagComponent.check(rewardBean.getId(), rewardBean.getNum()))
/*  40 */           return 10062;  continue;
/*     */       } 
/*  42 */       if (rewardBean.getType() == 9) {
/*  43 */         if (!activityBagComponent.check(time, rewardBean.getId(), rewardBean.getNum()))
/*  44 */           return 10050;  continue;
/*     */       } 
/*  46 */       if (rewardBean.getType() == 5) {
/*  47 */         EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  48 */         int haseNum = 0;
/*  49 */         for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/*  50 */           EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*  51 */           ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  52 */           if (equipEntity.getItemId() == rewardBean.getId()) {
/*  53 */             if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/*  54 */               if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/*  55 */                 haseNum++;  continue;
/*     */             } 
/*  57 */             if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  58 */               if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/*  59 */                 haseNum++;  continue;
/*     */             } 
/*  61 */             if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/*  62 */               equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/*  63 */               haseNum++;
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/*  68 */         if (haseNum < rewardBean.getNum()) {
/*  69 */           return 10062;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  75 */     boolean needRefreshBag = false;
/*  76 */     for (RewardBean rewardBean : cost) {
/*  77 */       if (rewardBean.getType() == 1) {
/*  78 */         CurrencyType currencyType = CurrencyType.values()[rewardBean.getId()];
/*  79 */         FinanceUtil.decCurrency(playerSession.getPlayer(), currencyType, rewardBean.getNum(), resourceEvent, true); continue;
/*  80 */       }  if (rewardBean.getType() == 2) {
/*  81 */         bagComponent.deleteItem(rewardBean.getId(), (int)rewardBean.getNum(), resourceEvent);
/*  82 */         needRefreshBag = true; continue;
/*  83 */       }  if (rewardBean.getType() == 9) {
/*  84 */         activityBagComponent.deleteItem(time, rewardBean.getId(), (int)rewardBean.getNum()); continue;
/*  85 */       }  if (rewardBean.getType() == 5) {
/*  86 */         EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  87 */         ArrayList<Long> canDeleteEquip = new ArrayList<>();
/*  88 */         ArrayList<Long> deleteEquip = new ArrayList<>();
/*  89 */         for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/*  90 */           EquipEntity equipEntity = (EquipEntity)iMapEntity;
/*  91 */           ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  92 */           if (equipEntity.getItemId() == rewardBean.getId()) {
/*  93 */             if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/*  94 */               if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/*  95 */                 canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */             } 
/*  97 */             if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  98 */               if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/*  99 */                 canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */             } 
/* 101 */             if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 102 */               equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 103 */               canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 108 */         for (int i = 0; i < rewardBean.getNum(); i++) {
/* 109 */           long mid = ((Long)canDeleteEquip.get(i)).longValue();
/* 110 */           EquipEntity entity = equipComponent.getEquipEntity(mid);
/* 111 */           if (null != entity) {
/* 112 */             deleteEquip.add(Long.valueOf(mid));
/* 113 */             equipComponent.deleteEquip(entity, ResourceEvent.TalismanCost);
/*     */           } 
/*     */         } 
/* 116 */         if (rewardBean.getNum() > 0L) {
/* 117 */           equipComponent.saveAllToDB();
/* 118 */           EquipUtil.deleteEquipNotice(playerSession, deleteEquip);
/*     */         } 
/*     */       } 
/*     */     } 
/* 122 */     if (needRefreshBag) {
/* 123 */       bagComponent.notice();
/*     */     }
/*     */     
/* 126 */     return 0;
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
/*     */   public static short handleCostRewards(ArrayList<Reward> cost, IPlayerSession playerSession, ResourceEvent resourceEvent) {
/* 138 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */ 
/*     */     
/* 141 */     for (Reward reward : cost) {
/* 142 */       short code = check(reward, playerSession, bagComponent);
/* 143 */       if (0 != code) {
/* 144 */         return code;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 149 */     costs(cost, playerSession, bagComponent, resourceEvent);
/*     */     
/* 151 */     return 0;
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
/*     */   public static short handleCostReward(Reward reward, IPlayerSession playerSession, ResourceEvent resourceEvent) {
/* 163 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/*     */ 
/*     */     
/* 166 */     short code = check(reward, playerSession, bagComponent);
/* 167 */     if (0 != code) {
/* 168 */       return code;
/*     */     }
/*     */ 
/*     */     
/* 172 */     cost(reward, playerSession, bagComponent, resourceEvent);
/*     */     
/* 174 */     return 0;
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
/*     */   public static short check(Reward reward, IPlayerSession playerSession, BagComponent bagComponent) {
/* 186 */     if (reward.type == 1) {
/* 187 */       CurrencyType currencyType = CurrencyType.values()[reward.id];
/* 188 */       if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), currencyType, reward.num)) {
/* 189 */         return getErrorCode(currencyType);
/*     */       }
/* 191 */     } else if (reward.type == 2) {
/* 192 */       if (!bagComponent.check(reward.id, reward.num)) {
/* 193 */         return 10062;
/*     */       }
/* 195 */     } else if (reward.type == 9) {
/* 196 */       ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 197 */       int time = TimeUtil.currentTime();
/* 198 */       if (!activityBagComponent.check(time, reward.id, reward.num)) {
/* 199 */         return 10050;
/*     */       }
/* 201 */     } else if (reward.type == 5) {
/* 202 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 203 */       int num = 0;
/* 204 */       for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 205 */         EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 206 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 207 */         if (equipEntity.getItemId() == reward.id) {
/* 208 */           if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/* 209 */             if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/* 210 */               num++;  continue;
/*     */           } 
/* 212 */           if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 213 */             if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/* 214 */               num++;  continue;
/*     */           } 
/* 216 */           if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 217 */             equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 218 */             num++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 223 */       if (num < reward.num) {
/* 224 */         return 10062;
/*     */       }
/*     */     } 
/* 227 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short check(ArrayList<RewardBean> costs, IPlayerSession playerSession, BagComponent bagComponent) {
/* 238 */     return check(costs, 1, playerSession, bagComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short check(ArrayList<RewardBean> costs, int num, IPlayerSession playerSession, BagComponent bagComponent) {
/* 249 */     if (null != costs && !costs.isEmpty()) {
/* 250 */       for (RewardBean cost : costs) {
/* 251 */         if (cost.getType() == 1) {
/* 252 */           CurrencyType currencyType = CurrencyType.values()[cost.getId()];
/* 253 */           if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), currencyType, cost.getNum() * num))
/* 254 */             return getErrorCode(currencyType);  continue;
/*     */         } 
/* 256 */         if (cost.getType() == 2) {
/* 257 */           if (!bagComponent.check(cost.getId(), cost.getNum() * num))
/* 258 */             return 10062;  continue;
/*     */         } 
/* 260 */         if (cost.getType() == 9) {
/* 261 */           ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 262 */           int time = TimeUtil.currentTime();
/* 263 */           if (!activityBagComponent.check(time, cost.getId(), cost.getNum() * num))
/* 264 */             return 10050;  continue;
/*     */         } 
/* 266 */         if (cost.getType() == 5) {
/* 267 */           EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 268 */           int haseNum = 0;
/* 269 */           for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 270 */             EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 271 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 272 */             if (equipEntity.getItemId() == cost.getId()) {
/* 273 */               if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/* 274 */                 if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/* 275 */                   haseNum++;  continue;
/*     */               } 
/* 277 */               if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 278 */                 if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/* 279 */                   haseNum++;  continue;
/*     */               } 
/* 281 */               if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 282 */                 equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 283 */                 haseNum++;
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 288 */           if (haseNum < cost.getNum()) {
/* 289 */             return 10062;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 294 */     return 0;
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
/*     */   public static short checkRewards(ArrayList<Reward> costs, IPlayerSession playerSession, BagComponent bagComponent) {
/* 306 */     if (null != costs && !costs.isEmpty()) {
/* 307 */       for (Reward cost : costs) {
/* 308 */         if (cost.type == 1) {
/* 309 */           CurrencyType currencyType = CurrencyType.values()[cost.id];
/* 310 */           if (!FinanceUtil.checkCurrency(playerSession.getPlayer(), currencyType, cost.num))
/* 311 */             return getErrorCode(currencyType);  continue;
/*     */         } 
/* 313 */         if (cost.type == 2) {
/* 314 */           if (!bagComponent.check(cost.id, cost.num))
/* 315 */             return 10062;  continue;
/*     */         } 
/* 317 */         if (cost.type == 9) {
/* 318 */           ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 319 */           int time = TimeUtil.currentTime();
/* 320 */           if (!activityBagComponent.check(time, cost.id, cost.num))
/* 321 */             return 10050;  continue;
/*     */         } 
/* 323 */         if (cost.type == 5) {
/* 324 */           EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 325 */           int num = 0;
/* 326 */           for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 327 */             EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 328 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 329 */             if (equipEntity.getItemId() == cost.id) {
/* 330 */               if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/* 331 */                 if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/* 332 */                   num++;  continue;
/*     */               } 
/* 334 */               if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 335 */                 if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/* 336 */                   num++;  continue;
/*     */               } 
/* 338 */               if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 339 */                 equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 340 */                 num++;
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 345 */           if (num < cost.num) {
/* 346 */             return 10062;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 351 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cost(ArrayList<RewardBean> costs, IPlayerSession playerSession, BagComponent bagComponent, ResourceEvent resourceEvent) {
/* 362 */     cost(costs, 1, playerSession, bagComponent, resourceEvent);
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
/*     */   public static void cost(ArrayList<RewardBean> costs, int num, IPlayerSession playerSession, BagComponent bagComponent, ResourceEvent resourceEvent) {
/* 374 */     boolean needRefreshBag = false;
/* 375 */     if (null != costs && !costs.isEmpty()) {
/* 376 */       for (RewardBean cost : costs) {
/* 377 */         if (cost.getType() == 1) {
/* 378 */           CurrencyType currencyType = CurrencyType.values()[cost.getId()];
/* 379 */           FinanceUtil.decCurrency(playerSession.getPlayer(), currencyType, cost.getNum() * num, resourceEvent, true); continue;
/* 380 */         }  if (cost.getType() == 2) {
/* 381 */           bagComponent.deleteItem(cost.getId(), (int)(cost.getNum() * num), resourceEvent);
/* 382 */           needRefreshBag = true; continue;
/* 383 */         }  if (cost.getType() == 9) {
/* 384 */           ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 385 */           int time = TimeUtil.currentTime();
/* 386 */           activityBagComponent.deleteItem(time, cost.getId(), (int)(cost.getNum() * num)); continue;
/* 387 */         }  if (cost.getType() == 5) {
/* 388 */           EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 389 */           ArrayList<Long> canDeleteEquip = new ArrayList<>();
/* 390 */           ArrayList<Long> deleteEquip = new ArrayList<>();
/* 391 */           for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 392 */             EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 393 */             ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 394 */             if (equipEntity.getItemId() == cost.getId()) {
/* 395 */               if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/* 396 */                 if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/* 397 */                   canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */               } 
/* 399 */               if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 400 */                 if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/* 401 */                   canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */               } 
/* 403 */               if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 404 */                 equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 405 */                 canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 410 */           for (int i = 0; i < cost.getNum(); i++) {
/* 411 */             long mid = ((Long)canDeleteEquip.get(i)).longValue();
/* 412 */             EquipEntity entity = equipComponent.getEquipEntity(mid);
/* 413 */             if (null != entity) {
/* 414 */               deleteEquip.add(Long.valueOf(mid));
/* 415 */               equipComponent.deleteEquip(entity, ResourceEvent.TalismanCost);
/*     */             } 
/*     */           } 
/* 418 */           if (cost.getNum() > 0L) {
/* 419 */             equipComponent.saveAllToDB();
/* 420 */             EquipUtil.deleteEquipNotice(playerSession, deleteEquip);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 425 */     if (needRefreshBag) {
/* 426 */       bagComponent.notice();
/*     */     }
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
/*     */   public static void cost(Reward reward, IPlayerSession playerSession, BagComponent bagComponent, ResourceEvent resourceEvent) {
/* 439 */     boolean needRefreshBag = false;
/* 440 */     if (reward.type == 1) {
/* 441 */       CurrencyType currencyType = CurrencyType.values()[reward.id];
/* 442 */       FinanceUtil.decCurrency(playerSession.getPlayer(), currencyType, reward.num, resourceEvent, true);
/* 443 */     } else if (reward.type == 2) {
/* 444 */       bagComponent.deleteItem(reward.id, (int)reward.num, resourceEvent);
/* 445 */       needRefreshBag = true;
/* 446 */     } else if (reward.type == 9) {
/* 447 */       ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 448 */       int time = TimeUtil.currentTime();
/* 449 */       activityBagComponent.deleteItem(time, reward.id, (int)reward.num);
/* 450 */     } else if (reward.type == 5) {
/* 451 */       EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 452 */       ArrayList<Long> canDeleteEquip = new ArrayList<>();
/* 453 */       ArrayList<Long> deleteEquip = new ArrayList<>();
/* 454 */       for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 455 */         EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 456 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 457 */         if (equipEntity.getItemId() == reward.id) {
/* 458 */           if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/* 459 */             if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/* 460 */               canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */           } 
/* 462 */           if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 463 */             if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/* 464 */               canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */           } 
/* 466 */           if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 467 */             equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 468 */             canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 473 */       for (int i = 0; i < reward.num; i++) {
/* 474 */         long mid = ((Long)canDeleteEquip.get(i)).longValue();
/* 475 */         EquipEntity entity = equipComponent.getEquipEntity(mid);
/* 476 */         if (null != entity) {
/* 477 */           deleteEquip.add(Long.valueOf(mid));
/* 478 */           equipComponent.deleteEquip(entity, ResourceEvent.TalismanCost);
/*     */         } 
/*     */       } 
/* 481 */       if (reward.num > 0L) {
/* 482 */         equipComponent.saveAllToDB();
/* 483 */         EquipUtil.deleteEquipNotice(playerSession, deleteEquip);
/*     */       } 
/*     */     } 
/* 486 */     if (needRefreshBag) {
/* 487 */       bagComponent.notice();
/*     */     }
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
/*     */   public static void costs(ArrayList<Reward> rewards, IPlayerSession playerSession, BagComponent bagComponent, ResourceEvent resourceEvent) {
/* 500 */     boolean needRefreshBag = false;
/* 501 */     for (Reward reward : rewards) {
/* 502 */       if (reward.type == 1) {
/* 503 */         CurrencyType currencyType = CurrencyType.values()[reward.id];
/* 504 */         FinanceUtil.decCurrency(playerSession.getPlayer(), currencyType, reward.num, resourceEvent, true); continue;
/* 505 */       }  if (reward.type == 2) {
/* 506 */         bagComponent.deleteItem(reward.id, (int)reward.num, resourceEvent);
/* 507 */         needRefreshBag = true; continue;
/* 508 */       }  if (reward.type == 9) {
/* 509 */         ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 510 */         int time = TimeUtil.currentTime();
/* 511 */         activityBagComponent.deleteItem(time, reward.id, (int)reward.num); continue;
/* 512 */       }  if (reward.type == 5) {
/* 513 */         EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/* 514 */         ArrayList<Long> canDeleteEquip = new ArrayList<>();
/* 515 */         ArrayList<Long> deleteEquip = new ArrayList<>();
/* 516 */         for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 517 */           EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 518 */           ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 519 */           if (equipEntity.getItemId() == reward.id) {
/* 520 */             if (EquipPart.HELMET.getPart() <= itemBean.getEquipPart() && itemBean.getEquipPart() <= EquipPart.CLOTH.getPart()) {
/* 521 */               if (equipEntity.getArtificeLevel() <= 0 && equipEntity.getArtificeProcess() <= 0 && equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0 && equipEntity.getZhuLv() <= 0 && EquipUtil.getEquipStonesNum(equipEntity) <= 0 && equipEntity.getStar() <= 0)
/* 522 */                 canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */             } 
/* 524 */             if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/* 525 */               if (equipEntity.getStrengthLv() <= 0 && equipEntity.getIsWear() == 0 && equipEntity.getRefineLv() <= 0)
/* 526 */                 canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));  continue;
/*     */             } 
/* 528 */             if (EquipUtil.isTalisman(equipEntity.getItemId()) && 
/* 529 */               equipEntity.getTalismanRank() <= 1 && equipEntity.getIsWear() == 0) {
/* 530 */               canDeleteEquip.add(Long.valueOf(equipEntity.getMid()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 535 */         for (int i = 0; i < reward.num; i++) {
/* 536 */           long mid = ((Long)canDeleteEquip.get(i)).longValue();
/* 537 */           EquipEntity entity = equipComponent.getEquipEntity(mid);
/* 538 */           if (null != entity) {
/* 539 */             deleteEquip.add(Long.valueOf(mid));
/* 540 */             equipComponent.deleteEquip(entity, ResourceEvent.TalismanCost);
/*     */           } 
/*     */         } 
/* 543 */         if (reward.num > 0L) {
/* 544 */           equipComponent.saveAllToDB();
/* 545 */           EquipUtil.deleteEquipNotice(playerSession, deleteEquip);
/*     */         } 
/*     */       } 
/*     */     } 
/* 549 */     if (needRefreshBag) {
/* 550 */       bagComponent.notice();
/*     */     }
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
/*     */   public static void costOffline(Reward reward, IPlayer iPlayer, PlayerComponent playerComponent, BagComponent bagComponent, ActivityBagComponent activityBagComponent, ResourceEvent resourceEvent, long playerId) {
/* 564 */     if (null != iPlayer && null != iPlayer.getSession()) {
/* 565 */       cost(reward, iPlayer.getSession(), bagComponent, resourceEvent);
/*     */     }
/* 567 */     else if (reward.type == 1) {
/* 568 */       CurrencyType currencyType = CurrencyType.values()[reward.id];
/* 569 */       FinanceUtil.decCurrencyOffline(playerComponent, currencyType, reward.num, resourceEvent, true);
/* 570 */     } else if (reward.type == 2) {
/* 571 */       bagComponent.deleteItem(reward.id, (int)reward.num, resourceEvent);
/* 572 */     } else if (reward.type == 9) {
/* 573 */       int time = TimeUtil.currentTime();
/* 574 */       activityBagComponent.deleteItem(time, reward.id, (int)reward.num);
/*     */     } 
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
/*     */   public static long getNum(IPlayerSession playerSession, RewardBean rewardBean, BagComponent bagComponent) {
/* 588 */     if (rewardBean.getType() == 1) {
/* 589 */       CurrencyType currencyType = CurrencyType.getCurrencyType(rewardBean.getType());
/* 590 */       return (int)FinanceUtil.getPlayerCurrency(playerSession.getPlayer(), currencyType);
/* 591 */     }  if (rewardBean.getType() == 2)
/* 592 */       return bagComponent.getItemNum(rewardBean.getId()); 
/* 593 */     if (rewardBean.getType() == 9) {
/* 594 */       ActivityBagComponent activityBagComponent = (ActivityBagComponent)playerSession.getPlayer().createIfNotExist(ActivityBagComponent.class);
/* 595 */       return activityBagComponent.getItemNum(rewardBean.getId());
/*     */     } 
/* 597 */     return 0L;
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
/*     */   public static short checkAndCost(Reward reward, IPlayerSession playerSession, BagComponent bagComponent, ResourceEvent resourceEvent) {
/* 609 */     short code = check(reward, playerSession, bagComponent);
/* 610 */     if (0 != code) {
/* 611 */       return code;
/*     */     }
/*     */     
/* 614 */     cost(reward, playerSession, bagComponent, resourceEvent);
/* 615 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short getErrorCode(CurrencyType currencyType) {
/* 625 */     if (currencyType == CurrencyType.CCY)
/* 626 */       return 10052; 
/* 627 */     if (currencyType == CurrencyType.CCY_B)
/* 628 */       return 10053; 
/* 629 */     if (currencyType == CurrencyType.COIN)
/* 630 */       return 10051; 
/* 631 */     if (currencyType == CurrencyType.EXP)
/* 632 */       return 10054; 
/* 633 */     if (currencyType == CurrencyType.Arena)
/* 634 */       return 10063; 
/* 635 */     if (currencyType == CurrencyType.Friend)
/* 636 */       return 10064; 
/* 637 */     if (currencyType == CurrencyType.Prestige)
/* 638 */       return 10065; 
/* 639 */     if (currencyType == CurrencyType.Contribute) {
/* 640 */       return 10066;
/*     */     }
/* 642 */     return 10085;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgam\\util\CostUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */