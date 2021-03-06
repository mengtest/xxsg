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
/*    */ public class TaskCompleteNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int id;
/*    */   
/*    */   public TaskCompleteNoticeResponse() {
/* 22 */     this.eventResponseId = 22204;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TaskCompleteNoticeResponse(short retCode) {
/* 27 */     this.eventResponseId = 22204;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.type);
/* 35 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskCompleteNoticeResponse clone() throws CloneNotSupportedException {
/* 46 */     TaskCompleteNoticeResponse clone = (TaskCompleteNoticeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.type = 0;
/* 53 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + this.id + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskCompleteNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */