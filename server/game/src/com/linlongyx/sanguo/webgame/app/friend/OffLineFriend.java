/*    */ package com.linlongyx.sanguo.webgame.app.friend;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OffLineFriend
/*    */ {
/* 15 */   private static HashSet<Long> playerIds = new HashSet<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static FriendComponent getFriendComponent(long playerId) {
/* 21 */     if (playerIds.contains(Long.valueOf(playerId))) {
/* 22 */       return null;
/*    */     }
/* 24 */     playerIds.add(Long.valueOf(playerId));
/* 25 */     return (FriendComponent)LookUpService.getComponent(playerId, FriendComponent.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void saveToDB(FriendComponent friendComponent) {
/* 30 */     friendComponent.saveToDB();
/* 31 */     playerIds.remove(Long.valueOf(friendComponent.getPlayerId()));
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\friend\OffLineFriend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */