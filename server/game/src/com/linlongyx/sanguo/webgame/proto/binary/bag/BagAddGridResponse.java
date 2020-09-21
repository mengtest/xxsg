/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*    */ public class BagAddGridResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int num;
/*    */   
/*    */   public BagAddGridResponse() {
/* 21 */     this.eventResponseId = 20709;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BagAddGridResponse(short retCode) {
/* 26 */     this.eventResponseId = 20709;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagAddGridResponse clone() throws CloneNotSupportedException {
/* 43 */     BagAddGridResponse clone = (BagAddGridResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"num\":" + this.num + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagAddGridResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */