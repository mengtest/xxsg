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
/*    */ public class PartnerSoulLevelResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long pid;
/*    */   public int soulLevel;
/*    */   
/*    */   public PartnerSoulLevelResponse() {
/* 22 */     this.eventResponseId = 23313;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PartnerSoulLevelResponse(short retCode) {
/* 27 */     this.eventResponseId = 23313;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeLong(out, this.pid);
/* 35 */     ProtocolUtil.writeInt(out, this.soulLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.pid = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.soulLevel = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PartnerSoulLevelResponse clone() throws CloneNotSupportedException {
/* 46 */     PartnerSoulLevelResponse clone = (PartnerSoulLevelResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.pid = 0L;
/* 53 */     this.soulLevel = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"pid\":" + this.pid + ",\"soulLevel\":" + this.soulLevel + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\partner\PartnerSoulLevelResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */