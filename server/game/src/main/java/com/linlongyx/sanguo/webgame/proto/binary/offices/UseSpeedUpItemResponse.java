/*    */ package com.linlongyx.sanguo.webgame.proto.binary.offices;
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
/*    */ public class UseSpeedUpItemResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int itemId;
/*    */   public int itemNum;
/*    */   public int endTime;
/*    */   
/*    */   public UseSpeedUpItemResponse() {
/* 23 */     this.eventResponseId = 22505;
/* 24 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public UseSpeedUpItemResponse(short retCode) {
/* 28 */     this.eventResponseId = 22505;
/* 29 */     this.codeType = 2;
/* 30 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 35 */     ProtocolUtil.writeInt(out, this.itemId);
/* 36 */     ProtocolUtil.writeInt(out, this.itemNum);
/* 37 */     ProtocolUtil.writeInt(out, this.endTime);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 42 */     this.itemId = ProtocolUtil.readUTFBinInt(in);
/* 43 */     this.itemNum = ProtocolUtil.readUTFBinInt(in);
/* 44 */     this.endTime = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public UseSpeedUpItemResponse clone() throws CloneNotSupportedException {
/* 49 */     UseSpeedUpItemResponse clone = (UseSpeedUpItemResponse)super.clone();
/* 50 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 55 */     this.itemId = 0;
/* 56 */     this.itemNum = 0;
/* 57 */     this.endTime = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     String retVal = "{\"itemId\":" + this.itemId + ",\"itemNum\":" + this.itemNum + ",\"endTime\":" + this.endTime + "}";
/* 63 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\offices\UseSpeedUpItemResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */