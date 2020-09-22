/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
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
/*    */ public class GroupNoticeChangeResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public String notice;
/*    */   
/*    */   public GroupNoticeChangeResponse() {
/* 22 */     this.eventResponseId = 21106;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupNoticeChangeResponse(short retCode) {
/* 27 */     this.eventResponseId = 21106;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeUTFBinary(out, this.notice);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.notice = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupNoticeChangeResponse clone() throws CloneNotSupportedException {
/* 44 */     GroupNoticeChangeResponse clone = (GroupNoticeChangeResponse)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.notice = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"notice\":" + StringUtil.str2Str(this.notice) + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupNoticeChangeResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */