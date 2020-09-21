/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bagua;
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
/*    */ public class BaguaSweepRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int insId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.type);
/* 30 */     ProtocolUtil.writeInt(out, this.insId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.insId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaguaSweepRequest clone() throws CloneNotSupportedException {
/* 41 */     BaguaSweepRequest clone = (BaguaSweepRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.type = 0;
/* 48 */     this.insId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"type\":" + this.type + ",\"insId\":" + this.insId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bagua\BaguaSweepRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */