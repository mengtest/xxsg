/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.sanguo.webgame.job.SysJob;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JobService
/*    */ {
/* 17 */   protected final ScheduledExecutorService scheduler = Fibers.createScheduler();
/* 18 */   protected final ExecutorService service = Fibers.createExecutorService();
/*    */   
/*    */   private Map<String, SysJob> jobs;
/*    */ 
/*    */   
/*    */   public void setJobs(Map<String, SysJob> jobs) {
/* 24 */     this.jobs = jobs;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initJobs() {
/* 31 */     for (SysJob job : this.jobs.values())
/* 32 */       this.scheduler.scheduleAtFixedRate((Runnable)job, job.getInitialDelay(), job.getPeriod(), TimeUnit.SECONDS); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\JobService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */