/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SceneActorData
/*    */ {
/*    */   public long id;
/*    */   public byte type;
/*    */   public int objectId;
/*    */   public short bornX;
/*    */   public short bornY;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 18 */     ProtocolUtil.writeLong(out, this.id);
/* 19 */     ProtocolUtil.writeByte(out, this.type);
/* 20 */     ProtocolUtil.writeInt(out, this.objectId);
/* 21 */     ProtocolUtil.writeShort(out, this.bornX);
/* 22 */     ProtocolUtil.writeShort(out, this.bornY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 27 */     this.id = ProtocolUtil.readUTFBinLong(in);
/* 28 */     this.type = ProtocolUtil.readUTFBinByte(in);
/* 29 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 30 */     this.bornX = ProtocolUtil.readUTFBinShort(in);
/* 31 */     this.bornY = ProtocolUtil.readUTFBinShort(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SceneActorData clone() throws CloneNotSupportedException {
/* 36 */     SceneActorData clone = (SceneActorData)super.clone();
/* 37 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 42 */     this.id = 0L;
/* 43 */     this.type = 0;
/* 44 */     this.objectId = 0;
/* 45 */     this.bornX = 0;
/* 46 */     this.bornY = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     String retVal = "{\"id\":" + this.id + ",\"type\":" + this.type + ",\"objectId\":" + this.objectId + ",\"bornX\":" + this.bornX + ",\"bornY\":" + this.bornY + "}";
/* 52 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SceneActorData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */