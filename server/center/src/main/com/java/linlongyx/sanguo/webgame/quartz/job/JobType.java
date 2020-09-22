/*     */ package linlongyx.sanguo.webgame.quartz.job;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.battle.BattlePrepareJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.battle.BattleStartJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepBeginJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepEndJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepFiveBalanceJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepFourBalanceJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepFourPrepareJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepOneBalanceJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepThreeBalanceJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepThreePrepareJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepTwoBalanceJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.match.MatchStepTwoPrepareJob;
/*     */ import com.linlongyx.sanguo.webgame.quartz.job.race.RaceOpenJob;
/*     */ import org.quartz.Job;
/*     */ import org.quartz.JobDataMap;
/*     */ 
/*     */ public enum JobType {
/*  19 */   EveryDayZero("job-every-day-zero", "every-day-zero", "0 0 0 * * ?", (Class)EveryDayZeroJob.class),
/*     */ 
/*     */   
/*  22 */   MondayZero("job-monday-zero", "monday-zero", "0 0 0 ? * MON", (Class)MondayZeroJob.class),
/*  23 */   MondayOne("job-monday-one", "monday-one", "0 0 1 ? * MON", (Class)MondayOneJob.class),
/*     */   
/*  25 */   DestinyRefreshRank("destiny-refresh-rank", "refreshRank", "0 0/5 * * * ?", (Class)RefreshRankJob.class),
/*     */   
/*  27 */   RetryReward("destiny-retry-reward", "retryReward", "0 0/3 * * * ?", (Class)RetryRewardJob.class),
/*     */ 
/*     */ 
/*     */   
/*  31 */   MatchStepBegin("match-step-begin", "step-begin", "0 0 0 ? * SAT", (Class)MatchStepBeginJob.class),
/*  32 */   MatchStepOnePrepare("match-step-one-prepare", "step-one-prepare", "0 0 20 ? * SAT", (Class)MatchStepOnePrepareJob.class),
/*  33 */   MatchStepOneBalance("match-step-one-balance", "step-one-balance", "0 5 20 ? * SAT", (Class)MatchStepOneBalanceJob.class),
/*  34 */   MatchStepTwoPrepare("match-step-two-prepare", "step-two-prepare", "0 10 20 ? * SAT", (Class)MatchStepTwoPrepareJob.class),
/*  35 */   MatchStepTwoBalance("match-step-two-balance", "step-two-balance", "0 15 20 ? * SAT", (Class)MatchStepTwoBalanceJob.class),
/*  36 */   MatchStepThreePrepare("match-step-three-prepare", "step-three-prepare", "0 20 20 ? * SAT", (Class)MatchStepThreePrepareJob.class),
/*  37 */   MatchStepThreeBalance("match-step-three-balance", "step-three-balance", "0 25 20 ? * SAT", (Class)MatchStepThreeBalanceJob.class),
/*     */   
/*  39 */   MatchStepFourPrepare("match-step-four-prepare", "step-four-prepare", "0 0 20 ? * SUN", (Class)MatchStepFourPrepareJob.class),
/*  40 */   MatchStepFourBalance("match-step-four-balance", "step-four-balance", "0 5 20 ? * SUN", (Class)MatchStepFourBalanceJob.class),
/*  41 */   MatchStepFivePrepare("match-step-five-prepare", "step-five-prepare", "0 10 20 ? * SUN", (Class)MatchStepFivePrepareJob.class),
/*  42 */   MatchStepFiveBalance("match-step-five-balance", "step-five-balance", "0 15 20 ? * SUN", (Class)MatchStepFiveBalanceJob.class),
/*  43 */   MatchStepEnd("match-step-end", "step-end", "0 20 20 ? * SUN", (Class)MatchStepEndJob.class),
/*     */   
/*  45 */   BattlePrepare("battle-prepare", "prepare", "0 55 19 ? * FRI", (Class)BattlePrepareJob.class),
/*  46 */   BattleStart("battle-start", "start", "0 00 20 ? * FRI", (Class)BattleStartJob.class),
/*  47 */   BattleEnd("battle-end", "end", "0 30 20 ? * FRI", (Class)BattleEndJob.class),
/*     */ 
/*     */ 
/*     */   
/*  51 */   RaceOpen("race-open", "race-open", "0 30 20 ? * 2-6", (Class)RaceOpenJob.class),
/*  52 */   RaceClose("race-close", "race-close", "0 0 21 ? * 2-6", (Class)RaceCloseJob.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private JobDataMap jobDataMap = new JobDataMap(); private static final String DEFAULT_JOB_GROUP_NAME = "sanguo-job-group"; private static final String DEFAULT_TRIGGER_GROUP_NAME = "sanguo-trigger-group"; private String jobName; private String jobGroupName;
/*     */   
/*     */   JobType(String jobName, String triggerName, String cron, Class<? extends Job> job) {
/*  65 */     this.jobName = jobName;
/*  66 */     this.jobGroupName = "sanguo-job-group";
/*  67 */     this.triggerName = triggerName;
/*  68 */     this.triggerGroupName = "sanguo-trigger-group";
/*  69 */     this.cron = cron;
/*  70 */     this.job = job;
/*     */   }
/*     */   private String triggerName; private String triggerGroupName; private String cron; private Class<? extends Job> job;
/*     */   JobType(String jobName, String triggerName, String cron, Class<? extends Job> job, JobDataMap jobDataMap) {
/*  74 */     this.jobName = jobName;
/*  75 */     this.jobGroupName = "sanguo-job-group";
/*  76 */     this.triggerName = triggerName;
/*  77 */     this.triggerGroupName = "sanguo-trigger-group";
/*  78 */     this.cron = cron;
/*  79 */     this.job = job;
/*  80 */     if (jobDataMap != null) {
/*  81 */       this.jobDataMap = jobDataMap;
/*     */     }
/*     */   }
/*     */   
/*     */   JobType(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron, Class<? extends Job> job, JobDataMap jobDataMap) {
/*  86 */     this.jobName = jobName;
/*  87 */     this.jobGroupName = jobGroupName;
/*  88 */     this.triggerName = triggerName;
/*  89 */     this.triggerGroupName = triggerGroupName;
/*  90 */     this.cron = cron;
/*  91 */     this.job = job;
/*  92 */     if (jobDataMap != null) {
/*  93 */       this.jobDataMap = jobDataMap;
/*     */     }
/*     */   }
/*     */   
/*     */   public String getJobName() {
/*  98 */     return this.jobName;
/*     */   }
/*     */   
/*     */   public String getJobGroupName() {
/* 102 */     return this.jobGroupName;
/*     */   }
/*     */   
/*     */   public String getTriggerName() {
/* 106 */     return this.triggerName;
/*     */   }
/*     */   
/*     */   public String getTriggerGroupName() {
/* 110 */     return this.triggerGroupName;
/*     */   }
/*     */   
/*     */   public String getCron() {
/* 114 */     return this.cron;
/*     */   }
/*     */   
/*     */   public Class<? extends Job> getJob() {
/* 118 */     return this.job;
/*     */   }
/*     */   
/*     */   public JobDataMap getJobDataMap() {
/* 122 */     return this.jobDataMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\quartz\job\JobType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */