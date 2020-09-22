/*    */ package com.linlongyx.sanguo.webgame.proto.binary.sign;
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
/*    */ @Message
/*    */ public class SignTodayResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int signToday;
/*    */   public int signCount;
/*    */   
/*    */   public SignTodayResponse() {
/* 22 */     this.eventResponseId = 25203;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public SignTodayResponse(short retCode) {
/* 27 */     this.eventResponseId = 25203;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.signToday);
/* 35 */     ProtocolUtil.writeInt(out, this.signCount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.signToday = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.signCount = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SignTodayResponse clone() throws CloneNotSupportedException {
/* 46 */     SignTodayResponse clone = (SignTodayResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.signToday = 0;
/* 53 */     this.signCount = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"signToday\":" + this.signToday + ",\"signCount\":" + this.signCount + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\sign\SignTodayResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */