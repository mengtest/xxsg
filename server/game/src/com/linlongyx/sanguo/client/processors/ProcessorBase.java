/*    */ package com.linlongyx.sanguo.client.processors;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.client.actor.Actor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ProcessorBase<REQ extends RequestBase, RESP extends ResponseBase>
/*    */ {
/*    */   public void handle(Actor actor, ResponseBase response) {
/*    */     try {
/* 19 */       process(actor, (RESP)response);
/* 20 */     } catch (Exception e) {
/* 21 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract void process(Actor paramActor, RESP paramRESP) throws Exception;
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\processors\ProcessorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */