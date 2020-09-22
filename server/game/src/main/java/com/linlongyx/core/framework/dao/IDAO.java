/*    */ package com.linlongyx.core.framework.dao;
/*    */ 
/*    */ public interface IDAO {
/*    */   TYPE getType();
/*    */   
/*    */   Object getTemplate();
/*    */   
/*    */   public enum TYPE {
/*  9 */     REDIS,
/* 10 */     MYSQL,
/* 11 */     MYBATIS,
/* 12 */     DRUID;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\IDAO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */