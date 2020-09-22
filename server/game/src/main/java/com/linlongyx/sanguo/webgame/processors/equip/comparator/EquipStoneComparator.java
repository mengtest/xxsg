/*    */ package com.linlongyx.sanguo.webgame.processors.equip.comparator;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.IntIntValue;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipStoneComparator
/*    */   implements Comparator<IntIntValue>
/*    */ {
/*    */   public int compare(IntIntValue o1, IntIntValue o2) {
/* 15 */     if (o1.value == o2.value) {
/* 16 */       return 0;
/*    */     }
/* 18 */     return (o1.value - o2.value < 0) ? -1 : 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\comparator\EquipStoneComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */