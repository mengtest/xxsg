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
/*    */ public class MountsAttrUp
/*    */   extends AttrUpBase
/*    */ {
/*    */   public MountsAttrUp() {
/* 16 */     super(PlayerAttrUp.AttrUpType.MOUNTS.getIndex(), false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refresh(IPlayer player, Set<Integer> updates) {
/* 21 */     Arrays.fill(this.attrs, 0L);
/* 22 */     AttrUpdateUtil.mountsAttr(player, this.attrs, updates, -1L);
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshPartner(IPlayer player, Set<Integer> updates, long pid) {
/* 27 */     if (pid == -1L) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     Arrays.fill(this.attrs, 0L);
/* 32 */     AttrUpdateUtil.mountsAttr(player, this.attrs, updates, pid);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\MountsAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */