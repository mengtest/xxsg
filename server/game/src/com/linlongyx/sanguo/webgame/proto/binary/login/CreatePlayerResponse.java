/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class CreatePlayerResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   
/*    */   public CreatePlayerResponse() {
/* 21 */     this.eventResponseId = 20002;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CreatePlayerResponse(short retCode) {
/* 26 */     this.eventResponseId = 20002;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeLong(out, this.playerId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CreatePlayerResponse clone() throws CloneNotSupportedException {
/* 43 */     CreatePlayerResponse clone = (CreatePlayerResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.playerId = 0L;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"playerId\":" + this.playerId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\CreatePlayerResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */