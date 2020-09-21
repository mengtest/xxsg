/*     */ package com.linlongyx.sanguo.webgame.startup;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.server.ServerManager;
/*     */ import com.linlongyx.core.utils.ChatUtil;
/*     */ import com.linlongyx.core.utils.MailUtil;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.arena.ArenaUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bagua.BaguaUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.bosshome.WorldBossUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.divine.DivineUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.rankAct.RankActUtil;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.DBIncrementService;
/*     */ import com.linlongyx.sanguo.webgame.service.GroupService;
/*     */ import com.linlongyx.sanguo.webgame.service.JmxAgent;
/*     */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import com.linlongyx.sanguo.webgame.service.PlayerBaseService;
/*     */ import com.linlongyx.sanguo.webgame.service.PubMailService;
/*     */ import com.linlongyx.sanguo.webgame.service.RankBaseService;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*     */ 
/*     */ public class GameServer {
/*  28 */   private static final Logger logger = LoggerFactory.getLogger(GameServer.class);
/*     */   
/*  30 */   private static String[] address = new String[] { "zengguanning@linlongyx.com" };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/*  49 */       AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(new Class[] { SpringConfig.class });
/*     */       
/*  51 */       annotationConfigApplicationContext.registerShutdownHook();
/*     */       
/*  53 */       ConstantService.init();
/*     */ 
/*     */       
/*  56 */       JsonTableService jsonTableService = (JsonTableService)AppContext.getBean("jsonTabel");
/*  57 */       if (!jsonTableService.initJsonTable()) {
/*  58 */         System.err.println("json data error");
/*  59 */         Runtime.getRuntime().exit(0);
/*     */       } 
/*     */ 
/*     */       
/*  63 */       ServerManager serverManager = (ServerManager)annotationConfigApplicationContext.getBean(ServerManager.class);
/*     */       
/*  65 */       DBIncrementService dbIncrementService = (DBIncrementService)annotationConfigApplicationContext.getBean(DBIncrementService.class);
/*  66 */       dbIncrementService.initFromDB();
/*     */       
/*  68 */       PubMailService pubMailService = (PubMailService)annotationConfigApplicationContext.getBean(PubMailService.class);
/*  69 */       pubMailService.initFromDB();
/*     */       
/*  71 */       PlayerBaseService playerBaseService = (PlayerBaseService)annotationConfigApplicationContext.getBean(PlayerBaseService.class);
/*  72 */       playerBaseService.init();
/*     */       
/*  74 */       ChatUtil chatUtil = (ChatUtil)annotationConfigApplicationContext.getBean(ChatUtil.class);
/*  75 */       chatUtil.initSensitiveWord();
/*     */       
/*  77 */       RankBaseService rankBaseService = (RankBaseService)annotationConfigApplicationContext.getBean(RankBaseService.class);
/*  78 */       rankBaseService.init();
/*     */       
/*  80 */       LuckyTurntableUtil.initFromDb();
/*     */ 
/*     */ 
/*     */       
/*  84 */       WorldBossUtil.executeOnStartUp();
/*     */       
/*  86 */       DivineUtil.loadRecords(DivineUtil.getCurDivineActId());
/*     */       
/*  88 */       GroupService groupService = (GroupService)annotationConfigApplicationContext.getBean(GroupService.class);
/*  89 */       groupService.repairGroupId();
/*  90 */       groupService.initFromDB();
/*     */       
/*  92 */       BaguaUtil.loadRecords();
/*     */ 
/*     */ 
/*     */       
/*  96 */       BossUtil.init();
/*  97 */       FightService.init();
/*  98 */       ArenaUtil.refresh();
/*  99 */       RankActUtil.forceRefreshRanks();
/*     */       
/* 101 */       serverManager.startServers();
/* 102 */       JmxAgent jmxAgent = (JmxAgent)annotationConfigApplicationContext.getBean(JmxAgent.class);
/* 103 */       JmxAgent.start();
/* 104 */       LookUpService lookUpService = (LookUpService)annotationConfigApplicationContext.getBean(LookUpService.class);
/* 105 */       JmxAgent.addOnlineNum(lookUpService);
/* 106 */       JmxAgent.addJson(jsonTableService);
/* 107 */       JmxAgent.addSensitive(chatUtil);
/*     */ 
/*     */       
/* 110 */       System.out.println("start game server now");
/* 111 */     } catch (Exception e) {
/* 112 */       logger.error(e.getMessage(), e);
/* 113 */       if (!MContext.getDebug()) {
/* 114 */         MailUtil.sendMail("三群", "<h2>" + MContext.getServerId() + "服启动失败</h2><p>" + e.getMessage() + "</p>", address);
/*     */       }
/*     */     } 
/*     */     
/* 118 */     System.out.println("Started server: " + MContext.getServerId());
/* 119 */     System.out.println("Started version: " + MContext.getVersion());
/* 120 */     System.out.println("Started version: " + VersionUtil.getCurVersion());
/*     */     
/* 122 */     LogUtil.errorLog(new Object[] { "Started version: ", Integer.valueOf(VersionUtil.getCurVersion()) });
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\startup\GameServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */