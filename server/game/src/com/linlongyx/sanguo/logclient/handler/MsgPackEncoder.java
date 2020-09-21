/*    */ package com.linlongyx.sanguo.logclient.handler;
/*    */ 
/*    */ import com.linlongyx.sanguo.logclient.event.Event;
/*    */ import com.linlongyx.sanguo.webgame.util.MsgpackUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageEncoder;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ @Sharable
/*    */ public class MsgPackEncoder
/*    */   extends MessageToMessageEncoder<Event>
/*    */ {
/*    */   protected void encode(ChannelHandlerContext channelHandlerContext, Event event, List<Object> list) throws Exception {
/* 18 */     int eventId = event.getId();
/* 19 */     ByteBuf buf = channelHandlerContext.alloc().buffer(4);
/* 20 */     buf.writeInt(eventId);
/* 21 */     ByteBuf msg = Unpooled.wrappedBuffer(new ByteBuf[] { buf, Unpooled.wrappedBuffer(MsgpackUtil.pack.write(event)) });
/* 22 */     list.add(msg);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\logclient\handler\MsgPackEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */