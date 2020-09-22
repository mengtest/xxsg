/*    */ package linlongyx.core.utils;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogUtils
/*    */ {
/* 12 */   private static final Logger errorLogger = LoggerFactory.getLogger("error");
/* 13 */   private static StringBuilder errorStringBuilder = new StringBuilder();
/*    */   
/* 15 */   private static final Logger debugLogger = LoggerFactory.getLogger("debug");
/* 16 */   private static StringBuilder debugStringBuilder = new StringBuilder();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void errorLog(Object... objects) {
/* 23 */     errorStringBuilder.setLength(0);
/* 24 */     for (Object obj : objects)
/* 25 */       errorStringBuilder.append(obj).append(" "); 
/* 26 */     errorLogger.error(errorStringBuilder.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void debugLog(Object... objects) {
/* 34 */     debugStringBuilder.setLength(0);
/* 35 */     for (Object obj : objects)
/* 36 */       debugStringBuilder.append(obj).append(" "); 
/* 37 */     debugLogger.error(debugStringBuilder.toString());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\LogUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */