/*    */ package com.linlongyx.core.framework.dao.proxy;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ENTITY_STATUS
/*    */ {
/* 10 */   STATUS_ADD(1), STATUS_DEL(1), STATUS_MOD(2), STATUS_NON(3);
/*    */   
/*    */   private int index;
/*    */ 
/*    */   
/*    */   ENTITY_STATUS(int index) {
/* 16 */     this.index = index;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 20 */     return this.index;
/*    */   }
/*    */   
/*    */   public void setIndex(int index) {
/* 24 */     this.index = index;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\proxy\IEntityProxy$ENTITY_STATUS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */