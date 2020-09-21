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
/*    */ @Message
/*    */ public class EndPlayFightResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public byte result;
/*    */   
/*    */   public EndPlayFightResponse() {
/* 19 */     this.eventResponseId = 20201;
/* 20 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public EndPlayFightResponse(short retCode) {
/* 24 */     this.eventResponseId = 20201;
/* 25 */     this.codeType = 2;
/* 26 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeByte(out, this.result);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.result = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EndPlayFightResponse clone() throws CloneNotSupportedException {
/* 41 */     EndPlayFightResponse clone = (EndPlayFightResponse)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.result = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     String retVal = "{\"result\":" + this.result + "}";
/* 53 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fight\EndPlayFightResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */