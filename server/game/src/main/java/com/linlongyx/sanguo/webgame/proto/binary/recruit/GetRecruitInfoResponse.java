/*    */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
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
/*    */ public class GetRecruitInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int freeCount;
/*    */   public int nextFreeTimes;
/*    */   public int times;
/*    */   public int recruitInvitation;
/*    */   public int tenCCYRecruit;
/*    */   
/*    */   public GetRecruitInfoResponse() {
/* 25 */     this.eventResponseId = 24002;
/* 26 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetRecruitInfoResponse(short retCode) {
/* 30 */     this.eventResponseId = 24002;
/* 31 */     this.codeType = 2;
/* 32 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeInt(out, this.freeCount);
/* 38 */     ProtocolUtil.writeInt(out, this.nextFreeTimes);
/* 39 */     ProtocolUtil.writeInt(out, this.times);
/* 40 */     ProtocolUtil.writeInt(out, this.recruitInvitation);
/* 41 */     ProtocolUtil.writeInt(out, this.tenCCYRecruit);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 46 */     this.freeCount = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.nextFreeTimes = ProtocolUtil.readUTFBinInt(in);
/* 48 */     this.times = ProtocolUtil.readUTFBinInt(in);
/* 49 */     this.recruitInvitation = ProtocolUtil.readUTFBinInt(in);
/* 50 */     this.tenCCYRecruit = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GetRecruitInfoResponse clone() throws CloneNotSupportedException {
/* 55 */     GetRecruitInfoResponse clone = (GetRecruitInfoResponse)super.clone();
/* 56 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 61 */     this.freeCount = 0;
/* 62 */     this.nextFreeTimes = 0;
/* 63 */     this.times = 0;
/* 64 */     this.recruitInvitation = 0;
/* 65 */     this.tenCCYRecruit = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     String retVal = "{\"freeCount\":" + this.freeCount + ",\"nextFreeTimes\":" + this.nextFreeTimes + ",\"times\":" + this.times + ",\"recruitInvitation\":" + this.recruitInvitation + ",\"tenCCYRecruit\":" + this.tenCCYRecruit + "}";
/* 71 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\GetRecruitInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */