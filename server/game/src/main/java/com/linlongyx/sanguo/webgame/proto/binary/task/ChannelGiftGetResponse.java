/*    */ package com.linlongyx.sanguo.webgame.proto.binary.task;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ public class ChannelGiftGetResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   public int state;
/*    */   
/*    */   public ChannelGiftGetResponse() {
/* 22 */     this.eventResponseId = 22215;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChannelGiftGetResponse(short retCode) {
/* 27 */     this.eventResponseId = 22215;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.id);
/* 35 */     ProtocolUtil.writeInt(out, this.state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.state = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChannelGiftGetResponse clone() throws CloneNotSupportedException {
/* 46 */     ChannelGiftGetResponse clone = (ChannelGiftGetResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.id = 0;
/* 53 */     this.state = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"id\":" + this.id + ",\"state\":" + this.state + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\ChannelGiftGetResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */