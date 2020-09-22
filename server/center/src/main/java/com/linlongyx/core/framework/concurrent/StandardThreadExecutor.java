/*     */ package com.linlongyx.core.framework.concurrent;
/*     */ 
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.RejectedExecutionHandler;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ public class StandardThreadExecutor
/*     */   extends ThreadPoolExecutor
/*     */ {
/*     */   public static final int DEFAULT_MIN_THREADS = 5;
/*     */   public static final int DEFAULT_MAX_THREADS = 20;
/*     */   public static final int DEFAULT_MAX_IDLE_TIME = 60000;
/*     */   protected AtomicInteger submittedTasksCount;
/*     */   private int maxSubmittedTaskCount;
/*     */   
/*     */   public StandardThreadExecutor() {
/*  32 */     this(5, 20);
/*     */   }
/*     */   
/*     */   public StandardThreadExecutor(int coreThread, int maxThreads) {
/*  36 */     this(coreThread, maxThreads, maxThreads);
/*     */   }
/*     */   
/*     */   public StandardThreadExecutor(int coreThread, int maxThreads, long keepAliveTime, TimeUnit unit) {
/*  40 */     this(coreThread, maxThreads, keepAliveTime, unit, maxThreads);
/*     */   }
/*     */   
/*     */   public StandardThreadExecutor(int coreThreads, int maxThreads, int queueCapacity) {
/*  44 */     this(coreThreads, maxThreads, queueCapacity, Executors.defaultThreadFactory());
/*     */   }
/*     */   
/*     */   public StandardThreadExecutor(int coreThreads, int maxThreads, int queueCapacity, ThreadFactory threadFactory) {
/*  48 */     this(coreThreads, maxThreads, 60000L, TimeUnit.MILLISECONDS, queueCapacity, threadFactory);
/*     */   }
/*     */   
/*     */   public StandardThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit, int queueCapacity) {
/*  52 */     this(coreThreads, maxThreads, keepAliveTime, unit, queueCapacity, Executors.defaultThreadFactory());
/*     */   }
/*     */ 
/*     */   
/*     */   public StandardThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit, int queueCapacity, ThreadFactory threadFactory) {
/*  57 */     this(coreThreads, maxThreads, keepAliveTime, unit, queueCapacity, threadFactory, new ThreadPoolExecutor.AbortPolicy());
/*     */   }
/*     */ 
/*     */   
/*     */   public StandardThreadExecutor(int coreThreads, int maxThreads, long keepAliveTime, TimeUnit unit, int queueCapacity, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
/*  62 */     super(coreThreads, maxThreads, keepAliveTime, unit, new ExecutorQueue(), threadFactory, handler);
/*  63 */     ((ExecutorQueue)getQueue()).setStandardThreadExecutor(this);
/*     */     
/*  65 */     this.submittedTasksCount = new AtomicInteger(0);
/*     */ 
/*     */     
/*  68 */     this.maxSubmittedTaskCount = queueCapacity + maxThreads;
/*     */   }
/*     */   
/*     */   public void execute(Runnable command) {
/*  72 */     int count = this.submittedTasksCount.incrementAndGet();
/*     */ 
/*     */ 
/*     */     
/*  76 */     if (count > this.maxSubmittedTaskCount) {
/*  77 */       this.submittedTasksCount.decrementAndGet();
/*  78 */       getRejectedExecutionHandler().rejectedExecution(command, this);
/*     */     } 
/*     */     
/*     */     try {
/*  82 */       super.execute(command);
/*  83 */     } catch (RejectedExecutionException rx) {
/*     */       
/*  85 */       if (!((ExecutorQueue)getQueue()).force(command)) {
/*  86 */         this.submittedTasksCount.decrementAndGet();
/*     */         
/*  88 */         getRejectedExecutionHandler().rejectedExecution(command, this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSubmittedTasksCount() {
/*  94 */     return this.submittedTasksCount.get();
/*     */   }
/*     */   
/*     */   public int getMaxSubmittedTaskCount() {
/*  98 */     return this.maxSubmittedTaskCount;
/*     */   }
/*     */   
/*     */   protected void afterExecute(Runnable r, Throwable t) {
/* 102 */     this.submittedTasksCount.decrementAndGet();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\concurrent\StandardThreadExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */