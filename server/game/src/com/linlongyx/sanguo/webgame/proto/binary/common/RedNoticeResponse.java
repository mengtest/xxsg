/*    */ package com.linlongyx.sanguo.webgame.proto.binary.common;
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
/*    */ public class RedNoticeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int sys;
/*    */   public int index;
/*    */   
/*    */   public RedNoticeResponse() {
/* 22 */     this.eventResponseId = 21008;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public RedNoticeResponse(short retCode) {
/* 27 */     this.eventResponseId = 21008;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.sys);
/* 35 */     ProtocolUtil.writeInt(out, this.index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.sys = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.index = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public RedNoticeResponse clone() throws CloneNotSupportedException {
/* 46 */     RedNoticeResponse clone = (RedNoticeResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.sys = 0;
/* 53 */     this.index = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"sys\":" + this.sys + ",\"index\":" + this.index + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\common\RedNoticeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */