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
/*    */ public class PlayerPositionRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public long playerId;
/*    */   public int sceneId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeLong(out, this.playerId);
/* 30 */     ProtocolUtil.writeInt(out, this.sceneId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 36 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerPositionRequest clone() throws CloneNotSupportedException {
/* 41 */     PlayerPositionRequest clone = (PlayerPositionRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.playerId = 0L;
/* 48 */     this.sceneId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"playerId\":" + this.playerId + ",\"sceneId\":" + this.sceneId + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\PlayerPositionRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */