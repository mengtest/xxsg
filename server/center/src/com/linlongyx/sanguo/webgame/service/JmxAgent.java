/*     */ package com.linlongyx.sanguo.webgame.service;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.utils.ChatUtil;
/*     */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
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
/*  27 */     ModelMBean playerComponent = managed.createMBean(new PlayerData());
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
/*     */   private static Resource path;
/*     */   
/*     */   private static String name;
/*     */   
/*     */   private static String pass;
/*     */   private static int port;
/*     */   
/*     */   public Resource getPath() {
/*  51 */     return path;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  55 */     JmxAgent.name = name;
/*     */   }
/*     */   
/*     */   public void setPass(String pass) {
/*  59 */     JmxAgent.pass = pass;
/*     */   }
/*     */   
/*     */   public void setPort(int port) {
/*  63 */     JmxAgent.port = port;
/*     */   }
/*     */   
/*     */   public void setPath(Resource path) {
/*  67 */     this; JmxAgent.path = path;
/*     */   }
/*     */   
/*     */   public static void start() throws Exception {
/*  71 */     Registry registry = Registry.getRegistry(null, null);
/*  72 */     InputStream stream = path.getInputStream();
/*  73 */     registry.loadMetadata(stream);
/*  74 */     stream.close();
/*  75 */     server = registry.getMBeanServer();
/*  76 */     managed = registry.findManagedBean("player");
/*  77 */     managed2 = registry.findManagedBean("onlineNum");
/*  78 */     managed3 = registry.findManagedBean("reloadJson");
/*  79 */     managed4 = registry.findManagedBean("reloadSensitive");
/*  80 */     ObjectName adapterName = new ObjectName("JmxAgent:name=htmladapter,port=" + port);
/*  81 */     HtmlAdaptorServer adapter = new HtmlAdaptorServer();
/*  82 */     adapter.setPort(port);
/*  83 */     AuthInfo login = new AuthInfo();
/*  84 */     login.setLogin(name);
/*  85 */     login.setPassword(pass);
/*  86 */     adapter.addUserAuthenticationInfo(login);
/*  87 */     server.registerMBean(adapter, adapterName);
/*  88 */     adapter.start();
/*     */   }
/*     */   
/*     */   public static void addJson(JsonTableService jsonTableService) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/*  92 */     ObjectName beanName = new ObjectName(managed3.getGroup() + ":Click=reloadJson");
/*  93 */     ModelMBean modelMBean = managed3.createMBean(jsonTableService);
/*  94 */     server.registerMBean(modelMBean, beanName);
/*     */   }
/*     */   
/*     */   public static void addOnlineNum(LookUpService lookUpService) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/*  98 */     ObjectName beanName = new ObjectName(managed2.getGroup() + ":Click=ShowDetail");
/*  99 */     ModelMBean lookUpServiceBean = managed2.createMBean(lookUpService);
/* 100 */     server.registerMBean(lookUpServiceBean, beanName);
/*     */   }
/*     */   
/*     */   public static void addSensitive(ChatUtil chatUtil) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/* 104 */     ObjectName beanName = new ObjectName(managed4.getGroup() + ":Click=reloadSensitive");
/* 105 */     ModelMBean chatUtilBean = managed4.createMBean(chatUtil);
/* 106 */     server.registerMBean(chatUtilBean, beanName);
/*     */   }
/*     */   
/*     */   public static void addJmx(IPlayer player) throws MalformedObjectNameException, InvalidTargetObjectTypeException, MBeanException, InstanceNotFoundException, InstanceAlreadyExistsException, NotCompliantMBeanException {
/* 110 */     ObjectName playerName = new ObjectName(managed.getGroup() + ":playerId=" + player.getPlayerId() + player.getPlayerName());
/* 111 */     ModelMBean playerBean = managed.createMBean(player);
/* 112 */     if (!server.isRegistered(playerName)) {
/* 113 */       server.registerMBean(playerBean, playerName);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void deleteJmx(IPlayer player) {
/*     */     try {
/* 119 */       ObjectName playerName = new ObjectName(managed.getGroup() + ":playerId=" + player.getPlayerId() + player.getPlayerName());
/* 120 */       if (server.isRegistered(playerName)) {
/* 121 */         server.unregisterMBean(playerName);
/*     */       }
/* 123 */     } catch (InstanceNotFoundException|javax.management.MBeanRegistrationException|MalformedObjectNameException e) {
/* 124 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\service\JmxAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */