/*    */ package com.linlongyx.core.framework.concurrent;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import org.jetlang.channels.Channel;
/*    */ import org.jetlang.channels.MemoryChannel;
/*    */ import org.jetlang.fibers.Fiber;
/*    */ import org.jetlang.fibers.PoolFiberFactory;
/*    */ import org.jetlang.fibers.ThreadFiber;
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
/*    */ public class Fibers
/*    */ {
/* 25 */   private static final ExecutorService SERVICE = Executors.newFixedThreadPool(2);
/*    */   
/*    */   private static final PoolFiberFactory FACT;
/*    */   
/* 29 */   private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()); static {
/* 30 */     FACT = new PoolFiberFactory(SERVICE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Fiber pooledFiber() {
/* 40 */     Fiber fiber = FACT.create();
/* 41 */     fiber.start();
/* 42 */     return fiber;
/*    */   }
/*    */   
/*    */   public static Fiber threadFiber() {
/* 46 */     ThreadFiber threadFiber = new ThreadFiber();
/* 47 */     threadFiber.start();
/* 48 */     return (Fiber)threadFiber;
/*    */   }
/*    */   
/*    */   public static ScheduledExecutorService createScheduler() {
/* 52 */     return SCHEDULER;
/*    */   }
/*    */   
/*    */   public static ExecutorService createExecutorService() {
/* 56 */     return SERVICE;
/*    */   }
/*    */   
/*    */   public static Channel<IEvent> createInbox() {
/* 60 */     return (Channel<IEvent>)new MemoryChannel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void shutdown() {
/* 67 */     SERVICE.shutdown();
/* 68 */     SCHEDULER.shutdown();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\concurrent\Fibers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */