/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
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
/*    */ public class FunctionTimeNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int functionId;
/*    */   public int status;
/*    */   
/*    */   public FunctionTimeNoticeResponse() {
/* 22 */     this.eventResponseId = 21006;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FunctionTimeNoticeResponse(short retCode) {
/* 27 */     this.eventResponseId = 21006;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.functionId);
/* 35 */     ProtocolUtil.writeInt(out, this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.functionId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FunctionTimeNoticeResponse clone() throws CloneNotSupportedException {
/* 46 */     FunctionTimeNoticeResponse clone = (FunctionTimeNoticeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.functionId = 0;
/* 53 */     this.status = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"functionId\":" + this.functionId + ",\"status\":" + this.status + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\FunctionTimeNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */