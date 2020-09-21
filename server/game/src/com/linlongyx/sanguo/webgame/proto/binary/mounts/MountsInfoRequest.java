/*    */ package com.linlongyx.sanguo.webgame.proto.binary.mounts;
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
/*    */ public class MountsInfoRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public void serial(ByteBuf out) {}
/*    */   
/*    */   public void unserial(ByteBuf in) {}
/*    */   
/*    */   public MountsInfoRequest clone() throws CloneNotSupportedException {
/* 35 */     MountsInfoRequest clone = (MountsInfoRequest)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\mounts\MountsInfoRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */