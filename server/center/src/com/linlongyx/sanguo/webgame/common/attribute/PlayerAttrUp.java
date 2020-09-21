/*    */ package com.linlongyx.sanguo.webgame.common.attribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerAttrUp
/*    */ {
/*    */   public enum AttrUpType
/*    */   {
/* 10 */     BASE(0),
/* 11 */     EQUIP(1),
/* 12 */     PARTNER(2),
/* 13 */     SKILL(3),
/* 14 */     PARTNER_EQUIP(4),
/* 15 */     LEADER(5),
/* 16 */     WARPET(6),
/* 17 */     GROUP(7),
/* 18 */     TITLE(8),
/* 19 */     MOUNTS(9),
/* 20 */     FASHION(10),
/* 21 */     GrowthGoal(11),
/* 22 */     END(12);
/*    */     
/*    */     private final int index;
/*    */     
/*    */     AttrUpType(int index) {
/* 27 */       this.index = index;
/*    */     }
/*    */     
/*    */     public int getIndex() {
/* 31 */       return this.index;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\attribute\PlayerAttrUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */