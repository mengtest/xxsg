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
/*   76 */   protected FighterData fighterData = new FighterData();
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
/*  100 */   protected List<AbstractBuff> buffs = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   protected long[] baseAttrs = new long[AttributeType.BASE_ATTR_END.getType()];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  110 */   protected long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
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
/*  126 */     return this.type;
/*      */   }
/*      */   
/*      */   public byte getHurtType() {
/*  130 */     return this.hurtType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAuto() {
/*  139 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void preStart(IFight fight) {
/*  144 */     Map<Integer, Long> damageBuffMap = new HashMap<>();
/*  145 */     List<AbstractBuff> damageBuffList = new ArrayList<>();
/*  146 */     Iterator<AbstractBuff> iter = this.buffs.iterator();
/*  147 */     while (iter.hasNext()) {
/*  148 */       AbstractBuff buff = iter.next();
/*  149 */       if (buff.tick()) {
/*  150 */         BoutPlayData playData = fight.genBoutPlayData();
/*  151 */         ResultPlayData resultPlayData = new ResultPlayData();
/*  152 */         resultPlayData.targetGuid = buff.getOwner().getGuid();
/*  153 */         resultPlayData.targeAction = 0;
/*  154 */         resultPlayData.hp = 0;
/*  155 */         resultPlayData.atomData.add(Integer.valueOf(2));
/*  156 */         resultPlayData.atomData.add(Integer.valueOf(buff.getBuffId()));
/*  157 */         playData.buffActions.add(resultPlayData);
/*  158 */         iter.remove(); continue;
/*      */       } 
/*  160 */       if (buff.getBean().getCalculationType() == 0) {
/*  161 */         long hurt = buff.calHurt();
/*  162 */         if (hurt > 0L) {
/*  163 */           if (!damageBuffMap.containsKey(Integer.valueOf(buff.getBuffId()))) {
/*  164 */             damageBuffMap.put(Integer.valueOf(buff.getBuffId()), Long.valueOf(hurt));
/*  165 */             damageBuffList.add(buff);
/*      */           } else {
/*  167 */             damageBuffMap.put(Integer.valueOf(buff.getBuffId()), Long.valueOf(hurt + ((Long)damageBuffMap.get(Integer.valueOf(buff.getBuffId()))).longValue()));
/*      */           } 
/*      */         } else {
/*  170 */           buff.effect(fight, true);
/*      */         } 
/*      */       } 
/*  173 */       if (buff.getRoundReset()) {
/*  174 */         buff.setTimes(buff.getBean().getTime());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  179 */     if (!damageBuffList.isEmpty()) {
/*  180 */       for (AbstractBuff buff : damageBuffList) {
/*  181 */         buff.effect(fight, true, ((Long)damageBuffMap.get(Integer.valueOf(buff.getBuffId()))).longValue());
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
/*  192 */     if (isForbidAction())
/*  193 */       return false; 
/*  194 */     if (isDead())
/*  195 */       return false; 
/*  196 */     return true;
/*      */   }
/*      */   
/*      */   public long getId() {
/*  200 */     return this.id;
/*      */   }
/*      */   
/*      */   public void setId(long id) {
/*  204 */     this.id = id;
/*      */   }
/*      */   
/*      */   public byte getSide() {
/*  208 */     return this.side;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSide(byte side) {
/*  213 */     this.side = side;
/*      */   }
/*      */   
/*      */   public byte getGuid() {
/*  217 */     return this.fighterData.guid;
/*      */   }
/*      */   
/*      */   public byte getPos() {
/*  221 */     return this.pos;
/*      */   }
/*      */   
/*      */   public byte getCalcPos() {
/*  225 */     byte calcPos = (byte)(this.pos % 6);
/*  226 */     if (0 == calcPos) {
/*  227 */       return 6;
/*      */     }
/*  229 */     return calcPos;
/*      */   }
/*      */   
/*      */   public void setPos(byte pos) {
/*  233 */     this.pos = pos;
/*      */   }
/*      */   
/*      */   public Byte getFury() {
/*  237 */     return Byte.valueOf(this.fury);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte decFury(byte fury) {
/*  247 */     this.fury = (byte)(this.fury - fury);
/*  248 */     if (this.fury < 0)
/*  249 */       this.fury = 0; 
/*  250 */     return this.fury;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte addFury(byte fury, boolean ignoreMax) {
/*  260 */     this.fury = (byte)(this.fury + fury);
/*  261 */     if (!ignoreMax && this.fury > getMaxFury())
/*  262 */       this.fury = getMaxFury(); 
/*  263 */     return this.fury;
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
/*  274 */     this.fury = (byte)(this.fury + fury);
/*  275 */     return this.fury;
/*      */   }
/*      */   
/*      */   public Byte getAtomFury() {
/*  279 */     return Byte.valueOf(this.atomFury);
/*      */   }
/*      */   
/*      */   public void setAtomFury(byte fury) {
/*  283 */     this.atomFury = fury;
/*      */   }
/*      */   
/*      */   public long getHP() {
/*  287 */     return getCalcAttr(AttributeType.HP);
/*      */   }
/*      */   
/*      */   public long decHP(long deltaHp, IFighter attacker) {
/*  291 */     if (isDead()) return 0L;
/*      */     
/*  293 */     if (isImmuneHurt())
/*  294 */       return 0L; 
/*  295 */     int type = AttributeType.HP.getType();
/*  296 */     this.attrs[type] = this.attrs[type] - deltaHp;
/*  297 */     if (this.attrs[type] < 0L) {
/*  298 */       this.attrs[type] = 0L;
/*  299 */       this.isDead = true;
/*  300 */       attacker.addKillNum();
/*      */     } 
/*  302 */     return deltaHp;
/*      */   }
/*      */   
/*      */   private boolean isImmuneHurt() {
/*  306 */     for (AbstractBuff buff : this.buffs) {
/*  307 */       if (!buff.isInvalid() && buff.getBean().getEffect() == BuffType.IMMUNE_HURT.getType())
/*  308 */         return true; 
/*      */     } 
/*  310 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isImmuneDebuff() {
/*  314 */     for (AbstractBuff buff : this.buffs) {
/*  315 */       if (!buff.isInvalid() && buff.getBean().getEffect() == BuffType.IMMUNE_DEBUFF.getType())
/*  316 */         return true; 
/*      */     } 
/*  318 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean canUseFurySkill() {
/*  328 */     for (AbstractBuff buff : this.buffs) {
/*  329 */       if (!buff.isInvalid() && FightConstant.SILENT_BUFF_TYPE.contains(Integer.valueOf(buff.getBean().getEffect()))) {
/*  330 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  334 */     if (null == this.furySkill) {
/*  335 */       return false;
/*      */     }
/*  337 */     return (this.fury >= this.furySkill.getBean().getFuryCost());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long addHP(long hp) {
/*  347 */     if (isDead()) return 0L;
/*      */     
/*  349 */     int type = AttributeType.HP.getType();
/*  350 */     long curHp = this.attrs[type];
/*  351 */     curHp += hp;
/*  352 */     if (curHp > this.fighterData.maxHp)
/*  353 */       curHp = this.fighterData.maxHp; 
/*  354 */     this.attrs[type] = curHp;
/*  355 */     return curHp;
/*      */   }
/*      */   
/*      */   public long getMaxHp() {
/*  359 */     return this.fighterData.maxHp;
/*      */   }
/*      */   
/*      */   public long getHPPer() {
/*  363 */     return getCalcAttr(AttributeType.HP) * 10000L / this.fighterData.maxHp;
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
/*  376 */     return (this.isDead || getHP() <= 0L);
/*      */   }
/*      */   
/*      */   public boolean isDisorder() {
/*  380 */     if (this.buffs.isEmpty())
/*  381 */       return false; 
/*  382 */     for (AbstractBuff buff : this.buffs) {
/*  383 */       if (!buff.isInvalid() && buff.getBean().getEffect() == BuffType.DISORDER.getType())
/*  384 */         return true; 
/*      */     } 
/*  386 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getPid() {
/*  395 */     return this.pid;
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
/*  406 */     for (AbstractBuff buff : this.buffs) {
/*  407 */       if (!buff.isInvalid() && FightConstant.SEAL_BUFF_TYPE.contains(Integer.valueOf(buff.getBean().getEffect())))
/*  408 */         return true; 
/*      */     } 
/*  410 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void initGuid(byte side) {
/*  416 */     this.side = side;
/*  417 */     int pos = this.pos;
/*  418 */     this.pos = (byte)(this.pos + side * 6);
/*      */     
/*  420 */     this.fighterData.guid = (byte)(pos + side * 100);
/*  421 */     if (this.fighterData.maxHp <= 0L) {
/*  422 */       this.fighterData.maxHp = this.baseAttrs[AttributeType.HP.getType()];
/*      */     }
/*  424 */     this.fighterData.skills.add(this.cmmSkill.getSkillData());
/*  425 */     if (null != this.furySkill)
/*  426 */       this.fighterData.skills.add(this.furySkill.getSkillData()); 
/*  427 */     if (null != this.fitSkill) {
/*  428 */       this.fighterData.skills.add(this.fitSkill.getSkillData());
/*      */     }
/*      */   }
/*      */   
/*      */   public long getDefAttr() {
/*  433 */     if (this.hurtType == 1)
/*  434 */       return getCalcAttr(AttributeType.PHY_DEF); 
/*  435 */     return getCalcAttr(AttributeType.MAG_DEF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCalcAttr(AttributeType type) {
/*  445 */     return getCalcAttr(type, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCalcAttr(AttributeType type, boolean allowNegative) {
/*  455 */     int attribType = type.getType();
/*  456 */     if (attribType < 1 || attribType > AttributeType.ATTRIB_TYPE_END.getType() || attribType >= this.attrs.length) {
/*  457 */       return 0L;
/*      */     }
/*  459 */     long baseVal = this.attrs[attribType];
/*  460 */     if (FightUtil.isBaseAttr(attribType))
/*  461 */       baseVal = this.baseAttrs[attribType]; 
/*  462 */     long attr = getBuffDeltaAttr(type, baseVal) + this.attrs[attribType];
/*  463 */     if (!allowNegative && attr < 0L) {
/*  464 */       attr = 0L;
/*      */     }
/*  466 */     return attr;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getAttr(int type) {
/*  471 */     if (type < 1 || type > AttributeType.ATTRIB_TYPE_END.getType() || type >= this.attrs.length)
/*  472 */       return 0L; 
/*  473 */     return this.attrs[type];
/*      */   }
/*      */   
/*      */   public void setAttr(int type, long attr) {
/*  477 */     if (type < 1 || type > AttributeType.ATTRIB_TYPE_END.getType())
/*      */       return; 
/*  479 */     this.attrs[type] = attr;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getBaseAttr(int type) {
/*  484 */     if (FightUtil.isBaseAttr(type)) {
/*  485 */       return this.baseAttrs[type];
/*      */     }
/*  487 */     return 0L;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBaseAttr(int type, long attr) {
/*  492 */     if (FightUtil.isBaseAttr(type)) {
/*  493 */       this.baseAttrs[type] = attr;
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
/*      */   public boolean addBuff(IFight fight, int buffId, IFighter attacker) {
/*  505 */     BuffBean buffBean = (BuffBean)JsonTableService.getJsonData(buffId, BuffBean.class);
/*  506 */     if (null == buffBean) {
/*  507 */       return false;
/*      */     }
/*      */     
/*  510 */     if (buffBean.getType() == 1 && isImmuneDebuff()) {
/*  511 */       LogUtil.errorLog(new Object[] { "AbstractFighter::addBuff ImmuneDebuff", Integer.valueOf(buffId), this });
/*  512 */       return false;
/*      */     } 
/*  514 */     BoutPlayData boutPlayData = fight.genBoutPlayData();
/*  515 */     ResultPlayData playData = new ResultPlayData();
/*  516 */     playData.targetGuid = getGuid();
/*      */     
/*  518 */     if (buffBean.getLimit() != 0) {
/*  519 */       AbstractBuff targetBuff = null;
/*  520 */       int round = Integer.MAX_VALUE, hadNum = 0;
/*  521 */       for (AbstractBuff abstractBuff : this.buffs) {
/*  522 */         if (abstractBuff.getBuffId() == buffId) {
/*  523 */           hadNum++;
/*  524 */           if (round > abstractBuff.getRound()) {
/*  525 */             round = abstractBuff.getRound();
/*  526 */             targetBuff = abstractBuff;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  531 */       if (buffBean.getLimit() <= hadNum && 
/*  532 */         targetBuff != null) {
/*      */         
/*  534 */         targetBuff.setRound(targetBuff.getBean().getLast());
/*  535 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  540 */     AbstractBuff buff = BuffFactory.build(buffBean.getEffect());
/*  541 */     if (null == buff)
/*  542 */       return false; 
/*  543 */     playData.atomData.add(Integer.valueOf(1));
/*  544 */     playData.atomData.add(Integer.valueOf(buffId));
/*  545 */     playData.atomData.add(Integer.valueOf(buffBean.getLast()));
/*      */     
/*  547 */     buff.init(buffBean, 1);
/*  548 */     buff.setMaster(attacker);
/*  549 */     buff.setOwner(this);
/*  550 */     buff.setHurtVal(attacker.getBaseAttr(AttributeType.ATTACK.getType()));
/*  551 */     this.buffs.add(buff);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  556 */     boutPlayData.buffActions.add(playData);
/*  557 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addBuff(IFight fight, int buffId, long finalHurt, IFighter attacker, ResultPlayData playData) {
/*  562 */     BuffBean buffBean = (BuffBean)JsonTableService.getJsonData(buffId, BuffBean.class);
/*  563 */     if (null == buffBean) {
/*  564 */       return false;
/*      */     }
/*      */     
/*  567 */     if (buffBean.getType() == 1 && isImmuneDebuff()) {
/*  568 */       LogUtil.errorLog(new Object[] { "AbstractFighter::addBuff ImmuneDebuff", Integer.valueOf(buffId), this });
/*  569 */       return false;
/*      */     } 
/*      */     
/*  572 */     if (buffBean.getLimit() != 0) {
/*  573 */       AbstractBuff targetBuff = null;
/*  574 */       int round = Integer.MAX_VALUE, hadNum = 0;
/*  575 */       for (AbstractBuff abstractBuff : this.buffs) {
/*  576 */         if (abstractBuff.getBuffId() == buffId) {
/*  577 */           hadNum++;
/*  578 */           if (round > abstractBuff.getRound()) {
/*  579 */             round = abstractBuff.getRound();
/*  580 */             targetBuff = abstractBuff;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  585 */       if (buffBean.getLimit() <= hadNum && 
/*  586 */         targetBuff != null) {
/*      */         
/*  588 */         targetBuff.setRound(targetBuff.getBean().getLast());
/*  589 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  594 */     AbstractBuff buff = BuffFactory.build(buffBean.getEffect());
/*  595 */     if (null == buff)
/*  596 */       return false; 
/*  597 */     playData.atomData.add(Integer.valueOf(1));
/*  598 */     playData.atomData.add(Integer.valueOf(buffId));
/*  599 */     playData.atomData.add(Integer.valueOf(buffBean.getLast()));
/*      */     
/*  601 */     buff.init(buffBean, 1);
/*  602 */     buff.setMaster(attacker);
/*  603 */     buff.setOwner(this);
/*      */     
/*  605 */     buff.setHurtVal(attacker.getBaseAttr(AttributeType.ATTACK.getType()));
/*  606 */     this.buffs.add(buff);
/*  607 */     long hp = buff.immediate(playData);
/*      */     
/*  609 */     fight.getSide(getSide()).updateTotalHurt(hp);
/*      */     
/*  611 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<AbstractBuff> getBuffList() {
/*  616 */     return this.buffs;
/*      */   }
/*      */   
/*      */   public void addKillNum() {
/*  620 */     this.killNum++;
/*      */   }
/*      */   
/*      */   public int getKillNum() {
/*  624 */     return this.killNum;
/*      */   }
/*      */   
/*      */   private int getBuffNumById(int buffId) {
/*  628 */     int total = 0;
/*  629 */     for (AbstractBuff buff : this.buffs) {
/*  630 */       if (buff.getBuffId() == buffId)
/*  631 */         total++; 
/*      */     } 
/*  633 */     return total;
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
/*  644 */     int val = 0;
/*  645 */     int per = 0;
/*  646 */     for (AbstractBuff buff : this.buffs) {
/*  647 */       if (!buff.isAffected() || buff.isInvalid())
/*      */         continue; 
/*  649 */       if (buff.getBean().getType() == BuffType.ADD_ATTRIB.getType() && buff
/*  650 */         .getBean().getEffectType() == type.getType()) {
/*  651 */         val += buff.getEffectValue();
/*  652 */         per += buff.getEffecPer(); continue;
/*  653 */       }  if (buff.getBean().getType() == BuffType.DEC_ATTRIB.getType() && buff
/*  654 */         .getBean().getEffectType() == type.getType()) {
/*  655 */         val -= buff.getEffectValue();
/*  656 */         per -= buff.getEffecPer();
/*      */       } 
/*      */     } 
/*      */     
/*  660 */     if (type.isRate()) {
/*  661 */       return per;
/*      */     }
/*      */     
/*  664 */     if (per == 0) {
/*  665 */       deltaVal = val;
/*      */     } else {
/*  667 */       deltaVal = (int)(Math.floor(baseValue * per / 10000.0D) + val);
/*      */     } 
/*      */ 
/*      */     
/*  671 */     return deltaVal;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public FighterData getFighterData() {
/*  677 */     if (this instanceof CandidateFighter || this instanceof MonsterCandidateFighter) {
/*  678 */       this.fighterData.buffs.clear();
/*  679 */       this.buffs.forEach(buff -> this.fighterData.buffs.add(Integer.valueOf(buff.getBuffId())));
/*  680 */       return this.fighterData;
/*      */     } 
/*      */     
/*  683 */     this.fighterData.type = this.type;
/*  684 */     this.fighterData.pos = this.pos;
/*      */ 
/*      */ 
/*      */     
/*  688 */     if (!this.isDead) {
/*  689 */       if (this.fighterData.hp == 0L) {
/*  690 */         this.fighterData.hp = getCalcAttr(AttributeType.HP);
/*      */       } else {
/*  692 */         this.fighterData.hp = getHP();
/*      */       } 
/*      */     }
/*  695 */     if (0L == this.maxHp) {
/*  696 */       this.maxHp = getCalcAttr(AttributeType.HP);
/*      */     }
/*  698 */     if (this.fighterData.maxHp == 0L) {
/*  699 */       this.fighterData.maxHp = this.maxHp;
/*      */     }
/*  701 */     this.fighterData.attack = getCalcAttr(AttributeType.ATTACK);
/*  702 */     this.fighterData.phyDef = getCalcAttr(AttributeType.PHY_DEF);
/*  703 */     this.fighterData.magDef = getCalcAttr(AttributeType.MAG_DEF);
/*  704 */     this.fighterData.fury = (byte)(int)getCalcAttr(AttributeType.INIT_FURY);
/*      */     
/*  706 */     this.fighterData.buffs.clear();
/*  707 */     this.buffs.forEach(buff -> this.fighterData.buffs.add(Integer.valueOf(buff.getBuffId())));
/*  708 */     return this.fighterData;
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
/*  719 */     Skill skill = this.cmmSkill;
/*  720 */     if (canUseFurySkill())
/*  721 */       skill = this.furySkill; 
/*  722 */     int ret = useSkill(fight, skill);
/*  723 */     return ret;
/*      */   }
/*      */   
/*      */   public void replace(IFight fight, IFighter target) {
/*  727 */     this.pos = target.getPos();
/*  728 */     this.side = target.getSide();
/*  729 */     (getFighterData()).pos = target.getPos();
/*  730 */     (getFighterData()).guid = target.getGuid();
/*  731 */     fight.getSide(target.getSide()).getCurGroup().replaceFighter(this, target);
/*  732 */     useSkill(fight, this.debutSkill);
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
/*      */   int useSkill(IFight fight, Skill skill) {
/*  744 */     this.playData = fight.genBoutPlayData();
/*  745 */     SkillPlayData skillPlayData = new SkillPlayData();
/*  746 */     skillPlayData.guid = getGuid();
/*  747 */     skillPlayData.skillId = skill.getSkillId();
/*  748 */     String str = "普通技能";
/*  749 */     if (skill.getBean().getType() == 2) {
/*  750 */       str = "怒气技能";
/*  751 */     } else if (skill.getBean().getType() == 3) {
/*  752 */       str = "合体技能";
/*      */     } 
/*  754 */     fight.appendDebugStr(this + "使用了" + str + "[" + skill.getSkillId() + "]");
/*      */     
/*  756 */     setAtomFury((byte)0);
/*  757 */     checkSkillEffect(fight, skill);
/*  758 */     for (Effect effect : skill.getEffects()) {
/*  759 */       skillEffect(effect, fight, skillPlayData);
/*      */     }
/*  761 */     if (!skillPlayData.effectData.isEmpty()) {
/*  762 */       this.playData.skillDatas.add(skillPlayData);
/*  763 */       byte addFury = 1;
/*  764 */       if (skill.getBean().getType() != 1)
/*  765 */         addFury = (byte)(skill.getBean().getFuryCost() * -1); 
/*  766 */       addFury(addFury, false);
/*      */     } 
/*  768 */     addFury((byte)0, false);
/*  769 */     skillPlayData.fury = this.fury;
/*  770 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkSkillEffect(IFight fight, Skill skill) {
/*  779 */     if (this.talisman == null || this.talisman.isEmpty())
/*      */       return; 
/*  781 */     for (int i = 0; i < skill.getEffects().size(); i++) {
/*  782 */       int effectId = ((Effect)skill.getEffects().get(i)).getEffectId();
/*  783 */       if (this.talisman.containsKey(Integer.valueOf(effectId))) {
/*  784 */         skill.getEffects().set(i, new Effect(((Integer)this.talisman.get(Integer.valueOf(effectId))).intValue(), skill.getLevel()));
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
/*  796 */     int side = effect.getBean().getSide();
/*  797 */     int target = effect.getBean().getTarget();
/*  798 */     int range = effect.getBean().getRange();
/*      */     
/*  800 */     EffectPlayData effectPlayData = new EffectPlayData();
/*  801 */     effectPlayData.effectId = effect.getEffectId();
/*      */     
/*  803 */     List<IFighter> targets = TargetSelector.selectTarget(this, side, target, range, fight, effectPlayData);
/*  804 */     assert targets != null;
/*  805 */     if (targets.isEmpty()) {
/*  806 */       LogUtils.errorLog(new Object[] { "选择的目标为空: " + getGuid() + ", " + effect.getEffectId() });
/*  807 */       return -1;
/*      */     } 
/*      */     
/*  810 */     for (IFighter t : targets) {
/*  811 */       fight.appendDebugStr(" 攻击" + t);
/*  812 */       effect(effect, t, effectPlayData, fight);
/*      */     } 
/*      */     
/*  815 */     fight.appendDebugStr("\n");
/*  816 */     skillPlayData.effectData.add(effectPlayData);
/*  817 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void effect(Effect effect, IFighter target, EffectPlayData effectPlayData, IFight fight) {
/*  825 */     ResultPlayData resultPlayData = new ResultPlayData();
/*  826 */     resultPlayData.targetGuid = target.getGuid();
/*      */     
/*  828 */     effectPlayData.resultData.add(resultPlayData);
/*  829 */     Pair<Integer, Long> hurtValue = new Pair(Integer.valueOf(2147483647), Long.valueOf(0L));
/*      */     
/*  831 */     FightUtil.hurt(effect, this, target, resultPlayData, fight, hurtValue);
/*      */     
/*  833 */     if (((Integer)hurtValue.getKey()).intValue() == Integer.MAX_VALUE) {
/*  834 */       long finalVal = target.decHP(((Long)hurtValue.getValue()).longValue(), this);
/*  835 */       resultPlayData.hp = (int)(finalVal * -1L);
/*  836 */       fight.getSide(getSide()).updateTotalHurt(finalVal);
/*  837 */       fight.appendDebugStr("; 扣除血量" + resultPlayData.hp + "; 被攻击后:" + target + ";");
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
/*  898 */     boolean flag = false;
/*  899 */     Iterator<AbstractBuff> iter = this.buffs.iterator();
/*  900 */     while (iter.hasNext()) {
/*  901 */       AbstractBuff buff = iter.next();
/*  902 */       if (buff.getBean().getType() == 0) {
/*  903 */         flag = true;
/*  904 */         playData.atomData.add(Integer.valueOf(2));
/*  905 */         playData.atomData.add(Integer.valueOf(buff.getBuffId()));
/*  906 */         iter.remove();
/*      */       } 
/*      */     } 
/*  909 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeBuff(ResultPlayData playData, int buffType, int buffNum, int level) {
/*  919 */     boolean flag = false;
/*  920 */     Iterator<AbstractBuff> iter = this.buffs.iterator();
/*  921 */     while (iter.hasNext()) {
/*  922 */       AbstractBuff buff = iter.next();
/*  923 */       if (buff.getBean().getType() == buffType && buff.getBean().getDispel() <= level) {
/*  924 */         flag = true;
/*  925 */         playData.atomData.add(Integer.valueOf(2));
/*  926 */         playData.atomData.add(Integer.valueOf(buff.getBuffId()));
/*  927 */         iter.remove();
/*  928 */         if (--buffNum <= 0)
/*      */           break; 
/*      */       } 
/*      */     } 
/*  932 */     return flag;
/*      */   }
/*      */   
/*      */   public Skill getFurySkill() {
/*  936 */     return this.furySkill;
/*      */   }
/*      */   
/*      */   public void setFurySkill(Skill furySkill) {
/*  940 */     this.furySkill = furySkill;
/*      */   }
/*      */   
/*      */   public Skill getFitSkill() {
/*  944 */     return this.fitSkill;
/*      */   }
/*      */   
/*      */   public void setFitSkill(Skill fitSkill) {
/*  948 */     this.fitSkill = fitSkill;
/*      */   }
/*      */   
/*      */   public Skill getCmmSkill() {
/*  952 */     return this.cmmSkill;
/*      */   }
/*      */   
/*      */   public void setCmmSkill(Skill cmmSkill) {
/*  956 */     this.cmmSkill = cmmSkill;
/*      */   }
/*      */   
/*      */   public boolean setDead(boolean dead) {
/*  960 */     boolean pre = this.isDead;
/*  961 */     this.isDead = dead;
/*  962 */     return pre;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel() {
/*  970 */     return this.level;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearBuff() {
/*  977 */     this.buffs.clear();
/*      */   }
/*      */   
/*      */   public boolean hasBuff(int buffType) {
/*  981 */     for (AbstractBuff buff : this.buffs) {
/*  982 */       if (buff.getBean().getEffect() == buffType)
/*  983 */         return true; 
/*      */     } 
/*  985 */     return false;
/*      */   }
/*      */   
/*      */   public Map<Integer, Integer> getTalisman() {
/*  989 */     return this.talisman;
/*      */   }
/*      */   
/*      */   public void setTalisman(Map<Integer, Integer> talisman) {
/*  993 */     this.talisman = talisman;
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
/* 1015 */     String string = "武将";
/* 1016 */     if (this.type == 0) {
/* 1017 */       string = "玩家";
/* 1018 */     } else if (this.type == 2) {
/* 1019 */       string = "怪物";
/*      */     } 
/* 1021 */     return string + "[guid:" + getGuid() + ",pos:" + getPos() + ",hp:" + getHP() + ",fury:" + getFury() + "]";
/*      */   }
/*      */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\fighter\AbstractFighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */