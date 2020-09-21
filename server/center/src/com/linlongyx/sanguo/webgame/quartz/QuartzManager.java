/*     */ package com.linlongyx.sanguo.webgame.quartz;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.JobType;
/*     */ import java.util.Date;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.quartz.Job;
/*     */ import org.quartz.JobBuilder;
/*     */ import org.quartz.JobDataMap;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.JobKey;
/*     */ import org.quartz.ScheduleBuilder;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.quartz.Trigger;
/*     */ import org.quartz.TriggerBuilder;
/*     */ import org.quartz.TriggerKey;
/*     */ 
/*     */ public class QuartzManager {
/*  19 */   private SchedulerFactory schedulerFactory = (SchedulerFactory)new StdSchedulerFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String cron, JobDataMap jobDataMap) {
/*     */     try {
/*     */       JobDetail jobDetail;
/*  34 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/*     */ 
/*     */       
/*  37 */       JobKey jobKey = new JobKey(jobName, jobGroupName);
/*  38 */       if (scheduler.checkExists(jobKey)) {
/*  39 */         jobDetail = scheduler.getJobDetail(jobKey);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  44 */         jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData(jobDataMap).build();
/*     */       } 
/*     */ 
/*     */       
/*  48 */       Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).startNow().withSchedule((ScheduleBuilder)CronScheduleBuilder.cronSchedule(cron)).build();
/*  49 */       scheduler.scheduleJob(jobDetail, trigger);
/*  50 */       if (!scheduler.isStarted()) {
/*  51 */         scheduler.start();
/*     */       }
/*  53 */     } catch (SchedulerException e) {
/*  54 */       LogUtil.errorLog(new Object[] { getClass(), e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addTrigger(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, String cron) {
/*     */     try {
/*  61 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/*     */ 
/*     */       
/*  64 */       Trigger trigger = TriggerBuilder.newTrigger().forJob(jobName, jobGroupName).withIdentity(triggerName, triggerGroupName).startNow().withSchedule((ScheduleBuilder)CronScheduleBuilder.cronSchedule(cron)).build();
/*  65 */       scheduler.scheduleJob(trigger);
/*  66 */     } catch (SchedulerException e) {
/*  67 */       LogUtil.errorLog(new Object[] { getClass(), e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, Date startDate, Date endDate, int intervalInSeconds, JobDataMap jobDataMap) {
/*     */     try {
/*     */       JobDetail jobDetail;
/*  86 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/*     */ 
/*     */       
/*  89 */       JobKey jobKey = new JobKey(jobName, jobGroupName);
/*  90 */       if (scheduler.checkExists(jobKey)) {
/*  91 */         jobDetail = scheduler.getJobDetail(jobKey);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/*  96 */         jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData(jobDataMap).build();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).startAt(startDate).endAt(endDate).withSchedule((ScheduleBuilder)SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(intervalInSeconds).repeatForever()).build();
/* 107 */       scheduler.scheduleJob(jobDetail, trigger);
/* 108 */       if (!scheduler.isStarted()) {
/* 109 */         scheduler.start();
/*     */       }
/* 111 */     } catch (SchedulerException e) {
/* 112 */       LogUtil.errorLog(new Object[] { getClass(), e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addOnceJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class<? extends Job> jobClass, Date startDate, JobDataMap jobDataMap) {
/*     */     try {
/*     */       JobDetail jobDetail;
/* 130 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/*     */ 
/*     */       
/* 133 */       JobKey jobKey = new JobKey(jobName, jobGroupName);
/* 134 */       if (scheduler.checkExists(jobKey)) {
/* 135 */         jobDetail = scheduler.getJobDetail(jobKey);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 140 */         jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData(jobDataMap).build();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 148 */       Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).startAt(startDate).withSchedule((ScheduleBuilder)SimpleScheduleBuilder.simpleSchedule().withRepeatCount(0)).build();
/* 149 */       scheduler.scheduleJob(jobDetail, trigger);
/* 150 */       if (!scheduler.isShutdown()) {
/* 151 */         scheduler.start();
/*     */       }
/* 153 */     } catch (SchedulerException e) {
/* 154 */       LogUtil.errorLog(new Object[] { getClass(), e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {
/*     */     try {
/* 171 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/* 172 */       CronTrigger trigger = (CronTrigger)scheduler.getTrigger(new TriggerKey(triggerName, triggerGroupName));
/* 173 */       if (trigger == null) {
/*     */         return;
/*     */       }
/*     */       
/* 177 */       String oldTime = trigger.getCronExpression();
/* 178 */       if (!oldTime.equalsIgnoreCase(cron))
/*     */       {
/*     */         
/* 181 */         TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
/*     */         
/* 183 */         triggerBuilder.withIdentity(triggerName, triggerGroupName);
/* 184 */         triggerBuilder.startNow();
/*     */         
/* 186 */         triggerBuilder.withSchedule((ScheduleBuilder)CronScheduleBuilder.cronSchedule(cron));
/*     */         
/* 188 */         trigger = (CronTrigger)triggerBuilder.build();
/*     */         
/* 190 */         scheduler.rescheduleJob(new TriggerKey(triggerName, triggerGroupName), (Trigger)trigger);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 200 */     catch (Exception e) {
/* 201 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
/*     */     try {
/* 217 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/* 218 */       scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroupName));
/* 219 */       scheduler.unscheduleJob(new TriggerKey(triggerName, triggerGroupName));
/* 220 */       scheduler.deleteJob(new JobKey(jobName, jobGroupName));
/*     */ 
/*     */     
/*     */     }
/* 224 */     catch (Exception e) {
/* 225 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTrigger(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
/*     */     try {
/* 240 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/* 241 */       scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroupName));
/* 242 */       scheduler.unscheduleJob(new TriggerKey(triggerName, triggerGroupName));
/*     */ 
/*     */     
/*     */     }
/* 246 */     catch (Exception e) {
/* 247 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void start() {
/*     */     try {
/* 257 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/* 258 */       LogUtil.debugLog(new Object[] { getClass(), "==========开启定时任务管理器=================" });
/* 259 */       if (!scheduler.isStarted()) {
/* 260 */         scheduler.start();
/*     */       }
/* 262 */       loadJobs();
/* 263 */     } catch (Exception e) {
/* 264 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loadJobs() {
/* 269 */     for (JobType job : JobType.values()) {
/* 270 */       addJob(job.getJobName(), job.getJobGroupName(), job.getTriggerName(), job.getTriggerGroupName(), job.getJob(), job.getCron(), job.getJobDataMap());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdown() {
/*     */     try {
/* 280 */       Scheduler scheduler = this.schedulerFactory.getScheduler();
/* 281 */       if (!scheduler.isShutdown()) {
/* 282 */         LogUtil.debugLog(new Object[] { getClass(), "==========关闭定时任务管理器=================" });
/* 283 */         Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
/* 284 */         scheduler.unscheduleJobs(new ArrayList<>(triggerKeySet));
/* 285 */         scheduler.shutdown();
/*     */       } 
/* 287 */     } catch (Exception e) {
/* 288 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\QuartzManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */