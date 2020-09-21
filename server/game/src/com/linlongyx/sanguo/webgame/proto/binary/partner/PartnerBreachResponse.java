/*    */ package com.linlongyx.sanguo.webgame.proto.binary.partner;
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
/*    */ public class PartnerBreachResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int breachCount;
/*    */   
/*    */   public PartnerBreachResponse() {
/* 21 */     this.eventResponseId = 23305;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerBreachResponse(short retCode) {
/* 26 */     this.eventResponseId = 23305;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.breachCount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.breachCount = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerBreachResponse clone() throws CloneNotSupportedException {
/* 43 */     PartnerBreachResponse clone = (PartnerBreachResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.breachCount = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"breachCount\":" + this.breachCount + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerBreachResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */