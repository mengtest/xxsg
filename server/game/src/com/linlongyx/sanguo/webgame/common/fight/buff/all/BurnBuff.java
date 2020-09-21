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
/*    */ public class BurnBuff
/*    */   extends AbstractBuff
/*    */ {
/*    */   public BurnBuff() {}
/*    */   
/*    */   public BurnBuff(BuffBean bean, int level) {
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
/* 33 */       LogUtil.errorLog(new Object[] { "BurnBuff::effect", "owner is dead", this.owner });
/* 34 */       return 0L;
/*    */     } 
/* 36 */     finalHurt = this.owner.decHP(finalHurt, getMaster());
/* 37 */     fight.getSide(FightUtil.getTargetSide(this.owner.getSide())).updateTotalHurt(finalHurt);
/* 38 */     if (addAtom) {
/* 39 */       BoutPlayData playData = fight.genBoutPlayData();
/* 40 */       ResultPlayData resultPlayData = new ResultPlayData();
/* 41 */       resultPlayData.targetGuid = this.owner.getGuid();
/* 42 */       resultPlayData.targeAction = 0;
/* 43 */       resultPlayData.hp = (int)(-1L * finalHurt);
/*    */       
/* 45 */       resultPlayData.atomData.add(Integer.valueOf(11));
/* 46 */       resultPlayData.atomData.add(Integer.valueOf(resultPlayData.hp));
/* 47 */       playData.buffActions.add(resultPlayData);
/*    */     } 
/* 49 */     return finalHurt;
/*    */   }
/*    */ 
/*    */   
/*    */   public long calHurt() {
/* 54 */     return getHurtVal() * getEffecPer() / 10000L + getEffectValue();
/*    */   }
/*    */   
/*    */   public long immediate(ResultPlayData resultPlayData) {
/* 58 */     if (this.owner.isDead()) {
/* 59 */       LogUtil.errorLog(new Object[] { "BurnBuff::immediate", "owner is dead", this.owner });
/* 60 */       return 0L;
/*    */     } 
/* 62 */     long hp = getHurtVal() * getEffecPer() / 10000L + getEffectValue();
/* 63 */     hp = this.owner.decHP(hp, getMaster());
/*    */     
/* 65 */     resultPlayData.atomData.add(Integer.valueOf(11));
/* 66 */     resultPlayData.atomData.add(Integer.valueOf((int)hp * -1));
/* 67 */     return hp;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\all\BurnBuff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */