/*     */ package com.linlongyx.sanguo.webgame.common.fight;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
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
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.common.team.Team;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayer;
/*     */ import com.linlongyx.sanguo.webgame.common.team.TeamPlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.AttrBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.SoulsStarBean;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FightUtil
/*     */ {
/*     */   public static boolean isFront(byte pos) {
/*  43 */     return (pos % 6 < 4 && pos % 6 > 0);
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
/*  54 */     pos1 = (byte)(pos1 % 6);
/*  55 */     pos2 = (byte)(pos2 % 6);
/*     */     
/*  57 */     if (isFront(pos1))
/*  58 */       return isFront(pos2); 
/*  59 */     return !isFront(pos2);
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
/*  70 */     pos1 = (byte)(pos1 % 6);
/*  71 */     pos2 = (byte)(pos2 % 6);
/*     */     
/*  73 */     int abs = Math.abs(pos1 - pos2);
/*  74 */     return (abs == 3 || abs == 0);
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
/*  85 */     if (!sameRow(pos1, pos2))
/*  86 */       return false; 
/*  87 */     return (Math.abs(pos1 - pos2) == 1);
/*     */   }
/*     */   
/*     */   public static byte getTargetGuid(byte guid) {
/*  91 */     if (guid < 100) {
/*  92 */       guid = (byte)(guid + 100);
/*     */     } else {
/*  94 */       guid = (byte)(guid - 100);
/*  95 */     }  return guid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getTargetSide(IFighter fighter, byte side) {
/* 105 */     return (byte)((fighter.getSide() + side) % 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte getTargetSide(byte side) {
/* 115 */     return (byte)((side + 1) % 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBaseAttr(int type) {
/* 125 */     return (type >= AttributeType.ATTRIBUTE_NON.getType() && type < AttributeType.BASE_ATTR_END.getType());
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
/* 140 */     hurtValue.setValue(Long.valueOf(FightCalculator.hurtType(effect, attacker, target)));
/*     */     
/* 142 */     int damageType = effect.getBean().getDemageType();
/* 143 */     if (damageType != 0) {
/* 144 */       if (!FightCalculator.isHit(attacker, target)) {
/* 145 */         resultPlayData.targeAction = (byte)(resultPlayData.targeAction | 0x2);
/* 146 */         fight.appendDebugStr("; " + target + "闪避");
/* 147 */         hurtValue.setKey(Integer.valueOf(-1));
/*     */         
/* 149 */         if (effect.getBean().getDodge() == 1) {
/* 150 */           List<Integer> list = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 151 */           if (!list.isEmpty()) {
/* 152 */             for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 153 */               if (ret != 0) {
/* 154 */                 fight.appendDebugStr("; 闪避, 附加效果:" + ret + ";");
/*     */               } }
/*     */           
/*     */           }
/*     */         } 
/*     */         return;
/*     */       } 
/* 161 */       if (effect.getBean().getDodge() == 2) {
/* 162 */         List<Integer> list = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 163 */         if (!list.isEmpty()) {
/* 164 */           for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 165 */             if (ret != 0) {
/* 166 */               fight.appendDebugStr("; 命中, 无伤害, 附加效果:" + ret + ";");
/*     */             } }
/*     */         
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */     } else {
/* 174 */       hurtValue.setKey(Integer.valueOf(-2));
/* 175 */       List<Integer> list = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 176 */       if (!list.isEmpty()) {
/* 177 */         for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 178 */           if (ret != 0) {
/* 179 */             fight.appendDebugStr("; 附加效果:" + ret + ";");
/*     */           } }
/*     */       
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 187 */     buffEffect(attacker, target, hurtValue, resultPlayData);
/*     */ 
/*     */     
/* 190 */     List<Integer> retList = TargetSelector.affectTarget(fight, hurtValue, effect, attacker, target, resultPlayData);
/* 191 */     if (!retList.isEmpty()) {
/* 192 */       for (Iterator<Integer> iterator = retList.iterator(); iterator.hasNext(); ) { int ret = ((Integer)iterator.next()).intValue();
/* 193 */         if (ret != 0) {
/* 194 */           fight.appendDebugStr("; 附加效果:" + ret + ";");
/*     */         } }
/*     */     
/*     */     }
/*     */ 
/*     */     
/* 200 */     if (FightCalculator.isCrit(effect, attacker, target)) {
/* 201 */       resultPlayData.targeAction = (byte)(resultPlayData.targeAction | 0x1);
/* 202 */       hurtValue.setValue(Long.valueOf((long)(((Long)hurtValue.getValue()).longValue() * (1.0D + FightCalculator.critRate(attacker, target)))));
/* 203 */       fight.appendDebugStr("; " + target + "被暴击");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     long finalVal = FightCalculator.hurtValue(hurtValue, attacker, target);
/*     */ 
/*     */     
/*     */     try {
/* 215 */       finalVal = FightCalculator.finalHurtValue(finalVal, attacker, target);
/* 216 */     } catch (IndexOutOfBoundsException e) {
/* 217 */       LogUtil.errorLog(new Object[] { "old destiny player fighters", e.getMessage() });
/*     */     } 
/*     */     
/* 220 */     finalVal = formatHurtVal((finalVal < 0L) ? 0L : finalVal);
/* 221 */     hurtValue.setValue(Long.valueOf(finalVal));
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
/*     */   private static void buffEffect(IFighter attacker, IFighter target, Pair<Integer, Long> hurtValue, ResultPlayData resultPlayData) {
/* 233 */     Iterator<AbstractBuff> iter = attacker.getBuffList().iterator();
/* 234 */     while (iter.hasNext()) {
/* 235 */       AbstractBuff buff = iter.next();
/* 236 */       if (buff.getBean().getCalculationType() == 1) {
/* 237 */         buff.immediate(resultPlayData, ((Long)hurtValue.getValue()).longValue());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 242 */     iter = target.getBuffList().iterator();
/* 243 */     while (iter.hasNext()) {
/* 244 */       AbstractBuff buff = iter.next();
/* 245 */       if (buff.getTimes() != -1) {
/* 246 */         if (buff.getTimes() == 0) {
/* 247 */           if (!buff.getRoundReset())
/* 248 */             iter.remove(); 
/*     */           continue;
/*     */         } 
/* 251 */         buff.setTimes(buff.getTimes() - 1);
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
/* 264 */     IFighter minfighter = fighters.get(0);
/* 265 */     byte min = minfighter.getGuid();
/* 266 */     for (IFighter fighter : fighters) {
/* 267 */       if (fighter.getGuid() < min) {
/* 268 */         min = fighter.getGuid();
/* 269 */         minfighter = fighter;
/*     */       } 
/*     */     } 
/* 272 */     return minfighter;
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
/* 284 */     SoulsStarBean soulsStarBean = (SoulsStarBean)JsonTableService.getJsonData(id, SoulsStarBean.class);
/* 285 */     SoulsStarBean.StarBean bean = (SoulsStarBean.StarBean)soulsStarBean.getStar().get(Integer.valueOf(level));
/*     */     
/* 287 */     for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */       
/* 289 */       if (type < attrs.length) {
/*     */ 
/*     */ 
/*     */         
/* 293 */         for (FightGroup group : fightSide.getGroupList()) {
/* 294 */           for (IFighter fighter : group.getFighters(true)) {
/* 295 */             if (type < AttributeType.BASE_ATTR_END.getType()) {
/* 296 */               baseAttrs[type] = baseAttrs[type] + fighter.getBaseAttr(type);
/*     */             }
/*     */ 
/*     */             
/* 300 */             if (type == AttributeType.HP.getType()) {
/* 301 */               attrs[type] = attrs[type] + fighter.getBaseAttr(type); continue;
/*     */             } 
/* 303 */             attrs[type] = attrs[type] + fighter.getAttr(type);
/*     */           } 
/*     */         } 
/*     */         
/* 307 */         if (type < AttributeType.BASE_ATTR_END.getType()) {
/* 308 */           baseAttrs[type] = baseAttrs[type] / (6 * fightSide.getGroupList().size());
/*     */         }
/* 310 */         attrs[type] = attrs[type] / (6 * fightSide.getGroupList().size());
/*     */       } 
/* 312 */     }  for (AttrBean attrBean : bean.getAttr()) {
/* 313 */       if (attrBean.getId() < AttributeType.BASE_ATTR_END.getType()) {
/* 314 */         baseAttrs[attrBean.getId()] = baseAttrs[attrBean.getId()] * attrBean.getNum() / 10000L;
/*     */       }
/* 316 */       attrs[attrBean.getId()] = attrs[attrBean.getId()] * attrBean.getNum() / 10000L;
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
/* 343 */     if (hurtVal < 1L)
/* 344 */       return 1L; 
/* 345 */     return hurtVal;
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
/* 361 */     long val = base;
/* 362 */     val = val * 10000L / 10000L;
/*     */     
/* 364 */     return val;
/*     */   }
/*     */   
/*     */   public static PlayerFightSide createTeamPlayerFightSide(Team team, boolean copyAttr, boolean isOpposite) {
/* 368 */     PlayerFightSide playerFightSide = new PlayerFightSide();
/* 369 */     List<IFighter> fighters = new ArrayList<>();
/* 370 */     List<Pair<Integer, Integer>> candidateFighters = null;
/* 371 */     Pair<Integer, Integer> zhenfa = null;
/* 372 */     for (TeamPlayer teamPlayer : team.getTeamPlayers()) {
/* 373 */       if (teamPlayer != null) {
/* 374 */         if (!teamPlayer.isRobot()) {
/* 375 */           if (teamPlayer.isLeader()) {
/* 376 */             candidateFighters = teamPlayer.getCandidateFighters();
/* 377 */             zhenfa = teamPlayer.getZhenfa();
/* 378 */             if (teamPlayer.isNeedReInit()) {
/* 379 */               CommonFighterData petFighterData = getFighterData(-1L, teamPlayer.getPlayerId(), (byte)3);
/* 380 */               if (petFighterData != null) {
/* 381 */                 PetFighter petFighter = new PetFighter(petFighterData, true);
/* 382 */                 teamPlayer.setPetFighter(new TeamPlayerFighter(false, (IFighter)petFighter));
/*     */               } 
/* 384 */               CommonFighterData stageFighterData = getFighterData(-1L, teamPlayer.getPlayerId(), (byte)4);
/* 385 */               if (stageFighterData != null) {
/* 386 */                 StageFighter stageFighter = new StageFighter(stageFighterData, true);
/* 387 */                 teamPlayer.setStageFighter(new TeamPlayerFighter(false, (IFighter)stageFighter));
/*     */               } 
/*     */             } 
/* 390 */             if (teamPlayer.getPetFighter() != null) {
/* 391 */               IFighter fighter = teamPlayer.getPetFighter().getFighter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 400 */               fighter.setPos((byte)0);
/* 401 */               (fighter.getFighterData()).pos = 0;
/* 402 */               playerFightSide.setPetFighter(fighter);
/*     */             } 
/* 404 */             if (teamPlayer.getStageFighter() != null) {
/* 405 */               IFighter fighter = teamPlayer.getStageFighter().getFighter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 413 */               fighter.setPos((byte)10);
/* 414 */               (fighter.getFighterData()).pos = 10;
/* 415 */               playerFightSide.setStageFighter(fighter);
/*     */             } 
/*     */           } 
/* 418 */           for (TeamPlayerFighter teamPlayerFighter : teamPlayer.getFighters()) {
/* 419 */             if (teamPlayerFighter != null) {
/* 420 */               if (teamPlayer.isNeedReInit()) {
/* 421 */                 if (teamPlayerFighter.getFighter().getPid() == -1L) {
/* 422 */                   CommonFighterData commonFighterData = getFighterData(teamPlayerFighter.getFighter().getPid(), teamPlayer.getPlayerId(), (byte)0);
/* 423 */                   teamPlayerFighter.setFighter((IFighter)new PlayerFighter(commonFighterData, (byte)teamPlayerFighter.getPos(), true));
/*     */                 } else {
/* 425 */                   CommonFighterData commonFighterData = getFighterData(teamPlayerFighter.getFighter().getPid(), teamPlayer.getPlayerId(), (byte)1);
/* 426 */                   teamPlayerFighter.setFighter((IFighter)new FighterFighter(commonFighterData, (byte)teamPlayerFighter.getPos(), teamPlayerFighter.getFighter().getPid(), true));
/*     */                 } 
/*     */               }
/*     */               
/* 430 */               teamPlayerFighter.getFighter().setPos((byte)teamPlayerFighter.getPos());
/* 431 */               (teamPlayerFighter.getFighter().getFighterData()).pos = (byte)teamPlayerFighter.getPos();
/* 432 */               fighters.add(teamPlayerFighter.getFighter());
/*     */             } 
/*     */           } 
/* 435 */           if (!teamPlayer.isLeader()) {
/* 436 */             IPlayerSession playerSession = null;
/* 437 */             if (!PlayerUtil.isRemotePlayer(teamPlayer.getPlayerId())) {
/* 438 */               playerSession = LookUpService.getByPlayerId(teamPlayer.getPlayerId()).getSession();
/*     */             }
/*     */ 
/*     */             
/* 442 */             playerFightSide.addPlayerSession(teamPlayer.getPlayerId(), playerSession);
/*     */           }  continue;
/*     */         } 
/* 445 */         for (TeamPlayerFighter teamPlayerFighter : teamPlayer.getFighters()) {
/* 446 */           if (teamPlayerFighter != null) {
/* 447 */             teamPlayerFighter.getFighter().setPos((byte)teamPlayerFighter.getPos());
/* 448 */             fighters.add(teamPlayerFighter.getFighter());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 454 */     FightGroup group = new FightGroup();
/* 455 */     group.setFighters(fighters);
/* 456 */     group.setId(team.getLeaderId());
/* 457 */     group.setName(team.getLeaderName());
/* 458 */     group.setFirstHandVal(team.getFirstHandVal());
/* 459 */     playerFightSide.addGroup(group);
/*     */ 
/*     */     
/* 462 */     if (candidateFighters != null) {
/* 463 */       for (Pair<Integer, Integer> kv : candidateFighters) {
/* 464 */         long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/* 465 */         long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 466 */         initcandidateFighterAttr((IFightSide)playerFightSide, ((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 467 */         CandidateFighter candidateFighter = new CandidateFighter(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 468 */         playerFightSide.addCandidateFigher((IFighter)candidateFighter);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 473 */     if (zhenfa != null) {
/* 474 */       playerFightSide.setZhenfa(zhenfa);
/*     */     }
/*     */     
/* 477 */     return playerFightSide;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PlayerFightSide createPvpFightSide(Map<Integer, CommonFighterData> fighterMap, List<Pair<Integer, Integer>> candidateFighters, Pair<Integer, Integer> zhenfa, long playerId, String playerName, int firstHandVal, boolean copyAttr) {
/* 487 */     PlayerFightSide playerFightSide = new PlayerFightSide();
/* 488 */     List<IFighter> fighters = new ArrayList<>();
/*     */     
/* 490 */     for (Map.Entry<Integer, CommonFighterData> kv : fighterMap.entrySet()) {
/* 491 */       FighterFighter fighterFighter; if (((Integer)kv.getKey()).intValue() == 0) {
/* 492 */         PetFighter petFighter = new PetFighter(kv.getValue(), copyAttr);
/* 493 */         playerFightSide.setPetFighter((IFighter)petFighter);
/* 494 */         playerFightSide.getPetFighter().setId((int)((CommonFighterData)kv.getValue()).getId());
/* 495 */       } else if (((Integer)kv.getKey()).intValue() == 10) {
/* 496 */         StageFighter stageFighter = new StageFighter(kv.getValue(), copyAttr);
/* 497 */         playerFightSide.setStageFighter((IFighter)stageFighter);
/* 498 */         playerFightSide.getStageFighter().setId((int)((CommonFighterData)kv.getValue()).getId());
/*     */       }
/* 500 */       else if (((CommonFighterData)kv.getValue()).getPid() == -1L) {
/* 501 */         PlayerFighter playerFighter = new PlayerFighter(kv.getValue(), ((Integer)kv.getKey()).byteValue(), copyAttr);
/* 502 */         fighters.add(playerFighter);
/*     */       } else {
/* 504 */         fighterFighter = new FighterFighter(kv.getValue(), ((Integer)kv.getKey()).byteValue(), ((CommonFighterData)kv.getValue()).getPid(), copyAttr);
/* 505 */         fighters.add(fighterFighter);
/*     */       } 
/*     */ 
/*     */       
/* 509 */       if (((CommonFighterData)kv.getValue()).getTalisman() != null) {
/* 510 */         fighterFighter.setTalisman(((CommonFighterData)kv.getValue()).getTalisman());
/*     */       }
/*     */     } 
/* 513 */     FightGroup group = new FightGroup();
/* 514 */     group.setFighters(fighters);
/* 515 */     group.setId(playerId);
/* 516 */     group.setName(playerName);
/* 517 */     group.setFirstHandVal(firstHandVal);
/* 518 */     playerFightSide.addGroup(group);
/*     */ 
/*     */     
/* 521 */     if (candidateFighters != null) {
/* 522 */       for (Pair<Integer, Integer> kv : candidateFighters) {
/*     */         
/* 524 */         long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/* 525 */         long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 526 */         initcandidateFighterAttr((IFightSide)playerFightSide, ((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 527 */         CandidateFighter candidateFighter = new CandidateFighter(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue(), baseAttrs, attrs);
/* 528 */         playerFightSide.addCandidateFigher((IFighter)candidateFighter);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 533 */     if (zhenfa != null) {
/* 534 */       playerFightSide.setZhenfa(zhenfa);
/*     */     }
/* 536 */     return playerFightSide;
/*     */   }
/*     */   
/*     */   public static List<Pair<Integer, Integer>> getCandidateFighterList(long playerId) {
/* 540 */     Player player = (Player)LookUpService.getByPlayerId(playerId);
/* 541 */     if (player == null) return null; 
/* 542 */     return player.getPlayerData().getCandidateFighters();
/*     */   }
/*     */   
/*     */   public static Pair<Integer, Integer> getZhenfa(long playerId) {
/* 546 */     Player player = (Player)LookUpService.getByPlayerId(playerId);
/* 547 */     if (player == null) return null; 
/* 548 */     return player.getPlayerData().getZhenfa();
/*     */   }
/*     */   
/*     */   public static CommonFighterData getFighterData(long pid, long playerId, byte fighterType) throws NullPointerException {
/* 552 */     Player player = (Player)LookUpService.getByPlayerId(playerId);
/* 553 */     if (player == null) return null; 
/* 554 */     if (fighterType == 0) {
/*     */       
/* 556 */       for (CommonFighterData commonFighterData : player.getPlayerData().getFighters().values()) {
/* 557 */         if (commonFighterData.getPid() == -1L && commonFighterData.getType() == 0)
/* 558 */           return commonFighterData; 
/*     */       } 
/*     */     } else {
/* 561 */       if (fighterType == 3)
/* 562 */         return (CommonFighterData)player.getPlayerData().getFighters().get(Integer.valueOf(0)); 
/* 563 */       if (fighterType == 1) {
/* 564 */         for (CommonFighterData commonFighterData : player.getPlayerData().getFighters().values()) {
/* 565 */           if (commonFighterData.getPid() == pid && commonFighterData.getType() == 1) {
/* 566 */             return commonFighterData;
/*     */           }
/*     */         } 
/* 569 */       } else if (fighterType == 4) {
/* 570 */         return (CommonFighterData)player.getPlayerData().getFighters().get(Integer.valueOf(10));
/*     */       } 
/* 572 */     }  return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearBuff(IFightSide fightSide) {
/* 577 */     for (FightGroup fightGroup : fightSide.getGroupList()) {
/* 578 */       for (IFighter fighter : fightGroup.getFighters(true)) {
/* 579 */         fighter.clearBuff();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeDeadFighters(IFightSide fightSide) {
/* 586 */     for (FightGroup fightGroup : fightSide.getGroupList())
/* 587 */       fightGroup.removeDeadFighter(); 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\FightUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */