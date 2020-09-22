/*     */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*     */ 
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.sanguo.webgame.app.kungfu.KungFuComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.skill.SkillComponent;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerFighter
/*     */   extends AbstractFighter
/*     */   implements IFighter
/*     */ {
/*     */   public PlayerFighter() {}
/*     */   
/*     */   public PlayerFighter(PlayerComponent component, byte pos) {
/*     */     Player player;
/*  31 */     this.id = component.getLeaderId();
/*  32 */     this.type = 0;
/*  33 */     this.hurtType = component.getSex();
/*  34 */     this.pos = pos;
/*  35 */     this.fury = 0;
/*  36 */     this.isAuto = true;
/*  37 */     this.pid = -1L;
/*     */     
/*  39 */     IPlayer iPlayer = LookUpService.getByPlayerId(component.getPlayerId());
/*     */     
/*  41 */     if (iPlayer == null) {
/*  42 */       PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/*  43 */       builder.validateAndSetValues();
/*  44 */       IPlayerSession playerSession = (IPlayerSession)builder.status(ISession.Status.CLOSED).isLogin(false).writeable(false).build();
/*  45 */       player = new Player(playerSession);
/*  46 */       player.setPlayerId(component.getPlayerId());
/*  47 */       playerSession.setPlayer((IPlayer)player);
/*     */     } 
/*     */     
/*  50 */     SkillComponent skillComponent = (SkillComponent)player.createIfNotExist(SkillComponent.class);
/*  51 */     KungFuComponent kungFuComponent = (KungFuComponent)player.createIfNotExist(KungFuComponent.class);
/*  52 */     for (Skill skill : skillComponent.getSkills()) {
/*  53 */       addSKill(skill);
/*     */     }
/*     */     
/*  56 */     Skill furySkill = kungFuComponent.getFurySkill();
/*  57 */     if (furySkill != null) {
/*  58 */       addSKill(furySkill);
/*     */     }
/*     */ 
/*     */     
/*  62 */     this.fighterData.id = this.id;
/*  63 */     this.fighterData.fashionId = component.getWearFashion();
/*  64 */     this.fighterData.type = this.type;
/*  65 */     this.fighterData.level = component.getLevel();
/*  66 */     this.fighterData.quality = component.getQuality();
/*  67 */     for (int type = 1; type < AttributeType.ATTRIB_TYPE_END.getType(); type++) {
/*     */       
/*  69 */       long val = component.getPlayerAttrUp().getAttrByType(type, -1L);
/*  70 */       if (type < AttributeType.BASE_ATTR_END.getType())
/*  71 */         this.baseAttrs[type] = val; 
/*  72 */       this.attrs[type] = val;
/*     */     } 
/*  74 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*     */     
/*  76 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerFighter(CommonFighterData commonFighterData, byte pos) {
/*  81 */     this.id = commonFighterData.getId();
/*  82 */     this.type = commonFighterData.getType();
/*  83 */     this.hurtType = commonFighterData.getHurtType();
/*  84 */     this.pos = pos;
/*  85 */     this.fury = 0;
/*  86 */     this.isAuto = true;
/*  87 */     this.talisman = commonFighterData.getTalisman();
/*  88 */     this.pid = -1L;
/*     */     
/*  90 */     for (Map.Entry<Integer, Integer> kv : commonFighterData.getSkillMap().entrySet()) {
/*  91 */       addSKill(new Skill(((Integer)kv.getKey()).intValue(), ((Integer)kv.getValue()).intValue()));
/*     */     }
/*     */     
/*  94 */     this.fighterData.id = this.id;
/*  95 */     this.fighterData.fashionId = commonFighterData.getFashionId();
/*  96 */     this.fighterData.quality = commonFighterData.getQuality();
/*  97 */     this.fighterData.level = commonFighterData.getLevel();
/*  98 */     this.baseAttrs = Arrays.copyOf(commonFighterData.getBaseAttrs(), (commonFighterData.getBaseAttrs()).length);
/*  99 */     this.attrs = Arrays.copyOf(commonFighterData.getAttrs(), (commonFighterData.getAttrs()).length);
/* 100 */     this.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*     */     
/* 102 */     this.fury = (byte)(int)this.attrs[AttributeType.INIT_FURY.getType()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSKill(Skill skill) {
/* 110 */     if (skill.getType() == 1) {
/* 111 */       setCmmSkill(skill);
/* 112 */     } else if (skill.getType() == 2) {
/* 113 */       setFurySkill(skill);
/* 114 */     } else if (skill.getType() == 3) {
/* 115 */       setFitSkill(skill);
/* 116 */     } else if (skill.getType() == 5) {
/* 117 */       setCmmSkill(skill);
/* 118 */     } else if (skill.getType() == 6) {
/* 119 */       setCmmSkill(skill);
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
/*     */   public byte getMaxFury() {
/* 132 */     return 4;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\PlayerFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */