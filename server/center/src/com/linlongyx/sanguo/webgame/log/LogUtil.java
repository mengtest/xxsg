/*    */ package com.linlongyx.sanguo.webgame.log;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.job.LogWriterJob;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogUtil
/*    */ {
/* 12 */   private static StringBuilder stringBuilder = new StringBuilder();
/*    */   
/*    */   private static final char SEPARATOR = '|';
/* 15 */   private static final Logger errorLogger = LoggerFactory.getLogger("error");
/* 16 */   private static StringBuilder errorStringBuilder = new StringBuilder();
/*    */   
/* 18 */   private static final Logger debugLogger = LoggerFactory.getLogger("debug");
/* 19 */   private static StringBuilder debugStringBuilder = new StringBuilder();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void errorLog(Object... objects) {
/* 27 */     errorStringBuilder.setLength(0);
/* 28 */     for (Object obj : objects)
/* 29 */       errorStringBuilder.append(obj).append(" "); 
/* 30 */     errorLogger.error(errorStringBuilder.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void debugLog(Object... objects) {
/* 39 */     debugStringBuilder.setLength(0);
/* 40 */     for (Object obj : objects)
/* 41 */       debugStringBuilder.append(obj).append(" "); 
/* 42 */     debugLogger.error(debugStringBuilder.toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void gameLog(LogType type, Object... objects) {
/* 52 */     addLog(type, objects);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static synchronized void addLog(LogType type, Object... objects) {
/* 62 */     stringBuilder.setLength(0);
/* 63 */     stringBuilder.append(type.getTableName());
/*    */     
/* 65 */     for (Object obj : objects) {
/* 66 */       stringBuilder.append('|').append(obj);
/*    */     }
/* 68 */     LogWriterJob.addLog(stringBuilder.toString());
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\log\LogUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */