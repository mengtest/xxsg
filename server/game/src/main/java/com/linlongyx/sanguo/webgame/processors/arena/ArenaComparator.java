/*    */ package com.linlongyx.sanguo.webgame.processors.arena;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.ArenaData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArenaComparator
/*    */   implements Comparator<ArenaData>
/*    */ {
/*    */   public int compare(ArenaData o1, ArenaData o2) {
/* 13 */     if (o1 == null && o2 == null) {
/* 14 */       return 0;
/*    */     }
/* 16 */     if (o1 == null) {
/* 17 */       return 1;
/*    */     }
/* 19 */     if (o2 == null) {
/* 20 */       return -1;
/*    */     }
/* 22 */     if (o1.rank > o2.rank)
/* 23 */       return 1; 
/* 24 */     if (o1.rank < o2.rank) {
/* 25 */       return -1;
/*    */     }
/* 27 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\arena\ArenaComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */