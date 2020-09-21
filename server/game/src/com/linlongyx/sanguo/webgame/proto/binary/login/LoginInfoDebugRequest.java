/*    */ package com.linlongyx.sanguo.webgame.proto.binary.login;
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
/*    */ public class LoginInfoDebugRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String token;
/*    */   public int serverId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.token);
/* 31 */     ProtocolUtil.writeInt(out, this.serverId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.token = ProtocolUtil.readUTFStr(in);
/* 37 */     this.serverId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LoginInfoDebugRequest clone() throws CloneNotSupportedException {
/* 42 */     LoginInfoDebugRequest clone = (LoginInfoDebugRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.token = null;
/* 49 */     this.serverId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"token\":" + StringUtil.str2Str(this.token) + ",\"serverId\":" + this.serverId + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\LoginInfoDebugRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */