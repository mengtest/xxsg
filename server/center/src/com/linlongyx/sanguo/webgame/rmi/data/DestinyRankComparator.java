/*    */ package com.linlongyx.sanguo.webgame.rmi.data;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestinyRankComparator
/*    */   implements Comparator<DestinyPlayerData>
/*    */ {
/*    */   public int compare(DestinyPlayerData o1, DestinyPlayerData o2) {
/* 12 */     if (o1 == null && o2 == null) {
/* 13 */       return -1;
/*    */     }
/* 15 */     if (o1 == null) {
/* 16 */       return 1;
/*    */     }
/* 18 */     if (o2 == null) {
/* 19 */       return -1;
/*    */     }
/*    */     
/* 22 */     if (o1.getDestinyPoint() > o2.getDestinyPoint())
/* 23 */       return -1; 
/* 24 */     if (o1.getDestinyPoint() < o2.getDestinyPoint()) {
/* 25 */       return 1;
/*    */     }
/* 27 */     return Integer.compare(o1.getTimestamp(), o2.getTimestamp());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\data\DestinyRankComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */