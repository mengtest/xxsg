/*      */ package com.linlongyx.sanguo.webgame.common.fight.fighter;
/*      */ 
/*      */ import com.linlongyx.core.utils.LogUtils;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.buff.BuffFactory;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.buff.BuffType;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.select.target.TargetSelector;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.skill.Effect;
/*      */ import com.linlongyx.sanguo.webgame.common.fight.skill.Skill;
/*      */ import com.linlongyx.sanguo.webgame.common.structure.Pair;
/*      */ import com.linlongyx.sanguo.webgame.config.bean.BuffBean;
/*      */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BoutPlayData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.EffectPlayData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.FighterData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*      */ import com.linlongyx.sanguo.webgame.proto.binary.struct.SkillPlayData;
/*      */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
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
/*      */ public abstract class AbstractFighter
/*      */   implements IFighter
/*      */ {
/*      */   protected long id;
/*      */   protected byte type;
/*      */   protected byte hurtType;
/*      */   protected boolean isAuto;
/*      */   protected Skill furySkill;
/*      */   protected Skill fitSkill;
/*      */   protected Skill cmmSkill;
/*      */   protected Skill debutSkill;
/*      */   protected int killNum;
/*   75 */   protected FighterData fighterData = new FighterData();
/*      */ 
/*      */   
/*      */   protected byte pos;
/*      */ 
/*      */   
/*      */   protected byte fury;
/*      */ 
/*      */   
/*      */   protected byte atomFury;
/*      */ 
/*      */   
/*      */   protected byte side;
/*      */ 
/*      */   
/*      */   protected short level;
/*      */ 
/*      */   
/*      */   protected long maxHp;
/*      */   
/*      */   protected volatile boolean isDead = false;
/*      */   
/*      */   protected Map<Integer, Integer> talisman;
/*      */   
/*   99 */   protected List<AbstractBuff> buffs = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  104 */   protected long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  109 */   protected long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected BoutPlayData playData;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected long pid;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte getType() {
/*  125 */     return this.type;
/*      */   }
/*      */   
/*      */   public byte getHurtType() {
/*  129 */     return this.hurtType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAuto() {
/*  138 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void preStart(IFight fight) {
/*  143 */     Map<Integer, Long> damageBuffMap = new HashMap<>();
/*  144 */     List<AbstractBuff> damageBuffList = new ArrayList<>();
/*  145 */     Iterator<AbstractBuff> iter = this.buffs.iterator();
/*  146 */     while (iter.hasNext()) {
/*  147 */       AbstractBuff buff = iter.next();
/*  148 */       if (buff.tick()) {
/*  149 */         BoutPlayData playData = fight.genBoutPlayData();
/*  150 */         ResultPlayData resultPlayData = new ResultPlayData();
/*  151 */         resultPlayData.targetGuid = buff.getOwner().getGuid();
/*  152 */         resultPlayData.targeAction = 0;
/*  153 */         resultPlayData.hp = 0;
/*  154 */         resultPlayData.atomData.add(Integer.valueOf(2));
/*  155 */         resultPlayData.atomData.add(Integer.valueOf(buff.getBuffId()));
/*  156 */         playData.buffActions.add(resultPlayData);
/*  157 */         iter.remove(); continue;
/*      */       } 
/*  159 */       if (buff.getBean().getCalculationType() == 0) {
/*  160 */         long hurt = buff.calHurt();
/*  161 */         if (hurt > 0L) {
/*  162 */           if (!damageBuffMap.containsKey(Integer.valueOf(buff.getBuffId()))) {
/*  163 */             damageBuffMap.put(Integer.valueOf(buff.getBuffId()), Long.valueOf(hurt));
/*  164 */             damageBuffList.add(buff);
/*      */           } else {
/*  166 */             damageBuffMap.put(Integer.valueOf(buff.getBuffId()), Long.valueOf(hurt + ((Long)damageBuffMap.get(Integer.valueOf(buff.getBuffId()))).longValue()));
/*      */           } 
/*      */         } else {
/*  169 */           buff.effect(fight, true);
/*      */         } 
/*      */       } 
/*  172 */       if (buff.getRoundReset()) {
/*  173 */         buff.setTimes(buff.getBean().getTime());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  178 */     if (!damageBuffList.isEmpty()) {
/*  179 */       for (AbstractBuff buff : damageBuffList) {
/*  180 */         buff.effect(fight, true, ((Long)damageBuffMap.get(Integer.valueOf(buff.getBuffId()))).longValue());
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canFight() {
/*  191 */     if (isForbidAction())
/*  192 */       return false; 
/*  193 */     if (isDead())
/*  194 */       return false; 
/*  195 */     return true;
/*      */   }
/*      */   
/*      */   public long getId() {
/*  199 */     return this.id;
/*      */   }
/*      */   
/*      */   public void setId(long id) {
/*  203 */     this.id = id;
/*      */   }
/*      */   
/*      */   public byte getSide() {
/*  207 */     return this.side;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSide(byte side) {
/*  212 */     this.side = side;
/*      */   }
/*      */   
/*      */   public byte getGuid() {
/*  216 */     return this.fighterData.guid;
/*      */   }
/*      */   
/*      */   public byte getPos() {
/*  220 */     return this.pos;
/*      */   }
/*      */   
/*      */   public byte getCalcPos() {
/*  224 */     byte calcPos = (byte)(this.pos % 6);
/*  225 */     if (0 == calcPos) {
/*  226 */       return 6;
/*      */     }
/*  228 */     return calcPos;
/*      */   }
/*      */   
/*      */   public void setPos(byte pos) {
/*  232 */     this.pos = pos;
/*      */   }
/*      */   
/*      */   public Byte getFury() {
/*  236 */     return Byte.valueOf(this.fury);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte decFury(byte fury) {
/*  246 */     this.fury = (byte)(this.fury - fury);
/*  247 */     if (this.fury < 0)
/*  248 */       this.fury = 0; 
/*  249 */     return this.fury;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte addFury(byte fury, boolean ignoreMax) {
/*  259 */     this.fury = (byte)(this.fury + fury);
/*  260 */     if (!ignoreMax && this.fury > getMaxFury())
/*  261 */       this.fury = getMaxFury(); 
/*  262 */     return this.fury;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte addAbFury(byte fury) {
/*  273 */     this.fury = (byte)(this.fury + fury);
/*  274 */     return this.fury;
/*      */   }
/*      */   
/*      */   public Byte getAtomFury() {
/*  278 */     return Byte.valueOf(this.atomFury);
/*      */   }
/*      */   
/*      */   public void setAtomFury(byte fury) {
/*  282 */     this.atomFury = fury;
/*      */   }
/*      */   
/*      */   public long getHP() {
/*  286 */     return getCalcAttr(AttributeType.HP);
/*      */   }
/*      */   
/*      */   public long decHP(long deltaHp, IFighter attacker) {
/*  290 */     if (isDead()) return 0L;
/*      */     
/*  292 */     if (isImmuneHurt())
/*  293 */       return 0L; 
/*  294 */     int type = AttributeType.HP.getType();
/*  295 */     this.attrs[type] = this.attrs[type] - deltaHp;
/*  296 */     if (this.attrs[type] <= 0L) {
/*  297 */       this.attrs[type] = 0L;
/*  298 */       this.isDead = true;
/*  299 */       attacker.addKillNum();
/*      */     } 
/*  301 */     return deltaHp;
/*      */   }
/*      */   
/*      */   private boolean isImmuneHurt() {
/*  305 */     for (AbstractBuff buff : this.buffs) {
/*  306 */       if (!buff.isInvalid() && buff.getBean().getEffect() == BuffType.IMMUNE_HURT.getType())
/*  307 */         return true; 
/*      */     } 
/*  309 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isImmuneDebuff() {
/*  313 */     for (AbstractBuff buff : this.buffs) {
/*  314 */       if (!buff.isInvalid() && buff.getBean().getEffect() == BuffType.IMMUNE_DEBUFF.getType())
/*  315 */         return true; 
/*      */     } 
/*  317 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean canUseFurySkill() {
/*  327 */     for (AbstractBuff buff : this.buffs) {
/*  328 */       if (!buff.isInvalid() && FightConstant.SILENT_BUFF_TYPE.contains(Integer.valueOf(buff.getBean().getEffect()))) {
/*  329 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  333 */     if (null == this.furySkill) {
/*  334 */       return false;
/*      */     }
/*  336 */     return (this.fury >= this.furySkill.getBean().getFuryCost());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long addHP(long hp) {
/*  346 */     if (isDead()) return 0L;
/*      */     
/*  348 */     int type = AttributeType.HP.getType();
/*  349 */     long curHp = this.attrs[type];
/*  350 */     curHp += hp;
/*  351 */     if (curHp > this.fighterData.maxHp)
/*  352 */       curHp = this.fighterData.maxHp; 
/*  353 */     this.attrs[type] = curHp;
/*  354 */     return curHp;
/*      */   }
/*      */   
/*      */   public long getMaxHp() {
/*  358 */     return this.fighterData.maxHp;
/*      */   }
/*      */   
/*      */   public long getHPPer() {
/*  362 */     return getCalcAttr(AttributeType.HP) * 10000L / this.fighterData.maxHp;
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
/*      */   public boolean isDead() {
/*  375 */     return (this.isDead || getHP() <= 0L);
/*      */   }
/*      */   
/*      */   public boolean isDisorder() {
/*  379 */     if (this.buffs.isEmpty())
/*  380 */       return false; 
/*  381 */     for (AbstractBuff buff : this.buffs) {
/*  382 */       if (!buff.isInvalid() && buff.getBean().getEffect() == BuffType.DISORDER.getType())
/*  383 */         return true; 
/*      */     } 
/*  385 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPid() {
/*  394 */     return this.pid;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel() {
/*  403 */     return this.level;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isForbidAction() {
/*  414 */     for (AbstractBuff buff : this.buffs) {
/*  415 */       if (!buff.isInvalid() && FightConstant.SEAL_BUFF_TYPE.contains(Integer.valueOf(buff.getBean().getEffect())))
/*  416 */         return true; 
/*      */     } 
/*  418 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initGuid(byte side) {
/*  424 */     this.side = side;
/*  425 */     int pos = this.pos;
/*  426 */     this.pos = (byte)(this.pos + side * 6);
/*      */     
/*  428 */     this.fighterData.guid = (byte)(pos + side * 100);
/*  429 */     if (this.fighterData.maxHp <= 0L) {
/*  430 */       this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*      */     }
/*  432 */     this.fighterData.skills.add(this.cmmSkill.getSkillData());
/*  433 */     if (null != this.furySkill)
/*  434 */       this.fighterData.skills.add(this.furySkill.getSkillData()); 
/*  435 */     if (null != this.fitSkill) {
/*  436 */       this.fighterData.skills.add(this.fitSkill.getSkillData());
/*      */     }
/*      */   }
/*      */   
/*      */   public long getDefAttr() {
/*  441 */     if (this.hurtType == 1)
/*  442 */       return getCalcAttr(AttributeType.PHY_DEF); 
/*  443 */     return getCalcAttr(AttributeType.MAG_DEF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCalcAttr(AttributeType type) {
/*  453 */     return getCalcAttr(type, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCalcAttr(AttributeType type, boolean allowNegative) {
/*  462 */     int attribType = type.getType();
/*  463 */     if (attribType < 1 || attribType > AttributeType.ATTRIB_TYPE_END.getType() || attribType >= this.attrs.length) {
/*  464 */       return 0L;
/*      */     }
/*  466 */     long baseVal = this.attrs[attribType];
/*  467 */     if (FightUtil.isBaseAttr(attribType))
/*  468 */       baseVal = this.baseAttrs[attribType]; 
/*  469 */     long attr = getBuffDeltaAttr(type, baseVal) + this.attrs[attribType];
/*  470 */     if (!allowNegative && attr < 0L) {
/*  471 */       attr = 0L;
/*      */     }
/*  473 */     return attr;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getAttr(int type) {
/*  478 */     if (type < 1 || type > AttributeType.ATTRIB_TYPE_END.getType() || type >= this.attrs.length)
/*  479 */       return 0L; 
/*  480 */     return this.attrs[type];
/*      */   }
/*      */   
/*      */   public void setAttr(int type, long attr) {
/*  484 */     if (type < 1 || type > AttributeType.ATTRIB_TYPE_END.getType())
/*      */       return; 
/*  486 */     this.attrs[type] = attr;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getBaseAttr(int type) {
/*  491 */     if (FightUtil.isBaseAttr(type)) {
/*  492 */       return this.baseAttrs[type];
/*      */     }
/*  494 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBaseAttr(int type, long attr) {
/*  499 */     if (FightUtil.isBaseAttr(type)) {
/*  500 */       this.baseAttrs[type] = attr;
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
/*      */   public boolean addBuff(IFight fight, int buffId, IFighter attacker) {
/*  513 */     BuffBean buffBean = (BuffBean)JsonTableService.getJsonData(buffId, BuffBean.class);
/*  514 */     if (null == buffBean) {
/*  515 */       return false;
/*      */     }
/*      */     
/*  518 */     if (buffBean.getType() == 1 && isImmuneDebuff()) {
/*  519 */       LogUtil.errorLog(new Object[] { "AbstractFighter::addBuff ImmuneDebuff", Integer.valueOf(buffId), this });
/*  520 */       return false;
/*      */     } 
/*  522 */     BoutPlayData boutPlayData = fight.genBoutPlayData();
/*  523 */     ResultPlayData playData = new ResultPlayData();
/*  524 */     playData.targetGuid = getGuid();
/*      */     
/*  526 */     if (buffBean.getLimit() != 0) {
/*  527 */       AbstractBuff targetBuff = null;
/*  528 */       int round = Integer.MAX_VALUE, hadNum = 0;
/*  529 */       for (AbstractBuff abstractBuff : this.buffs) {
/*  530 */         if (abstractBuff.getBuffId() == buffId) {
/*  531 */           hadNum++;
/*  532 */           if (round > abstractBuff.getRound()) {
/*  533 */             round = abstractBuff.getRound();
/*  534 */             targetBuff = abstractBuff;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  539 */       if (buffBean.getLimit() <= hadNum && 
/*  540 */         targetBuff != null) {
/*      */         
/*  542 */         targetBuff.setRound(targetBuff.getBean().getLast());
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
/*  557 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  562 */     AbstractBuff buff = BuffFactory.build(buffBean.getEffect());
/*  563 */     if (null == buff)
/*  564 */       return false; 
/*  565 */     playData.atomData.add(Integer.valueOf(1));
/*  566 */     playData.atomData.add(Integer.valueOf(buffId));
/*  567 */     playData.atomData.add(Integer.valueOf(buffBean.getLast()));
/*      */     
/*  569 */     buff.init(buffBean, 1);
/*  570 */     buff.setMaster(attacker);
/*  571 */     buff.setOwner(this);
/*  572 */     buff.setHurtVal(attacker.getBaseAttr(AttributeType.ATTACK.getType()));
/*  573 */     this.buffs.add(buff);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  578 */     boutPlayData.buffActions.add(playData);
/*  579 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addBuff(IFight fight, int buffId, long finalHurt, IFighter attacker, ResultPlayData playData) {
/*  584 */     BuffBean buffBean = (BuffBean)JsonTableService.getJsonData(buffId, BuffBean.class);
/*  585 */     if (null == buffBean) {
/*  586 */       return false;
/*      */     }
/*      */     
/*  589 */     if (buffBean.getType() == 1 && isImmuneDebuff()) {
/*  590 */       LogUtil.errorLog(new Object[] { "AbstractFighter::addBuff ImmuneDebuff", Integer.valueOf(buffId), this });
/*  591 */       return false;
/*      */     } 
/*      */     
/*  594 */     if (buffBean.getLimit() != 0) {
/*  595 */       AbstractBuff targetBuff = null;
/*  596 */       int round = Integer.MAX_VALUE, hadNum = 0;
/*  597 */       for (AbstractBuff abstractBuff : this.buffs) {
/*  598 */         if (abstractBuff.getBuffId() == buffId) {
/*  599 */           hadNum++;
/*  600 */           if (round > abstractBuff.getRound()) {
/*  601 */             round = abstractBuff.getRound();
/*  602 */             targetBuff = abstractBuff;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  607 */       if (buffBean.getLimit() <= hadNum && 
/*  608 */         targetBuff != null) {
/*      */         
/*  610 */         targetBuff.setRound(targetBuff.getBean().getLast());
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
/*  625 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  630 */     AbstractBuff buff = BuffFactory.build(buffBean.getEffect());
/*  631 */     if (null == buff)
/*  632 */       return false; 
/*  633 */     playData.atomData.add(Integer.valueOf(1));
/*  634 */     playData.atomData.add(Integer.valueOf(buffId));
/*  635 */     playData.atomData.add(Integer.valueOf(buffBean.getLast()));
/*      */     
/*  637 */     buff.init(buffBean, 1);
/*  638 */     buff.setMaster(attacker);
/*  639 */     buff.setOwner(this);
/*      */     
/*  641 */     buff.setHurtVal(attacker.getBaseAttr(AttributeType.ATTACK.getType()));
/*  642 */     this.buffs.add(buff);
/*  643 */     long hp = buff.immediate(playData);
/*      */     
/*  645 */     fight.getSide(getSide()).updateTotalHurt(hp);
/*      */     
/*  647 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<AbstractBuff> getBuffList() {
/*  652 */     return this.buffs;
/*      */   }
/*      */   
/*      */   public void addKillNum() {
/*  656 */     this.killNum++;
/*      */   }
/*      */   
/*      */   public int getKillNum() {
/*  660 */     return this.killNum;
/*      */   }
/*      */   
/*      */   private int getBuffNumById(int buffId) {
/*  664 */     int total = 0;
/*  665 */     for (AbstractBuff buff : this.buffs) {
/*  666 */       if (buff.getBuffId() == buffId)
/*  667 */         total++; 
/*      */     } 
/*  669 */     return total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long getBuffDeltaAttr(AttributeType type, long baseValue) {
/*      */     long deltaVal;
/*  680 */     int val = 0;
/*  681 */     int per = 0;
/*  682 */     for (AbstractBuff buff : this.buffs) {
/*  683 */       if (!buff.isAffected() || buff.isInvalid())
/*      */         continue; 
/*  685 */       if (buff.getBean().getType() == BuffType.ADD_ATTRIB.getType() && buff
/*  686 */         .getBean().getEffectType() == type.getType()) {
/*  687 */         val += buff.getEffectValue();
/*  688 */         per += buff.getEffecPer(); continue;
/*  689 */       }  if (buff.getBean().getType() == BuffType.DEC_ATTRIB.getType() && buff
/*  690 */         .getBean().getEffectType() == type.getType()) {
/*  691 */         val -= buff.getEffectValue();
/*  692 */         per -= buff.getEffecPer();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  697 */     if (type.isRate()) {
/*  698 */       return per;
/*      */     }
/*      */     
/*  701 */     if (per == 0) {
/*  702 */       deltaVal = val;
/*      */     } else {
/*  704 */       deltaVal = (int)(Math.floor(baseValue * per / 10000.0D) + val);
/*      */     } 
/*  706 */     return deltaVal;
/*      */   }
/*      */ 
/*      */   
/*      */   public FighterData getFighterData() {
/*  711 */     if (this instanceof CandidateFighter || this instanceof MonsterCandidateFighter) {
/*  712 */       this.fighterData.buffs.clear();
/*  713 */       this.buffs.forEach(buff -> this.fighterData.buffs.add(Integer.valueOf(buff.getBuffId())));
/*  714 */       return this.fighterData;
/*      */     } 
/*      */     
/*  717 */     this.fighterData.type = this.type;
/*  718 */     this.fighterData.pos = this.pos;
/*      */ 
/*      */ 
/*      */     
/*  722 */     if (!this.isDead) {
/*  723 */       if (this.fighterData.hp == 0L) {
/*  724 */         this.fighterData.hp = getCalcAttr(AttributeType.HP);
/*      */       } else {
/*  726 */         this.fighterData.hp = getHP();
/*      */       } 
/*      */     }
/*  729 */     if (0L == this.maxHp) {
/*  730 */       this.maxHp = getCalcAttr(AttributeType.HP);
/*      */     }
/*  732 */     if (this.fighterData.maxHp == 0L) {
/*  733 */       this.fighterData.maxHp = this.maxHp;
/*      */     }
/*  735 */     this.fighterData.attack = getCalcAttr(AttributeType.ATTACK);
/*  736 */     this.fighterData.phyDef = getCalcAttr(AttributeType.PHY_DEF);
/*  737 */     this.fighterData.magDef = getCalcAttr(AttributeType.MAG_DEF);
/*  738 */     this.fighterData.fury = (byte)(int)getCalcAttr(AttributeType.INIT_FURY);
/*      */     
/*  740 */     this.fighterData.buffs.clear();
/*  741 */     this.buffs.forEach(buff -> this.fighterData.buffs.add(Integer.valueOf(buff.getBuffId())));
/*  742 */     return this.fighterData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int auto(IFight fight) {
/*  753 */     Skill skill = this.cmmSkill;
/*  754 */     if (canUseFurySkill())
/*  755 */       skill = this.furySkill; 
/*  756 */     int ret = useSkill(fight, skill);
/*  757 */     return ret;
/*      */   }
/*      */   
/*      */   public void replace(IFight fight, IFighter target) {
/*  761 */     this.pos = target.getPos();
/*  762 */     this.side = target.getSide();
/*  763 */     (getFighterData()).pos = target.getPos();
/*  764 */     (getFighterData()).guid = target.getGuid();
/*  765 */     fight.getSide(target.getSide()).getCurGroup().replaceFighter(this, target);
/*  766 */     useSkill(fight, this.debutSkill);
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
/*      */   int useSkill(IFight fight, Skill skill) {
/*  779 */     this.playData = fight.genBoutPlayData();
/*  780 */     SkillPlayData skillPlayData = new SkillPlayData();
/*  781 */     skillPlayData.guid = getGuid();
/*  782 */     skillPlayData.skillId = skill.getSkillId();
/*  783 */     String str = "普通技能";
/*  784 */     if (skill.getBean().getType() == 2) {
/*  785 */       str = "怒气技能";
/*  786 */     } else if (skill.getBean().getType() == 3) {
/*  787 */       str = "合体技能";
/*      */     } 
/*  789 */     fight.appendDebugStr(this + "使用了" + str + "[" + skill.getSkillId() + "]");
/*      */     
/*  791 */     setAtomFury((byte)0);
/*  792 */     checkSkillEffect(fight, skill);
/*  793 */     for (Effect effect : skill.getEffects()) {
/*  794 */       skillEffect(effect, fight, skillPlayData);
/*      */     }
/*  796 */     if (!skillPlayData.effectData.isEmpty()) {
/*  797 */       this.playData.skillDatas.add(skillPlayData);
/*  798 */       byte addFury = 1;
/*  799 */       if (skill.getBean().getType() != 1)
/*  800 */         addFury = (byte)(skill.getBean().getFuryCost() * -1); 
/*  801 */       addFury(addFury, false);
/*      */     } 
/*  803 */     addFury((byte)0, false);
/*  804 */     skillPlayData.fury = this.fury;
/*  805 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkSkillEffect(IFight fight, Skill skill) {
/*  815 */     if (this.talisman == null || this.talisman.isEmpty())
/*      */       return; 
/*  817 */     for (int i = 0; i < skill.getEffects().size(); i++) {
/*  818 */       int effectId = ((Effect)skill.getEffects().get(i)).getEffectId();
/*  819 */       if (this.talisman.containsKey(Integer.valueOf(effectId))) {
/*  820 */         skill.getEffects().set(i, new Effect(((Integer)this.talisman.get(Integer.valueOf(effectId))).intValue(), skill.getLevel()));
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
/*      */   int skillEffect(Effect effect, IFight fight, SkillPlayData skillPlayData) {
/*  832 */     int side = effect.getBean().getSide();
/*  833 */     int target = effect.getBean().getTarget();
/*  834 */     int range = effect.getBean().getRange();
/*      */     
/*  836 */     EffectPlayData effectPlayData = new EffectPlayData();
/*  837 */     effectPlayData.effectId = effect.getEffectId();
/*      */     
/*  839 */     List<IFighter> targets = TargetSelector.selectTarget(this, side, target, range, fight, effectPlayData);
/*  840 */     assert targets != null;
/*  841 */     if (targets.isEmpty()) {
/*  842 */       LogUtils.errorLog(new Object[] { "选择的目标为空: " + getGuid() + ", " + effect.getEffectId() });
/*  843 */       return -1;
/*      */     } 
/*      */     
/*  846 */     for (IFighter t : targets) {
/*  847 */       fight.appendDebugStr(" 攻击" + t);
/*  848 */       effect(effect, t, effectPlayData, fight);
/*      */     } 
/*      */     
/*  851 */     fight.appendDebugStr("\n");
/*  852 */     skillPlayData.effectData.add(effectPlayData);
/*  853 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void effect(Effect effect, IFighter target, EffectPlayData effectPlayData, IFight fight) {
/*  861 */     ResultPlayData resultPlayData = new ResultPlayData();
/*  862 */     resultPlayData.targetGuid = target.getGuid();
/*      */     
/*  864 */     effectPlayData.resultData.add(resultPlayData);
/*  865 */     Pair<Integer, Long> hurtValue = new Pair(Integer.valueOf(2147483647), Long.valueOf(0L));
/*      */     
/*  867 */     FightUtil.hurt(effect, this, target, resultPlayData, fight, hurtValue);
/*      */     
/*  869 */     if (((Integer)hurtValue.getKey()).intValue() == Integer.MAX_VALUE) {
/*  870 */       long finalVal = target.decHP(((Long)hurtValue.getValue()).longValue(), this);
/*  871 */       resultPlayData.hp = (int)(finalVal * -1L);
/*  872 */       fight.getSide(getSide()).updateTotalHurt(finalVal);
/*  873 */       fight.appendDebugStr("; 扣除血量" + resultPlayData.hp + "; 被攻击后:" + target + ";");
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
/*      */   public boolean removeBuff(ResultPlayData playData) {
/*  934 */     boolean flag = false;
/*  935 */     Iterator<AbstractBuff> iter = this.buffs.iterator();
/*  936 */     while (iter.hasNext()) {
/*  937 */       AbstractBuff buff = iter.next();
/*  938 */       if (buff.getBean().getType() == 0) {
/*  939 */         flag = true;
/*  940 */         playData.atomData.add(Integer.valueOf(2));
/*  941 */         playData.atomData.add(Integer.valueOf(buff.getBuffId()));
/*  942 */         iter.remove();
/*      */       } 
/*      */     } 
/*  945 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeBuff(ResultPlayData playData, int buffType, int buffNum, int level) {
/*  955 */     boolean flag = false;
/*  956 */     Iterator<AbstractBuff> iter = this.buffs.iterator();
/*  957 */     while (iter.hasNext()) {
/*  958 */       AbstractBuff buff = iter.next();
/*  959 */       if (buff.getBean().getType() == buffType && buff.getBean().getDispel() <= level) {
/*  960 */         flag = true;
/*  961 */         playData.atomData.add(Integer.valueOf(2));
/*  962 */         playData.atomData.add(Integer.valueOf(buff.getBuffId()));
/*  963 */         iter.remove();
/*  964 */         if (--buffNum <= 0)
/*      */           break; 
/*      */       } 
/*      */     } 
/*  968 */     return flag;
/*      */   }
/*      */   
/*      */   public Skill getFurySkill() {
/*  972 */     return this.furySkill;
/*      */   }
/*      */   
/*      */   public void setFurySkill(Skill furySkill) {
/*  976 */     this.furySkill = furySkill;
/*      */   }
/*      */   
/*      */   public Skill getFitSkill() {
/*  980 */     return this.fitSkill;
/*      */   }
/*      */   
/*      */   public void setFitSkill(Skill fitSkill) {
/*  984 */     this.fitSkill = fitSkill;
/*      */   }
/*      */   
/*      */   public Skill getCmmSkill() {
/*  988 */     return this.cmmSkill;
/*      */   }
/*      */   
/*      */   public void setCmmSkill(Skill cmmSkill) {
/*  992 */     this.cmmSkill = cmmSkill;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBuff() {
/*  999 */     this.buffs.clear();
/*      */   }
/*      */   
/*      */   public boolean hasBuff(int buffType) {
/* 1003 */     for (AbstractBuff buff : this.buffs) {
/* 1004 */       if (buff.getBean().getEffect() == buffType)
/* 1005 */         return true; 
/*      */     } 
/* 1007 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<Integer, Integer> getTalisman() {
/* 1012 */     return this.talisman;
/*      */   }
/*      */   
/*      */   public void setTalisman(Map<Integer, Integer> talisman) {
/* 1016 */     this.talisman = talisman;
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
/*      */   public String toString() {
/* 1038 */     String string = "武将";
/* 1039 */     if (this.type == 0) {
/* 1040 */       string = "玩家";
/* 1041 */     } else if (this.type == 2) {
/* 1042 */       string = "怪物";
/*      */     } 
/* 1044 */     return string + "[guid:" + getGuid() + ",pos:" + getPos() + ",hp:" + getHP() + ",fury:" + getFury() + "]";
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\AbstractFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */