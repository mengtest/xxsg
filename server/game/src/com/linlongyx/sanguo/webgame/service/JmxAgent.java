/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.ChatUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.sun.jdmk.comm.AuthInfo;
/*     */ import com.sun.jdmk.comm.HtmlAdaptorServer;
/*     */ import java.io.InputStream;
/*     */ import javax.management.InstanceAlreadyExistsException;
/*     */ import javax.management.InstanceNotFoundException;
/*     */ import javax.management.JMException;
/*     */ import javax.management.MBeanException;
/*     */ import javax.management.MalformedObjectNameException;
/*     */ import javax.management.NotCompliantMBeanException;
/*     */ import javax.management.ObjectName;
/*     */ import javax.management.modelmbean.InvalidTargetObjectTypeException;
/*     */ import javax.management.modelmbean.ModelMBean;
/*     */ import org.apache.commons.modeler.ManagedBean;
/*     */ import org.apache.commons.modeler.Registry;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public class JmxAgent {
/*     */   public static void main(String[] args) throws Exception {
/*  23 */     start();
/*     */     
/*  25 */     ObjectName playerName = new ObjectName(managed.getDomain() + ":name=player");
/*     */     
/*  27 */     ModelMBean playerComponent = managed.createMBean(new PlayerComponent(1L, 2L));
/*     */     
/*  29 */     server.registerMBean(playerComponent, playerName);
/*     */   }
/*     */ 
/*     */   
/*     */   private static MBeanServer server;
/*     */   
/*     */   private static ManagedBean managed;
/*     */   
/*     */   private static ManagedBean managed2;
/*     */   
/*     */   private static ManagedBean managed3;
/*     */   
/*     */   private static ManagedBean managed4;
/*     */   
/*     */   private static ManagedBean managed5;
/*     */   
/*     */   private static Resource path;
/*     */   
/*     */   private static String name;
/*     */   
/*     */   private static String pass;
/*     */   
/*     */   private static int port;
/*     */ 
/*     */   
/*     */   public Resource getPath() {
/*  55 */     return path;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  59 */     JmxAgent.name = name;
/*     */   }
/*     */   
/*     */   public void setPass(String pass) {
/*  63 */     JmxAgent.pass = pass;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/*  67 */     JmxAgent.port = port;
/*     */   }
/*     */   
/*     */   public void setPath(Resource path) {
/*  71 */     this; JmxAgent.path = path;
/*     */   }
/*     */   
/*     */   public static void start() throws Exception {
/*  75 */     Registry registry = Registry.getRegistry(null, null);
/*  76 */     InputStream stream = path.getInputStream();
/*  77 */     registry.loadMetadata(stream);
/*  78 */     stream.close();
/*  79 */     server = registry.getMBeanServer();
/*  80 */     managed = registry.findManagedBean("player");
/*  81 */     managed2 = registry.findManagedBean("onlineNum");
/*  82 */     managed3 = registry.findManagedBean("reloadJson");
/*  83 */     managed4 = registry.findManagedBean("reloadSensitive");
/*  84 */     managed5 = registry.findManagedBean("statisticsService");
/*     */     
/*  86 */     ObjectName adapterName = new ObjectName("JmxAgent:name=htmladapter,port=" + port);
/*  87 */     HtmlAdaptorServer adapter = new HtmlAdaptorServer();
/*  88 */     adapter.setPort(port);
/*  89 */     AuthInfo login = new AuthInfo();
/*  90 */     login.setLogin(name);
/*  91 */     login.setPassword(pass);
/*  92 */     adapter.addUserAuthenticationInfo(login);
/*  93 */     server.registerMBean(adapter, adapterName);
/*  94 */     adapter.start();
/*     */   }
/*     */   
/*     */   public static void addJson(JsonTableService jsonTableService) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/*  98 */     ObjectName beanName = new ObjectName(managed3.getGroup() + ":Click=reloadJson");
/*  99 */     ModelMBean modelMBean = managed3.createMBean(jsonTableService);
/* 100 */     server.registerMBean(modelMBean, beanName);
/*     */   }
/*     */   
/*     */   public static void addOnlineNum(LookUpService lookUpService) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/* 104 */     ObjectName beanName = new ObjectName(managed2.getGroup() + ":Click=ShowDetail");
/* 105 */     ModelMBean lookUpServiceBean = managed2.createMBean(lookUpService);
/* 106 */     server.registerMBean(lookUpServiceBean, beanName);
/*     */   }
/*     */   
/*     */   public static void addSensitive(ChatUtil chatUtil) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/* 110 */     ObjectName beanName = new ObjectName(managed4.getGroup() + ":Click=reloadSensitive");
/* 111 */     ModelMBean chatUtilBean = managed4.createMBean(chatUtil);
/* 112 */     server.registerMBean(chatUtilBean, beanName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addJmx(IPlayer player) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/* 124 */     ObjectName playerName = new ObjectName(managed.getGroup() + ":playerId=" + player.getPlayerId() + player.getPlayerName());
/* 125 */     ModelMBean playerBean = managed.createMBean(player);
/* 126 */     if (!server.isRegistered(playerName)) {
/* 127 */       server.registerMBean(playerBean, playerName);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void deleteJmx(IPlayer player) {
/*     */     try {
/* 133 */       ObjectName playerName = new ObjectName(managed.getGroup() + ":playerId=" + player.getPlayerId() + player.getPlayerName());
/* 134 */       if (server.isRegistered(playerName)) {
/* 135 */         server.unregisterMBean(playerName);
/*     */       }
/* 137 */     } catch (InstanceNotFoundException|javax.management.MBeanRegistrationException|MalformedObjectNameException e) {
/* 138 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\JmxAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */