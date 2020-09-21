/*    */ package com.linlongyx.sanguo.webgame.app.limitexchange;
/*    */ 
/*    */ import com.linlongyx.core.framework.dao.entity.IEntity;
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LimitExchangeComponent
/*    */   extends AbstractMapComponent<LimitExchangeEntity>
/*    */ {
/*    */   public LimitExchangeComponent(long playerId) {
/* 16 */     super(LimitExchangeEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 21 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 26 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void init() {
/* 32 */     super.init();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 37 */     if (time == 0);
/*    */ 
/*    */     
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public LimitExchangeEntity getEntity(int id) {
/* 44 */     LimitExchangeEntity limitExchangeEntity = (LimitExchangeEntity)getEntity(String.valueOf(id));
/* 45 */     if (null == limitExchangeEntity) {
/* 46 */       limitExchangeEntity = new LimitExchangeEntity(this.playerId, id);
/* 47 */       limitExchangeEntity.setOpen(true);
/* 48 */       addEntity((IEntity)limitExchangeEntity);
/*    */     } 
/*    */     
/* 51 */     return limitExchangeEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Integer> getLimitExchangeGoods(int id) {
/* 56 */     return getEntity(id).getLimitExchangeGoods();
/*    */   }
/*    */   public void setLimitExchangeGoods(int id, Map<Integer, Integer> limitExchangeGoods) {
/* 59 */     getEntity(id).setLimitExchangeGoods(limitExchangeGoods);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\limitexchange\LimitExchangeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */