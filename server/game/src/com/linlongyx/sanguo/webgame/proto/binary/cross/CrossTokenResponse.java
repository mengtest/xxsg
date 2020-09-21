/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Message
/*    */ public class CrossTokenResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public String token;
/*    */   public String crossUrl;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.token);
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.crossUrl);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.token = ProtocolUtil.readUTFStr(in);
/* 36 */     this.crossUrl = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossTokenResponse clone() throws CloneNotSupportedException {
/* 41 */     CrossTokenResponse clone = (CrossTokenResponse)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.token = null;
/* 48 */     this.crossUrl = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"token\":" + StringUtil.str2Str(this.token) + ",\"crossUrl\":" + StringUtil.str2Str(this.crossUrl) + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossTokenResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */