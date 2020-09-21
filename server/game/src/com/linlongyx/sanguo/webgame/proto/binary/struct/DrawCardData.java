/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawCardData
/*    */ {
/*    */   public int pos;
/*    */   public int warehouseId;
/*    */   public int id;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 17 */     ProtocolUtil.writeInt(out, this.pos);
/* 18 */     ProtocolUtil.writeInt(out, this.warehouseId);
/* 19 */     ProtocolUtil.writeInt(out, this.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 24 */     this.pos = ProtocolUtil.readUTFBinInt(in);
/* 25 */     this.warehouseId = ProtocolUtil.readUTFBinInt(in);
/* 26 */     this.id = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DrawCardData clone() throws CloneNotSupportedException {
/* 31 */     DrawCardData clone = (DrawCardData)super.clone();
/* 32 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 37 */     this.pos = 0;
/* 38 */     this.warehouseId = 0;
/* 39 */     this.id = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     String retVal = "{\"pos\":" + this.pos + ",\"warehouseId\":" + this.warehouseId + ",\"id\":" + this.id + "}";
/* 45 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\DrawCardData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */