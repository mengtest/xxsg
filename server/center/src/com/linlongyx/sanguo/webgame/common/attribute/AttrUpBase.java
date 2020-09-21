/*    */ package com.linlongyx.sanguo.webgame.common.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightConstant;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.FightUtil;
/*    */ import com.linlongyx.sanguo.webgame.common.fight.attribute.AttributeType;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AttrUpBase
/*    */   implements Attribute
/*    */ {
/* 17 */   protected long[] attrs = new long[AttributeType.ATTRIB_TYPE_END.getType()];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected static long[] fightWeight;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int type;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isBaseAffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AttrUpBase(int type, boolean isBaseAffect) {
/* 41 */     this.type = type;
/* 42 */     this.isBaseAffect = isBaseAffect;
/* 43 */     fightWeight = FightConstant.getWeights();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getAttrByType(int type) {
/* 53 */     if (type < 0 || type >= this.attrs.length)
/* 54 */       return 0L; 
/* 55 */     return this.attrs[type];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getFightValue() {
/* 64 */     long total = 0L; int index;
/* 65 */     for (index = AttributeType.ATTACK.getType(); index < this.attrs.length; index++) {
/* 66 */       total += this.attrs[index] * fightWeight[index];
/*    */     }
/* 68 */     for (index = AttributeType.ATTACK.getType(); index < this.attrs.length; index++) {
/*    */       
/* 70 */       if (FightUtil.isBaseAttr(index))
/* 71 */         total += total * getAttrByType(index + 17) / 10000L; 
/*    */     } 
/* 73 */     return total;
/*    */   }
/*    */   
/*    */   public long getSysFightValue() {
/* 77 */     return this.attrs[AttributeType.ATTRIBUTE_NON.getType()];
/*    */   }
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {}
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {}
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\attribute\AttrUpBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */