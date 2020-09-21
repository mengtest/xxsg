/*     */ package com.linlongyx.sanguo.logclient;
/*     */ 
/*     */ import com.linlongyx.sanguo.logclient.handler.MsgPackDecoder;
/*     */ import com.linlongyx.sanguo.logclient.handler.MsgPackEncoder;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelPipeline;
/*     */ import io.netty.channel.socket.SocketChannel;
/*     */ import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
/*     */ import io.netty.handler.codec.LengthFieldPrepender;
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
/*     */ class null
/*     */   extends ChannelInitializer<SocketChannel>
/*     */ {
/*     */   protected void initChannel(SocketChannel socketChannel) throws Exception {
/* 126 */     ChannelPipeline pipeline = socketChannel.pipeline();
/* 127 */     pipeline.addLast("lengthDecoder", (ChannelHandler)new LengthFieldBasedFrameDecoder(2147483647, 0, 2, 0, 2));
/* 128 */     pipeline.addLast("msgDecoder", (ChannelHandler)new MsgPackDecoder());
/* 129 */     pipeline.addLast(new ChannelHandler[] { (ChannelHandler)ChatLogClient.access$000(this.this$0) });
/* 130 */     pipeline.addLast("lengthFieldPrepender", (ChannelHandler)new LengthFieldPrepender(2, false));
/* 131 */     pipeline.addLast("msgEncoder", (ChannelHandler)new MsgPackEncoder());
/*     */   }
/*     */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\ChatLogClient$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */