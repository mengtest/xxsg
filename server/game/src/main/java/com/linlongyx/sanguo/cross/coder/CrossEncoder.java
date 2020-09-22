/*    */ package com.linlongyx.sanguo.cross.coder;
/*    */ 
/*    */ import com.linlongyx.sanguo.cross.event.Events;
/*    */ import com.linlongyx.sanguo.cross.event.IRequestEvent;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.buffer.Unpooled;
/*    */ import io.netty.channel.ChannelHandler.Sharable;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToMessageEncoder;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ @Sharable
/*    */ public class CrossEncoder
/*    */   extends MessageToMessageEncoder<IRequestEvent>
/*    */ {
/*    */   protected void encode(ChannelHandlerContext channelHandlerContext, IRequestEvent request, List<Object> list) throws Exception {
/* 18 */     ByteBuf buf = channelHandlerContext.alloc().buffer(4);
/* 19 */     buf.writeInt(request.getId());
/* 20 */     ByteBuf msg = Unpooled.wrappedBuffer(new ByteBuf[] { buf, Unpooled.wrappedBuffer(Events.encodeToBytes(request)) });
/*    */     
/* 22 */     list.add(msg);
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\cross\coder\CrossEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */