/*    */ package com.linlongyx.sanguo.webgame.quartz;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.handler.MD5;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Code
/*    */ {
/* 10 */   private static Code code = null;
/*    */   
/*    */   public static Code getInstance() {
/* 13 */     if (code == null) {
/* 14 */       code = new Code();
/*    */     }
/* 16 */     return code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCode(String key) {
/* 21 */     MD5 md5 = new MD5();
/* 22 */     String code = md5.toDigest(key);
/* 23 */     return code;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\Code.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */