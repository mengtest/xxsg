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
/*    */ public class GroupChangeNameResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public String groupName;
/*    */   
/*    */   public GroupChangeNameResponse() {
/* 22 */     this.eventResponseId = 21123;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public GroupChangeNameResponse(short retCode) {
/* 27 */     this.eventResponseId = 21123;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeUTFBinary(out, this.groupName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.groupName = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupChangeNameResponse clone() throws CloneNotSupportedException {
/* 44 */     GroupChangeNameResponse clone = (GroupChangeNameResponse)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.groupName = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     String retVal = "{\"groupName\":" + StringUtil.str2Str(this.groupName) + "}";
/* 56 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupChangeNameResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */