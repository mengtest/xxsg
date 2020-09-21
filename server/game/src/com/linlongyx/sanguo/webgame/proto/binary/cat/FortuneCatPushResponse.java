/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cat;
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
/*    */ public class FortuneCatPushResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/* 20 */   public KeyValue data = new KeyValue();
/*    */   
/*    */   public FortuneCatPushResponse() {
/* 23 */     this.eventResponseId = 28005;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public FortuneCatPushResponse(short retCode) {
/* 28 */     this.eventResponseId = 28005;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.type);
/* 36 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.data = new KeyValue();
/* 43 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FortuneCatPushResponse clone() throws CloneNotSupportedException {
/* 48 */     FortuneCatPushResponse clone = (FortuneCatPushResponse)super.clone();
/* 49 */     clone.data = this.data.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.type = 0;
/* 56 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"type\":" + this.type + ",\"data\":" + this.data.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cat\FortuneCatPushResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */