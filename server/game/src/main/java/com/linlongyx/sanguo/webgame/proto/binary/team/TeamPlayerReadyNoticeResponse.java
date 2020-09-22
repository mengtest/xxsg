/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
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
/*    */ public class TeamPlayerReadyNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long teamId;
/*    */   public long playerId;
/*    */   public byte isReady;
/*    */   
/*    */   public TeamPlayerReadyNoticeResponse() {
/* 23 */     this.eventResponseId = 23714;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TeamPlayerReadyNoticeResponse(short retCode) {
/* 28 */     this.eventResponseId = 23714;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.teamId);
/* 36 */     ProtocolUtil.writeLong(out, this.playerId);
/* 37 */     ProtocolUtil.writeByte(out, this.isReady);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.teamId = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 44 */     this.isReady = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamPlayerReadyNoticeResponse clone() throws CloneNotSupportedException {
/* 49 */     TeamPlayerReadyNoticeResponse clone = (TeamPlayerReadyNoticeResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.teamId = 0L;
/* 56 */     this.playerId = 0L;
/* 57 */     this.isReady = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"teamId\":" + this.teamId + ",\"playerId\":" + this.playerId + ",\"isReady\":" + this.isReady + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\TeamPlayerReadyNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */