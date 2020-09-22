/*    */ package com.linlongyx.sanguo.webgame.proto.binary.bag;
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
/*    */ public class BagMultiUseItemRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int itemId;
/*    */   public int num;
/*    */   public int chooseId;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 30 */     ProtocolUtil.writeInt(out, this.itemId);
/* 31 */     ProtocolUtil.writeInt(out, this.num);
/* 32 */     ProtocolUtil.writeInt(out, this.chooseId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 37 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 38 */     this.num = ProtocolUtil.readUTFBinInt(in);
/* 39 */     this.chooseId = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BagMultiUseItemRequest clone() throws CloneNotSupportedException {
/* 44 */     BagMultiUseItemRequest clone = (BagMultiUseItemRequest)super.clone();
/* 45 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 50 */     this.itemId = 0;
/* 51 */     this.num = 0;
/* 52 */     this.chooseId = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     String retVal = "{\"itemId\":" + this.itemId + ",\"num\":" + this.num + ",\"chooseId\":" + this.chooseId + "}";
/* 58 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\bag\BagMultiUseItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */