/*    */ package com.linlongyx.sanguo.client.startup;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.common.util.RandUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameClient
/*    */ {
/* 11 */   public static Object o = new Object();
/*    */   
/* 13 */   public static int usrid_start = 10000;
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static String ip = "127.0.0.1";
/* 18 */   public static int port = 19050;
/*    */   
/* 20 */   public static int num = 1;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 26 */     if (args.length > 0) {
/* 27 */       if (args.length < 3) {
/* 28 */         System.out.println("Usage: java -jar GameClient.jar usrid_start serverIp serverPort");
/*    */         return;
/*    */       } 
/* 31 */       usrid_start = Integer.valueOf(args[0]).intValue();
/* 32 */       num = Integer.valueOf(args[1]).intValue();
/* 33 */       ip = args[2];
/* 34 */       port = Integer.valueOf(args[3]).intValue();
/*    */     } 
/* 36 */     for (long i = usrid_start; i < (usrid_start + 1); i++) {
/* 37 */       (new Thread(new WebSocketClient(i, ip, port))).start();
/* 38 */       int millTime = RandUtil.randNum(200, 500);
/* 39 */       Thread.sleep(millTime);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\startup\GameClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */