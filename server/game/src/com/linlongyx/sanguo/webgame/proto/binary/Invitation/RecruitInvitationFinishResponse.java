/*    */ package com.linlongyx.sanguo.webgame.proto.binary.Invitation;
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
/*    */ public class RecruitInvitationFinishResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int nextFreeTime;
/*    */   public int recruitInvitation;
/*    */   
/*    */   public RecruitInvitationFinishResponse() {
/* 22 */     this.eventResponseId = 25004;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RecruitInvitationFinishResponse(short retCode) {
/* 27 */     this.eventResponseId = 25004;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.nextFreeTime);
/* 35 */     ProtocolUtil.writeInt(out, this.recruitInvitation);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.nextFreeTime = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.recruitInvitation = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecruitInvitationFinishResponse clone() throws CloneNotSupportedException {
/* 46 */     RecruitInvitationFinishResponse clone = (RecruitInvitationFinishResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.nextFreeTime = 0;
/* 53 */     this.recruitInvitation = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"nextFreeTime\":" + this.nextFreeTime + ",\"recruitInvitation\":" + this.recruitInvitation + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\Invitation\RecruitInvitationFinishResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */