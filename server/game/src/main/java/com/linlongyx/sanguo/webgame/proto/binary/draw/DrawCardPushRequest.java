/*    */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
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
/*    */ public class DrawCardPushRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public void serial(ByteBuf out) {}
/*    */   
/*    */   public void unserial(ByteBuf in) {}
/*    */   
/*    */   public DrawCardPushRequest clone() throws CloneNotSupportedException {
/* 35 */     DrawCardPushRequest clone = (DrawCardPushRequest)super.clone();
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


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardPushRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */