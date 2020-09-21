/*    */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
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
/*    */ public class DrawCardPushResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int drawId;
/* 20 */   public KeyValue data = new KeyValue();
/*    */   
/*    */   public DrawCardPushResponse() {
/* 23 */     this.eventResponseId = 21806;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DrawCardPushResponse(short retCode) {
/* 28 */     this.eventResponseId = 21806;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.drawId);
/* 36 */     this.data.serial(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.drawId = ProtocolUtil.readUTFBinInt(in);
/* 42 */     this.data = new KeyValue();
/* 43 */     this.data.unserial(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DrawCardPushResponse clone() throws CloneNotSupportedException {
/* 48 */     DrawCardPushResponse clone = (DrawCardPushResponse)super.clone();
/* 49 */     clone.data = this.data.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.drawId = 0;
/* 56 */     this.data.reset();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"drawId\":" + this.drawId + ",\"data\":" + this.data.toString() + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardPushResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */