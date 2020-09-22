/*    */ package com.linlongyx.sanguo.webgame.proto.binary.title;
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
/*    */ public class TitleWearResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int title;
/*    */   
/*    */   public TitleWearResponse() {
/* 21 */     this.eventResponseId = 23803;
/* 22 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public TitleWearResponse(short retCode) {
/* 26 */     this.eventResponseId = 23803;
/* 27 */     this.codeType = 2;
/* 28 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.title);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.title = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public TitleWearResponse clone() throws CloneNotSupportedException {
/* 43 */     TitleWearResponse clone = (TitleWearResponse)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.title = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"title\":" + this.title + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\title\TitleWearResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */