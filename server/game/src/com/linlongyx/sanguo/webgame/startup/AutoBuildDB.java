/*     */ package com.linlongyx.sanguo.webgame.startup;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ public class AutoBuildDB
/*     */ {
/*  27 */   private static String classPackage = "com.linlongyx.sanguo.webgame.app";
/*     */   
/*     */   public static void main(String[] args) {
/*  30 */     AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(new Class[] { SpringConfig.class });
/*  31 */     checkDB();
/*  32 */     System.exit(0);
/*     */   }
/*     */   
/*     */   public static void checkDB() {
/*  36 */     JdbcTemplate jdbcTemplate = (JdbcTemplate)AppContext.getBean("jdbcTemplate");
/*     */     try {
/*  38 */       Connection connection = jdbcTemplate.getDataSource().getConnection();
/*  39 */       List<Class<?>> clazzs = getClasssFromPackage(classPackage);
/*  40 */       for (Class<?> clazz : clazzs) {
/*  41 */         Table table = (Table)clazz.getAnnotation(Table.class);
/*  42 */         if (table == null) {
/*     */           continue;
/*     */         }
/*  45 */         Field[] fields = clazz.getDeclaredFields();
/*     */         
/*  47 */         if (!hadTable(connection, table.tableName())) {
/*     */           
/*  49 */           createTable(jdbcTemplate, table.tableName(), fields); continue;
/*     */         } 
/*  51 */         Map<String, ColumnInfo> map = getColumnInfoMap(connection, table.tableName());
/*  52 */         for (Field field : fields) {
/*  53 */           if (Modifier.isTransient(field.getModifiers())) {
/*  54 */             TableField tf = field.<TableField>getAnnotation(TableField.class);
/*  55 */             if (null == tf || !tf.isKey()) {
/*     */               continue;
/*     */             }
/*     */           } 
/*  59 */           if (!map.containsKey(field.getName())) {
/*  60 */             addColumn(jdbcTemplate, table.tableName(), field);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } 
/*  70 */     } catch (SQLException e) {
/*  71 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean hadTable(Connection connection, String tableName) {
/*     */     try {
/*  85 */       ResultSet resultSet = connection.getMetaData().getColumns(null, null, tableName, null);
/*  86 */       if (resultSet.next()) {
/*  87 */         return true;
/*     */       }
/*  89 */     } catch (SQLException e) {
/*  90 */       e.printStackTrace();
/*     */     } 
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void createTable(JdbcTemplate jdbcTemplate, String tableName, Field[] fields) {
/* 103 */     StringBuilder stringBuilder = new StringBuilder();
/* 104 */     stringBuilder.append("CREATE TABLE `").append(tableName).append("`(");
/* 105 */     StringBuilder primary = new StringBuilder();
/* 106 */     for (Field field : fields) {
/* 107 */       TableField tableField = field.<TableField>getAnnotation(TableField.class);
/* 108 */       if (Modifier.isTransient(field.getModifiers()) && (
/* 109 */         null == tableField || !tableField.isKey())) {
/*     */         continue;
/*     */       }
/*     */       
/* 113 */       if (tableField != null && tableField.isKey()) {
/* 114 */         primary.append("`").append(field.getName()).append("`").append(",");
/* 115 */         if (tableField.isAutoInc()) {
/* 116 */           stringBuilder.append("`").append(field.getName()).append("` ").append(getSqlTypeByField(field)).append(" NOT NULL ").append(" AUTO_INCREMENT").append(",");
/*     */           continue;
/*     */         } 
/*     */       } 
/* 120 */       stringBuilder.append("`").append(field.getName()).append("` ").append(getSqlTypeByField(field)).append(" NOT NULL ").append(getDefaultValueByField(field)).append(","); continue;
/*     */     } 
/* 122 */     stringBuilder.deleteCharAt(stringBuilder.length() - 1);
/* 123 */     if (primary.length() != 0) {
/* 124 */       stringBuilder.append(",PRIMARY KEY (");
/* 125 */       stringBuilder.append(primary);
/* 126 */       stringBuilder.deleteCharAt(stringBuilder.length() - 1);
/* 127 */       stringBuilder.append(")");
/*     */     } 
/* 129 */     stringBuilder.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
/* 130 */     System.out.println(stringBuilder.toString());
/* 131 */     jdbcTemplate.update(stringBuilder.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map<String, ColumnInfo> getColumnInfoMap(Connection connection, String tableName) {
/* 142 */     Map<String, ColumnInfo> map = new HashMap<>();
/*     */     try {
/* 144 */       ResultSet resultSet = connection.getMetaData().getColumns(null, null, tableName, null);
/* 145 */       while (resultSet.next()) {
/* 146 */         ColumnInfo columnInfo = new ColumnInfo();
/*     */         
/* 148 */         columnInfo.name = resultSet.getString("COLUMN_NAME");
/*     */         
/* 150 */         columnInfo.type = resultSet.getString("TYPE_NAME");
/* 151 */         map.put(columnInfo.name, columnInfo);
/*     */       } 
/* 153 */       ResultSet resultSet1 = connection.getMetaData().getPrimaryKeys(null, null, tableName);
/* 154 */       while (resultSet1.next()) {
/* 155 */         ColumnInfo columnInfo = map.get(resultSet1.getString("COLUMN_NAME"));
/* 156 */         columnInfo.isKey = true;
/*     */       } 
/* 158 */     } catch (SQLException e) {
/* 159 */       e.printStackTrace();
/*     */     } 
/* 161 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addColumn(JdbcTemplate jdbcTemplate, String tableName, Field field) {
/* 172 */     TableField tableField = field.<TableField>getAnnotation(TableField.class);
/* 173 */     StringBuilder stringBuilder = new StringBuilder();
/* 174 */     stringBuilder.append("ALTER TABLE `").append(tableName).append("` ADD `").append(field.getName()).append("` ").append(getSqlTypeByField(field)).append(" NOT NULL ").append(getDefaultValueByField(field)).append(";");
/* 175 */     jdbcTemplate.update(stringBuilder.toString());
/* 176 */     System.out.println(stringBuilder.toString());
/* 177 */     stringBuilder = new StringBuilder();
/* 178 */     if (List.class.isAssignableFrom(field.getType())) {
/* 179 */       stringBuilder.append("UPDATE `").append(tableName).append("` SET `").append(field.getName()).append("` = '[]';");
/* 180 */       jdbcTemplate.update(stringBuilder.toString());
/* 181 */     } else if (Map.class.isAssignableFrom(field.getType())) {
/* 182 */       stringBuilder.append("UPDATE `").append(tableName).append("` SET `").append(field.getName()).append("` = '{}';");
/* 183 */       jdbcTemplate.update(stringBuilder.toString());
/* 184 */     } else if (Set.class.isAssignableFrom(field.getType())) {
/* 185 */       stringBuilder.append("UPDATE `").append(tableName).append("` SET `").append(field.getName()).append("` = '[]';");
/* 186 */       jdbcTemplate.update(stringBuilder.toString());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     System.out.println(stringBuilder.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean modifyColumn(JdbcTemplate jdbcTemplate, String tableName, Field field, ColumnInfo columnInfo) {
/* 209 */     TableField tableField = field.<TableField>getAnnotation(TableField.class);
/* 210 */     boolean needModify = false;
/* 211 */     if (tableField == null) {
/* 212 */       if (!columnInfo.type.equals(getSqlTypeByField(field))) {
/* 213 */         needModify = true;
/*     */       }
/*     */     }
/* 216 */     else if (columnInfo.isKey != tableField.isKey() || columnInfo.isAutoInc != tableField.isAutoInc()) {
/* 217 */       needModify = true;
/*     */     } 
/*     */     
/* 220 */     if (!needModify) {
/* 221 */       return false;
/*     */     }
/*     */     
/* 224 */     StringBuilder stringBuilder = new StringBuilder();
/* 225 */     stringBuilder.append("ALTER TABLE `").append(tableName).append("` MODIFY ").append(field.getName()).append(" ").append(getSqlTypeByField(field)).append(" NOT NULL ").append(getDefaultValueByField(field)).append(";");
/* 226 */     jdbcTemplate.update(stringBuilder.toString());
/* 227 */     if (tableField != null) {
/* 228 */       if (tableField.isKey() && !columnInfo.isKey) {
/*     */         
/* 230 */         stringBuilder = new StringBuilder();
/* 231 */         stringBuilder.append("ALTER TABLE `").append(tableName).append("` ADD PRIMARY KEY (`").append(field.getName()).append("`);");
/* 232 */         jdbcTemplate.update(stringBuilder.toString());
/* 233 */         if (tableField.isAutoInc() && !columnInfo.isAutoInc) {
/*     */           
/* 235 */           stringBuilder = new StringBuilder();
/* 236 */           stringBuilder.append("ALTER TABLE `").append(tableName).append("` MODIFY ").append(field.getName()).append(" ").append(getSqlTypeByField(field)).append(" NOT NULL ").append(" AUTO_INCREMENT").append(";");
/* 237 */           jdbcTemplate.update(stringBuilder.toString());
/*     */         } 
/*     */       } 
/* 240 */       if (tableField.isKey() && columnInfo.isKey && tableField.isAutoInc() && !columnInfo.isAutoInc) {
/*     */         
/* 242 */         stringBuilder = new StringBuilder();
/* 243 */         stringBuilder.append("ALTER TABLE `").append(tableName).append("` MODIFY ").append(field.getName()).append(" ").append(getSqlTypeByField(field)).append(" NOT NULL ").append(" AUTO_INCREMENT").append(";");
/* 244 */         jdbcTemplate.update(stringBuilder.toString());
/*     */       } 
/* 246 */       if (columnInfo.isKey && !tableField.isKey()) {
/*     */         
/* 248 */         stringBuilder = new StringBuilder();
/* 249 */         stringBuilder.append("ALTER TABLE `").append(tableName).append("` DROP PRIMARY KEY;");
/* 250 */         jdbcTemplate.update(stringBuilder.toString());
/*     */       } 
/*     */     } 
/* 253 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void dropColumn(JdbcTemplate jdbcTemplate, String tableName, Set<String> set) {
/* 264 */     if (set.size() == 0) {
/*     */       return;
/*     */     }
/* 267 */     StringBuilder stringBuilder = new StringBuilder();
/* 268 */     stringBuilder.append("ALTER TABLE `").append(tableName).append("`");
/* 269 */     for (String string : set) {
/* 270 */       stringBuilder.append(" DROP COLUMN ").append(string).append(",");
/*     */     }
/* 272 */     stringBuilder.deleteCharAt(stringBuilder.length() - 1);
/* 273 */     stringBuilder.append(";");
/* 274 */     jdbcTemplate.update(stringBuilder.toString());
/*     */   }
/*     */   
/*     */   private static String getDefaultValueByField(Field field) {
/* 278 */     if (field.getType() == long.class || field.getType() == Long.class)
/* 279 */       return "DEFAULT '0'"; 
/* 280 */     if (field.getType() == int.class || field.getType() == Integer.class)
/* 281 */       return "DEFAULT '0'"; 
/* 282 */     if (field.getType() == short.class || field.getType() == Short.class)
/* 283 */       return "DEFAULT '0'"; 
/* 284 */     if (field.getType() == byte.class || field.getType() == Byte.class)
/* 285 */       return "DEFAULT '0'"; 
/* 286 */     if (field.getType() == double.class || field.getType() == Double.class)
/* 287 */       return "DEFAULT '0.0'"; 
/* 288 */     if (field.getType() == float.class || field.getType() == Float.class)
/* 289 */       return "DEFAULT '0.0'"; 
/* 290 */     if (field.getType() == String.class)
/* 291 */       return "DEFAULT ''"; 
/* 292 */     if (field.getType() == boolean.class || field.getType() == Boolean.class)
/* 293 */       return "DEFAULT '0'"; 
/* 294 */     if (field.getType() == boolean.class || field.getType() == Boolean.class)
/* 295 */       return "DEFAULT '0'"; 
/* 296 */     if (List.class.isAssignableFrom(field.getType()))
/* 297 */       return ""; 
/* 298 */     if (Map.class.isAssignableFrom(field.getType())) {
/* 299 */       return "";
/*     */     }
/* 301 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getSqlTypeByField(Field field) {
/* 306 */     if (field.getType() == long.class || field.getType() == Long.class)
/* 307 */       return "BIGINT(20)"; 
/* 308 */     if (field.getType() == int.class || field.getType() == Integer.class)
/* 309 */       return "INT(11)"; 
/* 310 */     if (field.getType() == short.class || field.getType() == Short.class)
/* 311 */       return "SMALLINT(6)"; 
/* 312 */     if (field.getType() == byte.class || field.getType() == Byte.class)
/* 313 */       return "TINYINT(4)"; 
/* 314 */     if (field.getType() == double.class || field.getType() == Double.class)
/* 315 */       return "DOUBLE"; 
/* 316 */     if (field.getType() == float.class || field.getType() == Float.class)
/* 317 */       return "FLOAT"; 
/* 318 */     if (field.getType() == String.class)
/* 319 */       return "VARCHAR(255)"; 
/* 320 */     if (field.getType() == boolean.class || field.getType() == Boolean.class) {
/* 321 */       return "TINYINT(1)";
/*     */     }
/* 323 */     return "TEXT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<Class> getClasssFromPackage(String packageName) {
/* 334 */     List<Class<?>> clazzs = new ArrayList<>();
/*     */     
/* 336 */     boolean recursive = true;
/*     */     
/* 338 */     String packageDirName = packageName.replace('.', '/');
/*     */     
/*     */     try {
/* 341 */       Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
/* 342 */       while (dirs.hasMoreElements()) {
/* 343 */         URL url = dirs.nextElement();
/* 344 */         String protocol = url.getProtocol();
/* 345 */         if ("file".equals(protocol)) {
/* 346 */           System.out.println("file类型的扫描");
/* 347 */           String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
/* 348 */           findClassInPackageByFile(packageName, filePath, recursive, clazzs); continue;
/* 349 */         }  if ("jar".equals(protocol)) {
/* 350 */           System.out.println("jar类型的扫描");
/*     */         }
/*     */       } 
/* 353 */     } catch (Exception e) {
/* 354 */       e.printStackTrace();
/*     */     } 
/* 356 */     return clazzs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive, List<Class<?>> clazzs) {
/* 368 */     File dir = new File(filePath);
/* 369 */     if (!dir.exists() || !dir.isDirectory()) {
/*     */       return;
/*     */     }
/*     */     
/* 373 */     File[] dirFiles = dir.listFiles(new FileFilter()
/*     */         {
/*     */           public boolean accept(File file) {
/* 376 */             boolean acceptDir = (recursive && file.isDirectory());
/* 377 */             boolean acceptClass = file.getName().endsWith("class");
/* 378 */             return (acceptDir || acceptClass);
/*     */           }
/*     */         });
/* 381 */     for (File file : dirFiles) {
/* 382 */       if (file.isDirectory()) {
/* 383 */         findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
/*     */       } else {
/* 385 */         String className = file.getName().substring(0, file.getName().length() - 6);
/*     */         try {
/* 387 */           clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
/* 388 */         } catch (Exception e) {
/* 389 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\startup\AutoBuildDB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */