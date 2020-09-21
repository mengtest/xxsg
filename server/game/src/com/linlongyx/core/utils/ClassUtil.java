/*    */ package com.linlongyx.core.utils;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class ClassUtil
/*    */ {
/*    */   public static final List<Field> getFields(Class clazz) {
/* 22 */     List<Field> result = new ArrayList<>();
/* 23 */     Field[] fields = clazz.getDeclaredFields();
/* 24 */     clazz = clazz.getSuperclass();
/* 25 */     for (; clazz != null; clazz = clazz.getSuperclass()) {
/* 26 */       Field[] fs = clazz.getDeclaredFields();
/* 27 */       for (Field f : fs) {
/* 28 */         result.add(f);
/*    */       }
/*    */     } 
/* 31 */     for (Field f : fields) {
/* 32 */       result.add(f);
/*    */     }
/* 34 */     return result;
/*    */   }
/*    */   
/*    */   public static Method getSetMethod(Class objectClass, Field field) {
/*    */     try {
/* 39 */       Class[] parameterTypes = new Class[1];
/* 40 */       parameterTypes[0] = field.getType();
/* 41 */       StringBuffer sb = new StringBuffer();
/* 42 */       sb.append("set");
/* 43 */       sb.append(field.getName().substring(0, 1).toUpperCase());
/* 44 */       sb.append(field.getName().substring(1));
/* 45 */       Method method = objectClass.getMethod(sb.toString(), parameterTypes);
/* 46 */       return method;
/* 47 */     } catch (Exception e) {
/* 48 */       e.printStackTrace();
/*    */       
/* 50 */       return null;
/*    */     } 
/*    */   }
/*    */   public static void invokeSet(Object o, Field field, String value) {
/* 54 */     Method method = getSetMethod(o.getClass(), field);
/*    */     
/*    */     try {
/* 57 */       method.invoke(o, new Object[] { JSON.parseObject(value, field.getType()) });
/* 58 */     } catch (Exception e) {
/* 59 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\cor\\utils\ClassUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */