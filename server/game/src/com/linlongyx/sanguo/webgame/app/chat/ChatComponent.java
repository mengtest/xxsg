/*    */ package com.linlongyx.sanguo.webgame.app.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.AbstractMapComponent;
/*    */ 
/*    */ 
/*    */ public class ChatComponent
/*    */   extends AbstractMapComponent<ChatEntity>
/*    */ {
/*    */   public ChatComponent(long playerId) {
/* 10 */     super(ChatEntity.class, playerId);
/*    */   }
/*    */   
/*    */   public ChatEntity getEntity(long toId) {
/* 14 */     return (ChatEntity)getEntity(String.valueOf(toId));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getType() {
/* 19 */     return getClass().getSimpleName();
/*    */   }
/*    */ 
/*    */   
/*    */   public void build(long playerId) {
/* 24 */     this.playerId = playerId;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\chat\ChatComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */