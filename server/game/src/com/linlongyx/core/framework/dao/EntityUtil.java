/*     */ package com.linlongyx.core.framework.dao;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class EntityUtil
/*     */ {
/*     */   private static Class<?> clazz;
/*     */   private static IEntity entity;
/*     */   private static Map<String, String> initInfo;
/*     */   
/*     */   public static boolean isInField(String fieldName, Class<?> clazz) {
/*  28 */     Field[] fields = clazz.getDeclaredFields();
/*  29 */     for (Field field : fields) {
/*  30 */       if (field.getName().contains(fieldName))
/*  31 */         return true; 
/*     */     } 
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getSetterName(Field field) {
/*  43 */     String fieldName = field.getName();
/*     */     
/*  45 */     String firstLetter = field.getName().substring(0, 1).toUpperCase();
/*  46 */     if ((field.getType() == boolean.class || field.getType() == Boolean.class) && 
/*  47 */       fieldName.startsWith("is") && 
/*  48 */       fieldName.length() > 2 && Character.isUpperCase(fieldName.charAt(2))) {
/*  49 */       return "set" + fieldName.substring(2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  54 */     return "set" + firstLetter + field.getName().substring(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getGetterName(Field field) {
/*  64 */     String fieldName = field.getName();
/*     */     
/*  66 */     String firstLetter = fieldName.substring(0, 1).toUpperCase();
/*     */     
/*  68 */     if (field.getType() == boolean.class || field.getType() == Boolean.class) {
/*  69 */       if (fieldName.startsWith("is")) {
/*  70 */         return fieldName;
/*     */       }
/*  72 */       return "is" + firstLetter + fieldName.substring(1);
/*     */     } 
/*     */     
/*  75 */     return "get" + firstLetter + fieldName.substring(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAttrByMethod(String methodStr) {
/*  84 */     String firstLetter = methodStr.substring(3, 4).toLowerCase();
/*  85 */     return firstLetter + methodStr.substring(4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object generateTypeClass(Class<?> paramClass, String value, Type typeOfT) {
/*  95 */     if (int.class == paramClass) {
/*  96 */       return Integer.valueOf(Integer.parseInt(value));
/*     */     }
/*  98 */     if (String.class == paramClass)
/*  99 */       return value; 
/* 100 */     if (Long.class == paramClass || long.class == paramClass)
/* 101 */       return Long.valueOf(Long.parseLong(value)); 
/* 102 */     if (Byte.class == paramClass)
/* 103 */       return Byte.valueOf(Byte.parseByte(value)); 
/* 104 */     if (Short.class == paramClass)
/* 105 */       return Short.valueOf(Short.parseShort(value)); 
/* 106 */     if (Boolean.class == paramClass) {
/* 107 */       if ("1".equals(value)) return Boolean.valueOf(true); 
/* 108 */       return Boolean.valueOf(false);
/*     */     } 
/*     */ 
/*     */     
/* 112 */     return GsonUtil.fromJson(value, typeOfT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String generateTypeString(Class<?> paramClass, Object value) {
/* 122 */     if (int.class == paramClass || String.class == paramClass || Long.class == paramClass || long.class == paramClass || Byte.class == paramClass || Short.class == paramClass)
/*     */     {
/*     */       
/* 125 */       return value.toString(); } 
/* 126 */     if (Boolean.class == paramClass || boolean.class == paramClass) {
/* 127 */       return ((Boolean)value).booleanValue() ? "1" : "0";
/*     */     }
/* 129 */     return GsonUtil.toJson(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFieldValue(IEntity entity, Field field) {
/* 139 */     String getMethodName = getGetterName(field);
/*     */     try {
/* 141 */       Method getMethod = entity.getClass().getMethod(getMethodName, new Class[0]);
/*     */       
/* 143 */       Object value = getMethod.invoke(entity, new Object[0]);
/* 144 */       return generateTypeString(field.getType(), value);
/* 145 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 148 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFieldValue(Class<?> clazz, IEntity entity, String fieldName) {
/*     */     try {
/* 159 */       Field field = clazz.getDeclaredField(fieldName);
/* 160 */       String getMethodName = getGetterName(field);
/* 161 */       Method getMethod = entity.getClass().getMethod(getMethodName, new Class[0]);
/*     */       
/* 163 */       Object value = getMethod.invoke(entity, new Object[0]);
/* 164 */       return generateTypeString(field.getType(), value);
/* 165 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 168 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFieldName(Field field) {
/* 177 */     String keyName = field.getName();
/* 178 */     TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 179 */     if (Modifier.isTransient(field.getModifiers()) && null == tf)
/* 180 */       return null; 
/* 181 */     if (null != tf) {
/* 182 */       if (Modifier.isTransient(field.getModifiers()) && !tf.isKey())
/* 183 */         return null; 
/* 184 */       if (!tf.name().isEmpty())
/* 185 */         keyName = tf.name(); 
/*     */     } 
/* 187 */     return keyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getKeyField(Class<?> clazz) {
/* 195 */     Table table = clazz.<Table>getAnnotation(Table.class);
/* 196 */     if (table.keyField().isEmpty() && 
/* 197 */       table.isPlayerIdKey()) {
/* 198 */       return "playerId";
/*     */     }
/* 200 */     return table.keyField();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class<?> getKeyType(Class<?> clazz) {
/* 209 */     Table table = clazz.<Table>getAnnotation(Table.class);
/*     */     try {
/* 211 */       String keyStr = table.keyField();
/* 212 */       if (keyStr.isEmpty() && table.isPlayerIdKey()) {
/* 213 */         keyStr = "playerId";
/*     */       }
/* 215 */       Field field = clazz.getDeclaredField(keyStr);
/* 216 */       Class<?> type = field.getType();
/* 217 */       return type;
/* 218 */     } catch (NoSuchFieldException e) {
/* 219 */       e.printStackTrace();
/*     */       
/* 221 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isPlayerIdKey(Class<?> clazz) {
/* 230 */     Table table = clazz.<Table>getAnnotation(Table.class);
/* 231 */     return table.isPlayerIdKey();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initEntity(Class<?> clazz, IEntity entity, Map<String, String> initInfo) {
/* 240 */     EntityUtil.clazz = clazz;
/* 241 */     EntityUtil.entity = entity;
/* 242 */     EntityUtil.initInfo = initInfo;
/* 243 */     Field[] fields = clazz.getDeclaredFields();
/* 244 */     for (Field field : fields) {
/* 245 */       if (!Modifier.isFinal(field.getModifiers())) {
/*     */ 
/*     */         
/* 248 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 249 */         String fieldName = field.getName();
/* 250 */         if (null != tf && !tf.name().isEmpty()) {
/* 251 */           fieldName = tf.name();
/*     */         }
/* 253 */         String value = initInfo.get(fieldName);
/* 254 */         if (null != value) {
/*     */           
/* 256 */           String setMethodName = getSetterName(field);
/* 257 */           Method setMethod = null;
/*     */           try {
/* 259 */             Object val = generateTypeClass(field.getType(), value, field.getGenericType());
/* 260 */             if (val != null)
/* 261 */             { setMethod = clazz.getDeclaredMethod(setMethodName, new Class[] { field.getType() });
/* 262 */               setMethod.invoke(entity, new Object[] { generateTypeClass(field.getType(), value, field.getGenericType()) }); } 
/* 263 */           } catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException|IllegalAccessException e) {
/* 264 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initMapEntity(Class<?> clazz, IMapEntity entity, Map<String, Object> initInfo) {
/* 275 */     Field[] fields = clazz.getDeclaredFields();
/* 276 */     for (Field field : fields) {
/* 277 */       if (!Modifier.isFinal(field.getModifiers())) {
/*     */ 
/*     */         
/* 280 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 281 */         String fieldName = field.getName();
/* 282 */         if (null != tf && !tf.name().isEmpty()) {
/* 283 */           fieldName = tf.name();
/*     */         }
/* 285 */         Object value = initInfo.get(fieldName);
/* 286 */         if (null != value) {
/*     */           
/* 288 */           String setMethodName = getSetterName(field);
/* 289 */           Method setMethod = null;
/*     */           try {
/* 291 */             setMethod = clazz.getDeclaredMethod(setMethodName, new Class[] { field.getType() });
/* 292 */             setMethod.invoke(entity, new Object[] { generateTypeClass(field.getType(), value.toString(), field.getGenericType()) });
/* 293 */           } catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException|IllegalAccessException e) {
/* 294 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initMapEntity(Class<?> clazz, IMapEntity entity, IMapEntity sourceEntity) {
/* 306 */     Field[] fields = clazz.getDeclaredFields();
/* 307 */     for (Field field : fields) {
/* 308 */       if (!Modifier.isFinal(field.getModifiers())) {
/*     */         
/* 310 */         String setMethodName = getSetterName(field);
/* 311 */         String getMethodName = getGetterName(field);
/* 312 */         Method setMethod = null;
/* 313 */         Method getMethod = null;
/*     */         try {
/* 315 */           getMethod = clazz.getDeclaredMethod(getMethodName, new Class[0]);
/* 316 */           Object value = getMethod.invoke(sourceEntity, new Object[0]);
/* 317 */           if (value != null)
/* 318 */           { setMethod = clazz.getDeclaredMethod(setMethodName, new Class[] { field.getType() });
/* 319 */             setMethod.invoke(entity, new Object[] { value }); } 
/* 320 */         } catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException|IllegalAccessException e) {
/* 321 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTableName(Class<?> entityClass) {
/* 332 */     Table tableName = entityClass.<Table>getAnnotation(Table.class);
/* 333 */     String name = "";
/* 334 */     if (null != tableName) {
/* 335 */       name = tableName.tableName();
/*     */     } else {
/* 337 */       name = entityClass.getName().substring(entityClass.getName().lastIndexOf(".") + 1);
/* 338 */     }  return name;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\EntityUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */