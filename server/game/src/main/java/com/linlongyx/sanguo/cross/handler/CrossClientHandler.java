/*    */ package com.linlongyx.sanguo.cross.handler;
/*    */ import com.linlongyx.sanguo.cross.MessageCallback;
/*    */ import com.linlongyx.sanguo.cross.event.IRequestEvent;
/*    */ import com.linlongyx.sanguo.cross.event.IResponseEvent;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelFutureListener;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.SimpleChannelInboundHandler;
/*    */ import java.net.SocketAddress;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ @Sharable
/*    */ public class CrossClientHandler extends SimpleChannelInboundHandler<IResponseEvent> {
/* 15 */   private ConcurrentHashMap<String, MessageCallback> mapCallBack = new ConcurrentHashMap<>();
/*    */   
/*    */   private volatile Channel channel;
/*    */   private SocketAddress remoteAddr;
/*    */   
/*    */   public Channel getChannel() {
/* 21 */     return this.channel;
/*    */   }
/*    */   
/*    */   public SocketAddress getRemoteAddr() {
/* 25 */     return this.remoteAddr;
/*    */   }
/*    */   
/*    */   public void channelActive(ChannelHandlerContext ctx) throws Exception {
/* 29 */     super.channelActive(ctx);
/* 30 */     this.channel = ctx.channel();
/* 31 */     this.remoteAddr = this.channel.remoteAddress();
/*    */   }
/*    */   
/*    */   public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
/* 35 */     super.channelRegistered(ctx);
/*    */   }
/*    */   
/*    */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
/* 39 */     ctx.close();
/*    */   }
/*    */   
/*    */   public void close() {
/* 43 */     this.channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
/*    */   }
/*    */   
/*    */   public MessageCallback sendRequest(IRequestEvent request) {
/* 47 */     if (request.isSync()) {
/* 48 */       MessageCallback callBack = new MessageCallback(request);
/* 49 */       this.mapCallBack.put(String.valueOf(request.getId()) + request.getPlayerId(), callBack);
/* 50 */       this.channel.writeAndFlush(request);
/* 51 */       return callBack;
/*    */     } 
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void channelRead0(ChannelHandlerContext ctx, IResponseEvent response) throws Exception {
/* 58 */     System.out.println("client receive result!!!");
/* 59 */     if (response.isSync()) {
/* 60 */       String responseId = String.valueOf(response.getId()) + response.getPlayerId();
/* 61 */       MessageCallback callback = this.mapCallBack.get(responseId);
/* 62 */       if (callback != null) {
/* 63 */         this.mapCallBack.remove(responseId);
/* 64 */         callback.over(response);
/*    */       } 
/*    */     } else {
/* 67 */       MsgDispatcher.dispatch(response);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\handler\CrossClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */