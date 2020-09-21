/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankSInsComparator
/*    */   implements Comparator<RankingData>
/*    */ {
/*    */   public int compare(RankingData o1, RankingData o2) {
/* 14 */     if (o1 == null && o2 == null) {
/* 15 */       return 0;
/*    */     }
/* 17 */     if (o1 == null) {
/* 18 */       return 1;
/*    */     }
/* 20 */     if (o2 == null) {
/* 21 */       return -1;
/*    */     }
/*    */     
/* 24 */     if (o1.value > o2.value)
/* 25 */       return -1; 
/* 26 */     if (o1.value < o2.value) {
/* 27 */       return 1;
/*    */     }
/* 29 */     if (o1.vip > o2.vip)
/* 30 */       return -1; 
/* 31 */     if (o1.vip < o2.vip) {
/* 32 */       return 1;
/*    */     }
/*    */     
/* 35 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankSInsComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */