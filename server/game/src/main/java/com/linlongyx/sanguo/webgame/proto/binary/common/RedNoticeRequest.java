/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class RedNoticeRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public void serial(ByteBuf out) {}
/*    */   
/*    */   public void unserial(ByteBuf in) {}
/*    */   
/*    */   public RedNoticeRequest clone() throws CloneNotSupportedException {
/* 35 */     RedNoticeRequest clone = (RedNoticeRequest)super.clone();
/* 36 */     return clone;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {}
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     String retVal = "{}";
/* 46 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\RedNoticeRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */