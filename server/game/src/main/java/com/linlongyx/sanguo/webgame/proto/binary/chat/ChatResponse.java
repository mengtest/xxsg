/*    */ package com.linlongyx.sanguo.webgame.proto.binary.chat;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class ChatResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long sendPlayerId;
/*    */   public String context;
/*    */   
/*    */   public ChatResponse() {
/* 23 */     this.eventResponseId = 20602;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChatResponse(short retCode) {
/* 28 */     this.eventResponseId = 20602;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.sendPlayerId);
/* 36 */     ProtocolUtil.writeUTFBinary(out, this.context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.sendPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 42 */     this.context = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatResponse clone() throws CloneNotSupportedException {
/* 47 */     ChatResponse clone = (ChatResponse)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.sendPlayerId = 0L;
/* 54 */     this.context = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"sendPlayerId\":" + this.sendPlayerId + ",\"context\":" + StringUtil.str2Str(this.context) + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\chat\ChatResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */