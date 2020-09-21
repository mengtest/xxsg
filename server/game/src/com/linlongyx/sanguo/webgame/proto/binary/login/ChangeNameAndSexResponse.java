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
/*    */ public class ChangeNameAndSexResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public String playerName;
/*    */   public int sex;
/*    */   
/*    */   public ChangeNameAndSexResponse() {
/* 23 */     this.eventResponseId = 20018;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ChangeNameAndSexResponse(short retCode) {
/* 28 */     this.eventResponseId = 20018;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 36 */     ProtocolUtil.writeInt(out, this.sex);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 41 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 42 */     this.sex = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChangeNameAndSexResponse clone() throws CloneNotSupportedException {
/* 47 */     ChangeNameAndSexResponse clone = (ChangeNameAndSexResponse)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.playerName = null;
/* 54 */     this.sex = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 59 */     String retVal = "{\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"sex\":" + this.sex + "}";
/* 60 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\ChangeNameAndSexResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */