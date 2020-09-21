/*    */ package com.linlongyx.sanguo.webgame.quartz.job;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProcessingJob
/*    */ {
/* 16 */   private static final Map<Integer, IJob> jobMap = new HashMap<>();
/*    */   
/*    */   static {
/* 19 */     for (IJobType iJobType : IJobType.values()) {
/* 20 */       jobMap.put(Integer.valueOf(iJobType.getCmd()), iJobType.getiJob());
/*    */     }
/*    */   }
/*    */   
/*    */   public static synchronized short trigger(int cmd) {
/* 25 */     IJob iJob = jobMap.get(Integer.valueOf(cmd));
/* 26 */     if (null == iJob) return 10002; 
/* 27 */     return iJob.process();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\ProcessingJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */