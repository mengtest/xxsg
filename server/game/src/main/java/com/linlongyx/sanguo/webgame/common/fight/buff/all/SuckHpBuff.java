/*    */ package com.linlongyx.sanguo.webgame.common.fight.buff.all;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightCalculator;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.buff.AbstractBuff;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.BuffBean;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ResultPlayData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SuckHpBuff
/*    */   extends AbstractBuff
/*    */ {
/*    */   public SuckHpBuff() {}
/*    */   
/*    */   public SuckHpBuff(BuffBean bean, int level) {
/* 26 */     super(bean, level);
/*    */   }
/*    */   
/*    */   public long immediate(ResultPlayData resultPlayData, long hurtVal) {
/* 30 */     if (this.owner.isDead()) {
/* 31 */       LogUtil.errorLog(new Object[] { "SuckHpBuff::immediate", "owner is dead", this.owner });
/* 32 */       return 0L;
/*    */     } 
/* 34 */     long hp = hurtVal * getEffecPer() / 10000L + getEffectValue();
/*    */     
/* 36 */     hp = FightCalculator.calFinalRecoverHp(hp, this.owner);
/*    */     
/* 38 */     this.owner.addHP(hp);
/*    */     
/* 40 */     resultPlayData.atomData.add(Integer.valueOf(3));
/* 41 */     resultPlayData.atomData.add(Integer.valueOf(this.owner.getGuid()));
/* 42 */     resultPlayData.atomData.add(Integer.valueOf((int)hp));
/* 43 */     return hp;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\all\SuckHpBuff.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */