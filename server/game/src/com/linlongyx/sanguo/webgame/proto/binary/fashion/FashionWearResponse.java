/*    */ package com.linlongyx.sanguo.webgame.proto.binary.fashion;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ @Message
/*    */ public class FashionWearResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int fashionId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 27 */     ProtocolUtil.writeInt(out, this.fashionId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.fashionId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FashionWearResponse clone() throws CloneNotSupportedException {
/* 37 */     FashionWearResponse clone = (FashionWearResponse)super.clone();
/* 38 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 43 */     this.fashionId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     String retVal = "{\"fashionId\":" + this.fashionId + "}";
/* 49 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\fashion\FashionWearResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */