/*    */ package com.linlongyx.core.framework.concurrent;
/*    */ 
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ public class NamedThreadFactory
/*    */   implements ThreadFactory
/*    */ {
/* 10 */   private static AtomicInteger counter = new AtomicInteger(1);
/* 11 */   private String name = "Lane";
/*    */   private boolean daemon;
/*    */   private int priority;
/*    */   
/*    */   public NamedThreadFactory(String name) {
/* 16 */     this(name, false, -1);
/*    */   }
/*    */   
/*    */   public NamedThreadFactory(String name, boolean daemon) {
/* 20 */     this(name, daemon, -1);
/*    */   }
/*    */   
/*    */   public NamedThreadFactory(String name, boolean daemon, int priority) {
/* 24 */     this.name = name;
/* 25 */     this.daemon = daemon;
/* 26 */     this.priority = priority;
/*    */   }
/*    */ 
/*    */   
/*    */   public Thread newThread(Runnable r) {
/* 31 */     Thread thread = new Thread(r, this.name + "[" + counter.getAndIncrement() + "]");
/* 32 */     thread.setDaemon(this.daemon);
/* 33 */     if (this.priority != -1) {
/* 34 */       thread.setPriority(this.priority);
/*    */     }
/* 36 */     return thread;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\concurrent\NamedThreadFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */