/*    */ package com.linlongyx.sanguo.webgame.startup;
/*    */ 
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShutdownHook
/*    */   extends Thread
/*    */ {
/*    */   public void run() {
/* 18 */     shutdown();
/*    */   }
/*    */   
/*    */   private void shutdown() {
/* 22 */     LookUpService.shutdown();
/* 23 */     Fibers.shutdown();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\startup\ShutdownHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */