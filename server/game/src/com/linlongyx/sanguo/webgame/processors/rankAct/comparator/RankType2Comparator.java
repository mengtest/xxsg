/*    */ package com.linlongyx.sanguo.webgame.processors.rankAct.comparator;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankType2Comparator
/*    */   implements Comparator<RankingData>
/*    */ {
/*    */   public int compare(RankingData o1, RankingData o2) {
/* 13 */     if (o1 == null && o2 == null) {
/* 14 */       return 0;
/*    */     }
/* 16 */     if (o1 == null) {
/* 17 */       return 1;
/*    */     }
/* 19 */     if (o2 == null) {
/* 20 */       return -1;
/*    */     }
/*    */     
/* 23 */     if (o1.value > o2.value)
/* 24 */       return -1; 
/* 25 */     if (o1.value < o2.value) {
/* 26 */       return 1;
/*    */     }
/* 28 */     if (o1.level > o2.level)
/* 29 */       return -1; 
/* 30 */     if (o1.level < o2.level) {
/* 31 */       return 1;
/*    */     }
/*    */     
/* 34 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\rankAct\comparator\RankType2Comparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */