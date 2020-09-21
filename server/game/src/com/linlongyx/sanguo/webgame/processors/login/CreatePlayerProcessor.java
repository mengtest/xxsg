/*    */ package com.linlongyx.sanguo.webgame.processors.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.base.ProcessorBase;
/*    */ import com.linlongyx.core.framework.logic.IPlayer;
/*    */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.LogUtils;
/*    */ import com.linlongyx.core.utils.TimeUtil;
/*    */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*    */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*    */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*    */ import com.linlongyx.sanguo.webgame.log.LogType;
/*    */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CreatePlayerRequest;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.login.CreatePlayerResponse;
/*    */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreatePlayerProcessor
/*    */   extends ProcessorBase<CreatePlayerRequest, CreatePlayerResponse>
/*    */ {
/*    */   protected void initResponse() {
/* 26 */     this.response = (ResponseBase)new CreatePlayerResponse();
/*    */   }
/*    */ 
/*    */   
/*    */   protected synchronized short handleRequest(IPlayerSession playerSession, CreatePlayerRequest request) {
/* 31 */     long aTime = TimeUtil.currentTimeMillis();
/* 32 */     IPlayer player = playerSession.getPlayer();
/*    */ 
/*    */     
/* 35 */     short retCheck = LoginUtil.checkPlayerNameAndSex(request.playerName, request.sex);
/* 36 */     if (retCheck != 0) {
/* 37 */       return retCheck;
/*    */     }
/* 39 */     PlayerComponent playerComponent = (PlayerComponent)player.getComponent(PlayerComponent.class);
/* 40 */     UserComponent userComponent = (UserComponent)player.createIfNotExist(UserComponent.class);
/*    */     
/* 42 */     short retPlayer = LoginUtil.checkPlayerExist(playerComponent, userComponent);
/* 43 */     if (retPlayer != 0) {
/* 44 */       return retPlayer;
/*    */     }
/* 46 */     playerComponent = new PlayerComponent(request.userId, 0L);
/* 47 */     if (userComponent.getServerId() != MContext.getServerIdInt() && !ConstantService.hefuServers.contains(Integer.valueOf(userComponent.getServerId()))) {
/* 48 */       LogUtils.errorLog(new Object[] { "CreatePlayer", request.playerName, Byte.valueOf(request.sex), Integer.valueOf(userComponent.getServerId()), Integer.valueOf(MContext.getServerIdInt()) });
/* 49 */       return 10067;
/*    */     } 
/* 51 */     long playerId = playerComponent.buildPlayer(request.userId, request.playerName, request.sex, userComponent.getServerId());
/* 52 */     playerComponent.setHead("sex:" + request.sex + ":" + request.avatarUrl);
/*    */     
/* 54 */     short retCreate = LoginUtil.createPlayer(playerSession, request.userId, request.playerName, playerId, request.sex, userComponent, playerComponent, player, aTime);
/* 55 */     if (retCreate != 0)
/* 56 */       return retCreate; 
/* 57 */     String date = TimeUtil.getFormatDate();
/* 58 */     LogUtil.gameLog(LogType.FIRST_SCENE, new Object[] { Integer.valueOf(MContext.getServerIdInt()), Long.valueOf(playerComponent.getUserId()), Long.valueOf(player.getPlayerId()), Integer.valueOf(playerComponent.getChannel()), date, date });
/* 59 */     ((CreatePlayerResponse)this.response).playerId = playerId;
/*    */     
/* 61 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\CreatePlayerProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */