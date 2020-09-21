/*    */ package com.linlongyx.sanguo.webgame.app.sanguozhi;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SanGuoZhiComponent
/*    */   extends AbstractMapComponent<SanGuoZhiEntity>
/*    */ {
/*    */   public SanGuoZhiComponent(long playerId) {
/* 12 */     super(SanGuoZhiEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void init() {
/* 17 */     super.init();
/*    */   }
/*    */ 
/*    */   
/*    */   public SanGuoZhiEntity getEntity(int id) {
/* 22 */     SanGuoZhiEntity sanGuoZhiEntity = (SanGuoZhiEntity)getEntity(String.valueOf(id));
/* 23 */     if (null == sanGuoZhiEntity) {
/* 24 */       sanGuoZhiEntity = new SanGuoZhiEntity(this.playerId, id);
/* 25 */       addEntity((IEntity)sanGuoZhiEntity);
/* 26 */       saveToDB();
/*    */     } 
/* 28 */     return sanGuoZhiEntity;
/*    */   }
/*    */   
/*    */   public SanGuoZhiEntity getEntity2(int id) {
/* 32 */     SanGuoZhiEntity sanGuoZhiEntity = (SanGuoZhiEntity)getEntity(String.valueOf(id));
/* 33 */     return sanGuoZhiEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public void createEntitys(int id) {
/* 38 */     SanGuoZhiEntity sanGuoZhiEntity = (SanGuoZhiEntity)getEntity(String.valueOf(id));
/* 39 */     if (null == sanGuoZhiEntity) {
/* 40 */       sanGuoZhiEntity = new SanGuoZhiEntity(this.playerId, id);
/* 41 */       addEntity((IEntity)sanGuoZhiEntity);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void updateValuesDB(int id) {
/* 46 */     getProxy().setUpdate(String.valueOf(id), "values");
/*    */   }
/*    */   
/*    */   public void updateFinishesDB(int id) {
/* 50 */     getProxy().setUpdate(String.valueOf(id), "finishes");
/*    */   }
/*    */   
/*    */   public void updateRewardedDB(int id) {
/* 54 */     getProxy().setUpdate(String.valueOf(id), "rewarded");
/*    */   }
/*    */   
/*    */   public void updateActivityDB(int id) {
/* 58 */     getProxy().setUpdate(String.valueOf(id), "activity");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 63 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 68 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\sanguozhi\SanGuoZhiComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */