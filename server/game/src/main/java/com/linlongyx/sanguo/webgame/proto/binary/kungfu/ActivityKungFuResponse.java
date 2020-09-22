/*    */ package com.linlongyx.sanguo.webgame.proto.binary.kungfu;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KungFuData;
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
/*    */ public class ActivityKungFuResponse
/*    */   extends ResponseBase
/*    */ {
/* 19 */   public KungFuData data = new KungFuData();
/*    */   
/*    */   public ActivityKungFuResponse() {
/* 22 */     this.eventResponseId = 25602;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ActivityKungFuResponse(short retCode) {
/* 27 */     this.eventResponseId = 25602;
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
/* 39 */     this.data = new KungFuData();
/* 40 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivityKungFuResponse clone() throws CloneNotSupportedException {
/* 45 */     ActivityKungFuResponse clone = (ActivityKungFuResponse)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\kungfu\ActivityKungFuResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */