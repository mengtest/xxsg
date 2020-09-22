/*    */ package com.linlongyx.sanguo.webgame.proto.binary.struct;
/*    */ 
/*    */ import com.linlongyx.sanguo.webgame.proto.ProtocolUtil;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GoodsData
/*    */ {
/*    */   public int type;
/*    */   public int goodsId;
/*    */   public int sells;
/*    */   public int totalsSells;
/*    */   public int weekSells;
/*    */   public int actSells;
/*    */   public int stock;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 21 */     ProtocolUtil.writeInt(out, this.type);
/* 22 */     ProtocolUtil.writeInt(out, this.goodsId);
/* 23 */     ProtocolUtil.writeInt(out, this.sells);
/* 24 */     ProtocolUtil.writeInt(out, this.totalsSells);
/* 25 */     ProtocolUtil.writeInt(out, this.weekSells);
/* 26 */     ProtocolUtil.writeInt(out, this.actSells);
/* 27 */     ProtocolUtil.writeInt(out, this.stock);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 32 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 33 */     this.goodsId = ProtocolUtil.readUTFBinInt(in);
/* 34 */     this.sells = ProtocolUtil.readUTFBinInt(in);
/* 35 */     this.totalsSells = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.weekSells = ProtocolUtil.readUTFBinInt(in);
/* 37 */     this.actSells = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.stock = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public GoodsData clone() throws CloneNotSupportedException {
/* 43 */     GoodsData clone = (GoodsData)super.clone();
/* 44 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 49 */     this.type = 0;
/* 50 */     this.goodsId = 0;
/* 51 */     this.sells = 0;
/* 52 */     this.totalsSells = 0;
/* 53 */     this.weekSells = 0;
/* 54 */     this.actSells = 0;
/* 55 */     this.stock = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 60 */     String retVal = "{\"type\":" + this.type + ",\"goodsId\":" + this.goodsId + ",\"sells\":" + this.sells + ",\"totalsSells\":" + this.totalsSells + ",\"weekSells\":" + this.weekSells + ",\"actSells\":" + this.actSells + ",\"stock\":" + this.stock + "}";
/* 61 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\struct\GoodsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */