/*    */ package com.linlongyx.sanguo.webgame.proto.binary.zodiac;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class ZodiacTaskRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int actId;
/*    */   public int taskId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.actId);
/* 30 */     ProtocolUtil.writeInt(out, this.taskId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.actId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.taskId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ZodiacTaskRequest clone() throws CloneNotSupportedException {
/* 41 */     ZodiacTaskRequest clone = (ZodiacTaskRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.actId = 0;
/* 48 */     this.taskId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"actId\":" + this.actId + ",\"taskId\":" + this.taskId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\zodiac\ZodiacTaskRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */