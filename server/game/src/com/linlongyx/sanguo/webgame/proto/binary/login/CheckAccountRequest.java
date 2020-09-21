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
/*    */ public class CheckAccountRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String accountId;
/*    */   public String pwd;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.accountId);
/* 31 */     ProtocolUtil.writeUTFBinary(out, this.pwd);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.accountId = ProtocolUtil.readUTFStr(in);
/* 37 */     this.pwd = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CheckAccountRequest clone() throws CloneNotSupportedException {
/* 42 */     CheckAccountRequest clone = (CheckAccountRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.accountId = null;
/* 49 */     this.pwd = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"accountId\":" + StringUtil.str2Str(this.accountId) + ",\"pwd\":" + StringUtil.str2Str(this.pwd) + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\CheckAccountRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */