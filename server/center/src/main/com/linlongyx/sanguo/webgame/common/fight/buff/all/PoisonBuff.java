/*    */ package com.linlongyx.sanguo.webgame.common.fight.buff.all;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.IFight;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BuffBean;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BoutPlayData;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PoisonBuff
/*    */   extends AbstractBuff
/*    */ {
/*    */   public PoisonBuff() {}
/*    */   
/*    */   public PoisonBuff(BuffBean bean, int level) {
/* 22 */     super(bean, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public long effect(IFight fight, boolean addAtom) {
/* 27 */     return effect(fight, addAtom, calHurt());
/*    */   }
/*    */ 
/*    */   
/*    */   public long effect(IFight fight, boolean addAtom, long finalHurt) {
/* 32 */     if (this.owner.isDead()) {
/* 33 */       LogUtil.errorLog(new Object[] { "PoisonBuff::effect", "owner is dead", this.owner });
/* 34 */       return 0L;
/*    */     } 
/* 36 */     finalHurt = this.owner.decHP(finalHurt, getMaster());
/* 37 */     fight.getSide(FightUtil.getTargetSide(this.owner.getSide())).updateTotalHurt(finalHurt);
/*    */     
/* 39 */     if (addAtom) {
/* 40 */       BoutPlayData playData = fight.genBoutPlayData();
/* 41 */       ResultPlayData resultPlayData = new ResultPlayData();
/* 42 */       resultPlayData.targetGuid = this.owner.getGuid();
/* 43 */       resultPlayData.targeAction = 0;
/*    */       
/* 45 */       resultPlayData.hp = (int)(-1L * finalHurt);
/*    */       
/* 47 */       resultPlayData.atomData.add(Integer.valueOf(10));
/* 48 */       resultPlayData.atomData.add(Integer.valueOf(resultPlayData.hp));
/* 49 */       playData.buffActions.add(resultPlayData);
/*    */     } 
/* 51 */     return finalHurt;
/*    */   }
/*    */ 
/*    */   
/*    */   public long calHurt() {
/* 56 */     return getHurtVal() * getEffecPer() / 10000L + getEffectValue();
/*    */   }
/*    */   
/*    */   public long immediate(ResultPlayData resultPlayData) {
/* 60 */     if (this.owner.isDead()) {
/* 61 */       LogUtil.errorLog(new Object[] { "PoisonBuff::immediate", "owner is dead", this.owner });
/* 62 */       return 0L;
/*    */     } 
/* 64 */     long hp = getHurtVal() * getEffecPer() / 10000L + getEffectValue();
/* 65 */     hp = this.owner.decHP(hp, getMaster());
/*    */     
/* 67 */     resultPlayData.atomData.add(Integer.valueOf(10));
/* 68 */     resultPlayData.atomData.add(Integer.valueOf((int)hp * -1));
/* 69 */     return hp;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\all\PoisonBuff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */