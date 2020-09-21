/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.RequestBase;
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
/*    */ public class EnterConfirmRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public byte type;
/*    */   public int sceneId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeByte(out, this.type);
/* 30 */     ProtocolUtil.writeInt(out, this.sceneId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 36 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnterConfirmRequest clone() throws CloneNotSupportedException {
/* 41 */     EnterConfirmRequest clone = (EnterConfirmRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.type = 0;
/* 48 */     this.sceneId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"type\":" + this.type + ",\"sceneId\":" + this.sceneId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\EnterConfirmRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */