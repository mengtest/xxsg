/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossChatByChannelRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public byte type;
/*    */   public String context;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeByte(out, this.type);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 37 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossChatByChannelRequest clone() throws CloneNotSupportedException {
/* 42 */     CrossChatByChannelRequest clone = (CrossChatByChannelRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.type = 0;
/* 49 */     this.context = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"type\":" + this.type + ",\"context\":" + StringUtil.str2Str(this.context) + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossChatByChannelRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */