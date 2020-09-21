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
/*    */ public class ConstantSettingResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int value;
/*    */   public String strValue;
/*    */   
/*    */   public ConstantSettingResponse() {
/* 24 */     this.eventResponseId = 20014;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public ConstantSettingResponse(short retCode) {
/* 29 */     this.eventResponseId = 20014;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/* 37 */     ProtocolUtil.writeInt(out, this.value);
/* 38 */     ProtocolUtil.writeUTFBinary(out, this.strValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.value = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.strValue = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConstantSettingResponse clone() throws CloneNotSupportedException {
/* 50 */     ConstantSettingResponse clone = (ConstantSettingResponse)super.clone();
/* 51 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 56 */     this.type = 0;
/* 57 */     this.value = 0;
/* 58 */     this.strValue = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     String retVal = "{\"type\":" + this.type + ",\"value\":" + this.value + ",\"strValue\":" + StringUtil.str2Str(this.strValue) + "}";
/* 64 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\ConstantSettingResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */