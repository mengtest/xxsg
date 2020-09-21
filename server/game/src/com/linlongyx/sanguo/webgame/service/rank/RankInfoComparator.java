/*    */ package com.linlongyx.sanguo.webgame.service.rank;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.RankingData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RankInfoComparator
/*    */   implements Comparator<RankingData>
/*    */ {
/*    */   public int compare(RankingData o1, RankingData o2) {
/* 13 */     if (o1.value > o2.value)
/* 14 */       return -1; 
/* 15 */     if (o1.value == o2.value && 
/* 16 */       o1.playerId < o2.playerId) {
/* 17 */       return -1;
/*    */     }
/*    */     
/* 20 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\rank\RankInfoComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */