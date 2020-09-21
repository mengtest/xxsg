/*    */ package com.linlongyx.sanguo.webgame.server;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.core.framework.server.ServerManager;
/*    */ import com.linlongyx.core.framework.server.netty.AbstractNettyServer;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.quartz.JobStoreRunner;
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
/* 20 */   private Set<AbstractNettyServer> servers = new HashSet<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startServers() throws Exception {
/* 27 */     if (MContext.isWs()) {
/* 28 */       AbstractNettyServer wsServer = (AbstractNettyServer)AppContext.getBean("wsServer");
/* 29 */       wsServer.startServer();
/* 30 */       this.servers.add(wsServer);
/*    */     } 
/*    */     
/* 33 */     if (MContext.isWss()) {
/* 34 */       AbstractNettyServer wsServer = (AbstractNettyServer)AppContext.getBean("wssServer");
/* 35 */       wsServer.startServer();
/* 36 */       this.servers.add(wsServer);
/*    */     } 
/*    */ 
/*    */     
/* 40 */     AbstractNettyServer httpServer = (AbstractNettyServer)AppContext.getBean("httpServer");
/* 41 */     httpServer.startServer();
/* 42 */     this.servers.add(httpServer);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     JobStoreRunner qRunner = new JobStoreRunner();
/* 50 */     qRunner.task();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopServers() throws Exception {
/* 55 */     for (AbstractNettyServer nettyServer : this.servers) {
/*    */       try {
/* 57 */         nettyServer.stopServer();
/* 58 */       } catch (Exception e) {
/* 59 */         LogUtil.errorLog(new Object[] { "Unable to stop server {} due to error {}", nettyServer, e });
/* 60 */         throw e;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\server\ServerManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */