/*    */ package com.linlongyx.sanguo.webgame.net.http.answer;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.event.IAnswerHttpEvent;
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
/*    */ public enum HttpEventType
/*    */ {
/* 16 */   ReloadXml(1, new ReloadXmlEvent()),
/* 17 */   HotUpdate(2, new HotUpdateEvent()),
/* 18 */   MODULE(4, new ModuleEvent()),
/* 19 */   LOGOUT(9, new LogoutEvent()),
/* 20 */   AllLOGOUT(10, new AllLogoutEvent()),
/* 21 */   RUNNING(13, new RunningEvent()),
/* 22 */   SHUTDOWN(16, new ShutdownEvent());
/*    */   
/*    */   private int cmd;
/*    */   
/*    */   private IAnswerHttpEvent iHttpEvent;
/*    */   
/*    */   HttpEventType(int cmd, IAnswerHttpEvent iHttpEvent) {
/* 29 */     this.cmd = cmd;
/* 30 */     this.iHttpEvent = iHttpEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCmd() {
/* 37 */     return this.cmd;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IAnswerHttpEvent getiHttpEvent() {
/* 45 */     return this.iHttpEvent;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\http\answer\HttpEventType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */