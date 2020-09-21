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
/*    */ public class CheckAccountResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long userId;
/*    */   public long loginTime;
/*    */   public String sign;
/*    */   public int white;
/*    */   public int serverStatus;
/*    */   public int serverId;
/*    */   
/*    */   public CheckAccountResponse() {
/* 27 */     this.eventResponseId = 20011;
/* 28 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public CheckAccountResponse(short retCode) {
/* 32 */     this.eventResponseId = 20011;
/* 33 */     this.codeType = 2;
/* 34 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 39 */     ProtocolUtil.writeLong(out, this.userId);
/* 40 */     ProtocolUtil.writeLong(out, this.loginTime);
/* 41 */     ProtocolUtil.writeUTFBinary(out, this.sign);
/* 42 */     ProtocolUtil.writeInt(out, this.white);
/* 43 */     ProtocolUtil.writeInt(out, this.serverStatus);
/* 44 */     ProtocolUtil.writeInt(out, this.serverId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 49 */     this.userId = ProtocolUtil.readUTFBinLong(in);
/* 50 */     this.loginTime = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.sign = ProtocolUtil.readUTFStr(in);
/* 52 */     this.white = ProtocolUtil.readUTFBinInt(in);
/* 53 */     this.serverStatus = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.serverId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CheckAccountResponse clone() throws CloneNotSupportedException {
/* 59 */     CheckAccountResponse clone = (CheckAccountResponse)super.clone();
/* 60 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 65 */     this.userId = 0L;
/* 66 */     this.loginTime = 0L;
/* 67 */     this.sign = null;
/* 68 */     this.white = 0;
/* 69 */     this.serverStatus = 0;
/* 70 */     this.serverId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     String retVal = "{\"userId\":" + this.userId + ",\"loginTime\":" + this.loginTime + ",\"sign\":" + StringUtil.str2Str(this.sign) + ",\"white\":" + this.white + ",\"serverStatus\":" + this.serverStatus + ",\"serverId\":" + this.serverId + "}";
/* 76 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\CheckAccountResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */