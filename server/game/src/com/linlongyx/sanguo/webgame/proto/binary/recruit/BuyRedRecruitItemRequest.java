/*    */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
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
/*    */ public class BuyRedRecruitItemRequest
/*    */   extends RequestBase
/*    */ {
/*    */   public int itemId;
/*    */   public int itemNum;
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 29 */     ProtocolUtil.writeInt(out, this.itemId);
/* 30 */     ProtocolUtil.writeInt(out, this.itemNum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 35 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 36 */     this.itemNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BuyRedRecruitItemRequest clone() throws CloneNotSupportedException {
/* 41 */     BuyRedRecruitItemRequest clone = (BuyRedRecruitItemRequest)super.clone();
/* 42 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 47 */     this.itemId = 0;
/* 48 */     this.itemNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     String retVal = "{\"itemId\":" + this.itemId + ",\"itemNum\":" + this.itemNum + "}";
/* 54 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\BuyRedRecruitItemRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */