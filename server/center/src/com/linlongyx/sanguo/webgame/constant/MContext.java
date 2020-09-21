/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ 
/*    */ 
/*    */ public class MContext
/*    */   extends AppContext
/*    */ {
/*    */   public static final String QUARTZ_MANAGER = "quartzManager";
/*    */   private static boolean ws;
/*    */   private static boolean wss;
/*    */   private static String jks;
/*    */   private static String keystorePass;
/*    */   
/*    */   public static <T> T getBean(Class<T> clazz) {
/* 16 */     return (T)applicationContext.getBean(clazz);
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T> T getBean(String name, Class<T> clazz) {
/* 21 */     return (T)applicationContext.getBean(name, clazz);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isWs() {
/* 30 */     return ws;
/*    */   }
/*    */   
/*    */   public void setWs(boolean ws) {
/* 34 */     MContext.ws = ws;
/*    */   }
/*    */   
/*    */   public static boolean isWss() {
/* 38 */     return wss;
/*    */   }
/*    */   
/*    */   public void setWss(boolean wss) {
/* 42 */     MContext.wss = wss;
/*    */   }
/*    */   
/*    */   public static String getJks() {
/* 46 */     return jks;
/*    */   }
/*    */   
/*    */   public void setJks(String jks) {
/* 50 */     MContext.jks = jks;
/*    */   }
/*    */   
/*    */   public static String getKeystorePass() {
/* 54 */     return keystorePass;
/*    */   }
/*    */   
/*    */   public void setKeystorePass(String keystorePass) {
/* 58 */     MContext.keystorePass = keystorePass;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\constant\MContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */