/*    */ package com.linlongyx.sanguo.webgame.proto.binary.stage;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.StageData;
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
/*    */ @Message
/*    */ public class ActivityStageResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public StageData data = new StageData();
/*    */   
/*    */   public ActivityStageResponse() {
/* 22 */     this.eventResponseId = 25609;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ActivityStageResponse(short retCode) {
/* 27 */     this.eventResponseId = 25609;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.data = new StageData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivityStageResponse clone() throws CloneNotSupportedException {
/* 45 */     ActivityStageResponse clone = (ActivityStageResponse)super.clone();
/* 46 */     clone.data = this.data.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"data\":" + this.data.toString() + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\stage\ActivityStageResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */