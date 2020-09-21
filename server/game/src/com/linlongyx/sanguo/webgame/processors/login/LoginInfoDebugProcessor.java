/*     */ package com.linlongyx.sanguo.webgame.processors.login;
/*     */ 
/*     */ import com.linlongyx.core.framework.base.ProcessorBase;
/*     */ import com.linlongyx.core.framework.context.AppContext;
/*     */ import com.linlongyx.core.framework.logic.IComponent;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserComponent;
/*     */ import com.linlongyx.sanguo.webgame.app.user.UserEntity;
/*     */ import com.linlongyx.sanguo.webgame.common.player.Player;
/*     */ import com.linlongyx.sanguo.webgame.constant.MContext;
/*     */ import com.linlongyx.sanguo.webgame.processors.player.VersionUtil;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoDebugRequest;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.login.LoginInfoDebugResponse;
/*     */ import com.linlongyx.sanguo.webgame.proto.binary.struct.PlayerInfo;
/*     */ import com.linlongyx.sanguo.webgame.service.ConstantService;
/*     */ import com.linlongyx.sanguo.webgame.service.LookUpService;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class LoginInfoDebugProcessor
/*     */   extends ProcessorBase<LoginInfoDebugRequest, LoginInfoDebugResponse>
/*     */ {
/*     */   protected void initResponse() {
/*  30 */     this.response = (ResponseBase)new LoginInfoDebugResponse();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected short handleRequest(IPlayerSession playerSession, LoginInfoDebugRequest request) {
/*     */     UserComponent userComponent;
/*  37 */     if (!LookUpService.getRunning().get())
/*  38 */       return 10067; 
/*  39 */     if (!AppContext.getDebug()) {
/*  40 */       return 10067;
/*     */     }
/*     */     
/*  43 */     if (!Pattern.matches("^[0-9a-zA-Z]{1,64}", request.token)) {
/*  44 */       return 10026;
/*     */     }
/*     */ 
/*     */     
/*  48 */     long userId = Math.abs(request.token.hashCode());
/*  49 */     boolean newtoken = false;
/*  50 */     if (request.token.contains("uid")) {
/*  51 */       userId = Long.parseLong(request.token.substring(request.token.indexOf("uid") + 3));
/*  52 */       newtoken = true;
/*     */     } 
/*  54 */     LogUtils.errorLog(new Object[] { "LoginInfoDebug", request.token, Integer.valueOf(request.token.hashCode()), Long.valueOf(userId) });
/*     */     
/*  56 */     int serverId = request.serverId;
/*  57 */     if (request.serverId == 0) {
/*  58 */       serverId = MContext.getServerIdInt();
/*     */     }
/*     */     
/*  61 */     if (serverId != MContext.getServerIdInt() && !ConstantService.hefuServers.contains(Integer.valueOf(serverId))) {
/*  62 */       return 10067;
/*     */     }
/*  64 */     Player player = (Player)LookUpService.get(userId, serverId);
/*  65 */     if (null != player && serverId != VersionUtil.getServerIdByPlayerId(player.getPlayerId())) {
/*  66 */       PlayerSession oldPlayerSession = (PlayerSession)player.getSession();
/*  67 */       player.setSession(playerSession);
/*  68 */       playerSession.setPlayer((IPlayer)player);
/*  69 */       if (null != oldPlayerSession) {
/*  70 */         LoginUtil.loginReplace((IPlayerSession)oldPlayerSession);
/*  71 */         oldPlayerSession.setStatus(ISession.Status.RE_CONNECTING);
/*  72 */         oldPlayerSession.getTcpSender().close();
/*     */       } 
/*  74 */       player = null;
/*     */     } 
/*     */ 
/*     */     
/*  78 */     if (null == player) {
/*  79 */       userComponent = LookUpService.getUserComponent(userId);
/*  80 */       if (null == userComponent) {
/*  81 */         userComponent = new UserComponent(userId);
/*  82 */         userComponent.init();
/*     */       } 
/*     */       
/*  85 */       if (!userComponent.isDBInit()) {
/*  86 */         userComponent.initUser(userId, "", request.token, 1);
/*     */       }
/*  88 */       if (!newtoken)
/*  89 */         userComponent.setToken(request.token); 
/*  90 */       player = new Player(playerSession);
/*  91 */       player.setUserId(userId);
/*  92 */       player.addComponent((IComponent)userComponent);
/*     */       
/*  94 */       player.setSession(playerSession);
/*  95 */       playerSession.setPlayer((IPlayer)player);
/*     */     } else {
/*  97 */       userComponent = (UserComponent)player.getComponent(UserComponent.class);
/*  98 */       if (!newtoken) {
/*  99 */         userComponent.setToken(request.token);
/*     */       }
/* 101 */       PlayerSession oldPlayerSession = (PlayerSession)player.getSession();
/* 102 */       player.setSession(playerSession);
/* 103 */       playerSession.setPlayer((IPlayer)player);
/* 104 */       if (null != oldPlayerSession) {
/* 105 */         LoginUtil.loginReplace((IPlayerSession)oldPlayerSession);
/* 106 */         oldPlayerSession.setStatus(ISession.Status.RE_CONNECTING);
/* 107 */         oldPlayerSession.getTcpSender().close();
/*     */       } 
/*     */     } 
/* 110 */     if (userComponent.getOrderByPlayerId() == 0L) {
/* 111 */       userComponent.setOrderByPlayerId(1001000002L);
/*     */     }
/* 113 */     ((LoginInfoDebugResponse)this.response).userId = userId;
/* 114 */     Set<PlayerInfo> playerList = ((UserEntity)userComponent.getEntity()).getPlayers();
/* 115 */     if (null != playerList) {
/* 116 */       if (serverId == 0) {
/* 117 */         ((LoginInfoDebugResponse)this.response).players.addAll(playerList);
/*     */       } else {
/*     */         
/* 120 */         for (PlayerInfo playerInfo : playerList) {
/* 121 */           if (VersionUtil.getServerIdByPlayerId(playerInfo.playerId) == serverId) {
/* 122 */             ((LoginInfoDebugResponse)this.response).players.add(playerInfo);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 127 */     userComponent.setServerId(serverId);
/* 128 */     ((LoginInfoDebugResponse)this.response).version = MContext.getVersion();
/* 129 */     ((LoginInfoDebugResponse)this.response).tag = MContext.getTag();
/* 130 */     ((LoginInfoDebugResponse)this.response).key = playerSession.getKey();
/* 131 */     playerSession.setStatus(ISession.Status.CONNECTED);
/*     */     
/* 133 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\processors\login\LoginInfoDebugProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */