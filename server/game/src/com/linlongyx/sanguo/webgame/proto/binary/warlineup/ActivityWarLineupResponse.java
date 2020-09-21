/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warlineup;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.WarLineupData;
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
/*    */ public class ActivityWarLineupResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public WarLineupData data = new WarLineupData();
/*    */   
/*    */   public ActivityWarLineupResponse() {
/* 22 */     this.eventResponseId = 26501;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ActivityWarLineupResponse(short retCode) {
/* 27 */     this.eventResponseId = 26501;
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
/* 39 */     this.data = new WarLineupData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivityWarLineupResponse clone() throws CloneNotSupportedException {
/* 45 */     ActivityWarLineupResponse clone = (ActivityWarLineupResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warlineup\ActivityWarLineupResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */