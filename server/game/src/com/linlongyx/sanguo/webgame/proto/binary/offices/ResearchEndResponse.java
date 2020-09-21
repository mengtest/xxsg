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
/*    */ public class ResearchEndResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int officeId;
/*    */   public int level;
/*    */   public int currJobId;
/*    */   
/*    */   public ResearchEndResponse() {
/* 23 */     this.eventResponseId = 22504;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ResearchEndResponse(short retCode) {
/* 28 */     this.eventResponseId = 22504;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.officeId);
/* 36 */     ProtocolUtil.writeInt(out, this.level);
/* 37 */     ProtocolUtil.writeInt(out, this.currJobId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.officeId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.level = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.currJobId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResearchEndResponse clone() throws CloneNotSupportedException {
/* 49 */     ResearchEndResponse clone = (ResearchEndResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.officeId = 0;
/* 56 */     this.level = 0;
/* 57 */     this.currJobId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"officeId\":" + this.officeId + ",\"level\":" + this.level + ",\"currJobId\":" + this.currJobId + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\ResearchEndResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */