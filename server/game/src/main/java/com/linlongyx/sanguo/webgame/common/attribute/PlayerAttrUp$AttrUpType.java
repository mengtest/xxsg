/*    */ package com.linlongyx.sanguo.webgame.common.attribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum AttrUpType
/*    */ {
/* 10 */   BASE(0),
/* 11 */   EQUIP(1),
/* 12 */   PARTNER(2),
/* 13 */   SKILL(3),
/* 14 */   PARTNER_EQUIP(4),
/* 15 */   LEADER(5),
/* 16 */   WARPET(6),
/* 17 */   GROUP(7),
/* 18 */   TITLE(8),
/* 19 */   MOUNTS(9),
/* 20 */   FASHION(10),
/* 21 */   GROWTHGOAL(11),
/* 22 */   DESTINY(12),
/* 23 */   CARDBOOK(13),
/* 24 */   MILITARY(14),
/* 25 */   KUNGFU(15),
/* 26 */   STAGE(16),
/* 27 */   WARZHENFA(17),
/* 28 */   SOULS(18),
/* 29 */   ASSIST(19),
/* 30 */   ARTIFACT(20),
/* 31 */   RUNE(21),
/* 32 */   REINCARN(22),
/* 33 */   END(23);
/*    */   
/*    */   private final int index;
/*    */   
/*    */   AttrUpType(int index) {
/* 38 */     this.index = index;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 42 */     return this.index;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\attribute\PlayerAttrUp$AttrUpType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */