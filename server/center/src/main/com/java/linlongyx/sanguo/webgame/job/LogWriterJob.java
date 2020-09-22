/*    */ package linlongyx.sanguo.webgame.job;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentLinkedQueue;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogWriterJob
/*    */   extends AbstractSysJob
/*    */   implements SysJob
/*    */ {
/* 14 */   private static ConcurrentLinkedQueue<String> LOG_QUEUE = new ConcurrentLinkedQueue<>();
/* 15 */   private static final Logger logger = LoggerFactory.getLogger(LogWriterJob.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void addLog(String log) {
/* 27 */     logger.info(log);
/*    */   }
/*    */   
/*    */   public void run() {
/* 31 */     writeLog();
/*    */   }
/*    */   
/*    */   private void writeLog() {
/* 35 */     if (LOG_QUEUE.isEmpty())
/*    */       return; 
/*    */     while (true) {
/* 38 */       String s = LOG_QUEUE.poll();
/* 39 */       if (null == s)
/*    */         break; 
/* 41 */       logger.info(s);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\job\LogWriterJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */