/*    */ package com.linlongyx.sanguo.webgame.common.fight.select.range;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum RANGE_TARGET
/*    */ {
/*  8 */   RANGE_ONE(1, new SingleRange()),
/*  9 */   RANGE_ROW(2, new RowRange()),
/* 10 */   FRONT(3, new LineRange()),
/* 11 */   BACK(4, new CrossRange()),
/*    */   
/* 13 */   MIN_FURY(5, new AroundRange()),
/* 14 */   MAX_FURY(6, new AllRange()),
/*    */   
/* 16 */   RAND_TWO(7, new RandTwoRange()),
/* 17 */   RAND_THREE(8, new RandThreeRange()),
/* 18 */   RAND_FOUR(9, new RandFourRange());
/*    */   
/*    */   private int type;
/*    */   
/*    */   private RangeSelectable rangeSelect;
/*    */ 
/*    */   
/*    */   public static class TYPE
/*    */   {
/*    */     public static final int RANGE_ONE = 1;
/*    */     
/*    */     public static final int RANGE_ROW = 2;
/*    */     public static final int RANGE_LINE = 3;
/*    */     public static final int RANGE_CROSS = 4;
/*    */     public static final int RANGE_AROUND = 5;
/*    */     public static final int RANGE_ALL = 6;
/*    */     public static final int RANGE_RAND_TWO = 7;
/*    */     public static final int RANGE_RAND_THREE = 8;
/*    */     public static final int RANGE_RAND_FOUR = 9;
/*    */   }
/*    */   
/*    */   RANGE_TARGET(int type, RangeSelectable select) {
/* 40 */     this.type = type;
/* 41 */     this.rangeSelect = select;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getType() {
/* 46 */     return this.type;
/*    */   }
/*    */   
/*    */   public RangeSelectable getRangeSelect() {
/* 50 */     return this.rangeSelect;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\select\range\RANGE_TARGET.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */