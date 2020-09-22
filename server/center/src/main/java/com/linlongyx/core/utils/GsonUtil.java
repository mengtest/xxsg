/*    */ package com.linlongyx.core.utils;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ 
/*    */ public class GsonUtil
/*    */ {
/* 10 */   public static final Gson gson = new Gson();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String toJson(Object obj) {
/* 19 */     return gson.toJson(obj);
/*    */   }
/*    */   
/*    */   public static String toJson(Object obj, Class<?> type) {
/* 23 */     return gson.toJson(obj, type);
/*    */   }
/*    */   
/*    */   public static <T> T fromJson(String str, Class<T> type) {
/* 27 */     return (T)gson.fromJson(str, type);
/*    */   }
/*    */   
/*    */   public static <T> T fromJson(String json, Type typeOfT) {
/* 31 */     return (T)gson.fromJson(json, typeOfT);
/*    */   }
/*    */ 
/*    */   
/* 35 */   private static final Gson disGson = (new GsonBuilder()).disableHtmlEscaping().create();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String toJsonDisableHtmlEsc(Object obj) {
/* 44 */     return disGson.toJson(obj);
/*    */   }
/*    */   
/*    */   public static String toJsonDisableHtmlEsc(Object obj, Class<?> type) {
/* 48 */     return disGson.toJson(obj, type);
/*    */   }
/*    */   
/*    */   public static <T> T fromJsonDisableHtmlEsc(String str, Class<T> type) {
/* 52 */     return (T)disGson.fromJson(str, type);
/*    */   }
/*    */   
/*    */   public static <T> T fromJsonDisableHtmlEsc(String json, Type typeOfT) {
/* 56 */     return (T)disGson.fromJson(json, typeOfT);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\cor\\utils\GsonUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */