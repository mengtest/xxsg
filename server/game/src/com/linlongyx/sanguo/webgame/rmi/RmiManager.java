/*     */ package com.linlongyx.sanguo.webgame.rmi;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.ServerData;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.rank.RankingLevel;
/*     */ import org.springframework.remoting.rmi.RmiProxyFactoryBean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RmiManager
/*     */ {
/*     */   private String serviceName;
/*     */   private int logicPort;
/*     */   private String logicHost;
/*     */   private String rmiUrl;
/*  26 */   private RmiProxyFactoryBean crossProxyFactoryBean = new RmiProxyFactoryBean();
/*     */   private static ICrossRmi iCrossRmi;
/*     */   
/*     */   public static ICrossRmi getICrossRmi() {
/*  30 */     return iCrossRmi;
/*     */   }
/*     */   
/*     */   private void initRmi() {
/*     */     try {
/*  35 */       this.crossProxyFactoryBean.setServiceUrl(this.rmiUrl);
/*  36 */       this.crossProxyFactoryBean.setServiceInterface(ICrossRmi.class);
/*  37 */       this.crossProxyFactoryBean.setRefreshStubOnConnectFailure(true);
/*  38 */       this.crossProxyFactoryBean.setLookupStubOnStartup(false);
/*  39 */       this.crossProxyFactoryBean.afterPropertiesSet();
/*  40 */       iCrossRmi = (ICrossRmi)this.crossProxyFactoryBean.getObject();
/*  41 */     } catch (Exception e) {
/*  42 */       e.printStackTrace();
/*  43 */       LogUtil.errorLog(new Object[] { e });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  52 */     initRmi();
/*  53 */     registerLogicRmi();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerLogicRmi() {
/*     */     try {
/*  61 */       String logicRmiUrl = "rmi://" + this.logicHost + ":" + this.logicPort + "/" + this.serviceName;
/*  62 */       if (iCrossRmi != null) {
/*  63 */         ServerData serverData = new ServerData();
/*  64 */         serverData.setServerId(Integer.parseInt(AppContext.getServerId()));
/*  65 */         serverData.setOpentime(MContext.getOpenTimeInt());
/*  66 */         serverData.setWorldLevel(RankingLevel.getWorldLevel());
/*  67 */         serverData.setHefu(ConstantService.hefuServers);
/*  68 */         iCrossRmi.registerLogicRmi(logicRmiUrl, serverData);
/*     */       } 
/*  70 */     } catch (Exception e) {
/*  71 */       iCrossRmi = null;
/*  72 */       if (e instanceof org.springframework.remoting.RemoteLookupFailureException) {
/*  73 */         e.printStackTrace();
/*  74 */         LogUtil.errorLog(new Object[] { "RmiManager::registerLogicCrossRankRmi:e1:" + e.getMessage() });
/*     */       } else {
/*  76 */         LogUtil.errorLog(new Object[] { "RmiManager::registerLogicCrossRankRmi:e2:" + e.getMessage() });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLogicRmi() {
/*     */     try {
/*  86 */       if (iCrossRmi != null)
/*  87 */         iCrossRmi.removeLogicRmi(Integer.parseInt(AppContext.getServerId())); 
/*  88 */     } catch (Exception e) {
/*  89 */       iCrossRmi = null;
/*  90 */       LogUtil.errorLog(new Object[] { "RmiManager::removeLogicCrossRankRmi:" + e.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkLogicRmi() {
/*     */     try {
/* 100 */       if (iCrossRmi != null)
/* 101 */         return !iCrossRmi.needInit(MContext.getServerIdInt()); 
/* 102 */     } catch (Exception e) {
/* 103 */       iCrossRmi = null;
/* 104 */       LogUtil.errorLog(new Object[] { "RmiManager::checkLogicRmi:" + e.getMessage() });
/*     */     } 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getServiceName() {
/* 111 */     return this.serviceName;
/*     */   }
/*     */   
/*     */   public void setServiceName(String serviceName) {
/* 115 */     this.serviceName = serviceName;
/*     */   }
/*     */   
/*     */   public int getLogicPort() {
/* 119 */     return this.logicPort;
/*     */   }
/*     */   
/*     */   public void setLogicPort(int logicPort) {
/* 123 */     this.logicPort = logicPort;
/*     */   }
/*     */   
/*     */   public String getLogicHost() {
/* 127 */     return this.logicHost;
/*     */   }
/*     */   
/*     */   public void setLogicHost(String logicHost) {
/* 131 */     this.logicHost = logicHost;
/*     */   }
/*     */   
/*     */   public String getRmiUrl() {
/* 135 */     return this.rmiUrl;
/*     */   }
/*     */   
/*     */   public void setRmiUrl(String rmiUrl) {
/* 139 */     this.rmiUrl = rmiUrl;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\rmi\RmiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */