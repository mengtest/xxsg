/*     */ package com.linlongyx.sanguo.webgame.common.fight;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.unparalleled.UnparalleledComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CandidateFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CommonFighterData;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.FighterFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PetFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.StageFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.select.target.TargetSelector;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.FightGroup;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.IFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.side.PlayerFightSide;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.NewSkillBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.PvpInfoBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*     */ import com.linlongyx.sanguo.webgame.constant.MilitaryInsType;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.offices.MilitaryUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FightUtil
/*     */ {
/*     */   public static boolean isFront(byte pos) {
/*  63 */     return (pos % 6 < 4 && pos % 6 > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean sameRow(byte pos1, byte pos2) {
/*  74 */     pos1 = (byte)(pos1 % 6);
/*  75 */     pos2 = (byte)(pos2 % 6);
/*     */     
/*  77 */     if (isFront(pos1))
/*  78 */       return isFront(pos2); 
/*  79 */     return !isFront(pos2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean sameLine(byte pos1, byte pos2) {
/*  90 */     pos1 = (byte)(pos1 % 6);
/*  91 */     pos2 = (byte)(pos2 % 6);
/*     */     
/*  93 */     int abs = Math.abs(pos1 - pos2);
/*  94 */     return (abs == 3 || abs == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAround(byte pos1, byte pos2) {
/* 105 */     if (!sameRow(pos1, pos2))
/* 106 */       return false; 
/* 107 */     return (Math.abs(pos1 - pos2) == 1);
/*     */   }
/*     */   
/*     */   public static byte getTargetGuid(byte guid) {
/* 111 */     if (guid < 100) {
/* 112 */       guid = (byte)(guid + 100);
/*     */     } else {
/* 114 */       guid = (byte)(guid - 100);
/* 115 */     }  return guid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getTargetSide(IFighter fighter, byte side) {
/* 125 */     return (byte)((fighter.getSide() + side) % 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getTargetSide(byte side) {
/* 135 */     return (byte)((side + 1) % 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBaseAttr(int type) {
/* 145 */     return (type >= AttributeType.ATTRIBUTE_NON.getType() && type < AttributeType.BASE_ATTR_END.getType());
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
/*     */   public static void hurt(Effect effect, IFighter attacker, IFighter target, ResultPlayData resultPlayData, IFight fight, Pair<Integer, Long> hurtValue) {
/* 160 */     hurtValue.setValue(Long.valueOf(FightCalculator.hurtType(effect, attacker, target)));
/*     */     
/* 162 */     int damageType = effect.getBean().getDemageType();
/* 163 */     if (damageType != 0) {
/* 164 */       if (!FightCalculator.isHit(attacker, target)) {
/* 165 */         resultPlayData.targeAction = (byte)(resultPlayData.targeAction | 0x2);
/* 166 */         fight.appendDebugStr("; " + target + "闪避");
/* 167 */         hurtValue.setKey(Integer.valueOf(-1));
/*     */         
/* 169 */         if (effect.getBean().getDodge() == 1) {
/* 170 */           List<Integer> list = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 171 */           if (!list.isEmpty()) {
/* 172 */             for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 173 */               if (ret != 0) {
/* 174 */                 fight.appendDebugStr("; 闪避, 附加效果:" + ret + ";");
/*     */               } }
/*     */           
/*     */           }
/*     */         } 
/*     */         return;
/*     */       } 
/* 181 */       if (effect.getBean().getDodge() == 2) {
/* 182 */         List<Integer> list = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 183 */         if (!list.isEmpty()) {
/* 184 */           for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 185 */             if (ret != 0) {
/* 186 */               fight.appendDebugStr("; 命中, 无伤害, 附加效果:" + ret + ";");
/*     */             } }
/*     */         
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/* 194 */       hurtValue.setKey(Integer.valueOf(-2));
/* 195 */       List<Integer> list = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 196 */       if (!list.isEmpty()) {
/* 197 */         for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 198 */           if (ret != 0) {
/* 199 */             fight.appendDebugStr("; 附加效果:" + ret + ";");
/*     */           } }
/*     */       
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 207 */     buffEffect(attacker, target, hurtValue, resultPlayData);
/*     */ 
/*     */     
/* 210 */     List<Integer> retList = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 211 */     if (!retList.isEmpty()) {
/* 212 */       for (Iterator<Integer> iterator = retList.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 213 */         if (ret != 0) {
/* 214 */           fight.appendDebugStr("; 附加效果:" + ret + ";");
/*     */         } }
/*     */     
/*     */     }
/*     */ 
/*     */     
/* 220 */     if (FightCalculator.isCrit(effect, attacker, target)) {
/* 221 */       resultPlayData.targeAction = (byte)(resultPlayData.targeAction | 0x1);
/* 222 */       hurtValue.setValue(Long.valueOf((long)(((Long)hurtValue.getValue()).longValue() * (1.0D + FightCalculator.critRate(attacker, target)))));
/* 223 */       fight.appendDebugStr("; " + target + "被暴击");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     long finalVal = FightCalculator.hurtValue(hurtValue, attacker, target);
/*     */ 
/*     */     
/*     */     try {
/* 235 */       finalVal = FightCalculator.finalHurtValue(finalVal, attacker, target);
/* 236 */     } catch (IndexOutOfBoundsException e) {
/* 237 */       LogUtil.errorLog(new Object[] { "old destiny player fighters", e.getMessage() });
/*     */     } 
/*     */     
/* 240 */     finalVal = formatHurtVal((finalVal < 0L) ? 0L : finalVal);
/* 241 */     hurtValue.setValue(Long.valueOf(finalVal));
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
/*     */   private static void buffEffect(IFighter attacker, IFighter target, Pair<Integer, Long> hurtValue, ResultPlayData resultPlayData) {
/* 254 */     Iterator<AbstractBuff> iter = attacker.getBuffList().iterator();
/* 255 */     while (iter.hasNext()) {
/* 256 */       AbstractBuff buff = iter.next();
/* 257 */       if (buff.getBean().getCalculationType() == 1) {
/* 258 */         buff.immediate(resultPlayData, ((Long)hurtValue.getValue()).longValue());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 263 */     iter = target.getBuffList().iterator();
/* 264 */     while (iter.hasNext()) {
/* 265 */       AbstractBuff buff = iter.next();
/* 266 */       if (buff.getTimes() != -1) {
/* 267 */         if (buff.getTimes() == 0) {
/* 268 */           if (!buff.getRoundReset())
/* 269 */             iter.remove(); 
/*     */           continue;
/*     */         } 
/* 272 */         buff.setTimes(buff.getTimes() - 1);
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
/*     */   public static IFighter getMinGuidFighter(List<IFighter> fighters) {
/* 285 */     IFighter minfighter = fighters.get(0);
/* 286 */     byte min = minfighter.getGuid();
/* 287 */     for (IFighter fighter : fighters) {
/* 288 */       if (fighter.getGuid() < min) {
/* 289 */         min = fighter.getGuid();
/* 290 */         minfighter = fighter;
/*     */       } 
/*     */     } 
/* 293 */     return minfighter;
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
/*     */   public static long formatHurtVal(long hurtVal) {
/* 319 */     if (hurtVal < 1L)
/* 320 */       return 1L; 
/* 321 */     return hurtVal;
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
/*     */   public static long getBaseAttrAdd(long base, int level) {
/* 337 */     long val = base;
/* 338 */     val = val * 10000L / 10000L;
/*     */     
/* 340 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PlayerFightSide initPlayerFightSide(IPlayer player) {
/* 350 */     PlayerFightSide playerFightSide = new PlayerFightSide();
/* 351 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 352 */     playerFightSide.init(player, playerComponent.getFighters());
/* 353 */     return playerFightSide;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PlayerFightSide initPlayerPVPFightSide(IPlayer player, PvpInfoBean pvpInfoBean) {
/* 364 */     PlayerFightSide fightSide = initPlayerFightSide(player);
/* 365 */     fightSide.getCurGroup().setMaxRound(((PvpInfoBean.WaveBean)pvpInfoBean.getWave().get(Integer.valueOf(1))).getRound());
/* 366 */     return fightSide;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearBuff(IFightSide fightSide) {
/* 371 */     for (FightGroup fightGroup : fightSide.getGroupList()) {
/* 372 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 373 */         fighter.clearBuff();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeDeadFighters(IFightSide fightSide) {
/* 380 */     for (FightGroup fightGroup : fightSide.getGroupList()) {
/* 381 */       fightGroup.removeDeadFighter();
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
/*     */   public static void initcandidateFighterAttr(IFightSide fightSide, int id, int level, long[] baseAttrs, long[] attrs) {
/* 394 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(id, SoulsStarBean.class);
/* 395 */     SoulsStarBean.StarBean bean = (SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(level));
/*     */     
/* 397 */     for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/* 398 */       if (type < attrs.length) {
/*     */ 
/*     */         
/* 401 */         for (FightGroup group : fightSide.getGroupList()) {
/* 402 */           for (IFighter fighter : group.getFighters(true)) {
/* 403 */             if (type < AttributeType.BASE_ATTR_END.getType()) {
/* 404 */               baseAttrs[type] = baseAttrs[type] + fighter.getBaseAttr(type);
/*     */             }
/*     */ 
/*     */             
/* 408 */             if (type == AttributeType.HP.getType()) {
/* 409 */               attrs[type] = attrs[type] + fighter.getBaseAttr(type); continue;
/*     */             } 
/* 411 */             attrs[type] = attrs[type] + fighter.getAttr(type);
/*     */           } 
/*     */         } 
/*     */         
/* 415 */         if (type < AttributeType.BASE_ATTR_END.getType()) {
/* 416 */           baseAttrs[type] = baseAttrs[type] / (6 * fightSide.getGroupList().size());
/*     */         }
/* 418 */         attrs[type] = attrs[type] / (6 * fightSide.getGroupList().size());
/*     */       } 
/* 420 */     }  for (AttrBean attrBean : bean.getAttr()) {
/* 421 */       if (attrBean.getId() < AttributeType.BASE_ATTR_END.getType()) {
/* 422 */         baseAttrs[attrBean.getId()] = baseAttrs[attrBean.getId()] * attrBean.getNum() / 10000L;
/*     */       }
/* 424 */       attrs[attrBean.getId()] = attrs[attrBean.getId()] * attrBean.getNum() / 10000L;
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
/*     */   public static PlayerFightSide initUnParalleledSide(IPlayer player) {
/* 436 */     UnparalleledComponent unparalleledComponent = (UnparalleledComponent)player.createIfNotExist(UnparalleledComponent.class);
/* 437 */     PlayerFightSide playerFightSide = new PlayerFightSide();
/* 438 */     playerFightSide.init(player, unparalleledComponent.getBattlePartner());
/*     */     
/* 440 */     updateUnparaAttribute(unparalleledComponent, player, playerFightSide);
/* 441 */     return playerFightSide;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateUnparaAttribute(UnparalleledComponent unparalleledComponent, IPlayer player, PlayerFightSide playerFightSide) {
/* 446 */     if (unparalleledComponent.getAttrsList().isEmpty()) {
/* 447 */       PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/* 448 */       for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/* 449 */         ArrayList<Long> arrayList = new ArrayList<>();
/* 450 */         PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/* 451 */         if (null != partnerEntity) {
/* 452 */           partnerComponent.getPartnerAttrUp().getAttrList(arrayList, partnerEntity.getPid());
/* 453 */           unparalleledComponent.getAttrsList().put(Long.valueOf(partnerEntity.getPid()), arrayList);
/* 454 */           unparalleledComponent.getLevelMap().put(Long.valueOf(partnerEntity.getPid()), Integer.valueOf(partnerEntity.getLevel()));
/*     */         } 
/*     */       } 
/* 457 */       ArrayList<Long> arrayList2 = new ArrayList<>();
/* 458 */       PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 459 */       playerComponent.getPlayerAttrUp().getAttrList(arrayList2, -1L);
/* 460 */       unparalleledComponent.getAttrsList().put(Long.valueOf(-1L), arrayList2);
/* 461 */       unparalleledComponent.setAttrsList(unparalleledComponent.getAttrsList());
/* 462 */       unparalleledComponent.getLevelMap().put(Long.valueOf(-1L), Integer.valueOf(playerComponent.getLevel()));
/* 463 */       unparalleledComponent.setLevelMap(unparalleledComponent.getLevelMap());
/*     */     } 
/* 465 */     List<IFighter> fighters = playerFightSide.getCurGroup().getFighters(true);
/* 466 */     fighters.forEach(iFighter -> {
/*     */           if (unparalleledComponent.getPartneredHpMap().containsKey(Long.valueOf(iFighter.getPid()))) {
/*     */             long hp = ((Long)unparalleledComponent.getPartneredHpMap().get(Long.valueOf(iFighter.getPid()))).longValue();
/*     */             for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */               ArrayList<Long> attrList = (ArrayList<Long>)unparalleledComponent.getAttrsList().get(Long.valueOf(iFighter.getPid()));
/*     */               if (type < attrList.size()) {
/*     */                 long val = ((Long)attrList.get(type)).longValue();
/*     */                 iFighter.setAttr(type, val);
/*     */                 if (type < AttributeType.BASE_ATTR_END.getType())
/*     */                   iFighter.setBaseAttr(type, val); 
/*     */               } 
/*     */             } 
/*     */             (iFighter.getFighterData()).hp = hp;
/*     */             iFighter.setAttr(AttributeType.HP.getType(), hp);
/*     */             iFighter.setBaseAttr(AttributeType.HP.getType(), hp);
/*     */             (iFighter.getFighterData()).maxHp = ((Long)((ArrayList<Long>)unparalleledComponent.getAttrsList().get(Long.valueOf(iFighter.getPid()))).get(AttributeType.HP.getType())).longValue();
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   public static PlayerFightSide createTeamPlayerFightSide(Team team, boolean isOpposite) {
/* 487 */     PlayerFightSide playerFightSide = new PlayerFightSide();
/* 488 */     List<IFighter> fighters = new ArrayList<>();
/* 489 */     List<Pair<Integer, Integer>> candidateFighters = null;
/* 490 */     Pair<Integer, Integer> zhenfa = null;
/* 491 */     for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 492 */       if (teamPlayer != null) {
/* 493 */         if (!teamPlayer.isRobot()) {
/* 494 */           if (teamPlayer.isLeader()) {
/* 495 */             candidateFighters = teamPlayer.getCandidateFighters();
/* 496 */             zhenfa = teamPlayer.getZhenfa();
/* 497 */             if (teamPlayer.isNeedReInit()) {
/* 498 */               CommonFighterData petFighterData = getFighterData(-1L, teamPlayer.getPlayerId(), (byte)3);
/* 499 */               if (petFighterData != null) {
/* 500 */                 PetFighter petFighter = new PetFighter(petFighterData);
/* 501 */                 teamPlayer.setPetFighter(new TeamPlayerFighter(false, (IFighter)petFighter));
/*     */               } 
/* 503 */               CommonFighterData stageFighterData = getFighterData(-1L, teamPlayer.getPlayerId(), (byte)4);
/* 504 */               if (stageFighterData != null) {
/* 505 */                 StageFighter stageFighter = new StageFighter(stageFighterData);
/* 506 */                 teamPlayer.setStageFighter(new TeamPlayerFighter(false, (IFighter)stageFighter));
/*     */               } 
/*     */             } 
/* 509 */             if (teamPlayer.getPetFighter() != null) {
/* 510 */               IFighter fighter = teamPlayer.getPetFighter().getFighter();
/*     */               
/* 512 */               fighter.setPos((byte)0);
/* 513 */               (fighter.getFighterData()).pos = 0;
/* 514 */               playerFightSide.setPetFighter(fighter);
/*     */             } 
/* 516 */             if (teamPlayer.getStageFighter() != null) {
/* 517 */               IFighter fighter = teamPlayer.getStageFighter().getFighter();
/*     */               
/* 519 */               fighter.setPos((byte)10);
/* 520 */               (fighter.getFighterData()).pos = 10;
/* 521 */               playerFightSide.setStageFighter(fighter);
/*     */             } 
/*     */           } 
/* 524 */           for (TeamPlayerFighter teamPlayerFighter : teamPlayer.getFighters()) {
/* 525 */             if (teamPlayerFighter != null) {
/* 526 */               if (teamPlayer.isNeedReInit()) {
/* 527 */                 if (teamPlayerFighter.getFighter().getType() == 0) {
/* 528 */                   CommonFighterData commonFighterData = getFighterData(teamPlayerFighter.getFighter().getPid(), teamPlayer.getPlayerId(), (byte)0);
/* 529 */                   teamPlayerFighter.setFighter((IFighter)new PlayerFighter(commonFighterData, (byte)teamPlayerFighter.getPos()));
/* 530 */                 } else if (teamPlayerFighter.getFighter().getType() == 1) {
/* 531 */                   CommonFighterData commonFighterData = getFighterData(teamPlayerFighter.getFighter().getPid(), teamPlayer.getPlayerId(), (byte)1);
/* 532 */                   teamPlayerFighter.setFighter((IFighter)new FighterFighter(commonFighterData, (byte)teamPlayerFighter.getPos(), teamPlayerFighter.getFighter().getPid()));
/*     */                 } 
/*     */               }
/* 535 */               fighters.add(teamPlayerFighter.getFighter());
/*     */             } 
/*     */           } 
/* 538 */           if (!teamPlayer.isLeader()) {
/* 539 */             IPlayerSession playerSession = null;
/* 540 */             if (!PlayerUtil.isRemotePlayer(teamPlayer.getPlayerId())) {
/* 541 */               playerSession = LookUpService.getByPlayerId(teamPlayer.getPlayerId()).getSession();
/*     */             }
/*     */ 
/*     */             
/* 545 */             playerFightSide.addPlayerSession(teamPlayer.getPlayerId(), playerSession);
/*     */           }  continue;
/*     */         } 
/* 548 */         for (TeamPlayerFighter teamPlayerFighter : teamPlayer.getFighters()) {
/* 549 */           fighters.add(teamPlayerFighter.getFighter());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 554 */     FightGroup group = new FightGroup();
/* 555 */     group.setFighters(fighters);
/* 556 */     group.setId(team.getLeaderId());
/* 557 */     group.setName(team.getLeaderName());
/* 558 */     group.setFirstHandVal(team.getFirstHandVal());
/* 559 */     playerFightSide.addGroup(group);
/*     */ 
/*     */     
/* 562 */     if (candidateFighters != null) {
/* 563 */       for (Pair<Integer, Integer> kv : candidateFighters) {
/* 564 */         long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/* 565 */         long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 566 */         initcandidateFighterAttr((IFightSide)playerFightSide, ((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 567 */         CandidateFighter fighter = new CandidateFighter(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 568 */         playerFightSide.addCandidateFigher((IFighter)fighter);
/*     */       } 
/*     */     }
/* 571 */     if (zhenfa != null) {
/* 572 */       playerFightSide.setZhenfa(zhenfa);
/*     */     }
/* 574 */     return playerFightSide;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PlayerFightSide createPvpPlayerFightSide(long playerId, String playerName, Map<Integer, CommonFighterData> fighterMap, List<Pair<Integer, Integer>> candidateFighters, Pair<Integer, Integer> zhenfa, int firstHandVal, boolean isOpposite) {
/* 579 */     PlayerFightSide playerFightSide = new PlayerFightSide();
/* 580 */     List<IFighter> fighters = new ArrayList<>();
/* 581 */     for (Map.Entry<Integer, CommonFighterData> kv : fighterMap.entrySet()) {
/* 582 */       FighterFighter fighterFighter; IFighter fighter = null;
/* 583 */       if (((Integer)kv.getKey()).intValue() == 0) {
/* 584 */         PetFighter petFighter = new PetFighter(kv.getValue());
/* 585 */         playerFightSide.setPetFighter((IFighter)petFighter);
/* 586 */         playerFightSide.getPetFighter().setId((int)((CommonFighterData)kv.getValue()).getId());
/* 587 */       } else if (((Integer)kv.getKey()).intValue() == 10) {
/* 588 */         StageFighter stageFighter = new StageFighter(kv.getValue());
/* 589 */         playerFightSide.setStageFighter((IFighter)stageFighter);
/* 590 */         playerFightSide.getStageFighter().setId((int)((CommonFighterData)kv.getValue()).getId());
/*     */       }
/* 592 */       else if (((CommonFighterData)kv.getValue()).getPid() == -1L) {
/* 593 */         PlayerFighter playerFighter = new PlayerFighter(kv.getValue(), ((Integer)kv.getKey()).byteValue());
/* 594 */         fighters.add(playerFighter);
/*     */       } else {
/* 596 */         fighterFighter = new FighterFighter(kv.getValue(), ((Integer)kv.getKey()).byteValue(), ((CommonFighterData)kv.getValue()).getPid());
/* 597 */         fighters.add(fighterFighter);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 602 */       if (((CommonFighterData)kv.getValue()).getTalisman() != null) {
/* 603 */         fighterFighter.setTalisman(((CommonFighterData)kv.getValue()).getTalisman());
/*     */       }
/*     */     } 
/*     */     
/* 607 */     FightGroup group = new FightGroup();
/* 608 */     group.setFighters(fighters);
/* 609 */     group.setId(playerId);
/* 610 */     group.setName(playerName);
/* 611 */     group.setFirstHandVal(firstHandVal);
/* 612 */     playerFightSide.addGroup(group);
/*     */ 
/*     */     
/* 615 */     if (null != candidateFighters) {
/* 616 */       for (Pair<Integer, Integer> kv : candidateFighters) {
/*     */         
/* 618 */         long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/* 619 */         long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 620 */         initcandidateFighterAttr((IFightSide)playerFightSide, ((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 621 */         CandidateFighter candidateFighter = new CandidateFighter(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 622 */         playerFightSide.addCandidateFigher((IFighter)candidateFighter);
/*     */       } 
/*     */     }
/* 625 */     if (zhenfa != null) {
/* 626 */       playerFightSide.setZhenfa(zhenfa);
/*     */     }
/* 628 */     return playerFightSide;
/*     */   }
/*     */   
/*     */   public static List<Pair<Integer, Integer>> getCandidateFighterList(long playerId) {
/* 632 */     PlayerComponent playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/* 633 */     if (playerComponent == null)
/* 634 */       return null; 
/* 635 */     List<Pair<Integer, Integer>> candidateFighterList = new ArrayList<>();
/* 636 */     SoulsComponent soulsComponent = (SoulsComponent)LookUpService.getComponent(playerId, SoulsComponent.class);
/* 637 */     if (soulsComponent != null) {
/* 638 */       for (Iterator<Integer> iterator = playerComponent.getSoulsFighter().iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 639 */         if (id > 0) {
/* 640 */           candidateFighterList.add(new Pair(Integer.valueOf(id), Integer.valueOf(soulsComponent.getEntity(id).getStar())));
/*     */         } }
/*     */     
/*     */     }
/* 644 */     return candidateFighterList;
/*     */   }
/*     */   
/*     */   public static Pair<Integer, Integer> getZhenfa(long playerId) {
/* 648 */     WarLineupComponent warLineupComponent = (WarLineupComponent)LookUpService.getComponent(playerId, WarLineupComponent.class);
/* 649 */     if (warLineupComponent != null) {
/* 650 */       WarLineupEntity entity = warLineupComponent.getBattleWarLineup();
/* 651 */       if (entity != null) {
/* 652 */         return new Pair(Integer.valueOf(entity.getWarLineupId()), Integer.valueOf(entity.getStar()));
/*     */       }
/*     */     } 
/* 655 */     return null;
/*     */   }
/*     */   
/*     */   public static CommonFighterData getFighterData(long pid, long playerId, byte fighterType) {
/*     */     try {
/* 660 */       if (fighterType == 0) {
/* 661 */         if (!PlayerUtil.isRemotePlayer(playerId)) {
/*     */           PlayerComponent playerComponent;
/* 663 */           if (!LookUpService.isOnline(playerId)) {
/* 664 */             IPlayer player = LookUpService.forceGetPlayer(playerId);
/* 665 */             playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 666 */             playerComponent.getPlayerAttrUp().initBase(playerComponent, -1L);
/* 667 */             playerComponent.getPlayerAttrUp().initAll(player, -1L);
/*     */           } else {
/* 669 */             playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*     */           } 
/* 671 */           if (playerComponent == null) {
/* 672 */             throw new NullPointerException("get playerComponent exception, playerId: " + playerId);
/*     */           }
/*     */           
/* 675 */           long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*     */           
/* 677 */           long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 678 */           for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */             
/* 680 */             long val = playerComponent.getPlayerAttrUp().getAttrByType(type, -1L);
/* 681 */             if (type < AttributeType.BASE_ATTR_END.getType())
/* 682 */               baseAttrs[type] = val; 
/* 683 */             attrs[type] = val;
/*     */           } 
/* 685 */           SkillComponent skillComponent = (SkillComponent)LookUpService.getComponent(playerId, SkillComponent.class);
/* 686 */           KungFuComponent kungFuComponent = (KungFuComponent)LookUpService.getComponent(playerId, KungFuComponent.class);
/*     */           
/* 688 */           Skill furySkill = null;
/* 689 */           if (kungFuComponent != null) {
/* 690 */             furySkill = kungFuComponent.getFurySkill();
/*     */           }
/* 692 */           Map<Integer, Integer> skillMap = new HashMap<>();
/* 693 */           if (furySkill != null) {
/* 694 */             skillMap.put(Integer.valueOf(furySkill.getSkillId()), Integer.valueOf(furySkill.getLevel()));
/*     */           }
/* 696 */           if (skillComponent != null) {
/* 697 */             if (furySkill == null) {
/* 698 */               for (IMapEntity entity : skillComponent.getEntityMap().values()) {
/* 699 */                 SkillEntity skillEntity = (SkillEntity)entity;
/* 700 */                 skillMap.put(Integer.valueOf(skillEntity.getSkillId()), Integer.valueOf(skillEntity.getLevel()));
/*     */               } 
/*     */             } else {
/* 703 */               for (IMapEntity entity : skillComponent.getEntityMap().values()) {
/* 704 */                 SkillEntity skillEntity = (SkillEntity)entity;
/* 705 */                 NewSkillBean newSkillBean = (NewSkillBean)JsonTableService.getJsonData(skillEntity.getSkillId(), NewSkillBean.class);
/* 706 */                 if (newSkillBean.getType() != 2) {
/* 707 */                   skillMap.put(Integer.valueOf(skillEntity.getSkillId()), Integer.valueOf(skillEntity.getLevel()));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           }
/* 712 */           return new CommonFighterData(pid, playerComponent.getLeaderId(), playerComponent.getVip(), playerComponent.getSex(), playerComponent.getHead(), playerComponent
/* 713 */               .getPlayerName(), playerComponent.getTotalValue(), (byte)0, playerComponent.getSex(), skillMap, playerComponent
/* 714 */               .getLevel(), baseAttrs, attrs, playerComponent.getQuality(), (int)playerComponent.getFirstHand(), playerComponent.getWearFashion(), 
/* 715 */               EquipUtil.getTalismanByPid(playerId, pid));
/*     */         }
/*     */       
/*     */       }
/* 719 */       else if (fighterType == 3) {
/* 720 */         if (!PlayerUtil.isRemotePlayer(playerId)) {
/*     */           PlayerComponent playerComponent;
/* 722 */           if (!LookUpService.isOnline(playerId)) {
/* 723 */             IPlayer player = LookUpService.forceGetPlayer(playerId);
/* 724 */             playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 725 */             playerComponent.getPlayerAttrUp().initBase(playerComponent, -1L);
/* 726 */             playerComponent.getPlayerAttrUp().initAll(player, -1L);
/*     */           } else {
/* 728 */             playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*     */           } 
/* 730 */           if (playerComponent == null) {
/* 731 */             throw new NullPointerException("get playerComponent exception, playerId: " + playerId);
/*     */           }
/*     */           
/* 734 */           WarPetComponent warPetComponent = (WarPetComponent)LookUpService.getComponent(playerId, WarPetComponent.class);
/* 735 */           if (null == warPetComponent) {
/* 736 */             throw new NullPointerException("get warPetComponent exception, playerId: " + playerId);
/*     */           }
/* 738 */           Skill petSkill = warPetComponent.buildBattlePetSkill();
/* 739 */           WarPetEntity warPetEntity = warPetComponent.getBattleWarPet();
/* 740 */           if (warPetEntity == null) {
/* 741 */             return null;
/*     */           }
/* 743 */           int warPetId = warPetEntity.getWarPetId();
/* 744 */           Map<Integer, Integer> skillMap = new HashMap<>();
/* 745 */           skillMap.put(Integer.valueOf(petSkill.getSkillId()), Integer.valueOf(petSkill.getLevel()));
/*     */ 
/*     */           
/* 748 */           long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*     */           
/* 750 */           long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 751 */           for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */             
/* 753 */             long val = playerComponent.getPlayerAttrUp().getAttrByType(type, -1L);
/* 754 */             if (type < AttributeType.BASE_ATTR_END.getType())
/* 755 */               baseAttrs[type] = val; 
/* 756 */             attrs[type] = val;
/*     */           } 
/*     */ 
/*     */           
/* 760 */           return new CommonFighterData(pid, warPetId, (byte)0, (byte)0, "", "", playerComponent
/* 761 */               .getTotalValue(), (byte)3, playerComponent
/* 762 */               .getSex(), skillMap, playerComponent.getLevel(), baseAttrs, attrs, playerComponent.getQuality(), 0, 0, null);
/*     */         }
/*     */       
/*     */       }
/* 766 */       else if (fighterType == 1) {
/* 767 */         if (!PlayerUtil.isRemotePlayer(playerId)) {
/* 768 */           IPlayer iPlayer = LookUpService.forceGetPlayer(playerId);
/* 769 */           if (!LookUpService.isOnline(playerId)) {
/* 770 */             PartnerUtil.initBattlePartner(iPlayer);
/*     */           }
/* 772 */           PartnerComponent partnerComponent = (PartnerComponent)iPlayer.createIfNotExist(PartnerComponent.class);
/* 773 */           if (partnerComponent == null) {
/* 774 */             throw new NullPointerException("get partnerComponent exception, playerId: " + playerId);
/*     */           }
/*     */           
/* 777 */           long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*     */           
/* 779 */           long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 780 */           for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/* 781 */             long val = partnerComponent.getPartnerAttrUp().getAttrByType(type, pid);
/* 782 */             if (type < AttributeType.BASE_ATTR_END.getType())
/* 783 */               baseAttrs[type] = val; 
/* 784 */             attrs[type] = val;
/*     */           } 
/* 786 */           return new CommonFighterData(pid, partnerComponent.getEntity(pid).getTableId(), (byte)1, (short)partnerComponent.getEntity(pid).getStars(), baseAttrs, attrs, 
/* 787 */               EquipUtil.getTalismanByPid(playerId, pid));
/*     */         }
/*     */       
/*     */       }
/* 791 */       else if (fighterType == 4) {
/* 792 */         if (!PlayerUtil.isRemotePlayer(playerId)) {
/*     */           PlayerComponent playerComponent;
/* 794 */           if (!LookUpService.isOnline(playerId)) {
/* 795 */             IPlayer player = LookUpService.forceGetPlayer(playerId);
/* 796 */             playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 797 */             playerComponent.getPlayerAttrUp().initBase(playerComponent, -1L);
/* 798 */             playerComponent.getPlayerAttrUp().initAll(player, -1L);
/*     */           } else {
/* 800 */             playerComponent = (PlayerComponent)LookUpService.getComponent(playerId, PlayerComponent.class);
/*     */           } 
/* 802 */           if (playerComponent == null) {
/* 803 */             throw new NullPointerException("get playerComponent exception, playerId: " + playerId);
/*     */           }
/* 805 */           StageComponent stageComponent = (StageComponent)LookUpService.getComponent(playerId, StageComponent.class);
/* 806 */           if (null == stageComponent) {
/* 807 */             LogUtil.errorLog(new Object[] { "get stageComponent exception, playerId: " + playerId });
/* 808 */             return null;
/*     */           } 
/* 810 */           StageEntity stageEntity = stageComponent.getBattleStage();
/* 811 */           if (stageEntity == null) {
/* 812 */             return null;
/*     */           }
/* 814 */           int skillId = stageComponent.getBattleStageSkill();
/* 815 */           int stageId = stageEntity.getId();
/* 816 */           Map<Integer, Integer> skillMap = new HashMap<>();
/* 817 */           skillMap.put(Integer.valueOf(skillId), Integer.valueOf(1));
/*     */ 
/*     */           
/* 820 */           long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*     */           
/* 822 */           long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 823 */           for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */             
/* 825 */             long val = playerComponent.getPlayerAttrUp().getAttrByType(type, -1L);
/* 826 */             if (type < AttributeType.BASE_ATTR_END.getType())
/* 827 */               baseAttrs[type] = val; 
/* 828 */             attrs[type] = val;
/*     */           } 
/*     */           
/* 831 */           return new CommonFighterData(pid, stageId, (byte)0, (byte)0, "", "", playerComponent
/* 832 */               .getTotalValue(), (byte)4, playerComponent
/* 833 */               .getSex(), skillMap, (short)stageEntity.getLevel(), baseAttrs, attrs, playerComponent.getQuality(), 0, 0, null);
/*     */         }
/*     */       
/*     */       }
/* 837 */       else if (fighterType == 5) {
/* 838 */         SoulsComponent soulsComponent = (SoulsComponent)LookUpService.getComponent(playerId, SoulsComponent.class);
/* 839 */         if (null == soulsComponent) {
/* 840 */           throw new NullPointerException("get soulsComponent exception, playerId: " + playerId);
/*     */         }
/* 842 */         SoulsEntity soulsEntity = soulsComponent.getEntity((int)pid);
/* 843 */         if (soulsEntity == null) {
/* 844 */           throw new NullPointerException("get soulsEntity exception, playerId: " + playerId + ", pid: " + pid);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 852 */         return new CommonFighterData(pid, soulsEntity.getId(), (byte)5, (short)soulsEntity.getStar());
/*     */       } 
/* 854 */     } catch (Exception e) {
/* 855 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*     */     } 
/* 857 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addTempAttribute(IPlayer player, int type, PlayerFightSide playerFightSide) {
/*     */     long[] attrs;
/* 867 */     if (type == 8) {
/* 868 */       attrs = MilitaryUtil.getTempAttrbute(player.getPlayerId(), MilitaryInsType.SingleBoss.getType());
/* 869 */     } else if (type == 3) {
/* 870 */       attrs = MilitaryUtil.getTempAttrbute(player.getPlayerId(), MilitaryInsType.RankBoss.getType());
/* 871 */     } else if (type == 6) {
/* 872 */       attrs = MilitaryUtil.getTempAttrbute(player.getPlayerId(), MilitaryInsType.WorldBoss.getType());
/* 873 */     } else if (type == 14) {
/* 874 */       attrs = MilitaryUtil.getTempAttrbute(player.getPlayerId(), MilitaryInsType.GroupBoss.getType());
/*     */     } else {
/*     */       return;
/*     */     } 
/* 878 */     List<IFighter> fighters = playerFightSide.getCurGroup().getFighters(true);
/* 879 */     for (IFighter iFighter : fighters) {
/* 880 */       for (int attrType = 1; attrType < AttributeType.ATTRIB_TYPE_END.getType(); attrType++) {
/* 881 */         long val = attrs[attrType];
/* 882 */         iFighter.setAttr(attrType, iFighter.getAttr(attrType) + val);
/*     */       } 
/* 884 */       (iFighter.getFighterData()).hp = iFighter.getAttr(AttributeType.HP.getType());
/* 885 */       iFighter.setAttr(AttributeType.HP.getType(), iFighter.getAttr(AttributeType.HP.getType()));
/* 886 */       (iFighter.getFighterData()).maxHp = iFighter.getAttr(AttributeType.HP.getType());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\FightUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */