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
/*    */ public class TeamInfoResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int restTimes;
/*    */   
/*    */   public TeamInfoResponse() {
/* 21 */     this.eventResponseId = 23704;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TeamInfoResponse(short retCode) {
/* 26 */     this.eventResponseId = 23704;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.restTimes);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.restTimes = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TeamInfoResponse clone() throws CloneNotSupportedException {
/* 43 */     TeamInfoResponse clone = (TeamInfoResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.restTimes = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"restTimes\":" + this.restTimes + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\TeamInfoResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */