/*    */ package com.linlongyx.sanguo.webgame.proto.binary.timelimit;
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
/*    */ public class TimeLimitBuyRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int id;
/*    */   public int packageType;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.id);
/* 30 */     ProtocolUtil.writeInt(out, this.packageType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.id = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.packageType = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TimeLimitBuyRequest clone() throws CloneNotSupportedException {
/* 41 */     TimeLimitBuyRequest clone = (TimeLimitBuyRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.id = 0;
/* 48 */     this.packageType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"id\":" + this.id + ",\"packageType\":" + this.packageType + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\timelimit\TimeLimitBuyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */