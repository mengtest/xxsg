/*    */ package com.linlongyx.sanguo.webgame.proto.binary.shop;
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
/*    */ public class BuyGoodsRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int type;
/*    */   public int goodsId;
/*    */   public int num;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.type);
/* 31 */     ProtocolUtil.writeInt(out, this.goodsId);
/* 32 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.goodsId = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BuyGoodsRequest clone() throws CloneNotSupportedException {
/* 44 */     BuyGoodsRequest clone = (BuyGoodsRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.type = 0;
/* 51 */     this.goodsId = 0;
/* 52 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"type\":" + this.type + ",\"goodsId\":" + this.goodsId + ",\"num\":" + this.num + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\shop\BuyGoodsRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */