/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SceneDynData
/*    */ {
/*    */   public int objectId;
/*    */   public short posx;
/*    */   public short posy;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.objectId);
/* 18 */     ProtocolUtil.writeShort(out, this.posx);
/* 19 */     ProtocolUtil.writeShort(out, this.posy);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.objectId = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.posx = ProtocolUtil.readUTFBinShort(in);
/* 26 */     this.posy = ProtocolUtil.readUTFBinShort(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public SceneDynData clone() throws CloneNotSupportedException {
/* 31 */     SceneDynData clone = (SceneDynData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.objectId = 0;
/* 38 */     this.posx = 0;
/* 39 */     this.posy = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"objectId\":" + this.objectId + ",\"posx\":" + this.posx + ",\"posy\":" + this.posy + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\SceneDynData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */