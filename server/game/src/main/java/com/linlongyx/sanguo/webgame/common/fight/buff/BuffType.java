/*    */ package com.linlongyx.sanguo.webgame.common.fight.buff;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.fight.buff.all.BurnBuff;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.buff.all.PoisonBuff;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.buff.all.SuckHpBuff;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BuffType
/*    */ {
/* 12 */   ADD_ATTRIB(0),
/* 13 */   DEC_ATTRIB(1),
/*    */   
/* 15 */   DIZZY(1001),
/* 16 */   SILENT(1002),
/*    */   
/* 18 */   BURN_HURT(1003, (Class)BurnBuff.class),
/* 19 */   POISON_HURT(1004, (Class)PoisonBuff.class),
/*    */   
/* 21 */   DISORDER(1005),
/*    */   
/* 23 */   IMMUNE_HURT(1008),
/* 24 */   IMMUNE_DEBUFF(1009),
/* 25 */   SUCK_HP(1010, (Class)SuckHpBuff.class);
/*    */ 
/*    */   
/*    */   private final int type;
/*    */   
/*    */   private final Class<? extends AbstractBuff> buffClass;
/*    */ 
/*    */   
/*    */   BuffType(int type) {
/* 34 */     this.type = type;
/* 35 */     this.buffClass = AbstractBuff.class;
/*    */   }
/*    */   
/*    */   BuffType(int type, Class<? extends AbstractBuff> buffClass) {
/* 39 */     this.type = type;
/* 40 */     this.buffClass = buffClass;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 44 */     return this.type;
/*    */   }
/*    */   
/*    */   public AbstractBuff build() {
/*    */     try {
/* 49 */       return this.buffClass.newInstance();
/* 50 */     } catch (InstantiationException e) {
/* 51 */       LogUtil.errorLog(new Object[] { "BuffType::build error: ", e.getMessage() });
/* 52 */     } catch (IllegalAccessException e) {
/* 53 */       e.printStackTrace();
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\buff\BuffType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */