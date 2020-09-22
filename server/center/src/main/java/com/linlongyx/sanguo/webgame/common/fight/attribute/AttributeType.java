/*    */ package com.linlongyx.sanguo.webgame.common.fight.attribute;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum AttributeType
/*    */ {
/* 33 */   ATTRIBUTE_NON(0),
/* 34 */   ATTACK(1),
/* 35 */   PHY_DEF(2),
/* 36 */   MAG_DEF(3),
/* 37 */   HP(4),
/* 38 */   BASE_ATTR_END(5),
/*    */   
/* 40 */   CRIT(5),
/* 41 */   ANTI_CRIT(6),
/*    */   
/* 43 */   HIT(7),
/* 44 */   DODGE(8),
/*    */   
/* 46 */   STRIKE(9),
/* 47 */   ANTI_STRIKE(10),
/*    */   
/* 49 */   HURT_RATE(11, true),
/* 50 */   HURT_DERATE(12, true),
/*    */   
/* 52 */   HURT(13),
/* 53 */   DE_HURT(14),
/*    */   
/* 55 */   IGNORE_DEF(15),
/* 56 */   CRIT_RATE(16),
/*    */   
/* 58 */   FIRST_HAND(17),
/*    */   
/* 60 */   ATTACK_RATE(18, true),
/* 61 */   PHY_DEF_RATE(19, true),
/* 62 */   MAG_DEF_RATE(20, true),
/* 63 */   HP_RATE(21, true),
/*    */   
/* 65 */   ANTI_CRIT_RATE(22, true),
/* 66 */   HIT_RATE(23, true),
/* 67 */   DODGE_RATE(24, true),
/* 68 */   INIT_FURY(25, true),
/* 69 */   FINAL_HURT_RATE(26),
/* 70 */   FINAL_HURT_DERATE(27),
/*    */   
/* 72 */   RECOVER_HP_RATE(28, true),
/*    */   
/* 74 */   ATTRIB_TYPE_END(29);
/*    */   
/*    */   private final int type;
/*    */   
/*    */   private final boolean isRate;
/*    */ 
/*    */   
/*    */   AttributeType(int type) {
/* 82 */     this.type = type;
/* 83 */     this.isRate = false;
/*    */   }
/*    */   
/*    */   AttributeType(int type, boolean isRate) {
/* 87 */     this.type = type;
/* 88 */     this.isRate = isRate;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 92 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isRate() {
/* 97 */     return this.isRate;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\attribute\AttributeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */