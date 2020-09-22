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
/*    */ public class PlayerPositionResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public long playerId;
/*    */   public short x;
/*    */   public short y;
/*    */   
/*    */   public PlayerPositionResponse() {
/* 23 */     this.eventResponseId = 20110;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public PlayerPositionResponse(short retCode) {
/* 28 */     this.eventResponseId = 20110;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeLong(out, this.playerId);
/* 36 */     ProtocolUtil.writeShort(out, this.x);
/* 37 */     ProtocolUtil.writeShort(out, this.y);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 43 */     this.x = ProtocolUtil.readUTFBinShort(in);
/* 44 */     this.y = ProtocolUtil.readUTFBinShort(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerPositionResponse clone() throws CloneNotSupportedException {
/* 49 */     PlayerPositionResponse clone = (PlayerPositionResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.playerId = 0L;
/* 56 */     this.x = 0;
/* 57 */     this.y = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"playerId\":" + this.playerId + ",\"x\":" + this.x + ",\"y\":" + this.y + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\PlayerPositionResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */