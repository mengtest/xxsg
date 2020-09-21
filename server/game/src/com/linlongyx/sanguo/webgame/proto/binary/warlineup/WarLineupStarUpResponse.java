/*    */ package com.linlongyx.sanguo.webgame.proto.binary.warlineup;
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
/*    */ public class WarLineupStarUpResponse
/*    */   extends ResponseBase
/*    */ {
/*    */   public int warlineup;
/*    */   public int star;
/*    */   
/*    */   public WarLineupStarUpResponse() {
/* 22 */     this.eventResponseId = 26504;
/* 23 */     this.codeType = 2;
/*    */   }
/*    */   
/*    */   public WarLineupStarUpResponse(short retCode) {
/* 27 */     this.eventResponseId = 26504;
/* 28 */     this.codeType = 2;
/* 29 */     this.retCode = retCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void serial(ByteBuf out) {
/* 34 */     ProtocolUtil.writeInt(out, this.warlineup);
/* 35 */     ProtocolUtil.writeInt(out, this.star);
/*    */   }
/*    */ 
/*    */   
/*    */   public void unserial(ByteBuf in) {
/* 40 */     this.warlineup = ProtocolUtil.readUTFBinInt(in);
/* 41 */     this.star = ProtocolUtil.readUTFBinInt(in);
/*    */   }
/*    */ 
/*    */   
/*    */   public WarLineupStarUpResponse clone() throws CloneNotSupportedException {
/* 46 */     WarLineupStarUpResponse clone = (WarLineupStarUpResponse)super.clone();
/* 47 */     return clone;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reset() {
/* 52 */     this.warlineup = 0;
/* 53 */     this.star = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     String retVal = "{\"warlineup\":" + this.warlineup + ",\"star\":" + this.star + "}";
/* 59 */     return retVal;
/*    */   }
/*    */ }


/* Location:              D:\xxsg_52gmsy\game\target\classes\!\com\linlongyx\sanguo\webgame\proto\binary\warlineup\WarLineupStarUpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */