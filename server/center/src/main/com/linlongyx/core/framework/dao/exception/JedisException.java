/*    */ package com.linlongyx.core.framework.dao.exception;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JedisException
/*    */   extends Exception
/*    */ {
/*    */   public JedisException() {}
/*    */   
/*    */   public JedisException(String message) {
/* 12 */     super(message);
/*    */   }
/*    */   
/*    */   public JedisException(String message, Throwable cause) {
/* 16 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public JedisException(Throwable cause) {
/* 20 */     super(cause);
/*    */   }
/*    */   
/*    */   public JedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
/* 24 */     super(message, cause, enableSuppression, writableStackTrace);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\exception\JedisException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */