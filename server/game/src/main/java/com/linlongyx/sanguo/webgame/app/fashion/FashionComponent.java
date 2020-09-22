/*    */ package com.linlongyx.sanguo.webgame.app.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FashionComponent
/*    */   extends AbstractMapComponent<FashionEntity>
/*    */ {
/*    */   public FashionComponent(long playerId) {
/* 13 */     super(FashionEntity.class, playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 18 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 23 */     this.playerId = playerId;
/*    */   }
/*    */   
/*    */   public FashionEntity getFashionEntity(int fashionId) {
/* 27 */     return (FashionEntity)getEntity(String.valueOf(fashionId));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\fashion\FashionComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */