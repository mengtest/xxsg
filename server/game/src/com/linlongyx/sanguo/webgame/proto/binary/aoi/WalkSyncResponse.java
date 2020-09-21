/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
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
/*    */ public class WalkSyncResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public WalkSyncResponse() {
/* 20 */     this.eventResponseId = 20104;
/* 21 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WalkSyncResponse(short retCode) {
/* 25 */     this.eventResponseId = 20104;
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
/*    */   public WalkSyncResponse clone() throws CloneNotSupportedException {
/* 40 */     WalkSyncResponse clone = (WalkSyncResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\WalkSyncResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */