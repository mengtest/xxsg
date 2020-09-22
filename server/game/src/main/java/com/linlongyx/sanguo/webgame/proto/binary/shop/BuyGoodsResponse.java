/*    */ package com.linlongyx.sanguo.webgame.proto.binary.shop;
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
/*    */ public class BuyGoodsResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int goodsId;
/*    */   public int num;
/*    */   public int sells;
/*    */   public int totalSells;
/*    */   public int weekSells;
/*    */   public int actSells;
/*    */   public int stock;
/*    */   public int shopLevel;
/*    */   
/*    */   public BuyGoodsResponse() {
/* 29 */     this.eventResponseId = 23102;
/* 30 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BuyGoodsResponse(short retCode) {
/* 34 */     this.eventResponseId = 23102;
/* 35 */     this.codeType = 2;
/* 36 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 41 */     ProtocolUtil.writeInt(out, this.type);
/* 42 */     ProtocolUtil.writeInt(out, this.goodsId);
/* 43 */     ProtocolUtil.writeInt(out, this.num);
/* 44 */     ProtocolUtil.writeInt(out, this.sells);
/* 45 */     ProtocolUtil.writeInt(out, this.totalSells);
/* 46 */     ProtocolUtil.writeInt(out, this.weekSells);
/* 47 */     ProtocolUtil.writeInt(out, this.actSells);
/* 48 */     ProtocolUtil.writeInt(out, this.stock);
/* 49 */     ProtocolUtil.writeInt(out, this.shopLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 54 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 55 */     this.goodsId = ProtocolUtil.readUTFBinInt(in);
/* 56 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 57 */     this.sells = ProtocolUtil.readUTFBinInt(in);
/* 58 */     this.totalSells = ProtocolUtil.readUTFBinInt(in);
/* 59 */     this.weekSells = ProtocolUtil.readUTFBinInt(in);
/* 60 */     this.actSells = ProtocolUtil.readUTFBinInt(in);
/* 61 */     this.stock = ProtocolUtil.readUTFBinInt(in);
/* 62 */     this.shopLevel = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BuyGoodsResponse clone() throws CloneNotSupportedException {
/* 67 */     BuyGoodsResponse clone = (BuyGoodsResponse)super.clone();
/* 68 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 73 */     this.type = 0;
/* 74 */     this.goodsId = 0;
/* 75 */     this.num = 0;
/* 76 */     this.sells = 0;
/* 77 */     this.totalSells = 0;
/* 78 */     this.weekSells = 0;
/* 79 */     this.actSells = 0;
/* 80 */     this.stock = 0;
/* 81 */     this.shopLevel = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     String retVal = "{\"type\":" + this.type + ",\"goodsId\":" + this.goodsId + ",\"num\":" + this.num + ",\"sells\":" + this.sells + ",\"totalSells\":" + this.totalSells + ",\"weekSells\":" + this.weekSells + ",\"actSells\":" + this.actSells + ",\"stock\":" + this.stock + ",\"shopLevel\":" + this.shopLevel + "}";
/* 87 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\shop\BuyGoodsResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */