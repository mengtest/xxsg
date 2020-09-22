/*     */ package com.linlongyx.sanguo.webgame.common.fight.side;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.partner.PartnerEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.souls.SoulsEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.stage.StageEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warlineup.WarLineupEntity;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.warpet.WarPetEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.CandidateFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.FighterFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PetFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.PlayerFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.StageFighter;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*     */ import com.linlongyx.sanguo.webgame.processors.equip.EquipUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.partner.PartnerUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class PlayerFightSide
/*     */   extends AbstractFightSide
/*     */   implements IFightSide
/*     */ {
/*  42 */   private Map<Long, IPlayerSession> playerSessions = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(IPlayer player, List<Long> position) {
/*  51 */     initFighters(player, position);
/*     */   }
/*     */   
/*     */   private void initFighters(IPlayer player, List<Long> position) {
/*  55 */     PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/*  56 */     PartnerComponent partnerComponent = (PartnerComponent)player.createIfNotExist(PartnerComponent.class);
/*  57 */     if (!LookUpService.isOnline(player.getPlayerId())) {
/*  58 */       playerComponent.getPlayerAttrUp().initAll(player, -1L);
/*  59 */       PartnerUtil.initBattlePartner(player);
/*     */     } 
/*  61 */     Map<Long, Map<Integer, Integer>> talismamMap = Collections.unmodifiableMap(EquipUtil.getTalismanMap(player.getPlayerId()));
/*  62 */     List<IFighter> fighters = new ArrayList<>();
/*  63 */     byte pos = 0;
/*  64 */     IFighter fighter = null;
/*  65 */     for (Long f : position) {
/*  66 */       pos = (byte)(pos + 1);
/*  67 */       if (f.longValue() == -1L) {
/*  68 */         PlayerFighter playerFighter = new PlayerFighter(playerComponent, pos);
/*  69 */         fighters.add(playerFighter);
/*  70 */       } else if (f.longValue() > 0L) {
/*  71 */         PartnerEntity partnerEntity = partnerComponent.getEntity(f.longValue());
/*  72 */         if (null == partnerEntity) {
/*     */           continue;
/*     */         }
/*  75 */         fighterFighter = new FighterFighter(partnerComponent, pos, f.longValue());
/*  76 */         fighters.add(fighterFighter);
/*     */       } 
/*  78 */       if (fighterFighter != null && talismamMap.containsKey(Long.valueOf(fighterFighter.getPid()))) {
/*  79 */         fighterFighter.setTalisman(talismamMap.get(Long.valueOf(fighterFighter.getPid())));
/*     */       }
/*     */       
/*  82 */       FighterFighter fighterFighter = null;
/*     */     } 
/*  84 */     FightGroup group = new FightGroup();
/*  85 */     group.setFighters(fighters);
/*  86 */     group.setId(player.getPlayerId());
/*  87 */     group.setName(player.getPlayerName());
/*  88 */     group.setFirstHandVal((int)playerComponent.getFirstHand());
/*  89 */     addGroup(group);
/*  90 */     addPlayerSession(player.getPlayerId(), player.getSession());
/*     */     
/*  92 */     initStageFighter(playerComponent, player);
/*  93 */     initWarPet(playerComponent, player);
/*  94 */     initZhenfa(player);
/*  95 */     initCandidateFighters(player);
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
/*     */   private void initStageFighter(PlayerComponent playerComponent, IPlayer player) {
/* 113 */     StageComponent stageComponent = (StageComponent)player.createIfNotExist(StageComponent.class);
/* 114 */     if (stageComponent == null) {
/*     */       return;
/*     */     }
/* 117 */     StageEntity stageEntity = stageComponent.getBattleStage();
/* 118 */     if (stageEntity == null) {
/*     */       return;
/*     */     }
/* 121 */     Skill stageSkill = new Skill(stageComponent.getBattleStageSkill(), 1);
/* 122 */     StageFighter stageFighter = new StageFighter(playerComponent, (byte)10);
/* 123 */     stageFighter.initSkill(stageSkill);
/* 124 */     stageFighter.setLevel((short)stageEntity.getLevel());
/* 125 */     stageFighter.setStageId(stageEntity.getId());
/* 126 */     this.stageFighter = (IFighter)stageFighter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initCandidateFighters(IPlayer player) {
/* 134 */     PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/* 135 */     SoulsComponent soulsComponent = (SoulsComponent)player.getComponent(SoulsComponent.class);
/* 136 */     if (playerComponent == null || soulsComponent == null) {
/*     */       return;
/*     */     }
/* 139 */     List<Integer> candidateFighters = playerComponent.getSoulsFighter();
/* 140 */     if (!candidateFighters.isEmpty()) {
/* 141 */       for (Iterator<Integer> iterator = candidateFighters.iterator(); iterator.hasNext(); ) { int id = ((Integer)iterator.next()).intValue();
/* 142 */         if (id <= 0)
/* 143 */           continue;  SoulsEntity soulsEntity = soulsComponent.getEntity(id);
/* 144 */         long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/* 145 */         long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/* 146 */         FightUtil.initcandidateFighterAttr(this, id, soulsEntity.getStar(), baseAttrs, attrs);
/* 147 */         CandidateFighter candidateFighter = new CandidateFighter(id, soulsEntity.getStar(), baseAttrs, attrs);
/* 148 */         addCandidateFigher((IFighter)candidateFighter); }
/*     */     
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
/*     */   private void initWarPet(PlayerComponent playerComponent, IPlayer player) {
/* 164 */     WarPetComponent petComponent = (WarPetComponent)player.createIfNotExist(WarPetComponent.class);
/* 165 */     if (null == petComponent)
/*     */       return; 
/* 167 */     Skill petSkill = petComponent.buildBattlePetSkill();
/* 168 */     WarPetEntity warPetEntity = petComponent.getBattleWarPet();
/* 169 */     if (null != warPetEntity) {
/* 170 */       int pId = warPetEntity.getWarPetId();
/* 171 */       if (petSkill != null) {
/* 172 */         PetFighter petFighter = new PetFighter(playerComponent, (byte)0);
/* 173 */         petFighter.initSkill(petSkill);
/* 174 */         petFighter.setId(pId);
/* 175 */         this.petFighter = (IFighter)petFighter;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initZhenfa(IPlayer player) {
/* 186 */     WarLineupComponent warLineupComponent = (WarLineupComponent)player.createIfNotExist(WarLineupComponent.class);
/* 187 */     if (warLineupComponent != null) {
/* 188 */       WarLineupEntity entity = warLineupComponent.getBattleWarLineup();
/* 189 */       if (entity != null) {
/* 190 */         this.zhenfa = new Pair(Integer.valueOf(entity.getWarLineupId()), Integer.valueOf(entity.getStar()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncRecord(ResponseBase response) {
/* 199 */     this.playerSessions.values().forEach(playerSession -> playerSession.sendMessage(response));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPlayerSession(long playerId, IPlayerSession playerSession) {
/* 209 */     this.playerSessions.put(Long.valueOf(playerId), playerSession);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\side\PlayerFightSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */