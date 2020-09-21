/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.GroupMemberData;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupMemberComparator
/*    */   implements Comparator<GroupMemberData>
/*    */ {
/*    */   public int compare(GroupMemberData o1, GroupMemberData o2) {
/* 13 */     if (o1 == o2) {
/* 14 */       return 0;
/*    */     }
/* 16 */     if (o1.position == o2.position) {
/* 17 */       if (o1.fightValue == o2.fightValue)
/* 18 */         return 0; 
/* 19 */       if (o1.fightValue > o2.fightValue) {
/* 20 */         return -1;
/*    */       }
/* 22 */       return 1;
/* 23 */     }  if (o1.position < o2.position) {
/* 24 */       return -1;
/*    */     }
/* 26 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupMemberComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */