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
/*  36 */   private Map<Short, RequestBase> requests = new HashMap<>();
/*     */ 
/*     */   
/*     */   private IPlayerSession playerSession;
/*     */   
/*     */   private MsgDispatcher dispatcher;
/*     */   
/*  43 */   private static Executor executor = Fibers.createExecutorService();
/*     */ 
/*     */ 
/*     */   
/*     */   public WSServerHandler(MsgDispatcher dispatcher) {
/*  48 */     this.dispatcher = dispatcher;
/*     */   }
/*     */   
/*     */   public IPlayerSession getPlayerSession() {
/*  52 */     return this.playerSession;
/*     */   }
/*     */   
/*     */   public void setPlayerSession(IPlayerSession playerSession) {
/*  56 */     this.playerSession = playerSession;
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelActive(ChannelHandlerContext ctx) throws Exception {
/*  61 */     super.channelActive(ctx);
/*  62 */     if (null == this.playerSession) {
/*     */       
/*  64 */       PlayerSession.PlayerSessionBuilder builder = new PlayerSession.PlayerSessionBuilder();
/*  65 */       builder.validateAndSetValues();
/*  66 */       this.playerSession = (IPlayerSession)builder.status(ISession.Status.CONNECTING).isLogin(false).key().writeable(true).build();
/*  67 */       NettyTCPMessageSender wsSender = new NettyTCPMessageSender(ctx.channel());
/*  68 */       this.playerSession.setTcpSender((MessageSender)wsSender);
/*  69 */       this.dispatcher.setPlayerSession(this.playerSession);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
/*  75 */     handleWebSocketFrame(ctx, msg);
/*     */   }
/*     */   
/*     */   private RequestBase decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
/*  79 */     if (in.equals(Unpooled.EMPTY_BUFFER)) {
/*  80 */       closeConnection(ctx);
/*  81 */       return null;
/*     */     } 
/*  83 */     if (in.readableBytes() < 5) return null;
/*     */     
/*  85 */     in.markReaderIndex();
/*  86 */     short requestId = in.readShort();
/*  87 */     byte codeType = in.readByte();
/*  88 */     short bodySize = in.readShort();
/*     */     
/*  90 */     if (in.readableBytes() >= bodySize) {
/*  91 */       RequestBase request = getRequest(requestId);
/*  92 */       if (null == request) {
/*  93 */         in.skipBytes(bodySize);
/*  94 */         return null;
/*     */       } 
/*  96 */       request.reset();
/*     */       
/*  98 */       if (codeType == 2) {
/*  99 */         request.unserial(in);
/* 100 */       } else if (codeType == 1) {
/* 101 */         byte[] bytes = new byte[bodySize];
/* 102 */         in.readBytes(bytes);
/* 103 */         request = (RequestBase)MsgpackUtil.readPack(bytes, request.getClass());
/*     */       } else {
/* 105 */         request.unserial(in);
/*     */       } 
/*     */       
/* 108 */       return request;
/*     */     } 
/* 110 */     in.resetReaderIndex();
/*     */     
/* 112 */     return null;
/*     */   }
/*     */   
/*     */   private RequestBase getRequest(short requestId) {
/* 116 */     RequestBase request = this.requests.get(Short.valueOf(requestId));
/* 117 */     if (null == request) {
/* 118 */       request = MsgDispatcher.getRequest(requestId);
/* 119 */       this.requests.put(Short.valueOf(requestId), request);
/*     */     } 
/* 121 */     return request;
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
/* 126 */     if (frame instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame) {
/* 127 */       RequestBase request = decode(ctx, frame.content());
/* 128 */       if (null == request) {
/*     */         return;
/*     */       }
/* 131 */       short eventId = request.getEventId();
/*     */       
/* 133 */       if (eventId == 11302) {
/* 134 */         executor.execute(() -> this.dispatcher.dispatch(this.playerSession, request));
/*     */       } else {
/* 136 */         if (!this.playerSession.isLogin()) {
/* 137 */           LogUtils.errorLog(new Object[] { "WSServerHandler::handleWebSocketFrame|player not login", this.playerSession, Short.valueOf(eventId) });
/*     */           
/*     */           return;
/*     */         } 
/* 141 */         this.dispatcher.dispatch(this.playerSession, request);
/*     */       }  return;
/*     */     } 
/* 144 */     if (frame instanceof io.netty.handler.codec.http.websocketx.PingWebSocketFrame) {
/*     */       
/* 146 */       System.out.println("ping/pong心跳包");
/* 147 */       ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext ctx) throws Exception {
/* 153 */     playerLogout(ctx);
/* 154 */     super.channelInactive(ctx);
/*     */   }
/*     */   
/*     */   public void closeConnection(ChannelHandlerContext ctx) throws Exception {
/* 158 */     playerLogout(ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void playerLogout(ChannelHandlerContext ctx) {
/*     */     try {
/* 165 */       IPlayer player = this.playerSession.getPlayer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 174 */       if (null != player && 
/* 175 */         this.playerSession.getStatus() != ISession.Status.RE_CONNECTING)
/*     */       {
/*     */ 
/*     */         
/* 179 */         if (this.playerSession.isLogin()) {
/* 180 */           player.logout();
/*     */         }
/* 182 */         this.playerSession.setLogin(false);
/*     */       }
/*     */     
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/*     */     } 
/* 189 */     if (ctx.channel().isActive()) {
/* 190 */       ctx.channel().close();
/*     */     }
/* 192 */     ctx.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
/* 199 */     super.channelUnregistered(ctx);
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\handler\WSServerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */