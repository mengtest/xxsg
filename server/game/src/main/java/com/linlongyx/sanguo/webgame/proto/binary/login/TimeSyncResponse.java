/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class TimeSyncResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int timestamp;
/*    */   
/*    */   public TimeSyncResponse() {
/* 21 */     this.eventResponseId = 20005;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TimeSyncResponse(short retCode) {
/* 26 */     this.eventResponseId = 20005;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.timestamp);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.timestamp = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TimeSyncResponse clone() throws CloneNotSupportedException {
/* 43 */     TimeSyncResponse clone = (TimeSyncResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.timestamp = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"timestamp\":" + this.timestamp + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\TimeSyncResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */