/*    */ package com.linlongyx.sanguo.webgame.common.util;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
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
/*    */ public class SystemUtil
/*    */ {
/*    */   public static void executeLinuxShell(String shell) {
/*    */     try {
/* 21 */       if (!System.getProperty("os.name").matches("^(?i)Windows.*$")) {
/* 22 */         Runtime.getRuntime().exec(shell);
/*    */       }
/* 24 */     } catch (IOException e) {
/* 25 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String executeShell(String shell) {
/*    */     try {
/* 34 */       System.out.println(shell);
/* 35 */       Process proc = Runtime.getRuntime().exec(shell);
/* 36 */       StringBuilder buf = new StringBuilder();
/*    */       
/* 38 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream())); String ls_1;
/* 39 */       while ((ls_1 = bufferedReader.readLine()) != null) {
/* 40 */         buf.append(ls_1);
/*    */       }
/* 42 */       bufferedReader.close();
/* 43 */       proc.waitFor();
/* 44 */       LogUtil.errorLog(new Object[] { "executeShell:" + buf.toString() });
/* 45 */       return buf.toString();
/* 46 */     } catch (IOException|InterruptedException e) {
/* 47 */       e.printStackTrace();
/*    */       
/* 49 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isWindow() {
/* 57 */     return System.getProperty("os.name").matches("^(?i)Windows.*$");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void exit(String str) {
/* 65 */     if (null == str || "".equals(str)) {
/* 66 */       System.exit(0);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void checkGameStart(int port) {
/* 76 */     if (!isWindow()) {
/* 77 */       String shellStr = "lsof -i:";
/*    */ 
/*    */       
/* 80 */       exit(executeShell(shellStr + (port - 100)));
/*    */       
/* 82 */       exit(executeShell(shellStr + (port + 100)));
/* 83 */       exit(executeShell(shellStr + (port + 200)));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 88 */     checkGameStart(19050);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\commo\\util\SystemUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */