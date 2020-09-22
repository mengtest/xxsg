/*    */ package com.linlongyx.core.framework.logic;public interface ISession { void setStatus(Status paramStatus);
/*    */   Status getStatus();
/*    */   boolean isWriteable();
/*    */   void setWriteable(boolean paramBoolean);
/*    */   boolean isLogin();
/*    */   void setLogin(boolean paramBoolean);
/*    */   String getKey();
/*    */   void setKey(String paramString);
/*    */   void setTcpSender(MessageSender paramMessageSender);
/*    */   MessageSender getTcpSender();
/*    */   void setClientIp(int paramInt);
/*    */   int getClientIp();
/* 13 */   public enum Status { NOT_CONNECTED, CONNECTING, CONNECTED, RE_CONNECTING, CLOSED; }
/*    */    }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\logic\ISession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */