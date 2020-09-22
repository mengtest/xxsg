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
/*    */ public class ConstantSettingRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int value;
/*    */   public String strValue;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.type);
/* 32 */     ProtocolUtil.writeInt(out, this.value);
/* 33 */     ProtocolUtil.writeUTFBinary(out, this.strValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 38 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.value = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.strValue = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConstantSettingRequest clone() throws CloneNotSupportedException {
/* 45 */     ConstantSettingRequest clone = (ConstantSettingRequest)super.clone();
/* 46 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 51 */     this.type = 0;
/* 52 */     this.value = 0;
/* 53 */     this.strValue = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"type\":" + this.type + ",\"value\":" + this.value + ",\"strValue\":" + StringUtil.str2Str(this.strValue) + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\ConstantSettingRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */