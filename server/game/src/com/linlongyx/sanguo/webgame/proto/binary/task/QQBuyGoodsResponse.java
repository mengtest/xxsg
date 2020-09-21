/*    */ package com.linlongyx.sanguo.webgame.proto.binary.task;
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
/*    */ public class QQBuyGoodsResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int type;
/*    */   public int goodsId;
/*    */   public int num;
/*    */   public int sells;
/*    */   
/*    */   public QQBuyGoodsResponse() {
/* 24 */     this.eventResponseId = 22214;
/* 25 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public QQBuyGoodsResponse(short retCode) {
/* 29 */     this.eventResponseId = 22214;
/* 30 */     this.codeType = 2;
/* 31 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 36 */     ProtocolUtil.writeInt(out, this.type);
/* 37 */     ProtocolUtil.writeInt(out, this.goodsId);
/* 38 */     ProtocolUtil.writeInt(out, this.num);
/* 39 */     ProtocolUtil.writeInt(out, this.sells);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 44 */     this.type = ProtocolUtil.readUTFBinInt(in);
/* 45 */     this.goodsId = ProtocolUtil.readUTFBinInt(in);
/* 46 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 47 */     this.sells = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public QQBuyGoodsResponse clone() throws CloneNotSupportedException {
/* 52 */     QQBuyGoodsResponse clone = (QQBuyGoodsResponse)super.clone();
/* 53 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 58 */     this.type = 0;
/* 59 */     this.goodsId = 0;
/* 60 */     this.num = 0;
/* 61 */     this.sells = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     String retVal = "{\"type\":" + this.type + ",\"goodsId\":" + this.goodsId + ",\"num\":" + this.num + ",\"sells\":" + this.sells + "}";
/* 67 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\task\QQBuyGoodsResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */