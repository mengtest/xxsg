/*   */ package com.linlongyx.sanguo.cross.processor;
/*   */ 
/*   */ import com.linlongyx.sanguo.cross.event.IResponseEvent;
/*   */ 
/*   */ public abstract class AbstractCrossProcessor<RESP extends IResponseEvent>
/*   */   implements ICrossProcessor {
/*   */   public void handle(IResponseEvent event) {
/* 8 */     IResponseEvent iResponseEvent = event;
/* 9 */     process((RESP)iResponseEvent);
/*   */   }
/*   */   
/*   */   public abstract void process(RESP paramRESP);
/*   */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\processor\AbstractCrossProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */