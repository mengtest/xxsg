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
/*    */ public class PartnerStarUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int star;
/*    */   public byte isChoose;
/*    */   
/*    */   public PartnerStarUpResponse() {
/* 22 */     this.eventResponseId = 23306;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerStarUpResponse(short retCode) {
/* 27 */     this.eventResponseId = 23306;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.star);
/* 35 */     ProtocolUtil.writeByte(out, this.isChoose);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.star = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.isChoose = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerStarUpResponse clone() throws CloneNotSupportedException {
/* 46 */     PartnerStarUpResponse clone = (PartnerStarUpResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.star = 0;
/* 53 */     this.isChoose = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"star\":" + this.star + ",\"isChoose\":" + this.isChoose + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerStarUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */