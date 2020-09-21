/*    */ package com.linlongyx.sanguo.webgame.proto.binary.general;
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
/*    */ public class GeneralBuyOrderResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int num;
/*    */   public int time;
/*    */   public int orderNum;
/*    */   
/*    */   public GeneralBuyOrderResponse() {
/* 23 */     this.eventResponseId = 21205;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GeneralBuyOrderResponse(short retCode) {
/* 28 */     this.eventResponseId = 21205;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.num);
/* 36 */     ProtocolUtil.writeInt(out, this.time);
/* 37 */     ProtocolUtil.writeInt(out, this.orderNum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.time = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.orderNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GeneralBuyOrderResponse clone() throws CloneNotSupportedException {
/* 49 */     GeneralBuyOrderResponse clone = (GeneralBuyOrderResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.num = 0;
/* 56 */     this.time = 0;
/* 57 */     this.orderNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"num\":" + this.num + ",\"time\":" + this.time + ",\"orderNum\":" + this.orderNum + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\general\GeneralBuyOrderResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */