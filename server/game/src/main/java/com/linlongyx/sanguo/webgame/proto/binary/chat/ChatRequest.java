/*    */ package com.linlongyx.sanguo.webgame.proto.binary.chat;
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
/*    */ public class ChatRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long targetPlayerId;
/*    */   public String context;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeLong(out, this.targetPlayerId);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.targetPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 37 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatRequest clone() throws CloneNotSupportedException {
/* 42 */     ChatRequest clone = (ChatRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.targetPlayerId = 0L;
/* 49 */     this.context = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"targetPlayerId\":" + this.targetPlayerId + ",\"context\":" + StringUtil.str2Str(this.context) + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\chat\ChatRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */