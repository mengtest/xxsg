/*    */ package com.linlongyx.core.framework.dao.proxy;public interface IEntityProxy extends IProxy { ENTITY_STATUS getEntityStatus();
/*    */   void setEntityStatus(ENTITY_STATUS paramENTITY_STATUS);
/*    */   Object getEntity();
/*    */   Class<?> getEntityClass();
/*    */   String getTableName();
/*    */   String getKeyName();
/*    */   
/*    */   String getKeyValue();
/*    */   
/* 10 */   public enum ENTITY_STATUS { STATUS_ADD(1), STATUS_DEL(1), STATUS_MOD(2), STATUS_NON(3);
/*    */     
/*    */     private int index;
/*    */ 
/*    */     
/*    */     ENTITY_STATUS(int index) {
/* 16 */       this.index = index;
/*    */     }
/*    */     
/*    */     public int getIndex() {
/* 20 */       return this.index;
/*    */     }
/*    */     
/*    */     public void setIndex(int index) {
/* 24 */       this.index = index;
/*    */     } }
/*    */    }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\proxy\IEntityProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */