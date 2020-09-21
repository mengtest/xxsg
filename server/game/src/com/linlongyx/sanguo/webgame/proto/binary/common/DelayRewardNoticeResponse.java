/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
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
/*    */ public class DelayRewardNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public DelayRewardNoticeResponse() {
/* 20 */     this.eventResponseId = 21005;
/* 21 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DelayRewardNoticeResponse(short retCode) {
/* 25 */     this.eventResponseId = 21005;
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
/*    */   public DelayRewardNoticeResponse clone() throws CloneNotSupportedException {
/* 40 */     DelayRewardNoticeResponse clone = (DelayRewardNoticeResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\DelayRewardNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */