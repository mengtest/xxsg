/*     */ package com.linlongyx.sanguo.webgame.common.attribute;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.MD5;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.task.TaskComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttrUpdateUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.DestinyAttrUp;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.PartnerEquipAttrUp;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.SkillAttrUp;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.ExpLeaderBean;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.FighterBean;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.AttrUpdateResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.common.PartnerAttrUpdateResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.AttrValue;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class PartnerAttrUpdate {
/*  36 */   public Map<Long, long[]> mapBaseAttrs = (Map)new HashMap<>();
/*  37 */   public Map<Long, Set<Integer>> mapUpdateSet = new HashMap<>();
/*     */   
/*  39 */   private Map<Long, Set<Integer>> mapOldUpdateSet = new HashMap<>();
/*  40 */   private Map<Long, Set<Integer>> mapNewUpdateSet = new HashMap<>();
/*     */ 
/*     */   
/*  43 */   private Map<Long, AttrUpBase[]> attrUpBases = (Map)new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(long playerId) {
/*  49 */     IPlayerSession playerSession = null;
/*  50 */     if (LookUpService.getByPlayerId(playerId) != null) {
/*  51 */       playerSession = LookUpService.getByPlayerId(playerId).getSession();
/*     */     }
/*  53 */     if (null == playerSession) {
/*     */       return;
/*     */     }
/*  56 */     IPlayer player = playerSession.getPlayer();
/*  57 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  58 */     for (IMapEntity iMapEntity : partnerComponent.getEntityMap().values()) {
/*  59 */       PartnerEntity partnerEntity = (PartnerEntity)iMapEntity;
/*  60 */       initBase(null, partnerEntity.getPid());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initPlayer(PlayerComponent playerComponent, long pid) {
/*  68 */     initBase(playerComponent, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initAll(IPlayer player, long pid) {
/*  77 */     for (int i = 1; i < PlayerAttrUp.AttrUpType.END.getIndex(); i++) {
/*     */       
/*  79 */       if (this.attrUpBases.get(Long.valueOf(pid)) != null) {
/*  80 */         AttrUpBase attrUpBase = ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[i];
/*  81 */         if (null != attrUpBase) {
/*  82 */           if (pid == -1L) {
/*  83 */             attrUpBase.refresh(player, this.mapUpdateSet.get(Long.valueOf(pid)));
/*     */           } else {
/*  85 */             attrUpBase.refreshPartner(player, this.mapUpdateSet.get(Long.valueOf(pid)), pid);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*  90 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  91 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  92 */     PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/*  93 */     if (null == partnerEntity) {
/*  94 */       if (pid == -1L) {
/*  95 */         PlayerUtil.updatePlayerFightValue(playerComponent, true);
/*     */       }
/*     */     } else {
/*  98 */       long fightValue = partnerComponent.getPartnerAttrUp().getFightValue(partnerEntity.getLevel(), pid);
/*  99 */       PartnerUtil.updatePartnerFightValue(playerComponent, partnerEntity, fightValue);
/* 100 */       partnerComponent.updateFightValueDB(partnerEntity.getPid());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initBase(PlayerComponent playerComponent, long pid) {
/* 109 */     long[] baseAttrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 110 */     Set<Integer> updateSet = new HashSet<>();
/*     */     
/* 112 */     this.mapBaseAttrs.put(Long.valueOf(pid), baseAttrs);
/* 113 */     this.mapUpdateSet.put(Long.valueOf(pid), updateSet);
/* 114 */     this.attrUpBases.put(Long.valueOf(pid), new AttrUpBase[PlayerAttrUp.AttrUpType.END.getIndex()]);
/* 115 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.EQUIP.getIndex()] = (AttrUpBase)new EquipAttrUp();
/* 116 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.PARTNER.getIndex()] = (AttrUpBase)new PartnerAttrUp();
/* 117 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.SKILL.getIndex()] = (AttrUpBase)new SkillAttrUp();
/* 118 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.PARTNER_EQUIP.getIndex()] = (AttrUpBase)new PartnerEquipAttrUp();
/* 119 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.LEADER.getIndex()] = (AttrUpBase)new LeaderAttrUp();
/* 120 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.WARPET.getIndex()] = (AttrUpBase)new WarPetAttrUp();
/* 121 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.GROUP.getIndex()] = (AttrUpBase)new GroupAttrUp();
/* 122 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.TITLE.getIndex()] = (AttrUpBase)new TitleAttrUp();
/* 123 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.MOUNTS.getIndex()] = (AttrUpBase)new MountsAttrUp();
/* 124 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.FASHION.getIndex()] = (AttrUpBase)new FashionAttrUp();
/* 125 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.GROWTHGOAL.getIndex()] = (AttrUpBase)new GrowthGoalAttrUp();
/* 126 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.DESTINY.getIndex()] = (AttrUpBase)new DestinyAttrUp();
/* 127 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.CARDBOOK.getIndex()] = (AttrUpBase)new CardBookAttrUp();
/* 128 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.MILITARY.getIndex()] = (AttrUpBase)new MilitaryAttrUp();
/* 129 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.KUNGFU.getIndex()] = (AttrUpBase)new KungFuAttrUp();
/* 130 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.STAGE.getIndex()] = (AttrUpBase)new StageAttrUp();
/* 131 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.WARZHENFA.getIndex()] = (AttrUpBase)new WarLineupAttrUp();
/* 132 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.SOULS.getIndex()] = (AttrUpBase)new SoulsAttrUp();
/* 133 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.ASSIST.getIndex()] = (AttrUpBase)new AssistAttrUp();
/* 134 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.ARTIFACT.getIndex()] = (AttrUpBase)new ArtifactAttrUp();
/* 135 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.RUNE.getIndex()] = (AttrUpBase)new RuneAttrUp();
/* 136 */     ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[PlayerAttrUp.AttrUpType.REINCARN.getIndex()] = (AttrUpBase)new ReincarnAttrUp();
/* 137 */     if (playerComponent != null && pid == -1L) {
/* 138 */       initLevelAttr(playerComponent, playerComponent.getLevel());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public long getAttrList(List<Long> arrayList, long pid) {
/* 144 */     arrayList.clear();
/* 145 */     long total = 0L;
/* 146 */     for (int type = AttributeType.ATTRIBUTE_NON.getType(); type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/* 147 */       long val = getAttrByType(type, pid);
/* 148 */       arrayList.add(Long.valueOf(val));
/* 149 */       total += val;
/*     */     } 
/* 151 */     return total;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initLevelAttr(PlayerComponent playerComponent, int level) {
/* 156 */     long pid = -1L;
/* 157 */     ExpLeaderBean bean = (ExpLeaderBean)JsonTableService.getJsonData(level, ExpLeaderBean.class);
/* 158 */     if (null == bean) {
/* 159 */       LogUtil.errorLog(new Object[] { "AttrUpBase::init|bean of level is null: ", Integer.valueOf(level) });
/*     */       return;
/*     */     } 
/* 162 */     FighterBean fighterBean = (FighterBean)JsonTableService.getJsonData(playerComponent.getLeaderId(), FighterBean.class);
/* 163 */     if (null == fighterBean) {
/*     */       return;
/*     */     }
/* 166 */     Arrays.fill(this.mapBaseAttrs.get(Long.valueOf(pid)), 0L);
/* 167 */     AttrUpdateUtil.fighterLevelAttr(bean, fighterBean, this.mapBaseAttrs.get(Long.valueOf(pid)), this.mapUpdateSet.get(Long.valueOf(pid)), playerComponent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long getBaseAttr(int type, long pid) {
/* 178 */     long[] baseAttrs = this.mapBaseAttrs.get(Long.valueOf(pid));
/* 179 */     if (null == baseAttrs) {
/* 180 */       return 0L;
/*     */     }
/* 182 */     if (type < 0 || type >= baseAttrs.length)
/* 183 */       return 0L; 
/* 184 */     return baseAttrs[type];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private long updateAttrValue(List<AttrValue> attrValueList, long pid) {
/* 190 */     for (Iterator<Integer> iterator = ((Set)this.mapUpdateSet.get(Long.valueOf(pid))).iterator(); iterator.hasNext(); ) { int update = ((Integer)iterator.next()).intValue();
/* 191 */       AttrValue attrValue = new AttrValue();
/* 192 */       attrValue.attrType = (byte)update;
/* 193 */       attrValue.value = getAttrByType(update, pid);
/* 194 */       attrValueList.add(attrValue); }
/*     */ 
/*     */     
/* 197 */     long total = 0L;
/* 198 */     for (int type = AttributeType.ATTRIBUTE_NON.getType(); type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/* 199 */       long val = getAttrByType(type, pid);
/* 200 */       total += val;
/*     */     } 
/* 202 */     return total;
/*     */   }
/*     */   
/*     */   private long getBaseAttr(long val, int type, int level, long pid) {
/* 206 */     if (type == AttributeType.ATTACK.getType())
/* 207 */       return FightUtil.getBaseAttrAdd(val, level); 
/* 208 */     if (type == AttributeType.PHY_DEF.getType())
/* 209 */       return FightUtil.getBaseAttrAdd(val, level); 
/* 210 */     if (type == AttributeType.MAG_DEF.getType())
/* 211 */       return FightUtil.getBaseAttrAdd(val, level); 
/* 212 */     if (type == AttributeType.HP.getType()) {
/* 213 */       return FightUtil.getBaseAttrAdd(val, level);
/*     */     }
/* 215 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAttrByType(int type, long pid) {
/* 225 */     long total = getBaseAttr(type, pid);
/* 226 */     for (AttrUpBase attrUp : (AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid))) {
/* 227 */       if (null != attrUp) {
/* 228 */         total += attrUp.getAttrByType(type);
/*     */       }
/*     */     } 
/* 231 */     if (FightUtil.isBaseAttr(type))
/* 232 */       total += total * getBaseAttrPerByType(type, pid) / 10000L; 
/* 233 */     return total;
/*     */   }
/*     */   
/*     */   public long getBaseAttrPerByType(int baseType, long pid) {
/* 237 */     if (!FightUtil.isBaseAttr(baseType)) {
/* 238 */       return 0L;
/*     */     }
/* 240 */     return getAttrByType(baseType + 17, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshPartnerEquip(IPlayerSession iPlayerSession, long pid) {
/* 249 */     refresh(iPlayerSession, PlayerAttrUp.AttrUpType.PARTNER_EQUIP, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshPartner(IPlayerSession iPlayerSession, long pid) {
/* 259 */     refresh(iPlayerSession, PlayerAttrUp.AttrUpType.PARTNER, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshGrowthGoal(IPlayerSession iPlayerSession, long pid) {
/* 268 */     refresh(iPlayerSession, PlayerAttrUp.AttrUpType.GROWTHGOAL, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshDestiny(IPlayerSession iPlayerSession, long pid) {
/* 277 */     refresh(iPlayerSession, PlayerAttrUp.AttrUpType.DESTINY, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void refreshReincarn(IPlayerSession iPlayerSession, long pid) {
/* 286 */     refresh(iPlayerSession, PlayerAttrUp.AttrUpType.REINCARN, pid);
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
/*     */   public static void refresh(IPlayerSession iPlayerSession, PlayerAttrUp.AttrUpType attrUpType, long pid) {
/* 298 */     PartnerAttrUpdateResponse attrUpdateResponse = new PartnerAttrUpdateResponse();
/* 299 */     attrUpdateResponse.pid = pid;
/* 300 */     PartnerComponent partnerComponent = (PartnerComponent)iPlayerSession.getPlayer().createIfNotExist(PartnerComponent.class);
/* 301 */     PlayerComponent playerComponent = (PlayerComponent)iPlayerSession.getPlayer().createIfNotExist(PlayerComponent.class);
/* 302 */     if (pid == -1L) {
/* 303 */       long total = playerComponent.getPlayerAttrUp().freshAttr(attrUpType.getIndex(), iPlayerSession.getPlayer(), attrUpdateResponse.attrValues, pid);
/* 304 */       long fightValue = playerComponent.getPlayerAttrUp().getFightValue(playerComponent.getLevel(), -1L);
/* 305 */       if (Math.abs(playerComponent.getFightValue() - fightValue) >= 3000L) {
/* 306 */         LogUtil.errorLog(new Object[] { "PartnerAttrUpdate playerFightValue", Long.valueOf(playerComponent.getPlayerId()), Integer.valueOf(attrUpType.getIndex()), Long.valueOf(playerComponent.getFightValue()), Long.valueOf(fightValue), Long.valueOf(playerComponent.getTotalValue()) });
/*     */       }
/* 308 */       AttrUpdateUtil.updatePlayerfight(iPlayerSession, total, attrUpdateResponse.attrValues);
/*     */     } else {
/* 310 */       long total = partnerComponent.getPartnerAttrUp().freshAttr(attrUpType.getIndex(), iPlayerSession.getPlayer(), attrUpdateResponse.attrValues, pid);
/* 311 */       attrUpdateResponse.checksum = (new MD5()).toDigest(iPlayerSession.getKey() + total);
/* 312 */       PartnerEntity partnerEntity = partnerComponent.getEntity(pid);
/* 313 */       long fightValue = partnerComponent.getPartnerAttrUp().getFightValue(partnerEntity.getLevel(), pid);
/*     */       
/* 315 */       if (Math.abs(partnerEntity.getFightValue() - fightValue) >= 3000L) {
/* 316 */         LogUtil.errorLog(new Object[] { "PartnerAttrUpdate partnerFightValue", Long.valueOf(playerComponent.getPlayerId()), Long.valueOf(partnerEntity.getFightValue()), Long.valueOf(fightValue), Long.valueOf(playerComponent.getTotalValue()), Integer.valueOf(attrUpType.getIndex()), Integer.valueOf(partnerEntity.getTableId()), Long.valueOf(pid) });
/*     */       }
/*     */       
/* 319 */       PartnerUtil.updatePartnerFightValue(playerComponent, partnerComponent.getEntity(pid), fightValue);
/* 320 */       partnerComponent.updateFightValueDB(pid);
/* 321 */       attrUpdateResponse.fightValue = fightValue;
/* 322 */       iPlayerSession.sendMessage((ResponseBase)attrUpdateResponse);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long freshAttr(int type, IPlayer player, List<AttrValue> attrValueList, long pid) {
/* 333 */     if (type >= ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid))).length)
/* 334 */       return 0L; 
/* 335 */     AttrUpBase attrUpBase = ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid)))[type];
/*     */     
/* 337 */     if (pid == -1L) {
/* 338 */       attrUpBase.refresh(player, this.mapUpdateSet.get(Long.valueOf(pid)));
/*     */     } else {
/* 340 */       attrUpBase.refreshPartner(player, this.mapUpdateSet.get(Long.valueOf(pid)), pid);
/*     */     } 
/*     */     
/* 343 */     return updateAttrValue(attrValueList, pid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getFightValue(int level, long pid) {
/* 352 */     int base = 0;
/* 353 */     AttrUpBase[] attrUpBaseg = this.attrUpBases.get(Long.valueOf(pid));
/* 354 */     for (AttrUpBase attrUpBase : attrUpBaseg) {
/* 355 */       if (null != attrUpBase) {
/* 356 */         base = (int)(base + attrUpBase.getSysFightValue());
/*     */       }
/*     */     } 
/* 359 */     long total = getAttrFightValue(level, pid);
/* 360 */     return total / 10000L + base;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getAttrFightValue(int level, long pid) {
/* 369 */     long total = 0L;
/* 370 */     for (int type = AttributeType.ATTRIBUTE_NON.getType(); type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/* 371 */       long val = getAttrByType(type, pid);
/*     */       
/* 373 */       if (type >= AttributeType.ATTACK.getType() && type <= AttributeType.HP.getType()) {
/* 374 */         total += getBaseAttr(val, type, level, pid) * FightConstant.getWeight(type);
/*     */       } else {
/* 376 */         total += val * FightConstant.getWeight(type);
/*     */       } 
/* 378 */     }  return total;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initAttr(IPlayer player, PartnerEntity partnerEntity) {
/* 388 */     for (int i = 1; i < PlayerAttrUp.AttrUpType.END.getIndex(); i++) {
/* 389 */       if (null != this.attrUpBases.get(Long.valueOf(partnerEntity.getPid())) && null != ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(partnerEntity.getPid())))[i]) {
/* 390 */         ((AttrUpBase[])this.attrUpBases.get(Long.valueOf(partnerEntity.getPid())))[i].refreshPartner(player, this.mapUpdateSet.get(Long.valueOf(partnerEntity.getPid())), partnerEntity.getPid());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void levelUp(IPlayer player, int level) {
/* 399 */     long pid = -1L;
/* 400 */     AttrUpdateResponse attrUpdateResponse = new AttrUpdateResponse();
/* 401 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 402 */     initLevelAttr(playerComponent, level);
/*     */     
/* 404 */     for (AttrUpBase attrUpBase : (AttrUpBase[])this.attrUpBases.get(Long.valueOf(pid))) {
/* 405 */       if (attrUpBase != null && attrUpBase.isBaseAffect) {
/* 406 */         attrUpBase.refresh(player, this.mapUpdateSet.get(Long.valueOf(pid)));
/*     */       }
/*     */     } 
/* 409 */     long total = updateAttrValue(attrUpdateResponse.attrValues, pid);
/* 410 */     attrUpdateResponse.checksum = (new MD5()).toDigest(player.getSession().getKey() + total);
/* 411 */     player.getSession().sendMessage((ResponseBase)attrUpdateResponse);
/*     */     
/* 413 */     PlayerUtil.updatePlayerFightValue(playerComponent, false);
/* 414 */     TaskComponent taskComponent = (TaskComponent)player.createIfNotExist(TaskComponent.class);
/* 415 */     taskComponent.refreshSchedule(TaskType.FightValue, 0, 0L);
/*     */   }
/*     */   
/*     */   public Map<Long, AttrUpBase[]> getAttrUpBases() {
/* 419 */     return this.attrUpBases;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\attribute\PartnerAttrUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */