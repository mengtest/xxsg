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
/*    */ public class WarPetAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public WarPetAttrUp() {
/* 17 */     super(PlayerAttrUp.AttrUpType.WARPET.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 22 */     Arrays.fill(this.attrs, 0L);
/* 23 */     AttrUpdateUtil.warPetAttr(player, this.attrs, updates, -1L);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 28 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     Arrays.fill(this.attrs, 0L);
/* 33 */     AttrUpdateUtil.warPetAttr(player, this.attrs, updates, pid);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\WarPetAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */