/*    */ package com.linlongyx.sanguo.webgame.server;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.server.ServerManager;
/*    */ import com.linlongyx.core.framework.server.netty.AbstractNettyServer;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServerManagerImpl
/*    */   implements ServerManager
/*    */ {
/* 19 */   private Set<AbstractNettyServer> servers = new HashSet<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void startServers() throws Exception {
/* 24 */     if (MContext.isWs()) {
/* 25 */       AbstractNettyServer wsServer = (AbstractNettyServer)AppContext.getBean("wsServer");
/* 26 */       wsServer.startServer();
/* 27 */       this.servers.add(wsServer);
/*    */     } 
/*    */     
/* 30 */     if (MContext.isWss()) {
/* 31 */       AbstractNettyServer wssServer = (AbstractNettyServer)AppContext.getBean("wssServer");
/* 32 */       wssServer.startServer();
/* 33 */       this.servers.add(wssServer);
/*    */     } 
/*    */     
/* 36 */     AbstractNettyServer httpServer = (AbstractNettyServer)AppContext.getBean("httpServer");
/* 37 */     httpServer.startServer();
/* 38 */     this.servers.add(httpServer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopServers() throws Exception {
/* 43 */     for (AbstractNettyServer nettyServer : this.servers) {
/*    */       try {
/* 45 */         nettyServer.stopServer();
/* 46 */       } catch (Exception e) {
/* 47 */         LogUtil.errorLog(new Object[] { "Unable to stop server {} due to error {}", nettyServer, e });
/* 48 */         throw e;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\server\ServerManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */