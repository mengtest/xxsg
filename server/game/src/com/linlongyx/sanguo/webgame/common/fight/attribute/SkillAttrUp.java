/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.AttrUpBase;
/*    */ import com.linlongyx.sanguo.webgame.common.attribute.PlayerAttrUp;
/*    */ import java.util.Arrays;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkillAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public SkillAttrUp() {
/* 17 */     super(PlayerAttrUp.AttrUpType.SKILL.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 22 */     Arrays.fill(this.attrs, 0L);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\SkillAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */