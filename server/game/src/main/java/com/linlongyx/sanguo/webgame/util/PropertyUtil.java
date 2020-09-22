/*    */ package com.linlongyx.sanguo.webgame.util;
/*    */ 
/*    */ import com.linlongyx.core.framework.context.AppContext;
/*    */ import com.linlongyx.sanguo.webgame.processors.MsgDispatcher;
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
/* 18 */   private static final Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);
/*    */   private static Properties props;
/*    */   
/*    */   static {
/* 22 */     loadProps();
/*    */   }
/*    */   
/*    */   private static synchronized void loadProps() {
/* 26 */     logger.info("开始加载properties文件内容.......");
/* 27 */     props = new Properties();
/* 28 */     InputStream in = null;
/*    */     try {
/* 30 */       String platform = AppContext.getPlatform();
/* 31 */       in = PropertyUtil.class.getClassLoader().getResourceAsStream("webgame/platform/" + platform + ".properties");
/* 32 */       props.load(in);
/* 33 */     } catch (FileNotFoundException e) {
/* 34 */       logger.error("properties文件未找到");
/* 35 */     } catch (IOException e) {
/* 36 */       logger.error("出现IOException");
/*    */     } finally {
/*    */       try {
/* 39 */         if (null != in) {
/* 40 */           in.close();
/*    */         }
/* 42 */       } catch (IOException e) {
/* 43 */         logger.error("properties文件流关闭出现异常");
/*    */       } 
/*    */     } 
/* 46 */     logger.info("加载properties文件内容完成...........");
/* 47 */     logger.info("properties文件内容：" + props);
/*    */   }
/*    */   
/*    */   public static String getProperty(String key) {
/* 51 */     if (null == props) {
/* 52 */       loadProps();
/*    */     }
/* 54 */     return props.getProperty(key);
/*    */   }
/*    */   
/*    */   public static String getProperty(String key, String defaultValue) {
/* 58 */     if (null == props) {
/* 59 */       loadProps();
/*    */     }
/* 61 */     return props.getProperty(key, defaultValue);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgam\\util\PropertyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */