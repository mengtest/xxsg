/*    */ package com.linlongyx.sanguo.webgame.rmi;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.DestinyFileUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.race.RaceUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.rank.RankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.runewar.RunewarUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.tower.TowerUtil;
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
/*    */ public class RmiManager
/*    */ {
/*    */   private String host;
/*    */   private int port;
/*    */   private int cmPort;
/* 27 */   private String url = null;
/*    */   
/* 29 */   private ICrossRmi iCrossRmi = null;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void publishRmi() throws Exception {
/* 35 */     RMISocketFactory.setSocketFactory(new SMRMISocket(getCmPort()));
/* 36 */     this.iCrossRmi = new CrossRmiServer();
/* 37 */     setUrl(String.format("rmi://%s:%d/%s", new Object[] { getHost(), Integer.valueOf(getPort()), "crossRankRMI" }));
/* 38 */     LocateRegistry.createRegistry(getPort());
/* 39 */     Naming.rebind(getUrl(), this.iCrossRmi);
/* 40 */     LogUtil.errorLog(new Object[] { "LogicRmiManager::publish rmi service url: {}", getUrl() });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopRmi() {
/*    */     try {
/* 49 */       DestinyFileUtil.writeToFile();
/* 50 */       MatchUtil.saveToDb();
/* 51 */       BattleUtil.shutdownBattle();
/* 52 */       RankActUtil.saveDataToDb();
/* 53 */       RaceUtil.saveToDb();
/* 54 */       TowerUtil.saveToDb();
/* 55 */       RunewarUtil.saveToDb();
/* 56 */       if (getUrl() != null)
/* 57 */         Naming.unbind(getUrl()); 
/* 58 */       if (null != this.iCrossRmi) {
/* 59 */         UnicastRemoteObject.unexportObject(this.iCrossRmi, true);
/* 60 */         LogUtil.errorLog(new Object[] { "LogicRmiManager::stopRmi" });
/*    */       } 
/* 62 */     } catch (Exception e) {
/* 63 */       LogUtil.errorLog(new Object[] { "LogicRmiManager::stopRmi", e });
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getHost() {
/* 68 */     return this.host;
/*    */   }
/*    */   
/*    */   public void setHost(String host) {
/* 72 */     this.host = host;
/*    */   }
/*    */   
/*    */   public int getPort() {
/* 76 */     return this.port;
/*    */   }
/*    */   
/*    */   public void setPort(int port) {
/* 80 */     this.port = port;
/*    */   }
/*    */   
/*    */   public int getCmPort() {
/* 84 */     return this.cmPort;
/*    */   }
/*    */   
/*    */   public void setCmPort(int cmPort) {
/* 88 */     this.cmPort = cmPort;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 92 */     return this.url;
/*    */   }
/*    */   
/*    */   public void setUrl(String url) {
/* 96 */     this.url = url;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\rmi\RmiManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */