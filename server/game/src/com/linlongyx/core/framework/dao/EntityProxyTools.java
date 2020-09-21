/*     */ package com.linlongyx.core.framework.dao;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.proxy.EntityProxy;
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.utils.ClassUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityProxyTools
/*     */ {
/*     */   public static String makeSelectSql(String tableName) {
/*  24 */     StringBuilder sb = new StringBuilder();
/*  25 */     sb.append("SELECT * FROM ");
/*  26 */     sb.append("`" + tableName + "`");
/*  27 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String makeSql(Class<?> entityClass, String tableName) {
/*  31 */     StringBuilder sb = new StringBuilder();
/*  32 */     sb.append("REPLACE INTO `");
/*  33 */     sb.append(tableName);
/*  34 */     sb.append("`(");
/*  35 */     List<Field> fieldList = ClassUtil.getFields(entityClass);
/*  36 */     for (Field field : fieldList) {
/*  37 */       TableField tf = field.<TableField>getAnnotation(TableField.class);
/*  38 */       String keyName = field.getName();
/*  39 */       if (null != tf && !tf.name().isEmpty()) {
/*  40 */         keyName = tf.name();
/*     */       }
/*  42 */       if (Modifier.isTransient(field.getModifiers())) {
/*  43 */         if (null != tf && tf.isKey()) {
/*  44 */           sb.append("`").append(keyName).append("`,");
/*     */         }
/*     */         continue;
/*     */       } 
/*  48 */       sb.append("`").append(keyName).append("`,");
/*     */     } 
/*  50 */     sb.setLength(sb.length() - 1);
/*  51 */     sb.append(") VALUES ");
/*  52 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String makeSqlNotId(Class<?> entityClass, String tableName) {
/*  56 */     StringBuilder sb = new StringBuilder();
/*  57 */     sb.append("REPLACE INTO `");
/*  58 */     sb.append(tableName);
/*  59 */     sb.append("`(");
/*  60 */     List<Field> fieldList = ClassUtil.getFields(entityClass);
/*  61 */     for (Field field : fieldList) {
/*  62 */       TableField tf = field.<TableField>getAnnotation(TableField.class);
/*  63 */       String keyName = field.getName();
/*  64 */       if (null != tf && !tf.name().isEmpty()) {
/*  65 */         keyName = tf.name();
/*     */       }
/*  67 */       if (Modifier.isTransient(field.getModifiers()) && 
/*  68 */         null != tf && tf.isKey())
/*     */         continue; 
/*  70 */       sb.append("`").append(keyName).append("`,");
/*     */     } 
/*  72 */     sb.setLength(sb.length() - 1);
/*  73 */     sb.append(") VALUES ");
/*  74 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setAllFieldSql(IEntity entity, StringBuilder sb, Class<?> entityClass) {
/*  79 */     Field[] fields = entityClass.getDeclaredFields();
/*  80 */     sb.append("(");
/*  81 */     for (Field field : fields) {
/*  82 */       TableField tf = field.<TableField>getAnnotation(TableField.class);
/*  83 */       if (Modifier.isTransient(field.getModifiers())) {
/*  84 */         if (null != tf && tf.isKey()) {
/*  85 */           sb.append("'" + EntityUtil.getFieldValue(entity, field) + "',");
/*     */         }
/*     */       } else {
/*     */         
/*  89 */         sb.append("'" + EntityUtil.getFieldValue(entity, field) + "',");
/*     */       } 
/*  91 */     }  sb.setLength(sb.length() - 1);
/*  92 */     sb.append(")");
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
/*     */   public static void batchSave(Collection<EntityProxy> entityList, int batchNum, String tableName, Class<?> entityClass) {
/* 104 */     JdbcTemplate template = (JdbcTemplate)AppContext.getDAO().getTemplate();
/* 105 */     String sql = null;
/* 106 */     int count = 0;
/* 107 */     List<String> sqlList = new ArrayList<>();
/* 108 */     StringBuilder sb = new StringBuilder();
/* 109 */     for (EntityProxy proxy : entityList) {
/* 110 */       if (proxy.getEntityStatus() == IEntityProxy.ENTITY_STATUS.STATUS_MOD || proxy
/* 111 */         .getEntityStatus() == IEntityProxy.ENTITY_STATUS.STATUS_ADD) {
/* 112 */         setAllFieldSql(proxy.getEntity(), sb, entityClass);
/* 113 */         sb.append(",");
/*     */ 
/*     */ 
/*     */         
/* 117 */         proxy.reset();
/* 118 */         count++;
/* 119 */         if (count >= batchNum) {
/* 120 */           if (sql == null) sql = makeSql(entityClass, tableName); 
/* 121 */           sb.setLength(sb.length() - 1);
/* 122 */           sqlList.add(sql + sb.toString());
/* 123 */           sb.setLength(0);
/* 124 */           count = 0;
/*     */         } 
/*     */       } 
/* 127 */     }  if (sb.length() > 0) {
/* 128 */       if (sql == null) sql = makeSql(entityClass, tableName); 
/* 129 */       sb.setLength(sb.length() - 1);
/* 130 */       sqlList.add(sql + sb.toString());
/*     */     } 
/* 132 */     if (!sqlList.isEmpty())
/* 133 */       template.batchUpdate(sqlList.<String>toArray(new String[sqlList.size()])); 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\EntityProxyTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */