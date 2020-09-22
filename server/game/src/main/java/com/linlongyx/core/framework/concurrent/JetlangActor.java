/*    */ package com.linlongyx.core.framework.concurrent;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.IEvent;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.framework.protocol.TransmitEvent;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.ScheduledFuture;
/*    */ import org.jetlang.channels.Channel;
/*    */ import org.jetlang.core.Callback;
/*    */ import org.jetlang.core.DisposingExecutor;
/*    */ import org.jetlang.fibers.Fiber;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class JetlangActor
/*    */ {
/*    */   private Channel<IEvent> inbox;
/*    */   private Fiber fiber;
/*    */   private Callback<IEvent> callback;
/*    */   protected final ScheduledExecutorService scheduler;
/*    */   protected ScheduledFuture<?> scheduledFuture;
/*    */   
/*    */   protected JetlangActor() {
/* 28 */     this.fiber = Fibers.pooledFiber();
/* 29 */     this.inbox = Fibers.createInbox();
/* 30 */     this.scheduler = Fibers.createScheduler();
/*    */     
/* 32 */     this.callback = this::act;
/*    */     
/* 34 */     this.inbox.subscribe((DisposingExecutor)this.fiber, this.callback);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void post(IEvent message) {
/* 42 */     this.inbox.publish(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void transmit(ResponseBase response) {
/* 50 */     TransmitEvent transmitEvent = new TransmitEvent();
/* 51 */     transmitEvent.response = response;
/* 52 */     this.inbox.publish(transmitEvent);
/*    */   }
/*    */   
/*    */   protected abstract void act(IEvent paramIEvent);
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\concurrent\JetlangActor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */