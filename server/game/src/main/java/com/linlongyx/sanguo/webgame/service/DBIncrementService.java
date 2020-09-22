/*    */ package com.linlongyx.sanguo.webgame.service;
/*    */ 
/*    */ import com.linlongyx.core.framework.concurrent.Fibers;
/*    */ import com.linlongyx.core.framework.dao.entity.IMapEntity;
/*    */ import com.linlongyx.core.framework.dao.proxy.MapProxy;
/*    */ import com.linlongyx.core.framework.service.IBussinessService;
/*    */ import com.linlongyx.sanguo.webgame.app.increment.IncrementEntity;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import java.util.concurrent.ScheduledExecutorService;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DBIncrementService
/*    */   implements IBussinessService
/*    */ {
/*    */   private static final String INCREMENT_KEY = "increment_key";
/* 20 */   protected final ScheduledExecutorService scheduler = Fibers.createScheduler();
/*    */   
/* 22 */   private MapProxy mapProxy = new MapProxy(IncrementEntity.class, "increment_key");
/*    */ 
/*    */   
/*    */   public DBIncrementService() {
/* 26 */     this.scheduler.scheduleWithFixedDelay(this::saveToDB, 1L, 1L, TimeUnit.MINUTES);
/*    */   }
/*    */   
/*    */   public void initFromDB() {
/* 30 */     this.mapProxy.get();
/*    */     
/* 32 */     checkPlayerId();
/*    */   }
/*    */ 
/*    */   
/*    */   private void checkPlayerId() {
/* 37 */     String key = "playerId";
/* 38 */     String newkey = "playerId_" + MContext.getServerId();
/* 39 */     IncrementEntity entity = (IncrementEntity)this.mapProxy.getEntity(key);
/* 40 */     if (null != entity) {
/* 41 */       long value = entity.getIncrement().longValue();
/* 42 */       this.mapProxy.delEntity(key);
/*    */       
/* 44 */       IncrementEntity newentity = (IncrementEntity)this.mapProxy.createProxy(newkey);
/* 45 */       this.mapProxy.addEntity((IMapEntity)newentity);
/* 46 */       newentity.setInitVal(value);
/* 47 */       this.mapProxy.setUpdate(newkey, "increment");
/* 48 */       this.mapProxy.save();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveToDB() {
/* 54 */     this.mapProxy.saveAll();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Long generate(String key) {
/* 63 */     return generate(key, 0L);
/*    */   }
/*    */   
/*    */   public synchronized Long generate(String key, long initVal) {
/* 67 */     IncrementEntity entity = (IncrementEntity)this.mapProxy.getEntity(key);
/* 68 */     if (null == entity) {
/* 69 */       entity = (IncrementEntity)this.mapProxy.createProxy(key);
/* 70 */       this.mapProxy.addEntity((IMapEntity)entity);
/* 71 */       entity.setInitVal(initVal);
/*    */     } 
/* 73 */     long incr = entity.incr();
/* 74 */     this.mapProxy.setUpdate(key, "increment");
/* 75 */     this.mapProxy.save();
/* 76 */     return Long.valueOf(incr);
/*    */   }
/*    */   
/*    */   public synchronized void reset(String key, long initVal) {
/* 80 */     IncrementEntity entity = (IncrementEntity)this.mapProxy.getEntity(key);
/* 81 */     if (null == entity) {
/* 82 */       entity = (IncrementEntity)this.mapProxy.createProxy(key);
/* 83 */       this.mapProxy.addEntity((IMapEntity)entity);
/* 84 */       entity.setInitVal(initVal);
/*    */     } 
/* 86 */     entity.setInitVal(initVal);
/* 87 */     this.mapProxy.setUpdate(key, "increment");
/* 88 */     this.mapProxy.save();
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\service\DBIncrementService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */