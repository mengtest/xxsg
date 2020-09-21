/*    */ package com.linlongyx.sanguo.webgame.proto.binary.friend;
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
/*    */ public class FriendSerachRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String name;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.name = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public FriendSerachRequest clone() throws CloneNotSupportedException {
/* 39 */     FriendSerachRequest clone = (FriendSerachRequest)super.clone();
/* 40 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 45 */     this.name = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"name\":" + StringUtil.str2Str(this.name) + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\friend\FriendSerachRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */