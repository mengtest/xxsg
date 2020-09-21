/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.logic.ISession;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossOfflineNoticeResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.battle.BattleUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoginUtil
/*    */ {
/*    */   public static final int OFFLINE_TYPE_REPLACE = 1;
/*    */   public static final int OFFLINE_TYPE_CROSS_LOGOUT = 2;
/*    */   
/*    */   public static void loginReplace(IPlayerSession playerSession) {
/* 22 */     offlineNotice(playerSession, 1);
/* 23 */     BattleUtil.battlePlayerOffline(playerSession.getPlayer().getPlayerId());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void loginCrossLogout(IPlayerSession playerSession) {
/* 30 */     BattleUtil.battlePlayerOffline(playerSession.getPlayer().getPlayerId());
/* 31 */     offlineNotice(playerSession, 2);
/* 32 */     playerSession.setStatus(ISession.Status.RE_CONNECTING);
/* 33 */     playerSession.getTcpSender().close();
/* 34 */     playerSession.setLogin(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void offlineNotice(IPlayerSession playerSession, int type) {
/* 42 */     CrossOfflineNoticeResponse resp = new CrossOfflineNoticeResponse();
/* 43 */     resp.type = type;
/* 44 */     if (null != playerSession)
/* 45 */       playerSession.sendMessage((ResponseBase)resp); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\LoginUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */