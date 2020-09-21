/*    */ package com.linlongyx.sanguo.webgame.app.rechargeActivity;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RechargeActivityComponent
/*    */   extends AbstractMapComponent<RechargeActivityEntity>
/*    */ {
/*    */   public RechargeActivityComponent(long playerId) {
/* 13 */     super(RechargeActivityEntity.class, playerId);
/*    */   }
/*    */   
/*    */   public String getType() {
/* 17 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 22 */     this.playerId = playerId;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean reset(int time) {
/* 27 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\rechargeActivity\RechargeActivityComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */