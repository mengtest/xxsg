/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankKaiFuComparator
/*    */   implements Comparator<RankingData>
/*    */ {
/*    */   public int compare(RankingData o1, RankingData o2) {
/* 15 */     if (o1 == null && o2 == null) {
/* 16 */       return 0;
/*    */     }
/* 18 */     if (o1 == null) {
/* 19 */       return 1;
/*    */     }
/* 21 */     if (o2 == null) {
/* 22 */       return -1;
/*    */     }
/*    */     
/* 25 */     if (o1.value > o2.value)
/* 26 */       return -1; 
/* 27 */     if (o1.value < o2.value) {
/* 28 */       return 1;
/*    */     }
/* 30 */     if (o1.fightValue > o2.fightValue)
/* 31 */       return 1; 
/* 32 */     if (o1.fightValue < o2.fightValue) {
/* 33 */       return -1;
/*    */     }
/*    */     
/* 36 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankKaiFuComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */