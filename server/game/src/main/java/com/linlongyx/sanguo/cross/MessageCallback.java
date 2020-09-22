/*    */ package com.linlongyx.sanguo.cross;
/*    */ 
/*    */ import com.linlongyx.sanguo.cross.event.IRequestEvent;
/*    */ import com.linlongyx.sanguo.cross.event.IResponseEvent;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.locks.Condition;
/*    */ import java.util.concurrent.locks.Lock;
/*    */ import java.util.concurrent.locks.ReentrantLock;
/*    */ 
/*    */ public class MessageCallback
/*    */ {
/*    */   private IRequestEvent request;
/*    */   private IResponseEvent response;
/* 14 */   private Lock lock = new ReentrantLock();
/* 15 */   private Condition finish = this.lock.newCondition();
/*    */   
/*    */   public MessageCallback(IRequestEvent request) {
/* 18 */     this.request = request;
/*    */   }
/*    */   
/*    */   public IResponseEvent start() throws InterruptedException {
/*    */     try {
/* 23 */       this.lock.lock();
/* 24 */       this.finish.await(5L, TimeUnit.SECONDS);
/* 25 */       if (this.response != null) {
/* 26 */         return this.response;
/*    */       }
/* 28 */       return null;
/*    */     } finally {
/*    */       
/* 31 */       this.lock.unlock();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void over(IResponseEvent response) {
/*    */     try {
/* 37 */       this.lock.lock();
/* 38 */       this.response = response;
/* 39 */       this.finish.signal();
/*    */     } finally {
/* 41 */       this.lock.unlock();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\MessageCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */