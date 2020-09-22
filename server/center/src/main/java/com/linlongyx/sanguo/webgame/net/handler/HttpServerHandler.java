/*     */ package com.linlongyx.sanguo.webgame.net.handler;
/*     */ import com.linlongyx.sanguo.webgame.net.http.HttpHelper;
/*     */ import com.linlongyx.sanguo.webgame.net.processingevent.ProcessingHttpEvents;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.http.DefaultFullHttpResponse;
/*     */ import io.netty.handler.codec.http.FullHttpRequest;
/*     */ import io.netty.handler.codec.http.HttpMessage;
/*     */ import io.netty.handler.codec.http.HttpMethod;
/*     */ import io.netty.handler.codec.http.HttpVersion;
/*     */ import io.netty.handler.codec.http.QueryStringDecoder;
/*     */ import io.netty.handler.codec.http.multipart.Attribute;
/*     */ import io.netty.handler.codec.http.multipart.HttpDataFactory;
/*     */ import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
/*     */ import io.netty.handler.codec.http.multipart.InterfaceHttpData;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.slf4j.Logger;
/*     */ 
/*     */ public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
/*  25 */   private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
/*     */   
/*     */   private FullHttpRequest fullHttpRequest;
/*     */   
/*  29 */   private final StringBuilder responseContent = new StringBuilder();
/*     */   
/*     */   private HttpPostRequestDecoder decoder;
/*     */   
/*     */   private boolean readingChunks;
/*     */   
/*  35 */   private static final HttpDataFactory factory = (HttpDataFactory)new DefaultHttpDataFactory(16384L);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
/*  40 */     this.fullHttpRequest = msg;
/*     */     
/*  42 */     this.responseContent.setLength(0);
/*     */ 
/*     */ 
/*     */     
/*  46 */     if (this.fullHttpRequest.method().equals(HttpMethod.GET)) {
/*     */       
/*  48 */       QueryStringDecoder decoderQuery = new QueryStringDecoder(this.fullHttpRequest.uri());
/*  49 */       Map<String, List<String>> uriAttributes = decoderQuery.parameters();
/*  50 */       StringBuilder stringBuilder = new StringBuilder();
/*  51 */       uriAttributes.entrySet().forEach(entry -> stringBuilder.append((String)entry.getKey()).append("=").append(((List<String>)entry.getValue()).get(0)).append(","));
/*  52 */       logger.info("HttpServerHandler.channelRead0:" + stringBuilder.toString());
/*     */       
/*  54 */       if (uriAttributes.size() != 0 && HttpHelper.validateSign(uriAttributes)) {
/*  55 */         String value = ProcessingHttpEvents.trigger(uriAttributes);
/*  56 */         this.responseContent.append(value);
/*     */       }
/*     */     
/*  59 */     } else if (this.fullHttpRequest.method().equals(HttpMethod.POST)) {
/*     */       
/*  61 */       this.decoder = new HttpPostRequestDecoder(factory, (HttpRequest)this.fullHttpRequest);
/*  62 */       this.readingChunks = HttpUtil.isTransferEncodingChunked((HttpMessage)this.fullHttpRequest);
/*     */ 
/*     */       
/*  65 */       List<InterfaceHttpData> parmList = this.decoder.getBodyHttpDatas();
/*  66 */       Map<String, List<String>> uriAttributes = new HashMap<>();
/*  67 */       for (InterfaceHttpData parm : parmList) {
/*  68 */         List<String> params = new ArrayList<>();
/*  69 */         Attribute data = (Attribute)parm;
/*  70 */         params.add(data.getValue());
/*  71 */         uriAttributes.put(data.getName(), params);
/*     */       } 
/*  73 */       if (uriAttributes.size() != 0 && HttpHelper.validateSign(uriAttributes)) {
/*  74 */         String value = ProcessingHttpEvents.trigger(uriAttributes);
/*  75 */         this.responseContent.append(value);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     writeResponse(ctx.channel());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeResponse(Channel channel) {
/* 102 */     ByteBuf buf = Unpooled.copiedBuffer(this.responseContent.toString(), CharsetUtil.UTF_8);
/* 103 */     this.responseContent.setLength(0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     boolean close = (this.fullHttpRequest.headers().contains("Connection", "close", true) || (this.fullHttpRequest.protocolVersion().equals(HttpVersion.HTTP_1_0) && !this.fullHttpRequest.headers().contains("Connection", "keep-alive", true)));
/*     */ 
/*     */     
/* 111 */     DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
/* 112 */     defaultFullHttpResponse.headers().set("Content-Type", "text/plain; charset=UTF-8");
/*     */     
/* 114 */     if (!close)
/*     */     {
/*     */       
/* 117 */       defaultFullHttpResponse.headers().set("Content-Length", Integer.valueOf(buf.readableBytes()));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 122 */     ChannelFuture future = channel.writeAndFlush(defaultFullHttpResponse);
/*     */     
/* 124 */     if (close) {
/* 125 */       future.addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
/* 132 */     ctx.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
/* 137 */     logger.error(cause.getMessage());
/* 138 */     ctx.close();
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\net\handler\HttpServerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */