/*    */ package com.linlongyx.sanguo.webgame.quartz;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.net.http.HttpHelper;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.net.Socket;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpSender
/*    */ {
/* 18 */   static Logger logger = LoggerFactory.getLogger(HttpSender.class);
/* 19 */   private static String IP = "127.0.0.1";
/*    */   private int PORT;
/* 21 */   public static String key = "legend";
/*    */   
/*    */   public HttpSender(int PORT) {
/* 24 */     this.PORT = PORT;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void socket(int cmd) {
/*    */     try {
/* 32 */       Socket socket = new Socket(IP, this.PORT);
/* 33 */       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
/* 34 */       StringBuilder stringBuilder = new StringBuilder();
/* 35 */       stringBuilder.append("GET /?");
/* 36 */       Map<String, String> map = new HashMap<>();
/* 37 */       map.put("cmd", String.valueOf(cmd));
/* 38 */       stringBuilder.append(HttpHelper.buildUrl(map));
/* 39 */       stringBuilder.append(" HTTP/1.1\r\n");
/* 40 */       bw.write(stringBuilder.toString());
/* 41 */       bw.flush();
/* 42 */       bw.close();
/* 43 */       socket.close();
/* 44 */       logger.info("HttpSender.socket：" + IP + ":" + this.PORT + " " + stringBuilder.toString());
/* 45 */     } catch (IOException e) {
/* 46 */       logger.info("HttpSender.socket异常：" + e.toString());
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getPORT() {
/* 51 */     return this.PORT;
/*    */   }
/*    */   
/*    */   public void setPORT(int PORT) {
/* 55 */     this.PORT = PORT;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\quartz\HttpSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */