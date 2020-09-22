/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cross;
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
/*    */ public class CrossLoginRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String token;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeUTFBinary(out, this.token);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 34 */     this.token = ProtocolUtil.readUTFStr(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CrossLoginRequest clone() throws CloneNotSupportedException {
/* 39 */     CrossLoginRequest clone = (CrossLoginRequest)super.clone();
/* 40 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 45 */     this.token = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 50 */     String retVal = "{\"token\":" + StringUtil.str2Str(this.token) + "}";
/* 51 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\center\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cross\CrossLoginRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */