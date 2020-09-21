/*    */ package com.linlongyx.sanguo.webgame.quartz;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.sanguo.webgame.quartz.job.ProcessingJob;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class QuartzJob implements Job {
/* 12 */   private static Logger logger = LoggerFactory.getLogger(QuartzJob.class);
/*    */   
/*    */   public void execute(JobExecutionContext context) throws JobExecutionException {
/*    */     try {
/* 16 */       String cmdname = context.getTrigger().getName();
/* 17 */       if (cmdname != null) {
/* 18 */         int cmd = Integer.parseInt(cmdname);
/* 19 */         if (cmd < 20000) {
/* 20 */           HttpSender httpSender = (HttpSender)AppContext.getBean("httpSender");
/* 21 */           httpSender.socket(cmd);
/*    */         } else {
/* 23 */           ProcessingJob.trigger(cmd);
/*    */         } 
/* 25 */         logger.info("触发cmd：" + cmd);
/*    */       } else {
/* 27 */         logger.error("获取cmd出错: " + cmdname);
/*    */       } 
/* 29 */     } catch (Exception e) {
/* 30 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\QuartzJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */