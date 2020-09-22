/*    */ package com.linlongyx.core.framework.logic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerSessionBuilder
/*    */   extends Session.SessionBuilder
/*    */ {
/* 64 */   protected IPlayer player = null;
/*    */ 
/*    */   
/*    */   public void validateAndSetValues() {
/* 68 */     super.validateAndSetValues();
/*    */   }
/*    */   
/*    */   public IPlayerSession build() {
/* 72 */     return new PlayerSession(this);
/*    */   }
/*    */   
/*    */   public PlayerSessionBuilder player(IPlayer player) {
/* 76 */     this.player = player;
/* 77 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\PlayerSession$PlayerSessionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */