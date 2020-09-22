/*    */ package com.linlongyx.sanguo.webgame.net.handler;
/*    */ 
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.processors.MsgDispatcher;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.SimpleChannelInboundHandler;
/*    */ import io.netty.handler.codec.http.HttpRequest;
/*    */ import java.net.InetSocketAddress;
/*    */ 
/*    */ public class HttpHandler
/*    */   extends SimpleChannelInboundHandler<HttpRequest> {
/*    */   private MsgDispatcher dispatcher;
/*    */   
/*    */   public HttpHandler(MsgDispatcher dispatcher) {
/* 15 */     super(false);
/* 16 */     this.dispatcher = dispatcher;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
/* 21 */     String clientIP = msg.headers().get("X-Forwarded-For");
/* 22 */     if (clientIP == null) {
/*    */       
/* 24 */       InetSocketAddress inSocket = (InetSocketAddress)ctx.channel().remoteAddress();
/* 25 */       clientIP = inSocket.getAddress().getHostAddress();
/*    */     } 
/* 27 */     this.dispatcher.setClientIp(StringUtil.IpStr2IpNum(clientIP));
/* 28 */     ctx.fireChannelRead(msg);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\handler\HttpHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */