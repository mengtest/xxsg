/*    */ package com.linlongyx.sanguo.logclient.handler;
/*    */ 
/*    */ import com.linlongyx.sanguo.logclient.coder.EventDecoder;
/*    */ import com.linlongyx.sanguo.logclient.coder.ShareCoders;
/*    */ import com.linlongyx.sanguo.logclient.event.Event;
/*    */ import com.linlongyx.sanguo.logclient.event.Events;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageDecoder;
/*    */ import java.util.List;
/*    */ 
/*    */ @Sharable
/*    */ public class MsgPackDecoder
/*    */   extends MessageToMessageDecoder<ByteBuf>
/*    */ {
/*    */   protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
/* 18 */     int eventId = byteBuf.readInt();
/* 19 */     byte[] source = new byte[byteBuf.readableBytes()];
/* 20 */     byteBuf.readBytes(source);
/* 21 */     Event event = Events.decodeToEvent(eventId, (EventDecoder)ShareCoders.MSGPACK_EVENT_DECODER, source);
/* 22 */     list.add(event);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\handler\MsgPackDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */