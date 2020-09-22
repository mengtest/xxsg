/*    */ package com.linlongyx.startup;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.DestinyFileUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.destiny.match.MatchUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.race.RaceUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.rank.RankActUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.runewar.RunewarUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.tower.TowerUtil;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Scanner;
/*    */ import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*    */ import org.springframework.context.support.AbstractApplicationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossServer
/*    */ {
/*    */   public static void main(String[] args) {
/* 27 */     AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(new Class[] { SpringConfig.class });
/* 28 */     annotationConfigApplicationContext.registerShutdownHook();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 34 */       JsonTableService jsonTableService = (JsonTableService)AppContext.getBean("jsonTabel");
/* 35 */       if (!jsonTableService.initJsonTable()) {
/* 36 */         LogUtil.errorLog(new Object[] { "json data error" });
/* 37 */         Runtime.getRuntime().exit(0);
/*    */       } 
/*    */       
/* 40 */       LogUtil.debugLog(new Object[] { "cross server starting" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 54 */       DestinyFileUtil.readFromFile();
/* 55 */       MatchUtil.loadFromDb();
/* 56 */       RankActUtil.loadDataFromDb();
/* 57 */       BattleUtil.loadFromDb();
/* 58 */       RaceUtil.loadDataFromDb();
/* 59 */       TowerUtil.loadFromDb();
/* 60 */       RunewarUtil.loadFromDb();
/* 61 */       LogUtil.debugLog(new Object[] { "readLocalCache over" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 68 */       System.out.println("start cross server now");
/* 69 */     } catch (Exception e) {
/* 70 */       LogUtil.errorLog(new Object[] { e.getMessage(), e });
/*    */     } 
/* 72 */     LogUtil.debugLog(new Object[] { "cross server started " });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void exitDely(AbstractApplicationContext ctx) {
/* 78 */     Scanner scanner = new Scanner(System.in);
/*    */     try {
/* 80 */       while (scanner.hasNext()) {
/* 81 */         String next = scanner.next();
/* 82 */         if ("exit".equalsIgnoreCase(next)) {
/* 83 */           ctx.destroy();
/*    */           break;
/*    */         } 
/*    */       } 
/* 87 */     } catch (Exception e) {
/* 88 */       LogUtil.errorLog(new Object[] { "CrossServer::exitDely", e.getMessage() });
/*    */     } finally {
/* 90 */       scanner.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\startup\CrossServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */