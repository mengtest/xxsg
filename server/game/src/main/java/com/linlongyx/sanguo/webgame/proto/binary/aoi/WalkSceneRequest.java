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
/*    */ public class WalkSceneRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int sceneId;
/*    */   public short flexPosx;
/*    */   public short flexPosy;
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte active;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 33 */     ProtocolUtil.writeInt(out, this.sceneId);
/* 34 */     ProtocolUtil.writeShort(out, this.flexPosx);
/* 35 */     ProtocolUtil.writeShort(out, this.flexPosy);
/* 36 */     ProtocolUtil.writeShort(out, this.posx);
/* 37 */     ProtocolUtil.writeShort(out, this.posy);
/* 38 */     ProtocolUtil.writeByte(out, this.active);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 43 */     this.sceneId = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.flexPosx = ProtocolUtil.readUTFBinShort(in);
/* 45 */     this.flexPosy = ProtocolUtil.readUTFBinShort(in);
/* 46 */     this.posx = ProtocolUtil.readUTFBinShort(in);
/* 47 */     this.posy = ProtocolUtil.readUTFBinShort(in);
/* 48 */     this.active = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WalkSceneRequest clone() throws CloneNotSupportedException {
/* 53 */     WalkSceneRequest clone = (WalkSceneRequest)super.clone();
/* 54 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 59 */     this.sceneId = 0;
/* 60 */     this.flexPosx = 0;
/* 61 */     this.flexPosy = 0;
/* 62 */     this.posx = 0;
/* 63 */     this.posy = 0;
/* 64 */     this.active = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     String retVal = "{\"sceneId\":" + this.sceneId + ",\"flexPosx\":" + this.flexPosx + ",\"flexPosy\":" + this.flexPosy + ",\"posx\":" + this.posx + ",\"posy\":" + this.posy + ",\"active\":" + this.active + "}";
/* 70 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\aoi\WalkSceneRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */