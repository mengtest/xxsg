/*    */ package com.linlongyx.sanguo.webgame.log;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.job.LogWriterJob;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogUtil
/*    */ {
/* 13 */   private static StringBuilder stringBuilder = new StringBuilder();
/*    */   
/*    */   private static final char SEPARATOR = '|';
/* 16 */   private static final Logger errorLogger = LoggerFactory.getLogger("error");
/* 17 */   private static StringBuilder errorStringBuilder = new StringBuilder();
/*    */   
/* 19 */   private static final Logger debugLogger = LoggerFactory.getLogger("debug");
/* 20 */   private static StringBuilder debugStringBuilder = new StringBuilder();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void errorLog(Object... objects) {
/* 28 */     errorStringBuilder.setLength(0);
/* 29 */     for (Object obj : objects)
/* 30 */       errorStringBuilder.append(obj).append(" "); 
/* 31 */     errorLogger.error(errorStringBuilder.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void debugLog(Object... objects) {
/* 40 */     debugStringBuilder.setLength(0);
/* 41 */     for (Object obj : objects)
/* 42 */       debugStringBuilder.append(obj).append(" "); 
/* 43 */     debugLogger.debug(debugStringBuilder.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void gameLog(LogType type, Object... objects) {
/* 53 */     addLog(type, objects);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static synchronized void addLog(LogType type, Object... objects) {
/* 63 */     stringBuilder.setLength(0);
/* 64 */     stringBuilder.append(type.getTableName());
/*    */     
/* 66 */     for (Object obj : objects) {
/* 67 */       stringBuilder.append('|').append(obj);
/*    */     }
/*    */     
/* 70 */     LogWriterJob.addLog(stringBuilder.toString());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\log\LogUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */