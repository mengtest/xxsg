/*    */ package com.linlongyx.sanguo.webgame.proto.binary.zodiac;
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
/*    */ public class ZodiacExchangeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/*    */   public int itemId;
/*    */   public int num;
/*    */   
/*    */   public ZodiacExchangeResponse() {
/* 23 */     this.eventResponseId = 27501;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ZodiacExchangeResponse(short retCode) {
/* 28 */     this.eventResponseId = 27501;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.actId);
/* 36 */     ProtocolUtil.writeInt(out, this.itemId);
/* 37 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ZodiacExchangeResponse clone() throws CloneNotSupportedException {
/* 49 */     ZodiacExchangeResponse clone = (ZodiacExchangeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.actId = 0;
/* 56 */     this.itemId = 0;
/* 57 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"actId\":" + this.actId + ",\"itemId\":" + this.itemId + ",\"num\":" + this.num + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\zodiac\ZodiacExchangeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */