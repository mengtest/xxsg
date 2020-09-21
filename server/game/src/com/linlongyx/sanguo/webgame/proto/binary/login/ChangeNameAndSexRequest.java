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
/*    */ public class ChangeNameAndSexRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public String playerName;
/*    */   public int sex;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeUTFBinary(out, this.playerName);
/* 31 */     ProtocolUtil.writeInt(out, this.sex);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 36 */     this.playerName = ProtocolUtil.readUTFStr(in);
/* 37 */     this.sex = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public ChangeNameAndSexRequest clone() throws CloneNotSupportedException {
/* 42 */     ChangeNameAndSexRequest clone = (ChangeNameAndSexRequest)super.clone();
/* 43 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 48 */     this.playerName = null;
/* 49 */     this.sex = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String retVal = "{\"playerName\":" + StringUtil.str2Str(this.playerName) + ",\"sex\":" + this.sex + "}";
/* 55 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\login\ChangeNameAndSexRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */