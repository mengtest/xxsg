/*    */ package com.linlongyx.sanguo.webgame.processors.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.logic.ISession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.Rijndael;
/*    */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.processors.login.LoginUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossLoginRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.cross.CrossLoginResponse;
/*    */ import com.linlongyx.sanguo.webgame.rmi.LogicRmiUtil;
/*    */ import com.linlongyx.sanguo.webgame.rmi.data.PlayerData;
/*    */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossLoginProcessor
/*    */   extends ProcessorBase<CrossLoginRequest, CrossLoginResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 27 */     this.response = (ResponseBase)new CrossLoginResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected short handleRequest(IPlayerSession playerSession, CrossLoginRequest request) {
/* 32 */     String token = request.token;
/* 33 */     if (!PlayerUtil.tokenToKeyMap.containsKey(token)) {
/* 34 */       Rijndael rijndael = Rijndael.getInstance(MContext.getSecretKey().getBytes());
/* 35 */       String str = rijndael.decryptBase64ToString(request.token, "UTF-8");
/* 36 */       if (str == null) {
/* 37 */         return 11301;
/*    */       }
/* 39 */       PlayerUtil.tokenToKeyMap.put(token, str);
/*    */     } 
/* 41 */     String token_src = (String)PlayerUtil.tokenToKeyMap.get(token);
/* 42 */     String[] strs = token_src.split("_");
/* 43 */     if (strs.length < 3) {
/* 44 */       return 11301;
/*    */     }
/* 46 */     int serverId = Integer.parseInt(strs[0]);
/* 47 */     long userId = Long.parseLong(strs[1]);
/* 48 */     long playerId = Long.parseLong(strs[2]);
/* 49 */     int expire = Integer.parseInt(strs[3]);
/* 50 */     Player player = (Player)LookUpService.getByPlayerId(playerId);
/* 51 */     if (null == player) {
/* 52 */       player = new Player(playerSession);
/* 53 */       PlayerData playerData = LogicRmiUtil.getPlayerData("CrossLoginProcessor::getPlayerData", serverId, playerId);
/* 54 */       if (playerData == null) {
/* 55 */         return 10019;
/*    */       }
/* 57 */       player.setPlayerName(playerData.getPlayerName());
/* 58 */       player.setPlayerData(playerData);
/*    */     }
/* 60 */     else if (playerSession.getTcpSender().getChannel() != player.getSession().getTcpSender().getChannel()) {
/* 61 */       IPlayerSession oldPlayersession = player.getSession();
/* 62 */       if (oldPlayersession != null) {
/* 63 */         LoginUtil.loginReplace(oldPlayersession);
/* 64 */         oldPlayersession.setStatus(ISession.Status.RE_CONNECTING);
/* 65 */         oldPlayersession.getTcpSender().close();
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     player.setSession(playerSession);
/* 70 */     playerSession.setPlayer((IPlayer)player);
/* 71 */     player.setUserId(userId);
/* 72 */     player.setPlayerId(playerId);
/*    */     
/* 74 */     LookUpService.add((IPlayer)player);
/* 75 */     player.login();
/* 76 */     playerSession.setLogin(true);
/* 77 */     playerSession.setStatus(ISession.Status.CONNECTED);
/*    */     
/* 79 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\processors\cross\CrossLoginProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */