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
/*    */ public class WalkSyncRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int sceneId;
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte direction;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 31 */     ProtocolUtil.writeInt(out, this.sceneId);
/* 32 */     ProtocolUtil.writeShort(out, this.posx);
/* 33 */     ProtocolUtil.writeShort(out, this.posy);
/* 34 */     ProtocolUtil.writeByte(out, this.direction);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 39 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/* 40 */     this.posx = ProtocolUtil.readUTFBinShort(in);
/* 41 */     this.posy = ProtocolUtil.readUTFBinShort(in);
/* 42 */     this.direction = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WalkSyncRequest clone() throws CloneNotSupportedException {
/* 47 */     WalkSyncRequest clone = (WalkSyncRequest)super.clone();
/* 48 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.sceneId = 0;
/* 54 */     this.posx = 0;
/* 55 */     this.posy = 0;
/* 56 */     this.direction = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     String retVal = "{\"sceneId\":" + this.sceneId + ",\"posx\":" + this.posx + ",\"posy\":" + this.posy + ",\"direction\":" + this.direction + "}";
/* 62 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\WalkSyncRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */