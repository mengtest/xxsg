/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SceneAOI
/*    */ {
/*    */   public long playerId;
/*    */   public byte type;
/*    */   public short flexPosx;
/*    */   public short flexPosy;
/*    */   public short posx;
/*    */   public short posy;
/*    */   public byte active;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeLong(out, this.playerId);
/* 22 */     ProtocolUtil.writeByte(out, this.type);
/* 23 */     ProtocolUtil.writeShort(out, this.flexPosx);
/* 24 */     ProtocolUtil.writeShort(out, this.flexPosy);
/* 25 */     ProtocolUtil.writeShort(out, this.posx);
/* 26 */     ProtocolUtil.writeShort(out, this.posy);
/* 27 */     ProtocolUtil.writeByte(out, this.active);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.playerId = ProtocolUtil.readUTFBinLong(in);
/* 33 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 34 */     this.flexPosx = ProtocolUtil.readUTFBinShort(in);
/* 35 */     this.flexPosy = ProtocolUtil.readUTFBinShort(in);
/* 36 */     this.posx = ProtocolUtil.readUTFBinShort(in);
/* 37 */     this.posy = ProtocolUtil.readUTFBinShort(in);
/* 38 */     this.active = ProtocolUtil.readUTFBinByte(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SceneAOI clone() throws CloneNotSupportedException {
/* 43 */     SceneAOI clone = (SceneAOI)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.playerId = 0L;
/* 50 */     this.type = 0;
/* 51 */     this.flexPosx = 0;
/* 52 */     this.flexPosy = 0;
/* 53 */     this.posx = 0;
/* 54 */     this.posy = 0;
/* 55 */     this.active = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"playerId\":" + this.playerId + ",\"type\":" + this.type + ",\"flexPosx\":" + this.flexPosx + ",\"flexPosy\":" + this.flexPosy + ",\"posx\":" + this.posx + ",\"posy\":" + this.posy + ",\"active\":" + this.active + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SceneAOI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */