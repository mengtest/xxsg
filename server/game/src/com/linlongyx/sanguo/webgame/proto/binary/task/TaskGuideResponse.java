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
/*    */ public class TaskGuideResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int guideId;
/*    */   
/*    */   public TaskGuideResponse() {
/* 21 */     this.eventResponseId = 22208;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TaskGuideResponse(short retCode) {
/* 26 */     this.eventResponseId = 22208;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.guideId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.guideId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskGuideResponse clone() throws CloneNotSupportedException {
/* 43 */     TaskGuideResponse clone = (TaskGuideResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.guideId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"guideId\":" + this.guideId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskGuideResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */