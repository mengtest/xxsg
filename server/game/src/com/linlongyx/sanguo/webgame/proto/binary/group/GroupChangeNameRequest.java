/*    */ package com.linlongyx.sanguo.webgame.proto.binary.group;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class GroupChangeNameRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String groupName;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.groupName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.groupName = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GroupChangeNameRequest clone() throws CloneNotSupportedException {
/* 39 */     GroupChangeNameRequest clone = (GroupChangeNameRequest)super.clone();
/* 40 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 45 */     this.groupName = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"groupName\":" + StringUtil.str2Str(this.groupName) + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\group\GroupChangeNameRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */