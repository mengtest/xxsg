/*    */ package com.linlongyx.core.framework.logic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Sessions
/*    */   implements SessionFactory
/*    */ {
/* 10 */   public static final SessionFactory INSTANCE = new Sessions();
/*    */ 
/*    */   
/*    */   public ISession newSession() {
/* 14 */     return (new Session.SessionBuilder()).build();
/*    */   }
/*    */ 
/*    */   
/*    */   public IPlayerSession newPlayerSession(IPlayer player) {
/* 19 */     return (new PlayerSession.PlayerSessionBuilder()).player(player).build();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\Sessions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */