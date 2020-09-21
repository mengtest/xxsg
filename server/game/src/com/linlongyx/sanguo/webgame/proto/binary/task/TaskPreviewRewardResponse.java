/*    */ package com.linlongyx.sanguo.webgame.proto.binary.task;
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
/*    */ public class TaskPreviewRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int id;
/*    */   
/*    */   public TaskPreviewRewardResponse() {
/* 21 */     this.eventResponseId = 22216;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TaskPreviewRewardResponse(short retCode) {
/* 26 */     this.eventResponseId = 22216;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskPreviewRewardResponse clone() throws CloneNotSupportedException {
/* 43 */     TaskPreviewRewardResponse clone = (TaskPreviewRewardResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"id\":" + this.id + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskPreviewRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */