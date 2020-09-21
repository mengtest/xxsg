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
/*    */ public class TaskCompleteResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int taskId;
/*    */   
/*    */   public TaskCompleteResponse() {
/* 21 */     this.eventResponseId = 22202;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TaskCompleteResponse(short retCode) {
/* 26 */     this.eventResponseId = 22202;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.taskId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.taskId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskCompleteResponse clone() throws CloneNotSupportedException {
/* 43 */     TaskCompleteResponse clone = (TaskCompleteResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.taskId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"taskId\":" + this.taskId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskCompleteResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */