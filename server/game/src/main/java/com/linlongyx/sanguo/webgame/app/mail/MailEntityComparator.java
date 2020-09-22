/*    */ package com.linlongyx.sanguo.webgame.app.mail;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MailEntityComparator
/*    */   implements Comparator<MailEntity>
/*    */ {
/*    */   public int compare(MailEntity o1, MailEntity o2) {
/* 11 */     if (o1.getIsRead() < o2.getIsRead())
/* 12 */       return -1; 
/* 13 */     if (o1.getIsRead() == o2.getIsRead()) {
/* 14 */       if (o1.getIsExtract() < o2.getIsExtract())
/* 15 */         return -1; 
/* 16 */       if (o1.getIsExtract() == o2.getIsExtract()) {
/* 17 */         if (o1.getSendTime() > o2.getSendTime())
/* 18 */           return -1; 
/* 19 */         if (o1.getSendTime() == o2.getSendTime()) {
/* 20 */           return 0;
/*    */         }
/*    */       } 
/*    */     } 
/* 24 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\mail\MailEntityComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */