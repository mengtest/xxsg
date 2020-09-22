/*    */ package com.linlongyx.core.framework.logic.moblie;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.ISession;
/*    */ import com.linlongyx.core.framework.logic.Session;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerSessionBuilder
/*    */   extends Session.SessionBuilder
/*    */ {
/* 73 */   protected IPlayer player = null;
/*    */ 
/*    */   
/*    */   public void validateAndSetValues() {
/* 77 */     super.validateAndSetValues();
/*    */   }
/*    */   
/*    */   public PlayerSession build() {
/* 81 */     return new PlayerSession(this);
/*    */   }
/*    */   
/*    */   public PlayerSessionBuilder player(IPlayer player) {
/* 85 */     this.player = player;
/* 86 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\core\framework\logic\moblie\PlayerSession$PlayerSessionBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */