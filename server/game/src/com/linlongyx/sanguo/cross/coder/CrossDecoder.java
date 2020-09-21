/*    */ package com.linlongyx.sanguo.cross.coder;
/*    */ 
/*    */ import com.linlongyx.sanguo.cross.event.Events;
/*    */ import com.linlongyx.sanguo.cross.event.IResponseEvent;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ @Sharable
/*    */ public class CrossDecoder
/*    */   extends MessageToMessageDecoder<ByteBuf>
/*    */ {
/*    */   protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
/* 17 */     int eventId = byteBuf.readInt();
/* 18 */     byte[] bytes = new byte[byteBuf.readableBytes()];
/* 19 */     byteBuf.readBytes(bytes);
/* 20 */     IResponseEvent event = Events.decodeToEvent(eventId, bytes);
/* 21 */     if (event != null)
/* 22 */       list.add(event); 
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\coder\CrossDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */