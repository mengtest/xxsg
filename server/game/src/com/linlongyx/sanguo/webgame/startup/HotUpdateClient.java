/*    */ package com.linlongyx.sanguo.webgame.startup;
/*    */ 
/*    */ import com.linlongyx.core.framework.httpclient.HttpClientSingleton;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.util.Arrays;
/*    */ import java.util.concurrent.ConcurrentLinkedQueue;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HotUpdateClient
/*    */ {
/* 18 */   private static String[] cmds = new String[] { "/?cmd=1&signature=7923acbd1b2c24b57c5870a10f0705cf", "/?cmd=2&signature=bd6a59293b2e4eac0c8bc398dae77511" };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   private static String[] agents = new String[] { "weixin,xinghui", "weixin", "xinghui" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
/* 30 */   private static ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(POOL_SIZE);
/*    */   
/* 32 */   private static ConcurrentLinkedQueue<String> resultQueue = new ConcurrentLinkedQueue<>();
/*    */   public static void main(String[] args) {
/*    */     try {
/* 35 */       String cmd1tbName = "";
/* 36 */       String agent = agents[1];
/* 37 */       String cmd = cmds[0];
/* 38 */       if (cmd.contains("cmd=1") && !cmd1tbName.isEmpty()) {
/* 39 */         cmd = cmd + "&tbName=" + cmd1tbName;
/*    */       }
/* 41 */       String result = HttpClientSingleton.INSTANCE.get("http://sanguo-ops.linlongyx.com/api/get_agent/?agent=" + agent, null, null);
/* 42 */       String[] serverAddress = result.split(",");
/* 43 */       int count = 0;
/* 44 */       for (String address : serverAddress) {
/* 45 */         System.out.println(address);
/* 46 */         int delay = count / POOL_SIZE;
/* 47 */         String finalCmd = "http://" + address + cmd;
/* 48 */         scheduledService.schedule(() -> LogUtil.errorLog(new Object[] { finalCmd }, ), delay, TimeUnit.SECONDS);
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
/* 59 */         count++;
/*    */       }
/*    */     
/* 62 */     } catch (Exception e) {
/* 63 */       e.printStackTrace();
/* 64 */       LogUtil.errorLog(new Object[] { Arrays.toString((Object[])e.getStackTrace()) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\startup\HotUpdateClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */