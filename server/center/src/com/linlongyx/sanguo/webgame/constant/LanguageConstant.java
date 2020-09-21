/*    */ package com.linlongyx.sanguo.webgame.constant;
/*    */ 
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.sanguo.webgame.config.bean.LanguageBean;
/*    */ import com.linlongyx.sanguo.webgame.service.JsonTableService;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LanguageConstant
/*    */ {
/*    */   public static final int CROSS_TEAM_CREATE_NOTICE = 8101;
/*    */   public static final int CROSS_RESOURCE_OWN_NOTICE = 8104;
/*    */   public static final int CROSS_BATTLE_BE_HIT_NOTICE = 8105;
/*    */   
/*    */   public static String getLanguage(int langId) {
/* 22 */     LanguageBean languageBean = (LanguageBean)JsonTableService.getJsonData(langId, LanguageBean.class);
/* 23 */     if (null != languageBean) {
/* 24 */       return languageBean.getText();
/*    */     }
/* 26 */     return "";
/*    */   }
/*    */   
/*    */   public static String getAndReplaceLanguage(int langId, String... param) {
/* 30 */     LanguageBean languageBean = (LanguageBean)JsonTableService.getJsonData(langId, LanguageBean.class);
/* 31 */     if (null == languageBean) {
/* 32 */       return "";
/*    */     }
/* 34 */     String text = languageBean.getText();
/* 35 */     String[] strings = languageBean.getServerParameter().split(";");
/* 36 */     if (param.length != strings.length) {
/* 37 */       LogUtils.errorLog(new Object[] { "getAndReplaceLanguage", Integer.valueOf(param.length), Arrays.toString((Object[])strings), Arrays.toString((Object[])param) });
/* 38 */       return text;
/*    */     } 
/*    */     
/* 41 */     for (int i = 0; i < strings.length; i++) {
/* 42 */       text = text.replace("{" + strings[i] + "}", param[i]);
/*    */     }
/* 44 */     return text;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\constant\LanguageConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */