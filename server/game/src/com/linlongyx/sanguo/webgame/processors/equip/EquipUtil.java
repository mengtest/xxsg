/*     */ package com.linlongyx.sanguo.webgame.processors.equip;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.EquipFightValue;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.EquipStarBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.MasterForgingBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TalismanLevelBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.EquipParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.EquipPart;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.DeleteEquipNoticeResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.equip.EquipDataSysResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EquipData;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class EquipUtil
/*     */ {
/*     */   public static final int STONE_MIN_INDEX = 1;
/*     */   public static final int STONE_MAX_INDEX = 4;
/*     */   public static final int TIP_CODE = 1;
/*     */   public static final int TIP_LEVEL = 2;
/*     */   public static final int TYPE_STRENGTH = 1;
/*     */   public static final int TYPE_PURIFY = 2;
/*     */   public static final int TYPE_STONE = 3;
/*     */   public static final int TYPE_ZHUHUN = 4;
/*     */   public static final int TYPE_ARTIFICE = 5;
/*     */   public static final int TYPE_STAR = 6;
/*     */   public static final int TALISMAN_PART = 7;
/*     */   
/*     */   public static short getEquips(long pid, PartnerComponent partnerComponent, PlayerComponent playerComponent, Map<Integer, Long> equips) {
/*  60 */     if (pid != -1L) {
/*  61 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  62 */       if (null == partnerEntity) {
/*  63 */         return 13303;
/*     */       }
/*  65 */       equips.putAll(partnerEntity.getEquips());
/*     */     } else {
/*  67 */       equips.putAll(playerComponent.getEquips());
/*     */     } 
/*  69 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTreasure(int itemId) {
/*  78 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  79 */     if (null == itemBean) {
/*  80 */       return false;
/*     */     }
/*  82 */     return (itemBean.getEquipPart() == 5 || itemBean.getEquipPart() == 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isTalisman(int itemId) {
/*  92 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(itemId, ItemBean.class);
/*  93 */     if (null == itemBean) {
/*  94 */       return false;
/*     */     }
/*  96 */     return (itemBean.getEquipPart() == 7);
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
/*     */   public static List<EquipEntity> getEquipEntityList(EquipComponent equipComponent, Map<Integer, Long> equips, boolean isTreasure) {
/* 108 */     List<EquipEntity> list = new ArrayList<>();
/* 109 */     for (Map.Entry<Integer, Long> entry : equips.entrySet()) {
/* 110 */       EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/* 111 */       if (null != equipEntity) {
/* 112 */         boolean flag = isTreasure(equipEntity.getItemId());
/* 113 */         if (isTreasure) {
/* 114 */           if (flag)
/* 115 */             list.add(equipEntity); 
/*     */           continue;
/*     */         } 
/* 118 */         if (!flag) {
/* 119 */           list.add(equipEntity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EquipData getEquipData(EquipEntity equipEntity) {
/* 134 */     EquipData equipData = new EquipData();
/* 135 */     equipData.mid = equipEntity.getMid();
/* 136 */     equipData.itemId = equipEntity.getItemId();
/* 137 */     equipData.strengthLv = equipEntity.getStrengthLv();
/* 138 */     equipData.quaity = equipEntity.getQuaity();
/* 139 */     equipData.refineLv = equipEntity.getRefineLv();
/* 140 */     equipData.zhuLv = equipEntity.getZhuLv();
/* 141 */     equipData.artificeLevel = equipEntity.getArtificeLevel();
/* 142 */     equipData.artificeProcess = equipEntity.getArtificeProcess();
/* 143 */     equipData.artificeLucky = equipEntity.getArtificeLucky();
/* 144 */     equipData.fightValue = (equipEntity.getFightValue() == 0L) ? updateEquipFightValue(equipEntity) : equipEntity.getFightValue();
/* 145 */     Map<Integer, Integer> stones = equipEntity.getStones();
/* 146 */     if (!isTreasure(equipEntity.getItemId()) && stones.isEmpty()) {
/* 147 */       for (int i = 1; i <= 4; i++) {
/* 148 */         equipEntity.getStones().put(Integer.valueOf(i), Integer.valueOf(0));
/*     */       }
/*     */     }
/* 151 */     if (!isTreasure(equipEntity.getItemId())) {
/* 152 */       for (int i = 1; i <= 4; i++) {
/* 153 */         IntIntValue intIntValue = new IntIntValue();
/* 154 */         intIntValue.key = i;
/* 155 */         intIntValue.value = ((Integer)stones.getOrDefault(Integer.valueOf(i), Integer.valueOf(0))).intValue();
/* 156 */         equipData.stones.add(intIntValue);
/*     */       } 
/*     */     }
/* 159 */     equipData.isWear = equipEntity.getIsWear();
/* 160 */     equipData.talismanRank = equipEntity.getTalismanRank();
/* 161 */     equipData.belondTo = equipEntity.getBelondTo();
/* 162 */     equipData.star = equipEntity.getStar();
/* 163 */     return equipData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEquipStonesNum(EquipEntity equipEntity) {
/* 173 */     int num = 0;
/* 174 */     for (Iterator<Integer> iterator = equipEntity.getStones().values().iterator(); iterator.hasNext(); ) { int level = ((Integer)iterator.next()).intValue();
/* 175 */       if (level > 0) {
/* 176 */         num++;
/*     */       } }
/*     */     
/* 179 */     return num;
/*     */   }
/*     */   
/*     */   public static void EquipDataSys(IPlayerSession playerSession, EquipData equipData) {
/* 183 */     EquipDataSysResponse response = new EquipDataSysResponse();
/* 184 */     response.equipData = equipData;
/* 185 */     playerSession.sendMessage((ResponseBase)response);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getMaxFightValue(long playerId) {
/* 195 */     PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/* 196 */     EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/* 197 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/* 198 */     long maxPower = 0L;
/* 199 */     if (null != partnerComponent && null != playerComponent && null != equipComponent) {
/* 200 */       ArrayList<Long> fighters = playerComponent.getFighters();
/* 201 */       for (Long id : fighters) {
/* 202 */         if (id.longValue() == -1L) {
/* 203 */           Map<Integer, Long> playerEquips = playerComponent.getEquips();
/* 204 */           for (Map.Entry<Integer, Long> entry : playerEquips.entrySet()) {
/* 205 */             EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/* 206 */             if (null != equipEntity && equipEntity.getFightValue() > maxPower)
/* 207 */               maxPower = equipEntity.getFightValue(); 
/*     */           } 
/*     */           continue;
/*     */         } 
/* 211 */         PartnerEntity partnerEntity = partnerComponent.getEntity(id.longValue());
/* 212 */         if (null == partnerEntity) {
/*     */           continue;
/*     */         }
/* 215 */         Map<Integer, Long> equips = partnerEntity.getEquips();
/* 216 */         for (Map.Entry<Integer, Long> entry : equips.entrySet()) {
/* 217 */           EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)entry.getValue()).longValue());
/* 218 */           if (null != equipEntity && equipEntity.getFightValue() > maxPower) {
/* 219 */             maxPower = equipEntity.getFightValue();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     return maxPower;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long updateEquipFightValue(EquipEntity equipEntity) {
/* 234 */     EquipFightValue equipFightValue = new EquipFightValue(0, false);
/* 235 */     long total = equipFightValue.getEquipFightValue(equipEntity);
/* 236 */     equipEntity.setFightValue(total);
/* 237 */     return equipEntity.getFightValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static MasterForgingBean getEquipStrengthGoal(EquipComponent equipComponent, Map<Integer, Long> equipMap, int type) {
/* 242 */     int level = 100000;
/* 243 */     for (Iterator<Integer> iterator = equipMap.keySet().iterator(); iterator.hasNext(); ) { int part = ((Integer)iterator.next()).intValue();
/* 244 */       if (part == EquipPart.RING.getPart() || part == EquipPart.WRISTER.getPart()) {
/*     */         continue;
/*     */       }
/* 247 */       EquipEntity equipEntity = equipComponent.getEquipEntity(((Long)equipMap.get(Integer.valueOf(part))).longValue());
/* 248 */       if (null == equipEntity) {
/* 249 */         level = 0;
/*     */         break;
/*     */       } 
/* 252 */       if (type == 1 && equipEntity.getStrengthLv() < level) {
/* 253 */         level = equipEntity.getStrengthLv();
/*     */       }
/* 255 */       if (type == 2 && equipEntity.getRefineLv() < level) {
/* 256 */         level = equipEntity.getRefineLv();
/*     */       }
/* 258 */       if (type == 4 && equipEntity.getZhuLv() < level) {
/* 259 */         level = equipEntity.getZhuLv();
/*     */       }
/* 261 */       if (type == 5 && equipEntity.getArtificeLevel() < level) {
/* 262 */         level = equipEntity.getArtificeLevel();
/*     */       }
/* 264 */       if (type == 6 && equipEntity.getStar() < level) {
/* 265 */         level = equipEntity.getStar();
/*     */       }
/* 267 */       if (type == 3) {
/* 268 */         int stoneMinLevel = getStoneMinLevel(equipEntity);
/* 269 */         if (stoneMinLevel < level) {
/* 270 */           level = stoneMinLevel;
/*     */         }
/*     */       }  }
/*     */     
/* 274 */     if (level == 100000) {
/* 275 */       return null;
/*     */     }
/* 277 */     return getFormGoalsBean(level, type);
/*     */   }
/*     */   
/*     */   private static MasterForgingBean getFormGoalsBean(int typeLevel, int type) {
/* 281 */     EquipParameter parameter = (EquipParameter)ParameterConstant.getParameter(78);
/* 282 */     Map<Integer, MasterForgingBean> map = parameter.getGoals(type);
/* 283 */     if (null == map) {
/* 284 */       return null;
/*     */     }
/* 286 */     int selectLevel = getLevel(new ArrayList<>(map.keySet()), typeLevel);
/*     */     
/* 288 */     return map.get(Integer.valueOf(selectLevel));
/*     */   }
/*     */   
/*     */   private static int getLevel(ArrayList<Integer> list, int level) {
/* 292 */     Collections.sort(list);
/* 293 */     int selectLv = -1;
/* 294 */     for (Integer lv : list) {
/* 295 */       if (level >= lv.intValue()) {
/* 296 */         selectLv = lv.intValue();
/*     */       }
/*     */     } 
/* 299 */     return selectLv;
/*     */   }
/*     */   
/*     */   private static int getStoneMinLevel(EquipEntity equipEntity) {
/* 303 */     int level = 100000;
/* 304 */     if (equipEntity.getStones().isEmpty()) {
/* 305 */       for (int i = 1; i <= 4; i++) {
/* 306 */         equipEntity.getStones().put(Integer.valueOf(i), Integer.valueOf(0));
/*     */       }
/*     */     }
/* 309 */     for (Iterator<Integer> iterator = equipEntity.getStones().values().iterator(); iterator.hasNext(); ) { int lv = ((Integer)iterator.next()).intValue();
/* 310 */       if (level > lv) {
/* 311 */         level = lv;
/*     */       } }
/*     */     
/* 314 */     return level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getEquipMaxFightValue(EquipComponent equipComponent) {
/* 323 */     long fightValue = 0L;
/* 324 */     for (IMapEntity iMapEntity : equipComponent.getEntityMap().values()) {
/* 325 */       EquipEntity equipEntity = (EquipEntity)iMapEntity;
/* 326 */       if (fightValue < equipEntity.getFightValue()) {
/* 327 */         fightValue = equipEntity.getFightValue();
/*     */       }
/*     */     } 
/* 330 */     return fightValue;
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
/*     */   public static HashMap<Long, HashMap<Integer, Integer>> getTalismanMap(long playerId) {
/* 372 */     HashMap<Long, HashMap<Integer, Integer>> map = new HashMap<>();
/* 373 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 374 */     EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/* 375 */     if (playerComponent == null || equipComponent == null) {
/* 376 */       return map;
/*     */     }
/* 378 */     if (playerComponent.getTalisman() != 0L) {
/* 379 */       HashMap<Integer, Integer> effectMap = new HashMap<>();
/* 380 */       EquipEntity equipEntity = equipComponent.getEquipEntity(playerComponent.getTalisman());
/* 381 */       TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(equipEntity.getItemId(), TalismanLevelBean.class);
/* 382 */       if (null != talismanLevelBean) {
/* 383 */         TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(equipEntity.getTalismanRank()));
/* 384 */         for (TalismanLevelBean.LvBean.TalentsBean attrBean : lvBean.getTalents()) {
/* 385 */           effectMap.put(Integer.valueOf(attrBean.getOld()), Integer.valueOf(attrBean.getFresh()));
/*     */         }
/*     */       } 
/* 388 */       map.put(Long.valueOf(-1L), effectMap);
/*     */     } 
/* 390 */     PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/* 391 */     if (partnerComponent == null) {
/* 392 */       return map;
/*     */     }
/* 394 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 395 */       if (pid == -1L) {
/*     */         continue;
/*     */       }
/* 398 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 399 */       if (null != partnerEntity && partnerEntity.getTalisman() != 0L) {
/* 400 */         HashMap<Integer, Integer> effectMap = new HashMap<>();
/* 401 */         EquipEntity equipEntity = equipComponent.getEquipEntity(partnerEntity.getTalisman());
/* 402 */         if (null == equipEntity) {
/* 403 */           partnerEntity.setTalisman(0L);
/*     */           continue;
/*     */         } 
/* 406 */         TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(equipEntity.getItemId(), TalismanLevelBean.class);
/* 407 */         if (null != talismanLevelBean) {
/* 408 */           TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(equipEntity.getTalismanRank()));
/* 409 */           for (TalismanLevelBean.LvBean.TalentsBean attrBean : lvBean.getTalents()) {
/* 410 */             effectMap.put(Integer.valueOf(attrBean.getOld()), Integer.valueOf(attrBean.getFresh()));
/*     */           }
/*     */         } 
/*     */         
/* 414 */         map.put(Long.valueOf(partnerEntity.getPid()), effectMap);
/*     */       }  }
/*     */     
/* 417 */     return map;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<Integer, Integer> getTalismanByPid(long playerId, long pid) {
/* 422 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 423 */     EquipComponent equipComponent = (EquipComponent)LookUpService.getComponent(playerId, EquipComponent.class);
/* 424 */     if (playerComponent == null || equipComponent == null) {
/* 425 */       return null;
/*     */     }
/* 427 */     if (pid == -1L) {
/* 428 */       if (playerComponent.getTalisman() != 0L) {
/* 429 */         EquipEntity equipEntity = equipComponent.getEquipEntity(playerComponent.getTalisman());
/* 430 */         return getTalismanEffectMap(equipEntity.getItemId(), equipEntity.getTalismanRank());
/*     */       } 
/*     */     } else {
/* 433 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/* 434 */       if (partnerComponent != null) {
/* 435 */         PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 436 */         if (null != partnerEntity && partnerEntity.getTalisman() != 0L) {
/* 437 */           EquipEntity equipEntity = equipComponent.getEquipEntity(partnerEntity.getTalisman());
/* 438 */           return getTalismanEffectMap(equipEntity.getItemId(), equipEntity.getTalismanRank());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 443 */     return null;
/*     */   }
/*     */   
/*     */   private static Map<Integer, Integer> getTalismanEffectMap(int itemId, int talismanRank) {
/* 447 */     Map<Integer, Integer> map = new HashMap<>();
/* 448 */     TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(itemId, TalismanLevelBean.class);
/* 449 */     if (null != talismanLevelBean) {
/* 450 */       TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(talismanRank));
/* 451 */       for (TalismanLevelBean.LvBean.TalentsBean attrBean : lvBean.getTalents()) {
/* 452 */         map.put(Integer.valueOf(attrBean.getOld()), Integer.valueOf(attrBean.getFresh()));
/*     */       }
/*     */     } 
/* 455 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deleteEquipNotice(IPlayerSession playerSession, ArrayList<Long> deteleEquip) {
/* 464 */     DeleteEquipNoticeResponse response = new DeleteEquipNoticeResponse();
/* 465 */     response.deleteList = new ArrayList<>(deteleEquip);
/* 466 */     playerSession.sendMessage((ResponseBase)response);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EquipStarBean.StarBean getEquipStarGoalth(EquipComponent equipComponent, Map<Integer, Long> equipMap) {
/* 471 */     EquipStarBean.StarBean starBean2 = null;
/* 472 */     Map<Integer, Integer> suitNum = new HashMap<>();
/* 473 */     int star = 99999;
/* 474 */     for (Iterator<Long> iterator = equipMap.values().iterator(); iterator.hasNext(); ) { long mid = ((Long)iterator.next()).longValue();
/* 475 */       EquipEntity equipEntity = equipComponent.getEquipEntity(mid);
/* 476 */       if (null != equipEntity) {
/* 477 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/* 478 */         if (null == itemBean) {
/*     */           continue;
/*     */         }
/* 481 */         int i = itemBean.getSuitType();
/* 482 */         boolean isTreasure = isTreasure(itemBean.getId());
/* 483 */         if (!isTreasure) {
/* 484 */           suitNum.put(Integer.valueOf(i), Integer.valueOf(((Integer)suitNum.getOrDefault(Integer.valueOf(i), Integer.valueOf(0))).intValue() + 1));
/* 485 */           if (equipEntity.getStar() < star) {
/* 486 */             star = equipEntity.getStar();
/*     */           }
/*     */         } 
/*     */       }  }
/*     */     
/* 491 */     int num = 0;
/* 492 */     int suitType = 0;
/* 493 */     for (Map.Entry<Integer, Integer> entry : suitNum.entrySet()) {
/* 494 */       if (((Integer)entry.getValue()).intValue() > num) {
/* 495 */         num = ((Integer)entry.getValue()).intValue();
/* 496 */         suitType = ((Integer)entry.getKey()).intValue();
/*     */       } 
/*     */     } 
/* 499 */     if (num >= 4) {
/* 500 */       EquipStarBean starBean = (EquipStarBean)JsonTableService.getJsonData(suitType, EquipStarBean.class);
/* 501 */       if (null != starBean) {
/* 502 */         EquipStarBean.StarBean starBean1 = (EquipStarBean.StarBean)starBean.getStar().get(Integer.valueOf(star));
/* 503 */         if (null != starBean1) {
/* 504 */           starBean2 = starBean1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 509 */     return starBean2;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\EquipUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */