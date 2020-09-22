/*     */ package com.linlongyx.core.framework.dao.proxy;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.EntityUtil;
/*     */ import com.linlongyx.core.framework.dao.IDAO;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.annotation.TableField;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.redis.RedisClientTemplate;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentSkipListSet;
/*     */ import net.sf.cglib.proxy.Callback;
/*     */ import net.sf.cglib.proxy.Enhancer;
/*     */ import net.sf.cglib.proxy.MethodInterceptor;
/*     */ import net.sf.cglib.proxy.MethodProxy;
/*     */ import org.springframework.dao.DataAccessException;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityProxy
/*     */   implements IEntityProxy, MethodInterceptor
/*     */ {
/*     */   protected String entityKeyId;
/*     */   protected volatile IEntityProxy.ENTITY_STATUS entityStatus;
/*     */   private Class<?> entityClass;
/*     */   private IEntity proxyEntity;
/*  38 */   private StringBuffer stringBuffer = new StringBuffer();
/*     */ 
/*     */   
/*  41 */   private ConcurrentSkipListSet<String> updates = new ConcurrentSkipListSet<>();
/*     */   
/*     */   public void createProxy(Object... key) {
/*  44 */     Enhancer enhancer = new Enhancer();
/*  45 */     enhancer.setSuperclass(this.entityClass);
/*  46 */     enhancer.setCallback((Callback)this);
/*  47 */     enhancer.setClassLoader(this.entityClass.getClassLoader());
/*  48 */     if (key.length == 0) {
/*  49 */       this.proxyEntity = (IEntity)enhancer.create();
/*     */     } else {
/*  51 */       this.proxyEntity = (IEntity)enhancer.create(new Class[] {
/*  52 */             EntityUtil.getKeyType(getEntityClass()) }, key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EntityProxy() {
/*  57 */     this.entityKeyId = "";
/*  58 */     this.entityStatus = IEntityProxy.ENTITY_STATUS.STATUS_NON;
/*     */   }
/*     */   
/*     */   public EntityProxy(Class<?> clazz) {
/*  62 */     this.entityKeyId = "";
/*  63 */     this.entityStatus = IEntityProxy.ENTITY_STATUS.STATUS_NON;
/*  64 */     this.entityClass = clazz;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityProxy(Class<?> entityClass, String keyId) {
/*  69 */     this.entityKeyId = keyId;
/*  70 */     this.entityStatus = IEntityProxy.ENTITY_STATUS.STATUS_NON;
/*  71 */     this.entityClass = entityClass;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityProxy(Class<?> entityClass, String keyId, IEntityProxy.ENTITY_STATUS status) {
/*  76 */     this.entityKeyId = keyId;
/*  77 */     this.entityStatus = status;
/*  78 */     this.entityClass = entityClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
/*  86 */     Object result = methodProxy.invokeSuper(o, args);
/*     */     
/*  88 */     String methodStr = method.getName();
/*     */ 
/*     */     
/*  91 */     doAfter(methodStr);
/*  92 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEntityKeyId() {
/*  97 */     return this.entityKeyId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityKeyId(String entityKeyId) {
/* 102 */     this.entityKeyId = entityKeyId;
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityProxy.ENTITY_STATUS getEntityStatus() {
/* 107 */     return this.entityStatus;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityStatus(IEntityProxy.ENTITY_STATUS entityStatus) {
/* 112 */     updateEntityStatus(entityStatus);
/*     */   }
/*     */   
/*     */   private void updateEntityStatus(IEntityProxy.ENTITY_STATUS status) {
/* 116 */     if (this.entityStatus.getIndex() > status.getIndex())
/* 117 */       this.entityStatus = status; 
/*     */   }
/*     */   
/*     */   public IEntity getEntity() {
/* 121 */     return this.proxyEntity;
/*     */   }
/*     */   
/*     */   public Class<?> getEntityClass() {
/* 125 */     return this.entityClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 132 */     this.stringBuffer.setLength(0);
/* 133 */     this.updates.clear();
/* 134 */     this.entityStatus = IEntityProxy.ENTITY_STATUS.STATUS_NON;
/*     */   }
/*     */   
/*     */   public void setUpdateStatus(String field) {
/* 138 */     this.updates.add(field);
/* 139 */     setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_MOD);
/*     */   }
/*     */   
/*     */   private void init(Map<String, String> initInfo) {
/* 143 */     if (initInfo.isEmpty())
/*     */       return; 
/* 145 */     EntityUtil.initEntity(getEntityClass(), this.proxyEntity, initInfo);
/*     */     
/* 147 */     reset();
/*     */   }
/*     */ 
/*     */   
/*     */   private void doAfter(String methodName) {
/* 152 */     if (methodName.substring(0, 3).toUpperCase().equals("SET")) {
/* 153 */       String attribName = EntityUtil.getAttrByMethod(methodName);
/*     */       try {
/* 155 */         if (null == getEntityClass().getDeclaredField(attribName))
/*     */           return; 
/* 157 */         this.updates.add(attribName);
/* 158 */         updateEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_MOD);
/* 159 */       } catch (NoSuchFieldException noSuchFieldException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, String> getUpdateMap() {
/* 166 */     Map<String, String> map = new HashMap<>();
/* 167 */     Class<?> entityClass = getEntityClass();
/*     */     
/*     */     try {
/* 170 */       for (String str : this.updates) {
/* 171 */         if (!EntityUtil.isInField(str, entityClass))
/*     */           continue; 
/* 173 */         Field field = entityClass.getDeclaredField(str);
/* 174 */         if (Modifier.isTransient(field.getModifiers()))
/*     */           continue; 
/* 176 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/*     */         
/* 178 */         String keyName = field.getName();
/* 179 */         if (null != tf && !tf.name().isEmpty()) {
/* 180 */           keyName = tf.name();
/*     */         }
/*     */         
/* 183 */         String getMethodName = EntityUtil.getGetterName(field);
/* 184 */         Method getMethod = entityClass.getMethod(getMethodName, new Class[0]);
/*     */         
/* 186 */         Object value = getMethod.invoke(this.proxyEntity, new Object[0]);
/* 187 */         map.put(keyName, EntityUtil.generateTypeString(field.getType(), value));
/*     */       } 
/* 189 */     } catch (NoSuchFieldException|NoSuchMethodException|java.lang.reflect.InvocationTargetException|IllegalAccessException e) {
/* 190 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 193 */     return map;
/*     */   }
/*     */   
/*     */   private Map<String, String> getAllFieldMap() {
/* 197 */     Map<String, String> map = new HashMap<>();
/*     */     
/* 199 */     Class<?> entityClass = getEntityClass();
/*     */     
/* 201 */     Field[] fields = entityClass.getDeclaredFields();
/*     */     
/* 203 */     for (Field field : fields) {
/* 204 */       String keyName = field.getName();
/* 205 */       TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 206 */       if (null != tf && !tf.name().isEmpty()) {
/* 207 */         keyName = tf.name();
/*     */       }
/* 209 */       if (Modifier.isTransient(field.getModifiers())) {
/* 210 */         if (null != tf && tf.isKey()) {
/* 211 */           map.put(keyName, EntityUtil.getFieldValue(this.proxyEntity, field));
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 216 */         map.put(keyName, EntityUtil.getFieldValue(this.proxyEntity, field));
/*     */       } 
/* 218 */     }  return map;
/*     */   }
/*     */   
/*     */   private Map<String, String> getAllMap() {
/* 222 */     Map<String, String> map = new HashMap<>();
/*     */     
/* 224 */     Class<?> entityClass = getEntityClass();
/*     */     
/* 226 */     Field[] fields = entityClass.getDeclaredFields();
/*     */     
/* 228 */     for (Field field : fields) {
/* 229 */       String keyName = field.getName();
/* 230 */       if (!Modifier.isTransient(field.getModifiers())) {
/*     */         
/* 232 */         TableField tf = field.<TableField>getAnnotation(TableField.class);
/* 233 */         if (null != tf && !tf.name().isEmpty()) {
/* 234 */           keyName = tf.name();
/*     */         }
/* 236 */         map.put(keyName, EntityUtil.getFieldValue(this.proxyEntity, field));
/*     */       } 
/* 238 */     }  return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTableName() {
/* 244 */     Table mysqlTableName = this.entityClass.<Table>getAnnotation(Table.class);
/* 245 */     String name = "";
/* 246 */     if (null != mysqlTableName) {
/* 247 */       name = mysqlTableName.tableName();
/*     */     } else {
/* 249 */       name = this.entityClass.getName().substring(this.entityClass.getName().lastIndexOf(".") + 1);
/* 250 */     }  return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyName() {
/* 255 */     return EntityUtil.getKeyField(getEntityClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKeyValue() {
/* 260 */     String keyName = EntityUtil.getKeyField(getEntityClass());
/* 261 */     return EntityUtil.getFieldValue(getEntityClass(), getEntity(), keyName);
/*     */   }
/*     */ 
/*     */   
/*     */   private String makeSelectSql() {
/* 266 */     this.stringBuffer.setLength(0);
/* 267 */     this.stringBuffer.append("SELECT * FROM `");
/* 268 */     this.stringBuffer.append(getTableName());
/* 269 */     this.stringBuffer.append("` WHERE ");
/* 270 */     this.stringBuffer.append(getKeyName());
/* 271 */     this.stringBuffer.append(" = '");
/* 272 */     this.stringBuffer.append(getKeyValue()).append("'");
/*     */     
/* 274 */     return this.stringBuffer.toString();
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
/*     */   private String makeUpdateSql() {
/* 299 */     this.stringBuffer.setLength(0);
/* 300 */     this.stringBuffer.append("UPDATE `");
/* 301 */     this.stringBuffer.append(getTableName());
/* 302 */     this.stringBuffer.append("` SET ");
/* 303 */     Map<String, String> map = getUpdateMap();
/* 304 */     String tempStr = "";
/* 305 */     for (Map.Entry<String, String> entry : map.entrySet()) {
/* 306 */       tempStr = tempStr + (String)entry.getKey() + "='" + (String)entry.getValue() + "',";
/*     */     }
/*     */     
/* 309 */     this.stringBuffer.append(tempStr.substring(0, tempStr.length() - 1));
/* 310 */     this.stringBuffer.append(" WHERE ").append(getKeyName()).append("= '").append(getKeyValue()).append("'");
/* 311 */     return this.stringBuffer.toString();
/*     */   }
/*     */   
/*     */   private String makeUpdateAllSql() {
/* 315 */     this.stringBuffer.setLength(0);
/* 316 */     this.stringBuffer.append("REPLACE INTO `");
/* 317 */     this.stringBuffer.append(getTableName());
/* 318 */     this.stringBuffer.append("` (");
/* 319 */     Map<String, String> map = getAllFieldMap();
/* 320 */     String tempStr = ") VALUES (";
/* 321 */     for (Map.Entry<String, String> entry : map.entrySet()) {
/* 322 */       if (null == entry.getValue()) { tempStr = tempStr + " '',"; }
/* 323 */       else { tempStr = tempStr + " '" + (String)entry.getValue() + "',"; }
/* 324 */        this.stringBuffer.append(entry.getKey());
/* 325 */       this.stringBuffer.append(",");
/*     */     } 
/*     */     
/* 328 */     this.stringBuffer.setLength(this.stringBuffer.length() - 1);
/* 329 */     this.stringBuffer.append(tempStr.substring(0, tempStr.length() - 1));
/* 330 */     this.stringBuffer.append(")");
/* 331 */     return this.stringBuffer.toString();
/*     */   }
/*     */   
/*     */   private String makeDeleteSql() {
/* 335 */     this.stringBuffer.setLength(0);
/* 336 */     this.stringBuffer.append("DELETE FROM `");
/* 337 */     this.stringBuffer.append(getTableName());
/* 338 */     this.stringBuffer.append("` WHERE ").append(getKeyName()).append(" = '").append(getKeyValue()).append("'");
/* 339 */     return this.stringBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveAll() {
/* 344 */     return save(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean save() {
/* 349 */     return save(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized boolean save(boolean isAll) {
/* 358 */     IDAO dao = AppContext.getDAO();
/* 359 */     if (dao.getType() == IDAO.TYPE.REDIS) {
/* 360 */       return saveRE(dao, isAll);
/*     */     }
/*     */     
/* 363 */     return saveMY(dao, isAll);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean get() {
/* 369 */     IDAO dao = AppContext.getDAO();
/* 370 */     if (dao.getType() == IDAO.TYPE.REDIS) {
/* 371 */       return getRE(dao);
/*     */     }
/*     */     
/* 374 */     return getMY(dao);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean saveMY(IDAO dao, boolean isAll) {
/* 380 */     IEntityProxy.ENTITY_STATUS status = getEntityStatus();
/* 381 */     JdbcTemplate template = (JdbcTemplate)dao.getTemplate();
/*     */     
/*     */     try {
/* 384 */       if (isAll) {
/* 385 */         template.update(makeUpdateAllSql());
/* 386 */         reset();
/* 387 */         return true;
/*     */       } 
/*     */       
/* 390 */       switch (status) {
/*     */         case STATUS_DEL:
/* 392 */           template.update(makeDeleteSql());
/*     */           break;
/*     */         case STATUS_ADD:
/* 395 */           template.update(makeUpdateAllSql());
/*     */           break;
/*     */         case STATUS_MOD:
/* 398 */           template.update(makeUpdateSql());
/*     */           break;
/*     */         case STATUS_NON:
/*     */           break;
/*     */         default:
/* 403 */           return false;
/*     */       } 
/* 405 */       reset();
/*     */     }
/* 407 */     catch (Exception e) {
/* 408 */       LogUtils.errorLog(new Object[] { "EntityProxy::saveMY", e.getMessage(), e });
/* 409 */       return false;
/*     */     } 
/* 411 */     return true;
/*     */   }
/*     */   
/*     */   private boolean saveRE(IDAO dao, boolean isAll) {
/* 415 */     Map<String, String> info = getUpdateMap();
/* 416 */     if (isAll)
/* 417 */       info = getAllMap(); 
/* 418 */     if (info.isEmpty()) {
/* 419 */       reset();
/* 420 */       return true;
/*     */     } 
/* 422 */     IEntityProxy.ENTITY_STATUS status = getEntityStatus();
/* 423 */     String key = getEntityKeyId();
/* 424 */     RedisClientTemplate template = (RedisClientTemplate)dao.getTemplate();
/*     */     
/* 426 */     switch (status) {
/*     */       case STATUS_DEL:
/* 428 */         template.del(key);
/*     */       
/*     */       case STATUS_ADD:
/* 431 */         template.hmset(key, info);
/*     */       
/*     */       case STATUS_MOD:
/* 434 */         template.hmset(key, info);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case STATUS_NON:
/* 441 */         reset();
/* 442 */         return true;
/*     */     } 
/*     */     return false;
/*     */   } private boolean getMY(IDAO dao) {
/* 446 */     JdbcTemplate template = (JdbcTemplate)dao.getTemplate();
/* 447 */     Map<String, Object> info = null;
/*     */     try {
/* 449 */       info = template.queryForMap(makeSelectSql());
/* 450 */       if (null == info || info.isEmpty()) {
/* 451 */         return false;
/*     */       }
/* 453 */       Map<String, String> map = new HashMap<>();
/* 454 */       for (Map.Entry<String, Object> i : info.entrySet()) {
/* 455 */         if (null != i.getValue()) map.put(i.getKey(), i.getValue().toString()); 
/*     */       } 
/* 457 */       init(map);
/* 458 */       return true;
/* 459 */     } catch (DataAccessException dataAccessException) {
/*     */ 
/*     */       
/* 462 */       return false;
/*     */     } 
/*     */   }
/*     */   private boolean getRE(IDAO dao) {
/* 466 */     RedisClientTemplate template = (RedisClientTemplate)dao.getTemplate();
/* 467 */     String keyId = getEntityKeyId();
/* 468 */     Map<String, String> info = template.hgetAll(keyId);
/* 469 */     if (null == info || info.isEmpty())
/* 470 */       return false; 
/* 471 */     init(info);
/* 472 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\dao\proxy\EntityProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */