/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.target;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum SINGLE_TARGET
/*    */ {
/*  8 */   DEFAULT(0, new DefaultTarget()),
/*  9 */   SELF(1, new SelfTarget()),
/* 10 */   FRONT(2, new FrontTarget()),
/* 11 */   BACK(3, new BackTarget()),
/*    */   
/* 13 */   MIN_FURY(4, new MinFuryTarget()),
/* 14 */   MAX_FURY(5, new MaxFuryTarget()),
/*    */   
/* 16 */   MIN_CURHP(6, new MinHpTarget()),
/* 17 */   MAX_CURHP(7, new MaxHpTarget()),
/*    */   
/* 19 */   MIN_HP(8, new MinHpPerTarget()),
/* 20 */   MAX_HP(9, new MaxHpPerTarget()),
/* 21 */   MIN_DEF(10, new MinDefTarget()),
/* 22 */   MAX_DEF(11, new MaxDefTarget()),
/*    */   
/* 24 */   RANDOM(12, new RandomTarget());
/*    */   
/*    */   private int type;
/*    */   
/*    */   private SingleSelectable targetSelect;
/*    */ 
/*    */   
/*    */   public static class TYPE
/*    */   {
/*    */     public static final int TARGET_DEFAULT = 0;
/*    */     
/*    */     public static final int TARGET_SELF = 1;
/*    */     
/*    */     public static final int TARGET_FRONT = 2;
/*    */     
/*    */     public static final int TARGET_BACK = 3;
/*    */     
/*    */     public static final int TARGET_MIN_FURY = 4;
/*    */     
/*    */     public static final int TARGET_MAX_FURY = 5;
/*    */     public static final int TARGET_MIN_CURHP = 6;
/*    */     public static final int TARGET_MAX_CURHP = 7;
/*    */     public static final int TARGET_MIN_HP_PER = 8;
/*    */     public static final int TARGET_MAX_HP_PER = 9;
/*    */     public static final int TARGET_MIN_DEF_CUR = 10;
/*    */     public static final int TARGET_MAX_DEF_CUR = 11;
/*    */     public static final int TARGET_RANDOM = 12;
/*    */   }
/*    */   
/*    */   SINGLE_TARGET(int type, SingleSelectable select) {
/* 54 */     this.type = type;
/* 55 */     this.targetSelect = select;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 60 */     return this.type;
/*    */   }
/*    */   
/*    */   public SingleSelectable getTargetSelect() {
/* 64 */     return this.targetSelect;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\target\SINGLE_TARGET.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */