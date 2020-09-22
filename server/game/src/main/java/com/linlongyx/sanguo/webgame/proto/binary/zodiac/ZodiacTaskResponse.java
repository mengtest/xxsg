/*    */ package com.linlongyx.sanguo.webgame.proto.binary.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import com.linlongyx.sanguo.webgame.proto.binary.struct.KeyValue;
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
/*    */ public class ZodiacTaskResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int actId;
/* 20 */   public KeyValue task = new KeyValue();
/*    */   
/*    */   public ZodiacTaskResponse() {
/* 23 */     this.eventResponseId = 27504;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ZodiacTaskResponse(short retCode) {
/* 28 */     this.eventResponseId = 27504;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.actId);
/* 36 */     this.task.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.task = new KeyValue();
/* 43 */     this.task.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ZodiacTaskResponse clone() throws CloneNotSupportedException {
/* 48 */     ZodiacTaskResponse clone = (ZodiacTaskResponse)super.clone();
/* 49 */     clone.task = this.task.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.actId = 0;
/* 56 */     this.task.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"actId\":" + this.actId + ",\"task\":" + this.task.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\zodiac\ZodiacTaskResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */