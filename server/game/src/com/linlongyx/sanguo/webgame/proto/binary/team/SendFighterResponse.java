/*    */ package com.linlongyx.sanguo.webgame.proto.binary.team;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import org.msgpack.annotation.Message;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class SendFighterResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public SendFighterResponse() {
/* 20 */     this.eventResponseId = 23707;
/* 21 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SendFighterResponse(short retCode) {
/* 25 */     this.eventResponseId = 23707;
/* 26 */     this.codeType = 2;
/* 27 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {}
/*    */ 
/*    */   
/*    */   public SendFighterResponse clone() throws CloneNotSupportedException {
/* 40 */     SendFighterResponse clone = (SendFighterResponse)super.clone();
/* 41 */     return clone;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {}
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\team\SendFighterResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */