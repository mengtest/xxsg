/*    */ package com.linlongyx.sanguo.webgame.proto.binary.recruit;
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
/*    */ public class BuyRedRecruitItemResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int itemId;
/*    */   public int itemNum;
/*    */   
/*    */   public BuyRedRecruitItemResponse() {
/* 22 */     this.eventResponseId = 24005;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public BuyRedRecruitItemResponse(short retCode) {
/* 27 */     this.eventResponseId = 24005;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.itemId);
/* 35 */     ProtocolUtil.writeInt(out, this.itemNum);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.itemNum = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public BuyRedRecruitItemResponse clone() throws CloneNotSupportedException {
/* 46 */     BuyRedRecruitItemResponse clone = (BuyRedRecruitItemResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.itemId = 0;
/* 53 */     this.itemNum = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"itemId\":" + this.itemId + ",\"itemNum\":" + this.itemNum + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\recruit\BuyRedRecruitItemResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */