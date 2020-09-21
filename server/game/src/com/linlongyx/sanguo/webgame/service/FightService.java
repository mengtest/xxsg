/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.bosshome.BossUtil;
/*    */ import java.util.Arrays;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.ScheduledFuture;
/*    */ import java.util.concurrent.TimeUnit;
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
/*    */ public class FightService
/*    */ {
/* 24 */   private static final ScheduledExecutorService scheduler = Fibers.createScheduler();
/*    */   private static ScheduledFuture<?> scheduledFuture;
/*    */   private static final int HANDLEMSG_PEROID = 1;
/*    */   
/*    */   public static void init() {
/* 29 */     scheduledFuture = scheduler.scheduleAtFixedRate(FightService::tick, 0L, 1L, TimeUnit.SECONDS);
/* 30 */     scheduler.scheduleAtFixedRate(FightService::checkTick, 2L, 2L, TimeUnit.SECONDS);
/*    */   }
/*    */ 
/*    */   
/*    */   private static final long MAX_SCHEDULE_MILLISECONDS = 5L;
/*    */   
/*    */   private static void checkTick() {
/*    */     try {
/* 38 */       long nowMills = TimeUtil.currentTimeMillis();
/* 39 */       if (tickTimestamp + 5L < nowMills) {
/*    */         
/* 41 */         scheduledFuture.cancel(true);
/*    */         
/* 43 */         tickTimestamp = TimeUtil.currentTimeMillis();
/* 44 */         scheduledFuture = scheduler.scheduleAtFixedRate(FightService::tick, 0L, 1L, TimeUnit.SECONDS);
/*    */       } 
/* 46 */     } catch (Exception e) {
/* 47 */       e.printStackTrace();
/* 48 */       LogUtils.errorLog(new Object[] { "FightService::checkTick Exception ", Arrays.asList(e.getStackTrace()) });
/*    */     } 
/*    */   }
/*    */   
/*    */   private static final long CHECKTICK_PEROID = 2L;
/*    */   private static long tickTimestamp;
/*    */   
/*    */   private static void tick() {
/*    */     try {
/* 57 */       tickTimestamp = TimeUtil.currentTimeMillis();
/* 58 */       BossUtil.handle();
/* 59 */     } catch (Exception e) {
/* 60 */       e.printStackTrace();
/* 61 */       LogUtils.errorLog(new Object[] { "FightService::tick Exception", Arrays.asList(e.getStackTrace()) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\FightService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */