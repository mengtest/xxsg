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
/*    */ public class LoginInfoRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long userId;
/*    */   public long loginTime;
/*    */   public String sign;
/*    */   public int white;
/*    */   public int serverStatus;
/*    */   public int serverId;
/*    */   public byte isNewUser;
/*    */   public long orderByPlayerId;
/*    */   public String uid;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 37 */     ProtocolUtil.writeLong(out, this.userId);
/* 38 */     ProtocolUtil.writeLong(out, this.loginTime);
/* 39 */     ProtocolUtil.writeUTFBinary(out, this.sign);
/* 40 */     ProtocolUtil.writeInt(out, this.white);
/* 41 */     ProtocolUtil.writeInt(out, this.serverStatus);
/* 42 */     ProtocolUtil.writeInt(out, this.serverId);
/* 43 */     ProtocolUtil.writeByte(out, this.isNewUser);
/* 44 */     ProtocolUtil.writeLong(out, this.orderByPlayerId);
/* 45 */     ProtocolUtil.writeUTFBinary(out, this.uid);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 50 */     this.userId = ProtocolUtil.readUTFBinLong(in);
/* 51 */     this.loginTime = ProtocolUtil.readUTFBinLong(in);
/* 52 */     this.sign = ProtocolUtil.readUTFStr(in);
/* 53 */     this.white = ProtocolUtil.readUTFBinInt(in);
/* 54 */     this.serverStatus = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.serverId = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.isNewUser = ProtocolUtil.readUTFBinByte(in);
/* 57 */     this.orderByPlayerId = ProtocolUtil.readUTFBinLong(in);
/* 58 */     this.uid = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public LoginInfoRequest clone() throws CloneNotSupportedException {
/* 63 */     LoginInfoRequest clone = (LoginInfoRequest)super.clone();
/* 64 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 69 */     this.userId = 0L;
/* 70 */     this.loginTime = 0L;
/* 71 */     this.sign = null;
/* 72 */     this.white = 0;
/* 73 */     this.serverStatus = 0;
/* 74 */     this.serverId = 0;
/* 75 */     this.isNewUser = 0;
/* 76 */     this.orderByPlayerId = 0L;
/* 77 */     this.uid = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     String retVal = "{\"userId\":" + this.userId + ",\"loginTime\":" + this.loginTime + ",\"sign\":" + StringUtil.str2Str(this.sign) + ",\"white\":" + this.white + ",\"serverStatus\":" + this.serverStatus + ",\"serverId\":" + this.serverId + ",\"isNewUser\":" + this.isNewUser + ",\"orderByPlayerId\":" + this.orderByPlayerId + ",\"uid\":" + this.uid + "}";
/* 83 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\LoginInfoRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */