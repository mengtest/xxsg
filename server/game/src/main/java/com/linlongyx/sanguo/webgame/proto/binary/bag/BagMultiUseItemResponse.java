/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*    */ public class BagMultiUseItemResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int itemId;
/*    */   public int num;
/*    */   public int chooseId;
/*    */   
/*    */   public BagMultiUseItemResponse() {
/* 23 */     this.eventResponseId = 20713;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BagMultiUseItemResponse(short retCode) {
/* 28 */     this.eventResponseId = 20713;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.itemId);
/* 36 */     ProtocolUtil.writeInt(out, this.num);
/* 37 */     ProtocolUtil.writeInt(out, this.chooseId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.chooseId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagMultiUseItemResponse clone() throws CloneNotSupportedException {
/* 49 */     BagMultiUseItemResponse clone = (BagMultiUseItemResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.itemId = 0;
/* 56 */     this.num = 0;
/* 57 */     this.chooseId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"itemId\":" + this.itemId + ",\"num\":" + this.num + ",\"chooseId\":" + this.chooseId + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagMultiUseItemResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */