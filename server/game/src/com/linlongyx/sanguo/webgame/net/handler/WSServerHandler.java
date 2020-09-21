/*     */ package com.linlongyx.sanguo.webgame.net.handler;
/*     */ 
/*     */ import com.linlongyx.core.framework.communication.MessageSender;
/*     */ import com.linlongyx.core.framework.communication.NettyTCPMessageSender;
/*     */ import com.linlongyx.core.framework.concurrent.Fibers;
/*     */ import com.linlongyx.core.framework.logic.IPlayer;
/*     */ import com.linlongyx.core.framework.logic.IPlayerSession;
/*     */ import com.linlongyx.core.framework.logic.ISession;
/*     */ import com.linlongyx.core.framework.logic.PlayerSession;
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.utils.LogUtils;
/*     */ import com.linlongyx.core.utils.TimeUtil;
/*     */ import com.linlongyx.sanguo.webgame.app.player.PlayerComponent;
/*     */ import com.linlongyx.sanguo.webgame.processors.MsgDispatcher;
/*     */ import com.linlongyx.sanguo.webgame.util.MsgpackUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.SimpleChannelInboundHandler;
/*     */ import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
/*     */ import io.netty.handler.codec.http.websocketx.WebSocketFrame;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.Executor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WSServerHandler
/*     */   extends SimpleChannelInboundHandler<WebSocketFrame>
/*     */ {
/*     */   private static final int HEAD_LEN = 5;
/*  38 */   private Map<Short, RequestBase> requests = new HashMap<>();
/*     */ 
/*     */   
/*     */   private IPlayerSession playerSession;
/*     */   
/*     */   private MsgDispatcher dispatcher;
/*     */   
/*  45 */   private static Executor executor = Fibers.createExecutorService();
/*     */ 
/*     */ 
/*     */   
/*     */   public WSServerHandler(MsgDispatcher dispatcher) {
/*  50 */     this.dispatcher = dispatcher;
/*     */   }
/*     */   
/*     */   public IPlayerSession getPlayerSession() {
/*  54 */     return this.playerSession;
/*     */   }
/*     */   
/*     */   public void setPlayerSession(IPlayerSession playerSession) {
/*  58 */     this.playerSession = playerSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelActive(ChannelHandlerContext ctx) throws Exception {
/*  63 */     super.channelActive(ctx);
/*  64 */     if (null == this.playerSession) {
/*     */       
/*  66 */       PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/*  67 */       builder.validateAndSetValues();
/*  68 */       this.playerSession = (IPlayerSession)builder.status(ISession.Status.CONNECTING).isLogin(false).key().writeable(true).build();
/*  69 */       NettyTCPMessageSender wsSender = new NettyTCPMessageSender(ctx.channel());
/*  70 */       this.playerSession.setTcpSender((MessageSender)wsSender);
/*  71 */       this.dispatcher.setPlayerSession(this.playerSession);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
/*  77 */     handleWebSocketFrame(ctx, msg);
/*     */   }
/*     */   
/*     */   private RequestBase decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
/*  81 */     if (in.equals(Unpooled.EMPTY_BUFFER)) {
/*  82 */       closeConnection(ctx);
/*  83 */       return null;
/*     */     } 
/*  85 */     if (in.readableBytes() < 5) return null;
/*     */     
/*  87 */     in.markReaderIndex();
/*  88 */     short requestId = in.readShort();
/*  89 */     byte codeType = in.readByte();
/*  90 */     short bodySize = in.readShort();
/*     */     
/*  92 */     if (in.readableBytes() >= bodySize) {
/*  93 */       RequestBase request = getRequest(requestId);
/*  94 */       if (null == request) {
/*  95 */         in.skipBytes(bodySize);
/*  96 */         return null;
/*     */       } 
/*  98 */       request.reset();
/*     */       
/* 100 */       if (codeType == 2) {
/* 101 */         request.unserial(in);
/* 102 */       } else if (codeType == 1) {
/* 103 */         byte[] bytes = new byte[bodySize];
/* 104 */         in.readBytes(bytes);
/* 105 */         request = (RequestBase)MsgpackUtil.readPack(bytes, request.getClass());
/*     */       } else {
/* 107 */         request.unserial(in);
/*     */       } 
/*     */       
/* 110 */       return request;
/*     */     } 
/* 112 */     in.resetReaderIndex();
/*     */     
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   private RequestBase getRequest(short requestId) {
/* 118 */     RequestBase request = this.requests.get(Short.valueOf(requestId));
/* 119 */     if (null == request) {
/* 120 */       request = MsgDispatcher.getRequest(requestId);
/* 121 */       this.requests.put(Short.valueOf(requestId), request);
/*     */     } 
/* 123 */     return request;
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
/* 128 */     if (frame instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame) {
/* 129 */       RequestBase request = decode(ctx, frame.content());
/* 130 */       if (null == request) {
/*     */         return;
/*     */       }
/* 133 */       short eventId = request.getEventId();
/*     */       
/* 135 */       if (eventId == 10003 || eventId == 10002 || eventId == 10001 || eventId == 10010) {
/*     */         
/* 137 */         executor.execute(() -> this.dispatcher.dispatch(this.playerSession, request));
/*     */       } else {
/*     */         
/* 140 */         if (!this.playerSession.isLogin()) {
/*     */           
/* 142 */           LogUtils.errorLog(new Object[] { "WSServerHandler::handleWebSocketFrame|player not login", this.playerSession, Short.valueOf(eventId) });
/*     */           
/*     */           return;
/*     */         } 
/* 146 */         this.dispatcher.dispatch(this.playerSession, request);
/*     */       }  return;
/*     */     } 
/* 149 */     if (frame instanceof io.netty.handler.codec.http.websocketx.PingWebSocketFrame) {
/*     */       
/* 151 */       System.out.println("ping/pong心跳包");
/* 152 */       ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 158 */     playerLogout(ctx);
/* 159 */     super.channelInactive(ctx);
/*     */   }
/*     */   
/*     */   public void closeConnection(ChannelHandlerContext ctx) throws Exception {
/* 163 */     playerLogout(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playerLogout(ChannelHandlerContext ctx) {
/*     */     try {
/* 170 */       IPlayer player = this.playerSession.getPlayer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       if (null != player) {
/* 180 */         if (this.playerSession.getStatus() == ISession.Status.RE_CONNECTING) {
/* 181 */           PlayerComponent playerComponent = (PlayerComponent)player.createIfNotExist(PlayerComponent.class);
/* 182 */           playerComponent.setLoginOutTime(TimeUtil.currentTime());
/*     */         } else {
/* 184 */           if (this.playerSession.isLogin()) {
/* 185 */             player.logout();
/*     */           }
/* 187 */           this.playerSession.setLogin(false);
/*     */         }
/*     */       
/*     */       }
/* 191 */     } catch (Exception e) {
/* 192 */       e.printStackTrace();
/*     */     } 
/* 194 */     if (ctx.channel().isActive()) {
/* 195 */       ctx.channel().close();
/*     */     }
/* 197 */     ctx.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
/* 204 */     super.channelUnregistered(ctx);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\net\handler\WSServerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */