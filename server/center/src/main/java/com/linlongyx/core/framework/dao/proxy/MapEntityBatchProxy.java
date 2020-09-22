/*     */ package com.linlongyx.core.framework.dao.proxy;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.EntityUtil;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class MapEntityBatchProxy
/*     */ {
/*  23 */   private Logger log = LoggerFactory.getLogger(MapEntityBatchProxy.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private final Class<? extends IMapEntity> entityClass;
/*     */ 
/*     */ 
/*     */   
/*  31 */   private int batchMergeNum = 200;
/*     */   
/*     */   public void setBatchMergeNum(int batchMergeNum) {
/*  34 */     this.batchMergeNum = batchMergeNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   private HashMap<String, IMapEntity> entityMergeMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   private HashMap<Long, List<String>> delsMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   public MapEntityBatchProxy(Class<? extends IMapEntity> entityClass) {
/*  49 */     this.entityClass = entityClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addMergeMapEntity(IMapEntity entity, long playerId) {
/*  58 */     this.entityMergeMap.put(playerId + "_" + entity.mapKey(), entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDelMapEntity(Object key, long playerId) {
/*  67 */     List<String> list = this.delsMap.get(Long.valueOf(playerId));
/*  68 */     if (list == null) {
/*  69 */       list = new ArrayList<>();
/*  70 */       this.delsMap.put(Long.valueOf(playerId), list);
/*     */     } 
/*  72 */     list.add(key.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void batchMerge(JdbcTemplate template) {
/*  80 */     if (this.entityMergeMap.isEmpty())
/*  81 */       return;  Iterator<Map.Entry<String, IMapEntity>> iter = this.entityMergeMap.entrySet().iterator();
/*  82 */     List<IMapEntity> tempList = new LinkedList<>();
/*  83 */     while (iter.hasNext()) {
/*  84 */       Map.Entry<String, IMapEntity> entry = iter.next();
/*  85 */       tempList.add(entry.getValue());
/*  86 */       if (tempList.size() >= this.batchMergeNum) {
/*  87 */         String mergeSql = makeMergeSql(tempList);
/*  88 */         if (mergeSql != null && !"".equals(mergeSql)) template.execute(mergeSql); 
/*  89 */         tempList.clear();
/*     */       } 
/*     */     } 
/*  92 */     if (!tempList.isEmpty()) {
/*  93 */       String mergeSql = makeMergeSql(tempList);
/*  94 */       if (mergeSql != null && !"".equals(mergeSql)) template.execute(mergeSql); 
/*  95 */       tempList.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String makeDeleteSql() {
/* 101 */     StringBuilder stringBuffer = new StringBuilder();
/* 102 */     if (this.delsMap.isEmpty()) return null; 
/* 103 */     Table table = this.entityClass.<Table>getAnnotation(Table.class);
/* 104 */     stringBuffer.append("DELETE FROM `");
/* 105 */     stringBuffer.append(EntityUtil.getTableName(this.entityClass));
/* 106 */     stringBuffer.append("` WHERE `").append(table.keyField()).append("` IN (");
/* 107 */     for (List<String> delList : this.delsMap.values()) {
/* 108 */       for (String del : delList) {
/* 109 */         stringBuffer.append("'").append(del).append("',");
/*     */       }
/*     */     } 
/* 112 */     stringBuffer.setLength(stringBuffer.length() - 1);
/* 113 */     stringBuffer.append(")");
/* 114 */     stringBuffer.append(" AND `playerId` IN ('");
/* 115 */     for (Iterator<Long> iterator = this.delsMap.keySet().iterator(); iterator.hasNext(); ) { long playerId = ((Long)iterator.next()).longValue();
/* 116 */       stringBuffer.append(playerId).append(","); }
/*     */     
/* 118 */     stringBuffer.setLength(stringBuffer.length() - 1);
/* 119 */     stringBuffer.append(")");
/* 120 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String makeMergeSql(List<IMapEntity> list) {
/* 126 */     StringBuilder stringBuffer = new StringBuilder();
/* 127 */     if (list.isEmpty()) return null; 
/* 128 */     stringBuffer.append("REPLACE INTO `").append(EntityUtil.getTableName(this.entityClass)).append("` (");
/* 129 */     Field[] fields = this.entityClass.getDeclaredFields();
/* 130 */     for (Field field : fields) {
/* 131 */       String keyName = EntityUtil.getFieldName(field);
/* 132 */       if (keyName != null)
/*     */       {
/* 134 */         stringBuffer.append("`").append(keyName).append("`,"); } 
/*     */     } 
/* 136 */     stringBuffer.setLength(stringBuffer.length() - 1);
/* 137 */     stringBuffer.append(") VALUES ");
/* 138 */     for (IEntity entity : list) {
/* 139 */       if (null == entity)
/* 140 */         continue;  stringBuffer.append("(");
/* 141 */       for (Field field : fields) {
/* 142 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 143 */         if (!Modifier.isTransient(field.getModifiers()) || tf != null)
/*     */         {
/* 145 */           if (!Modifier.isTransient(field.getModifiers()) || tf.isKey()) {
/*     */             
/* 147 */             String value = EntityUtil.getFieldValue(entity, field);
/* 148 */             if (null == value) {
/* 149 */               this.log.error("error occur where read field : " + field.getName() + " class: " + this.entityClass
/* 150 */                   .getName());
/* 151 */               stringBuffer.setLength(0);
/*     */               break;
/*     */             } 
/* 154 */             stringBuffer.append("'").append(value)
/* 155 */               .append("',");
/*     */           }  } 
/* 157 */       }  stringBuffer.setLength(stringBuffer.length() - 1);
/* 158 */       stringBuffer.append("),");
/*     */     } 
/* 160 */     stringBuffer.setLength(stringBuffer.length() - 1);
/* 161 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void batchDel(JdbcTemplate template) {
/* 168 */     String deleteSql = makeDeleteSql();
/* 169 */     if (null == deleteSql || "".equals(deleteSql))
/* 170 */       return;  template.execute(deleteSql);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runBatch() {
/*     */     try {
/* 179 */       JdbcTemplate template = (JdbcTemplate)AppContext.getDAO().getTemplate();
/* 180 */       batchDel(template);
/* 181 */       batchMerge(template);
/* 182 */     } catch (Exception e) {
/* 183 */       this.log.error("", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\proxy\MapEntityBatchProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */