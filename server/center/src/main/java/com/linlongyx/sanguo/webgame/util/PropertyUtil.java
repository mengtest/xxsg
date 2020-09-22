/*    */ package com.linlongyx.sanguo.webgame.util;
/*    */ 
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PropertyUtil
/*    */ {
/* 16 */   private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
/*    */   private static Properties props;
/*    */   
/*    */   static {
/* 20 */     loadProps();
/*    */   }
/*    */   
/*    */   private static synchronized void loadProps() {
/* 24 */     logger.info("开始加载properties文件内容.......");
/* 25 */     props = new Properties();
/* 26 */     InputStream in = null;
/*    */     try {
/* 28 */       in = PropertyUtil.class.getClassLoader().getResourceAsStream("webgame/props/webgame.properties");
/*    */       
/* 30 */       props.load(in);
/* 31 */     } catch (FileNotFoundException e) {
/* 32 */       logger.error("properties文件未找到");
/* 33 */     } catch (IOException e) {
/* 34 */       logger.error("出现IOException");
/*    */     } finally {
/*    */       try {
/* 37 */         if (null != in) {
/* 38 */           in.close();
/*    */         }
/* 40 */       } catch (IOException e) {
/* 41 */         logger.error("properties文件流关闭出现异常");
/*    */       } 
/*    */     } 
/* 44 */     logger.info("加载properties文件内容完成...........");
/* 45 */     logger.info("properties文件内容：" + props);
/*    */   }
/*    */   
/*    */   public static String getProperty(String key) {
/* 49 */     if (null == props) {
/* 50 */       loadProps();
/*    */     }
/* 52 */     return props.getProperty(key);
/*    */   }
/*    */   
/*    */   public static String getProperty(String key, String defaultValue) {
/* 56 */     if (null == props) {
/* 57 */       loadProps();
/*    */     }
/* 59 */     return props.getProperty(key, defaultValue);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgam\\util\PropertyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */