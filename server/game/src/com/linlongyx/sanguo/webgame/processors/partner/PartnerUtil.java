/*     */ package com.linlongyx.sanguo.webgame.processors.partner;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterReincarnBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PartnerLvBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PartnerPrimBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.RelationBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.TaskTypeBean;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.LoginParameter;
/*     */ import com.linlongyx.sanguo.webgame.config.parameter.PartnerParameter;
/*     */ import com.linlongyx.sanguo.webgame.constant.CurrencyType;
/*     */ import com.linlongyx.sanguo.webgame.constant.ParameterConstant;
/*     */ import com.linlongyx.sanguo.webgame.constant.TaskType;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.task.TaskUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.partner.PushPartnerInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PartnerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class PartnerUtil
/*     */ {
/*     */   public static ArrayList<Integer> getRelationMap(ArrayList<Integer> partnerList, ArrayList<Integer> equipList, ArrayList<FighterBean> fighterBeans) {
/*  49 */     ArrayList<Integer> relationList = new ArrayList<>();
/*  50 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RelationBean.class);
/*  51 */     map.values().forEach(o -> {
/*     */           RelationBean relationBean = (RelationBean)o;
/*     */           boolean hadRelation = true;
/*     */           for (Integer partnerId : relationBean.getPara()) {
/*     */             if (relationBean.getType() == 1) {
/*     */               if (partnerList.indexOf(partnerId) < 0) {
/*     */                 hadRelation = false;
/*     */                 break;
/*     */               } 
/*     */               continue;
/*     */             } 
/*     */             for (FighterBean fighterBean : fighterBeans) {
/*     */               label23: for (Integer relation : fighterBean.getRelation()) {
/*     */                 if (relation.intValue() == relationBean.getId()) {
/*     */                   if (equipList.indexOf(partnerId) < 0) {
/*     */                     break label23;
/*     */                   }
/*     */                   continue;
/*     */                 } 
/*     */                 hadRelation = false;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           if (hadRelation) {
/*     */             relationList.add(Integer.valueOf(relationBean.getId()));
/*     */           }
/*     */         });
/*  78 */     return relationList;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateMaxSize(IPlayer player) {
/*  83 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  84 */     ExtendComponent extendComponent = (ExtendComponent)player.createIfNotExist(ExtendComponent.class);
/*  85 */     LoginParameter loginParameter = (LoginParameter)ParameterConstant.getParameter(0);
/*  86 */     int max = loginParameter.getBattleMaxCount();
/*  87 */     extendComponent.setBattleMaxCount(max);
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
/*     */   public static int updateDesLv(PartnerEntity partnerEntity, PartnerComponent partnerComponent, IPlayerSession playerSession) {
/*  99 */     int desLv = partnerEntity.getDesLv();
/* 100 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     return desLv;
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
/*     */   public static void addPartnerExp(int addExp, IPlayerSession playerSession, PartnerComponent partnerComponent, PartnerEntity partnerEntity) {
/* 120 */     long aExp = partnerEntity.getExp() + addExp;
/* 121 */     int oldLevel = partnerEntity.getLevel();
/* 122 */     int level = oldLevel;
/* 123 */     PartnerLvBean partnerLvBean = (PartnerLvBean)JsonTableService.getJsonData(level, PartnerLvBean.class);
/* 124 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/* 125 */     while (aExp >= partnerLvBean.getExp() && partnerLvBean.getExp() != 0) {
/* 126 */       aExp -= partnerLvBean.getExp();
/* 127 */       level++;
/* 128 */       partnerLvBean = (PartnerLvBean)JsonTableService.getJsonData(level, PartnerLvBean.class);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     PlayerComponent playerComponent = (PlayerComponent)playerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 136 */     if (level > playerComponent.getLevel()) {
/* 137 */       level = Math.max(oldLevel, playerComponent.getLevel());
/*     */     }
/* 139 */     partnerEntity.setLevel(level);
/* 140 */     partnerEntity.setExp(aExp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Integer, Integer> getAttAddParam(PartnerComponent partnerComponent) {
/* 150 */     Map<Integer, Integer> map = new HashMap<>();
/* 151 */     if (null == partnerComponent) {
/* 152 */       return map;
/*     */     }
/* 154 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(partnerComponent.getPlayerId());
/* 155 */     if (null == playerComponent) {
/* 156 */       return map;
/*     */     }
/* 158 */     ArrayList<Long> followers = playerComponent.getFighters();
/* 159 */     partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*     */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*     */           if (followers.contains(Long.valueOf(partnerEntity.getPid()))) {
/*     */             int primLv = partnerEntity.getPrimLv();
/*     */             if (0 == primLv) {
/*     */               primLv = 1;
/*     */             }
/*     */             PartnerPrimBean partnerPrimBean = (PartnerPrimBean)JsonTableService.getJsonData(primLv, PartnerPrimBean.class);
/*     */             if (null != partnerPrimBean) {
/*     */               map.put(Integer.valueOf(partnerEntity.getTableId()), Integer.valueOf(partnerPrimBean.getPartnerAttc()));
/*     */             }
/*     */           } 
/*     */         });
/* 172 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateAckAdd(IPlayerSession playerSession) {
/* 181 */     if (null == playerSession) {
/*     */       return;
/*     */     }
/* 184 */     PartnerComponent partnerComponent = (PartnerComponent)playerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 185 */     Player player = (Player)playerSession.getPlayer();
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
/*     */   public static void pushPartnerInfo(IPlayerSession playerSession, PartnerInfo partnerInfo) {
/* 199 */     PushPartnerInfoResponse response = new PushPartnerInfoResponse();
/* 200 */     response.partnerInfos.add(partnerInfo);
/* 201 */     playerSession.sendMessage((ResponseBase)response);
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
/*     */   public static ArrayList<Integer> getRelation(int partner, ArrayList<Integer> equipList, FighterBean fighterBeans, IPlayer player, int talisman) {
/* 214 */     ArrayList<Integer> relationList = new ArrayList<>();
/* 215 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 216 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RelationBean.class);
/* 217 */     for (Object o : map.values()) {
/* 218 */       RelationBean relationBean = (RelationBean)o;
/* 219 */       boolean hadRelation = true;
/* 220 */       boolean hadRelation2 = false;
/* 221 */       if (fighterBeans.getRelation().indexOf(Integer.valueOf(relationBean.getId())) < 0) {
/*     */         continue;
/*     */       }
/* 224 */       int size = relationBean.getPara().size();
/* 225 */       int num = 1;
/* 226 */       for (Integer partnerId : relationBean.getPara()) {
/*     */         
/* 228 */         if (relationBean.getType() == 1) {
/* 229 */           if (partnerId.intValue() == partner) {
/*     */             continue;
/*     */           }
/* 232 */           for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 233 */             PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 234 */             if (relationBean.getPara().indexOf(Integer.valueOf(partnerEntity.getTableId())) >= 0 && partnerEntity.getTableId() != partner) {
/* 235 */               num++;
/*     */             }
/*     */           } 
/* 238 */           if (size != num) {
/* 239 */             hadRelation = false;
/*     */           }
/*     */           continue;
/*     */         } 
/* 243 */         for (Integer relation : fighterBeans.getRelation()) {
/* 244 */           if (relation.intValue() != relationBean.getId() && equipList.indexOf(partnerId) < 0) {
/* 245 */             hadRelation = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 249 */         for (Integer relation : fighterBeans.getRelation()) {
/* 250 */           if (relation.intValue() == relationBean.getId() && talisman == partnerId.intValue()) {
/* 251 */             hadRelation2 = true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 257 */       if (hadRelation || hadRelation2) {
/* 258 */         relationList.add(Integer.valueOf(relationBean.getId()));
/*     */       }
/*     */     } 
/* 261 */     return relationList;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PartnerInfo tranformPartner(long pid, PlayerComponent playerComponent, PartnerEntity partnerEntity) {
/* 266 */     PartnerInfo partner = new PartnerInfo();
/* 267 */     if (pid == -1L) {
/*     */       
/* 269 */       partner.pid = -1L;
/* 270 */       partner.level = playerComponent.getLevel();
/* 271 */       partner.tid = playerComponent.getLeaderId();
/* 272 */       partner.exp = playerComponent.getCurrency(CurrencyType.EXP);
/* 273 */       partner.wearSkin = 0;
/* 274 */       partner.activeSkins = new ArrayList();
/* 275 */       partner.desLv = playerComponent.getDesLv();
/* 276 */       partner.progress = playerComponent.getDesProgress();
/* 277 */       partner.primLevel = 0;
/* 278 */       partner.stars = playerComponent.getStars();
/* 279 */       partner.status = 1;
/* 280 */       partner.breachLevel = playerComponent.getBreakthroughs();
/* 281 */       partner.soulLevel = playerComponent.getSoulLevel();
/*     */ 
/*     */       
/* 284 */       playerComponent.getEquips().keySet().forEach(part -> {
/*     */             KeyValue keyValue = new KeyValue();
/*     */             keyValue.key = part.intValue();
/*     */             keyValue.value = ((Long)playerComponent.getEquips().get(part)).longValue();
/*     */             partner.equips.add(keyValue);
/*     */           });
/* 290 */       KeyValue keyValue = new KeyValue();
/* 291 */       keyValue.key = 7L;
/* 292 */       keyValue.value = playerComponent.getTalisman();
/* 293 */       partner.equips.add(keyValue);
/* 294 */       partner.reinIds = new ArrayList(playerComponent.getReincarnationIds());
/*     */     } else {
/* 296 */       partner.pid = partnerEntity.getPid();
/* 297 */       partner.tid = partnerEntity.getTableId();
/* 298 */       partner.level = partnerEntity.getLevel();
/* 299 */       partner.exp = partnerEntity.getExp();
/* 300 */       partner.wearSkin = partnerEntity.getWearSkin();
/* 301 */       partner.activeSkins = new ArrayList(partnerEntity.getActiveSkins());
/* 302 */       partner.desLv = partnerEntity.getDesLv();
/* 303 */       partner.progress = partnerEntity.getProgress();
/* 304 */       partner.primLevel = partnerEntity.getPrimLv();
/* 305 */       partner.stars = partnerEntity.getStars();
/* 306 */       partner.status = partnerEntity.getStatus();
/* 307 */       partner.breachLevel = partnerEntity.getBreakthroughs();
/* 308 */       partner.fightValue = partnerEntity.getFightValue();
/* 309 */       partner.soulLevel = partnerEntity.getSoulLevel();
/*     */       
/* 311 */       partnerEntity.getEquips().keySet().forEach(part -> {
/*     */             KeyValue keyValue = new KeyValue();
/*     */             keyValue.key = part.intValue();
/*     */             keyValue.value = ((Long)partnerEntity.getEquips().get(part)).longValue();
/*     */             partner.equips.add(keyValue);
/*     */           });
/* 317 */       KeyValue keyValue = new KeyValue();
/* 318 */       keyValue.key = 7L;
/* 319 */       keyValue.value = partnerEntity.getTalisman();
/* 320 */       partner.equips.add(keyValue);
/* 321 */       partner.reinIds = new ArrayList(partnerEntity.getReincarnationIds());
/* 322 */       PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerComponent.getPlayerId(), PartnerComponent.class);
/* 323 */       partnerComponent.getPartnerAttrUp().getAttrList(partner.attributes, partnerEntity.getPid());
/*     */     } 
/* 325 */     return partner;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PartnerInfo tranformPartner2(PlayerComponent playerComponent) {
/* 330 */     PartnerInfo partner = new PartnerInfo();
/* 331 */     partner.pid = -1L;
/* 332 */     partner.level = playerComponent.getLevel();
/* 333 */     partner.tid = playerComponent.getLeaderId();
/* 334 */     partner.exp = 0L;
/* 335 */     partner.wearSkin = playerComponent.getWearFashion();
/* 336 */     partner.activeSkins = new ArrayList();
/* 337 */     partner.desLv = playerComponent.getDesLv();
/* 338 */     partner.progress = playerComponent.getDesProgress();
/* 339 */     partner.primLevel = 0;
/* 340 */     partner.stars = playerComponent.getStars();
/* 341 */     partner.status = 1;
/* 342 */     partner.breachLevel = playerComponent.getBreakthroughs();
/* 343 */     partner.soulLevel = playerComponent.getSoulLevel();
/* 344 */     playerComponent.getEquips().keySet().forEach(part -> {
/*     */           KeyValue keyValue = new KeyValue();
/*     */           keyValue.key = part.intValue();
/*     */           keyValue.value = ((Long)playerComponent.getEquips().get(part)).longValue();
/*     */           partner.equips.add(keyValue);
/*     */         });
/* 350 */     KeyValue keyValue = new KeyValue();
/* 351 */     keyValue.key = 7L;
/* 352 */     keyValue.value = playerComponent.getTalisman();
/* 353 */     partner.equips.add(keyValue);
/* 354 */     partner.reinIds = new ArrayList(playerComponent.getReincarnationIds());
/* 355 */     return partner;
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
/*     */   public static ArrayList<Integer> getLearderRelation(int leader, ArrayList<Integer> equipList, FighterBean fighterBean, IPlayer player, int talisman) {
/* 368 */     ArrayList<Integer> relationList = new ArrayList<>();
/* 369 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 370 */     Map<Integer, Object> map = JsonTableService.getJsonTable(RelationBean.class);
/* 371 */     for (Object o : map.values()) {
/* 372 */       RelationBean relationBean = (RelationBean)o;
/* 373 */       boolean hadRelation = true;
/* 374 */       boolean hadRelation2 = false;
/* 375 */       if (fighterBean.getRelation().indexOf(Integer.valueOf(relationBean.getId())) < 0) {
/*     */         continue;
/*     */       }
/* 378 */       int size = relationBean.getPara().size();
/* 379 */       int num = 1;
/* 380 */       for (Integer partnerId : relationBean.getPara()) {
/*     */         
/* 382 */         if (relationBean.getType() == 1) {
/* 383 */           if (partnerId.intValue() == leader) {
/*     */             continue;
/*     */           }
/* 386 */           for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 387 */             PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 388 */             if (relationBean.getPara().indexOf(Integer.valueOf(partnerEntity.getTableId())) >= 0) {
/* 389 */               num++;
/*     */             }
/*     */           } 
/* 392 */           if (size != num) {
/* 393 */             hadRelation = false;
/*     */           }
/*     */           continue;
/*     */         } 
/* 397 */         for (Integer relation : fighterBean.getRelation()) {
/* 398 */           if (relation.intValue() != relationBean.getId() && equipList.indexOf(partnerId) < 0) {
/* 399 */             hadRelation = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 403 */         for (Integer relation : fighterBean.getRelation()) {
/* 404 */           if (relation.intValue() == relationBean.getId() && talisman == partnerId.intValue()) {
/* 405 */             hadRelation2 = true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 411 */       if (hadRelation || hadRelation2) {
/* 412 */         relationList.add(Integer.valueOf(relationBean.getId()));
/*     */       }
/*     */     } 
/* 415 */     return relationList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updatePartnerFightValue(PlayerComponent playerComponent, PartnerEntity partnerEntity, long value) {
/* 425 */     if (null == partnerEntity || partnerEntity.getFightValue() == value) {
/*     */       return;
/*     */     }
/* 428 */     partnerEntity.setFightValue(value);
/* 429 */     if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) >= 0) {
/* 430 */       PlayerUtil.updateTotalFightValue(partnerEntity.getPlayerId(), true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initPartner(IPlayer player) {
/* 439 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 440 */     partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*     */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*     */           partnerComponent.getPartnerAttrUp().initBase(null, partnerEntity.getPid());
/*     */           partnerComponent.getPartnerAttrUp().initAll(player, partnerEntity.getPid());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initBattlePartner(IPlayer player) {
/* 453 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 454 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 455 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/* 456 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*     */       
/* 458 */       if (null != partnerEntity) {
/* 459 */         partnerComponent.getPartnerAttrUp().initBase(playerComponent, partnerEntity.getPid());
/* 460 */         partnerComponent.getPartnerAttrUp().initAll(player, partnerEntity.getPid());
/*     */       }  }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateReincarn(TaskType taskType, long value, long playerId, int targetId) {
/* 469 */     PartnerParameter partnerParameter = (PartnerParameter)ParameterConstant.getParameter(33);
/* 470 */     Map<Integer, Set<Integer>> map = partnerParameter.getLevelMap();
/* 471 */     PlayerComponent playerComponent = LookUpService.getPlayerComponent(playerId);
/* 472 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 473 */     int playerLevel = playerComponent.getLevel();
/* 474 */     if (map.get(Integer.valueOf(playerLevel)) != null) {
/* 475 */       Set<Integer> reinIds = map.get(Integer.valueOf(playerLevel));
/* 476 */       int reinId = 0;
/* 477 */       for (Iterator<Integer> iterator = fighterBean.getReincarn().iterator(); iterator.hasNext(); ) { int rein = ((Integer)iterator.next()).intValue();
/* 478 */         if (reinIds.contains(Integer.valueOf(rein))) {
/* 479 */           reinId = rein;
/*     */           break;
/*     */         }  }
/*     */       
/* 483 */       if (reinId != 0) {
/* 484 */         FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reinId, FighterReincarnBean.class);
/* 485 */         if (playerComponent.getReincarnationMap().get(Integer.valueOf(reinId)) == null) {
/* 486 */           playerComponent.getReincarnationMap().put(Integer.valueOf(reinId), new HashMap<>());
/* 487 */           playerComponent.setReincarnationMap(playerComponent.getReincarnationMap());
/*     */         } 
/* 489 */         Map<Integer, Long> taskIdMap = (Map<Integer, Long>)playerComponent.getReincarnationMap().get(Integer.valueOf(reinId));
/* 490 */         ArrayList<FighterReincarnBean.TaskBean> taskBeans = fighterReincarnBean.getTask();
/* 491 */         for (FighterReincarnBean.TaskBean taskBean : taskBeans) {
/* 492 */           TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(taskBean.getType(), TaskTypeBean.class);
/* 493 */           if (taskType.getType() != taskBean.getType()) {
/*     */             continue;
/*     */           }
/* 496 */           long lastValue = ((Long)taskIdMap.getOrDefault(Integer.valueOf(taskType.getType()), Long.valueOf(0L))).longValue();
/* 497 */           long targetValue = 0L;
/* 498 */           if (taskTypeBean.getCountType() == 0) {
/* 499 */             if (taskBean.getTargetId() == -1) {
/* 500 */               targetValue = TaskUtil.getSchedule(playerId, taskBean.getType(), playerComponent.getLeaderId());
/*     */             } else {
/* 502 */               targetValue = TaskUtil.getSchedule(playerId, taskBean.getType(), taskBean.getTargetId());
/*     */             }
/*     */           
/* 505 */           } else if (targetId != 0 && taskBean.getTargetId() != 0 && targetId == taskBean.getTargetId()) {
/* 506 */             targetValue = lastValue + value;
/* 507 */           } else if (targetId != 0 && taskBean.getTargetId() != 0 && targetId != taskBean.getTargetId()) {
/* 508 */             targetValue = lastValue;
/*     */           } else {
/* 510 */             targetValue = lastValue + value;
/*     */           } 
/*     */           
/* 513 */           if (lastValue < taskBean.getNum()) {
/* 514 */             taskIdMap.put(Integer.valueOf(taskBean.getType()), Long.valueOf(targetValue));
/* 515 */             playerComponent.getReincarnationMap().put(Integer.valueOf(reinId), taskIdMap);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 521 */     PartnerComponent partnerComponent = (PartnerComponent)LookUpService.getComponent(playerId, PartnerComponent.class);
/* 522 */     for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 523 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 524 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 525 */       int partnerLevel = partnerEntity.getLevel();
/* 526 */       if (map.get(Integer.valueOf(partnerLevel)) != null) {
/* 527 */         Set<Integer> reinIds = map.get(Integer.valueOf(partnerLevel));
/* 528 */         int reinId = 0;
/* 529 */         for (Iterator<Integer> iterator = fighterBean.getReincarn().iterator(); iterator.hasNext(); ) { int rein = ((Integer)iterator.next()).intValue();
/* 530 */           if (reinIds.contains(Integer.valueOf(rein))) {
/* 531 */             reinId = rein;
/*     */             break;
/*     */           }  }
/*     */         
/* 535 */         if (reinId != 0) {
/* 536 */           FighterReincarnBean fighterReincarnBean = (FighterReincarnBean)JsonTableService.getJsonData(reinId, FighterReincarnBean.class);
/* 537 */           if (partnerEntity.getReincarnationMap().get(Integer.valueOf(reinId)) == null) {
/* 538 */             partnerEntity.getReincarnationMap().put(Integer.valueOf(reinId), new HashMap<>());
/* 539 */             partnerEntity.setReincarnationMap(partnerEntity.getReincarnationMap());
/*     */           } 
/* 541 */           Map<Integer, Long> taskIdMap = (Map<Integer, Long>)partnerEntity.getReincarnationMap().get(Integer.valueOf(reinId));
/* 542 */           ArrayList<FighterReincarnBean.TaskBean> taskBeans = fighterReincarnBean.getTask();
/* 543 */           for (FighterReincarnBean.TaskBean taskBean : taskBeans) {
/* 544 */             TaskTypeBean taskTypeBean = (TaskTypeBean)JsonTableService.getJsonData(taskBean.getType(), TaskTypeBean.class);
/* 545 */             if (taskType.getType() != taskBean.getType()) {
/*     */               continue;
/*     */             }
/* 548 */             long lastValue = ((Long)taskIdMap.getOrDefault(Integer.valueOf(taskType.getType()), Long.valueOf(0L))).longValue();
/* 549 */             long targetValue = 0L;
/* 550 */             if (taskTypeBean.getCountType() == 0) {
/* 551 */               if (taskBean.getTargetId() == -1) {
/* 552 */                 targetValue = TaskUtil.getSchedule(playerId, taskBean.getType(), partnerEntity.getTableId());
/*     */               } else {
/* 554 */                 targetValue = TaskUtil.getSchedule(playerId, taskBean.getType(), taskBean.getTargetId());
/*     */               } 
/*     */             } else {
/*     */               
/* 558 */               targetValue = lastValue + value;
/*     */             } 
/* 560 */             if (lastValue < taskBean.getNum()) {
/* 561 */               taskIdMap.put(Integer.valueOf(taskBean.getType()), Long.valueOf(targetValue));
/* 562 */               partnerEntity.getReincarnationMap().put(Integer.valueOf(reinId), taskIdMap);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
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
/*     */   public static boolean isReincarn(Map<Integer, Set<Integer>> levelMap, int level, PartnerEntity partnerEntity) {
/* 579 */     int reinId = 0;
/* 580 */     Set<Integer> reinIds = levelMap.get(Integer.valueOf(level));
/* 581 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/* 582 */     for (Iterator<Integer> iterator = fighterBean.getReincarn().iterator(); iterator.hasNext(); ) { int rein = ((Integer)iterator.next()).intValue();
/* 583 */       if (reinIds.contains(Integer.valueOf(rein))) {
/* 584 */         reinId = rein;
/*     */         break;
/*     */       }  }
/*     */     
/* 588 */     if (!partnerEntity.getReincarnationIds().contains(Integer.valueOf(reinId))) {
/* 589 */       return false;
/*     */     }
/* 591 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\partner\PartnerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */