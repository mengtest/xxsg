/*    */ package com.linlongyx.sanguo.webgame.rmi;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ import java.rmi.server.RMISocketFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SMRMISocket
/*    */   extends RMISocketFactory
/*    */ {
/*    */   private final int port;
/*    */   
/*    */   public SMRMISocket(int port) {
/* 16 */     this.port = port;
/*    */   }
/*    */ 
/*    */   
/*    */   public Socket createSocket(String host, int port) throws IOException {
/* 21 */     return new Socket(host, port);
/*    */   }
/*    */   
/*    */   public ServerSocket createServerSocket(int port) throws IOException {
/* 25 */     if (port == 0)
/* 26 */       port = this.port; 
/* 27 */     return new ServerSocket(port);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\SMRMISocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */