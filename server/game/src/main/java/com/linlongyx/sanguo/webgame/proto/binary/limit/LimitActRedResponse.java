/*    */ package com.linlongyx.sanguo.webgame.proto.binary.limit;
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
/*    */ public class LimitActRedResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   
/*    */   public LimitActRedResponse() {
/* 21 */     this.eventResponseId = 22704;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public LimitActRedResponse(short retCode) {
/* 26 */     this.eventResponseId = 22704;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.actId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LimitActRedResponse clone() throws CloneNotSupportedException {
/* 43 */     LimitActRedResponse clone = (LimitActRedResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.actId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"actId\":" + this.actId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\limit\LimitActRedResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */