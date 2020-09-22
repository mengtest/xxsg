/*     */ package com.linlongyx.core.framework.dao.proxy;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.EntityUtil;
/*     */ import com.linlongyx.core.framework.dao.IDAO;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import net.sf.cglib.proxy.Callback;
/*     */ import net.sf.cglib.proxy.Enhancer;
/*     */ import net.sf.cglib.proxy.MethodInterceptor;
/*     */ import net.sf.cglib.proxy.MethodProxy;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class MapProxy implements IMapProxy, MethodInterceptor {
/*  32 */   private static final Logger LOG = LoggerFactory.getLogger(MapProxy.class);
/*     */   
/*     */   protected String entityKeyId;
/*     */   
/*     */   private Long playerId;
/*     */   
/*     */   private Class<?> entityClass;
/*  39 */   private ReentrantLock lock = new ReentrantLock();
/*  40 */   private Map<String, IMapEntity> proxyMapEntity = new ConcurrentHashMap<>();
/*  41 */   private Set<String> adds = new HashSet<>();
/*  42 */   private Set<String> dels = new HashSet<>();
/*  43 */   private ConcurrentHashMap<String, Set<String>> updates = new ConcurrentHashMap<>();
/*     */   
/*  45 */   private StringBuffer stringBuffer = new StringBuffer();
/*     */   
/*     */   public MapProxy(Class<?> clazz) {
/*  48 */     this.entityKeyId = "";
/*  49 */     this.entityClass = clazz;
/*     */   }
/*     */   
/*     */   public MapProxy(Class<? extends IMapEntity> clazz, long playerId) {
/*  53 */     this.playerId = Long.valueOf(playerId);
/*  54 */     this.entityClass = clazz;
/*     */   }
/*     */   
/*     */   public MapProxy(Class<? extends IMapEntity> clazz, String key) {
/*  58 */     this.entityKeyId = key;
/*  59 */     this.entityClass = clazz;
/*     */   }
/*     */   
/*     */   public IMapEntity createProxy(Object key) {
/*  63 */     Enhancer enhancer = new Enhancer();
/*  64 */     enhancer.setSuperclass(getEntityClass());
/*  65 */     enhancer.setCallback((Callback)this);
/*  66 */     enhancer.setClassLoader(getEntityClass().getClassLoader());
/*  67 */     if (EntityUtil.isPlayerIdKey(getEntityClass())) {
/*  68 */       return (IMapEntity)enhancer.create(new Class[] { long.class, 
/*  69 */             EntityUtil.getKeyType(getEntityClass()) }, new Object[] {
/*  70 */             Long.valueOf(getPlayerId()), key
/*     */           });
/*     */     }
/*  73 */     return (IMapEntity)enhancer.create(new Class[] { EntityUtil.getKeyType(getEntityClass()) }, new Object[] { key });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntityKeyId() {
/*  79 */     return this.entityKeyId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityKeyId(String entityKeyId) {
/*  84 */     this.entityKeyId = entityKeyId;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  88 */     return this.playerId.longValue();
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  92 */     this.playerId = Long.valueOf(playerId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  97 */     this.adds.clear();
/*  98 */     this.dels.clear();
/*  99 */     this.updates.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public Class<?> getEntityClass() {
/* 104 */     return this.entityClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntity(IMapEntity entity) {
/*     */     try {
/* 113 */       this.lock.lock();
/* 114 */       Object key = entity.mapKey();
/* 115 */       if (null == this.proxyMapEntity.put(key.toString(), entity)) {
/* 116 */         this.adds.add(key.toString());
/* 117 */         this.updates.remove(key.toString());
/*     */       } 
/*     */     } finally {
/* 120 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void delEntity(IMapEntity entity) {
/* 125 */     String key = entity.mapKey().toString();
/* 126 */     delEntity(key);
/*     */   }
/*     */   
/*     */   public void delEntity(String id) {
/*     */     try {
/* 131 */       this.lock.lock();
/* 132 */       if (null != this.proxyMapEntity.remove(id)) {
/* 133 */         this.dels.add(id);
/* 134 */         this.adds.remove(id);
/*     */       } 
/*     */     } finally {
/* 137 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveAll() {
/* 143 */     return save(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save() {
/* 148 */     IDAO dao = AppContext.getDAO();
/* 149 */     if (dao.getType() == IDAO.TYPE.REDIS) {
/* 150 */       return saveRE(dao, false);
/*     */     }
/* 152 */     return save(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean get() {
/* 157 */     IDAO dao = AppContext.getDAO();
/* 158 */     if (dao.getType() == IDAO.TYPE.REDIS) {
/* 159 */       return getRE(dao);
/*     */     }
/* 161 */     return getMY(dao);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean save(boolean isAll) {
/*     */     try {
/* 167 */       this.lock.lock();
/* 168 */       IDAO dao = AppContext.getDAO();
/* 169 */       if (dao.getType() == IDAO.TYPE.REDIS) {
/* 170 */         return saveRE(dao, isAll);
/*     */       }
/* 172 */       return saveMY(dao, isAll);
/*     */     } finally {
/* 174 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private Map<String, String> getUpdateMap() {
/* 179 */     Map<String, String> map = new HashMap<>();
/* 180 */     for (String key : this.updates.keySet()) {
/* 181 */       if (this.proxyMapEntity.containsKey(key)) {
/* 182 */         map.put(key, GsonUtil.toJson(this.proxyMapEntity.get(key), getEntityClass()));
/*     */       }
/*     */     } 
/*     */     
/* 186 */     for (String key : this.adds) {
/* 187 */       if (this.proxyMapEntity.containsKey(key)) {
/* 188 */         map.put(key, GsonUtil.toJson(this.proxyMapEntity.get(key), getEntityClass()));
/*     */       }
/*     */     } 
/* 191 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean getRE(IDAO dao) {
/* 198 */     String key = getEntityKeyId();
/* 199 */     RedisClientTemplate template = (RedisClientTemplate)dao.getTemplate();
/* 200 */     Map<String, String> map = template.hgetAll(key);
/* 201 */     if (null == map || map.isEmpty())
/* 202 */       return false; 
/* 203 */     initRE(map);
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   private boolean getMY(IDAO dao) {
/* 208 */     JdbcTemplate template = (JdbcTemplate)dao.getTemplate();
/* 209 */     List<Map<String, Object>> info = template.queryForList(makeSelectSql());
/* 210 */     if (info.isEmpty()) {
/* 211 */       return false;
/*     */     }
/* 213 */     initMY(info);
/* 214 */     return true;
/*     */   }
/*     */   
/*     */   private boolean saveRE(IDAO dao, boolean isAll) {
/* 218 */     String key = getEntityKeyId();
/* 219 */     RedisClientTemplate template = (RedisClientTemplate)dao.getTemplate();
/*     */     
/* 221 */     for (String del : this.dels) {
/* 222 */       template.hdel(key, del);
/*     */     }
/*     */     
/* 225 */     Map<String, String> info = getUpdateMap();
/* 226 */     if (info.isEmpty())
/* 227 */       return true; 
/* 228 */     template.hmset(key, info);
/* 229 */     reset();
/* 230 */     return true;
/*     */   }
/*     */   
/*     */   private boolean saveMY(IDAO dao, boolean isAll) {
/* 234 */     JdbcTemplate template = (JdbcTemplate)dao.getTemplate();
/* 235 */     List<String> sqlList = new ArrayList<>();
/*     */     try {
/* 237 */       this.lock.lock();
/* 238 */       String sql = makeDeleteSql();
/* 239 */       if (null != sql) {
/* 240 */         sqlList.add(sql);
/*     */       }
/* 242 */       if (isAll) {
/* 243 */         makeInsertAllSql(sqlList);
/*     */       } else {
/* 245 */         sql = makeInsertSql();
/* 246 */         if (null != sql)
/* 247 */           sqlList.add(sql); 
/* 248 */         sqlList.addAll(makeUpdateSql());
/*     */       } 
/* 250 */       reset();
/*     */     } finally {
/* 252 */       this.lock.unlock();
/*     */     } 
/*     */     try {
/* 255 */       int size = sqlList.size();
/* 256 */       if (size != 0) {
/* 257 */         template.batchUpdate(sqlList.<String>toArray(new String[size]));
/*     */       }
/* 259 */     } catch (Exception e) {
/* 260 */       LogUtils.errorLog(new Object[] { "MapProxy::saveMY", e.getMessage(), e });
/* 261 */       return false;
/*     */     } 
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initRE(Map<String, String> initInfo) {
/* 272 */     if (null == initInfo)
/*     */       return; 
/* 274 */     for (Map.Entry<String, String> info : initInfo.entrySet()) {
/* 275 */       IMapEntity sourceEntity = (IMapEntity)GsonUtil.fromJson(info.getValue(), getEntityClass());
/*     */       
/* 277 */       IMapEntity entity = createProxy(EntityUtil.generateTypeClass(EntityUtil.getKeyType(getEntityClass()), info
/* 278 */             .getKey(), EntityUtil.getKeyType(getEntityClass())));
/*     */       
/* 280 */       EntityUtil.initMapEntity(getEntityClass(), entity, sourceEntity);
/* 281 */       this.proxyMapEntity.put(entity.mapKey().toString(), entity);
/*     */     } 
/* 283 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initMY(List<Map<String, Object>> initInfo) {
/* 291 */     for (Map<String, Object> info : initInfo) {
/* 292 */       Object keyValue = info.get(EntityUtil.getKeyField(getEntityClass()));
/* 293 */       if (null == keyValue) {
/*     */         continue;
/*     */       }
/* 296 */       Table table = getEntityClass().<Table>getAnnotation(Table.class);
/* 297 */       Field field = null;
/*     */       try {
/* 299 */         field = getEntityClass().getDeclaredField(table.keyField());
/* 300 */         IMapEntity entity = createProxy(EntityUtil.generateTypeClass(field.getType(), keyValue.toString(), field.getGenericType()));
/* 301 */         EntityUtil.initMapEntity(getEntityClass(), entity, info);
/* 302 */         this.proxyMapEntity.put(entity.mapKey().toString(), entity);
/* 303 */       } catch (NoSuchFieldException e) {
/* 304 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 309 */     reset();
/*     */   }
/*     */   
/*     */   public String makeSelectSql() {
/* 313 */     this.stringBuffer.setLength(0);
/* 314 */     this.stringBuffer.append("SELECT * FROM `");
/* 315 */     this.stringBuffer.append(getTableName()).append("`");
/*     */     
/* 317 */     if (EntityUtil.isPlayerIdKey(getEntityClass()))
/* 318 */       this.stringBuffer.append(" WHERE `playerId` = '")
/* 319 */         .append(this.playerId).append("'"); 
/* 320 */     return this.stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private String makeInsertSql() {
/* 325 */     this.stringBuffer.setLength(0);
/* 326 */     if (this.adds.isEmpty()) {
/* 327 */       return null;
/*     */     }
/* 329 */     this.stringBuffer.append("REPLACE INTO `").append(getTableName()).append("` (");
/*     */     
/* 331 */     Field[] fields = getEntityClass().getDeclaredFields();
/* 332 */     for (Field field : fields) {
/* 333 */       String keyName = EntityUtil.getFieldName(field);
/* 334 */       if (keyName != null)
/*     */       {
/* 336 */         this.stringBuffer.append("`").append(keyName).append("`,"); } 
/*     */     } 
/* 338 */     this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 339 */     this.stringBuffer.append(") VALUES ");
/*     */     
/* 341 */     for (String add : this.adds) {
/* 342 */       IEntity entity = (IEntity)this.proxyMapEntity.get(add);
/* 343 */       if (null == entity)
/*     */         continue; 
/* 345 */       this.stringBuffer.append("(");
/* 346 */       for (Field field : fields) {
/* 347 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 348 */         if (!Modifier.isTransient(field.getModifiers()) || tf != null)
/*     */         {
/* 350 */           if (!Modifier.isTransient(field.getModifiers()) || tf.isKey()) {
/*     */             
/* 352 */             String value = EntityUtil.getFieldValue(entity, field);
/* 353 */             if (null == value) {
/* 354 */               LOG.error("error occur where read field : " + field.getName() + " class: " + this.entityClass
/* 355 */                   .getName());
/* 356 */               this.stringBuffer.setLength(0);
/*     */               
/*     */               break;
/*     */             } 
/* 360 */             this.stringBuffer.append("'").append(value)
/* 361 */               .append("',");
/*     */           }  } 
/* 363 */       }  this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 364 */       this.stringBuffer.append("),");
/*     */     } 
/* 366 */     this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 367 */     return this.stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private void makeInsertAllSql(List<String> sqlList) {
/* 372 */     this.stringBuffer.setLength(0);
/* 373 */     this.stringBuffer.append("REPLACE INTO `").append(getTableName()).append("` (");
/*     */     
/* 375 */     Field[] fields = getEntityClass().getDeclaredFields();
/* 376 */     for (Field field : fields) {
/* 377 */       String keyName = EntityUtil.getFieldName(field);
/* 378 */       if (null != keyName)
/*     */       {
/* 380 */         this.stringBuffer.append("`").append(keyName).append("`,");
/*     */       }
/*     */     } 
/* 383 */     this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 384 */     this.stringBuffer.append(") VALUES ");
/* 385 */     boolean isUpdate = false;
/* 386 */     for (IEntity entity : this.proxyMapEntity.values()) {
/* 387 */       this.stringBuffer.append("(");
/* 388 */       for (Field field : fields) {
/* 389 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 390 */         if (!Modifier.isTransient(field.getModifiers()) || tf != null)
/*     */         {
/* 392 */           if (!Modifier.isTransient(field.getModifiers()) || tf.isKey()) {
/*     */             
/* 394 */             String value = EntityUtil.getFieldValue(entity, field);
/* 395 */             if (null == value) {
/* 396 */               LOG.error("error occur where read field : " + field.getName() + " class: " + this.entityClass
/* 397 */                   .getName());
/* 398 */               this.stringBuffer.setLength(0);
/*     */               
/*     */               return;
/*     */             } 
/* 402 */             this.stringBuffer.append("'").append(value)
/* 403 */               .append("',");
/*     */           }  } 
/* 405 */       }  isUpdate = true;
/* 406 */       this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 407 */       this.stringBuffer.append("),");
/*     */     } 
/* 409 */     this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 410 */     if (isUpdate)
/* 411 */       sqlList.add(this.stringBuffer.toString()); 
/*     */   }
/*     */   
/*     */   private List<String> makeUpdateSql() {
/* 415 */     List<String> list = new ArrayList<>();
/* 416 */     if (this.updates.isEmpty())
/* 417 */       return list; 
/* 418 */     Table table = getEntityClass().<Table>getAnnotation(Table.class);
/*     */     
/* 420 */     for (Map.Entry<String, Set<String>> update : this.updates.entrySet()) {
/* 421 */       this.stringBuffer.setLength(0);
/* 422 */       IMapEntity entity = this.proxyMapEntity.get(update.getKey());
/* 423 */       if (null == entity)
/*     */         continue; 
/* 425 */       this.stringBuffer.append("UPDATE `").append(getTableName()).append("` SET ");
/* 426 */       for (String attrib : update.getValue()) {
/* 427 */         this.stringBuffer.append("`").append(attrib).append("` = '")
/* 428 */           .append(EntityUtil.getFieldValue(getEntityClass(), (IEntity)entity, attrib)).append("',");
/*     */       }
/* 430 */       this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 431 */       this.stringBuffer.append(" WHERE `").append(table.keyField())
/* 432 */         .append("` = '").append(entity.mapKey()).append("'");
/* 433 */       if (table.isPlayerIdKey()) {
/* 434 */         this.stringBuffer.append(" AND `playerId` = '")
/* 435 */           .append(this.playerId).append("'");
/*     */       }
/* 437 */       list.add(this.stringBuffer.toString());
/*     */     } 
/* 439 */     return list;
/*     */   }
/*     */   
/*     */   private String makeDeleteSql() {
/* 443 */     this.stringBuffer.setLength(0);
/* 444 */     if (this.dels.isEmpty()) {
/* 445 */       return null;
/*     */     }
/* 447 */     Table table = getEntityClass().<Table>getAnnotation(Table.class);
/* 448 */     this.stringBuffer.append("DELETE FROM `");
/* 449 */     this.stringBuffer.append(getTableName());
/* 450 */     this.stringBuffer.append("` WHERE `").append(table.keyField()).append("` IN (");
/*     */     
/* 452 */     for (String del : this.dels) {
/* 453 */       this.stringBuffer.append("'").append(del).append("',");
/*     */     }
/* 455 */     this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 456 */     this.stringBuffer.append(")");
/* 457 */     if (table.isPlayerIdKey()) {
/* 458 */       this.stringBuffer.append(" AND `playerId` = '")
/* 459 */         .append(this.playerId).append("'");
/*     */     }
/*     */     
/* 462 */     return this.stringBuffer.toString();
/*     */   }
/*     */   
/*     */   public Map<String, IMapEntity> getEntityMap() {
/* 466 */     return this.proxyMapEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public IMapEntity getEntity(String key) {
/* 471 */     return this.proxyMapEntity.get(key);
/*     */   }
/*     */   
/*     */   public String getTableName() {
/* 475 */     Class<?> entityClass = this.entityClass;
/* 476 */     Table tableName = entityClass.<Table>getAnnotation(Table.class);
/* 477 */     String name = "";
/* 478 */     if (null != tableName) {
/* 479 */       name = tableName.tableName();
/*     */     } else {
/* 481 */       name = entityClass.getName().substring(entityClass.getName().lastIndexOf(".") + 1);
/* 482 */     }  return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
/* 489 */     Object result = methodProxy.invokeSuper(o, args);
/*     */ 
/*     */     
/* 492 */     doAfter(o, method);
/*     */     
/* 494 */     return result;
/*     */   }
/*     */   
/*     */   private void doAfter(Object o, Method method) {
/* 498 */     String methodName = method.getName();
/* 499 */     if (!methodName.substring(0, 3).toUpperCase().equals("SET")) {
/*     */       return;
/*     */     }
/* 502 */     String attribName = EntityUtil.getAttrByMethod(methodName);
/*     */     
/* 504 */     Object value = ((IMapEntity)o).mapKey();
/* 505 */     setUpdate(value.toString(), attribName);
/*     */   }
/*     */   
/*     */   public void setUpdate(String key, String field) {
/*     */     try {
/* 510 */       this.lock.lock();
/* 511 */       if (this.adds.contains(key) || this.dels.contains(key))
/*     */         return; 
/* 513 */       if (this.updates.containsKey(key)) {
/* 514 */         ((Set<String>)this.updates.get(key)).add(field);
/*     */         return;
/*     */       } 
/* 517 */       Set<String> set = new HashSet<>();
/* 518 */       set.add(field);
/* 519 */       this.updates.put(key, set);
/* 520 */     } catch (NullPointerException e) {
/* 521 */       LOG.error("MapProxy::setUpdate error key: " + field + ", keyId: " + this.entityKeyId);
/*     */     } finally {
/* 523 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> getAdds() {
/* 528 */     return this.adds;
/*     */   }
/*     */   
/*     */   public Set<String> getDels() {
/* 532 */     return this.dels;
/*     */   }
/*     */   
/*     */   public ConcurrentHashMap<String, Set<String>> getUpdates() {
/* 536 */     return this.updates;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\core\framework\dao\proxy\MapProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */