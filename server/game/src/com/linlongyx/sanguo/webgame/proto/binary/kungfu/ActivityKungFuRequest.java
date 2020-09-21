/*    */ package com.linlongyx.sanguo.webgame.proto.binary.kungfu;
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
/*    */ public class ActivityKungFuRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int kungfu;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 28 */     ProtocolUtil.writeInt(out, this.kungfu);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 33 */     this.kungfu = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ActivityKungFuRequest clone() throws CloneNotSupportedException {
/* 38 */     ActivityKungFuRequest clone = (ActivityKungFuRequest)super.clone();
/* 39 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 44 */     this.kungfu = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     String retVal = "{\"kungfu\":" + this.kungfu + "}";
/* 50 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\kungfu\ActivityKungFuRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */