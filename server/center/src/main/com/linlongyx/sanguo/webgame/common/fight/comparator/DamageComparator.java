/*    */ package com.linlongyx.sanguo.webgame.common.fight.comparator;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.BossDamageData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageComparator
/*    */   implements Comparator<BossDamageData>
/*    */ {
/*    */   public int compare(BossDamageData o1, BossDamageData o2) {
/* 13 */     if (o1 == o2) {
/* 14 */       return 0;
/*    */     }
/* 16 */     long temp1 = o1.damage - o2.damage;
/* 17 */     if (temp1 == 0L)
/* 18 */       return 0; 
/* 19 */     if (temp1 > 0L) {
/* 20 */       return -1;
/*    */     }
/* 22 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\common\fight\comparator\DamageComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */