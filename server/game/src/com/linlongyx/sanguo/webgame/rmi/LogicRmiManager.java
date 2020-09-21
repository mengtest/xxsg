/*    */ package com.linlongyx.sanguo.webgame.rmi;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.rmi.Naming;
/*    */ import java.rmi.registry.LocateRegistry;
/*    */ import java.rmi.server.RMISocketFactory;
/*    */ import java.rmi.server.UnicastRemoteObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogicRmiManager
/*    */ {
/*    */   private String host;
/*    */   private int port;
/*    */   private int cmPort;
/* 20 */   private String url = null;
/*    */   
/*    */   private String serviceName;
/* 23 */   private ILogicRmi iLogicRmi = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void publishRmi() throws Exception {
/* 29 */     RMISocketFactory.setSocketFactory(new SMRMISocket(getCmPort()));
/* 30 */     this.iLogicRmi = new LogicRmiServer();
/* 31 */     setUrl(String.format("rmi://%s:%d/%s", new Object[] { getHost(), Integer.valueOf(getPort()), this.serviceName }));
/* 32 */     LocateRegistry.createRegistry(getPort());
/* 33 */     Naming.rebind(getUrl(), this.iLogicRmi);
/* 34 */     LogUtil.errorLog(new Object[] { "LogicRmiManager::publish rmi service url: {}", getUrl() });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopRmi() {
/*    */     try {
/* 43 */       if (getUrl() != null)
/* 44 */         Naming.unbind(getUrl()); 
/* 45 */       if (null != this.iLogicRmi) {
/* 46 */         UnicastRemoteObject.unexportObject(this.iLogicRmi, true);
/* 47 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::stopRmi" });
/*    */       } 
/* 49 */     } catch (Exception e) {
/* 50 */       LogUtil.errorLog(new Object[] { "LogicRmiManager::stopRmi", e });
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getHost() {
/* 55 */     return this.host;
/*    */   }
/*    */   
/*    */   public void setHost(String host) {
/* 59 */     this.host = host;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 63 */     return this.port;
/*    */   }
/*    */   
/*    */   public void setPort(int port) {
/* 67 */     this.port = port;
/*    */   }
/*    */   
/*    */   public int getCmPort() {
/* 71 */     return this.cmPort;
/*    */   }
/*    */   
/*    */   public void setCmPort(int cmPort) {
/* 75 */     this.cmPort = cmPort;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 79 */     return this.url;
/*    */   }
/*    */   
/*    */   public void setUrl(String url) {
/* 83 */     this.url = url;
/*    */   }
/*    */   
/*    */   public String getServiceName() {
/* 87 */     return this.serviceName;
/*    */   }
/*    */   
/*    */   public void setServiceName(String serviceName) {
/* 91 */     this.serviceName = serviceName;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\LogicRmiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */