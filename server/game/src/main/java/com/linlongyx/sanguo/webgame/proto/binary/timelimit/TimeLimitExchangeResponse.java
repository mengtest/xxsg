/*    */ package com.linlongyx.sanguo.webgame.proto.binary.timelimit;
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
/*    */ public class TimeLimitExchangeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int packageType;
/*    */   public int num;
/*    */   
/*    */   public TimeLimitExchangeResponse() {
/* 22 */     this.eventResponseId = 25211;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TimeLimitExchangeResponse(short retCode) {
/* 27 */     this.eventResponseId = 25211;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.packageType);
/* 35 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.packageType = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TimeLimitExchangeResponse clone() throws CloneNotSupportedException {
/* 46 */     TimeLimitExchangeResponse clone = (TimeLimitExchangeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.packageType = 0;
/* 53 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"packageType\":" + this.packageType + ",\"num\":" + this.num + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\timelimit\TimeLimitExchangeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */