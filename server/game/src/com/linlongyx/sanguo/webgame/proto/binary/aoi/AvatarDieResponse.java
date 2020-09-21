/*    */ package com.linlongyx.sanguo.webgame.proto.binary.aoi;
/*    */ 
/*    */ import com.linlongyx.core.framework.protocol.ResponseBase;
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
/*    */ public class AvatarDieResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public byte type;
/*    */   public int objectId;
/*    */   
/*    */   public AvatarDieResponse() {
/* 23 */     this.eventResponseId = 20112;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public AvatarDieResponse(short retCode) {
/* 28 */     this.eventResponseId = 20112;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeByte(out, this.type);
/* 37 */     ProtocolUtil.writeInt(out, this.objectId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 44 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public AvatarDieResponse clone() throws CloneNotSupportedException {
/* 49 */     AvatarDieResponse clone = (AvatarDieResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.playerId = 0L;
/* 56 */     this.type = 0;
/* 57 */     this.objectId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"playerId\":" + this.playerId + ",\"type\":" + this.type + ",\"objectId\":" + this.objectId + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\AvatarDieResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */