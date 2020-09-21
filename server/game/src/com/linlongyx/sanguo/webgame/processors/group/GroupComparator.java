/*    */ package com.linlongyx.sanguo.webgame.processors.group;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.group.GroupEntity;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroupComparator
/*    */   implements Comparator<GroupEntity>
/*    */ {
/*    */   public int compare(GroupEntity o1, GroupEntity o2) {
/* 13 */     if (o1 == o2) {
/* 14 */       return 0;
/*    */     }
/* 16 */     if (o1.getLevel() == o2.getLevel()) {
/* 17 */       if (o1.getExp() == o2.getExp())
/* 18 */         return 0; 
/* 19 */       if (o1.getExp() > o2.getExp()) {
/* 20 */         return -1;
/*    */       }
/* 22 */       return 1;
/* 23 */     }  if (o1.getLevel() > o2.getLevel()) {
/* 24 */       return -1;
/*    */     }
/*    */     
/* 27 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\group\GroupComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */