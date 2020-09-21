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
/*    */ public class NormalInvitationFinishResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int cdTime;
/*    */   public int invitationNum;
/*    */   public int invitationTotal;
/*    */   
/*    */   public NormalInvitationFinishResponse() {
/* 23 */     this.eventResponseId = 25003;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public NormalInvitationFinishResponse(short retCode) {
/* 28 */     this.eventResponseId = 25003;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.cdTime);
/* 36 */     ProtocolUtil.writeInt(out, this.invitationNum);
/* 37 */     ProtocolUtil.writeInt(out, this.invitationTotal);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.cdTime = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.invitationNum = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.invitationTotal = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalInvitationFinishResponse clone() throws CloneNotSupportedException {
/* 49 */     NormalInvitationFinishResponse clone = (NormalInvitationFinishResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.cdTime = 0;
/* 56 */     this.invitationNum = 0;
/* 57 */     this.invitationTotal = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"cdTime\":" + this.cdTime + ",\"invitationNum\":" + this.invitationNum + ",\"invitationTotal\":" + this.invitationTotal + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\Invitation\NormalInvitationFinishResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */