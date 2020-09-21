/*    */ package com.linlongyx.sanguo.webgame.proto.binary.cardbook;
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
/*    */ public class CardBookAskGiftRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int itemId;
/*    */   public int chatType;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.itemId);
/* 30 */     ProtocolUtil.writeInt(out, this.chatType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.chatType = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public CardBookAskGiftRequest clone() throws CloneNotSupportedException {
/* 41 */     CardBookAskGiftRequest clone = (CardBookAskGiftRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.itemId = 0;
/* 48 */     this.chatType = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"itemId\":" + this.itemId + ",\"chatType\":" + this.chatType + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\cardbook\CardBookAskGiftRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */