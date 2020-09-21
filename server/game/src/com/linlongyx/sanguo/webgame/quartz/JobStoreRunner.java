/*    */ package com.linlongyx.sanguo.webgame.quartz;
/*    */ 
/*    */ import org.quartz.Scheduler;
/*    */ import org.quartz.SchedulerException;
/*    */ import org.quartz.impl.StdSchedulerFactory;
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
/*    */ 
/*    */ public class JobStoreRunner
/*    */ {
/* 19 */   private static JobStoreRunner instance = null;
/*    */   
/*    */   public static JobStoreRunner getInstance() {
/* 22 */     if (instance == null) {
/* 23 */       instance = new JobStoreRunner();
/*    */     }
/* 25 */     return instance;
/*    */   }
/*    */   public void task() throws SchedulerException {
/* 28 */     StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
/* 29 */     Scheduler scheduler = stdSchedulerFactory.getScheduler();
/* 30 */     scheduler.start();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 35 */       JobStoreRunner qRunner = new JobStoreRunner();
/* 36 */       qRunner.task();
/* 37 */       System.out.println("JobStoreRunner tigger start.");
/* 38 */     } catch (Exception e) {
/* 39 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\JobStoreRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */