/*     */ package com.linlongyx.core.framework.dao.proxy;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.EntityProxyTools;
/*     */ import com.linlongyx.core.framework.dao.EntityUtil;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
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
/*     */ 
/*     */ public class EntityListProxy
/*     */ {
/*  36 */   private int batchNum = 1000;
/*     */   
/*     */   public void setBatchNum(int batchNum) {
/*  39 */     this.batchNum = batchNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Class<?> entityClass;
/*     */ 
/*     */   
/*     */   private String keyField;
/*     */   
/*     */   private String tableName;
/*     */   
/*     */   private ConcurrentHashMap<String, EntityProxy> entityMap;
/*     */ 
/*     */   
/*     */   public EntityListProxy(Class<?> entityClass) {
/*  55 */     this.entityMap = new ConcurrentHashMap<>();
/*     */     this.entityClass = entityClass;
/*     */     this.keyField = EntityUtil.getKeyField(entityClass);
/*     */     this.tableName = EntityUtil.getTableName(entityClass);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityProxy getOneEntity(String key) {
/*  63 */     return this.entityMap.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  71 */     JdbcTemplate template = (JdbcTemplate)AppContext.getDAO().getTemplate();
/*  72 */     List<Map<String, Object>> mapList = template.queryForList(EntityProxyTools.makeSelectSql(this.tableName));
/*  73 */     if (mapList == null || mapList.isEmpty())
/*  74 */       return;  mapList.forEach(map -> {
/*     */           Map<String, String> mapStr = new HashMap<>();
/*     */           for (Map.Entry<String, Object> i : (Iterable<Map.Entry<String, Object>>)map.entrySet()) {
/*     */             if (null != i.getValue()) {
/*     */               mapStr.put(i.getKey(), i.getValue().toString());
/*     */             }
/*     */           } 
/*     */           EntityProxy proxy = new EntityProxy(this.entityClass);
/*     */           proxy.createProxy(new Object[] { map.get(this.keyField) });
/*     */           EntityUtil.initEntity(this.entityClass, proxy.getEntity(), mapStr);
/*     */           proxy.reset();
/*     */           this.entityMap.put(proxy.getKeyValue(), proxy);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void batchSave() {
/*  91 */     if (this.entityMap.isEmpty())
/*  92 */       return;  EntityProxyTools.batchSave(this.entityMap.values(), this.batchNum, this.tableName, this.entityClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void batchDel() {
/*  99 */     if (this.entityMap.isEmpty())
/* 100 */       return;  StringBuilder sb = new StringBuilder();
/* 101 */     Iterator<Map.Entry<String, EntityProxy>> iterator = this.entityMap.entrySet().iterator();
/* 102 */     while (iterator.hasNext()) {
/* 103 */       Map.Entry<String, EntityProxy> next = iterator.next();
/* 104 */       EntityProxy entityProxy = next.getValue();
/* 105 */       if (entityProxy.entityStatus != IEntityProxy.ENTITY_STATUS.STATUS_DEL)
/* 106 */         continue;  sb.append("" + (String)next.getKey() + ",");
/* 107 */       iterator.remove();
/*     */     } 
/* 109 */     if (sb.length() <= 0)
/* 110 */       return;  sb.setLength(sb.length() - 1);
/* 111 */     String deleteSql = makeDeleteSql();
/* 112 */     deleteSql = deleteSql + sb.toString() + ")";
/* 113 */     JdbcTemplate template = (JdbcTemplate)AppContext.getDAO().getTemplate();
/* 114 */     template.execute(deleteSql);
/*     */   }
/*     */   
/*     */   private String makeDeleteSql() {
/* 118 */     StringBuilder stringBuilder = new StringBuilder();
/* 119 */     stringBuilder.append("DELETE FROM `");
/* 120 */     stringBuilder.append(this.tableName);
/* 121 */     stringBuilder.append("` WHERE ").append("`" + this.keyField + "`").append(" in (");
/* 122 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntity(EntityProxy proxy) {
/* 131 */     this.entityMap.put(proxy.getKeyValue(), proxy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IEntity createEntity(long id) {
/* 139 */     EntityProxy proxy = new EntityProxy(this.entityClass, "", IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/* 140 */     proxy.createProxy(new Object[] { Long.valueOf(id) });
/* 141 */     this.entityMap.put(id + "", proxy);
/* 142 */     return proxy.getEntity();
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<String, EntityProxy> getEntityMap() {
/* 146 */     return this.entityMap;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\proxy\EntityListProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */