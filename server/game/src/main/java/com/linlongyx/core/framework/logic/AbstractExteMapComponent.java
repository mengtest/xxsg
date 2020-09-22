/*     */ package com.linlongyx.core.framework.logic;
/*     */ 
/*     */ import com.linlongyx.core.framework.dao.entity.IExteMapEntity;
/*     */ import com.linlongyx.core.framework.dao.proxy.ExteMapProxy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractExteMapComponent<T extends IExteMapEntity>
/*     */   implements IComponent
/*     */ {
/*     */   protected long playerId;
/*     */   protected long userId;
/*     */   protected ExteMapProxy proxy;
/*     */   private boolean isDBInit;
/*     */   private boolean needSaveToDB;
/*     */   
/*     */   protected AbstractExteMapComponent(Class<? extends IExteMapEntity> clazz, long playerId) {
/*  24 */     this.proxy = new ExteMapProxy(clazz);
/*  25 */     this.playerId = playerId;
/*  26 */     this.proxy.setPlayerId(playerId);
/*     */   }
/*     */   
/*     */   public boolean isDBInit() {
/*  30 */     return this.isDBInit;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getPlayerId() {
/*  35 */     return this.playerId;
/*     */   }
/*     */   
/*     */   public void setPlayerId(long playerId) {
/*  39 */     this.playerId = playerId;
/*     */   }
/*     */   
/*     */   public long getUserId() {
/*  43 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(long userId) {
/*  47 */     this.userId = userId;
/*     */   }
/*     */   
/*     */   public ExteMapProxy getProxy() {
/*  51 */     return this.proxy;
/*     */   }
/*     */   
/*     */   public void maybeSaveToDB() {
/*  55 */     if (this.needSaveToDB)
/*  56 */       saveToDB(); 
/*     */   }
/*     */   
/*     */   public void setNeedSaveToDB(boolean needSaveToDB) {
/*  60 */     this.needSaveToDB = needSaveToDB;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveToDB() {
/*  65 */     return this.proxy.save();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean saveAllToDB() {
/*  70 */     return this.proxy.saveAll();
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/*  75 */     getFromDB();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getFromDB() {
/*  80 */     if (this.proxy.get()) {
/*  81 */       this.isDBInit = true;
/*  82 */       return true;
/*     */     } 
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T getEntity(String key) {
/*  91 */     return (T)getProxy().getEntity(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntity(T entity) {
/* 100 */     getProxy().addEntity((IExteMapEntity)entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEntity(T entity) {
/* 109 */     getProxy().removeEntity((IExteMapEntity)entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean reset(int time) {
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void build(long playerId) {}
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\AbstractExteMapComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */