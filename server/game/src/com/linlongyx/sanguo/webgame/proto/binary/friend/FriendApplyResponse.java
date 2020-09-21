/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
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
/*    */ public class FriendApplyResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public FriendApplyResponse() {
/* 20 */     this.eventResponseId = 23408;
/* 21 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FriendApplyResponse(short retCode) {
/* 25 */     this.eventResponseId = 23408;
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
/*    */   public FriendApplyResponse clone() throws CloneNotSupportedException {
/* 40 */     FriendApplyResponse clone = (FriendApplyResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendApplyResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */