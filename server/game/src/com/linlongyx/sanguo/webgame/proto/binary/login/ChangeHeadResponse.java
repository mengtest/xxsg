/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
/*    */ import com.linlongyx.core.utils.StringUtil;
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
/*    */ public class ChangeHeadResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public String head;
/*    */   
/*    */   public ChangeHeadResponse() {
/* 22 */     this.eventResponseId = 20019;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChangeHeadResponse(short retCode) {
/* 27 */     this.eventResponseId = 20019;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeUTFBinary(out, this.head);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.head = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChangeHeadResponse clone() throws CloneNotSupportedException {
/* 44 */     ChangeHeadResponse clone = (ChangeHeadResponse)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.head = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"head\":" + StringUtil.str2Str(this.head) + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\ChangeHeadResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */