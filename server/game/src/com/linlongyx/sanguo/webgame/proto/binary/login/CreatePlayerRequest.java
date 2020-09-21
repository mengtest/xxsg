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
/*    */ public class CreatePlayerRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long userId;
/*    */   public byte sex;
/*    */   public String playerName;
/*    */   public String avatarUrl;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 32 */     ProtocolUtil.writeLong(out, this.userId);
/* 33 */     ProtocolUtil.writeByte(out, this.sex);
/* 34 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 35 */     ProtocolUtil.writeUTFBinary(out, this.avatarUrl);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.userId = ProtocolUtil.readUTFBinLong(in);
/* 41 */     this.sex = ProtocolUtil.readUTFBinByte(in);
/* 42 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 43 */     this.avatarUrl = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CreatePlayerRequest clone() throws CloneNotSupportedException {
/* 48 */     CreatePlayerRequest clone = (CreatePlayerRequest)super.clone();
/* 49 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.userId = 0L;
/* 55 */     this.sex = 0;
/* 56 */     this.playerName = null;
/* 57 */     this.avatarUrl = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"userId\":" + this.userId + ",\"sex\":" + this.sex + ",\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"avatarUrl\":" + StringUtil.str2Str(this.avatarUrl) + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\CreatePlayerRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */