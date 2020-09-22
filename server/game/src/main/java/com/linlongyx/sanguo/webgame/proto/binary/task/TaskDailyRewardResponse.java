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
/*    */ public class TaskDailyRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int id;
/*    */   public int point;
/*    */   
/*    */   public TaskDailyRewardResponse() {
/* 23 */     this.eventResponseId = 22211;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TaskDailyRewardResponse(short retCode) {
/* 28 */     this.eventResponseId = 22211;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     ProtocolUtil.writeInt(out, this.id);
/* 37 */     ProtocolUtil.writeInt(out, this.point);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.point = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TaskDailyRewardResponse clone() throws CloneNotSupportedException {
/* 49 */     TaskDailyRewardResponse clone = (TaskDailyRewardResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.id = 0;
/* 57 */     this.point = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"type\":" + this.type + ",\"id\":" + this.id + ",\"point\":" + this.point + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\TaskDailyRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */