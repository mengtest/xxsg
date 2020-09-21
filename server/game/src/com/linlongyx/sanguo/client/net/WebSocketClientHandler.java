/*     */ package com.linlongyx.sanguo.client.net;
/*     */ 
/*     */ import com.linlongyx.core.framework.protocol.RequestBase;
/*     */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*     */ import com.linlongyx.core.utils.GsonUtil;
/*     */ import com.linlongyx.sanguo.client.actor.Actor;
/*     */ import com.linlongyx.sanguo.client.actor.NettyTCPMessageSender;
/*     */ import com.linlongyx.sanguo.client.processors.MsgDispatcher;
/*     */ import com.linlongyx.sanguo.webgame.util.MsgpackUtil;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelPromise;
/*     */ import io.netty.channel.SimpleChannelInboundHandler;
/*     */ import io.netty.handler.codec.http.FullHttpResponse;
/*     */ import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
/*     */ import io.netty.handler.codec.http.websocketx.WebSocketFrame;
/*     */ import io.netty.util.CharsetUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WebSocketClientHandler
/*     */   extends SimpleChannelInboundHandler<Object>
/*     */ {
/*     */   private final WebSocketClientHandshaker handshaker;
/*     */   private ChannelPromise handshakeFuture;
/*     */   private static final int HEAD_LEN = 7;
/*  34 */   private Map<Short, RequestBase> requests = new HashMap<>();
/*     */   
/*  36 */   private MsgDispatcher dispatcher = new MsgDispatcher();
/*     */   
/*     */   private Actor client;
/*     */   
/*     */   private long userId;
/*     */   
/*     */   public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
/*  43 */     this.handshaker = handshaker;
/*     */   }
/*     */   
/*     */   public ChannelFuture handshakeFuture() {
/*  47 */     return (ChannelFuture)this.handshakeFuture;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlerAdded(ChannelHandlerContext ctx) {
/*  52 */     this.handshakeFuture = ctx.newPromise();
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelActive(ChannelHandlerContext ctx) {
/*  57 */     this.handshaker.handshake(ctx.channel());
/*  58 */     if (null == this.client) {
/*  59 */       this.client = new Actor();
/*  60 */       NettyTCPMessageSender tcpSender = new NettyTCPMessageSender(ctx.channel());
/*  61 */       this.client.setTcpSender(tcpSender);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext ctx) {
/*  67 */     System.out.println("WebSocket Client disconnected!");
/*     */   }
/*     */ 
/*     */   
/*     */   public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
/*  72 */     Channel ch = ctx.channel();
/*  73 */     if (!this.handshaker.isHandshakeComplete()) {
/*  74 */       this.handshaker.finishHandshake(ch, (FullHttpResponse)msg);
/*     */       
/*  76 */       this.handshakeFuture.setSuccess();
/*     */       
/*     */       return;
/*     */     } 
/*  80 */     if (msg instanceof FullHttpResponse) {
/*  81 */       FullHttpResponse response = (FullHttpResponse)msg;
/*  82 */       throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response
/*  83 */           .status() + ", content=" + response
/*  84 */           .content().toString(CharsetUtil.UTF_8) + ')');
/*     */     } 
/*     */     
/*  87 */     WebSocketFrame frame = (WebSocketFrame)msg;
/*  88 */     if (frame instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame) {
/*  89 */       ResponseBase response = decode(ctx, frame.content());
/*  90 */       if (null != response)
/*  91 */         MsgDispatcher.dispatch(this.client, response);  return;
/*     */     } 
/*  93 */     if (!(frame instanceof io.netty.handler.codec.http.websocketx.PongWebSocketFrame))
/*     */     {
/*  95 */       if (frame instanceof io.netty.handler.codec.http.websocketx.CloseWebSocketFrame)
/*     */       {
/*  97 */         ch.close(); } 
/*     */     }
/*     */   }
/*     */   
/*     */   private ResponseBase decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
/* 102 */     if (in.equals(Unpooled.EMPTY_BUFFER)) {
/* 103 */       closeConnection(ctx);
/* 104 */       return null;
/*     */     } 
/* 106 */     if (in.readableBytes() < 7) return null;
/*     */     
/* 108 */     in.markReaderIndex();
/* 109 */     short responseId = in.readShort();
/* 110 */     byte codeType = in.readByte();
/* 111 */     short retCode = in.readShort();
/* 112 */     short bodySize = in.readShort();
/*     */     
/* 114 */     if (0 != retCode) {
/* 115 */       ResponseBase response = getResponse(responseId);
/* 116 */       response.retCode = retCode;
/* 117 */       return response;
/*     */     } 
/*     */     
/* 120 */     if (in.readableBytes() >= bodySize) {
/* 121 */       ResponseBase response = getResponse(responseId);
/* 122 */       if (null == response) {
/* 123 */         in.skipBytes(bodySize);
/* 124 */         return null;
/*     */       } 
/* 126 */       response.reset();
/*     */       
/* 128 */       if (codeType == 2) {
/* 129 */         response.unserial(in);
/* 130 */       } else if (codeType == 1) {
/* 131 */         byte[] bytes = new byte[bodySize];
/* 132 */         in.readBytes(bytes);
/* 133 */         String json = MsgpackUtil.pack.read(bytes).toString();
/* 134 */         json = json.substring(1, json.length() - 1);
/* 135 */         json = json.replace("\\", "");
/*     */         
/* 137 */         response = (ResponseBase)GsonUtil.fromJson(json, response.getClass());
/*     */       } else {
/* 139 */         response.unserial(in);
/*     */       } 
/* 141 */       return response;
/*     */     } 
/* 143 */     in.resetReaderIndex();
/*     */     
/* 145 */     return null;
/*     */   }
/*     */   
/*     */   private ResponseBase getResponse(short responseId) {
/* 149 */     ResponseBase responseBase = MsgDispatcher.getResponse(responseId);
/* 150 */     return responseBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
/* 155 */     cause.printStackTrace();
/* 156 */     if (!this.handshakeFuture.isDone()) {
/* 157 */       this.handshakeFuture.setFailure(cause);
/*     */     }
/* 159 */     ctx.close();
/*     */   }
/*     */   
/*     */   public void closeConnection(ChannelHandlerContext ctx) throws Exception {
/* 163 */     ctx.close();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\client\net\WebSocketClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */