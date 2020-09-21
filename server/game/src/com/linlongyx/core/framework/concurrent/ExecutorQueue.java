/*     */ package com.linlongyx.core.framework.concurrent;
/*     */ 
/*     */ import java.util.concurrent.LinkedTransferQueue;
/*     */ import java.util.concurrent.RejectedExecutionException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ExecutorQueue
/*     */   extends LinkedTransferQueue<Runnable>
/*     */ {
/*     */   private static final long serialVersionUID = -265236426751004839L;
/*     */   StandardThreadExecutor threadPoolExecutor;
/*     */   
/*     */   public void setStandardThreadExecutor(StandardThreadExecutor threadPoolExecutor) {
/* 124 */     this.threadPoolExecutor = threadPoolExecutor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean force(Runnable o) {
/* 129 */     if (this.threadPoolExecutor.isShutdown()) {
/* 130 */       throw new RejectedExecutionException("Executor not running, can't force a command into the queue");
/*     */     }
/*     */     
/* 133 */     return super.offer(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean offer(Runnable o) {
/* 138 */     int poolSize = this.threadPoolExecutor.getPoolSize();
/*     */ 
/*     */     
/* 141 */     if (poolSize == this.threadPoolExecutor.getMaximumPoolSize()) {
/* 142 */       return super.offer(o);
/*     */     }
/*     */ 
/*     */     
/* 146 */     if (this.threadPoolExecutor.getSubmittedTasksCount() <= poolSize) {
/* 147 */       return super.offer(o);
/*     */     }
/*     */ 
/*     */     
/* 151 */     if (poolSize < this.threadPoolExecutor.getMaximumPoolSize()) {
/* 152 */       return false;
/*     */     }
/*     */     
/* 155 */     return super.offer(o);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\concurrent\ExecutorQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */