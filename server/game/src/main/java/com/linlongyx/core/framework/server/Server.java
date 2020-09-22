/*    */ package com.linlongyx.core.framework.server;
/*    */ 
/*    */ public interface Server
/*    */ {
/*    */   TransmissionProtocol getTransmissionProtocol();
/*    */   
/*    */   void startServer() throws Exception;
/*    */   
/*    */   void stopServer() throws Exception;
/*    */   
/*    */   public enum TRANSMISSION_PROTOCOL
/*    */     implements TransmissionProtocol
/*    */   {
/* 14 */     TCP, HTTP;
/*    */   }
/*    */   
/*    */   public static interface TransmissionProtocol {}
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\server\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */