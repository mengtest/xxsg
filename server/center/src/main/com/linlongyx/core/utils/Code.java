/*    */ package com.linlongyx.core.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Code
/*    */ {
/*  8 */   private static Code code = null;
/*    */   
/*    */   public static Code getInstance() {
/* 11 */     if (code == null) {
/* 12 */       code = new Code();
/*    */     }
/* 14 */     return code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode(String key) {
/* 19 */     MD5 md5 = new MD5();
/* 20 */     String code = md5.toDigest(key);
/* 21 */     return code;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final int java8hash(Object key) {
/*    */     int h;
/* 31 */     return (key == null) ? 0 : ((h = key.hashCode()) ^ h >>> 16);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\Code.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */