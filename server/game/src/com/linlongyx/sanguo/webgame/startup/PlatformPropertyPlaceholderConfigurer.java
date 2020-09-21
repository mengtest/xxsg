/*    */ package com.linlongyx.sanguo.webgame.startup;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import java.io.IOException;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlatformPropertyPlaceholderConfigurer
/*    */   extends PropertyPlaceholderConfigurer
/*    */ {
/*    */   protected Properties mergeProperties() throws IOException {
/* 28 */     return super.mergeProperties();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void loadProperties(Properties props) throws IOException {
/* 33 */     super.loadProperties(props);
/* 34 */     Properties platformProps = new Properties();
/* 35 */     platformProps.load(getClass().getClassLoader().getResourceAsStream(props.getProperty("path") + props.getProperty("platform") + ".properties"));
/* 36 */     props.putAll(platformProps);
/* 37 */     LogUtil.debugLog(new Object[] { "加载配置文件" });
/* 38 */     for (Map.Entry<Object, Object> kv : props.entrySet()) {
/* 39 */       LogUtil.debugLog(new Object[] { "key -> " + kv.getKey() + ", value -> " + kv.getValue() });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\startup\PlatformPropertyPlaceholderConfigurer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */