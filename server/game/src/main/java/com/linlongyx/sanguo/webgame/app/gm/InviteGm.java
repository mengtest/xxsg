/*    */ package com.linlongyx.sanguo.webgame.app.gm;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.sanguo.webgame.processors.Invitation.GetInvitationRewardProcessor;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.Invitation.GetInvitationRewardRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InviteGm
/*    */   implements IGm
/*    */ {
/*    */   public void gm(IPlayerSession playerSession, String[] strings) {
/* 14 */     if (strings[2].equals("reward")) {
/* 15 */       GetInvitationRewardRequest request = new GetInvitationRewardRequest();
/* 16 */       int type = Integer.parseInt(strings[3]);
/* 17 */       int value = Integer.parseInt(strings[4]);
/* 18 */       long playerId = Long.parseLong(strings[5]);
/* 19 */       request.type = type;
/* 20 */       request.value = value;
/* 21 */       request.playerId = playerId;
/* 22 */       (new GetInvitationRewardProcessor()).handle(playerSession, (RequestBase)request);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\app\gm\InviteGm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */