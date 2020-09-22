/*    */ package com.linlongyx.sanguo.webgame.proto.binary.offices;
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
/*    */ public class StartResearchResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int officeId;
/*    */   public int endTime;
/*    */   public int status;
/*    */   
/*    */   public StartResearchResponse() {
/* 23 */     this.eventResponseId = 22503;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public StartResearchResponse(short retCode) {
/* 28 */     this.eventResponseId = 22503;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.officeId);
/* 36 */     ProtocolUtil.writeInt(out, this.endTime);
/* 37 */     ProtocolUtil.writeInt(out, this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.officeId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.status = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public StartResearchResponse clone() throws CloneNotSupportedException {
/* 49 */     StartResearchResponse clone = (StartResearchResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.officeId = 0;
/* 56 */     this.endTime = 0;
/* 57 */     this.status = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"officeId\":" + this.officeId + ",\"endTime\":" + this.endTime + ",\"status\":" + this.status + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\StartResearchResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */