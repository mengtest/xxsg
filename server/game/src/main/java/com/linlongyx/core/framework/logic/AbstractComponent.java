/*     */ package com.linlongyx.core.framework.logic;
/*     */ 
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.dao.annotation.Table;
/*     */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*     */ import com.linlongyx.core.framework.dao.proxy.EntityProxy;
/*     */ import com.linlongyx.core.framework.dao.proxy.IEntityProxy;
/*     */ import com.linlongyx.core.utils.ClassUtil;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractComponent<T extends IEntity>
/*     */   implements IComponent
/*     */ {
/*     */   protected long playerId;
/*     */   protected long userId;
/*     */   protected EntityProxy proxy;
/*     */   private boolean isDBInit;
/*     */   private boolean needSaveToDB;
/*     */   
/*     */   protected AbstractComponent(Class<?> clazz) {
/*  29 */     this.proxy = new EntityProxy(clazz);
/*     */   }
/*     */ 
/*     */   
/*     */   protected AbstractComponent(Class<?> clazz, long playerId) {
/*  34 */     this.proxy = new EntityProxy(clazz);
/*     */     
/*  36 */     this.playerId = playerId;
/*  37 */     makeKey();
/*     */   }
/*     */   
/*     */   protected AbstractComponent(Class<?> clazz, long playerId, long userId) {
/*  41 */     this.proxy = new EntityProxy(clazz);
/*     */     
/*  43 */     this.playerId = playerId;
/*  44 */     this.userId = userId;
/*  45 */     makeKey();
/*     */   }
/*     */   
/*     */   protected void makeKey() {
/*  49 */     Table prefix = (Table)this.proxy.getEntityClass().getAnnotation(Table.class);
/*  50 */     String name = prefix.prefix();
/*     */     
/*  52 */     String dbKey = name.replace("%playerId", Long.toString(this.playerId)).replace("%serverId", AppContext.getServerId()).replace("%userId", Long.toString(this.userId));
/*  53 */     this.proxy.setEntityKeyId(dbKey);
/*     */   }
/*     */   
/*     */   public boolean isDBInit() {
/*  57 */     return this.isDBInit;
/*     */   }
/*     */   
/*     */   public void setDBInit(boolean isDBInit) {
/*  61 */     this.isDBInit = isDBInit;
/*     */   }
/*     */   
/*     */   public long getPlayerId() {
/*  65 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  69 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  73 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  77 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public EntityProxy getProxy() {
/*  81 */     return this.proxy;
/*     */   }
/*     */   
/*     */   public void setProxy(EntityProxy proxy) {
/*  85 */     this.proxy = proxy;
/*     */   }
/*     */   
/*     */   public T getEntity() {
/*  89 */     return (T)this.proxy.getEntity();
/*     */   }
/*     */   
/*     */   public void setUpdate(String field) {
/*  93 */     getProxy().setUpdateStatus(field);
/*     */   }
/*     */   
/*     */   public void maybeSaveToDB() {
/*  97 */     if (this.needSaveToDB)
/*  98 */       saveToDB(); 
/*     */   }
/*     */   
/*     */   public void setNeedSaveToDB(boolean needSaveToDB) {
/* 102 */     this.needSaveToDB = needSaveToDB;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveToDB() {
/* 107 */     return this.proxy.save();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveAllToDB() {
/* 112 */     return this.proxy.saveAll();
/*     */   }
/*     */   
/*     */   public void init() {
/* 116 */     if (!getFromDB() && this.playerId != 0L) {
/* 117 */       build(this.playerId);
/* 118 */       this.proxy.setEntityStatus(IEntityProxy.ENTITY_STATUS.STATUS_ADD);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFromDB() {
/* 124 */     if (this.proxy.get()) {
/* 125 */       this.isDBInit = true;
/* 126 */       return true;
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     return getClass().getSimpleName() + ":" + this.proxy.getEntity().toString() + ";<br>";
/*     */   }
/*     */ 
/*     */   
/*     */   public String setValue(String id, String fieldName, String value) {
/*     */     try {
/* 148 */       Field field = this.proxy.getEntityClass().getDeclaredField(fieldName);
/* 149 */       ClassUtil.invokeSet(this, field, value);
/* 150 */     } catch (NoSuchFieldException e) {
/* 151 */       e.printStackTrace();
/*     */     } 
/* 153 */     return toString();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\AbstractComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */