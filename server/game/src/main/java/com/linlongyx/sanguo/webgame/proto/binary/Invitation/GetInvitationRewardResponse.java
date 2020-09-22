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
/*    */ public class GetInvitationRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int value;
/*    */   public long playerId;
/*    */   
/*    */   public GetInvitationRewardResponse() {
/* 23 */     this.eventResponseId = 25002;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GetInvitationRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 25002;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     ProtocolUtil.writeInt(out, this.value);
/* 37 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.value = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GetInvitationRewardResponse clone() throws CloneNotSupportedException {
/* 49 */     GetInvitationRewardResponse clone = (GetInvitationRewardResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.value = 0;
/* 57 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"type\":" + this.type + ",\"value\":" + this.value + ",\"playerId\":" + this.playerId + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\Invitation\GetInvitationRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */