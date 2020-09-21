/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fight;
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
/*    */ public class EndPlayFightResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte result;
/*    */   
/*    */   public EndPlayFightResponse() {
/* 21 */     this.eventResponseId = 20201;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EndPlayFightResponse(short retCode) {
/* 26 */     this.eventResponseId = 20201;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeByte(out, this.result);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.result = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EndPlayFightResponse clone() throws CloneNotSupportedException {
/* 43 */     EndPlayFightResponse clone = (EndPlayFightResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.result = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"result\":" + this.result + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\EndPlayFightResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */