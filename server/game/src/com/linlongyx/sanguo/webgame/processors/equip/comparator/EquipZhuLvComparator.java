/*    */ package com.linlongyx.sanguo.webgame.processors.equip.comparator;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.app.equip.EquipEntity;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EquipZhuLvComparator
/*    */   implements Comparator<EquipEntity>
/*    */ {
/*    */   public int compare(EquipEntity o1, EquipEntity o2) {
/* 15 */     if (o1.getZhuLv() == o2.getZhuLv()) {
/* 16 */       return 0;
/*    */     }
/* 18 */     return (o1.getZhuLv() - o2.getZhuLv() < 0) ? -1 : 1;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\equip\comparator\EquipZhuLvComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */