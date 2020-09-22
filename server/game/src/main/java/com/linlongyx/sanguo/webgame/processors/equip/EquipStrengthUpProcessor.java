/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.sanguo.webgame.app.bag.BagComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.util.FinanceUtil;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipStrengthBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasureStrengthBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TreasureSuitBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ResourceEvent;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStrengthUpRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipStrengthUpResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.LongIntValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.Reward;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class EquipStrengthUpProcessor extends ProcessorBase<EquipStrengthUpRequest, EquipStrengthUpResponse> {
/*     */   protected void initResponse() {
/*  35 */     this.response = (ResponseBase)new EquipStrengthUpResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, EquipStrengthUpRequest request) {
/*  40 */     if (!FunctionOpenConstant.isFunctionOpen(playerSession, 801)) {
/*  41 */       return 10061;
/*     */     }
/*  43 */     long pid = request.pid;
/*  44 */     long mid = request.mid;
/*  45 */     int type = request.type;
/*  46 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*     */     
/*  48 */     EquipComponent equipComponent = (EquipComponent)playerSession.getPlayer().createIfNotExist(EquipComponent.class);
/*  49 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  50 */     int levelLimit = playerComponent.getLevel() * 2;
/*  51 */     int TreasurelevelLimit = playerComponent.getLevel();
/*     */     
/*  53 */     Map<Integer, Long> equips = new HashMap<>();
/*  54 */     short code = EquipUtil.getEquips(pid, partnerComponent, playerComponent, equips);
/*  55 */     if (0 != code) {
/*  56 */       return code;
/*     */     }
/*  58 */     Map<Integer, Integer> tip = new HashMap<>();
/*  59 */     if (1 == type) {
/*  60 */       boolean isExist = false;
/*  61 */       for (Map.Entry<Integer, Long> entry : equips.entrySet()) {
/*  62 */         if (((Long)entry.getValue()).longValue() == mid) {
/*  63 */           isExist = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*  67 */       if (!isExist) {
/*  68 */         return 10805;
/*     */       }
/*  70 */       EquipEntity equipEntity = equipComponent.getEquipEntity(mid);
/*  71 */       if (null == equipEntity) {
/*  72 */         return 10801;
/*     */       }
/*  74 */       boolean talisman = EquipUtil.isTalisman(equipEntity.getItemId());
/*  75 */       if (talisman) {
/*  76 */         return 10814;
/*     */       }
/*  78 */       if (EquipUtil.isTreasure(equipEntity.getItemId())) {
/*  79 */         strengthUpTreasureFive(playerSession, equipEntity, TreasurelevelLimit, equipComponent, tip);
/*     */       } else {
/*  81 */         strengthUpEquipFive(playerSession, equipEntity, levelLimit, equipComponent, tip);
/*     */       } 
/*  83 */     } else if (2 == type) {
/*  84 */       List<EquipEntity> list = EquipUtil.getEquipEntityList(equipComponent, equips, false);
/*  85 */       if (list.size() == 0) {
/*  86 */         return 10806;
/*     */       }
/*  88 */       strengthUpEquipOneKey(playerSession, equipComponent, list, levelLimit, tip);
/*  89 */     } else if (3 == type) {
/*  90 */       List<EquipEntity> list = EquipUtil.getEquipEntityList(equipComponent, equips, true);
/*  91 */       if (list.size() == 0) {
/*  92 */         return 10806;
/*     */       }
/*  94 */       strengthUpTreasureOneKey(playerSession, equipComponent, list, TreasurelevelLimit, tip);
/*     */     } else {
/*  96 */       return 10814;
/*     */     } 
/*  98 */     int count = ((Integer)tip.get(Integer.valueOf(2))).intValue();
/*     */     
/* 100 */     if (count > 0) {
/* 101 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 102 */       if (null != taskComponent) {
/* 103 */         taskComponent.refreshSchedule(TaskType.StrengthFighter, 0, 0L);
/* 104 */         taskComponent.refreshSchedule(TaskType.EquipStrengthUp, 0, count);
/*     */       } 
/*     */       
/* 107 */       AttrUpdateUtil.refreshPartnerEquip(playerSession);
/* 108 */       AttrUpdateUtil.refreshPlayerEquip(playerSession);
/* 109 */       AttrUpdateUtil.refreshGrowthGoal(playerSession);
/* 110 */       if (pid != -1L) {
/* 111 */         PartnerAttrUpdate.refreshPartnerEquip(playerSession, pid);
/* 112 */         PartnerAttrUpdate.refreshGrowthGoal(playerSession, pid);
/*     */       } 
/*     */     } else {
/* 115 */       int error = ((Integer)tip.get(Integer.valueOf(1))).intValue();
/* 116 */       return (short)error;
/*     */     } 
/* 118 */     LogUtils.errorLog(new Object[] { "EquipStrength", Long.valueOf(playerSession.getPlayer().getPlayerId()), Long.valueOf(pid), Long.valueOf(mid), Integer.valueOf(type), Integer.valueOf(count) });
/* 119 */     ((EquipStrengthUpResponse)this.response).pid = pid;
/* 120 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void strengthUpEquipFive(IPlayerSession playerSession, EquipEntity equipEntity, int levelLimit, EquipComponent equipComponent, Map<Integer, Integer> tip) {
/* 131 */     int oldLevel = equipEntity.getStrengthLv();
/* 132 */     int strengthLv = oldLevel;
/* 133 */     long myCoin = FinanceUtil.getPlayerCurrency(playerSession.getPlayer(), CurrencyType.COIN);
/* 134 */     long consumeCoin = 0L;
/* 135 */     int code = 0;
/* 136 */     for (int i = 0; i < 5; i++) {
/* 137 */       if (strengthLv >= levelLimit) {
/* 138 */         code = 10808;
/*     */         break;
/*     */       } 
/* 141 */       EquipStrengthBean equipStrengthBean = (EquipStrengthBean)JsonTableService.getJsonData(strengthLv + 1, EquipStrengthBean.class);
/* 142 */       if (null == equipStrengthBean) {
/* 143 */         code = 10803;
/*     */         break;
/*     */       } 
/* 146 */       equipStrengthBean = (EquipStrengthBean)JsonTableService.getJsonData(strengthLv, EquipStrengthBean.class);
/* 147 */       if (null == equipStrengthBean) {
/* 148 */         code = 10802;
/*     */         break;
/*     */       } 
/* 151 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 152 */       if (null == itemBean) {
/* 153 */         code = 10703;
/*     */         break;
/*     */       } 
/* 156 */       EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipSuitBean.class);
/* 157 */       if (null == equipSuitBean) {
/* 158 */         code = 10802;
/*     */         break;
/*     */       } 
/* 161 */       Map<Integer, EquipSuitBean.EquipPartBean> equipPart = equipSuitBean.getEquipPart();
/* 162 */       EquipSuitBean.EquipPartBean equipPartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 163 */       if (null == equipPartBean) {
/* 164 */         code = 10802;
/*     */         break;
/*     */       } 
/* 167 */       int tempCoin = (int)Math.ceil(equipStrengthBean.getCoin() * equipPartBean.getCostCoin() / 10000.0D);
/* 168 */       if (consumeCoin + tempCoin <= myCoin) {
/* 169 */         consumeCoin += tempCoin;
/*     */       } else {
/* 171 */         code = 10050;
/*     */         break;
/*     */       } 
/* 174 */       strengthLv++;
/*     */     } 
/* 176 */     if (strengthLv != oldLevel) {
/* 177 */       FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.COIN, consumeCoin, ResourceEvent.EquipStrengthUp, true);
/* 178 */       equipEntity.setStrengthLv(strengthLv);
/* 179 */       equipComponent.updateStrengthToDB(equipEntity);
/* 180 */       LongIntValue longIntValue = new LongIntValue();
/* 181 */       longIntValue.key = equipEntity.getMid();
/* 182 */       longIntValue.value = strengthLv;
/* 183 */       ((EquipStrengthUpResponse)this.response).equips.add(longIntValue);
/* 184 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 185 */       EquipUtil.EquipDataSys(playerSession, equipData);
/*     */     } 
/* 187 */     tip.put(Integer.valueOf(1), Integer.valueOf(code));
/* 188 */     tip.put(Integer.valueOf(2), Integer.valueOf(strengthLv - oldLevel));
/* 189 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 190 */     if (null != taskComponent) {
/* 191 */       taskComponent.refreshSchedule(TaskType.EquipLevel, 0, strengthLv);
/* 192 */       taskComponent.refreshSchedule(TaskType.StrengthFighter, 0, 0L);
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
/*     */   private void strengthUpTreasureFive(IPlayerSession playerSession, EquipEntity equipEntity, int levelLimit, EquipComponent equipComponent, Map<Integer, Integer> tip) {
/* 204 */     int oldLevel = equipEntity.getStrengthLv();
/* 205 */     int strengthLv = oldLevel;
/* 206 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 207 */     Map<Integer, Integer> cost = new HashMap<>();
/* 208 */     int code = 0;
/* 209 */     for (int i = 0; i < 5; i++) {
/* 210 */       if (strengthLv >= levelLimit) {
/* 211 */         code = 10808;
/*     */         break;
/*     */       } 
/* 214 */       TreasureStrengthBean treasureStrengthBean = (TreasureStrengthBean)JsonTableService.getJsonData(strengthLv + 1, TreasureStrengthBean.class);
/* 215 */       if (null == treasureStrengthBean) {
/* 216 */         code = 10803;
/*     */         break;
/*     */       } 
/* 219 */       treasureStrengthBean = (TreasureStrengthBean)JsonTableService.getJsonData(strengthLv, TreasureStrengthBean.class);
/* 220 */       if (null == treasureStrengthBean) {
/* 221 */         code = 10802;
/*     */         break;
/*     */       } 
/* 224 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 225 */       if (null == itemBean) {
/* 226 */         code = 10703;
/*     */         break;
/*     */       } 
/* 229 */       TreasureSuitBean treasureSuitBean = (TreasureSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), TreasureSuitBean.class);
/* 230 */       if (null == treasureSuitBean) {
/* 231 */         code = 10802;
/*     */         break;
/*     */       } 
/* 234 */       Map<Integer, TreasureSuitBean.TreasurePartBean> equipPart = treasureSuitBean.getTreasurePart();
/* 235 */       TreasureSuitBean.TreasurePartBean treasurePartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 236 */       if (null == treasurePartBean) {
/* 237 */         code = 10802;
/*     */         break;
/*     */       } 
/* 240 */       int tempNum = (int)Math.ceil(treasureStrengthBean.getCostStone() * treasurePartBean.getCostCoin() / 10000.0D);
/* 241 */       tempNum += ((Integer)cost.getOrDefault(Integer.valueOf(treasureStrengthBean.getItemId()), Integer.valueOf(0))).intValue();
/* 242 */       if (bagComponent.check(treasureStrengthBean.getItemId(), tempNum)) {
/* 243 */         cost.put(Integer.valueOf(treasureStrengthBean.getItemId()), Integer.valueOf(tempNum));
/*     */       } else {
/* 245 */         code = 10050;
/*     */         break;
/*     */       } 
/* 248 */       strengthLv++;
/*     */     } 
/*     */     
/* 251 */     if (strengthLv != oldLevel) {
/* 252 */       short error = toCost(playerSession, cost);
/* 253 */       if (0 == error) {
/* 254 */         equipEntity.setStrengthLv(strengthLv);
/* 255 */         equipComponent.updateStrengthToDB(equipEntity);
/* 256 */         LongIntValue longIntValue = new LongIntValue();
/* 257 */         longIntValue.key = equipEntity.getMid();
/* 258 */         longIntValue.value = strengthLv;
/* 259 */         ((EquipStrengthUpResponse)this.response).equips.add(longIntValue);
/* 260 */         EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 261 */         EquipUtil.EquipDataSys(playerSession, equipData);
/*     */       } else {
/* 263 */         code = error;
/*     */       } 
/* 265 */       TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 266 */       if (null != taskComponent) {
/* 267 */         taskComponent.refreshSchedule(TaskType.TreasureStrenth, 0, (strengthLv - oldLevel));
/*     */       }
/*     */     } 
/* 270 */     tip.put(Integer.valueOf(1), Integer.valueOf(code));
/* 271 */     tip.put(Integer.valueOf(2), Integer.valueOf(strengthLv - oldLevel));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void strengthUpEquipOneKey(IPlayerSession playerSession, EquipComponent equipComponent, List<EquipEntity> list, int levelLimit, Map<Integer, Integer> tip) {
/*     */     int minLevel, code;
/* 283 */     Map<Long, Integer> strengthUpEquip = new HashMap<>();
/* 284 */     Map<Long, Integer> midItemIds = new HashMap<>();
/* 285 */     for (EquipEntity equipEntity : list) {
/* 286 */       strengthUpEquip.put(Long.valueOf(equipEntity.getMid()), Integer.valueOf(equipEntity.getStrengthLv()));
/* 287 */       midItemIds.put(Long.valueOf(equipEntity.getMid()), Integer.valueOf(equipEntity.getItemId()));
/*     */     } 
/* 289 */     long myCoin = FinanceUtil.getPlayerCurrency(playerSession.getPlayer(), CurrencyType.COIN);
/* 290 */     long consumeCoin = 0L;
/* 291 */     Set<Long> dirtySet = new HashSet<>();
/* 292 */     int count = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     while (true) {
/* 297 */       long minMid = 0L;
/* 298 */       minLevel = Integer.MAX_VALUE;
/* 299 */       for (Map.Entry<Long, Integer> entry : strengthUpEquip.entrySet()) {
/* 300 */         if (((Integer)entry.getValue()).intValue() < minLevel) {
/* 301 */           minLevel = ((Integer)entry.getValue()).intValue();
/* 302 */           minMid = ((Long)entry.getKey()).longValue();
/*     */         } 
/*     */       } 
/* 305 */       if (0L == minMid) {
/* 306 */         code = 10804;
/*     */         break;
/*     */       } 
/* 309 */       if (minLevel >= levelLimit) {
/* 310 */         code = 10808;
/*     */         break;
/*     */       } 
/* 313 */       EquipStrengthBean equipStrengthBean = (EquipStrengthBean)JsonTableService.getJsonData(minLevel + 1, EquipStrengthBean.class);
/* 314 */       if (null == equipStrengthBean) {
/* 315 */         code = 10803;
/*     */         break;
/*     */       } 
/* 318 */       equipStrengthBean = (EquipStrengthBean)JsonTableService.getJsonData(minLevel, EquipStrengthBean.class);
/* 319 */       if (null == equipStrengthBean) {
/* 320 */         code = 10802;
/*     */         break;
/*     */       } 
/* 323 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(((Integer)midItemIds.getOrDefault(Long.valueOf(minMid), Integer.valueOf(0))).intValue(), ItemBean.class);
/* 324 */       if (null == itemBean) {
/* 325 */         code = 10703;
/*     */         break;
/*     */       } 
/* 328 */       EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipSuitBean.class);
/* 329 */       if (null == equipSuitBean) {
/* 330 */         code = 10802;
/*     */         break;
/*     */       } 
/* 333 */       Map<Integer, EquipSuitBean.EquipPartBean> equipPart = equipSuitBean.getEquipPart();
/* 334 */       EquipSuitBean.EquipPartBean equipPartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 335 */       if (null == equipPartBean) {
/* 336 */         code = 10802;
/*     */         break;
/*     */       } 
/* 339 */       int tempCoin = (int)Math.ceil(equipStrengthBean.getCoin() * equipPartBean.getCostCoin() / 10000.0D);
/* 340 */       if (consumeCoin + tempCoin <= myCoin) {
/* 341 */         consumeCoin += tempCoin;
/*     */       } else {
/* 343 */         code = 10050;
/*     */         break;
/*     */       } 
/* 346 */       strengthUpEquip.put(Long.valueOf(minMid), Integer.valueOf(minLevel + 1));
/* 347 */       dirtySet.add(Long.valueOf(minMid));
/* 348 */       count++;
/*     */     } 
/* 350 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 351 */     if (null != taskComponent) {
/* 352 */       taskComponent.refreshSchedule(TaskType.EquipLevel, 0, (minLevel + count));
/* 353 */       taskComponent.refreshSchedule(TaskType.StrengthFighter, 0, 0L);
/*     */     } 
/* 355 */     FinanceUtil.decCurrency(playerSession.getPlayer(), CurrencyType.COIN, consumeCoin, ResourceEvent.EquipStrengthUp, true);
/*     */     
/* 357 */     updateEquip(dirtySet, equipComponent, strengthUpEquip);
/*     */     
/* 359 */     tip.put(Integer.valueOf(1), Integer.valueOf(code));
/* 360 */     tip.put(Integer.valueOf(2), Integer.valueOf(count));
/* 361 */     for (EquipEntity equipEntity : list) {
/* 362 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 363 */       EquipUtil.EquipDataSys(playerSession, equipData);
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
/*     */   private void strengthUpTreasureOneKey(IPlayerSession playerSession, EquipComponent equipComponent, List<EquipEntity> list, int levelLimit, Map<Integer, Integer> tip) {
/*     */     int code;
/* 376 */     Map<Long, Integer> strengthUpEquip = new HashMap<>();
/* 377 */     Map<Long, Integer> midItemIds = new HashMap<>();
/* 378 */     for (EquipEntity equipEntity : list) {
/* 379 */       strengthUpEquip.put(Long.valueOf(equipEntity.getMid()), Integer.valueOf(equipEntity.getStrengthLv()));
/* 380 */       midItemIds.put(Long.valueOf(equipEntity.getMid()), Integer.valueOf(equipEntity.getItemId()));
/*     */     } 
/* 382 */     BagComponent bagComponent = (BagComponent)playerSession.getPlayer().createIfNotExist(BagComponent.class);
/* 383 */     Set<Long> dirtySet = new HashSet<>();
/* 384 */     int count = 0;
/*     */ 
/*     */     
/* 387 */     Map<Integer, Integer> cost = new HashMap<>();
/*     */     
/*     */     while (true) {
/* 390 */       long minMid = 0L;
/* 391 */       int minLevel = Integer.MAX_VALUE;
/* 392 */       for (Map.Entry<Long, Integer> entry : strengthUpEquip.entrySet()) {
/* 393 */         if (((Integer)entry.getValue()).intValue() < minLevel) {
/* 394 */           minLevel = ((Integer)entry.getValue()).intValue();
/* 395 */           minMid = ((Long)entry.getKey()).longValue();
/*     */         } 
/*     */       } 
/* 398 */       if (0L == minMid) {
/* 399 */         code = 10804;
/*     */         break;
/*     */       } 
/* 402 */       if (minLevel >= levelLimit) {
/* 403 */         code = 10808;
/*     */         break;
/*     */       } 
/* 406 */       TreasureStrengthBean treasureStrengthBean = (TreasureStrengthBean)JsonTableService.getJsonData(minLevel + 1, TreasureStrengthBean.class);
/* 407 */       if (null == treasureStrengthBean) {
/* 408 */         code = 10803;
/*     */         break;
/*     */       } 
/* 411 */       treasureStrengthBean = (TreasureStrengthBean)JsonTableService.getJsonData(minLevel, TreasureStrengthBean.class);
/* 412 */       if (null == treasureStrengthBean) {
/* 413 */         code = 10802;
/*     */         break;
/*     */       } 
/* 416 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(((Integer)midItemIds.getOrDefault(Long.valueOf(minMid), Integer.valueOf(0))).intValue(), ItemBean.class);
/* 417 */       if (null == itemBean) {
/* 418 */         code = 10703;
/*     */         break;
/*     */       } 
/* 421 */       TreasureSuitBean treasureSuitBean = (TreasureSuitBean)JsonTableService.getJsonData(itemBean.getSuitType(), TreasureSuitBean.class);
/* 422 */       if (null == treasureSuitBean) {
/* 423 */         code = 10802;
/*     */         break;
/*     */       } 
/* 426 */       Map<Integer, TreasureSuitBean.TreasurePartBean> equipPart = treasureSuitBean.getTreasurePart();
/* 427 */       TreasureSuitBean.TreasurePartBean treasurePartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/* 428 */       if (null == treasurePartBean) {
/* 429 */         code = 10802;
/*     */         break;
/*     */       } 
/* 432 */       int tempNum = (int)Math.ceil(treasureStrengthBean.getCostStone() * treasurePartBean.getCostCoin() / 10000.0D);
/* 433 */       tempNum += ((Integer)cost.getOrDefault(Integer.valueOf(treasureStrengthBean.getItemId()), Integer.valueOf(0))).intValue();
/* 434 */       if (bagComponent.check(treasureStrengthBean.getItemId(), tempNum)) {
/* 435 */         cost.put(Integer.valueOf(treasureStrengthBean.getItemId()), Integer.valueOf(tempNum));
/*     */       } else {
/* 437 */         code = 10050;
/*     */         break;
/*     */       } 
/* 440 */       strengthUpEquip.put(Long.valueOf(minMid), Integer.valueOf(minLevel + 1));
/* 441 */       dirtySet.add(Long.valueOf(minMid));
/* 442 */       count++;
/*     */     } 
/*     */     
/* 445 */     short error = toCost(playerSession, cost);
/* 446 */     if (0 == error) {
/* 447 */       updateEquip(dirtySet, equipComponent, strengthUpEquip);
/*     */     } else {
/* 449 */       code = error;
/*     */     } 
/*     */     
/* 452 */     tip.put(Integer.valueOf(1), Integer.valueOf(code));
/* 453 */     tip.put(Integer.valueOf(2), Integer.valueOf(count));
/* 454 */     for (EquipEntity equipEntity : list) {
/* 455 */       EquipData equipData = EquipUtil.getEquipData(equipEntity);
/* 456 */       EquipUtil.EquipDataSys(playerSession, equipData);
/*     */     } 
/* 458 */     TaskComponent taskComponent = (TaskComponent)playerSession.getPlayer().createIfNotExist(TaskComponent.class);
/* 459 */     if (null != taskComponent) {
/* 460 */       taskComponent.refreshSchedule(TaskType.TreasureStrenth, 0, count);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private short toCost(IPlayerSession playerSession, Map<Integer, Integer> cost) {
/* 471 */     ArrayList<Reward> costs = new ArrayList<>();
/* 472 */     for (Map.Entry<Integer, Integer> entry : cost.entrySet()) {
/* 473 */       Reward reward = new Reward();
/* 474 */       ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), ItemBean.class);
/* 475 */       if (null != itemBean) {
/* 476 */         reward.type = (byte)itemBean.getItemType();
/* 477 */         reward.id = ((Integer)entry.getKey()).intValue();
/* 478 */         reward.num = ((Integer)entry.getValue()).intValue();
/* 479 */         costs.add(reward);
/*     */       } 
/*     */     } 
/* 482 */     short code = CostUtil.handleCostRewards(costs, playerSession, ResourceEvent.EquipStrengthUp);
/* 483 */     return code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateEquip(Set<Long> dirtySet, EquipComponent equipComponent, Map<Long, Integer> strengthUpEquip) {
/* 493 */     int maxLv = 0;
/* 494 */     for (Long mid : dirtySet) {
/* 495 */       EquipEntity equipEntity = equipComponent.getEquipEntity(mid.longValue());
/* 496 */       if (null != equipEntity && strengthUpEquip.containsKey(mid)) {
/* 497 */         int strengthLv = ((Integer)strengthUpEquip.get(mid)).intValue();
/* 498 */         equipEntity.setStrengthLv(strengthLv);
/* 499 */         equipComponent.updateStrengthToDB(equipEntity);
/* 500 */         LongIntValue longIntValue = new LongIntValue();
/* 501 */         longIntValue.key = equipEntity.getMid();
/* 502 */         longIntValue.value = strengthLv;
/* 503 */         ((EquipStrengthUpResponse)this.response).equips.add(longIntValue);
/* 504 */         if (strengthLv > maxLv)
/* 505 */           maxLv = strengthLv; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipStrengthUpProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */