/*      */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*      */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*      */ import com.linlongyx.core.framework.logic.IPlayer;
/*      */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*      */ import com.linlongyx.core.utils.LogUtils;
/*      */ import com.linlongyx.sanguo.webgame.app.equip.EquipComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.extend.ExtendComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.general.GeneralComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.mounts.MountsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.mounts.MountsEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.sanguozhi.SanGuoZhiEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.singleIns.SingleInsComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*      */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*      */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*      */ import com.linlongyx.sanguo.webgame.common.attribute.PartnerAttrUpdate;
/*      */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.EquipArtificeBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.EquipStarBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.EquipStoneBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.EquipSuitBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ExpupBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterBreakBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterQualityBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.FighterStarBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ItemBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.KungfuBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.KungfuHandbookBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.KungfuLevelBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.MountBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.PetBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.PetHandbookBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.PetLevelBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.RecordStarBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.RelationBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.SoulBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.StageBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.StageHandbookBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.StageLevelBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.TalismanLevelBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.TreasureSuitBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaBean;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.ZhenfaLevelBean;
/*      */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.common.AttrUpdateResponse;
/*      */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ public class AttrUpdateUtil {
/*      */   public static ArrayList<Long> getPartnerPidList(IPlayerSession iPlayerSession) {
/*   63 */     ArrayList<Long> arrayList = new ArrayList<>();
/*   64 */     PartnerComponent partnerComponent = (PartnerComponent)iPlayerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/*   65 */     partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*      */           PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*      */           arrayList.add(Long.valueOf(partnerEntity.getPid()));
/*      */         });
/*   69 */     return arrayList;
/*      */   }
/*      */   
/*      */   public static final int TYPE_WEI_GUO = 1;
/*      */   public static final int TYPE_SHU_GUO = 2;
/*      */   public static final int TYPE_WU_GUO = 3;
/*      */   public static final int TYPE_QUN_XIONG = 4;
/*      */   
/*      */   public static void refreshPartnerEquip(IPlayerSession iPlayerSession) {
/*   78 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.PARTNER_EQUIP, -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshPartner(IPlayerSession iPlayerSession) {
/*   87 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.PARTNER, -1L);
/*   88 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*   89 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.PARTNER, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshLeader(IPlayerSession iPlayerSession) {
/*   99 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.LEADER, -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshPlayerEquip(IPlayerSession iPlayerSession) {
/*  108 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.EQUIP, -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshAllEquip(IPlayerSession iPlayerSession) {
/*  117 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.EQUIP, -1L);
/*  118 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  119 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.PARTNER_EQUIP, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshGroup(IPlayerSession iPlayerSession) {
/*  129 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.GROUP, -1L);
/*  130 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  131 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.GROUP, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshWarPet(IPlayerSession iPlayerSession) {
/*  142 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.WARPET, -1L);
/*  143 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  144 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.WARPET, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshWarLineup(IPlayerSession iPlayerSession) {
/*  154 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.WARZHENFA, -1L);
/*  155 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  156 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.WARZHENFA, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshTitle(IPlayerSession iPlayerSession) {
/*  167 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.TITLE, -1L);
/*  168 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  169 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.TITLE, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshMounts(IPlayerSession iPlayerSession) {
/*  180 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.MOUNTS, -1L);
/*  181 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  182 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.MOUNTS, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshFashion(IPlayerSession iPlayerSession) {
/*  192 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.FASHION, -1L);
/*  193 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  194 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.FASHION, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshGrowthGoal(IPlayerSession iPlayerSession) {
/*  205 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.GROWTHGOAL, -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshGrowthGoalStar(IPlayerSession iPlayerSession) {
/*  214 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.GROWTHGOAL, -1L);
/*  215 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  216 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.GROWTHGOAL, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshDestiny(IPlayerSession iPlayerSession) {
/*  226 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.DESTINY, -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshCardBook(IPlayerSession iPlayerSession) {
/*  235 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.CARDBOOK, -1L);
/*  236 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  237 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.CARDBOOK, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshMilitary(IPlayerSession iPlayerSession) {
/*  247 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.MILITARY, -1L);
/*  248 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  249 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.MILITARY, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshKungFu(IPlayerSession iPlayerSession) {
/*  259 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.KUNGFU, -1L);
/*  260 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  261 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.KUNGFU, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshStage(IPlayerSession iPlayerSession) {
/*  271 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.STAGE, -1L);
/*  272 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  273 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.STAGE, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshSouls(IPlayerSession iPlayerSession) {
/*  283 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.SOULS, -1L);
/*  284 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  285 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.SOULS, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshAssist(IPlayerSession iPlayerSession) {
/*  295 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.ASSIST, -1L);
/*  296 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  297 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.ASSIST, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshArtifact(IPlayerSession iPlayerSession) {
/*  306 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.ARTIFACT, -1L);
/*  307 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  308 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.ARTIFACT, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void refreshRune(IPlayerSession iPlayerSession) {
/*  317 */     PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.RUNE, -1L);
/*  318 */     for (Iterator<Long> iterator = getPartnerPidList(iPlayerSession).iterator(); iterator.hasNext(); ) { long pid = ((Long)iterator.next()).longValue();
/*  319 */       PartnerAttrUpdate.refresh(iPlayerSession, PlayerAttrUp.AttrUpType.RUNE, pid); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updatePlayerfight(IPlayerSession iPlayerSession, long total, ArrayList<AttrValue> attrValues) {
/*  330 */     AttrUpdateResponse attrUpdateResponse = new AttrUpdateResponse();
/*  331 */     PlayerComponent playerComponent = (PlayerComponent)iPlayerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/*  332 */     attrUpdateResponse.checksum = (new MD5()).toDigest(iPlayerSession.getKey() + total);
/*  333 */     attrUpdateResponse.attrValues = attrValues;
/*  334 */     PlayerUtil.updatePlayerFightValue(playerComponent, true);
/*  335 */     iPlayerSession.sendMessage((ResponseBase)attrUpdateResponse);
/*  336 */     PlayerUtil.updateKeyValueInfo(iPlayerSession, KeyValueConstant.PLAYER_FIGHT_VALUE.getKey(), playerComponent.getFightValue());
/*      */     
/*  338 */     TaskComponent taskComponent = (TaskComponent)iPlayerSession.getPlayer().createIfNotExist(TaskComponent.class);
/*  339 */     taskComponent.refreshSchedule(TaskType.FightValue, 0, 0L);
/*      */ 
/*      */ 
/*      */     
/*  343 */     CrossRankActUtil.refreshRankValue(CrossRankActType.PlayerFight.getType(), playerComponent.getTotalValue(), playerComponent.getPlayerId());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateAttr(FighterBean fighterBean, long[] attrs, Set<Integer> updates, int attrId, long attrNum, boolean isAdd, boolean battleAdd) {
/*  359 */     int id = attrId % 100;
/*  360 */     if (attrId > 0 && attrId < 100 && isAdd) {
/*  361 */       attrs[attrId] = attrs[attrId] + attrNum;
/*  362 */       updates.add(Integer.valueOf(attrId));
/*      */     } 
/*  364 */     if (!battleAdd) {
/*      */       return;
/*      */     }
/*  367 */     if (attrId > 100 && attrId < 200) {
/*  368 */       attrs[id] = attrs[id] + attrNum;
/*  369 */       updates.add(Integer.valueOf(id));
/*      */     } 
/*  371 */     if (null == fighterBean) {
/*      */       return;
/*      */     }
/*  374 */     if (attrId > 200 && attrId < 300) {
/*  375 */       if (fighterBean.getCamp() == 1) {
/*  376 */         attrs[id] = attrs[id] + attrNum;
/*  377 */         updates.add(Integer.valueOf(id));
/*      */       } 
/*  379 */     } else if (attrId > 300 && attrId < 400) {
/*  380 */       if (fighterBean.getCamp() == 2) {
/*  381 */         attrs[id] = attrs[id] + attrNum;
/*  382 */         updates.add(Integer.valueOf(id));
/*      */       } 
/*  384 */     } else if (attrId > 400 && attrId < 500) {
/*  385 */       if (fighterBean.getCamp() == 3) {
/*  386 */         attrs[id] = attrs[id] + attrNum;
/*  387 */         updates.add(Integer.valueOf(id));
/*      */       } 
/*  389 */     } else if (attrId > 500 && attrId < 600 && 
/*  390 */       fighterBean.getCamp() == 4) {
/*  391 */       attrs[id] = attrs[id] + attrNum;
/*  392 */       updates.add(Integer.valueOf(id));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateAttr(FighterBean fighterBean, long[] attrs, Set<Integer> updates, int attrId, long attrNum, boolean isAdd) {
/*  410 */     updateAttr(fighterBean, attrs, updates, attrId, attrNum, isAdd, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void partnerAttribute(IPlayer player, long[] attrs, Set<Integer> updates, long pid, boolean isLeader) {
/*  423 */     Map<Integer, Integer> partnerMap = new HashMap<>();
/*  424 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  425 */     EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/*  426 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  427 */     GeneralComponent generalComponent = (GeneralComponent)player.createIfNotExist(GeneralComponent.class);
/*      */     
/*  429 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  430 */     partnerMap.put(Integer.valueOf(partnerEntity.getTableId()), Integer.valueOf(partnerEntity.getStars()));
/*  431 */     ExpupBean expupBean = (ExpupBean)JsonTableService.getJsonData(partnerEntity.getLevel(), ExpupBean.class);
/*  432 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBean.class);
/*  433 */     FighterStarBean fighterStarBean = (FighterStarBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterStarBean.class);
/*  434 */     FighterBreakBean fighterBreakBean = (FighterBreakBean)JsonTableService.getJsonData(partnerEntity.getTableId(), FighterBreakBean.class);
/*  435 */     if (null == expupBean || null == fighterStarBean || null == fighterBreakBean) {
/*  436 */       LogUtil.errorLog(new Object[] { "AttrUpdateUtil::init|bean of expupBean or fighterStarBean or fighterBreakBean is null: ", Integer.valueOf(partnerEntity.getLevel()), Integer.valueOf(partnerEntity.getBreakthroughs()), Integer.valueOf(partnerEntity.getStars()) });
/*      */       return;
/*      */     } 
/*  439 */     if (isLeader) {
/*      */       
/*  441 */       for (AttrBean attrBean : ((FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(partnerEntity.getBreakthroughs()))).getAttr()) {
/*  442 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */       }
/*  444 */       for (int i = 0; i <= partnerEntity.getBreakthroughs(); i++) {
/*  445 */         for (FighterBreakBean.LevelBean.TalentBean talentBean : ((FighterBreakBean.LevelBean)fighterBreakBean.getLevel().get(Integer.valueOf(i))).getTalent()) {
/*  446 */           updateAttr(fighterBean, attrs, updates, talentBean.getId(), talentBean.getNum(), false);
/*      */         }
/*      */       } 
/*      */       
/*  450 */       SoulBean soulBean = (SoulBean)JsonTableService.getJsonData(partnerEntity.getSoulLevel(), SoulBean.class);
/*  451 */       for (SoulBean.AttrBean attrBean : soulBean.getAttr()) {
/*  452 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */       }
/*      */     } else {
/*      */       
/*  456 */       for (AttrBean attrBean : fighterBean.getAttr()) {
/*  457 */         long value = attrBean.getNum();
/*  458 */         attrs[attrBean.getId()] = attrs[attrBean.getId()] + value;
/*  459 */         updates.add(Integer.valueOf(attrBean.getId()));
/*      */       } 
/*      */       
/*  462 */       fighterLevelAttr(expupBean, fighterBean, attrs, updates, partnerEntity, fighterStarBean);
/*      */ 
/*      */       
/*  465 */       for (FighterStarBean.LevelBean.AttrBean attrBean : ((FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(partnerEntity.getStars()))).getAttr()) {
/*  466 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  471 */       partnerComponent.getEntityMap().values().forEach(iMapEntity -> {
/*      */             PartnerEntity partnerEntity2 = (PartnerEntity)iMapEntity;
/*      */             
/*      */             FighterBreakBean fighterBreakBean2 = (FighterBreakBean)JsonTableService.getJsonData(partnerEntity2.getTableId(), FighterBreakBean.class);
/*      */             
/*      */             FighterBean fighterBean2 = (FighterBean)JsonTableService.getJsonData(partnerEntity2.getTableId(), FighterBean.class);
/*      */             
/*      */             SoulBean soulBean = (SoulBean)JsonTableService.getJsonData(partnerEntity2.getSoulLevel(), SoulBean.class);
/*      */             if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity2.getPid())) >= 0 || generalComponent.getAssistInBattle().indexOf(Long.valueOf(partnerEntity2.getPid())) >= 0) {
/*      */               partnerBreach(playerComponent, fighterBreakBean, fighterBreakBean2, fighterBean, fighterBean2, attrs, updates, partnerEntity2, partnerEntity, true);
/*      */               for (SoulBean.AttrBean attrBean : soulBean.getAttr()) {
/*      */                 if (partnerEntity.getPid() == partnerEntity2.getPid()) {
/*      */                   updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true, true);
/*      */                   continue;
/*      */                 } 
/*      */                 updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false, true);
/*      */               } 
/*      */             } else {
/*      */               partnerBreach(playerComponent, fighterBreakBean, fighterBreakBean2, fighterBean, fighterBean2, attrs, updates, partnerEntity2, partnerEntity, false);
/*      */               for (SoulBean.AttrBean attrBean : soulBean.getAttr()) {
/*      */                 if (partnerEntity.getPid() == partnerEntity2.getPid()) {
/*      */                   updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true, false);
/*      */                   continue;
/*      */                 } 
/*      */                 updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false, false);
/*      */               } 
/*      */             } 
/*      */           });
/*  499 */       if (playerComponent.getFighters().indexOf(Long.valueOf(partnerEntity.getPid())) >= 0) {
/*  500 */         leaderTalent(playerComponent, fighterBean, attrs, updates, true);
/*      */       } else {
/*  502 */         leaderTalent(playerComponent, fighterBean, attrs, updates, false);
/*      */       } 
/*      */       
/*  505 */       SoulBean soulBean2 = (SoulBean)JsonTableService.getJsonData(playerComponent.getSoulLevel(), SoulBean.class);
/*  506 */       for (SoulBean.AttrBean attrBean : soulBean2.getAttr()) {
/*  507 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */       }
/*      */       
/*  510 */       SanGuoZhiComponent sanGuoZhiComponent = (SanGuoZhiComponent)player.createIfNotExist(SanGuoZhiComponent.class);
/*  511 */       sanGuoZhiComponent.getEntityMap().values().forEach(iMapEntity2 -> {
/*      */             SanGuoZhiEntity sanGuoZhiEntity2 = (SanGuoZhiEntity)iMapEntity2;
/*      */             
/*      */             if (sanGuoZhiEntity2.isActivity()) {
/*      */               RecordStarBean recordStarBean = (RecordStarBean)JsonTableService.getJsonData(sanGuoZhiEntity2.getRecordStarId(), RecordStarBean.class);
/*      */               for (RecordStarBean.AttrBean attrBean : recordStarBean.getAttr()) {
/*      */                 updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */               }
/*      */             } 
/*      */           });
/*  521 */       ArrayList<Integer> equipList = new ArrayList<>();
/*  522 */       partnerEntity.getEquips().values().forEach(equipId -> {
/*      */             if (equipId.longValue() != 0L) {
/*      */               EquipEntity equipEntity = equipComponent.getEquipEntity(equipId.longValue());
/*      */               if (null != equipEntity) {
/*      */                 equipList.add(Integer.valueOf(equipEntity.getItemId()));
/*      */               }
/*      */             } 
/*      */           });
/*  530 */       int itemId = 0;
/*  531 */       if (partnerEntity.getTalisman() != 0L) {
/*  532 */         EquipEntity equipEntity = equipComponent.getEquipEntity(partnerEntity.getTalisman());
/*  533 */         if (null != equipEntity) {
/*  534 */           itemId = equipEntity.getItemId();
/*      */         }
/*      */       } 
/*  537 */       ArrayList<Integer> relationList = PartnerUtil.getRelation(partnerEntity.getTableId(), equipList, fighterBean, player, itemId);
/*  538 */       for (Integer relation : relationList) {
/*  539 */         RelationBean relationBean = (RelationBean)JsonTableService.getJsonData(relation.intValue(), RelationBean.class);
/*  540 */         relationBean.getAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void partnerBreach(PlayerComponent playerComponent, FighterBreakBean fighterBreakBean, FighterBreakBean fighterBreakBean2, FighterBean fighterBean, FighterBean fighterBean2, long[] attrs, Set<Integer> updates, PartnerEntity partnerEntity2, PartnerEntity partnerEntity, boolean add) {
/*  565 */     for (AttrBean attrBean : ((FighterBreakBean.LevelBean)fighterBreakBean2.getLevel().get(Integer.valueOf(partnerEntity2.getBreakthroughs()))).getAttr()) {
/*  566 */       if (partnerEntity2.getPid() == partnerEntity.getPid()) {
/*  567 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true, add); continue;
/*      */       } 
/*  569 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false, add);
/*      */     } 
/*      */ 
/*      */     
/*  573 */     for (int i = 0; i <= partnerEntity2.getBreakthroughs(); i++) {
/*  574 */       for (FighterBreakBean.LevelBean.TalentBean talentBean : ((FighterBreakBean.LevelBean)fighterBreakBean2.getLevel().get(Integer.valueOf(i))).getTalent()) {
/*  575 */         if (partnerEntity2.getPid() == partnerEntity.getPid()) {
/*  576 */           updateAttr(fighterBean, attrs, updates, talentBean.getId(), talentBean.getNum(), true, add); continue;
/*      */         } 
/*  578 */         updateAttr(fighterBean, attrs, updates, talentBean.getId(), talentBean.getNum(), false, add);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void leaderTalent(PlayerComponent playerComponent, FighterBean fighterBean, long[] attrs, Set<Integer> updates, boolean add) {
/*  595 */     int leaderId = playerComponent.getLeaderId();
/*  596 */     FighterBreakBean fighterBreakBean3 = (FighterBreakBean)JsonTableService.getJsonData(leaderId, FighterBreakBean.class);
/*  597 */     if (null != fighterBreakBean3) {
/*  598 */       for (AttrBean attrBean : ((FighterBreakBean.LevelBean)fighterBreakBean3.getLevel().get(Integer.valueOf(playerComponent.getBreakthroughs()))).getAttr()) {
/*  599 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false, add);
/*      */       }
/*      */       
/*  602 */       for (int i = 0; i <= playerComponent.getBreakthroughs(); i++) {
/*  603 */         for (FighterBreakBean.LevelBean.TalentBean talentBean : ((FighterBreakBean.LevelBean)fighterBreakBean3.getLevel().get(Integer.valueOf(i))).getTalent()) {
/*  604 */           updateAttr(fighterBean, attrs, updates, talentBean.getId(), talentBean.getNum(), false, add);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void fighterLevelAttr(ExpLeaderBean expupBean, FighterBean fighterBean, long[] attrs, Set<Integer> updates, PlayerComponent playerComponent) {
/*  619 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FighterQualityBean.class);
/*  620 */     FighterQualityBean fighterQualityBean1 = null;
/*  621 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  622 */       FighterQualityBean fighterQualityBean = (FighterQualityBean)entry.getValue();
/*  623 */       if (fighterQualityBean.getFightType() == fighterBean.getFightType() && fighterQualityBean.getQuality().get(Integer.valueOf(playerComponent.getQuality())) != null) {
/*  624 */         fighterQualityBean1 = fighterQualityBean;
/*      */         break;
/*      */       } 
/*      */     } 
/*  628 */     if (null == fighterQualityBean1) {
/*  629 */       LogUtil.errorLog(new Object[] { "fighterLevelAttr fighterQualityBean quality Not exit", Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(fighterBean.getFightType()), Integer.valueOf(playerComponent.getQuality()) });
/*      */       return;
/*      */     } 
/*  632 */     for (AttrBean attrBean : expupBean.getAttr()) {
/*  633 */       for (FighterQualityBean.QualityBean.AttrParBean attrParBean : ((FighterQualityBean.QualityBean)fighterQualityBean1.getQuality().get(Integer.valueOf(playerComponent.getQuality()))).getAttrPar()) {
/*  634 */         if (attrParBean.getId() == attrBean.getId()) {
/*  635 */           long value = attrBean.getNum() * attrParBean.getPar() / 10000L;
/*  636 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), value, true);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void fighterLevelAttr(ExpupBean expupBean, FighterBean fighterBean, long[] attrs, Set<Integer> updates, PartnerEntity partnerEntity, FighterStarBean fighterStarBean) {
/*  652 */     Map<Integer, Object> map = JsonTableService.getJsonTable(FighterQualityBean.class);
/*  653 */     FighterQualityBean fighterQualityBean1 = null;
/*  654 */     for (Map.Entry<Integer, Object> entry : map.entrySet()) {
/*  655 */       FighterQualityBean fighterQualityBean = (FighterQualityBean)entry.getValue();
/*  656 */       if (fighterQualityBean.getFightType() == fighterBean.getFightType() && fighterQualityBean.getQuality().get(Integer.valueOf(fighterBean.getQuality())) != null) {
/*  657 */         fighterQualityBean1 = fighterQualityBean;
/*      */         break;
/*      */       } 
/*      */     } 
/*  661 */     if (null == fighterQualityBean1) {
/*  662 */       LogUtil.errorLog(new Object[] { "fighterLevelAttr fighterQualityBean quality Not exit", Integer.valueOf(fighterBean.getFightType()), Integer.valueOf(fighterBean.getQuality()) });
/*      */       return;
/*      */     } 
/*  665 */     for (AttrBean attrBean : expupBean.getAttr()) {
/*  666 */       long qualityPar = 0L;
/*  667 */       long starPar = 0L;
/*      */       
/*  669 */       for (FighterQualityBean.QualityBean.AttrParBean attrParBean : ((FighterQualityBean.QualityBean)fighterQualityBean1.getQuality().get(Integer.valueOf(fighterBean.getQuality()))).getAttrPar()) {
/*  670 */         if (attrParBean.getId() == attrBean.getId()) {
/*  671 */           qualityPar = attrParBean.getPar();
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*  676 */       for (FighterStarBean.LevelBean.AttrParbonusBean attrParBean : ((FighterStarBean.LevelBean)fighterStarBean.getLevel().get(Integer.valueOf(partnerEntity.getStars()))).getAttrParbonus()) {
/*  677 */         if (attrParBean.getId() == attrBean.getId()) {
/*  678 */           starPar = attrParBean.getPar();
/*      */           break;
/*      */         } 
/*      */       } 
/*  682 */       long value = (long)(attrBean.getNum() * (qualityPar + starPar) / 10000.0D);
/*  683 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), value, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void updateNormal(FighterBean fighterBean, EquipEntity equipEntity, long[] attrs, Set<Integer> updates, Map<Integer, Integer> suitNum) {
/*  695 */     int strengthLv = equipEntity.getStrengthLv();
/*  696 */     int refineLv = equipEntity.getRefineLv();
/*  697 */     int zhuLv = equipEntity.getZhuLv();
/*  698 */     ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  699 */     if (null == itemBean) {
/*      */       return;
/*      */     }
/*      */     
/*  703 */     itemBean.getAttr().forEach(attrBean -> {
/*      */           attrs[attrBean.getId()] = attrs[attrBean.getId()] + attrBean.getNum();
/*      */           updates.add(Integer.valueOf(attrBean.getId()));
/*      */         });
/*  707 */     int suitType = itemBean.getSuitType();
/*  708 */     boolean isTreasure = EquipUtil.isTreasure(itemBean.getId());
/*  709 */     if (!isTreasure) {
/*  710 */       suitNum.put(Integer.valueOf(suitType), Integer.valueOf(((Integer)suitNum.getOrDefault(Integer.valueOf(suitType), Integer.valueOf(0))).intValue() + 1));
/*  711 */       EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(suitType, EquipSuitBean.class);
/*  712 */       if (null == equipSuitBean) {
/*      */         return;
/*      */       }
/*  715 */       Map<Integer, EquipSuitBean.EquipPartBean> equipPart = equipSuitBean.getEquipPart();
/*  716 */       EquipSuitBean.EquipPartBean equipPartBean = equipPart.get(Integer.valueOf(itemBean.getEquipPart()));
/*  717 */       if (null == equipPartBean) {
/*      */         return;
/*      */       }
/*      */       
/*  721 */       equipPartBean.getStrengthAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * strengthLv, true));
/*      */ 
/*      */ 
/*      */       
/*  725 */       equipPartBean.getPurifyAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * refineLv, true));
/*      */ 
/*      */ 
/*      */       
/*  729 */       Map<Integer, Integer> stones = equipEntity.getStones();
/*  730 */       for (Map.Entry<Integer, Integer> entry : stones.entrySet()) {
/*  731 */         EquipStoneBean equipStoneBean = (EquipStoneBean)JsonTableService.getJsonData(((Integer)entry.getKey()).intValue(), EquipStoneBean.class);
/*  732 */         if (null == equipStoneBean || 0 == ((Integer)entry.getValue()).intValue()) {
/*      */           continue;
/*      */         }
/*  735 */         equipStoneBean.getAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), (attrBean.getNum() * ((Integer)entry.getValue()).intValue()), true));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  740 */       equipPartBean.getZhuhunAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * zhuLv, true));
/*      */ 
/*      */ 
/*      */       
/*  744 */       int artiLevel = equipEntity.getArtificeLevel();
/*  745 */       int artiProcess = equipEntity.getArtificeProcess();
/*  746 */       EquipArtificeBean equipArtificeBean = (EquipArtificeBean)JsonTableService.getJsonData(suitType, EquipArtificeBean.class);
/*  747 */       if (null != equipArtificeBean) {
/*  748 */         for (int i = 0; i < artiLevel; i++) {
/*  749 */           EquipArtificeBean.LvBean lvBean = (EquipArtificeBean.LvBean)equipArtificeBean.getLv().get(Integer.valueOf(i));
/*  750 */           int maxVal = lvBean.getMaxVal();
/*      */           
/*  752 */           for (AttrBean attrBean : equipPartBean.getArtificeAttr()) {
/*  753 */             long addVal = (long)((attrBean.getNum() * maxVal) * lvBean.getValProb() / 10000.0D);
/*  754 */             updateAttr(fighterBean, attrs, updates, attrBean.getId(), addVal, true);
/*      */           } 
/*      */           
/*  757 */           for (AttrBean addBean : equipPartBean.getArtifiAdd()) {
/*  758 */             long addVal = (long)(addBean.getNum() * lvBean.getAdd_att() / 10000.0D);
/*  759 */             updateAttr(fighterBean, attrs, updates, addBean.getId(), addVal, true);
/*      */           } 
/*      */         } 
/*      */         
/*  763 */         for (AttrBean attrBean : equipPartBean.getArtificeAttr()) {
/*  764 */           long addVal = (long)((attrBean.getNum() * artiProcess) * ((EquipArtificeBean.LvBean)equipArtificeBean.getLv().get(Integer.valueOf(artiLevel))).getValProb() / 10000.0D);
/*  765 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), addVal, true);
/*      */         } 
/*      */       } 
/*      */       
/*  769 */       int star = equipEntity.getStar();
/*  770 */       int addition = 0;
/*  771 */       EquipStarBean starBean = (EquipStarBean)JsonTableService.getJsonData(itemBean.getSuitType(), EquipStarBean.class);
/*  772 */       if (null != starBean) {
/*  773 */         EquipStarBean.StarBean starBean1 = (EquipStarBean.StarBean)starBean.getStar().get(Integer.valueOf(star));
/*  774 */         if (null != starBean1) {
/*  775 */           addition = starBean1.getAtt_star();
/*      */         }
/*  777 */         for (AttrBean attrBean : equipPartBean.getAttStar()) {
/*  778 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * 1L * addition / 10000L, true);
/*      */         }
/*      */       } 
/*      */     } else {
/*  782 */       TreasureSuitBean treasureSuitBean = (TreasureSuitBean)JsonTableService.getJsonData(suitType, TreasureSuitBean.class);
/*  783 */       if (null == treasureSuitBean) {
/*      */         return;
/*      */       }
/*  786 */       Map<Integer, TreasureSuitBean.TreasurePartBean> treasurePart = treasureSuitBean.getTreasurePart();
/*  787 */       TreasureSuitBean.TreasurePartBean treasurePartBean = treasurePart.get(Integer.valueOf(itemBean.getEquipPart()));
/*  788 */       if (null == treasurePartBean) {
/*      */         return;
/*      */       }
/*      */       
/*  792 */       treasurePartBean.getStrengthAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * strengthLv, true));
/*      */ 
/*      */ 
/*      */       
/*  796 */       treasurePartBean.getPurifyAttr().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * refineLv, true));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateEquipSuit(FighterBean fighterBean, long[] attrs, Set<Integer> updates, Map<Integer, Integer> suitNum) {
/*  810 */     int num = 0;
/*  811 */     int suitType = 0;
/*  812 */     for (Map.Entry<Integer, Integer> entry : suitNum.entrySet()) {
/*  813 */       if (((Integer)entry.getValue()).intValue() > num) {
/*  814 */         num = ((Integer)entry.getValue()).intValue();
/*  815 */         suitType = ((Integer)entry.getKey()).intValue();
/*      */       } 
/*      */     } 
/*  818 */     EquipSuitBean equipSuitBean = (EquipSuitBean)JsonTableService.getJsonData(suitType, EquipSuitBean.class);
/*  819 */     if (null == equipSuitBean) {
/*      */       return;
/*      */     }
/*  822 */     if (num >= 4) {
/*  823 */       equipSuitBean.getSuitC().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*      */     }
/*      */ 
/*      */     
/*  827 */     if (num >= 3) {
/*  828 */       equipSuitBean.getSuitB().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*      */     }
/*      */ 
/*      */     
/*  832 */     if (num >= 2) {
/*  833 */       equipSuitBean.getSuitA().forEach(attrBean -> updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void updateTalisman(IPlayer player, FighterBean fighterBean, long[] attrs, Set<Integer> updates, long pid) {
/*  850 */     long talismanId = 0L;
/*  851 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  852 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  853 */     for (Iterator<Long> iterator = playerComponent.getFighters().iterator(); iterator.hasNext(); ) { long id = ((Long)iterator.next()).longValue();
/*  854 */       if (id == 0L) {
/*      */         continue;
/*      */       }
/*  857 */       if (id == -1L) {
/*  858 */         talismanId = playerComponent.getTalisman();
/*      */       } else {
/*  860 */         PartnerEntity partnerEntity = partnerComponent.getEntity(id);
/*  861 */         if (null != partnerEntity) {
/*  862 */           talismanId = partnerEntity.getTalisman();
/*      */         }
/*      */       } 
/*  865 */       if (talismanId != 0L) {
/*  866 */         EquipComponent equipComponent = (EquipComponent)player.createIfNotExist(EquipComponent.class);
/*  867 */         EquipEntity equipEntity = equipComponent.getEquipEntity(talismanId);
/*  868 */         if (null == equipEntity) {
/*      */           continue;
/*      */         }
/*  871 */         ItemBean itemBean = (ItemBean)JsonTableService.getJsonData(equipEntity.getItemId(), ItemBean.class);
/*  872 */         if (null == itemBean) {
/*      */           continue;
/*      */         }
/*  875 */         if (itemBean.getEquipPart() != 7) {
/*      */           continue;
/*      */         }
/*  878 */         int equipRank = equipEntity.getTalismanRank();
/*  879 */         TalismanLevelBean talismanLevelBean = (TalismanLevelBean)JsonTableService.getJsonData(equipEntity.getItemId(), TalismanLevelBean.class);
/*  880 */         if (talismanLevelBean == null) {
/*      */           continue;
/*      */         }
/*  883 */         TalismanLevelBean.LvBean lvBean = (TalismanLevelBean.LvBean)talismanLevelBean.getLv().get(Integer.valueOf(equipRank));
/*  884 */         for (AttrBean attrBean : lvBean.getAttr()) {
/*  885 */           if (pid == id) {
/*  886 */             updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */           } 
/*  888 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */         } 
/*      */ 
/*      */         
/*  892 */         for (AttrBean attrBean : lvBean.getTalentsAttr()) {
/*  893 */           if (pid == id) {
/*  894 */             updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */           } 
/*  896 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */         } 
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void warPetAttr(IPlayer player, long[] attrs, Set<Integer> updates, long pid) {
/*  912 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  913 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  914 */     FighterBean fighterBean = null;
/*  915 */     if (pid != -1L) {
/*  916 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*      */     } else {
/*  918 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*      */     } 
/*  920 */     if (null == fighterBean) {
/*  921 */       LogUtils.errorLog(new Object[] { "warPetAttr", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*      */       return;
/*      */     } 
/*  924 */     WarPetComponent warPetComponent = (WarPetComponent)player.createIfNotExist(WarPetComponent.class);
/*  925 */     for (IMapEntity iMapEntity : warPetComponent.getEntityMap().values()) {
/*  926 */       WarPetEntity warPetEntity = (WarPetEntity)iMapEntity;
/*  927 */       oneWarPetAttr(fighterBean, warPetEntity, attrs, updates, pid);
/*      */     } 
/*      */     
/*  930 */     ExtendComponent extendComponent = (ExtendComponent)player.createIfNotExist(ExtendComponent.class);
/*  931 */     for (Iterator<Integer> iterator = extendComponent.getWpHandbook().keySet().iterator(); iterator.hasNext(); ) { int handbookId = ((Integer)iterator.next()).intValue();
/*  932 */       PetHandbookBean petHandbookBean = (PetHandbookBean)JsonTableService.getJsonData(handbookId, PetHandbookBean.class);
/*  933 */       PetHandbookBean.StarBean starBean1 = (PetHandbookBean.StarBean)petHandbookBean.getStar().get(extendComponent.getWpHandbook().get(Integer.valueOf(handbookId)));
/*  934 */       for (AttrBean attrBean : starBean1.getAttr()) {
/*  935 */         if (pid == -1L) {
/*  936 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */         } 
/*  938 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void warLineupAttr(IPlayer player, long[] attrs, Set<Integer> updates, long pid) {
/*  953 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  954 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  955 */     FighterBean fighterBean = null;
/*  956 */     if (pid != -1L) {
/*  957 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*      */     } else {
/*  959 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*      */     } 
/*  961 */     if (null == fighterBean) {
/*  962 */       LogUtils.errorLog(new Object[] { "warLineupAttr", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*      */       return;
/*      */     } 
/*  965 */     WarLineupComponent warLineupComponent = (WarLineupComponent)player.createIfNotExist(WarLineupComponent.class);
/*  966 */     for (IMapEntity iMapEntity : warLineupComponent.getEntityMap().values()) {
/*  967 */       WarLineupEntity warLineupEntity = (WarLineupEntity)iMapEntity;
/*  968 */       oneWarLineupAttr(fighterBean, warLineupEntity, attrs, updates, pid);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void mountsAttr(IPlayer player, long[] attrs, Set<Integer> updates, long pid) {
/*  995 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  996 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  997 */     FighterBean fighterBean = null;
/*  998 */     if (pid != -1L) {
/*  999 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*      */     } else {
/* 1001 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*      */     } 
/* 1003 */     if (null == fighterBean) {
/* 1004 */       LogUtils.errorLog(new Object[] { "mountsAttr", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*      */       return;
/*      */     } 
/* 1007 */     MountsComponent mountsComponent = (MountsComponent)player.createIfNotExist(MountsComponent.class);
/* 1008 */     for (IMapEntity iMapEntity : mountsComponent.getEntityMap().values()) {
/* 1009 */       MountsEntity mountsEntity = (MountsEntity)iMapEntity;
/* 1010 */       oneMountsAttr(fighterBean, attrs, updates, mountsEntity, pid);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void kungFuAttr(IPlayer player, long[] attrs, Set<Integer> updates, long pid) {
/* 1023 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 1024 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 1025 */     FighterBean fighterBean = null;
/* 1026 */     if (pid != -1L) {
/* 1027 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*      */     } else {
/* 1029 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*      */     } 
/* 1031 */     if (null == fighterBean) {
/* 1032 */       LogUtils.errorLog(new Object[] { "kungFuAttr", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*      */       return;
/*      */     } 
/* 1035 */     KungFuComponent kungFuComponent = (KungFuComponent)player.createIfNotExist(KungFuComponent.class);
/* 1036 */     for (IMapEntity iMapEntity : kungFuComponent.getEntityMap().values()) {
/* 1037 */       KungFuEntity kungFuEntity = (KungFuEntity)iMapEntity;
/* 1038 */       oneKungFuAttr(fighterBean, kungFuEntity, attrs, updates, pid);
/*      */     } 
/*      */ 
/*      */     
/* 1042 */     SingleInsComponent singleInsComponent = (SingleInsComponent)player.createIfNotExist(SingleInsComponent.class);
/* 1043 */     for (Iterator<Integer> iterator = singleInsComponent.getKfHandbook().keySet().iterator(); iterator.hasNext(); ) { int handbookId = ((Integer)iterator.next()).intValue();
/* 1044 */       KungfuHandbookBean kungfuHandbookBean = (KungfuHandbookBean)JsonTableService.getJsonData(handbookId, KungfuHandbookBean.class);
/* 1045 */       KungfuHandbookBean.StarBean starBean1 = (KungfuHandbookBean.StarBean)kungfuHandbookBean.getStar().get(singleInsComponent.getKfHandbook().get(Integer.valueOf(handbookId)));
/* 1046 */       for (AttrBean attrBean : starBean1.getAttr()) {
/* 1047 */         if (pid == -1L) {
/* 1048 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */         } 
/* 1050 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void stageAttr(IPlayer player, long[] attrs, Set<Integer> updates, long pid) {
/* 1065 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 1066 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 1067 */     FighterBean fighterBean = null;
/* 1068 */     if (pid != -1L) {
/* 1069 */       fighterBean = (FighterBean)JsonTableService.getJsonData(partnerComponent.getEntity(pid).getTableId(), FighterBean.class);
/*      */     } else {
/* 1071 */       fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/*      */     } 
/* 1073 */     if (null == fighterBean) {
/* 1074 */       LogUtils.errorLog(new Object[] { "stageAttr", Long.valueOf(player.getPlayerId()), Long.valueOf(pid) });
/*      */       return;
/*      */     } 
/* 1077 */     StageComponent stageComponent = (StageComponent)player.createIfNotExist(StageComponent.class);
/* 1078 */     for (IMapEntity iMapEntity : stageComponent.getEntityMap().values()) {
/* 1079 */       StageEntity stageEntity = (StageEntity)iMapEntity;
/* 1080 */       oneStageAttr(fighterBean, stageEntity, attrs, updates, pid);
/*      */     } 
/*      */ 
/*      */     
/* 1084 */     SingleInsComponent singleInsComponent = (SingleInsComponent)player.createIfNotExist(SingleInsComponent.class);
/* 1085 */     for (Iterator<Integer> iterator = singleInsComponent.getStageHandbook().keySet().iterator(); iterator.hasNext(); ) { int handbookId = ((Integer)iterator.next()).intValue();
/* 1086 */       StageHandbookBean stageHandbookBean = (StageHandbookBean)JsonTableService.getJsonData(handbookId, StageHandbookBean.class);
/* 1087 */       StageHandbookBean.StarBean starBean1 = (StageHandbookBean.StarBean)stageHandbookBean.getStar().get(singleInsComponent.getStageHandbook().get(Integer.valueOf(handbookId)));
/* 1088 */       for (AttrBean attrBean : starBean1.getAttr()) {
/* 1089 */         if (pid == -1L) {
/* 1090 */           updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */         } 
/* 1092 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */       }  }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void oneMountsAttr(FighterBean fighterBean, long[] attrs, Set<Integer> updates, MountsEntity mountsEntity, long pid) {
/* 1107 */     MountBean mountBean = (MountBean)JsonTableService.getJsonData(mountsEntity.getMountsId(), MountBean.class);
/* 1108 */     if (null == mountBean) {
/* 1109 */       LogUtil.errorLog(new Object[] { "AttrUpdateUtil::init|bean of mountsid is null: ", Integer.valueOf(mountsEntity.getMountsId()) });
/*      */       return;
/*      */     } 
/* 1112 */     MountBean.StarBean starBean = (MountBean.StarBean)mountBean.getStar().get(Integer.valueOf(mountsEntity.getStar()));
/* 1113 */     for (AttrBean attrBean : starBean.getAttr()) {
/* 1114 */       if (pid == -1L) {
/* 1115 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */       } 
/* 1117 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1122 */     MountLevelBean mountLevelBean = (MountLevelBean)JsonTableService.getJsonData(mountsEntity.getLevel(), MountLevelBean.class);
/* 1123 */     for (AttrBean attrBean : mountLevelBean.getAttr()) {
/* 1124 */       if (pid == -1L) {
/* 1125 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, true); continue;
/*      */       } 
/* 1127 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void oneWarPetAttr(FighterBean fighterBean, WarPetEntity warPetEntity, long[] attrs, Set<Integer> updates, long pid) {
/* 1143 */     PetBean petBean = (PetBean)JsonTableService.getJsonData(warPetEntity.getWarPetId(), PetBean.class);
/* 1144 */     if (null == petBean) {
/* 1145 */       LogUtil.errorLog(new Object[] { "AttrUpdateUtil::init|bean of warpetid is null: ", Integer.valueOf(warPetEntity.getWarPetId()) });
/*      */       return;
/*      */     } 
/* 1148 */     PetBean.StarBean starBean = (PetBean.StarBean)petBean.getStar().get(Integer.valueOf(warPetEntity.getStar()));
/* 1149 */     for (AttrBean attrBean : starBean.getAttr()) {
/* 1150 */       if (pid == -1L) {
/* 1151 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */       } 
/* 1153 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1158 */     PetLevelBean petLevelBean = (PetLevelBean)JsonTableService.getJsonData(warPetEntity.getLevel(), PetLevelBean.class);
/* 1159 */     for (AttrBean attrBean : petLevelBean.getAttr()) {
/* 1160 */       if (pid == -1L) {
/* 1161 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, true); continue;
/*      */       } 
/* 1163 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void oneWarLineupAttr(FighterBean fighterBean, WarLineupEntity warLineupEntity, long[] attrs, Set<Integer> updates, long pid) {
/* 1178 */     ZhenfaBean zhenfaBean = (ZhenfaBean)JsonTableService.getJsonData(warLineupEntity.getWarLineupId(), ZhenfaBean.class);
/* 1179 */     if (null == zhenfaBean) {
/* 1180 */       LogUtil.errorLog(new Object[] { "AttrUpdateUtil::init|bean of warpetid is null: ", Integer.valueOf(warLineupEntity.getWarLineupId()) });
/*      */       return;
/*      */     } 
/* 1183 */     ZhenfaBean.StarBean starBean = (ZhenfaBean.StarBean)zhenfaBean.getStar().get(Integer.valueOf(warLineupEntity.getStar()));
/*      */     
/* 1185 */     ZhenfaLevelBean zhenfaLevelBean = (ZhenfaLevelBean)JsonTableService.getJsonData(warLineupEntity.getLevel(), ZhenfaLevelBean.class);
/* 1186 */     for (ZhenfaLevelBean.AttrBean attrBean : zhenfaLevelBean.getAttr()) {
/* 1187 */       if (pid == -1L) {
/* 1188 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, true); continue;
/*      */       } 
/* 1190 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void oneKungFuAttr(FighterBean fighterBean, KungFuEntity kungFuEntity, long[] attrs, Set<Integer> updates, long pid) {
/* 1207 */     KungfuBean kungfuBean = (KungfuBean)JsonTableService.getJsonData(kungFuEntity.getKungFuId(), KungfuBean.class);
/* 1208 */     if (null == kungfuBean) {
/* 1209 */       LogUtil.errorLog(new Object[] { "AttrUpdateUtil::init|bean of KungFuId is null: ", Integer.valueOf(kungFuEntity.getKungFuId()) });
/*      */       return;
/*      */     } 
/* 1212 */     KungfuBean.StarBean starBean = (KungfuBean.StarBean)kungfuBean.getStar().get(Integer.valueOf(kungFuEntity.getStar()));
/* 1213 */     for (KungfuBean.StarBean.AttrBean attrBean : starBean.getAttr()) {
/* 1214 */       if (pid == -1L) {
/* 1215 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */       } 
/* 1217 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1222 */     KungfuLevelBean kungfuLevelBean = (KungfuLevelBean)JsonTableService.getJsonData(kungFuEntity.getLevel(), KungfuLevelBean.class);
/* 1223 */     for (KungfuLevelBean.AttrBean attrBean : kungfuLevelBean.getAttr()) {
/* 1224 */       if (pid == -1L) {
/* 1225 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, true); continue;
/*      */       } 
/* 1227 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void oneStageAttr(FighterBean fighterBean, StageEntity stageEntity, long[] attrs, Set<Integer> updates, long pid) {
/* 1243 */     StageBean stageBean = (StageBean)JsonTableService.getJsonData(stageEntity.getId(), StageBean.class);
/* 1244 */     if (null == stageBean) {
/* 1245 */       LogUtil.errorLog(new Object[] { "AttrUpdateUtil::init|bean of stageid is null: ", Integer.valueOf(stageEntity.getId()) });
/*      */       return;
/*      */     } 
/* 1248 */     StageBean.StarBean starBean = (StageBean.StarBean)stageBean.getStar().get(Integer.valueOf(stageEntity.getStar()));
/* 1249 */     for (StageBean.StarBean.AttrBean attrBean : starBean.getAttr()) {
/* 1250 */       if (pid == -1L) {
/* 1251 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), true); continue;
/*      */       } 
/* 1253 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum(), false);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1258 */     StageLevelBean stageLevelBean = (StageLevelBean)JsonTableService.getJsonData(stageEntity.getLevel(), StageLevelBean.class);
/* 1259 */     for (StageLevelBean.AttrBean attrBean : stageLevelBean.getAttr()) {
/* 1260 */       if (pid == -1L) {
/* 1261 */         updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, true); continue;
/*      */       } 
/* 1263 */       updateAttr(fighterBean, attrs, updates, attrBean.getId(), attrBean.getNum() * starBean.getLevelPar() / 10000L, false);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\AttrUpdateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */