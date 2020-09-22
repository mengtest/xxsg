/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipFightValue
/*    */   extends AttrUpBase
/*    */ {
/*    */   public EquipFightValue(int type, boolean isBaseAffect) {
/* 21 */     super(type, isBaseAffect);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getEquipFightValue(EquipEntity equipEntity) {
/* 30 */     Arrays.fill(this.attrs, 0L);
/* 31 */     AttrUpdateUtil.updateNormal(null, equipEntity, this.attrs, new HashSet<>(), new HashMap<>());
/* 32 */     return getFightValue() / 10000L;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\EquipFightValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */