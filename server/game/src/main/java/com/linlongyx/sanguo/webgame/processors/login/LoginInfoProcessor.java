/*     */ package com.linlongyx.sanguo.webgame.processors.login;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.log.LogUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.PlayerUtil;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LoginInfoProcessor
/*     */   extends ProcessorBase<LoginInfoRequest, LoginInfoResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  29 */     this.response = (ResponseBase)new LoginInfoResponse();
/*     */   }
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, LoginInfoRequest request) {
/*     */     UserComponent userComponent;
/*  35 */     short recode = LoginUtil.loginPreCheck(playerSession, request.white, request.serverStatus);
/*  36 */     if (recode != 0) {
/*  37 */       return recode;
/*     */     }
/*  39 */     int serverId = request.serverId;
/*  40 */     if (request.serverId == 0) {
/*  41 */       return 10067;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  46 */     if (serverId != MContext.getServerIdInt() && !ConstantService.hefuServers.contains(Integer.valueOf(serverId))) {
/*  47 */       return 10067;
/*     */     }
/*  49 */     Player player = (Player)LookUpService.get(request.userId, serverId);
/*     */     
/*  51 */     short retNeedSign = LoginUtil.checkNeedSign(player, request.sign, request.userId, request.loginTime, request.white);
/*  52 */     if (retNeedSign != 0) {
/*  53 */       return retNeedSign;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  58 */     if (null != player && serverId > 0 && serverId != VersionUtil.getServerIdByPlayerId(player.getPlayerId())) {
/*  59 */       PlayerSession oldPlayerSession = (PlayerSession)player.getSession();
/*  60 */       player.setSession(playerSession);
/*  61 */       playerSession.setPlayer((IPlayer)player);
/*  62 */       if (null != oldPlayerSession) {
/*  63 */         LoginUtil.loginReplace((IPlayerSession)oldPlayerSession);
/*  64 */         oldPlayerSession.setStatus(ISession.Status.RE_CONNECTING);
/*  65 */         oldPlayerSession.getTcpSender().close();
/*     */       } 
/*  67 */       player = null;
/*     */     } 
/*     */ 
/*     */     
/*  71 */     if (null == player) {
/*     */       
/*  73 */       userComponent = LookUpService.getUserComponent(request.userId);
/*  74 */       if (null == userComponent) {
/*  75 */         userComponent = new UserComponent(request.userId);
/*  76 */         userComponent.init();
/*     */       } 
/*     */       
/*  79 */       if (!userComponent.isDBInit()) {
/*  80 */         userComponent.initUser(request.userId, "", request.sign, 1);
/*     */       }
/*  82 */       userComponent.setUid(request.uid);
/*  83 */       userComponent.setToken(request.sign);
/*  84 */       userComponent.saveToDB();
/*  85 */       player = new Player(playerSession);
/*  86 */       player.setUserId(request.userId);
/*  87 */       player.addComponent((IComponent)userComponent);
/*     */       
/*  89 */       player.setSession(playerSession);
/*  90 */       playerSession.setPlayer((IPlayer)player);
/*     */     } else {
/*  92 */       userComponent = (UserComponent)player.getComponent(UserComponent.class);
/*  93 */       userComponent.setToken(request.sign);
/*  94 */       userComponent.saveToDB();
/*  95 */       PlayerSession oldPlayerSession = (PlayerSession)player.getSession();
/*  96 */       player.setSession(playerSession);
/*  97 */       playerSession.setPlayer((IPlayer)player);
/*  98 */       if (null != oldPlayerSession) {
/*  99 */         LoginUtil.loginReplace((IPlayerSession)oldPlayerSession);
/* 100 */         oldPlayerSession.setStatus(ISession.Status.RE_CONNECTING);
/* 101 */         oldPlayerSession.getTcpSender().close();
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     userComponent.setToken(request.sign);
/* 106 */     Set<PlayerInfo> playerList = ((UserEntity)userComponent.getEntity()).getPlayers();
/* 107 */     if (null != playerList) {
/* 108 */       if (serverId == 0) {
/* 109 */         ((LoginInfoResponse)this.response).players.addAll(playerList);
/*     */       } else {
/* 111 */         for (PlayerInfo playerInfo : playerList) {
/* 112 */           if (VersionUtil.getServerIdByPlayerId(playerInfo.playerId) == serverId) {
/* 113 */             ((LoginInfoResponse)this.response).players.add(playerInfo);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 119 */     LogUtil.errorLog(new Object[] { "LoginInfoProcessor::handleRequest", Long.valueOf(request.userId), Integer.valueOf(request.serverId), Long.valueOf(request.orderByPlayerId) });
/* 120 */     userComponent.setServerId(serverId);
/*     */ 
/*     */     
/* 123 */     if (PlayerUtil.getPlatformType() == 1 && request.isNewUser == 1 && request.orderByPlayerId != 0L)
/*     */     {
/* 125 */       if (userComponent.getOrderByPlayerId() == 0L) {
/* 126 */         userComponent.setOrderByPlayerId(request.orderByPlayerId);
/*     */       }
/*     */     }
/*     */     
/* 130 */     userComponent.saveAllToDB();
/*     */     
/* 132 */     ((LoginInfoResponse)this.response).userId = request.userId;
/* 133 */     ((LoginInfoResponse)this.response).version = MContext.getVersion();
/* 134 */     ((LoginInfoResponse)this.response).tag = MContext.getTag();
/* 135 */     ((LoginInfoResponse)this.response).key = playerSession.getKey();
/* 136 */     playerSession.setStatus(ISession.Status.CONNECTED);
/* 137 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\LoginInfoProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */