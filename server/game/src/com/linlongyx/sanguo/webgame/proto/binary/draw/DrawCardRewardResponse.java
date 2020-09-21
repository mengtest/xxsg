/*    */ package com.linlongyx.sanguo.webgame.proto.binary.draw;
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
/*    */ public class DrawCardRewardResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int drawId;
/*    */   public int num;
/*    */   
/*    */   public DrawCardRewardResponse() {
/* 22 */     this.eventResponseId = 21805;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public DrawCardRewardResponse(short retCode) {
/* 27 */     this.eventResponseId = 21805;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.drawId);
/* 35 */     ProtocolUtil.writeInt(out, this.num);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.drawId = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.num = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public DrawCardRewardResponse clone() throws CloneNotSupportedException {
/* 46 */     DrawCardRewardResponse clone = (DrawCardRewardResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.drawId = 0;
/* 53 */     this.num = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"drawId\":" + this.drawId + ",\"num\":" + this.num + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\draw\DrawCardRewardResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */