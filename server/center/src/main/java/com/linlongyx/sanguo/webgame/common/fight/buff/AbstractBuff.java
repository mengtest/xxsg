/*     */ package com.linlongyx.sanguo.webgame.common.fight.buff;
/*     */ 
/*     */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*     */ import com.linlongyx.sanguo.webgame.common.fight.fighter.IFighter;
/*     */ import com.linlongyx.sanguo.webgame.config.bean.BuffBean;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractBuff
/*     */   implements Buffable
/*     */ {
/*     */   protected BuffBean bean;
/*     */   protected int buffId;
/*     */   protected int round;
/*     */   protected int level;
/*     */   protected boolean affected;
/*     */   protected long hurtVal;
/*     */   protected IFighter master;
/*     */   protected IFighter owner;
/*     */   protected List<KeyValue> effects;
/*     */   protected int times;
/*     */   protected boolean roundReset;
/*     */   
/*     */   public AbstractBuff() {}
/*     */   
/*     */   public AbstractBuff(BuffBean bean, int level) {
/*  36 */     init(bean, level);
/*     */   }
/*     */   
/*     */   public void init(BuffBean bean, int level) {
/*  40 */     this.buffId = bean.getId();
/*  41 */     this.round = bean.getLast();
/*  42 */     this.times = bean.getTime();
/*  43 */     this.roundReset = (bean.getLastAndTime() == 1);
/*  44 */     this.level = level;
/*  45 */     this.affected = true;
/*  46 */     this.effects = new ArrayList<>();
/*  47 */     this.bean = bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tick() {
/*  55 */     this.affected = true;
/*  56 */     if (this.round > 0)
/*  57 */       this.round--; 
/*  58 */     return (this.round == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInvalid() {
/*  63 */     if (this.roundReset && this.times == 0) {
/*  64 */       return true;
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public long effect(IFight fight, boolean addAtom) {
/*  70 */     return 0L;
/*     */   }
/*     */   
/*     */   public long effect(IFight fight, boolean addAtom, long finalHurt) {
/*  74 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long calHurt() {
/*  82 */     return 0L;
/*     */   }
/*     */   
/*     */   public long immediate(ResultPlayData resultPlayData) {
/*  86 */     return 0L;
/*     */   }
/*     */   
/*     */   public long immediate(ResultPlayData resultPlayData, long hurtVal) {
/*  90 */     return 0L;
/*     */   }
/*     */   
/*     */   public int getEffectValue() {
/*  94 */     return this.bean.getEffectValue() + this.bean.getEffecPer() * (this.level - 1);
/*     */   }
/*     */   
/*     */   public int getEffecPer() {
/*  98 */     return this.bean.getEffecPer();
/*     */   }
/*     */ 
/*     */   
/*     */   public BuffBean getBean() {
/* 103 */     return this.bean;
/*     */   }
/*     */   
/*     */   public int getBuffId() {
/* 107 */     return this.buffId;
/*     */   }
/*     */   
/*     */   public int getRound() {
/* 111 */     return this.round;
/*     */   }
/*     */   
/*     */   public void setRound(int round) {
/* 115 */     this.round = round;
/*     */   }
/*     */   
/*     */   public boolean isAffected() {
/* 119 */     return this.affected;
/*     */   }
/*     */   
/*     */   public void setAffected(boolean affected) {
/* 123 */     this.affected = affected;
/*     */   }
/*     */   
/*     */   public int getLevel() {
/* 127 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getHurtVal() {
/* 133 */     return this.hurtVal;
/*     */   }
/*     */   
/*     */   public void setHurtVal(long hurtVal) {
/* 137 */     this.hurtVal = hurtVal;
/*     */   }
/*     */   
/*     */   public IFighter getMaster() {
/* 141 */     return this.master;
/*     */   }
/*     */   
/*     */   public void setMaster(IFighter master) {
/* 145 */     this.master = master;
/*     */   }
/*     */   
/*     */   public IFighter getOwner() {
/* 149 */     return this.owner;
/*     */   }
/*     */   
/*     */   public void setOwner(IFighter owner) {
/* 153 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   public int getTimes() {
/* 157 */     return this.times;
/*     */   }
/*     */   
/*     */   public void setTimes(int times) {
/* 161 */     this.times = times;
/*     */   }
/*     */   
/*     */   public boolean getRoundReset() {
/* 165 */     return this.roundReset;
/*     */   }
/*     */   
/*     */   public void setRoundReset(boolean roundReset) {
/* 169 */     this.roundReset = roundReset;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\AbstractBuff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */